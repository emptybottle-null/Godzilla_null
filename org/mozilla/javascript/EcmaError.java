/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class EcmaError
extends RhinoException {
    static final long serialVersionUID = -6261226256957286699L;
    private String errorName;
    private String errorMessage;

    EcmaError(String errorName, String errorMessage, String sourceName, int lineNumber, String lineSource, int columnNumber) {
        this.recordErrorOrigin(sourceName, lineNumber, lineSource, columnNumber);
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }

    @Deprecated
    public EcmaError(Scriptable nativeError, String sourceName, int lineNumber, int columnNumber, String lineSource) {
        this("InternalError", ScriptRuntime.toString(nativeError), sourceName, lineNumber, lineSource, columnNumber);
    }

    @Override
    public String details() {
        return this.errorName + ": " + this.errorMessage;
    }

    public String getName() {
        return this.errorName;
    }

    public String getErrorMessage() {
        return this.errorMessage;
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

    @Deprecated
    public Scriptable getErrorObject() {
        return null;
    }
}

