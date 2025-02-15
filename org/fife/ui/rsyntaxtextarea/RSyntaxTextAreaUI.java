/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.InputMapUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import org.fife.ui.rsyntaxtextarea.RSTAView;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaDefaultInputMap;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.SyntaxView;
import org.fife.ui.rsyntaxtextarea.WrappedSyntaxView;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaUI;

public class RSyntaxTextAreaUI
extends RTextAreaUI {
    private static final String SHARED_ACTION_MAP_NAME = "RSyntaxTextAreaUI.actionMap";
    private static final String SHARED_INPUT_MAP_NAME = "RSyntaxTextAreaUI.inputMap";
    private static final EditorKit DEFAULT_KIT = new RSyntaxTextAreaEditorKit();

    public static ComponentUI createUI(JComponent ta) {
        return new RSyntaxTextAreaUI(ta);
    }

    public RSyntaxTextAreaUI(JComponent rSyntaxTextArea) {
        super(rSyntaxTextArea);
    }

    @Override
    public View create(Element elem) {
        RTextArea c = this.getRTextArea();
        if (c instanceof RSyntaxTextArea) {
            RSyntaxTextArea area = (RSyntaxTextArea)c;
            View v = area.getLineWrap() ? new WrappedSyntaxView(elem) : new SyntaxView(elem);
            return v;
        }
        return null;
    }

    @Override
    protected Highlighter createHighlighter() {
        return new RSyntaxTextAreaHighlighter();
    }

    @Override
    protected String getActionMapName() {
        return SHARED_ACTION_MAP_NAME;
    }

    @Override
    public EditorKit getEditorKit(JTextComponent tc) {
        return DEFAULT_KIT;
    }

    @Override
    protected InputMap getRTextAreaInputMap() {
        InputMapUIResource map = new InputMapUIResource();
        InputMap shared = (InputMap)UIManager.get(SHARED_INPUT_MAP_NAME);
        if (shared == null) {
            shared = new RSyntaxTextAreaDefaultInputMap();
            UIManager.put(SHARED_INPUT_MAP_NAME, shared);
        }
        map.setParent(shared);
        return map;
    }

    @Override
    protected void paintEditorAugmentations(Graphics g) {
        super.paintEditorAugmentations(g);
        this.paintMatchedBracket(g);
    }

    protected void paintMatchedBracket(Graphics g) {
        RSyntaxTextArea rsta = (RSyntaxTextArea)this.textArea;
        if (rsta.isBracketMatchingEnabled()) {
            Rectangle dotRect;
            Rectangle match = rsta.getMatchRectangle();
            if (match != null) {
                this.paintMatchedBracketImpl(g, rsta, match);
            }
            if (rsta.getPaintMatchedBracketPair() && (dotRect = rsta.getDotRectangle()) != null) {
                this.paintMatchedBracketImpl(g, rsta, dotRect);
            }
        }
    }

    protected void paintMatchedBracketImpl(Graphics g, RSyntaxTextArea rsta, Rectangle r) {
        if (rsta.getAnimateBracketMatching()) {
            Color bg = rsta.getMatchedBracketBGColor();
            int arcWH = 5;
            if (bg != null) {
                g.setColor(bg);
                g.fillRoundRect(r.x, r.y, r.width, r.height - 1, 5, 5);
            }
            g.setColor(rsta.getMatchedBracketBorderColor());
            g.drawRoundRect(r.x, r.y, r.width, r.height - 1, 5, 5);
        } else {
            Color bg = rsta.getMatchedBracketBGColor();
            if (bg != null) {
                g.setColor(bg);
                g.fillRect(r.x, r.y, r.width, r.height - 1);
            }
            g.setColor(rsta.getMatchedBracketBorderColor());
            g.drawRect(r.x, r.y, r.width, r.height - 1);
        }
    }

    @Override
    protected void propertyChange(PropertyChangeEvent e) {
        String name = e.getPropertyName();
        if (name.equals("RSTA.syntaxScheme")) {
            this.modelChanged();
        } else {
            super.propertyChange(e);
        }
    }

    public void refreshSyntaxHighlighting() {
        this.modelChanged();
    }

    @Override
    public int yForLine(int line) throws BadLocationException {
        Rectangle alloc = this.getVisibleEditorRect();
        if (alloc != null) {
            RSTAView view = (RSTAView)((Object)this.getRootView(this.textArea).getView(0));
            return view.yForLine(alloc, line);
        }
        return -1;
    }

    @Override
    public int yForLineContaining(int offs) throws BadLocationException {
        Rectangle alloc = this.getVisibleEditorRect();
        if (alloc != null) {
            RSTAView view = (RSTAView)((Object)this.getRootView(this.textArea).getView(0));
            return view.yForLineContaining(alloc, offs);
        }
        return -1;
    }
}

