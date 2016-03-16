import java.util.*;
import java.io.*;

public class ScheduleGenerator
{
	public static void main(String[] args) throws IOException
	{
		Random r = new Random();
		int n = r.nextInt(5163);
		
		Scanner nameFinder = new Scanner(new File ("src/Names.txt"));
		String name = "";
		
		while(n > 0 && nameFinder.hasNextLine())
		{
			name = nameFinder.nextLine();
			n--;
		}
		//System.out.println(name.indexOf(" "));
		name = name.substring(0,name.indexOf(" "));
		int room = (r.nextInt(6)*100+r.nextInt(21));
		
		FileWriter fw = new FileWriter("Schedules/"+(name+"_"+String.valueOf(room)+".csv"));
		
		fw.write("Name:,"+name+",,,,,,\n");
		fw.write("Room:,"+room+",,,,,,\n");
		if(r.nextInt(2)==0)
		{
			fw.write("Preference:,Clean\n");
		}
		else
		{
			fw.write("Preference:,Cook\n");
		}
		fw.write(",,,,,,,\n");
		fw.write("Schedule,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday\n");
		for(int i = 6; i < 23; i++)
		{
			fw.write(i+"-"+(i+1));
			for(int j = 0; j<7 ; j++)
			{
				fw.write(",");
				if(r.nextInt(2)==1)
				{
					fw.write("x");
				}				
			}
			fw.write("\n");
		}
		fw.close();
	}
}