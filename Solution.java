package cpsc433;

import java.util.ArrayList;

public class Solution {

	//****************************************************************************************************************
	//****************************************************************************************************************
	//****************************************************************************************************************
	//DELETE????????!!!!!!!!!!!!!!!!!!!!!
	public Solution(String outfilename) {
		// TODO Auto-generated constructor stub
	}
	//****************************************************************************************************************
	//****************************************************************************************************************
	//****************************************************************************************************************

	public static Object verbosity;
	
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
		//Bolean that will be set to true if the person being examined in the list of people is a manager, projest head or
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
			if (!people.get(i).getGroupHead().equals("None") || !people.get(i).getProjectHead().equals("None") || people.get(i).getIsManager())
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


}
