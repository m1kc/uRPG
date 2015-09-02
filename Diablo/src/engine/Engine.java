/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.io.IOException;
import java.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * @author Makc
 */
public class Engine extends GameCanvas implements Runnable
{
    Image canvas;
    Graphics m;

    Vector lowObjects = new Vector();
    Vector highObjects = new Vector();
    Vector floatObjects = new Vector();
    Vector images = new Vector();

    Vector words = new Vector();

    Vector lights = new Vector();

    int[] snows;

    int CamX=0;
    int CamY=0;
    int HeroX=0;
    int HeroY=0;
    int WolfX=-50;
    int WolfY=-50;

    boolean uUp=false;
    boolean dUp=false;
    boolean lUp=false;
    boolean rUp=false;

    Date d = new Date ();
    Random Randomizer = new Random (d.getTime());

    Image HeroL,HeroR,HeroU,HeroD;

    Image HeroL1,HeroL2,HeroL3,HeroL4,HeroL5,HeroL6;
    Image HeroR1,HeroR2,HeroR3,HeroR4,HeroR5,HeroR6;
    Image HeroD1,HeroD2,HeroD3,HeroD4,HeroD5,HeroD6;
    Image HeroU1,HeroU2,HeroU3,HeroU4,HeroU5,HeroU6;

    Image HeroKL1,HeroKL2,HeroKL3,HeroKL4,HeroKL5,HeroKL6;
    Image HeroKR1,HeroKR2,HeroKR3,HeroKR4,HeroKR5,HeroKR6;
    Image HeroKD1,HeroKD2,HeroKD3,HeroKD4,HeroKD5,HeroKD6;
    Image HeroKU1,HeroKU2,HeroKU3,HeroKU4,HeroKU5,HeroKU6;

    Image WolfL,WolfR,WolfU,WolfD;

    Image WolfL1,WolfL2,WolfL3,WolfL4,WolfL5,WolfL6;
    Image WolfR1,WolfR2,WolfR3,WolfR4,WolfR5,WolfR6;
    Image WolfD1,WolfD2,WolfD3,WolfD4,WolfD5,WolfD6;
    Image WolfU1,WolfU2,WolfU3,WolfU4,WolfU5,WolfU6;

    Image WolfKL1,WolfKL2,WolfKL3,WolfKL4,WolfKL5,WolfKL6;
    Image WolfKR1,WolfKR2,WolfKR3,WolfKR4,WolfKR5,WolfKR6;
    Image WolfKD1,WolfKD2,WolfKD3,WolfKD4,WolfKD5,WolfKD6;
    Image WolfKU1,WolfKU2,WolfKU3,WolfKU4,WolfKU5,WolfKU6;

    int facing = 0;
    int wolfFacing = 0;
    int F_LEFT = 0;
    int F_RIGHT = 1;
    int F_DOWN = 2;
    int F_UP = 3;

    int action = 0;
    int wolfAction = 0;
    int A_STILL = 0;
    int A_WALK = 1;
    int A_ATTACK = 2;
    //boolean isMoving = false;
    //boolean isAttacking = false;

    int wolfState=1;
    int state=1;

    Image ground_texture;

    int tx=0;
    int ty=0;

    int OnScreen=0;
    boolean hit=false;

    Image dark;

    long time = System.currentTimeMillis();
    long time2 = System.currentTimeMillis();
    int fps = 0;
    int tw = 0;
    int tww = 0;
    int k = 0;

    int qw = 0;

    boolean Scene = false;

    int weather=0;
    int SUN=0;
    int RAIN=1;
    int SNOW=2;
    int NIGHT=3;

    /*
     *
        m1kc 2D Engine v.1.0.
     *
     */

    public void run()
    {
        boolean frameskip = false;
        while(true)
        {
            tick();
            //if (fps>10) { frameskip = false; } else { frameskip = !frameskip; }
            if (weather==NIGHT) { frameskip = !frameskip; } else { frameskip = false; }
            if (!frameskip)
            {
                Graphics g = getGraphics();
                doPaint(g);
                flushGraphics();
            }
        }
    }

    public void start()
    {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void DrawLowObjects(Graphics g)
    {
        int i;
        MapObject q;
        for (i=0; i<lowObjects.size(); i++)
        {
            q = (MapObject) lowObjects.elementAt(i);
            if ((q.x+32 >= CamX)&&(q.x <= CamX+getWidth())&&(q.y+32 >= CamY)&&(q.y <= CamY+getHeight()))
            {
                g.drawImage((Image) images.elementAt(q.i), q.x-CamX, q.y-CamY, Graphics.LEFT | Graphics.TOP);
                OnScreen++;
            }
        }
    }

    private void DrawHighObjects(Graphics g)
    {
        int i;
        MapObject q;
        for (i=0; i<highObjects.size(); i++)
        {
            q = (MapObject) highObjects.elementAt(i);
            if ((q.x+32 >= CamX)&&(q.x <= CamX+getWidth())&&(q.y+32 >= CamY)&&(q.y <= CamY+getHeight()))
            {
                g.drawImage((Image) images.elementAt(q.i), q.x-CamX, q.y-CamY, Graphics.LEFT | Graphics.TOP);
                OnScreen++;
            }
        }
    }

    private void DrawLowFloats(Graphics g)
    {
        int i;
        MapObject q;
        for (i=0; i<floatObjects.size(); i++)
        {
            q = (MapObject) floatObjects.elementAt(i);
            if ((q.y<HeroY)&&(q.x+32 >= CamX)&&(q.x <= CamX+getWidth())&&(q.y+32 >= CamY)&&(q.y <= CamY+getHeight()))
            {
                g.drawImage((Image) images.elementAt(q.i), q.x-CamX, q.y-CamY, Graphics.LEFT | Graphics.TOP);
                OnScreen++;
            }
        }
    }

    private void DrawHighFloats(Graphics g)
    {
        int i;
        MapObject q;
        for (i=0; i<floatObjects.size(); i++)
        {
            q = (MapObject) floatObjects.elementAt(i);
            if ((q.y>=HeroY)&&(q.x+32 >= CamX)&&(q.x <= CamX+getWidth())&&(q.y+32 >= CamY)&&(q.y <= CamY+getHeight()))
            {
                g.drawImage((Image) images.elementAt(q.i), q.x-CamX, q.y-CamY, Graphics.LEFT | Graphics.TOP);
                OnScreen++;
            }
        }
    }

    /**
     * Добавляет дерево (вариант 2) по заданным координатам.
     * @param x координата по оси Х
     * @param y координата по оси Y
     **/

    private void AddTree2(int x, int y)
    {
        highObjects.addElement(new MapObject(x,y,0,true));
        highObjects.addElement(new MapObject(x,y+32,1,true));
        highObjects.addElement(new MapObject(x,y+64,2,true));

        highObjects.addElement(new MapObject(x+32,y,3,true));
        highObjects.addElement(new MapObject(x+32,y+32,4,true));
        highObjects.addElement(new MapObject(x+32,y+64,5,true));
        lowObjects.addElement(new MapObject(x+32,y+96,6,false));

        highObjects.addElement(new MapObject(x+64,y,7,true));
        highObjects.addElement(new MapObject(x+64,y+32,8,true));
        highObjects.addElement(new MapObject(x+64,y+64,9,true));
    }

    private void checkAnimation()
    {
        int i;
        MapObject q;
        for (i=0; i<lowObjects.size(); i++)
        {
            q = (MapObject) lowObjects.elementAt(i);
            if ((q.i>=10)&&(q.i<=15))
            {
                q.i++;
                if (q.i > 15) q.i = 10;
                lowObjects.setElementAt(q, i);
            }
        }
        for (i=0; i<highObjects.size(); i++)
        {
            q = (MapObject) highObjects.elementAt(i);
            if ((q.i>=10)&&(q.i<=15))
            {
                q.i++;
                if (q.i > 15) q.i = 10;
                highObjects.setElementAt(q, i);
            }
        }
        for (i=0; i<floatObjects.size(); i++)
        {
            q = (MapObject) floatObjects.elementAt(i);
            if ((q.i>=10)&&(q.i<=15))
            {
                q.i++;
                if (q.i > 15) q.i = 10;
                floatObjects.setElementAt(q, i);
            }
        }
    }

    private void prepareSnow(boolean atUp)
    {
        int i;
        snows = new int[getWidth()];
        if (atUp)
        {
            for (i=0; i<getWidth(); i++)
            {
                snows[i]=Randomizer.nextInt()%getHeight()-getHeight();
            }
        }
        else
        {
            for (i=0; i<getWidth(); i++)
            {
                snows[i]=Randomizer.nextInt()%getHeight();
            }
        }
    }

    private void checkHits()
    {
        int i;
        for (i=0; i<lowObjects.size(); i++)
        {
            if (!((MapObject) lowObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) lowObjects.elementAt(i);
                if ((HeroY+32 > l.y)&&(HeroY < l.y+32)&&(HeroX+32 > l.x)&&(HeroX < l.x+32))
                {
                    hit=true;
                    if (facing == F_LEFT) HeroX=l.x+32;
                    if (facing == F_RIGHT) HeroX=l.x-32;
                    if (facing == F_UP) HeroY=l.y+32;
                    if (facing == F_DOWN) HeroY=l.y-32;
                    action=A_STILL;
                }
            }
        }
        for (i=0; i<highObjects.size(); i++)
        {
            if (!((MapObject) highObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) highObjects.elementAt(i);
                if ((HeroY+32 > l.y)&&(HeroY < l.y+32)&&(HeroX+32 > l.x)&&(HeroX < l.x+32))
                {
                    hit=true;
                    if (facing == F_LEFT) HeroX=l.x+32;
                    if (facing == F_RIGHT) HeroX=l.x-32;
                    if (facing == F_UP) HeroY=l.y+32;
                    if (facing == F_DOWN) HeroY=l.y-32;
                    action=A_STILL;
                }
            }
        }
        for (i=0; i<floatObjects.size(); i++)
        {
            if (!((MapObject) floatObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) floatObjects.elementAt(i);
                if ((HeroY+32 > l.y)&&(HeroY < l.y+32)&&(HeroX+32 > l.x)&&(HeroX < l.x+32))
                {
                    hit=true;
                    if (facing == F_LEFT) HeroX=l.x+32;
                    if (facing == F_RIGHT) HeroX=l.x-32;
                    if (facing == F_UP) HeroY=l.y+32;
                    if (facing == F_DOWN) HeroY=l.y-32;
                    action=A_STILL;
                }
            }
        }
    }

    private void checkWolfHits()
    {
        int i;
        for (i=0; i<lowObjects.size(); i++)
        {
            if (!((MapObject) lowObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) lowObjects.elementAt(i);
                if ((WolfY+32 > l.y)&&(WolfY < l.y+32)&&(WolfX+32 > l.x)&&(WolfX < l.x+32))
                {
                    hit=true;
                    if (wolfFacing == F_LEFT) WolfX=l.x+32;
                    if (wolfFacing == F_RIGHT) WolfX=l.x-32;
                    if (wolfFacing == F_UP) WolfY=l.y+32;
                    if (wolfFacing == F_DOWN) WolfY=l.y-32;
                    wolfAction=A_STILL;
                }
            }
        }
        for (i=0; i<highObjects.size(); i++)
        {
            if (!((MapObject) highObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) highObjects.elementAt(i);
                if ((WolfY+32 > l.y)&&(WolfY < l.y+32)&&(WolfX+32 > l.x)&&(WolfX < l.x+32))
                {
                    hit=true;
                    if (wolfFacing == F_LEFT) WolfX=l.x+32;
                    if (wolfFacing == F_RIGHT) WolfX=l.x-32;
                    if (wolfFacing == F_UP) WolfY=l.y+32;
                    if (wolfFacing == F_DOWN) WolfY=l.y-32;
                    wolfAction=A_STILL;
                }
            }
        }
        for (i=0; i<floatObjects.size(); i++)
        {
            if (!((MapObject) floatObjects.elementAt(i)).isAvailable)
            {
                MapObject l = (MapObject) floatObjects.elementAt(i);
                if ((WolfY+32 > l.y)&&(WolfY < l.y+32)&&(WolfX+32 > l.x)&&(WolfX < l.x+32))
                {
                    hit=true;
                    if (wolfFacing == F_LEFT) WolfX=l.x+32;
                    if (wolfFacing == F_RIGHT) WolfX=l.x-32;
                    if (wolfFacing == F_UP) WolfY=l.y+32;
                    if (wolfFacing == F_DOWN) WolfY=l.y-32;
                    wolfAction=A_STILL;
                }
            }
        }

        if ((hit)&&((wolfFacing==F_LEFT)||(wolfFacing==F_RIGHT)))
        {
            if (HeroY+16>=WolfY) WolfY+=6;
            if (HeroY+16<WolfY) WolfY-=6;
        }

        if ((hit)&&((wolfFacing==F_DOWN)||(wolfFacing==F_UP)))
        {
            if (HeroX+16>=WolfX) WolfX+=6;
            if (HeroX+16<WolfX) WolfX-=6;
        }
    }

    private void drawHero(Graphics m)
    {
        //int q = getWidth()/2-23;
        //int w = getHeight()/2-30;

        int q = HeroX-CamX;
        int w = HeroY-CamY-32;

        if (action==A_STILL)
        {
            if (facing==F_LEFT) m.drawImage(HeroL, q, w, Graphics.LEFT | Graphics.TOP);
            if (facing==F_RIGHT) m.drawImage(HeroR, q, w, Graphics.LEFT | Graphics.TOP);
            if (facing==F_UP) m.drawImage(HeroU, q-8, w, Graphics.LEFT | Graphics.TOP);
            if (facing==F_DOWN) m.drawImage(HeroD, q-8, w, Graphics.LEFT | Graphics.TOP);
        }

        if (action==A_WALK)
        {
            if (facing==F_LEFT)
            {
                if (state==1) m.drawImage(HeroL1, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroL2, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroL3, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroL4, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroL5, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroL6, q, w, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_RIGHT)
            {
                if (state==1) m.drawImage(HeroR1, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroR2, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroR3, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroR4, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroR5, q, w, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroR6, q, w, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_UP)
            {
                if (state==1) m.drawImage(HeroU1, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroU2, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroU3, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroU4, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroU5, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroU6, q-8, w, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_DOWN)
            {
                if (state==1) m.drawImage(HeroD1, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroD2, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroD3, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroD4, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroD5, q-8, w, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroD6, q-8, w, Graphics.LEFT | Graphics.TOP);
            }
        }

        if (action==A_ATTACK)
        {
            if (facing==F_LEFT)
            {
                if (state==1) m.drawImage(HeroKL1, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroKL2, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroKL3, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroKL4, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroKL5, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroKL6, q-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_RIGHT)
            {
                if (state==1) m.drawImage(HeroKR1, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroKR2, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroKR3, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroKR4, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroKR5, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroKR6, q-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_UP)
            {
                if (state==1) m.drawImage(HeroKU1, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroKU2, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroKU3, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroKU4, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroKU5, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroKU6, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (facing==F_DOWN)
            {
                if (state==1) m.drawImage(HeroKD1, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==2) m.drawImage(HeroKD2, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==3) m.drawImage(HeroKD3, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==4) m.drawImage(HeroKD4, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==5) m.drawImage(HeroKD5, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (state==6) m.drawImage(HeroKD6, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
        }
    }

    private void drawWolf(Graphics m)
    {
        int q = WolfX-CamX;
        int w = WolfY-CamY;

        if (wolfAction==A_STILL)
        {
            if (wolfFacing==F_LEFT) m.drawImage(WolfL, q, w, Graphics.LEFT | Graphics.TOP);
            if (wolfFacing==F_RIGHT) m.drawImage(WolfR, q, w, Graphics.LEFT | Graphics.TOP);
            if (wolfFacing==F_UP) m.drawImage(WolfU, q, w, Graphics.LEFT | Graphics.TOP);
            if (wolfFacing==F_DOWN) m.drawImage(WolfD, q, w, Graphics.LEFT | Graphics.TOP);
        }

        if (wolfAction==A_WALK)
        {
            if (wolfFacing==F_LEFT)
            {
                if (wolfState==1) m.drawImage(WolfL1, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfL2, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfL3, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfL4, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfL5, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfL6, q, w, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_RIGHT)
            {
                if (wolfState==1) m.drawImage(WolfR1, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfR2, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfR3, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfR4, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfR5, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfR6, q, w, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_UP)
            {
                if (wolfState==1) m.drawImage(WolfU1, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfU2, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfU3, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfU4, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfU5, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfU6, q, w, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_DOWN)
            {
                if (wolfState==1) m.drawImage(WolfD1, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfD2, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfD3, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfD4, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfD5, q, w, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfD6, q, w, Graphics.LEFT | Graphics.TOP);
            }
        }

        if (wolfAction==A_ATTACK)
        {
            if (wolfFacing==F_LEFT)
            {
                if (wolfState==1) m.drawImage(WolfKL1, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfKL2, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfKL3, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfKL4, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfKL5, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfKL6, q-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_RIGHT)
            {
                if (wolfState==1) m.drawImage(WolfKR1, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfKR2, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfKR3, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfKR4, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfKR5, q-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfKR6, q-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_UP)
            {
                if (wolfState==1) m.drawImage(WolfKU1, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfKU2, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfKU3, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfKU4, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfKU5, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfKU6, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
            if (wolfFacing==F_DOWN)
            {
                if (wolfState==1) m.drawImage(WolfKD1, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==2) m.drawImage(WolfKD2, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==3) m.drawImage(WolfKD3, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==4) m.drawImage(WolfKD4, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==5) m.drawImage(WolfKD5, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
                if (wolfState==6) m.drawImage(WolfKD6, q-8-32, w-12, Graphics.LEFT | Graphics.TOP);
            }
        }
    }

    private void drawWeather(Graphics m)
    {
        int i,j;

        if (weather == RAIN)
        {
            int l;
            for (i=0; i<getWidth(); i++)
            {
                j = Math.abs(Randomizer.nextInt()%getHeight());
                l = Randomizer.nextInt()%96;
                m.setColor(128+l,128+l,128+l);
                m.drawLine(i, j, i, j+5);
            }
        }

        if (weather == SNOW)
        {
            int plus=2;
            for (i=0; i<getWidth(); i++)
            {
                m.setColor(255,255,255);
                m.drawLine(i, snows[i]-1, i, snows[i]+1);
                m.drawLine(i-1, snows[i], i+1, snows[i]);
                snows[i]+=plus;
                plus+=3;
                if (plus>10) plus-=10;
                if (snows[i]>getHeight()) snows[i]=Randomizer.nextInt()%50-50;
            }
        }

        if (weather==NIGHT)
        {
            boolean flag;
            int p;
            Light q;
            for (i=0; i<=getWidth(); i+=4)
            {
                for (j=0; j<=getHeight(); j+=4)
                {
                    flag = true;
                    for (p=0; p<lights.size(); p++)
                    {
                        q = (Light) lights.elementAt(p);
                        if ((Math.sqrt((q.x-CamX-i)*(q.x-CamX-i)+(q.y-CamY-j)*(q.y-CamY-j)))<=q.intensity) flag=false;
                    }
                    if (flag) m.drawImage(dark, i, j, Graphics.LEFT | Graphics.TOP);
                }
            }
        }
    }

    /**
     * constructor
     */

    public Engine()
    {
        super(false);

        setFullScreenMode(true);

        canvas = Image.createImage(getWidth(), getHeight());

        try 
        {
            HeroL = Image.createImage("/7.png");
            HeroR = Image.createImage("/14.png");
            HeroD = Image.createImage("/21.png");
            HeroU = Image.createImage("/28.png");

            HeroL1 = Image.createImage("/1.png");
            HeroL2 = Image.createImage("/2.png");
            HeroL3 = Image.createImage("/3.png");
            HeroL4 = Image.createImage("/4.png");
            HeroL5 = Image.createImage("/5.png");
            HeroL6 = Image.createImage("/6.png");

            HeroR1 = Image.createImage("/8.png");
            HeroR2 = Image.createImage("/9.png");
            HeroR3 = Image.createImage("/10.png");
            HeroR4 = Image.createImage("/11.png");
            HeroR5 = Image.createImage("/12.png");
            HeroR6 = Image.createImage("/13.png");

            HeroD1 = Image.createImage("/15.png");
            HeroD2 = Image.createImage("/16.png");
            HeroD3 = Image.createImage("/17.png");
            HeroD4 = Image.createImage("/18.png");
            HeroD5 = Image.createImage("/19.png");
            HeroD6 = Image.createImage("/20.png");

            HeroU1 = Image.createImage("/22.png");
            HeroU2 = Image.createImage("/23.png");
            HeroU3 = Image.createImage("/24.png");
            HeroU4 = Image.createImage("/25.png");
            HeroU5 = Image.createImage("/26.png");
            HeroU6 = Image.createImage("/27.png");

            ground_texture = Image.createImage("/texturetrava1.png");

            HeroKL1 = Image.createImage("/hero_kick_left_1.png");
            HeroKL2 = Image.createImage("/hero_kick_left_2.png");
            HeroKL3 = Image.createImage("/hero_kick_left_3.png");
            HeroKL4 = Image.createImage("/hero_kick_left_4.png");
            HeroKL5 = Image.createImage("/hero_kick_left_5.png");
            HeroKL6 = Image.createImage("/hero_kick_left_6.png");

            HeroKR1 = Image.createImage("/hero_kick_right_1.png");
            HeroKR2 = Image.createImage("/hero_kick_right_2.png");
            HeroKR3 = Image.createImage("/hero_kick_right_3.png");
            HeroKR4 = Image.createImage("/hero_kick_right_4.png");
            HeroKR5 = Image.createImage("/hero_kick_right_5.png");
            HeroKR6 = Image.createImage("/hero_kick_right_6.png");

            HeroKU1 = Image.createImage("/hero_kick_up_1.png");
            HeroKU2 = Image.createImage("/hero_kick_up_2.png");
            HeroKU3 = Image.createImage("/hero_kick_up_3.png");
            HeroKU4 = Image.createImage("/hero_kick_up_4.png");
            HeroKU5 = Image.createImage("/hero_kick_up_5.png");
            HeroKU6 = Image.createImage("/hero_kick_up_6.png");

            HeroKD1 = Image.createImage("/hero_kick_down_1.png");
            HeroKD2 = Image.createImage("/hero_kick_down_2.png");
            HeroKD3 = Image.createImage("/hero_kick_down_3.png");
            HeroKD4 = Image.createImage("/hero_kick_down_4.png");
            HeroKD5 = Image.createImage("/hero_kick_down_5.png");
            HeroKD6 = Image.createImage("/hero_kick_down_6.png");

            WolfL = Image.createImage("/Volk stay left.png");
            WolfR = Image.createImage("/Volk stay right.png");
            WolfD = Image.createImage("/Volkrun stay down.png");
            WolfU = Image.createImage("/Volk stay up.png");

            WolfL1 = Image.createImage("/lVolkrun1.png");
            WolfL2 = Image.createImage("/lVolkrun2.png");
            WolfL3 = Image.createImage("/lVolkrun3.png");
            WolfL4 = Image.createImage("/lVolkrun4.png");
            WolfL5 = Image.createImage("/lVolkrun5.png");
            WolfL6 = Image.createImage("/lVolkrun6.png");

            WolfR1 = Image.createImage("/rVolkrun1.png");
            WolfR2 = Image.createImage("/rVolkrun2.png");
            WolfR3 = Image.createImage("/rVolkrun3.png");
            WolfR4 = Image.createImage("/rVolkrun4.png");
            WolfR5 = Image.createImage("/rVolkrun5.png");
            WolfR6 = Image.createImage("/rVolkrun6.png");

            WolfD1 = Image.createImage("/Volkrun1.png");
            WolfD2 = Image.createImage("/Volkrun2.png");
            WolfD3 = Image.createImage("/Volkrun3.png");
            WolfD4 = Image.createImage("/Volkrun4.png");
            WolfD5 = Image.createImage("/Volkrun5.png");
            WolfD6 = Image.createImage("/Volkrun6.png");

            WolfU1 = Image.createImage("/uVolkrun1.png");
            WolfU2 = Image.createImage("/uVolkrun2.png");
            WolfU3 = Image.createImage("/uVolkrun3.png");
            WolfU4 = Image.createImage("/uVolkrun4.png");
            WolfU5 = Image.createImage("/uVolkrun5.png");
            WolfU6 = Image.createImage("/uVolkrun6.png");

        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        try
        {
            images.addElement(Image.createImage("/Tree2 11.png"));              // 0
            images.addElement(Image.createImage("/Tree2 12.png"));              // 1
            images.addElement(Image.createImage("/Tree2 13.png"));              // 2
            images.addElement(Image.createImage("/Tree2 21.png"));              // 3
            images.addElement(Image.createImage("/Tree2 22.png"));              // 4
            images.addElement(Image.createImage("/Tree2 23.png"));              // 5
            images.addElement(Image.createImage("/Tree2 24.png"));              // 6
            images.addElement(Image.createImage("/Tree2 31.png"));              // 7
            images.addElement(Image.createImage("/Tree2 32.png"));              // 8
            images.addElement(Image.createImage("/Tree2 33.png"));              // 9

            images.addElement(Image.createImage("/Fakel1.png"));                // 10
            images.addElement(Image.createImage("/Fakel2.png"));                // 11
            images.addElement(Image.createImage("/Fakel3.png"));                // 12
            images.addElement(Image.createImage("/Fakel4.png"));                // 13
            images.addElement(Image.createImage("/Fakel5.png"));                // 14
            images.addElement(Image.createImage("/Fakel6.png"));                // 15

            images.addElement(Image.createImage("/Fakel0.png"));                // 16
            images.addElement(Image.createImage("/Fakel01left.png"));           // 17
            images.addElement(Image.createImage("/Fakel01right.png"));          // 18


        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


        /*int i,j;
        for (i=0; i<=100; i++)
        {
        for (j=0; j<=100; j++)
        {
        MapObject y = new MapObject();
        y.i = 0;
        y.x = i*32;
        y.y = j*32;
        lowObjects.addElement(y);
        }
        }*/


        AddTree2(0,0);
        AddTree2(32,32);
        MapObject t_flame = new MapObject(128,128,10,true);
        MapObject t_fakel = new MapObject(128,128+32,16,false);
        floatObjects.addElement(t_flame);
        floatObjects.addElement(t_fakel);

        words.addElement("Тест движка.");
        words.addElement("Подождите 10-15 секунд.");
        words.addElement("Все.");
        words.addElement("Добро пожаловать!");
        words.addElement("Ща мы научим вас управлению.");
        words.addElement("2,4,6,8 - вот и все управление.");

        int i;
        int x,y;
        for (i=0; i<1000; i++)
        {
            x = Math.abs(Randomizer.nextInt()%3200);
            y = Math.abs(Randomizer.nextInt()%3200);
            if ((x>182)&&(y>182)) AddTree2(x,y);
        }

        prepareSnow(false);

        Light l = new Light();
        l.x=144;
        l.y=144;
        l.intensity=144;
        lights.addElement(l);

        try {
            dark = Image.createImage("/dark.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void tick()
    {
        // Перемещаем героя, если необходимо
        if (action==A_WALK)
        {
            if (facing==F_LEFT) HeroX-=6;
            if (facing==F_RIGHT) HeroX+=6;
            if (facing==F_UP) HeroY-=6;
            if (facing==F_DOWN) HeroY+=6;
            state++;
            if (state>6) state=1;

            /*if (facing==F_LEFT) tx+=6;
            if (facing==F_RIGHT) tx-=6;
            if (facing==F_UP) ty+=6;
            if (facing==F_DOWN) ty-=6;*/
        }

        if (action==A_ATTACK)
        {
            state++;
            if (state>6) action=A_STILL;
        }

        // И волка

        if (wolfAction==A_WALK)
        {
            if (wolfFacing==F_LEFT) WolfX-=6;
            if (wolfFacing==F_RIGHT) WolfX+=6;
            if (wolfFacing==F_UP) WolfY-=6;
            if (wolfFacing==F_DOWN) WolfY+=6;
            wolfState++;
            if (wolfState>6)
            {
                wolfAction = A_STILL;
                wolfState = 1;
            }
        }

        // Анимируем

        checkAnimation();

        // Проверяем столкновения

        checkHits();
        checkWolfHits();
    }
    
    /**
     * paint
     */
    public void doPaint(Graphics m)
    {
        //m = canvas.getGraphics();
        int i;

        // от сих
        OnScreen=0;
        hit=false;
        // до сих

        // Закрашиваем экран
        m.setColor(0,0,0);
        m.fillRect(0, 0, getWidth(), getHeight());

        // Выставляем камеру
        CamX = HeroX-getWidth()/2+16;
        CamY = HeroY-getHeight()/2+30-32;

        // Рисуем траву

        tx = 176-CamX;
        ty = 220-CamY;

        while (tx>0) tx-=176;
        while (tx<-176) tx+=176;
        while (ty>0) ty-=220;
        while (ty<-220) ty+=220;

        int j;
        for (i=0; i<=getWidth()/176+2; i++)
        {
            for (j=0; j<=getHeight()/220+2; j++)
            {
                m.drawImage(ground_texture, tx+i*176, ty+j*220, Graphics.LEFT | Graphics.TOP);
            }
        }

        // Рисуем карту, 1
        DrawLowObjects(m);
        DrawLowFloats(m);

        // Рисуем героя и волка

        if (HeroY>WolfY+16)
        {
            drawWolf(m);
            drawHero(m);
        }
        else
        {
            drawHero(m);
            drawWolf(m);
        }

        // Рисуем карту, 2

        DrawHighFloats(m);
        DrawHighObjects(m);

        // Погода

        drawWeather(m);

        // Если сцена - рисуем полоски

        if (Scene)
        {
            m.setColor(0,0,0);
            m.fillRect(0, 0, getWidth(), 50);
            m.fillRect(0, getHeight()-50, getWidth(), 50);
            m.setColor(128,128,128);
            m.drawLine(0, 50, getWidth(), 50);
            m.drawLine(0, getHeight()-50, getWidth(), getHeight()-50);
        }

        // А если диалог - рисуем диалог

        if (words.size()>0)
        {
            m.setColor(0,0,0);
            m.fillRect(0, getHeight()-50, getWidth(), 50);
            m.setColor(128,128,128);
            m.drawLine(0, getHeight()-50, getWidth(), getHeight()-50);

            m.setColor(255,255,255);
            m.drawString((String) words.elementAt(0), 5, getHeight()-45, Graphics.LEFT | Graphics.TOP);
        }

        // AI волка

        if (HeroX>WolfX+32) wolfFacing=F_RIGHT;
        if (HeroX+32<WolfX) wolfFacing=F_LEFT;
        if (HeroY>WolfY+32) wolfFacing=F_DOWN;
        if (HeroY+32<WolfY) wolfFacing=F_UP;

        int distance=64;

        if (HeroX>WolfX+32 + distance) { wolfFacing=F_RIGHT; wolfAction=A_WALK; }
        if (HeroX+32 + distance < WolfX) { wolfFacing=F_LEFT; wolfAction=A_WALK; }
        if (HeroY>WolfY+32 + distance) { wolfFacing=F_DOWN; wolfAction=A_WALK; }
        if (HeroY+32 + distance < WolfY) { wolfFacing=F_UP; wolfAction=A_WALK; }

        // Информация

            time2 = time;
            time = System.currentTimeMillis();
            tw = (int) (time - time2);
            tww += tw;
            k++;
            if (tww>=1000)
            {
                fps = k;
                tww = 0;
                k = 0;

                if (fps>30) qw+=10;
                if (fps<10) qw-=10;
                if (qw<0) qw=0;
            }
        

        m.setColor(255,255,255);
        m.drawString("Объектов: "+OnScreen+ " fps: "+fps, 0, 0, Graphics.LEFT | Graphics.TOP);
        if (hit) m.drawString("HIT", 0, 20, Graphics.LEFT | Graphics.TOP);
        m.drawString("action: "+action+" face: "+facing+" state: "+state, 0, 40, Graphics.LEFT | Graphics.TOP);
        m.drawString("X: "+HeroX+" Y: "+HeroY+" delay: "+qw, 0, 60, Graphics.LEFT | Graphics.TOP);
        m.drawString("waction: "+wolfAction+" wface: "+wolfFacing+" wstate: "+wolfState, 0, 80, Graphics.LEFT | Graphics.TOP);

        m.drawRect(HeroX-CamX, HeroY-CamY, 32, 32);

        // Отрисовка

        //g.drawImage(canvas,0,0,Graphics.TOP|Graphics.LEFT);
        //repaint();

        try
        {
            Thread.sleep(qw);
        }
        catch (Throwable ex)
        {
            
        }
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) 
    {
        if (keyCode==KEY_NUM3)
        {
            diablo.Diablo.display.setCurrent(diablo.Diablo.backpack);
        }

        if (keyCode == -4) { facing = F_RIGHT; action=A_WALK; state=1; }
        if (keyCode == -3) { facing = F_LEFT; action=A_WALK; state=1; }
        if (keyCode == -2) { facing = F_DOWN; action=A_WALK; state=1; }
        if (keyCode == -1) { facing = F_UP; action=A_WALK; state=1; }
        if (keyCode == KEY_NUM6) { facing = F_RIGHT; action=A_WALK; state=1; }
        if (keyCode == KEY_NUM4) { facing = F_LEFT; action=A_WALK; state=1; }
        if (keyCode == KEY_NUM8) { facing = F_DOWN; action=A_WALK; state=1; }
        if (keyCode == KEY_NUM2) { facing = F_UP; action=A_WALK; state=1; }

        // Не помешает ввести дополнительные проверки

        if (keyCode == KEY_STAR)
        {
            Scene = !Scene;
        }

        if (keyCode == KEY_POUND)
        {
            weather++;
            if (weather>NIGHT) weather=SUN;
        }

        if ((keyCode == KEY_NUM5)||(keyCode==-5))
        {
            if (words.size()>0)
            {
                words.removeElementAt(0);
            }
            else
            {
                if (!(action == A_ATTACK))
                {
                    action = A_ATTACK;
                    state = 1;
                }
            }
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode)
    {
        if (keyCode == -4) { action=A_STILL; }
        if (keyCode == -3) { action=A_STILL; }
        if (keyCode == -2) { action=A_STILL; }
        if (keyCode == -1) { action=A_STILL; }
        if (keyCode == KEY_NUM6) { action=A_STILL; }
        if (keyCode == KEY_NUM4) { action=A_STILL; }
        if (keyCode == KEY_NUM8) { action=A_STILL; }
        if (keyCode == KEY_NUM2) { action=A_STILL; }

        // Не помешает ввести дополнительные проверки
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
    protected  void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y) {
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y) {
    }

}