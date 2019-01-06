package Creatures;

import Accessories.Point3D;

public class Fruit {
	
	private Point3D point;
	private int ID;
	private double weight;
	private String type;
	private int [] pixels;
	
	public Fruit(String line) {
		String[] data = line.split(",");
		type = "F";
		ID = Integer.parseInt(data[1]);
		point = new Point3D(Double.parseDouble(data[3]) , Double.parseDouble(data[2]) , Double.parseDouble(data[4]));
		weight	 = Double.parseDouble(data[5]);
		pixels = new int [2];
	}
	
	//Copy constructor.
	
	public Fruit(Fruit f) {
		this.point = f.point;
		this.ID = f.ID;
		this.weight = f.weight;
		this.type = f.type;
		pixels = new int [2];
		
	}
	
	public String toString() {
		String st = "";
		st += "\n"+ type+ ", " + ID + ", "+ point.x() + ", "+ point.y()+" 0" + ", "+ weight;
		return st;
		
	}

	public Point3D getPoint() {return point;}
	public void setPoint(Point3D point) {this.point = point;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public double getWeight(){return weight;}
	public void getWeight(double weight) {	this.weight = weight;}
	public String getType(){return type;}
	public void setType(String type) {this.type = type;}
	public int[] getPixels() { return pixels; }
	public void setPixels(int[] pixels) { this.pixels = pixels; }

}
