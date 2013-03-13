package cpsc433;

import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Environment extends PredicateReader implements SisyphusPredicates{

	//Added in the arraylist of type string for ease of finding people by name.
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> groups = new ArrayList<String>();
	private ArrayList<String> projects = new ArrayList<String>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Tuple> assignment = new ArrayList<Tuple>();
	
	public Environment(PredicateReader p) 
	{
		super(p);		
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
	public void a_person(String p) 
	{
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to search for the person.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Person was found so update the field within.
				people.get(i).setSecretary(true);
				exists = true;
			}
		}
		//Person was not found in the list of people
		if (!exists)
		{
			//Create the person and set their name.
			Person per = new Person(p);
			per.setPerson(true);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_person(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return true;
			}
		} 
		return false;
	}

	@Override
	public void a_secretary(String p) 
	{
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to search for the person.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Person was found so update the field within.
				people.get(i).setSecretary(true);
				exists = true;
			}
		}
		//Person was not found in the list of people
		if (!exists)
		{
			//Create the person and set their name as well as the secretary field to true.
			Person per = new Person(p);
			per.setSecretary(true);
			a_person(p);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_secretary(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getSecretary();
			}
		}
		return false;
	}
	
	@Override
	public void a_researcher(String p) {
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to search for the person.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Person was found so update the field within.
				people.get(i).setResearcher(true);
				exists = true;
			}
		}
		//Person was not found.
		if (!exists)
		{
			//Create the person and set their name as well as the researcher field.
			Person per = new Person(p);
			per.setResearcher(true);
			a_person(p);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_researcher(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getResearcher();
			}
		}
		return false;
	}

	@Override
	public void a_manager(String p)
	{
		//Boolean used to detect if the person exists or not.
		boolean exists = false;
		//Loop to search for the person.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Person exists so update the field within.
				people.get(i).setManager(true);
				exists = true;
			}
		}
		//Person was not found.
		if (!exists)
		{
			//Create the person and set the manager field to true.
			Person per = new Person(p);
			per.setManager(true);
			a_person(p);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_manager(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getManager();
			}
		}
		return false;	
	}

	@Override
	public void a_smoker(String p) 
	{
		//Boolean to tell if the person exists.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set smoker to true for this person.
				people.get(i).setSmoker(true);
				exists = true;
				break;
			}
		}
		//Person wasn't found.
		if (!exists)
		{
			//Create the person and set the apropriate fields.
			Person per = new Person(p);
			per.setSmoker(true);
			a_person(p);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_smoker(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getSmoker();
			}
		}
		return false;	
	}

	@Override
	public void a_hacker(String p) 
	{
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set hacker to true for this person.
				people.get(i).setHacker(true);
				exists = true;
				break;
			}
		}
		//Person didn't exist
		if (!exists)
		{
			//Create the person and set the Hacker field to true
			Person per = new Person(p);
			per.setHacker(true);
			a_person(p);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
	}

	@Override
	public boolean e_hacker(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getHacker();
			}
		}
		return false;
	}

	@Override
	public void a_in_group(String p, String grp) 
	{
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the group name for this person.
				people.get(i).setGroup(grp);
				exists = true;
				break;
			}
		}
		//Person was not found.
		if (!exists)
		{
			//Create the person and set the appropriate fields.
			Person per = new Person(p);
			per.setGroup(grp);
			a_person(p);
			a_group(grp);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
		//Checks to see if the group exists in the group list.
		if (!groups.contains(grp))
		{
			//If not, add it.
			groups.add(grp);
		}
	}

	@Override
	public boolean e_in_group(String p, String grp) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getGroup().equals(grp);
			}
		}
		return false;	
	}

	@Override
	public void a_in_project(String p, String prj) 
	{
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the project name for this person.
				people.get(i).setProject(prj);
				exists = true;
				break;
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		if (!exists)
		{
			Person per = new Person(p);
			per.setProject(prj);
			a_person(p);
			a_project(prj);
			per.setName(p);
			people.add(per);
			//Add the persons name to the list of people.
			names.add(p);
		}
		//Check to see if the project already exists in the project list.
		if (!projects.contains(prj))
		{
			//If not, add it.
			projects.add(prj);
		}
	}

	
	//*******************************************************************************************************
	//*******************************************************************************************************
	//*******************************************************************************************************
	//GOT TO HERE********************************************************************************************
	//*******************************************************************************************************
	//*******************************************************************************************************
	//*******************************************************************************************************
	@Override
	public boolean e_in_project(String p, String prj) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getProject().equals(prj);
			}
		}
		return false;	
	}

	@Override
	public void a_heads_group(String p, String grp) 
	{
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the project name for this person.
				people.get(i).setGroupHead(grp);
				exists = true;
				break;
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		if (!exists)
		{
			Person per = new Person(p);
			per.setGroupHead(grp);
			a_person(p);
			a_group(grp);
			per.setName(p);
			people.add(per);
		}
	}

	@Override
	public boolean e_heads_group(String p, String grp) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getGroupHead().equals(grp);
			}
		}
		return false;
	}

	@Override
	public void a_heads_project(String p, String prj) 
	{
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the project name for this person.
				people.get(i).setProjectHead(prj);
				exists = true;
				break;
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		if (!exists)
		{
			Person per = new Person(p);
			per.setProjectHead(prj);
			a_person(p);
			a_project(prj);
			per.setName(p);
			people.add(per);
		}
	}

	@Override
	public boolean e_heads_project(String p, String prj) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getProjectHead().equals(prj);
			}
		}
		return false;
	}

	@Override
	public void a_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) 
	{
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the project name for this person.
				people.get(i).setWorksWith(p2s);
				exists = true;
				break;
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		if (!exists)
		{
			Person per = new Person(p);
			per.setWorksWith(p2s);
			a_person(p);
			per.setName(p);
			people.add(per);
		}
		
		for (int i = 0; i < p2s.size(); i++)
		{
			Pair<ParamType, Object> pair = p2s.pollFirst();
			String per = (String) pair.getValue();
			System.out.println(" -> PERSON: " + per);
			//check to see if everyone in the list is already a person
			for (int j = 0; j < people.size(); j++)
			{
				if (per.equals(people.get(j)))
				{
					break;
				}
				else
				{
					Person newPerson = new Person(per);
					a_person(per);
					newPerson.setName(per);
				}
			}
		}
	}

	@Override
	public boolean e_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getWorksWith().equals(p2s);
			}
		}
		return false;
	}

	//STEVE: We need to overload the worksWith method in the person class somehow.
	@Override
	public void a_works_with(String p, String p2) 
	{
		/*boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the worksWith field for this person.
				people.get(i).setWorksWith(p2);
				exists = true;
				break;
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		if (!exists)
		{
			Person per = new Person(p);
			per.setWorksWith(p2);
			a_person(p);
			per.setName(p);
			people.add(per);
		}
		exists = false;
		//Check to see if the second person exists
		for (int j = 0; j < people.size(); j++)
		{
			if (people.get(j).getName().equals(p2))
			{
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Person newPerson = new Person(p2);
			a_person(p2);
			newPerson.setName(p2);
		}*/
	}

	//Again, the worksWith method needs to be overloaded.
	//Even though there are no errors, this method will always return false.
	@Override
	public boolean e_works_with(String p, String p2) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getWorksWith().equals(p2);
			}
		}
		return false;

	}
	
	@Override
	//****************************************************************************************************************
	//a_assign_to is a method which creates the list of tuples that represents the solution as specified in the 	 *
	//output file.																									 *
	//****************************************************************************************************************
	public void a_assign_to(String p, String room) throws Exception 
	{
		//Boolean to check if the room already exists in the list of room/people pairs.
		boolean exists = false;
		//Loop to chekc the list of already instantiated rooms in the list of rooms.
		for (int i = 0; i < assignment.size(); i++)
		{
			//Check to see if the room matches a room already in the list.
			if (assignment.get(i).getRoom().equals(room))
			{
				exists = true;
				//Add the person in the assignment line to the room.  Don't add a duplicate room.
				assignment.get(i).setPeople(p);
				break;
			}
		}
		//Check if the room didn't exist in the list.
		if (!exists)
		{
			//If no match was found in the list of rooms, create a new tuple with the first element being the specified room and
			//the second element being the person being assigned to the room.
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
		}
		for (int i=0;i<people.size();i++)
		{
			System.out.println(people.get(i).getName());
		}*/
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				return people.get(i).getRoom().getName().equals(room);
			}
		}
		return false;
	}

	@Override
	public void a_room(String r) {
		boolean exists = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Room room = new Room(r);
		}
	}

	@Override
	public boolean e_room(String r) {
		//Loop to find the room in the list of rooms names we have.
		for (int i = 0; i<rooms.size(); i++)
		{
			//Compares the room name
			if (rooms.get(i).getName().equals(r))
			{
				return true;
			}
		}
		return false;
	}

	//Again, for these two methods we're going to have to do some crazy stuff like worksWith
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
		boolean exists = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(room))
			{
				rooms.get(i).setClose(set);
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Room newRoom = new Room(room);
			newRoom.setClose(set);
		}
	}

	@Override
	public boolean e_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		for (int i = 0; i<rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(room))
			{
				return rooms.get(i).getClose().equals(set);
			}
		}
		return false;
	}

	@Override
	public void a_large_room(String r) {
		boolean exists = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				rooms.get(i).setSize("large");
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Room room = new Room(r);
			room.setSize("large");
		}
	}

	@Override
	public boolean e_large_room(String r) {
		for (int i = 0; i<rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				return rooms.get(i).getSize().equals("large");
			}
		}
		return false;
	}

	@Override
	public void a_medium_room(String r) {
		boolean exists = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				rooms.get(i).setSize("medium");
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Room room = new Room(r);
			room.setSize("medium");
		}		
	}

	@Override
	public boolean e_medium_room(String r) {
		for (int i = 0; i<rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				return rooms.get(i).getSize().equals("medium");
			}
		}
		return false;
	}

	@Override
	public void a_small_room(String r) {
		boolean exists = false;
		for (int i = 0; i < rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				rooms.get(i).setSize("small");
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Room room = new Room(r);
			room.setSize("small");
		}				
	}

	@Override
	public boolean e_small_room(String r) {
		for (int i = 0; i<rooms.size(); i++)
		{
			if (rooms.get(i).getName().equals(r))
			{
				return rooms.get(i).getSize().equals("small");
			}
		}
		return false;

	}

	@Override
	public void a_group(String g) {
		
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
