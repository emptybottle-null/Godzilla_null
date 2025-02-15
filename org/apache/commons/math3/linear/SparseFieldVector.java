/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.FieldVectorChangingVisitor;
import org.apache.commons.math3.linear.FieldVectorPreservingVisitor;
import org.apache.commons.math3.linear.SparseFieldMatrix;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SparseFieldVector<T extends FieldElement<T>>
implements FieldVector<T>,
Serializable {
    private static final long serialVersionUID = 7841233292190413362L;
    private final Field<T> field;
    private final OpenIntToFieldHashMap<T> entries;
    private final int virtualSize;

    public SparseFieldVector(Field<T> field) {
        this(field, 0);
    }

    public SparseFieldVector(Field<T> field, int dimension) {
        this.field = field;
        this.virtualSize = dimension;
        this.entries = new OpenIntToFieldHashMap<T>(field);
    }

    protected SparseFieldVector(SparseFieldVector<T> v, int resize) {
        this.field = v.field;
        this.virtualSize = v.getDimension() + resize;
        this.entries = new OpenIntToFieldHashMap<T>(v.entries);
    }

    public SparseFieldVector(Field<T> field, int dimension, int expectedSize) {
        this.field = field;
        this.virtualSize = dimension;
        this.entries = new OpenIntToFieldHashMap<T>(field, expectedSize);
    }

    public SparseFieldVector(Field<T> field, T[] values) throws NullArgumentException {
        MathUtils.checkNotNull(values);
        this.field = field;
        this.virtualSize = values.length;
        this.entries = new OpenIntToFieldHashMap<T>(field);
        for (int key = 0; key < values.length; ++key) {
            T value = values[key];
            this.entries.put(key, value);
        }
    }

    public SparseFieldVector(SparseFieldVector<T> v) {
        this.field = v.field;
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToFieldHashMap<T>(super.getEntries());
    }

    private OpenIntToFieldHashMap<T> getEntries() {
        return this.entries;
    }

    @Override
    public FieldVector<T> add(SparseFieldVector<T> v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        SparseFieldVector res = (SparseFieldVector)this.copy();
        OpenIntToFieldHashMap.Iterator iter = super.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            Object value = iter.value();
            if (this.entries.containsKey(key)) {
                res.setEntry(key, (FieldElement)this.entries.get(key).add(value));
                continue;
            }
            res.setEntry(key, value);
        }
        return res;
    }

    @Override
    public FieldVector<T> append(SparseFieldVector<T> v) {
        SparseFieldVector res = new SparseFieldVector(this, v.getDimension());
        OpenIntToFieldHashMap.Iterator iter = v.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key() + this.virtualSize, iter.value());
        }
        return res;
    }

    @Override
    public FieldVector<T> append(FieldVector<T> v) {
        if (v instanceof SparseFieldVector) {
            return this.append((T)((SparseFieldVector)v));
        }
        int n = v.getDimension();
        SparseFieldVector<T> res = new SparseFieldVector<T>(this, n);
        for (int i = 0; i < n; ++i) {
            res.setEntry(i + this.virtualSize, v.getEntry(i));
        }
        return res;
    }

    @Override
    public FieldVector<T> append(T d) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        SparseFieldVector<T> res = new SparseFieldVector<T>(this, 1);
        res.setEntry(this.virtualSize, d);
        return res;
    }

    @Override
    public FieldVector<T> copy() {
        return new SparseFieldVector<T>(this);
    }

    @Override
    public T dotProduct(FieldVector<T> v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        FieldElement res = (FieldElement)this.field.getZero();
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res = (FieldElement)res.add(v.getEntry(iter.key()).multiply(iter.value()));
        }
        return (T)res;
    }

    @Override
    public FieldVector<T> ebeDivide(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        this.checkVectorDimensions(v.getDimension());
        SparseFieldVector<FieldElement> res = new SparseFieldVector<FieldElement>(this);
        OpenIntToFieldHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), (FieldElement)iter.value().divide(v.getEntry(iter.key())));
        }
        return res;
    }

    @Override
    public FieldVector<T> ebeMultiply(FieldVector<T> v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        SparseFieldVector<FieldElement> res = new SparseFieldVector<FieldElement>(this);
        OpenIntToFieldHashMap.Iterator iter = res.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res.setEntry(iter.key(), (FieldElement)iter.value().multiply(v.getEntry(iter.key())));
        }
        return res;
    }

    @Override
    @Deprecated
    public T[] getData() {
        return this.toArray();
    }

    @Override
    public int getDimension() {
        return this.virtualSize;
    }

    @Override
    public T getEntry(int index) throws OutOfRangeException {
        this.checkIndex(index);
        return this.entries.get(index);
    }

    @Override
    public Field<T> getField() {
        return this.field;
    }

    @Override
    public FieldVector<T> getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, n);
        }
        this.checkIndex(index);
        this.checkIndex(index + n - 1);
        SparseFieldVector res = new SparseFieldVector(this.field, n);
        int end = index + n;
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (key < index || key >= end) continue;
            res.setEntry(key - index, iter.value());
        }
        return res;
    }

    @Override
    public FieldVector<T> mapAdd(T d) throws NullArgumentException {
        return this.copy().mapAddToSelf(d);
    }

    @Override
    public FieldVector<T> mapAddToSelf(T d) throws NullArgumentException {
        for (int i = 0; i < this.virtualSize; ++i) {
            this.setEntry(i, (FieldElement)this.getEntry(i).add(d));
        }
        return this;
    }

    @Override
    public FieldVector<T> mapDivide(T d) throws NullArgumentException, MathArithmeticException {
        return this.copy().mapDivideToSelf(d);
    }

    @Override
    public FieldVector<T> mapDivideToSelf(T d) throws NullArgumentException, MathArithmeticException {
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.entries.put(iter.key(), (FieldElement)iter.value().divide(d));
        }
        return this;
    }

    @Override
    public FieldVector<T> mapInv() throws MathArithmeticException {
        return this.copy().mapInvToSelf();
    }

    @Override
    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        for (int i = 0; i < this.virtualSize; ++i) {
            this.setEntry(i, (FieldElement)((FieldElement)this.field.getOne()).divide(this.getEntry(i)));
        }
        return this;
    }

    @Override
    public FieldVector<T> mapMultiply(T d) throws NullArgumentException {
        return this.copy().mapMultiplyToSelf(d);
    }

    @Override
    public FieldVector<T> mapMultiplyToSelf(T d) throws NullArgumentException {
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.entries.put(iter.key(), (FieldElement)iter.value().multiply(d));
        }
        return this;
    }

    @Override
    public FieldVector<T> mapSubtract(T d) throws NullArgumentException {
        return this.copy().mapSubtractToSelf(d);
    }

    @Override
    public FieldVector<T> mapSubtractToSelf(T d) throws NullArgumentException {
        return this.mapAddToSelf((FieldElement)((FieldElement)this.field.getZero()).subtract(d));
    }

    @Override
    public FieldMatrix<T> outerProduct(SparseFieldVector<T> v) {
        int n = v.getDimension();
        SparseFieldMatrix<FieldElement> res = new SparseFieldMatrix<FieldElement>(this.field, this.virtualSize, n);
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            OpenIntToFieldHashMap.Iterator iter2 = v.entries.iterator();
            while (iter2.hasNext()) {
                iter2.advance();
                res.setEntry(iter.key(), iter2.key(), (FieldElement)iter.value().multiply(iter2.value()));
            }
        }
        return res;
    }

    @Override
    public FieldMatrix<T> outerProduct(FieldVector<T> v) {
        if (v instanceof SparseFieldVector) {
            return this.outerProduct((SparseFieldVector)v);
        }
        int n = v.getDimension();
        SparseFieldMatrix<FieldElement> res = new SparseFieldMatrix<FieldElement>(this.field, this.virtualSize, n);
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int row = iter.key();
            T value = iter.value();
            for (int col = 0; col < n; ++col) {
                res.setEntry(row, col, (FieldElement)value.multiply(v.getEntry(col)));
            }
        }
        return res;
    }

    @Override
    public FieldVector<T> projection(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        this.checkVectorDimensions(v.getDimension());
        return v.mapMultiply((FieldElement)this.dotProduct(v).divide(v.dotProduct(v)));
    }

    @Override
    public void set(T value) {
        MathUtils.checkNotNull(value);
        for (int i = 0; i < this.virtualSize; ++i) {
            this.setEntry(i, value);
        }
    }

    @Override
    public void setEntry(int index, T value) throws NullArgumentException, OutOfRangeException {
        MathUtils.checkNotNull(value);
        this.checkIndex(index);
        this.entries.put(index, value);
    }

    @Override
    public void setSubVector(int index, FieldVector<T> v) throws OutOfRangeException {
        this.checkIndex(index);
        this.checkIndex(index + v.getDimension() - 1);
        int n = v.getDimension();
        for (int i = 0; i < n; ++i) {
            this.setEntry(i + index, v.getEntry(i));
        }
    }

    @Override
    public SparseFieldVector<T> subtract(SparseFieldVector<T> v) throws DimensionMismatchException {
        this.checkVectorDimensions(v.getDimension());
        SparseFieldVector res = (SparseFieldVector)this.copy();
        OpenIntToFieldHashMap.Iterator iter = super.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (this.entries.containsKey(key)) {
                res.setEntry(key, (FieldElement)this.entries.get(key).subtract(iter.value()));
                continue;
            }
            res.setEntry(key, (FieldElement)((FieldElement)this.field.getZero()).subtract(iter.value()));
        }
        return res;
    }

    @Override
    public FieldVector<T> subtract(FieldVector<T> v) throws DimensionMismatchException {
        if (v instanceof SparseFieldVector) {
            return this.subtract((SparseFieldVector)v);
        }
        int n = v.getDimension();
        this.checkVectorDimensions(n);
        SparseFieldVector<FieldElement> res = new SparseFieldVector<FieldElement>(this);
        for (int i = 0; i < n; ++i) {
            if (this.entries.containsKey(i)) {
                res.setEntry(i, (FieldElement)this.entries.get(i).subtract(v.getEntry(i)));
                continue;
            }
            res.setEntry(i, (FieldElement)((FieldElement)this.field.getZero()).subtract(v.getEntry(i)));
        }
        return res;
    }

    @Override
    public T[] toArray() {
        FieldElement[] res = (FieldElement[])MathArrays.buildArray(this.field, this.virtualSize);
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            res[iter.key()] = iter.value();
        }
        return res;
    }

    private void checkIndex(int index) throws OutOfRangeException {
        if (index < 0 || index >= this.getDimension()) {
            throw new OutOfRangeException(index, (Number)0, this.getDimension() - 1);
        }
    }

    private void checkIndices(int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
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

    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        if (this.getDimension() != n) {
            throw new DimensionMismatchException(this.getDimension(), n);
        }
    }

    @Override
    public FieldVector<T> add(FieldVector<T> v) throws DimensionMismatchException {
        if (v instanceof SparseFieldVector) {
            return this.add((SparseFieldVector)v);
        }
        int n = v.getDimension();
        this.checkVectorDimensions(n);
        SparseFieldVector<FieldElement> res = new SparseFieldVector<FieldElement>(this.field, this.getDimension());
        for (int i = 0; i < n; ++i) {
            res.setEntry(i, (FieldElement)v.getEntry(i).add(this.getEntry(i)));
        }
        return res;
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> visitor) {
        int dim = this.getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; ++i) {
            visitor.visit(i, this.getEntry(i));
        }
        return visitor.end();
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        this.checkIndices(start, end);
        visitor.start(this.getDimension(), start, end);
        for (int i = start; i <= end; ++i) {
            visitor.visit(i, this.getEntry(i));
        }
        return visitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> visitor) {
        return this.walkInDefaultOrder(visitor);
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return this.walkInDefaultOrder(visitor, start, end);
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> visitor) {
        int dim = this.getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; ++i) {
            this.setEntry(i, visitor.visit(i, this.getEntry(i)));
        }
        return visitor.end();
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        this.checkIndices(start, end);
        visitor.start(this.getDimension(), start, end);
        for (int i = start; i <= end; ++i) {
            this.setEntry(i, visitor.visit(i, this.getEntry(i)));
        }
        return visitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> visitor) {
        return this.walkInDefaultOrder(visitor);
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return this.walkInDefaultOrder(visitor, start, end);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.field == null ? 0 : this.field.hashCode());
        result = 31 * result + this.virtualSize;
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int temp = iter.value().hashCode();
            result = 31 * result + temp;
        }
        return result;
    }

    public boolean equals(Object obj) {
        T test;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SparseFieldVector)) {
            return false;
        }
        SparseFieldVector other = (SparseFieldVector)obj;
        if (this.field == null ? other.field != null : !this.field.equals(other.field)) {
            return false;
        }
        if (this.virtualSize != other.virtualSize) {
            return false;
        }
        OpenIntToFieldHashMap.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            test = other.getEntry(iter.key());
            if (test.equals(iter.value())) continue;
            return false;
        }
        iter = other.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            test = iter.value();
            if (test.equals(this.getEntry(iter.key()))) continue;
            return false;
        }
        return true;
    }
}

