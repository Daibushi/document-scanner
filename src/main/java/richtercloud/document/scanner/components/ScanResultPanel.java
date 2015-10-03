/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package richtercloud.document.scanner.components;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author richter
 */
public class ScanResultPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    private ScanResultPanelFetcher retriever;
    private byte[] scanData;
    private final static String LABEL_DEFAULT_TEXT = "No data scanned";
    private Set<ScanResultPanelUpdateListener> updateListener = new HashSet<>();

    /**
     * Creates new form ScanResultPanel
     */
    protected ScanResultPanel() {
        this.initComponents();
    }

    public ScanResultPanel(ScanResultPanelFetcher retriever, byte[] initialValue) {
        this();
        this.retriever = retriever;
        this.scanData = initialValue;
        handleScanDataUpdate();
    }

    public void addUpdateListerner(ScanResultPanelUpdateListener updateListener) {
        this.updateListener.add(updateListener);
    }

    public void removeUpdateListener(ScanResultPanelUpdateListener updateListener) {
        this.updateListener.remove(updateListener);
    }

    private void handleScanDataUpdate() {
        if(this.scanData != null) {
            this.scanResultLabel.setText(String.format("%d bytes of data scanned", this.scanData.length));
        }else {
            this.scanResultLabel.setText(LABEL_DEFAULT_TEXT);
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

        scanResultScanButton = new javax.swing.JButton();
        scanResultLabel = new javax.swing.JLabel();
        scanResultDeleteButton = new javax.swing.JButton();

        scanResultScanButton.setText("Save image data");
        scanResultScanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanResultScanButtonActionPerformed(evt);
            }
        });

        scanResultLabel.setText(LABEL_DEFAULT_TEXT);

        scanResultDeleteButton.setText("Delete");
        scanResultDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanResultDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scanResultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scanResultScanButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scanResultDeleteButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scanResultScanButton)
                    .addComponent(scanResultLabel)
                    .addComponent(scanResultDeleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scanResultScanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanResultScanButtonActionPerformed
        this.scanData = this.retriever.fetch();
        for(ScanResultPanelUpdateListener updateListener : this.updateListener) {
            updateListener.onUpdate(new ScanResultPanelUpdateEvent(scanData));
        }
        handleScanDataUpdate();
    }//GEN-LAST:event_scanResultScanButtonActionPerformed

    private void scanResultDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanResultDeleteButtonActionPerformed
        this.scanData = null;
        for(ScanResultPanelUpdateListener updateListener : this.updateListener) {
            updateListener.onUpdate(new ScanResultPanelUpdateEvent(scanData));
        }
        handleScanDataUpdate();
    }//GEN-LAST:event_scanResultDeleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton scanResultDeleteButton;
    private javax.swing.JLabel scanResultLabel;
    private javax.swing.JButton scanResultScanButton;
    // End of variables declaration//GEN-END:variables
}
