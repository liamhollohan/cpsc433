package cpsc433;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Environment extends PredicateReader implements SisyphusPredicates{

	public Environment(PredicateReader p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public boolean fixedAssignments;
	public Solution currentSolution;

	public int fromFile(String datafile) {
		int ret = 0;
		BufferedReader stream = null;
		System.out.println("reading file "+datafile+"...");
		try {
			stream = new BufferedReader(new InputStreamReader(new FileInputStream(datafile)));
			ret = fromStream(stream);
		}
		catch (FileNotFoundException ex) {
			error("Can't open file " + datafile);
			return -1;
		}
		try {
			if (stream!=null) stream.close();
		}
		catch (IOException ex) {
			error("Can't close file " + datafile);
		}
		System.out.println("... read "+ret+" lines from file "+datafile);
		return ret;
	}

	public static void reset() {
		// TODO Auto-generated method stub
		
	}

	public static Environment get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void a_person(String p) {
		Person per = new Person();
		per.setName(p);
	}

	@Override
	public boolean e_person(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_secretary(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_secretary(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_researcher(String p) {
		Person per = new Person();
		per.setName(p);
		System.out.println(per.toString(per));
	}

	@Override
	public boolean e_researcher(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_manager(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_manager(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_smoker(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_smoker(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_hacker(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_hacker(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_in_group(String p, String grp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_in_group(String p, String grp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_in_project(String p, String prj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_in_project(String p, String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_heads_group(String p, String grp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_heads_group(String p, String grp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_heads_project(String p, String prj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_heads_project(String p, String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_works_with(String p, String p2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_works_with(String p, String p2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_assign_to(String p, String room) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_close(String room, String room2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_close(String room, String room2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_large_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_large_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_medium_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_medium_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_small_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_small_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_group(String g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_group(String g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_project(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_project(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_large_project(String prj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_large_project(String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_group(String p, String grp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_group(String p, String grp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_project(String p, String prj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_project(String p, String prj) {
		// TODO Auto-generated method stub
		return false;
	}

}
