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
public class SpriteData
{
    String name;

    String imageName;
    Image i;
    int frameWidth, frameHeight;
    int collisionRectWidth,collisionRectHeight;
    boolean registerAsSprite;
    boolean registerAsGroundSprite;
    boolean registerAsMassive;
    int[] animation;
    boolean registerAsAnimated;

    public SpriteData(String name, String imageName, int frameWidth, int frameHeight, int collisionRectWidth, int collisionRectHeight, boolean registerAsSprite, boolean registerAsGroundSprite, boolean registerAsMassive, int[] animation, boolean registerAsAnimated)
    {
        this.name = name;
        this.imageName = imageName;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.collisionRectWidth = collisionRectWidth;
        this.collisionRectHeight = collisionRectHeight;
        this.registerAsSprite = registerAsSprite;
        this.registerAsGroundSprite = registerAsGroundSprite;
        this.registerAsMassive = registerAsMassive;
        this.animation = animation;
        this.registerAsAnimated = registerAsAnimated;
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
