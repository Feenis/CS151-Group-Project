
public class Event 
{
	String title;
	String date;
	String startTime;
	String endTime;
	
	public Event(String newTitle, String newDate, String newStart, String newEnd)
	{
		title 		= newTitle;
		date  		= newDate;
		startTime 	= newStart;
		endTime 	= newEnd;
	}
	
	public String printEvent(Event event)
	{
		return  event.date + " " + event.startTime +" - "+ event.endTime + " " + event.title;
	}

}
