/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author пользователь
 */
public class uRPG extends MIDlet
{
    static Display d;
    static MainCanvas c;
    static DialogCanvas dc;
    static StartMenu sm;
    static GameData data;
    static LevelLoader ll;
    static StatsScreen ss;

    static uRPG midlet;

    public void startApp() 
    {
        midlet = this;
        d = Display.getDisplay(this);
        //c = new MainCanvas();
        dc = new DialogCanvas();
        sm = new StartMenu();
        data = new GameData();
        ll = new LevelLoader();
        ss = new StatsScreen();
        d.setCurrent(sm);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
