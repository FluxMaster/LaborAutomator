/*
	Copyright Andrew Polasek
    on July 7th 2015
*/

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Laborer{
	
	public static void main(String[] args) throws IOException
	{
		int NUMBEROFHOURS = 4;
		
		//We need to input files and create the data structure
		File sf = new File("./Schedules");
		File[] Scheds = sf.listFiles();
		
		//Prints names of Schedules
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
			Scanner sc = new Scanner(Scheds[i]);
			sc.useDelimiter(",|\n");
			//Parse name and Room Number
			sc.next();
			String name = new String(sc.next());
			sc.nextLine();
			sc.next();
			String room = sc.next();
			//System.out.println(name + " " + room);
			
			//Begin Loop
			sc.nextLine();
			sc.nextLine();
			//sc.nextLine();
			
			Day[] week = new Day[7];
			for(int q = 0; q < 7; q++)
			{
				week[q] = new Day();
			}
			
			/******rewrite using string splitting******
			for(int k = 0; k < 17; k++)
			{
				String temp = sc.nextLine();
				String[] strarr = temp.split(",");
				System.out.print("strarr length: " + strarr.length + " ");
				/*debug
				for(int q = 0; q < strarr.length; q++)
				{
					System.out.println(strarr[q]);
				}
				
				
				for(int j = 1; j < 8; j++)
				{
					//Parse Days and Form Objects
					if(strarr[j] == "")
					{
						(week[j-1]).setBlock(k,1);
						//System.out.print(1);
					}
					else
					{
						(week[j-1]).setBlock(k,0);
						//System.out.print(temp);
					}
					System.out.print(j + ": " + strarr[j] + " ");
				}
				System.out.println();
			}
			******rewrite using string splitting******/
			
			//******rewrite using string splitting******
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
						if(temp.equals(""))
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
				//******rewrite using string splitting******
			}
			catch(NoSuchElementException e)
			{
				System.out.println(name);
				System.exit(1);
			}
			
			
			
			
			//System.out.println();
			//End Loop
			
			/*Debug
			System.out.println();
			for(int g = 0; g < 17; g++)
			{
				for(int e = 0; e < 7; e++)
				{
					System.out.print(week[e].getBlock(g));
				}
				System.out.println();
			}
			*/
			
			//Form Schedule Object
			Schedule finalSched = 
				new Schedule(week[0],week[1],week[2],
							 week[3],week[4],week[5],
							 week[6]);
			//Form Person Object
			Person finalPerson = new Person(name,room,finalSched);
			//Put it in the Hash
			members.put(finalPerson.getName(),finalPerson);
			
			/*
			Iterator<Map.Entry<String, Person>> iterator = members.entrySet().iterator() ;
			while(iterator.hasNext()){
				Map.Entry<String, Person> studentEntry = iterator.next();
				System.out.println(studentEntry.getKey() +" :: "+ studentEntry.getValue());
			}
			*/
			
		}
		//End Loop	
	
		//Then we need to populate the "Sorter" structure
		File jobs = new File("./Jobs.csv");
		//Parse Jobs
		Scanner jl = new Scanner (jobs);
		jl.nextLine();
		jl.useDelimiter(",|\n");
		
		ArrayList jobList = new ArrayList(0);
		
		//Put the jobs in the Job List
		while(jl.hasNext())
		{
			String[] splitter = (jl.nextLine()).split(",");
			Job temp = new Job(splitter[0],
								Integer.parseInt(splitter[1]),
								Integer.parseInt(splitter[2]),
								Integer.parseInt(splitter[3]));
			jl.nextLine();
			jobList.add(temp);
		}
		
		
		
		/*Put the jobs in the Job List
		while(jl.hasNext())
		{
			Job temp = new Job(jl.next(),
								jl.nextInt(),
								jl.nextInt(),
								jl.nextInt());
			jl.nextLine();
			jobList.add(temp);
		}
		*/
		
		//Begin Loop through Jobs
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
						//can't do
						cando = false;
					}
				}
				if(cando)
				{
					((Job)jobList.get(i)).addPerson(temp);
					temp.incrementAvail();
				}
			}
		}
		//End Loops
		
		/*Debug
		System.out.println("PreSort");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println(jobList.get(i));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				System.out.print(((Job)jobList.get(i)).getPerson(j).getName() + " ");
			}
			System.out.println();
		}
		*/
		
		
		//Sort "Sorter" by number of people
		Collections.sort(jobList);
		
		/* Debug
		System.out.println("PostSort");
		for(int i = 0; i < jobList.size(); i++)
		{
			System.out.println(jobList.get(i));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				System.out.print(((Job)jobList.get(i)).getPerson(j).getName() + " ");
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
		//Create Labor Schedule Structure??
		
		//Go through each job
		for(int i = 0; i < jobList.size(); i++)
		{
			boolean posfilled = false;
			//System.out.println(((Job)jobList.get(i)));
			//Choose people with least availability (Pre Sorted)!
			System.out.println((((Job)jobList.get(i))));
			for(int j = 0; j < ((Job)jobList.get(i)).getSize(); j++)
			{
				//check if at hours!
				if((((Job)jobList.get(i)).getPerson(j).getHours() + ((Job)jobList.get(i)).getLength() <= NUMBEROFHOURS))
					if(!(((Job)jobList.get(i)).getPerson(j).hasJob(((Job)jobList.get(i)))))
					{
						//write labor schedule to file with a >> command
						System.out.println(((Job)jobList.get(i)).getPerson(j).getName() + "\n");
						((Job)jobList.get(i)).getPerson(j).addHours(((Job)jobList.get(i)).getLength());
						((Job)jobList.get(i)).getPerson(j).addJob(((Job)jobList.get(i)));
						posfilled = true;
						break;
					}
			}
			if(!posfilled)
			{
				System.out.println("Nobody to fill this\n");
			}
		}
		//End Loop
	}
}
