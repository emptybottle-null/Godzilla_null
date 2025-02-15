/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.TransformedIterator;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;

@GwtIncompatible
public final class MutableClassToInstanceMap<B>
extends ForwardingMap<Class<? extends B>, B>
implements ClassToInstanceMap<B>,
Serializable {
    private final Map<Class<? extends B>, B> delegate;

    public static <B> MutableClassToInstanceMap<B> create() {
        return new MutableClassToInstanceMap(new HashMap());
    }

    public static <B> MutableClassToInstanceMap<B> create(Map<Class<? extends B>, B> backingMap) {
        return new MutableClassToInstanceMap<B>(backingMap);
    }

    private MutableClassToInstanceMap(Map<Class<? extends B>, B> delegate) {
        this.delegate = Preconditions.checkNotNull(delegate);
    }

    @Override
    protected Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    private static <B> Map.Entry<Class<? extends B>, B> checkedEntry(final Map.Entry<Class<? extends B>, B> entry) {
        return new ForwardingMapEntry<Class<? extends B>, B>(){

            @Override
            protected Map.Entry<Class<? extends B>, B> delegate() {
                return entry;
            }

            @Override
            public B setValue(B value) {
                return super.setValue(MutableClassToInstanceMap.cast((Class)this.getKey(), value));
            }
        };
    }

    @Override
    public Set<Map.Entry<Class<? extends B>, B>> entrySet() {
        return new ForwardingSet<Map.Entry<Class<? extends B>, B>>(){

            @Override
            protected Set<Map.Entry<Class<? extends B>, B>> delegate() {
                return MutableClassToInstanceMap.this.delegate().entrySet();
            }

            @Override
            public Spliterator<Map.Entry<Class<? extends B>, B>> spliterator() {
                return CollectSpliterators.map(this.delegate().spliterator(), x$0 -> MutableClassToInstanceMap.checkedEntry(x$0));
            }

            @Override
            public Iterator<Map.Entry<Class<? extends B>, B>> iterator() {
                return new TransformedIterator<Map.Entry<Class<? extends B>, B>, Map.Entry<Class<? extends B>, B>>(this.delegate().iterator()){

                    @Override
                    Map.Entry<Class<? extends B>, B> transform(Map.Entry<Class<? extends B>, B> from) {
                        return MutableClassToInstanceMap.checkedEntry(from);
                    }
                };
            }

            @Override
            public Object[] toArray() {
                return this.standardToArray();
            }

            @Override
            public <T> T[] toArray(T[] array) {
                return this.standardToArray(array);
            }
        };
    }

    @Override
    @CanIgnoreReturnValue
    public B put(Class<? extends B> key, B value) {
        return super.put(key, MutableClassToInstanceMap.cast(key, value));
    }

    @Override
    public void putAll(Map<? extends Class<? extends B>, ? extends B> map) {
        LinkedHashMap<Class<B>, B> copy = new LinkedHashMap<Class<B>, B>(map);
        for (Map.Entry entry : copy.entrySet()) {
            MutableClassToInstanceMap.cast((Class)entry.getKey(), entry.getValue());
        }
        super.putAll(copy);
    }

    @Override
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> type, T value) {
        return MutableClassToInstanceMap.cast(type, this.put(type, value));
    }

    @Override
    public <T extends B> T getInstance(Class<T> type) {
        return MutableClassToInstanceMap.cast(type, this.get(type));
    }

    @CanIgnoreReturnValue
    private static <B, T extends B> T cast(Class<T> type, B value) {
        return Primitives.wrap(type).cast(value);
    }

    private Object writeReplace() {
        return new SerializedForm(this.delegate());
    }

    private static final class SerializedForm<B>
    implements Serializable {
        private final Map<Class<? extends B>, B> backingMap;
        private static final long serialVersionUID = 0L;

        SerializedForm(Map<Class<? extends B>, B> backingMap) {
            this.backingMap = backingMap;
        }

        Object readResolve() {
            return MutableClassToInstanceMap.create(this.backingMap);
        }
    }
}

