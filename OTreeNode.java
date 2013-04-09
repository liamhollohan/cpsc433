package cpsc433;

import java.util.ArrayList;
import java.util.Random;


public class OTreeNode implements Comparable<OTreeNode> {
    public ArrayList<Tuple> assignment = new ArrayList<Tuple>();
    public ArrayList<Room> remainingRooms = new ArrayList<Room>();
    public ArrayList<Person> remainingPeople = new ArrayList<Person>();
    private Environment env;
    
    int utility = 0;
    
    //create root node
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
        utility = 0;
    }
    
    //Create children node
    public OTreeNode(Environment env, ArrayList<Tuple> assignment, ArrayList<Room> remainingRooms, ArrayList<Person> remainingPeople, int utility) {
        this.env = env;
        this.assignment = assignment;
        this.remainingRooms = remainingRooms;
        this.remainingPeople = remainingPeople;
        this.utility = utility;
        //System.out.println(this.toString());
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

            if (checkFullRoom(childRemainingRooms.get(i), childAssignment))
            	childRemainingRooms.remove(i);
            
            int childUtility = getProjectedUtility(childAssignment);
            //System.out.println("childUtility = " + childUtility);
        	childRemainingPeople.remove(p);
            children[i] = new OTreeNode(env, childAssignment, childRemainingRooms, childRemainingPeople, childUtility);
        }
        return children;
    }
    
    //Double check this method
    private boolean checkFullRoom(Room room, ArrayList<Tuple> childAssignment) {
    	int peopleCount = 0;
    	for (int i = 0; i < childAssignment.size(); i++)
    	{
    		if (childAssignment.get(i).getRoom().equals(room))
    		{
	    		peopleCount++;
	    		if (childAssignment.get(i).getPerson().getManager() || !childAssignment.get(i).getPerson().getGroupHead().equals("None") || !childAssignment.get(i).getPerson().getProjectHead().equals("None")) 
	    			return true;
	    		else if (peopleCount >= 2)
	    			return true;
    		}
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
    
	//Get Actual Utility
	public int getUtility(ArrayList<Tuple> assignment)
	{
		int totalUtility = 0;
		for(int i = 0; i < assignment.size(); i++)
		{
			Tuple assigned = assignment.get(i);
			//Check constraints for group heads.
			if(!assignment.get(i).getPerson().getGroupHead().equals("none"))
			{
				
				totalUtility += Solution.checkGroupHeadLarge(assigned); // 1
				totalUtility += Solution.checkGroupHeadsClose(assigned, assignment); // 2
				totalUtility += Solution.checkSecretaryClose(assigned, assignment); // 3
			}
			if(assignment.get(i).getPerson().getSecretary())
			{
				totalUtility += Solution.checkSecretariesTogether(assigned, assignment); // 4
			}
			if(assignment.get(i).getPerson().getManager())
			{
				totalUtility += Solution.checkManagerCloseSecretary(assigned, assignment); // 5
				totalUtility += Solution.checkManagerCloseGroupHead(assigned, assignment); // 6
				totalUtility += Solution.checkManagerCloseAll(assigned, assignment); // 7
			}
			if(!assignment.get(i).getPerson().getProjectHead().equals("none"))
			{
				totalUtility += Solution.checkHeadProjClose2All(assigned, assignment); // 8
				totalUtility += Solution.checkLargeProjectHeadCloseSecretary(assigned, assignment, env.getProjects()); // 9
				totalUtility += Solution.checkProjHeadCloseGroupHead(assigned, assignment, env.getProjects());// 10
			}
			if(assignment.get(i).getPerson().getSmoker())
			{
				totalUtility += Solution.checkSmokersTogether(assigned, assignment); // 11
			}
			totalUtility += Solution.checkSameProjectTogether(assigned, assignment); // 12
			totalUtility += Solution.checkHackersShare(assigned, assignment); // 13
			totalUtility += Solution.checkShareOffice(assigned, assignment); // 14
			totalUtility += Solution.checkWorkTogether(assigned, assignment); // 15
			totalUtility += Solution.checkShareSmall(assigned, assignment); // 16	
		}
		//System.out.println("TOTAL == " + totalUtility);
		return totalUtility;
	}
	
    public int getProjectedUtility(ArrayList<Tuple> childAssignment) {
        int utility = getUtility(childAssignment);
        
        if (remainingPeople.size() > 0 && childAssignment.size() != 0) {
            utility += utility/childAssignment.size() * 0.4 * (remainingPeople.size() + 1);
        }
        
        return utility;
    }
    
    public boolean isComplete() {
    	return remainingPeople.size() == 0;
    }
    
    @Override
    public int compareTo(OTreeNode other) {
        return Integer.compare(getUtility(assignment), other.getUtility(other.assignment));
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
        ArrayList<Room> otherRemainingRooms = otherNode.remainingRooms;
        
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
        ArrayList<Person> otherRemainingPeople = otherNode.remainingPeople;
        
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

        retStr += " | " + utility;
        
        return retStr;
    }
}
