import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TestMethods {
	static Schedule schedule;
	static ArrayList<Subject> subjects;
	
	public static void processText(String line){
		String[] tokens = line.split(" ");
		System.out.println(line);
		try{
			if (tokens[0].startsWith("\'") && tokens[0].endsWith("\'")){
				subjects.add(new Subject(tokens[0].substring(1, tokens[0].length()-1), 
						Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
			}else if (tokens[0].startsWith("\"'") && tokens[0].endsWith("\'")){
				tokens[0] = tokens[0].substring(2, tokens[0].length()-1);
				int index = tokens.length-3;
				String name2 = "";
				if (tokens[index].endsWith("\"")){
					tokens[index] = tokens[index].substring(0, tokens[index].length()-1);
					for (int i = 1; i <= index; i++){
						name2 += tokens[i].toLowerCase() + " ";
					}
					name2 = name2.substring(0, name2.length()-1);
				}else{
					return;
				}
				int subjIndex = -1;
				for (int i = 0; i < subjects.size() && subjIndex == -1; i++){
					if (subjects.get(i).name.equals(tokens[0])){
						subjIndex = i;
					}
				}
				double t = Double.parseDouble(tokens[tokens.length-2]);
				double v = Double.parseDouble(tokens[tokens.length-1]);
				if (subjIndex == -1){
					//add ability to give difficulty, enjoyment to specific tasks here
					return;
				}
				else{
					schedule.tasks.add(new Task(tokens[0] + " " + name2, subjects.get(subjIndex), t, v));
				}
			}else{
				return;
			}
		}catch(Exception e){
			return;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader b = new BufferedReader(new FileReader("scheduleTest.txt"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		StringTokenizer st = new StringTokenizer(b.readLine());
		schedule = new Schedule();
		schedule.energy = Double.parseDouble(st.nextToken());
		schedule.startTime = Double.parseDouble(st.nextToken());
		schedule.stopTime = Double.parseDouble(st.nextToken());
		schedule.idealStop = Double.parseDouble(st.nextToken());
		subjects = new ArrayList<Subject>();
		st = new StringTokenizer(b.readLine());
		String temp = "";
		boolean atEnd = false;
		while (!atEnd){
			temp = "";
			while (st.hasMoreTokens()){
				temp += st.nextToken() + " ";
			}
			if (temp.length() > 0){
				processText(temp.substring(0, temp.length()-1));
			}
			String newLine = b.readLine();
			if (newLine == null){
				atEnd = true;
			}else{
				st = new StringTokenizer(newLine);
			}
		}
		//System.out.println(schedule);
		/*for (int i = 0; i < subjects.size(); i++){
			System.out.println(subjects.get(i).name + " " + subjects.get(i).difficulty 
					+ " " + subjects.get(i).enjoyment);
			
		}*/
	}
}
