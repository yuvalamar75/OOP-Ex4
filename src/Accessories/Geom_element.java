package Accessories;
/*
 * This interface return distanced3D and distanced2D
 */
public interface Geom_element {/*
	 * The function return the distance in 3d;
	 * @param Point3D to calculate distance to her.
	 */
	public double distance3D(Point3D p) ;
	/*
	 * The function return the distance in 2d;
	 * @param Point3D to calculate distance to her.
	 */
	public double distance2D(Point3D p);
	
}
