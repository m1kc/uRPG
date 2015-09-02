/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.*;

/**
 * @author пользователь
 */
public class StartMenu extends Canvas
{
    String[] menu = {"Играть","Выход"};
    int s = 0;
    Font f = Font.getDefaultFont();

    /**
     * constructor
     */
    public StartMenu()
    {
        setFullScreenMode(true);
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        g.setColor(0);
        g.fillRect(0,0,getWidth(),getHeight());
        for (int i=0; i<menu.length; i++)
        {
            if (i==s) g.setColor(0xFF0000);
            else g.setColor(0xFFFFFF);
            g.drawString(menu[i], 5, 5+i*f.getHeight(), Graphics.LEFT|Graphics.TOP);
        }
        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        if (getGameAction(keyCode)==UP) s--;
        if (getGameAction(keyCode)==DOWN) s++;
        if (s<0) s = 0;
        if (s>=menu.length) s = menu.length-1;
        if (getGameAction(keyCode)==FIRE)
        {
            if (s==0)
            {
                //uRPG.c.paused = false;
                //uRPG.c.initEngine();
                //uRPG.c.loadTestLevel();
                //uRPG.d.setCurrent(uRPG.c);
                uRPG.ll.loadLevel("test!");
            }
            if (s==1)
            {
                uRPG.midlet.notifyDestroyed();
            }
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode)
    {
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

}