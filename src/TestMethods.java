import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TestMethods {
	public static void main(String[] args) throws IOException{
		BufferedReader b = new BufferedReader(new FileReader("scheduleTest.txt"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		StringTokenizer st = new StringTokenizer(b.readLine());
		Schedule schedule = new Schedule();
		schedule.energy = Double.parseDouble(st.nextToken());
		schedule.startTime = Double.parseDouble(st.nextToken());
		schedule.stopTime = Double.parseDouble(st.nextToken());
		schedule.idealStop = Double.parseDouble(st.nextToken());
		st = new StringTokenizer(b.readLine());
		while (st.hasMoreTokens()){
			
			st = new StringTokenizer(b.readLine());
		}
		
	}
}
