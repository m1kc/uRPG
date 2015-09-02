/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.*;

/**
 * @author пользователь
 */
public class StatsScreen extends Canvas
{
    int mode = 0;
    int marker = 0;
    Font f = Font.getDefaultFont();
    int s = 0;

    /**
     * constructor
     */
    public StatsScreen()
    {
        setFullScreenMode(true);
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        marker = 0;
        g.setColor(0x000000);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (mode==0)
        {
            g.setColor(0x00FF00);
            g.drawString("Основные", getWidth()-10, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();
            g.drawLine(0, marker, getWidth()-10, marker);
            marker+=2;
            g.drawLine(0, marker, getWidth()-11, marker);

            g.setColor(0xFFFFFF);
            g.fillTriangle(getWidth()-5, f.getHeight()/2+5, getWidth()-5, f.getHeight()/2-5, getWidth(), f.getHeight()/2);
        }
        if (mode==1)
        {
            g.setColor(0x00FF00);
            g.drawString("Дополнительные", 10, marker, Graphics.LEFT | Graphics.TOP);
            marker+=f.getHeight();
            g.drawLine(10, marker, getWidth(), marker);
            marker+=2;
            g.drawLine(11, marker, getWidth(), marker);

            g.setColor(0xFFFFFF);
            g.fillTriangle(5, f.getHeight()/2+5, 5, f.getHeight()/2-5, 0, f.getHeight()/2);
        }
        marker+=10;
        if (mode==0)
        {
            if (s==0) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Сила:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.power, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==1) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Ловкость:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.agility, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==2) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Реакция:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.reaction, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==3) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Адреналин:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.adrenaline, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==4) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Выносливость:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.stamina, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
        }
        if (mode==1)
        {
            if (s==0) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Интеллект:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.intellect, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==1) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Внимательность:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.perception, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==2) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Харизма:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.charisma, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==3) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Лидерство:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.leadership, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
            marker+=f.getHeight();

            if (s==4) g.setColor(0x00FF00); else g.setColor(0xFFFFFF);
            g.drawString("Сообразительность:", 5, marker, Graphics.LEFT | Graphics.TOP);
            g.drawString(""+uRPG.c.hero.wisdom, getWidth()-5, marker, Graphics.RIGHT | Graphics.TOP);
        }
        marker+=f.getHeight();
        marker+=f.getHeight();
        g.setColor(0xFFFFFF);
        g.drawString("Опыт: "+uRPG.c.hero.exp, 5, marker, Graphics.LEFT | Graphics.TOP);

        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        if (getGameAction(keyCode)==RIGHT) mode=1;
        if (getGameAction(keyCode)==LEFT) mode=0;

        if (getGameAction(keyCode)==UP) s--;
        if (getGameAction(keyCode)==DOWN) s++;
        if (s<0) s=4;
        if (s>4) s=0;

        if (keyCode==KEY_NUM3) uRPG.d.setCurrent(uRPG.c);
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