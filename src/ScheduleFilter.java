import java.util.ArrayList;

public class ScheduleFilter {
	static ArrayList<Subject> subjects = new ArrayList<Subject>();
	static Schedule schedule = new Schedule();
	
	public static String filterLine1(String line){
		String message = "";
		try{
			String[] tokens = line.split(" ");
			if (tokens.length != 4){
				return "Either too much or too little infomation about schedule times";
			}
			message = "Energy must be a number";
			schedule.energy = Double.parseDouble(tokens[0]);
			message = "Start time must be a number";
			schedule.startTime = Double.parseDouble(tokens[1]);
			message = "End time must be a number";
			schedule.stopTime = Double.parseDouble(tokens[2]);
			message = "Ideal end time must be a number";
			schedule.idealStop = Double.parseDouble(tokens[3]);
			if (schedule.idealStop > schedule.stopTime){
				return "Ideal stop time must be before actual stop time";
			}
			if (schedule.startTime >= schedule.stopTime){
				return "Start time must be before stop time";
			}
			if (schedule.stopTime >= 2500){
				return "Stop time must be before 24:5999...";
			}
		}catch(Exception e){
			return message;
		}
		return "";
	}
	public static String filterLine(String line){
		String[] tokens = line.split(" ");
		String message = "";
		try{
			if (tokens[0].startsWith("\'")){
				String subjName = TestMethods.subjName(tokens);
				//System.out.println(subjName);
				if (subjName.equals("")){
					return "Cannot have a blank subject name";
				}
				message = "Subject difficulty & enjoyment must be numbers";
				double d = Double.parseDouble(tokens[tokens.length-2])/100;
				double e = Double.parseDouble(tokens[tokens.length-1])/100;
				if (d <= 0 || d > 1 || e <= 0 || e > 1){
					return "Subject difficulty & enjoyment must be between 0 & 100, not including 0";
				}
				subjects.add(new Subject(subjName, d, e));
			//	System.out.println(subjName);
			}else if (tokens[0].startsWith("\"'")){
				tokens[0] = tokens[0].substring(1);
				String subjName = TestMethods.subjName(tokens);
				if (subjName.equals("")){
					return "Cannot have a blank task name";
				}
				int index = tokens.length-3;
				int start = TestMethods.subjIndex(tokens);
				String name2 = "";
				if (tokens[index].endsWith("\"")){
					tokens[index] = tokens[index].substring(0, tokens[index].length()-1);
					for (int i = start+1; i <= index; i++){
						name2 += tokens[i].toLowerCase() + " ";
					}
					name2 = name2.substring(0, name2.length()-1);
				}else{
					return "Text file format invalid: \" needed at end";
				}
				int subjIndex = -1;
				for (int i = 0; i < subjects.size() && subjIndex == -1; i++){
					if (subjects.get(i).name.equals(subjName)){
						subjIndex = i;
					}
				}
				message = "Task expected time and variability must be numbers";
				double t = Double.parseDouble(tokens[tokens.length-2]);
				double v = Double.parseDouble(tokens[tokens.length-1]);
				if (subjIndex == -1){
					//add ability to give difficulty, enjoyment to specific tasks here
					return "Task name must be in format: subjectname taskname, e.g., precalc hw";
				}
				else{
					//System.out.println(subjName);
					schedule.tasks.add(new Task(subjName + " " + name2, subjects.get(subjIndex), t, v));
				}
			}else if (tokens[0].equals("break")){
				String breakName = TestMethods.subjName(tokens, 1);
				int index = tokens.length;
				message = "Break times must be numbers";
				double start = Double.parseDouble(tokens[index-3]);
				double end = Double.parseDouble(tokens[index-2]);
				if (start >= end || end >= 2500){
					return "Start time must be before end time and maximum time is 24:59.9999";
				}
				schedule.breaks.add(new Break(breakName, start, end, Double.parseDouble(tokens[index-1])));
			}else if(tokens[0].equals("endtask")){
				if (tokens.length < 4){
				    return "Not enough information for end task to be made";
				}
				String endName = TestMethods.subjName(tokens, 1); //can be blank it doesn't matter
				if (endName == null){endName = "";}
				message = "End task difficulty & enjoyment must be numbers";
				double d = Double.parseDouble(tokens[tokens.length-2])/100;
				double e = Double.parseDouble(tokens[tokens.length-1])/100;
				if (d <= 0 || d > 1 || e <= 0 || e > 1){
					return "End task difficulty & enjoyment must be between 0 & 100, not including 0";
				}
				schedule.endTask = new Task(endName, d, e, 0, 0);
			    }else{
				return "Text file not formatted properly, could not be processed";
			}
			RankSchedule.T = RankSchedule.T(schedule);
	    	RankSchedule.b = RankSchedule.b(schedule);
		}
		catch(Exception e){ //hopefully this will not happen, since GUI will filter user input
			return message;
		}
		message = "";
		return message;
	}
	public static String filterAll(String[] lines){
		String msgs = "";
		String output = "";
		for (int i = 0; i < lines.length; i++){
			output = filterLine(lines[i]);
			if (!output.equals("")){
				msgs += "\n" + output;
			}
		}
		return msgs;
	}
}
