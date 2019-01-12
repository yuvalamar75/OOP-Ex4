package Tests;

import Accessories.*;
import Creatures.Player;
import Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LOSTest {
    static LOS los;
    static Game game;
    //static GraphBuilder builder;
    static Convertors convertor;
    static  Player player;
    static int[] pixels = new int[2];

    @BeforeEach
    void setUp() {

        //set up game
        File resourcesDirectory = new File("resources/Ex4_OOP_example5.csv");
        String path = resourcesDirectory.getAbsolutePath();
        game = new Game(path);
        System.out.println(game.getFruits().isEmpty());
        //set up player
        player = new Player(35.21070936386768, 32.103782167042894, 20);
        game.setPlayer(player);
        //set up the convertor
        convertor = new Convertors(800,500,35.20238, 35.21236, 32.10190, 32.10569);
        //start
        //builder = new GraphBuilder(game, convertor);
        PixelConvertor.changePixels(game, convertor);
        los = new LOS(game,convertor);
    }

    @Test
    void LOS() {

        pixels = game.getPlayer().getPixels();
        Point3D playerPixels = new Point3D(pixels[0],pixels[1]);
        System.out.println("player : "+ playerPixels.get_x() + " , " + playerPixels.get_y());

        Point3D seenPoint = new Point3D(game.getBlocks().get(1).getPoints()[0]);

        //System.out.println(seenPoint.get_x()+", "+ seenPoint.get_y());
        Point3D notSeenPoint = new Point3D(game.getBlocks().get(0).getPoints()[3]);
        //system.out.println(notSeenPoint.get_x()+", "+ seenPoint.get_y());

        for (int i = 0 ; i<game.getBlocks().size() ; i++){
            for (int j = 0 ; j<game.getBlocks().get(i).getPoints().length ; j++ ){
                System.out.print(game.getBlocks().get(i).getPoints()[j].get_x()+" , " + game.getBlocks().get(i).getPoints()[j].get_y() +" \n");
            }

            System.out.println();
        }

        boolean seenTarget = los.isIntersects(playerPixels,seenPoint);
        boolean notSeenTarget = los.isIntersects(playerPixels,notSeenPoint);
        assertEquals(false, seenTarget);
        assertEquals(true, notSeenTarget);
    }

}