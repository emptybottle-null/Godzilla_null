/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.tools.shell.ConsoleTextArea;
import org.mozilla.javascript.tools.shell.Main;

public class JSConsole
extends JFrame
implements ActionListener {
    static final long serialVersionUID = 2551225560631876300L;
    private File CWD;
    private JFileChooser dlg;
    private ConsoleTextArea consoleTextArea;

    public String chooseFile() {
        String dir;
        if (this.CWD == null && (dir = SecurityUtilities.getSystemProperty("user.dir")) != null) {
            this.CWD = new File(dir);
        }
        if (this.CWD != null) {
            this.dlg.setCurrentDirectory(this.CWD);
        }
        this.dlg.setDialogTitle("Select a file to load");
        int returnVal = this.dlg.showOpenDialog(this);
        if (returnVal == 0) {
            String result = this.dlg.getSelectedFile().getPath();
            this.CWD = new File(this.dlg.getSelectedFile().getParent());
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        new JSConsole(args);
    }

    public void createFileChooser() {
        this.dlg = new JFileChooser();
        FileFilter filter = new FileFilter(){

            @Override
            public boolean accept(File f) {
                String ext;
                if (f.isDirectory()) {
                    return true;
                }
                String name = f.getName();
                int i = name.lastIndexOf(46);
                return i > 0 && i < name.length() - 1 && (ext = name.substring(i + 1).toLowerCase()).equals("js");
            }

            @Override
            public String getDescription() {
                return "JavaScript Files (*.js)";
            }
        };
        this.dlg.addChoosableFileFilter(filter);
    }

    public JSConsole(String[] args) {
        super("Rhino JavaScript Console");
        JMenuItem item;
        int i;
        JMenuBar menubar = new JMenuBar();
        this.createFileChooser();
        String[] fileItems = new String[]{"Load...", "Exit"};
        String[] fileCmds = new String[]{"Load", "Exit"};
        char[] fileShortCuts = new char[]{'L', 'X'};
        String[] editItems = new String[]{"Cut", "Copy", "Paste"};
        char[] editShortCuts = new char[]{'T', 'C', 'P'};
        String[] plafItems = new String[]{"Metal", "Windows", "Motif"};
        boolean[] plafState = new boolean[]{true, false, false};
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        JMenu plafMenu = new JMenu("Platform");
        plafMenu.setMnemonic('P');
        for (i = 0; i < fileItems.length; ++i) {
            item = new JMenuItem(fileItems[i], fileShortCuts[i]);
            item.setActionCommand(fileCmds[i]);
            item.addActionListener(this);
            fileMenu.add(item);
        }
        for (i = 0; i < editItems.length; ++i) {
            item = new JMenuItem(editItems[i], editShortCuts[i]);
            item.addActionListener(this);
            editMenu.add(item);
        }
        ButtonGroup group = new ButtonGroup();
        for (int i2 = 0; i2 < plafItems.length; ++i2) {
            JRadioButtonMenuItem item2 = new JRadioButtonMenuItem(plafItems[i2], plafState[i2]);
            group.add(item2);
            item2.addActionListener(this);
            plafMenu.add(item2);
        }
        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(plafMenu);
        this.setJMenuBar(menubar);
        this.consoleTextArea = new ConsoleTextArea(args);
        JScrollPane scroller = new JScrollPane(this.consoleTextArea);
        this.setContentPane(scroller);
        this.consoleTextArea.setRows(24);
        this.consoleTextArea.setColumns(80);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.pack();
        this.setVisible(true);
        Main.setIn(this.consoleTextArea.getIn());
        Main.setOut(this.consoleTextArea.getOut());
        Main.setErr(this.consoleTextArea.getErr());
        Main.main(args);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String plaf_name = null;
        if (cmd.equals("Load")) {
            String f = this.chooseFile();
            if (f != null) {
                f = f.replace('\\', '/');
                this.consoleTextArea.eval("load(\"" + f + "\");");
            }
        } else if (cmd.equals("Exit")) {
            System.exit(0);
        } else if (cmd.equals("Cut")) {
            this.consoleTextArea.cut();
        } else if (cmd.equals("Copy")) {
            this.consoleTextArea.copy();
        } else if (cmd.equals("Paste")) {
            this.consoleTextArea.paste();
        } else {
            if (cmd.equals("Metal")) {
                plaf_name = "javax.swing.plaf.metal.MetalLookAndFeel";
            } else if (cmd.equals("Windows")) {
                plaf_name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            } else if (cmd.equals("Motif")) {
                plaf_name = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }
            if (plaf_name != null) {
                try {
                    UIManager.setLookAndFeel(plaf_name);
                    SwingUtilities.updateComponentTreeUI(this);
                    this.consoleTextArea.postUpdateUI();
                    this.createFileChooser();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(this, exc.getMessage(), "Platform", 0);
                }
            }
        }
    }
}

