/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MultiDimensionMismatchException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = -8415396756375798143L;
    private final Integer[] wrong;
    private final Integer[] expected;

    public MultiDimensionMismatchException(Integer[] wrong, Integer[] expected) {
        this((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH, wrong, expected);
    }

    public MultiDimensionMismatchException(Localizable specific, Integer[] wrong, Integer[] expected) {
        super(specific, wrong, expected);
        this.wrong = (Integer[])wrong.clone();
        this.expected = (Integer[])expected.clone();
    }

    public Integer[] getWrongDimensions() {
        return (Integer[])this.wrong.clone();
    }

    public Integer[] getExpectedDimensions() {
        return (Integer[])this.expected.clone();
    }

    public int getWrongDimension(int index) {
        return this.wrong[index];
    }

    public int getExpectedDimension(int index) {
        return this.expected[index];
    }
}

