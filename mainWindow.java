import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


@SuppressWarnings("serial")
public class mainWindow extends JPanel
{
	private JPanel    	paneMainMenu	= new JPanel();
	
	private JLabel    	lblMonthName	= new JLabel();
	private JTextArea 	txtMonthView	= new JTextArea();
	private JButton   	btnNextMonth	= new JButton("Next");
	private JButton   	btnPrevMonth	= new JButton("Previous");
	
	private JLabel    	lblDayView 		= new JLabel();
	private JTextArea 	txtDayView   	= new JTextArea("There are no events scheduled for today!\n\nTry Refreshing today's events!");
	private JButton   	btnCreate 		= new JButton("Create");
	private JButton   	btnGoTo   		= new JButton("Go To...");
	private JButton   	btnDelete 		= new JButton("Delete");
	private JButton   	btnQuit   		= new JButton("Quit");
	private JButton 	btnRefresh 		= new JButton("Refresh Today's Events");
	
	DateFormatter 				df  				= new DateFormatter(new SimpleDateFormat("MM/dd/yyyy"));
	private JFormattedTextField frmDateOfEvent 		= new JFormattedTextField(df);
	String dateOfEvent = "";
	
	public mainWindow(JPanel contentPane, final EventCreator eventList, final CalendarCreator curMonth) 
	{
		paneMainMenu = contentPane;
		setPreferredSize (new Dimension(600, 400));
        setLayout (null);        
        
      //********************************************************************* DEALS WITH MONTH VIEW
        lblMonthName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblMonthName.setBounds(10, 19, 219, 20);
        lblMonthName.setText(curMonth.getMonthYear());
        
        txtMonthView.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtMonthView.setColumns(30);
        txtMonthView.setEditable(false);
        txtMonthView.setBounds(10, 50, 220, 130);
        txtMonthView.setText(curMonth.printCalendar(curMonth.getAmountOfDaysInCurrentMonth(), curMonth.getFirstDateOfCurrentMonth()));
        
        btnNextMonth.setBounds(129, 191, 100, 20);
        btnNextMonth.setMnemonic('N');
        btnNextMonth.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ changeToNextMonth(curMonth); }
        });
        
        btnPrevMonth.setBounds(10, 191, 100, 20);
        btnPrevMonth.setMnemonic('P');
        btnPrevMonth.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ changeToPrevMonth(curMonth); }
        });
        
        //********************************************************************* DEALS WITH DAY VIEW
        lblDayView.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDayView.setBounds(371, 19, 220, 20);
        lblDayView.setText("Today is: " + curMonth.getCurDate());
        
        btnRefresh.setBounds(251, 18, 219, 23);
        
        txtDayView.setBounds(240, 50, 350, 270);
        txtDayView.setEditable(false);  
        
        btnRefresh.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				ArrayList<Event> foundEvents = new ArrayList<Event>();
		        foundEvents = eventList.searchForEvent(curMonth.getCurDate());
		        String allEventsForToday = "";
		        
		        if (!foundEvents.isEmpty())
		        {
		        	for(Event event: foundEvents)
		        	{
		        		allEventsForToday += " " + event.printEvent(event) + "\n";
		        	}
		        	txtDayView.setText("Today's event(s):\n" + allEventsForToday);
		        }
		        else
		        {
		        	txtDayView.setText("There are no events scheduled for today!\n\nTry Refreshing today's events!");
		        }	
			}
        });
        
        
        
        btnCreate.setBounds(240, 369, 166, 20);
        btnCreate.setMnemonic('C');
        btnCreate.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				CardLayout cardLayout = (CardLayout) paneMainMenu.getLayout();
                cardLayout.show(paneMainMenu, "createWindowPanel");
			}
        });
        
        btnGoTo.setBounds(240, 338, 166, 20);
        btnGoTo.setMnemonic('G');
        btnGoTo.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				ArrayList<Event> foundEvents = new ArrayList<Event>();
		        foundEvents = eventList.searchForEvent(dateOfEvent);
		        String allEventsForToday = "";
		        
		        if (!foundEvents.isEmpty())
		        {
		        	for(Event event: foundEvents)
		        	{
		        		allEventsForToday += " " + event.printEvent(event) + "\n";
		        	}
		        	txtDayView.setText("Event(s) for " + dateOfEvent + ":\n" + allEventsForToday);
		        }
		        else
		        {
		        	txtDayView.setText("There are no events scheduled for "+ dateOfEvent +"!\n\nTry Refreshing today's events!");
		        }	
			}
        });
        
        dateOfEvent = curMonth.getCurDate();
        frmDateOfEvent.setValue(new Date());
		frmDateOfEvent.setBounds(425, 338, 166, 20);
		frmDateOfEvent.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e) 
			{ 
				dateOfEvent = frmDateOfEvent.getText() ;
            }
			@Override
			public void focusGained(FocusEvent e) 
			{ 
				frmDateOfEvent.setText("");
            }
		});
        
        btnDelete.setBounds(425, 369, 166, 20);
        btnDelete.setMnemonic('D');
        btnDelete.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				CardLayout cardLayout = (CardLayout) paneMainMenu.getLayout();
                cardLayout.show(paneMainMenu, "deleteWindow");
			}
        });
        
        btnQuit.setBounds(10, 369, 60, 20);
        btnQuit.setMnemonic('Q');
        btnQuit.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				printToFile(eventList);
				System.exit(0);
			}
        });
        
        
        add (lblMonthName);
        add (txtMonthView);
        add (btnNextMonth);
        add (btnPrevMonth);
        add (lblDayView);
        add (txtDayView);
        add (btnCreate);
        add (btnGoTo);
        add (btnDelete);
        add (btnQuit);
        add (frmDateOfEvent);   
        add(btnRefresh);
	}
	
	public void changeToPrevMonth(CalendarCreator curMonth)
	{
		curMonth.prevCalendar();
		txtMonthView.setText(curMonth.printCalendar(curMonth.getAmountOfDaysInCurrentMonth(), curMonth.getFirstDateOfCurrentMonth()));
		lblMonthName.setText(curMonth.getMonthYear());
	}
	
	public void changeToNextMonth(CalendarCreator curMonth)
	{
		curMonth.nextCalendar();
		txtMonthView.setText(curMonth.printCalendar(curMonth.getAmountOfDaysInCurrentMonth(), curMonth.getFirstDateOfCurrentMonth()));
		lblMonthName.setText(curMonth.getMonthYear());
	}
	
	public void printToFile(EventCreator eventList)
	{
		try 
		{
			File file = new File("reservation.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			
			bw.write(eventList.printAllEvents());
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

}
