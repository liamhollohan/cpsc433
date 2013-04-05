package cpsc433;

//****************************************************************************************************************
//Tuple is a class which holds a room number and name of a person that is assigned to a room.  It acts as a pair *
//in which the first element is a room name represented as a sting and the second element is another string      *
//representing the person assigned to the room.																	 *
//****************************************************************************************************************
public class Tuple {

	private Room room;
	private Person name;
	
	//Constructor for the class tuple.  It takes in a room and a person and assigns them as a pair.
	public  Tuple(Room room, Person person)
	{
		setRoom(room);
		setPerson(person);
	}
	
	public void setPerson(Person person) 
	{
		this.name = person;
		
	}
	
	public Person getPerson()
	{
		return this.name;
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
	
