package cpsc433;

import java.util.ArrayList;
import java.util.Arrays;

public class OTreeNode implements Comparable<OTreeNode> {
    private ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    private ArrayList<Room> remaining = new ArrayList<Room>();
    
    private Environment env;
    
    private float utility = -1;
    
    //create root node
    public OTreeNode(Environment env) {
        this.env = env;
        
        //All the rooms are added to the remaining list
        ArrayList<Room> rooms = env.getRooms();
        for (int i = 0; i < rooms.size(); i++)
        {
        	remaining.add(rooms.get(i));
        }
    }
    
    //Create children node
    public OTreeNode(Environment env, ArrayList<Tuple> assignment, ArrayList<Room> remaining) {
        this.env = env;
        this.assignment = assignment;
        this.remaining = remaining;
    }
    
    public OTreeNode[] Branch() {
        OTreeNode[] children = new OTreeNode[remaining.size()];
        
        if (remaining.size() == 1) {
            int childPath[] = Arrays.copyOf(path, path.length + 2);
            childPath[childPath.length - 2] = remaining[0];
            childPath[childPath.length - 1] = 0;
            
            children[0] = new OTreeNode(env, childPath, new int[0]);
        } else {
            for (int i = 0; i < remaining.length; i++) {
                int childPath[] = Arrays.copyOf(path, path.length + 1);
                childPath[childPath.length - 1] = remaining[i];
                
                int childRemaining[] = new int[remaining.length - 1];
                for (int j = 0, k = 0; j < remaining.length; j++) {
                    if (j != i) {
                        childRemaining[k++] = remaining[j];
                    }
                }
                
                children[i] = new OTreeNode(env, childPath, childRemaining);
            }
        }
        
        return children;
    }
    
    private float getPathUtility() {
        if (pathUtility < 0) {
            pathUtility = 0;
            
            for (int i = 0; i < path.length - 1; i++) {
                pathUtility += env.getDist(path[i], path[i+1]);
            }
        }
        
        return pathUtility;
    }
    
    public float getUtility() {
        float utility = getPathUtility();
        
        if (remaining.length > 0) {
            utility += env.getAvgDist() * 0.4 * (remaining.length + 1);
        }
        
        return utility;
    }
    
    public boolean isComplete() {
        return remaining.length == 0;
    }
    
    public int getPathLength() {
        return path.length;
    }
    
    public int getPathPoint(int i) {
        return path[i];
    }
    
    @Override
    public int compareTo(OTreeNode other) {
        return Float.compare(getUtility(), other.getUtility());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        
        if (obj.getClass() != getClass()) return false;
        
        OTreeNode otherNode = (OTreeNode) obj;
        
        if (otherNode.getPathLength() != getPathLength()) return false;
        
        for (int i = 0; i < path.length; i++) {
            if (path[i] != otherNode.getPathPoint(i)) return false;
        }
        
        return true;
    }
    
    public String toString() {
        String retStr = "P: <";
        
        for (int i = 0; i < path.length; i++)
            retStr += (i != 0 ? "," : "") + path[i];
        
        retStr += ">, R: {";
        
        for (int i = 0; i < remaining.length; i++) 
            retStr += (i != 0 ? "," : "" ) + remaining[i];
        
        retStr += "}";
        
        return retStr;
    }
}
