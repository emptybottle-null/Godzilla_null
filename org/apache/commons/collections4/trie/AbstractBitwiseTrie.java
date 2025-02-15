/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.KeyAnalyzer;

public abstract class AbstractBitwiseTrie<K, V>
extends AbstractMap<K, V>
implements Trie<K, V>,
Serializable {
    private static final long serialVersionUID = 5826987063535505652L;
    private final KeyAnalyzer<? super K> keyAnalyzer;

    protected AbstractBitwiseTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        if (keyAnalyzer == null) {
            throw new NullPointerException("keyAnalyzer");
        }
        this.keyAnalyzer = keyAnalyzer;
    }

    protected KeyAnalyzer<? super K> getKeyAnalyzer() {
        return this.keyAnalyzer;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Trie[").append(this.size()).append("]={\n");
        for (Map.Entry entry : this.entrySet()) {
            buffer.append("  ").append(entry).append("\n");
        }
        buffer.append("}\n");
        return buffer.toString();
    }

    final K castKey(Object key) {
        return (K)key;
    }

    final int lengthInBits(K key) {
        if (key == null) {
            return 0;
        }
        return this.keyAnalyzer.lengthInBits(key);
    }

    final int bitsPerElement() {
        return this.keyAnalyzer.bitsPerElement();
    }

    final boolean isBitSet(K key, int bitIndex, int lengthInBits) {
        if (key == null) {
            return false;
        }
        return this.keyAnalyzer.isBitSet(key, bitIndex, lengthInBits);
    }

    final int bitIndex(K key, K foundKey) {
        return this.keyAnalyzer.bitIndex(key, 0, this.lengthInBits(key), foundKey, 0, this.lengthInBits(foundKey));
    }

    final boolean compareKeys(K key, K other) {
        if (key == null) {
            return other == null;
        }
        if (other == null) {
            return false;
        }
        return this.keyAnalyzer.compare(key, other) == 0;
    }

    static boolean compare(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    static abstract class BasicEntry<K, V>
    implements Map.Entry<K, V>,
    Serializable {
        private static final long serialVersionUID = -944364551314110330L;
        protected K key;
        protected V value;

        public BasicEntry(K key) {
            this.key = key;
        }

        public BasicEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V setKeyValue(K key, V value) {
            this.key = key;
            return this.setValue(value);
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V previous = this.value;
            this.value = value;
            return previous;
        }

        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry other = (Map.Entry)o;
            return AbstractBitwiseTrie.compare(this.key, other.getKey()) && AbstractBitwiseTrie.compare(this.value, other.getValue());
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}

