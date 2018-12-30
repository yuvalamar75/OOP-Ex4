package Accessories;

import java.util.Observable;

public class nextStep extends Observable {
    private Point3D point, curr;
    private double[] azimut;
    private MyCoords coords = new MyCoords();

    public void setPoint(Point3D point) {
        synchronized (this) {
            this.point = point;
        }
    }
        public void setPoint(Point3D point, Point3D curr) {
            synchronized (this) {
                this.point = point;
                azimut = coords.azimuth_elevation_dist(curr,point);
            }

        setChanged();
        notifyObservers();
    }

    public synchronized Point3D getPoint() {
        return point;
    }

        public synchronized double getazimut() {
            return azimut[0];
        }

}
