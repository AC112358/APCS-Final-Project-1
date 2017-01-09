import java.awt.*;
import javax.swing.*;

public class GridBagLayoutDemo {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

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

        survey = new JLabel("Survey");
        if (shouldWeightX) {
            gbc.weightx = 1.0;
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
	gridbag.setConstraints(survey, gbc);
        pane.add(survey, gbc);

        button = new JButton("Long-Named Button 4");
        gbc.ipady = 40;      //make this component tall
        gbc.weightx = 0.0;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pane.add(button, gbc);

	Table surveytab = new Table();

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

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
	frame.setSize(600,200);
        frame.pack();
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
