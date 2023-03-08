import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.border.Border;

/**
 *
 * Description
 *
 * @version 1.0 from 20/02/2023
 * @author 
 */

public class ShowReportCheckin extends JFrame {
  // start attributes
  private JTable jTable1 = new JTable(0, 6);
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private DefaultTableModel Model = (DefaultTableModel) jTable1.getModel();
  
  private JLabel lSearchByID1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JButton bSearch1 = new JButton();
  private JLabel lSearchByDATE1 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JButton bSearch2 = new JButton();
  private JButton bRefresh1 = new JButton();
  private JLabel lReportCheckIn2 = new JLabel();
  // end attributes

  public ShowReportCheckin() { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 688; 
    int frameHeight = 687;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("ShowReport Check In");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    Empshowdata();
    jTable1.getColumnModel().getColumn(0).setHeaderValue("YOURID");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("NAME");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("LASTNAME");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("CHECKEMP");
    jTable1.getColumnModel().getColumn(4).setHeaderValue("TIME");
     jTable1.getColumnModel().getColumn(5).setHeaderValue("DATE");
    
    jScrollPane1.setBounds(16, 240, 640, 392);
    cp.add(jScrollPane1);
    
    lSearchByID1.setBounds(160, 88, 88, 40);
    lSearchByID1.setText("Search By ID !");
    cp.add(lSearchByID1);
    jTextField1.setBounds(272, 88, 128, 40);
    cp.add(jTextField1);
    bSearch1.setBounds(424, 88, 104, 40);
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
    lSearchByDATE1.setBounds(160, 136, 101, 40);
    jTextField2.setBounds(272, 136, 128, 40);
    bSearch2.setBounds(424, 136, 104, 40);
    lSearchByDATE1.setText("Search By DATE !");
    cp.add(lSearchByDATE1);
    cp.add(jTextField2);
    bSearch2.setText("Search");
    bSearch2.setMargin(new Insets(2, 2, 2, 2));
    bSearch2.setBackground(Color.decode("#F5F5F5"));      
    bSearch2.setBorder(border);
    bSearch2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSearch2_ActionPerformed(evt);
      }
    });
    cp.add(bSearch2);
    bRefresh1.setBounds(152, 192, 376, 40);
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
    lReportCheckIn2.setBounds(232, 24, 220, 37);
    lReportCheckIn2.setText("Report Check In");
    lReportCheckIn2.setFont(new Font("Dialog", Font.BOLD, 28));
    cp.add(lReportCheckIn2);
    // end components
    setVisible(true);
  } // end of public ShowReportCheckin
  
  // start methods
  public void Empshowdata(){
    try {
      // Step 1: Connect to the database
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      //  Execute a query
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT e1.YOURID,e2.NAME, e2.LASTNAME,e1.CHECKEMP ,e1.TIME,e1.DATE FROM emptimecheck e1 JOIN employee e2 ON e1.YOURID = e2.YOURID;");
      
      //  Create a DefaultTableModel object
      DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      
      while (rs.next()) {
        String YOURID = rs.getString("YOURID");  //<<< the columns name in database
        String NAME = rs.getString("NAME");    //<<< the columns name in database
        String LASTNAME = rs.getString("LASTNAME");   //<<< the columns name in database
        String CHECKEMP = rs.getString("CHECKEMP"); //<<< the columns name in database
        String TIME = rs.getString("TIME");
        String DATE = rs.getString("DATE"); //<<< the columns name in database
        model.addRow(new Object[] { YOURID, NAME, LASTNAME,CHECKEMP,TIME,DATE });  // add row to the table until it can't find any data in database 
      }
      
      // Close the connection
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }  
  };
  
  
  public void searchByID(){
    try {
      
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      String YOURID = jTextField1.getText();
      if (YOURID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error! Please enter The ID");
        Empshowdata();
        return; // Stop the method execution
      }
      
      String sqlQuery = "SELECT e1.YOURID, e2.NAME, e2.LASTNAME, e1.CHECKEMP, e1.TIME ,e1.DATE FROM emptimecheck e1 JOIN employee e2 ON e1.YOURID = e2.YOURID WHERE e1.YOURID =?;";
      PreparedStatement ps = conn.prepareStatement(sqlQuery);
      ps.setString(1, YOURID); 
      
      
      ResultSet rs = ps.executeQuery();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      while (rs.next()) {
        String YourID = rs.getString("YOURID");  //<<< the columns name in database
        String NAME = rs.getString("NAME");    //<<< the columns name in database
        String LASTNAME = rs.getString("LASTNAME");   //<<< the columns name in database
        String CHECKEMP = rs.getString("CHECKEMP"); //<<< the columns name in database
        String TIME = rs.getString("TIME");
        String DATE = rs.getString("DATE"); //<<< the columns name in database
        model.addRow(new Object[] { YourID , NAME, LASTNAME,CHECKEMP,TIME,DATE });  // add row to the table until it can't find any data in database 
      }
      
      
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
       jTextField1.setText("");
  }
  
   public void searchByDATE(){
    try {
      
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      String sDATE = jTextField2.getText();
      if (sDATE.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error! Please enter The DATE example [01-01-2021]");
        Empshowdata();
        return; // Stop the method execution
      }
      
      String sqlQuery = "SELECT e1.YOURID, e2.NAME, e2.LASTNAME, e1.CHECKEMP, e1.TIME ,e1.DATE FROM emptimecheck e1 JOIN employee e2 ON e1.YOURID = e2.YOURID WHERE e1.DATE =?;";
      PreparedStatement ps = conn.prepareStatement(sqlQuery);
      ps.setString(1, sDATE); 
      
      ResultSet rs = ps.executeQuery();
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      while (rs.next()) {
        String YourID = rs.getString("YOURID");  //<<< the columns name in database
        String NAME = rs.getString("NAME");    //<<< the columns name in database
        String LASTNAME = rs.getString("LASTNAME");   //<<< the columns name in database
        String CHECKEMP = rs.getString("CHECKEMP"); //<<< the columns name in database
        String TIME = rs.getString("TIME");
        String DATE = rs.getString("DATE"); //<<< the columns name in database
        model.addRow(new Object[] { YourID , NAME, LASTNAME,CHECKEMP,TIME,DATE });  // add row to the table until it can't find any data in database 
      }
      
    
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
       jTextField2.setText("");
  }  
  public static void main(String[] args) {
  
    new ShowReportCheckin();
  } // end of main
  public void bSearch1_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
     searchByID();
  }
  public void bSearch2_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
      searchByDATE();
  } // end of bSearch2_ActionPerformed

  public void bRefresh1_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
  } // end of bRefresh1_ActionPerformed

  // end methods
} // end of class ShowReport
  
 
