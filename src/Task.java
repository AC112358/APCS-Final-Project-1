
public class Task {
	String name;
	double difficulty, enjoyment, eTime, timeVar, taskTime;
	public Task(String name, double d, double e, double time, double var){
		this.name = name;
		difficulty = d;
		enjoyment = e;
		eTime = time;
		timeVar = var;
	}
	public Task(){
		name = "";
		difficulty = .5;
		enjoyment = .5;
		eTime = 10;
		timeVar = 5;
		taskTime = 0;
	}
	public Task(String name, Subject subj, double time, double var){
		this(name, subj.difficulty, subj.enjoyment, time, var);
	}
	public String toString(){
		String total = name;
		total += "\n" + "- Difficulty: " + difficulty + "\n- Enjoyment: " + enjoyment;
		total += "\n- Expected Time: " + eTime + "\n- Time Variation: " + timeVar;
		return total;
	}
	public Task makeCopy(){
		Task copy = new Task(name, difficulty, enjoyment, eTime, timeVar);
		copy.taskTime = taskTime;
		return copy;
	}
}
