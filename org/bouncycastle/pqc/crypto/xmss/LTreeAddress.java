/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;

final class LTreeAddress
extends XMSSAddress {
    private static final int TYPE = 1;
    private final int lTreeAddress;
    private final int treeHeight;
    private final int treeIndex;

    private LTreeAddress(Builder builder) {
        super(builder);
        this.lTreeAddress = builder.lTreeAddress;
        this.treeHeight = builder.treeHeight;
        this.treeIndex = builder.treeIndex;
    }

    protected byte[] toByteArray() {
        byte[] byArray = super.toByteArray();
        Pack.intToBigEndian(this.lTreeAddress, byArray, 16);
        Pack.intToBigEndian(this.treeHeight, byArray, 20);
        Pack.intToBigEndian(this.treeIndex, byArray, 24);
        return byArray;
    }

    protected int getLTreeAddress() {
        return this.lTreeAddress;
    }

    protected int getTreeHeight() {
        return this.treeHeight;
    }

    protected int getTreeIndex() {
        return this.treeIndex;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected static class Builder
    extends XMSSAddress.Builder<Builder> {
        private int lTreeAddress = 0;
        private int treeHeight = 0;
        private int treeIndex = 0;

        protected Builder() {
            super(1);
        }

        protected Builder withLTreeAddress(int n) {
            this.lTreeAddress = n;
            return this;
        }

        protected Builder withTreeHeight(int n) {
            this.treeHeight = n;
            return this;
        }

        protected Builder withTreeIndex(int n) {
            this.treeIndex = n;
            return this;
        }

        @Override
        protected XMSSAddress build() {
            return new LTreeAddress(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}

