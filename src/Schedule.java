import java.util.ArrayList;

public class Schedule {
	ArrayList<Task> tasks = new ArrayList<Task>();
	double energy, utility;
	double idealStop, startTime, stopTime;
	Task endTask;
	public Schedule(double e, double start, double stop){
		energy = e;
		idealStop = stop;
		startTime = start;
	}
	public Schedule(){
		energy = 100;
		utility = 0;
	}
}
