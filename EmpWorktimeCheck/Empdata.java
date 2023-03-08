import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;


/**
 *
 * Description
 *
 * @version 1.0 from 18/02/2023
 * @author 
 */

public class Empdata extends JFrame {
  // start attributes
  private JTable jTable1 = new JTable(0, 4);
  private JScrollPane jScrollPane1 = new JScrollPane(jTable1);
  private DefaultTableModel Model = (DefaultTableModel) jTable1.getModel();

  
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JLabel lYOURID1 = new JLabel();
  private JLabel lNAME1 = new JLabel();
  private JLabel lLASTNAME1 = new JLabel();
  private JLabel lPHONE1 = new JLabel();
  private JButton bAdd1 = new JButton();
  private JButton bEdit1 = new JButton();
  private JButton bDelete1 = new JButton();
  private JButton bClear1 = new JButton();
  private JButton bSearchdataForm1 = new JButton();
  // end attributes
  
  public Empdata() { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 532; 
    int frameHeight = 612;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Employee Data");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    
    Empshowdata();
    
    jScrollPane1.setBounds(16, 328, 488, 232);
    cp.add(jScrollPane1);
    
    jTable1.getColumnModel().getColumn(0).setHeaderValue("YOURID");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("NAME");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("LASTNAME");
    jTable1.getColumnModel().getColumn(3).setHeaderValue("PHONE");
    
    
    
    jTextField1.setBounds(240, 32, 104, 40);
    cp.add(jTextField1);
    jTextField2.setBounds(240, 80, 104, 40);
    cp.add(jTextField2);
    jTextField3.setBounds(240, 128, 104, 40);
    cp.add(jTextField3);
    jTextField4.setBounds(240, 176, 104, 40);
    cp.add(jTextField4);
    lYOURID1.setBounds(144, 32, 80, 40);
    lYOURID1.setText("YOURID");
    cp.add(lYOURID1);
    lNAME1.setBounds(144, 80, 80, 40);
    lNAME1.setText("NAME");
    cp.add(lNAME1);
    lLASTNAME1.setBounds(144, 128, 80, 40);
    lLASTNAME1.setText("LASTNAME");
    cp.add(lLASTNAME1);
    lPHONE1.setBounds(144, 176, 80, 40);
    lPHONE1.setText("PHONE");
    cp.add(lPHONE1);
    bAdd1.setBounds(72, 248, 80, 24);
    bAdd1.setText("Add");
    bAdd1.setMargin(new Insets(2, 2, 2, 2));
    bAdd1.setBackground(Color.decode("#DFFFD8"));
    Border border = BorderFactory.createLineBorder(Color.decode("#B2B2B2"));        
    bAdd1.setBorder(border);
    bAdd1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bAdd1_ActionPerformed(evt);
      }
    });
    cp.add(bAdd1);
    bEdit1.setBounds(168, 248, 80, 24);
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
    bDelete1.setBounds(264, 248, 80, 24);
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
    bClear1.setBounds(360, 248, 80, 24);
    bClear1.setText("Clear");
    bClear1.setMargin(new Insets(2, 2, 2, 2));
    bClear1.setBackground(Color.decode("#F5F5F5"));     
    bClear1.setBorder(border);     
    bClear1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bClear1_ActionPerformed(evt);
      }
    });
    cp.add(bClear1);
    
    jTable1.addMouseListener(new MouseAdapter() { 
      public void mouseClicked(MouseEvent evt) { 
        jTable1_MouseClicked(evt);
      }
    });
    
    bDelete1.setEnabled(false);
    bSearchdataForm1.setBounds(376, 296, 128, 24);
    bSearchdataForm1.setText("Search data Form");
    bSearchdataForm1.setMargin(new Insets(2, 2, 2, 2));
    bSearchdataForm1.setBackground(Color.decode("#F5F5F5"));     
    bSearchdataForm1.setBorder(border);  
    bSearchdataForm1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSearchdataForm1_ActionPerformed(evt);
      }
    });
    cp.add(bSearchdataForm1);  
    bEdit1.setEnabled(false);
    
    // end components
    
    setVisible(true);
  } // end of public Empdata
  public void Empshowdata(){
    try {
      // Step 1: Connect to the database
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      
      //  Execute a query
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM employee");
      
      //  Create a DefaultTableModel object
      DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
      //       DefaultTableModel model =  new DefaultTableModel(
      //          new Object[] { "lYourID1" , "title" , "author" , "publisher" , "publishedYear"  }, 0);  // set a name in table to display 
      
      
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
  // start methods
  
  public static void main(String[] args) {
    new Empdata();
  } // end of main
  
  public void bAdd1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    String YourID = jTextField1.getText();
    String Name  = jTextField2.getText();
    String Lastname  = jTextField3.getText();
    String Phone = jTextField4.getText();
    
    if (YourID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error! Please enter Your ID");
        return; // Stop the method execution
    }
    
    try{
      //      con = DriverManager.getConnection(dbUrl,"root","");
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      //      Statement stmt = connection.createStatement();
      //       String sqlStatement =;
      PreparedStatement ps = null;
      ps = conn.prepareStatement("insert into employee values (?,?,?,?)");
      ps.setString(1, YourID);
      ps.setString(2, Name);
      ps.setString(3, Lastname);
      ps.setString(4, Phone);
      ps.executeUpdate();
      ps.close();
      conn.close();
      
      jTextField1.setText("");
      jTextField2.setText("");
      jTextField3.setText("");
      jTextField4.setText("");
      
      JOptionPane.showMessageDialog(null, "Saved!");
      
      bEdit1.setEnabled(false);
      bDelete1.setEnabled(false);
      
    }catch(Exception e) {
      System.out.println("Connection fails");
      JOptionPane.showMessageDialog(null, "Error! \nID incorrect");
    } 
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
  } // end of bAdd1_ActionPerformed

  public void bEdit1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    int selectedIndex = jTable1.getSelectedRow();
    
    try {
      int ID = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/emptime", "root", "");
      conn.createStatement();
      PreparedStatement ps  = null;
      Statement st = conn.createStatement();
      
      
      String YOURID = jTextField1.getText();
      String NAME = jTextField2.getText();
      String LASTNAME = jTextField3.getText();
      String PHONE = jTextField4.getText();
      
      
      ps = conn.prepareStatement("update employee set YOURID = ?, NAME = ? , LASTNAME = ? , PHONE = ?   where YOURID= ? ");
      ps.setString(1, YOURID);
      ps.setString(2, NAME);
      ps.setString(3, LASTNAME);
      ps.setString(4, PHONE);
      ps.setString(5, YOURID);
      
      
      
      ps.executeUpdate();
      ps.close();
      conn.close();
      
      jTextField1.setText("");
      jTextField2.setText("");
      jTextField3.setText("");
      jTextField4.setText("");
      JOptionPane.showMessageDialog(null, "Updated!");
      
      
      bEdit1.setEnabled(false);
      bDelete1.setEnabled(false);
      
      
    } catch(SQLException e) {
      System.out.println("SQL error occurred: " + e.getMessage());
      JOptionPane.showMessageDialog(null, "Error");
    }
    model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
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
        
        ps = conn.prepareStatement("delete from employee where YOURID=? ");
        ps.setString(1, YOURID);
        
        
        
        ps.executeUpdate();
        
        ps.close();
        conn.close();
        
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        JOptionPane.showMessageDialog(null, "Deleted!");
        
        
        
        bEdit1.setEnabled(false);
        bDelete1.setEnabled(false);
        
      }
      
      
    } catch(Exception e) {
      System.out.println("Connection fails");
      JOptionPane.showMessageDialog(null, "Error");
    }
    // TODO add your code here
    model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
    
  } // end of bDelete1_ActionPerformed

  public void bClear1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);
    Empshowdata();
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    bEdit1.setEnabled(false);
    bDelete1.setEnabled(false);
  } // end of bClear1_ActionPerformed
  public void jTable1_MouseClicked(MouseEvent evt) {
    
    int selectedRow = jTable1.getSelectedRow();
    
    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
    int selectedIndex = jTable1.getSelectedRow();
    
    jTextField1.setText(model.getValueAt(selectedIndex, 0).toString());
    jTextField2.setText(model.getValueAt(selectedIndex, 1).toString());
    jTextField3.setText(model.getValueAt(selectedIndex, 2).toString());
    jTextField4.setText(model.getValueAt(selectedIndex, 3).toString());
    bEdit1.setEnabled(true);
    bDelete1.setEnabled(true);
  }
  public void bSearchdataForm1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
    EmpSearch Ems = new EmpSearch();
  } // end of bSearchdataForm1_ActionPerformed

  // end methods
} // end of class EmpdataTest
