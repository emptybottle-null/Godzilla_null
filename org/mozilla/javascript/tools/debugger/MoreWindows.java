/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.mozilla.javascript.tools.debugger.FileWindow;
import org.mozilla.javascript.tools.debugger.SwingGui;

class MoreWindows
extends JDialog
implements ActionListener {
    private static final long serialVersionUID = 5177066296457377546L;
    private String value;
    private JList list;
    private SwingGui swingGui;
    private JButton setButton;
    private JButton cancelButton;

    MoreWindows(SwingGui frame, Map<String, FileWindow> fileWindows, String title, String labelText) {
        super(frame, title, true);
        this.swingGui = frame;
        this.cancelButton = new JButton("Cancel");
        this.setButton = new JButton("Select");
        this.cancelButton.addActionListener(this);
        this.setButton.addActionListener(this);
        this.getRootPane().setDefaultButton(this.setButton);
        this.list = new JList(new DefaultListModel());
        DefaultListModel model = (DefaultListModel)this.list.getModel();
        model.clear();
        for (String data : fileWindows.keySet()) {
            model.addElement(data);
        }
        this.list.setSelectedIndex(0);
        this.setButton.setEnabled(true);
        this.list.setSelectionMode(1);
        this.list.addMouseListener(new MouseHandler());
        JScrollPane listScroller = new JScrollPane(this.list);
        listScroller.setPreferredSize(new Dimension(320, 240));
        listScroller.setMinimumSize(new Dimension(250, 80));
        listScroller.setAlignmentX(0.0f);
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, 1));
        JLabel label = new JLabel(labelText);
        label.setLabelFor(this.list);
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));
        listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, 0));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(this.cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(this.setButton);
        Container contentPane = this.getContentPane();
        contentPane.add((Component)listPane, "Center");
        contentPane.add((Component)buttonPane, "South");
        this.pack();
        this.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent ke) {
                int code = ke.getKeyCode();
                if (code == 27) {
                    ke.consume();
                    MoreWindows.this.value = null;
                    MoreWindows.this.setVisible(false);
                }
            }
        });
    }

    public String showDialog(Component comp) {
        this.value = null;
        this.setLocationRelativeTo(comp);
        this.setVisible(true);
        return this.value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Cancel")) {
            this.setVisible(false);
            this.value = null;
        } else if (cmd.equals("Select")) {
            this.value = (String)this.list.getSelectedValue();
            this.setVisible(false);
            this.swingGui.showFileWindow(this.value, -1);
        }
    }

    private class MouseHandler
    extends MouseAdapter {
        private MouseHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                MoreWindows.this.setButton.doClick();
            }
        }
    }
}

