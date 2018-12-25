package Accessories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Creatures.Block;
import Creatures.Fruit;
import Creatures.Pacman;
import Creatures.Player;


public class Game {

	private ArrayList<Fruit> fruits;
	private ArrayList<Block> blocks;
	private ArrayList<Pacman> pacmans;
	private ArrayList<Pacman> ghosts;
	private Player player;

	public Game() {
		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = new Player(0,0,0);
	}

	public Game(String path) {

		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = new Player(0,0,0);

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String content = br.readLine();
			
			String line = br.readLine();
			while (line != null) {
				if (line.startsWith("M")) {
					player = new Player(line);
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
	
	
	public Game (Game g) {
		
		fruits = new ArrayList<>();
		blocks = new ArrayList<>();
		pacmans = new ArrayList<>();
		ghosts = new ArrayList<>();
		player = new Player(0,0,0);
		
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