/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.ExpressionException;
import org.springframework.lang.Nullable;

public class ParseException
extends ExpressionException {
    public ParseException(@Nullable String expressionString, int position, String message) {
        super(expressionString, position, message);
    }

    public ParseException(int position, String message, Throwable cause) {
        super(position, message, cause);
    }

    public ParseException(int position, String message) {
        super(position, message);
    }
}

