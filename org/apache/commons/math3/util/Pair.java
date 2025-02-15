/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public Pair(Pair<? extends K, ? extends V> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public K getFirst() {
        return this.key;
    }

    public V getSecond() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair oP = (Pair)o;
        return (this.key == null ? oP.key == null : this.key.equals(oP.key)) && (this.value == null ? oP.value == null : this.value.equals(oP.value));
    }

    public int hashCode() {
        int result = this.key == null ? 0 : this.key.hashCode();
        int h = this.value == null ? 0 : this.value.hashCode();
        result = 37 * result + h ^ h >>> 16;
        return result;
    }

    public String toString() {
        return "[" + this.getKey() + ", " + this.getValue() + "]";
    }

    public static <K, V> Pair<K, V> create(K k, V v) {
        return new Pair<K, V>(k, v);
    }
}

