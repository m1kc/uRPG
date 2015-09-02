/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * @author пользователь
 */
public class GameDesign {

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Image ground_textures;
    private TiledLayer ground_testlevel;
    private Image hero1;
    private Sprite hero;
    public int stand_leftDelay = 200;
    public int[] stand_left = {6};
    public int stand_rightDelay = 200;
    public int[] stand_right = {13};
    public int stand_upDelay = 200;
    public int[] stand_up = {27};
    public int stand_downDelay = 200;
    public int[] stand_down = {20};
    private Image Tree2g4;
    private Sprite tree2;
    public int tree2seq1Delay = 200;
    public int[] tree2seq1 = {0};
    private Image grass1;
    private Sprite grass_NO_USE;
    public int grass_NO_USE_default_sequenceDelay = 200;
    public int[] grass_NO_USE_default_sequence = {0};
    private Image house1;
    private Image tree10alt;
    private Sprite Chest;
    public int ChestSeq1Delay = 200;
    public int[] ChestSeq1 = {0};
    private Image sunduk1;
    private Sprite Bush1;
    public int Bush1seq001Delay = 200;
    public int[] Bush1seq001 = {0};
    private Image a_f_bush1_pb;
    private Image a_f_bush3_pb;
    private Image a_f_bush2_pb;
    private Sprite Bush2;
    public int Bush2seq001Delay = 200;
    public int[] Bush2seq001 = {0};
    private Image water;
    public int aniwaterseq001Delay = 200;
    public int[] aniwaterseq001 = {1, 2, 3, 4, 5, 6};
    private TiledLayer Water1;
    public int aniwaterWater1;
    private Sprite Bush3;
    public int Bush3seq001Delay = 200;
    public int[] Bush3seq001 = {0};
    private Image waterside;
    private TiledLayer Waterside1;
    private Sprite impzgrass_NO_USE;
    public int impzgrass_NO_USEseq001Delay = 200;
    public int[] impzgrass_NO_USEseq001 = {0, 0, 0, 0, 0};
    private Image back_pb;
    //</editor-fold>//GEN-END:|fields|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    public void updateLayerManagerForTestLevel(LayerManager lm) throws java.io.IOException {//GEN-LINE:|1-updateLayerManager|0|1-preUpdate
        // write pre-update user code here
//GEN-LINE:|1-updateLayerManager|1|1-postUpdate
        // write post-update user code here
    }//GEN-BEGIN:|1-updateLayerManager|2|
//GEN-END:|1-updateLayerManager|2|

    public Image getGround_textures() throws java.io.IOException {//GEN-BEGIN:|2-getter|0|2-preInit
        if (ground_textures == null) {//GEN-END:|2-getter|0|2-preInit
            // write pre-init user code here
            ground_textures = Image.createImage("/res/ground_textures.png");//GEN-BEGIN:|2-getter|1|2-postInit
        }//GEN-END:|2-getter|1|2-postInit
        // write post-init user code here
        return this.ground_textures;//GEN-BEGIN:|2-getter|2|
    }
//GEN-END:|2-getter|2|

    public TiledLayer getGround_testlevel() throws java.io.IOException {//GEN-BEGIN:|3-getter|0|3-preInit
        if (ground_testlevel == null) {//GEN-END:|3-getter|0|3-preInit
            // write pre-init user code here
            ground_testlevel = new TiledLayer(20, 20, getGround_textures(), 32, 32);//GEN-BEGIN:|3-getter|1|3-midInit
            int[][] tiles = {
                { 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 2, 1, 1, 0, 0, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 0, 0, 1 },
                { 2, 2, 2, 1, 0, 0, 0, 1, 1, 2, 2, 2, 2, 0, 0, 1, 1, 1, 0, 0 },
                { 1, 1, 2, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1, 1, 2, 2, 0, 1, 0, 0 },
                { 0, 2, 0, 1, 1, 2, 2, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 2, 2, 1, 1, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 },
                { 0, 1, 2, 2, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0 },
                { 0, 0, 0, 2, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 0, 1, 2, 2, 2, 2, 1, 1, 2, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };//GEN-END:|3-getter|1|3-midInit
            // write mid-init user code here
            for (int row = 0; row < 20; row++) {//GEN-BEGIN:|3-getter|2|3-postInit
                for (int col = 0; col < 20; col++) {
                    ground_testlevel.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|3-getter|2|3-postInit
        // write post-init user code here
        return ground_testlevel;//GEN-BEGIN:|3-getter|3|
    }
//GEN-END:|3-getter|3|

    public Image getHero1() throws java.io.IOException {//GEN-BEGIN:|4-getter|0|4-preInit
        if (hero1 == null) {//GEN-END:|4-getter|0|4-preInit
            // write pre-init user code here
            hero1 = Image.createImage("/res/hero1.png");//GEN-BEGIN:|4-getter|1|4-postInit
        }//GEN-END:|4-getter|1|4-postInit
        // write post-init user code here
        return this.hero1;//GEN-BEGIN:|4-getter|2|
    }
//GEN-END:|4-getter|2|



    public Sprite getHero() throws java.io.IOException {//GEN-BEGIN:|7-getter|0|7-preInit
        if (hero == null) {//GEN-END:|7-getter|0|7-preInit
            // write pre-init user code here
            hero = new Sprite(getHero1(), 112, 72);//GEN-BEGIN:|7-getter|1|7-postInit
            hero.setFrameSequence(stand_left);//GEN-END:|7-getter|1|7-postInit
            // write post-init user code here
        }//GEN-BEGIN:|7-getter|2|
        return hero;
    }
//GEN-END:|7-getter|2|

    public Image getTree2g4() throws java.io.IOException {//GEN-BEGIN:|12-getter|0|12-preInit
        if (Tree2g4 == null) {//GEN-END:|12-getter|0|12-preInit
            // write pre-init user code here
            Tree2g4 = Image.createImage("/res/Tree2g4.png");//GEN-BEGIN:|12-getter|1|12-postInit
        }//GEN-END:|12-getter|1|12-postInit
        // write post-init user code here
        return this.Tree2g4;//GEN-BEGIN:|12-getter|2|
    }
//GEN-END:|12-getter|2|

    public Sprite getTree2() throws java.io.IOException {//GEN-BEGIN:|13-getter|0|13-preInit
        if (tree2 == null) {//GEN-END:|13-getter|0|13-preInit
            // write pre-init user code here
            tree2 = new Sprite(getTree2g4(), 96, 128);//GEN-BEGIN:|13-getter|1|13-postInit
            tree2.setFrameSequence(tree2seq1);//GEN-END:|13-getter|1|13-postInit
            // write post-init user code here
        }//GEN-BEGIN:|13-getter|2|
        return tree2;
    }
//GEN-END:|13-getter|2|

    public Image getGrass1() throws java.io.IOException {//GEN-BEGIN:|15-getter|0|15-preInit
        if (grass1 == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            grass1 = Image.createImage("/res/grass1.png");//GEN-BEGIN:|15-getter|1|15-postInit
        }//GEN-END:|15-getter|1|15-postInit
        // write post-init user code here
        return this.grass1;//GEN-BEGIN:|15-getter|2|
    }
//GEN-END:|15-getter|2|

    public Sprite getGrass_NO_USE() throws java.io.IOException {//GEN-BEGIN:|16-getter|0|16-preInit
        if (grass_NO_USE == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            grass_NO_USE = new Sprite(getGrass1(), 176, 220);//GEN-BEGIN:|16-getter|1|16-postInit
            grass_NO_USE.setFrameSequence(grass_NO_USE_default_sequence);//GEN-END:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return grass_NO_USE;
    }
//GEN-END:|16-getter|2|

    public Image getHouse1() throws java.io.IOException {//GEN-BEGIN:|18-getter|0|18-preInit
        if (house1 == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            house1 = Image.createImage("/res/a_f_bush3_pb.png");//GEN-BEGIN:|18-getter|1|18-postInit
        }//GEN-END:|18-getter|1|18-postInit
        // write post-init user code here
        return this.house1;//GEN-BEGIN:|18-getter|2|
    }
//GEN-END:|18-getter|2|

    public Image getTree10alt() throws java.io.IOException {//GEN-BEGIN:|23-getter|0|23-preInit
        if (tree10alt == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            tree10alt = Image.createImage("/res/Tree2g4.png");//GEN-BEGIN:|23-getter|1|23-postInit
        }//GEN-END:|23-getter|1|23-postInit
        // write post-init user code here
        return this.tree10alt;//GEN-BEGIN:|23-getter|2|
    }
//GEN-END:|23-getter|2|



    public Image getSunduk1() throws java.io.IOException {//GEN-BEGIN:|26-getter|0|26-preInit
        if (sunduk1 == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            sunduk1 = Image.createImage("/res/sunduk1.png");//GEN-BEGIN:|26-getter|1|26-postInit
        }//GEN-END:|26-getter|1|26-postInit
        // write post-init user code here
        return this.sunduk1;//GEN-BEGIN:|26-getter|2|
    }
//GEN-END:|26-getter|2|

    public Sprite getChest() throws java.io.IOException {//GEN-BEGIN:|27-getter|0|27-preInit
        if (Chest == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            Chest = new Sprite(getSunduk1(), 32, 32);//GEN-BEGIN:|27-getter|1|27-postInit
            Chest.setFrameSequence(ChestSeq1);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return Chest;
    }
//GEN-END:|27-getter|2|





    public Image getA_f_bush1_pb() throws java.io.IOException {//GEN-BEGIN:|33-getter|0|33-preInit
        if (a_f_bush1_pb == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            a_f_bush1_pb = Image.createImage("/res/a_f_bush1_pb.png");//GEN-BEGIN:|33-getter|1|33-postInit
        }//GEN-END:|33-getter|1|33-postInit
        // write post-init user code here
        return this.a_f_bush1_pb;//GEN-BEGIN:|33-getter|2|
    }
//GEN-END:|33-getter|2|

    public Sprite getBush1() throws java.io.IOException {//GEN-BEGIN:|34-getter|0|34-preInit
        if (Bush1 == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            Bush1 = new Sprite(getA_f_bush1_pb(), 135, 116);//GEN-BEGIN:|34-getter|1|34-postInit
            Bush1.setFrameSequence(Bush1seq001);//GEN-END:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return Bush1;
    }
//GEN-END:|34-getter|2|

    public Image getA_f_bush2_pb() throws java.io.IOException {//GEN-BEGIN:|36-getter|0|36-preInit
        if (a_f_bush2_pb == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            a_f_bush2_pb = Image.createImage("/res/a_f_bush2_pb.png");//GEN-BEGIN:|36-getter|1|36-postInit
        }//GEN-END:|36-getter|1|36-postInit
        // write post-init user code here
        return this.a_f_bush2_pb;//GEN-BEGIN:|36-getter|2|
    }
//GEN-END:|36-getter|2|

    public Sprite getBush2() throws java.io.IOException {//GEN-BEGIN:|37-getter|0|37-preInit
        if (Bush2 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            Bush2 = new Sprite(getA_f_bush2_pb(), 140, 136);//GEN-BEGIN:|37-getter|1|37-postInit
            Bush2.setFrameSequence(Bush2seq001);//GEN-END:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return Bush2;
    }
//GEN-END:|37-getter|2|

    public Image getA_f_bush3_pb() throws java.io.IOException {//GEN-BEGIN:|39-getter|0|39-preInit
        if (a_f_bush3_pb == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            a_f_bush3_pb = Image.createImage("/res/a_f_bush3_pb.png");//GEN-BEGIN:|39-getter|1|39-postInit
        }//GEN-END:|39-getter|1|39-postInit
        // write post-init user code here
        return this.a_f_bush3_pb;//GEN-BEGIN:|39-getter|2|
    }
//GEN-END:|39-getter|2|

    public Sprite getBush3() throws java.io.IOException {//GEN-BEGIN:|40-getter|0|40-preInit
        if (Bush3 == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            Bush3 = new Sprite(getA_f_bush3_pb(), 159, 124);//GEN-BEGIN:|40-getter|1|40-postInit
            Bush3.setFrameSequence(Bush3seq001);//GEN-END:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return Bush3;
    }
//GEN-END:|40-getter|2|

    public Image getWater() throws java.io.IOException {//GEN-BEGIN:|42-getter|0|42-preInit
        if (water == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            water = Image.createImage("/res/water.png");//GEN-BEGIN:|42-getter|1|42-postInit
        }//GEN-END:|42-getter|1|42-postInit
        // write post-init user code here
        return this.water;//GEN-BEGIN:|42-getter|2|
    }
//GEN-END:|42-getter|2|

    public TiledLayer getWater1() throws java.io.IOException {//GEN-BEGIN:|43-getter|0|43-preInit
        if (Water1 == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            Water1 = new TiledLayer(20, 20, getWater(), 32, 32);//GEN-BEGIN:|43-getter|1|43-midInit
            aniwaterWater1 = Water1.createAnimatedTile(aniwaterseq001[0]);
            int[][] tiles = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, aniwaterWater1, aniwaterWater1, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0 },
                { 0, 0, 0, 0, aniwaterWater1, aniwaterWater1, aniwaterWater1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };//GEN-END:|43-getter|1|43-midInit
            // write mid-init user code here
            for (int row = 0; row < 20; row++) {//GEN-BEGIN:|43-getter|2|43-postInit
                for (int col = 0; col < 20; col++) {
                    Water1.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|43-getter|2|43-postInit
        // write post-init user code here
        return Water1;//GEN-BEGIN:|43-getter|3|
    }
//GEN-END:|43-getter|3|

    public Image getWaterside() throws java.io.IOException {//GEN-BEGIN:|46-getter|0|46-preInit
        if (waterside == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            waterside = Image.createImage("/res/waterside.png");//GEN-BEGIN:|46-getter|1|46-postInit
        }//GEN-END:|46-getter|1|46-postInit
        // write post-init user code here
        return this.waterside;//GEN-BEGIN:|46-getter|2|
    }
//GEN-END:|46-getter|2|

    public TiledLayer getWaterside1() throws java.io.IOException {//GEN-BEGIN:|47-getter|0|47-preInit
        if (Waterside1 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            Waterside1 = new TiledLayer(20, 20, getWaterside(), 32, 32);//GEN-BEGIN:|47-getter|1|47-midInit
            int[][] tiles = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
                { 0, 0, 0, 0, 8, 12, 6, 6, 6, 6, 10, 0, 0, 12, 6, 10, 0, 0, 0, 7 },
                { 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 8, 0, 0, 7, 0, 8, 0, 0, 0, 7 },
                { 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 8, 0, 0, 9, 5, 11, 0, 0, 0, 7 },
                { 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 8, 12, 6, 6, 6, 6, 10, 0, 0, 7 },
                { 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 8, 7, 0, 0, 0, 0, 8, 0, 12, 3 },
                { 0, 0, 0, 0, 8, 9, 5, 5, 5, 5, 11, 7, 0, 2, 1, 0, 4, 6, 3, 0 },
                { 0, 0, 0, 0, 4, 6, 6, 6, 6, 6, 6, 3, 0, 8, 7, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 5, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 9, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 4, 6, 10, 9, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 8, 9, 1, 0, 0, 0, 0, 0, 0, 0, 4, 6, 3, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 6, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
            };//GEN-END:|47-getter|1|47-midInit
            // write mid-init user code here
            for (int row = 0; row < 20; row++) {//GEN-BEGIN:|47-getter|2|47-postInit
                for (int col = 0; col < 20; col++) {
                    Waterside1.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|47-getter|2|47-postInit
        // write post-init user code here
        return Waterside1;//GEN-BEGIN:|47-getter|3|
    }
//GEN-END:|47-getter|3|

    public Image getBack_pb() throws java.io.IOException {//GEN-BEGIN:|48-getter|0|48-preInit
        if (back_pb == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            back_pb = Image.createImage("/res/back_pb.png");//GEN-BEGIN:|48-getter|1|48-postInit
        }//GEN-END:|48-getter|1|48-postInit
        // write post-init user code here
        return this.back_pb;//GEN-BEGIN:|48-getter|2|
    }
//GEN-END:|48-getter|2|

    public Sprite getImpzgrass_NO_USE() throws java.io.IOException {//GEN-BEGIN:|49-getter|0|49-preInit
        if (impzgrass_NO_USE == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            impzgrass_NO_USE = new Sprite(getBack_pb(), 256, 256);//GEN-BEGIN:|49-getter|1|49-postInit
            impzgrass_NO_USE.setFrameSequence(impzgrass_NO_USEseq001);//GEN-END:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return impzgrass_NO_USE;
    }
//GEN-END:|49-getter|2|

    public void updateLayerManagerForWaterTest(LayerManager lm) throws java.io.IOException {//GEN-LINE:|51-updateLayerManager|0|51-preUpdate
        // write pre-update user code here
        getWaterside1().setPosition(0, 0);//GEN-BEGIN:|51-updateLayerManager|1|51-postUpdate
        getWaterside1().setVisible(true);
        lm.append(getWaterside1());
        getWater1().setPosition(0, 0);
        getWater1().setVisible(true);
        lm.append(getWater1());//GEN-END:|51-updateLayerManager|1|51-postUpdate
        // write post-update user code here
    }//GEN-BEGIN:|51-updateLayerManager|2|
//GEN-END:|51-updateLayerManager|2|





}
