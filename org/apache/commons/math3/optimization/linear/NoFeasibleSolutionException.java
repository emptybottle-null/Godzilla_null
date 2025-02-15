/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.linear;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

@Deprecated
public class NoFeasibleSolutionException
extends MathIllegalStateException {
    private static final long serialVersionUID = -3044253632189082760L;

    public NoFeasibleSolutionException() {
        super(LocalizedFormats.NO_FEASIBLE_SOLUTION, new Object[0]);
    }
}

