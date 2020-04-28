import java.util.*;

class DirectedGraph {
    public ArrayList<Node> storageList;
         
    public DirectedGraph() {
        this.storageList = new ArrayList<>();
    }

    void addNode(final int nodeVal){
        Node value = new Node(nodeVal);
        storageList.add(value);
    }

    void addDirectedEdge(final Node first, final Node second){
        if(storageList.contains(first) && storageList.contains(second) && notTheSame(first, second) && !(nextDoor(first,second))){
            first.neighbors.add(second); 
        }       
     }
    void removeDirectedEdge(final Node first, final Node second){
        if(storageList.contains(first) && storageList.contains(second) && notTheSame(first, second) && nextDoor(first,second)){
            first.neighbors.remove(second);
        }
    }
    boolean notTheSame(Node input1, Node input2){
        if(input1.data == input2.data){
            return false;
        }
        return true;
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
    //removes cycles becuase its DAG - acyclic no common neighboors otherwise we get cycle
    void removeCommonNodes(Node second, Node first){
        for(int i = 0; i < second.neighbors.size(); i++){
            if(second.neighbors.get(i).neighbors.contains(first)){
                removeDirectedEdge(second.neighbors.get(i),first);
            }
        }
    }
    void printMyGraph(){
        for(Node myNode: storageList){
          System.out.print(myNode.data + ": ");
          for(Node adjacent: myNode.neighbors){
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
