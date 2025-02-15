/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.focusabletip.TipUtil;

public class MatchedBracketPopup
extends JWindow {
    public static final String PROPERTY_CONSIDER_TEXTAREA_BACKGROUND = "rsta.matchedBracket.considerTextAreaBackground";
    private RSyntaxTextArea textArea;
    private transient Listener listener;
    private static final int LEFT_EMPTY_BORDER = 5;
    private static final boolean CONSIDER_TEXTAREA_BG = Boolean.getBoolean("rsta.matchedBracket.considerTextAreaBackground");

    MatchedBracketPopup(Window parent, RSyntaxTextArea textArea, int offsToRender) {
        super(parent);
        this.textArea = textArea;
        JPanel cp = new JPanel(new BorderLayout());
        RSyntaxTextArea toolTipParam = CONSIDER_TEXTAREA_BG ? textArea : null;
        cp.setBorder(BorderFactory.createCompoundBorder(TipUtil.getToolTipBorder(toolTipParam), BorderFactory.createEmptyBorder(2, 5, 5, 5)));
        cp.setBackground(TipUtil.getToolTipBackground(toolTipParam));
        this.setContentPane(cp);
        cp.add(new JLabel(this.getText(offsToRender)));
        this.installKeyBindings();
        this.listener = new Listener();
        this.setLocation();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (size != null) {
            size.width = Math.min(size.width, 800);
        }
        return size;
    }

    private String getText(int offsToRender) {
        int line = 0;
        try {
            line = this.textArea.getLineOfOffset(offsToRender);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            return null;
        }
        int lastLine = line + 1;
        if (line > 0) {
            try {
                int startOffs = this.textArea.getLineStartOffset(line);
                int length = this.textArea.getLineEndOffset(line) - startOffs;
                String text = this.textArea.getText(startOffs, length);
                if (text.trim().length() == 1) {
                    --line;
                }
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(this.textArea);
                ble.printStackTrace();
            }
        }
        Font font = this.textArea.getFontForTokenType(20);
        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<style>body { font-size:\"").append(font.getSize());
        sb.append("pt\" }</style><nobr>");
        while (line < lastLine) {
            for (Token t = this.textArea.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                t.appendHTMLRepresentation(sb, this.textArea, true, true);
            }
            sb.append("<br>");
            ++line;
        }
        return sb.toString();
    }

    private void installKeyBindings() {
        InputMap im = this.getRootPane().getInputMap(1);
        ActionMap am = this.getRootPane().getActionMap();
        KeyStroke escapeKS = KeyStroke.getKeyStroke(27, 0);
        im.put(escapeKS, "onEscape");
        am.put("onEscape", new EscapeAction());
    }

    private void setLocation() {
        Point topLeft = this.textArea.getVisibleRect().getLocation();
        SwingUtilities.convertPointToScreen(topLeft, this.textArea);
        topLeft.y = Math.max(topLeft.y - 24, 0);
        this.setLocation(topLeft.x - 5, topLeft.y);
    }

    private class Listener
    extends WindowAdapter
    implements ComponentListener {
        Listener() {
            MatchedBracketPopup.this.addWindowFocusListener(this);
            Window parent = (Window)MatchedBracketPopup.this.getParent();
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
            this.uninstallAndHide();
        }

        @Override
        public void windowIconified(WindowEvent e) {
            this.checkForParentWindowEvent(e);
        }

        private boolean checkForParentWindowEvent(WindowEvent e) {
            if (e.getSource() == MatchedBracketPopup.this.getParent()) {
                this.uninstallAndHide();
                return true;
            }
            return false;
        }

        private void uninstallAndHide() {
            Window parent = (Window)MatchedBracketPopup.this.getParent();
            parent.removeWindowFocusListener(this);
            parent.removeWindowListener(this);
            parent.removeComponentListener(this);
            MatchedBracketPopup.this.removeWindowFocusListener(this);
            MatchedBracketPopup.this.setVisible(false);
            MatchedBracketPopup.this.dispose();
        }
    }

    private class EscapeAction
    extends AbstractAction {
        private EscapeAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MatchedBracketPopup.this.listener.uninstallAndHide();
        }
    }
}

