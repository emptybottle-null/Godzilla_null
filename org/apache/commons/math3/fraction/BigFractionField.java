/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.fraction.BigFraction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BigFractionField
implements Field<BigFraction>,
Serializable {
    private static final long serialVersionUID = -1699294557189741703L;

    private BigFractionField() {
    }

    public static BigFractionField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public BigFraction getOne() {
        return BigFraction.ONE;
    }

    @Override
    public BigFraction getZero() {
        return BigFraction.ZERO;
    }

    @Override
    public Class<? extends FieldElement<BigFraction>> getRuntimeClass() {
        return BigFraction.class;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BigFractionField INSTANCE = new BigFractionField();

        private LazyHolder() {
        }
    }
}

