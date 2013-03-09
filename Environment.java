package cpsc433;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Environment extends PredicateReader implements SisyphusPredicates{

	private ArrayList<Person> people = new ArrayList();
	private ArrayList<Tuple> assignment = new ArrayList();
	
	public Environment(PredicateReader p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public boolean fixedAssignments;
	public Solution currentSolution;
	
	public static void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Tuple> getAssignment()
	{
		return assignment;
	}
	
	public ArrayList<Person> getPeople()
	{
		return people;
	}
	
	public static Environment get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void a_person(String p) {
		Person per = new Person();
		per.setName(p);
		people.add(per);
	}

	@Override
	public boolean e_person(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_secretary(String p) {
		Person per = new Person();
		per.setName(p);
		people.add(per);
		System.out.println(per.toString(per));
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
		people.add(per);
		System.out.println(people.size());
		System.out.println(per.toString(per));
	}

	@Override
	public boolean e_researcher(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_manager(String p) {
		Person per = new Person();
		per.setName(p);
		per.setIsManager(true);
		people.add(per);
		System.out.println(people.size());
		System.out.println(per.toString(per));
	}

	@Override
	public boolean e_manager(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_smoker(String p) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setSmoker(true);
				break;
			}
		}
	}

	@Override
	public boolean e_smoker(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_hacker(String p) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setHacker(true);
				break;
			}
		}
	}

	@Override
	public boolean e_hacker(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_in_group(String p, String grp) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setGroup(grp);
				break;
			}
		}
	}

	@Override
	public boolean e_in_group(String p, String grp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_in_project(String p, String prj) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setProject(prj);
				break;
			}
		}
	}

	@Override
	public boolean e_in_project(String p, String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_heads_group(String p, String grp) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setGroupHead(grp);
				break;
			}
		}	
	}

	@Override
	public boolean e_heads_group(String p, String grp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_heads_project(String p, String prj) 
	{
		for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setProjectHead(prj);
				break;
			}
		}	
	}

	@Override
	public boolean e_heads_project(String p, String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) 
	{
		System.out.println(p2s.first());
		/*for (int i = 0; i<people.size(); i++)
		{
			if (people.get(i).getName().equals(p))
			{
				people.get(i).setWorksWith(p2s);
				break;
			}
		}	*/
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
	public void a_assign_to(String p, String room) throws Exception 
	{
		boolean exists = false;
		for (int i = 0; i < assignment.size(); i++)
		{
			if (assignment.get(i).getRoom().equals(room))
			{
				exists = true;
				assignment.get(i).setPeople(p);
				break;
			}
		}
		if (!exists)
		{
			Tuple t = new Tuple(room, p);
			assignment.add(t);
		}
		/*for (int i=0;i<assignment.size();i++)
		{
			System.out.println(assignment.get(i).getRoom());
			ArrayList<String> x = assignment.get(i).getPeople();
			for (int j = 0; j < x.size(); j++)
			{
				System.out.println(x.get(j));
			}
		}*/
		for (int i=0;i<people.size();i++)
		{
			System.out.println(people.get(i).getName());
		}
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
