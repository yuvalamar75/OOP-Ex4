package Accessories;

import Creatures.Fruit;
import GraphObject.Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Target {

    private int ID;
    private static int counterID = 0;
    private Point3D point;
    private ArrayList<String> path;
    private Fruit fruit;
    private double distance;

    public Target(Point3D point,Fruit fruit){
        this.point = point;
        this.ID = counterID++;
        this.fruit = fruit;
        distance = 0;
        path = new ArrayList<>();

    }
    public String toString(){
        String st = "";
        st += "ID is :"+ ID + " ,dis is :" + distance + "the path is :";
        for (int i = 0 ; i<path.size() ; i++){
                st+= path.get(i)+", ";
        }
        st+= " the fruit point is: "+ fruit.getPoint().get_y()+", "+ fruit.getPoint().get_x();
        return st;
    }

    public static void resetCounterId(){
        counterID = 0;
    }
    public Point3D getPoint() { return point; }
    public void setPoint(Point3D point) { this.point = point; }
    public ArrayList<String> getPath() { return path; }
    public void setPath(ArrayList<String> path) { this.path = path; }
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    public Fruit getFruit() { return fruit; }
    public void setFruit(Fruit fruit) { this.fruit = fruit; }
    public static int getCounterID() {
        return counterID;
    }
    public static void setCounterID(int counterID) { Target.counterID = counterID; }

}
