import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args){
    
    GridGraph grid = createRandomGridGraph(100);
    //GridGraph grid = createRandomGridGraph(10);
    //grid.printGrid();

    //System.out.println();
    GridNode source = grid.getNodeAtIndx(0);
    System.out.println("Source Node: " + "("+ source.x + "," + source.y+") ");
    GridNode dest = grid.getNodeAtIndx(grid.maze.size()-1);
    System.out.println("Dest Node: " + "("+ dest.x + "," + dest.y +") ");
    System.out.println("Problem 6c A* Path: ");
    ArrayList<GridNode> star = aStar(source, dest);
    System.out.println();
    System.out.print("[ ");
    for (GridNode node: star){
        System.out.print("("+node.x + "," + node.y+") ");
    }
    System.out.print("]");
    //System.out.println(star);
    
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
        //aStarPath.add(sourceNode);
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
            //aStarPath.add(curr);
        }
        aStarPath = pathTaken(curr);
        return aStarPath;
    }

    static ArrayList<GridNode> pathTaken(GridNode dest){
        ArrayList<GridNode> lst = new ArrayList<>();
        GridNode curr = dest;
        while(curr != null){
            lst.add(curr);
            curr = curr.parent;
        }
        Collections.reverse(lst);
        return lst;
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