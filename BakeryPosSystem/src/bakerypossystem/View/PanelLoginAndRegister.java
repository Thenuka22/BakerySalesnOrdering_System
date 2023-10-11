package bakerypossystem.View;

import CustomComponents.Button;
import CustomComponents.MyPasswordField;
import CustomComponents.MyTextField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import bakerypossystem.Controller.CUserSignIn;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Customer Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user_1.png")));
        txtUser.setHint("Username");
        register.add(txtUser, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Log in");
        register.add(cmd, "w 40%, h 40");

     
        JLabel label1 = new JLabel("Use Guest Account Instead");
        label1.setFont(new Font("sansserif", 1, 20));
        label1.setForeground(new Color(7, 164, 121));
        register.add(label1);
        
        //  Add an ActionListener to cmd1 button
    label1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            openAnotherFrame(); 
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelLoginAndRegister.this);
            frame.dispose(); 
           }
        @Override
        public void mouseEntered(MouseEvent e){
           label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
});
     cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
        String username = txtUser.getText();
        String password = txtPass.getText();
        CustomerView DashBoardCustomize = new CustomerView();
        //CUserSignIn nn = new CUserSignIn();
        if(username.equals("Thenuka" )&& password.equals("1234")) {
            DashBoardCustomize.show();
        }
         else
            JOptionPane.showMessageDialog(null, "Sign In Unsuccessful.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });


     

   }
    private void openAnotherFrame() {
    // Create and open another frame here
    CustomerView anotherFrame = new CustomerView();
    
    // Add components, set properties, and customize the new frame as needed
    anotherFrame.setVisible(true);
}

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Bakery Staff Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Login");
        login.add(cmd, "w 40%, h 40");
         
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
        String username = txtEmail.getText();
        String password = txtPass.getText();
        DashBoardCustomize DashBoardCustomize = new DashBoardCustomize();
        CUserSignIn nn = new CUserSignIn();
        if(nn.signIn(username, password)==1) {
            DashBoardCustomize.show();
        }
         else
            JOptionPane.showMessageDialog(null, "Sign In Unsuccessful.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
       

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
