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
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Alumne
 */
public class MainForm extends javax.swing.JFrame {
  
    ArrayList<Obras> obras = new ArrayList<Obras>();
    JList<Obras> lstObras;
    final static File imagesFolder = new File(System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\images\\");
    final static String images = (System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\images\\");
    File[] listOfFiles = imagesFolder.listFiles();
    private boolean confirmSave = false;
    UpdateDialog update;
    String registro;
   
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        lstObras = new JList<Obras>();
        jScrollPane1.setViewportView(lstObras);
        loadFile();
        lstObras.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstObrasValueChanged(evt);
            }
        });
        lstObras.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                   mouseClickOpus(evt);
                }
            }
        });
    }

    public void mouseClickOpus(java.awt.event.MouseEvent e) {
        if(e.getClickCount() == 2){
            AfirmativeUpdateDialog aup = new AfirmativeUpdateDialog(this, true);
            registro = lstObras.getSelectedValue().getRegistre();
            aup.setVisible(true);
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuInsert = new javax.swing.JMenuItem();
        mnuUpdate = new javax.swing.JMenuItem();
        mnuDelete = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setToolTipText("");

        jMenu2.setText("File");

        mnuExit.setText("Exit & Save");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public boolean isConfirmSave() {
        return confirmSave;
    }

    public void setConfirmSave(boolean confirmSave) {
        this.confirmSave = confirmSave;
    }
    
    public void loadFile()  {
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
    }
    private void mnuInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInsertActionPerformed
        InsertDialog insertDialog = new InsertDialog(this, true);
            insertDialog.setVisible(true);
    }//GEN-LAST:event_mnuInsertActionPerformed

    private void mnuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUpdateActionPerformed
        update = new UpdateDialog(this, true);
        update.setVisible(true);
    }//GEN-LAST:event_mnuUpdateActionPerformed

    private void mnuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeleteActionPerformed
        DeleteDialog deleteDialog = new DeleteDialog(this, true);
        deleteDialog.setVisible(true);
    }//GEN-LAST:event_mnuDeleteActionPerformed

    private void Save() {
        try (Writer writer = new FileWriter(System.getProperty("user.home") + "\\AppData\\Local\\OpusList\\data\\obres.json")) {
        Gson gson = new GsonBuilder().create();
        gson.toJson(obras, writer);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_mnuExitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ConfirmSaveDialog saveDialog = new ConfirmSaveDialog(this, true);
        saveDialog.setVisible(true);
        if (confirmSave)
            Save();
    }//GEN-LAST:event_formWindowClosing

    public void UpdateObrasListView() {
        DefaultListModel<Obras> usersListModel = new DefaultListModel<Obras>();
        for(Obras o: obras) {
            usersListModel.addElement(o);
        }
        lstObras.setModel(usersListModel);      
    }
    
    ImageIcon resizeImageIcon (BufferedImage originalImage, int desiredWidth, int desiredHeight) {
        int newHeight = 0;    
        int newWidth = 0;
        float aspectRatio = (float)originalImage.getWidth() / originalImage.getHeight();
        if (originalImage.getWidth() > originalImage.getHeight()) {
            newWidth = desiredWidth;
            newHeight = Math.round( desiredWidth / aspectRatio);                
        }
        else {
            newHeight = desiredHeight;
            newWidth = Math.round(desiredHeight * aspectRatio);
        }
        Image resultingImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        ImageIcon imageIcon = new ImageIcon(outputImage);
        return imageIcon;
    }
    
    void lstObrasValueChanged(ListSelectionEvent evt) {
        Obras selectedObra = lstObras.getSelectedValue();
        if (selectedObra != null) {
            for (Obras o: obras) {
                if (o.getRegistre().equals(selectedObra.getRegistre())) {
                    try {
                        BufferedImage bufferedImage;
                        if (o.getimatge()== (null)) {
                            bufferedImage = ImageIO.read(getClass().getResource(images + 1)); 
                        }
                        else {
                            String imagePath = images + o.getimatge();
                            bufferedImage = ImageIO.read(new File(imagePath));
                        }
                        ImageIcon icon = resizeImageIcon(bufferedImage, lblImage.getWidth(), lblImage.getHeight());
                        lblImage.setIcon(icon);
                        
                    }
                    catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }            
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JMenuItem mnuDelete;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuInsert;
    private javax.swing.JMenuItem mnuUpdate;
    // End of variables declaration//GEN-END:variables

}
