/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.io.OutputStream;
import javax.swing.SwingUtilities;
import org.mozilla.javascript.tools.shell.ConsoleTextArea;
import org.mozilla.javascript.tools.shell.ConsoleWrite;

class ConsoleWriter
extends OutputStream {
    private ConsoleTextArea textArea;
    private StringBuffer buffer;

    public ConsoleWriter(ConsoleTextArea textArea) {
        this.textArea = textArea;
        this.buffer = new StringBuffer();
    }

    @Override
    public synchronized void write(int ch) {
        this.buffer.append((char)ch);
        if (ch == 10) {
            this.flushBuffer();
        }
    }

    public synchronized void write(char[] data, int off, int len) {
        for (int i = off; i < len; ++i) {
            this.buffer.append(data[i]);
            if (data[i] != '\n') continue;
            this.flushBuffer();
        }
    }

    @Override
    public synchronized void flush() {
        if (this.buffer.length() > 0) {
            this.flushBuffer();
        }
    }

    @Override
    public void close() {
        this.flush();
    }

    private void flushBuffer() {
        String str = this.buffer.toString();
        this.buffer.setLength(0);
        SwingUtilities.invokeLater(new ConsoleWrite(this.textArea, str));
    }
}

