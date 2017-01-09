

public class Table extends JPanel{

    JTable jt;
    public Table(){

        String [] heading={"Subject","Difficulty","Enjoyment"};
        String [][] data={{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};


        DefaultTableModel surveytab = new DefaultTableModel(data,heading);

        JTable surveytab = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(600,200));
        table.setFillsViewportHeight(true);

        JScrollPane js = new JScrollPane(surveytab);
        js.setVisible(true);
        add(js);

    }
