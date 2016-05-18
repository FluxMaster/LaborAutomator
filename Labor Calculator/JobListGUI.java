import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class JobListGUI extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	private JButton addB, backB, saveB, loadB;
	private JTextArea jobs;
	private JScrollPane csp;
	
	
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
		abHandler.dispose();
		addB.addActionListener(abHandler);
		
		backB = new JButton("Main Menu");
		BackButtonHandler bbHandler = new BackButtonHandler();
		backB.addActionListener(bbHandler);
		
		
		JPanel bPanel = new JPanel();
		GridLayout bGrid = new GridLayout(1,4);
		bPanel.add(saveB);
		bPanel.add(loadB);
		bPanel.add(addB);
		bPanel.add(backB);
		
		jobs = new JTextArea();
		jobs.setEditable(true);
		csp = new JScrollPane(jobs);
		
		loadList();
		
		setTitle("Jobs List");
		Container pane = getContentPane();
		
		pane.add(bPanel,BorderLayout.NORTH);
		pane.add(csp,BorderLayout.CENTER);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private class BackButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
			new MainMenuGUI();
		}
	}
	
	private class LoadButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			loadList();
		}
	}
	
	public void loadList()
	{
		File list = new File("Jobs/Jobs.csv");
		int count = 1;
		String temp = "";
		try
		{
			Scanner sc = new Scanner(list);
			sc.useDelimiter(",");
			while(sc.hasNextLine())
			{
				String[] split = sc.nextLine().split(",");
				System.out.println(split);
				temp+=
				split[0]+","+
				Day.numberToDay(Integer.parseInt(split[1]))+","+
				Day.numberToTime(Integer.parseInt(split[2]))+","+
				split[3]+","+
				split[4]+"\n";
				count++;
			}
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(
						null, 
						"Error Loading List at line " + count + "\n" + exp, 
						"Error", 
						JOptionPane.ERROR_MESSAGE);
		}
		jobs.setText(temp);
	}
	
	private class SaveButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			File list = new File("Jobs/Jobs.csv");
			int count = 1;
			try
			{
				FileWriter fw = new FileWriter(list);
				Scanner sc = new Scanner(jobs.getText());
				sc.useDelimiter(",");
				while(sc.hasNextLine())
				{
					String[] split = sc.nextLine().split(",");
					fw.write
					(
						split[0]+","+
						Day.dayToNumber(split[1])+","+
						Day.timeToNumber(split[2])+","+
						split[3]+","+
						split[4]+"\n"
					);
					count++;
				}
				fw.close();
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(
							null, 
							"Error Saving Line " + count +"/n" + exp, 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class AddButtonHandler extends JFrame implements ActionListener 
	{
		private static final int WIDTH = 600;
		private static final int HEIGHT = 220;
		private JLabel jName, jNumWorkers, jLength, jTime, jType;
		private JTextField jNameTF, jNumWorkersTF, jLengthTF;
		private JCheckBox sunB,monB,tueB,wedB,thuB,friB,satB;
		private JButton addB, cancelB;
		private JComboBox lTime, lType;
		String[] timeList = {"6AM","7AM","8AM","9AM","10AM","11AM","12PM","1PM","2PM","3PM","4PM","5PM","6PM","7PM","8PM","9PM","10PM"};
		String[] prefList = {"Cook","Clean"};
		
		public void actionPerformed(ActionEvent e)
		{
			new AddButtonHandler ();
		}
		
		public AddButtonHandler()
		{	
			lTime = new JComboBox(timeList);
			lType = new JComboBox(prefList);
			
			jName = new JLabel("Name of Job:");
			jNumWorkers = new JLabel("Number of Workers:");
			jLength = new JLabel("Number of Hours:");
			jTime = new JLabel("Time:");
			jType = new JLabel("Type:");
			
			
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
			GridLayout ajLayoutN = new GridLayout(5,2);
			ajPaneN.setLayout(ajLayoutN);
			
			ajPaneN.add(jName);
			ajPaneN.add(jNameTF);
			ajPaneN.add(jNumWorkers);
			ajPaneN.add(jNumWorkersTF);
			ajPaneN.add(jLength);
			ajPaneN.add(jLengthTF);
			ajPaneN.add(jTime);
			ajPaneN.add(lTime);
			ajPaneN.add(jType);
			ajPaneN.add(lType);
			
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
				try
				{
					//File temp = new File("Jobs/Jobs.csv");
					//FileWriter fw = new FileWriter(temp,true);
					JToggleButton[] tggList = {sunB,monB,tueB,wedB,thuB,friB,satB};
					for(int i = 0; i<7; i++)
					{
						if(tggList[i].isSelected())
						{
							for(int j = Integer.valueOf(jNumWorkersTF.getText()); j > 0; j--)
							{
								if(jobs.getText().isEmpty());
									
								else if((!(jobs.getText().substring(jobs.getText().length()-1,jobs.getText().length())).equals("\n")))
								{
									jobs.setText(jobs.getText()+"\n");
								}
								
								jobs.setText(
									jobs.getText()+
									jNameTF.getText()+","
									+Day.numberToDay(i)+","
									+Day.numberToTime(timeIndex((String)lTime.getSelectedItem()))+","
									+jLengthTF.getText()+","
									+lType.getSelectedItem()+"\n"
									);
									
							}
						}
					}
					//fw.close();
					//loadList();
					
					jNumWorkers.setText("");
					jNameTF.setText("");
					jLengthTF.setText("");
					
					dispose();
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
			
			public int timeIndex(String time)
			{
				for(int i = 0; i < timeList.length; i++)
				{
					if(time.equals(timeList[i]))
						return i;
				}
				return -1;
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