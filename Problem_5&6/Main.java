import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args){
    /*
    HashMap<Node,Integer> map =  new HashMap<>();
    //GraphSearch tools = new GraphSearch();
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
    */

    /*
    GridGraph grid = createRandomGridGraph(100);
    //grid.printGrid();

    //System.out.println();
    GridNode source = grid.getNodeAtIndx(0);
    System.out.println("Source Node: " + "("+ dest.x + "," + dest.y+") ");
    GridNode dest = grid.getNodeAtIndx(grid.maze.size()-1);
    System.out.println("Dest Node: ""("+ dest.x + "," + dest.y+") ");
    System.out.println("Problem 6c A* Path: ");
    ArrayList<GridNode> star = aStar(source, dest);
    System.out.println();
    System.out.print("[ ");
    for (GridNode node: star){
        System.out.print("("+node.x + "," + node.y+") ");
    }
    System.out.print(" ]");
    //System.out.println(star);
    */

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
        PriorityQueue<NodeWithEdge> pQueue = new PriorityQueue<NodeWithEdge>(11, new NodeComparator());
        distance.put(start, 0);
        pQueue.add(new NodeWithEdge(start, 0));
        //Node curr = start;
        while(!(pQueue.isEmpty())){
            NodeWithEdge currnt = pQueue.poll();
            Node curr = currnt.getNode();
            curr.isVisited = true;
            for(NodeWithEdge neighbor: curr.neighbors){
                int tempDistance = currnt.weight + neighbor.weight;
                //check if our map contains the node and if it isnt visited
                if(distance.containsKey(neighbor.node) && !(neighbor.node.isVisited)){
                    //we need to check if current path is less than original path
                    if(tempDistance < distance.get(neighbor.node)){
                        distance.put(neighbor.node, tempDistance);
                    }
                }
                else{
                    distance.put(neighbor.node,tempDistance);
                    pQueue.add(neighbor);
                }              
            }
        }
        return distance;
    }

    static GridGraph createRandomGridGraph(int n){
        GridGraph grid = new GridGraph();   
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                grid.addGridNode(i, j);
            }
        }

        for(GridNode first: grid.maze){
            List<GridNode> random = grid.getRandomCandidateNodes(first);
            //System.out.println(random);
            for(GridNode second: random){
                grid.addUndirectedEdge(first,second);
            }
        }
        return grid;
    }
    //More testing required
    static ArrayList<GridNode> aStar(GridNode sourceNode, GridNode destNode){
        ArrayList<GridNode> aStarPath = new ArrayList<>();
        //HashMap<GridNode, PairsOfDistances> myMap = new HashMap<>();
        Queue<GridNode> shortestMove = new PriorityQueue<>();

        int heuristic = heuristicManhattan(sourceNode, destNode);
        PairsOfDistances pair = new PairsOfDistances(0, heuristic);
        sourceNode.setPairs(pair);
        aStarPath.add(sourceNode);
        GridNode curr = sourceNode;
        //run till we dont reach our dest node
        while(curr != destNode){
            //finalize curr
            curr.isVisited = true;
            //iterate over all of curr's neighboors
            for(GridNode neighboor: curr.neighbors){
                //if neighbr not finalized
                if(!(neighboor.isVisited)){
                    int neighborHeuristic = heuristicManhattan(neighboor, destNode);
                    int distance = curr.pairs.getDistSoFar() + calculateDistance(curr,neighboor);
                    //if not in map add it
                    if(neighboor.pairs == null){
                        neighboor.parent = curr;
                        neighboor.pairs = new PairsOfDistances(distance, neighborHeuristic);
                        shortestMove.add(neighboor);
                    }
                    //if we have seen check its distance and set g+h 
                    if(neighboor.pairs != null){
                      if(distance < neighboor.pairs.getDistSoFar()){
                            shortestMove.remove(neighboor);
                            neighboor.parent = curr;
                            neighboor.pairs.setDistanceSoFar(distance);
                            neighboor.pairs.setDistPlsHeurstc();
                            shortestMove.add(neighboor);
                      }
                    }
                }
            }
            curr = shortestMove.poll();
            //System.out.println();
            aStarPath.add(curr);
        }
        return aStarPath;
    }

    static int heuristicManhattan(GridNode currentSqr, GridNode goal){
        int manhattanDistance = Math.abs(currentSqr.x - goal.x) + Math.abs(currentSqr.y - goal.y);
        return manhattanDistance;
    }

    static int calculateDistance(GridNode curr, GridNode nextDoorNeigbhor){
        int distance = Math.abs(nextDoorNeigbhor.x - curr.x) + Math.abs(nextDoorNeigbhor.y - curr.y);
        return distance;
    }

    
}