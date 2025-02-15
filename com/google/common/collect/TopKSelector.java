/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
final class TopKSelector<T> {
    private final int k;
    private final Comparator<? super T> comparator;
    private final T[] buffer;
    private int bufferSize;
    private @Nullable T threshold;

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int k) {
        return TopKSelector.least(k, Ordering.natural());
    }

    public static <T> TopKSelector<T> least(int k, Comparator<? super T> comparator) {
        return new TopKSelector<T>(comparator, k);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int k) {
        return TopKSelector.greatest(k, Ordering.natural());
    }

    public static <T> TopKSelector<T> greatest(int k, Comparator<? super T> comparator) {
        return new TopKSelector(Ordering.from(comparator).reverse(), k);
    }

    private TopKSelector(Comparator<? super T> comparator, int k) {
        this.comparator = Preconditions.checkNotNull(comparator, "comparator");
        this.k = k;
        Preconditions.checkArgument(k >= 0, "k must be nonnegative, was %s", k);
        this.buffer = new Object[k * 2];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public void offer(@Nullable T elem) {
        if (this.k == 0) {
            return;
        }
        if (this.bufferSize == 0) {
            this.buffer[0] = elem;
            this.threshold = elem;
            this.bufferSize = 1;
        } else if (this.bufferSize < this.k) {
            this.buffer[this.bufferSize++] = elem;
            if (this.comparator.compare(elem, this.threshold) > 0) {
                this.threshold = elem;
            }
        } else if (this.comparator.compare(elem, this.threshold) < 0) {
            this.buffer[this.bufferSize++] = elem;
            if (this.bufferSize == 2 * this.k) {
                this.trim();
            }
        }
    }

    private void trim() {
        int left = 0;
        int right = 2 * this.k - 1;
        int minThresholdPosition = 0;
        int iterations = 0;
        int maxIterations = IntMath.log2(right - left, RoundingMode.CEILING) * 3;
        while (left < right) {
            int pivotIndex = left + right + 1 >>> 1;
            int pivotNewIndex = this.partition(left, right, pivotIndex);
            if (pivotNewIndex > this.k) {
                right = pivotNewIndex - 1;
            } else {
                if (pivotNewIndex >= this.k) break;
                left = Math.max(pivotNewIndex, left + 1);
                minThresholdPosition = pivotNewIndex;
            }
            if (++iterations < maxIterations) continue;
            Arrays.sort(this.buffer, left, right, this.comparator);
            break;
        }
        this.bufferSize = this.k;
        this.threshold = this.buffer[minThresholdPosition];
        for (int i = minThresholdPosition + 1; i < this.k; ++i) {
            if (this.comparator.compare(this.buffer[i], this.threshold) <= 0) continue;
            this.threshold = this.buffer[i];
        }
    }

    private int partition(int left, int right, int pivotIndex) {
        T pivotValue = this.buffer[pivotIndex];
        this.buffer[pivotIndex] = this.buffer[right];
        int pivotNewIndex = left;
        for (int i = left; i < right; ++i) {
            if (this.comparator.compare(this.buffer[i], pivotValue) >= 0) continue;
            this.swap(pivotNewIndex, i);
            ++pivotNewIndex;
        }
        this.buffer[right] = this.buffer[pivotNewIndex];
        this.buffer[pivotNewIndex] = pivotValue;
        return pivotNewIndex;
    }

    private void swap(int i, int j) {
        T tmp = this.buffer[i];
        this.buffer[i] = this.buffer[j];
        this.buffer[j] = tmp;
    }

    TopKSelector<T> combine(TopKSelector<T> other) {
        for (int i = 0; i < other.bufferSize; ++i) {
            this.offer(other.buffer[i]);
        }
        return this;
    }

    public void offerAll(Iterable<? extends T> elements) {
        this.offerAll(elements.iterator());
    }

    public void offerAll(Iterator<? extends T> elements) {
        while (elements.hasNext()) {
            this.offer(elements.next());
        }
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        if (this.bufferSize > this.k) {
            Arrays.fill(this.buffer, this.k, this.buffer.length, null);
            this.bufferSize = this.k;
            this.threshold = this.buffer[this.k - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }
}

