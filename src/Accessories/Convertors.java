package Accessories;



import java.awt.image.BufferedImage;

//import com.sun.prism.Image;


/**
 * this class calculates converts from pixels to gps and gps to pixels.
 * the constructors must recive the top right GPS coordinate and the bottom left GPS coordinate
 in order to calculate the rations.
 */

public class Convertors {

    private Map map;
    private int height;
    private int width;
    private double TopRightX;
    private double TopRightY;
    private double LeftButtomX;
    private double LeftButtomY;
    private double pixelMaxY;
    private double pixelMinY;
    private Range CoordsRangeX;
    private Range CoordsRangeY;
    private Range ImgRangeX;
    private Range ImgRangeY;
    private BufferedImage img;

    //this constructor takes map and GPS top right and left bottom points
    public Convertors(Map map, double LeftButtomX, double TopRightX, double LeftButtomY, double TopRightY) {

        this.map = map;

        ImgRangeX = new Range(0, map.getMapWidth());
        ImgRangeY = new Range(0, map.getMapHeight());

        CoordsRangeX= new Range(LeftButtomX, TopRightX);
        CoordsRangeY= new Range(LeftButtomY, TopRightY);



        pixelMaxY= map.getMapHeight();
        this.LeftButtomX=LeftButtomX;
        this.TopRightX=TopRightX;
        this.LeftButtomY= LeftButtomY;
        this.TopRightY= TopRightY;
    }
    /**
     *
     * @param height
     * @param width
     * @param LeftButtomX the bottom GPS coordinate latitude
     * @param TopRightX the top GPS coordinate latitude
     * @param LeftButtomY the bottom GPS coordinate longtitude
     * @param TopRightY the top GPS coordinate longtitude
     */
    public Convertors(int height, int width, double LeftButtomX, double TopRightX, double LeftButtomY, double TopRightY) {

        ImgRangeX = new Range(0, width);
        ImgRangeY = new Range(0, height);


        CoordsRangeX = new Range(LeftButtomX, TopRightX);
        CoordsRangeY = new Range(LeftButtomY, TopRightY);

        pixelMaxY = height;

        this.LeftButtomX = LeftButtomX;
        this.TopRightX = TopRightX;
        this.LeftButtomY = LeftButtomY;
        this.TopRightY = TopRightY;

    }

	/*
	 * these 2 functions handle the converts.
	 * the fact that GPS coordinates and pixels are opposite to each other(GPS goes from bottom up to top right
	 	when pixels starts from top left to bottom right)
	 	we need to switch the result like a mirror.
	 */

    /**
     *
     * @param x pixel X
     * @param y pixel Y
     * @return point converted by x and y pixels
     */
    public Point3D pixel2Gps(double x, double y) {

        // getting the relations

        double relX= ImgRangeX.relation(x);
        double relY= ImgRangeY.relation(y);

        // getting the vals

        double lat= CoordsRangeX.getval(relX);
        double lon= CoordsRangeY.getval(relY);

        //switching the result

        if (relY>=0.5) {
            double eps= TopRightY - lon;
            lon = LeftButtomY + eps;
        }



        else {
            double eps= lon - LeftButtomY;

            lon = TopRightY - eps;
        }
        Point3D p = new Point3D(lat, lon,0);
        return p;

    }

    /**
     *
     * @param p Point to be converted
     * @return the pixels of this point in img
     */
    public  int [] gps2Pixels(Point3D p) {
        // getting the relations

        double relX= CoordsRangeX.relation(p.get_x());
        double relY= CoordsRangeY.relation(p.get_y());

        //gettings the vals

        double x= ImgRangeX.getval(relX);
        double y= ImgRangeY.getval(relY);

        //switching the result


        if (relY >= 0.5) {
            double eps= pixelMaxY - y;
            y = pixelMinY + eps;
        }
        else {
            double eps= y - pixelMinY;
            y = pixelMaxY - eps;
        }


        int [] arr = {(int)x, (int)y};
        return arr;



    }

    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}