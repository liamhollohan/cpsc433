package cpsc433;

public class Person {

	private String name;
	private Boolean smoker;
	private Boolean hacker;
	private String project;
	private String group;
	private Person[] worksWith;
	private Boolean projectHead;
	private Boolean groupHead;
	private char role;
	private Boolean assigned;
	private Room room;
	
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
	
	public void setWorksWith(Person[] worksWith)
	{
		this.worksWith = worksWith;
	}
	
	public Person[] getWorksWith()
	{
		return this.worksWith;
	}
	
	public void setProjectHead(Boolean projectHead)
	{
		this.projectHead = projectHead;
	}
	
	public Boolean getProjectHead()
	{
		return this.projectHead;
	}
	
	public void setGroupHead(Boolean groupHead)
	{
		this.groupHead = groupHead;
	}
	
	public Boolean getGroupHead()
	{
		return this.groupHead;
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