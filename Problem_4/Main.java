import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//import javax.tools.ToolProvider;

class Node {
    public int data;
    public ArrayList<Node> neighbors;
    public boolean isVisited;
  
    public Node(final int data) {
      this.data = data;
      this.isVisited = false;
      this.neighbors = new ArrayList<>();
    }
}

public class Main{
  public static void main(String[] args){
    ArrayList<Node> myListmDfs = new ArrayList<>();
    ArrayList<Node> myListKhans = new ArrayList<>();
    /*
    DirectedGraph graph = new DirectedGraph();
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

    graph.addDirectedEdge(one, two);
    //graph.addDirectedEdge(one,three);
    graph.addDirectedEdge(two, four);
    graph.addDirectedEdge(three, four);
    graph.addDirectedEdge(one, four);
    graph.addDirectedEdge(three, five);
    graph.addDirectedEdge(four, five);
    graph.addDirectedEdge(four, six);
    graph.addDirectedEdge(six, seven);
    graph.addDirectedEdge(five, seven);
    graph.addDirectedEdge(five, eight);
    //graph.printMyGraph();
    */


    TopSort topSort = new TopSort();

    DirectedGraph myGraph = createRandomDAG(1000);
    DirectedGraph myGraphKhans = createRandomDAG(10);

    //myGraph.printMyGraph();
    System.out.println();
    //System.out.println("Modified DFS: ");
    //myListmDfs = topSort.mDFS(myGraph);  
    for(Node some: myListmDfs){
      System.out.print(some.data + " ");
    }

    System.out.println("Khans Algorithm: ");
    myListKhans = topSort.khans(myGraphKhans);
    for(Node some: myListKhans){
      System.out.print(some.data + " ");
    } 
  }
  static DirectedGraph createRandomDAG(final int n){
    //ArrayList<Integer> myList = new ArrayList<>();
    //Random rand = new Random();
    DirectedGraph graph = new DirectedGraph();
    HashSet<Node> sawIt = new HashSet<>();
    for(int i = 1; i < n; i++){
          graph.addNode(i);
    }
   for(Node first: graph.storageList){   
       sawIt.add(first);
       //sent lower and upperbound for range 
       int lower = ThreadLocalRandom.current().nextInt(0,Math.floorDiv(n,2));
       int range = ThreadLocalRandom.current().nextInt(Math.floorDiv(n,2)+1,n);
       List<Node> copyList = new ArrayList<Node>(graph.storageList);
       Collections.shuffle(copyList);
       //Return list
       List<Node> randomizedNodeList = copyList.subList(lower,range);
       for(Node second: randomizedNodeList){
         //second predecessor node but not neighboor of current noe -first
         if(sawIt.contains(second) && !(second.neighbors.contains(first))){
            graph.addDirectedEdge(first, second);
            graph.removeCommonNodes(second,first);
         }
         //if we never saw the second node
         else if(!(sawIt.contains(second))){
           graph.addDirectedEdge(first, second);
         }
       }
     }
    return graph;
 }
 
}
