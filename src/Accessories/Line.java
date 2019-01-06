package Accessories;
import java.awt.geom.Line2D;

public class Line {
private Point3D p1;
private Point3D p2;
private double m;
private double n;
private Range rangeX;
private Range rangeY;


public Line(Point3D p1 , Point3D p2){
    this.p1 = p1;
    this.p2 = p2;
    double [] line = setLine(p1, p2);
    m = line[0];
    n = line[1];
    rangeX = new Range(Math.max(p1.get_x(), p2.get_x()), Math.min(p1.get_x(), p2.get_x()));
    rangeY = new Range(Math.max(p1.get_y(), p2.get_y()), Math.min(p1.get_y(), p2.get_y()));
}

private double[] setLine(Point3D p1, Point3D p2){
    double[] line = new double[2];
    line[0] = ((p2.get_y()-p1.get_y())/(p2.get_x()-p1.get_x()));
    line[1] = ((p2.get_x()*p1.get_y()-p1.get_x()*p2.get_y())/(p2.get_x()-p1.get_x()));

    return line;
}

public double getValueOfY(double x){
    return (getM()*x) + getN();
}

public boolean isInLimit(Point3D point){
    double y = getValueOfY(point.get_x());
    Point3D newPoint  = new Point3D(point.get_x(), y);
    boolean X = rangeX.isIn(newPoint.get_x());
    boolean Y = rangeY.isIn(newPoint.get_y());
    if (X && Y){
        return true;
    }
    return false;
}



    public Point3D getP1() {return p1; }
    public void setP1(Point3D p1) { this.p1 = p1; }
    public Point3D getP2() { return p2; }
    public void setP2(Point3D p2) { this.p2 = p2; }
    public double getM() { return m; }
    public void setM(double m) { this.m = m; }
    public double getN() { return n; }
    public void setN(double n) { this.n = n; }

}
