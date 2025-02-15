/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.tools.shell.ConsoleTextArea;

class ConsoleWrite
implements Runnable {
    private ConsoleTextArea textArea;
    private String str;

    public ConsoleWrite(ConsoleTextArea textArea, String str) {
        this.textArea = textArea;
        this.str = str;
    }

    @Override
    public void run() {
        this.textArea.write(this.str);
    }
}

