package cpsc433;

import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Room 
{
	private String name;
	private String size;
	private Person[] people;
	private TreeSet<Pair<ParamType, Object>> close;
	private ArrayList<String> close2 = new ArrayList<String>();
	
	public Room(String name)
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
	
	public ArrayList<String> getClose2()
	{
		return this.close2;
	}
	
	public void setClose(String close)
	{
		this.close2.add(close);
	}
}
