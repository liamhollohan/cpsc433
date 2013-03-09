package cpsc433;

import java.util.ArrayList;

public class Tuple {

	private String room;
	private ArrayList<String> people = new ArrayList();
	
	public  Tuple(String room, String person)
	{
		setRoom(room);
		setPeople(person);
	}
	
	public void setRoom(String room)
	{
		this.room = room;
	}
	
	public String getRoom()
	{
		return this.room;
	}
	
	public void setPeople(String person)
	{
		if (!(this.people).contains(person))
			(this.people).add(person);
		else
			System.out.println(" -> Error (SetPeople)");
	}
	
	public ArrayList<String> getPeople()
	{
		return this.people;
	}
}
