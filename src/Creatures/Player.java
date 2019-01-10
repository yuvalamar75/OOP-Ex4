package Creatures;

import Accessories.Point3D;

/**
 * this class represents Player
 */
public class Player {
	
	private Point3D point;
	private int [] pixels ;
	private int ID;
	private double radius;
	private double speed;
	private String type;
	private double score;

    /**
     * builds Player from given lat, lon, speed
     * @param lat cordinate
     * @param lon  cordinate
     * @param Speed of the player pacman
     */
	public Player( double lat, double lon, int Speed ) {
		
		point = new Point3D(lat, lon, 0);
		speed = Speed;
		radius = 1;
		type = "M";
		score = 0;
		pixels = new int[2];
		
	}

    /**
     *
     * @param line builds Player from a string
     */
    public Player( String line ) {

        String[] data = line.split(",");
        type = data[0];
        ID = Integer.parseInt(data[1]);
        point = new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),Double.parseDouble(data[4]));
        speed = Double.parseDouble(data[5]);
        radius = Double.parseDouble(data[6]);
        score = 0;
        pixels = new int[2];
    }

    /**
     *
     * @return string describing the object
     */
	public String toString(){
		String st = "";
		st += type+", "+ ID+", "+ point.x()+", " + point.y()+", "+ score+", "+ speed +", "+ radius;
		return st;
	}

	/**
	 *
	 * @param p copy constructor
	 */
	
	public Player ( Player p ) {
		
		this.point = p.point;
		this.ID = p.ID;
		this.radius = p.radius;
		this.speed = p.speed;
		this.score = p.score;
		this.type = p.type;
		this.pixels = p.pixels;
		
	}

    //////////////////getters ans setters/////////////////////////

	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public double getSpeed() {return speed;}
	public void setSpeed(double speed) {this.speed = speed;}
	public double getRadius() {return radius;}
	public void setRadius(double radius) {this.radius = radius;}
	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}
	public Point3D getPoint() {return point;}
	public void setPoint(Point3D point) {this.point = point;}
	public double getScore() {return score;}
	public void setScore(double score) {this.score = score;}
	public int[] getPixels() { return pixels; }
	public void setPixels(int[] pixels) { this.pixels[0] = pixels[0];this.pixels[1] = pixels[1]; }
}
