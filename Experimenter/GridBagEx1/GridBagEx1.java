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

//===========================

/*
public class Survey extends JFrame{

    private JFrame f;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel survey;
    private JScrollPane sp;
    
    public JTable sTable;
    private String[] colNames;
    public Object[][] data;
    
    private JButton addB;
    private JLabel addL;
    private JButton update;

    public Survey()
        {
	    gui();
        }
    //===================================

    public void gui()
    {
	//frame setup
	f = new JFrame("Survey");
	f.setVisible(true);
	f.setSize(800,400); //800 width by 400 height
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//Survey Label Panel
	p1 = new JPanel();
	p1.setBackground(Color.YELLOW);
	
	addL = new JLabel("SURVEY");

	//Button Panel
	//ADD
	p2 = new JPanel();
	p2.setBackground(Color.BLUE);
	
	addB = new JButton("ADD");
	//UPDATE
	p3 = new JPanel();
	p3.setBackground(Color.WHITE);

	update = new JButton();

	//survey table
	survey = new JPanel();
	/*
	//layout of survey table (rows, cols, h_gap, y_gap)
	panel.setLayout(new GridLayouts(7, 3, 0, 0));
	*/
    //--------------------------------------------
	//originally forgot to put this in the constructor used method to initialize
     //array parts for sTable
/*    colNames = new String[] {"Subject Name", "Difficulty(/100)", "Enjoyment(/100)"};
    data = new String[6][3]; 
    //follows the JTable Constructor (Object[][] rowData, Object[] coldata)
    sTable = new JTable (data, colNames);
    //add JTable to a container
    sp = new JScrollPane(sTable);
  
   //---------------------------------------------------------------------			

	//gbc will be used in formatting out table for survey
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(10,10,10,10);
	
	//adding components to Panel
	p1.add(addL);
	p2.add(addB);
	p3.add(update);
	survey.add(sp, BorderLayout.CENTER);


	//adding panels to frame
	f.add(p1,BorderLayout.NORTH );
	f.add(p2,BorderLayout.SOUTH );
	f.add(p3,BorderLayout.SOUTH );
        f.getContentPane();
	f.add(survey);
	f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
	new Survey();

    }
}

/*
    //instead of the mess below, I could just use JTable I realized
    
    /*
    //survey components
    JLabel COL1 = new JLabel ("Subject Name");
    JLabel COL2 = new JLabel ("Difficulty(/100)");
    JLabel COL3 = new JLabel ("Enjoyment(/100)");
    //survey subjects
    String s1 = null;
    String s2 = null;
    String s3 = null;
    String s4 = null;
    String s5 = null;
    String s6 = null;
    String s7 = null;
    JTextField S1 = new JTextField(s1,30);
    JTextField S2 = new JTextField(s2,30);
    JTextField S3 = new JTextField(s3,30);
    JTextField S4 = new JTextField(s4,30);
    JTextField S5 = new JTextField(s5,30);
    JTextField S6 = new JTextField(s6,30);
    //survey difficulties
    String d1 = null;
    String d2 = null;
    String d3 = null;
    String d4 = null;
    String d5 = null;
    String d6 = null;
    String d7 = null;
    JTextField D1 = new JTextField(d1,30);
    JTextField D2 = new JTextField(d2,30);
    JTextField D3 = new JTextField(d3,30);
    JTextField D4 = new JTextField(d4,30);
    JTextField D5 = new JTextField(d5,30);
    JTextField D6 = new JTextField(d6,30);
    //survey enjoyments
    String e1 = null;
    String e2 = null;
    String e3 = null;
    String e4 = null;
    String e5 = null;
    String e6 = null;
    String e7 = null;
    JTextField E1 = new JTextField(e1,30);
    JTextField E2 = new JTextField(e2,30);
    JTextField E3 = new JTextField(e3,30);
    JTextField E4 = new JTextField(e4,30);
    JTextField E5 = new JTextField(e5,30);
    JTextField E6 = new JTextField(e6,30);   
    */
