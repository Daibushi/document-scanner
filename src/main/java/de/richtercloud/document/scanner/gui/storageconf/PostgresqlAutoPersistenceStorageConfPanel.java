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
package de.richtercloud.document.scanner.gui.storageconf;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.richtercloud.document.scanner.gui.Constants;
import de.richtercloud.document.scanner.gui.DocumentScanner;
import de.richtercloud.document.scanner.gui.conf.DocumentScannerConf;
import richtercloud.jhbuild.java.wrapper.ActionOnMissingBinary;
import richtercloud.jhbuild.java.wrapper.ArchitectureNotRecognizedException;
import richtercloud.jhbuild.java.wrapper.BuildFailureException;
import richtercloud.jhbuild.java.wrapper.ExtractionException;
import richtercloud.jhbuild.java.wrapper.JHBuildJavaWrapper;
import richtercloud.jhbuild.java.wrapper.MissingSystemBinary;
import richtercloud.jhbuild.java.wrapper.ModuleBuildFailureException;
import richtercloud.jhbuild.java.wrapper.OSNotRecognizedException;
import richtercloud.jhbuild.java.wrapper.download.Downloader;
import richtercloud.jhbuild.java.wrapper.download.GUIDownloader;
import richtercloud.message.handler.ExceptionMessage;
import richtercloud.message.handler.IssueHandler;
import richtercloud.reflection.form.builder.jpa.storage.PostgresqlAutoPersistenceStorageConf;
import richtercloud.swing.worker.get.wait.dialog.SwingWorkerGetWaitDialog;

/**
 *
 * @author richter
 */
public class PostgresqlAutoPersistenceStorageConfPanel extends StorageConfPanel<PostgresqlAutoPersistenceStorageConf> {
    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = LoggerFactory.getLogger(PostgresqlAutoPersistenceStorageConfPanel.class);
    private final static String DOWNLOAD_DIR_NAME = "postgresql";
    private final static String INSTALL_PREFIX_DIR_NAME = "postgresql-install";
    private final PostgresqlAutoPersistenceStorageConf storageConf;
    private final DocumentScannerConf documentScannerConf;
    private final IssueHandler issueHandler;

    public PostgresqlAutoPersistenceStorageConfPanel(PostgresqlAutoPersistenceStorageConf storageConf,
            DocumentScannerConf documentScannerConf,
            IssueHandler issueHandler) {
        initComponents();
        if(storageConf == null) {
            throw new IllegalArgumentException("storageConf mustn't be null");
        }
        this.storageConf = storageConf;
        this.documentScannerConf = documentScannerConf;
        this.issueHandler = issueHandler;
        this.databaseNameTextField.setText(storageConf.getDatabaseName());
        this.databaseDirTextField.setText(storageConf.getDatabaseDir());
        this.hostnameTextField.setText(storageConf.getHostname());
        this.portSpinner.setValue(storageConf.getPort());
        this.usernameTextField.setText(storageConf.getUsername());
        this.initdbBinaryPathTextField.setText(storageConf.getInitdbBinaryPath());
        this.postgresBinaryPathTextField.setText(storageConf.getPostgresBinaryPath());
        this.createdbBinaryPathTextField.setText(storageConf.getCreatedbBinaryPath());
    }

    @Override
    public PostgresqlAutoPersistenceStorageConf getStorageConf() {
        return this.storageConf;
    }

    @Override
    public void save() {
        this.storageConf.setDatabaseName(this.databaseNameTextField.getText());
        this.storageConf.setDatabaseDir(this.databaseDirTextField.getText());
        this.storageConf.setHostname(this.hostnameTextField.getText());
        this.storageConf.setPort((int) this.portSpinner.getValue());
        String username = this.usernameTextField.getText();
        this.storageConf.setUsername(username);
        String password = String.valueOf(this.passwordPasswordField.getPassword());
        this.storageConf.setPassword(password);
        this.storageConf.setInitdbBinaryPath(this.initdbBinaryPathTextField.getText());
        this.storageConf.setPostgresBinaryPath(this.postgresBinaryPathTextField.getText());
        this.storageConf.setCreatedbBinaryPath(this.createdbBinaryPathTextField.getText());
    }

    @Override
    public void cancel() {
        //do nothing
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        databaseNameTextField = new javax.swing.JTextField();
        databaseNameTextFieldLabel = new javax.swing.JLabel();
        hostnameTextField = new javax.swing.JTextField();
        hostnameTextFieldLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordPasswordField = new javax.swing.JPasswordField();
        portSpinner = new javax.swing.JSpinner();
        portSpinnerLabel = new javax.swing.JLabel();
        databaseDirTextField = new javax.swing.JTextField();
        databaseDirTextFieldLabel = new javax.swing.JLabel();
        initdbBinaryPathTextField = new javax.swing.JTextField();
        postgresBinaryPathTextField = new javax.swing.JTextField();
        initdbBinaryPathTextFieldLabel = new javax.swing.JLabel();
        postgresBinaryPathTextFieldLabel = new javax.swing.JLabel();
        downloadButton = new javax.swing.JButton();
        baseDirTextField = new javax.swing.JTextField();
        baseDirTextFieldLabel = new javax.swing.JLabel();
        directorySeparator = new javax.swing.JSeparator();
        createdbBinaryPathTextField = new javax.swing.JTextField();
        createdbBinaryPathTextFieldLabel = new javax.swing.JLabel();

        databaseNameTextFieldLabel.setText("Database name");

        hostnameTextFieldLabel.setText("Hostname");

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        passwordPasswordField.setText("jPasswordField1");

        portSpinnerLabel.setText("Port");

        databaseDirTextFieldLabel.setText("Database directory");

        initdbBinaryPathTextFieldLabel.setText("initdb binary path");

        postgresBinaryPathTextFieldLabel.setText("postgres binary path");

        downloadButton.setText("Download");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        baseDirTextFieldLabel.setText("Base directory");

        createdbBinaryPathTextFieldLabel.setText("createdb binary path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(portSpinnerLabel)
                            .addComponent(hostnameTextFieldLabel)
                            .addComponent(databaseDirTextFieldLabel)
                            .addComponent(databaseNameTextFieldLabel)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(databaseNameTextField)
                            .addComponent(databaseDirTextField)
                            .addComponent(hostnameTextField)
                            .addComponent(portSpinner)
                            .addComponent(usernameTextField)
                            .addComponent(passwordPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(directorySeparator)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(downloadButton)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(postgresBinaryPathTextFieldLabel)
                            .addComponent(initdbBinaryPathTextFieldLabel)
                            .addComponent(baseDirTextFieldLabel)
                            .addComponent(createdbBinaryPathTextFieldLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createdbBinaryPathTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(initdbBinaryPathTextField)
                            .addComponent(postgresBinaryPathTextField)
                            .addComponent(baseDirTextField))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baseDirTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downloadButton)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initdbBinaryPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(initdbBinaryPathTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postgresBinaryPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(postgresBinaryPathTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdbBinaryPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdbBinaryPathTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directorySeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(databaseNameTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseDirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(databaseDirTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostnameTextFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portSpinnerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.AvoidCatchingThrowable"})
    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        try {
            assert documentScannerConf.getBinaryDownloadDir() != null;
            File installationPrefixDir = new File(documentScannerConf.getBinaryDownloadDir(),
                        //points to installation prefixes as well
                    INSTALL_PREFIX_DIR_NAME);
            File downloadDir = new File(documentScannerConf.getBinaryDownloadDir(),
                    DOWNLOAD_DIR_NAME);
            Downloader downloader = new GUIDownloader(SwingUtilities.getWindowAncestor(this),
                DocumentScanner.generateApplicationWindowTitle("Downloading MySQL",
                        Constants.APP_NAME,
                        Constants.APP_VERSION), //downloadDialogTitle
                "Downloading MySQL", //labelText
                "Downloading MySQL" //progressBarText
            );
            JHBuildJavaWrapper jHBuildJavaWrapper = new JHBuildJavaWrapper(installationPrefixDir,
                    downloadDir,
                    ActionOnMissingBinary.DOWNLOAD,
                    ActionOnMissingBinary.DOWNLOAD,
                    downloader, //downloader
                    false, //skipMD5Check
                    false, //silenceStdout
                    false, //silenceStderr
                    issueHandler
            );
            final SwingWorkerGetWaitDialog dialog = new SwingWorkerGetWaitDialog(SwingUtilities.getWindowAncestor(this), //parent
                    "Building PostgreSQL", //dialogTitle
                    "Building PostgreSQL", //labelText,
                    "Building PostgreSQL" //progressBarText
            );
            SwingWorker<Boolean, Void> downloadWorker = new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() {
                    try {
                        jHBuildJavaWrapper.installModuleset("postgresql-9.5.7" //moduleName
                        );
                        return true;
                    } catch (OSNotRecognizedException
                            | ArchitectureNotRecognizedException
                            | IOException
                            | ExtractionException
                            | InterruptedException
                            | MissingSystemBinary
                            | BuildFailureException
                            | ModuleBuildFailureException ex) {
                        //all not critical
                        issueHandler.handle(new ExceptionMessage(ex));
                        return false;
                    }
                }

                @Override
                protected void done() {
                    dialog.setVisible(false);
                }
            };
            downloadWorker.execute();
            dialog.setVisible(true);
            if(dialog.isCanceled()) {
                jHBuildJavaWrapper.cancelInstallModuleset();
                return;
            }
            try {
                downloadWorker.get();
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error("unexpected exception during download of PostgreSQL occured",
                        ex);
                issueHandler.handleUnexpectedException(new ExceptionMessage(ex));
            }
            baseDirTextField.setText(installationPrefixDir.getAbsolutePath());
            initdbBinaryPathTextField.setText(new File(installationPrefixDir, String.join(File.separator, "bin", "initdb")).getAbsolutePath());
            postgresBinaryPathTextField.setText(new File(installationPrefixDir, String.join(File.separator, "bin", "postgres")).getAbsolutePath());
            createdbBinaryPathTextField.setText(new File(installationPrefixDir, String.join(File.separator, "bin", "createdb")).getAbsolutePath());
        }catch(Throwable ex) {
            LOGGER.error("unexpected exception during download of PostgreSQL occured",
                    ex);
            issueHandler.handleUnexpectedException(new ExceptionMessage(ex));
        }
    }//GEN-LAST:event_downloadButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField baseDirTextField;
    private javax.swing.JLabel baseDirTextFieldLabel;
    private javax.swing.JTextField createdbBinaryPathTextField;
    private javax.swing.JLabel createdbBinaryPathTextFieldLabel;
    private javax.swing.JTextField databaseDirTextField;
    private javax.swing.JLabel databaseDirTextFieldLabel;
    private javax.swing.JTextField databaseNameTextField;
    private javax.swing.JLabel databaseNameTextFieldLabel;
    private javax.swing.JSeparator directorySeparator;
    private javax.swing.JButton downloadButton;
    private javax.swing.JTextField hostnameTextField;
    private javax.swing.JLabel hostnameTextFieldLabel;
    private javax.swing.JTextField initdbBinaryPathTextField;
    private javax.swing.JLabel initdbBinaryPathTextFieldLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordPasswordField;
    private javax.swing.JSpinner portSpinner;
    private javax.swing.JLabel portSpinnerLabel;
    private javax.swing.JTextField postgresBinaryPathTextField;
    private javax.swing.JLabel postgresBinaryPathTextFieldLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}