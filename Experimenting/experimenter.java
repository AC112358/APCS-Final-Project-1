import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class TVSchedule
{
    private static final int GAP = 5;
    private static TVSchedule tvSchedule;

    private void createAndDisplayGUI()
    {
        JFrame frame = new JFrame("HOTEL TV SCHEDULE");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        //centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        centerPanel.setLayout(new GridLayout(0, 4, 5, 5));
        centerPanel.add(createChannelOnePanel());
        centerPanel.add(createChannelTwoPanel());
        centerPanel.add(createListPanel());
        centerPanel.add(createInformationPanel());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(Color.RED.darker());
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JButton exitButton = new JButton("EXIT");
        bottomPanel.add(exitButton);

        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createChannelOnePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        GridBagConstraints gbc = new GridBagConstraints();

        String[] columnNames = {
                                    "Time",
                                    "Title"
                               }; 
        Object[][] data = {
                            {"01:00","Cowboy and Alchemist."}
                          };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };                      

        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1)
                        , "Channel 1"
                        , TitledBorder.CENTER
                        , TitledBorder.DEFAULT_POSITION));      
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);     

        JButton removeButton = new JButton("Remove Selected");
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(removeButton, gbc);

        return panel;
    }

    private JPanel createChannelTwoPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        GridBagConstraints gbc = new GridBagConstraints();

        String[] columnNames = {
                                    "Time",
                                    "Title"
                               }; 
        Object[][] data = {
                            {"02:00","Grey's Anatomy"}
                          };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };                      

        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1)
                        , "Channel 2"
                        , TitledBorder.CENTER
                        , TitledBorder.DEFAULT_POSITION));      
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);     

        JButton removeButton = new JButton("Remove Selected");
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(removeButton, gbc);

        return panel;
    }

    private JPanel createListPanel()
    {       
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        GridBagConstraints gbc = new GridBagConstraints();

        String[] columnNames = {
                                    "Genre",
                                    "Title",
                                    "Duration (Hours)"
                               }; 
        Object[][] data = {
                            {"Comedy","C & A", "1.5"}
                          };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };                      

        table.setPreferredScrollableViewportSize(new Dimension(200, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1)
                        , "List"
                        , TitledBorder.CENTER
                        , TitledBorder.DEFAULT_POSITION));
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc); 

        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(Box.createRigidArea(new Dimension(100, 30)), gbc);    

        return panel;
    }

    private JPanel createInformationPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 1, 2, 2));
        bottomPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 1)
                        , "Information"
                        , TitledBorder.LEFT
                        , TitledBorder.DEFAULT_POSITION));

        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("TITLE : ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        JTextField titleField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(titleField, gbc);

        JLabel genreLabel = new JLabel("GENRE : ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(genreLabel, gbc);

        JTextField genreField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(genreField, gbc);

        JLabel durationLabel = new JLabel("DURATION : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(durationLabel, gbc);

        JTextField durationField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(durationField, gbc);

        JLabel actorLabel = new JLabel("ACTOR : ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(actorLabel, gbc);

        JTextField actorField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(actorField, gbc);

        JLabel directorLabel = new JLabel("DIRECTOR : ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(directorLabel, gbc);

        JTextField directorField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(directorField, gbc);

        JLabel rentLabel = new JLabel("RENTABLE : ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(rentLabel, gbc);

        JCheckBox rentCBox = new JCheckBox(" ", false);
        rentCBox.setOpaque(true);
        rentCBox.setBackground(Color.WHITE);
        rentCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        gbc.gridx = 1;
        panel.add(rentCBox, gbc);

        JLabel synopsisLabel = new JLabel("SYNOPSIS : ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(synopsisLabel, gbc);

        JTextArea synopsisArea = new JTextArea(10, 5);
        synopsisArea.setBackground(Color.BLUE.darker());
        synopsisArea.setForeground(Color.WHITE);
        synopsisArea.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        panel.add(synopsisArea, gbc);

        JButton addProgramButton = new JButton("Add Program");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(addProgramButton, gbc);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(00.15, 00.15, 60.00, 00.15));
        gbc.gridx = 2;
        gbc.gridy = 8;      
        panel.add(spinner, gbc);

        bottomPanel.add(panel);
        return bottomPanel;
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                tvSchedule = new TVSchedule();
                tvSchedule.createAndDisplayGUI();
            }
        });
    }
}
