package cpsc433;

import java.util.ArrayList;
import java.util.TreeSet;

import cpsc433.Predicate.ParamType;

public class Room 
{
	private String name;
	private String size;
	private int numAssigned = 0;
	private ArrayList<String> close = new ArrayList<String>();
	private boolean isFull = false;
	
	public Room(String name)
	{
		this.name = name;
		this.size = "None";
		this.numAssigned = 0;
		this.isFull = false;
	}
	
	public int getNumAssigned() 
	{
		return numAssigned;
	}

	public void setNumAssigned(int numAssigned) 
	{
		this.numAssigned = numAssigned;
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
	
	public ArrayList<String> getClose()
	{
		return this.close;
	}
	
	public void setClose(String close)
	{
		if (!this.close.contains(close))
			this.close.add(close);
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
}
