/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.Caret;
import org.fife.ui.rsyntaxtextarea.focusabletip.TipUtil;
import org.fife.ui.rtextarea.ClipboardHistory;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.fife.ui.rtextarea.RTextArea;

class ClipboardHistoryPopup
extends JWindow {
    private RTextArea textArea;
    private ChoiceList list;
    private transient Listener listener;
    private boolean prevCaretAlwaysVisible;
    private static final int VERTICAL_SPACE = 1;
    private static final String MSG = "org.fife.ui.rtextarea.RTextArea";

    ClipboardHistoryPopup(Window parent, RTextArea textArea) {
        super(parent);
        this.textArea = textArea;
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(BorderFactory.createCompoundBorder(TipUtil.getToolTipBorder(), BorderFactory.createEmptyBorder(2, 5, 5, 5)));
        cp.setBackground(TipUtil.getToolTipBackground());
        this.setContentPane(cp);
        ResourceBundle msg = ResourceBundle.getBundle(MSG);
        JLabel title = new JLabel(msg.getString("Action.ClipboardHistory.Popup.Label"));
        cp.add((Component)title, "North");
        this.list = new ChoiceList();
        JScrollPane sp = new JScrollPane(this.list);
        sp.setHorizontalScrollBarPolicy(31);
        cp.add(sp);
        this.installKeyBindings();
        this.listener = new Listener();
        this.setLocation();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (size != null) {
            size.width = Math.min(size.width, 300);
            size.width = Math.max(size.width, 200);
        }
        return size;
    }

    private void insertSelectedItem() {
        LabelValuePair lvp = (LabelValuePair)this.list.getSelectedValue();
        if (lvp != null) {
            this.listener.uninstallAndHide();
            String text = lvp.value;
            this.textArea.replaceSelection(text);
            ClipboardHistory.get().add(text);
        }
    }

    private void installKeyBindings() {
        InputMap im = this.getRootPane().getInputMap(1);
        ActionMap am = this.getRootPane().getActionMap();
        KeyStroke escapeKS = KeyStroke.getKeyStroke(27, 0);
        im.put(escapeKS, "onEscape");
        am.put("onEscape", new EscapeAction());
        this.list.getInputMap().remove(escapeKS);
    }

    public void setContents(List<String> contents) {
        this.list.setContents(contents);
        this.pack();
    }

    private void setLocation() {
        Rectangle r;
        try {
            r = this.textArea.modelToView(this.textArea.getCaretPosition());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Point p = r.getLocation();
        SwingUtilities.convertPointToScreen(p, this.textArea);
        r.x = p.x;
        r.y = p.y;
        Rectangle screenBounds = TipUtil.getScreenBoundsForPoint(r.x, r.y);
        int totalH = this.getHeight();
        int y = r.y + r.height + 1;
        if (y + totalH > screenBounds.height) {
            y = r.y - 1 - this.getHeight();
        }
        int x = r.x;
        if (!this.textArea.getComponentOrientation().isLeftToRight()) {
            x -= this.getWidth();
        }
        if (x < screenBounds.x) {
            x = screenBounds.x;
        } else if (x + this.getWidth() > screenBounds.x + screenBounds.width) {
            x = screenBounds.x + screenBounds.width - this.getWidth();
        }
        this.setLocation(x, y);
    }

    @Override
    public void setVisible(boolean visible) {
        if (this.list.getModel().getSize() == 0) {
            UIManager.getLookAndFeel().provideErrorFeedback(this.textArea);
            return;
        }
        super.setVisible(visible);
        this.updateTextAreaCaret(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> {
                this.requestFocus();
                if (this.list.getModel().getSize() > 0) {
                    this.list.setSelectedIndex(0);
                }
                this.list.requestFocusInWindow();
            });
        }
    }

    private void updateTextAreaCaret(boolean visible) {
        Caret caret = this.textArea.getCaret();
        if (caret instanceof ConfigurableCaret) {
            ConfigurableCaret cc = (ConfigurableCaret)caret;
            if (visible) {
                this.prevCaretAlwaysVisible = cc.isAlwaysVisible();
                cc.setAlwaysVisible(true);
            } else {
                cc.setAlwaysVisible(this.prevCaretAlwaysVisible);
            }
        }
    }

    private static class LabelValuePair {
        private String label;
        private String value;
        private static final int LABEL_MAX_LENGTH = 50;

        LabelValuePair(String value) {
            this.label = this.value = value;
            int newline = this.label.indexOf(10);
            boolean multiLine = false;
            if (newline > -1) {
                this.label = this.label.substring(0, newline);
                multiLine = true;
            }
            if (this.label.length() > 50) {
                this.label = this.label.substring(0, 50) + "...";
            } else if (multiLine) {
                int toRemove = 3 - (50 - this.label.length());
                if (toRemove > 0) {
                    this.label = this.label.substring(0, this.label.length() - toRemove);
                }
                this.label = this.label + "...";
            }
        }

        public String toString() {
            return this.label;
        }
    }

    private static final class ChoiceList
    extends JList<LabelValuePair> {
        private ChoiceList() {
            super(new DefaultListModel());
            this.setSelectionMode(0);
            this.installKeyboardActions();
        }

        private void installKeyboardActions() {
            InputMap im = this.getInputMap();
            ActionMap am = this.getActionMap();
            im.put(KeyStroke.getKeyStroke(40, 0), "onDown");
            am.put("onDown", new AbstractAction(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = (this.getSelectedIndex() + 1) % this.getModel().getSize();
                    this.ensureIndexIsVisible(index);
                    this.setSelectedIndex(index);
                }
            });
            im.put(KeyStroke.getKeyStroke(38, 0), "onUp");
            am.put("onUp", new AbstractAction(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = this.getSelectedIndex() - 1;
                    if (index < 0) {
                        index += this.getModel().getSize();
                    }
                    this.ensureIndexIsVisible(index);
                    this.setSelectedIndex(index);
                }
            });
        }

        private void setContents(List<String> contents) {
            DefaultListModel model = (DefaultListModel)this.getModel();
            model.clear();
            for (String str : contents) {
                model.addElement(new LabelValuePair(str));
            }
            this.setVisibleRowCount(Math.min(model.getSize(), 8));
        }
    }

    private class Listener
    extends WindowAdapter
    implements ComponentListener {
        Listener() {
            ClipboardHistoryPopup.this.addWindowFocusListener(this);
            ClipboardHistoryPopup.this.list.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        ClipboardHistoryPopup.this.insertSelectedItem();
                    }
                }
            });
            ClipboardHistoryPopup.this.list.getInputMap().put(KeyStroke.getKeyStroke(10, 0), "onEnter");
            ClipboardHistoryPopup.this.list.getActionMap().put("onEnter", new AbstractAction(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    ClipboardHistoryPopup.this.insertSelectedItem();
                }
            });
            Window parent = (Window)ClipboardHistoryPopup.this.getParent();
            parent.addWindowFocusListener(this);
            parent.addWindowListener(this);
            parent.addComponentListener(this);
        }

        @Override
        public void componentResized(ComponentEvent e) {
            this.uninstallAndHide();
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            this.uninstallAndHide();
        }

        @Override
        public void componentShown(ComponentEvent e) {
            this.uninstallAndHide();
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            this.uninstallAndHide();
        }

        @Override
        public void windowActivated(WindowEvent e) {
            this.checkForParentWindowEvent(e);
        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            if (e.getSource() == ClipboardHistoryPopup.this) {
                this.uninstallAndHide();
            }
        }

        @Override
        public void windowIconified(WindowEvent e) {
            this.checkForParentWindowEvent(e);
        }

        private boolean checkForParentWindowEvent(WindowEvent e) {
            if (e.getSource() == ClipboardHistoryPopup.this.getParent()) {
                this.uninstallAndHide();
                return true;
            }
            return false;
        }

        private void uninstallAndHide() {
            Window parent = (Window)ClipboardHistoryPopup.this.getParent();
            parent.removeWindowFocusListener(this);
            parent.removeWindowListener(this);
            parent.removeComponentListener(this);
            ClipboardHistoryPopup.this.removeWindowFocusListener(this);
            ClipboardHistoryPopup.this.setVisible(false);
            ClipboardHistoryPopup.this.dispose();
        }
    }

    private class EscapeAction
    extends AbstractAction {
        private EscapeAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ClipboardHistoryPopup.this.listener.uninstallAndHide();
        }
    }
}

