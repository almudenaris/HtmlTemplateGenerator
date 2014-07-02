/*
 *  Copyright 2012-2013 Ontology Engineering Group, Universidad Politecnica de Madrid, Spain

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

/*
 * WidocoGui2.java
 *
 * Created on 16-jun-2014, 21:38:19
 */
package widoco.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFileChooser;

/**
 *
 * @author Daniel Garijo
 */
public class GuiStep3 extends javax.swing.JFrame {
    private GuiController g;
    private String abstractPath;
    private String introductionPath;
    private String overviewPath;
    private String descriptionPath;
    private String referencesPath;
    /** Creates new form WidocoGui2
     * @param g */
    public GuiStep3(GuiController g) {
        this.g = g;
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        this.setLocation(x, y);
        
        /**
         * If some sections exist, they shall be updated here. Namely the paths to the html files
         */
        if(!g.getConfig().isIncludeAbstract())checkBoxAbstract.setSelected(false);
        if(!g.getConfig().isIncludeIntroduction())checkBoxIntro.setSelected(false);
        if(!g.getConfig().isIncludeOverview())checkBoxOverview.setSelected(false);
        if(!g.getConfig().isIncludeDescription())checkBoxDescription.setSelected(false);
        if(!g.getConfig().isIncludeReferences())checkBoxRefs.setSelected(false);
        if(!g.getConfig().isPublishProvenance())checkBoxProv.setSelected(false);
        //if(g.isDiagram())
                        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneSteps = new javax.swing.JTextPane();
        jSeparator2 = new javax.swing.JSeparator();
        labelTitle = new javax.swing.JLabel();
        labelSteps = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        checkBoxAbstract = new javax.swing.JCheckBox();
        checkBoxIntro = new javax.swing.JCheckBox();
        checkBoxOverview = new javax.swing.JCheckBox();
        checkBoxDescription = new javax.swing.JCheckBox();
        checkBoxRefs = new javax.swing.JCheckBox();
        abstractSectionButton = new javax.swing.JButton();
        introductionSectionButton = new javax.swing.JButton();
        overviewSectionButton = new javax.swing.JButton();
        descriptionSectionButton = new javax.swing.JButton();
        referencesSectionButton = new javax.swing.JButton();
        checkBoxDiagram = new javax.swing.JCheckBox();
        checkBoxProv = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        nextButton.setText("Next >");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        backButton.setText("< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        textPaneSteps.setEditable(false);
        textPaneSteps.setContentType("text/html"); // NOI18N
        textPaneSteps.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r \n1. Select template<br/>       \n2. Load Metadata<br/>\n<b>3. Load Sections</b><br/>\n4. Configure LODE<br/>\n5. Generate!\n  </body>\r\n</html>\r\n");
        jScrollPane1.setViewportView(textPaneSteps);

        labelTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTitle.setText("Step 3: Load Sections. Select which sections you want your document to have");

        labelSteps.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelSteps.setText("Steps");

        checkBoxAbstract.setSelected(true);
        checkBoxAbstract.setText("Include Abstract Section");

        checkBoxIntro.setSelected(true);
        checkBoxIntro.setText("Include Introduction Section");

        checkBoxOverview.setSelected(true);
        checkBoxOverview.setText("Include Overview Section");

        checkBoxDescription.setSelected(true);
        checkBoxDescription.setText("Include Description Section");

        checkBoxRefs.setSelected(true);
        checkBoxRefs.setText("Include References Section");

        abstractSectionButton.setText("Load...");
        abstractSectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abstractSectionButtonActionPerformed(evt);
            }
        });

        introductionSectionButton.setText("Load...");
        introductionSectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introductionSectionButtonActionPerformed(evt);
            }
        });

        overviewSectionButton.setText("Load...");
        overviewSectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewSectionButtonActionPerformed(evt);
            }
        });

        descriptionSectionButton.setText("Load...");
        descriptionSectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionSectionButtonActionPerformed(evt);
            }
        });

        referencesSectionButton.setText("Load...");
        referencesSectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                referencesSectionButtonActionPerformed(evt);
            }
        });

        checkBoxDiagram.setText("Create a diagram file of the ontology");
        checkBoxDiagram.setEnabled(false);

        checkBoxProv.setSelected(true);
        checkBoxProv.setText("Publish the provenance of the vocabulary");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxDiagram)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(checkBoxAbstract)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(abstractSectionButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(checkBoxIntro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(introductionSectionButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(checkBoxOverview)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(overviewSectionButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkBoxRefs)
                                    .addComponent(checkBoxDescription))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(descriptionSectionButton)
                                    .addComponent(referencesSectionButton))))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkBoxProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxAbstract)
                    .addComponent(abstractSectionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxIntro)
                    .addComponent(introductionSectionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBoxOverview)
                    .addComponent(overviewSectionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionSectionButton)
                    .addComponent(checkBoxDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(referencesSectionButton)
                    .addComponent(checkBoxRefs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkBoxDiagram)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxProv)
                .addGap(49, 49, 49))
        );

        jLabel1.setText("<html>Select the sections you want your document to have.<br/> If you have a section you want to load, click on the \"Load\" button.<br/>The diagram of the ontology will be created on the \"images\" folder of the documentation.</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelSteps, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(labelSteps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(backButton)
                    .addComponent(nextButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.saveState();
        g.switchState("back");
    }//GEN-LAST:event_backButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.saveState();
        g.switchState("cancel");
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.saveState();
        g.switchState("next");
    }//GEN-LAST:event_nextButtonActionPerformed

    private void saveState(){
        g.getConfig().setIncludeAbstract(checkBoxAbstract.isSelected());
        g.getConfig().setAbstractPath(this.abstractPath);
        g.getConfig().setIncludeDescription(checkBoxDescription.isSelected());
        g.getConfig().setDescriptionPath(descriptionPath);
        g.getConfig().setIncludeIntroduction(checkBoxIntro.isSelected());
        g.getConfig().setIntroductionPath(introductionPath);
        g.getConfig().setIncludeOverview(checkBoxOverview.isSelected());
        g.getConfig().setOverviewPath(overviewPath);
        g.getConfig().setPublishProvenance(checkBoxProv.isSelected());
        g.getConfig().setIncludeReferences(checkBoxRefs.isSelected());
        g.getConfig().setReferencesPath(referencesPath);
        //g.saveDiagram(rootPaneCheckingEnabled);
    }
    
    private void abstractSectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abstractSectionButtonActionPerformed
        abstractPath = loadSection();
    }//GEN-LAST:event_abstractSectionButtonActionPerformed

    private void introductionSectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introductionSectionButtonActionPerformed
        introductionPath = loadSection();
    }//GEN-LAST:event_introductionSectionButtonActionPerformed

    private void overviewSectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overviewSectionButtonActionPerformed
        overviewPath = loadSection();
    }//GEN-LAST:event_overviewSectionButtonActionPerformed

    private void descriptionSectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionSectionButtonActionPerformed
        descriptionPath = loadSection();
    }//GEN-LAST:event_descriptionSectionButtonActionPerformed

    private void referencesSectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_referencesSectionButtonActionPerformed
        referencesPath = loadSection();
    }//GEN-LAST:event_referencesSectionButtonActionPerformed

    private String loadSection(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GuiStep3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GuiStep3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GuiStep3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GuiStep3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new GuiStep3().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abstractSectionButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox checkBoxAbstract;
    private javax.swing.JCheckBox checkBoxDescription;
    private javax.swing.JCheckBox checkBoxDiagram;
    private javax.swing.JCheckBox checkBoxIntro;
    private javax.swing.JCheckBox checkBoxOverview;
    private javax.swing.JCheckBox checkBoxProv;
    private javax.swing.JCheckBox checkBoxRefs;
    private javax.swing.JButton descriptionSectionButton;
    private javax.swing.JButton introductionSectionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelSteps;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton overviewSectionButton;
    private javax.swing.JButton referencesSectionButton;
    private javax.swing.JTextPane textPaneSteps;
    // End of variables declaration//GEN-END:variables
}
