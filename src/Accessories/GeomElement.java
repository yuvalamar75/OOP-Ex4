package Accessories;
/*
 * This class implements the interface return distanced3D and distanced2D
 *
 */


public class GeomElement implements Geom_element{
	Point3D p;
	//String[] s;
	final double EARTH_RADIUS=6371000;;
	/*public GeomElement(String[] s) {
		this.s = s;
	}*/
	 public GeomElement(Point3D p) {
		this.p=p;
	
	}
	/*
	 * The function return the distance in 3d;
	 * @param Point3D to calculate distance to her.
	 */

	@Override
	public double distance3D(Point3D p) {
	
		double dist=this.p.distance3D(p);
		return dist;
		
	}
	/*
	 * The function return the distance in 2d;
	 * @param Point3D to calculate distance to her.
	 */
	@Override
	public double distance2D(Point3D p) {
		double dist=this.p.distance3D(p);
		return dist;
		
	}
	
/*	public double distance2D(Point3D p) {
		double x=Double.parseDouble(s[6]);
		double y=Double.parseDouble(s[7]);
		double z=Double.parseDouble(s[8]);
		Point3D a=new Point3D(x,y,z);
		double dist=a.distance2D(p);
		return dist;
	}
	public double distance2D(Point3D p,int x,int y,int z) {
		double _x=Double.parseDouble(s[x]);
		double _y=Double.parseDouble(s[y]);
		double _z=Double.parseDouble(s[z]);
		Point3D a=new Point3D(_x,_y,_z);
		double dist=a.distance3D(p);
		return dist;
		
	}
*/


}
