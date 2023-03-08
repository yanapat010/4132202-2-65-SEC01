import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionListener; 
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.border.Border;

/**
 *
 * Description
 *
 * @version 1.0 from 19/02/2023
 * @author 
 */

public class EmpSearch extends JFrame {
  private JComboBox<String> jComboBox1 = new JComboBox<>();
  // start attributes
  private DefaultComboBoxModel<String> jComboBox1Model = new DefaultComboBoxModel<>();
  private JTable jTable1 = new JTable(0, 4);
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private DefaultTableModel Model = (DefaultTableModel) jTable1.getModel();

  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JButton bSearch1 = new JButton();
  private JTextField jTextField4 = new JTextField();
  private JLabel lIDHERE1 = new JLabel();
  
  private JButton bRefresh1 = new JButton();
  // end attributes

  public EmpSearch() { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 521; 
    int frameHeight = 532;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("EmpSearch");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    jComboBox1.setModel(jComboBox1Model);
    jComboBox1.setBounds(64, 48, 96, 32);
    cp.add(jComboBox1);
    jTextField1.setBounds(168, 48, 80, 32);
    cp.add(jTextField1);
    jTextField2.setBounds(256, 48, 80, 32);
    cp.add(jTextField2);
    jTextField3.setBounds(344, 48, 80, 32);
    cp.add(jTextField3); 
    
     Combobox();
     Empshowdata();

    jTable1.getColumnModel().getColumn(0).setHeaderValue("YOURID");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("NAME");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("LASTNAME");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("PHONE");
    
    
    jScrollPane1.setBounds(16, 256, 472, 224);
    cp.add(jScrollPane1);
    bSearch1.setBounds(312, 160, 88, 32);
    bSearch1.setText("Search");
    bSearch1.setMargin(new Insets(2, 2, 2, 2));
    bSearch1.setBackground(Color.decode("#F5F5F5"));
    Border border = BorderFactory.createLineBorder(Color.decode("#B2B2B2"));      
    bSearch1.setBorder(border);    
    
    bSearch1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSearch1_ActionPerformed(evt);
      }
    });
    cp.add(bSearch1);
    jTextField4.setBounds(208, 160, 80, 32);
    cp.add(jTextField4);
    lIDHERE1.setBounds(120, 160, 80, 32);
    lIDHERE1.setText("ID HERE !");
    cp.add(lIDHERE1);
    bRefresh1.setBounds(16, 224, 472, 24);
    bRefresh1.setText("Refresh");
    bRefresh1.setMargin(new Insets(2, 2, 2, 2));
    bRefresh1.setBackground(Color.decode("#14C38E"));     
    bRefresh1.setBorder(border);  
    bRefresh1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bRefresh1_ActionPerformed(evt);
      }
    });
    cp.add(bRefresh1);
    // end components
    setVisible(true);
  } 
  
  
  public static void main(String[] args) {
    new EmpSearch();
  } // end of main
  
  // start methods
    public void Empshowdata(){
    try {
      // Step 1: Connect to the database
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      //  Execute a query
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM employee");
      
      DefaultTableModel model = (DefaultTableModel)jTable1.getModel(); 
      
      
      while (rs.next()) {
        String YourID= rs.getString("YOURID");  //<<< the columns name in database
        String name = rs.getString("NAME");    //<<< the columns name in database
        String Lastname = rs.getString("LASTNAME");   //<<< the columns name in database
        String Phone = rs.getString("PHONE"); 
        model.addRow(new Object[] { YourID,name,Lastname,Phone});  // add row to the table until it can't find any data in database 
        
      }
      
      
      // Close the connection
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    };  
  };
  
  
  public void Combobox(){
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/emptime", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employee");
        while (rs.next()) {
            String YourID= rs.getString("YOURID");
            jComboBox1.addItem(YourID); 
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
      jComboBox1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String selectedID = jComboBox1.getSelectedItem().toString();
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/emptime", "root", "");
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE YOURID = ?");
                ps.setString(1, selectedID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("NAME");
                    String lastname = rs.getString("LASTNAME");
                    String phone = rs.getString("PHONE");
                    jTextField1.setText(name);
                    jTextField2.setText(lastname);
                    jTextField3.setText(phone);
                }
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
};
  public void search(){
    try {

  Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost/emptime", "root", "");
  

  String YOURID = jTextField4.getText();
  

  String sqlQuery = "SELECT * FROM employee WHERE YOURID = ?";
  PreparedStatement ps = conn.prepareStatement(sqlQuery);
  ps.setString(1, YOURID); 
  

  ResultSet rs = ps.executeQuery();
  DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
  while (rs.next()) {
    String yourID = rs.getString("YOURID");
    String name = rs.getString("NAME");
    String lastname = rs.getString("LASTNAME");
    String phone = rs.getString("PHONE");
    model.addRow(new Object[] { yourID, name, lastname, phone });
  }
  

  conn.close();
} catch (SQLException e) {
  e.printStackTrace();
}
    jTextField4.setText("");
  } 
    
  public void bSearch1_ActionPerformed(ActionEvent evt) {
  DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    search();

}
   public void bRefresh1_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
    
  } 
}


  

 



