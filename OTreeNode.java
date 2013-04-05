package cpsc433;

import java.util.ArrayList;
import java.util.Arrays;

public class OTreeNode implements Comparable<OTreeNode> {
    private ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    private ArrayList<Room> remainingRooms = new ArrayList<Room>();
    private ArrayList<Person> remainingPeople = new ArrayList<Person>();
    private Environment env;
    
    private float utility = -1;
    
    //create root node
    public OTreeNode(Environment env) {
        this.env = env;
        
        //All the rooms are added to the remainingRoom list
        ArrayList<Room> rooms = env.getRooms();
        for (int i = 0; i < rooms.size(); i++)
        	remainingRooms.add(rooms.get(i));
        
        //All the people are added to the remainingPeople list
        ArrayList<Person> people = env.getPeople();
        for (int i = 0; i < people.size(); i++)
        	remainingPeople.add(people.get(i));
    }
    
    //Create children node
    public OTreeNode(Environment env, ArrayList<Tuple> assignment, ArrayList<Room> remainingRooms, ArrayList<Person> remainingPeople) {
        this.env = env;
        this.assignment = assignment;
        this.remainingRooms = remainingRooms;
        this.remainingPeople = remainingPeople;
    }
    
    public OTreeNode[] Branch() {
        OTreeNode[] children = new OTreeNode[remainingRooms.size()];
        
        //If there is only one room remaining
        if (remainingRooms.size() == 1) {
        	ArrayList<Tuple> childAssignment;
        	//copy the previously computed assignments into the new branch
        	for (int i = 0; i < assignment.size(); i++)
        	{
        		childAssignment.add(assignment.get(i));
        	}
        	//add the remaining people to the one remaining room
        	for (int i = 0; i < remainingPeople.size(); i++)
        	{
        		Tuple t = new Tuple(remainingRooms.get(0), remainingPeople.get(i));
        		remainingPeople.remove(i);
        		childAssignment.add(t);
        	}
            remainingRooms.remove(0);
            //Error Checking: Empty remainingRooms and remainingPeople lists
            if (!remainingRooms.isEmpty() || !remainingPeople.isEmpty())
            	System.out.println(" -> Error: Final solution is not removing final element properly.");
            
            children[0] = new OTreeNode(env, childAssignment, remainingRooms, remainingPeople);
        } else {
            for (int i = 0; i < remainingRooms.size(); i++) {
                ArrayList<Tuple> childAssignment;
            	for (int j = 0; j < assignment.size(); j++)
            	{
            		childAssignment.add(assignment.get(j));
            	}
            	Tuple t = new Tuple(remainingRooms.get(0));
                childAssignment.add();
                
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
