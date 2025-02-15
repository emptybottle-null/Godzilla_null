/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.DoubleArray;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class ResizableDoubleArray
implements DoubleArray,
Serializable {
    @Deprecated
    public static final int ADDITIVE_MODE = 1;
    @Deprecated
    public static final int MULTIPLICATIVE_MODE = 0;
    private static final long serialVersionUID = -3485529955529426875L;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_EXPANSION_FACTOR = 2.0;
    private static final double DEFAULT_CONTRACTION_DELTA = 0.5;
    private double contractionCriterion = 2.5;
    private double expansionFactor = 2.0;
    private ExpansionMode expansionMode = ExpansionMode.MULTIPLICATIVE;
    private double[] internalArray;
    private int numElements = 0;
    private int startIndex = 0;

    public ResizableDoubleArray() {
        this(16);
    }

    public ResizableDoubleArray(int initialCapacity) throws MathIllegalArgumentException {
        this(initialCapacity, 2.0);
    }

    public ResizableDoubleArray(double[] initialArray) {
        this(16, 2.0, 2.5, ExpansionMode.MULTIPLICATIVE, initialArray);
    }

    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor) throws MathIllegalArgumentException {
        this(initialCapacity, (double)expansionFactor);
    }

    public ResizableDoubleArray(int initialCapacity, double expansionFactor) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, 0.5 + expansionFactor);
    }

    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria) throws MathIllegalArgumentException {
        this(initialCapacity, (double)expansionFactor, (double)contractionCriteria);
    }

    public ResizableDoubleArray(int initialCapacity, double expansionFactor, double contractionCriterion) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, contractionCriterion, ExpansionMode.MULTIPLICATIVE, null);
    }

    @Deprecated
    public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria, int expansionMode) throws MathIllegalArgumentException {
        this(initialCapacity, expansionFactor, contractionCriteria, expansionMode == 1 ? ExpansionMode.ADDITIVE : ExpansionMode.MULTIPLICATIVE, null);
        this.setExpansionMode(expansionMode);
    }

    public ResizableDoubleArray(int initialCapacity, double expansionFactor, double contractionCriterion, ExpansionMode expansionMode, double ... data) throws MathIllegalArgumentException {
        if (initialCapacity <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, initialCapacity);
        }
        this.checkContractExpand(contractionCriterion, expansionFactor);
        this.expansionFactor = expansionFactor;
        this.contractionCriterion = contractionCriterion;
        this.expansionMode = expansionMode;
        this.internalArray = new double[initialCapacity];
        this.numElements = 0;
        this.startIndex = 0;
        if (data != null && data.length > 0) {
            this.addElements(data);
        }
    }

    public ResizableDoubleArray(ResizableDoubleArray original) throws NullArgumentException {
        MathUtils.checkNotNull(original);
        ResizableDoubleArray.copy(original, this);
    }

    public synchronized void addElement(double value) {
        if (this.internalArray.length <= this.startIndex + this.numElements) {
            this.expand();
        }
        this.internalArray[this.startIndex + this.numElements++] = value;
    }

    public synchronized void addElements(double[] values) {
        double[] tempArray = new double[this.numElements + values.length + 1];
        System.arraycopy(this.internalArray, this.startIndex, tempArray, 0, this.numElements);
        System.arraycopy(values, 0, tempArray, this.numElements, values.length);
        this.internalArray = tempArray;
        this.startIndex = 0;
        this.numElements += values.length;
    }

    public synchronized double addElementRolling(double value) {
        double discarded = this.internalArray[this.startIndex];
        if (this.startIndex + (this.numElements + 1) > this.internalArray.length) {
            this.expand();
        }
        ++this.startIndex;
        this.internalArray[this.startIndex + (this.numElements - 1)] = value;
        if (this.shouldContract()) {
            this.contract();
        }
        return discarded;
    }

    public synchronized double substituteMostRecentElement(double value) throws MathIllegalStateException {
        if (this.numElements < 1) {
            throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY, new Object[0]);
        }
        int substIndex = this.startIndex + (this.numElements - 1);
        double discarded = this.internalArray[substIndex];
        this.internalArray[substIndex] = value;
        return discarded;
    }

    @Deprecated
    protected void checkContractExpand(float contraction, float expansion) throws MathIllegalArgumentException {
        this.checkContractExpand((double)contraction, (double)expansion);
    }

    protected void checkContractExpand(double contraction, double expansion) throws NumberIsTooSmallException {
        if (contraction < expansion) {
            NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, (Number)1, true);
            e.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, contraction, expansion);
            throw e;
        }
        if (contraction <= 1.0) {
            NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, (Number)1, false);
            e.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, contraction);
            throw e;
        }
        if (expansion <= 1.0) {
            NumberIsTooSmallException e = new NumberIsTooSmallException(contraction, (Number)1, false);
            e.getContext().addMessage(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, expansion);
            throw e;
        }
    }

    public synchronized void clear() {
        this.numElements = 0;
        this.startIndex = 0;
    }

    public synchronized void contract() {
        double[] tempArray = new double[this.numElements + 1];
        System.arraycopy(this.internalArray, this.startIndex, tempArray, 0, this.numElements);
        this.internalArray = tempArray;
        this.startIndex = 0;
    }

    public synchronized void discardFrontElements(int i) throws MathIllegalArgumentException {
        this.discardExtremeElements(i, true);
    }

    public synchronized void discardMostRecentElements(int i) throws MathIllegalArgumentException {
        this.discardExtremeElements(i, false);
    }

    private synchronized void discardExtremeElements(int i, boolean front) throws MathIllegalArgumentException {
        if (i > this.numElements) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, i, this.numElements);
        }
        if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, i);
        }
        this.numElements -= i;
        if (front) {
            this.startIndex += i;
        }
        if (this.shouldContract()) {
            this.contract();
        }
    }

    protected synchronized void expand() {
        int newSize = 0;
        newSize = this.expansionMode == ExpansionMode.MULTIPLICATIVE ? (int)FastMath.ceil((double)this.internalArray.length * this.expansionFactor) : (int)((long)this.internalArray.length + FastMath.round(this.expansionFactor));
        double[] tempArray = new double[newSize];
        System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
        this.internalArray = tempArray;
    }

    private synchronized void expandTo(int size) {
        double[] tempArray = new double[size];
        System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
        this.internalArray = tempArray;
    }

    @Deprecated
    public float getContractionCriteria() {
        return (float)this.getContractionCriterion();
    }

    public double getContractionCriterion() {
        return this.contractionCriterion;
    }

    public synchronized double getElement(int index) {
        if (index >= this.numElements) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index >= 0) {
            return this.internalArray[this.startIndex + index];
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    public synchronized double[] getElements() {
        double[] elementArray = new double[this.numElements];
        System.arraycopy(this.internalArray, this.startIndex, elementArray, 0, this.numElements);
        return elementArray;
    }

    @Deprecated
    public float getExpansionFactor() {
        return (float)this.expansionFactor;
    }

    @Deprecated
    public int getExpansionMode() {
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            switch (this.expansionMode) {
                case MULTIPLICATIVE: {
                    return 0;
                }
                case ADDITIVE: {
                    return 1;
                }
            }
            throw new MathInternalError();
        }
    }

    @Deprecated
    synchronized int getInternalLength() {
        return this.internalArray.length;
    }

    public int getCapacity() {
        return this.internalArray.length;
    }

    public synchronized int getNumElements() {
        return this.numElements;
    }

    @Deprecated
    public synchronized double[] getInternalValues() {
        return this.internalArray;
    }

    protected double[] getArrayRef() {
        return this.internalArray;
    }

    protected int getStartIndex() {
        return this.startIndex;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public void setContractionCriteria(float contractionCriteria) throws MathIllegalArgumentException {
        this.checkContractExpand(contractionCriteria, this.getExpansionFactor());
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            this.contractionCriterion = contractionCriteria;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double compute(MathArrays.Function f) {
        int num;
        int start;
        double[] array;
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            array = this.internalArray;
            start = this.startIndex;
            num = this.numElements;
        }
        return f.evaluate(array, start, num);
    }

    public synchronized void setElement(int index, double value) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index + 1 > this.numElements) {
            this.numElements = index + 1;
        }
        if (this.startIndex + index >= this.internalArray.length) {
            this.expandTo(this.startIndex + (index + 1));
        }
        this.internalArray[this.startIndex + index] = value;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public void setExpansionFactor(float expansionFactor) throws MathIllegalArgumentException {
        this.checkContractExpand(this.getContractionCriterion(), (double)expansionFactor);
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            this.expansionFactor = expansionFactor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public void setExpansionMode(int expansionMode) throws MathIllegalArgumentException {
        if (expansionMode != 0 && expansionMode != 1) {
            throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, expansionMode, 0, "MULTIPLICATIVE_MODE", 1, "ADDITIVE_MODE");
        }
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            if (expansionMode == 0) {
                this.setExpansionMode(ExpansionMode.MULTIPLICATIVE);
            } else if (expansionMode == 1) {
                this.setExpansionMode(ExpansionMode.ADDITIVE);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public void setExpansionMode(ExpansionMode expansionMode) {
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            this.expansionMode = expansionMode;
        }
    }

    @Deprecated
    protected void setInitialCapacity(int initialCapacity) throws MathIllegalArgumentException {
    }

    public synchronized void setNumElements(int i) throws MathIllegalArgumentException {
        if (i < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, i);
        }
        int newSize = this.startIndex + i;
        if (newSize > this.internalArray.length) {
            this.expandTo(newSize);
        }
        this.numElements = i;
    }

    private synchronized boolean shouldContract() {
        if (this.expansionMode == ExpansionMode.MULTIPLICATIVE) {
            return (double)((float)this.internalArray.length / (float)this.numElements) > this.contractionCriterion;
        }
        return (double)(this.internalArray.length - this.numElements) > this.contractionCriterion;
    }

    @Deprecated
    public synchronized int start() {
        return this.startIndex;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void copy(ResizableDoubleArray source, ResizableDoubleArray dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        ResizableDoubleArray resizableDoubleArray = source;
        synchronized (resizableDoubleArray) {
            ResizableDoubleArray resizableDoubleArray2 = dest;
            synchronized (resizableDoubleArray2) {
                dest.contractionCriterion = source.contractionCriterion;
                dest.expansionFactor = source.expansionFactor;
                dest.expansionMode = source.expansionMode;
                dest.internalArray = new double[source.internalArray.length];
                System.arraycopy(source.internalArray, 0, dest.internalArray, 0, dest.internalArray.length);
                dest.numElements = source.numElements;
                dest.startIndex = source.startIndex;
            }
        }
    }

    public synchronized ResizableDoubleArray copy() {
        ResizableDoubleArray result = new ResizableDoubleArray();
        ResizableDoubleArray.copy(this, result);
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ResizableDoubleArray)) {
            return false;
        }
        ResizableDoubleArray resizableDoubleArray = this;
        synchronized (resizableDoubleArray) {
            Object object2 = object;
            synchronized (object2) {
                boolean result = true;
                ResizableDoubleArray other = (ResizableDoubleArray)object;
                result = result && other.contractionCriterion == this.contractionCriterion;
                result = result && other.expansionFactor == this.expansionFactor;
                result = result && other.expansionMode == this.expansionMode;
                result = result && other.numElements == this.numElements;
                boolean bl = result = result && other.startIndex == this.startIndex;
                if (!result) {
                    return false;
                }
                return Arrays.equals(this.internalArray, other.internalArray);
            }
        }
    }

    public synchronized int hashCode() {
        int[] hashData = new int[]{Double.valueOf(this.expansionFactor).hashCode(), Double.valueOf(this.contractionCriterion).hashCode(), this.expansionMode.hashCode(), Arrays.hashCode(this.internalArray), this.numElements, this.startIndex};
        return Arrays.hashCode(hashData);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum ExpansionMode {
        MULTIPLICATIVE,
        ADDITIVE;

    }
}

