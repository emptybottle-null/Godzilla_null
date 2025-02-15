/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import org.apache.xmlbeans.XmlCursor;

public class XmlLineNumber
extends XmlCursor.XmlBookmark {
    private int _line;
    private int _column;
    private int _offset;

    public XmlLineNumber(int line) {
        this(line, -1, -1);
    }

    public XmlLineNumber(int line, int column) {
        this(line, column, -1);
    }

    public XmlLineNumber(int line, int column, int offset) {
        super(false);
        this._line = line;
        this._column = column;
        this._offset = offset;
    }

    public int getLine() {
        return this._line;
    }

    public int getColumn() {
        return this._column;
    }

    public int getOffset() {
        return this._offset;
    }
}

