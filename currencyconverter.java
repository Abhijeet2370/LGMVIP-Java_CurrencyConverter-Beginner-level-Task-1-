import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class currencyconverter extends JFrame {
    
    private JButton convert;
    private JLabel amount;
    private JLabel from;
    private JLabel to;
    private JPanel jPanel1;
    private JTextField Eamount;
    private JComboBox<String> cb1;
    private JComboBox<String> cb2;                

    public currencyconverter() {
        initComponents();
    }
                       
    private void initComponents() {     

        jPanel1 = new JPanel();
        amount = new JLabel();
        from = new JLabel();
        to = new JLabel();
        Eamount = new JTextField("Enter amount");
        cb1 = new JComboBox<>();
        cb2 = new JComboBox<>();
        convert = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        amount.setFont(new Font("Noto sans", 0, 15)); 
        amount.setText("Enter the Amount: ");

        from.setFont(new Font("Noto sans", 0, 15)); 
        from.setText("From :");

        to.setFont(new Font("Noto sans", 0, 15)); 
        to.setText("To :");

        Eamount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EamountActionPerformed(evt);
            }
        });

        cb1.setModel(new DefaultComboBoxModel<>(new String[] { "INR","USD","EGP","EURO","CNY"}));
        cb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        cb2.setModel(new DefaultComboBoxModel<>(new String[] { "INR","USD","EGP","EURO","CNY" }));

        convert.setFont(new Font("Noto sans", 0, 12)); 
        convert.setText("Convert");
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                convertActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(amount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(from, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(to, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(convert, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Eamount)
                            .addComponent(cb1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(Eamount, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(convert, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
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

        pack();
        setLocationRelativeTo(null);
    }                        

    private void EamountActionPerformed(ActionEvent evt) {                                          
    }                                         

    private void convertActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Double Convert;
        Double amount = Double.parseDouble(Eamount.getText());

        if(cb1.getSelectedItem().toString()=="INR" && cb2.getSelectedItem().toString() == "INR"){
            Convert = amount ;
            JOptionPane.showMessageDialog(this,"The Amount is: "+Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="INR" && cb2.getSelectedItem().toString() == "USD"){
            Convert = amount * 0.012;
            JOptionPane.showMessageDialog(this,"The Amount is: "+Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="INR" && cb2.getSelectedItem().toString() == "EGP"){
            Convert = amount * 0.36;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="INR" && cb2.getSelectedItem().toString() == "EURO"){
            Convert = amount * 0.011;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="INR" && cb2.getSelectedItem().toString() == "CNY"){
            Convert = amount * 0.083;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="USD" && cb2.getSelectedItem().toString() == "USD"){
            Convert = amount ;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="USD" && cb2.getSelectedItem().toString() == "INR"){
            Convert = amount * 81.30;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="USD" && cb2.getSelectedItem().toString() == "EGP"){
            Convert = amount * 29.65;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="USD" && cb2.getSelectedItem().toString() == "EURO"){
            Convert = amount * 0.92;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="USD" && cb2.getSelectedItem().toString() == "CNY"){
            Convert = amount * 6.75;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EGP" && cb2.getSelectedItem().toString() == "EGP"){
            Convert = amount;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EGP" && cb2.getSelectedItem().toString() == "INR"){
            Convert = amount * 2.74;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EGP" && cb2.getSelectedItem().toString() == "USD"){
            Convert = amount * 0.034;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EGP" && cb2.getSelectedItem().toString() == "EURO"){
            Convert = amount * 0.031;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EGP" && cb2.getSelectedItem().toString() == "CNY"){
            Convert = amount * 0.23;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EURO" && cb2.getSelectedItem().toString() == "EURO"){
            Convert = amount;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EURO" && cb2.getSelectedItem().toString() == "INR"){
            Convert = amount * 88.20;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EURO" && cb2.getSelectedItem().toString() == "USD"){
            Convert = amount * 1.08;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EURO" && cb2.getSelectedItem().toString() == "EGP"){
            Convert = amount * 32.16;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="EURO" && cb2.getSelectedItem().toString() == "CNY"){
            Convert = amount * 7.32;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="CNY" && cb2.getSelectedItem().toString() == "CNY"){
            Convert = amount;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="CNY" && cb2.getSelectedItem().toString() == "INR"){
            Convert = amount * 12.05;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="CNY" && cb2.getSelectedItem().toString() == "USD"){
            Convert = amount * 0.15;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="CNY" && cb2.getSelectedItem().toString() == "EGP"){
            Convert = amount * 4.40;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }
        else if(cb1.getSelectedItem().toString()=="CNY" && cb2.getSelectedItem().toString() == "EURO"){
            Convert = amount * 0.14;
            JOptionPane.showMessageDialog(this,"The Amount is: "+ Convert.toString());
        }

        
    }                                        

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {                                        
    }                                       
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(currencyconverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(currencyconverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(currencyconverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(currencyconverter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new currencyconverter().setVisible(true);
            }
        });
    }
}
                    


