package GUI;

import javax.swing.*;
import java.awt.*;

public class myFrame extends JFrame {
    /**
     * this class represents the frame
     */
    private int x;
    private int y;
    private Board gameBoard;
    private MenuItem addPlayerButton;
    private MenuItem loadGame;
    private MenuItem runStep;
    private MenuItem autoRun;
    private MenuItem runAlgo;
    private MenuItem openStatics;

    /**
     * @param gameBoard build the frame with given board
     */
    public myFrame(Board gameBoard){

        this.gameBoard = gameBoard;
        this.setBounds(100,100,800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(gameBoard);
        this.setTitle("Pacman");
        setVisible(true);


        ////////////////////////////menuBar and MenuItems////////////////////////

        MenuBar main = new MenuBar();
        this.setMenuBar(main);

        Menu load = new Menu("Load Game");
        main.add(load);

        loadGame = new MenuItem("Load");
        load.add(loadGame);

        Menu add = new Menu("Add");
        main.add(add);

        addPlayerButton = new MenuItem("Add Player");
        add.add(addPlayerButton);

        Menu algo = new Menu("Algo");
        main.add(algo);

        runAlgo = new MenuItem("Run Algorithm");
        algo.add(runAlgo);

        Menu run = new Menu("RUN");
        main.add(run);

        runStep = new MenuItem("Run Steps");
        run.add(runStep);

        autoRun = new MenuItem("Auto Run");
        run.add(autoRun);

        Menu statics = new Menu("Statics");
        main.add(statics);

        openStatics = new MenuItem("view Statics");
        statics.add(openStatics);




    }


    ///////////////////getters ans setters////////////////////////////////

    public int getX() { return x; }
    public MenuItem getRunStep() { return runStep; }
    public MenuItem getAddPlayerButton() { return addPlayerButton; }
    public Board getGameBoard() { return gameBoard; }
    public void setGameBoard(Board gameBoard) { this.gameBoard = gameBoard; }
    public MenuItem getLoadGame() { return loadGame; }
    public void setLoadGame(MenuItem loadGame) { this.loadGame = loadGame; }
    public void setX(int x) { this.x = x; }
    public MenuItem getAutoRun() { return autoRun; }
    public void setAutoRun(MenuItem autoRun) { this.autoRun = autoRun; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public MenuItem getRunAlgo() { return runAlgo; }
    public void setRunAlgo(MenuItem runAlgo) { this.runAlgo = runAlgo; }
    public MenuItem getOpenStatics() { return openStatics; }
    public void setOpenStatics(MenuItem openStatics) { this.openStatics = openStatics; }

}

