package cpsc433;

public class Group 
{
	private String name;
	private String size;
	
	public Group(String name)
	{
		this.name = name;
		this.size = "none";
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
