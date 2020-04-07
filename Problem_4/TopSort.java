import java.util.*;

public class TopSort {
    public TopSort(){}

    ArrayList<Node> khans(final DirectedGraph graph){
        HashMap<Node, Integer> inDegrees = inDegreeMapInitializer(graph);
        ArrayList<Node> listToReturn = new ArrayList<>();

        final Deque<Node> queue = new ArrayDeque<>();
        addNodesWithoutDependenciesToQueue(inDegrees, queue);

        while(!(queue.isEmpty())){
            Node curr = queue.removeLast();
            listToReturn.add(curr);
            for(Node neighbor: curr.neighbors){
                if(inDegrees.containsKey(neighbor)){
                    inDegrees.put(neighbor,inDegrees.get(neighbor)-1);
                }
                if(inDegrees.get(neighbor) == 0){
                    queue.addFirst(neighbor);
                }
            }
        }
        return listToReturn;
    }

    private static void addNodesWithoutDependenciesToQueue(HashMap<Node, Integer> inDegree, Deque<Node> queue) {
         for(Node graphNode: inDegree.keySet()){
             if(inDegree.get(graphNode) == 0){
                queue.addFirst(graphNode);
                inDegree.put(graphNode, inDegree.get(graphNode)-1);
             }
         }
    }
    
    HashMap<Node, Integer> inDegreeMapInitializer(final DirectedGraph graph){
         HashMap<Node,Integer> inDegreeMap = new HashMap<>();

         for(Node value: graph.storageList){
             inDegreeMap.put(value, 0);
         }

         for(Node graphNode: inDegreeMap.keySet()){
            for(int i = 0; i < graphNode.neighbors.size(); i++){
                if(inDegreeMap.containsKey(graphNode.neighbors.get(i))){
                    Node curr = graphNode.neighbors.get(i);
                    inDegreeMap.put(curr,inDegreeMap.get(curr)+1);
                }
            }
         }

        return inDegreeMap;
    }
    ArrayList<Node> mDFS(final DirectedGraph graph){
        Stack<Node> storage = new Stack<Node>();
        ArrayList<Node> finalList = new ArrayList<Node>();
        for(Node vertex: graph.getAllNodes()){
            if(!(vertex.isVisited)){
                mDFSHelper(vertex,storage);
            }
        }
        while(!(storage.isEmpty())){
            Node current = storage.pop();
            finalList.add(current);
        }
        return finalList;
    }

    void mDFSHelper(Node vrtx, Stack<Node> container){
        vrtx.isVisited = true;
        for(Node nextDoor: vrtx.neighbors){
            if(!(nextDoor.isVisited)){
                mDFSHelper(nextDoor, container);
            }
        }
        container.push(vrtx);
    }

}