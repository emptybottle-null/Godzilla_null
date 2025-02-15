/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.linear;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class UnboundedSolutionException
extends MathIllegalStateException {
    private static final long serialVersionUID = 940539497277290619L;

    public UnboundedSolutionException() {
        super(LocalizedFormats.UNBOUNDED_SOLUTION, new Object[0]);
    }
}

