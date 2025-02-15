/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.tools.debugger.FilePopupMenu;
import org.mozilla.javascript.tools.debugger.FileWindow;

class FileTextArea
extends JTextArea
implements ActionListener,
PopupMenuListener,
KeyListener,
MouseListener {
    private static final long serialVersionUID = -25032065448563720L;
    private FileWindow w;
    private FilePopupMenu popup;

    public FileTextArea(FileWindow w) {
        this.w = w;
        this.popup = new FilePopupMenu(this);
        this.popup.addPopupMenuListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFont(new Font("Monospaced", 0, 12));
    }

    public void select(int pos) {
        block9: {
            if (pos >= 0) {
                try {
                    int line = this.getLineOfOffset(pos);
                    Rectangle rect = this.modelToView(pos);
                    if (rect == null) {
                        this.select(pos, pos);
                        break block9;
                    }
                    try {
                        Rectangle nrect = this.modelToView(this.getLineStartOffset(line + 1));
                        if (nrect != null) {
                            rect = nrect;
                        }
                    } catch (Exception exc) {
                        // empty catch block
                    }
                    JViewport vp = (JViewport)this.getParent();
                    Rectangle viewRect = vp.getViewRect();
                    if (viewRect.y + viewRect.height > rect.y) {
                        this.select(pos, pos);
                    } else {
                        rect.y += (viewRect.height - rect.height) / 2;
                        this.scrollRectToVisible(rect);
                        this.select(pos, pos);
                    }
                } catch (BadLocationException exc) {
                    this.select(pos, pos);
                }
            }
        }
    }

    private void checkPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.popup.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.checkPopup(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.checkPopup(e);
        this.requestFocus();
        this.getCaret().setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.checkPopup(e);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pos = this.viewToModel(new Point(this.popup.x, this.popup.y));
        this.popup.setVisible(false);
        String cmd = e.getActionCommand();
        int line = -1;
        try {
            line = this.getLineOfOffset(pos);
        } catch (Exception exc) {
            // empty catch block
        }
        if (cmd.equals("Set Breakpoint")) {
            this.w.setBreakPoint(line + 1);
        } else if (cmd.equals("Clear Breakpoint")) {
            this.w.clearBreakPoint(line + 1);
        } else if (cmd.equals("Run")) {
            this.w.load();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 8: 
            case 9: 
            case 10: 
            case 127: {
                e.consume();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }
}

