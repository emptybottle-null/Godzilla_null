/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.FileHeader;
import org.mozilla.javascript.tools.debugger.FileTextArea;
import org.mozilla.javascript.tools.debugger.RunProxy;
import org.mozilla.javascript.tools.debugger.SwingGui;

class FileWindow
extends JInternalFrame
implements ActionListener {
    private static final long serialVersionUID = -6212382604952082370L;
    private SwingGui debugGui;
    private Dim.SourceInfo sourceInfo;
    FileTextArea textArea;
    private FileHeader fileHeader;
    private JScrollPane p;
    int currentPos;

    void load() {
        String url = this.getUrl();
        if (url != null) {
            RunProxy proxy = new RunProxy(this.debugGui, 2);
            proxy.fileName = url;
            proxy.text = this.sourceInfo.source();
            new Thread(proxy).start();
        }
    }

    public int getPosition(int line) {
        int result = -1;
        try {
            result = this.textArea.getLineStartOffset(line);
        } catch (BadLocationException badLocationException) {
            // empty catch block
        }
        return result;
    }

    public boolean isBreakPoint(int line) {
        return this.sourceInfo.breakableLine(line) && this.sourceInfo.breakpoint(line);
    }

    public void toggleBreakPoint(int line) {
        if (!this.isBreakPoint(line)) {
            this.setBreakPoint(line);
        } else {
            this.clearBreakPoint(line);
        }
    }

    public void setBreakPoint(int line) {
        boolean changed;
        if (this.sourceInfo.breakableLine(line) && (changed = this.sourceInfo.breakpoint(line, true))) {
            this.fileHeader.repaint();
        }
    }

    public void clearBreakPoint(int line) {
        boolean changed;
        if (this.sourceInfo.breakableLine(line) && (changed = this.sourceInfo.breakpoint(line, false))) {
            this.fileHeader.repaint();
        }
    }

    public FileWindow(SwingGui debugGui, Dim.SourceInfo sourceInfo) {
        super(SwingGui.getShortName(sourceInfo.url()), true, true, true, true);
        this.debugGui = debugGui;
        this.sourceInfo = sourceInfo;
        this.updateToolTip();
        this.currentPos = -1;
        this.textArea = new FileTextArea(this);
        this.textArea.setRows(24);
        this.textArea.setColumns(80);
        this.p = new JScrollPane();
        this.fileHeader = new FileHeader(this);
        this.p.setViewportView(this.textArea);
        this.p.setRowHeaderView(this.fileHeader);
        this.setContentPane(this.p);
        this.pack();
        this.updateText(sourceInfo);
        this.textArea.select(0);
    }

    private void updateToolTip() {
        int n = this.getComponentCount() - 1;
        if (n > 1) {
            n = 1;
        } else if (n < 0) {
            return;
        }
        Component c = this.getComponent(n);
        if (c != null && c instanceof JComponent) {
            ((JComponent)c).setToolTipText(this.getUrl());
        }
    }

    public String getUrl() {
        return this.sourceInfo.url();
    }

    public void updateText(Dim.SourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
        String newText = sourceInfo.source();
        if (!this.textArea.getText().equals(newText)) {
            this.textArea.setText(newText);
            int pos = 0;
            if (this.currentPos != -1) {
                pos = this.currentPos;
            }
            this.textArea.select(pos);
        }
        this.fileHeader.update();
        this.fileHeader.repaint();
    }

    public void setPosition(int pos) {
        this.textArea.select(pos);
        this.currentPos = pos;
        this.fileHeader.repaint();
    }

    public void select(int start, int end) {
        int docEnd = this.textArea.getDocument().getLength();
        this.textArea.select(docEnd, docEnd);
        this.textArea.select(start, end);
    }

    @Override
    public void dispose() {
        this.debugGui.removeWindow(this);
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (!cmd.equals("Cut")) {
            if (cmd.equals("Copy")) {
                this.textArea.copy();
            } else if (cmd.equals("Paste")) {
                // empty if block
            }
        }
    }
}

