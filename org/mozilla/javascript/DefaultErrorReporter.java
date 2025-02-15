/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.ScriptRuntime;

class DefaultErrorReporter
implements ErrorReporter {
    static final DefaultErrorReporter instance = new DefaultErrorReporter();
    private boolean forEval;
    private ErrorReporter chainedReporter;

    private DefaultErrorReporter() {
    }

    static ErrorReporter forEval(ErrorReporter reporter) {
        DefaultErrorReporter r = new DefaultErrorReporter();
        r.forEval = true;
        r.chainedReporter = reporter;
        return r;
    }

    @Override
    public void warning(String message, String sourceURI, int line, String lineText, int lineOffset) {
        if (this.chainedReporter != null) {
            this.chainedReporter.warning(message, sourceURI, line, lineText, lineOffset);
        }
    }

    @Override
    public void error(String message, String sourceURI, int line, String lineText, int lineOffset) {
        if (this.forEval) {
            String error = "SyntaxError";
            String TYPE_ERROR_NAME = "TypeError";
            String DELIMETER = ": ";
            String prefix = "TypeError: ";
            if (message.startsWith("TypeError: ")) {
                error = "TypeError";
                message = message.substring("TypeError: ".length());
            }
            throw ScriptRuntime.constructError(error, message, sourceURI, line, lineText, lineOffset);
        }
        if (this.chainedReporter == null) {
            throw this.runtimeError(message, sourceURI, line, lineText, lineOffset);
        }
        this.chainedReporter.error(message, sourceURI, line, lineText, lineOffset);
    }

    @Override
    public EvaluatorException runtimeError(String message, String sourceURI, int line, String lineText, int lineOffset) {
        if (this.chainedReporter != null) {
            return this.chainedReporter.runtimeError(message, sourceURI, line, lineText, lineOffset);
        }
        return new EvaluatorException(message, sourceURI, line, lineText, lineOffset);
    }
}

