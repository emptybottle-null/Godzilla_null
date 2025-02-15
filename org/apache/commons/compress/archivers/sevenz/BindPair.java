/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

class BindPair {
    long inIndex;
    long outIndex;

    BindPair() {
    }

    public String toString() {
        return "BindPair binding input " + this.inIndex + " to output " + this.outIndex;
    }
}

