import java.util.*;
/*
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
*/

class Node {
    public int data;
    //public List<NodeWithEdge> neighbors;
    public Weight distance;
    public Map<Node,Integer> connected;
    public boolean isVisited;
  
    public Node(final int data) {
      this.data = data;
      this.isVisited = false;
      this.distance = null;
      //this.neighbors = new ArrayList<>();
      this.connected = new HashMap<>();
    }
}

class Weight{

    public int weight;
    public Node prev;

    public Weight(){
        this.weight = 0;
        this.prev = null;
    }

    void setWeight(int w){
        this.weight = w;
    }

    int getCurrWeight(){
        return weight;
    }

    void setParent(Node some){
        this.prev = some;
    }

}

class NodeComparator implements Comparator<Node>{
     public int compare(Node node1, Node node2){
        if(node1.distance.getCurrWeight() < node2.distance.getCurrWeight()){
            return -1;
        }
        else if(node1.distance.getCurrWeight() > node2.distance.getCurrWeight()){
            return 1;
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

    void addWeightedEdge(final Node first, final Node second, final int edgeWeight){
        if(storageList.contains(first) && storageList.contains(second) && notTheSame(first, second) && !(nextDoor(first,second))){
            first.connected.put(second,edgeWeight); 
        }       
     }
    void removeDirectedEdge(final Node first, final Node second){
        if(storageList.contains(first)&& storageList.contains(second) && notTheSame(first,second) && nextDoor(first,second)){
            first.connected.remove(second);    
         }
    }
    boolean notTheSame(Node node, Node node2){
        if(node.data == node2.data){
            return false;
        }
        return true;
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
    //needs fixing
    void removeCommonNodes(Node second, Node first){
        for(Node next: second.connected.keySet()){
            if(next.connected.containsKey(first)){
                removeDirectedEdge(first, second);
            }
        }
    }
    
    boolean nextDoor(Node input1, Node input2){
        if(input1.connected.containsKey(input2) || input2.connected.containsKey(input1)){
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

    void printMyGraph(){
        for(Node vrtx: storageList){
            System.out.print(vrtx.data + ": ");
            for(Node edge: vrtx.connected.keySet()){
                System.out.print(edge.data + " ");
            }
            System.out.println();
        }
    }
}