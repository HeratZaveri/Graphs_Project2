import java.util.*;

public class MainDijkstras {

    public static void main(String[] args){
        HashMap<Node,Integer> map =  new HashMap<>();
        WeightedGraph graph = new WeightedGraph();
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
    
        graph.addWeightedEdge(one, two, 6);
        graph.addWeightedEdge(two, three,5);
        graph.addWeightedEdge(one, four,1);
        graph.addWeightedEdge(two, five,2);
        //graph.addWeightedEdge(one, four,5);
        //graph.addWeightedEdge(three, five,3);
        graph.addWeightedEdge(four, five,1);
        graph.addWeightedEdge(four, two, 2);
        //graph.addWeightedEdge(six, seven, 2);
        graph.addWeightedEdge(five, three,5);
        //graph.addWeightedEdge(five, eight,3);
        //graph.addUndirectedEdge(eight, five);
        //graph.printMyGraph();
        
        map = dijkstras(one);
        for(Node value: map.keySet()){
          System.out.println(value.data + ": " + map.get(value));
        }
        }
        /*
        Fix This:
        static WeightedGraph createRandomCompleteWeightedGraph(int n){
            WeightedGraph graph = new WeightedGraph();
            Set<Node> sawIt = new HashSet<>();
            for(int i = 1; i <= n; i++){
                graph.addNode(i);
            }
            for(Node first: graph.getAllNodes()){
                sawIt.add(first);
                //sent lower and upperbound for range 
                int randomWeight = ThreadLocalRandom.current().nextInt(1,10);
                int lower = ThreadLocalRandom.current().nextInt(0,Math.floorDiv(n,2));
                int range = ThreadLocalRandom.current().nextInt(Math.floorDiv(n,2)+1,n);
                List<Node> copyList = new ArrayList<Node>(graph.storageList);
                Collections.shuffle(copyList);
                //Return list
                List<Node> randomizedNodeList = copyList.subList(lower,range);
                for(Node second: randomizedNodeList){
                    if(sawIt.contains(second) && !(second.neighbors.contains(first))){
                        graph.addWeightedEdge(first, second,randomWeight);
                        graph.removeCommonNodes(second,first);
                    }
                    else if(!(sawIt.contains(second))){
                        graph.addWeightedEdge(first, second,randomWeight);
                    }      
           }
                
            }
        }
        */
    
        static WeightedGraph createLinkedList(int n){
           WeightedGraph graph = new WeightedGraph();
           for(int i = 1; i < n; i++){
                graph.addNode(i);
           }
           for(int i = 0; i < graph.storageList.size()-1; i++){
                graph.addWeightedEdge(graph.getNodeAtIndx(i), graph.getNodeAtIndx(i+1),1);
           }
           return graph;
    
        }
        static HashMap<Node, Integer> dijkstras(Node start){
            HashMap<Node, Integer> distance = new HashMap<>();
            PriorityQueue<Node> pQueue = new PriorityQueue<Node>(11, new NodeComparator());
            distance.put(start, 0);
            start.distance = new Weight();
            pQueue.add(start);
            //Node curr = start;
            while(!(pQueue.isEmpty())){
                Node currnt = pQueue.poll();
                currnt.isVisited = true;
                for(Node neighbor: currnt.connected.keySet()){
                    int tempDistance = currnt.distance.weight + currnt.connected.get(neighbor);
                    //check if our map contains the node and if it isnt visited
                    if(distance.containsKey(neighbor) && !(neighbor.isVisited)){
                        //we need to check if current path is less than original path
                        if(tempDistance < distance.get(neighbor)){
                            pQueue.remove(neighbor);
                            neighbor.distance.setWeight(tempDistance);
                            distance.replace(neighbor, tempDistance);
                            pQueue.add(neighbor);
                        }
                    }
                    else{
                        distance.put(neighbor,tempDistance);
                        neighbor.distance = new Weight();
                        neighbor.distance.setWeight(tempDistance);
                        pQueue.add(neighbor);
                    }              
                }
            }
            return distance;
        }

}