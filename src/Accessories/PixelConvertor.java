package Accessories;

import Creatures.Block;
import Creatures.Fruit;
import Game.Game;

/**
 * This class gets all the points of the creatures in gps cordinate and change them to pixel point
 * this function use the class Convertors to get the exact locatin of the point.
 */
public class PixelConvertor {

    public static void changePixels(Game game, Convertors convertor){
        for (Fruit fruit : game.getFruits()) {
            fruit.setPixels(convertor.gps2Pixels(fruit.getPoint()));
        }

        //change the blocks points gps2pixels.
        for (Block block : game.getBlocks()) {
            Point3D[] points = block.getPoints();
            for (int i = 0; i < points.length; i++) {
                //change the point to pixels


                int[] pixels = convertor.gps2Pixels(points[i]);
                points[i].set_x(pixels[0]);
                points[i].set_y(pixels[1]);
            }

            //add 2 pixels for each pixels will not enter here

            points[0].set_x(points[0].get_x() - 3);
            points[0].set_y(points[0].get_y() - 3);

            points[1].set_x(points[1].get_x() + 3);
            points[1].set_y(points[1].get_y() - 3);

            points[2].set_x(points[2].get_x() + 3);
            points[2].set_y(points[2].get_y() + 3);

            points[3].set_x(points[3].get_x() - 3);
            points[3].set_y(points[3].get_y() + 3);

        }
    }
}
