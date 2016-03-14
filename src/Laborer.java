/*
	Copyright Andrew Polasek
    on July 7th 2015
*/

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Laborer
{
	
	public static void main(String[] args) throws IOException
	{
		
		//This is the total number of Hours you have devoted to Flex Labor
		int HOURSFLEX = 45;
		
		//This is the number of Hours you expect members to do weekly.
		int NUMBEROFHOURS = 4;
		
		//We need to input files and create the data structure
		File sf = new File("Schedules");
		File[] Scheds = sf.listFiles();
		
		
		/*
		for(int i = 0; i < Scheds.length; i++)
		{
			System.out.println(Scheds[i].getName());
		}
		*/
		
		//Create Member Hash
		Map members = new HashMap<String, Person>();
		
		//Begin Loop
		for(int i = 0; i < Scheds.length; i++)
		{
			//Open file
			//System.out.println("Opening " + (Scheds[i]).getName());
			Scanner sc = new Scanner(Scheds[i]);
			sc.useDelimiter(",|\n");
			//Parse name and Room Number
			sc.next();
			String name = new String(sc.next());
			/*
			if(name.compareTo("") == 0);
			{
				//System.out.println("Somebody didn't write their name at the top");
				name = Scheds[i].getName();
			}
			*/
			//System.out.println(name);
			
			sc.nextLine();
			sc.next();
			String room = sc.next();
			//System.out.println(name + " " + room);
			
			sc.nextLine();
			sc.next();
			String preference = sc.next();
			
			
			//Begin Loop
			sc.nextLine();
			sc.nextLine();
			//sc.nextLine();
			
			Day[] week = new Day[7];
			for(int q = 0; q < 7; q++)
			{
				week[q] = new Day();
			}
			try
			{
				for(int k = 0; k < 17; k++)
				{
					String temp = sc.nextLine();
					//int j = 0;
					sc.next();
					for(int j = 0; j < 7; j++)
					{
						//Parse Days and Form Objects
						//If This and (the next are empty due to commas or end line), we're all good
						try
						{	
							temp = sc.next();
						}
						catch(NoSuchElementException e)
						{
							temp = "";
						}
						if(temp.equals("") || temp.equals("\r"))
						{
							(week[j]).setBlock(k,1);
							//System.out.print(j + ":Free ");
						}
						else
						{
							(week[j]).setBlock(k,0);
							//System.out.print(j+ ":" + temp + " ");
						}
					}
					//System.out.println();
				}
				
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Error in input from:");
				System.out.println(Scheds[i].getName());
				System.out.println(name);
				System.exit(1);
			}
			catch(Exception e)
			{
				System.out.println("Big Error in input from:");
				System.out.println(Scheds[i].getName());
				System.out.println(name);
				System.exit(1);
			}
			
			
			
			
			//System.out.println();
			//End Loop
			
			/*
			System.out.println();
			for(int g = 0; g < 17; g++)
			{
				for(int e = 0; e < 7; e++)
				{
					System.out.print(week[e].getBlock(g));
				}
				System.out.println();
			}
			System.out.println();
			*/
			
			//Form Schedule Object
			Schedule finalSched = 
				new Schedule(week[0],week[1],week[2],
							 week[3],week[4],week[5],
							 week[6]);
			//Form Person Object
			Person finalPerson = new Person(name,room,preference,finalSched);
			//Put it in the Hash
			//System.out.println("Putting " + finalPerson.getName() + " in the hash");
			members.put((String)(finalPerson.getName() +" "+ finalPerson.getRoom()),finalPerson);
		}
		//End Loop	
	
		// Debug
		
		/*
		Iterator<Map.Entry<String, Person>> debugIterator = members.entrySet().iterator() ;
		while(debugIterator.hasNext()){
			Map.Entry<String, Person> studentEntry = debugIterator.next();
			System.out.println(studentEntry.getKey() +" :: "+ studentEntry.getValue());
		}
		*/
			
	
	
		//Then we need to populate the "Sorter" structure
		File jobs = new File("Jobs/Jobs.csv");		
		//Parse Jobs
		Scanner jl = new Scanner (jobs);
		//jl.nextLine();
		jl.useDelimiter(",|\n");
		
		ArrayList jobList = new ArrayList(0);
		
		//Put the jobs in the Job List
		while(jl.hasNext())
		{
			String jobline = new String(jl.nextLine());
			if(!(jobline.isEmpty()))
			{
				String[] splitter = (jobline).split(",");
				if(!(splitter[0].charAt(0) == '#'))
				{
					Job temp = new Job(splitter[0],
									Integer.parseInt(splitter[1]),
									Integer.parseInt(splitter[2]),
									Integer.parseInt(splitter[3]),
									splitter[4]);
					jobList.add(temp);
				}
			}
		}
		try
		{
			Scanner aj = new Scanner(new File("Jobs/AssignedJobs.csv"));
			try
			{	
				//Scan assigned job list "#person,name,day,time,length"
				System.out.println("Assigned Jobs");
				//System.out.println("StartLoop");
				while(aj.hasNextLine())
				{
					String jobline = aj.nextLine();
					String[] splitter = (jobline).split(",");
					if(!(splitter[0].charAt(0) == '#'))
					{
						Job temp = new Job(splitter[1],
										Integer.parseInt(splitter[2]),
										Integer.parseInt(splitter[3]),
										Integer.parseInt(splitter[4]),
										"Assigned");
						((Person)members.get(splitter[0])).addJob(temp);
						System.out.println(temp + " " + splitter[0]);
					}
					
				}
				
				//Hard Coded Stuff Build Parser for this instead later
				/*
				((Person)members.get("Olivia505")).addHours(2);
				((Person)members.get("Callie404")).addHours(2);
				((Person)members.get("Taylor210")).addHours(2);
				*/
			}
			catch(Exception e)
			{
				System.out.println(e);
				System.out.println("Something is probably wrong with your AssignedJobs.csv");
			}
		}
		catch(Exception e)
		{
			//System.out.println(e.toString());
			System.out.println("No Assigned Jobs");
		}
		//By sorting the job list by time, users can order the jobs
		//in Jobs.csv arbitrarily, but my lazy hack for preventing
		//counting the same job multiple times will still work
		Collections.sort(jobList, new JobTimeSorter());
		
		//Begin Loop through Jobs
		Job lastJob = new Job("",0,0,0,"");
		for(int i = 0; i < jobList.size(); i++)
		{
			//Begin Loop through people
			Iterator<Map.Entry<String, Person>> iterator = members.entrySet().iterator() ;
			while(iterator.hasNext())
			{
				Map.Entry<String, Person> studentEntry = iterator.next();
				Person temp = studentEntry.getValue(); 
				//Put people in each job list
				boolean cando = true;
				for(int j = 0; j < ((Job)jobList.get(i)).getLength(); j++)
				{
					if((temp.getSchedule().getDay(((Job)jobList.get(i)).getDay()).getBlock(((Job)jobList.get(i)).getTime()+j))==0)
					{
						//System.out.println(temp.getName() + " " + temp.getRoom() + " can't do " + (((Job)jobList.get(i)).getName()) + " because of conflict at " + (((Job)jobList.get(i)).getTime()+j));
						//can't do
						cando = false;
					}
				}
				if(cando)
				{
					//System.out.println(temp.getName() + " can do it");
					((Job)jobList.get(i)).addPerson(temp);
					if(!((Job)jobList.get(i)).equals(lastJob))
						temp.incrementAvail();
				}
			}
			lastJob = (Job)jobList.get(i);
		}
		//End Loops
		
		/* Debug
		
		
		System.out.println("PreSort");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println(jobList.get(i));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				System.out.print(((Job)jobList.get(i)).getPerson(j).getName() + ((Job)jobList.get(i)).getPerson(j).getRoom() + " ");
			}
			System.out.println();
		}
		
		
		
		
		Iterator<Map.Entry<String, Person>> debugIterator = members.entrySet().iterator() ;
		while(debugIterator.hasNext()){
			Map.Entry<String, Person> studentEntry = debugIterator.next();
			System.out.println(studentEntry.getKey() +" :: "+ studentEntry.getValue());
		}
		
		Debug */
	
		List<Person> peopleByAvail = new ArrayList<Person>(members.values());
		Collections.sort(peopleByAvail);
		
		/*
		System.out.println("People by Availability");
		for(int i = 0; i < peopleByAvail.size(); i++)
		{
			System.out.println(peopleByAvail.get(i) + ": " + (peopleByAvail.get(i)).getAvail());
		}
		*/
		
		if(HOURSFLEX>0)
		{
			System.out.println("Flex Laborers");
			
			int w = 0;
			while(HOURSFLEX > 3)
			{
				//System.out.println(peopleByAvail.get(w).getName());
				((Person)(peopleByAvail.get(w))).addJob(new Job("Flex Labor", 0, 0, 4,"Flex"));
				//((Person)(peopleByAvail.get(w))).addHours(4);
				HOURSFLEX-=4;
				System.out.println(((Person)peopleByAvail.get(w)) + " has 4 hours of Flex Labor");
				w++;
			}
			
			System.out.println("You have " + HOURSFLEX + " hours left of Flex Labor");	
		}
		else
		{
			System.out.println("No Flex Laborers");
		}
		//Sort "Sorter" by number of people
		
		Collections.sort(jobList,new JobSizeSorter());
		
		/*
		System.out.println("PostSort");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println(jobList.get(i));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				System.out.println(((Job)jobList.get(i)).getPerson(j).getName() + ((Job)jobList.get(i)).getPerson(j).getRoom());
			}
			System.out.println();
		}
		*/
		
		for(int i = 0; i<jobList.size(); i++)
		{
			((Job)jobList.get(i)).sortPeople();
		}
		
		/*Debug
		System.out.println("PostSort");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println(jobList.get(i));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				System.out.print(((Job)jobList.get(i)).getPerson(j).getName() + " " + ((Job)jobList.get(i)).getPerson(j).getAvail());
			}
			System.out.println();
		}
		*/
		
		//Then we need to go through the "Sorter" create the "Labor Schedule"
		
		int weight = 1000;
		while(weight>=0)
		{			
			boolean SAT = true;
			FileWriter fw = new FileWriter("ScheduleData.csv");
			fw.write("#person,name,day,time,length"+"\n");
			//Go through each job
			for(int i = 0; i < jobList.size(); i++)
			{
				boolean posfilled = false;
				//Choose people with least availability.
				//We need to resort if we want to capture preferences.
				//Such a sort will subtract the current weight if the job type matches person's preference
				
				//Adjust for preferences
				for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
				{
					if(((((Job)jobList.get(i)).getPerson(j)).getPreference()).equalsIgnoreCase(((Job)jobList.get(i)).getType()))
						(((Job)jobList.get(i)).getPerson(j)).subAvail(weight);
					//System.out.println((((Job)jobList.get(i)).getPerson(j)) + " had their weight reduced to " + (((Job)jobList.get(i)).getPerson(j)).getAvail());
				}
				//Sort for preferences
				((Job)jobList.get(i)).sortPeople();
				
				for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
				{
					//check if at hours!
					if((((Job)jobList.get(i)).getPerson(j).getHours() + ((Job)jobList.get(i)).getLength() <= NUMBEROFHOURS))
					{
						if(!(((Job)jobList.get(i)).getPerson(j).hasJob(((Job)jobList.get(i)))))
						{
							((Job)jobList.get(i)).getPerson(j).addJob(((Job)jobList.get(i)));
							((Job)jobList.get(i)).setDoer(((Job)jobList.get(i)).getPerson(j));
							fw.write(((Job)jobList.get(i)).getPerson(j)+","+((Job)jobList.get(i)).getName()+","+((Job)jobList.get(i)).getDay()+","+((Job)jobList.get(i)).getTime()+","+((Job)jobList.get(i)).getLength()+"\n");
							posfilled = true;
							break;
						}
					}
				}
				
				//Adjust preferences back;
				for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
				{
					if(((((Job)jobList.get(i)).getPerson(j)).getPreference()).equalsIgnoreCase(((Job)jobList.get(i)).getType()))
						(((Job)jobList.get(i)).getPerson(j)).addAvail(weight);
					//System.out.println((((Job)jobList.get(i)).getPerson(j)) + " had their weight reverted to " + (((Job)jobList.get(i)).getPerson(j)).getAvail());
				}
				
				if(!posfilled)
				{
					System.out.println("Nobody to Fill " + jobList.get(i));
					for(int j = 0; j<jobList.size(); j++)
					{
						((Job)jobList.get(j)).getDoer().removeJob((Job)jobList.get(j));
						((Job)jobList.get(j)).removeDoer();
					}
					SAT = false;
					break;
				}
			}
			if(SAT==false)
			{
				System.out.println("SAT FALSE ON WEIGHT "+ weight);
				weight--;
			}
			else
			{
				System.out.println("SAT TRUE ON WEIGHT " + weight);
				System.out.println("Saving Schedule Data");
				fw.close();
				weight = -1;
			}
		}
		
		Collections.sort(jobList,new JobTimeSorter());
		
		System.out.println("Jobs by Time");
		double matchCount = 0;
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.print((((Job)jobList.get(i))) + " ");
			System.out.print((((Job)jobList.get(i))).getDoer() + " Preference: " + (((Job)jobList.get(i))).getDoer().getPreference());
			if(((Job)jobList.get(i)).getType().equals((((Job)jobList.get(i))).getDoer().getPreference()))
			{
				matchCount++;
				System.out.println(" Match!");
			}
			else
			{
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("Match Count is: " + matchCount);
		System.out.println("Match rate is: " + (double)(matchCount/(double)jobList.size()));
		
		
		//Go Through Each Person
		//Print the people with not enough hours
		System.out.println("Students with not enough hours");
		Iterator<Map.Entry<String, Person>> iterator = members.entrySet().iterator() ;
		while(iterator.hasNext())
		{
			Map.Entry<String, Person> studentEntry = iterator.next();
			if(studentEntry.getValue().getHours() < NUMBEROFHOURS)
			{
				System.out.println(studentEntry.getValue().getName() + ": " + studentEntry.getValue().getRoom() + " needs " + (NUMBEROFHOURS - studentEntry.getValue().getHours()) + " hours");
			}
		}
	}
}
