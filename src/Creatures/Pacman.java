package Creatures;

import Accessories.Point3D;

public class Pacman {
	
	private Point3D point;
	private int ID;
	private double radius;
	private double speed;
	private String type;
	//private int counter = 0 ;
	
	public Pacman(String line) {

		String[] data = line.split(",");
		type = data[0];
		ID = Integer.parseInt(data[1]);
		point = new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),Double.parseDouble(data[4]));
		speed = Double.parseDouble(data[5]);
		radius = Double.parseDouble(data[6]);
		
	}

	public String toString() {
		String st = "";
		st += "\n"+ type+", " + ID + ", " + point.x() + ", "+ point.y()+" 0" + ", "+ radius +", "+ speed;
		return st;

	}
	
	public Pacman(Pacman p) {
		this.point = p.point;
		this.ID = p.ID;
		this.radius = p.radius;
		this.speed = p.speed;
		this.type = p.type;
	}

	


	
	public Point3D getPoint() {return point;}
	public void setPoint(Point3D point) {this.point = point;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public double getRadius() {return radius;}
	public void setRadius(double radius) {this.radius = radius;}
	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}

}
