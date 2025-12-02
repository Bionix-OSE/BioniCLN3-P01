/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.DashTabs;
import dao.AccountDAO;
import controller.AccountCtrl;
import controller.CustomerCtrl;
import model.Account;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;

public class Usermgr extends javax.swing.JPanel {

    
    private AccountDAO controller = new AccountDAO(); 
    private AccountCtrl accountCtrl = new AccountCtrl();
    private CustomerCtrl customerCtrl = new CustomerCtrl();
    private DefaultTableModel tableModel;

    public Usermgr() {
        initComponents();
        loadData();
        if(btnResetPwd != null) {
            btnResetPwd.addActionListener(e -> addUser()); 
        }
        if(btnF5 != null) btnF5.addActionListener(e -> loadData());
        if(btnDel != null) btnDel.addActionListener(e -> deleteAccount());
        if(btnAdd != null) btnAdd.addActionListener(e -> resetPassword());
    }
    
    private void addUser() {
        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        JComboBox<String> cbRole = new JComboBox<>(new String[]{"customer", "admin"});
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(txtUser);
        panel.add(new JLabel("Password:"));
        panel.add(txtPass);
        panel.add(new JLabel("Role:"));
        panel.add(cbRole);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New User",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();
            String role = (String) cbRole.getSelectedItem();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!");
                return;
            }

            try {
                if (accountCtrl.getAccount(user) != null) {
                     JOptionPane.showMessageDialog(this, "Username already exists!");
                     return;
                }

                accountCtrl.register(user, pass, role);

                if ("customer".equalsIgnoreCase(role)) {
                    Account newAcc = accountCtrl.getAccount(user);
                    if (newAcc != null) {
                         customerCtrl.addCustomer(user, "", "", newAcc.getId());
                    }
                }

                JOptionPane.showMessageDialog(this, "User added successfully!");
                loadData();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
            }
        }
    }
    
    
    private void resetPassword() {
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a user to reset password.");
            return;
        }
        
        String username = (String) Table.getValueAt(selectedRow, 1);
        String newPass = JOptionPane.showInputDialog(this, "Enter new password for " + username + ":");
        
        if (newPass != null && !newPass.trim().isEmpty()) {
            try {
                int id = (int) Table.getValueAt(selectedRow, 0);
                controller.updatePassword(id, newPass);
                JOptionPane.showMessageDialog(this, "Password updated!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void loadData() {
        tableModel = (DefaultTableModel) Table.getModel();
        tableModel.setRowCount(0);

        ArrayList<Account> list = controller.getAll(); //

        for (Account acc : list) {
            tableModel.addRow(new Object[]{
                acc.getId(),
                acc.getUsername(),
                acc.getRole()
            });
        }
    }
    
    private void deleteAccount() {
        int selectedRow = Table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an account to delete.");
            return;
        }

        int id = (int) Table.getValueAt(selectedRow, 0);
        String username = (String) Table.getValueAt(selectedRow, 1);

        if ("admin".equalsIgnoreCase(username)) {
            JOptionPane.showMessageDialog(this, "Cannot delete Main Admin!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete account: " + username + "?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                controller.delete(id);
                loadData();
                JOptionPane.showMessageDialog(this, "Deleted successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TableView = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        ControlStrip = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnResetPwd = new javax.swing.JButton();
        btnF5 = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 720));

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table.setColumnSelectionAllowed(true);
        Table.setFillsViewportHeight(true);
        Table.setShowGrid(true);
        Table.setShowHorizontalLines(false);
        Table.getTableHeader().setReorderingAllowed(false);
        TableView.setViewportView(Table);
        Table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setMinWidth(16);
            Table.getColumnModel().getColumn(0).setPreferredWidth(48);
            Table.getColumnModel().getColumn(0).setMaxWidth(64);
            Table.getColumnModel().getColumn(2).setMinWidth(16);
            Table.getColumnModel().getColumn(2).setPreferredWidth(64);
            Table.getColumnModel().getColumn(2).setMaxWidth(128);
        }

        btnAdd.setText("Reset password");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnResetPwd.setText("Add user");

        btnF5.setText("Refresh");

        btnDel.setText("Delete selected");

        javax.swing.GroupLayout ControlStripLayout = new javax.swing.GroupLayout(ControlStrip);
        ControlStrip.setLayout(ControlStripLayout);
        ControlStripLayout.setHorizontalGroup(
            ControlStripLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlStripLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResetPwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnF5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDel)
                .addContainerGap())
        );
        ControlStripLayout.setVerticalGroup(
            ControlStripLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlStripLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ControlStripLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnF5)
                    .addComponent(btnDel)
                    .addComponent(btnResetPwd))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ControlStrip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TableView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(ControlStrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(TableView, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ControlStrip;
    private javax.swing.JTable Table;
    private javax.swing.JScrollPane TableView;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnF5;
    private javax.swing.JButton btnResetPwd;
    // End of variables declaration//GEN-END:variables
}