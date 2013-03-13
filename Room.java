package cpsc433;

import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Room 
{
	private String name;
	private String size;
	private Person[] people;
	private TreeSet<Pair<ParamType, Object>> close;
	
	public Room(String name)
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
	
	public void setPeople(Person[] people)
	{
		this.people = people;
	}
	
	public Person[] getPeople()
	{
		return this.people;
	}
	
	public void setClose(TreeSet<Pair<ParamType, Object>> close)
	{
		this.close = close;
	}
	
	public TreeSet<Pair<ParamType, Object>> getClose()
	{
		return this.close;
	}
}
