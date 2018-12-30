package Accessories;

import Creatures.Player;
import GUI.Board;
import Robot.Play;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PlayerThread extends Thread implements Observer {
    Player player;
    private Point3D nextStep;
    private Play play;
    private Board board;
    private double azimut;

    public PlayerThread(Board board,Play play){
        this.board = board;
        this.player = board.getGame().getPlayer();
        this.play = play;
        this.nextStep = new Point3D(board.getGame().getPlayer().getPoint().get_x(),board.getGame().getPlayer().getPoint().get_y(),0);
        observe(board.getNextStep());    }

    private void observe(Observable o) { o.addObserver(this); }

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
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
    }

    /*
    get the azimut and the point from the observer object
     */
    @Override
    public void update(Observable o, Object arg) {
        nextStep = ((nextStep) o).getPoint();
        azimut = ((nextStep) o).getazimut();
    }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public Point3D getNextStep() { return nextStep; }
    public void setNextStep(Point3D nextStep) { this.nextStep = nextStep; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
    public double getAzimut() { return azimut; }
    public void setAzimut(double azimut) { this.azimut = azimut; }
}
