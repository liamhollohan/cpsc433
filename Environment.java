package cpsc433;

import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Environment extends PredicateReader implements SisyphusPredicates{

	//Various arrays to hold all of the data read in from the file.
	private static ArrayList<String> peopleNames = new ArrayList<String>();
	private static ArrayList<Person> people = new ArrayList<Person>();
	private static ArrayList<String> groupNames = new ArrayList<String>();
	private static ArrayList<Group> groups = new ArrayList<Group>();
	private static ArrayList<String> projectNames = new ArrayList<String>();
	private static ArrayList<Project> projects = new ArrayList<Project>();
	private static ArrayList<String> roomNames = new ArrayList<String>();
	private static ArrayList<Room> rooms = new ArrayList<Room>();

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
		groups.clear();
		projectNames.clear();
		projects.clear();
		roomNames.clear();
		rooms.clear();
		people.clear();
	}
	
	public ArrayList<String> getPeopleNames()
	{
		return peopleNames;
	}
	
	public ArrayList<String> getRoomNames()
	{
		return roomNames;
	}
	
	public ArrayList<Person> getPeople()
	{
		return people;
	}
	
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
	
	public ArrayList<Group> getGroups()
	{
		return groups;
	}
	
	public ArrayList<Project> getProjects()
	{
		return projects;
	}
	
	@Override
	public void a_person(String p) 
	{
		//Check to see if the person doesn't already exist.
		if(!peopleNames.contains(p))
		{
			//Create the person and set their name.
			Person newPerson = new Person(p);
			people.add(newPerson);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_person(String p) {
		//Loop to find the person in the list of people supplied in the input file.
		for (int i = 0; i<peopleNames.size(); i++)
		{
			//Check the persons name.
			if (peopleNames.get(i).equals(p))
			{
				return true; //if they exist...
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
		} else {
			//Person doesn't exist yet so create the person and set their name and set the secretary field to true.
			Person newPerson = new Person(p);
			newPerson.setSecretary(true);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			Person newPerson = new Person(p);
			newPerson.setResearcher(true);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			Person newPerson = new Person(p);
			newPerson.setManager(true);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			Person newPerson = new Person(p);
			newPerson.setSmoker(true);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			Person newPerson = new Person(p);
			newPerson.setHacker(true);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			//Person doesn't exist yet so create the person and set their name and set the group field.
			Person newPerson = new Person(p);
			newPerson.setGroup(grp);
			//Add the new person to both our lists.
			people.add(newPerson);
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
			//Person doesn't exist yet so create the person and set their name and set the project field to true.
			Person newPerson = new Person(p);
			people.add(newPerson);
			peopleNames.add(p);
			newPerson.setProject(prj);
		}
		//Check to see if the project already exists.
		if (!projectNames.contains(prj))
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
			//Person doesn't exist yet so create the person and set their name and set the group they head.
			Person newPerson = new Person(p);
			//Add the new person to both our lists.
			people.add(newPerson);
			peopleNames.add(p);
			newPerson.setGroupHead(grp);
		}
		//Check to see if the group already exists.
		if (!groupNames.contains(grp))
		{
			//If not, add it to both of our lists.
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
					people.get(i).setProjectHead(prj);
					break;
				}
			}
		}
		else
		{
			//Person doesn't exist yet so create the person and set their name and set the project field to true.
			Person newPerson = new Person(p);
			people.add(newPerson);
			peopleNames.add(p);
			newPerson.setProjectHead(prj);
		}
		//Check to see if the project already exists.
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
		for (Pair<ParamType, Object> pair : p2s) {
			String personName = (String)pair.getValue();
			a_works_with(p, personName);
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
		//Loop to find the person in the list of people supplied in the input file.
		if (peopleNames.contains(p))
		{
			for (int i = 0; i<people.size(); i++)
			{
				//Check the persons name.
				if (people.get(i).getName().equals(p))
				{
					//Set the person that the person works with.
					people.get(i).setWorksWith(p2);
					break;
				}
			}
		}
		//If the person doesn't exist, create a new person with the proper values.
		else
		{
			Person per = new Person(p);
			per.setWorksWith(p2);
			people.add(per);
			//Add the persons name to the list of people.
			peopleNames.add(p);
		}
	}

	@Override
	public boolean e_works_with(String p, String p2) {
		if (peopleNames.contains(p))
		{
			for (int i = 0; i < people.size(); i++)
			{
				if (people.get(i).getName().equals(p) && people.get(i).getWorksWith().contains(p2))
					return true;
			}
		}
		return false;
	}
	
	@Override
	//****************************************************************************************************************
	//a_assign_to is a method which creates the list of tuples that represents the solution as specified in the 	 *
	//output file.																									 *
	//****************************************************************************************************************
	public void a_assign_to(String p, String room) 
	{	
		SisyphusI.output.print("assign-to("+p+", "+room+")\n");
	}

	@Override
	public void a_room(String r) {
		if (!roomNames.contains(r))
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
		//If the first room doesn't exists
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
					return rooms.get(i).getClose().contains(room2);
				}
			}
		}
		return false;
	}

	@Override
	public void a_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		for (Pair<ParamType, Object> pair : set) {
			String room2 = (String) pair.getValue();
			a_close(room, room2);
		}
	}

	@Override
	public boolean e_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		for (Pair<ParamType, Object> pair : set) {
			String room2 = (String) pair.getValue();
			if (e_close(room, room2) == false)
				return false;
		}
		return true;
	}

	@Override
	public void a_large_room(String r) {
		if (roomNames.contains(r))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(r))
				{
					rooms.get(i).setSize("large");
					break;
				}
			}
		}
		else
		{
			Room room = new Room(r);
			room.setSize("large");
			rooms.add(room);
			roomNames.add(r);
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
		if (roomNames.contains(r))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(r))
				{
					rooms.get(i).setSize("medium");
					break;
				}
			}
		}
		else
		{
			Room room = new Room(r);
			room.setSize("medium");
			rooms.add(room);
			roomNames.add(r);
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
		if (roomNames.contains(r))
		{
			for (int i = 0; i < rooms.size(); i++)
			{
				if (rooms.get(i).getName().equals(r))
				{
					rooms.get(i).setSize("small");
					break;
				}
			}
		}
		else
		{
			Room room = new Room(r);
			room.setSize("small");
			rooms.add(room);
			roomNames.add(r);
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
			Group newGroup = new Group(g);
			groups.add(newGroup);
			groupNames.add(g);
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
		if (projectNames.contains(prj))
		{
			for (int i = 0; i < projects.size(); i++)
			{
				if (projects.get(i).getName().equals(prj))
				{
					projects.get(i).setLarge(true);
					break;
				}
			}
		}
		else
		{
			Project project= new Project(prj);
			project.setLarge(true);
			projects.add(project);
			projectNames.add(prj);
		}
	}

	@Override
	public boolean e_large_project(String prj) {
		for (int i = 0; i < projects.size(); i++)
			if (projects.get(i).getName().equals(prj))
				return projects.get(i).getLarge();
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
		a_in_project(p, prj);
	}

	@Override
	public boolean e_project(String p, String prj) {
		return e_in_project(p, prj);
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		return false;
	}
}
