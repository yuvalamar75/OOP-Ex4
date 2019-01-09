package Controller;

import Accessories.*;
import Creatures.Fruit;
import Creatures.Player;
import GUI.Board;
import GUI.myFrame;
import Game.Game;
import Robot.Play;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * our program adapts the "MVC" pattern - Model, View, Controller.
 * this class represents the "Controller". the brain of the MVS pattern.
 * the controller knows all the classes it needs for the program, in particular the board(view) and game(model).
 * there is no "thinking" function in the view and model classes - all made in here.
 */
public class Controller implements Observer {

    private Game game;
    private Map map;
    private myFrame frame;
    private Board board;
    private Play play;
    private ArrayList<String> board_data;
    private PlayerThread playerThread;
    private Point3D nextStep;
    private double azimut;
    private GraphBuilder builder;
    private MyCoords coords = new MyCoords();
    private Target target;
    private boolean firstTimeRun = true, runThread = false, runAlgo = false;

    public Controller() {

        game = new Game();
        map = new Map();
        board = new Board(game, map);
        frame = new myFrame(board);
        board_data = new ArrayList<>();

        // Init next step to player's current point
        nextStep = new Point3D(game.getPlayer().getPoint());
        initListeners();
    }

    /** Update from board on mouse clicked
     * the observer object just changed -> start the update function
      */

    @Override
    public void update(Observable o, Object arg) {
        nextStep = ((nextStep) o).getPoint();
        azimut = ((nextStep) o).getazimut();

        if (firstTimeRun) {
            initServer();
            firstTimeRun = false;

        }

        if (board.isRunStepByStep()) {
            doNextStep();
        }

        if (board.isAutoRun()) {
            if (!runThread) {
                RunThread();
                runThread = true;
            }
        }
    }

    /**
     * init the listeners from the board.
     * dou to MVS pattern the controller control the listeners
     */
    private void initListeners() {

        // If clicked on add player -> set to true
        frame.getAddPlayerButton().addActionListener(e -> { board.setAddPlayer(true); });
        // If clicked on doNextStep step by step -> doNextStep game
        frame.getRunStep().addActionListener(e -> {
            board.setRunStepByStep(true);
            board.setAutoRun(false);
        });
        // if clicked on loadGame -> initGame
        frame.getLoadGame().addActionListener(e -> {
            initGame();

        });
        // if clicked on AutoRun -> set in board AutoRun
        frame.getAutoRun().addActionListener(e -> {
            board.setRunStepByStep(false);
            board.setAutoRun(true);
            createThread();
        });
        // if clicked runAlgo -> set autoAlgorithem function to start the program
        frame.getRunAlgo().addActionListener(e -> {
            board.setRunStepByStep(false);
            board.setAutoRun(false);
            runAlgo = true;
            autoAlgorithm();

        });

        frame.getOpenStatics().addActionListener(e -> {


        });

        // Controller is observing the next step OBSERVABLE object
        observe(board.getNextStep());
    }

    private void autoAlgorithm() {
        initServer();
        createThread();
        RunThread();
    }


    /**
     * init game from existing file
     */
    private void initGame() {
        JFileChooser jfc = new JFileChooser();
        String pathFile = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV files (*csv)", "csv");
        jfc.setFileFilter(filter);
        int ret = jfc.showOpenDialog(this.frame);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            pathFile = file.getAbsolutePath();

        }

        play = new Play(pathFile);
        game.refresh(play.getBoard());
        board.repaint();

    }

    /**
     * if the first time -> init server
     */
    private void initServer() {

        if (firstTimeRun) { // Hence player is not null
            play.setIDs(308522416, 311229488);
            play.setInitLocation(game.getPlayer().getPoint().get_y(), game.getPlayer().getPoint().get_x());
            play.start();
        }

    }

    /**
     * do the next step with "stepByStep" button
     */
    public void doNextStep() {
        System.out.println("> In Run Game");
        play.rotate(azimut);
        board_data = play.getBoard();
        System.out.println(play.getStatistics());
        game.refresh(board_data);
        // repaint
        board.repaint();
    }

    /**
     * run the Thread
     */
    private void RunThread() {
        playerThread.start();
    }

    /**
     *
     * @param fruitPoint
     * @return if the fruit still in the fruits List
     */
    public boolean isIn(Point3D fruitPoint){
        for (Fruit fruit : game.getFruits()){
            if (fruitPoint.get_x() == fruit.getPoint().get_x() && fruitPoint.get_y() == fruit.getPoint().get_y()){
                return true;
            }
        }
        return false;
    }
    /**
     * create from player -> ThreadPlayer
     */

    /**
     * create the Thread
     */
    private void createThread() {
        playerThread = new PlayerThread(game.getPlayer());
    }

    /**
     * this class represent PlayerThread.
     * it can be run by 2 options:
     * 1. autoRun - by clicks
     * 2. by Algrorithem
     */
    class PlayerThread extends Thread {
        private Player player;
        private boolean flag = true;

        /**
         * constructor of the PlayerThread
         * @param player
         */
        public PlayerThread(Player player) {
            this.player = game.getPlayer();

        }

        public void setPlayer(){ this.player = game.getPlayer(); }

        @Override
        /**
         * the run method can be activated or woth clicks or with algorithem
         */
        public void run()
        {
            if (board.isAutoRun()) {

                while (play.isRuning()) {
                    play.rotate(azimut);
                    ArrayList<String> board_data = play.getBoard();
                    board.getGame().refresh(board_data);
                    board.repaint();
                    System.out.println(play.getStatistics());
                    try {
                        sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            else if (runAlgo) {

                boolean getOut;
                boolean reachDes;
                while (play.isRuning() && !game.getFruits().isEmpty()) {
                    getOut = false;
                    reachDes = true;
                    game.refresh(play.getBoard());
                    builder = new GraphBuilder(game, board.getConvertor());
                    target = builder.getClosestTarget();
                    Point3D targetPoint = (target.getFruit().getPoint());
                    ArrayList<String> path = target.getPath();

                        if (path.size() == 1 && isIn(targetPoint) && getOut == false) {
                            System.out.println("stright to fruit");
                            //System.out.println("this dis is : "+ target.getDistance());
                            while (isIn(targetPoint) && play.isRuning() && player.getPoint().distance3DInGps(targetPoint) > 1)
                            {

                                double[] azimut = coords.azimuth_elevation_dist(player.getPoint(), targetPoint);
                                play.rotate(azimut[0]);
                                game.refresh(play.getBoard());
                                System.out.println(play.getStatistics());
                                setPlayer();
                                board.repaint();

                                try {
                                    sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            getOut = true;
                        }

                        //if the path is biiger then 1.

                        if (path.size() > 1 && isIn(targetPoint)) {

                            // this is wehere i get out from break
                            for (int i = 1; i < target.getPath().size() && getOut == false ; i++) {
                                reachDes = false;
                                if ( i == path.size() && getOut == false && isIn(targetPoint)){

                                    System.out.println("to the last fruit");
                                    while (isIn(targetPoint) || play.isRuning() ){
                                        double[] azimut = coords.azimuth_elevation_dist(player.getPoint(), targetPoint);
                                        play.rotate(azimut[0]);
                                        game.refresh(play.getBoard());
                                        System.out.println(play.getStatistics());
                                        setPlayer();
                                        board.repaint();

                                        try {
                                            sleep(10);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    getOut = true;
                                }

                                else {

                                    if (!isIn(targetPoint) || !play.isRuning() || getOut || reachDes){
                                        continue;
                                    }
                                    System.out.println("stil in the path");

                                    int nextIndexInPath = Integer.parseInt(target.getPath().get(i));
                                    Point3D nextPointInPathInPixels = builder.getVertices().get(nextIndexInPath).getPoint();
                                    Point3D nextPointInPathInGPS = board.getConvertor().pixel2Gps(nextPointInPathInPixels.get_x(), nextPointInPathInPixels.get_y());

                                    while (isIn(targetPoint) && !player.getPoint().equals(nextPointInPathInGPS) && reachDes == false) {
                                        if(player.getPoint().distance3DInGps(nextPointInPathInGPS)<2){
                                            reachDes = true;
                                            continue;
                                        }

                                        double[] azimut = coords.azimuth_elevation_dist(player.getPoint(), nextPointInPathInGPS);
                                        play.rotate(azimut[0]);
                                        game.refresh(play.getBoard());
                                        System.out.println(play.getStatistics());
                                        setPlayer();
                                        board.repaint();

                                        try {
                                            sleep(10);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    if (!isIn(targetPoint)) {
                                        getOut = true;
                                        continue;
                                    }
                                    else{
                                        continue;
                                    }
                                }
                                continue;
                            }
                            continue;
                        }
                        continue;
                    }
                }
            }
        }

    ////////////////////getters and setters////////////////////

    // all the getters and setters
    public ArrayList<String> getBoard_data() {
        return board_data;
    }
    public void setBoard_data(ArrayList<String> board_data) {
        this.board_data = board_data;
    }
    public Point3D getNextStep() {
        return nextStep;
    }
    public void setNextStep(Point3D nextStep) {
        this.nextStep = nextStep;
    }
    public double getAzimut() {
        return azimut;
    }
    public void setAzimut(double azimut) {
        this.azimut = azimut;
    }
    public boolean isFirstTimeRun() {
        return firstTimeRun;
    }
    public void setFirstTimeRun(boolean firstTimeRun) {
        this.firstTimeRun = firstTimeRun;
    }
    public boolean isRunThread() {
        return runThread;
    }
    public void setRunThread(boolean runThread) {
        this.runThread = runThread;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public myFrame getFrame() {
        return frame;
    }
    public void setFrame(myFrame frame) {
        this.frame = frame;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public Play getPlay() {
        return play;
    }
    public void setPlay(Play play) {
        this.play = play;
    }
    public PlayerThread getPlayerThread() {
        return playerThread;
    }
    public void setPlayerThread(PlayerThread playerThread) {
        this.playerThread = playerThread;
    }
    private void observe(Observable o) {
        o.addObserver(this);
    }
}