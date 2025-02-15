/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class SingularOperatorException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = -476049978595245033L;

    public SingularOperatorException() {
        super(LocalizedFormats.SINGULAR_OPERATOR, new Object[0]);
    }
}

