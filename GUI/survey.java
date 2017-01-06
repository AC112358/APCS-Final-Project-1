import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class survey extends JFrame{

    public void main (String[] args){
	JFrame surveyFrame = new JFrame ("Survey");
	//frame is closeable
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	//for just about all swing, I will be using GridBagLayout
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	//Survey Heading
	JLabel Survey = new JLabel("SURVEY");
	Survey.Center;
	c.fill = GridBagConstraints.HORIZONTAL;
	//padding around the word SURVEY
	c.ipady = 10;
	//space between columns (we make 0 to make it span across 3 columns essentially)
	c.weightx = 0;

	c.gridx = 0;
	//just one column
	c.gridy = 1;
	pane.add(Survey, c);


	//first subpanel consist of 
	//v1: Finite Rows (10 rows + 1 header, 3 columns)
	setLayout(new GridLayout(11,3,0,0));
	//table header
	public static final CENTER;
	JLabel SubjectName = new JLabel("Subject name");
	SubjectName.CENTER;
	JLabel Difficulty = new JLabel("Difficulty(/100)");
	Difficulty.CENTER;
	JLabel Enjoyment = new JLabel("Enjoyment(/100)");
	Enjoyment.CENTER;
	//
	JTextField Subject1 = new JTextField("");
	JTextField Subject2 = new JTextField("");
	JTextField Subject3 = new JTextField("");
	JTextField Subject4 = new JTextField("");
	JTextField Subject5 = new JTextField("");
	JTextField Activity6 = new JTextField("");
	JTextField Activity7 = new JTextField("");
	JTextField Activity8 = new JTextField("");
	JTextField Activity9 = new JTextField("");
	JTextField Activity10 = new JTextField("");
	    //There were some git problems so I saved my previous and my edited version, will delete excess later	
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class survey extends JFrame{

    public void main (String[] args){
	JFrame surveyFrame = new JFrame ("Survey");
	//frame is closeable
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

	//for just about all swing, I will be using GridBagLayout
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	//Survey Heading
	JLabel Survey = new JLabel("SURVEY");
	Survey.Center;
	c.fill = GridBagConstraints.HORIZONTAL;
	//padding around the word SURVEY
	c.ipady = 10;
	//space between columns (we make 0 to make it span across 3 columns essentially)
	c.weightx = 0;

	c.gridx = 0;
	//just one column
	c.gridy = 1;
	pane.add(Survey, c);


	//first subpanel consist of 
	//v1: Finite Rows (10 rows + 1 header, 3 columns)
	setLayout(new GridLayout(11,3,0,0));
	//table header
	public static final CENTER;
	JLabel SubjectName = new JLabel("Subject name");
	SubjectName.CENTER;
	JLabel Difficulty = new JLabel("Difficulty(/100)");
	Difficulty.CENTER;
	JLabel Enjoyment = new JLabel("Enjoyment(/100)");
	Enjoyment.CENTER;
	//
	JTextField Subject1 = new JTextField("");
	JTextField Subject2 = new JTextField("");
	JTextField Subject3 = new JTextField("");
	JTextField Subject4 = new JTextField("");
	JTextField Subject5 = new JTextField("");
	JTextField Activity6 = new JTextField("");
	JTextField Activity7 = new JTextField("");
	JTextField Activity8 = new JTextField("");
	JTextField Activity9 = new JTextField("");
	JTextField Activity10 = new JTextField("");
    }
}
