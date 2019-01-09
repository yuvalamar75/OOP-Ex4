package Accessories;

import Accessories.Convertors;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class holds the map of the game.
 * this object load the img from the user computer and holds
 * all of img data such as size, pixels and holds convert object.
 *
 * @author YuvalAmar and DvirHacohen
 *
 */
public class Map {

    private static BufferedImage myMap;
    private static Convertors convertor;
    private static double eps;
    private static double topRightX=35.21236;
    private static double topRightY=32.10569;
    private static double leftButtomX=35.20238;
    private static double leftButtomY=32.10190;


    private int mapWidth;
    private int mapHeight;

    public Map(){
        try {
            myMap = ImageIO.read(new File("Ariel1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        this.mapHeight = myMap.getHeight();
        this.mapWidth = myMap.getWidth();

    }
    /**
     * load image from given path and create converter object.
     * @param path where the img in
     */
    public Map(String path) {
        try {
            myMap = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mapWidth = myMap.getWidth();
        mapHeight = myMap.getHeight();

    }

    public void buildConvertor(){

        convertor = new Convertors(mapHeight ,mapWidth, leftButtomX, topRightX, leftButtomY, topRightY);

    }


    public double getMapWidth() { return mapWidth; }
    public double getMapHeight() { return mapHeight; }
    public Convertors getConverter() {
        return convertor;
    }
    public BufferedImage getMyMap() { return myMap; }
    public void setMapWidth(int mapWidth) { this.mapWidth = mapWidth; }
    public void setMapHeight(int mapHeight) { this.mapHeight = mapHeight; }




}


