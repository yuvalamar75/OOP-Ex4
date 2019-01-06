package Accessories;

import Creatures.Block;
import GUI.Board;

public class LOS {
    private Board board;
    private int height;
    private int width;
    public LOS(Board board){
        this.board = board;
    }

    //this function return array with the width and the height of the block
    /*public int [] widthHeight(){
        for (Block block : board.getGame().getBlocks()) {

            int[] pixels = board.getConvertor().gps2Pixels(block.getTopLeft());

            int[] pixelsHeight = board.getConvertor().gps2Pixels(block.getBottomLeft());
            int[] pixelsWidth = board.getConvertor().gps2Pixels(block.getTopRight());

            width = pixelsWidth[0] - pixels[0];
            height = pixelsHeight[1] - pixels[1];
        }
    }*/

   /* public boolean LOS(Point3D source, Point3D target){


    }*/
}
