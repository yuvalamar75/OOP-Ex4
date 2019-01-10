package Accessories;

import Creatures.Block;
import Game.Game;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * this class represent lineOfSight  to check if 2 vertices in game can see each other
 */
public class LOS {

    private ArrayList<Rectangle2D> rectangles;
    private Game game;
    private Convertors convertor;

    /**
     *
     * @param game to build the rectangles
     * @param convertor to convert and build the rectangles
     */
    public LOS(Game game , Convertors convertor){
        this.game = game;
        this.convertor = convertor;
        rectangles = new ArrayList<>();
        buildRectangles();

    }

    //this function builds array with all rectangles
    private void buildRectangles(){
        for (Block block : game.getBlocks()) {
            int[] pixels = convertor.gps2Pixels(block.getTopLeft());
            int[] pixelsHeight = convertor.gps2Pixels(block.getBottomLeft());
            int[] pixelsWidth = convertor.gps2Pixels(block.getTopRight());
            int width = Math.abs(pixelsWidth[0] - pixels[0]);
            int height = Math.abs(pixelsHeight[1] - pixels[1]);
            Rectangle2D rec = new Rectangle(pixels[0], pixels[1], width, height);
            rectangles.add(rec);
        }
    }

    /**
     *
     * @param source vertice
     * @param target vertice
     * @return false iff they see each other
     */
    public boolean LOS(Point3D source, Point3D target){

        Point2D sourceP = new Point((int)source.get_x(),(int) source.get_y());
        Point2D targetP = new Point((int)target.get_x(),(int) target.get_y());
        Line2D line = new Line2D.Double(sourceP, targetP);

        for (Rectangle2D rectangle : rectangles){
            if (line.intersects(rectangle)){
                return true;
            }
        }
        return false;
    }

}
