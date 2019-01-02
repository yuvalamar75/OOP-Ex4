package Accessories;

import Creatures.Block;
import Creatures.Fruit;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LinesContainter {

    private Game game;
    private Map map;
    private ArrayList<Line2D> bloclLines;
    private ArrayList<Line2D> fruitLines;
    private Line2D playerLine;
    private MyCoords coords = new MyCoords();

    public LinesContainter(Game game, Map map){
        this.game = game;
        this.map = map;
    }


    public Fruit getClosestFruit() {
        double minDis = Integer.MAX_VALUE;
        Fruit closetFruit = null;
        for (Fruit fruit : game.getFruits()) {
            double dis = coords.distance3d(game.getPlayer().getPoint(), fruit.getPoint());
            if (dis < minDis) {
                minDis = dis;
                closetFruit = fruit;
            }
        }
        return closetFruit;
    }

    public int orientation(Point3D p, Point3D q, Point3D r) {

        double val = (q.get_y() - p.get_y()) * (r.get_x() - q.get_x())
                - (q.get_x() - p.get_x()) * (r.get_y() - q.get_y());

        if (val == 0.0)
            return 0; // c	olinear

        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }


    public boolean intersect(Point3D p1, Point3D q1, Point3D p2, Point3D q2) {

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;
        return false;
    }

    public static void main(String[] args) {
        Point3D p1 = new Point3D(4,6);
        Point3D q1 = new Point3D(6,10);
        Point3D p2 = new Point3D(2,3);
        Point3D q2 = new Point3D(2,7);
        //System.out.println("intersect: "+ LinesContainter.intersect(p1, q1, p2, q2));
    }
}
