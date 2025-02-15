/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import javax.xml.namespace.QName;

public final class QNameCache {
    private static final float DEFAULT_LOAD = 0.7f;
    private final float loadFactor;
    private int numEntries = 0;
    private int threshold;
    private int hashmask;
    private QName[] table;

    public QNameCache(int initialCapacity, float loadFactor) {
        int capacity;
        assert (initialCapacity > 0);
        assert (loadFactor > 0.0f && loadFactor < 1.0f);
        for (capacity = 16; capacity < initialCapacity; capacity <<= 1) {
        }
        this.loadFactor = loadFactor;
        this.hashmask = capacity - 1;
        this.threshold = (int)((float)capacity * loadFactor);
        this.table = new QName[capacity];
    }

    public QNameCache(int initialCapacity) {
        this(initialCapacity, 0.7f);
    }

    public QName getName(String uri, String localName) {
        return this.getName(uri, localName, "");
    }

    public QName getName(String uri, String localName, String prefix) {
        assert (localName != null);
        if (uri == null) {
            uri = "";
        }
        if (prefix == null) {
            prefix = "";
        }
        int index = QNameCache.hash(uri, localName, prefix) & this.hashmask;
        while (true) {
            QName q;
            if ((q = this.table[index]) == null) {
                ++this.numEntries;
                if (this.numEntries >= this.threshold) {
                    this.rehash();
                }
                this.table[index] = new QName(uri, localName, prefix);
                return this.table[index];
            }
            if (QNameCache.equals(q, uri, localName, prefix)) {
                return q;
            }
            index = index - 1 & this.hashmask;
        }
    }

    private void rehash() {
        int newLength = this.table.length * 2;
        QName[] newTable = new QName[newLength];
        int newHashmask = newLength - 1;
        for (int i = 0; i < this.table.length; ++i) {
            QName q = this.table[i];
            if (q == null) continue;
            int newIndex = QNameCache.hash(q.getNamespaceURI(), q.getLocalPart(), q.getPrefix()) & newHashmask;
            while (newTable[newIndex] != null) {
                newIndex = newIndex - 1 & newHashmask;
            }
            newTable[newIndex] = q;
        }
        this.table = newTable;
        this.hashmask = newHashmask;
        this.threshold = (int)((float)newLength * this.loadFactor);
    }

    private static int hash(String uri, String localName, String prefix) {
        int h = 0;
        h += prefix.hashCode() << 10;
        h += uri.hashCode() << 5;
        return h += localName.hashCode();
    }

    private static boolean equals(QName q, String uri, String localName, String prefix) {
        return q.getLocalPart().equals(localName) && q.getNamespaceURI().equals(uri) && q.getPrefix().equals(prefix);
    }
}

