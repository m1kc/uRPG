/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.Image;

/**
 *
 * @author пользователь
 */
public class ImageData
{
    String name;

    String imageName;
    Image i;

    public ImageData(String name, String imageName)
    {
        this.name = name;
        this.imageName = imageName;
    }

    public Image getImage()
    {
        if (i==null)
        {
            try
            {
                i = Image.createImage(imageName);
            }
            catch (Throwable ex)
            {
            }
        }

        return i;
    }
}
