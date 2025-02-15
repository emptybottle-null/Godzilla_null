/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3;

import org.apache.commons.math3.FieldElement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Field<T> {
    public T getZero();

    public T getOne();

    public Class<? extends FieldElement<T>> getRuntimeClass();
}

