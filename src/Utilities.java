public class Utilities{
    public static String makeMilitary(String time){
	//time is formated hh:mm AM/PM
	time = time.toUpperCase();
	//	System.out.println(time);
	String[] timeArr = time.split(" ");
	time = "";
	for (int i = 0; i < timeArr.length; i++){
	    // System.out.println("time: " + timeArr[i]);
	    time += timeArr[i];
	}
	//	System.out.println(time);
	String h = "";
	String m = "";
	String amPm = "";
	//	System.out.println(time);
	try{
	    //  System.out.println(time);
	    String[] timeStrings = time.split(":");
	    //   System.out.println(timeStrings[0] + " " +timeStrings[1]);
	    h = timeStrings[0];
	    double hour = Double.parseDouble(h);
	    m = timeStrings[1].substring(0, 2);
	    double minute = Double.parseDouble(m);
	    //   System.out.println(hour +  " " + minute);
	    amPm = timeStrings[1].substring(2);
	    //  System.out.println(timeStrings[1] + ": " + amPm);
	    //  System.out.println(minute + " >= 60?" + (minute>=60));
	    if (minute >= 60){
		return "ERROR: Time invalid";
	    }
	    // System.out.println("ok got here");
	    if (amPm.equals("AM")){
		//System.out.println("hi");
		if (hour == 12){
		    return "00" + m;
		}
		if (hour < 12){
		    //  System.out.println("Returning " + h + m);
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

    /*
     public static void main(String[] args){
	 String rdm = "";
	 String am = "AM";
	 for (int i = 0; i < 100; i++){
	     if (Math.random() < .5){
		 am = "AM";
	     }else{
		 am = "PM";
	     }
	     rdm = "" + ((int)(Math.random()*12)+1) + ":" + (int)(Math.random()*60) + am;
	     // System.out.println(makeMilitary(rdm));
	     String militTime = makeMilitary(rdm);
	     if (!militTime.equals("ERROR: Time invalid")){
		 double totalTime = Double.parseDouble(militTime.substring(militTime.length() - 2, militTime.length())) +
		     60*Double.parseDouble(militTime.substring(0, militTime.length() - 2));
		 //	 System.out.println(totalTime);
		 if (!rdm.equals( RankSchedule.timeString(totalTime))){
			 System.out.println("didnt work " + rdm);
		     }
	     }else{
		 System.out.println("ERROR WITH TIME STRING!! " + rdm);
	     }
	 }
	 }*/
}
