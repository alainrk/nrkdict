/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nrkdictGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author narko
 */
public class MainFrame extends javax.swing.JFrame {
    private GuiController guiController;
    private boolean currentlyEditing;

    public MainFrame(GuiController guiController){
        super("NrkDict");
        currentlyEditing = false;
        this.guiController = guiController;
        initComponents();
        setVisible(true);
        
        /*********** LOGIC & BINDING ************/
        setDictjComboBox();
        /* Set combobox for autocompletion, editable so autocomplete is extendible to items not contained in comboBox */
        wordjComboBox.setEditable(true);
        /* LOOK AT THIS: In an editable combobox is the editor component that is focused, so i give it the listener */
        wordjComboBox.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar == KeyEvent.VK_ENTER) {
                    loadDefinition();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        AutoCompleteDecorator.decorate(wordjComboBox);
        definitionjTextArea.setEditable(false);
    }

 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DictjLabel = new javax.swing.JLabel();
        DictjComboBox = new javax.swing.JComboBox();
        wordjComboBox = new javax.swing.JComboBox();
        DictjLabel1 = new javax.swing.JLabel();
        removeDictjButton = new javax.swing.JButton();
        addDictjButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        definitionjTextArea = new javax.swing.JTextArea();
        DictjLabel2 = new javax.swing.JLabel();
        editDefinitionjButton = new javax.swing.JButton();
        removeWordjButton = new javax.swing.JButton();
        addWordjButton = new javax.swing.JButton();
        searchjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(500, 600));

        DictjLabel.setText("Select a word:");

        DictjLabel1.setText("Choose your dictionary:");

        removeDictjButton.setText("Remove");
        removeDictjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDictjButtonActionPerformed(evt);
            }
        });

        addDictjButton.setText("Add");

        definitionjTextArea.setColumns(20);
        definitionjTextArea.setRows(5);
        jScrollPane3.setViewportView(definitionjTextArea);

        DictjLabel2.setText("Definition:");

        editDefinitionjButton.setText("Edit definition");
        editDefinitionjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editDefinitionjButtonMouseClicked(evt);
            }
        });

        removeWordjButton.setText("Remove");
        removeWordjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordjButtonActionPerformed(evt);
            }
        });

        addWordjButton.setText("Add");

        searchjButton.setText("Search");
        searchjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchjButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editDefinitionjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(wordjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DictjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(removeWordjButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(addWordjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(removeDictjButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(addDictjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(DictjLabel1)
                                .addComponent(DictjLabel2)
                                .addComponent(DictjLabel))))
                    .addComponent(searchjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DictjLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DictjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeDictjButton)
                    .addComponent(addDictjButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DictjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wordjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeWordjButton)
                    .addComponent(addWordjButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchjButton)
                .addGap(18, 18, 18)
                .addComponent(DictjLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editDefinitionjButton)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeDictjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDictjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeDictjButtonActionPerformed

    private void removeWordjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWordjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeWordjButtonActionPerformed

    private void searchjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchjButtonMouseClicked
        loadDefinition();
    }//GEN-LAST:event_searchjButtonMouseClicked

    private void editDefinitionjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDefinitionjButtonMouseClicked
        /* NOT Currently definition editing */
        if (currentlyEditing == false){
            currentlyEditing = true;
            definitionjTextArea.setEditable(true);
            editDefinitionjButton.setText("Save");
        }
        /* Currently definition editing */
        else {
            currentlyEditing = false;
            definitionjTextArea.setEditable(false);
            editDefinitionjButton.setText("Edit definition");
            // Avoid error
            if (wordjComboBox.getSelectedItem() != null){
                guiController.modifyTerm(wordjComboBox.getSelectedItem().toString(), definitionjTextArea.getText());
            }
            
        }
    }//GEN-LAST:event_editDefinitionjButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DictjComboBox;
    private javax.swing.JLabel DictjLabel;
    private javax.swing.JLabel DictjLabel1;
    private javax.swing.JLabel DictjLabel2;
    private javax.swing.JButton addDictjButton;
    private javax.swing.JButton addWordjButton;
    private javax.swing.JTextArea definitionjTextArea;
    private javax.swing.JButton editDefinitionjButton;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton removeDictjButton;
    private javax.swing.JButton removeWordjButton;
    private javax.swing.JButton searchjButton;
    private javax.swing.JComboBox wordjComboBox;
    // End of variables declaration//GEN-END:variables

    /* Set the dictionary combo box, onchange load selected dictionary */
    private void setDictjComboBox(){
        ArrayList words = guiController.getAllDicts();
        Iterator itr = words.iterator();
        while (itr.hasNext()){
            DictjComboBox.addItem(itr.next());            
        }        
        DictjComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDefinitionLayout();
                guiController.loadDict(DictjComboBox.getSelectedItem().toString());
                setWordjComboBox();
            }
        });
    }
    
    private void setWordjComboBox(){
        /* Clean */
        System.out.println(" DEBUGGING GUI: setWordjComboBox, items: "+wordjComboBox.getItemCount());
        if (wordjComboBox.getItemCount() != 0){
            System.out.println(" DEBUGGING GUI: setWordjComboBox, removing items");
            wordjComboBox.removeAllItems();
            for(ActionListener al : wordjComboBox.getActionListeners()){
//            wordjComboBox.removeActionListener(al);
//            System.out.println(" DEBUGGING GUI: setWordjComboBox, removing listeners: "+wordjComboBox.getActionListeners());
            }
        }
        ArrayList words = guiController.getAllWords();
        if (words != null) {
            /* If there are words in dictionary add them and set the listener to get translation */
            Iterator itr = words.iterator();
            while (itr.hasNext()){
                wordjComboBox.addItem(itr.next());
            }
        }
        wordjComboBox.addActionListener(new ActionListener() {
            @Override
            /* On selection in combo box, reset everything deal with definition */
            public void actionPerformed(ActionEvent e) {
                resetDefinitionLayout();
                loadDefinition();
            }
        });
    }
    
    private void loadDefinition(){
        resetDefinitionLayout();
        definitionjTextArea.setText(guiController.getTransl(wordjComboBox.getSelectedItem().toString()));
        definitionjTextArea.setEditable(false);
    }
    
    /* Reset state of textarea, button etc.*/
    private void resetDefinitionLayout(){
        definitionjTextArea.removeAll();
        if (currentlyEditing == true ){
            currentlyEditing = false;
            definitionjTextArea.setEditable(false);
            editDefinitionjButton.setText("Edit definition");
        }
    }
}