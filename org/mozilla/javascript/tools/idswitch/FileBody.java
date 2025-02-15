/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.idswitch;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileBody {
    private char[] buffer = new char[16384];
    private int bufferEnd;
    private int lineBegin;
    private int lineEnd;
    private int nextLineStart;
    private int lineNumber;
    ReplaceItem firstReplace;
    ReplaceItem lastReplace;

    public char[] getBuffer() {
        return this.buffer;
    }

    public void readData(Reader r) throws IOException {
        int n_read;
        int capacity = this.buffer.length;
        int offset = 0;
        while ((n_read = r.read(this.buffer, offset, capacity - offset)) >= 0) {
            if (capacity != (offset += n_read)) continue;
            char[] tmp = new char[capacity *= 2];
            System.arraycopy(this.buffer, 0, tmp, 0, offset);
            this.buffer = tmp;
        }
        this.bufferEnd = offset;
    }

    public void writeInitialData(Writer w) throws IOException {
        w.write(this.buffer, 0, this.bufferEnd);
    }

    public void writeData(Writer w) throws IOException {
        int offset = 0;
        ReplaceItem x = this.firstReplace;
        while (x != null) {
            int before_replace = x.begin - offset;
            if (before_replace > 0) {
                w.write(this.buffer, offset, before_replace);
            }
            w.write(x.replacement);
            offset = x.end;
            x = x.next;
        }
        int tail = this.bufferEnd - offset;
        if (tail != 0) {
            w.write(this.buffer, offset, tail);
        }
    }

    public boolean wasModified() {
        return this.firstReplace != null;
    }

    public boolean setReplacement(int begin, int end, String text) {
        if (FileBody.equals(text, this.buffer, begin, end)) {
            return false;
        }
        ReplaceItem item = new ReplaceItem(begin, end, text);
        if (this.firstReplace == null) {
            this.firstReplace = this.lastReplace = item;
        } else if (begin < this.firstReplace.begin) {
            item.next = this.firstReplace;
            this.firstReplace = item;
        } else {
            ReplaceItem cursor = this.firstReplace;
            ReplaceItem next = cursor.next;
            while (next != null) {
                if (begin < next.begin) {
                    item.next = next;
                    cursor.next = item;
                    break;
                }
                cursor = next;
                next = next.next;
            }
            if (next == null) {
                this.lastReplace.next = item;
            }
        }
        return true;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getLineBegin() {
        return this.lineBegin;
    }

    public int getLineEnd() {
        return this.lineEnd;
    }

    public void startLineLoop() {
        this.lineNumber = 0;
        this.nextLineStart = 0;
        this.lineEnd = 0;
        this.lineBegin = 0;
    }

    public boolean nextLine() {
        int i;
        if (this.nextLineStart == this.bufferEnd) {
            this.lineNumber = 0;
            return false;
        }
        char c = '\u0000';
        for (i = this.nextLineStart; i != this.bufferEnd && (c = this.buffer[i]) != '\n' && c != '\r'; ++i) {
        }
        this.lineBegin = this.nextLineStart;
        this.lineEnd = i;
        this.nextLineStart = i == this.bufferEnd ? i : (c == '\r' && i + 1 != this.bufferEnd && this.buffer[i + 1] == '\n' ? i + 2 : i + 1);
        ++this.lineNumber;
        return true;
    }

    private static boolean equals(String str, char[] array, int begin, int end) {
        if (str.length() == end - begin) {
            int i = begin;
            int j = 0;
            while (i != end) {
                if (array[i] != str.charAt(j)) {
                    return false;
                }
                ++i;
                ++j;
            }
            return true;
        }
        return false;
    }

    private static class ReplaceItem {
        ReplaceItem next;
        int begin;
        int end;
        String replacement;

        ReplaceItem(int begin, int end, String text) {
            this.begin = begin;
            this.end = end;
            this.replacement = text;
        }
    }
}

