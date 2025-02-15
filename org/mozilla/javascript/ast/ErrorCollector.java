/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.ParseProblem;

public class ErrorCollector
implements IdeErrorReporter {
    private List<ParseProblem> errors = new ArrayList<ParseProblem>();

    @Override
    public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void warning(String message, String sourceName, int offset, int length) {
        this.errors.add(new ParseProblem(ParseProblem.Type.Warning, message, sourceName, offset, length));
    }

    @Override
    public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void error(String message, String sourceName, int fileOffset, int length) {
        this.errors.add(new ParseProblem(ParseProblem.Type.Error, message, sourceName, fileOffset, length));
    }

    @Override
    public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
        throw new UnsupportedOperationException();
    }

    public List<ParseProblem> getErrors() {
        return this.errors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.errors.size() * 100);
        for (ParseProblem pp : this.errors) {
            sb.append(pp.toString()).append("\n");
        }
        return sb.toString();
    }
}

