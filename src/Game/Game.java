package Game;

import Creatures.Block;
import Creatures.Fruit;
import Creatures.Pacman;
import Creatures.Player;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Game {
	/**
	 * this class represent the "game" (model)
	 * it holds all the arguments of the game
	 */
	private ArrayList<Fruit> fruits;
	private ArrayList<Block> blocks;
	private ArrayList<Pacman> pacmans;
	private ArrayList<Pacman> ghosts;
	private Player player;


	public Game() {
		init();

	}
	private void init(){

		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = new Player(0,0,0);
	}

	public Game (ArrayList<String> playersInboard)
	{	fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = null;



			for (int i=0 ; i< playersInboard.size(); i++ ){
				String line = playersInboard.get(i);

				if (line.startsWith("M")) {
					player = new Player(line);
					setPlayer(player);

				}
				if (line.startsWith("P")) {
					Pacman p = new Pacman(line);
					pacmans.add(p);
				}
				if (line.startsWith("G")) {
					Pacman g = new Pacman(line);
					ghosts.add(g);
				}
				if (line.startsWith("F")) {
					Fruit f = new Fruit(line);
					fruits.add(f);
				}
				if (line.startsWith("B")) {
					Block b = new Block(line);
					blocks.add(b);

				}

			}
	}

	public Game(String path) {

		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = new Player(0 ,0 , 20);


		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String content = br.readLine();

			String line = br.readLine();
			while (line != null) {
				if (line.startsWith("M")) {
					player = new Player(line);
					setPlayer(player);
				}
				if (line.startsWith("P")) {
					Pacman p = new Pacman(line);
					pacmans.add(p);
				}
				if (line.startsWith("G")) {
					Pacman g = new Pacman(line);
					ghosts.add(g);
				}
				if (line.startsWith("F")) {
					Fruit f = new Fruit(line);
					fruits.add(f);
				}
				if (line.startsWith("B")) {
					Block b = new Block(line);
					blocks.add(b);
				}
				line = br.readLine();
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	/**
	 *
	 * @param new_data ArrayList on new Data.
	 * update the game by this data
	 */
	public void refresh(ArrayList<String> new_data){

		pacmans.clear();
		blocks.clear();
		ghosts.clear();
		fruits.clear();
		player = null;

		for (int i=0 ; i< new_data.size(); i++ ){
			String line = new_data.get(i);

			if (line.startsWith("M")) {
				Player player = new Player(line);
				setPlayer(player);

			}
			if (line.startsWith("P")) {
				Pacman p = new Pacman(line);
				pacmans.add(p);
			}
			if (line.startsWith("G")) {
				Pacman g = new Pacman(line);
				ghosts.add(g);
			}
			if (line.startsWith("F")) {
				Fruit f = new Fruit(line);
				fruits.add(f);
			}
			if (line.startsWith("B")) {
				Block b = new Block(line);
				blocks.add(b);
			}
		}
	}

	/**
	 * copy constructor
	 * @param g reprenst game
	 */
	public Game (Game g) {
		
		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = null;
		
		for (int i = 0; i < fruits.size(); i++) {
			fruits.add(new Fruit(g.getFruits().get(i)));
		}
		for (int i = 0; i < blocks.size(); i++) {
			blocks.add(new Block(g.getBlocks().get(i)));
		}
		for (int i = 0; i < pacmans.size(); i++) {
			pacmans.add(new Pacman(g.getPacmans().get(i)));
		}
		for (int i = 0; i < ghosts.size(); i++) {
			ghosts.add(new Pacman(g.getGhosts().get(i)));
		}
		player = new Player(g.getPlayer());

	}

	/**
	 * clear the game
	 */
	public void clear(){

		fruits.clear();
		pacmans.clear();
		blocks.clear();
		ghosts.clear();
		setPlayer(null);
	}







	////////////////////getters and setters////////////////////

	public ArrayList<Fruit> getFruits() {return fruits;}
	public void setFruits(ArrayList<Fruit> fruits) {this.fruits = fruits;}
	public ArrayList<Block> getBlocks() {return blocks;}
	public void setBlocks(ArrayList<Block> blocks) {this.blocks = blocks;}
	public ArrayList<Pacman> getPacmans() {return pacmans;}
	public void setPacmans(ArrayList<Pacman> pacmans) {this.pacmans = pacmans;}
	public ArrayList<Pacman> getGhosts() {return ghosts;}
	public void setGhosts(ArrayList<Pacman> ghosts) {this.ghosts = ghosts;}
	public Player getPlayer() {return player;}
	public void setPlayer(Player player) {this.player = player;}

}
