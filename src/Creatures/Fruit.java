package Creatures;

import Geom.Point3D;

public class Fruit {
	
	private Point3D point;
	private int ID;
	private int radius;
	private int weight;
	private String type;
	
	public Fruit(String line) {
		
		String[] data = line.split(",");
		type = "F";
		ID = Integer.parseInt(data[1]);
		point = new Point3D(Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]));
		weight	 = Integer.parseInt(data[5]);
		
	}
	
	//Copy constructor.
	
	public Fruit(Fruit f) {
		this.point = f.point;
		this.ID = f.ID;
		this.radius = f.radius;
		this.weight = f.weight;
		this.type = f.type;
		
	}
	
	public String toString() {
		String st = "";
		st += "\n"+ type+", " + ID + ", " + ", "+", " + point.x() + ", "+ point.y()+" 0" + ", "+ weight;
		return st;
		
	}

	public Point3D getPoint() {return point;}
	public void setPoint(Point3D point) {this.point = point;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public int getRadius(){return radius;}
	public void setRadius(int radius) {	this.radius = radius;}
	public int getWeight(){return weight;}
	public void getWeight(int weight) {	this.weight = weight;}
	public String getType(){return type;}
	public void setType(String type) {this.type = type;}

}
