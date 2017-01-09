import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ScheduleDriver {
	public static void run(){
		try{
			BufferedReader b = new BufferedReader(new FileReader("scheduleTest.txt"));
			//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
			StringTokenizer st = new StringTokenizer(b.readLine());
			TestMethods.schedule = new Schedule();
			TestMethods.schedule.energy = Double.parseDouble(st.nextToken());
			TestMethods.schedule.startTime = Double.parseDouble(st.nextToken());
			TestMethods.schedule.stopTime = Double.parseDouble(st.nextToken());
			TestMethods.schedule.idealStop = Double.parseDouble(st.nextToken());
			TestMethods.subjects = new ArrayList<Subject>();
			TestMethods.schedule.endTask = new Task("sleep", 0, 1, 0, 0);
			st = new StringTokenizer(b.readLine());
			String temp = "";
			boolean atEnd = false;
			while (!atEnd){
				temp = "";
				while (st.hasMoreTokens()){
				    temp += st.nextToken().toLowerCase() + " ";
				}
				if (temp.length() > 0){
				    System.out.println(ScheduleFilter.filterLine(temp.substring(0, temp.length()-1)));
					TestMethods.processText(temp.substring(0, temp.length()-1));		
				}
				String newLine = b.readLine();
				if (newLine == null){
					atEnd = true;
				}else{
					st = new StringTokenizer(newLine);
				}
			}
			b.close();
			//	TestMethods.schedule.endTask = new Task("sleep", 0, 1, 0, 0);
			Collections.sort(TestMethods.schedule.breaks);
			PrintWriter out = new PrintWriter(new FileWriter(new File("schedule.txt")));
			Schedule optimum = RankSchedule.optimizeSchedule2(TestMethods.schedule);
			String total = RankSchedule.runSchedule2(optimum, false);
			String[] lines = total.split("\n");
			for (String line : lines){
				out.println(line);
			}
			//	System.out.println(optimum.endTask);
			out.close();
		}catch(Exception e){
			System.exit(0);
		}
	}
	public static void main(String[] args){
		run();
	}
}
