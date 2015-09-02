/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.*;

/**
 * @author пользователь
 */
public class LevelLoader extends Canvas
{
    Font f = Font.getDefaultFont();

    String string = "Загрузка";
    String[] points = {"",".","..","..."};
    int i = 0;

    Thread operation;

    /**
     * constructor
     */
    public LevelLoader()
    {
        setFullScreenMode(true);
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        if (!operation.isAlive())
        {
            uRPG.d.setCurrent(uRPG.c);
            return;
        }

        g.setColor(0x000000);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(0xFFFFFF);
        i++; if (i>=points.length) i=0;
        g.drawString(string+points[i], 5, getHeight()/2-f.getHeight()/2, Graphics.LEFT | Graphics.TOP);

        try 
        {
            Thread.sleep(250);
        }
        catch (InterruptedException ex)
        {
        }

        repaint();
    }

    public void loadLevel(String s)
    {
        operation = null;

        if (s.equals("test!"))
        {
            operation = new Thread()
            {
                public void run()
                {
                    string = "Создание экземпляра";
                    uRPG.c = new MainCanvas();
                    string = "Инициализация";
                    uRPG.c.paused = false;
                    uRPG.c.initEngine();
                    string = "Загрузка";
                    uRPG.c.loadTestLevel();
                    string = "z-сортировка";
                    uRPG.c.zSort();
                }
            };
        }

        if (operation != null)
        {
            operation.start();
        }

        uRPG.d.setCurrent(this);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
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