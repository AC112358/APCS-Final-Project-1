
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
}
