/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;

public interface BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int var1, int var2, double var3) throws NotStrictlyPositiveException, NotPositiveException, NumberIsTooLargeException, OutOfRangeException;
}

