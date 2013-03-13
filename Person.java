package cpsc433;

import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Person {

	private String name;
	private boolean person;
	private boolean secretary;
	private boolean researcher;
	private boolean manager;
	private Boolean smoker;
	private Boolean hacker;
	private String project;
	private String group;
	private TreeSet<Pair<ParamType,Object>> worksWith;// = new TreeSet<Pair<ParamType,Object>>();
	private String projectHead = "None";
	private String groupHead = "None";
	private boolean isManager = false;
	private Boolean assigned;
	private Room room;
	
	//Constructor for the person class
	/*public Person(String name, Boolean secretary, Boolean smoker, Boolean hacker, String project, String group, TreeSet<Pair<ParamType,Object>> worksWith, String projectHead, String groupHead, Boolean assigned, Room room)
	{
		setName(name);
		setSecretary(secretary);
		setSmoker(smoker);
		setHacker(hacker);
		setProject(project);
		setGroup(group);
		setWorksWith(worksWith);
		setProjectHead(projectHead);
		setGroupHead(groupHead);
		setAssigned(assigned);
		setRoom(room);
	}*/

	public Person(String name)
	{
		this.name = name;
		this.person = true;
		this.secretary = false;
		this.smoker = false;
		this.hacker = false;
		this.project = "None";
		this.group = "None";
		this.worksWith = null;
		this.projectHead = "None";
		this.groupHead = "None";
		this.assigned = false;
		this.room = null;
	}
	
	//Getters and setters for the person class.
	public String toString(Person p)
	{
		return "Name: " + p.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setPerson(boolean person)
	{
		this.person = person;
	}
	
	public boolean getPerson()
	{
		return this.person;
	}
	
	public void setSecretary(boolean secretary)
	{
		this.secretary = secretary;
	}
	
	public boolean getSecretary()
	{
		return this.secretary;
	}
	
	public void setResearcher(boolean researcher)
	{
		this.researcher = researcher;
	}
	
	public boolean getResearcher()
	{
		return this.researcher;
	}
	
	public void setManager(boolean manager)
	{
		this.manager= manager;
	}
	
	public boolean getManager()
	{
		return this.manager;
	}
	
	public void setSmoker(Boolean smoker)
	{
		this.smoker = smoker;
	}
	
	public Boolean getSmoker()
	{
		return this.smoker;
	}
	
	public void setHacker(Boolean hacker)
	{
		this.hacker = hacker;
	}
	
	public Boolean getHacker()
	{
		return this.hacker;
	}
	
	public void setProject(String project)
	{
		this.project = project;
	}
	
	public String getProject()
	{
		return this.project;
	}
	
	public void setGroup(String group)
	{
		this.group = group;
	}
	
	public String getGroup()
	{
		return this.group;
	}
	
	public void setWorksWith(TreeSet<Pair<ParamType,Object>> worksWith)
	{
		this.worksWith = worksWith;
	}
	
	public TreeSet<Pair<ParamType,Object>> getWorksWith()
	{
		return this.worksWith;
	}
	
	/*public void setWorksWith(String worksWith) {
		Pair p = new Pair(this.name, worksWith);
		System.out.println(this.worksWith.pollFirst());
		if (this.worksWith.isEmpty())
		{
			this.worksWith = new TreeSet<Pair<ParamType,Object>>();
			this.worksWith.add(p);
		}
		this.worksWith.add(p);
		for (int i =0; i<this.worksWith.size(); i++)
		{
			Pair<ParamType, Object> pair = this.worksWith.pollFirst();
			String per = (String) pair.getValue();
			System.out.println(" -> VALUE: " + per);
		}
	}*/

	public void setProjectHead(String projectHead)
	{
		this.projectHead = projectHead;
	}
	
	public String getProjectHead()
	{
		return this.projectHead;
	}
	
	public void setGroupHead(String groupHead)
	{
		this.groupHead = groupHead;
	}
	
	public String getGroupHead()
	{
		return this.groupHead;
	}
	
	public void setIsManager(boolean isManager)
	{
		this.isManager = isManager;
	}
	
	public boolean getIsManager()
	{
		return this.isManager;
	}
	
	public void setAssigned(Boolean assigned)
	{
		this.assigned = assigned;
	}
	
	public Boolean getAssigned()
	{
		return this.assigned;
	}
	
	public void setRoom(Room room)
	{
		this.room = room;
	}
	
	public Room getRoom()
	{
		return this.room;
	}
}