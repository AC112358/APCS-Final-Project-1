import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TestMethods {
	static Schedule schedule;
	static ArrayList<Subject> subjects;
	
	
	public static String subjName(String[] tokens){
		return subjName(tokens, 0);
	}
	public static String subjName(String[] tokens, int start){
		int endSubj = -1;
		String subjName = "";
		for (int i = start; i < tokens.length-2; i++){ //note this requires the name to have subj + taskname
			subjName += tokens[i] + " ";
			if (tokens[i].endsWith("\'")){
				endSubj = i;
				break;
			}
		}
		if (endSubj == -1){
			return "";
		}
		subjName = subjName.substring(1, subjName.length()-2);
		//System.out.println(subjName);
		return subjName;
	}
	
	public static int subjIndex(String[] tokens){
		int endSubj = -1;
		for (int i = 0; i < tokens.length-2; i++){ //note this requires the name to have subj + taskname
			if (tokens[i].endsWith("\'")){
				endSubj = i;
				break;
			}
		}
		if (endSubj == -1){
			return 0;
		}
		return endSubj;
	}
	
	public static void processText(String line){
		String[] tokens = line.split(" ");
		//System.out.println(line);
		try{
			if (tokens[0].startsWith("\'")){
				String subjName = subjName(tokens);
				//System.out.println(subjName);
				if (subjName.equals("")){
					return;
				}
				subjects.add(new Subject(subjName,Double.parseDouble(tokens[tokens.length-2])/100, Double.parseDouble(tokens[tokens.length-1])/100));
				System.out.println(subjName);
			}else if (tokens[0].startsWith("\"'")){
				tokens[0] = tokens[0].substring(1);
				String subjName = subjName(tokens);
				if (subjName.equals("")){
					return;
				}
				int index = tokens.length-3;
				int start = subjIndex(tokens);
				String name2 = "";
				if (tokens[index].endsWith("\"")){
					tokens[index] = tokens[index].substring(0, tokens[index].length()-1);
					for (int i = start+1; i <= index; i++){
						name2 += tokens[i].toLowerCase() + " ";
					}
					name2 = name2.substring(0, name2.length()-1);
				}else{
					return;
				}
				int subjIndex = -1;
				for (int i = 0; i < subjects.size() && subjIndex == -1; i++){
					if (subjects.get(i).name.equals(subjName)){
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
					System.out.println(subjName);
					schedule.tasks.add(new Task(subjName + " " + name2, subjects.get(subjIndex), t, v));
				}
			}else if (tokens[0].equals("break")){
				String breakName = subjName(tokens, 1);
				int index = tokens.length;
				schedule.breaks.add(new Break(breakName, Double.parseDouble(tokens[index-3]), 
						Double.parseDouble(tokens[index-2]), Double.parseDouble(tokens[index-1])));
			}else{
				return;
			}
		}catch(Exception e){ //hopefully this will not happen, since GUI will filter user input
			System.out.println("User input invalid!");
			System.exit(0);
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
		double orig = schedule.energy;
		ArrayList<ArrayList<Integer>> order = permutations(range(schedule.tasks.size()));
		for (int i = 0; i < order.size(); i++){
			ArrayList<Task> temp = new ArrayList<Task>();
			for (int j = 0; j < order.get(i).size(); j++){
				temp.add(schedule.tasks.get(order.get(i).get(j)));
			}
			schedule.tasks = temp;
			schedule.energy = orig;
			RankSchedule.runSchedule(schedule, true);
		}
	}
	
	public static double toMinutes(double militTime){ //e.g., 1200
		return ((int)militTime/100)*60 + ((int)militTime%100);
	}
	

	
	public static void main(String[] args) throws IOException{
		/*Task t = new Task("high diff, low e", 100, 1, 100, 10);
		Task t2 = new Task("low diff, high e", 1, 100, 100, 10);
		System.out.println(t);
		System.out.println(t2);*/
				//String name, double d, double e, double time, double var
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
		System.out.println(schedule);
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
		Collections.sort(schedule.breaks);
		makeSchedules();
		System.out.println("-----AND NOW FOR THE GRAND EVENT----");
		Schedule optimum = RankSchedule.optimizeSchedule2(schedule);
		//System.out.println(optimum);
		//System.out.println("UTILITY: " + optimum.utility);
		RankSchedule.runSchedule(optimum, true);
	}
}
