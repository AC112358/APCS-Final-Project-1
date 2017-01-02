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
						Double.parseDouble(tokens[1])/100, Double.parseDouble(tokens[2])/100));
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
	
	public static ArrayList<ArrayList<Integer>> permutations(ArrayList<Integer> list){
		if (list.size() == 0){
			return null;
		}
		if (list.size() == 1){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(list.get(0));
			ArrayList<ArrayList<Integer>> temp2 = new ArrayList<ArrayList<Integer>>();
			temp2.add(temp);
			return temp2;
		}
		ArrayList<ArrayList<Integer>> total = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < list.size(); i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int j = 0; j < list.size(); j++){
				if (j != i){
					temp.add(list.get(j));
				}
			}
			ArrayList<ArrayList<Integer>> results = permutations(temp);
			temp.clear();
			for (int j = 0; j < results.size(); j++){
				temp = new ArrayList<Integer>();
				temp.add(list.get(i));
				temp.addAll(results.get(j));
				total.add(temp);
			}
		}
		return total;
	}
	public static ArrayList<Integer> range(int n){
		ArrayList<Integer> total = new ArrayList<Integer>();
		for (int i = 0; i < n; i++){
			total.add(i);
		}
		return total;
	}
	
	public static void makeSchedules(){
		ArrayList<ArrayList<Integer>> order = permutations(range(schedule.tasks.size()));
		for (int i = 0; i < order.size(); i++){
			ArrayList<Task> temp = new ArrayList<Task>();
			for (int j = 0; j < order.get(i).size(); j++){
				temp.add(schedule.tasks.get(order.get(i).get(j)));
			}
			schedule.tasks = temp;
			runSchedule();
		}
	}
	
	public static double toMinutes(double militTime){ //e.g., 1200
		return (militTime/100)*60 + (militTime%100);
	}
	
	public static void runSchedule(){
		System.out.println("\n\n");
		double newEnergy, newTime, totalTime;
		totalTime = 0;
		double timeDiff = toMinutes(schedule.stopTime) - toMinutes(schedule.startTime);
		for (int i = 0; i < schedule.tasks.size() && totalTime < timeDiff; i++){
			newTime = RankSchedule.taskTime(schedule, i);
			newEnergy = RankSchedule.newEnergy(schedule, i);
			if (newEnergy <= 0){
				Task t = new Task();
				t.name = "Break";
				t.taskTime = 20;
				t.difficulty = -1; //arbitrary as of now, increases energy
				t.enjoyment = -1;
				schedule.tasks.add(i, t);
			}
			newTime = RankSchedule.taskTime(schedule, i);
			newEnergy = RankSchedule.newEnergy(schedule, i);
			schedule.tasks.get(i).taskTime = newTime;
			schedule.energy = newEnergy;
			System.out.println(schedule.tasks.get(i).name + ": " + schedule.tasks.get(i).taskTime);
			System.out.println("\t" + schedule.energy);
			totalTime += newTime;
		}
		schedule.utility = RankSchedule.utility(schedule);
		System.out.println("Utility: " + schedule.utility);
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
		/*ArrayList<Integer> test = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++){
			test.add(i);
		}
		System.out.println(permutations(test).size());*/
		schedule.endTask = new Task("sleep", 0, 1, 0, 0);
		makeSchedules();
	}
}
