package Accessories;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphNode {
    private Point3D point;
    private static int ID = 0;
    private ArrayList<GraphNode> neigbours;
    private boolean isSeen = false;

    public GraphNode(Point3D point){
        this.point = point;
        ID = (ID++);
        neigbours = new ArrayList<>();
    }


    public Point3D getPoint() { return point; }
    public void setPoint(Point3D point) { this.point = point; }
    public static int getID() { return ID; }
    public static void setID(int ID) { GraphNode.ID = ID; }
    public ArrayList<GraphNode> getNeigbours() { return neigbours; }
    public void setNeigbours(ArrayList<GraphNode> neigbours) { this.neigbours = neigbours; }
    public boolean isSeen() { return isSeen; }
    public void setSeen(boolean seen) { isSeen = seen; }

}
