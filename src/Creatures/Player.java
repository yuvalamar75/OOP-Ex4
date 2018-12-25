package Creatures;

import Geom.Point3D;

public class Player {
	
	private Point3D point;
	private int ID;
	private int radius;
	private int speed;
	private String type;
	private double score;
	
	public Player( double lat, double lon, int Speed ) {
		
		point = new Point3D(lat, lon, 0);
		speed = Speed;
		radius = 1;
		type = "M";
		score = 0;
		
	}
	

	public Player( String line ) {
		
		String[] data = line.split(",");
		type = data[0];
		ID = Integer.parseInt(data[1]);
		point = new Point3D(Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]));
		speed = Integer.parseInt(data[5]);
		radius = Integer.parseInt(data[6]);
		score = 0;
	}
	
	//Copy constructor.
	
	public Player ( Player p ) {
		
		this.point = p.point;
		this.ID = p.ID;
		this.radius = p.radius;
		this.speed = p.speed;
		this.score = p.score;
		this.type = p.type;
		
	}


	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public int getSpeed() {return speed;}
	public void setSpeed(int speed) {this.speed = speed;}
	public int getRadius() {return radius;}
	public void setRadius(int radius) {this.radius = radius;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public Point3D getPoint() {return point;}
	public void setPoint(Point3D point) {this.point = point;}
	public double getScore() {return score;}
	public void setScore(double score) {this.score = score;}
}
