/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.*;

/**
 * @author пользователь
 */
public class DialogCanvas extends Canvas
{
    Dialog dialog;
    int s = 0;

    Font f = Font.getDefaultFont();

    /**
     * constructor
     */
    public DialogCanvas()
    {
        setFullScreenMode(true);
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g)
    {
        g.setColor(0x000000);
        g.fillRect(0,0,getWidth(),getHeight());

        g.setColor(0x00FF00);

        int marker = f.getHeight();
        g.drawString(dialog.title, getWidth()/2, marker, Graphics.HCENTER | Graphics.TOP);
        marker+=f.getHeight()*2;
        for (int i=0; i<dialog.words.length; i++)
        {
            if (i==s) g.setColor(0x00FF00);
            else g.setColor(0xFFFFFF);
            g.drawString(""+(i+1)+". "+dialog.words[i], 10, marker, Graphics.LEFT | Graphics.TOP);
            marker+=f.getHeight();
        }

        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        if (getGameAction(keyCode)==DOWN) s++;
        if (getGameAction(keyCode)==UP) s--;
        if (s<0) s=0;
        if (s>=dialog.words.length) s = dialog.words.length-1;

        if (getGameAction(keyCode)==FIRE) 
        {
            if (dialog.exitAction != null) dialog.exitAction.run();
            showDialog(dialog.vars[s]);
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

    public void showDialog(Dialog dialog)
    {
        if (dialog==null)
        {
            uRPG.d.setCurrent(uRPG.c);
        }
        else
        {
            this.dialog = dialog;
            s = 0;
            uRPG.d.setCurrent(uRPG.dc);
        }
    }

}