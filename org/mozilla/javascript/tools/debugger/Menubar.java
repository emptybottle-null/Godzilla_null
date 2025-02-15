/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.mozilla.javascript.tools.debugger.SwingGui;

class Menubar
extends JMenuBar
implements ActionListener {
    private static final long serialVersionUID = 3217170497245911461L;
    private List<JMenuItem> interruptOnlyItems = Collections.synchronizedList(new ArrayList());
    private List<JMenuItem> runOnlyItems = Collections.synchronizedList(new ArrayList());
    private SwingGui debugGui;
    private JMenu windowMenu;
    private JCheckBoxMenuItem breakOnExceptions;
    private JCheckBoxMenuItem breakOnEnter;
    private JCheckBoxMenuItem breakOnReturn;

    Menubar(SwingGui debugGui) {
        KeyStroke k;
        JMenuItem item;
        int i;
        this.debugGui = debugGui;
        String[] fileItems = new String[]{"Open...", "Run...", "", "Exit"};
        String[] fileCmds = new String[]{"Open", "Load", "", "Exit"};
        char[] fileShortCuts = new char[]{'0', 'N', '\u0000', 'X'};
        int[] fileAccelerators = new int[]{79, 78, 0, 81};
        String[] editItems = new String[]{"Cut", "Copy", "Paste", "Go to function..."};
        char[] editShortCuts = new char[]{'T', 'C', 'P', 'F'};
        String[] debugItems = new String[]{"Break", "Go", "Step Into", "Step Over", "Step Out"};
        char[] debugShortCuts = new char[]{'B', 'G', 'I', 'O', 'T'};
        String[] plafItems = new String[]{"Metal", "Windows", "Motif"};
        char[] plafShortCuts = new char[]{'M', 'W', 'F'};
        int[] debugAccelerators = new int[]{19, 116, 122, 118, 119, 0, 0};
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        JMenu plafMenu = new JMenu("Platform");
        plafMenu.setMnemonic('P');
        JMenu debugMenu = new JMenu("Debug");
        debugMenu.setMnemonic('D');
        this.windowMenu = new JMenu("Window");
        this.windowMenu.setMnemonic('W');
        for (i = 0; i < fileItems.length; ++i) {
            if (fileItems[i].length() == 0) {
                fileMenu.addSeparator();
                continue;
            }
            item = new JMenuItem(fileItems[i], fileShortCuts[i]);
            item.setActionCommand(fileCmds[i]);
            item.addActionListener(this);
            fileMenu.add(item);
            if (fileAccelerators[i] == 0) continue;
            k = KeyStroke.getKeyStroke(fileAccelerators[i], 2);
            item.setAccelerator(k);
        }
        for (i = 0; i < editItems.length; ++i) {
            item = new JMenuItem(editItems[i], editShortCuts[i]);
            item.addActionListener(this);
            editMenu.add(item);
        }
        for (i = 0; i < plafItems.length; ++i) {
            item = new JMenuItem(plafItems[i], plafShortCuts[i]);
            item.addActionListener(this);
            plafMenu.add(item);
        }
        for (i = 0; i < debugItems.length; ++i) {
            item = new JMenuItem(debugItems[i], debugShortCuts[i]);
            item.addActionListener(this);
            if (debugAccelerators[i] != 0) {
                k = KeyStroke.getKeyStroke(debugAccelerators[i], 0);
                item.setAccelerator(k);
            }
            if (i != 0) {
                this.interruptOnlyItems.add(item);
            } else {
                this.runOnlyItems.add(item);
            }
            debugMenu.add(item);
        }
        this.breakOnExceptions = new JCheckBoxMenuItem("Break on Exceptions");
        this.breakOnExceptions.setMnemonic('X');
        this.breakOnExceptions.addActionListener(this);
        this.breakOnExceptions.setSelected(false);
        debugMenu.add(this.breakOnExceptions);
        this.breakOnEnter = new JCheckBoxMenuItem("Break on Function Enter");
        this.breakOnEnter.setMnemonic('E');
        this.breakOnEnter.addActionListener(this);
        this.breakOnEnter.setSelected(false);
        debugMenu.add(this.breakOnEnter);
        this.breakOnReturn = new JCheckBoxMenuItem("Break on Function Return");
        this.breakOnReturn.setMnemonic('R');
        this.breakOnReturn.addActionListener(this);
        this.breakOnReturn.setSelected(false);
        debugMenu.add(this.breakOnReturn);
        this.add(fileMenu);
        this.add(editMenu);
        this.add(debugMenu);
        JMenuItem item2 = new JMenuItem("Cascade", 65);
        this.windowMenu.add(item2);
        item2.addActionListener(this);
        item2 = new JMenuItem("Tile", 84);
        this.windowMenu.add(item2);
        item2.addActionListener(this);
        this.windowMenu.addSeparator();
        item2 = new JMenuItem("Console", 67);
        this.windowMenu.add(item2);
        item2.addActionListener(this);
        this.add(this.windowMenu);
        this.updateEnabled(false);
    }

    public JCheckBoxMenuItem getBreakOnExceptions() {
        return this.breakOnExceptions;
    }

    public JCheckBoxMenuItem getBreakOnEnter() {
        return this.breakOnEnter;
    }

    public JCheckBoxMenuItem getBreakOnReturn() {
        return this.breakOnReturn;
    }

    public JMenu getDebugMenu() {
        return this.getMenu(2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String plaf_name = null;
        if (cmd.equals("Metal")) {
            plaf_name = "javax.swing.plaf.metal.MetalLookAndFeel";
        } else if (cmd.equals("Windows")) {
            plaf_name = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else if (cmd.equals("Motif")) {
            plaf_name = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        } else {
            Object source = e.getSource();
            if (source == this.breakOnExceptions) {
                this.debugGui.dim.setBreakOnExceptions(this.breakOnExceptions.isSelected());
            } else if (source == this.breakOnEnter) {
                this.debugGui.dim.setBreakOnEnter(this.breakOnEnter.isSelected());
            } else if (source == this.breakOnReturn) {
                this.debugGui.dim.setBreakOnReturn(this.breakOnReturn.isSelected());
            } else {
                this.debugGui.actionPerformed(e);
            }
            return;
        }
        try {
            UIManager.setLookAndFeel(plaf_name);
            SwingUtilities.updateComponentTreeUI(this.debugGui);
            SwingUtilities.updateComponentTreeUI(this.debugGui.dlg);
        } catch (Exception ignored) {
            // empty catch block
        }
    }

    public void addFile(String url) {
        JMenuItem item;
        int count = this.windowMenu.getItemCount();
        if (count == 4) {
            this.windowMenu.addSeparator();
            ++count;
        }
        JMenuItem lastItem = this.windowMenu.getItem(count - 1);
        boolean hasMoreWin = false;
        int maxWin = 5;
        if (lastItem != null && lastItem.getText().equals("More Windows...")) {
            hasMoreWin = true;
            ++maxWin;
        }
        if (!hasMoreWin && count - 4 == 5) {
            JMenuItem item2 = new JMenuItem("More Windows...", 77);
            this.windowMenu.add(item2);
            item2.setActionCommand("More Windows...");
            item2.addActionListener(this);
            return;
        }
        if (count - 4 <= maxWin) {
            if (hasMoreWin) {
                --count;
                this.windowMenu.remove(lastItem);
            }
            String shortName = SwingGui.getShortName(url);
            item = new JMenuItem((char)(48 + (count - 4)) + " " + shortName, 48 + (count - 4));
            this.windowMenu.add(item);
            if (hasMoreWin) {
                this.windowMenu.add(lastItem);
            }
        } else {
            return;
        }
        item.setActionCommand(url);
        item.addActionListener(this);
    }

    public void updateEnabled(boolean interrupted) {
        JMenuItem item;
        int i;
        for (i = 0; i != this.interruptOnlyItems.size(); ++i) {
            item = this.interruptOnlyItems.get(i);
            item.setEnabled(interrupted);
        }
        for (i = 0; i != this.runOnlyItems.size(); ++i) {
            item = this.runOnlyItems.get(i);
            item.setEnabled(!interrupted);
        }
    }
}

