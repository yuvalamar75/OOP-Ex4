package Accessories;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphNode {
    private Point3D pointPixels;

    private static int CountereID = 0;
    private int ID;
    private ArrayList<GraphNode> neigbours;
    private boolean isSeen = false;

    public GraphNode(Point3D point){
        this.pointPixels = point;
        this.ID = CountereID++;
        neigbours = new ArrayList<>();
    }

    public static void resetCounterId(){
        CountereID = 0;
    }

    public static void minus1(){
        CountereID --;
    }
    public Point3D getPoint() { return pointPixels; }
    public void setPoint(Point3D point) { this.pointPixels = point; }
    public  int getID() { return ID; }
    public  void setID(int ID) { this.ID = ID; }
    public ArrayList<GraphNode> getNeigbours() { return neigbours; }
    public void setNeigbours(ArrayList<GraphNode> neigbours) { this.neigbours = neigbours; }
    public boolean isSeen() { return isSeen; }
    public void setSeen(boolean seen) { isSeen = seen; }


}
