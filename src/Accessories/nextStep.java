package Accessories;

import java.util.Observable;

public class nextStep extends Observable {
    private Point3D point;

    public void setPoint(Point3D point) {
        synchronized (this) {
            this.point = point;
        }
        setChanged();
        notifyObservers();
    }

    public synchronized Point3D getPoint() {
        return point;
    }
}
