import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

class Graph {
    public List<Node> myAdjList;

    public Graph() {
      this.myAdjList = new ArrayList<>();
    }
    void addNode(final int nodeVal){
        Node value = new Node(nodeVal);
        myAdjList.add(value);
    }
    void addUndirectedEdge(final Node first, final Node second){
        if(myAdjList.contains(first) && myAdjList.contains(second) && notTheSame(first,second) && !(adjacent(first, second))){
            first.neighbors.add(second);
            second.neighbors.add(first);
        }
    }
    void removeUndirectedEdge(final Node first, final Node second){
        if(myAdjList.contains(first) && myAdjList.contains(second) && notTheSame(first,second) && adjacent(first, second)){
            first.neighbors.remove(second);
            second.neighbors.remove(first);
        }
    }

    public HashSet<Node> getAllNodes(){
        final HashSet<Node> myNodes = new HashSet<>();
        for(final Node nodeVal: myAdjList){
            if(!(myNodes.contains(nodeVal))){
                myNodes.add(nodeVal);
            }
        }
        return myNodes;
    }

    Node getNode(int value){
       Node found = null;
       for(Node get: myAdjList){
         if(get.data == value){
            found = get;
         }
       }
       return found;
    }

    boolean notTheSame(final Node input1, final Node input2){
      if(input1.data == input2.data){
        return false;
      }
      return true;
    }
    static boolean adjacent(final Node first, final Node second){
      if(first.neighbors.contains(second) && second.neighbors.contains(first)){
        return true;
      }
      return false;
    }
    void printMyGraph(){
      for(final Node myNode: myAdjList){
        System.out.print(myNode.data + ": ");
        for(final Node adjacent: myNode.neighbors){
           System.out.print(adjacent.data + " ");
        }
        System.out.println();
      }
    }
}

class Main {
  /*
  public static void main(final String[] args) {
    ArrayList<Node> myList = new ArrayList<>();
    //GraphSearch tools = new GraphSearch();
    Graph graph = new Graph();
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

    graph.addUndirectedEdge(one, two);
    graph.addUndirectedEdge(one,three);
    graph.addUndirectedEdge(two, four);
    graph.addUndirectedEdge(three, four);
    graph.addUndirectedEdge(three, five);
    graph.addUndirectedEdge(four, five);
    graph.addUndirectedEdge(four, six);
    graph.addUndirectedEdge(three, seven);
    graph.addUndirectedEdge(five, seven);
    graph.addUndirectedEdge(five, eight);
    //graph.addUndirectedEdge(eight, five);
    graph.printMyGraph();
    
  
    GraphSearch tools = new GraphSearch();
    //myList = tools.DFSRec(two, seven);
    //myList = tools.DFSIter(two, seven);
    //myList = tools. BFTIter(graph);
    for(Node value: myList){
      System.out.print(value.data + " ");
    }
    System.out.println();
  }
  */
  static Graph createRandomUnweightedGraph(final int n){
       //ArrayList<Integer> myList = new ArrayList<>();
       //Random rand = new Random();
       final Graph graph = new Graph();
       for(int i = 1; i < n; i++){
             graph.addNode(i);
       }
      for(final Node first: graph.myAdjList){   
        final int lower = ThreadLocalRandom.current().nextInt(1,Math.floorDiv(n, 2));
        final int range = ThreadLocalRandom.current().nextInt(Math.floorDiv(n, 2)+1,n);
        final List<Node> randomizedNodeList = createRandomList(graph.myAdjList,lower,range);
         for(final Node second: randomizedNodeList){
              graph.addUndirectedEdge(first, second);
         }
      }
       return graph;
  }
  // N nodes connected N 1 -> N 2 -> N 3 
  static Graph createLinkedList(final int n){
       final Graph graph = new Graph();
       for(int i = 1; i < n; i++){
            graph.addNode(i);
       }
       for(int i = 0; i < graph.myAdjList.size()-1; i++){
            graph.addUndirectedEdge(graph.myAdjList.get(i), graph.myAdjList.get(i+1));
       }
       return graph;
  }
  //randomize my list to create random graph in specified range
  static List<Node> createRandomList(final List<Node> lst,final int lower, final int n){
         //create copy of list
         final List<Node> copyList = new ArrayList<Node>(lst);
         Collections.shuffle(copyList);
         //System.out.println(copyList);
         return copyList.subList(lower,n);
  }
}

class GraphSearch {

  public GraphSearch(){

  }

  ArrayList<Node> DFSRec(final Node start, final Node end){
      ArrayList<Node> search = new ArrayList<Node>();
      return dfsRecHelp(start, end, search);
  }
  ArrayList<Node> dfsRecHelp(final Node begin, final Node find, final ArrayList<Node> myList){
      if(!(myList.contains(find))){
           if(!(begin.isVisited)){
               begin.isVisited = true;
               myList.add(begin);
               for(Node next: begin.neighbors){
                   dfsRecHelp(next, find, myList);
               }
           }
      }
      return myList;
  }
  ArrayList<Node> DFSIter(final Node start, final Node end){
      ArrayList<Node> search = new ArrayList<Node>();
      Stack<Node> storage = new Stack<Node>();
      storage.push(start);
      //search.add(start);
      //start.isVisited = true;
      while(!(storage.empty())){
          Node curr = storage.pop();
          curr.isVisited = true;
          search.add(curr);
          if(curr.data == end.data){
              return search;
          } 
          for(Node nextDoor: curr.neighbors){
              if(!(nextDoor.isVisited)){
                  storage.push(nextDoor);
                  nextDoor.isVisited = true;
              }
          }
      }
      return search;
  }
/*
  ArrayList<Node> BFTRec(Graph graph){
      ArrayList<Node> search = new ArrayList<Node>();
      Deque<Node> queue = new ArrayDeque<>();
      return BFTRecHelp(graph,search,queue);
  }

  ArrayList<Node> BFTRecHelp(graph,search,queue)
  
*/
  ArrayList<Node> BFTIter(Graph graph){
      ArrayList<Node> traversal = new ArrayList<Node>();
      Deque<Node> myQueue = new ArrayDeque<>();
      for(Node vertex: graph.getAllNodes()){
          if(!(vertex.isVisited)){
              vertex.isVisited = true;
              myQueue.add(vertex);
              //vertex.isVisited = true;
              while(!(myQueue.isEmpty())){
                  Node curr = myQueue.poll();
                  traversal.add(curr);
                  for(Node nextDoor: vertex.neighbors){
                      if(!(nextDoor.isVisited)){
                          nextDoor.isVisited = true;
                          myQueue.add(nextDoor);                           
                      }
                  }
              }
          }
      }
      return traversal;
  }
}

