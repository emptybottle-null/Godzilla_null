/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import com.google.common.cache.LongAddable;
import com.google.common.cache.LongAdder;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible(emulated=true)
final class LongAddables {
    private static final Supplier<LongAddable> SUPPLIER;

    LongAddables() {
    }

    public static LongAddable create() {
        return SUPPLIER.get();
    }

    static {
        Supplier<LongAddable> supplier;
        try {
            new LongAdder();
            supplier = new Supplier<LongAddable>(){

                @Override
                public LongAddable get() {
                    return new LongAdder();
                }
            };
        } catch (Throwable t) {
            supplier = new Supplier<LongAddable>(){

                @Override
                public LongAddable get() {
                    return new PureJavaLongAddable();
                }
            };
        }
        SUPPLIER = supplier;
    }

    private static final class PureJavaLongAddable
    extends AtomicLong
    implements LongAddable {
        private PureJavaLongAddable() {
        }

        @Override
        public void increment() {
            this.getAndIncrement();
        }

        @Override
        public void add(long x) {
            this.getAndAdd(x);
        }

        @Override
        public long sum() {
            return this.get();
        }
    }
}

