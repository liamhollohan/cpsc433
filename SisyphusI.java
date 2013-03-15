package cpsc433;

import java.io.FileOutputStream;
import java.io.PrintStream;

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
		try {
			traceFile = new PrintStream(new FileOutputStream("trace.out"));
			traceFile.print("Trace sisyphusI.Test");
			for (String s: args) traceFile.print(" "+s);
			traceFile.println("\n"+new java.util.Date());
		}
		catch (Exception ex) {traceFile = null;}

		/*if (args.length>=2) {
			long runtime = DEFAULT_MAX_TIME;
			if (args.length<3) {
				printSynopsis();
				println("No run time given; assuming run time of "+runtime+" seconds");
			}
			else {
				runtime = new Long(args[2]).longValue();
			}

			//Read input file
			PredicateReader prI = new PredicateReader(args[0]);
			Environment envI = new Environment(prI);
			envI.fromFile(args[0]);
			
			
		}
		else*/ if (args.length <= 2) {
			String outfile = makeOutfilename(args[0]);
			try {
				output = new PrintStream(new FileOutputStream(outfile));
			}
			catch (Exception ex) {traceFile = null;}
			PredicateReader prI = new PredicateReader(args[0]);
			Environment envI = new Environment(prI);
			envI.fromFile(args[0]);
			
			
		}
		else { // go into "command mode" if there's nothing on the command line
			PredicateReader env = Environment.get();
			printSynopsis();
			commandMode(env);
		}

		if (traceFile!=null) {
			traceFile.println(new java.util.Date());
			traceFile.close();
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
	 * Utility method to print out a synopsis of the command line.
	 */
	static void printSynopsis() {
    	println("Synopsis: Sisyphus <search-prg> [<env-file> [<maxTimeInMilliseconds:default="+DEFAULT_MAX_TIME+">]]");
	}
	
	static void println(String s) {
		System.out.println(s);
		traceFile.println(s);
	}

	static void print(String s) {
		System.out.print(s);
		traceFile.print(s);
	}

	static void write(byte[] s, int offset, int count) throws Exception {
		System.out.write(s, offset, count);
		traceFile.write(s, offset, count);;
	}
	
	/**
	 * Implment "command mode": repeatedly read lines of predictes
	 * from {@link System#in} and either assert them (if the line starts
	 * with a "!") or evaluate them (and return "true" or "false" to
	 * {@link System#out}. 
	 * @param env the environment that can interpret the predicates.
	 */
	public static void commandMode(PredicateReader env) {
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
	}
	
}
