package cpsc433;

import java.io.File;

public class Driver {

	public static void main(String[] args)
	{
		System.out.println(" -> Initializing...");
		
		String input = args[0];
		String output = args[1];

		PredicateReader pr = new PredicateReader(input);
		Environment env = new Environment(pr);
		env.fromFile(args[0]);
		
		System.out.println(" -> Terminating...");
	}
	
}
