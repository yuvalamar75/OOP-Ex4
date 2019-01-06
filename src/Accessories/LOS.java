package Accessories;

import Creatures.Block;
import GUI.Board;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LOS {
    private ArrayList<Rectangle> rectangles;
    private Board board;

    public LOS(Board board){
        this.board = board;
        rectangles = new ArrayList<>();
        buildRectangles();

    }

    //this function builds array with all rectangles
    private void buildRectangles(){
        for (Block block : board.getGame().getBlocks()) {

            int[] pixels = board.getConvertor().gps2Pixels(block.getTopLeft());
            int[] pixelsHeight = board.getConvertor().gps2Pixels(block.getBottomLeft());
            int[] pixelsWidth = board.getConvertor().gps2Pixels(block.getTopRight());

            int width = pixelsWidth[0] - pixels[0];
            int height = pixelsHeight[1] - pixels[1];
            Rectangle rec = new Rectangle(pixels[0], pixels[1], width, height);
            rectangles.add(rec);
        }
    }


    public boolean LOS(Point3D source, Point3D target){
       // System.out.println("in LOS");
        Point2D sourceP = new Point((int)source.get_x(),(int) source.get_y());
        Point2D targetP = new Point((int)target.get_x(),(int) target.get_y());

        Line2D line = new Line2D.Double(sourceP, targetP);

        for (Rectangle rectangle : rectangles){
            //boolean intersects = false;
            if (line.intersects(rectangle)){
               // System.out.println("true");
                return true;

            }
        }
        //System.out.println(false);
        return false;

    }







}
