/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

class SerialVersionUIDAdder$Item
implements Comparable {
    String name;
    int access;
    String desc;

    SerialVersionUIDAdder$Item(String string, int n, String string2) {
        this.name = string;
        this.access = n;
        this.desc = string2;
    }

    public int compareTo(Object object) {
        SerialVersionUIDAdder$Item serialVersionUIDAdder$Item = (SerialVersionUIDAdder$Item)object;
        int n = this.name.compareTo(serialVersionUIDAdder$Item.name);
        if (n == 0) {
            n = this.desc.compareTo(serialVersionUIDAdder$Item.desc);
        }
        return n;
    }
}

