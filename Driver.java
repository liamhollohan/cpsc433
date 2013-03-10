package cpsc433;

import java.util.ArrayList;

public class Driver {

	//****************************************************************************************************************
	//Driver is the main method which reads the input file supplied and the output file and then checks if the output*
	//produced adheres to all of the hard constraints specified in the problem discription.  Hard constraints 		 *
	//include:																										 *
	//1)No person is assigned to more than one room.																 *
	//2)No more that two people can be assigned to one room.														 *
	//3)If a person is a manager, group head or project head, they must have a room to themselves.					 *
	//4)All people in the specified input are assigned a room to be in.												 *
	//In order to check the solution given by the output file,  we create a output environment and then compare it   *
	//against the input environment that we create from the given input file. 										 *				 
	//****************************************************************************************************************
	public static void main(String[] args)
	{
		System.out.println(" -> Initializing...");
		
		String input = args[0];
		String output = args[1];

		//Read input file
		PredicateReader prI = new PredicateReader(input);
		Environment envI = new Environment(prI);
		envI.fromFile(input);
		
		//Read output file
		PredicateReader prO = new PredicateReader(output);
		Environment envO = new Environment(prO);
		envO.fromFile(output);
		ArrayList<Tuple> assignment = envO.getAssignment();
		ArrayList<Person> people = envI.getPeople();

		//Check if the output violates any of the hard constraints of the problem.
		Solution.checkHardConstraints(assignment, people);		
		
		System.out.println(" -> Terminating...");
	}
	
}
