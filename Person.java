package cpsc433;

import java.util.ArrayList;

public class Person {

	private String name;
	private boolean secretary;
	private boolean researcher;
	private boolean manager;
	private Boolean smoker;
	private Boolean hacker;
	private String project;
	private String group;
	private ArrayList<String> worksWith = new ArrayList<String>();
	private String projectHead = "None";
	private String groupHead = "None";
	private Boolean assigned;
	private Room room;


	public Person(String name)
	{
		this.name = name;
		this.secretary = false;
		this.smoker = false;
		this.hacker = false;
		this.manager = false;
		this.project = "None";
		this.group = "None";
		this.projectHead = "None";
		this.groupHead = "None";
		this.assigned = false;
		this.room = null;
	}
	
	//Getters and setters for the person class.
	public static String toString(Person p)
	{
		return "";
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
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
	
	public ArrayList<String> getWorksWith()
	{
		return this.worksWith;
	}
	
	public void setWorksWith(String worksWith) 
	{
		if (!this.worksWith.contains(worksWith))
			this.worksWith.add(worksWith);
	}

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