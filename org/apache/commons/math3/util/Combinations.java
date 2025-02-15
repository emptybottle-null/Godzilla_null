/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Combinations
implements Iterable<int[]> {
    private final int n;
    private final int k;
    private final IterationOrder iterationOrder;

    public Combinations(int n, int k) {
        this(n, k, IterationOrder.LEXICOGRAPHIC);
    }

    private Combinations(int n, int k, IterationOrder iterationOrder) {
        CombinatoricsUtils.checkBinomial(n, k);
        this.n = n;
        this.k = k;
        this.iterationOrder = iterationOrder;
    }

    public int getN() {
        return this.n;
    }

    public int getK() {
        return this.k;
    }

    @Override
    public Iterator<int[]> iterator() {
        if (this.k == 0 || this.k == this.n) {
            return new SingletonIterator(MathArrays.natural(this.k));
        }
        switch (this.iterationOrder) {
            case LEXICOGRAPHIC: {
                return new LexicographicIterator(this.n, this.k);
            }
        }
        throw new MathInternalError();
    }

    public Comparator<int[]> comparator() {
        return new LexicographicComparator(this.n, this.k);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class LexicographicComparator
    implements Comparator<int[]>,
    Serializable {
        private static final long serialVersionUID = 20130906L;
        private final int n;
        private final int k;

        LexicographicComparator(int n, int k) {
            this.n = n;
            this.k = k;
        }

        @Override
        public int compare(int[] c1, int[] c2) {
            if (c1.length != this.k) {
                throw new DimensionMismatchException(c1.length, this.k);
            }
            if (c2.length != this.k) {
                throw new DimensionMismatchException(c2.length, this.k);
            }
            int[] c1s = MathArrays.copyOf(c1);
            Arrays.sort(c1s);
            int[] c2s = MathArrays.copyOf(c2);
            Arrays.sort(c2s);
            long v1 = this.lexNorm(c1s);
            long v2 = this.lexNorm(c2s);
            if (v1 < v2) {
                return -1;
            }
            if (v1 > v2) {
                return 1;
            }
            return 0;
        }

        private long lexNorm(int[] c) {
            long ret = 0L;
            for (int i = 0; i < c.length; ++i) {
                int digit = c[i];
                if (digit < 0 || digit >= this.n) {
                    throw new OutOfRangeException(digit, (Number)0, this.n - 1);
                }
                ret += (long)(c[i] * ArithmeticUtils.pow(this.n, i));
            }
            return ret;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class SingletonIterator
    implements Iterator<int[]> {
        private final int[] singleton;
        private boolean more = true;

        SingletonIterator(int[] singleton) {
            this.singleton = singleton;
        }

        @Override
        public boolean hasNext() {
            return this.more;
        }

        @Override
        public int[] next() {
            if (this.more) {
                this.more = false;
                return this.singleton;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class LexicographicIterator
    implements Iterator<int[]> {
        private final int k;
        private final int[] c;
        private boolean more = true;
        private int j;

        LexicographicIterator(int n, int k) {
            this.k = k;
            this.c = new int[k + 3];
            if (k == 0 || k >= n) {
                this.more = false;
                return;
            }
            for (int i = 1; i <= k; ++i) {
                this.c[i] = i - 1;
            }
            this.c[k + 1] = n;
            this.c[k + 2] = 0;
            this.j = k;
        }

        @Override
        public boolean hasNext() {
            return this.more;
        }

        @Override
        public int[] next() {
            if (!this.more) {
                throw new NoSuchElementException();
            }
            int[] ret = new int[this.k];
            System.arraycopy(this.c, 1, ret, 0, this.k);
            int x = 0;
            if (this.j > 0) {
                this.c[this.j] = x = this.j;
                --this.j;
                return ret;
            }
            if (this.c[1] + 1 < this.c[2]) {
                this.c[1] = this.c[1] + 1;
                return ret;
            }
            this.j = 2;
            boolean stepDone = false;
            while (!stepDone) {
                this.c[this.j - 1] = this.j - 2;
                x = this.c[this.j] + 1;
                if (x == this.c[this.j + 1]) {
                    ++this.j;
                    continue;
                }
                stepDone = true;
            }
            if (this.j > this.k) {
                this.more = false;
                return ret;
            }
            this.c[this.j] = x;
            --this.j;
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum IterationOrder {
        LEXICOGRAPHIC;

    }
}

