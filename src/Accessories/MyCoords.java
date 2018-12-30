package Accessories;

/**
 * This class implement coords_converter interface
 * The class has functions that Performs operations on GPS coordinates.
 *	Like adding,distanced,vectorin3D etc.
 *
 * 
 * @author YuVaLAndDvIr
 *
 */


public class MyCoords implements coords_converter{
	final double EARTH_RADIUS=6371000;
	/* computes a new point which is the gps point transformed by a 3D vector (in meters)/
	 * 
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		if(isValid_GPS_Point(gps)==false) return null;//test if the point is valid gps point
		double Lon_Norm=Math.cos(gps.get_x()*Math.PI/180);
		double x=Math.asin(local_vector_in_meter.x()/EARTH_RADIUS)*(180/Math.PI)+gps.x();
		double y=Math.asin(local_vector_in_meter.y()/(EARTH_RADIUS*Lon_Norm))*(180/Math.PI)+gps.y();
		double z=gps.z()+local_vector_in_meter.z();
		Point3D gpsn=new Point3D(x,y,z);
		if(gpsn.x()>180 || gpsn.x()<-180) {
			gpsn.set_x(((gpsn.x()+180)%360)-180);
		}
		
		if(isValid_GPS_Point(gpsn)==false) return null;//test if the point is valid gps point

		
		
		return gpsn;


	}
	/** 
	 * computes the 3D distance (in meters) between the two gps like points 
	 * @param gps0
	 * @param gps1
	 * @return
	 */
	public double distance3d(Point3D gps0, Point3D gps1) {
		if(isValid_GPS_Point(gps0)==false) return (Double) Double.MIN_VALUE;//test if the point is valid gps point
		if(isValid_GPS_Point(gps1)==false) return (Double) Double.MIN_VALUE;//test if the point is valid gps point

		double lonnorm=Math.cos(gps0.x()*(Math.PI/180));
		double diflongtuitde;
		double diflatitude;
		double difaltitude;

		double dif_radlatitude;
		double dif_radlongtuitde;
		double altmeter;
		double lonmeter;
		diflatitude = gps1.x()-gps0.x();
		diflongtuitde = gps1.y()-gps0.y();
		difaltitude = gps1.z()-gps0.z();
		dif_radlatitude = diflatitude*Math.PI/180;
		dif_radlongtuitde = diflongtuitde*Math.PI/180;
		altmeter = Math.sin(dif_radlatitude)*EARTH_RADIUS;
		lonmeter = Math.sin(dif_radlongtuitde)*EARTH_RADIUS*lonnorm;

		double distance= Math.sqrt(Math.pow(altmeter, 2)+Math.pow(lonmeter, 2)+Math.pow(difaltitude, 2));
		return distance;

	}
	/** 
	 * computes the 3D vector (in meters) between two gps like points 
	 * */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		if(isValid_GPS_Point(gps0)==false) return null;//test if the point is valid gps point
		if(isValid_GPS_Point(gps1)==false) return null;//test if the point is valid gps point

		double lonNorm=Math.cos(gps0.x()*Math.PI/180);
		double diflatitude =gps1.x()-gps0.x();
		double diflongtuitde=gps1.y()-gps0.y();
		double diff_z=gps1.z()-gps0.z();
		double dif_radlatitude=diflatitude*Math.PI/180;
		double dif_radlongtuitde=diflongtuitde*Math.PI/180;
		double toMeterlatitude=Math.sin(dif_radlatitude)*EARTH_RADIUS;
		double toMeterlongtuitde=Math.sin(dif_radlongtuitde)*lonNorm*EARTH_RADIUS;
		return new Point3D(toMeterlatitude, toMeterlongtuitde, diff_z);


	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double [] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double azimut = 0;
		if(!(isValid_GPS_Point(gps0))||!(isValid_GPS_Point(gps1))) {
			return null;
		}
		Point3D vec =vector3D(gps0, gps1);
		if(vec.get_x()==0 && vec.get_y()==0 && vec.get_z()==0) azimut = 0;
		else azimut = Math.toDegrees(Math.atan(Math.abs(vec.x()/vec.y())));
		if (vec.y()<0) {
			if (vec.x()>0) {
				azimut=180-azimut;
			}else
				azimut=180+azimut;
		}else {
			if(vec.x()<0) {
				azimut=360-azimut;
			}
		}
		//distance//
		double distance = distance3d(gps0,gps1);
		//elevation//
		double high = gps1.z() - gps0.z();
		double elevation = Math.toDegrees(Math.asin(high/distance));
		double arr[] = {azimut,elevation,distance};
		return arr;
	}


	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return boolen true/false.
	 */

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if ((p.x()<=180 && p.x()>=-180) && (p.y()>=-90 && p.y()<=90) && (p.z()>=-450 )) return true;
		return false;
	}
}








