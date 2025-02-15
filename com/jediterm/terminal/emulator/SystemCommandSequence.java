/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator;

import com.google.common.collect.Lists;
import com.jediterm.terminal.TerminalDataStream;
import java.io.IOException;
import java.util.List;

public class SystemCommandSequence {
    private final List<Object> myArgs = Lists.newArrayList();
    private final StringBuilder mySequenceString = new StringBuilder();

    public SystemCommandSequence(TerminalDataStream dataStream) throws IOException {
        this.readSystemCommandSequence(dataStream);
    }

    private void readSystemCommandSequence(TerminalDataStream stream) throws IOException {
        boolean isNumber = true;
        int number = 0;
        StringBuilder string = new StringBuilder();
        while (true) {
            char b = stream.getChar();
            this.mySequenceString.append(b);
            if (b == ';' || this.isEnd(b)) {
                if (this.isTwoBytesEnd(b)) {
                    string.delete(string.length() - 1, string.length());
                }
                if (isNumber) {
                    this.myArgs.add(number);
                } else {
                    this.myArgs.add(string.toString());
                }
                if (this.isEnd(b)) break;
                isNumber = true;
                number = 0;
                string = new StringBuilder();
                continue;
            }
            if (isNumber) {
                if ('0' <= b && b <= '9') {
                    number = number * 10 + b - 48;
                } else {
                    isNumber = false;
                }
                string.append(b);
                continue;
            }
            string.append(b);
        }
    }

    private boolean isEnd(char b) {
        return b == '\u0007' || b == '\u009c' || this.isTwoBytesEnd(b);
    }

    private boolean isTwoBytesEnd(char ch) {
        int len = this.mySequenceString.length();
        return len >= 2 && this.mySequenceString.charAt(len - 2) == '\u001b' && ch == '\\';
    }

    public String getStringAt(int i) {
        if (i >= this.myArgs.size()) {
            return null;
        }
        Object val = this.myArgs.get(i);
        return val instanceof String ? (String)val : null;
    }

    public Integer getIntAt(int i) {
        if (i >= this.myArgs.size()) {
            return null;
        }
        Object val = this.myArgs.get(i);
        return val instanceof Integer ? (Integer)val : null;
    }

    public String getSequenceString() {
        return this.mySequenceString.toString();
    }
}

