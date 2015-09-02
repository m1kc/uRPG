/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * @author пользователь
 */
public class MainCanvas extends Canvas implements GameConstants
{
    boolean paused = false;

    int fps;
    int rfps;
    int delay = 15;
    int real = 0;
    String[] console;

    Font f = Font.getDefaultFont();

    int CamX, CamY;

    TiledLayer ground;
    TiledLayer water,waterside;
    int wi = 0;
    Vector sprites = new Vector();
    Vector groundSprites = new Vector();
    Vector animatedSprites = new Vector();
    Vector massives = new Vector();
    Unit hero;
    Vector units = new Vector();

    Unit lastUnit = null;
    Sprite lastSprite = null;

    GameDesign gd = new GameDesign();

    Image grass;

    int[] dlights;
    int nightState = 0;
    boolean night = false;
    boolean rain = false;
    Weather w = new Weather();

    Unit npc1 = null;
    Unit lw = null;

    int frags = 0;

    boolean cinematics = false;
    int cinematicsState = 0;

    public Thread script1;

    /**
     * constructor
     */
    public MainCanvas()
    {
        setFullScreenMode(true);
    }

    /**
     * Инициализация движка
     */
    public void initEngine()
    {
        Thread heapCleaner = new Thread(){
            public void run()
            {
                while (true)
                {
                    System.gc();
                    try
                    {
                        Thread.sleep(2500);
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
            }
        };
        heapCleaner.start();

        Thread fpsController = new Thread(){
            public void run()
            {
                while(true)
                {
                    fps = rfps;
                    rfps = 0;

                    if (uRPG.d.getCurrent() == uRPG.c)
                    {
                        if (fps>20) delay+=5;
                        if (fps<15) delay-=5;
                        if (delay<0) delay=0;
                    }

                    try
                    {
                        real = 1000/(1000/fps-delay);
                    }
                    catch (Throwable ex)
                    {
                        real = 0;
                    }

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
            }
        };
        fpsController.start();

        Thread aniwater = new Thread(){
            public void run()
            {
                int wi = 0;
                while(true)
                {
                    try
                    {
                        wi++;
                        if (wi>=7) wi = 1;
                        getWater().setAnimatedTile(-1, wi);
                        Thread.sleep(250);
                    }
                    catch (Throwable ex)
                    {
                    }
                }
            }
        };
        aniwater.start();

        Thread hRestore = new Thread(){
            public void run()
            {
                while(true)
                {
                    for (int i=0; i<units.size(); i++)
                    {
                        Unit c = (Unit) units.elementAt(i);
                        if (c.hp<c.maxHP) c.hp++;
                    }
                    try
                    {
                        Thread.sleep(250);
                    }
                    catch (InterruptedException ex)
                    {
                    }
                }
            }
        };
        hRestore.start();

        Thread enemyGenerator = new Thread()
        {
            public void run()
            {
                while(true)
                {
                    try
                    {
                        if (lw.hp == 0)
                        {
                            createNewEnemy();
                            frags++;
                        }
                        Thread.sleep(1000);
                    }
                    catch (Throwable ex)
                    {
                    }
                }
            }
        };
        enemyGenerator.start();

        script1 = new Thread()
        {
            public void run()
            {
                try
                {
                    //while (cinematicsState<50) Thread.sleep(10);
                    npc1.side = EVIL;
                    while (npc1.hp > 0) Thread.sleep(10);
                    cinematics = false;
                    frags++;
                }
                catch (Throwable ex)
                {
                }
            }
        };
    }
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        // очистка экрана
        g.setColor(0x000000);
        g.fillRect(0,0,getWidth(),getHeight());

        // основная часть
        zSort();
        render(g);
        drawUI(g);
        if (!paused) tick();

        // fps
        rfps++;

        // delay
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException ex)
        {
        }

        // перерисовка
        repaint();
    }

    /**
     * Создать нового противника в случайном месте
     */
    private void createNewEnemy()
    {
        try
        {
            Random random = new Random();
            createUnit("hero", random.nextInt()%500, random.nextInt()%500);
            lastUnit.side = EVIL;
            lw = lastUnit;
            lw.setMainStats(5, 5, 4, 5, 3);

            for (int i=0; i<massives.size(); i++)
            {
                if (lastUnit.s.collidesWith((Sprite)massives.elementAt(i), false))
                {
                    die(lastUnit);
                    createNewEnemy();
                }
            }
        }
        catch (Throwable ex)
        {
        }
    }

    /**
     * Сгенерировать и нарисовать консоль
     * @param g Экземпляр Graphics для рисования
     */
    private void drawConsole(Graphics g)
    {
        if (console == null) console = new String[4];
        console[0] = "fps: " + fps + " delay: " + delay + " real: " + real;
        console[1] = "night: "+night+" nightState: "+nightState;
        console[2] = "rain: "+rain;
        console[3] = "Фраги: "+frags;

        for (int i = 0; i<console.length; i++)
        {
            g.setColor(0x000000);
            g.drawString(console[i], 2+1, i*f.getHeight()+1, Graphics.LEFT | Graphics.TOP);
            g.setColor(0xFFFFFF);
            g.drawString(console[i], 2, i*f.getHeight(), Graphics.LEFT | Graphics.TOP);
        }
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        if (getGameAction(keyCode)==DOWN) hero.tryChangeStateAndDirection(WALK, DOWN);
        if (getGameAction(keyCode)==UP) hero.tryChangeStateAndDirection(WALK, UP);
        if (getGameAction(keyCode)==LEFT) hero.tryChangeStateAndDirection(WALK, LEFT);
        if (getGameAction(keyCode)==RIGHT) hero.tryChangeStateAndDirection(WALK, RIGHT);
        if (getGameAction(keyCode)==FIRE)
        {
            if (npcAvailable() != null)
            {
                uRPG.dc.showDialog(npcAvailable().NPCdialog);
            }
            else
            {
                hero.tryChangeStateAndDirection(ATTACK, hero.direction);
            }
        }

        if (keyCode == KEY_POUND)
        {
            night = !night;
        }

        if (keyCode == KEY_STAR)
        {
            rain = !rain;
        }

        if (keyCode == KEY_NUM0)
        {
            cinematics = !cinematics;
        }

        if (keyCode==KEY_NUM1)
        {
            uRPG.ll.loadLevel("test!");
        }

        if (keyCode==KEY_NUM3)
        {
            uRPG.d.setCurrent(uRPG.ss);
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode)
    {
        if (getGameAction(keyCode)==DOWN) hero.tryChangeStateAndDirection(STAND, DOWN);
        if (getGameAction(keyCode)==UP) hero.tryChangeStateAndDirection(STAND, UP);
        if (getGameAction(keyCode)==LEFT) hero.tryChangeStateAndDirection(STAND, LEFT);
        if (getGameAction(keyCode)==RIGHT) hero.tryChangeStateAndDirection(STAND, RIGHT);
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode)
    {
    }
    
    /**
     * Called when the pointer is dragged.
     */
    protected  void pointerDragged(int x, int y)
    {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y)
    {
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y)
    {
    }

    /**
     * Создать юнит
     * @param name Имя UnitData в unitFactory
     * @param x Позиция по оси X
     * @param y Позиция по оси Y
     */
    private void createUnit(String name, int x, int y)
    {
        UnitData u = null;

        for (int i=0; i<uRPG.data.unitFactory.size(); i++)
        {
            if (((UnitData)uRPG.data.unitFactory.elementAt(i)).name.equals(name))
            {
                u = (UnitData)uRPG.data.unitFactory.elementAt(i);
            }
        }

        if (u==null)
        {
            System.out.println("Error creating unit: "+name);
            return;
        }

        lastUnit = new Unit(new Sprite(u.getImage(),u.frameWidth,u.frameHeight));
        lastUnit.stand = u.stand;
        lastUnit.walk = u.walk;
        lastUnit.attack = u.attack;
        lastUnit.dead = u.dead;
        lastUnit.appendStateAnimation();
        setCollisionRect(lastUnit.s,u.collisionRectWidth,u.collisionRectHeight);
        lastUnit.s.setPosition(x, y);
        if (u.registerAsSprite) sprites.addElement(lastUnit.s);
        if (u.registerAsGroundSprite) groundSprites.addElement(lastUnit.s);
        if (u.registerAsUnit) units.addElement(lastUnit);
        lastUnit.setMainStats(u.power, u.agility, u.reaction, u.adrenaline, u.stamina);
    }

    /**
     * Создать спрайт
     * @param name Имя SpriteData в spriteFactory
     * @param x Позиция по оси X
     * @param y Позиция по оси Y
     */
    private void createSprite(String name, int x, int y)
    {
        SpriteData u = null;

        for (int i=0; i<uRPG.data.spriteFactory.size(); i++)
        {
            if (((SpriteData)uRPG.data.spriteFactory.elementAt(i)).name.equals(name))
            {
                u = (SpriteData)uRPG.data.spriteFactory.elementAt(i);
            }
        }

        if (u==null)
        {
            System.out.println("Error creating sprite: "+name);
            return;
        }

        if (u.frameWidth == -1) u.frameWidth = u.getImage().getWidth();
        if (u.frameHeight == -1) u.frameHeight = u.getImage().getHeight();

        lastSprite = new Sprite(u.getImage(),u.frameWidth,u.frameHeight);
        setCollisionRect(lastSprite,u.collisionRectWidth,u.collisionRectHeight);
        lastSprite.setPosition(x, y);

        if (u.registerAsSprite) sprites.addElement(lastSprite);
        if (u.registerAsGroundSprite) groundSprites.addElement(lastSprite);
        if (u.registerAsMassive) massives.addElement(lastSprite);

        if (u.animation != null) lastSprite.setFrameSequence(u.animation);

        if (u.registerAsAnimated) animatedSprites.addElement(lastSprite);
    }

    /**
     * Получить изображение из imageFactory
     * @param name Имя ImageData в imageFactory
     * @return Загруженное изображение
     */
    private Image getImage(String name)
    {
        ImageData u = null;

        for (int i=0; i<uRPG.data.imageFactory.size(); i++)
        {
            if (((ImageData)uRPG.data.imageFactory.elementAt(i)).name.equals(name))
            {
                u = (ImageData)uRPG.data.imageFactory.elementAt(i);
            }
        }

        return u.getImage();
    }

    /**
     * Отрисовать игровое поле
     * @param g Экземпляр Graphics для рисования
     */
    private void render(Graphics g)
    {
        int tx = grass.getWidth()-CamX;
        int ty = grass.getHeight()-CamY;

        while (tx>0) tx-=grass.getWidth();
        while (tx<-grass.getWidth()) tx+=grass.getWidth();
        while (ty>0) ty-=grass.getHeight();
        while (ty<-grass.getHeight()) ty+=grass.getHeight();

        for (int i=tx; i<=getWidth(); i+=grass.getWidth())
        {
            for (int j=ty; j<=getHeight(); j+=grass.getHeight())
            {
                g.drawImage(grass, i, j, Graphics.LEFT | Graphics.TOP);
            }
        }

        g.translate(-CamX, -CamY);
        ground.paint(g);
        for (int i=0; i<groundSprites.size(); i++)
        {
            Sprite s = (Sprite)groundSprites.elementAt(i);
            boolean flag = true;
            if (s.getX()+s.getWidth()-CamX<0) flag = false;
            if (s.getY()+s.getHeight()-CamY<0) flag = false;
            if (s.getX()-CamX > getWidth()) flag = false;
            if (s.getY()-CamY > getHeight()) flag = false;
            if (flag) s.paint(g);
            //((Sprite)groundSprites.elementAt(i)).paint(g);
        }
        water.paint(g);
        waterside.paint(g);
        for (int i=0; i<sprites.size(); i++)
        {
            Sprite s = (Sprite)sprites.elementAt(i);
            boolean flag = true;
            if (s.getX()+s.getWidth()-CamX<0) flag = false;
            if (s.getY()+s.getHeight()-CamY<0) flag = false;
            if (s.getX()-CamX > getWidth()) flag = false;
            if (s.getY()-CamY > getHeight()) flag = false;
            if (flag) s.paint(g);
            //((Sprite)sprites.elementAt(i)).paint(g);
        }
        if (npcAvailable() != null)
        {
            Sprite s = npcAvailable().s;
            int x = s.getX()+s.getWidth()/2;
            int y = s.getY();
            g.setColor(0xFFFF00);
            g.drawString("(!)", x, y, Graphics.HCENTER | Graphics.BOTTOM);
        }
        for (int i=0; i<units.size(); i++)
        {
            Unit c = (Unit) units.elementAt(i);
            int x = c.s.getX()+c.s.getWidth()/2;
            int y = c.s.getY();
            g.setColor(0x00FF00);
            g.drawString(""+c.hp, x, y, Graphics.HCENTER | Graphics.BOTTOM);
        }
        g.translate(CamX, CamY);

        drawNight(g);

        drawRain(g);
    }

    /**
     * Нарисовать ночь, при необходимости сделать ее темнее или светлее
     * @param g Экземпляр Graphics для рисования
     */
    private void drawNight(Graphics g)
    {
        if (night)
        {
            nightState++;
            if (nightState>128) nightState = 128;
        }
        else
        {
            nightState--;
            if (nightState<0) nightState=0;
        }

        if (nightState == 0)
        {
            if (dlights != null)
            {
                dlights = null;
                System.gc();
            }
            return;
        }

        if (dlights == null)
        {
            dlights = new int[getWidth() * getHeight()];
        }

        if (dlights[0] != color(nightState,0,0,0))
        {
            int z = color(nightState,0,0,0);
            for (int i=0; i<dlights.length; i++) dlights[i]=z;
        }

        g.drawRGB(dlights, 0, getWidth(), 0, 0, getWidth(), getHeight(), true);
    }

    /**
     * Собрать цвет в ARGB формате из отдельных компонентов
     * @param a alpha
     * @param r red
     * @param g green
     * @param b blue
     * @return Цвет в ARGB формате
     */
    public int color(int a, int r, int g, int b)
    {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }

    /**
     * Загрузить тестовый уровень
     */
    public void loadTestLevel()
    {
        try
        {            
            grass = getImage("grass");

            ground = gd.getGround_testlevel();

            water = gd.getWater1();

            waterside = gd.getWaterside1();
            //genWaterside();

            createUnit("hero",50,110);
            hero = lastUnit;
            hero.side = HERO_GROUP;
            hero.setAdditionalStats(5, 5, 5, 5, 5);

            createUnit("hero",0,110);
            lastUnit.side = HERO_GROUP;

            createSprite("tree2",50,150);

            createSprite("chest",20,180);

            createUnit("hero",50,50);
            lastUnit.isNPC = true;
            lastUnit.setMainStats(5, 5, 4, 5, 4);
            /////////////////////////
            Dialog d2 = new Dialog();
            d2.title = "Закнись или сдохнешь...";
            String[] w2 = {"Да? Проверим?","Да пошел ты!"};
            d2.exitAction = new Runnable(){
                public void run()
                {
                    npc1.isNPC = false;
                    cinematics = true;
                    uRPG.c.script1.start();
                }
            };
            d2.words = w2;
            Dialog[] q2 = {null,null};
            d2.vars = q2;
            /////////////////////////
            Dialog d1 = new Dialog();
            d1.title = "Тебе чего?";
            String[] w1 = {"Ничего.","Да я так, гуляю просто...","Иди к черту."};
            d1.words = w1;
            Dialog[] q = {null,null,d2};
            d1.vars = q;
            ////////////
            lastUnit.NPCdialog = d1;
            npc1 = lastUnit;

            createNewEnemy();

            createSprite("bush1",150,180);
            createSprite("bush2",250,180);
            createSprite("bush3",350,180);

            Random r = new Random();
            for (int i = 0; i<100; i++)
            {
                createSprite("tree2",r.nextInt()%500,r.nextInt()%500);

                boolean flag = true;
                if (lastSprite.collidesWith(water, false)) flag = false;
                if (lastSprite.collidesWith(hero.s, false)) flag = false;
                if (lastSprite.collidesWith(npc1.s, false)) flag = false;

                if (!flag)
                {
                    sprites.removeElement(lastSprite);
                    massives.removeElement(lastSprite);
                }
            }
            for (int i = 0; i<75; i++)
            {
                createSprite("bush"+(Math.abs(r.nextInt()%3)+1),r.nextInt()%700,r.nextInt()%700);
                // Кто не понял - "bush1","bush2","bush3". Кто-то из них.

                boolean flag = true;
                if (lastSprite.collidesWith(hero.s, false)) flag = false;
                if (lastSprite.collidesWith(npc1.s, false)) flag = false;

                if (!flag)
                {
                    sprites.removeElement(lastSprite);
                    massives.removeElement(lastSprite);
                }
            }
        }
        catch (Throwable ex)
        {
        }
    }

    /**
     * Отсортировать спрайты по удаленности
     */
    public void zSort()
    {
        Sprite s1, s2;
        int max = 0;
        boolean outInfo = false;
        if ((uRPG.d.getCurrent()==uRPG.ll)&&(sprites.size()>100)) outInfo = true;

        for (int i=0; i<sprites.size()-1; i++)
        {
            s1 = (Sprite) sprites.elementAt(i);
            s2 = (Sprite) sprites.elementAt(i+1);
            if (s1.getY()+s1.getHeight() > s2.getY()+s2.getHeight())
            {
                sprites.setElementAt(s2, i);
                sprites.setElementAt(s1, i+1);
                if (outInfo)
                {
                    if (i>max) max=i;
                    uRPG.ll.string="z-сортировка "+max+"/"+sprites.size();
                }
                i = -1;
            }
        }
    }

    /**
     * Тик
     */
    private void tick()
    {
        for (int i=0; i<units.size(); i++)
        {
            Unit processingUnit = (Unit) units.elementAt(i);
            processingUnit.s.nextFrame();
            if (processingUnit.state == WALK)
            {
                int dx = 0,dy = 0;
                if (processingUnit.direction==DOWN) dy = 5;
                if (processingUnit.direction==UP) dy = -5;
                if (processingUnit.direction==RIGHT) dx = 5;
                if (processingUnit.direction==LEFT) dx = -5;
                processingUnit.s.move(dx, dy);
                boolean flag = false;
                if (processingUnit.s.collidesWith(water, false)) flag = true;
                for (int j=0; j<massives.size(); j++)
                {
                    Sprite s = (Sprite) massives.elementAt(j);
                    if (processingUnit.s.collidesWith(s, false)) flag = true;
                }
                if (flag)
                {
                    processingUnit.s.move(-dx, -dy);
                    processingUnit.tryChangeStateAndDirection(STAND, processingUnit.direction);
                }
            }

            processingUnit.frameReaction();

            Unit target = getNearestTargetForAI(processingUnit,100);
            if ((processingUnit.side != NEUTRAL)&&(target != null)&&(processingUnit != hero))
            {
                if ((distanceBetween(processingUnit,target)<100)&&(target.state != DEAD))
                {
                    int n = DOWN;
                    int dx = target.s.getX() - processingUnit.s.getX();
                    int dy = target.s.getY() - processingUnit.s.getY();
                    if (Math.abs(dx)>Math.abs(dy))
                    {
                        if (dx>0) n = RIGHT;
                        else n = LEFT;
                    }
                    else
                    {
                        if (dy>0) n = DOWN;
                        else n = UP;
                    }
                    processingUnit.tryChangeStateAndDirection(WALK, n);
                }

                if ((distanceBetween(processingUnit,target)<30)&&(target.state != DEAD))
                {
                    int n = DOWN;
                    int dx = target.s.getX() - processingUnit.s.getX();
                    int dy = target.s.getY() - processingUnit.s.getY();
                    if (Math.abs(dx)>Math.abs(dy))
                    {
                        if (dx>0) n = RIGHT;
                        else n = LEFT;
                    }
                    else
                    {
                        if (dy>0) n = DOWN;
                        else n = UP;
                    }
                    processingUnit.tryChangeStateAndDirection(ATTACK, n);
                }
            }
            if ((processingUnit.side == HERO_GROUP)&&(target==null)&&(processingUnit != hero))
            {
                if (distanceBetween(processingUnit,hero)>30)
                {
                    int n = DOWN;
                    int dx = hero.s.getX() - processingUnit.s.getX();
                    int dy = hero.s.getY() - processingUnit.s.getY();
                    if (Math.abs(dx)>Math.abs(dy))
                    {
                        if (dx>0) n = RIGHT;
                        else n = LEFT;
                    }
                    else
                    {
                        if (dy>0) n = DOWN;
                        else n = UP;
                    }
                    processingUnit.tryChangeStateAndDirection(WALK, n);
                }
                else
                {
                    processingUnit.tryChangeStateAndDirection(STAND, processingUnit.direction);
                }
            }

            if (processingUnit.causesDamage)
            {
                processingUnit.causesDamage = false;
                for (int j=0; j<units.size(); j++)
                {
                    Unit attackedUnit = (Unit) units.elementAt(j);
                    if (processingUnit == attackedUnit) continue;
                    if (processingUnit.side == attackedUnit.side) continue;
                    if (distanceBetween(processingUnit,attackedUnit)<30)
                    {
                        boolean crit = false;
                        if (Math.abs(new Random().nextInt()%100)<processingUnit.critChance) crit = true;

                        int damage = processingUnit.pAtt-attackedUnit.pDef;
                        if (crit) damage *= 2;
                        if (damage<0) damage = 0;
                        attackedUnit.hp-=damage;

                        if ((processingUnit==hero)&&(attackedUnit.side == NEUTRAL))
                        {
                            attackedUnit.side = EVIL;
                        }

                        if (attackedUnit.hp < 0) attackedUnit.hp = 0;
                        if (attackedUnit.hp == 0)
                        {
                            int dexp = 5+minusStats(attackedUnit,processingUnit);
                            if (dexp<0) dexp = 0;
                            if (processingUnit.side == HERO_GROUP) hero.exp += dexp;

                            die(attackedUnit);
                        }
                    }
                }
            }
        }

        CamX = hero.s.getX()+hero.s.getWidth()/2-getWidth()/2;
        CamY = hero.s.getY()+hero.s.getHeight()/2-getHeight()/2;
    }

    /**
     * Задать область столкновений для заданного спрайта, ориентируясь на его низ
     * @param s Спрайт
     * @param w Ширина области
     * @param h Высота области
     */
    public void setCollisionRect(Sprite s,int w,int h)
    {
        s.defineCollisionRectangle(s.getWidth()/2-w/2, s.getHeight()-h, w, h);
    }

    /**
     * Задать область столкновений для заданного спрайта, ориентируясь на его центр
     * @param s Спрайт
     * @param w Ширина области
     * @param h Высота области
     */
    public void setCollisionRectRelativeToCenter(Sprite s,int w,int h)
    {
        s.defineCollisionRectangle(s.getWidth()/2-w/2, s.getHeight()/2-h/2, w, h);
    }

    /**
     * Hайти NPC рядом с героем
     * @return NPC или null, если их нет поблизости
     */
    private Unit npcAvailable()
    {
        Unit c = null;
        for (int i=0; i<units.size(); i++)
        {
            Unit z = (Unit) units.elementAt(i);
            if ((z.isNPC)&&(distanceBetween(hero,z)<75))
            {
                c = z;
            }
        }
        return c;
    }

    /**
     * Определить расстояние между двумя юнитами
     * @param c1 Первый юнит
     * @param c2 Второй юнит
     * @return Расстояние между ними
     */
    private int distanceBetween(Unit c1, Unit c2)
    {
        Sprite s1 = c1.s;
        Sprite s2 = c2.s;
        int x1 = s1.getX()+s1.getWidth()/2;
        int x2 = s2.getX()+s2.getWidth()/2;
        int y1 = s1.getY()+s1.getHeight();
        int y2 = s2.getY()+s2.getHeight();
        return (int) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    /**
     * Нарисовать дождь, сдвинуть капли, если они есть на экране
     * @param g Экземпляр Graphics для рисования
     */
    private void drawRain(Graphics g)
    {
        w.genRain = rain;
        w.screenWidth = getWidth();
        w.screenHeight = getHeight();
        w.drawRain(g);
    }

    /**
     * Получить water
     * @return water
     */
    public TiledLayer getWater()
    {
        return water;
    }

    /**
     * Нарисовать интерфейс
     * @param g Экземпляр Graphics для рисования
     */
    private void drawUI(Graphics g)
    {
        drawConsole(g);

        g.setColor(0xFFFF00);
        if (lw.s.getX()<CamX) g.fillTriangle(0, getHeight()/2, 5, getHeight()/2-5, 5, getHeight()/2+5);
        if (lw.s.getX()>CamX+getWidth()) g.fillTriangle(getWidth(), getHeight()/2, getWidth()-5, getHeight()/2-5, getWidth()-5, getHeight()/2+5);
        if (lw.s.getY()<CamY) g.fillTriangle(getWidth()/2, 0, getWidth()/2-5, 5, getWidth()/2+5, 5);
        if (lw.s.getY()>CamY+getHeight()) g.fillTriangle(getWidth()/2, getHeight(), getWidth()/2-5, getHeight()-5, getWidth()/2+5, getHeight()-5);

        drawDiagMessage(g);

        drawCinematics(g);
    }

    /**
     * Нарисовать полоски режима кинематики, после чего при необходимости расширить или сузить их
     * @param g Экземпляр Graphics для рисования
     */
    private void drawCinematics(Graphics g)
    {
        if (cinematics) cinematicsState++;
        else cinematicsState--;
        if (cinematicsState>50) cinematicsState=50;
        if (cinematicsState<0) cinematicsState=0;

        if (cinematicsState>0)
        {
            g.setColor(0x000000);
            g.fillRect(0, 0, getWidth(), cinematicsState);
            g.fillRect(0, getHeight()-cinematicsState, getWidth(), cinematicsState);
            g.setColor(0x808080);
            g.drawLine(0, cinematicsState, getWidth(), cinematicsState);
            g.drawLine(0, getHeight()-cinematicsState, getWidth(), getHeight()-cinematicsState);
        }
    }

    /**
     * Сгенерировать и нарисовать диагностическое сообщение
     * @param g Экземпляр Graphics для рисования
     */
    public void drawDiagMessage(Graphics g)
    {
        int color = 0x00FF00;
        String msg = "Нормальный режим";

        if (fps > 20)
        {
            color = 0xFFFF00;
            msg = "Балансировка fps...";
        }
        if ((fps < 15) && (delay > 0))
        {
            msg = "Балансировка fps...";
            color = 0xFFFF00;
        }
        if ((fps < 15) && (delay == 0))
        {
            msg = "Высокая нагрузка";
            color = 0xFF8000;
        }
        if ((fps < 10) && (delay == 0))
        {
            msg = "Движок перегружен";
            color = 0xFF0000;
        }

        g.setColor(0x000000);
        g.drawString(msg, 5 + 1, getHeight() - 5 + 1, Graphics.LEFT | Graphics.BOTTOM);
        g.setColor(color);
        g.drawString(msg, 5, getHeight() - 5, Graphics.LEFT | Graphics.BOTTOM);
    }

    /**
     * Убить юнит
     * @param c Кандидат в трупы
     */
    public void die(Unit c)
    {
        c.state = DEAD;
        c.appendStateAnimation();
        sprites.removeElement(c.s);
        massives.removeElement(c.s);
        units.removeElement(c);
        groundSprites.addElement(c.s);
    }

    /**
     * Удалить юнит
     * @param c Кандидат в трупы
     */
    public void dissolve(Unit c)
    {
        c.state = DEAD;
        sprites.removeElement(c.s);
        massives.removeElement(c.s);
        units.removeElement(c);
    }

    /**
     * Получить ближайшую цель для AI юнита
     * @param attacker Атакующий юнит
     * @param maxDistance Максимальная дистанция
     * @return Цель или null, если цель не найдена
     */
    public Unit getNearestTargetForAI(Unit attacker, int maxDistance)
    {
        Unit target = null;
        int distanceToTarget = Integer.MAX_VALUE;

        for (int i=0; i<units.size(); i++)
        {
            Unit victim = (Unit) units.elementAt(i);
            int distanceToVictim = distanceBetween(attacker,victim);
            if ((distanceToVictim<distanceToTarget)&&(attacker.side != victim.side)&&(distanceToVictim<maxDistance)&&(victim.side != NEUTRAL))
            {
                target = victim;
                distanceToTarget = distanceToVictim;
            }
        }

        return target;
    }

    /**
     * Сгенерировать берега
     */
    private void genWaterside()
    {
        waterside.fillCells(0, 0, waterside.getColumns(), waterside.getRows(), 0);
        for (int i=1; i<water.getColumns()-1; i++)
        {
            for (int j=1; j<water.getRows()-1; j++)
            {
                if (water.getCell(i, j) != -1) continue;
                boolean waterAtLeft = (water.getCell(i-1, j)==-1);
                boolean waterAtRight = (water.getCell(i+1, j)==-1);
                boolean waterAtUp = (water.getCell(i, j-1)==-1);
                boolean waterAtDown = (water.getCell(i, j+1)==-1);
                // Кругом вода? Ну и похуй.
                if (waterAtLeft&&waterAtRight&&waterAtUp&&waterAtDown) continue;
                // С трех сторон
                if (waterAtRight&&waterAtUp&&waterAtDown) { waterside.setCell(i, j, 8); continue; }
                if (waterAtLeft&&waterAtUp&&waterAtDown) { waterside.setCell(i, j, 7); continue; }
                if (waterAtLeft&&waterAtRight&&waterAtDown) { waterside.setCell(i, j, 5); continue; }
                if (waterAtLeft&&waterAtRight&&waterAtUp) { waterside.setCell(i, j, 6); continue; }
                // C двух сторон (уголки, то бишь)
                if (waterAtRight&&waterAtDown) { waterside.setCell(i, j, 2); continue; }
                if (waterAtRight&&waterAtUp) { waterside.setCell(i, j, 4); continue; }
                if (waterAtLeft&&waterAtDown) { waterside.setCell(i, j, 1); continue; }
                if (waterAtLeft&&waterAtUp) { waterside.setCell(i, j, 3); continue; }
            }
        }
    }

    private int minusStats(Unit u1, Unit u2)
    {
        int x = 0;
        x += (u1.power - u2.power);
        x += (u1.agility - u2.agility);
        x += (u1.reaction - u2.reaction);
        x += (u1.adrenaline - u2.adrenaline);
        x += (u1.stamina - u2.stamina);
        //System.out.println(""+x);
        return x;
    }

}