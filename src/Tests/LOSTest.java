package Tests;

import Accessories.*;
import Creatures.Block;
import Creatures.Player;
import Game.Game;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LOSTest {
    static LOS los;
    static Game game;
    static GraphBuilder builder;
    static Convertors convertor;
    static int[] pixels = new int[2];

    void setUp() {

        //set up game
        File resourcesDirectory = new File("resources/Ex4_OOP_example5.csv");
        String path = resourcesDirectory.getAbsolutePath();
        Game game = new Game(path);
        //set up player
        Player player = new Player(35.21070936386768, 32.103782167042894, 20);
        game.setPlayer(player);
        //set up the convertor
        Convertors convertor = new Convertors(800,500,35.20238, 35.21236, 32.10190, 32.10569);
        //start
        builder = new GraphBuilder(game, convertor);
        los = new LOS(game,convertor);


       /* Block block1 = new Block("1,2,10,0,4,5,0,0");
        Point3D player = new Point3D(1,7,0);
        Point3D traget = new Point3D(10,7,0);*/

    }

    @Test
    void LOS() {

        pixels = game.getPlayer().getPixels();
        Point3D playerPixels = new Point3D(pixels[0],pixels[1]);
        Point3D seenPoint = new Point3D(game.getBlocks().get(1).getPoints()[1]);
        Point3D notSeenPoint = new Point3D(game.getBlocks().get(0).getPoints()[3]);

        boolean seenTarget = los.LOS(playerPixels,seenPoint);
        boolean notSeenTarget = los.LOS(playerPixels,notSeenPoint);
        boolean expected = true;
        boolean notExpected = false;
        assertEquals(true, seenTarget);
        assertEquals(false, notSeenTarget);


    }

}