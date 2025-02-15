/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class IllConditionedOperatorException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = -7883263944530490135L;

    public IllConditionedOperatorException(double cond) {
        super(LocalizedFormats.ILL_CONDITIONED_OPERATOR, cond);
    }
}

