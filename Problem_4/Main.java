import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//import javax.tools.ToolProvider;

class Node {
    public int data;
    public List<Node> neighbors;
    public boolean isVisited;
  
    public Node(final int data) {
      this.data = data;
      this.isVisited = false;
      this.neighbors = new ArrayList<>();
    }
}

public class Main{
  public static void main(String[] args){
    ArrayList<Node> myList = new ArrayList<>();
    TopSort topSort = new TopSort();
    DirectedGraph myGraph = new DirectedGraph();
    myGraph = createRandomDAG(1000);
    //myGraph.printMyGraph();
    System.out.println();
    //myList = topSort.mDFS(myGraph);
    //myList = topSort.khans(myGraph);
    //System.out.println(myList);
    for(Node some: myList){
      System.out.print(some.data + " ");
    }

  }
  static DirectedGraph createRandomDAG(final int n){
    //ArrayList<Integer> myList = new ArrayList<>();
    //Random rand = new Random();
    final DirectedGraph graph = new DirectedGraph();
    HashSet<Node> seen = new HashSet<>();
    for(int i = 1; i < n; i++){
          graph.addNode(i);
    }
   for(Node first: graph.storageList){   
       seen.add(first);
       int lower = ThreadLocalRandom.current().nextInt(1,Math.floorDiv(n, 2));
       int range = ThreadLocalRandom.current().nextInt(Math.floorDiv(n, 2)+1,n);
       List<Node> randomizedNodeList = createRandomList(graph.storageList,lower,range);
       for(Node second: randomizedNodeList){
         if(!seen.contains(second)){
            graph.addDirectedEdge(first, second);
         }
       }
     }
    return graph;
 }

 static List<Node> createRandomList(final List<Node> lst, int lower, int n){
    //create copy of list
    final List<Node> copyList = new ArrayList<Node>(lst);
    Collections.shuffle(copyList);
    //System.out.println(copyList);
    return copyList.subList(lower,n);
  }
}
