 import java.awt.*;
 import java.util.*;
 import javax.swing.*;
 import java.applet.Applet;
 public class GridBagEx1 extends Applet {

    private JPanel survey;
    private JScrollPane sp;
    public JTable sTable;
    private String[] colNames;
    public String[][] data;
     
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
     
     public void init() {
         GridBagLayout gb = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();

    //array parts for sTable
    colNames = new String[] {"Subject Name", "Difficulty(/100)", "Enjoyment(/100)"};
    data = new String[6][3]; 
	 
         setFont(new Font("Helvetica", Font.PLAIN, 28));
         setLayout(gb);
         c.fill = GridBagConstraints.BOTH;
	   c.gridwidth = GridBagConstraints.REMAINDER;
	   c.gridheight = 3;
	 makeLabel("SURVEY", gb, c);
	 //	   c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row	 
         makeScrollPane(data, colNames, gb, c);     
 	   c.gridwidth = 1;	   	   //reset to the default
 	   c.gridheight = 2;

         makeLabel(
		   "<html>HOW-TO-USE<br><br><br>*Enter Name and Date<br><br>*Press Update to<br>commit changes<br><br>*Press Add to add a<br>subject(entire row)<br><br><br><html>",
gb,
c);
         c.weighty = 0.0;		   //reset to the default
 	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
 	   c.gridheight = 1;		   //reset to the default
         makebutton("ADD", gb, c);
         makebutton("UPDATE", gb, c);
         setSize(2000,800);
     }


     
     public static void main(String args[]) {
 	   Frame f = new Frame("GridBag Layout Example");
 	   GridBagEx1 ex1 = new GridBagEx1();
 	   ex1.init();
 	   f.add("Center", ex1);
 	   f.pack();
 	   f.setSize(f.getPreferredSize());
 	   f.show();
     }
 }
