
public class Break implements Comparable<Break>{
	double startTime, endTime;
	double time;
	String name;
	public Break(String n, double startTime, double endTime, double t){
		name = n;
		time = t;
		this.startTime = startTime;
		this.endTime = endTime;
		if (time < 0){
			time = 0;
		}
		/*if (toMinutes(endTime) - toMinutes(startTime) < time){
			time = toMinutes(endTime) - toMinutes(startTime);
		}*/
	}
	public Break(){
		name = "";
		time = 0;
		startTime = 0;
		endTime = 0;
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
    	return (int)(toMinutes(this.startTime) - toMinutes(b.startTime));
    }
    
    public Break makeCopy(){
    	return new Break(name, startTime, endTime, time);
    }
    
}
