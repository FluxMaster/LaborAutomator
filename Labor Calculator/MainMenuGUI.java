import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MainMenuGUI extends JFrame
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 175;
	
	private JButton calculateB, jobsB, asgnJobsB, aboutB;
	private JLabel titleL,subtitleL;
	private CalculateButtonHandler cbHandler;
	private JobsButtonHandler jlHandler;
	private AssignedJobsHandler ajHandler;
	private AboutButtonHandler abHandler;
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
		AssignedJobsHandler ajHandler = new AssignedJobsHandler();
		asgnJobsB.addActionListener(ajHandler);
		
		aboutB = new JButton("About");
		abHandler = new AboutButtonHandler();
		aboutB.addActionListener(abHandler);
		
		titleL = new JLabel("C.H.A.R.L.I.E.", SwingConstants.CENTER);
		subtitleL = new JLabel("College Houses Automated Reliable Labor Inducer Executable",SwingConstants.CENTER);
		
		Container pane = getContentPane();
		GroupLayout layout = new GroupLayout(pane);
		pane.setLayout(layout);
		
		layout.setHorizontalGroup
		(
			layout.createSequentialGroup()
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(calculateB)
						.addComponent(titleL)
						.addComponent(subtitleL)
						.addComponent(jobsB)
						.addComponent(asgnJobsB)
						.addComponent(aboutB)
				)
		);
		
		layout.setVerticalGroup
		(
			layout.createSequentialGroup()
				.addComponent(titleL)
				.addComponent(subtitleL)
				.addComponent(calculateB)
				.addComponent(jobsB)
				.addComponent(asgnJobsB)
				.addComponent(aboutB)
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
				new CalculatorGUI();
				dispose();
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
			new JobListGUI();
			dispose();
		}
	}
	
	private class AssignedJobsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new AssignedGUI();
			dispose();
		}
	}
	
	private class AboutButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(
						null, 
						"C.H.A.R.L.I.E. was written during the 2015-2016 school year by Andy Polasek of Taos Cooperative.\n"+
						"It is named in honor of Charlie Hicks who worked as Labor Czar at Pearl after \"The Anarchy\" and worked as Maintenance Director of College Houses for many years.\n"+ 
						"This program is gifted to College Houses for use in setting up their houses' labor systems.\n"+
						"It is provided without warranty, but if you reach out to Andy Polasek at \"andy@polasek.com\" or other Labor Czars, they may be able to help with your problems.", 
						"About", 
						JOptionPane.PLAIN_MESSAGE);
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