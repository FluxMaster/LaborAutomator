import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ScheduleMaker extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	
	private JLabel nameL,roomL,preferenceL,scheduleL;
	private JLabel[] week = new JLabel[7];
    private	JLabel[] time = new JLabel[17];
	
	private JComboBox prefList;
	private JTextField nameTF,RoomTF;
	private JTextField[][] sched = new JTextField[7][17];
	private JButton saveB, exitB;
	
	//private SaveScheduleHandler ssHandler;
	//private ExitButtonHandler ebHandler;
	
	public ScheduleMaker()
	{
		nameL = new JLabel("Name:",SwingConstants.LEFT);
		roomL = new JLabel("Room:",SwingConstants.LEFT);
		preferenceL = new JLabel("Preference:",SwingConstants.LEFT);
		scheduleL = new JLabel("Schedule",SwingConstants.CENTER);
		
		week[0]= new JLabel("Sunday",SwingConstants.CENTER);
		week[1]= new JLabel("Monday",SwingConstants.CENTER);
		week[2]= new JLabel("Tuesday",SwingConstants.CENTER);
		week[3]= new JLabel("Wednesday",SwingConstants.CENTER);
		week[4]= new JLabel("Thursday",SwingConstants.CENTER);
		week[5]= new JLabel("Friday",SwingConstants.CENTER);
		week[6]= new JLabel("Saturday",SwingConstants.CENTER);
		
		for(int i = 0; i < sched.length; i++)
		{
			for(int j = 0; j < sched[i].length; j++)
			{
				sched[i][j] = new JTextField(20);
			}
		}
		
		/*
		saveB = new JButton("Save Schedule");
		ssHandler = new SaveScheduleHandler();
		saveB.addActionListener(ssHandler);
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);
		*/
		
		setTitle("Labor Schedule Form");
		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		
		layout.setHorizontalGroup
		(
			layout.createSequentialGroup()
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(nameL)
						.addComponent(roomL)
						.addComponent(preferenceL)
				)	
		);
		
		layout.setVerticalGroup
		(
			layout.createSequentialGroup()
				.addComponent(nameL)
				.addComponent(roomL)
				.addComponent(preferenceL)
		);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		ScheduleMaker sm = new ScheduleMaker();
	}
}