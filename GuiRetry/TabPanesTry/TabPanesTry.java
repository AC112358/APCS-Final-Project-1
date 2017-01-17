import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class TabPanesTry extends JPanel 
{

    JSplitPane p = new JSplitPane();
    JTabbedPane spi = new JTabbedPane();

    
    JPanel tp = new JPanel();
    String[] tColNames = new String[] {"Task (Subjectname Taskname)",
				       "Estimated Time to Complete (min)",
				       "Time Variability (min)"};
    String[][] tData = new String[6][3];
    DefaultTableModel tTable = new DefaultTableModel (tData, tColNames);
    JTable tt = new JTable(tTable);

    //===========
    JPanel bp = new JPanel();
    String[] bColNames = new String[] {"Break name",
				       "Break Time (Range)",
				       "Time To Complete (min)"};
    String[][] bData = new String[6][3];
    DefaultTableModel bTable = new DefaultTableModel (bData, bColNames);
    JTable bt = new JTable (bTable);
    //============
    JPanel sp = new JPanel();
    String[] sColNames = new String[] {"Subject Name",
				       "Difficulty (/100)",
				       "Enjoyment (/100)"};
    String[][] sData = new String[6][3];
    DefaultTableModel sTable = new DefaultTableModel (sData, sColNames);
    JTable st = new JTable(sTable);

    //labels
    JLabel sl = new JLabel(
			   "<html>HOW-TO-USE<br><br>*Press Update to commit changes<br><br>*Press Add to add a subject(entire row)<br><br>*Difficulty and Enjoyment are out of 100<br><br><html>"
			   );
    JLabel tl = new JLabel(
			   "<html>HOW-TO-USE<br><br><br>*Enter Tasks, Estimated Time to Complete, and Time Variability<br><br>*Time Variability (TV)means the range around the estimated time <br> Ex: 30 minutes TV and 45 minutes Estimated Time to Complete<br>means task would take anywhere from 15-75 minutes <br><br>*Press Update to commit changes<br><br>*Press Add to add a task(entire row)<br><br>*Be as Specific as Possible<br><br><html>"
			   );
    JLabel bl = new JLabel(
			   "<html>HOW-TO-USE<br><br><br>*Enter Breaks, Break Time(Range), and Time to<br> complete. Be sure to Update<br><br>*BreakTime(Range) means around what time is this<br>break going to Happen. <br>Ex: Dinner will be 6:00pm to 8:00pm<br><br>*Ideal Stop Time and Time Interval refers to your entire<br>schuedule, not just breaks.<br><br> *Please Specify in Military Time. <br><br>*Press Update to commit changes<br><br>*Press Add to add a break (entire row)<br><br>*Be as Specific as Possible<br><br><html>"
			   );
    

    

    //buttons
    JButton addt = new JButton("ADD TASK");
    JButton addb = new JButton("ADD BREAK");

    JButton updatet = new JButton("UPDATE TASK");
    JButton updateb = new JButton("UPDATE BREAK");

    JButton adds = new JButton("ADD SUBJECT (ROW)");
    JButton updates = new JButton("UPDATE SURVEY");

    //
    Writer output;

    public TabPanesTry() {

		//set Table modes
	/*	st.setSelectionMode(SINGLE_SELECTION);
	bt.setSelectionMode(SINGLE_SELECTION);
	tt.setSelectionMode(SINGLE_SELECTION); */

	

        bp.setBorder(BorderFactory.createTitledBorder("Break List"));
	BoxLayout bLayout = new BoxLayout(bp, BoxLayout.Y_AXIS);
	bp.setLayout(bLayout);
	
        tp.setBorder(BorderFactory.createTitledBorder("Task List"));
	BoxLayout tLayout = new BoxLayout(tp, BoxLayout.Y_AXIS);
	tp.setLayout(tLayout);

        sp.setBorder(BorderFactory.createTitledBorder("Survey Form"));
	
	bp.add(new JScrollPane (bt));
	bp.add(bl);
	bp.add(addb);
	bp.add(updateb);
	
	tp.add(new JScrollPane (tt));
	tp.add(tl);
	tp.add(addt);
	tp.add(updatet);

	sp.add(new JScrollPane (st));
	sp.add(sl);
	sp.add(adds);
	sp.add(updates);


		//adding actionlisteners to buttons
	addb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		   
		    bTable.addRow(new Object[] {});
		}
	    } );
	addt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		    
		    tTable.addRow(new Object[] {});
		}
	    } );
	adds.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		    
		    sTable.addRow(new Object[] {});
		}
	    } );

	updateb.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {


            PrintWriter os = new PrintWriter();

            for (int row = 0; row < bt.getRowCount(); row++) {
		    os.println("'" + bt.getValue(row,0) + "' "
			       + bt.getValue(row, 1) + " "
			       + bt.getValue(row, 2));
                    os.println("");
            }
            os.close();

	    }
		    });
	         
	updatet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {


            PrintWriter os = new PrintWriter(file);

            for (int row = 0; row < tt.getRowCount(); row++) {
		    os.println("'" + tt.getValue(row,0) + "' "
			       + tt.getValue(row, 1) + " "
			       + tt.getValue(row, 2));
                    os.println("");
            }
            os.close();

        } 
		    });
	updates.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {

		for (int row = 0; row < st.getRowCount(); row++){
		    for (int col = 0; col < st.getColCount(); col++){
			String[][] temp = sData[row][col];
		    os.println("'" + st.getValue(row,0) + "' "
			       + st.getValue(row, 1) + " "
			       + st.getValue(row, 2));
                    os.println("");

        } 
	    }); 
	

	//survey pane settings
	sp.setPreferredSize(new Dimension(1000, 700));

	JSplitPane temp = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT);
	temp.add(tp);
	temp.add(bp);
	p = temp;
	//splitpane settings
	p.setOneTouchExpandable(true);
        p.setDividerLocation(500);
	p.setPreferredSize(new Dimension(1000, 700));
	

	//Adding tabs
	spi.add("Survey", sp);
	spi.add("Planner", p);
	add(spi, BorderLayout.CENTER);
	//JFrame.setContentPane(JTabbedPane spi);

    }

    //gets
    public JTabbedPane getTabbedPane()
    {
	return spi;
    }

    public void setTabbedPane (JTabbedPane tab)
    {
	tab = getTabbedPane();
    }

	/* if(e.getSource() == updateb)
	    {
		
	    }
	if(e.getSource() == ubdatet)
	    {
		
	    }
	if(e.getSource() == ubdates)
	    {
		
	    } 
    private void saveResultsActionPerformed(ActionEvent evt) {


    int returnVal = fileChooser.showSaveDialog(NewJFrame.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            File file = fileChooser.getSelectedFile();
            PrintWriter os = new PrintWriter(file);

            for (int row = 0; row < tt.getRowCount(); row++) {
		    os.println("'" + tt.getValue(row,0) + "' "
			       + tt.getValue(row, 1) + " "
			       + tt.getValue(row, 2));
                    os.println("");
            }
            os.close();
            System.out.println("Done!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    } */

    output = new BufferedWriter(new FileWriter(scheduleTest.txt, true));
    output.append;
    
	

    public static void createAndShowGui (){

	JFrame f = new JFrame("Schedule Planner Doc");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setSize(1500,800);
	f.add(new TabPanesTry(), BorderLayout.CENTER);

	//Display
	f.pack();
	f.setLocationRelativeTo(null);
	f.setVisible(true);
    }

    public static void main (String[] args){
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGui();
		}
	    });
    }
    
}
	
