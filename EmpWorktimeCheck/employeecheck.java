import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;




/**
 *
 * Description
 *
 * @version 1.0 from 13/02/2023
 * @author 
 */
public class employeecheck extends JFrame {
  // start attributes
  private JLabel lYourID1 = new JLabel();
  private JLabel lTime1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JButton bCheckIn2 = new JButton();
  private JButton bEdit1 = new JButton();
  private JButton bDelete1 = new JButton();
  private JButton bClear1 = new JButton();
  private JTable jTable1 = new JTable(0, 4);
  private DefaultTableModel Model = (DefaultTableModel) jTable1.getModel(); 
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private JButton bRefreshTable1 = new JButton(); 
  
  private JTextField jTextField3 = new JTextField();
  private JLabel lChangeto1 = new JLabel();
  private JTextField jTextField4 = new JTextField();
  private JLabel lCheck1 = new JLabel();
  private JTextField jTextField5 = new JTextField();
  private JLabel lDate1 = new JLabel();
  private JLabel lCHECKIN2 = new JLabel();
  // end attributes
  
 
  

  public employeecheck() { 
    
    
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 560; 
    int frameHeight = 806;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Employee Check In");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    
    
    DataTable();
    lYourID1.setBounds(80, 176, 113, 33);
    lYourID1.setText("Your ID");
    cp.add(lYourID1);
    lTime1.setBounds(80, 224, 113, 33);
    lTime1.setText("Time");
    cp.add(lTime1);
    jTextField1.setBounds(216, 176, 144, 40);
    cp.add(jTextField1);
    jTextField2.setBounds(216, 224, 144, 40);
    cp.add(jTextField2);
    jTextField2.setEnabled(true);
    bCheckIn2.setBounds(160, 384, 256, 40);
    bCheckIn2.setText("Check In");
    bCheckIn2.setMargin(new Insets(2, 2, 2, 2));
    bCheckIn2.setBackground(Color.decode("#DFFFD8"));
    Border border = BorderFactory.createLineBorder(Color.decode("#B2B2B2"));        
    bCheckIn2.setBorder(border);
    
    
    
    
    //------------------------------------------------------------DATE TIME FUCNTION ------------------------------------------------------
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss | dd-MM-yyyy ");
    //    LocalDateTime now = LocalDateTime.now();
    //    String formattedDateTime = now.format(formatter);
    //    
    //    jTextField2.setText(formattedDateTime);
    
    //---------------------------------------------------------------------------------------------------------------------------------------
    Timer timer = new Timer(1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        jTextField2.setText(LocalDateTime.now().format(formatter));
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jTextField5.setText(LocalDateTime.now().format(formatter2));
      }
    });
    timer.start();
    
    
    
    
    bCheckIn2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bCheckIn2_ActionPerformed(evt);
      }
    });
    cp.add(bCheckIn2);
    bEdit1.setBounds(104, 456, 80, 24);
    bEdit1.setText("Edit");
    bEdit1.setMargin(new Insets(2, 2, 2, 2));
    bEdit1.setBackground(Color.decode("#FFFDA2"));     
    bEdit1.setBorder(border);
    bEdit1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bEdit1_ActionPerformed(evt);
      }
    });
    cp.add(bEdit1);
    bDelete1.setBounds(240, 456, 80, 24);
    bDelete1.setText("Delete");
    bDelete1.setMargin(new Insets(2, 2, 2, 2));
    bDelete1.setBackground(Color.decode("#FFAB76"));     
    bDelete1.setBorder(border);
    bDelete1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bDelete1_ActionPerformed(evt);
      }
    });
    cp.add(bDelete1);
    bClear1.setBounds(376, 456, 80, 24);
    bClear1.setText("Clear");
    bClear1.setMargin(new Insets(2, 2, 2, 2));
    bClear1.setBackground(Color.decode("#F5F5F5"));     
    bClear1.setBorder(border);                                            
    bClear1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bClear1_ActionPerformed(evt);
      }
    });
    jTable1.getColumnModel().getColumn(0).setHeaderValue("YOURID");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("CHECKEMP");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("TIME");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("DATE");
    Border border1 = BorderFactory.createLineBorder(Color.decode("#222222"));  
    jTable1.setBorder(border1);  
//    
    
    jTable1.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent evt) { 
        jTable1_MouseClicked(evt);
      }
    });
    
    
    cp.add(bClear1);
    jScrollPane1.setBounds(32, 528, 488, 216);
 
    jScrollPane1.setBorder(border1);    
    cp.add(jScrollPane1);
    bRefreshTable1.setBounds(32, 496, 480, 24);
    bRefreshTable1.setText("RefreshTable");
    bRefreshTable1.setMargin(new Insets(2, 2, 2, 2)); 
    bRefreshTable1.setBackground(Color.decode("#14C38E"));     
    bRefreshTable1.setBorder(border);  
    cp.add(bRefreshTable1);
    bRefreshTable1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bRefreshTable1_ActionPerformed(evt);
      }
    });
    
    jTextField3.setBounds(368, 176, 112, 40);
    cp.add(jTextField3);
    jTextField3.setEnabled(false);
    
    
    lChangeto1.setBounds(392, 144, 80, 24);
    lChangeto1.setText("Change to ");
    cp.add(lChangeto1);
    jTextField4.setBounds(216, 320, 144, 40);
    cp.add(jTextField4);
    jTextField4.setEnabled(false);
    
    lCheck1.setBounds(80, 320, 113, 33);
    lCheck1.setText("Check");
    cp.add(lCheck1);
    jTextField5.setBounds(216, 272, 144, 40);
    lDate1.setBounds(80, 272, 113, 33);
    cp.add(jTextField5);
    lDate1.setText("Date");
    cp.add(lDate1);
    lCHECKIN2.setBounds(152, 48, 265, 60);
    lCHECKIN2.setForeground(Color.decode("#54B435"));  
    lCHECKIN2.setText("CHECK IN !");
    lCHECKIN2.setFont(new Font("Dialog", Font.BOLD, 48));
    cp.add(lCHECKIN2);
    // end components
    setVisible(true);
  };
  // start methods
  public void DataTable(){
    try {
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      //  Execute a query
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM emptimecheck");
      
      //  Create a DefaultTableModel object
      DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      //       DefaultTableModel model =  new DefaultTableModel(
      //          new Object[] { "lYourID1" , "title" , "author" , "publisher" , "publishedYear"  }, 0);  // set a name in table to display 
      
      
      while (rs.next()) {
        String YourID= rs.getString("YOURID");  //<<< the columns name in database
        String CHECKEMP = rs.getString("CHECKEMP");    //<<< the columns name in database
        String TIME = rs.getString("TIME");
        String DATE = rs.getString("DATE");   //<<< the columns name in database
        model.addRow(new Object[] { YourID,  CHECKEMP, TIME , DATE});  // add row to the table until it can't find any data in database 
        
      }
      // Close the connection
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    };  
  };
   
  public static void main(String[] args) {
    new employeecheck();
  } // end of main
  
  public void bCheckIn2_ActionPerformed(ActionEvent evt) {
    String YOURID = jTextField1.getText();
    String CHECKEMP = "Check In ✔";
    String TIME = jTextField2.getText();
    String DATE = jTextField5.getText();
    
    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/emptime", "root", "");
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      // Check if the employee has already checked in today
      ps = conn.prepareStatement("SELECT * FROM emptimecheck WHERE YOURID = ? AND DATE = ?");
      ps.setString(1, YOURID);
      ps.setString(2, DATE);
      rs = ps.executeQuery();
      
      int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to check in this ID ","Warning",JOptionPane.YES_NO_OPTION);
      if(dialogResult == JOptionPane.YES_OPTION){
      
      if (YOURID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error! Please enter Your ID ");
        return; // Stop the method execution
      }
      
      if (rs.next()) {
        // The employee has already checked in today, show warning message
        JOptionPane.showMessageDialog(null, "You have already checked in today.", "Warning", JOptionPane.WARNING_MESSAGE);
      } else {
        // The employee has not checked in today, insert check-in record
        ps = conn.prepareStatement("INSERT INTO emptimecheck VALUES (?, ?, ?, ?)");
        ps.setString(1, YOURID);
        ps.setString(2, CHECKEMP);
        ps.setString(3, TIME);
        ps.setString(4, DATE);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Checked in successfully!");
      }
      }
      
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error occurred while checking in.");
    }
    jTextField1.setText("");
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    DataTable();
  }
 // end of bCheckIn2_ActionPerformed

  public void bEdit1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    int selectedIndex = jTable1.getSelectedRow();
          String CheckemptyID = jTextField3.getText();
       if (CheckemptyID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error! Please enter Your ID in Textfield Change to ");
        return; // Stop the method execution
      }
    try {
      int ID = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      conn.createStatement();
      PreparedStatement ps  = null;
      Statement st = conn.createStatement();
      
      
      String YOURID = jTextField1.getText();
      String YOURIDCHANGE = jTextField3.getText();
      
      ps = conn.prepareStatement("update emptimecheck set YOURID =? where YOURID= ? ");
      ps.setString(1, YOURIDCHANGE);
      ps.setString(2, YOURID);
      
      
      
      ps.executeUpdate();
      ps.close();
      conn.close();
      
      jTextField1.setText("");
      jTextField3.setText("");
      JOptionPane.showMessageDialog(null, "Updated!");
      
      bCheckIn2.setEnabled(true);
      bEdit1.setEnabled(false);
      bDelete1.setEnabled(false);
      jTextField3.setEnabled(false);
      
      
    } catch(SQLException e) {
      System.out.println("SQL error occurred: " + e.getMessage());
      JOptionPane.showMessageDialog(null, "Error");
    }
    model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    DataTable(); 
  } // end of bEdit1_ActionPerformed

  public void bDelete1_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    int selectedIndex = jTable1.getSelectedRow();
    
    try {
      int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
      
      int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to Delete the record","Warning",JOptionPane.YES_NO_OPTION);
      if(dialogResult == JOptionPane.YES_OPTION){
        Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/emptime", "root", "");
        conn.createStatement();
        PreparedStatement ps  = null;
        
        String YOURID = jTextField1.getText();
        
        ps = conn.prepareStatement("delete from emptimecheck where YOURID=? ");
        ps.setString(1, YOURID);
        
        
        
        ps.executeUpdate();
        
        ps.close();
        conn.close();
        
        jTextField1.setText("");
        
        JOptionPane.showMessageDialog(null, "Deleted!");
        
        
        
        bCheckIn2.setEnabled(true);
        bEdit1.setEnabled(false);
        bDelete1.setEnabled(false);
        jTextField3.setEnabled(false);
        
      }
      
      
    } catch(Exception e) {
      System.out.println("Connection fails");
      JOptionPane.showMessageDialog(null, "Error");
    }
    // TODO add your code here
    model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    DataTable();
  } // end of bDelete1_ActionPerformed

  public void bClear1_ActionPerformed(ActionEvent evt) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    DataTable();
    
    jTextField1.setText("");
    jTextField4.setText("");
    
    bCheckIn2.setEnabled(true);
    bEdit1.setEnabled(false);
    bDelete1.setEnabled(false);
    jTextField3.setEnabled(false);
    jTextField1.setEnabled(true);
    // TODO add your code here
    
  } // end of bClear1_ActionPerformed

  public void bRefreshTable1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    DataTable();
    bCheckIn2.setEnabled(true);
    bEdit1.setEnabled(false);
    bDelete1.setEnabled(false);
    jTextField3.setEnabled(false);
    jTextField1.setEnabled(true);
  }; // end of bRefreshTable1_ActionPerformed
  
  public void jTable1_MouseClicked(MouseEvent evt) {
    
    int selectedRow = jTable1.getSelectedRow();
    
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    int selectedIndex = jTable1.getSelectedRow();
    
    jTextField1.setText(model.getValueAt(selectedIndex, 0).toString());
    jTextField2.setText(model.getValueAt(selectedIndex, 2).toString());
    jTextField4.setText(model.getValueAt(selectedIndex, 1).toString());
    
    
    bCheckIn2.setEnabled(false);
    bEdit1.setEnabled(true);
    bDelete1.setEnabled(true);
    jTextField3.setEnabled(true);
    jTextField1.setEnabled(false);
  }

  

  // end methods
}