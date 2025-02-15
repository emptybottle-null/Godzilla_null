/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import org.mozilla.javascript.tools.debugger.Dim;
import org.mozilla.javascript.tools.debugger.MessageDialogWrapper;
import org.mozilla.javascript.tools.debugger.SwingGui;

class RunProxy
implements Runnable {
    static final int OPEN_FILE = 1;
    static final int LOAD_FILE = 2;
    static final int UPDATE_SOURCE_TEXT = 3;
    static final int ENTER_INTERRUPT = 4;
    private SwingGui debugGui;
    private int type;
    String fileName;
    String text;
    Dim.SourceInfo sourceInfo;
    Dim.StackFrame lastFrame;
    String threadTitle;
    String alertMessage;

    public RunProxy(SwingGui debugGui, int type) {
        this.debugGui = debugGui;
        this.type = type;
    }

    @Override
    public void run() {
        switch (this.type) {
            case 1: {
                try {
                    this.debugGui.dim.compileScript(this.fileName, this.text);
                } catch (RuntimeException ex) {
                    MessageDialogWrapper.showMessageDialog(this.debugGui, ex.getMessage(), "Error Compiling " + this.fileName, 0);
                }
                break;
            }
            case 2: {
                try {
                    this.debugGui.dim.evalScript(this.fileName, this.text);
                } catch (RuntimeException ex) {
                    MessageDialogWrapper.showMessageDialog(this.debugGui, ex.getMessage(), "Run error for " + this.fileName, 0);
                }
                break;
            }
            case 3: {
                String fileName = this.sourceInfo.url();
                if (this.debugGui.updateFileWindow(this.sourceInfo) || fileName.equals("<stdin>")) break;
                this.debugGui.createFileWindow(this.sourceInfo, -1);
                break;
            }
            case 4: {
                this.debugGui.enterInterruptImpl(this.lastFrame, this.threadTitle, this.alertMessage);
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(this.type));
            }
        }
    }
}

