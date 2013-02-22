/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nrkdictGUI;
/**
 *
 * @author narko
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm(){
        super("NrkDict");
        initComponents();
        setVisible(true);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(500, 600));

        DictjLabel.setText("Select a word:");

        DictjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        wordjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        removeWordjButton.setText("Remove");
        removeWordjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordjButtonActionPerformed(evt);
            }
        });

        addWordjButton.setText("Add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DictjLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editDefinitionjButton)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeDictjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDictjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeDictjButtonActionPerformed

    private void removeWordjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWordjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeWordjButtonActionPerformed

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
    private javax.swing.JComboBox wordjComboBox;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTextField AutoSearchjTextField;
}
