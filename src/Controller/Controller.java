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

    private Point3D nextStep;
    private boolean firstTimeRun = true;

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

    private void observe(Observable o) {
        o.addObserver(this);
    }

    // Update from board on mouse clicked
    @Override
    public void update(Observable o, Object arg) {
        nextStep = ((nextStep) o).getPoint();
        System.out.println("Next step is now: " + nextStep);

        if (firstTimeRun) {
            initServer();
            firstTimeRun = false;
        }
        doNextStep();
    }

    public void doNextStep() {
        System.out.println("> In Run Game");
        MyCoords coords = new MyCoords();
        double azimut = coords.azimuth_elevation_dist(game.getPlayer().getPoint(), nextStep);
        play.rotate(azimut);
        board_data = play.getBoard();
        System.out.println(play.getStatistics());
        Refresh(board_data);
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
        });

        // if clicked on loadGame -> initGame
        frame.getLoadGame().addActionListener(e -> {
            initGame();
        });



        // Controller is observing the next step OBSERVABLE object
        observe(board.getNextStep());
    }

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
        Refresh(play.getBoard());
        board.update();

    }

    private void Refresh(ArrayList<String> new_data){

        game.getPacmans().clear();
        game.getBlocks().clear();;
        game.getGhosts().clear();
        game.getFruits().clear();
        game.setPlayer(null);

        for (int i=0 ; i< new_data.size(); i++ ){
            String line = new_data.get(i);

            if (line.startsWith("M")) {
                Player player = new Player(line);
                game.setPlayer(player);
            }
            if (line.startsWith("P")) {
                Pacman p = new Pacman(line);
                game.getPacmans().add(p);
            }
            if (line.startsWith("G")) {
                Pacman g = new Pacman(line);
                game.getGhosts().add(g);
            }
            if (line.startsWith("F")) {
                Fruit f = new Fruit(line);
                game.getFruits().add(f);
            }
            if (line.startsWith("B")) {
                Block b = new Block(line);
                game.getBlocks().add(b);
            }

        }

    }

    private void initServer() {
        if (firstTimeRun) { // Hence player is not null
            play.setIDs(308522416, 311229488);
            play.setInitLocation(game.getPlayer().getPoint().get_y(), game.getPlayer().getPoint().get_x());
            play.start();
        }
    }

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


}
