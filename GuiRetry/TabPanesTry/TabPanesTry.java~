import javax.swing.*;
import java.awt.*;

public class TabPanes extends JPanel
{
    //JFrame window = new JFrame();
    JPanel window = new JPanel();
    JTabbedPane spi = new JTabbedPane();
    JScrollPane t = new JScrollPane();
    JScrollPane b = new JScrollPane();
    JSplitPane p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				  t,b);

    public TabPanes() {
	//splitpane settings
	p.setOneTouchExpandable(true);
        p.setDividerLocation(750);
	p.setPreferredSize(new Dimension(1500, 800));

	//settings for components of p
	
	JLabel sp = new JLabel();
	sp.setText("Planner");
	JLabel st = new JLabel();
	sp.setText("Task Lists");
	JLabel sb = new JLabel();
	sp.setText("Break Lists");

	//Adding Components
	t.add(st);
	b.add(sb);
	//what didn't work
	window.add(p, BorderLayout.CENTER);
	//JFrame.setContentPane(JTabbedPane spi);

    }

    //gets
    public JTabbedPane getTabbedPane()
    {
	return spi;
    }
    
	

    public static void createAndShowGui (){

	JFrame f = new JFrame("Schedule Planner Doc");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setSize(1600,900);
	TabPanes tp = new TabPanes();
	f.getContentPane().add(tp.getTabbedPane());

	//Display
	   
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int xcor = (int) ((d.getWidth() - f.getWidth()) / 2);
	int ycor = (int) ((d.getHeight() - f.getHeight()) / 2);
	f.setLocation(xcor, ycor);

	f.pack();
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
	
