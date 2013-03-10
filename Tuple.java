package cpsc433;

import java.util.ArrayList;

//****************************************************************************************************************
//Tuple is a class which holds a room number and a list of people that are assigned to a room.  It acts as a pair*
//in which the first element is a room name represented as a sting and the second element is an arraylist of     *
//strings which represent the people assigned to a room.  The arraylist will be at most two people long as       *
//specified by the hard constraints.																			 *
//****************************************************************************************************************
public class Tuple {

	private String room;
	private ArrayList<String> people = new ArrayList();
	
	//Constructor for the class tuple.  It takes in a room and a person and assigns them as a pair.
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
	
	//****************************************************************************************************************
	//Set people is a method which adds a person to a room.  If the person already exists in the room,  an error is  *
	//output to the console.  Otherwise, the person is added to the rear of the arraylist.							 *
	//****************************************************************************************************************
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
