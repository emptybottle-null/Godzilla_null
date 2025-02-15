/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;

final class OTSHashAddress
extends XMSSAddress {
    private static final int TYPE = 0;
    private final int otsAddress;
    private final int chainAddress;
    private final int hashAddress;

    private OTSHashAddress(Builder builder) {
        super(builder);
        this.otsAddress = builder.otsAddress;
        this.chainAddress = builder.chainAddress;
        this.hashAddress = builder.hashAddress;
    }

    protected byte[] toByteArray() {
        byte[] byArray = super.toByteArray();
        Pack.intToBigEndian(this.otsAddress, byArray, 16);
        Pack.intToBigEndian(this.chainAddress, byArray, 20);
        Pack.intToBigEndian(this.hashAddress, byArray, 24);
        return byArray;
    }

    protected int getOTSAddress() {
        return this.otsAddress;
    }

    protected int getChainAddress() {
        return this.chainAddress;
    }

    protected int getHashAddress() {
        return this.hashAddress;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected static class Builder
    extends XMSSAddress.Builder<Builder> {
        private int otsAddress = 0;
        private int chainAddress = 0;
        private int hashAddress = 0;

        protected Builder() {
            super(0);
        }

        protected Builder withOTSAddress(int n) {
            this.otsAddress = n;
            return this;
        }

        protected Builder withChainAddress(int n) {
            this.chainAddress = n;
            return this;
        }

        protected Builder withHashAddress(int n) {
            this.hashAddress = n;
            return this;
        }

        @Override
        protected XMSSAddress build() {
            return new OTSHashAddress(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}

