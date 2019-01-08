package Accessories;

import Creatures.Block;
import Creatures.Fruit;
import Creatures.Player;
import GUI.Board;
import GraphObject.Graph;
import GraphObject.Graph_Algo;
import GraphObject.Node;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBuilder {

    private Board board;
    private MyCoords coords = new MyCoords();
    private ArrayList<GraphNode> vertices;
    private ArrayList<Target> targts;
    private LOS los;
    private Graph graph;
    private Fruit fruitTarget;
    private boolean playerFlag = false;
    private boolean blockFlag = false;
    private static boolean boxesFlag = false;

    public GraphBuilder(Board board) {

        GraphNode.resetCounterId();
        Target.resetCounterId();

        this.board = board;
        los = new LOS(board);
        graph = new Graph();
        vertices = new ArrayList<>();
        targts = new ArrayList<>();

        init();

    }
    public void init(){

        BuildPathes();
    }
    public void changePlayerPixels() {
        //change the player point2pixels
        if (!playerFlag) {
            board.getGame().getPlayer().setPixels(board.getConvertor().gps2Pixels(board.getGame().getPlayer().getPoint()));
            Point3D point = new Point3D(board.getGame().getPlayer().getPixels()[0] , board.getGame().getPlayer().getPixels()[1]);
            GraphNode playerNode = new GraphNode(point);
            vertices.add(playerNode);

        }

        else {
            Point3D point = new Point3D(board.getGame().getPlayer().getPixels()[0] , board.getGame().getPlayer().getPixels()[1]);
            GraphNode playerNode = new GraphNode(point);
            vertices.add(playerNode);
        }

        playerFlag = true;

    }

    public void changeVertices()
    {

        //change the fruits points gps2pixels.
        if (!blockFlag ) {

            for (Fruit fruit : board.getGame().getFruits()) {
                fruit.setPixels(board.getConvertor().gps2Pixels(fruit.getPoint()));
            }
                //change the blocks points gps2pixels.
                for (Block block : board.getGame().getBlocks()) {
                    Point3D[] points = block.getPoints();
                    for (int i = 0; i < points.length; i++) {
                        //change the point to pixels


                        int[] pixels = board.getConvertor().gps2Pixels(points[i]);
                        points[i].set_x(pixels[0]);
                        points[i].set_y(pixels[1]);
                    }

                    //add 2 pixels for each pixels will not enter here
                    if (!boxesFlag) {

                        points[0].set_x(points[0].get_x() - 5);
                        points[0].set_y(points[0].get_y() - 5);

                        points[1].set_x(points[1].get_x() + 5);
                        points[1].set_y(points[1].get_y() - 5);

                        points[2].set_x(points[2].get_x() + 5);
                        points[2].set_y(points[2].get_y() + 5);

                        points[3].set_x(points[3].get_x() - 5);
                        points[3].set_y(points[3].get_y() + 5);
                    }


                    for (int i = 0; i < points.length; i++) {
                        GraphNode blockNode = new GraphNode(points[i]);
                        vertices.add(blockNode);
                    }
                }

        } else {

            for (Block block : board.getGame().getBlocks()) {
                Point3D[] points = block.getPoints();
                for (int i = 0; i < points.length; i++) {
                    GraphNode blockNode = new GraphNode(points[i]);
                    vertices.add(blockNode);
                }
            }
        }

        blockFlag = true;
    }

    public void BuildPathes() {

        for (Fruit fruit : board.getGame().getFruits()) {

            GraphNode.resetCounterId();
            changePlayerPixels();
            changeVertices();

            Point3D fruitPixels = new Point3D(fruit.getPixels()[0],fruit.getPixels()[1]);
            GraphNode fruitNode = new GraphNode(fruitPixels);

            vertices.add(fruitNode);

            Target target = new Target(fruitPixels, fruit);

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

            // to deltete!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
           /* System.out.println("iteration number "+ target.getID());

            for (int i = 0 ; i<vertices.size() ; i++){
                GraphNode curr = vertices.get(i);
                System.out.println("ID :"+ curr.getID()+"\n");
                for (GraphNode neighbour : curr.getNeigbours()) {
                    System.out.print(neighbour.getID()+", ");
                }

                System.out.println();
            }*/

            buildGraph(target);
        }
    }

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

        if (target.getFruit().getID() != board.getGame().getFruits().get(board.getGame().getFruits().size()-1).getID()) {
            vertices.clear();
        }
    }

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

    public void PrintAllTaregets (){
       for (int i = 0 ; i<targts.size() ; i++){
            Target target = targts.get(i);
            System.out.println(target.toString());
            System.out.println("-----------------------------");
        }
    }

    public void clear(){
        graph.clear_meta_data();
    }


    public ArrayList<GraphNode> getVertices() { return vertices; }
    public void setVertices(ArrayList<GraphNode> vertices) { this.vertices = vertices; }
    public LOS getLos() { return los; }
    public void setLos(LOS los) { this.los = los; }
    public Graph getGraph() { return graph; }
    public void setGraph(Graph graph) { this.graph = graph; }
    public Fruit getFruitTarget() { return fruitTarget; }
    public void setFruitTarget(Fruit fruitTarget) { this.fruitTarget = fruitTarget; }
    public  boolean isPlayerFlag() { return playerFlag; }
    public  void setPlayerFlag(boolean playerFlag) { playerFlag = playerFlag; }
    public  boolean isBlockFlag() { return blockFlag; }
    public  void setBlockFlag(boolean blockFlag) { blockFlag = blockFlag; }
    public static boolean isBoxesFlag() { return boxesFlag; }
    public static void setBoxesFlag(boolean boxesFlag) { GraphBuilder.boxesFlag = boxesFlag; }

}
