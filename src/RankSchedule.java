public class RankSchedule {
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
	double total = Math.pow(Math.E, t.enjoyment * t.taskTime * t.difficulty * s.energy);
	total += .5;
	return 1/total;
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
	return schedule.energy + 10*b.time;  
    }

    public static double taskBreakEnergy(Schedule schedule, int i){
	Task t = schedule.tasks.get(i);
	return schedule.energy + 10*t.taskTime;
    }
public static void runSchedule(Schedule schedule, boolean verbose){
	if (verbose){System.out.println("\n\n");}
		double newEnergy, newTime, totalTime;
		newEnergy = schedule.energy;
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
				if (newEnergy <= 0){
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
