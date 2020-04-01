import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Node {
  public int data;
  public List<Node> listOfList;
  public boolean isVisited;

  public Node(int data) {
    this.data = data;
    this.isVisited = false;
    this.listOfList = new ArrayList<>();
  }
}

class Graph {
    //public HashMap<String,List<String>> myAdjList;
    public List<Node> myAdjList;

    public Graph() {
      this.myAdjList = new ArrayList<>();
    }
    void addNode(int nodeVal){
        Node value = new Node(nodeVal);
        myAdjList.add(value);
    }
    void addUndirectedEdge(Node first, Node second){
        if(myAdjList.contains(first) && myAdjList.contains(second) && notTheSame(first,second) && notAdjacent(first, second)){
            first.listOfList.add(second);
            second.listOfList.add(first);
        }
    }
    void removeUndirectedEdge(Node first, Node second){
        if(myAdjList.contains(first) && myAdjList.contains(second) && notTheSame(first,second) && notAdjacent(first, second)){
            first.listOfList.remove(second);
            second.listOfList.remove(first);
        }
    }
    //Linked List would have a directed edge
    void addLinkedListEdge(Node first, Node second){
      if(myAdjList.contains(first) && myAdjList.contains(second)){
        first.listOfList.add(second);
      }
    }

    public HashSet<Node> getAllNodes(){
        HashSet<Node> myNodes = new HashSet<>();
        for(Node nodeVal: myAdjList){
            if(!(myNodes.contains(nodeVal))){
                myNodes.add(nodeVal);
            }
        }
        return myNodes;
    }

    boolean notTheSame(Node input1, Node input2){
      if(input1.data == input2.data){
        return false;
      }
      return true;
    }
    static boolean notAdjacent(Node first, Node second){
      if(first.listOfList.contains(second) || second.listOfList.contains(first)){
        return false;
      }else{
        return true; 
      }
    }
    void printMyGraph(){
      for(Node myNode: myAdjList){
        System.out.print(myNode.data + ": ");
        for(Node adjacent: myNode.listOfList){
           System.out.print(adjacent.data + " ");
        }
        System.out.println();
      }
    }
}

class Main {
  public static void main(String[] args) {
     
    Graph graph = createRandomUnweighteGraph(15);
    graph.printMyGraph();
    
    System.out.println();

    Graph graph2 = createLinkedList(10);
    graph2.printMyGraph();
  }
  static Graph createRandomUnweighteGraph(int n){
       //ArrayList<Integer> myList = new ArrayList<>();
       //Random rand = new Random();
       Graph graph = new Graph();
       for(int i = 1; i < n; i++){
             graph.addNode(i);
       }
      for(Node first: graph.myAdjList){   
        int range = ThreadLocalRandom.current().nextInt(1,n);
        List<Node> randomizedNodeList = createRandomList(graph.myAdjList,range);
         for(Node second: randomizedNodeList){
              graph.addUndirectedEdge(first, second);
         }
      }
       return graph;
  }
  // N nodes connected N 1 -> N 2 -> N 3 only in one direction
  static Graph createLinkedList(int n){
       Graph graph = new Graph();
       for(int i = 1; i < n; i++){
            graph.addNode(i);
       }
       for(int i = 0; i < graph.myAdjList.size()-1; i++){
            graph.addLinkedListEdge(graph.myAdjList.get(i), graph.myAdjList.get(i+1));
       }
       return graph;
  }

  //randomize my list to create random graph in specified range
  static List<Node> createRandomList(List<Node> lst, int n){
         //System.out.println(lst);
         List<Node> copyList = new ArrayList<Node>(lst);
         Collections.shuffle(copyList);
         //System.out.println(copyList);
         return copyList.subList(0,n);
  }
}
