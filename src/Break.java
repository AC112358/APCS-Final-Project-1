
public class Break extends Task{
	double startTime, endTime;
	public Break(String name, double d, double e, double time, double var, double startTime, double endTime){
		super(name, d, e, time, var);
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
