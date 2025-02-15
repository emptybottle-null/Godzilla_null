/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.random;

import java.util.Collection;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface RandomData {
    public String nextHexString(int var1) throws NotStrictlyPositiveException;

    public int nextInt(int var1, int var2) throws NumberIsTooLargeException;

    public long nextLong(long var1, long var3) throws NumberIsTooLargeException;

    public String nextSecureHexString(int var1) throws NotStrictlyPositiveException;

    public int nextSecureInt(int var1, int var2) throws NumberIsTooLargeException;

    public long nextSecureLong(long var1, long var3) throws NumberIsTooLargeException;

    public long nextPoisson(double var1) throws NotStrictlyPositiveException;

    public double nextGaussian(double var1, double var3) throws NotStrictlyPositiveException;

    public double nextExponential(double var1) throws NotStrictlyPositiveException;

    public double nextUniform(double var1, double var3) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException;

    public double nextUniform(double var1, double var3, boolean var5) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException;

    public int[] nextPermutation(int var1, int var2) throws NumberIsTooLargeException, NotStrictlyPositiveException;

    public Object[] nextSample(Collection<?> var1, int var2) throws NumberIsTooLargeException, NotStrictlyPositiveException;
}

