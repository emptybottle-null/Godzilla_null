/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.map.AbstractMapDecorator;

public class PassiveExpiringMap<K, V>
extends AbstractMapDecorator<K, V>
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<Object, Long> expirationMap = new HashMap<Object, Long>();
    private final ExpirationPolicy<K, V> expiringPolicy;

    private static long validateAndConvertToMillis(long timeToLive, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new NullPointerException("Time unit must not be null");
        }
        return TimeUnit.MILLISECONDS.convert(timeToLive, timeUnit);
    }

    public PassiveExpiringMap() {
        this(-1L);
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expiringPolicy) {
        this(expiringPolicy, new HashMap());
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expiringPolicy, Map<K, V> map) {
        super(map);
        if (expiringPolicy == null) {
            throw new NullPointerException("Policy must not be null.");
        }
        this.expiringPolicy = expiringPolicy;
    }

    public PassiveExpiringMap(long timeToLiveMillis) {
        this(new ConstantTimeToLiveExpirationPolicy(timeToLiveMillis), new HashMap());
    }

    public PassiveExpiringMap(long timeToLiveMillis, Map<K, V> map) {
        this(new ConstantTimeToLiveExpirationPolicy(timeToLiveMillis), map);
    }

    public PassiveExpiringMap(long timeToLive, TimeUnit timeUnit) {
        this(PassiveExpiringMap.validateAndConvertToMillis(timeToLive, timeUnit));
    }

    public PassiveExpiringMap(long timeToLive, TimeUnit timeUnit, Map<K, V> map) {
        this(PassiveExpiringMap.validateAndConvertToMillis(timeToLive, timeUnit), map);
    }

    public PassiveExpiringMap(Map<K, V> map) {
        this(-1L, map);
    }

    @Override
    public void clear() {
        super.clear();
        this.expirationMap.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        this.removeIfExpired(key, this.now());
        return super.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        this.removeAllExpired(this.now());
        return super.containsValue(value);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        this.removeAllExpired(this.now());
        return super.entrySet();
    }

    @Override
    public V get(Object key) {
        this.removeIfExpired(key, this.now());
        return super.get(key);
    }

    @Override
    public boolean isEmpty() {
        this.removeAllExpired(this.now());
        return super.isEmpty();
    }

    private boolean isExpired(long now, Long expirationTimeObject) {
        if (expirationTimeObject != null) {
            long expirationTime = expirationTimeObject;
            return expirationTime >= 0L && now >= expirationTime;
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        this.removeAllExpired(this.now());
        return super.keySet();
    }

    private long now() {
        return System.currentTimeMillis();
    }

    @Override
    public V put(K key, V value) {
        this.removeIfExpired(key, this.now());
        long expirationTime = this.expiringPolicy.expirationTime(key, value);
        this.expirationMap.put(key, expirationTime);
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        for (Map.Entry<K, V> entry : mapToCopy.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        this.expirationMap.remove(key);
        return super.remove(key);
    }

    private void removeAllExpired(long now) {
        Iterator<Map.Entry<Object, Long>> iter = this.expirationMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Object, Long> expirationEntry = iter.next();
            if (!this.isExpired(now, expirationEntry.getValue())) continue;
            super.remove(expirationEntry.getKey());
            iter.remove();
        }
    }

    private void removeIfExpired(Object key, long now) {
        Long expirationTimeObject = this.expirationMap.get(key);
        if (this.isExpired(now, expirationTimeObject)) {
            this.remove(key);
        }
    }

    @Override
    public int size() {
        this.removeAllExpired(this.now());
        return super.size();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map)in.readObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }

    @Override
    public Collection<V> values() {
        this.removeAllExpired(this.now());
        return super.values();
    }

    @FunctionalInterface
    public static interface ExpirationPolicy<K, V>
    extends Serializable {
        public long expirationTime(K var1, V var2);
    }

    public static class ConstantTimeToLiveExpirationPolicy<K, V>
    implements ExpirationPolicy<K, V> {
        private static final long serialVersionUID = 1L;
        private final long timeToLiveMillis;

        public ConstantTimeToLiveExpirationPolicy() {
            this(-1L);
        }

        public ConstantTimeToLiveExpirationPolicy(long timeToLiveMillis) {
            this.timeToLiveMillis = timeToLiveMillis;
        }

        public ConstantTimeToLiveExpirationPolicy(long timeToLive, TimeUnit timeUnit) {
            this(PassiveExpiringMap.validateAndConvertToMillis(timeToLive, timeUnit));
        }

        @Override
        public long expirationTime(K key, V value) {
            if (this.timeToLiveMillis >= 0L) {
                long now = System.currentTimeMillis();
                if (now > Long.MAX_VALUE - this.timeToLiveMillis) {
                    return -1L;
                }
                return now + this.timeToLiveMillis;
            }
            return -1L;
        }
    }
}

