package cpsc433;

import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Person {

	private String name;
	private Boolean smoker;
	private Boolean hacker;
	private String project;
	private String group;
	private String[] worksWith;
	private String projectHead = "None";
	private String groupHead = "None";
	private boolean isManager = false;
	private char role;
	private Boolean assigned;
	private Room room;
	
	public Person(String name, Boolean smoker, Boolean hacker, String project, String group, String[] worksWith, String projectHead, String groupHead, char role, Boolean assigned, Room room)
	{
		setName(name);
		setSmoker(smoker);
		setHacker(hacker);
		setProject(project);
		setGroup(group);
		setWorksWith(worksWith);
		setProjectHead(projectHead);
		setGroupHead(groupHead);
		setRole(role);
		setAssigned(assigned);
		setRoom(room);
	}
	
	public Person() {
		
	}

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
	
	public void setWorksWith(String[] worksWith)
	{
		this.worksWith = worksWith;
	}
	
	public String[] getWorksWith()
	{
		return this.worksWith;
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
	
	public void setIsManager(boolean isManager)
	{
		this.isManager = isManager;
	}
	
	public boolean getIsManager()
	{
		return this.isManager;
	}
	
	public void setRole(char role)
	{
		this.role = role;
	}
	
	public char getRole()
	{
		return this.role;
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