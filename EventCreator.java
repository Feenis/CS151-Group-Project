import java.util.*;


@SuppressWarnings("serial")
public class EventCreator extends ArrayList<Event>
{
	ArrayList<Event> listOfEvents;
	
	public EventCreator ()
	{
		listOfEvents = new ArrayList<Event>();
	}
	
	public void addEventToList (Event newEvent)
	{
		listOfEvents.add(newEvent);
	}
	
	public void deleteEvent (Event newEvent)
	{
		listOfEvents.remove(newEvent);
	}
	
	public void deleteAllEvents ()
	{
		listOfEvents.clear();
	}
	
	public ArrayList<Event> searchForEvent(String date)
	{
		ArrayList<Event> foundEvents = new ArrayList<Event>();
		
		for(Event eventInList : listOfEvents) 
		{
	        if (date.equals(eventInList.date))
	        {
	        	foundEvents.add(eventInList);
	        }
	    }

		return foundEvents;
	}
	
	public String printAllEvents()
	{
		String allEvents = " ";
		
		for(Event eventInList : listOfEvents) 
		{
	        allEvents += eventInList.printEvent(eventInList) + "\n";
	    }
		return allEvents;
	}
}
