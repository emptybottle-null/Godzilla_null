/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.fraction.Fraction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FractionField
implements Field<Fraction>,
Serializable {
    private static final long serialVersionUID = -1257768487499119313L;

    private FractionField() {
    }

    public static FractionField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public Fraction getOne() {
        return Fraction.ONE;
    }

    @Override
    public Fraction getZero() {
        return Fraction.ZERO;
    }

    @Override
    public Class<? extends FieldElement<Fraction>> getRuntimeClass() {
        return Fraction.class;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final FractionField INSTANCE = new FractionField();

        private LazyHolder() {
        }
    }
}

