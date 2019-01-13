package GUI;

import Accessories.*;
import Creatures.Block;
import Creatures.Fruit;
import Creatures.Pacman;
import Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

/**
 * this class represents the Board of the game(view)
 */

public class Board extends JPanel implements MouseListener {
    private  boolean addPlayer;
    private boolean runStepByStep;
    private boolean autoRun;
    private boolean runAlgo;
    private  Point3D clickStep;
    private Game game;
    private Map map;
    private BufferedImage mapImage, cherry, pacman, ghost, player;
    private Convertors convertor;
    private static int x;
    private static int y;

    // Observable
    private Observable nextStep = new nextStep();

    public Board(Game game, Map map){
        this.game = game;
        this.map = map;
        this.addMouseListener(this);
        initGUI();
    }

    /**
     * init the board characters
     */
    private void initGUI(){
        try {
            mapImage = map.getMyMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cherry = ImageIO.read(new File("Resources\\cherry.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pacman = ImageIO.read(new File("Resources\\badpacman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ghost = ImageIO.read(new File("Resources\\ghost.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player = ImageIO.read(new File("Resources\\pacman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param g paint function of the gui
     */
    public void paint(Graphics g){

        int width = this.getWidth();
        int height = this.getHeight();
        g.drawImage(mapImage, 0, 0,width,height, this);

        convertor = new Convertors(height, width, 35.20238, 35.21236, 32.10190, 32.10569);

        try {
            for (Pacman pac : game.getPacmans()) {

                int[] pixels = convertor.gps2Pixels(pac.getPoint());
                g.drawImage(pacman, pixels[0], pixels[1], null);
            }
            if (!game.getFruits().isEmpty()) {
                for (Fruit f : game.getFruits()) {

                    int[] pixels = convertor.gps2Pixels(f.getPoint());
                    g.drawImage(cherry, pixels[0], pixels[1], null);
                }
            }

            for (Pacman gh : game.getGhosts()) {
                int[] pixels = convertor.gps2Pixels(gh.getPoint());
                g.drawImage(ghost, pixels[0], pixels[1], null);
            }

            for (Block block : game.getBlocks()) {
                int[] pixels = convertor.gps2Pixels(block.getTopLeft());
                int[] pixelsHeight = convertor.gps2Pixels(block.getBottomLeft());
                int[] pixelsWidth = convertor.gps2Pixels(block.getTopRight());

                int widthDis = pixelsWidth[0] - pixels[0];
                int heigtDis = pixelsHeight[1] - pixels[1];

                g.fillRect(pixels[0], pixels[1], widthDis, heigtDis);
            }

            if (player != null) {
                int[] pixels = convertor.gps2Pixels(game.getPlayer().getPoint());
                g.drawImage(player, pixels[0], pixels[1], null);
            }
        }

        catch (Exception e){

        }
    }

    /**
     *
     * @param e when clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        System.out.println(x +", "+ y);


        // if add player ->
        if (addPlayer) {
            Point3D playerPoint = convertor.pixel2Gps(x,y);
            game.getPlayer().setPoint(playerPoint);

            clickStep = new Point3D(playerPoint);
            addPlayer = false;

            System.out.println(playerPoint.get_x() +", " + playerPoint.get_y());


            repaint();
        }

        // On every mouse click, change player location/point
        if (runStepByStep){

            Point3D newPoint = convertor.pixel2Gps(x,y);
            clickStep = new Point3D(newPoint);

            // Updates observers -> Controller
            ((nextStep) nextStep).setPoint(clickStep, game.getPlayer().getPoint());

        }

        //// On every mouse click, change player location/point

        if (autoRun){

            Point3D newPoint = convertor.pixel2Gps(x,y);
            clickStep = new Point3D(newPoint);

            // Updates observers -> Controller
            ((nextStep) nextStep).setPoint(clickStep , game.getPlayer().getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }




    //////////////////////getters and setters//////////////////

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public boolean isAddPlayer() { return addPlayer;}
    public void setAddPlayer(boolean addPlayer) { this.addPlayer = addPlayer;}
    public boolean isRunStepByStep() { return runStepByStep; }
    public void setRunStepByStep(boolean runStepByStep) { this.runStepByStep = runStepByStep; }
    public  Point3D getClickStep() { return clickStep; }
    public  void setClickStep(Point3D clickStep) { this.clickStep = clickStep; }
    public boolean isAutoRun() { return autoRun; }
    public void setAutoRun(boolean autoRun) { this.autoRun = autoRun; }
    public Convertors getConvertor() { return convertor; }
    public boolean isRunAlgo() { return runAlgo; }
    public void setRunAlgo(boolean runAlgo) { this.runAlgo = runAlgo; }
    public Observable getNextStep() {
        return nextStep;
    }
}
