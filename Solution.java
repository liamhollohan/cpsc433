package cpsc433;

import java.util.ArrayList;

public class Solution 
{
	public static Object verbosity;
	
	//This is called in his Test class. I'm not positive what it is supposed to do - liam
	public Solution(String outfilename) {
		// TODO Auto-generated constructor stub
	}

	//****************************************************************************************************************
	//The checkHardConstraints method is in charge of checking if the specified solution violates any of the hard    *
	//Constraints specified by the problem instance. It takes in an arraylist of tuples called assignment which      *																 
	//represents a list of rooms with the list of people assigned to each room as the second element in the tuple.	 *
	//checkHardConstraints also takes in the list of people specified from the input file to check the output 		 *
	//against.  This method uses two for loops to check if the generated solution violates any hard constraint and   *
	//will write out the error if any occur.																		 *
	//****************************************************************************************************************
	public static boolean checkHardConstraints(ArrayList<Tuple> assignment, ArrayList<Person> people)
	{
		//Loop to check how many people are assigned to each room.
		for (int j = 0; j < assignment.size(); j++)
		{
			//Check to see if the number of people assigned to a room is greater than two.  If true error and break out of the function.
			if (assignment.get(j).getPeople().size() > 2)
			{
				System.out.println(" -> Error: \"Two\" many people in a room");
				return false;
			}
		}
		//Boolean that will be set to true if the person being examined in the list of people is a manager, projest head or
		//group head.
		boolean manager = false;
		//Iterate through the list of people specified in the supplied input file.
		for (int i = 0; i < people.size(); i++)
		{
			//Count that keeps track of how many rooms a person is assigned to.
			int count = 0;
			//Create a duplicate of the person being compared for easibility of coding.
			Person per = people.get(i);
			//Check to see if the person being looked at is a manger, group head or project head.
			if (!people.get(i).getGroupHead().equals("None") || !people.get(i).getProjectHead().equals("None") || people.get(i).getManager())
			{
				System.out.println(people.get(i).getName());
				manager = true;
			}
			//Loop to iterate through the solution to the problem.  Check each tuple to see if any of the hard constraints are violated.
			for (int j = 0; j < assignment.size(); j++)
			{
				//Check to see if a group head project head or manager is sharing a room.  If true error and break out of the function.
				if (manager && assignment.get(j).getPeople().contains(people.get(i).getName()) && assignment.get(j).getPeople().size() > 1)
				{
					System.out.println(" -> Error: Big Money No Share");
					return false;
				}
				//Check if the person is in a room.  If true increment count by one.
				if (assignment.get(j).getPeople().contains(per.getName()))
				{
					count++;
				}
			}
			//Check to see if a person is assigned to more than one room.  If true error and break out of the function.
			if (count > 1)
			{
				System.out.println(" -> Error: Duplicate Assignment");
				return false;
			}
			//Check to see if the person is assigned to a room.  If true error and break out of the function.
			if (count == 0)
			{
				System.out.println(" -> Error: No assignment");
				return false;
			}
			//Reset the manager boolean to false.
			manager = false;
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
			SysiphusI.output.print("person(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a secretary and if so print the predicate to the file.
			if(people.get(i).getSecretary() == true)
				SysiphusI.output.print("secretary(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a researcher and if so print the predicate to the file.
			if(people.get(i).getSecretary() == true)
				SysiphusI.output.print("secretary(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a manager and if so print the predicate to the file.
			if(people.get(i).getManager() == true)
				SysiphusI.output.print("manager(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a smoker and if so print the predicate to the file.
			if(people.get(i).getSmoker() == true)
				SysiphusI.output.print("smoker(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is a hacker and if so print the predicate to the file.
			if(people.get(i).getHacker() == true)
				SysiphusI.output.print("hacker(" + people.get(i).getName() + ")" + "\n");
			//Check if the person is in a project and if they are print the person/project predicate to the file.
			if(!people.get(i).getProject().equals("None"))
				SysiphusI.output.print("project(" + people.get(i).getName() + ", " + people.get(i).getProject() + ")" + "\n");
			//Check if the person is in a group and if they are print the person/group predicate to the file.
			if(!people.get(i).getGroup().equals("None"))
				SysiphusI.output.print("group(" + people.get(i).getName() + ", " + people.get(i).getGroup() + ")" + "\n");
			//Check to see if the person works with anyone and if so print the worksWith predicate to the file.
			if(!people.get(i).getWorksWith().isEmpty())
			{
				SysiphusI.output.print("works-with(" + people.get(i).getName() + " {");
				//Loop to print out the list of people the current person works with.
				for(int j = 0; j < people.get(i).getWorksWith().size() - 1; j++)
				{
					SysiphusI.output.print(people.get(i).getWorksWith().get(j) + ", ")
				}
				SysiphusI.output.print(people.get(i).getWorksWith().get(j) + "})" + "/n");
			}
			//Check if the person is a project head and if they are print the person/project predicate to the file.
			if(!people.get(i).getProjectHead().equals("None"))
				SysiphusI.output.print("heads-project(" + people.get(i).getName() + ", " + people.get(i).getProject() + ")" + "\n");
			//Check if the person is a group head and if they are print the person/group predicate to the file.
			if(!people.get(i).getGroupHead().equals("None"))
				SysiphusI.output.print("heads-group(" + people.get(i).getName() + ", " + people.get(i).getGroup() + ")" + "\n");
			
		}
	}
	
	public static void printRooms(ArrayList<Room> rooms)
	{
		//Loop to go through and print all of the room predicates to the file.
		for(int i = 0;i < rooms.size(); i++)
		{
			//Print the room name predicate.
			SysiphusI.output.print("room(" + rooms.get(i).getName() +")" + "\n");
			//Check the size of the room and print it.
			if(!rooms.get(i).getSize().equals("None"))
			{
				//Check if the room is a small room.
				if(rooms.get(i).getSize().equals("small"))
				{
					SysiphusI.output.print("small-room (" + rooms.get(i).getName() +")" + "\n");
				}
				//Check if the room is a medium room.
				if(rooms.get(i).getSize().equals("medium"))
				{
					SysiphusI.output.print("medium-room (" + rooms.get(i).getName() +")" + "\n");
				}
				//Check if the room is a large room.
				if(rooms.get(i).getSize().equals("large"))
				{
					SysiphusI.output.print("large-room (" + rooms.get(i).getName() +")" + "\n");
				}
			}
			//Check to see if the room is close to any other rooms and if so print out all of the close rooms.
			if(!rooms.get(i).getClose().isEmpty())
			{
				SysiphusI.output.print("close(" + rooms.get(i).getName() + " {");
				//Loop to print out the list of people the current person works with.
				for(int j = 0; j < rooms.get(i).getClose().size() - 1; j++)
				{
					SysiphusI.output.print(rooms.get(i).getClose().get(j) + ", ")
				}
				SysiphusI.output.print(rooms.get(i).getClose().get(j) + "})" + "/n");
			}
		}
	}
	
	public static void printGroups(ArrayList<Group> groups)
	{
		//Loop to go through and print all of the group predicates to the file.
		for(int i = 0;i < groups.size(); i++)
		{
			//Print the group name predicate.
			SysiphusI.output.print("group(" + groups.get(i).getName() +")" + "\n");
		}
	}
	
	public static void printProjects(ArrayList<Project> projects)
	{
		//Loop to go through and print all of the project predicates to the file.
		for(int i = 0;i < projects.size(); i++)
		{
			//Print the group name predicate.
			SysiphusI.output.print("projects(" + projects.get(i).getName() +")" + "\n");
			//Check to see if the group is a large group.
			if(!projects.get(i).getSize().equals("None"))
			{
				SysiphusI.output.print("large-project (" + projects.get(i).getName() +")" + "\n");
			}
		}
	}
}
