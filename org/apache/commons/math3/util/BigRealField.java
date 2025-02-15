/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.BigReal;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BigRealField
implements Field<BigReal>,
Serializable {
    private static final long serialVersionUID = 4756431066541037559L;

    private BigRealField() {
    }

    public static BigRealField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public BigReal getOne() {
        return BigReal.ONE;
    }

    @Override
    public BigReal getZero() {
        return BigReal.ZERO;
    }

    @Override
    public Class<? extends FieldElement<BigReal>> getRuntimeClass() {
        return BigReal.class;
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BigRealField INSTANCE = new BigRealField();

        private LazyHolder() {
        }
    }
}

