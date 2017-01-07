import java.util.ArrayList;

public class RankSchedule {
	static double T = 1;
	static double b = 1;
	public static double F(Schedule s, int k, int r, double t){
		double sum1 = 1;
		double sum2 = 1;
		for (int i = 0; i < r; i++){
			sum2 += s.tasks.get(i).taskTime;
		}
		for (int i = k; i < r; i++){
			sum1 += s.tasks.get(i).taskTime;
		}
		return t/T * s.tasks.get(r).taskTime * (Math.pow(sum1, b) - Math.pow(sum2, b));
	}
	public static double b(Schedule s){
		return .9;
	}
	public static double newEnergy2(Schedule s, int i){ //energy in this case only determines break times
		return newEnergy(s, i);
	}
public static double T(Schedule s){
		return 100;
	}
	public static double taskTime2(Schedule s, int r, int breakIndex){
		double total =  1;
		for (int i = 0; i < r; i++){
			total += s.tasks.get(i).taskTime;
		}
		total = Math.pow(total, b);
		total *= s.tasks.get(r).taskTime;
		for (int i = 0; i < breakIndex; i++){
			total += F(s, i, r, s.breaks.get(i).time);
		}
		return total;
	}
	
	public static void runSchedule2(Schedule schedule, boolean verbose){
		if (verbose){System.out.println("\n\n");}
			double newEnergy, newTime, totalTime;
			newTime = 0;
			newEnergy = schedule.energy;
			totalTime = 0;
			double timeDiff = toMinutes(schedule.stopTime) - toMinutes(schedule.startTime);
			double curTime = toMinutes(schedule.startTime);
			int breakIndex = 0;
			for (int i = 0; i < schedule.tasks.size() && totalTime < timeDiff; i++){
			    if (breakIndex < schedule.breaks.size() && curTime >= toMinutes(schedule.breaks.get(breakIndex).startTime)){
			    	if (verbose){
			    		System.out.println(schedule.breaks.get(breakIndex).name + ": " + schedule.breaks.get(breakIndex).time);
			    	}
			    	breakIndex++;
			    	i--;
			    }else{
					newTime = RankSchedule.taskTime2(schedule, i, breakIndex);
					newEnergy = RankSchedule.newEnergy2(schedule, i);
					if (newEnergy <= 0){
						Task t = new Task("Break", 0, 1, 20, 0);
						//Task(String name, double difficulty, double enjoyment, double time, double var)
						schedule.tasks.add(i, t);
						newTime = RankSchedule.taskTime2(schedule, i, breakIndex); 
						}
					schedule.tasks.get(i).time2 = newTime;
					if (verbose){
						System.out.println(schedule.tasks.get(i).name + ": " + schedule.tasks.get(i).taskTime);
					}
			    }
				schedule.energy = newEnergy;
				if (verbose){
					System.out.println("\t" + schedule.energy);
				}
				totalTime += newTime;
				curTime += totalTime;
		}
			schedule.utility = RankSchedule.utility2(schedule);
			if (verbose){
				System.out.println("Utility: " + schedule.utility);
			}
		}
	public static void runSchedule2(Schedule s){
		runSchedule2(s, false);
	}
	public static Schedule optimizeSchedule2(Schedule s){
    	T = T(s);
    	b = b(s);
    	//return simAnneal2(s, 100);
    	ArrayList<Schedule> allSchedules = new ArrayList<Schedule>();
    	allSchedules.add(s);
    	for (int i = 0; i < 100; i++){
    		Schedule temp = randomizeTasks(s);
    		allSchedules.add(hillClimber2(temp, 100));
    	}
    	int maxdex = 0;
    	for (int i = 0; i < 100; i++){
    		if (allSchedules.get(maxdex).utility < allSchedules.get(i).utility){
    			maxdex = i;
    		}
    	}
    	return allSchedules.get(maxdex);
    }
	public static Schedule randomizeTasks(Schedule s){
		Schedule total = s.makeCopy();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < s.tasks.size(); i++){
			indices.add(i);
		}
		int temp = 0;
		for (int i = 0; i < total.tasks.size(); i++){
			temp = indices.remove((int)(Math.random()*indices.size()));
			total.tasks.set(temp, s.tasks.get(temp).makeCopy());
		}
		return total;
	}
	public static Schedule hillClimber2(Schedule s, int index){
		if (index <= 0){
			return s;
		}
		index--;
		Schedule mutant = mutate(s);
		if (mutant.utility > s.utility){
			return hillClimber2(mutant, index);
		}
		return hillClimber2(s, index);
	}
	
    public static Schedule simAnneal2(Schedule s, int temp){
    	if (temp <= 0){
    		return s;
    	}
    	Schedule mutant = mutate(s);
    	runSchedule2(mutant, false);
    	//System.out.println(mutant.energy);
    	//System.out.println();
    	mutant.utility = utility2(mutant);
    	int newTemp = temp - 1;
    	//System.out.println(s.utility);
    	if (mutant.utility > s.utility){
    		return simAnneal2(mutant, newTemp);
    	}
    	double toCompare = Math.pow(Math.E, (s.utility-mutant.utility)/(100*temp)) + 1;
    	toCompare = 1/toCompare;
    	if (Math.random() <= toCompare){ //seems about in range
    		//	System.out.println("selecting worse mutant");
    			return simAnneal2(mutant, newTemp);
    	}
    	//System.out.println("selecting better mutant" + toCompare);
    	return simAnneal2(s, newTemp);
    }
    public static double utility2(Schedule s){
        if (s.energy < 0){
        return -1;
        }
        double A = 1;
        double B = 1;
        double total = 0;
        for (int i = 0; i < s.tasks.size(); i++){
            total += A * s.tasks.get(i).enjoyment * s.tasks.get(i).time2;
        }
        total *= Math.sqrt(s.energy);
        total -= B * (s.stopTime - s.idealStop) * s.endTask.enjoyment;
        return total;
    }
	
	
	
	
	
    public static double taskTime(Schedule s, int i){
        if (s.energy == 0){
        return 0;
        }
        double k = 20;
        Task t = s.tasks.get(i);
        double total = (k*t.difficulty)/(t.enjoyment*s.energy)*(t.timeVar) + t.eTime;
        return total;
    }
    public static double newEnergy(Schedule s, int i){
		/* double k = -0.2;
	        double c = -10;
	        Task t = s.tasks.get(i);
	        return k * t.taskTime + c * t.difficulty + s.energy;*/
		Task t = s.tasks.get(i);
		//	double total = Math.pow(s.energy, t.enjoyment*t.difficulty*3);
		//	total *= t.taskTime;
		double total = Math.pow(Math.E,  (t.taskTime * t.difficulty)/(s.energy * t.enjoyment*10));
		//System.out.println(total);
		total += 4;
		total = 1/total;
		total += .8;
		//System.out.println("\t\t" + total);
		total = Math.pow(s.energy, total);
		return total;
    }
    public static double utility(Schedule s){
        if (s.energy < 0){
        return -1;
        }
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
    public static double toMinutes(double militTime){ //e.g., 1200
		return ((int)militTime/100)*60 + ((int)militTime%100);
	}

    public static Schedule optimizeSchedule(Schedule s){
    	return simAnneal(s, 100);
    }
   

    public static Schedule mutate(Schedule schedule){
    	int firstIndex = (int)(Math.random()*(schedule.tasks.size()-1));
    	Schedule copy = schedule.makeCopy();
    	Task tempTask = copy.tasks.get(firstIndex);
    	copy.tasks.set(firstIndex, copy.tasks.get(firstIndex+1));
    	copy.tasks.set(firstIndex+1, tempTask);
    	return copy;
    }
    public static Schedule simAnneal(Schedule s, int temp){
    	if (temp <= 0){
    		return s;
    	}
    	Schedule mutant = mutate(s);
    	runSchedule(mutant, false);
    	//System.out.println(mutant.energy);
    	//System.out.println();
    	mutant.utility = utility(mutant);
    	int newTemp = temp - 1;
    	if (mutant.utility > s.utility){
    		return simAnneal(mutant, newTemp);
    	}
    	if (Math.random() <= Math.pow(Math.E, (s.utility-mutant.utility)/(-1*temp))){ //seems about in range
    			return simAnneal(mutant, newTemp);
    	}
    	return simAnneal(s, newTemp);
    }

    public static void runSchedule(Schedule schedule){
    	runSchedule(schedule, false);
    }

    public static double breakEnergy(Schedule schedule, int i){
    	Break b = schedule.breaks.get(i);
    	return schedule.energy + .1 * b.time;  
    }

    public static double taskBreakEnergy(Schedule schedule, int i){
    	Task t = schedule.tasks.get(i);
    	return schedule.energy + .1 * t.taskTime;
    }
public static void runSchedule(Schedule schedule, boolean verbose){
	if (verbose){System.out.println("\n\n");}
		double newEnergy, newTime, totalTime;
		newEnergy = schedule.energy;
		double origEnergy = schedule.energy;
		totalTime = 0;
		double timeDiff = toMinutes(schedule.stopTime) - toMinutes(schedule.startTime);
		double curTime = toMinutes(schedule.startTime);
		int breakIndex = 0;
		for (int i = 0; i < schedule.tasks.size() && totalTime < timeDiff; i++){
		    if (breakIndex < schedule.breaks.size() && curTime >= toMinutes(schedule.breaks.get(breakIndex).startTime)){
		    	newEnergy = breakEnergy(schedule, breakIndex);  //arbitrary increase of energy
		    	newTime = schedule.breaks.get(breakIndex).time;
		    	if (verbose){
		    		System.out.println(schedule.breaks.get(breakIndex).name + ": " + schedule.breaks.get(breakIndex).time);
		    	}
		    	breakIndex++;
		    	i--;
		    }else{
				newTime = RankSchedule.taskTime(schedule, i);
				newEnergy = RankSchedule.newEnergy(schedule, i);
				if (newEnergy <= origEnergy * .2){
					Task t = new Task();
					t.name = "Break";
					t.taskTime = 20;
					t.difficulty = 0; //arbitrary as of now, increases energy
					t.enjoyment = 1;
					schedule.tasks.add(i, t);
					//i++;
					//newEnergy += 20;
					newTime = RankSchedule.taskTime(schedule, i);
					}
				schedule.tasks.get(i).taskTime = newTime;
				if (newEnergy <= 0){
				    newEnergy = RankSchedule.taskBreakEnergy(schedule, i);
				}
				if (verbose){
					System.out.println(schedule.tasks.get(i).name + ": " + schedule.tasks.get(i).taskTime);
				}
		    }
			schedule.energy = newEnergy;
			if (verbose){
				System.out.println("\t" + schedule.energy);
			}
			totalTime += newTime;
			curTime += totalTime;
	}
		schedule.utility = RankSchedule.utility(schedule);
		if (verbose){
			System.out.println("Utility: " + schedule.utility);
		}
	}
}
