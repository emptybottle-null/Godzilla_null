/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.external.biz.binaryEscape;

import java.util.LinkedList;

class StringIterator {
    protected int position = 0;
    protected int lineNo;
    protected char[] text;
    protected String texts;
    protected int begin = 0;
    protected LinkedList mark1 = new LinkedList();
    protected LinkedList mark2 = new LinkedList();

    public StringIterator(String paramString) {
        this(paramString, 0);
    }

    public String toString() {
        return this.texts;
    }

    public StringIterator(String paramString, int paramInt) {
        this.texts = paramString;
        this.text = paramString.toCharArray();
        this.lineNo = paramInt;
    }

    public boolean hasNext() {
        return this.position < this.text.length;
    }

    public boolean hasNext(int paramInt) {
        return this.position + paramInt - 1 < this.text.length;
    }

    public int getLineNumber() {
        return this.lineNo;
    }

    public String getErrorToken() {
        return String.format(" EntireLine:%s LineNumber:%d LineMarker:%d", this.getEntireLine(), this.getLineNumber(), this.getLineMarker());
    }

    public String getEntireLine() {
        int i;
        for (i = this.position; i < this.text.length && this.text[i] != '\n'; ++i) {
        }
        return this.texts.substring(this.begin, i);
    }

    public int getLineMarker() {
        return this.position - this.begin;
    }

    public boolean isNextString(String paramString) {
        return this.position + paramString.length() <= this.text.length && this.texts.substring(this.position, this.position + paramString.length()).equals(paramString);
    }

    public boolean isNextChar(char paramChar) {
        return this.hasNext() && this.text[this.position] == paramChar;
    }

    public char peek() {
        return this.hasNext() ? this.text[this.position] : (char)'\u0000';
    }

    public void skip(int paramInt) {
        this.position += paramInt;
    }

    public String next(int paramInt) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int b = 0; b < paramInt; b = (int)((byte)(b + 1))) {
            stringBuffer.append(this.next());
        }
        return stringBuffer.toString();
    }

    public char next() {
        char c = this.text[this.position];
        if (this.position > 0 && this.text[this.position - 1] == '\n') {
            ++this.lineNo;
            this.begin = this.position;
        }
        ++this.position;
        return c;
    }

    public void mark() {
        this.mark1.add(0, new Integer(this.position));
        this.mark2.add(0, new Integer(this.lineNo));
    }

    public String reset() {
        Integer integer1 = (Integer)this.mark1.removeFirst();
        Integer integer2 = (Integer)this.mark2.removeFirst();
        return this.texts.substring(integer1, this.position);
    }
}

