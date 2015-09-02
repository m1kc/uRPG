/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import java.util.*;
import javax.microedition.lcdui.*;
/**
 *
 * @author пользователь
 */
public class Weather
{
    boolean DGsupported;
    
    int screenWidth;
    int screenHeight;
    
    // It reminds me... it's not too bad, it's not too bad.
    int[] rain;
    int[] rainSpeeds;
    Vector tears = new Vector();
    Image tear;
    boolean genRain = false;
    
    Random random = new Random(new Date().getTime());
    
    public Weather()
    {
        try
        {
            tear = Image.createImage("/res/tear.png");
        }
        catch (Throwable ex)
        {
        }
    }
    
    public void drawRain(Graphics g)
    {
        if (rain == null)
        {
            rain = new int[screenWidth];
            rainSpeeds = new int[screenWidth];
            for (int i = 0; i < screenWidth; i++)
            {
                rainSpeeds[i] = Math.abs(random.nextInt() % 5) + 5;
                rain[i] = screenHeight;
            }
        }
        
        for (int i = 0; i < screenWidth; i++)
        {
            rain[i] += rainSpeeds[i];
            g.setColor(0x808080);
            g.drawLine(i, rain[i] - 5, i, rain[i]);
            if ((rain[i] > screenHeight + 5)&&(genRain))
            {
                rain[i] = -Math.abs(random.nextInt() % screenHeight);
                rainSpeeds[i] = Math.abs(random.nextInt() % 5) + 5;
            }
        }
        
        for (int i = 0; i < tears.size(); i++)
        {
            Tear tea = (Tear) tears.elementAt(i);
            g.drawImage(tear, tea.x, tea.y, Graphics.LEFT | Graphics.TOP);
            tea.lifetime--;
            if ((tea.lifetime%2==0)&&(tea.lifetime<50)) tea.y++;
            if (tea.lifetime == 0)
            {
                tears.removeElement(tea);
                i--;
            }
        }
        
        if ((random.nextInt() % 10 == 0)&&(genRain))
        {
            Tear tea = new Tear(Math.abs(random.nextInt() % screenWidth), Math.abs(random.nextInt() % screenHeight), 200);
            tears.addElement(tea);
        }
    }

}
