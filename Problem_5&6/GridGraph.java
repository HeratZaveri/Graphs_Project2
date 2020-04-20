import java.util.*;

class GridNode{
    public int x;
    public int y;
    //public int data;
    boolean isVisited;
    List<GridNode> neighbors;

    public GridNode(int x, int y){
        this.x = x;
        this.y = y;
        this.isVisited = false;
        //this.data = data;
        this.neighbors = new ArrayList<>();
    }
}

class GridGraph{
    ArrayList<GridNode> maze;

    public GridGraph(){
        this.maze = new ArrayList<>();
    }

    void addGridNode(int x, int y){
         GridNode value = new GridNode(x, y);
         maze.add(value);
    }

    void addUndirectedEdge(GridNode first, GridNode second){
        if(validDirection(first, second) && notTheSame(first, second) && !(nextDoor(first, second))){
            first.neighbors.add(second);
            second.neighbors.add(first);
        }
    }

    void removeUndirectedEdge(GridNode first, GridNode second){
        if(notTheSame(first, second) && nextDoor(first, second)){
            first.neighbors.remove(second);
            second.neighbors.remove(first);
        }

    }

    HashSet<GridNode> getAllNodes(){
        HashSet<GridNode> mySet = new HashSet<>();
        for(GridNode nodeVal: maze){
            if(!(mySet.contains(nodeVal))){
                    mySet.add(nodeVal);
            }
        }
        return mySet;

    }
    boolean nextDoor(GridNode first, GridNode second){
        if(first.neighbors.contains(second) && second.neighbors.contains(first)){
            return true;
        }
        return false;
    }
    boolean notTheSame(GridNode input1, GridNode input2){
        if((input1.x == input2.x) && (input1.y == input2.y)){
            return false;
        }
        return true;
    }
    boolean validDirection(GridNode node1, GridNode node2){
        //up - same row different colum
        if(node1.y - 1 == node2.y && node1.x == node2.x){
            return true;
        }
        
        //down
        if(node1.y + 1 == node2.y && node1.x == node2.x){
            return true;
        }

        //left
        if(node1.x - 1 == node2.x && node1.y == node2.y){
            return true;
        }

        //right
        if(node1.x + 1 == node2.x && node1.y == node2.y){
            return true;
        }

        return false;
    }

    GridNode getNodeAtIndx(int i){
        return maze.get(i);
    }

}