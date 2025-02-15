/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.operator.OperatorException;

public class OperatorCreationException
extends OperatorException {
    public OperatorCreationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public OperatorCreationException(String string) {
        super(string);
    }
}

