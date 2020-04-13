import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args){
    HashMap<Node,Integer> map =  new HashMap<>();
    //GraphSearch tools = new GraphSearch();
    WeightedGraph graph = new WeightedGraph();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(4);
    graph.addNode(5);
    graph.addNode(6);
    graph.addNode(7);
    graph.addNode(8);

    Node one = graph.getNode(1);
    Node two = graph.getNode(2);
    Node three = graph.getNode(3);
    Node four = graph.getNode(4);
    Node five = graph.getNode(5);
    Node six = graph.getNode(6);
    Node seven = graph.getNode(7);
    Node eight = graph.getNode(8);

    graph.addWeightedEdge(one, two, 6);
    graph.addWeightedEdge(two, three,5);
    graph.addWeightedEdge(one, four,1);
    graph.addWeightedEdge(two, five,2);
    //graph.addWeightedEdge(one, four,5);
    //graph.addWeightedEdge(three, five,3);
    graph.addWeightedEdge(four, five,1);
    graph.addWeightedEdge(four, two, 2);
    //graph.addWeightedEdge(six, seven, 2);
    graph.addWeightedEdge(five, three,5);
    //graph.addWeightedEdge(five, eight,3);
    //graph.addUndirectedEdge(eight, five);
    //graph.printMyGraph();
    
    map = dijkstras(one);
    for(Node value: map.keySet()){
      System.out.println(value.data + ": " + map.get(value));
    }
    System.out.println();

    }
    /*
    WeightedGraph createRandomCompleteWeightedGraph(int n){
    }
    */

    WeightedGraph createLinkedList(int n){
        WeightedGraph graph = new WeightedGraph();
       for(int i = 1; i < n; i++){
            graph.addNode(i);
       }
       for(int i = 0; i < graph.storageList.size()-1; i++){
            graph.addWeightedEdge(graph.getNodeAtIndx(i), graph.getNodeAtIndx(i+1),1);
       }
       return graph;

    }
    static HashMap<Node, Integer> dijkstras(Node start){
        HashMap<Node, Integer> distance = new HashMap<>();
        PriorityQueue<NodeWithEdge> pQueue = new PriorityQueue<NodeWithEdge>(11, new NodeComparator());
        distance.put(start, 0);
        pQueue.add(new NodeWithEdge(start, 0));
        //Node curr = start;
        while(!(pQueue.isEmpty())){
            NodeWithEdge currnt = pQueue.poll();
            Node curr = currnt.getNode();
            curr.isVisited = true;
            for(NodeWithEdge neighbor: curr.neighbors){
                int tempDistance = currnt.weight + neighbor.weight;
                //check if our map contains the node and if it isnt visited
                if(distance.containsKey(neighbor.node) && !(neighbor.node.isVisited)){
                    //we need to check if current path is less than original path
                    if(tempDistance < distance.get(neighbor.node)){
                        distance.put(neighbor.node, tempDistance);
                        //set it as visited
                        neighbor.node.isVisited = true;

                    }
                }
                else{
                    distance.put(neighbor.node,tempDistance);
                    pQueue.add(neighbor);
                }              
            }
        }
        return distance;
    }

    GridGraph createRandomGridGraph(int n){
        GridGraph grid = new GridGraph();   
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                grid.addGridNode(i, j, i);
            }
        }

        for(int i = 0; i < grid.maze.size(); i++){
            for(int j = 0; j < grid.maze.size(); j++){
                grid.addGridNode(i, j, i);
            }
        }
        return grid;
    }
}