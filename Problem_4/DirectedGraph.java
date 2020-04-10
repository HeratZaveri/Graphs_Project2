import java.util.*;

class DirectedGraph {
    public List<Node> storageList;
         
    public DirectedGraph() {
        this.storageList = new ArrayList<>();
    }

    void addNode(final int nodeVal){
        final Node value = new Node(nodeVal);
        storageList.add(value);
    }

    void addDirectedEdge(final Node first, final Node second){
        if(storageList.contains(first) && storageList.contains(second) && (first.data != second.data) && !(nextDoor(first,second))){
            first.neighbors.add(second); 
        }       
     }
    void removeDirectedEdge(final Node first, final Node second){
        if(storageList.contains(first) && storageList.contains(second) && (first.data != second.data) && nextDoor(first,second)){
            first.neighbors.remove(second);
        }
    }

    boolean nextDoor(Node input1, Node input2){
        if(input1.neighbors.contains(input2) || input2.neighbors.contains(input1)){
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
        for(final Node myNode: storageList){
          System.out.print(myNode.data + ": ");
          for(final Node adjacent: myNode.neighbors){
             System.out.print(adjacent.data + " ");
          }
          System.out.println();
        }
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
      
}
