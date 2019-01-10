package Accessories;

import java.util.Observable;

/**
 * this class represent the Observable object of the program.
 * the controller needs to know when another clicked is being made and get the Pixels of the click and azimut to get there
 * this observer notify the controller about new point and azimut.
 */
public class nextStep extends Observable {
    private Point3D point, curr;
    private double[] azimut;
    private MyCoords coords = new MyCoords();

    /**
     * @param  curr the current  point
     * @param point when mouse clicked  set the point of the observer to be that point and notify the controller with new point and azimut
     */
    public void setPoint(Point3D point, Point3D curr) {
            synchronized (this) {
                this.point = point;
                azimut = coords.azimuth_elevation_dist(curr,point);
            }

        setChanged();
        notifyObservers();
}
    ////////////////////getters and setters////////////////////

    public synchronized Point3D getPoint() { return point; }
    public synchronized double getazimut() { return azimut[0]; }

}
