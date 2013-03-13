package cpsc433;

import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Environment extends PredicateReader implements SisyphusPredicates{

	private static ArrayList<String> peopleNames = new ArrayList<String>();
	private static ArrayList<String> groupNames = new ArrayList<String>();
	private static ArrayList<Group> groups = new ArrayList<Group>();
	private static ArrayList<String> projectNames = new ArrayList<String>();
	private static ArrayList<Project> projects = new ArrayList<Project>();
	private static ArrayList<String> roomNames = new ArrayList<String>();
	private static ArrayList<Room> rooms = new ArrayList<Room>();
	private static ArrayList<Person> people = new ArrayList<Person>();
	private static ArrayList<Tuple> assignment = new ArrayList<Tuple>();
	
	public Environment(PredicateReader p) 
	{
		super(p);		
	}

	public boolean fixedAssignments;
	public Solution currentSolution;
	
	public static void reset() 
	{
		//Resets all ArrayList values.
		peopleNames.clear();
		groupNames.clear();
		//groups.clear();
		projectNames.clear();
		projects.clear();
		//roomNames.clear();
		rooms.clear();
		people.clear();
		assignment.clear();
	}
	
	public ArrayList<Tuple> getAssignment()
	{
		return assignment;
	}
	
	public ArrayList<Person> getPeople()
	{
		return people;
	}
	
	public static Environment get() 
	{
		return null;
	}
	
	@Override
	public void a_person(String p) 
	{
		//Check to see if the person doesn't already exist.
		if(!peopleNames.contains(p))
		{
			//Create the person and set their name.
			Person per = new Person(p);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		System.out.println(" -> Size: " + peopleNames.size());
		for (int i = 0; i < peopleNames.size(); i++)
		{
			System.out.println(" -> Names: " + peopleNames.get(i));
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Person was found so update secretary to true.
					people.get(i).setSecretary(true);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the secretary field to true.
			Person per = new Person(p);
			per.setSecretary(true);
			people.add(per);
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_secretary(String p) 
	{
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
	public void a_researcher(String p) 
	{
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Person was found so update researcher to true.
					people.get(i).setResearcher(true);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the researcher field to true.
			Person per = new Person(p);
			per.setResearcher(true);
			people.add(per);
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_researcher(String p) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Person was found so update manager to true.
					people.get(i).setManager(true);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the manager field to true.
			Person per = new Person(p);
			per.setManager(true);
			people.add(per);
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_manager(String p) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Person was found so update smoker to true.
					people.get(i).setSmoker(true);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the smoker field to true.
			Person per = new Person(p);
			per.setSmoker(true);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_smoker(String p) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Person was found so update hacker to true.
					people.get(i).setHacker(true);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the hacker field to true.
			Person per = new Person(p);
			per.setHacker(true);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}	
	}

	@Override
	public boolean e_hacker(String p) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to find the person in the list of people supplied in the input file.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Set the group name for this person.
					people.get(i).setGroup(grp);
					break;
				}
			}
		}
		else
		{
			//Person was not found so create the person and set the appropriate fields.
			Person per = new Person(p);
			per.setGroup(grp);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		//Checks to see if the group exists in the group list.
		if (!groupNames.contains(grp))
		{
			//If not, add it.
			Group newGroup = new Group(grp);
			groups.add(newGroup);
			groupNames.add(grp);
		}
	}

	@Override
	public boolean e_in_group(String p, String grp) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to find the person in the list of people supplied in the input file.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Set the project name for this person.
					people.get(i).setProject(prj);
					break;
				}
			}
		}
		else
		{
			//Person never existed.  Create them and populate the proper values.
			Person per = new Person(p);
			per.setProject(prj);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		//Check to see if the project already exists.
		if (!projects.contains(prj))
		{
			//If not, add it.
			Project newProject = new Project(prj);
			projects.add(newProject);
			projectNames.add(prj);
		}
	}

	@Override
	public boolean e_in_project(String p, String prj) 
	{
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
		//Check to see if the person already exists.
		if(peopleNames.contains(p))
		{
			//Loop to search for the person.
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Set this person as a group head
					people.get(i).setGroupHead(grp);
					break;
				}
			}
		}
		else
		{
			//Person didn't exist so create them and populate the proper fields.
			Person per = new Person(p);
			per.setGroupHead(grp);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		if (!groupNames.contains(grp))
		{
			//If not, add it.
			Group newGroup = new Group(grp);
			groups.add(newGroup);
			groupNames.add(grp);
		}
	}

	@Override
	public boolean e_heads_group(String p, String grp) 
	{
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
		//Boolean to detect if the person exists.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the person as a project head.
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
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		if (!projectNames.contains(prj))
		{
			//If not, add it.
			Project newProject = new Project(prj);
			projects.add(newProject);
			projectNames.add(prj);
		}
	}

	@Override
	public boolean e_heads_project(String p, String prj) 
	{
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
		//Boolean to detect if the person exists.
		boolean exists = false;
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Set the group that the person works with.
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
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		
		for (int i = 0; i < p2s.size(); i++)
		{
			Pair<ParamType, Object> pair = p2s.pollFirst();
			String per = (String) pair.getValue();
			//System.out.println(" -> PERSON: " + per);
			//check to see if everyone in the list is already a person
			exists = false;
			for (int j = 0; j < people.size(); j++)
			{
				if (per.equals(people.get(j).getName()))
				{
					exists = true;
					break;
				}
				if (!exists)
				{
					Person newPerson = new Person(per);
					people.add(newPerson);
					//Add the persons name to the list of people if they don't exist.
					peopleNames.add(p);
				}
			}
		}
	}

	@Override
	public boolean e_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) 
	{
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

	@Override
	public void a_works_with(String p, String p2) {
	}

	@Override
	public boolean e_works_with(String p, String p2) {
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
		//Loop to check the list of already instantiated rooms in the list of rooms.
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
		//For testing:
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
			rooms.add(room);
			roomNames.add(r);
		}
	}

	@Override
	public boolean e_room(String r) {
		return roomNames.contains(r);
	}

	@Override
	public void a_close(String room, String room2) {
		if (roomNames.contains(room))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(room))
				{
					rooms.get(i).setClose(room2);
					break;
				}
			}
		}
		else
		{
			Room newRoom = new Room(room);
			newRoom.setClose(room2);
			rooms.add(newRoom);
			roomNames.add(room);
		}
		//Check to see if the second room exists.
		if (roomNames.contains(room2))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(room2))
				{
					rooms.get(i).setClose(room);
					break;
				}
			}
		}
		else
		{
			Room newRoom = new Room(room2);
			newRoom.setClose(room);
			rooms.add(newRoom);
			roomNames.add(room2);
		}
	}

	@Override
	public boolean e_close(String room, String room2) {
		if (roomNames.contains(room))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(room))
				{
					return rooms.get(i).getClose2().contains(room2);
				}
			}
		}
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
			rooms.add(newRoom);
			roomNames.add(room);
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
		//Check to see if the group already exists.
		if(!groupNames.contains(g))
		{
			//group was not found so create the group.
			groupNames.add(g);
			Group newGroup = new Group(g);
			groups.add(newGroup);
		}
	}

	@Override
	public boolean e_group(String g) {
		return groupNames.contains(g);
	}

	@Override
	public void a_project(String p) {
		//Check to see if the project already exists.
		if(!projectNames.contains(p))
		{
			//projects was not found so create the project.
			Project prj = new Project(p);
			projects.add(prj);
			//Add project to the array list
			projectNames.add(p);
		}
	}

	@Override
	public boolean e_project(String p) {
		return projectNames.contains(p);
	}

	@Override
	public void a_large_project(String prj) {
		boolean exists = false;
		for (int i = 0; i < projects.size(); i++)
		{
			if (projects.get(i).getName().equals(prj))
			{
				projects.get(i).setSize("large");
				exists = true;
				break;
			}
		}
		if (!exists)
		{
			Project p = new Project(prj);
			p.setSize("large");
			projectNames.add(prj);
		}
	}

	@Override
	public boolean e_large_project(String prj) {
		for (int i = 0; i < projects.size(); i++)
			if (projects.get(i).getName().equals(prj))
				return projects.get(i).getSize().equals("large");
		return false;
	}

	@Override
	public void a_group(String p, String grp) {
		a_in_group(p, grp);
	}

	@Override
	public boolean e_group(String p, String grp) {
		return e_in_group(p, grp);
	}

	@Override
	public void a_project(String p, String prj) {
		//Boolean to detect if the person exists or not.
		boolean exists = false;
		//Loop to search for the person.
		for (int i = 0; i<people.size(); i++)
		{
			//Check the persons name.
			if (people.get(i).getName().equals(p))
			{
				//Person was found so update the field within.
				people.get(i).setProject(prj);
				exists = true;
				break;
			}
		}
		//Person was not found in the list of people
		if (!exists)
		{
			//Create the person and set their name.
			Person per = new Person(p);
			per.setProject(prj);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
		
		//Check if the project exists
		if (!projectNames.contains(prj))
		{
			Project newProject = new Project(prj);
			projects.add(newProject);
			projectNames.add(prj);
		}
	}

	@Override
	public boolean e_project(String p, String prj) {
		return e_in_project(p, prj);
	}

}
