package Accessories;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphNode {
    private Point3D point;
    private static int CountereID = 0;
    private int ID;
    private ArrayList<GraphNode> neigbours;
    private boolean isSeen = false;

    public GraphNode(Point3D point){
        this.point = point;
        this.ID = CountereID++;

        neigbours = new ArrayList<>();
    }


    public Point3D getPoint() { return point; }
    public void setPoint(Point3D point) { this.point = point; }
    public  int getID() { return ID; }
    public  void setID(int ID) { this.ID = ID; }
    public ArrayList<GraphNode> getNeigbours() { return neigbours; }
    public void setNeigbours(ArrayList<GraphNode> neigbours) { this.neigbours = neigbours; }
    public boolean isSeen() { return isSeen; }
    public void setSeen(boolean seen) { isSeen = seen; }

}
