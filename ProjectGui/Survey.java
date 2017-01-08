import javax.swing.*;
import java.awt.*;


public class Survey {

    private JFrame f;
    private JPanel p1;
    private JPanel p2;
    private JPanel survey;
    private JButton addB;
    private JLabel addL;

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
	

    public Survey()
    {
	gui();
    }

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
	p2 = new JPanel();
	p2.setBackground(Color.BLUE);
	
	addB = new JButton("ADD");

	//survey table
	survey = new JPanel();
	//layout of survey table (rows, cols, h_gap, y_gap)
	panel.setLayout(new GridLayouts(7, 3, 0, 0));
			

	//gbc will be used in formatting out table for survey
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(10,10,10,10);
	
	//adding components to Panel
	p1.add(addL);
	p2.add(addB);


	//adding panels to frame
	f.add(p1,BorderLayout.NORTH );
	f.add(p2,BorderLayout.SOUTH );
    }

    public static void main(String[] args)
    {
	new Survey();

    }
}
