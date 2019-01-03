package Accessories;

import Creatures.Block;
import Creatures.Fruit;
import Creatures.Player;
import GUI.Board;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GraphBuilder {

    private Board board;
    private ArrayList<Line2D> bloclLines;
    private ArrayList<Line2D> fruitLines;
    private Line2D playerLine;
    private MyCoords coords = new MyCoords();
    private ArrayList<GraphNode> vertices;

    public GraphBuilder(Board board){
        this.board = board;
        changePlayerPixels();
        changeVertices();
    }

    public void changePlayerPixels(){
        //change the player point2pixels
        board.getGame().getPlayer().setPixels(board.getConvertor().gps2Pixels(board.getGame().getPlayer().getPoint()));
        int[] playerPixels = board.getGame().getPlayer().getPixels();
        Point3D point = new Point3D(playerPixels[0], playerPixels[1]);
        GraphNode playerNode = new GraphNode(point);
        vertices.add(playerNode);
    }

    public void changeVertices() {
        //change the fruits points gps2pixels.

        for (Fruit fruit : board.getGame().getFruits()) {
            fruit.setPixels(board.getConvertor().gps2Pixels(fruit.getPoint()));
        }


        //change the blocks points gps2pixels.
        for (Block block : board.getGame().getBlocks()) {
            Point3D[] points = block.getPoints();
            for (int i = 0; i < points.length; i++) {
                //change the point to pixels
                int[] pixels = board.getConvertor().gps2Pixels(points[i]);
                points[i].set_x(pixels[0]);
                points[i].set_x(pixels[1]);

            }
            //add 2 pixels for each pixels

            points[0].set_x(points[0].get_x() - 2);
            points[0].set_y(points[0].get_y() - 2);

            points[1].set_x(points[0].get_x() + 2);
            points[1].set_y(points[0].get_y() - 2);

            points[2].set_x(points[0].get_x() + 2);
            points[2].set_y(points[0].get_y() + 2);

            points[3].set_x(points[0].get_x() - 2);
            points[3].set_y(points[0].get_y() + 2);

            for (int i = 0; i < points.length; i++) {
                GraphNode blockNode = new GraphNode(points[i]);
                vertices.add(blockNode);
            }
        }
    }
    public Fruit getClosestFruit() {
        double minDis = Integer.MAX_VALUE;
        Fruit closetFruit = null;
        for (Fruit fruit : board.getGame().getFruits()) {
            
            double dis = coords.distance3d(board.getGame().getPlayer().getPoint(), fruit.getPoint());
            if (dis < minDis) {
                minDis = dis;
                closetFruit = fruit;
            }
        }
        
        Point3D closestFruitPoint = new Point3D(closetFruit.getPixels()[0], closetFruit.getPixels()[1]);
        GraphNode fruitNode = new GraphNode(closestFruitPoint);
        return closetFruit;
        
    }

    public int orientation(Point3D p, Point3D q, Point3D r) {

        double val = (q.get_y() - p.get_y()) * (r.get_x() - q.get_x())
                - (q.get_x() - p.get_x()) * (r.get_y() - q.get_y());

        if (val == 0.0)
            return 0; // c	olinear

        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }
    //convert the vertices of the block to pixels and move them


    public boolean intersect(Point3D p1, Point3D q1, Point3D p2, Point3D q2) {

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;
        return false;
    }

}
