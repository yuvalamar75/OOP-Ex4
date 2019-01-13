package Tests;

import Accessories.*;
import Creatures.Player;
import Game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LOSTest {
    private LOS los;
    private Game game;
    private Convertors convertor;
    @BeforeEach
    void setUp() {

        //set up game
        File resourcesDirectory = new File("resources/Ex4_OOP_example5.csv");
        String path = resourcesDirectory.getAbsolutePath();
        game = new Game(path);

        //set up the convertor
        convertor = new Convertors(443,786,35.20238, 35.21236, 32.10190, 32.10569);
        //set up player
        Player player = new Player(35.21070936386768, 32.103782167042894, 20);
        player.setPixels(convertor.gps2Pixels(player.getPoint()));
        game.setPlayer(player);

        //start
        GraphBuilder build =  new GraphBuilder(game, convertor);
        los  = build.getLos();
    }

    @Test
    void LOS() {
        Point3D playerPixels = new Point3D(game.getPlayer().getPixels()[0], game.getPlayer().getPixels()[1]);
        Point3D seenPoint = new Point3D(game.getBlocks().get(1).getPoints()[2]);
        Point3D notSeenPoint = new Point3D(game.getBlocks().get(0).getPoints()[3]);

        boolean seenTarget = los.isIntersects(playerPixels , seenPoint);
        boolean notSeenTarget = los.isIntersects(playerPixels , notSeenPoint);

        assertEquals(false, seenTarget);
        assertEquals(true, notSeenTarget);
    }

}