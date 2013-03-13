package cpsc433;

public class Project 
{
	private String name;
	private String size;
	
	public Project(String name)
	{
		this.name = name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setSize(String size)
	{
		this.size = size;
	}
	
	public String getSize()
	{
		return this.size;
	}
}
