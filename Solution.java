package cpsc433;

import java.util.ArrayList;

public class Solution {

	public Solution(String outfilename) {
		// TODO Auto-generated constructor stub
	}

	public static Object verbosity;
	
	public static boolean checkHardConstraints(ArrayList<Tuple> assignment, ArrayList<Person> people)
	{
		allAssign_NoDuplicates(assignment, people);
		bigMoneyNoShare(assignment, people);
		return true;
	}

	
	private static void bigMoneyNoShare(ArrayList<Tuple> assignment, ArrayList<Person> people) 
	{
		
	}


	public static boolean allAssign_NoDuplicates(ArrayList<Tuple> assignment, ArrayList<Person> people)
	{
		System.out.println(people.size());
		System.out.println(assignment.size());
		boolean manager = false;
		for (int i = 0; i < people.size(); i++)
		{
			int count = 0;
			Person per = people.get(i);
			if (!people.get(i).getGroupHead().equals("None") || !people.get(i).getProjectHead().equals("None") || people.get(i).getIsManager())
			{
				System.out.println(people.get(i).getName());
				manager = true;
			}
			for (int j = 0; j < assignment.size(); j++)
			{
				if (manager && assignment.get(j).getPeople().contains(people.get(i).getName()) && assignment.get(j).getPeople().size() > 1)
				{
					System.out.println(" -> Error: Big Money No Share");
					return false;
				}
				if (assignment.get(j).getPeople().size() > 2)
				{
					System.out.println(" -> Error: \"Two\" many people in a room");
					return false;
				}
				if (assignment.get(j).getPeople().contains(per.getName()))
				{
					count++;
				}
			}
			if (count > 1)
			{
				System.out.println(" -> Error: Duplicate Assignment");
				return false;
			}
			if (count == 0)
			{
				System.out.println(" -> Error: No assignment");
				return false;
			}
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
