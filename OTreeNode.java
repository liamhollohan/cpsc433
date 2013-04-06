package cpsc433;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OTreeNode implements Comparable<OTreeNode> {
    private ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    private ArrayList<Room> remainingRooms = new ArrayList<Room>();
    private ArrayList<Person> remainingPeople = new ArrayList<Person>();
    private Environment env;
    
    private float assignmentUtility = -1;
    
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
        ArrayList<Tuple> childAssignment = null;
        
        //If there is only one room remaining
        if (remainingRooms.size() == 1) {
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
        } 
        else //there is more than one room left to assign
        {
            for (int i = 0; i < remainingRooms.size(); i++) 
            {
            	//Copy over parent assignment to child
                for (int j = 0; j < assignment.size(); j++)
            	{
            		childAssignment.add(assignment.get(j));
            	}
                //Choose a random person to assign to the room.
                Random rand = new Random();
                int x = rand.nextInt() % remainingPeople.size();
            	Tuple t = new Tuple(remainingRooms.get(i),remainingPeople.get(x));
                childAssignment.add(t);
                remainingPeople.remove(x);

                if (remainingRooms.get(i).isFull())
                	remainingRooms.remove(i);

                children[i] = new OTreeNode(env, childAssignment, remainingRooms, remainingPeople);
            }
        }
        
        return children;
    }
    
    private float getPathUtility() {
        if (assignmentUtility < 0) {
            assignmentUtility = 0;
        }
        
        return assignmentUtility;
    }
    
    public float getUtility() {
        float utility = getPathUtility();
        
        //Predict the future utility
        /*if (remaining.length > 0) {
            utility += env.getAvgDist() * 0.4 * (remaining.length + 1);
        }*/
        
        return utility;
    }
    
    public boolean isComplete() {
        return remainingRooms.size() == 0 && remainingPeople.size() == 0;
    }
    
   /* public int getPathLength() {
        return path.length;
    }*/
    
    /*public int getPathPoint(int i) {
        return path[i];
    }*/
    
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
        
        //if (otherNode.getPathLength() != getPathLength()) return false;
        
        for (int i = 0; i < path.length; i++) {
            if (path[i] != otherNode.getPathPoint(i)) return false;
        }
        
        return true;
    }
    
    public String toString() {
        String retStr = "A: <";
        
        for (int i = 0; i < assignment.size(); i++)
            retStr += (i != 0 ? "," : "") + assignment.get(i);
        
        retStr += ">, R-rooms: {";
        
        for (int i = 0; i < remainingRooms.size(); i++) 
            retStr += (i != 0 ? "," : "" ) + remainingRooms.get(i);
        
        
        retStr += "}, R-people: {";
        
        for (int i = 0; i < remainingPeople.size(); i++) 
            retStr += (i != 0 ? "," : "" ) + remainingPeople.get(i);
        
        retStr += "}";
        
        return retStr;
    }
}
