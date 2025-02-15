/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel;

import org.springframework.expression.spel.SpelParseException;

public class InternalParseException
extends RuntimeException {
    public InternalParseException(SpelParseException cause) {
        super(cause);
    }

    @Override
    public SpelParseException getCause() {
        return (SpelParseException)super.getCause();
    }
}

