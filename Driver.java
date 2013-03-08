package cpsc433;

import java.io.File;

public class Driver {

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
		
		System.out.println(" -> Terminating...");
		
		
	}
	
}
