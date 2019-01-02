package Creatures;

import Accessories.Point3D;

public class Block {

	private String type;
	private int ID;
	private double minLat;
	private double minLon;
	private double maxLat;
	private double maxLon;
	private double weight;
	private Point3D min;
	private Point3D max;

	public Block(String line) {
		String[] data = line.split(",");
		type = "B";
		ID = Integer.parseInt(data[1]);
		weight = Double.parseDouble(data[8]);
		minLat = Math.min(Double.parseDouble(data[2]),Double.parseDouble(data[5]));
		minLon = Math.min(Double.parseDouble(data[3]),Double.parseDouble(data[6]));
		maxLat = Math.max(Double.parseDouble(data[2]),Double.parseDouble(data[5]));
		maxLon = Math.max(Double.parseDouble(data[3]),Double.parseDouble(data[6]));

		min = new Point3D(minLon, minLat);
		max = new Point3D(maxLon,maxLat );

	}

	//Copy constructor.

	public Block(Block b) {
		this.ID = b.ID;
		this.minLat = b.minLat;
		this.minLon = b.minLon;
		this.maxLat = b.maxLat;
		this.maxLon = b.maxLon;
		this.weight = b.weight;
		this.min = b.min;
		this.max = b.max;
	}

	public String toString() {
		String st = "";
		st += "\n" + type + ", " + ID + ", " + ", " + ", " + min.x() + ", " + min.y() + ", " + max.x() + ", " + max.y();
		return st;

	}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public double getMinLat() {return minLat;}
	public void setMinLat(double minLat) {this.minLat = minLat;}
	public double getMinLon() {return minLon;}
	public void setMinLon(double minLon) {this.minLon = minLon;}
	public double getMaxLat() {return maxLat;}
	public void setMaxLat(double maxLat) {this.maxLat = maxLat;}
	public double getMaxLon() {return maxLon;}
	public void setMaxLon(double maxLon) {this.maxLon = maxLon;}
	public double getWeight() {return weight;}
	public void setWeight(int weight) {this.weight = weight;}
	public Point3D getMin() {return min;}
	public void setMin(Point3D min) {this.min = min;}
	public Point3D getMax() {return max;}
	public void setMax(Point3D max) {this.max = max;}


}
