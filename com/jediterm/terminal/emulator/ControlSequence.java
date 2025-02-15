/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator;

import com.google.common.collect.Lists;
import com.jediterm.terminal.TerminalDataStream;
import com.jediterm.terminal.util.CharUtils;
import java.io.IOException;
import java.util.ArrayList;

public class ControlSequence {
    private int myArgc = 0;
    private int[] myArgv;
    private char myFinalChar;
    private ArrayList<Character> myUnhandledChars;
    private boolean myStartsWithQuestionMark = false;
    private boolean myStartsWithMoreMark = false;
    private final StringBuilder mySequenceString = new StringBuilder();

    ControlSequence(TerminalDataStream channel) throws IOException {
        this.myArgv = new int[5];
        this.readControlSequence(channel);
    }

    private void readControlSequence(TerminalDataStream channel) throws IOException {
        char b;
        this.myArgc = 0;
        int digit = 0;
        int seenDigit = 0;
        int pos = -1;
        while (true) {
            b = channel.getChar();
            this.mySequenceString.append(b);
            if (b == '?' && ++pos == 0) {
                this.myStartsWithQuestionMark = true;
                continue;
            }
            if (b == '>' && pos == 0) {
                this.myStartsWithMoreMark = true;
                continue;
            }
            if (b == ';') {
                if (digit <= 0) continue;
                ++this.myArgc;
                if (this.myArgc == this.myArgv.length) {
                    int[] replacement = new int[this.myArgv.length * 2];
                    System.arraycopy(this.myArgv, 0, replacement, 0, this.myArgv.length);
                    this.myArgv = replacement;
                }
                this.myArgv[this.myArgc] = 0;
                digit = 0;
                continue;
            }
            if ('0' <= b && b <= '9') {
                this.myArgv[this.myArgc] = this.myArgv[this.myArgc] * 10 + b - 48;
                ++digit;
                seenDigit = 1;
                continue;
            }
            if (':' <= b && b <= '?') {
                this.addUnhandled(b);
                continue;
            }
            if ('@' <= b && b <= '~') break;
            this.addUnhandled(b);
        }
        this.myFinalChar = b;
        this.myArgc += seenDigit;
    }

    private void addUnhandled(char b) {
        if (this.myUnhandledChars == null) {
            this.myUnhandledChars = Lists.newArrayList();
        }
        this.myUnhandledChars.add(Character.valueOf(b));
    }

    public boolean pushBackReordered(TerminalDataStream channel) throws IOException {
        if (this.myUnhandledChars == null) {
            return false;
        }
        char[] bytes = new char[1024];
        int i = 0;
        for (char b : this.myUnhandledChars) {
            bytes[i++] = b;
        }
        bytes[i++] = 27;
        bytes[i++] = 91;
        if (this.myStartsWithQuestionMark) {
            bytes[i++] = 63;
        }
        if (this.myStartsWithMoreMark) {
            bytes[i++] = 62;
        }
        for (int argi = 0; argi < this.myArgc; ++argi) {
            if (argi != 0) {
                bytes[i++] = 59;
            }
            String s = Integer.toString(this.myArgv[argi]);
            for (int j = 0; j < s.length(); ++j) {
                bytes[i++] = s.charAt(j);
            }
        }
        bytes[i++] = this.myFinalChar;
        channel.pushBackBuffer(bytes, i);
        return true;
    }

    int getCount() {
        return this.myArgc;
    }

    final int getArg(int index, int defaultValue) {
        if (index >= this.myArgc) {
            return defaultValue;
        }
        return this.myArgv[index];
    }

    public String appendTo(String str) {
        StringBuilder sb = new StringBuilder(str);
        this.appendToBuffer(sb);
        return sb.toString();
    }

    public final void appendToBuffer(StringBuilder sb) {
        sb.append("ESC[");
        if (this.myStartsWithQuestionMark) {
            sb.append("?");
        }
        if (this.myStartsWithMoreMark) {
            sb.append(">");
        }
        String sep = "";
        for (int i = 0; i < this.myArgc; ++i) {
            sb.append(sep);
            sb.append(this.myArgv[i]);
            sep = ";";
        }
        sb.append(this.myFinalChar);
        if (this.myUnhandledChars != null) {
            sb.append(" Unhandled:");
            CharUtils.CharacterType last = CharUtils.CharacterType.NONE;
            for (char b : this.myUnhandledChars) {
                last = CharUtils.appendChar(sb, last, b);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.appendToBuffer(sb);
        return sb.toString();
    }

    public char getFinalChar() {
        return this.myFinalChar;
    }

    public boolean startsWithQuestionMark() {
        return this.myStartsWithQuestionMark;
    }

    public boolean startsWithMoreMark() {
        return this.myStartsWithMoreMark;
    }

    public String getSequenceString() {
        return this.mySequenceString.toString();
    }
}

