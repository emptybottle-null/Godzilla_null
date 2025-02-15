/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.lang.Nullable;

public class ExpressionException
extends RuntimeException {
    @Nullable
    protected final String expressionString;
    protected int position;

    public ExpressionException(String message) {
        super(message);
        this.expressionString = null;
        this.position = 0;
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
        this.expressionString = null;
        this.position = 0;
    }

    public ExpressionException(@Nullable String expressionString, String message) {
        super(message);
        this.expressionString = expressionString;
        this.position = -1;
    }

    public ExpressionException(@Nullable String expressionString, int position, String message) {
        super(message);
        this.expressionString = expressionString;
        this.position = position;
    }

    public ExpressionException(int position, String message) {
        super(message);
        this.expressionString = null;
        this.position = position;
    }

    public ExpressionException(int position, String message, Throwable cause) {
        super(message, cause);
        this.expressionString = null;
        this.position = position;
    }

    @Nullable
    public final String getExpressionString() {
        return this.expressionString;
    }

    public final int getPosition() {
        return this.position;
    }

    @Override
    public String getMessage() {
        return this.toDetailedString();
    }

    public String toDetailedString() {
        if (this.expressionString != null) {
            StringBuilder output = new StringBuilder();
            output.append("Expression [");
            output.append(this.expressionString);
            output.append(']');
            if (this.position >= 0) {
                output.append(" @");
                output.append(this.position);
            }
            output.append(": ");
            output.append(this.getSimpleMessage());
            return output.toString();
        }
        return this.getSimpleMessage();
    }

    public String getSimpleMessage() {
        return super.getMessage();
    }
}

