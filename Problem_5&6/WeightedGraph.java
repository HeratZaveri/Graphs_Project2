import java.util.*;
class NodeWithEdge{
    public Node node;
    public int weight;

    public NodeWithEdge(Node node, int weight){
        this.node = node;
        this.weight = weight;
    }

    Node getNode(){
        return node;
    }
}
class Node {
    public int data;
    public List<NodeWithEdge> neighbors;
    public List<Node> connected;
    public boolean isVisited;
  
    public Node(final int data) {
      this.data = data;
      this.isVisited = false;
      this.neighbors = new ArrayList<>();
      this.connected = new ArrayList<>();
    }
}

class NodeComparator implements Comparator<NodeWithEdge>{
     public int compare(NodeWithEdge node1, NodeWithEdge node2){
        if(node1.weight <= node2.weight){
            return 1;
        }
        else if(node1.weight > node2.weight){
            return -1;
        }

        return 0;
     }

}
class WeightedGraph {
    public List<Node> storageList;
         
    public WeightedGraph() {
        this.storageList = new ArrayList<>();
    }

    void addNode(final int nodeVal){
        Node val = new Node(nodeVal);
        storageList.add(val);
    }

    void addWeightedEdge(final Node first, final Node second, final int weight){
        if(storageList.contains(first) && storageList.contains(second) && (first.data != second.data) && !(nextDoor(first,second))){
            first.neighbors.add(new NodeWithEdge(second,weight));
            first.connected.add(second); 
        }       
     }
    void removeDirectedEdge(final Node first, final Node second, int weight){
        if(storageList.contains(first)&& storageList.contains(second) && (first.data != second.data) && nextDoor(first,second)){
            first.connected.remove(second);     
         }
    }
    Node getNodeAtIndx(int i){
        return storageList.get(i);
    }
    Node getNode(int value){
        Node found = null;
        for(Node get: storageList){
          if(get.data == value){
             found = get;
          }
        }
        return found;
    }
    /*
    //needs fixing
    void removeCommonNodes(Node second, Node first){
        for(int i = 0; i < second.neighbors.size(); i++){
            if(second.neighbors.get(i).neighbors.contains(first)){
                removeDirectedEdge(second.neighbors.get(i),first);
            }
        }
    }
    */
    boolean nextDoor(Node input1, Node input2){
        if(input1.connected.contains(input2) || input2.connected.contains(input1)){
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