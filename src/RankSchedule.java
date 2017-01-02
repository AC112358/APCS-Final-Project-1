public class RankSchedule {
	public static double taskTime(Schedule s, int i){
		double k = 2;
		Task t = s.tasks.get(i);
		double total = (k*t.difficulty)/(t.enjoyment*s.energy)*(t.timeVar) + t.eTime;
		return total;
	}
	public static double newEnergy(Schedule s, int i){
		double k = -2;
		double c = -2;
		Task t = s.tasks.get(i);
		return k * t.taskTime + c * t.difficulty + s.energy;
	}
	public static double utility(Schedule s){
		double A = 1;
		double B = 1;
		double total = 0;
		for (int i = 0; i < s.tasks.size(); i++){
			total += A * s.tasks.get(i).enjoyment * s.tasks.get(i).taskTime;
		}
		total *= Math.sqrt(s.energy);
		total -= B * (s.stopTime - s.idealStop) * s.endTask.enjoyment;
		return total;
	}
	
}
