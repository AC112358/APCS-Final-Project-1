import java.util.ArrayList;

public class Schedule {
	ArrayList<Task> tasks = new ArrayList<Task>();
	double energy, utility;
	double idealStop, startTime, stopTime;
	Task endTask;
	ArrayList<Break> breaks = new ArrayList<Break>();
	public Schedule(double e, double start, double stop){
		energy = e;
		idealStop = stop;
		startTime = start;
	}
	public Schedule(){
		energy = 100;
		utility = 0;
	}
	public String toString(){
		String total = "";
		total += "" + "Energy: " + energy + ", Start time: " + startTime + ", Stop time: " + stopTime + ", Ideal stop: " + idealStop;
		total += "\nTASKS:";
		for (int i = 0; i < tasks.size(); i++){
			total += "\n" + tasks.get(i);
		}
		total += "\nBREAKS:";
		for (int i = 0; i < breaks.size(); i++){
			total += "\n" + breaks.get(i);
		}
		return total;
	}
}
