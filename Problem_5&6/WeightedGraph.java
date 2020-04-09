import java.util.*;

class Node {
    public int data;
    public HashMap<Node, Integer> neighbors;
    public boolean isVisited;
  
    public Node(final int data) {
      this.data = data;
      this.isVisited = false;
      this.neighbors = new HashMap<>();
    }
}
class WeightedGraph {
    public List<Node> storageList;
         
    public WeightedGraph() {
        this.storageList = new ArrayList<>();
    }

    void addNode(final int nodeVal){
        final Node value = new Node(nodeVal);
        storageList.add(value);
    }

    void addWeightedEdge(final Node first, final Node second, final int weight){
        if(storageList.contains(first) && storageList.contains(second) && (first.data != second.data) && !(nextDoor(first,second))){
            first.neighbors.put(second,weight); 
        }       
     }
    void removeDirectedEdge(final Node first, final Node second, int weight){
        if(storageList.contains(first)&& storageList.contains(second) && (first.data != second.data) && nextDoor(first,second)){
            first.neighbors.remove(second);     
         }
    }
    Node getNodeAtIndx(int i){
        return storageList.get(i);
     }

    boolean nextDoor(Node input1, Node input2){
        if(input1.neighbors.containsKey(input2) || input2.neighbors.containsKey(input1)){
            return true;
        }
        return false;   
    }
    HashSet<Node> getAllNodes(){
        HashSet<Node> myNodes = new HashSet<>();
        for(Node nodeVal: storageList){
            if(!(myNodes.contains(nodeVal))){
                    myNodes.add(nodeVal);
            }
        }
        return myNodes;
    }  
}