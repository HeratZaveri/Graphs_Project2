import java.util.*;

class GraphSearch {
  
    ArrayList<Node> DFSRec(final Node start, final Node end){
        ArrayList<Node> search = new ArrayList<Node>();
        return dfsRecHelp(start, end, search);
    }
    ArrayList<Node> dfsRecHelp(final Node begin, final Node find, final ArrayList<Node> myList){
        if(!(myList.contains(find))){
             if(!(begin.isVisited)){
                 begin.isVisited = true;
                 myList.add(begin);
                 for(Node next: myList){
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
        start.isVisited = true;
        while(!(storage.empty())){
            Node curr = storage.pop();
            //curr.isVisited = true;
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
    ArrayList<Node> BFTRec(Graph graph){
        ArrayList<Node> search = new ArrayList<Node>();
    }

    ArrayList<Node> BFTIter(Graph graph){
        ArrayList<Node> traversal = new ArrayList<Node>();
        Deque<Node> myQueue = new ArrayDeque<>();
        for(Node vertex: graph.myAdjList){
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
