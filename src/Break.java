
public class Break{
	double startTime, endTime;
	double time;
	String name;
	public Break(String n, double startTime, double endTime, double t){
		name = n;
		time = t;
		this.startTime = startTime;
		this.endTime = endTime;
		if (toMinutes(endTime) - toMinutes(startTime) < time){
			time = toMinutes(endTime) - toMinutes(startTime);
		}
	}
	 public static double toMinutes(double militTime){ //e.g., 1200
			return ((int)militTime/100)*60 + ((int)militTime%100);
		}
	public String toString(){
		String total = name;
		total += "\n" + "- Earliest: " + startTime + "\n- Latest: " + endTime;
		total += "\n- Time it takes: " + time;
		return total;
	}
    public int compareTo(Break b){
	return toMinutes(this.startTime) - toMinutes(b.startTime);
    }
}
