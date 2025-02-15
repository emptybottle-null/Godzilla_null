/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.tools.debugger.ContextWindow;
import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.FileTextArea;
import org.mozilla.javascript.tools.debugger.FileWindow;
import org.mozilla.javascript.tools.debugger.FindFunction;
import org.mozilla.javascript.tools.debugger.GuiCallback;
import org.mozilla.javascript.tools.debugger.JSInternalConsole;
import org.mozilla.javascript.tools.debugger.Menubar;
import org.mozilla.javascript.tools.debugger.MessageDialogWrapper;
import org.mozilla.javascript.tools.debugger.MoreWindows;
import org.mozilla.javascript.tools.debugger.RunProxy;

public class SwingGui
extends JFrame
implements GuiCallback {
    private static final long serialVersionUID = -8217029773456711621L;
    Dim dim;
    private Runnable exitAction;
    private JDesktopPane desk;
    private ContextWindow context;
    private Menubar menubar;
    private JToolBar toolBar;
    private JSInternalConsole console;
    private JSplitPane split1;
    private JLabel statusBar;
    private final Map<String, JFrame> toplevels = Collections.synchronizedMap(new HashMap());
    private final Map<String, FileWindow> fileWindows = Collections.synchronizedMap(new HashMap());
    private FileWindow currentWindow;
    JFileChooser dlg;
    private EventQueue awtEventQueue;

    public SwingGui(Dim dim, String title) {
        super(title);
        this.dim = dim;
        this.init();
        dim.setGuiCallback(this);
    }

    public Menubar getMenubar() {
        return this.menubar;
    }

    public void setExitAction(Runnable r) {
        this.exitAction = r;
    }

    public JSInternalConsole getConsole() {
        return this.console;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            this.console.consoleTextArea.requestFocus();
            this.context.split.setDividerLocation(0.5);
            try {
                this.console.setMaximum(true);
                this.console.setSelected(true);
                this.console.show();
                this.console.consoleTextArea.requestFocus();
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }

    void addTopLevel(String key, JFrame frame) {
        if (frame != this) {
            this.toplevels.put(key, frame);
        }
    }

    private void init() {
        JButton stepOutButton;
        JButton stepOverButton;
        JButton stepIntoButton;
        JButton goButton;
        JButton breakButton;
        this.menubar = new Menubar(this);
        this.setJMenuBar(this.menubar);
        this.toolBar = new JToolBar();
        String[] toolTips = new String[]{"Break (Pause)", "Go (F5)", "Step Into (F11)", "Step Over (F7)", "Step Out (F8)"};
        int count = 0;
        JButton button = breakButton = new JButton("Break");
        button.setToolTipText("Break");
        button.setActionCommand("Break");
        button.addActionListener(this.menubar);
        button.setEnabled(true);
        button.setToolTipText(toolTips[count++]);
        button = goButton = new JButton("Go");
        button.setToolTipText("Go");
        button.setActionCommand("Go");
        button.addActionListener(this.menubar);
        button.setEnabled(false);
        button.setToolTipText(toolTips[count++]);
        button = stepIntoButton = new JButton("Step Into");
        button.setToolTipText("Step Into");
        button.setActionCommand("Step Into");
        button.addActionListener(this.menubar);
        button.setEnabled(false);
        button.setToolTipText(toolTips[count++]);
        button = stepOverButton = new JButton("Step Over");
        button.setToolTipText("Step Over");
        button.setActionCommand("Step Over");
        button.setEnabled(false);
        button.addActionListener(this.menubar);
        button.setToolTipText(toolTips[count++]);
        button = stepOutButton = new JButton("Step Out");
        button.setToolTipText("Step Out");
        button.setActionCommand("Step Out");
        button.setEnabled(false);
        button.addActionListener(this.menubar);
        button.setToolTipText(toolTips[count++]);
        Dimension dim = stepOverButton.getPreferredSize();
        breakButton.setPreferredSize(dim);
        breakButton.setMinimumSize(dim);
        breakButton.setMaximumSize(dim);
        breakButton.setSize(dim);
        goButton.setPreferredSize(dim);
        goButton.setMinimumSize(dim);
        goButton.setMaximumSize(dim);
        stepIntoButton.setPreferredSize(dim);
        stepIntoButton.setMinimumSize(dim);
        stepIntoButton.setMaximumSize(dim);
        stepOverButton.setPreferredSize(dim);
        stepOverButton.setMinimumSize(dim);
        stepOverButton.setMaximumSize(dim);
        stepOutButton.setPreferredSize(dim);
        stepOutButton.setMinimumSize(dim);
        stepOutButton.setMaximumSize(dim);
        this.toolBar.add(breakButton);
        this.toolBar.add(goButton);
        this.toolBar.add(stepIntoButton);
        this.toolBar.add(stepOverButton);
        this.toolBar.add(stepOutButton);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        this.getContentPane().add((Component)this.toolBar, "North");
        this.getContentPane().add((Component)contentPane, "Center");
        this.desk = new JDesktopPane();
        this.desk.setPreferredSize(new Dimension(600, 300));
        this.desk.setMinimumSize(new Dimension(150, 50));
        this.console = new JSInternalConsole("JavaScript Console");
        this.desk.add(this.console);
        this.context = new ContextWindow(this);
        this.context.setPreferredSize(new Dimension(600, 120));
        this.context.setMinimumSize(new Dimension(50, 50));
        this.split1 = new JSplitPane(0, this.desk, this.context);
        this.split1.setOneTouchExpandable(true);
        SwingGui.setResizeWeight(this.split1, 0.66);
        contentPane.add((Component)this.split1, "Center");
        this.statusBar = new JLabel();
        this.statusBar.setText("Thread: ");
        contentPane.add((Component)this.statusBar, "South");
        this.dlg = new JFileChooser();
        FileFilter filter = new FileFilter(){

            @Override
            public boolean accept(File f) {
                String ext;
                if (f.isDirectory()) {
                    return true;
                }
                String n = f.getName();
                int i = n.lastIndexOf(46);
                return i > 0 && i < n.length() - 1 && (ext = n.substring(i + 1).toLowerCase()).equals("js");
            }

            @Override
            public String getDescription() {
                return "JavaScript Files (*.js)";
            }
        };
        this.dlg.addChoosableFileFilter(filter);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SwingGui.this.exit();
            }
        });
    }

    private void exit() {
        if (this.exitAction != null) {
            SwingUtilities.invokeLater(this.exitAction);
        }
        this.dim.setReturnValue(5);
    }

    FileWindow getFileWindow(String url) {
        if (url == null || url.equals("<stdin>")) {
            return null;
        }
        return this.fileWindows.get(url);
    }

    static String getShortName(String url) {
        int lastSlash = url.lastIndexOf(47);
        if (lastSlash < 0) {
            lastSlash = url.lastIndexOf(92);
        }
        String shortName = url;
        if (lastSlash >= 0 && lastSlash + 1 < url.length()) {
            shortName = url.substring(lastSlash + 1);
        }
        return shortName;
    }

    void removeWindow(FileWindow w) {
        this.fileWindows.remove(w.getUrl());
        JMenu windowMenu = this.getWindowMenu();
        int count = windowMenu.getItemCount();
        JMenuItem lastItem = windowMenu.getItem(count - 1);
        String name = SwingGui.getShortName(w.getUrl());
        for (int i = 5; i < count; ++i) {
            int pos;
            String text;
            JMenuItem item = windowMenu.getItem(i);
            if (item == null || !(text = item.getText()).substring((pos = text.indexOf(32)) + 1).equals(name)) continue;
            windowMenu.remove(item);
            if (count == 6) {
                windowMenu.remove(4);
                break;
            }
            int j = i - 4;
            while (i < count - 1) {
                JMenuItem thisItem = windowMenu.getItem(i);
                if (thisItem != null) {
                    text = thisItem.getText();
                    if (text.equals("More Windows...")) break;
                    pos = text.indexOf(32);
                    thisItem.setText((char)(48 + j) + " " + text.substring(pos + 1));
                    thisItem.setMnemonic(48 + j);
                    ++j;
                }
                ++i;
            }
            if (count - 6 != 0 || lastItem == item || !lastItem.getText().equals("More Windows...")) break;
            windowMenu.remove(lastItem);
            break;
        }
        windowMenu.revalidate();
    }

    void showStopLine(Dim.StackFrame frame) {
        String sourceName = frame.getUrl();
        if (sourceName == null || sourceName.equals("<stdin>")) {
            if (this.console.isVisible()) {
                this.console.show();
            }
        } else {
            this.showFileWindow(sourceName, -1);
            int lineNumber = frame.getLineNumber();
            FileWindow w = this.getFileWindow(sourceName);
            if (w != null) {
                this.setFilePosition(w, lineNumber);
            }
        }
    }

    protected void showFileWindow(String sourceUrl, int lineNumber) {
        FileWindow w = this.getFileWindow(sourceUrl);
        if (w == null) {
            Dim.SourceInfo si = this.dim.sourceInfo(sourceUrl);
            this.createFileWindow(si, -1);
            w = this.getFileWindow(sourceUrl);
        }
        if (lineNumber > -1) {
            int start = w.getPosition(lineNumber - 1);
            int end = w.getPosition(lineNumber) - 1;
            w.textArea.select(start);
            w.textArea.setCaretPosition(start);
            w.textArea.moveCaretPosition(end);
        }
        try {
            if (w.isIcon()) {
                w.setIcon(false);
            }
            w.setVisible(true);
            w.moveToFront();
            w.setSelected(true);
            this.requestFocus();
            w.requestFocus();
            w.textArea.requestFocus();
        } catch (Exception exc) {
            // empty catch block
        }
    }

    protected void createFileWindow(Dim.SourceInfo sourceInfo, int line) {
        boolean activate = true;
        String url = sourceInfo.url();
        FileWindow w = new FileWindow(this, sourceInfo);
        this.fileWindows.put(url, w);
        if (line != -1) {
            if (this.currentWindow != null) {
                this.currentWindow.setPosition(-1);
            }
            try {
                w.setPosition(w.textArea.getLineStartOffset(line - 1));
            } catch (BadLocationException exc) {
                try {
                    w.setPosition(w.textArea.getLineStartOffset(0));
                } catch (BadLocationException ee) {
                    w.setPosition(-1);
                }
            }
        }
        this.desk.add(w);
        if (line != -1) {
            this.currentWindow = w;
        }
        this.menubar.addFile(url);
        w.setVisible(true);
        if (activate) {
            try {
                w.setMaximum(true);
                w.setSelected(true);
                w.moveToFront();
            } catch (Exception exc) {
                // empty catch block
            }
        }
    }

    protected boolean updateFileWindow(Dim.SourceInfo sourceInfo) {
        String fileName = sourceInfo.url();
        FileWindow w = this.getFileWindow(fileName);
        if (w != null) {
            w.updateText(sourceInfo);
            w.show();
            return true;
        }
        return false;
    }

    private void setFilePosition(FileWindow w, int line) {
        boolean activate = true;
        FileTextArea ta = w.textArea;
        try {
            if (line == -1) {
                w.setPosition(-1);
                if (this.currentWindow == w) {
                    this.currentWindow = null;
                }
            } else {
                int loc = ta.getLineStartOffset(line - 1);
                if (this.currentWindow != null && this.currentWindow != w) {
                    this.currentWindow.setPosition(-1);
                }
                w.setPosition(loc);
                this.currentWindow = w;
            }
        } catch (BadLocationException exc) {
            // empty catch block
        }
        if (activate) {
            if (w.isIcon()) {
                this.desk.getDesktopManager().deiconifyFrame(w);
            }
            this.desk.getDesktopManager().activateFrame(w);
            try {
                w.show();
                w.toFront();
                w.setSelected(true);
            } catch (Exception exc) {
                // empty catch block
            }
        }
    }

    void enterInterruptImpl(Dim.StackFrame lastFrame, String threadTitle, String alertMessage) {
        this.statusBar.setText("Thread: " + threadTitle);
        this.showStopLine(lastFrame);
        if (alertMessage != null) {
            MessageDialogWrapper.showMessageDialog(this, alertMessage, "Exception in Script", 0);
        }
        this.updateEnabled(true);
        Dim.ContextData contextData = lastFrame.contextData();
        JComboBox ctx = this.context.context;
        List<String> toolTips = this.context.toolTips;
        this.context.disableUpdate();
        int frameCount = contextData.frameCount();
        ctx.removeAllItems();
        ctx.setSelectedItem(null);
        toolTips.clear();
        for (int i = 0; i < frameCount; ++i) {
            Dim.StackFrame frame = contextData.getFrame(i);
            String url = frame.getUrl();
            int lineNumber = frame.getLineNumber();
            String shortName = url;
            if (url.length() > 20) {
                shortName = "..." + url.substring(url.length() - 17);
            }
            String location = "\"" + shortName + "\", line " + lineNumber;
            ctx.insertItemAt(location, i);
            location = "\"" + url + "\", line " + lineNumber;
            toolTips.add(location);
        }
        this.context.enableUpdate();
        ctx.setSelectedIndex(0);
        ctx.setMinimumSize(new Dimension(50, ctx.getMinimumSize().height));
    }

    private JMenu getWindowMenu() {
        return this.menubar.getMenu(3);
    }

    private String chooseFile(String title) {
        int returnVal;
        this.dlg.setDialogTitle(title);
        File CWD = null;
        String dir = SecurityUtilities.getSystemProperty("user.dir");
        if (dir != null) {
            CWD = new File(dir);
        }
        if (CWD != null) {
            this.dlg.setCurrentDirectory(CWD);
        }
        if ((returnVal = this.dlg.showOpenDialog(this)) == 0) {
            try {
                String result = this.dlg.getSelectedFile().getCanonicalPath();
                CWD = this.dlg.getSelectedFile().getParentFile();
                Properties props = System.getProperties();
                props.put("user.dir", CWD.getPath());
                System.setProperties(props);
                return result;
            } catch (IOException ignored) {
            } catch (SecurityException ignored) {
                // empty catch block
            }
        }
        return null;
    }

    private JInternalFrame getSelectedFrame() {
        JInternalFrame[] frames = this.desk.getAllFrames();
        for (int i = 0; i < frames.length; ++i) {
            if (!frames[i].isShowing()) continue;
            return frames[i];
        }
        return frames[frames.length - 1];
    }

    private void updateEnabled(boolean interrupted) {
        ((Menubar)this.getJMenuBar()).updateEnabled(interrupted);
        int cc = this.toolBar.getComponentCount();
        for (int ci = 0; ci < cc; ++ci) {
            boolean enableButton = ci == 0 ? !interrupted : interrupted;
            this.toolBar.getComponent(ci).setEnabled(enableButton);
        }
        if (interrupted) {
            this.toolBar.setEnabled(true);
            int state = this.getExtendedState();
            if (state == 1) {
                this.setExtendedState(0);
            }
            this.toFront();
            this.context.setEnabled(true);
        } else {
            if (this.currentWindow != null) {
                this.currentWindow.setPosition(-1);
            }
            this.context.setEnabled(false);
        }
    }

    static void setResizeWeight(JSplitPane pane, double weight) {
        try {
            Method m = JSplitPane.class.getMethod("setResizeWeight", Double.TYPE);
            m.invoke(pane, new Double(weight));
        } catch (NoSuchMethodException exc) {
        } catch (IllegalAccessException exc) {
        } catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String readFile(String fileName) {
        String text;
        try {
            FileReader r = new FileReader(fileName);
            try {
                text = Kit.readReader(r);
            } finally {
                ((Reader)r).close();
            }
        } catch (IOException ex) {
            MessageDialogWrapper.showMessageDialog(this, ex.getMessage(), "Error reading " + fileName, 0);
            text = null;
        }
        return text;
    }

    @Override
    public void updateSourceText(Dim.SourceInfo sourceInfo) {
        RunProxy proxy = new RunProxy(this, 3);
        proxy.sourceInfo = sourceInfo;
        SwingUtilities.invokeLater(proxy);
    }

    @Override
    public void enterInterrupt(Dim.StackFrame lastFrame, String threadTitle, String alertMessage) {
        if (SwingUtilities.isEventDispatchThread()) {
            this.enterInterruptImpl(lastFrame, threadTitle, alertMessage);
        } else {
            RunProxy proxy = new RunProxy(this, 4);
            proxy.lastFrame = lastFrame;
            proxy.threadTitle = threadTitle;
            proxy.alertMessage = alertMessage;
            SwingUtilities.invokeLater(proxy);
        }
    }

    @Override
    public boolean isGuiEventThread() {
        return SwingUtilities.isEventDispatchThread();
    }

    @Override
    public void dispatchNextGuiEvent() throws InterruptedException {
        AWTEvent event;
        EventQueue queue = this.awtEventQueue;
        if (queue == null) {
            this.awtEventQueue = queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        }
        if ((event = queue.getNextEvent()) instanceof ActiveEvent) {
            ((ActiveEvent)((Object)event)).dispatch();
        } else {
            Object source = event.getSource();
            if (source instanceof Component) {
                Component comp = (Component)source;
                comp.dispatchEvent(event);
            } else if (source instanceof MenuComponent) {
                ((MenuComponent)source).dispatchEvent(event);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int returnValue = -1;
        if (cmd.equals("Cut") || cmd.equals("Copy") || cmd.equals("Paste")) {
            JInternalFrame f = this.getSelectedFrame();
            if (f != null && f instanceof ActionListener) {
                ((ActionListener)((Object)f)).actionPerformed(e);
            }
        } else if (cmd.equals("Step Over")) {
            returnValue = 0;
        } else if (cmd.equals("Step Into")) {
            returnValue = 1;
        } else if (cmd.equals("Step Out")) {
            returnValue = 2;
        } else if (cmd.equals("Go")) {
            returnValue = 3;
        } else if (cmd.equals("Break")) {
            this.dim.setBreak();
        } else if (cmd.equals("Exit")) {
            this.exit();
        } else if (cmd.equals("Open")) {
            String text;
            String fileName = this.chooseFile("Select a file to compile");
            if (fileName != null && (text = this.readFile(fileName)) != null) {
                RunProxy proxy = new RunProxy(this, 1);
                proxy.fileName = fileName;
                proxy.text = text;
                new Thread(proxy).start();
            }
        } else if (cmd.equals("Load")) {
            String text;
            String fileName = this.chooseFile("Select a file to execute");
            if (fileName != null && (text = this.readFile(fileName)) != null) {
                RunProxy proxy = new RunProxy(this, 2);
                proxy.fileName = fileName;
                proxy.text = text;
                new Thread(proxy).start();
            }
        } else if (cmd.equals("More Windows...")) {
            MoreWindows dlg = new MoreWindows(this, this.fileWindows, "Window", "Files");
            dlg.showDialog(this);
        } else if (cmd.equals("Console")) {
            if (this.console.isIcon()) {
                this.desk.getDesktopManager().deiconifyFrame(this.console);
            }
            this.console.show();
            this.desk.getDesktopManager().activateFrame(this.console);
            this.console.consoleTextArea.requestFocus();
        } else if (!(cmd.equals("Cut") || cmd.equals("Copy") || cmd.equals("Paste"))) {
            if (cmd.equals("Go to function...")) {
                FindFunction dlg = new FindFunction(this, "Go to function", "Function");
                dlg.showDialog(this);
            } else if (cmd.equals("Tile")) {
                JInternalFrame[] frames = this.desk.getAllFrames();
                int count = frames.length;
                int cols = (int)Math.sqrt(count);
                int rows = cols;
                if (rows * cols < count && rows * ++cols < count) {
                    ++rows;
                }
                Dimension size = this.desk.getSize();
                int w = size.width / cols;
                int h = size.height / rows;
                int x = 0;
                int y = 0;
                for (int i = 0; i < rows; ++i) {
                    int index;
                    for (int j = 0; j < cols && (index = i * cols + j) < frames.length; ++j) {
                        JInternalFrame f = frames[index];
                        try {
                            f.setIcon(false);
                            f.setMaximum(false);
                        } catch (Exception exc) {
                            // empty catch block
                        }
                        this.desk.getDesktopManager().setBoundsForFrame(f, x, y, w, h);
                        x += w;
                    }
                    y += h;
                    x = 0;
                }
            } else if (cmd.equals("Cascade")) {
                JInternalFrame[] frames = this.desk.getAllFrames();
                int count = frames.length;
                int y = 0;
                int x = 0;
                int h = this.desk.getHeight();
                int d = h / count;
                if (d > 30) {
                    d = 30;
                }
                int i = count - 1;
                while (i >= 0) {
                    JInternalFrame f = frames[i];
                    try {
                        f.setIcon(false);
                        f.setMaximum(false);
                    } catch (Exception exc) {
                        // empty catch block
                    }
                    Dimension dimen = f.getPreferredSize();
                    int w = dimen.width;
                    h = dimen.height;
                    this.desk.getDesktopManager().setBoundsForFrame(f, x, y, w, h);
                    --i;
                    x += d;
                    y += d;
                }
            } else {
                FileWindow obj = this.getFileWindow(cmd);
                if (obj != null) {
                    FileWindow w = obj;
                    try {
                        if (w.isIcon()) {
                            w.setIcon(false);
                        }
                        w.setVisible(true);
                        w.moveToFront();
                        w.setSelected(true);
                    } catch (Exception exc) {
                        // empty catch block
                    }
                }
            }
        }
        if (returnValue != -1) {
            this.updateEnabled(false);
            this.dim.setReturnValue(returnValue);
        }
    }
}

