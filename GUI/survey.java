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

    public survey(){
	//v1: Finite Rows (10 rows + 1 header, 3 columns)
	setLayout(new GridLayout(11,3,0,0));
	//table header
	JLabel SubjectName = new JLabel("Subject name");
	JLabel Difficulty = new JLabel("Difficulty(/100)");
	JLabel Enjoyment = new JLabel("Enjoyment(/100)");
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
	
