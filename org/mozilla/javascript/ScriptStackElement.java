/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;

public final class ScriptStackElement
implements Serializable {
    static final long serialVersionUID = -6416688260860477449L;
    public final String fileName;
    public final String functionName;
    public final int lineNumber;

    public ScriptStackElement(String fileName, String functionName, int lineNumber) {
        this.fileName = fileName;
        this.functionName = functionName;
        this.lineNumber = lineNumber;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.renderMozillaStyle(sb);
        return sb.toString();
    }

    public void renderJavaStyle(StringBuilder sb) {
        sb.append("\tat ").append(this.fileName);
        if (this.lineNumber > -1) {
            sb.append(':').append(this.lineNumber);
        }
        if (this.functionName != null) {
            sb.append(" (").append(this.functionName).append(')');
        }
    }

    public void renderMozillaStyle(StringBuilder sb) {
        if (this.functionName != null) {
            sb.append(this.functionName).append("()");
        }
        sb.append('@').append(this.fileName);
        if (this.lineNumber > -1) {
            sb.append(':').append(this.lineNumber);
        }
    }

    public void renderV8Style(StringBuilder sb) {
        sb.append("    at ");
        if (this.functionName == null || "anonymous".equals(this.functionName) || "undefined".equals(this.functionName)) {
            this.appendV8Location(sb);
        } else {
            sb.append(this.functionName).append(" (");
            this.appendV8Location(sb);
            sb.append(')');
        }
    }

    private void appendV8Location(StringBuilder sb) {
        sb.append(this.fileName);
        if (this.lineNumber > -1) {
            sb.append(':').append(this.lineNumber);
        }
    }
}

