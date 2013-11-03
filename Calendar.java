/**
 * MAIN
 * 
 * @author  Nikita Pankratov
 * @version 10/14/2013
 */

import java.awt.*;
import javax.swing.*;


public class Calendar
{
	CalendarCreator		curMonth 			= new CalendarCreator();
	EventCreator    	EventList			= new EventCreator();
	JPanel 				contentPane			= new JPanel();
    mainWindow 			newMainWindow 		= new mainWindow(contentPane, EventList, curMonth);
    createWindow 		newCreateWindow		= new createWindow(contentPane, EventList, curMonth);
    deleteWindow		newDeleteWindow		= new deleteWindow(contentPane, EventList, curMonth);
    
    private void displayGUI()
    {
        JFrame frame = new JFrame("Welcome to your Calendar!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());
        
        contentPane.add(newMainWindow, "mainWindowPanel"); 
        contentPane.add(newCreateWindow, "createWindowPanel");
        contentPane.add(newDeleteWindow, "deleteWindow");
        
        frame.setContentPane(contentPane);
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Calendar().displayGUI();
            }
        });
    }
}
