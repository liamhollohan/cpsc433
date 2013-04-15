package cpsc433;

import java.util.ArrayList;

public class Solution 
{
	public static Object verbosity;
	
	//This is called in his Test class. I'm not positive what it is supposed to do - liam
	public Solution(String outfilename) {
		// TODO Auto-generated constructor stub
	}

	//**************************************************************************************************
	//**************************************************************************************************
	//**************************************************************************************************
	//ADDED ALL CHECKING BELOW *************************************************************************
	//****************************************************************************************************************
	//Need to document
	//****************************************************************************************************************
	//Might be useless if we are dequeuing properly
	public static boolean checkTwoMany(ArrayList<Tuple> assignment)
	{
		int count;
		String roomName;
		//Loop to check how many people are assigned to each room.
		for (int j = 0; j < assignment.size(); j++)
		{
			roomName = assignment.get(j).getRoom().getName();
			count = 0;
			for(int i = j; i < assignment.size(); i++)
				if(assignment.get(i).getRoom().getName().equals(roomName))
					count++;			
			if (count > 2)
			{
				System.out.println(" -> Error: \"Two\" many people in a room");
				return false;
			}
		}
		return true;
	}
	
	//Will be useless if we are dequeuing full rooms when completed.
	public static boolean checkBigMoney(ArrayList<Tuple> assignment)
	{
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
					if(assignment.get(j).getRoom().getName().equals(roomNumber) && !assignment.get(j).getPerson().getName().equals(managerName))
					{
						//System.out.println(" -> Error: Big Money No Share!");
						return false;
					}
				}
			}
			manager = false;
		}
		return true;
	}	
	
	//Check multiple assignment and all assigned
	public static boolean checkAssignment(ArrayList<Tuple> assignment, ArrayList<Person> people)
	{
		int count;
		String name2Check;
		for(int i = 0; i < people.size(); i++)
		{
			count = 0;
			name2Check = people.get(i).getName();
			for(int j = 0; j < assignment.size(); j++)
			{
				if(assignment.get(j).getPerson().getName().equals(name2Check))
					count++;
			}
			if(count > 1)
			{
				System.out.println(" -> Error: Person Is Assigned To Multiple Rooms!");
				return false;
			}
			if(count < 1)
			{
				System.out.println(" -> Error: Not Everyone Is Assigned A Room!");
				return false;
			}
		}
		return true;		
	}
	
	public static boolean checkSolvable(ArrayList<Person> people, ArrayList<Room> rooms)
	{
		int managerCount = 0;
		int otherPeople = 0;
		
		for(int i = 0; i < people.size(); i++)
		{
			if(people.get(i).getManager() || !people.get(i).getGroupHead().equals("None") || !people.get(i).getProjectHead().equals("None"))
				managerCount++;
			else
				otherPeople++;
		}
		
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
	
	//ADDED THIS*************************************************************
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
		
		
		//Get utility function which returns the utility of the given proposed solution
		/*public static int getUtility(ArrayList<Tuple> assignedPeople, ArrayList<Project> projects)
		{
			int utility = 0;
			
			//Need to call all soft constraint checkers here
			//utility += checkGroupHeadLarge(assignment); // 1
			//utility += checkGroupHeadsClose(assignment, assignedPeople); // 2
			//utility += checkSecretaryClose(assignment, assignedPeople); // 3
			if(assignment.getPerson().getSecretary())
			{
				//utility += checkSecretariesTogether(assignment, assignedPeople); // 4
			}	
			//utility += checkManagerCloseSecretary(assignment, assignedPeople); // 5
			//utility += checkManagerCloseGroupHead(assignment, assignedPeople); // 6
			//utility += checkManagerCloseAll(assignment, assignedPeople); // 7
			//utility += checkHeadProjClose2All(assignment, assignedPeople); // 8
			//utility += checkLargeProjectHeadCloseSecretary(assignment, assignedPeople, projects); // 9
			//utility += checkProjHeadCloseGroupHead(assignment, assignedPeople, projects);// 10
			if(assignment.getPerson().getSmoker())
			{
				//utility += checkSmokersTogether(assignment, assignedPeople); // 11
			}
			//NEED A DEEP COPY LIST TO DEQUEUE FROM
			//utility += checkSameProjectTogether(assignment, assignedPeople); // 12
			//utility += checkHackersShare(assignment, assignedPeople); // 13
			//utility += checkShareOffice(assignment, assignedPeople); // 14
			//utility += checkWorkTogether(assignment, assignedPeople); // 15
			//utility += checkShareSmall(assignment, assignedPeople); // 16		
			
			utility = getNodeUtility(assignedPeople, projects);
			return utility;
		}*/
		
		// 1
		static int checkGroupHeadLarge(Tuple assignment)
		{
			if(!assignment.getPerson().getGroupHead().equals("None") && !assignment.getRoom().getSize().equals("large"))
				return -40;
			
			return 0;
		}	
		
		// 2
		static int checkGroupHeadsClose(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			int totalPenalty = 0;
			//Check if the person to assign is a group head
			if(!assignment.getPerson().getGroupHead().equals("None"))
			{
				String group = assignment.getPerson().getGroupHead();
				String personName = assignment.getPerson().getName();
				String room = assignment.getRoom().getName();
				boolean roomClose = false;			
				//Check all of the assigned people to see if they are close to the group head.
				for(int i = 0; i < assignedPeople.size(); i++)
				{	
					//**********************************************************
					//Check if the person is part of the same group and that you are not looking at the person // MAY BE ABLE TO OPTIMIZE BY REMOVING THE LATER CHECK FOR ALL UTILITY FUNCTIONS
					//WILL REQUIRE TESTING IF THAT IS THE CASE
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
		
		// 3
		static int checkSecretaryClose(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			if(!assignment.getPerson().getGroupHead().equals("None"))
			{
				String group = assignment.getPerson().getGroup(); 
				String name = assignment.getPerson().getName();
				String room = assignment.getRoom().getName();
				boolean secretaryClose = false;
				for(int i = 0; i < assignedPeople.size(); i++)
				{
					if(assignedPeople.get(i).getPerson().getSecretary() && assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(name))
					{
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
				if(!secretaryClose)
					return -30;
			}
			return 0;
		}
		
		// 4
		static int checkSecretariesTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			String room = assignment.getRoom().getName();
			String person = assignment.getPerson().getName();
			
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				if(assignedPeople.get(i).getRoom().getName().equals(room) && (!assignedPeople.get(i).getPerson().getName().equals(person) && !assignedPeople.get(i).getPerson().getSecretary()))
					return -5;
			}
			return 0;		
		}
		
		// 5
		static int checkManagerCloseSecretary(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			if(assignment.getPerson().getManager())
			{
				String group = assignment.getPerson().getGroup();
				String name = assignment.getPerson().getName();
				String room = assignment.getRoom().getName();
				boolean secretaryClose = false;
				for(int i = 0; i < assignedPeople.size(); i++)
				{
					if(assignedPeople.get(i).getPerson().getSecretary() && assignedPeople.get(i).getPerson().getGroup().equals(group) && !assignedPeople.get(i).getPerson().getName().equals(name))
					{
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
				if(!secretaryClose)
					return -20;
			}
			return 0;
		}
		
		// 6
		static int checkManagerCloseGroupHead(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			if(assignment.getPerson().getManager())
			{
				String group = assignment.getPerson().getGroup();
				String name = assignment.getPerson().getName();
				String room = assignment.getRoom().getName();
				boolean groupHeadClose = false;
				for(int i = 0; i < assignedPeople.size(); i++)
				{
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
				if(!groupHeadClose)
					return -20;
			}
			return 0;
		}
		
		// 7
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
					//**********************************************************
					//Check if the person is part of the same group and that you are not looking at the person // MAY BE ABLE TO OPTIMIZE BY REMOVING THE LATER CHECK FOR ALL UTILITY FUNCTIONS
					//WILL REQUIRE TESTING IF THAT IS THE CASE
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
		
		// 8
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
					//**********************************************************
					//Check if the person is part of the same group and that you are not looking at the person // MAY BE ABLE TO OPTIMIZE BY REMOVING THE LATER CHECK FOR ALL UTILITY FUNCTIONS
					//WILL REQUIRE TESTING IF THAT IS THE CASE
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
		
		// 9
		static int checkLargeProjectHeadCloseSecretary(Tuple assignment, ArrayList<Tuple> assignedPeople, ArrayList<Project> projects)
		{
			if(!assignment.getPerson().getProjectHead().equals("None"))
			{
				String proj = assignment.getPerson().getProject();
				boolean large = false;
				for(int i = 0; i < projects.size(); i++)
				{
					if(projects.get(i).getName().equals(proj) && projects.get(i).getLarge())
						large = projects.get(i).getLarge();		
					if(large)
					{
						String group = assignment.getPerson().getGroup();
						boolean secretaryClose = false;
						String room = assignment.getRoom().getName();
						String name = assignment.getPerson().getName();
						for(int j = 0; j < assignedPeople.size(); j++)
						{
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
						if(!secretaryClose)
							return -10;
					}
				}
			}		
			return 0;
		}
		
		// 10
		static int checkProjHeadCloseGroupHead(Tuple assignment, ArrayList<Tuple> assignedPeople, ArrayList<Project> projects)
		{
			if(!assignment.getPerson().getProjectHead().equals("None"))
			{
				String proj = assignment.getPerson().getProject();
				boolean large = false;
				for(int i = 0; i < projects.size(); i++)
				{
					if(projects.get(i).getName().equals(proj) && projects.get(i).getLarge())
						large = projects.get(i).getLarge();		
					if(large)
					{
						String group = assignment.getPerson().getGroup();
						boolean groupHeadClose = false;
						String room = assignment.getRoom().getName();
						String name = assignment.getPerson().getName();
						for(int j = 0; j < assignedPeople.size(); j++)
						{
							if(assignedPeople.get(j).getPerson().getGroupHead().equals(group) && !assignedPeople.get(j).getPerson().getName().equals(name))
							{
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
						if(!groupHeadClose)
							return -10;
					}
				}
			}		
			return 0;
		}
		// 11
		static int checkSmokersTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			String room = assignment.getRoom().getName();
			String person = assignment.getPerson().getName();
			
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				if(assignedPeople.get(i).getRoom().getName().equals(room) && (!assignedPeople.get(i).getPerson().getName().equals(person) && !assignedPeople.get(i).getPerson().getSmoker()))
					return -50;
			}
			return 0;		
		}
		
		// 12
		static int checkSameProjectTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			String project = assignment.getPerson().getProject();
			int index = assignedPeople.indexOf(assignment);
			if(!project.equals("None"))
			{
				String room = assignment.getRoom().getName();
				for(int i = index; i < assignedPeople.size(); i++)
				{
					if(assignedPeople.get(i).getRoom().getName().equals(room) && project.equals(assignedPeople.get(i).getPerson().getProject()) && !assignment.getPerson().getName().equals(assignedPeople.get(i).getPerson().getName()))
						return -7;
				}	
			}
			return 0;
		}
		
		// 13
		static int checkHackersShare(Tuple assignment, ArrayList<Tuple> assignedPeople) 
		{
			if(!assignment.getPerson().getSecretary())
			{
				String room = assignment.getRoom().getName();
				boolean hacker = assignment.getPerson().getHacker();
				int index = assignedPeople.indexOf(assignment);
				for(int i = index; i < assignedPeople.size(); i++)
				{
					if(assignedPeople.get(i).getRoom().getName().equals(room) && assignedPeople.get(i).getPerson().getHacker() != hacker && !assignedPeople.get(i).getPerson().getSecretary())
						return -2;
				}
			}
			return 0;
		}
		
		// 14
		static int checkShareOffice(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			String name = assignment.getPerson().getName();
			String room = assignment.getRoom().getName();
			
			for(int i = 0; i < assignedPeople.size(); i++)
			{
				if(assignedPeople.get(i).getRoom().getName().equals(room) && !name.equals(assignedPeople.get(i).getPerson().getName()))
					return -4;
			}		
			return 0;
		}	
		
		// 15
		static int checkWorkTogether(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{
			String room = assignment.getRoom().getName();
			String name = assignment.getPerson().getName();
			int index = assignedPeople.indexOf(assignment);
			boolean workTogether = false;
			
			for(int i = index; i < assignedPeople.size(); i++)
			{
				if(assignedPeople.get(i).getRoom().getName().equals(room) && !assignedPeople.get(i).getPerson().getName().equals(name))
				{
					for(int j = 0; j < assignedPeople.get(i).getPerson().getWorksWith().size(); j++)
					{
						if(assignedPeople.get(i).getPerson().getWorksWith().get(j).equals(name))
						{
							workTogether = true;
							break;
						}
					}
					if(!workTogether)
						return -3;
				}			
			}		
			return 0;
		}
		
		// 16
		static int checkShareSmall(Tuple assignment, ArrayList<Tuple> assignedPeople)
		{		
			if(assignment.getRoom().getSize().equals("small"))
			{
				String name = assignment.getPerson().getName();
				String room = assignment.getRoom().getName();
				int index = assignedPeople.indexOf(assignment);
				for(int i = index; i < assignedPeople.size(); i++)
				{
					if(assignedPeople.get(i).getRoom().getName().equals(room) && !name.equals(assignedPeople.get(i).getPerson().getName()))
						return -25;
				}			
			}
			return 0;
		}
}
