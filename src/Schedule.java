import java.util.ArrayList;

public class Schedule implements Comparable<Schedule>{
	ArrayList<Task> tasks = new ArrayList<Task>();
	double energy, utility;
	double idealStop, startTime, stopTime;
	double actualStop;
	Task endTask;
	int seed = (int)(Math.random()*Integer.MAX_VALUE);
	ArrayList<Break> breaks = new ArrayList<Break>();
	public Schedule(double e, double start, double stop){
		energy = e;
		idealStop = stop;
		startTime = start;
		actualStop = stop;
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
	
	public Schedule makeCopy(){
		Schedule copy = new Schedule(energy, startTime, idealStop);
		copy.stopTime = stopTime;
		copy.utility = utility;
		for (int i = 0; i < tasks.size(); i++){
			copy.tasks.add(tasks.get(i).makeCopy());
		}
		copy.endTask = endTask.makeCopy();
		for (int i = 0; i < breaks.size(); i++){
			copy.breaks.add(breaks.get(i).makeCopy());
		}
		copy.seed = seed;
		return copy;
	}
	public int compareTo(Schedule other){
		return (int)(Math.pow(10, 6)*utility-other.utility);
	}
}
