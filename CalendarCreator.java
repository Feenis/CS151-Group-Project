
/**
 * Deals with month view
 * 
 * @author  Nikita Pankratov
 * @version 10/19/2013
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class CalendarCreator extends GregorianCalendar
{
	GregorianCalendar newCalendar;
	
	public CalendarCreator()
	{
		newCalendar = new GregorianCalendar();
	}
	
	public void nextCalendar()
	{
		this.set(this.MONTH, this.get(this.MONTH)+1);
	}
	
	public void prevCalendar()
	{
		this.set(this.MONTH, this.get(this.MONTH)-1);
	}
	
    public String getMonthYear() 
    {
        return this.getDisplayName(this.MONTH, this.LONG, Locale.US) + " " + this.get(this.YEAR);
    }
    
    public String getCurDate()
    {
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	Date today = new Date();
        return formatter.format(today);
    }
       
    public String printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) 
    {
    	String printMonth = "Su  Mo  Tu  We  Th  Fr  Sa\n";
        int weekdayIndex = 0; // reset index of weekday
        
        for (int day = 1; day < firstWeekdayOfMonth; day++) 
        {
        	printMonth += "    ";
            weekdayIndex++;
        }

        // print the days of month in tabular format.
        for (int day = 1; day <= numberOfMonthDays; day++) 
        {
            // print day
        	printMonth += String.format("%1$2d", day);;

            // next weekday
            weekdayIndex++;
            // if it is the last weekday
            if (weekdayIndex == 7) 
            {
                // reset it
                weekdayIndex = 0;
                // and go to next line
                printMonth += "\n";
            } 
            else 
            {
            	printMonth += "  ";
            }
        }

        // print a final new-line.
        printMonth += "\n";
        
        return printMonth;
    }
    
    public int getFirstDateOfCurrentMonth() 
    {
    	this.set(this.DAY_OF_MONTH, this.getInstance().getActualMinimum(this.DAY_OF_MONTH));
    	
    	return this.get(DAY_OF_WEEK);
    }
    
    public int getAmountOfDaysInCurrentMonth()
    {
    	return this.getActualMaximum(this.DAY_OF_MONTH);
    }
}
