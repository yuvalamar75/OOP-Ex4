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

	private Point3D bottomLeft;
	private Point3D topRight;
	private Point3D bottomRight;
	private Point3D topLeft;
	private Point3D [] points = new Point3D[4];

	private double height;
	private double width;

	public Block(String line) {
		String[] data = line.split(",");
		type = "B";
		ID = Integer.parseInt(data[1]);
		weight = Double.parseDouble(data[8]);

		minLat = Math.min(Double.parseDouble(data[2]),Double.parseDouble(data[5]));
		minLon = Math.min(Double.parseDouble(data[3]),Double.parseDouble(data[6]));
		maxLat = Math.max(Double.parseDouble(data[2]),Double.parseDouble(data[5]));
		maxLon = Math.max(Double.parseDouble(data[3]),Double.parseDouble(data[6]));

		bottomLeft = new Point3D(minLon, minLat);
		topRight = new Point3D(maxLon,maxLat);
		bottomRight = new Point3D(maxLon, minLat);
		topLeft = new Point3D(minLon, maxLat);

		createVertices();


	}
	//create array with all the corners of the block
	private void createVertices() {
		points[0] = topLeft;
		points[1] = topRight;
		points[2] = bottomRight;
		points[3] = bottomLeft;
	}


	//Copy constructor.

	public Block(Block b) {
		this.ID = b.ID;
		this.minLat = b.minLat;
		this.minLon = b.minLon;
		this.maxLat = b.maxLat;
		this.maxLon = b.maxLon;
		this.weight = b.weight;
		this.bottomLeft = b.bottomLeft;
		this.topRight = b.topRight;
		this.bottomRight = b.bottomRight;
		this.topLeft = b.topLeft;
		this.points = b.points;
	}

	public String toString() {
		String st = "";
		st += "\n" + type + ", " + ID + ", " + ", " + ", " + bottomLeft.x() + ", " + bottomLeft.y() + ", " + topRight.x() + ", " + topRight.y();
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
	public Point3D getBottomLeft() {return bottomLeft;}
	public void setBottomLeft(Point3D bottomLeft) {this.bottomLeft = bottomLeft;}
	public Point3D getTopRight() {return topRight;}
	public void setTopRight(Point3D topRight) {this.topRight = topRight;}
	public Point3D getBottomRight() { return bottomRight; }
	public void setBottomRight(Point3D bottomRight) { this.bottomRight = bottomRight; }
	public Point3D getTopLeft() { return topLeft; }
	public void setTopLeft(Point3D topLeft) { this.topLeft = topLeft; }
	public Point3D[] getPoints() { return points; }
	public void setPoints(Point3D[] points) {
		this.points[0] = points[0];
		this.points[1] = points[1];
		this.points[2] = points[2];
		this.points[3] = points[3];
	}


}
