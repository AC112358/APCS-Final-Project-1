public class Utilities{
    public static String makeMilitary(String time){
	//time is formated hh:mm AM/PM
	time = time.toUpperCase();
	time = String.join(time.split(" "));
	String h = "";
	String m = "";
	String amPm = "";
	try{
	    String timeStrings = time.split(":");
	    h = timeStrings[0];
	    double hour = Double.parseDouble(h);
	    m = timeStrings[0].substring(0, 2);
	    double minute = Double.parseDouble(m);
	    amPm = timeStrings[0].substring(2);
	    if (amPm.equals("AM")){
		if (hour == 12){
		    return "00" + m;
		}
		return h + m;
	    }
	    else if(amPm.equals("PM")){
		if (hour == 12){
		    return h + m;
		}
		return "" + (hour+12) + m;
	    }
	    else{
		return "ERROR: Time invalid";
	    }
	    
	}catch(Exception e){
	    return "ERROR: Time invalid";
	}
    }
    public static void main(String[] args){
	System.out.println(makeMilitary("12: 33 PM"));
    }
}
