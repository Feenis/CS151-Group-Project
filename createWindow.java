import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;



public class createWindow extends JPanel
{
	private JPanel    			paneCreateWindow	= new JPanel();
	EventCreator 				newEventList 		= new EventCreator();
	
	DateFormatter 				df  				= new DateFormatter(new SimpleDateFormat("MM/dd/yyyy"));

	private JLabel 				lblTitleOfEvent 	= new JLabel("Title of event: ");
	private JTextField 			txtTitleOfEvent 	= new JTextField();
	private JLabel 				lblDate 			= new JLabel("Date of event: ");
	private JFormattedTextField frmDateOfEvent 		= new JFormattedTextField(df);
	private JLabel 				lblStartingTime		= new JLabel("Starting time of event: ");
	private JTextField 			txtStartingTime 	= new JTextField();
	private JLabel 				lblEndingTime 		= new JLabel("Ending time of event: ");
	private JTextField 			txtEndingTime 		= new JTextField();
	private JButton 			btnPreview 			= new JButton("Preview:");
	private JButton 			btnSaveEvent		= new JButton("Save Event");
	private JTextArea 			txtPreview 			= new JTextArea();
	private JButton 			btnBackToMain 		= new JButton("Back");
	
	String titleOfEvent = "";
	String dateOfEvent 	= "";
	String StartingTime = "";
	String EndingTime 	= "23:59";
	
	public createWindow(JPanel contentPane, final EventCreator eventList, CalendarCreator curMonth) 
	{
		paneCreateWindow = contentPane;
		setPreferredSize (new Dimension(600, 400));
        setLayout (null);
		
        lblTitleOfEvent.setLabelFor(txtTitleOfEvent);
		lblTitleOfEvent.setBounds(10, 11, 130, 14);
		txtTitleOfEvent.setText("Sample event title");
		txtTitleOfEvent.setColumns(10);
		txtTitleOfEvent.setBounds(150, 8, 440, 20);
		txtTitleOfEvent.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e) 
			{ 
				titleOfEvent = txtTitleOfEvent.getText() ;
            }
			@Override
			public void focusGained(FocusEvent e) 
			{ 
				txtTitleOfEvent.setText("");
            }
		});
		
		lblDate.setLabelFor(frmDateOfEvent);
		lblDate.setBounds(10, 39, 130, 14);
		frmDateOfEvent.setValue(new Date());
		frmDateOfEvent.setBounds(150, 36, 440, 20);
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
		
		lblStartingTime.setLabelFor(txtStartingTime);
		lblStartingTime.setBounds(10, 67, 130, 14);
		txtStartingTime.setColumns(10);
		txtStartingTime.setBounds(150, 64, 100, 20);
		txtStartingTime.setText("00:00");
		txtStartingTime.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e) 
			{ 
				StartingTime = txtStartingTime.getText() ;
            }
			@Override
			public void focusGained(FocusEvent e) 
			{ 
				txtStartingTime.setText("");
            }
		});
		
		lblEndingTime.setLabelFor(txtEndingTime);
		lblEndingTime.setBounds(350, 67, 130, 14);
		txtEndingTime.setColumns(10);
		txtEndingTime.setBounds(490, 64, 100, 20);
		txtEndingTime.setText("23:59");
		txtEndingTime.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e) 
			{ 
				EndingTime = txtEndingTime.getText() ;
            }
			@Override
			public void focusGained(FocusEvent e) 
			{ 
				txtEndingTime.setText("");
            }
		});
		
		btnPreview.setBounds(10, 139, 130, 20);
		btnPreview.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				if (!(dateOfEvent.isEmpty() || StartingTime.isEmpty() || titleOfEvent.isEmpty() ))
				{
					txtPreview.setText(dateOfEvent + " " + StartingTime + " - " + EndingTime + " " + titleOfEvent);
				}
				else if (dateOfEvent.isEmpty())
				{
					txtPreview.setText("The date of event has not been set. Please enter all information.");
				}
				else if (StartingTime.isEmpty())
				{
					txtPreview.setText("The starting time of event has not been set. Please enter all information.");
				}
				else if (titleOfEvent.isEmpty())
				{
					txtPreview.setText("The title of event has not been set. Please enter all information.");
				}
			}
        });
		
		btnSaveEvent.setBounds(460, 139, 130, 20);
		btnSaveEvent.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				if (!(dateOfEvent.isEmpty() || StartingTime.isEmpty() || titleOfEvent.isEmpty() ))
				{
					eventList.addEventToList( new Event (titleOfEvent, dateOfEvent, StartingTime, EndingTime));
					txtPreview.setText("Your new event has been created:\n\t "+  dateOfEvent + " " + StartingTime + " - " + EndingTime + " " + titleOfEvent);
				}
				else if (dateOfEvent.isEmpty())
				{
					txtPreview.setText("The date of event has not been set hence the event was NOT created. Please enter all information.");
				}
				else if (StartingTime.isEmpty())
				{
					txtPreview.setText("The starting time of event has not been set hence the event was NOT created. Please enter all information.");
				}
				else if (titleOfEvent.isEmpty())
				{
					txtPreview.setText("The title of event has not been set hence the event was NOT created. Please enter all information.");
				}
			}
        });		
		
		txtPreview.setEditable(false);
		txtPreview.setBounds(10, 170, 580, 188);
		
		btnBackToMain.setBounds(10, 369, 100, 20);
        btnBackToMain.setMnemonic('B');
        btnBackToMain.addActionListener( new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) 
			{ 
				CardLayout cardLayout = (CardLayout) paneCreateWindow.getLayout();
				cardLayout.show(paneCreateWindow, "mainWindowPanel");
            }
        });
        
        
        add(lblTitleOfEvent);
		add(txtTitleOfEvent);
		add(lblDate);
		add(frmDateOfEvent);
		add(lblStartingTime);
		add(lblEndingTime);
		add(txtStartingTime);
		add(txtEndingTime);
		add(btnPreview);
		add(btnSaveEvent);
		add(txtPreview);
		add(btnBackToMain);
	}
}
