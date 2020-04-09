import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args){

    }
    WeightedGraph createRandomCompleteWeightedGraph(int n){


    }

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
    HashMap<Node, Integer> dijkstras(final Node start){
        HashMap<Node, Integer> dijkstra = new HashMap<>();
        dijkstra.put(start, 0);
        Node curr = start;
        while(curr != null && dijkstra.containsKey(curr)){
            for(Node neighbor: curr.neighbors.keySet()){
                if(!(neighbor.isVisited)){
                    dijkstra.put(neighbor, dijkstra.getOrDefault(neighbor, 0) + curr.neighbors.get(neighbor));

                }
            }
           //curr = next min distant node
        }

        return dijkstra;
    }
}