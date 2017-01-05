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
        double k = -0.2;
        double c = -10;
        Task t = s.tasks.get(i);
        return k * t.taskTime + c * t.difficulty + s.energy;
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

    public static void optimizeSchedule(Schedule s){
	simAnneal(s, 100);
    }

    public static void simAnneal(Schedule s, int temp){
	
    }

public static void runSchedule(Schedule schedule){
		System.out.println("\n\n");
		double newEnergy, newTime, totalTime;
		totalTime = 0;
		double timeDiff = toMinutes(schedule.stopTime) - toMinutes(schedule.startTime);
		double curTime = toMinutes(schedule.startTime);
		int breakIndex = 0;
		for (int i = 0; i < schedule.tasks.size() && totalTime < timeDiff; i++){
		    if (breakIndex < s.breaks.size() && curTime >= s.breaks.get(breakIndex).startTime){
	    newEnergy = newEnergy * 1.1;  //arbitrary increase of energy
	    newTime = s.breaks.get(breakIndex).time;
	    breakIndex++;
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
			}

			schedule.tasks.get(i).taskTime = newTime;
	}
			schedule.energy = newEnergy;
			System.out.println(schedule.tasks.get(i).name + ": " + schedule.tasks.get(i).taskTime);
			System.out.println("\t" + schedule.energy);
			totalTime += newTime;
			curTime += totalTime;
	}
		schedule.utility = RankSchedule.utility(schedule);
		System.out.println("Utility: " + schedule.utility);
	}
}
