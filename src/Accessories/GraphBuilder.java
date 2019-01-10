package Accessories;

import Creatures.Block;
import Creatures.Fruit;
import Game.Game;
import Graph.Graph;
import Graph.Graph_Algo;
import Graph.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * this class represent the building pathes procces.
 *  for each fruit we build target object and find the minimum path to it.
 *  this class is being called every time fruit is eaten to find the new minimum path in accordance the new location of the player
 */
public class GraphBuilder {

    private Game game;
    private Convertors convertor;
    private ArrayList<GraphNode> vertices;
    private ArrayList<Target> targts;
    private LOS los;
    private Fruit fruitTarget;
    private boolean playerFlag = false;
    private boolean converted = false;


    /**
     *
     * @param game the model of the game.
     * @param convertor to compute the pixels
     */
    public GraphBuilder(Game game, Convertors convertor) {


        this.game = game;
        this.convertor = convertor;
        los = new LOS(game, convertor);
        vertices = new ArrayList<>();
        targts = new ArrayList<>();

        init();

    }

    public void init(){

        buildPathes();
    }

    /**
     * this method computes all the paths to each fruit.
     * it calls BfS to each fruit.
     * running time complexity : O((|V|+|E|) * |F|)
     */
    public void buildPathes() {

        for (Fruit fruit :game.getFruits()) {

            GraphNode.resetCounterId();
            changePlayerPixels();
            changeVerticesToPixels();
            addBlocksVertices();
            Point3D fruitPixels = new Point3D(fruit.getPixels()[0],fruit.getPixels()[1]);
            GraphNode fruitNode = new GraphNode(fruitPixels);
            vertices.add(fruitNode);
            Target target = new Target(fruitPixels, fruit);

            //find the neigbours
            BFS();

            // build the grpah
            buildGraph(target);
        }
    }

    /**
     * computes all the neigbours in the graph in order: Player->block->block->...->fruit
     * for more information about BFS: https://en.wikipedia.org/wiki/Breadth-first_search
     */
    private void BFS() {

        int fruitIndex = vertices.size()-1;
        Queue<GraphNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(vertices.get(0));
        while (!bfsQueue.isEmpty()) {
            GraphNode pollNode = bfsQueue.poll();
            pollNode.setSeen(true);
            for (GraphNode node : vertices) {
                if (node.isSeen()) {
                    continue;
                } else if (node.getID() == pollNode.getID()) {
                    continue;
                } else if (!los.LOS(pollNode.getPoint(), node.getPoint())) {
                    pollNode.getNeigbours().add(node);
                    if (node.getID() != fruitIndex) {
                        bfsQueue.add(node);
                        node.setSeen(true);
                    }
                }
            }
        }
    }

    /**
     *this method is called to build the graph in accordance of the BFS output
     * @param target build the graph to each target and add to list
     */
    private void buildGraph(Target target) {
        Graph g = new Graph();

        for (int i = 0; i < vertices.size() ; i++) {
            if (vertices.get(i).getNeigbours().size() != 0) {
                Node d = new Node("" + i);
                d.set_id(i);
                g.add(d);
            }
        }

        Node a = new Node("" + (vertices.size() -1));
        a.set_id((vertices.size() -1));
        g.add(a);

        for (int i = 0; i < vertices.size(); i++) {
            GraphNode curr = vertices.get(i);
            if (curr.getNeigbours().size() != 0 ) {
                for (GraphNode neighbour : curr.getNeigbours()) {
                    g.addEdge("" + i, "" + neighbour.getID(), curr.getPoint().distance2D(neighbour.getPoint()));
                }
            }
        }
        // find the minimums path to the fruit
        Graph_Algo.dijkstra(g, ""+ 0);
        Node b = g.getNodeByName("" + (vertices.size()-1));
        ArrayList<String> shortestPath = b.getPath();
        target.setDistance(b.getDist());

        for (int i = 0 ;i < shortestPath.size() ; i++){
            String s = b.getPath().get(i);
            target.getPath().add(s);
        }

        targts.add(target);
        g.clear_meta_data();
        GraphNode.resetCounterId();
        Target.resetCounterId();

        if (target.getFruit().getID() != game.getFruits().get(game.getFruits().size()-1).getID()) {
            vertices.clear();
        }
    }

    /**
     *
     * @return the closest fruitTarget to the player
     */
    public Target getClosestTarget() {
        if (targts.isEmpty() ) return null;
        double min = Integer.MAX_VALUE;
        Target Mintarget = targts.get(0);
        for (Target target : targts){
            if (target.getDistance() < min){
                min = target.getDistance();
                Mintarget = target;

            }
        }
        return Mintarget;
    }

    /**
     * this method computes the pixels of the player and add to vertices list
     */
    public void changePlayerPixels() {
        //change the player point2pixels
        if (!playerFlag) {
            game.getPlayer().setPixels(convertor.gps2Pixels(game.getPlayer().getPoint()));
            Point3D point = new Point3D(game.getPlayer().getPixels()[0] , game.getPlayer().getPixels()[1]);
            GraphNode playerNode = new GraphNode(point);
            vertices.add(playerNode);

        }

        else {
            Point3D point = new Point3D(game.getPlayer().getPixels()[0] , game.getPlayer().getPixels()[1]);
            GraphNode playerNode = new GraphNode(point);
            vertices.add(playerNode);
        }

        playerFlag = true;

    }

    /**
     * this functions comoutes the pixels of the fruits abd block vertices
     */
    public void changeVerticesToPixels() {
        //change the fruits points gps2pixels.
        if (!converted) {
            for (Fruit fruit : game.getFruits()) {
                fruit.setPixels(convertor.gps2Pixels(fruit.getPoint()));
            }
            //change the blocks points gps2pixels.
            for (Block block : game.getBlocks()) {
                Point3D[] points = block.getPoints();
                for (int i = 0; i < points.length; i++) {
                    //change the point to pixels


                    int[] pixels = convertor.gps2Pixels(points[i]);
                    points[i].set_x(pixels[0]);
                    points[i].set_y(pixels[1]);
                }

                //add 2 pixels for each pixels will not enter here

                points[0].set_x(points[0].get_x() - 3);
                points[0].set_y(points[0].get_y() - 3);

                points[1].set_x(points[1].get_x() + 3);
                points[1].set_y(points[1].get_y() - 3);

                points[2].set_x(points[2].get_x() + 3);
                points[2].set_y(points[2].get_y() + 3);

                points[3].set_x(points[3].get_x() - 3);
                points[3].set_y(points[3].get_y() + 3);

            }

        } else {
            return;
        }

        converted = true;
    }

    /**
     * add the blocks vertices to vertices list
     */
    private void addBlocksVertices() {

        for (Block block :game.getBlocks()) {
            Point3D[] points = block.getPoints();
            for (int i = 0; i < points.length; i++) {
                GraphNode blockNode = new GraphNode(points[i]);
                vertices.add(blockNode);
            }
        }
    }





    /////////////////////getters ans setter//////////////////////////////////

    public ArrayList<GraphNode> getVertices() { return vertices; }
    public void setVertices(ArrayList<GraphNode> vertices) { this.vertices = vertices; }
    public LOS getLos() { return los; }
    public void setLos(LOS los) { this.los = los; }
    public Fruit getFruitTarget() { return fruitTarget; }
    public void setFruitTarget(Fruit fruitTarget) { this.fruitTarget = fruitTarget; }
    public  boolean isPlayerFlag() { return playerFlag; }
    public  void setPlayerFlag(boolean playerFlag) { playerFlag = playerFlag; }
    public  boolean isBlockFlag() { return converted; }
    public  void setBlockFlag(boolean blockFlag) { blockFlag = blockFlag; }
}
