public class Utilities{
    public static String makeMilitary(String time){
	//time is formated hh:mm AM/PM
	time = time.toUpperCase();
	String[] timeArr = time.split(" ");
	time = "";
	for (int i = 0; i < timeArr.length; i++){
	    time += timeArr[i];
	}
	String h = "";
	String m = "";
	String amPm = "";
	try{
	    //  System.out.println(time);
	    String[] timeStrings = time.split(":");
	    //   System.out.println(timeStrings[0] + " " +timeStrings[1]);
	    h = timeStrings[0];
	    double hour = Double.parseDouble(h);
	    m = timeStrings[1].substring(0, 2);
	    double minute = Double.parseDouble(m);
	    // System.out.println(hour +  " " + minute);
	    amPm = timeStrings[1].substring(2);
	    if (minute >= 60){
		return "ERROR: Time invalid";
	    }
	    if (amPm.equals("AM")){
		if (hour == 12){
		    return "00" + m;
		}
		if (hour < 12){
		    return h + m;
		}
		return "ERROR: Time invalid";
	    }
	    else if(amPm.equals("PM")){
		if (hour == 12){
		    return h + m;
		}
		if (hour < 12){
		    return "" + (int)(hour+12) + m;
		}
		return "ERROR: Time invalid";
	    }
	    else{
		return "ERROR: Time invalid";
	    }
	    
	}catch(Exception e){
	    return "ERROR: Time invalid";
	}
    }
     public static void main(String[] args){
	System.out.println(makeMilitary(args[0]));
	String militTime = makeMilitary(args[0]);
	if (!militTime.equals("ERROR: Time invalid")){
	double totalTime = 60*Double.parseDouble(militTime.substring(militTime.length() - 2, militTime.length())) +
	    Double.parseDouble(militTime.substring(0, militTime.length() - 2));
    System.out.println(RankSchedule.timeString(totalTime));
     }
	}
}
