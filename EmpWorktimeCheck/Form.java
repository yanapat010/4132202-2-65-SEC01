import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;
import javax.swing.border.Border;


/**
 *
 * Description
 *
 * @version 1.0 from 17/02/2023
 * @author 
 */

public class Form extends JFrame {
  // start attributes
  private JButton bEmpCheckIn2 = new JButton();
  private JButton bEmployeeData1 = new JButton();
  private JButton bShowReportCheckin1 = new JButton();
  private JButton bEmpCheckOut1 = new JButton();
  private JButton bShowReportCheckOut1 = new JButton();
  private JLabel lFORM1 = new JLabel();
  private JLabel lCHECKIN1 = new JLabel();
  private JLabel lCHECKOUT1 = new JLabel();
  private JLabel lReport1 = new JLabel();
  private JLabel lEMPLOYEEDATA1 = new JLabel();
  // end attributes
  
  public Form() { 
    // Frame-Init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 701; 
    int frameHeight = 764;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Form");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    
    bEmpCheckIn2.setBounds(88, 216, 152, 48);
    bEmpCheckIn2.setText("Emp Check In");
    bEmpCheckIn2.setMargin(new Insets(2, 2, 2, 2));
    bEmpCheckIn2.setBackground(Color.decode("#DFFFD8"));
    Border border = BorderFactory.createLineBorder(Color.decode("#B2B2B2"));        
    bEmpCheckIn2.setBorder(border);
    bEmpCheckIn2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bEmpCheckIn2_ActionPerformed(evt);
      }
    });
    cp.add(bEmpCheckIn2);
    bEmployeeData1.setBounds(216, 576, 256, 64);
    bEmployeeData1.setText("Employee Data");
    bEmployeeData1.setMargin(new Insets(2, 2, 2, 2));
    bEmployeeData1.setBackground(Color.decode("#EFFFFD"));      
    bEmployeeData1.setBorder(border);
    bEmployeeData1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bEmployeeData1_ActionPerformed(evt);
      }
    });
    cp.add(bEmployeeData1);
    bShowReportCheckin1.setBounds(88, 384, 152, 48);
    bShowReportCheckin1.setText("ShowReport Check in");
    bShowReportCheckin1.setMargin(new Insets(2, 2, 2, 2));
    bShowReportCheckin1.setBackground(Color.decode("#DFFFD8"));      
    bShowReportCheckin1.setBorder(border);
    bShowReportCheckin1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bShowReportCheckin1_ActionPerformed(evt);
      }
    });
    cp.add(bShowReportCheckin1);
    bEmpCheckOut1.setBounds(432, 216, 152, 48);
    bEmpCheckOut1.setText("Emp Check Out");
    bEmpCheckOut1.setMargin(new Insets(2, 2, 2, 2));
    bEmpCheckOut1.setBackground(Color.decode("#FF6363"));
    bEmpCheckOut1.setBorder(border);
    bEmpCheckOut1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bEmpCheckOut1_ActionPerformed(evt);
      }
    });
    bEmpCheckOut1.setIconTextGap(1);
    cp.add(bEmpCheckOut1);
    bShowReportCheckOut1.setBounds(432, 384, 152, 48);
    bShowReportCheckOut1.setText("ShowReport Check Out");
    bShowReportCheckOut1.setMargin(new Insets(2, 2, 2, 2));
    bShowReportCheckOut1.setBackground(Color.decode("#FF6363"));
    bShowReportCheckOut1.setBorder(border);
    bShowReportCheckOut1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bShowReportCheckOut1_ActionPerformed(evt);
      }
    });
    cp.add(bShowReportCheckOut1);
    lFORM1.setBounds(264, 16, 144, 57);
    lFORM1.setText("FORM");
    lFORM1.setFont(new Font("Dialog", Font.BOLD, 45));
    lFORM1.setHorizontalTextPosition(SwingConstants.CENTER);
    lFORM1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lFORM1);
    lCHECKIN1.setBounds(56, 144, 219, 57);
    lCHECKIN1.setText("CHECK IN");
    lCHECKIN1.setFont(new Font("Dialog", Font.BOLD, 45));
    lCHECKIN1.setHorizontalAlignment(SwingConstants.CENTER);
    lCHECKIN1.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lCHECKIN1);
    lCHECKOUT1.setBounds(368, 144, 271, 57);
    lCHECKOUT1.setText("CHECK OUT");
    lCHECKOUT1.setFont(new Font("Dialog", Font.BOLD, 45));
    lCHECKOUT1.setHorizontalAlignment(SwingConstants.CENTER);
    lCHECKOUT1.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lCHECKOUT1);
    lReport1.setBounds(200, 288, 271, 57);
    lReport1.setText("Report");
    lReport1.setFont(new Font("Dialog", Font.BOLD, 45));
    lReport1.setHorizontalAlignment(SwingConstants.CENTER);
    lReport1.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lReport1);
    lEMPLOYEEDATA1.setBounds(152, 496, 389, 57);
    lEMPLOYEEDATA1.setText("EMPLOYEE DATA");
    lEMPLOYEEDATA1.setFont(new Font("Dialog", Font.BOLD, 45));
    lEMPLOYEEDATA1.setHorizontalAlignment(SwingConstants.CENTER);
    lEMPLOYEEDATA1.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lEMPLOYEEDATA1);
    // end components
    
    setVisible(true);
  } // end of public Form
  
  // start methods
  
  public static void main(String[] args) {
    new Form();
  } // end of main
  
  public void bEmpCheckIn2_ActionPerformed(ActionEvent evt) {
 
    employeecheck e = new employeecheck();
  } 
 

  public void bEmployeeData1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
       Empdata ET = new Empdata();
  } // end of bEmployeeData1_ActionPerformed

  public void bShowReportCheckin1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
     ShowReportCheckin Sr = new ShowReportCheckin();
  } // end of bShowReportCheckin1_ActionPerformed

  public void bEmpCheckOut1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
     employeecheckOut empOut = new employeecheckOut();
  } // end of bEmpCheckOut1_ActionPerformed

  public void bShowReportCheckOut1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
      ShowReportCheckOut empRpOut = new ShowReportCheckOut();
  } // end of bShowReportCheckOut1_ActionPerformed

  // end methods
} // end of class Form
