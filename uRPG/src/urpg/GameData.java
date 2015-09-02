/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import java.util.*;

/**
 *
 * @author пользователь
 */
public class GameData
{
    Vector imageFactory = new Vector();
    Vector unitFactory = new Vector();
    Vector spriteFactory = new Vector();

    Animation stand,walk,attack,dead;

    public GameData()
    {
        {
            int[] left = {6};
            int[] down = {20};
            int[] up = {27};
            int[] right = {13};
            stand = new Animation(left,right,up,down);
        }
        {
            int[] left = {0,1,2,3,4,5};
            int[] down = {14,15,16,17,18,19};
            int[] up = {21,22,23,24,25,26};
            int[] right = {7,8,9,10,11,12};
            walk = new Animation(left,right,up,down);
        }
        {
            int[] left = {34,35,36,37,38,39};
            int[] down = {28,29,30,31,32,33};
            int[] up = {46,47,48,49,50,51};
            int[] right = {40,41,42,43,44,45};
            attack = new Animation(left,right,up,down);
        }
        {
            int[] left = {52};
            int[] down = {52};
            int[] up = {52};
            int[] right = {52};
            dead = new Animation(left,right,up,down);
        }

        registerImages();
        registerSprites();
        registerUnits();
    }

    private void registerUnits()
    {
        registerUnit("hero", "/res/hero1.png", 112, 72, stand, walk, attack, dead, 16, 16, true, false, true, false, 5, 5, 5, 5, 5);
    }

    private void registerSprites()
    {
        registerSprite("tree2", "/res/Tree2g4.png", -1, -1, 16, 16, true, false, true, null, false);
        registerSprite("chest", "/res/sunduk1.png", -1, -1, 16, 32, true, false, true, null, false);
        registerSprite("bush1", "/res/a_f_bush1_pb.png", -1, -1, 32, 32, true, false, true, null, false);
        registerSprite("bush2", "/res/a_f_bush2_pb.png", -1, -1, 32, 32, true, false, true, null, false);
        registerSprite("bush3", "/res/a_f_bush3_pb.png", -1, -1, 32, 32, true, false, true, null, false);
    }

    private void registerImages()
    {
        registerImage("grass","/res/grass1.png");
        registerImage("ground","/res/ground_textures.png");
        registerImage("water","/res/water.png");
        registerImage("waterside","/res/waterside.png");
    }

    public void registerUnit(String name, String imageName, int frameWidth, int frameHeight, Animation stand, Animation walk, Animation attack, Animation dead, int collisionRectWidth, int collisionRectHeight, boolean registerAsSprite, boolean registerAsGroundSprite, boolean registerAsUnit, boolean registerAsMassive, int power, int agility, int reaction, int adrenaline,int stamina)
    {
        unitFactory.addElement(new UnitData(name, imageName, frameWidth, frameHeight, stand, walk, attack, dead, collisionRectWidth, collisionRectHeight, registerAsSprite, registerAsGroundSprite, registerAsUnit, registerAsMassive, power, agility, reaction, adrenaline, stamina));
    }

    public void registerSprite(String name, String imageName, int frameWidth, int frameHeight, int collisionRectWidth, int collisionRectHeight, boolean registerAsSprite, boolean registerAsGroundSprite, boolean registerAsMassive, int[] animation, boolean registerAsAnimated)
    {
        spriteFactory.addElement(new SpriteData(name, imageName, frameWidth, frameHeight, collisionRectWidth, collisionRectHeight, registerAsSprite, registerAsGroundSprite, registerAsMassive, animation, registerAsAnimated));
    }

    public void registerImage(String name, String imageName)
    {
        imageFactory.addElement(new ImageData(name, imageName));
    }
}
