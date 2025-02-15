/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.focusabletip;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.MouseInputAdapter;
import org.fife.ui.rsyntaxtextarea.PopupWindowDecorator;
import org.fife.ui.rsyntaxtextarea.focusabletip.TipUtil;
import org.fife.ui.rsyntaxtextarea.focusabletip.TipWindow;

public class FocusableTip {
    private JTextArea textArea;
    private TipWindow tipWindow;
    private URL imageBase;
    private TextAreaListener textAreaListener;
    private HyperlinkListener hyperlinkListener;
    private String lastText;
    private Dimension maxSize;
    private Rectangle tipVisibleBounds;
    private static final int X_MARGIN = 18;
    private static final int Y_MARGIN = 12;
    private static final ResourceBundle MSG = ResourceBundle.getBundle("org.fife.ui.rsyntaxtextarea.focusabletip.FocusableTip");

    public FocusableTip(JTextArea textArea, HyperlinkListener listener) {
        this.setTextArea(textArea);
        this.hyperlinkListener = listener;
        this.textAreaListener = new TextAreaListener();
        this.tipVisibleBounds = new Rectangle();
    }

    private void computeTipVisibleBounds() {
        Rectangle r = this.tipWindow.getBounds();
        Point p = r.getLocation();
        SwingUtilities.convertPointFromScreen(p, this.textArea);
        r.setLocation(p);
        this.tipVisibleBounds.setBounds(r.x, r.y - 15, r.width, r.height + 30);
    }

    private void createAndShowTipWindow(MouseEvent e, String text) {
        Window owner = SwingUtilities.getWindowAncestor(this.textArea);
        this.tipWindow = new TipWindow(owner, this, text);
        this.tipWindow.setHyperlinkListener(this.hyperlinkListener);
        PopupWindowDecorator decorator = PopupWindowDecorator.get();
        if (decorator != null) {
            decorator.decorate(this.tipWindow);
        }
        SwingUtilities.invokeLater(() -> {
            if (this.tipWindow == null) {
                return;
            }
            this.tipWindow.fixSize();
            ComponentOrientation o = this.textArea.getComponentOrientation();
            Point p = e.getPoint();
            SwingUtilities.convertPointToScreen(p, this.textArea);
            Rectangle sb = TipUtil.getScreenBoundsForPoint(p.x, p.y);
            int y = p.y + 12;
            if (y + this.tipWindow.getHeight() >= sb.y + sb.height && (y = p.y - 12 - this.tipWindow.getHeight()) < sb.y) {
                y = sb.y + 12;
            }
            int x = p.x - 18;
            if (!o.isLeftToRight()) {
                x = p.x - this.tipWindow.getWidth() + 18;
            }
            if (x < sb.x) {
                x = sb.x;
            } else if (x + this.tipWindow.getWidth() > sb.x + sb.width) {
                x = sb.x + sb.width - this.tipWindow.getWidth();
            }
            this.tipWindow.setLocation(x, y);
            this.tipWindow.setVisible(true);
            this.computeTipVisibleBounds();
            this.textAreaListener.install(this.textArea);
            this.lastText = text;
        });
    }

    public URL getImageBase() {
        return this.imageBase;
    }

    public Dimension getMaxSize() {
        return this.maxSize;
    }

    static String getString(String key) {
        return MSG.getString(key);
    }

    public void possiblyDisposeOfTipWindow() {
        if (this.tipWindow != null) {
            this.tipWindow.dispose();
            this.tipWindow = null;
            this.textAreaListener.uninstall();
            this.tipVisibleBounds.setBounds(-1, -1, 0, 0);
            this.lastText = null;
            this.textArea.requestFocus();
        }
    }

    void removeListeners() {
        this.textAreaListener.uninstall();
    }

    public void setImageBase(URL url) {
        this.imageBase = url;
    }

    public void setMaxSize(Dimension maxSize) {
        this.maxSize = maxSize;
    }

    private void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
        ToolTipManager.sharedInstance().registerComponent(textArea);
    }

    public void toolTipRequested(MouseEvent e, String text) {
        if (text == null || text.length() == 0) {
            this.possiblyDisposeOfTipWindow();
            this.lastText = text;
            return;
        }
        if (this.lastText == null || text.length() != this.lastText.length() || !text.equals(this.lastText)) {
            this.possiblyDisposeOfTipWindow();
            this.createAndShowTipWindow(e, text);
        }
    }

    private class TextAreaListener
    extends MouseInputAdapter
    implements CaretListener,
    ComponentListener,
    FocusListener,
    KeyListener {
        private TextAreaListener() {
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            Object source = e.getSource();
            if (source == FocusableTip.this.textArea) {
                FocusableTip.this.possiblyDisposeOfTipWindow();
            }
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            this.handleComponentEvent(e);
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            this.handleComponentEvent(e);
        }

        @Override
        public void componentResized(ComponentEvent e) {
            this.handleComponentEvent(e);
        }

        @Override
        public void componentShown(ComponentEvent e) {
            this.handleComponentEvent(e);
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            boolean tipClicked;
            Component c = e.getOppositeComponent();
            boolean bl = tipClicked = c instanceof TipWindow || c != null && SwingUtilities.getWindowAncestor(c) instanceof TipWindow;
            if (!tipClicked) {
                FocusableTip.this.possiblyDisposeOfTipWindow();
            }
        }

        private void handleComponentEvent(ComponentEvent e) {
            FocusableTip.this.possiblyDisposeOfTipWindow();
        }

        public void install(JTextArea textArea) {
            textArea.addCaretListener(this);
            textArea.addComponentListener(this);
            textArea.addFocusListener(this);
            textArea.addKeyListener(this);
            textArea.addMouseListener(this);
            textArea.addMouseMotionListener(this);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 27) {
                FocusableTip.this.possiblyDisposeOfTipWindow();
            } else if (e.getKeyCode() == 113 && FocusableTip.this.tipWindow != null && !FocusableTip.this.tipWindow.getFocusableWindowState()) {
                FocusableTip.this.tipWindow.actionPerformed(null);
                e.consume();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (FocusableTip.this.tipVisibleBounds == null || !FocusableTip.this.tipVisibleBounds.contains(e.getPoint())) {
                FocusableTip.this.possiblyDisposeOfTipWindow();
            }
        }

        public void uninstall() {
            FocusableTip.this.textArea.removeCaretListener(this);
            FocusableTip.this.textArea.removeComponentListener(this);
            FocusableTip.this.textArea.removeFocusListener(this);
            FocusableTip.this.textArea.removeKeyListener(this);
            FocusableTip.this.textArea.removeMouseListener(this);
            FocusableTip.this.textArea.removeMouseMotionListener(this);
        }
    }
}

