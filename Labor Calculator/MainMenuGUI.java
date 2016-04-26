import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MainMenuGUI extends JFrame
{
	private static final int WIDTH = 520;
	private static final int HEIGHT = 80;
	
	private JButton calculateB, jobsB, asgnJobsB;
	private JLabel titleL;
	private CalculateButtonHandler cbHandler;
	private JobsButtonHandler jlHandler;
	private AssignedJobsHandler ajHandler;
	private DummyButtonHandler dbHandler;
	
	public MainMenuGUI()
	{
		calculateB = new JButton("Calculate Schedule");
		cbHandler = new CalculateButtonHandler();
		calculateB.addActionListener(cbHandler);
		
		jobsB = new JButton("Scheduled Jobs List Editor");
		jlHandler = new JobsButtonHandler();
		jobsB.addActionListener(jlHandler);
		
		asgnJobsB = new JButton("Assigned Jobs List Editor");
		dbHandler = new DummyButtonHandler();
		asgnJobsB.addActionListener(dbHandler);
		
		titleL = new JLabel("Labor Calculator", SwingConstants.CENTER);
		
		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		
		layout.setHorizontalGroup
		(
			layout.createSequentialGroup()
				.addComponent(calculateB)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(titleL)
						.addComponent(jobsB)
				)
				.addComponent(asgnJobsB)
		);
		
		layout.setVerticalGroup
		(
			layout.createSequentialGroup()
				.addComponent(titleL)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(calculateB)
						.addComponent(jobsB)
						.addComponent(asgnJobsB)
				)
		);
		
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		MainMenuGUI MMGUI = new MainMenuGUI();
	}
	
	private class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				CalculatorGUI.main(new String[0]);
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(null, 
										"STUFF DON'T WORK", 
										"ERROR",
										JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class JobsButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JobListGUI JLGUI = new JobListGUI();
		}
	}
	
	private class AssignedJobsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		}
	}
	
	private class DummyButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, 
										"Work in Progress!", 
										"Warning",
										JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	
	
}