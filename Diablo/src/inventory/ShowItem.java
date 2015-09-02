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
public class ShowItem extends Canvas
{
    Object item;

    Vector strings = new Vector();
    Image icon = Image.createImage(1, 1);

    Font nameFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

    int i;

    /**
     * constructor
     */
    public ShowItem()
    {
        setFullScreenMode(true);
    }

    public void update()
    {
        if (item.getClass()==Weapon.class)
        {
            Weapon item;
            item = (Weapon) this.item;
            strings.removeAllElements();

            strings.addElement(item.name);

            strings.addElement("Уровень: "+item.level);
            strings.addElement("Вес: "+item.weight+" грамм");

            strings.addElement("Атака: "+item.damage);

            strings.addElement("Сила: "+item.strength);
            strings.addElement("Ловкость: "+item.agility);
            strings.addElement("Интеллект: "+item.intellect);
            strings.addElement("Выносливость: "+item.stamina);
            strings.addElement("Боевой дух: "+item.spirit);

            if (item.enchants.size()>0)
            {
                strings.addElement("Энчанты:");
                WeaponEnchant we;
                for (i=0; i<item.enchants.size(); i++)
                {
                    we = (WeaponEnchant) item.enchants.elementAt(i);
                    strings.addElement(we.name);
                }
            }
            else
            {
                strings.addElement("Нет энчантов");
            }

            icon = item.icon;
        }

        if (item.getClass() == Something.class)
        {
            Something item;
            item = (Something) this.item;

            strings.removeAllElements();

            strings.addElement(item.name);
            strings.addElement("Вес: "+item.weight+" грамм");
            strings.addElement(item.description);

            icon = item.icon;
        }
    }
    
    /**
     * paint
     */
    public void paint(Graphics g) 
    {
        g.setColor(128, 128, 128);
        g.fillRect(0, 0, getWidth(), getHeight());        
        g.setColor(255,255,255);
        g.setFont(nameFont);

        for (i=0; i<strings.size(); i++)
        {
            g.drawString((String) strings.elementAt(i), 5, 5+i*nameFont.getHeight(), Graphics.LEFT | Graphics.TOP);
        }

        g.drawImage(icon, getWidth()-5, 5, Graphics.RIGHT | Graphics.TOP);

        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) 
    {
        if ((keyCode==-5)||(keyCode==KEY_NUM5))
        {
            this.item = null;
            this.icon = Image.createImage(1, 1);
            this.strings = new Vector();
            System.gc();
            diablo.Diablo.display.setCurrent(diablo.Diablo.backpack);
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