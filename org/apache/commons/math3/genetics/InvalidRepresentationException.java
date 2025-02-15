/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;

public class InvalidRepresentationException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public InvalidRepresentationException(Localizable pattern, Object ... args) {
        super(pattern, args);
    }
}

