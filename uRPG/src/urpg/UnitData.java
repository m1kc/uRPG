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
public class UnitData
{
    String name;

    String imageName;
    Image i;
    int frameWidth,frameHeight;
    Animation stand,walk,attack,dead;
    int collisionRectWidth,collisionRectHeight;
    boolean registerAsSprite;
    boolean registerAsGroundSprite;
    boolean registerAsUnit;
    boolean registerAsMassive;
    int power,agility,reaction,adrenaline,stamina;

    public UnitData(String name, String imageName, int frameWidth, int frameHeight, Animation stand, Animation walk, Animation attack, Animation dead, int collisionRectWidth, int collisionRectHeight, boolean registerAsSprite, boolean registerAsGroundSprite, boolean registerAsUnit, boolean registerAsMassive, int power, int agility, int reaction, int adrenaline,int stamina)
    {
        this.name = name;
        this.imageName = imageName;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.stand = stand;
        this.walk = walk;
        this.attack = attack;
        this.dead = dead;
        this.collisionRectWidth = collisionRectWidth;
        this.collisionRectHeight = collisionRectHeight;
        this.registerAsSprite = registerAsSprite;
        this.registerAsGroundSprite = registerAsGroundSprite;
        this.registerAsUnit = registerAsUnit;
        this.registerAsMassive = registerAsMassive;
        this.power = power;
        this.agility = agility;
        this.reaction = reaction;
        this.adrenaline = adrenaline;
        this.stamina = stamina;
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
