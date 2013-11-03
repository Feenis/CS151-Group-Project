import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;


public class deleteWindow extends JPanel
{
	private JPanel    			paneDeleteWindow	= new JPanel();
	
	DateFormatter 				df  				= new DateFormatter(new SimpleDateFormat("MM/dd/yyyy"));
	private JFormattedTextField frmDateOfEvent 		= new JFormattedTextField(df);
	
	private JButton 			btnBackToMain 		= new JButton("Back");
	private JButton 			btnDeleteAll 		= new JButton("Delete All Events");
	private JButton 			btnDeleteSelected	= new JButton("Delete Selected Day Events");
	private JLabel 				lblDate 			= new JLabel("Date of event to be deleted: ");
	private JTextArea   		txtEvent 			= new JTextArea();
	
	String dateOfEvent = "";
	
	public deleteWindow(JPanel contentPane, final EventCreator eventList, final CalendarCreator curMonth) 
	{
		paneDeleteWindow = contentPane;
		setPreferredSize (new Dimension(600, 400));
        setLayout (null);
		
        txtEvent.setText(eventList.printAllEvents());
		txtEvent.setEditable(false);
		txtEvent.setBounds(10, 170, 580, 188);
        
        btnDeleteAll.setBounds(342, 139, 248, 20);
        btnDeleteAll.setMnemonic('A');
        btnDeleteAll.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				System.out.println("\n ----------------------------------\n" + eventList.printAllEvents());
				eventList.deleteAllEvents();
				System.out.println(eventList.printAllEvents() + "\n ----------------------------------");
				txtEvent.setText("No events anymore");
            }
        });
        
        lblDate.setLabelFor(frmDateOfEvent);
		lblDate.setBounds(10, 42, 210, 14);
		frmDateOfEvent.setValue(new Date());
		frmDateOfEvent.setBounds(230, 39, 360, 20);
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
        
        btnDeleteSelected.setBounds(10, 139, 248, 20);
        btnDeleteSelected.setMnemonic('A');
        btnDeleteSelected.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				ArrayList<Event> foundEvents = new ArrayList<Event>();
				System.out.println("---------------------\n" + curMonth.getCurDate() + "\n---------------------");
		        foundEvents = eventList.searchForEvent(dateOfEvent);
		        String allDeletedEvents = "";
		        
		        if (!foundEvents.isEmpty())
		        {
		        	for(Event event: foundEvents)
		        	{
		        		allDeletedEvents += "Deleted event:\n " + event.printEvent(event);
		        		eventList.deleteEvent(event);
		        		txtEvent.setText(allDeletedEvents);
		        	}
		        	
		        }
		        else
		        {
		        	txtEvent.setText("There are no events scheduled for today!");
		        }
            }
        });
        
        
		btnBackToMain.setBounds(10, 369, 100, 20);
        btnBackToMain.setMnemonic('B');
        btnBackToMain.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				CardLayout cardLayout = (CardLayout) paneDeleteWindow.getLayout();
				cardLayout.show(paneDeleteWindow, "mainWindowPanel");
            }
        });
		
        add(txtEvent);
        add(btnDeleteAll);
        add(btnDeleteSelected);
		add(btnBackToMain);
		add(lblDate);
		add(frmDateOfEvent);
	}
}
