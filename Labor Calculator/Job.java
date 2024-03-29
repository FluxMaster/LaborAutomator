/*
	Copyright Andrew Polasek
    on July 7th 2015
*/

import java.util.*;
import java.io.*;

public class Job implements Comparable<Job>
{
	private static int count;
	private String name;
	private int time;
	private int day;
	private int length;
	private ArrayList people;
	private int ID;
	private Person doer;
	private String type;
	
	//It's important to note that time slots are not
	//referenced by time buy by numbers 0 - 16
	//This will line up with the other objects well
	
	//Constructor
	public Job(String name, int day, int time, int length, String type)
	{
		this.name = name;
		this.time = time;
		this.day = day;
		this.length = length;
		this.ID = ++count;
		this.type = type;
		people = new ArrayList(0);
		
	}
	
	//Adds a person who can do this job
	public void addPerson(Person p)
	{
		people.add(p);
	}
	//sorts araylist
	public void sortPeople()
	{
		Collections.sort(people);
	}
	
	//toString
	public String toString()
	{
		return getID() + ": " + getName() + " at " + (6 +(getTime())) + " o'Clock on " + dayParser(getDay()) + " for " + getLength() + " hours."; 
	}
	
	@Override public int compareTo(Job jother) 
	{
		return new Integer(this.getSize()).compareTo(new Integer(jother.getSize()));
	}
	
	public void setDoer(Person p)
	{
		this.doer = p;
	}
	
	//Getters
	public String getName()
	{
		return this.name;
	}
	
	public int getTime()
	{
		return this.time;
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public int getLength()
	{
		return this.length;
	}
	
	public int getSize()
	{
		return this.people.size();
	}
	
	public Person getPerson(int i)
	{
		return (Person)this.people.get(i);
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public Person getDoer()
	{
		return this.doer;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void removeDoer()
	{
		this.doer = null;
	}
	
	public boolean equals(Job j)
	{
		//System.out.println("Start");
		if(this.name.equals(j.getName()))
		{
			//System.out.println("Name");
			if(this.time == j.getTime())
			{
				//System.out.println("Time");
				if(this.day == j.getDay())
				{
					//System.out.println("Day");
					if(this.length == j.getLength())
					{
						//System.out.println("Length");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean conflicts(Job j)
	{
		if(this.day == j.getDay())
		{
			if(this.time == j.getTime())
			{
				return true;
			}
			else if(this.time - j.getTime() < 0 && Math.abs(this.time - j.getTime()) < this.length)
			{
				return true;
			}
			else if(this.time - j.getTime() > 0 && Math.abs(this.time - j.getTime()) < j.getLength())
			{
				return true;
			}
		}
		return false;
	}
	
	
	String dayParser(int x)
	{
		switch(x)
		{
			case 0:
				return "Sunday";
			case 1:
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
		}
		return "OOPS";
	}
}

class JobSizeSorter implements Comparator<Job>
{
	public int compare(Job j1, Job j2)
	{
		return new Integer(j1.getSize()).compareTo(new Integer(j2.getSize()));
	}
}

class JobIDSorter implements Comparator<Job>
{
	public int compare(Job j1, Job j2)
	{
		return new Integer(j1.getID()).compareTo(new Integer(j2.getID()));
	}
}

class JobTimeSorter implements Comparator<Job>
{
	public int compare(Job j1, Job j2)
	{
		if(new Integer(j1.getDay()).compareTo(new Integer(j2.getDay())) != 0)
			return new Integer(j1.getDay()).compareTo(new Integer(j2.getDay()));
		else
			return new Integer(j1.getTime()).compareTo(new Integer(j2.getTime()));
	}
}