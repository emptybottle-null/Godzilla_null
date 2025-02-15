/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultidimensionalCounter
implements Iterable<Integer> {
    private final int dimension;
    private final int[] uniCounterOffset;
    private final int[] size;
    private final int totalSize;
    private final int last;

    public MultidimensionalCounter(int ... size) throws NotStrictlyPositiveException {
        this.dimension = size.length;
        this.size = MathArrays.copyOf(size);
        this.uniCounterOffset = new int[this.dimension];
        this.last = this.dimension - 1;
        int tS = size[this.last];
        for (int i = 0; i < this.last; ++i) {
            int count = 1;
            for (int j = i + 1; j < this.dimension; ++j) {
                count *= size[j];
            }
            this.uniCounterOffset[i] = count;
            tS *= size[i];
        }
        this.uniCounterOffset[this.last] = 0;
        if (tS <= 0) {
            throw new NotStrictlyPositiveException(tS);
        }
        this.totalSize = tS;
    }

    public Iterator iterator() {
        return new Iterator();
    }

    public int getDimension() {
        return this.dimension;
    }

    public int[] getCounts(int index) throws OutOfRangeException {
        if (index < 0 || index >= this.totalSize) {
            throw new OutOfRangeException(index, (Number)0, this.totalSize);
        }
        int[] indices = new int[this.dimension];
        int count = 0;
        for (int i = 0; i < this.last; ++i) {
            int idx = 0;
            int offset = this.uniCounterOffset[i];
            while (count <= index) {
                count += offset;
                ++idx;
            }
            count -= offset;
            indices[i] = --idx;
        }
        indices[this.last] = index - count;
        return indices;
    }

    public int getCount(int ... c) throws OutOfRangeException, DimensionMismatchException {
        if (c.length != this.dimension) {
            throw new DimensionMismatchException(c.length, this.dimension);
        }
        int count = 0;
        for (int i = 0; i < this.dimension; ++i) {
            int index = c[i];
            if (index < 0 || index >= this.size[i]) {
                throw new OutOfRangeException(index, (Number)0, this.size[i] - 1);
            }
            count += this.uniCounterOffset[i] * c[i];
        }
        return count + c[this.last];
    }

    public int getSize() {
        return this.totalSize;
    }

    public int[] getSizes() {
        return MathArrays.copyOf(this.size);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.dimension) {
            sb.append("[").append(this.getCount(i++)).append("]");
        }
        return sb.toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class Iterator
    implements java.util.Iterator<Integer> {
        private final int[] counter;
        private int count;
        private final int maxCount;

        Iterator() {
            this.counter = new int[MultidimensionalCounter.this.dimension];
            this.count = -1;
            this.maxCount = MultidimensionalCounter.this.totalSize - 1;
            this.counter[((MultidimensionalCounter)MultidimensionalCounter.this).last] = -1;
        }

        @Override
        public boolean hasNext() {
            return this.count < this.maxCount;
        }

        @Override
        public Integer next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            for (int i = MultidimensionalCounter.this.last; i >= 0; --i) {
                if (this.counter[i] != MultidimensionalCounter.this.size[i] - 1) {
                    int n = i;
                    this.counter[n] = this.counter[n] + 1;
                    break;
                }
                this.counter[i] = 0;
            }
            return ++this.count;
        }

        public int getCount() {
            return this.count;
        }

        public int[] getCounts() {
            return MathArrays.copyOf(this.counter);
        }

        public int getCount(int dim) {
            return this.counter[dim];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

