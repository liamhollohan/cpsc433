package cpsc433;

public class Project 
{
	private String name;
	private boolean large;
	
	public Project(String name)
	{
		this.name = name;
		this.large = false;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setLarge(boolean large)
	{
		this.large = large;
	}
	
	public boolean getLarge()
	{
		return this.large;
	}
}
