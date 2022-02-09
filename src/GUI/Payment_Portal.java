/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.print.*;
import java.awt.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.ImageIcon;



/**
 *
 * @author weera
 */
public class Payment_Portal extends javax.swing.JFrame {

    /**
     * Creates new form Payment_Portal
     */
    public Payment_Portal() {
        initComponents();
        
        String addr = "jdbc:derby://localhost:1527/Moodle";
        
        int transID = 0;
        
        try{
            
            
            Connection link2 = DriverManager.getConnection(addr,"operator","operator1");
            Statement st2 = link2.createStatement();

            String scan2 = "select * from YEARS";
            ResultSet ad_data2 = st2.executeQuery(scan2);
            String year = "0";
            
            ArrayList<String> yearList = new ArrayList<String>();
            
            while(ad_data2.next()){
                year = ad_data2.getString(1);
                yearList.add(year);
            }
            
            int yearCount = yearList.size();
            
            link2.close();
            
            Connection link1 = DriverManager.getConnection(addr,"operator","operator1");
            Statement st1 = link1.createStatement();

            String scan1 = "select * from STUDENT_LOG";
            ResultSet ad_data1 = st1.executeQuery(scan1);
            String stID;
            int intstID;
            String stName;
            String addYear;
            int i;
            int tableLength;
            int tempStID;
            String tempYear;
            
            
            while(ad_data1.next()){
                
                i = 0;
                boolean exist = false;
                
                
                
                while ( i != yearCount){
                    
                    tableLength = paymentsList.size();
                    System.out.println("Size is "+ tableLength);
                    System.out.println("Size2 is "+ yearCount);
                    
                    transID+=1;
                    System.out.println(i);
                    addYear = yearList.get(i);
                    Payments obj = new Payments();
                    intstID = ad_data1.getInt(1);
                    stName = ad_data1.getString(2);
                    System.out.println("Length of Table is: " +tableLength);
                    
                    if (tableLength>0){
                        for (int j=0; j!=(tableLength-1); j+=1){
                        
                            tempStID = paymentsList.get(j).getStudentNumber();
                            tempYear = paymentsList.get(j).getYearLabel();
                            System.out.println(tempStID+ " : "+tempYear);

                            if ((tempStID == intstID)&&(tempYear.equals(addYear))){
                                exist = true;
                                System.out.println("True");
                            }
                        }
                    }
                    
                    
                    if (!exist){
                        obj.setName(stName);
                        obj.setPaidId(transID);
                        obj.setYearLabel(addYear);
                        obj.setStatus(false);
                        obj.setStudentNumber(intstID);
                        Project_2PUEntityManager.getTransaction().begin();
                        Project_2PUEntityManager.merge(obj);
                        Project_2PUEntityManager.getTransaction().commit();
                        paymentsList = paymentsQuery.getResultList();
                        update4(paymentsList);
                        
                    }
                    
                    i+=1;
                    
                
                }
                
                
            }
            link1.close();
            

        }catch(Exception e){
        System.out.println(e);
        } 
        
    }
    
    public class HelloWorldPrinter
            implements Printable {
        public int print(Graphics g, PageFormat pf, int page)
                throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
    }
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    g.drawString("Hello world!", 100, 100);

    // tell the caller that this page is part
    // of the printed document
    return PAGE_EXISTS;
  }
}
    
    
    void update4(List test){
        
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, test, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${paidId}"));
        columnBinding.setColumnName("Paid Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${studentNumber}"));
        columnBinding.setColumnName("Student ID");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${yearLabel}"));
        columnBinding.setColumnName("Year Label");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(Boolean.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
    }
    
    void updateCombo1 (List year){
        
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, year, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);
    }
    
    void updateCombo2 (List year){
        
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, year, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("GUI/Bundle"); // NOI18N
        Project_2PUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory(bundle.getString("Payment_Portal.Project_2PUEntityManager.persistenceUnit")).createEntityManager(); // NOI18N
        paymentsQuery = java.beans.Beans.isDesignTime() ? null : Project_2PUEntityManager.createQuery(bundle.getString("Payment_Portal.paymentsQuery.query")); // NOI18N
        paymentsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : paymentsQuery.getResultList();
        paymentsQuery1 = java.beans.Beans.isDesignTime() ? null : Project_2PUEntityManager.createQuery(bundle.getString("Payment_Portal.paymentsQuery1.query")); // NOI18N
        paymentsList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : paymentsQuery1.getResultList();
        yearsQuery = java.beans.Beans.isDesignTime() ? null : Project_2PUEntityManager.createQuery(bundle.getString("Payment_Portal.yearsQuery.query")); // NOI18N
        yearsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yearsQuery.getResultList();
        buttonGroup1 = new javax.swing.ButtonGroup();
        yearsQuery1 = java.beans.Beans.isDesignTime() ? null : Project_2PUEntityManager.createQuery(bundle.getString("Payment_Portal.yearsQuery1.query")); // NOI18N
        yearsList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : yearsQuery1.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("Payment_Portal.jPanel1.border.title"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, yearsList, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jComboBox1InputMethodTextChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("Payment_Portal.jLabel1.text")); // NOI18N

        jLabel2.setText(bundle.getString("Payment_Portal.jLabel2.text")); // NOI18N

        jLabel3.setText(bundle.getString("Payment_Portal.jLabel3.text")); // NOI18N

        jLabel4.setText(bundle.getString("Payment_Portal.jLabel4.text")); // NOI18N

        jButton1.setText(bundle.getString("Payment_Portal.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText(bundle.getString("Payment_Portal.jLabel5.text")); // NOI18N

        jLabel6.setText(bundle.getString("Payment_Portal.jLabel6.text")); // NOI18N

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, yearsList1, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);

        jButton2.setText(bundle.getString("Payment_Portal.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText(bundle.getString("Payment_Portal.jRadioButton1.text")); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(bundle.getString("Payment_Portal.jRadioButton2.text")); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText(bundle.getString("Payment_Portal.jRadioButton3.text")); // NOI18N
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText(bundle.getString("Payment_Portal.jLabel7.text")); // NOI18N

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, paymentsList1, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${paidId}"));
        columnBinding.setColumnName("Paid Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${studentNumber}"));
        columnBinding.setColumnName("Student ID");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${yearLabel}"));
        columnBinding.setColumnName("Year Label");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(Boolean.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTable1);

        jButton3.setText(bundle.getString("Payment_Portal.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(bundle.getString("Payment_Portal.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText(bundle.getString("Payment_Portal.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5))
                            .addComponent(jTextField1)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4)
                            .addComponent(jTextField2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
                
        

        String yearSelected = jComboBox1.getSelectedItem().toString();
        Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel AND (p.status = false)");
        qN.setParameter("yearLabel", yearSelected);
        paymentsList.clear();
        paymentsList = qN.getResultList();
        update4(paymentsList);
        
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        
        int year = Integer.parseInt(jComboBox1.getSelectedItem().toString());
        
        if (jRadioButton1.isSelected()){

            String yearSelected = jComboBox1.getSelectedItem().toString();
            Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel AND (p.status = true)");
            qN.setParameter("yearLabel", yearSelected);
            paymentsList.clear();
            paymentsList = qN.getResultList();
            update4(paymentsList);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        int index = Integer.parseInt(jTextField1.getText());
        Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.studentNumber = :studentNumber");
        qN.setParameter("studentNumber", index);
        paymentsList.clear();
        paymentsList = qN.getResultList();
        update4(paymentsList);
        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        int year = Integer.parseInt(jComboBox1.getSelectedItem().toString());

        String yearSelected = jComboBox1.getSelectedItem().toString();
        Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel");
        qN.setParameter("yearLabel", yearSelected);
        paymentsList.clear();
        paymentsList = qN.getResultList();
        update4(paymentsList);
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Years obj = new Years();
        int calenderYear;
        try{
            
            calenderYear = Integer.parseInt(jTextField3.getText());
            obj.setYearid(calenderYear);
            Project_2PUEntityManager.getTransaction().begin();
            Project_2PUEntityManager.persist(obj);
            Project_2PUEntityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(rootPane, "Calender Year : " + calenderYear + " Successfully Added!");
            yearsList = yearsQuery.getResultList();
            updateCombo1(yearsList);
            updateCombo2(yearsList);
            
            int tableEntries = 0;
            tableEntries = paymentsList.size();
            int tempID = 0;
            String tempName;
            int logNo = paymentsList.get(tableEntries-1).getPaidId();
            
            for (int j = 0; j == tableEntries-1; j+=1){
                System.out.println(j);
                tempID = paymentsList.get(j).getStudentNumber();
                tempName = paymentsList.get(j).getName();
                Payments newLog = new Payments();
                newLog.setName(tempName);
                newLog.setStudentNumber(tempID);
                newLog.setStatus(false);
                newLog.setPaidId(logNo+1);
                Project_2PUEntityManager.getTransaction().begin();
                Project_2PUEntityManager.persist(newLog);
                Project_2PUEntityManager.getTransaction().commit();
                paymentsList = paymentsQuery.getResultList();
                update4(paymentsList);
                logNo +=1;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        
        if (jRadioButton1.isSelected()){
            String yearSelected = jComboBox1.getSelectedItem().toString();
            Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel AND (p.status = true)");
            qN.setParameter("yearLabel", yearSelected);
            paymentsList.clear();
            paymentsList = qN.getResultList();
            update4(paymentsList);
        }
        
        else if(jRadioButton2.isSelected()){
            String yearSelected = jComboBox1.getSelectedItem().toString();
            Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel AND (p.status = false)");
            qN.setParameter("yearLabel", yearSelected);
            paymentsList.clear();
            paymentsList = qN.getResultList();
            update4(paymentsList);
        }
        
        else if(jRadioButton2.isSelected()){
            String yearSelected = jComboBox1.getSelectedItem().toString();
            Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel");
            qN.setParameter("yearLabel", yearSelected);
            paymentsList.clear();
            paymentsList = qN.getResultList();
            update4(paymentsList);
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBox1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1InputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int stID = Integer.parseInt(jTextField2.getText());
        String payYear = jComboBox2.getSelectedItem().toString();
        String tempYear;
        boolean available = false;
        
        int tempID = 0;
        int newID = 0;
        int tempStID = 0;
                
        String addr = "jdbc:derby://localhost:1527/Moodle";
        
        try{

            Connection link = DriverManager.getConnection(addr,"operator","operator1");
            Statement st = link.createStatement();

            String scan = "select * from PAYMENTS";
            ResultSet ad_data = st.executeQuery(scan);
            
            while(ad_data.next()){
                tempYear = ad_data.getString(4);
                tempID = Integer.parseInt(ad_data.getString(1));
                tempStID = Integer.parseInt(ad_data.getString(5));
                
                
                if ((stID == tempStID)&&(tempYear.equalsIgnoreCase(payYear))){
                    available = true;
                    Payments newpay = Project_2PUEntityManager.find(Payments.class, tempID);
                    if (newpay.getStatus()){
                        JOptionPane.showMessageDialog(rootPane, "Payment Already Made"); 
                        break;                        
                    }
                    
                    
                    else {
                        newID = tempID;
                        newpay.setStatus(true);
                        Project_2PUEntityManager.getTransaction().begin();
                        Project_2PUEntityManager.merge(newpay);
                        Project_2PUEntityManager.getTransaction().commit();
                        JOptionPane.showMessageDialog(rootPane, "Payment : " + newID + " Successfully Made!");
                        paymentsList = paymentsQuery.getResultList();
                        update4(paymentsList);
                        PrinterJob pj = PrinterJob.getPrinterJob();
                        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
                        try {
                            pj.print();
                        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
                        break;
                            
                    }                    
                }
                Logger logger = Logger.getLogger("MyLog");  
                FileHandler fh; 
                fh = new FileHandler("D:/Java Course/Project_2/Project_2/Payment_log.log",true);  
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter); 
                logger.info("Payment By :" + jTextField2.getText() + "For Year :" + jComboBox2.getSelectedItem().toString()); 
            }

        }catch(Exception e){
        System.out.println(e);
        }   
        if (!available){
            JOptionPane.showMessageDialog(rootPane, "Invalid Index");
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Query qN = Project_2PUEntityManager.createQuery("SELECT p FROM Payments p");
        paymentsList.clear();
        paymentsList = qN.getResultList();
        update4(paymentsList);
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jTextField1.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = 3.0;  
    double headerHeight = 5.0;                  
    double footerHeight = 5.0;        
    double width = cm_to_pp(8); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
}
   
    
    
    protected static double cm_to_pp(double cm)
    {            
	        return toPPI(cm * 0.393600787);            
    }
 
protected static double toPPI(double inch)
    {            
	        return inch * 72d;            
    }
    


public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {
      ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg"); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
           // int headerRectHeighta=40;
           
            g2d.drawString("---------------STUDENT REGISTRATION PORTAL---------------",10,y);y+=yShift;
            g2d.drawString("---------------------------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Register ID:               "+jTextField2.getText()+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Registered Year :                 "+jComboBox2.getSelectedItem().toString()+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Payment (RS)   :                 "+jTextField4.getText()+"   ",10,y);y+=yShift;
  
            g2d.drawString("*************************************",10,y);y+=yShift;      
           

    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
  
  
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Payment_Portal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment_Portal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment_Portal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment_Portal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment_Portal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager Project_2PUEntityManager;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private java.util.List<GUI.Payments> paymentsList;
    private java.util.List<GUI.Payments> paymentsList1;
    private javax.persistence.Query paymentsQuery;
    private javax.persistence.Query paymentsQuery1;
    private java.util.List<GUI.Years> yearsList;
    private java.util.List<GUI.Years> yearsList1;
    private javax.persistence.Query yearsQuery;
    private javax.persistence.Query yearsQuery1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
