/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.EvaluationException;

public class ExpressionInvocationTargetException
extends EvaluationException {
    public ExpressionInvocationTargetException(int position, String message, Throwable cause) {
        super(position, message, cause);
    }

    public ExpressionInvocationTargetException(int position, String message) {
        super(position, message);
    }

    public ExpressionInvocationTargetException(String expressionString, String message) {
        super(expressionString, message);
    }

    public ExpressionInvocationTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionInvocationTargetException(String message) {
        super(message);
    }
}

