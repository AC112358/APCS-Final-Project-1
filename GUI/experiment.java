import javax.swing.*;
import java.awt.*;

//will change up bits of codes from online to expriement and possible add to survey.java

public class experiment extends JFrame{
    /*
    private static final int FrameWid = 200;
    private static final int FrameLen = 200;

    public void example(Graphics g){
	Dimension d = this.getPreferredSize();
	int fontSize = 50;
    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
     
    g.setColor(Color.red);
    
    g.drawString("SURVEY", 10, 20);
  }
    /*    
    public void actionPerformed(ActionEvent e) { 
    ...
    }*/
    /*
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.getContentPane().add(new experiment());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200,200);
    frame.setVisible(true);
  }
*/
    //still need to figure out what these do
    public experiment()
    {
        // sets layout of square
        setLayout(new GridLayout(2,3));

        //set JLabel properties such as border, text, font, etc.
        Border line =  new LineBorder(Color.yellow, 4);
        Font TNR = new Font("Times New Roman", Font.BOLD, 20);
        JLabel black = new JLabel("Black", JLabel.CENTER);
        JLabel blue = new JLabel("Blue", JLabel.CENTER);
        JLabel cyan = new JLabel("Cyan", JLabel.CENTER);
        JLabel green = new JLabel("Green", JLabel.CENTER);
        JLabel magenta = new JLabel("Magenta", JLabel.CENTER);
        JLabel orange = new JLabel("Orange", JLabel.CENTER);

        // add set properties and add panel to JFrame
        black.setOpaque(true);
        black.setForeground(Color.black);
        black.setBackground(Color.white);
        black.setBorder(line);
        black.setFont(TNR);
	//tool tips help allows how to use a certain component to show up when cursor is placed over that component
        black.setToolTipText("This is Black");
        add(black);

        // add set properties and add panel to JFrame
        blue.setOpaque(true);
        blue.setForeground(Color.blue);
        blue.setBackground(Color.white);
        blue.setBorder(line);
        blue.setFont(TNR);
        blue.setToolTipText("This is Blue");
        add(blue);

        // add set properties and add panel to JFrame
        cyan.setOpaque(true);
        cyan.setForeground(Color.cyan);
        cyan.setBackground(Color.white);
        cyan.setBorder(line);
        cyan.setFont(TNR);
        cyan.setToolTipText("This is Cyan");
        add(cyan);

        // add set properties and add panel to JFrame
        green.setOpaque(true);
        green.setForeground(Color.green);
        green.setBackground(Color.white);
        green.setBorder(line);
        green.setFont(TNR);
        green.setToolTipText("This is Green");
        add(green);

        // add set properties and add panel to JFrame
        magenta.setOpaque(true);
        magenta.setForeground(Color.magenta);
        magenta.setBackground(Color.white);
        magenta.setBorder(line);
        magenta.setFont(TNR);
        magenta.setToolTipText("This is Magenta");
        add(magenta);

        // add set properties and add panel to JFrame
        orange.setOpaque(true);
        orange.setForeground(Color.orange);
        orange.setBackground(Color.white);
        orange.setBorder(line);
        orange.setFont(TNR);
        orange.setToolTipText("This is Orange");
        add(orange);





    }


    public static void main(String[] args) 
    {
        SixLabels frame = new SixLabels(); // creates frame
        frame.setTitle("Chapter 12 Exercise 12.8"); // sets name on title bar
        frame.setSize(600, 400); // sets size of GUI frame
        frame.setLocationRelativeTo(null); // Centers GUI frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop/Close program on exit 
        frame.setVisible(true); // Shows the frame, must be true

    }

}
