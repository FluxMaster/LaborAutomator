import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ScheduleMaker extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 575;
	
	private JLabel nameL,roomL,preferenceL,scheduleL;
	private JLabel[] week = new JLabel[7];
    private	JLabel[] time = new JLabel[17];
	
	private JComboBox prefList;
	private JTextField nameTF,roomTF;
	private JTextField[][] sched = new JTextField[7][17];
	private JButton saveB, loadB;
	private JFileChooser fc;
	
	private SaveScheduleHandler ssHandler;
	private LoadScheduleHandler lsHandler;
	
	public ScheduleMaker()
	{
		nameL = new JLabel("Name:",SwingConstants.LEFT);
		roomL = new JLabel("Room:",SwingConstants.LEFT);
		preferenceL = new JLabel("Preference:",SwingConstants.LEFT);
		scheduleL = new JLabel("Schedule",SwingConstants.CENTER);
		
		nameTF = new JTextField(20);
		roomTF = new JTextField(5);
		
		String[] prefTypes = {"Cook","Clean"};
		prefList = new JComboBox(prefTypes);
		
		fc = new JFileChooser();
		CSVFilter fltr = new CSVFilter();
		fc.setFileFilter(fltr);
		
		week[0] = new JLabel("Sunday",SwingConstants.CENTER);
		week[1] = new JLabel("Monday",SwingConstants.CENTER);
		week[2] = new JLabel("Tuesday",SwingConstants.CENTER);
		week[3] = new JLabel("Wednesday",SwingConstants.CENTER);
		week[4] = new JLabel("Thursday",SwingConstants.CENTER);
		week[5] = new JLabel("Friday",SwingConstants.CENTER);
		week[6] = new JLabel("Saturday",SwingConstants.CENTER);
		
		time[0] = new JLabel("6-7AM",SwingConstants.CENTER);
		time[1] = new JLabel("7-8AM",SwingConstants.CENTER);
		time[2] = new JLabel("8-9AM",SwingConstants.CENTER);
		time[3] = new JLabel("9-10AM",SwingConstants.CENTER);
		time[4] = new JLabel("10-11AM",SwingConstants.CENTER);
		time[5] = new JLabel("11AM-12PM",SwingConstants.CENTER);
		time[6] = new JLabel("12-1PM",SwingConstants.CENTER);
		time[7] = new JLabel("1-2PM",SwingConstants.CENTER);
		time[8] = new JLabel("2-3PM",SwingConstants.CENTER);
		time[9] = new JLabel("3-4PM",SwingConstants.CENTER);
		time[10] = new JLabel("4-5PM",SwingConstants.CENTER);
		time[11] = new JLabel("5-6PM",SwingConstants.CENTER);
		time[12] = new JLabel("6-7PM",SwingConstants.CENTER);
		time[13] = new JLabel("7-8PM",SwingConstants.CENTER);
		time[14] = new JLabel("8-9PM",SwingConstants.CENTER);
		time[15] = new JLabel("9-10PM",SwingConstants.CENTER);
		time[16] = new JLabel("10-11PM",SwingConstants.CENTER);
		
		for(int i = 0; i < sched.length; i++)
		{
			for(int j = 0; j < sched[i].length; j++)
			{
				sched[i][j] = new JTextField(20);
			}
		}
		
		saveB = new JButton("Save Schedule");
		ssHandler = new SaveScheduleHandler();
		saveB.addActionListener(ssHandler);
		
		loadB = new JButton("Load Schedule");
		lsHandler = new LoadScheduleHandler();
		loadB.addActionListener(lsHandler);
		
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
						.addComponent(scheduleL)
						.addComponent(time[0])
						.addComponent(time[1])
						.addComponent(time[2])
						.addComponent(time[3])
						.addComponent(time[4])
						.addComponent(time[5])
						.addComponent(time[6])
						.addComponent(time[7])
						.addComponent(time[8])
						.addComponent(time[9])
						.addComponent(time[10])
						.addComponent(time[11])
						.addComponent(time[12])
						.addComponent(time[13])
						.addComponent(time[14])
						.addComponent(time[15])
						.addComponent(time[16])
						.addComponent(saveB)
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(nameTF)
						.addComponent(roomTF)
						.addComponent(prefList)
						.addComponent(week[0])
						.addComponent(sched[0][0])
						.addComponent(sched[0][1])
						.addComponent(sched[0][2])
						.addComponent(sched[0][3])
						.addComponent(sched[0][4])
						.addComponent(sched[0][5])
						.addComponent(sched[0][6])
						.addComponent(sched[0][7])
						.addComponent(sched[0][8])
						.addComponent(sched[0][9])
						.addComponent(sched[0][10])
						.addComponent(sched[0][11])
						.addComponent(sched[0][12])
						.addComponent(sched[0][13])
						.addComponent(sched[0][14])
						.addComponent(sched[0][15])
						.addComponent(sched[0][16])
						.addComponent(loadB)
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[1])
						.addComponent(sched[1][0])
						.addComponent(sched[1][1])
						.addComponent(sched[1][2])
						.addComponent(sched[1][3])
						.addComponent(sched[1][4])
						.addComponent(sched[1][5])
						.addComponent(sched[1][6])
						.addComponent(sched[1][7])
						.addComponent(sched[1][8])
						.addComponent(sched[1][9])
						.addComponent(sched[1][10])
						.addComponent(sched[1][11])
						.addComponent(sched[1][12])
						.addComponent(sched[1][13])
						.addComponent(sched[1][14])
						.addComponent(sched[1][15])
						.addComponent(sched[1][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[2])
						.addComponent(sched[2][0])
						.addComponent(sched[2][1])
						.addComponent(sched[2][2])
						.addComponent(sched[2][3])
						.addComponent(sched[2][4])
						.addComponent(sched[2][5])
						.addComponent(sched[2][6])
						.addComponent(sched[2][7])
						.addComponent(sched[2][8])
						.addComponent(sched[2][9])
						.addComponent(sched[2][10])
						.addComponent(sched[2][11])
						.addComponent(sched[2][12])
						.addComponent(sched[2][13])
						.addComponent(sched[2][14])
						.addComponent(sched[2][15])
						.addComponent(sched[2][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[3])
						.addComponent(sched[3][0])
						.addComponent(sched[3][1])
						.addComponent(sched[3][2])
						.addComponent(sched[3][3])
						.addComponent(sched[3][4])
						.addComponent(sched[3][5])
						.addComponent(sched[3][6])
						.addComponent(sched[3][7])
						.addComponent(sched[3][8])
						.addComponent(sched[3][9])
						.addComponent(sched[3][10])
						.addComponent(sched[3][11])
						.addComponent(sched[3][12])
						.addComponent(sched[3][13])
						.addComponent(sched[3][14])
						.addComponent(sched[3][15])
						.addComponent(sched[3][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[4])
						.addComponent(sched[4][0])
						.addComponent(sched[4][1])
						.addComponent(sched[4][2])
						.addComponent(sched[4][3])
						.addComponent(sched[4][4])
						.addComponent(sched[4][5])
						.addComponent(sched[4][6])
						.addComponent(sched[4][7])
						.addComponent(sched[4][8])
						.addComponent(sched[4][9])
						.addComponent(sched[4][10])
						.addComponent(sched[4][11])
						.addComponent(sched[4][12])
						.addComponent(sched[4][13])
						.addComponent(sched[4][14])
						.addComponent(sched[4][15])
						.addComponent(sched[4][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[5])
						.addComponent(sched[5][0])
						.addComponent(sched[5][1])
						.addComponent(sched[5][2])
						.addComponent(sched[5][3])
						.addComponent(sched[5][4])
						.addComponent(sched[5][5])
						.addComponent(sched[5][6])
						.addComponent(sched[5][7])
						.addComponent(sched[5][8])
						.addComponent(sched[5][9])
						.addComponent(sched[5][10])
						.addComponent(sched[5][11])
						.addComponent(sched[5][12])
						.addComponent(sched[5][13])
						.addComponent(sched[5][14])
						.addComponent(sched[5][15])
						.addComponent(sched[5][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(week[6])
						.addComponent(sched[6][0])
						.addComponent(sched[6][1])
						.addComponent(sched[6][2])
						.addComponent(sched[6][3])
						.addComponent(sched[6][4])
						.addComponent(sched[6][5])
						.addComponent(sched[6][6])
						.addComponent(sched[6][7])
						.addComponent(sched[6][8])
						.addComponent(sched[6][9])
						.addComponent(sched[6][10])
						.addComponent(sched[6][11])
						.addComponent(sched[6][12])
						.addComponent(sched[6][13])
						.addComponent(sched[6][14])
						.addComponent(sched[6][15])
						.addComponent(sched[6][16])
				)
		);
		
		layout.setVerticalGroup
		(
			layout.createSequentialGroup()
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(nameL)
						.addComponent(nameTF)
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(roomL)
						.addComponent(roomTF)
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(preferenceL)
						.addComponent(prefList)
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(scheduleL)
						.addComponent(week[0])
						.addComponent(week[1])
						.addComponent(week[2])
						.addComponent(week[3])
						.addComponent(week[4])
						.addComponent(week[5])
						.addComponent(week[6])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[0])
						.addComponent(sched[0][0])
						.addComponent(sched[1][0])
						.addComponent(sched[2][0])
						.addComponent(sched[3][0])
						.addComponent(sched[4][0])
						.addComponent(sched[5][0])
						.addComponent(sched[6][0])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[1])
						.addComponent(sched[0][1])
						.addComponent(sched[1][1])
						.addComponent(sched[2][1])
						.addComponent(sched[3][1])
						.addComponent(sched[4][1])
						.addComponent(sched[5][1])
						.addComponent(sched[6][1])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[2])
						.addComponent(sched[0][2])
						.addComponent(sched[1][2])
						.addComponent(sched[2][2])
						.addComponent(sched[3][2])
						.addComponent(sched[4][2])
						.addComponent(sched[5][2])
						.addComponent(sched[6][2])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[3])
						.addComponent(sched[0][3])
						.addComponent(sched[1][3])
						.addComponent(sched[2][3])
						.addComponent(sched[3][3])
						.addComponent(sched[4][3])
						.addComponent(sched[5][3])
						.addComponent(sched[6][3])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[4])
						.addComponent(sched[0][4])
						.addComponent(sched[1][4])
						.addComponent(sched[2][4])
						.addComponent(sched[3][4])
						.addComponent(sched[4][4])
						.addComponent(sched[5][4])
						.addComponent(sched[6][4])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[5])
						.addComponent(sched[0][5])
						.addComponent(sched[1][5])
						.addComponent(sched[2][5])
						.addComponent(sched[3][5])
						.addComponent(sched[4][5])
						.addComponent(sched[5][5])
						.addComponent(sched[6][5])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[6])
						.addComponent(sched[0][6])
						.addComponent(sched[1][6])
						.addComponent(sched[2][6])
						.addComponent(sched[3][6])
						.addComponent(sched[4][6])
						.addComponent(sched[5][6])
						.addComponent(sched[6][6])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[7])
						.addComponent(sched[0][7])
						.addComponent(sched[1][7])
						.addComponent(sched[2][7])
						.addComponent(sched[3][7])
						.addComponent(sched[4][7])
						.addComponent(sched[5][7])
						.addComponent(sched[6][7])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[8])
						.addComponent(sched[0][8])
						.addComponent(sched[1][8])
						.addComponent(sched[2][8])
						.addComponent(sched[3][8])
						.addComponent(sched[4][8])
						.addComponent(sched[5][8])
						.addComponent(sched[6][8])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[9])
						.addComponent(sched[0][9])
						.addComponent(sched[1][9])
						.addComponent(sched[2][9])
						.addComponent(sched[3][9])
						.addComponent(sched[4][9])
						.addComponent(sched[5][9])
						.addComponent(sched[6][9])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[10])
						.addComponent(sched[0][10])
						.addComponent(sched[1][10])
						.addComponent(sched[2][10])
						.addComponent(sched[3][10])
						.addComponent(sched[4][10])
						.addComponent(sched[5][10])
						.addComponent(sched[6][10])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[11])
						.addComponent(sched[0][11])
						.addComponent(sched[1][11])
						.addComponent(sched[2][11])
						.addComponent(sched[3][11])
						.addComponent(sched[4][11])
						.addComponent(sched[5][11])
						.addComponent(sched[6][11])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[12])
						.addComponent(sched[0][12])
						.addComponent(sched[1][12])
						.addComponent(sched[2][12])
						.addComponent(sched[3][12])
						.addComponent(sched[4][12])
						.addComponent(sched[5][12])
						.addComponent(sched[6][12])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[13])
						.addComponent(sched[0][13])
						.addComponent(sched[1][13])
						.addComponent(sched[2][13])
						.addComponent(sched[3][13])
						.addComponent(sched[4][13])
						.addComponent(sched[5][13])
						.addComponent(sched[6][13])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[14])
						.addComponent(sched[0][14])
						.addComponent(sched[1][14])
						.addComponent(sched[2][14])
						.addComponent(sched[3][14])
						.addComponent(sched[4][14])
						.addComponent(sched[5][14])
						.addComponent(sched[6][14])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[15])
						.addComponent(sched[0][15])
						.addComponent(sched[1][15])
						.addComponent(sched[2][15])
						.addComponent(sched[3][15])
						.addComponent(sched[4][15])
						.addComponent(sched[5][15])
						.addComponent(sched[6][15])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(time[16])
						.addComponent(sched[0][16])
						.addComponent(sched[1][16])
						.addComponent(sched[2][16])
						.addComponent(sched[3][16])
						.addComponent(sched[4][16])
						.addComponent(sched[5][16])
						.addComponent(sched[6][16])
				)
				.addGroup
				(
					layout.createParallelGroup()
						.addComponent(saveB)
						.addComponent(loadB)
				)
		);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class SaveScheduleHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(nameTF.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
						"Please Enter a Name",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				}
				else if(roomTF.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
						"Please Enter a Room Number",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int fileval = fc.showSaveDialog(ScheduleMaker.this);
					if(fileval == JFileChooser.APPROVE_OPTION)
					{
						File temp = fc.getSelectedFile();
						
						String finalName = "";
						if(!temp.getPath().substring(temp.getPath().length()-4).equals(".csv"))
						{
							finalName = temp.getPath()+".csv";
						}
						else
						{
							finalName = temp.getPath();
						}
						FileWriter fw = new FileWriter(finalName,false);
					
						fw.write("Name:,"+nameTF.getText()+",,,,,,\n");
						fw.write("Room:,"+roomTF.getText()+",,,,,,\n");
						fw.write("Preference:,"+prefList.getSelectedItem()+"\n");
						fw.write(",,,,,,,\n");
						fw.write("Schedule,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday\n");
						for(int i = 6; i < 23; i++)
						{
							fw.write(i+"-"+(i+1));
							for(int j = 0; j<7 ; j++)
							{
								fw.write(",");
								fw.write(sched[j][i-6].getText());
							}
							fw.write("\n");
						}
						fw.close();
						JOptionPane.showMessageDialog(null,
							"Schedule Saved",
							"Message",
							JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(null,
							"Error Saving Schedule",
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class LoadScheduleHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int returnVal = fc.showOpenDialog(ScheduleMaker.this);
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File loader = fc.getSelectedFile();
					
					Scanner sc = new Scanner(loader);
					
					sc.useDelimiter(",|\n");
					
					sc.next();
					nameTF.setText(sc.next());
					sc.nextLine();
					
					sc.next();
					roomTF.setText(sc.next());
					sc.nextLine();
					
					sc.next();
					prefList.setSelectedItem(sc.next());
					sc.nextLine();
					
					sc.nextLine();
					
					for(int k = 0; k < 17; k++)
					{
						String temp = "";
						sc.nextLine();
						sc.next();
						
						for(int j = 0; j < 7; j++)
						{
							try
							{
								temp = sc.next();
							}
							catch(NoSuchElementException oops)
							{
								temp = "";
							}
							if(!temp.equals("") || !temp.equals("\r"))
							{
								sched[j][k].setText(temp);
							}
						}
					}
				}
			}
			catch(Exception exp)
			{
				JOptionPane.showMessageDialog(null,
							"Error Loading Schedule\n"+exp.toString(),
							"Error",
							JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class CSVFilter extends javax.swing.filechooser.FileFilter
	{
		public boolean accept(File f)
		{
			if(f.isDirectory())
				return true;
			if(f.getName().substring(f.getName().length()-3).equals("csv"))
				return true;
			return false;
		}
		
		public String getDescription()
		{
			return ".csv files";
		}
	}
	
	public static void main(String[] args)
	{
		ScheduleMaker sm = new ScheduleMaker();
	}
}