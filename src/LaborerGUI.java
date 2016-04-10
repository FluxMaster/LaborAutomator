import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LaborerGUI extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	
	private JLabel flexL, hpmL, mFlexL;
	private JTextField flexTF, hpmTF, mFlexTF;
	private JButton calculateB, exitB;
	
	///Button handlers:
	private CalculateButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;
	
	public LaborerGUI()
	{
		flexL = new JLabel("Number of total flex hours :", SwingConstants.LEFT);
		hpmL = new JLabel("Number of hours worked by each member :", SwingConstants.LEFT);
		mFlexL = new JLabel("Maximum flex hours worked by one member :", SwingConstants.LEFT);
		
		flexTF = new JTextField(10);
		hpmTF = new JTextField(10);
		mFlexTF = new JTextField(10);
		
		calculateB = new JButton("Calculate");
		cbHandler = new CalculateButtonHandler();
		calculateB.addActionListener(cbHandler);
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);
		
		setTitle("College Houses Labor");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(4,2));
		
		pane.add(flexL);
		pane.add(flexTF);
		pane.add(hpmL);
		pane.add(hpmTF);
		pane.add(mFlexL);
		pane.add(mFlexTF);
		pane.add(calculateB);
		pane.add(exitB);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(Integer.parseInt(mFlexTF.getText()) > Integer.parseInt(hpmTF.getText()))
			{
				LaborerGUI.infoBox("Maximum flex hours per member must be less than or equal to number of hours worked per member.","Error");
			}
			else
			{			
				String[] temp = {flexTF.getText(),hpmTF.getText(),mFlexTF.getText()};
				try
				{
					Laborer.main(temp);
				}
				catch(Exception excep)
				{
					System.out.println(excep);
					excep.printStackTrace();
					
					System.out.println("GUI Problem.");
					
					
					System.exit(1);
				}
				System.exit(0);
			}	
		}
	}
	
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		LaborerGUI LGUI = new LaborerGUI();
	}
	
}