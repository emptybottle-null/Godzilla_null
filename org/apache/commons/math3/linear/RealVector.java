/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.function.Multiply;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.OpenMapRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVectorChangingVisitor;
import org.apache.commons.math3.linear.RealVectorPreservingVisitor;
import org.apache.commons.math3.linear.SparseRealVector;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class RealVector {
    public abstract int getDimension();

    public abstract double getEntry(int var1) throws OutOfRangeException;

    public abstract void setEntry(int var1, double var2) throws OutOfRangeException;

    public void addToEntry(int index, double increment) throws OutOfRangeException {
        this.setEntry(index, this.getEntry(index) + increment);
    }

    public abstract RealVector append(RealVector var1);

    public abstract RealVector append(double var1);

    public abstract RealVector getSubVector(int var1, int var2) throws NotPositiveException, OutOfRangeException;

    public abstract void setSubVector(int var1, RealVector var2) throws OutOfRangeException;

    public abstract boolean isNaN();

    public abstract boolean isInfinite();

    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
    }

    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        int d = this.getDimension();
        if (d != n) {
            throw new DimensionMismatchException(d, n);
        }
    }

    protected void checkIndex(int index) throws OutOfRangeException {
        if (index < 0 || index >= this.getDimension()) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.INDEX, (Number)index, 0, this.getDimension() - 1);
        }
    }

    protected void checkIndices(int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        int dim = this.getDimension();
        if (start < 0 || start >= dim) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.INDEX, (Number)start, 0, dim - 1);
        }
        if (end < 0 || end >= dim) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.INDEX, (Number)end, 0, dim - 1);
        }
        if (end < start) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, (Number)end, start, false);
        }
    }

    public RealVector add(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        RealVector result = v.copy();
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            int index = e.getIndex();
            result.setEntry(index, e.getValue() + result.getEntry(index));
        }
        return result;
    }

    public RealVector subtract(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        RealVector result = v.mapMultiply(-1.0);
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            int index = e.getIndex();
            result.setEntry(index, e.getValue() + result.getEntry(index));
        }
        return result;
    }

    public RealVector mapAdd(double d) {
        return this.copy().mapAddToSelf(d);
    }

    public RealVector mapAddToSelf(double d) {
        if (d != 0.0) {
            return this.mapToSelf(FunctionUtils.fix2ndArgument(new Add(), d));
        }
        return this;
    }

    public abstract RealVector copy();

    public double dotProduct(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        double d = 0.0;
        int n = this.getDimension();
        for (int i = 0; i < n; ++i) {
            d += this.getEntry(i) * v.getEntry(i);
        }
        return d;
    }

    public double cosine(RealVector v) throws DimensionMismatchException, MathArithmeticException {
        double norm = this.getNorm();
        double vNorm = v.getNorm();
        if (norm == 0.0 || vNorm == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return this.dotProduct(v) / (norm * vNorm);
    }

    public abstract RealVector ebeDivide(RealVector var1) throws DimensionMismatchException;

    public abstract RealVector ebeMultiply(RealVector var1) throws DimensionMismatchException;

    public double getDistance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        double d = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            double diff = e.getValue() - v.getEntry(e.getIndex());
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }

    public double getNorm() {
        double sum = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            double value = e.getValue();
            sum += value * value;
        }
        return FastMath.sqrt(sum);
    }

    public double getL1Norm() {
        double norm = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            norm += FastMath.abs(e.getValue());
        }
        return norm;
    }

    public double getLInfNorm() {
        double norm = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            norm = FastMath.max(norm, FastMath.abs(e.getValue()));
        }
        return norm;
    }

    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        double d = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            d += FastMath.abs(e.getValue() - v.getEntry(e.getIndex()));
        }
        return d;
    }

    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v);
        double d = 0.0;
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            d = FastMath.max(FastMath.abs(e.getValue() - v.getEntry(e.getIndex())), d);
        }
        return d;
    }

    public int getMinIndex() {
        int minIndex = -1;
        double minValue = Double.POSITIVE_INFINITY;
        Iterator<Entry> iterator = this.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (!(entry.getValue() <= minValue)) continue;
            minIndex = entry.getIndex();
            minValue = entry.getValue();
        }
        return minIndex;
    }

    public double getMinValue() {
        int minIndex = this.getMinIndex();
        return minIndex < 0 ? Double.NaN : this.getEntry(minIndex);
    }

    public int getMaxIndex() {
        int maxIndex = -1;
        double maxValue = Double.NEGATIVE_INFINITY;
        Iterator<Entry> iterator = this.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (!(entry.getValue() >= maxValue)) continue;
            maxIndex = entry.getIndex();
            maxValue = entry.getValue();
        }
        return maxIndex;
    }

    public double getMaxValue() {
        int maxIndex = this.getMaxIndex();
        return maxIndex < 0 ? Double.NaN : this.getEntry(maxIndex);
    }

    public RealVector mapMultiply(double d) {
        return this.copy().mapMultiplyToSelf(d);
    }

    public RealVector mapMultiplyToSelf(double d) {
        return this.mapToSelf(FunctionUtils.fix2ndArgument(new Multiply(), d));
    }

    public RealVector mapSubtract(double d) {
        return this.copy().mapSubtractToSelf(d);
    }

    public RealVector mapSubtractToSelf(double d) {
        return this.mapAddToSelf(-d);
    }

    public RealVector mapDivide(double d) {
        return this.copy().mapDivideToSelf(d);
    }

    public RealVector mapDivideToSelf(double d) {
        return this.mapToSelf(FunctionUtils.fix2ndArgument(new Divide(), d));
    }

    public RealMatrix outerProduct(RealVector v) {
        int m = this.getDimension();
        int n = v.getDimension();
        AbstractRealMatrix product = v instanceof SparseRealVector || this instanceof SparseRealVector ? new OpenMapRealMatrix(m, n) : new Array2DRowRealMatrix(m, n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                product.setEntry(i, j, this.getEntry(i) * v.getEntry(j));
            }
        }
        return product;
    }

    public RealVector projection(RealVector v) throws DimensionMismatchException, MathArithmeticException {
        double norm2 = v.dotProduct(v);
        if (norm2 == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return v.mapMultiply(this.dotProduct(v) / v.dotProduct(v));
    }

    public void set(double value) {
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            e.setValue(value);
        }
    }

    public double[] toArray() {
        int dim = this.getDimension();
        double[] values = new double[dim];
        for (int i = 0; i < dim; ++i) {
            values[i] = this.getEntry(i);
        }
        return values;
    }

    public RealVector unitVector() throws MathArithmeticException {
        double norm = this.getNorm();
        if (norm == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return this.mapDivide(norm);
    }

    public void unitize() throws MathArithmeticException {
        double norm = this.getNorm();
        if (norm == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        this.mapDivideToSelf(this.getNorm());
    }

    public Iterator<Entry> sparseIterator() {
        return new SparseEntryIterator();
    }

    public Iterator<Entry> iterator() {
        final int dim = this.getDimension();
        return new Iterator<Entry>(){
            private int i = 0;
            private Entry e = new Entry();

            @Override
            public boolean hasNext() {
                return this.i < dim;
            }

            @Override
            public Entry next() {
                if (this.i < dim) {
                    this.e.setIndex(this.i++);
                    return this.e;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }
        };
    }

    public RealVector map(UnivariateFunction function) {
        return this.copy().mapToSelf(function);
    }

    public RealVector mapToSelf(UnivariateFunction function) {
        Iterator<Entry> it = this.iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            e.setValue(function.value(e.getValue()));
        }
        return this;
    }

    public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
        return this.copy().combineToSelf(a, b, y);
    }

    public RealVector combineToSelf(double a, double b, RealVector y) throws DimensionMismatchException {
        this.checkVectorDimensions(y);
        for (int i = 0; i < this.getDimension(); ++i) {
            double xi = this.getEntry(i);
            double yi = y.getEntry(i);
            this.setEntry(i, a * xi + b * yi);
        }
        return this;
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor visitor) {
        int dim = this.getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; ++i) {
            visitor.visit(i, this.getEntry(i));
        }
        return visitor.end();
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        this.checkIndices(start, end);
        visitor.start(this.getDimension(), start, end);
        for (int i = start; i <= end; ++i) {
            visitor.visit(i, this.getEntry(i));
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor visitor) {
        return this.walkInDefaultOrder(visitor);
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return this.walkInDefaultOrder(visitor, start, end);
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor visitor) {
        int dim = this.getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; ++i) {
            this.setEntry(i, visitor.visit(i, this.getEntry(i)));
        }
        return visitor.end();
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        this.checkIndices(start, end);
        visitor.start(this.getDimension(), start, end);
        for (int i = start; i <= end; ++i) {
            this.setEntry(i, visitor.visit(i, this.getEntry(i)));
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor visitor) {
        return this.walkInDefaultOrder(visitor);
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return this.walkInDefaultOrder(visitor, start, end);
    }

    public boolean equals(Object other) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    public int hashCode() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    public static RealVector unmodifiableRealVector(final RealVector v) {
        return new RealVector(){

            @Override
            public RealVector mapToSelf(UnivariateFunction function) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealVector map(UnivariateFunction function) {
                return v.map(function);
            }

            @Override
            public Iterator<Entry> iterator() {
                final Iterator<Entry> i = v.iterator();
                return new Iterator<Entry>(){
                    private final UnmodifiableEntry e;
                    {
                        this.e = new UnmodifiableEntry();
                    }

                    @Override
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    @Override
                    public Entry next() {
                        this.e.setIndex(((Entry)i.next()).getIndex());
                        return this.e;
                    }

                    @Override
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            @Override
            public Iterator<Entry> sparseIterator() {
                final Iterator<Entry> i = v.sparseIterator();
                return new Iterator<Entry>(){
                    private final UnmodifiableEntry e;
                    {
                        this.e = new UnmodifiableEntry();
                    }

                    @Override
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    @Override
                    public Entry next() {
                        this.e.setIndex(((Entry)i.next()).getIndex());
                        return this.e;
                    }

                    @Override
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            @Override
            public RealVector copy() {
                return v.copy();
            }

            @Override
            public RealVector add(RealVector w) throws DimensionMismatchException {
                return v.add(w);
            }

            @Override
            public RealVector subtract(RealVector w) throws DimensionMismatchException {
                return v.subtract(w);
            }

            @Override
            public RealVector mapAdd(double d) {
                return v.mapAdd(d);
            }

            @Override
            public RealVector mapAddToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealVector mapSubtract(double d) {
                return v.mapSubtract(d);
            }

            @Override
            public RealVector mapSubtractToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealVector mapMultiply(double d) {
                return v.mapMultiply(d);
            }

            @Override
            public RealVector mapMultiplyToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealVector mapDivide(double d) {
                return v.mapDivide(d);
            }

            @Override
            public RealVector mapDivideToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealVector ebeMultiply(RealVector w) throws DimensionMismatchException {
                return v.ebeMultiply(w);
            }

            @Override
            public RealVector ebeDivide(RealVector w) throws DimensionMismatchException {
                return v.ebeDivide(w);
            }

            @Override
            public double dotProduct(RealVector w) throws DimensionMismatchException {
                return v.dotProduct(w);
            }

            @Override
            public double cosine(RealVector w) throws DimensionMismatchException, MathArithmeticException {
                return v.cosine(w);
            }

            @Override
            public double getNorm() {
                return v.getNorm();
            }

            @Override
            public double getL1Norm() {
                return v.getL1Norm();
            }

            @Override
            public double getLInfNorm() {
                return v.getLInfNorm();
            }

            @Override
            public double getDistance(RealVector w) throws DimensionMismatchException {
                return v.getDistance(w);
            }

            @Override
            public double getL1Distance(RealVector w) throws DimensionMismatchException {
                return v.getL1Distance(w);
            }

            @Override
            public double getLInfDistance(RealVector w) throws DimensionMismatchException {
                return v.getLInfDistance(w);
            }

            @Override
            public RealVector unitVector() throws MathArithmeticException {
                return v.unitVector();
            }

            @Override
            public void unitize() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public RealMatrix outerProduct(RealVector w) {
                return v.outerProduct(w);
            }

            @Override
            public double getEntry(int index) throws OutOfRangeException {
                return v.getEntry(index);
            }

            @Override
            public void setEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public void addToEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public int getDimension() {
                return v.getDimension();
            }

            @Override
            public RealVector append(RealVector w) {
                return v.append(w);
            }

            @Override
            public RealVector append(double d) {
                return v.append(d);
            }

            @Override
            public RealVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
                return v.getSubVector(index, n);
            }

            @Override
            public void setSubVector(int index, RealVector w) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public void set(double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override
            public double[] toArray() {
                return v.toArray();
            }

            @Override
            public boolean isNaN() {
                return v.isNaN();
            }

            @Override
            public boolean isInfinite() {
                return v.isInfinite();
            }

            @Override
            public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
                return v.combine(a, b, y);
            }

            @Override
            public RealVector combineToSelf(double a, double b, RealVector y) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            class UnmodifiableEntry
            extends Entry {
                UnmodifiableEntry() {
                }

                public double getValue() {
                    return v.getEntry(this.getIndex());
                }

                public void setValue(double value) throws MathUnsupportedOperationException {
                    throw new MathUnsupportedOperationException();
                }
            }
        };
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class SparseEntryIterator
    implements Iterator<Entry> {
        private final int dim;
        private Entry current;
        private Entry next;

        protected SparseEntryIterator() {
            this.dim = RealVector.this.getDimension();
            this.current = new Entry();
            this.next = new Entry();
            if (this.next.getValue() == 0.0) {
                this.advance(this.next);
            }
        }

        protected void advance(Entry e) {
            if (e == null) {
                return;
            }
            do {
                e.setIndex(e.getIndex() + 1);
            } while (e.getIndex() < this.dim && e.getValue() == 0.0);
            if (e.getIndex() >= this.dim) {
                e.setIndex(-1);
            }
        }

        @Override
        public boolean hasNext() {
            return this.next.getIndex() >= 0;
        }

        @Override
        public Entry next() {
            int index = this.next.getIndex();
            if (index < 0) {
                throw new NoSuchElementException();
            }
            this.current.setIndex(index);
            this.advance(this.next);
            return this.current;
        }

        @Override
        public void remove() throws MathUnsupportedOperationException {
            throw new MathUnsupportedOperationException();
        }
    }

    protected class Entry {
        private int index;

        public Entry() {
            this.setIndex(0);
        }

        public double getValue() {
            return RealVector.this.getEntry(this.getIndex());
        }

        public void setValue(double value) {
            RealVector.this.setEntry(this.getIndex(), value);
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}

