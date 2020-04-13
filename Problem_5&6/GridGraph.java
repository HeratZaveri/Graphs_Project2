import java.util.*;

class GridNode{
    public int x;
    public int y;
    public int data;
    List<GridNode> neighbors;

    public GridNode(int x, int y, int data){
        this.x = x;
        this.y = y;
        this.data = data;
        this.neighbors = new ArrayList<>();
    }
}

class GridGraph{
    ArrayList<GridNode> maze;

    public GridGraph(){
        this.maze = new ArrayList<>();
    }

    void addGridNode(int x, int y, int nodeVal){
         GridNode value = new GridNode(x, y, nodeVal);
         maze.add(value);
    }

    void addUndirectedEdge(GridNode first, GridNode second){
        if(validDirection(first, second) && notTheSame(first, second) && !(nextDoor(first, second))){
            first.neighbors.add(second);
            second.neighbors.add(second);
        }
    }

    void removeUndirectedEdge(GridNode first, GridNode second){

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
        if(input1.data == input2.data){
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
}