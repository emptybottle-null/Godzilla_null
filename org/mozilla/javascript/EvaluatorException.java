/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.RhinoException;

public class EvaluatorException
extends RhinoException {
    static final long serialVersionUID = -8743165779676009808L;

    public EvaluatorException(String detail) {
        super(detail);
    }

    public EvaluatorException(String detail, String sourceName, int lineNumber) {
        this(detail, sourceName, lineNumber, null, 0);
    }

    public EvaluatorException(String detail, String sourceName, int lineNumber, String lineSource, int columnNumber) {
        super(detail);
        this.recordErrorOrigin(sourceName, lineNumber, lineSource, columnNumber);
    }

    @Deprecated
    public String getSourceName() {
        return this.sourceName();
    }

    @Deprecated
    public int getLineNumber() {
        return this.lineNumber();
    }

    @Deprecated
    public int getColumnNumber() {
        return this.columnNumber();
    }

    @Deprecated
    public String getLineSource() {
        return this.lineSource();
    }
}

