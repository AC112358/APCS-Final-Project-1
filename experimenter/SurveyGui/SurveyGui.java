import java.awt.*;
import javax.swing.*;
import java.util.*;

public class SurveyGui extends JFrame{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private JScrollPane jsp;
    public JTable surveyTable;

    public String[][] data = new String[6][3];
    public String[] colNames = new String[3];
	colNames[0] = "Subject_Name";
	colNames[1] = "Difficulty(/100)";
	colNames[2] = "Enjoyment(/100)";    

    surveyTable =  new DefaultTableModel(data,colNames));
    surveyTable.setGridColor(BLACK);

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
	JLabel survey;
        pane.setLayout(new GridBagLayout());
	GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.CENTER;
        }

        surveyL = new JLabel("Survey");
        if (shouldWeightX) {
            gbc.weightx = 1.0;
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
	gridbag.setConstraints(surveyL, gbc);
        pane.add(surveyL, gbc);

        jsp = new JScrollPane(surveyTable);
        gbc.ipady = 40;      //make this component tall
        gbc.weightx = 0.0;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pane.add(jsp, gbc);
	    

        button = new JButton("ADD");
        gbc.ipady = 0;       //reset to default
        gbc.weighty = 1.0;   //request any extra vertical space
        gbc.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc.insets = new Insets(10,0,0,0);  //top padding
        gbc.gridx = 1;       //aligned with button 2
        gbc.gridwidth = 2;   //2 columns wide
        gbc.gridy = 2;       //third row
        pane.add(button, gbc);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        //Create and Set up the window. Then Allow Closing
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
	frame.add(jsp);

        //Display the window.
	frame.setTitle("Survey doc");
	frame.setSize(600,200);
	frame.setLocationRelativeto(null);//center screen
        //frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
	//I searched this up and found that by funning on multiple threads at once, the speed of running it can improve. I'm not 100% sure about this line, but I hope this line can minimize the time it would take to set up a whole bunch of stuff
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

         //creating and showing this application's GUI.
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
