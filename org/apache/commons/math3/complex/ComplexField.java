/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.complex.Complex;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ComplexField
implements Field<Complex>,
Serializable {
    private static final long serialVersionUID = -6130362688700788798L;

    private ComplexField() {
    }

    public static ComplexField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Complex getOne() {
        return Complex.ONE;
    }

    @Override
    public Complex getZero() {
        return Complex.ZERO;
    }

    @Override
    public Class<? extends FieldElement<Complex>> getRuntimeClass() {
        return Complex.class;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final ComplexField INSTANCE = new ComplexField();

        private LazyHolder() {
        }
    }
}

