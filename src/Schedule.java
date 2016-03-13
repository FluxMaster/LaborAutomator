/*
	Copyright Andrew Polasek
    on July 7th 2015
*/

public class Schedule
{
	private Day sunday;
	private Day monday;
	private Day tuesday;
	private Day wednesday;
	private Day thursday;
	private Day friday;
	private Day saturday;
	
	//Constructor
	public Schedule(Day sunday, Day monday, Day tuesday, Day wednesday, Day thursday, Day friday, Day saturday)
	{
		this.sunday = sunday;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
	}
	
	//Getters
	public Day getSunday()
	{
		return this.sunday;
	}
	
	public Day getMonday()
	{
		return this.monday;
	}
	
	public Day getTuesday()
	{
		return this.tuesday;
	}
	
	public Day getWednesday()
	{
		return this.wednesday;
	}
	
	public Day getThursday()
	{
		return this.thursday;
	}
	
	public Day getFriday()
	{
		return this.friday;
	}
	
	public Day getSaturday()
	{
		return this.saturday;
	}
	
	public Day getDay(int x)
	{
		switch (x)
		{
			case 0: return sunday;
			case 1: return monday;
			case 2: return tuesday;
			case 3: return wednesday;
			case 4: return thursday;
			case 5: return friday;
			case 6: return saturday;
			default:System.out.println("ERROR: No such day exists");
		}
		return null;
	}
	
	//Setters
	public void setSunday(Day sunday)
	{
		this.sunday = sunday;
	}
	public void setMonday(Day monday)
	{
		this.monday = monday;
	}
	public void setTuesday(Day tuesday)
	{
		this.tuesday = tuesday;
	}
	public void setWednesday(Day wednesday)
	{
		this.wednesday = wednesday;
	}
	public void setThursday(Day thursday)
	{
		this.thursday = thursday;
	}
	public void setSetFriday(Day friday)
	{
		this.friday = friday;
	}
	public void setSaturday(Day saturday)
	{
		this.saturday = saturday;
	}
}	