package Controller;

import Accessories.*;
import Creatures.Block;
import Creatures.Fruit;
import Creatures.Pacman;
import Creatures.Player;
import GUI.Board;
import GUI.myFrame;
import Robot.Play;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Game game;
    private myFrame frame;
    private Map map;
    private Board board;
    private Play play;
    private ArrayList<String> board_data;
    private PlayerThread playerThread;
    private Point3D nextStep;
    private double azimut;
    private boolean firstTimeRun = true,runThread = false;

    public Controller(){

        game = new Game();
        map = new Map();
        board = new Board(game, map);
        frame = new myFrame(board);
        board_data = new ArrayList<>();
        // Init next step to player's current point
        nextStep = new Point3D(game.getPlayer().getPoint());
        initListeners();
    }

    // Update from board on mouse clicked
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
     * do the next step with "stepByStep" button
     */
    public void doNextStep() {
        System.out.println("> In Run Game");
        play.rotate(azimut);
        board_data = play.getBoard();
        System.out.println(play.getStatistics());
        game.refresh(board_data);
        // repaint
        board.update();
    }
    private void initListeners() {

        // If clicked on add player -> set to true
        frame.getAddPlayerButton().addActionListener(e -> {
            board.setAddPlayer(true);
        });

        // If clicked on doNextStep step by step -> doNextStep game
        frame.getRunStep().addActionListener(e -> {
            board.setRunStepByStep(true);
            board.setAutoRun(false);
        });

        // if clicked on loadGame -> initGame
        frame.getLoadGame().addActionListener(e -> {
            initGame();
        });

        frame.getAutoRun().addActionListener(e -> {
            board.setRunStepByStep(false);
            board.setAutoRun(true);
            createThread();
        });


        // Controller is observing the next step OBSERVABLE object
        observe(board.getNextStep());
    }
    /**
     *     init game from existing file
     */
    private void initGame() {
        JFileChooser jfc = new JFileChooser();
        String pathFile = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV files (*csv)", "csv");
        jfc.setFileFilter(filter);
        int ret = jfc.showOpenDialog(this.frame);
        if(ret == JFileChooser.APPROVE_OPTION)
        {
            File file = jfc.getSelectedFile();
            pathFile = file.getAbsolutePath();

        }

        play = new Play(pathFile);
        game.refresh(play.getBoard());
        board.update();

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
    public ArrayList<String> getBoard_data() { return board_data; }
    public void setBoard_data(ArrayList<String> board_data) { this.board_data = board_data; }
    public Point3D getNextStep() { return nextStep; }
    public void setNextStep(Point3D nextStep) { this.nextStep = nextStep; }
    public double getAzimut() { return azimut; }
    public void setAzimut(double azimut) { this.azimut = azimut; }
    public boolean isFirstTimeRun() { return firstTimeRun; }
    public void setFirstTimeRun(boolean firstTimeRun) { this.firstTimeRun = firstTimeRun; }
    public boolean isRunThread() { return runThread; }
    public void setRunThread(boolean runThread) { this.runThread = runThread; }
    public Game getGame() {return game; }
    public void setGame(Game game) { this.game = game; }
    public myFrame getFrame() { return frame; }
    public void setFrame(myFrame frame) { this.frame = frame; }
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
    public Play getPlay() { return play;}
    public void setPlay(Play play) { this.play = play; }
    public PlayerThread getPlayerThread() { return playerThread; }
    public void setPlayerThread(PlayerThread playerThread) { this.playerThread = playerThread; }
    private void observe(Observable o) { o.addObserver(this); }
    /**
     * run the Thread
     */
    private void RunThread() { playerThread.start(); }
    /**
     * create from player -> ThreadPlayer
     */

    private void createThread() {
        playerThread = new PlayerThread(game.getPlayer());
    }

    class PlayerThread extends Thread{
        private Player player;
        private boolean flag = true;

        public PlayerThread(Player player) {
            this.player = game.getPlayer();
        }

        @Override
        public void  run() {
            System.out.println("> In ThredRun");
            while(play.isRuning()) {
                play.rotate(azimut);
                ArrayList<String> board_data = play.getBoard();
                board.getGame().refresh(board_data);
                board.update();
                System.out.println(play.getStatistics());
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
