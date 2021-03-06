package Tests;

import Accessories.Convertors;
import Game.Game;
import Accessories.GraphBuilder;
import Accessories.Target;
import Creatures.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class GraphBuilderTest {
    static GraphBuilder builder;
    static Game game;
    @BeforeEach
     void setUp() {

        //set up game
        File resourcesDirectory = new File("resources/Ex4_OOP_example5.csv");
        String path = resourcesDirectory.getAbsolutePath();
        game = new Game(path);
        //set up player
        Player player = new Player(35.21070936386768, 32.103782167042894, 20);
        game.setPlayer(player);
        //set up the convertor
        Convertors convertor = new Convertors(800,500,35.20238, 35.21236, 32.10190, 32.10569);
        //start
        builder = new GraphBuilder(game, convertor);
    }

    @Test
    void getClosestTarget() {

        for (int i = 0 ; i<game.getBlocks().size() ; i++){
            for (int j = 0 ; j<game.getBlocks().get(i).getPoints().length ; j++ ){
                System.out.print(game.getBlocks().get(i).getPoints()[j].get_x()+" , " + game.getBlocks().get(i).getPoints()[j].get_y() +" \n");
            }
            System.out.println();
        }

        Target target = builder.getClosestTarget();
        assertEquals(0, Integer.parseInt(target.getPath().get(0)));
        assertEquals(6, Integer.parseInt(target.getPath().get(1)));
        assertEquals(39, target.getFruit().getID());

    }
}