package Accessories;

import Creatures.Block;
import Creatures.Fruit;
import Creatures.Player;
import GUI.Board;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBuilder {

    private Board board;
    private MyCoords coords = new MyCoords();
    private ArrayList<GraphNode> vertices;
    private LOS los;
    private Graph graph;

    public GraphBuilder(Board board) {
        this.board = board;
        los = new LOS(board);
        graph = new Graph();
        vertices = new ArrayList<>();
        changePlayerPixels();
        changeVertices();
        getClosestFruit();
        BFS();


    }

    public void changePlayerPixels() {
        //change the player point2pixels
        board.getGame().getPlayer().setPixels(board.getConvertor().gps2Pixels(board.getGame().getPlayer().getPoint()));
        int[] playerPixels = board.getGame().getPlayer().getPixels();
        Point3D point = new Point3D(playerPixels[0], playerPixels[1]);
        GraphNode playerNode = new GraphNode(point);
        vertices.add(playerNode);
    }

    public void changeVertices() {
        //change the fruits points gps2pixels.

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
            //add 2 pixels for each pixels

            points[0].set_x(points[0].get_x() - 2);
            points[0].set_y(points[0].get_y() - 2);

            points[1].set_x(points[1].get_x() + 2);
            points[1].set_y(points[1].get_y() - 2);

            points[2].set_x(points[2].get_x() + 2);
            points[2].set_y(points[2].get_y() + 2);

            points[3].set_x(points[3].get_x() - 2);
            points[3].set_y(points[3].get_y() + 2);

            for (int i = 0; i < points.length; i++) {
                GraphNode blockNode = new GraphNode(points[i]);
                vertices.add(blockNode);

            }
        }
    }

    public void BFS() {
        Queue<GraphNode> bfsQueue = new LinkedList<>();
        int fruitIndex = vertices.size()-1;
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

        for (GraphNode node : vertices) {
            node.setSeen(false);
        }


        for (GraphNode node : vertices){

            System.out.println(node.getID()+ ": ");
            for (GraphNode ne : node.getNeigbours()){
                System.out.println(ne.getID()+ " , ");
            }
            System.out.println("----------------------------");
        }
        buildGraph();

    }

    public void getClosestFruit() {
        double minDis = Integer.MAX_VALUE;
        Fruit closetFruit = null;
        for (Fruit fruit : board.getGame().getFruits()) {
            double dis = coords.distance3d(board.getGame().getPlayer().getPoint(), fruit.getPoint());
            if (dis < minDis) {
                minDis = dis;
                closetFruit = fruit;
            }
        }

        Point3D closestFruitPoint = new Point3D(closetFruit.getPixels()[0], closetFruit.getPixels()[1]);
        GraphNode fruitNode = new GraphNode(closestFruitPoint);
        vertices.add(fruitNode);
    }

    public void buildGraph() {
        for (int i = 0; i < vertices.size(); i++) {
                Node d = new Node("" + i);
                graph.add(d);
        }
        for (int i = 0; i < vertices.size(); i++) {
            GraphNode curr = vertices.get(i);
            for (GraphNode neighbour : curr.getNeigbours()) {
                System.out.println(curr.getPoint().distance2D(neighbour.getPoint()));
                graph.addEdge(""+i,""+neighbour.getID(),curr.getPoint().distance2D(neighbour.getPoint()));
            }

        }

        Graph_Algo.dijkstra(graph, "0");

        Node b = graph.getNodeByName("" + (vertices.size()-1));
        System.out.println("***** Graph Demo for OOP_Ex4 *****");
        System.out.println(b);
        System.out.println("Dist: "+b.getDist());
        ArrayList<String> shortestPath = b.getPath();
        for(int i=0;i<shortestPath.size();i++) {
            System.out.print(","+shortestPath.get(i));
        }



    }

}
