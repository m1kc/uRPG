/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventory;

import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * @author Makc
 */
public class Backpack extends Canvas
{

    Font nameFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

    Vector items = new Vector();

    int se = 0;

    int maxweight = 50000;

    /**
     * constructor
     */
    public Backpack() 
    {
        setFullScreenMode(true);

        //test
        Weapon w = new Weapon();
        w.name="Топор";
        w.damage=16;
        w.level=1;
        w.strength=1;
        w.agility=2;
        w.intellect=0;
        w.stamina=1;
        w.spirit=0;
        w.icon = Image.createImage(32, 32);
        Graphics q = w.icon.getGraphics();
        q.setColor(128,128,128);
        q.fillRect(0, 0, 32, 32);
        q.setColor(128,64,0);
        q.fillRect(20, 0, 12, 32);
        q.setColor(192,192,192);
        q.fillRect(8, 0, 24, 10);
        //WeaponEnchant we = new WeaponEnchant();
        //we.name = "Инкрустированная рукоять";
        //w.enchants.addElement(we);
        w.weight = 1900;
        items.addElement(w);

        Something s = new Something();
        s.description = "Обычный серый камень.";
        s.name = "Камень";
        s.icon = Image.createImage(40, 20);
        Graphics g = s.icon.getGraphics();
        g.setColor(128,128,128);
        g.fillRect(0, 0, 40, 20);
        g.setColor(192,192,192);
        g.fillArc(0, 0, 40, 20, 0, 360);
        s.weight = 50;
        items.addElement(s);
    } 

    private String extractName(Object item)
    {
        if (item.getClass()==Weapon.class)
        {
            Weapon w;
            w = (Weapon) item;
            return w.name;
        }
        if (item.getClass()==Something.class)
        {
            Something w;
            w = (Something) item;
            return w.name;
        }

        return "";
    }

    private int extractWeight(Object item)
    {
        if (item.getClass()==Weapon.class)
        {
            Weapon w;
            w = (Weapon) item;
            return w.weight;
        }
        if (item.getClass()==Something.class)
        {
            Something w;
            w = (Something) item;
            return w.weight;
        }

        return 0;
    }

    /**
     * paint
     */
    public void paint(Graphics g) 
    {
        g.setColor(128, 128, 128);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(nameFont);

        int i,j;
        int w = 0;
        String s;
        for (i=0; i<items.size(); i++)
        {
            s = extractName(items.elementAt(i));
            j = extractWeight(items.elementAt(i));
            if (!(i==se))
            {
                g.setColor(255,255,255);
                g.drawString(s, 5, 5+i*nameFont.getHeight(), Graphics.LEFT | Graphics.TOP);
                g.drawString(j+" г", getWidth()-5, 5+i*nameFont.getHeight(), Graphics.RIGHT | Graphics.TOP);
            }
            else
            {
                g.setColor(255,255,255);
                g.fillRect(1, 5+i*nameFont.getHeight(), getWidth()-2, nameFont.getHeight());
                g.setColor(0,0,0);
                g.drawString(s, 5, 5+i*nameFont.getHeight(), Graphics.LEFT | Graphics.TOP);
                g.drawString(j+" г", getWidth()-5, 5+i*nameFont.getHeight(), Graphics.RIGHT | Graphics.TOP);
            }
            w+=j;
        }

        g.setColor(255,255,255);
        g.drawLine(0, getHeight()-nameFont.getHeight(), getWidth(), getHeight()-nameFont.getHeight());
        if (w<maxweight) g.setColor(255,255,255);
        if (w==maxweight) g.setColor(0,128,255);
        if (w>maxweight) g.setColor(255,128,128);
        g.drawString(w+"/"+maxweight, getWidth()/2, getHeight()-nameFont.getHeight(), Graphics.HCENTER | Graphics.TOP);

        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) 
    {
        if (keyCode==KEY_NUM3)
        {
            diablo.Diablo.display.setCurrent(diablo.Diablo.engine);
        }

        if (keyCode == -2) { se++; }
        if (keyCode == -1) { se--; }
        if (keyCode == KEY_NUM8) { se++; }
        if (keyCode == KEY_NUM2) { se--; }

        if (se<0) se=items.size()-1;
        if (se>=items.size()) se=0;

        if ((keyCode==-5)||(keyCode==KEY_NUM5))
        {
            diablo.Diablo.showitem.item = items.elementAt(se);
            diablo.Diablo.showitem.update();
            diablo.Diablo.display.setCurrent(diablo.Diablo.showitem);
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode) {
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode) {
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