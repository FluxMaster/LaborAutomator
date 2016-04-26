import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class CalculatorGUI extends JFrame
{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	
	private JLabel flexL, hpmL, mFlexL;
	private JTextField flexTF, hpmTF, mFlexTF;
	private JButton calculateB, exitB;
	
	///Button handlers:
	private CalculateButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;
	
	public CalculatorGUI()
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
		exitB = new JButton("Main Menu");
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.PLAIN_MESSAGE);
    }
	
	public static void errorBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
    }
	
	private class CalculateButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Integer.parseInt(flexTF.getText());
				Integer.parseInt(hpmTF.getText());
				Integer.parseInt(mFlexTF.getText());
				
				if(flexTF.getText().equals(""))
					CalculatorGUI.errorBox("Please enter the number of Flex Hours avaliable to be worked.","Error");
				else if(hpmTF.getText().equals(""))
					CalculatorGUI.errorBox("Please enter the number of Hours each Member works.","Error");
				else if(mFlexTF.getText().equals(""))
					CalculatorGUI.errorBox("Please enter the maximum number of Flex Hours a Member can work.","Error");
				else if(Integer.parseInt(mFlexTF.getText()) > Integer.parseInt(hpmTF.getText()))
					CalculatorGUI.errorBox("Maximum flex hours per member must be less than or equal to number of hours worked per member.","Error");
				else
				{	
					String[] temp = {flexTF.getText(),hpmTF.getText(),mFlexTF.getText()};
					try
					{
						Calculator.main(temp);
					}
					catch(Exception excep)
					{
						System.out.println(excep);
						excep.printStackTrace();
						dispose();
					}
					dispose();
				}	
			}
			catch(NumberFormatException exp)
			{
				CalculatorGUI.errorBox("Please Input Integers for Hour Amounts","Error");
			}
		}
	}
	
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		CalculatorGUI CGUI = new CalculatorGUI();
	}
	
}