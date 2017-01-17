import javax.swing.*;
import java.awt.*;

public class TabPanesTry extends JPanel
{

    JSplitPane p = new JSplitPane();
    JTabbedPane spi = new JTabbedPane();

    
    JPanel tp = new JPanel();
    JScrollPane t = new JScrollPane();
    String[] tColNames = new String[] {"Task (Subjectname Taskname)",
				       "Estimated Time to Complete (min)",
				       "Time Variability (min)"};
    String[][] tData = new String[6][3];
    JTable tt = new JTable(tData, tColNames);

    
    JPanel bp = new JPanel();
    String[] bColNames = new String[] {"Break name",
				       "Break Time (Range)",
				       "Time To Complete (min)"};
    String[][] bData = new String[6][3];
    JTable bt = new JTable(bData, bColNames);

    

    //buttons
    JButton addt = new JButton("ADD TASK");
    JButton addb = new JButton("ADD BREAK");

    JButton updatet = new JButton("UPDATE TASK");
    JButton updateb = new JButton("UPDATE BREAK");

    public TabPanesTry() {

        bp.setBorder(BorderFactory.createTitledBorder("Break List"));
	BoxLayout bLayout = new BoxLayout(bp, BoxLayout.Y_AXIS);
	bp.setLayout(bLayout);
	
        tp.setBorder(BorderFactory.createTitledBorder("Task List"));
	BoxLayout tLayout = new BoxLayout(tp, BoxLayout.Y_AXIS);
	tp.setLayout(tLayout);

	
	bp.add(new JScrollPane (bt));
	bp.add(addb);
	bp.add(updateb);
	
	tp.add(new JScrollPane (tt));
	tp.add(addt);
	tp.add(updatet);

	JSplitPane temp = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT);
	temp.add(tp);
	temp.add(bp);
	p = temp;
	//splitpane settings
	p.setOneTouchExpandable(true);
        p.setDividerLocation(500);
	p.setPreferredSize(new Dimension(1000, 400));
	

	//Adding Components
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
	
