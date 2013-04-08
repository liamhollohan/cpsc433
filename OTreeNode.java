package cpsc433;

import java.util.ArrayList;
import java.util.Random;


public class OTreeNode implements Comparable<OTreeNode> {
    public ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    public ArrayList<Room> remainingRooms = new ArrayList<Room>();
    public ArrayList<Person> remainingPeople = new ArrayList<Person>();
    private Environment env;
    
    private int utility = 0;
    
    //create root node
    public OTreeNode(Environment env) {
        
    	System.out.println(" -> Creating the root node!");
    	
    	this.env = env;
        
        //All the rooms are added to the remainingRoom list
        ArrayList<Room> rooms = env.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
        	remainingRooms.add(rooms.get(i));
        }
        
        //All the people are added to the remainingPeople list
        ArrayList<Person> people = env.getPeople();
        for (int i = 0; i < people.size(); i++) {
        	remainingPeople.add(people.get(i));
        }
        utility = 0;
    }
    
    //Create children node
    public OTreeNode(Environment env, ArrayList<Tuple> assignment, ArrayList<Room> remainingRooms, ArrayList<Person> remainingPeople, int utility) {
        this.env = env;
        this.assignment = assignment;
        this.remainingRooms = remainingRooms;
        this.remainingPeople = remainingPeople;
        this.utility = utility;
        System.out.println(this.toString());
    }
    
    public OTreeNode[] Branch() 
    {
        //create a new array of children (One child per room remaining)
    	OTreeNode[] children = new OTreeNode[remainingRooms.size()];
    	Person p = randomPerson();
    	//loop through to create a new branch for every room
    	for (int i = 0; i < remainingRooms.size(); i++) 
        {
    		ArrayList<Tuple> childAssignment = new ArrayList<Tuple>();
    		ArrayList<Room> childRemainingRooms = new ArrayList<Room>();
    		ArrayList<Person> childRemainingPeople = new ArrayList<Person>();
    		//System.out.print(" -> Branch: ");
        	//Copy over parent assignment to child
            for (int j = 0; j < assignment.size(); j++) {
        		childAssignment.add(assignment.get(j));
        	}
        	//Copy over parent remainingRooms to child
            for (int j = 0; j < remainingRooms.size(); j++) {
        		childRemainingRooms.add(remainingRooms.get(j));
        	}            	
            //Copy over parent remainingPeople to child
            for (int j = 0; j < remainingPeople.size(); j++) {
        		childRemainingPeople.add(remainingPeople.get(j));
        	}
            
            Tuple t = new Tuple(childRemainingRooms.get(i),p);
            childAssignment.add(t);

            boolean isFull = checkFullRoom(childRemainingRooms.get(i), p);
            
            childRemainingRooms.get(i).setNumAssigned(childRemainingRooms.get(i).getNumAssigned()+1);
            
            if (isFull)
            	childRemainingRooms.remove(i);

            
            int childUtility = utility;
            //Fake utility generator
        	if (utility == 0) {
                Random rand = new Random();
            	childUtility = Math.abs(rand.nextInt(100));
        	} else {
                Random rand = new Random();
            	childUtility += Math.abs(rand.nextInt(10));
        	}
        	childRemainingPeople.remove(p);
            children[i] = new OTreeNode(env, childAssignment, childRemainingRooms, childRemainingPeople, childUtility);
        }
        return children;
    }
    
    //Double check this method
    private boolean checkFullRoom(Room room, Person p) {
    	//int numAssigned = room.getNumAssigned();
    	//room.setNumAssigned(numAssigned+1);
    	//check if a manager is assigned to that room
    	for (int i = 0; i < assignment.size(); i++)
    	{
    		if (assignment.get(i).getRoom().equals(room) && assignment.get(i).getPerson().getManager())
    			return true; 
    		else if (assignment.get(i).getRoom().getNumAssigned() > 1)
    			return true;
    		else if (p.getManager())
    			return true;
    	}
		return false;
	}

	private Person randomPerson()
    {
    	//choose a random remainingPerson to assign to a room
    	int x = 0;
        Person p;
    	if (remainingPeople.size() > 1) {
        	//Choose a random person to assign to the room.
        	Random rand = new Random();
        	x = Math.abs(rand.nextInt() % remainingPeople.size());
        	p = remainingPeople.get(x);
        	//remainingPeople.remove(x);
        } else {
        	p = remainingPeople.get(0);
        	//remainingPeople.remove(0);
        }
    	return p;
    }
    
    public int getUtility() {
    	return utility;
    }
    
    public boolean isComplete() {
    	return remainingPeople.size() == 0;
    }
    
    public ArrayList<Room> getRemainingRooms() {
    	return remainingRooms;
    }
    
    public ArrayList<Person> getRemainingPeople() {
    	return remainingPeople;
    }
    
    @Override
    public int compareTo(OTreeNode other) {
        return Float.compare(getUtility(), other.getUtility());
    }
    
    /**
     * @param : obj (OTreeNode object that will be compared with this instance of OTreeNode
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        
        if (obj.getClass() != getClass()) return false;
        
        OTreeNode otherNode = (OTreeNode) obj;
        
        //if (otherNode.getPathLength() != getPathLength()) return false;
        
        //Get the list of remaining rooms from the other node
        ArrayList<Room> otherRemainingRooms = otherNode.getRemainingRooms();
        
        //check to make sure their sizes are the same
        if (otherRemainingRooms.size() != remainingRooms.size())
        	return false;

        //loop through the entire list to make sure every element of the list is the same
        for (int i = 0; i < remainingRooms.size(); i++)
        {
        	if (remainingRooms.get(i) != otherRemainingRooms.get(i))
        		return false;
        }
        
        //Get the list of remaining people from the other node
        ArrayList<Person> otherRemainingPeople = otherNode.getRemainingPeople();
        
        //Check to make sure their sizes are the same
        if (otherRemainingPeople.size() != remainingPeople.size())
        	return false;
        
        //Loop through the entire list to make sure every element of the list is the same
        for (int i = 0; i < remainingPeople.size(); i++)
        {
        	if (remainingPeople.get(i) != otherRemainingPeople.get(i))
        		return false;
        }
        
        return true;
    }
    
    public String toString() {
        String retStr = "A: <";
        
        for (int i = 0; i < assignment.size(); i++)
            retStr += (i != 0 ? "," : "") + "[" +assignment.get(i).getPerson().getName()+", " +assignment.get(i).getRoom().getName()+"]";
        
        retStr += ">, R-rooms: {";
        
        for (int i = 0; i < remainingRooms.size(); i++) 
            retStr += (i != 0 ? "," : "" ) + remainingRooms.get(i).getName();
        
        
        retStr += "}, R-people: {";
        
        for (int i = 0; i < remainingPeople.size(); i++) 
            retStr += (i != 0 ? "," : "" ) + remainingPeople.get(i).getName();
        
        retStr += "}";

        retStr += " | " + getUtility();
        
        return retStr;
    }
}
