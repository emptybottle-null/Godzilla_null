/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.EvalTextArea;
import org.mozilla.javascript.tools.debugger.Evaluator;
import org.mozilla.javascript.tools.debugger.MyTableModel;
import org.mozilla.javascript.tools.debugger.MyTreeTable;
import org.mozilla.javascript.tools.debugger.SwingGui;
import org.mozilla.javascript.tools.debugger.VariableModel;

class ContextWindow
extends JPanel
implements ActionListener {
    private static final long serialVersionUID = 2306040975490228051L;
    private SwingGui debugGui;
    JComboBox context;
    List<String> toolTips;
    private JTabbedPane tabs;
    private JTabbedPane tabs2;
    private MyTreeTable thisTable;
    private MyTreeTable localsTable;
    private MyTableModel tableModel;
    private Evaluator evaluator;
    private EvalTextArea cmdLine;
    JSplitPane split;
    private boolean enabled;

    public ContextWindow(final SwingGui debugGui) {
        this.debugGui = debugGui;
        this.enabled = false;
        JPanel left = new JPanel();
        JToolBar t1 = new JToolBar();
        t1.setName("Variables");
        t1.setLayout(new GridLayout());
        t1.add(left);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
        p1.add(t1);
        JLabel label = new JLabel("Context:");
        this.context = new JComboBox();
        this.context.setLightWeightPopupEnabled(false);
        this.toolTips = Collections.synchronizedList(new ArrayList());
        label.setBorder(this.context.getBorder());
        this.context.addActionListener(this);
        this.context.setActionCommand("ContextSwitch");
        GridBagLayout layout = new GridBagLayout();
        left.setLayout(layout);
        GridBagConstraints lc = new GridBagConstraints();
        lc.insets.left = 5;
        lc.anchor = 17;
        lc.ipadx = 5;
        layout.setConstraints(label, lc);
        left.add(label);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 0;
        c.fill = 2;
        c.anchor = 17;
        layout.setConstraints(this.context, c);
        left.add(this.context);
        this.tabs = new JTabbedPane(3);
        this.tabs.setPreferredSize(new Dimension(500, 300));
        this.thisTable = new MyTreeTable(new VariableModel());
        JScrollPane jsp = new JScrollPane(this.thisTable);
        jsp.getViewport().setViewSize(new Dimension(5, 2));
        this.tabs.add("this", jsp);
        this.localsTable = new MyTreeTable(new VariableModel());
        this.localsTable.setAutoResizeMode(4);
        this.localsTable.setPreferredSize(null);
        jsp = new JScrollPane(this.localsTable);
        this.tabs.add("Locals", jsp);
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.gridheight = 0;
        c.fill = 1;
        c.anchor = 17;
        layout.setConstraints(this.tabs, c);
        left.add(this.tabs);
        this.evaluator = new Evaluator(debugGui);
        this.cmdLine = new EvalTextArea(debugGui);
        this.tableModel = this.evaluator.tableModel;
        jsp = new JScrollPane(this.evaluator);
        JToolBar t2 = new JToolBar();
        t2.setName("Evaluate");
        this.tabs2 = new JTabbedPane(3);
        this.tabs2.add("Watch", jsp);
        this.tabs2.add("Evaluate", new JScrollPane(this.cmdLine));
        this.tabs2.setPreferredSize(new Dimension(500, 300));
        t2.setLayout(new GridLayout());
        t2.add(this.tabs2);
        p2.add(t2);
        this.evaluator.setAutoResizeMode(4);
        this.split = new JSplitPane(1, p1, p2);
        this.split.setOneTouchExpandable(true);
        SwingGui.setResizeWeight(this.split, 0.5);
        this.setLayout(new BorderLayout());
        this.add((Component)this.split, "Center");
        final JToolBar finalT1 = t1;
        final JToolBar finalT2 = t2;
        final JPanel finalP1 = p1;
        final JPanel finalP2 = p2;
        final JSplitPane finalSplit = this.split;
        final ContextWindow finalThis = this;
        ComponentListener clistener = new ComponentListener(){
            boolean t2Docked = true;

            void check(Component comp) {
                JFrame frame;
                Container thisParent = finalThis.getParent();
                if (thisParent == null) {
                    return;
                }
                Container parent = finalT1.getParent();
                boolean leftDocked = true;
                boolean rightDocked = true;
                boolean adjustVerticalSplit = false;
                if (parent != null) {
                    if (parent != finalP1) {
                        while (!(parent instanceof JFrame)) {
                            parent = parent.getParent();
                        }
                        frame = (JFrame)parent;
                        debugGui.addTopLevel("Variables", frame);
                        if (!frame.isResizable()) {
                            frame.setResizable(true);
                            frame.setDefaultCloseOperation(0);
                            final WindowListener[] l = (WindowListener[])frame.getListeners(WindowListener.class);
                            frame.removeWindowListener(l[0]);
                            frame.addWindowListener(new WindowAdapter(){

                                @Override
                                public void windowClosing(WindowEvent e) {
                                    ContextWindow.this.context.hidePopup();
                                    l[0].windowClosing(e);
                                }
                            });
                        }
                        leftDocked = false;
                    } else {
                        leftDocked = true;
                    }
                }
                if ((parent = finalT2.getParent()) != null) {
                    if (parent != finalP2) {
                        while (!(parent instanceof JFrame)) {
                            parent = parent.getParent();
                        }
                        frame = (JFrame)parent;
                        debugGui.addTopLevel("Evaluate", frame);
                        frame.setResizable(true);
                        rightDocked = false;
                    } else {
                        rightDocked = true;
                    }
                }
                if (leftDocked && this.t2Docked && rightDocked && this.t2Docked) {
                    return;
                }
                this.t2Docked = rightDocked;
                JSplitPane split = (JSplitPane)thisParent;
                if (leftDocked) {
                    if (rightDocked) {
                        finalSplit.setDividerLocation(0.5);
                    } else {
                        finalSplit.setDividerLocation(1.0);
                    }
                    if (adjustVerticalSplit) {
                        split.setDividerLocation(0.66);
                    }
                } else if (rightDocked) {
                    finalSplit.setDividerLocation(0.0);
                    split.setDividerLocation(0.66);
                } else {
                    split.setDividerLocation(1.0);
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                this.check(e.getComponent());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                this.check(e.getComponent());
            }

            @Override
            public void componentResized(ComponentEvent e) {
                this.check(e.getComponent());
            }

            @Override
            public void componentShown(ComponentEvent e) {
                this.check(e.getComponent());
            }
        };
        p1.addContainerListener(new ContainerListener(){

            @Override
            public void componentAdded(ContainerEvent e) {
                Container thisParent = finalThis.getParent();
                JSplitPane split = (JSplitPane)thisParent;
                if (e.getChild() == finalT1) {
                    if (finalT2.getParent() == finalP2) {
                        finalSplit.setDividerLocation(0.5);
                    } else {
                        finalSplit.setDividerLocation(1.0);
                    }
                    split.setDividerLocation(0.66);
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                Container thisParent = finalThis.getParent();
                JSplitPane split = (JSplitPane)thisParent;
                if (e.getChild() == finalT1) {
                    if (finalT2.getParent() == finalP2) {
                        finalSplit.setDividerLocation(0.0);
                        split.setDividerLocation(0.66);
                    } else {
                        split.setDividerLocation(1.0);
                    }
                }
            }
        });
        t1.addComponentListener(clistener);
        t2.addComponentListener(clistener);
        this.setEnabled(false);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.context.setEnabled(enabled);
        this.thisTable.setEnabled(enabled);
        this.localsTable.setEnabled(enabled);
        this.evaluator.setEnabled(enabled);
        this.cmdLine.setEnabled(enabled);
    }

    public void disableUpdate() {
        this.enabled = false;
    }

    public void enableUpdate() {
        this.enabled = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.enabled) {
            return;
        }
        if (e.getActionCommand().equals("ContextSwitch")) {
            Dim.ContextData contextData = this.debugGui.dim.currentContextData();
            if (contextData == null) {
                return;
            }
            int frameIndex = this.context.getSelectedIndex();
            this.context.setToolTipText(this.toolTips.get(frameIndex));
            int frameCount = contextData.frameCount();
            if (frameIndex >= frameCount) {
                return;
            }
            Dim.StackFrame frame = contextData.getFrame(frameIndex);
            Object scope = frame.scope();
            Object thisObj = frame.thisObj();
            this.thisTable.resetTree(new VariableModel(this.debugGui.dim, thisObj));
            VariableModel scopeModel = scope != thisObj ? new VariableModel(this.debugGui.dim, scope) : new VariableModel();
            this.localsTable.resetTree(scopeModel);
            this.debugGui.dim.contextSwitch(frameIndex);
            this.debugGui.showStopLine(frame);
            this.tableModel.updateModel();
        }
    }
}

