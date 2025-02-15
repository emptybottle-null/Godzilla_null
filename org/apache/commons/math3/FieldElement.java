/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldElement<T> {
    public T add(T var1) throws NullArgumentException;

    public T subtract(T var1) throws NullArgumentException;

    public T negate();

    public T multiply(int var1);

    public T multiply(T var1) throws NullArgumentException;

    public T divide(T var1) throws NullArgumentException, MathArithmeticException;

    public T reciprocal() throws MathArithmeticException;

    public Field<T> getField();
}

