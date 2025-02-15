/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SparseRealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OpenMapRealVector
extends SparseRealVector
implements Serializable {
    public static final double DEFAULT_ZERO_TOLERANCE = 1.0E-12;
    private static final long serialVersionUID = 8772222695580707260L;
    private final OpenIntToDoubleHashMap entries;
    private final int virtualSize;
    private final double epsilon;

    public OpenMapRealVector() {
        this(0, 1.0E-12);
    }

    public OpenMapRealVector(int dimension) {
        this(dimension, 1.0E-12);
    }

    public OpenMapRealVector(int dimension, double epsilon) {
        this.virtualSize = dimension;
        this.entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
    }

    protected OpenMapRealVector(OpenMapRealVector v, int resize) {
        this.virtualSize = v.getDimension() + resize;
        this.entries = new OpenIntToDoubleHashMap(v.entries);
        this.epsilon = v.epsilon;
    }

    public OpenMapRealVector(int dimension, int expectedSize) {
        this(dimension, expectedSize, 1.0E-12);
    }

    public OpenMapRealVector(int dimension, int expectedSize, double epsilon) {
        this.virtualSize = dimension;
        this.entries = new OpenIntToDoubleHashMap(expectedSize, 0.0);
        this.epsilon = epsilon;
    }

    public OpenMapRealVector(double[] values) {
        this(values, 1.0E-12);
    }

    public OpenMapRealVector(double[] values, double epsilon) {
        this.virtualSize = values.length;
        this.entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
        for (int key = 0; key < values.length; ++key) {
            double value = values[key];
            if (this.isDefaultValue(value)) continue;
            this.entries.put(key, value);
        }
    }

    public OpenMapRealVector(Double[] values) {
        this(values, 1.0E-12);
    }

    public OpenMapRealVector(Double[] values, double epsilon) {
        this.virtualSize = values.length;
        this.entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = epsilon;
        for (int key = 0; key < values.length; ++key) {
            double value = values[key];
            if (this.isDefaultValue(value)) continue;
            this.entries.put(key, value);
        }
    }

    public OpenMapRealVector(OpenMapRealVector v) {
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToDoubleHashMap(v.getEntries());
        this.epsilon = v.epsilon;
    }

    public OpenMapRealVector(RealVector v) {
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToDoubleHashMap(0.0);
        this.epsilon = 1.0E-12;
        for (int key = 0; key < this.virtualSize; ++key) {
            double value = v.getEntry(key);
            if (this.isDefaultValue(value)) continue;
            this.entries.put(key, value);
        }
    }

    private OpenIntToDoubleHashMap getEntries() {
        return this.entries;
    }

    protected boolean isDefaultValue(double value) {
        return FastMath.abs(value) < this.epsilon;
    }

    @Override
    public RealVector add(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return this.add((OpenMapRealVector)v);
        }
        return super.add(v);
    }

    public OpenMapRealVector add(OpenMapRealVector v) throws DimensionMismatchException {
        OpenIntToDoubleHashMap randomAccess;
        this.checkVectorDimensions(v.getDimension());
        boolean copyThis = this.entries.size() > v.entries.size();
        OpenMapRealVector res = copyThis ? this.copy() : v.copy();
        OpenIntToDoubleHashMap.Iterator iter = copyThis ? v.entries.iterator() : this.entries.iterator();
        OpenIntToDoubleHashMap openIntToDoubleHashMap = randomAccess = copyThis ? this.entries : v.entries;
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (randomAccess.containsKey(key)) {
                res.setEntry(key, randomAccess.get(key) + iter.value());
                continue;
            }
            res.setEntry(key, iter.value());
        }
        return res;
    }

    public OpenMapRealVector append(OpenMapRealVector v) {
        OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
        OpenIntToDoubleHashMap.Iterator iter = v.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key() + this.virtualSize, iter.value());
        }
        return res;
    }

    @Override
    public OpenMapRealVector append(RealVector v) {
        if (v instanceof OpenMapRealVector) {
            return this.append((OpenMapRealVector)v);
        }
        OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
        for (int i = 0; i < v.getDimension(); ++i) {
            res.setEntry(i + this.virtualSize, v.getEntry(i));
        }
        return res;
    }

    @Override
    public OpenMapRealVector append(double d) {
        OpenMapRealVector res = new OpenMapRealVector(this, 1);
        res.setEntry(this.virtualSize, d);
        return res;
    }

    @Override
    public OpenMapRealVector copy() {
        return new OpenMapRealVector(this);
    }

    @Deprecated
    public double dotProduct(OpenMapRealVector v) throws DimensionMismatchException {
        return this.dotProduct((RealVector)v);
    }

    @Override
    public OpenMapRealVector ebeDivide(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        int n = this.getDimension();
        for (int i = 0; i < n; ++i) {
            res.setEntry(i, this.getEntry(i) / v.getEntry(i));
        }
        return res;
    }

    @Override
    public OpenMapRealVector ebeMultiply(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = new OpenMapRealVector(this);
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), iter.value() * v.getEntry(iter.key()));
        }
        return res;
    }

    @Override
    public OpenMapRealVector getSubVector(int index, int n) throws NotPositiveException, OutOfRangeException {
        this.checkIndex(index);
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        this.checkIndex(index + n - 1);
        OpenMapRealVector res = new OpenMapRealVector(n);
        int end = index + n;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (key < index || key >= end) continue;
            res.setEntry(key - index, iter.value());
        }
        return res;
    }

    @Override
    public int getDimension() {
        return this.virtualSize;
    }

    public double getDistance(OpenMapRealVector v) throws DimensionMismatchException {
        int key;
        this.checkVectorDimensions(v.getDimension());
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        double res = 0.0;
        while (iter.hasNext()) {
            iter.advance();
            key = iter.key();
            double delta = iter.value() - v.getEntry(key);
            res += delta * delta;
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            key = iter.key();
            if (this.entries.containsKey(key)) continue;
            double value = iter.value();
            res += value * value;
        }
        return FastMath.sqrt(res);
    }

    @Override
    public double getDistance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return this.getDistance((OpenMapRealVector)v);
        }
        return super.getDistance(v);
    }

    @Override
    public double getEntry(int index) throws OutOfRangeException {
        this.checkIndex(index);
        return this.entries.get(index);
    }

    public double getL1Distance(OpenMapRealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        double max = 0.0;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
            max += delta;
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (this.entries.containsKey(key)) continue;
            double delta = FastMath.abs(iter.value());
            max += FastMath.abs(delta);
        }
        return max;
    }

    @Override
    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return this.getL1Distance((OpenMapRealVector)v);
        }
        return super.getL1Distance(v);
    }

    private double getLInfDistance(OpenMapRealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        double max = 0.0;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
            if (!(delta > max)) continue;
            max = delta;
        }
        iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (this.entries.containsKey(key) || !(iter.value() > max)) continue;
            max = iter.value();
        }
        return max;
    }

    @Override
    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return this.getLInfDistance((OpenMapRealVector)v);
        }
        return super.getLInfDistance(v);
    }

    @Override
    public boolean isInfinite() {
        boolean infiniteFound = false;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            double value = iter.value();
            if (Double.isNaN(value)) {
                return false;
            }
            if (!Double.isInfinite(value)) continue;
            infiniteFound = true;
        }
        return infiniteFound;
    }

    @Override
    public boolean isNaN() {
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            if (!Double.isNaN(iter.value())) continue;
            return true;
        }
        return false;
    }

    @Override
    public OpenMapRealVector mapAdd(double d) {
        return this.copy().mapAddToSelf(d);
    }

    @Override
    public OpenMapRealVector mapAddToSelf(double d) {
        for (int i = 0; i < this.virtualSize; ++i) {
            this.setEntry(i, this.getEntry(i) + d);
        }
        return this;
    }

    @Override
    public void setEntry(int index, double value) throws OutOfRangeException {
        this.checkIndex(index);
        if (!this.isDefaultValue(value)) {
            this.entries.put(index, value);
        } else if (this.entries.containsKey(index)) {
            this.entries.remove(index);
        }
    }

    @Override
    public void setSubVector(int index, RealVector v) throws OutOfRangeException {
        this.checkIndex(index);
        this.checkIndex(index + v.getDimension() - 1);
        for (int i = 0; i < v.getDimension(); ++i) {
            this.setEntry(i + index, v.getEntry(i));
        }
    }

    @Override
    public void set(double value) {
        for (int i = 0; i < this.virtualSize; ++i) {
            this.setEntry(i, value);
        }
    }

    public OpenMapRealVector subtract(OpenMapRealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = this.copy();
        OpenIntToDoubleHashMap.Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (this.entries.containsKey(key)) {
                res.setEntry(key, this.entries.get(key) - iter.value());
                continue;
            }
            res.setEntry(key, -iter.value());
        }
        return res;
    }

    @Override
    public RealVector subtract(RealVector v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        if (v instanceof OpenMapRealVector) {
            return this.subtract((OpenMapRealVector)v);
        }
        return super.subtract(v);
    }

    @Override
    public OpenMapRealVector unitVector() throws MathArithmeticException {
        OpenMapRealVector res = this.copy();
        res.unitize();
        return res;
    }

    @Override
    public void unitize() throws MathArithmeticException {
        double norm = this.getNorm();
        if (this.isDefaultValue(norm)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.entries.put(iter.key(), iter.value() / norm);
        }
    }

    @Override
    public double[] toArray() {
        double[] res = new double[this.virtualSize];
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res[iter.key()] = iter.value();
        }
        return res;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        long temp = Double.doubleToLongBits(this.epsilon);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        result = 31 * result + this.virtualSize;
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            temp = Double.doubleToLongBits(iter.value());
            result = 31 * result + (int)(temp ^ temp >> 32);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        double test;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenMapRealVector)) {
            return false;
        }
        OpenMapRealVector other = (OpenMapRealVector)obj;
        if (this.virtualSize != other.virtualSize) {
            return false;
        }
        if (Double.doubleToLongBits(this.epsilon) != Double.doubleToLongBits(other.epsilon)) {
            return false;
        }
        OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            test = other.getEntry(iter.key());
            if (Double.doubleToLongBits(test) == Double.doubleToLongBits(iter.value())) continue;
            return false;
        }
        iter = other.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            test = iter.value();
            if (Double.doubleToLongBits(test) == Double.doubleToLongBits(this.getEntry(iter.key()))) continue;
            return false;
        }
        return true;
    }

    public double getSparsity() {
        return (double)this.entries.size() / (double)this.getDimension();
    }

    @Override
    public Iterator<RealVector.Entry> sparseIterator() {
        return new OpenMapSparseIterator();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class OpenMapSparseIterator
    implements Iterator<RealVector.Entry> {
        private final OpenIntToDoubleHashMap.Iterator iter;
        private final RealVector.Entry current;

        protected OpenMapSparseIterator() {
            this.iter = OpenMapRealVector.this.entries.iterator();
            this.current = new OpenMapEntry(this.iter);
        }

        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override
        public RealVector.Entry next() {
            this.iter.advance();
            return this.current;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    protected class OpenMapEntry
    extends RealVector.Entry {
        private final OpenIntToDoubleHashMap.Iterator iter;

        protected OpenMapEntry(OpenIntToDoubleHashMap.Iterator iter) {
            super(OpenMapRealVector.this);
            this.iter = iter;
        }

        public double getValue() {
            return this.iter.value();
        }

        public void setValue(double value) {
            OpenMapRealVector.this.entries.put(this.iter.key(), value);
        }

        public int getIndex() {
            return this.iter.key();
        }
    }
}

