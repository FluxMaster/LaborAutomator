import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class JobListGUI extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	
	private JButton addB, delB, saveB, loadB;
	private JTextArea jobs;
	
	public JobListGUI()
	{
		saveB = new JButton("Save List");
		SaveButtonHandler sbHandler = new SaveButtonHandler();
		saveB.addActionListener(sbHandler);
		
		loadB = new JButton("Load List");
		LoadButtonHandler lbHandler = new LoadButtonHandler();
		loadB.addActionListener(lbHandler);
		
		addB = new JButton("Add Job");
		AddButtonHandler abHandler = new AddButtonHandler();
		addB.addActionListener(abHandler);
		
		JPanel bPanel = new JPanel();
		GridLayout bGrid = new GridLayout(1,4);
		bPanel.add(saveB);
		bPanel.add(loadB);
		bPanel.add(addB);
		
		jobs = new JTextArea();
		jobs.setEditable(false);
		
		lbHandler.loadList();
		
		setTitle("Jobs List");
		Container pane = getContentPane();
		
		pane.add(bPanel,BorderLayout.NORTH);
		pane.add(jobs,BorderLayout.CENTER);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private class LoadButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			loadList();
		}
		
		public void loadList()
		{
			File list = new File("Jobs/Jobs.csv");
			try
			{
				Scanner sc = new Scanner(list);
				String temp = "";
				while(sc.hasNextLine())
				{
					temp+=sc.nextLine()+"\n";
				}
				jobs.setText(temp);
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(
							null, 
							"No Jobs List Found", 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class SaveButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			File list = new File("Jobs/Jobs.csv");
			try
			{
				FileWriter fw = new FileWriter(list);
				fw.write(jobs.getText());
				fw.close();
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(
							null, 
							"No Jobs List Found", 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class AddButtonHandler extends JFrame implements ActionListener 
	{
		private static final int WIDTH = 600;
		private static final int HEIGHT = 150;
		private JLabel jName, jNumWorkers, jLength;
		private JTextField jNameTF, jNumWorkersTF, jLengthTF;
		private JCheckBox sunB,monB,tueB,wedB,thuB,friB,satB;
		private JButton addB, cancelB;
		
		public void actionPerformed(ActionEvent e)
		{
			jName = new JLabel("Name:");
			jNumWorkers = new JLabel("Number of Workers:");
			jLength = new JLabel("Number of Hours:");
			
			jNameTF = new JTextField();
			jNumWorkersTF = new JTextField();
			jLengthTF = new JTextField();
			
			sunB = new JCheckBox("Sunday");
			monB = new JCheckBox("Monday");
			tueB = new JCheckBox("Tuesday");
			wedB = new JCheckBox("Wednesday");
			thuB = new JCheckBox("Thursday");
			friB = new JCheckBox("Friday");
			satB = new JCheckBox("Saturday");
			
			addB = new JButton("Add Job");
			AddJobHandler ajHandler = new AddJobHandler();
			addB.addActionListener(ajHandler);
			
			cancelB = new JButton("Cancel");
			CancelButtonHandler cbHandler = new CancelButtonHandler();
			cancelB.addActionListener(cbHandler);
			
			JPanel ajPaneN = new JPanel();
			GridLayout ajLayoutN = new GridLayout(3,2);
			ajPaneN.setLayout(ajLayoutN);
			
			ajPaneN.add(jName);
			ajPaneN.add(jNameTF);
			ajPaneN.add(jNumWorkers);
			ajPaneN.add(jNumWorkersTF);
			ajPaneN.add(jLength);
			ajPaneN.add(jLengthTF);
			
			JPanel ajPaneC = new JPanel();
			GridLayout ajLayoutC = new GridLayout(1,7);
			ajPaneC.setLayout(ajLayoutC);
			
			ajPaneC.add(sunB);
			ajPaneC.add(monB);
			ajPaneC.add(tueB);
			ajPaneC.add(wedB);
			ajPaneC.add(thuB);
			ajPaneC.add(friB);
			ajPaneC.add(satB);
			
			JPanel ajPaneS = new JPanel();
			GridLayout ajLayoutS = new GridLayout (1,2);
			ajPaneS.setLayout(ajLayoutS);
			
			ajPaneS.add(addB);
			ajPaneS.add(cancelB);
			
			Container display = getContentPane();
			display.add(ajPaneN,BorderLayout.NORTH);
			display.add(ajPaneC,BorderLayout.CENTER);
			display.add(ajPaneS,BorderLayout.SOUTH);
			
			setSize(WIDTH,HEIGHT);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
		private class AddJobHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		}
		
		private class CancelButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		}
	}
	
	public static void main(String[] args)
	{
		new JobListGUI();
	}
}