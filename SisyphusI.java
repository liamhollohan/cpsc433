package cpsc433;

import java.io.FileOutputStream;
import java.io.PrintStream;

import cpsc433.OTreeNode;

public class SisyphusI {
	
	private static final long DEFAULT_MAX_TIME = 30000;
	static PrintStream traceFile;
	public static PrintStream output;

	//****************************************************************************************************************
	//This is the main method which reads the input file supplied and the output file and then checks if the output  *
	//produced adheres to all of the hard constraints specified in the problem description.  Hard constraints 		 *
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
		long startTime = System.currentTimeMillis();

		if (args.length == 2) 
		{
			String outfile = makeOutfilename(args[0]);
			try {
				output = new PrintStream(new FileOutputStream(outfile));
			}
			catch (Exception ex) {traceFile = null;}
			PredicateReader prI = new PredicateReader(args[0]);
			Environment envI = new Environment(prI);
			envI.fromFile(args[0]);
			
			//Check to see if finding a solution is possible
			boolean isSolvable = true;
			/*if (!Solution.checkSolvable(envI.getPeople(), envI.getRooms())) {
				isSolvable = false;
			}*/
			int numPeople = envI.getPeople().size();
			int totalTime = Integer.parseInt(args[1]);
			int runningTime = (int) (totalTime - (totalTime*0.02) - numPeople/3);
			
			//=============Begin OTree Computation==================
			OTree tree = new OTree(envI);

			OTreeNode bestSol = null;

			boolean isComplete = false;
			int transCount = 0;
			long currentStart = 0;
			long currentEnd = 0;
			if(isSolvable) {
				for (;;transCount++) {
		            currentStart = System.currentTimeMillis();
					isComplete = tree.Transition();
		            
		            OTreeNode newBest = tree.getBestSol();
		            
		            if (newBest != null && !newBest.equals(bestSol)) {
		                bestSol = newBest;
		                //System.out.println("####New Best [" + transCount + "] : " + bestSol.toString() + ":" + bestSol.getUtility());
		            }
		            currentEnd = System.currentTimeMillis();
		           
		            if ((System.currentTimeMillis() - startTime + (currentEnd - currentStart)) > runningTime || isComplete) break;
				}
			}
			
			System.out.println("# of Transitions: " + transCount);
			//=============End OTree Computation====================
			
			System.out.println("Checkpoint 1: " + String.valueOf(System.currentTimeMillis() - startTime) );
			
			//startTime = System.currentTimeMillis();
			if (bestSol != null) {
				System.out.println("Best Solution: " + bestSol.toString() + ":\n" + bestSol.getUtility());
				for (int i = 0; i < bestSol.assignment.size(); i++) {
					envI.a_assign_to(bestSol.assignment.get(i).getPerson().getName(), bestSol.assignment.get(i).getRoom().getName());
				}
			} else {
				System.out.println(" -> ERROR: No solution found!");
			}
			System.out.println("Time elapsed: " + String.valueOf(System.currentTimeMillis() - startTime) );
		}
	}
	
	/**
	 * Utility method the create an output filename from an input filename.
	 * @param in A String representing the input filename
	 * @return an appropriate output filename (which is the input filename + ".out")
	 */
	static String makeOutfilename(String in) {
		return in+".out";
	}
	
	/**
	 * Implement "command mode": repeatedly read lines of predicates
	 * from {@link System#in} and either assert them (if the line starts
	 * with a "!") or evaluate them (and return "true" or "false" to
	 * {@link System#out}. 
	 * @param env the environment that can interpret the predicates.
	 */
	/*public static void commandMode(PredicateReader env) {
		final int maxBuf = 200;
		byte[] buf = new byte[maxBuf];
		int length;
		try {
			print("\nSisyphus I: query using predicates, assert using \"!\" prefixing predicates;\n !exit() to quit; !help() for help.\n\n> ");
			while ((length=System.in.read(buf))!=-1) {
				String s = new String(buf,0,length);
				s = s.trim();
				if (s.equals("exit")) break;
				if (s.equals("?")||s.equals("help")) {
					s = "!help()";
					println("> !help()");
				}
				if (s.length()>0) {
					if (s.charAt(0)=='!') {
						env.assert_(s.substring(1));
					}
					else { 
						print(" --> "+env.eval(s));
					}
				}
				print("\n> ");
			}
		} catch (Exception e) {
			println("exiting: "+e.toString());
		}
	}*/
	
}
