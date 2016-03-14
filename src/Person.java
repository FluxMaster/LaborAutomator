/*
	Copyright Andrew Polasek
    on July 7th 2015
*/
import java.util.*;

public class Person implements Comparable<Person>
{
	private String name;
	private String room;
	private int avail;
	private Schedule sched;
	private int hours;
	private ArrayList jobsList;
	private String preference;
	
	//Constructor
	public Person(String name, String room, String preference, Schedule sched)
	{
		this.name=name;
		this.room=room;
		this.sched=sched;
		this.avail = 0;
		this.hours = 0;
		this.preference = preference;
		jobsList = new ArrayList(0);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getRoom()
	{
		return this.room;
	}
	
	public Schedule getSchedule()
	{
		return this.sched;
	}
	
	public int getHours()
	{
		return this.hours;
	}
	
	public int getAvail()
	{
		return this.avail;
	}
	
	public String getPreference()
	{
		return this.preference;
	}
	
	//Functions
	public void incrementAvail()
	{
		this.avail++;
	}
	
	public void addHours(int num)
	{
		this.hours += num;
	}
	
	public void removeHours(int num)
	{
		this.hours -= num;
	}
	
	public void addJob(Job j)
	{
		jobsList.add(j);
		addHours(j.getLength());
	}
	
	public void removeJob(Job j)
	{
		for(int i = 0; i<jobsList.size(); i++)
		{
			if (j.equals(jobsList.get(i)))
			{
				jobsList.remove(i);
			}
		}
		removeHours(j.getLength());
	}
	
	public boolean hasJob(Job j)
	{
		for(int i = 0; i < jobsList.size(); i++)
		{
			//System.out.println("Is it true?");
			if(((Job)jobsList.get(i)).conflicts(j))
			{
				//System.out.println("YES!");
				return true;
			}
		}
		return false;
	}
	
	public ArrayList getJobsList()
	{
		return jobsList;
	}
	
	public String toString()
	{
		return getName() + " " + getRoom();
	}
	
	@Override public int compareTo(Person pother) 
	{
		return new Integer(this.getAvail()).compareTo(new Integer(pother.getAvail()));
	}
}

class PeopleSorter implements Comparator<Person>
{
	public int compare(Person p1, Person p2)
	{
		return new Integer(p1.getAvail()).compareTo(new Integer(p2.getAvail()));
	}
}