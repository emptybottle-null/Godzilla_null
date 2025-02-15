/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldODEState<T extends RealFieldElement<T>> {
    private final T time;
    private final T[] state;
    private final T[][] secondaryState;

    public FieldODEState(T time, T[] state) {
        this((RealFieldElement)time, (RealFieldElement[])state, null);
    }

    public FieldODEState(T time, T[] state, T[][] secondaryState) {
        this.time = time;
        this.state = (RealFieldElement[])state.clone();
        this.secondaryState = this.copy(time.getField(), (RealFieldElement[][])secondaryState);
    }

    protected T[][] copy(Field<T> field, T[][] original) {
        if (original == null) {
            return null;
        }
        RealFieldElement[][] copied = (RealFieldElement[][])MathArrays.buildArray(field, original.length, -1);
        for (int i = 0; i < original.length; ++i) {
            copied[i] = (RealFieldElement[])original[i].clone();
        }
        return copied;
    }

    public T getTime() {
        return this.time;
    }

    public int getStateDimension() {
        return this.state.length;
    }

    public T[] getState() {
        return (RealFieldElement[])this.state.clone();
    }

    public int getNumberOfSecondaryStates() {
        return this.secondaryState == null ? 0 : this.secondaryState.length;
    }

    public int getSecondaryStateDimension(int index) {
        return index == 0 ? this.state.length : this.secondaryState[index - 1].length;
    }

    public T[] getSecondaryState(int index) {
        return index == 0 ? (RealFieldElement[])this.state.clone() : (RealFieldElement[])this.secondaryState[index - 1].clone();
    }
}

