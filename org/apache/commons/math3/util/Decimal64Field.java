/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.Decimal64;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Decimal64Field
implements Field<Decimal64> {
    private static final Decimal64Field INSTANCE = new Decimal64Field();

    private Decimal64Field() {
    }

    public static final Decimal64Field getInstance() {
        return INSTANCE;
    }

    @Override
    public Decimal64 getZero() {
        return Decimal64.ZERO;
    }

    @Override
    public Decimal64 getOne() {
        return Decimal64.ONE;
    }

    @Override
    public Class<? extends FieldElement<Decimal64>> getRuntimeClass() {
        return Decimal64.class;
    }
}

