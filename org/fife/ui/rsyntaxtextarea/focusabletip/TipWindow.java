/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.focusabletip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.focusabletip.FocusableTip;
import org.fife.ui.rsyntaxtextarea.focusabletip.SizeGrip;
import org.fife.ui.rsyntaxtextarea.focusabletip.TipUtil;

class TipWindow
extends JWindow
implements ActionListener {
    private FocusableTip ft;
    private JEditorPane textArea;
    private String text;
    private transient TipListener tipListener;
    private transient HyperlinkListener userHyperlinkListener;
    private static TipWindow visibleInstance;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    TipWindow(Window owner, FocusableTip ft, String msg) {
        super(owner);
        this.ft = ft;
        if (msg != null && msg.length() >= 6 && !msg.substring(0, 6).toLowerCase().equals("<html>")) {
            msg = "<html>" + RSyntaxUtilities.escapeForHtml(msg, "<br>", false);
        }
        this.text = msg;
        this.tipListener = new TipListener();
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(TipUtil.getToolTipBorder());
        cp.setBackground(TipUtil.getToolTipBackground());
        this.textArea = new JEditorPane("text/html", this.text);
        TipUtil.tweakTipEditorPane(this.textArea);
        if (ft.getImageBase() != null) {
            ((HTMLDocument)this.textArea.getDocument()).setBase(ft.getImageBase());
        }
        this.textArea.addMouseListener(this.tipListener);
        this.textArea.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                this.ft.possiblyDisposeOfTipWindow();
            }
        });
        cp.add(this.textArea);
        this.setFocusableWindowState(false);
        this.setContentPane(cp);
        this.setBottomPanel();
        this.pack();
        KeyAdapter ka = new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    TipWindow.this.ft.possiblyDisposeOfTipWindow();
                }
            }
        };
        this.addKeyListener(ka);
        this.textArea.addKeyListener(ka);
        Class<TipWindow> clazz = TipWindow.class;
        synchronized (TipWindow.class) {
            if (visibleInstance != null) {
                visibleInstance.dispose();
            }
            visibleInstance = this;
            // ** MonitorExit[var6_6] (shouldn't be in output)
            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.getFocusableWindowState()) {
            this.setFocusableWindowState(true);
            this.setBottomPanel();
            this.textArea.removeMouseListener(this.tipListener);
            this.pack();
            this.addWindowFocusListener(new WindowAdapter(){

                @Override
                public void windowLostFocus(WindowEvent e) {
                    TipWindow.this.ft.possiblyDisposeOfTipWindow();
                }
            });
            this.ft.removeListeners();
            if (e == null) {
                this.requestFocus();
            }
        }
    }

    @Override
    public void dispose() {
        Container cp = this.getContentPane();
        for (int i = 0; i < cp.getComponentCount(); ++i) {
            cp.getComponent(i).removeMouseListener(this.tipListener);
        }
        this.ft.removeListeners();
        super.dispose();
    }

    void fixSize() {
        Dimension d = this.textArea.getPreferredSize();
        Rectangle r = null;
        try {
            r = this.textArea.modelToView(this.textArea.getDocument().getLength() - 1);
            d = this.textArea.getPreferredSize();
            d.width += 25;
            int maxWindowW = this.ft.getMaxSize() != null ? this.ft.getMaxSize().width : 600;
            int maxWindowH = this.ft.getMaxSize() != null ? this.ft.getMaxSize().height : 400;
            d.width = Math.min(d.width, maxWindowW);
            d.height = Math.min(d.height, maxWindowH);
            this.textArea.setPreferredSize(d);
            this.textArea.setSize(d);
            r = this.textArea.modelToView(this.textArea.getDocument().getLength() - 1);
            if (r.y + r.height > d.height) {
                d.height = r.y + r.height + 5;
                if (this.ft.getMaxSize() != null) {
                    d.height = Math.min(d.height, maxWindowH);
                }
                this.textArea.setPreferredSize(d);
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        this.pack();
    }

    public String getText() {
        return this.text;
    }

    private void setBottomPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add((Component)new JSeparator(), "North");
        boolean focusable = this.getFocusableWindowState();
        if (focusable) {
            SizeGrip sg = new SizeGrip();
            sg.applyComponentOrientation(sg.getComponentOrientation());
            panel.add((Component)sg, "After");
            MouseInputAdapter adapter = new MouseInputAdapter(){
                private Point lastPoint;

                @Override
                public void mouseDragged(MouseEvent e) {
                    Point p = e.getPoint();
                    SwingUtilities.convertPointToScreen(p, panel);
                    if (this.lastPoint == null) {
                        this.lastPoint = p;
                    } else {
                        int dx = p.x - this.lastPoint.x;
                        int dy = p.y - this.lastPoint.y;
                        TipWindow.this.setLocation(TipWindow.this.getX() + dx, TipWindow.this.getY() + dy);
                        this.lastPoint = p;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    this.lastPoint = e.getPoint();
                    SwingUtilities.convertPointToScreen(this.lastPoint, panel);
                }
            };
            panel.addMouseListener(adapter);
            panel.addMouseMotionListener(adapter);
        } else {
            panel.setOpaque(false);
            JLabel label = new JLabel(FocusableTip.getString("FocusHotkey"));
            Color fg = UIManager.getColor("Label.disabledForeground");
            Font font = this.textArea.getFont();
            font = font.deriveFont(font.getSize2D() - 1.0f);
            label.setFont(font);
            if (fg == null) {
                fg = Color.GRAY;
            }
            label.setOpaque(true);
            Color bg = TipUtil.getToolTipBackground();
            label.setBackground(bg);
            label.setForeground(fg);
            label.setHorizontalAlignment(11);
            label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            panel.add(label);
            panel.addMouseListener(this.tipListener);
        }
        Container cp = this.getContentPane();
        if (cp.getComponentCount() == 2) {
            Component comp = cp.getComponent(0);
            cp.remove(0);
            JScrollPane sp = new JScrollPane(comp);
            Border emptyBorder = BorderFactory.createEmptyBorder();
            sp.setBorder(emptyBorder);
            sp.setViewportBorder(emptyBorder);
            sp.setBackground(this.textArea.getBackground());
            sp.getViewport().setBackground(this.textArea.getBackground());
            cp.add(sp);
            cp.getComponent(0).removeMouseListener(this.tipListener);
            cp.remove(0);
        }
        cp.add((Component)panel, "South");
    }

    public void setHyperlinkListener(HyperlinkListener listener) {
        if (this.userHyperlinkListener != null) {
            this.textArea.removeHyperlinkListener(this.userHyperlinkListener);
        }
        this.userHyperlinkListener = listener;
        if (this.userHyperlinkListener != null) {
            this.textArea.addHyperlinkListener(this.userHyperlinkListener);
        }
    }

    private final class TipListener
    extends MouseAdapter {
        private TipListener() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            TipWindow.this.actionPerformed(null);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Component source = (Component)e.getSource();
            Point p = e.getPoint();
            SwingUtilities.convertPointToScreen(p, source);
            if (!TipWindow.this.getBounds().contains(p)) {
                TipWindow.this.ft.possiblyDisposeOfTipWindow();
            }
        }
    }
}

