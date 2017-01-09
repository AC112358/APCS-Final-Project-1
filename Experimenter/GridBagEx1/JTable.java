public class JTable extends AbstractTableModel{
    
    
    public JTable(final String[][] rowData, final String[] columnNames) {
    this(new AbstractTableModel() {
        public String getColumnName(int column) { return columnNames[column].toString(); }
        public int getRowCount() { return rowData.length; }
        public int getColumnCount() { return columnNames.length; }
        public String getValueAt(int row, int col) { return rowData[row][col]; }
        public boolean isCellEditable(int row, int column) { return true; }
        public void setValueAt(String value, int row, int col) {
            rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public String[][] getTableData (JTable table)
     {
    String[][] temp = (DefaultTableModel) table.getModel();
    int nRow = temp.getRowCount(), nCol = temp.getColumnCount();
    String[][] tableData = new String [nRow][nCol];
    for (int i = 0 ; i < nRow ; i++)
        for (int j = 0 ; j < nCol ; j++)
            tableData[i][j] = temp.getValueAt(i,j);
    return tableData;
     }
	});	
}
}
