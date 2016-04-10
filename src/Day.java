/*
	Copyright Andrew Polasek
    on July 7th 2015
*/

import java.io.*;
import java.util.*;

public class Day
{
	/*
		Here is an array for each days schedules.
		The goal is that it is as long as you have time slots in a day.
		If the person is available, put a 1 there, if not, put a 0.
		My time slots are 1 hour long and start at 6:00-7:00AM and end 
			with 10:00 to 11:00PM and correspond to numbers 0-17.
	*/
	
	/*
	0 	6 A
	1 	7 A
	2 	8 A
	3 	9 A
	4	10A
	5	11A
	6	12N
	7	1 P
	8	2 P
	9	3 P
	10	4 P
	11	5 P
	12	6 P
	13	7 P
	14	8 P
	15	9 P
	16	10 P
	17	11 P
	
	*/
	
	public int[] time = new int[17];
	
	//Constructor
	public Day(){}
	
	//Getters
	public int[] getTime()
	{
		return time;
	}
	public int getBlock(int block)
	{
		return time[block];
	}
	
	//Setters
	public void setTime(int[] time)
	{
		this.time = time;
	}
	
	public void setBlock(int block, int value)
	{
		time[block] = value;
	}
	
	public static String numberToName(int x)
	{
		switch (x)
		{
			case 0: return "Sunday";
			case 1: return "Monday";
			case 2: return "Tuesday";
			case 3: return "Wednesday";
			case 4: return "Thursday";
			case 5: return "Friday";
			case 6: return "Saturday";
			default:System.out.println("ERROR: No such day exists");
		}
		return null;
	}
}