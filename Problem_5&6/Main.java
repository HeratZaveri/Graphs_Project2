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

    graph.addWeightedEdge(one, two, 2);
    graph.addWeightedEdge(two, four,7);
    graph.addWeightedEdge(one, three,6);
    graph.addWeightedEdge(three, four,3);
    graph.addWeightedEdge(one, four,5);
    graph.addWeightedEdge(three, five,3);
    graph.addWeightedEdge(four, five,2);
    graph.addWeightedEdge(four, six, 4);
    graph.addWeightedEdge(six, seven, 2);
    graph.addWeightedEdge(five, seven,5);
    graph.addWeightedEdge(five, eight,3);
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
    static HashMap<Node, Integer> dijkstras(final Node start){
        HashMap<Node, Integer> dijkstra = new HashMap<>();
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        dijkstra.put(start, 0);
        Node curr = start;
        while(curr != null && dijkstra.containsKey(curr)){
            for(Node neighbor: curr.neighbors.keySet()){
                if(!(neighbor.isVisited)){
                    dijkstra.put(neighbor, dijkstra.getOrDefault(neighbor, 0) + curr.neighbors.get(neighbor));
                    pQueue.add(neighbor);
                }
            }
           curr = pQueue.poll();
        }

        return dijkstra;
    }
}