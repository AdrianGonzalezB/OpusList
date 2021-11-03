/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spdvi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Alumne
 */
public class MainForm extends javax.swing.JFrame {
  
    ArrayList<Obras> obras = new ArrayList<Obras>();
    private JList<Obras> lstObras;
    private JFileChooser fileChooser;
    final static File imagesFolder = new File(System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\images\\");
    File[] listOfFiles = imagesFolder.listFiles();
   
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        lstObras = new JList<Obras>();
        jScrollPane1.setViewportView(lstObras);
        lstObras.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstUsersValueChanged(evt);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnLoad = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        mnuInsert = new javax.swing.JMenuItem();
        mnuRead = new javax.swing.JMenuItem();
        mnuUpdate = new javax.swing.JMenuItem();
        mnuDelete = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setToolTipText("");

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jMenu2.setText("File");

        mnuExit.setText("Exit");
        jMenu2.add(mnuExit);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("CRUD");

        mnuInsert.setText("Insert");
        mnuInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInsertActionPerformed(evt);
            }
        });
        jMenu1.add(mnuInsert);

        mnuRead.setText("Read");
        mnuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReadActionPerformed(evt);
            }
        });
        jMenu1.add(mnuRead);

        mnuUpdate.setText("Update");
        mnuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUpdateActionPerformed(evt);
            }
        });
        jMenu1.add(mnuUpdate);

        mnuDelete.setText("Delete");
        mnuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeleteActionPerformed(evt);
            }
        });
        jMenu1.add(mnuDelete);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(btnInsert))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\data\\obres.json"));
            java.lang.reflect.Type LIST_OF_OBRA_TYPE = new TypeToken<List<Obras>>() {}.getType();;
            obras = gson.fromJson(reader, LIST_OF_OBRA_TYPE);
            UpdateObrasListView();
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void mnuInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInsertActionPerformed
        InsertDialog insertDialog = new InsertDialog(this, true);
            insertDialog.setVisible(true);
    }//GEN-LAST:event_mnuInsertActionPerformed

    private void mnuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReadActionPerformed
        
    }//GEN-LAST:event_mnuReadActionPerformed

    private void mnuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateActionPerformed
        UpdateDialog updateDialog = new UpdateDialog(this, true);
        updateDialog.setVisible(true);
    }//GEN-LAST:event_mnuUpdateActionPerformed

    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        DeleteDialog deleteDialog = new DeleteDialog(this, true);
        deleteDialog.setVisible(true);
    }//GEN-LAST:event_mnuDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        try (Writer writer = new FileWriter(System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\data\\obres.json")) {
        Gson gson = new GsonBuilder().create();
        gson.toJson(obras, writer);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        UpdateObrasListView();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void UpdateObrasListView() {
        DefaultListModel<Obras> usersListModel = new DefaultListModel<Obras>();
        for(Obras o: obras) {
            usersListModel.addElement(o);
        }
        lstObras.setModel(usersListModel);      
    }
    
    private void lstUsersValueChanged(ListSelectionEvent evt) {
        Obras selectedObra = lstObras.getSelectedValue();
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLoad;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenuItem mnuInsert;
    private javax.swing.JMenuItem mnuRead;
    private javax.swing.JMenuItem mnuUpdate;
    // End of variables declaration//GEN-END:variables
}
