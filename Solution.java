package cpsc433;

import java.util.ArrayList;

public class Solution 
{
	public static Object verbosity;
	
	public Solution(String outfilename) {}

	//****************************************************************************************************************
	//Check two many checks through the list of assigned people and ensures that no more than two people are assigned*
	//to one room.  It will output an error if more than two people are assigned to a room.							 *
	//Returns a boolean stating if the test passed or not.															 *
	//****************************************************************************************************************
	public static boolean checkTwoMany(ArrayList<Tuple> assignment)
	{
		int count;
		String roomName;
		//Loop to check how many people are assigned to each room.
		for (int j = 0; j < assignment.size(); j++)
		{
			//Get the room name you will be checking for
			roomName = assignment.get(j).getRoom().getName();
			//Counter to keep track of how many people are assigned to the room
			count = 0;
			for(int i = j; i < assignment.size(); i++)
				//Check if the room matches the assignment pair and if so increment the counter
				if(assignment.get(i).getRoom().getName().equals(roomName))
					count++;			
			if (count > 2)
			{
				//If the count is greater than two, output the error message
				System.out.println(" -> Error: \"Two\" many people in a room");
				return false;
			}
		}
		return true;
	}
	
	//****************************************************************************************************************
	//CheckBigMoney is a function which checks through the list of assigned people and checks if any managers, group *
	//heads or project heads are sharing a room with anyone else.  If they are paired with another person, a hard    *
	//constraint is violated and the appropriate error message is output to the user.								 *
	//Returns a boolean stating if the test passed or failed.														 *
	//****************************************************************************************************************
	public static boolean checkBigMoney(ArrayList<Tuple> assignment)
	{
		//Boolean flag to indicate whether a manager, project head or group head is being checked
		boolean manager = false;
		for(int i = 0; i < assignment.size(); i++)
		{
			//Check if the person is a manager, group head or project head.
			if(assignment.get(i).getPerson().getManager() || !assignment.get(i).getPerson().getGroupHead().equals("None") || !assignment.get(i).getPerson().getProjectHead().equals("None"))
				manager = true;
			//If the person is a manager, check if they are sharing a room with anyone.
			if(manager)
			{
				String managerName = assignment.get(i).getPerson().getName();
				String roomNumber = assignment.get(i).getRoom().getName();				
				for(int j = 0; j < assignment.size(); j++)
				{
					//Check if the person is sharing a room and if so output the appropriate error.
					if(assignment.get(j).getRoom().getName().equals(roomNumber) && !assignment.get(j).getPerson().getName().equals(managerName))
					{
						System.out.println(" -> Error: Big Money No Share!");
						return false;
					}
				}
			}
			//Set the flag to false and chekc the next person.
			manager = false;
		}
		return true;
	}	
	
	//****************************************************************************************************************
	//Check multiple assignment and all assigned checks both if all people in the input file are assigned a room or  *
	//not and also checks if a person is assigned to multiple rooms.  Both of these conditions are hard constraints  *
	//that must not be violated and if they are the assignmetn fails and the appropriate error message is output to  *
	//the user.																										 *
	//Returns a boolean stating if the test passed or failed.														 *
	//****************************************************************************************************************
	public static boolean checkAssignment(ArrayList<Tuple> assignment, ArrayList<Person> people)
	{
		///Counter for how many times the person was assigned
		int count;
		String name2Check;
		for(int i = 0; i < people.size(); i++)
		{
			count = 0;
			name2Check = people.get(i).getName();
			//Loop to go through the assignment list and count the number of times the person is assigned
			for(int j = 0; j < assignment.size(); j++)
			{
				if(assignment.get(j).getPerson().getName().equals(name2Check))
					count++;
			}
			//Check if the count is greater than 1, meaning the person was assigned to more than one room
			if(count > 1)
			{
				System.out.println(" -> Error: Person Is Assigned To Multiple Rooms!");
				return false;
			}
			//checkAssignmentto see if the person was assigned to at least one room.
			if(count < 1)
			{
				System.out.println(" -> Error: Not Everyone Is Assigned A Room!");
				return false;
			}
		}
		return true;		
	}
	
	//****************************************************************************************************************
	//Checksolvable checks the given problem to see if it will be solvable given the number of rooms that the problem*
	//instance has specified.  It compares the number of rooms to the amount of people that can't share rooms vs the *
	//number of peole that can.																						 *
	//Returns a boolean stating if the problem is solvable or not.													 *
	//****************************************************************************************************************
	public static boolean checkSolvable(ArrayList<Person> people, ArrayList<Room> rooms)
	{
		int managerCount = 0;
		int otherPeople = 0;
		
		//loop to go through the list of people and count the number of managers, project heads and group heads as well as
		//the people who are not.
		for(int i = 0; i < people.size(); i++)
		{
			//If the person is a manger, group head or project head increment the manager count else increment the regular person count
			if(people.get(i).getManager() || !people.get(i).getGroupHead().equals("None") || !people.get(i).getProjectHead().equals("None"))
				managerCount++;
			else
				otherPeople++;
		}		
		//Check to see if the number of people given can fit the rooms specified without breaking any hard constraints
		if((rooms.size() - managerCount - ((otherPeople/2) + (otherPeople%2))) < 0)
		{
			System.out.println(" -> Error: Problem Is Unsolvable With The Provided Input!");
			return false;
		}
		return true;		
	}
	
	
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSolved() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getGoodness() {
		// TODO Auto-generated method stub
		return 0;
	}

	//****************************************************************************************************************
	//Below are various print functions for debugging purposes
	public static void printPeople(ArrayList<Person> people)
	{
		//Loop to go through and print all of the predicates to do ith people to the file.
		for(int i = 0;i < people.size(); i++)
		{
			SisyphusI.output.print("person(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a secretary and if so print the predicate to the file.
			if(people.get(i).getSecretary() == true)
				SisyphusI.output.print("secretary(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a researcher and if so print the predicate to the file.
			if(people.get(i).getResearcher() == true)
				SisyphusI.output.print("researcher(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a manager and if so print the predicate to the file.
			if(people.get(i).getManager() == true)
				SisyphusI.output.print("manager(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a smoker and if so print the predicate to the file.
			if(people.get(i).getSmoker() == true)
				SisyphusI.output.print("smoker(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a hacker and if so print the predicate to the file.
			if(people.get(i).getHacker() == true)
				SisyphusI.output.print("hacker(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is in a project and if they are print the person/project predicate to the file.
			if(!people.get(i).getProject().equals("None"))
				SisyphusI.output.print("project(" + people.get(i).getName() + ", " + people.get(i).getProject() + ")" + "\n");
			//Check if the person is in a group and if they are print the person/group predicate to the file.
			if(!people.get(i).getGroup().equals("None"))
				SisyphusI.output.print("group(" + people.get(i).getName() + ", " + people.get(i).getGroup() + ")" + "\n");
			//Check to see if the person works with anyone and if so print the worksWith predicate to the file.
			if(!people.get(i).getWorksWith().isEmpty())
			{
				SisyphusI.output.print("works-with(" + people.get(i).getName() + ", {");
				//Loop to print out the list of people the current person works with.
				int j = 0;
				for(j = 0; j < people.get(i).getWorksWith().size() - 1; j++)
				{
					SisyphusI.output.print(people.get(i).getWorksWith().get(j) + ", ");
				}
				SisyphusI.output.print(people.get(i).getWorksWith().get(j) + "})" + "\n");
			}
			//Check if the person is a project head and if they are print the person/project predicate to the file.
			if(!people.get(i).getProjectHead().equals("None"))
				SisyphusI.output.print("heads-project(" + people.get(i).getName() + ", " + people.get(i).getProjectHead() + ")" + "\n");
			//Check if the person is a group head and if they are print the person/group predicate to the file.
			if(!people.get(i).getGroupHead().equals("None"))
				SisyphusI.output.print("heads-group(" + people.get(i).getName() + ", " + people.get(i).getGroupHead() + ")" + "\n");
			SisyphusI.output.print("\n");
		}
	}
	
	public static void printRooms(ArrayList<Room> rooms)
	{
		//Loop to go through and print all of the room predicates to the file.
		for(int i = 0;i < rooms.size(); i++)
		{
			//Print the room name predicate.
			SisyphusI.output.print("room(" + rooms.get(i).getName() +")" + "\n");
			//Check the size of the room and print it.
			if(!rooms.get(i).getSize().equals("None"))
			{
				//Check if the room is a small room.
				if(rooms.get(i).getSize().equals("small"))
				{
					SisyphusI.output.print("small-room (" + rooms.get(i).getName() +")" + "\n");
				}
				//Check if the room is a medium room.
				if(rooms.get(i).getSize().equals("medium"))
				{
					SisyphusI.output.print("medium-room (" + rooms.get(i).getName() +")" + "\n");
				}
				//Check if the room is a large room.
				if(rooms.get(i).getSize().equals("large"))
				{
					SisyphusI.output.print("large-room (" + rooms.get(i).getName() +")" + "\n");
				}
			}
			//Check to see if the room is close to any other rooms and if so print out all of the close rooms.
			if(!rooms.get(i).getClose().isEmpty())
			{
				SisyphusI.output.print("close(" + rooms.get(i).getName() + ", {");
				//Loop to print out the list of people the current person works with.
				int j = 0;
				for(j = 0; j < rooms.get(i).getClose().size() - 1; j++)
				{
					SisyphusI.output.print(rooms.get(i).getClose().get(j) + ", ");
				}
				SisyphusI.output.print(rooms.get(i).getClose().get(j) + "})" + "\n");
				SisyphusI.output.print("\n");
			}
		}
	}
	
	public static void printGroups(ArrayList<Group> groups)
	{
		//Loop to go through and print all of the group predicates to the file.
		for(int i = 0;i < groups.size(); i++)
		{
			SisyphusI.output.print("\n");
			System.out.println(groups.size());
			//Print the group name predicate.
			SisyphusI.output.print("group(" + groups.get(i).getName() +")" + "\n");
		}
	}
	
	public static void printProjects(ArrayList<Project> projects)
	{
		//Loop to go through and print all of the project predicates to the file.
		for(int i = 0;i < projects.size(); i++)
		{
			SisyphusI.output.print("\n");

			//Print the group name predicate.
			SisyphusI.output.print("project(" + projects.get(i).getName() +")" + "\n");
			//Check to see if the group is a large group.
			if(projects.get(i).getLarge())
			{
				SisyphusI.output.print("large-project (" + projects.get(i).getName() +")" + "\n");
			}
		}
	}
	
	public static void printAssignment(ArrayList<Tuple> assignment) 
	{
		for(int i = 0; i < assignment.size(); i++)
		{
			SisyphusI.output.print("Room: " + assignment.get(i).getRoom().getName());
			SisyphusI.output.print("\n");
			SisyphusI.output.print("Person: " + assignment.get(i).getPerson().getName());
			SisyphusI.output.print("\n");
			SisyphusI.output.print("\n");
		}
	}
	//Above are various print functions for debugging purposes
	//****************************************************************************************************************

	//Soft constraint 1
	//****************************************************************************************************************
	//checkGroupHeadLarge checks if all group heads are assigned a large room.  A penalty of -40 is applied if they  *
	//are not assigned a large room.																				 *
	//****************************************************************************************************************
	static int checkGroupHeadLarge(Tuple assignment)
	{
		if(!assignment.getPerson().getGroupHead().equals("None") && !assignment.getRoom().getSize().equals("large"))
			return -40;
		
		return 0;
	}	
	
	//Soft constraint 2
	//****************************************************************************************************************
	//Check if the group head is close to all group members.  If not a penalty of -2 is assigned for each person who *
	//is not within close proximity.																				 *
	//****************************************************************************************************************
	static int checkGroupHeadsClose(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Total penalty calculated so far
		int totalPenalty = 0;
		//Check if the person to assign is a group head
		if(!assignment.getPerson().getGroupHead().equals("None"))
		{
			//Get the various attributes from the person in order to do the appropriate checks
			String group = assignment.getPerson().getGroupHead();
			String personName = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean roomClose = false;			
			//Check all of the assigned people to see if they are close to the group head.
			for(int i = 0; i < assignedPeople.size(); i++)
			{	
				//Check if the person is part of the same group and that you are not looking at the person
				if(assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(personName))
				{
					//Loop through the person to check close rooms and see if the group head will be close to them.
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							roomClose = true;
							break;
						}
					}
					//If the room wasn't found in the close list then increase the penalty.
					if(!roomClose)
						totalPenalty = totalPenalty + -2;					
				}
				roomClose = false;
			}
		}
		return totalPenalty;
	}
	
	//Soft constraint 3
	//****************************************************************************************************************
	//Check secretary close finds out if the group head is close to at least one secretary in the group.  If at least*
	//one secretary is not close a penalty of -30 will be returned.													 *
	//****************************************************************************************************************
	static int checkSecretaryClose(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Find out if the person being assigned is a group head
		if(!assignment.getPerson().getGroupHead().equals("None"))
		{
			//get the persons attributes used for the checks
			String group = assignment.getPerson().getGroup(); 
			String name = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean secretaryClose = false;
			//Loop lto go through the rest of the people and see if a secretary is assigned to a room close by that is in the same group
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				//Check if the person is a secretary and is in the same group.
				if(assignedPeople.get(i).getPerson().getSecretary() && assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(name))
				{
					//Loop to go through the close rooms list and see if the room that the secretary is in is within range
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							secretaryClose = true;
							break;
						}
					}					
				}				
			}
			//Return the penalty if no secretary was found that was within rnage of the group head.
			if(!secretaryClose)
				return -30;
		}
		return 0;
	}
	
	//Soft constraint 4
	//****************************************************************************************************************
	//Checks to see if secretaries are sharing an office or not.  If they are not, a penalty of -5 is to be returned *
	//****************************************************************************************************************
	static int checkSecretariesTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Get the room and name of the person being assigned to a room.
		String room = assignment.getRoom().getName();
		String person = assignment.getPerson().getName();
		
		//Loop through the assigned people checking if they share a room with the person being assigned.
		for(int i = 0; i < assignedPeople.size(); i++)
		{
			//If the person found is sharing a room with the person being assigned and is not a secretary, assign the penalty.
			if(assignedPeople.get(i).getRoom().getName().equals(room) && (!assignedPeople.get(i).getPerson().getName().equals(person) && !assignedPeople.get(i).getPerson().getSecretary()))
				return -5;
		}
		return 0;		
	}
	
	//Soft constraint 5
	//****************************************************************************************************************
	//Check Manager close finds out if the manager is close to at least one secretary in the group.  If at least	 *
	//one secretary is not close a penalty of -20 will be returned.													 *
	//****************************************************************************************************************
	static int checkManagerCloseSecretary(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Find out if the person is a manager or not
		if(assignment.getPerson().getManager())
		{
			//Get the various attributes needed to check if so.
			String group = assignment.getPerson().getGroup();
			String name = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean secretaryClose = false;
			//Lop through the assigned people looking for a secretary
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				//Check if the current person is a secretary and if so see if they are in a room close to the manager
				if(assignedPeople.get(i).getPerson().getSecretary() && assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(name))
				{
					//Loop to go through the close rooms list and check if the room is contained in the list
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							secretaryClose = true;
							break;
						}
					}					
				}				
			}
			//If the secretary is not close return the penalty
			if(!secretaryClose)
				return -20;
		}
		return 0;
	}
	
	//Soft constraint 6
	//****************************************************************************************************************
	//CheckManagerCloseGroupHead checks to see if the manager of the group is close to the group head of the given   *
	//group.  If they are not, return a penalty of -20.																 *
	//****************************************************************************************************************
	static int checkManagerCloseGroupHead(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Check if the person is a manager or not
		if(assignment.getPerson().getManager())
		{
			//get the persons attributes needed for checking purposes
			String group = assignment.getPerson().getGroup();
			String name = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean groupHeadClose = false;
			//Loop through the people assigned looking for the group head
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				//If the person is the group head of the group that the manager is in, check if there room is in close proximity
				if(assignedPeople.get(i).getPerson().getGroupHead().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(name))
				{
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							groupHeadClose = true;
							break;
						}
					}					
				}				
			}
			//Return the penalty if the room was not within range
			if(!groupHeadClose)
				return -20;
		}
		return 0;
	}
	
	//Soft constraint 7
	//****************************************************************************************************************
	//CheckManagerCloseAll checks to see if the manager of the given group is positioned close to all group mamber   *
	//within there group.  If a group member is not close, a penalty of -2 in incurred for each person not within    *
	//close range of the manager of the group.																	     *
	//****************************************************************************************************************
	static int checkManagerCloseAll(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		int totalPenalty = 0;
		//Check if the person to assign is a manager
		if(assignment.getPerson().getManager())
		{
			String group = assignment.getPerson().getGroup();
			String personName = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean roomClose = false;			
			//Check all of the assigned people to see if they are close to the manager.
			for(int i = 0; i < assignedPeople.size(); i++)
			{	
				//Check if the person is part of the same group and that you are not looking at the person
				if(assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(personName))
				{
					//Loop through the person to check close rooms and see if the manager will be close to them.
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							roomClose = true;
							break;
						}
					}
					//If the room wasn't found in the close list then increase the penalty.
					if(!roomClose)
						totalPenalty = totalPenalty + -2;					
				}
				roomClose = false;
			}
		}
		return totalPenalty;
	}
	
	//Soft constraint 8
	//****************************************************************************************************************
	//checkHeadProjClose2All is a function which checks to see if the project head is close to all members of the    *
	//same project.  If they are not withing close range, a penalty of -5 will be added for each member not within   *
	//range.																										 *
	//****************************************************************************************************************
	static int checkHeadProjClose2All(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		int totalPenalty = 0;
		//Check if the person to assign is a project head
		if(!assignment.getPerson().getProjectHead().equals("None"))
		{
			String project = assignment.getPerson().getProjectHead();
			String personName = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			boolean roomClose = false;			
			//Check all of the assigned people to see if they are close to the group head.
			for(int i = 0; i < assignedPeople.size(); i++)
			{	
				//Check if the person is part of the same group and that you are not looking at the person
				if(assignedPeople.get(i).getPerson().getProject().equals(project) && !assignedPeople.get(i).getPerson().getName().equals(personName))
				{
					//Loop through the person to check close rooms and see if the group head will be close to them.
					for(int j = 0; j < assignedPeople.get(i).getRoom().getClose().size(); j++)
					{
						//If the room is found in the close rooms list, set roomClose to true.
						if(assignedPeople.get(i).getRoom().getClose().get(j).equals(room))
						{
							roomClose = true;
							break;
						}
					}
					//If the room wasn't found in the close list then increase the penalty.
					if(!roomClose)
						totalPenalty = totalPenalty + -5;					
				}
				roomClose = false;
			}
		}
		return totalPenalty;
	}
	
	//Soft constraint 9
	//****************************************************************************************************************
	//checkLargeProjectHeadCloseSecretary checks to see if the head of a large project is close to at least one      *
	//secretary of the given project.  If the head of a large project is not close to at least one secretary of their*
	//project, return a penalty of -10																				 *
	//****************************************************************************************************************
	static int checkLargeProjectHeadCloseSecretary(Tuple assignment, ArrayList<Tuple> assignedPeople, ArrayList<Project> projects)
	{
		//Check to see if the person being assigned is a project head
		if(!assignment.getPerson().getProjectHead().equals("None"))
		{
			String proj = assignment.getPerson().getProject();
			boolean large = false;
			//Search the list of projects to see if the project is large or not.
			for(int i = 0; i < projects.size(); i++)
			{
				//Check if the project is a large one.
				if(projects.get(i).getName().equals(proj) && projects.get(i).getLarge())
					large = projects.get(i).getLarge();	
				//If the project is large, search through the people to see if one is a secretary that is close by
				if(large)
				{
					String group = assignment.getPerson().getGroup();
					boolean secretaryClose = false;
					String room = assignment.getRoom().getName();
					String name = assignment.getPerson().getName();
					//Loop to check for a secretary of the given project
					for(int j = 0; j < assignedPeople.size(); j++)
					{
						//If the person is a secretary for the large project check if the room they are assigned to is close by
						if(assignedPeople.get(j).getPerson().getSecretary() && assignedPeople.get(j).getPerson().getGroup().equals(group) && !assignedPeople.get(j).getPerson().getName().equals(name))
						{
							for(int k = 0; k < assignedPeople.get(j).getRoom().getClose().size(); k++)
							{
								//If the room is found in the close rooms list, set roomClose to true.
								if(assignedPeople.get(j).getRoom().getClose().get(k).equals(room))
								{
									secretaryClose = true;
									break;
								}
							}					
						}				
					}
					//Return a penalty if no secretary was found within close range of the project head
					if(!secretaryClose)
						return -10;
				}
			}
		}		
		return 0;
	}
	
	//Soft constraint 10
	//****************************************************************************************************************
	//checkProjectHeadCloseGroupHead checks to see if the project head of a large project is close to the group head *
	//of the same project.  If the project head and group head are not within close range of one another, a penalty  *
	//of -10 is returned																							 *
	//****************************************************************************************************************
	static int checkProjHeadCloseGroupHead(Tuple assignment, ArrayList<Tuple> assignedPeople, ArrayList<Project> projects)
	{
		//Check if the person being assigned is a project head
		if(!assignment.getPerson().getProjectHead().equals("None"))
		{
			//Get the attributes of the person needed to be checked for
			String proj = assignment.getPerson().getProject();
			boolean large = false;
			//Check to see if the project is large or not
			for(int i = 0; i < projects.size(); i++)
			{
				if(projects.get(i).getName().equals(proj) && projects.get(i).getLarge())
					large = projects.get(i).getLarge();		
				//If the project is large, check if the group head of the project is close by
				if(large)
				{
					//Get the group name
					String group = assignment.getPerson().getGroup();
					boolean groupHeadClose = false;
					String room = assignment.getRoom().getName();
					String name = assignment.getPerson().getName();
					//Loop through the rest of the people to find the head of the group
					for(int j = 0; j < assignedPeople.size(); j++)
					{
						//If the person found is the group head and is in the same project check to see if they are close to the project head of the group
						if(assignedPeople.get(j).getPerson().getGroupHead().equals(group) && !assignedPeople.get(j).getPerson().getName().equals(name))
						{
							//Loop to check the rooms to see if the person is close to the project head
							for(int k = 0; k < assignedPeople.get(j).getRoom().getClose().size(); k++)
							{
								//If the room is found in the close rooms list, set roomClose to true.
								if(assignedPeople.get(j).getRoom().getClose().get(k).equals(room))
								{
									groupHeadClose = true;
									break;
								}
							}					
						}				
					}
					//Return the penalty if the group head was not in close range
					if(!groupHeadClose)
						return -10;
				}
			}
		}		
		return 0;
	}
	
	//Soft constraint 11
	//****************************************************************************************************************
	//Check to see if two smokers share an office.  If a smoker shares an office with a non smoker, a penalty of -50 *
	//is to be returned.																							 *
	//****************************************************************************************************************
	static int checkSmokersTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Get the room and the persons name being assigned
		String room = assignment.getRoom().getName();
		String person = assignment.getPerson().getName();
		
		//Loop through the people assigned to see if anyone is assigned to the same room.
		for(int i = 0; i < assignedPeople.size(); i++)
		{
			//see if they are in the same room and are not a smoker.  If so assign the penalty
			if(assignedPeople.get(i).getRoom().getName().equals(room) && (!assignedPeople.get(i).getPerson().getName().equals(person) && !assignedPeople.get(i).getPerson().getSmoker()))
				return -50;
		}
		return 0;		
	}
	
	//Soft constraint 12
	//****************************************************************************************************************
	//CheckSameProjectTOgether checks to see if two people that are part of the same project are grouped together in *
	//one room.  If they are, a penalty of -7 is returned as they should not share a room with one another.			 *
	//****************************************************************************************************************
	static int checkSameProjectTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		//Get the project name
		String project = assignment.getPerson().getProject();
		int index = assignedPeople.indexOf(assignment);
		//Check to see if the person is part of a project or not
		if(!project.equals("None"))
		{
			String room = assignment.getRoom().getName();
			//Loop to go through the people assigned to see if someone is assigned the same room or not.
			for(int i = index; i < assignedPeople.size(); i++)
			{
				//If the person is assigned the same room and is part of the same project, assigne the penalty
				if(assignedPeople.get(i).getRoom().getName().equals(room) && project.equals(assignedPeople.get(i).getPerson().getProject()) && !assignment.getPerson().getName().equals(assignedPeople.get(i).getPerson().getName()))
					return -7;
			}	
		}
		return 0;
	}
	
	//Soft constraint 13
	//****************************************************************************************************************
	//Check to see if two hackers share a room with one another or is two non hackers share a room together.  If they*
	//share a room and neither of which is a secretary assigne a penalty of -2										 *
	//****************************************************************************************************************
	static int checkHackersShare(Tuple assignment, ArrayList<Tuple> assignedPeople) 
	{
		//Check to see if the person is not a secretary
		if(!assignment.getPerson().getSecretary())
		{
			String room = assignment.getRoom().getName();
			boolean hacker = assignment.getPerson().getHacker();
			int index = assignedPeople.indexOf(assignment);
			//Loop through the rest of the people to see if they share a rooom with the person being assigned
			for(int i = index; i < assignedPeople.size(); i++)
			{
				//If they share a room and are opposite (ie. hacke/non-hacker) inocur the appropriate penalty
				if(assignedPeople.get(i).getRoom().getName().equals(room) && assignedPeople.get(i).getPerson().getHacker() != hacker && !assignedPeople.get(i).getPerson().getSecretary())
					return -2;
			}
		}
		return 0;
	}
	
	//Soft constraint 14
	//****************************************************************************************************************
	//Check to see if two people share an office or not.  If two peoiple are grouped in a small office, a penalty of *
	//-4 should be assigned.																						 *										
	//****************************************************************************************************************
	static int checkShareOffice(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		String name = assignment.getPerson().getName();
		String room = assignment.getRoom().getName();
		
		//Check to see if anyone assigned shares an office with the current person being assigned.
		for(int i = 0; i < assignedPeople.size(); i++)
		{
			//If they share an office, assign the penalty.
			if(assignedPeople.get(i).getRoom().getName().equals(room) && !name.equals(assignedPeople.get(i).getPerson().getName()))
				return -4;
		}		
		return 0;
	}	
	
	//Soft constraint 15
	//****************************************************************************************************************
	//Check to see if people who share an office work together or not.  If they share an office and don't work       *
	//together, a penalty of -3 should be assigned.																	 *
	//****************************************************************************************************************
	static int checkWorkTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{
		String room = assignment.getRoom().getName();
		String name = assignment.getPerson().getName();
		int index = assignedPeople.indexOf(assignment);
		boolean workTogether = false;
		
		//Loop to go through the people and see if another person is assigned the same room.
		for(int i = index; i < assignedPeople.size(); i++)
		{
			//Check if the two people are assigned the same room.
			if(assignedPeople.get(i).getRoom().getName().equals(room) && !assignedPeople.get(i).getPerson().getName().equals(name))
			{
				//If they do share the same room, check if the two people work together or not.
				for(int j = 0; j < assignedPeople.get(i).getPerson().getWorksWith().size(); j++)
				{
					//If they do work together set the boolean to true.
					if(assignedPeople.get(i).getPerson().getWorksWith().get(j).equals(name))
					{
						workTogether = true;
						break;
					}
				}
				//If they don't work together assign the proper penalty
				if(!workTogether)
					return -3;
			}			
		}		
		return 0;
	}
	
	//Soft constraint 16
	//****************************************************************************************************************
	//Check to see if two people share a small office or not.  If they do share a small office, a penalty of -25 is  *
	//to be applied to the total.																					 *
	//****************************************************************************************************************
	static int checkShareSmall(Tuple assignment, ArrayList<Tuple> assignedPeople)
	{	
		//Check if the room being assigned is a small room or not.
		if(assignment.getRoom().getSize().equals("small"))
		{
			String name = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			int index = assignedPeople.indexOf(assignment);
			//If the room is small, check if anyone else is assigned to that office.
			for(int i = index; i < assignedPeople.size(); i++)
			{
				//If another person is assigned to the small room, assign the penalty.
				if(assignedPeople.get(i).getRoom().getName().equals(room) && !name.equals(assignedPeople.get(i).getPerson().getName()))
					return -25;
			}			
		}
		return 0;
	}
}
