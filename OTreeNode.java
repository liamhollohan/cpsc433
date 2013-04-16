package cpsc433;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class defines the structure of a node in the or tree. It contains
 * all the information stored in the node including a list of remaining
 * people, remaining rooms, and assignment.
 * @author Liam Hollohan with help from Andrew Kuipers
 *
 */
public class OTreeNode implements Comparable<OTreeNode> {
	//Lists included in the node
    public ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    public ArrayList<Room> remainingRooms = new ArrayList<Room>();
    public ArrayList<Person> remainingPeople = new ArrayList<Person>();
    private Environment env;
    
    private int utility; //computed utility
    private int projectedUtility; //projected utility if it isn't a leaf node
    
    //used to calculate the projected utility of the node (calculateProjectedUtility)
    private final float MODIFIER = 0.35f;
    
    //Constructor for Root Node of the Or Tree
    public OTreeNode(Environment env) {
        
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
        
        //Utility at root node = 0;
        utility = 0;
    }
    
    //Constructor for every subsequent child node of the Or Tree
    public OTreeNode(Environment env, ArrayList<Tuple> assignment, ArrayList<Room> remainingRooms, ArrayList<Person> remainingPeople, int utility, int projectedUtility) {
        this.env = env;
        this.assignment = assignment;
        this.remainingRooms = remainingRooms;
        this.remainingPeople = remainingPeople;
        this.utility = utility;
        this.projectedUtility = projectedUtility;
        //System.out.println(this.toString());
    }
    
    /**
     * This function creates a list of children nodes to be added to the tree
     * under the current parent node (this). It will only create the child 
     * node if the hard constraints are met. We loop through all of the
     * remaining rooms and assign a randomly chosen person to each room.
     * @return children - list of children connected to this node.
     */
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
            
            //Assign the selected to person to the room
            Tuple t = new Tuple(childRemainingRooms.get(i),p);
            childAssignment.add(t);

            //If the room is now full, remove the room from the remainingRooms list.
            if (checkFullRoom(childRemainingRooms.get(i), childAssignment))
            	childRemainingRooms.remove(i);
            
            //Calculate both forms of the utility for the new child
            int childUtility = calculateUtility(childAssignment);
            int childProjectedUtility = calculateProjectedUtility(childUtility, childAssignment);
        	childRemainingPeople.remove(p);

        	//Add the child to list of children nodes
        	children[i] = new OTreeNode(env, childAssignment, childRemainingRooms, childRemainingPeople, childUtility, childProjectedUtility);
        }
        return children;
    }
    
    /**
     * This method simply checks if the given room is full in the given assignment.
     * @param room
     * @param childAssignment
     * @return whether or not the room is full
     */
    private boolean checkFullRoom(Room room, ArrayList<Tuple> childAssignment) {
    	int peopleCount = 0;
    	for (int i = 0; i < childAssignment.size(); i++)
    	{
    		if (childAssignment.get(i).getRoom().equals(room))
    		{
	    		peopleCount++;
	    		//check if the person requires their own room
	    		if (childAssignment.get(i).getPerson().getManager() || !childAssignment.get(i).getPerson().getGroupHead().equals("None") || !childAssignment.get(i).getPerson().getProjectHead().equals("None")) 
	    			return true;
	    		//check if there are 2 people in the room already
	    		else if (peopleCount >= 2)
	    			return true;
    		}
    	}
		return false;
	}

    /**
     * This method selects a random person to be assigned to the rooms of
     * the child.
     * @return p - Person to assign to the rooms
     */
	private Person randomPerson()
    {
    	int x = 0;
        Person p;
    	if (remainingPeople.size() > 1) {
        	//Choose a random person to assign to the room.
        	Random rand = new Random();
        	x = Math.abs(rand.nextInt() % remainingPeople.size());
        	p = remainingPeople.get(x);
        } else {
        	//Select the last remaining person to assign to the room
        	p = remainingPeople.get(0);
        }
    	return p;
    }
    
	/**
	 * This method checks all the soft constraints and accumulates the penalties
	 * associated with that assignment.
	 * @param assignment
	 * @return totalUtility - the total amount of penalties assigned to the assignment
	 */
	public int calculateUtility(ArrayList<Tuple> assignment)
	{
		int totalUtility = 0;
		for(int i = 0; i < assignment.size(); i++)
		{
			Tuple assigned = assignment.get(i);
			//Check constraints for group heads.
			if(!assignment.get(i).getPerson().getGroupHead().equals("none"))
			{
				//Check all soft constraint violations that have to do with group heads
				totalUtility += Solution.checkGroupHeadLarge(assigned); // 1
				totalUtility += Solution.checkGroupHeadsClose(assigned, assignment); // 2
				totalUtility += Solution.checkSecretaryClose(assigned, assignment); // 3
			}
			if(assignment.get(i).getPerson().getSecretary())
			{
				//Check soft constraint violations that have to do with secretarys
				totalUtility += Solution.checkSecretariesTogether(assigned, assignment); // 4
			}
			if(assignment.get(i).getPerson().getManager())
			{
				//Chaeck all manager related soft constraints
				totalUtility += Solution.checkManagerCloseSecretary(assigned, assignment); // 5
				totalUtility += Solution.checkManagerCloseGroupHead(assigned, assignment); // 6
				totalUtility += Solution.checkManagerCloseAll(assigned, assignment); // 7
			}
			if(!assignment.get(i).getPerson().getProjectHead().equals("none"))
			{
				//Chaeck all project head related soft constraints
				totalUtility += Solution.checkHeadProjClose2All(assigned, assignment); // 8
				totalUtility += Solution.checkLargeProjectHeadCloseSecretary(assigned, assignment, env.getProjects()); // 9
				totalUtility += Solution.checkProjHeadCloseGroupHead(assigned, assignment, env.getProjects());// 10
			}
			if(assignment.get(i).getPerson().getSmoker())
			{
				//Check smoker related soft constraints
				totalUtility += Solution.checkSmokersTogether(assigned, assignment); // 11
			}
			//Check the rest of the soft constraints
			totalUtility += Solution.checkSameProjectTogether(assigned, assignment); // 12
			totalUtility += Solution.checkHackersShare(assigned, assignment); // 13
			totalUtility += Solution.checkShareOffice(assigned, assignment); // 14
			totalUtility += Solution.checkWorkTogether(assigned, assignment); // 15
			totalUtility += Solution.checkShareSmall(assigned, assignment); // 16	
		}
		//System.out.println("TOTAL == " + totalUtility);
		return totalUtility;
	}
	
	/**
	 * This method calculates what it predicts to be the final utility at the
	 * leaf node of the tree.
	 * @param childAssignment
	 * @return childUtility - the total pojected utility for a given node in the tree
	 */
    public int calculateProjectedUtility(ArrayList<Tuple> childAssignment) {
    	//Get the actual utility
        int childUtility = calculateUtility(childAssignment);
        
        //If the size is greater than 0 eople left to assign, calculate the total projected utility
        if (remainingPeople.size() > 0 && childAssignment.size() != 0) {
        	//Calculate the projected utility of a given node given the amount of people yet to assign and the actual utility
            childUtility += childUtility/childAssignment.size() * MODIFIER * (remainingPeople.size() + 1);
        }
        return childUtility;
    }

    
    public int calculateProjectedUtility(int utility, ArrayList<Tuple> childAssignment)
    {
        if (remainingPeople.size() > 0 && childAssignment.size() != 0) {
            utility += utility/childAssignment.size() * MODIFIER * (remainingPeople.size() + 1);
        }
    	return 0;
    }
    
    public int getProjectedUtility()
    {
    	return projectedUtility;
    }
    
    public int getUtility()
    {
    	return utility;
    }
    
    public boolean isComplete() {
    	return remainingPeople.size() == 0;
    }
    
    @Override
    public int compareTo(OTreeNode other) {
        return Integer.compare(getProjectedUtility(), other.getProjectedUtility());
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
        
        ArrayList<Tuple> otherAssignment = otherNode.assignment;
        
        if (otherAssignment.size() != assignment.size())
        	return false;
        
        for (int i = 0; i < assignment.size(); i++)
        {
        	if (assignment.get(i).equals(otherAssignment.get(i)))
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

        retStr += " | " + utility;
        
        return retStr;
    }
}
