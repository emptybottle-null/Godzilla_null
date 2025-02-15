/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.util.Pack;

public abstract class XMSSAddress {
    private final int layerAddress;
    private final long treeAddress;
    private final int type;
    private final int keyAndMask;

    protected XMSSAddress(Builder builder) {
        this.layerAddress = builder.layerAddress;
        this.treeAddress = builder.treeAddress;
        this.type = builder.type;
        this.keyAndMask = builder.keyAndMask;
    }

    protected byte[] toByteArray() {
        byte[] byArray = new byte[32];
        Pack.intToBigEndian(this.layerAddress, byArray, 0);
        Pack.longToBigEndian(this.treeAddress, byArray, 4);
        Pack.intToBigEndian(this.type, byArray, 12);
        Pack.intToBigEndian(this.keyAndMask, byArray, 28);
        return byArray;
    }

    protected final int getLayerAddress() {
        return this.layerAddress;
    }

    protected final long getTreeAddress() {
        return this.treeAddress;
    }

    public final int getType() {
        return this.type;
    }

    public final int getKeyAndMask() {
        return this.keyAndMask;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected static abstract class Builder<T extends Builder> {
        private final int type;
        private int layerAddress = 0;
        private long treeAddress = 0L;
        private int keyAndMask = 0;

        protected Builder(int n) {
            this.type = n;
        }

        protected T withLayerAddress(int n) {
            this.layerAddress = n;
            return this.getThis();
        }

        protected T withTreeAddress(long l) {
            this.treeAddress = l;
            return this.getThis();
        }

        protected T withKeyAndMask(int n) {
            this.keyAndMask = n;
            return this.getThis();
        }

        protected abstract XMSSAddress build();

        protected abstract T getThis();
    }
}

