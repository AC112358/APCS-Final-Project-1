 import java.awt.*;
 import java.util.*;
 import javax.swing.*;
 import java.applet.Applet;
 public class Planner extends Applet {

    private JPanel TaskList;
    private JScrollPane sp;
    public JTable tTable;
    private String[] colNames;
    public String[][] data;

     //Making Parts
     protected void makebutton(String name,
                               GridBagLayout gb,
                               GridBagConstraints c) {
         JButton button = new JButton(name);
         gb.setConstraints(button, c);
         add(button);
     }

     protected void makeTextField(String text, int cols,
				  GridBagLayout gb,
				  GridBagConstraints c) {
	 JTextField textField = new JTextField(text,cols);
	 gb.setConstraints(textField, c);
         add(textField);
     }

     protected void makeLabel(String text,
				  GridBagLayout gb,
				  GridBagConstraints c) {
	 JLabel label = new JLabel(text);
	 gb.setConstraints(label, c);
         add(label);
     }
    
     protected void makeScrollPane(String[][] info,
			      String[] colNames,
			      GridBagLayout gb,
			      GridBagConstraints c) {
	 JTable sTable = new JTable(info, colNames);
	 JScrollPane sp = new JScrollPane(sTable);
	 gb.setConstraints(sp, c);
         add(sp);
     }
     //================================================================    
     public void initTaskList() {
         GridBagLayout gb = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();

    //array parts for sTable
    colNames = new String[] {"Task (Subjectname Taskname",
			     "Estimated Time to Complete (min)",
			     "Time Variability (min)"};
    data = new String[6][3]; 
	 
         setFont(new Font("Helvetica", Font.PLAIN, 28));
         setLayout(gb);
         c.fill = GridBagConstraints.BOTH;
	   c.gridwidth = GridBagConstraints.REMAINDER;
	   c.gridheight = 3;
	 makeLabel("TASK LIST", gb, c);
	 //	   c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row	 
         makeScrollPane(data, colNames, gb, c);     
 	   c.gridwidth = 1;	   	   //reset to the default
 	   c.gridheight = 2;

         makeLabel(
		   "<html>HOW-TO-USE<br><br><br>*Enter Tasks, Estimated Time to Complete, and Time<br>Variability. Be Sure To Update.<br><br> *Time Variability (TV) means the range around the<br> estimated time.<br>==Ex: 30 min TV and 45 min Estimated Time to Complete<br> means time would take anywhere from <br> 15-75 min. <br><br>*Press Update to commit changes<br><br>*Press Add to add a subject(entire row)<br><br>*Be as Specific as Possible<br><br><html>",
gb,
c);
         c.weighty = 0.0;		   //reset to the default
 	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 	   c.gridheight = 1;		   //reset to the default
         makebutton("ADD TASK", gb, c);
         makebutton("UPDATE TASK", gb, c);
         setSize(2000,800);
     
     //==============================================
     //Break Components and Overall Components
     
    //array parts for sTable
    colNames = new String[] {"Break name",
			     "Break Time (Range)",
			     "Time To Complete (min)"};
    data = new String[4][3]; 
	 
         setFont(new Font("Helvetica", Font.PLAIN, 28));
         setLayout(gb);
         c.fill = GridBagConstraints.BOTH;
	   c.gridwidth = GridBagConstraints.REMAINDER;
	   c.gridheight = 3;
	 makeLabel("BREAK LIST", gb, c);
	 //	   c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row	 
         makeScrollPane(data, colNames, gb, c);     
 	   c.gridwidth = 1;	   	   //reset to the default
 	   c.gridheight = 2;

         makeLabel(
		   "<html>HOW-TO-USE<br><br><br>*Enter Breaks, Break Time(Range), and Time to<br> complete. Be sure to Update<br><br>*BreakTime(Range) means around what time is this<br>break going to Happen. <br>Ex: Dinner will be 6:00pm to 8:00pm<br><br>*Ideal Stop Time and Time Interval refers to your entire<br>schuedule, not just breaks.<br><br> *Please Specify in Military Time. <br><br>*Press Update to commit changes<br><br>*Press Add to add a subject(entire row)<br><br>*Be as Specific as Possible<br><br><html>",
gb,
c);
         c.weighty = 0.0;		   //reset to the default
 	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 	   c.gridheight = 1;		   //reset to the default
         makebutton("ADD BREAK", gb, c);
         makebutton("UPDATE BREAK", gb, c);
         setSize(2000,800);
     }


     
     public static void main(String args[]) {
 	   Frame f = new Frame("PlannerDoc-TaskList");
 	   SurveyGui ex1 = new TaskList();
 	   ex1.init();
 	   f.add("Center", ex1);
 	   f.pack();
 	   f.setSize(f.getPreferredSize());
 	   f.show();
     }
 }
