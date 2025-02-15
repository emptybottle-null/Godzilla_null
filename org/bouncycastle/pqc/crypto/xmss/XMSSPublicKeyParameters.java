/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

public final class XMSSPublicKeyParameters
extends AsymmetricKeyParameter
implements XMSSStoreableObjectInterface {
    private final XMSSParameters params;
    private final byte[] root;
    private final byte[] publicSeed;

    private XMSSPublicKeyParameters(Builder builder) {
        super(false);
        this.params = builder.params;
        if (this.params == null) {
            throw new NullPointerException("params == null");
        }
        int n = this.params.getDigestSize();
        byte[] byArray = builder.publicKey;
        if (byArray != null) {
            int n2 = n;
            int n3 = n;
            int n4 = n2 + n3;
            if (byArray.length != n4) {
                throw new IllegalArgumentException("public key has wrong size");
            }
            int n5 = 0;
            this.root = XMSSUtil.extractBytesAtOffset(byArray, n5, n2);
            this.publicSeed = XMSSUtil.extractBytesAtOffset(byArray, n5 += n2, n3);
        } else {
            byte[] byArray2 = builder.root;
            if (byArray2 != null) {
                if (byArray2.length != n) {
                    throw new IllegalArgumentException("length of root must be equal to length of digest");
                }
                this.root = byArray2;
            } else {
                this.root = new byte[n];
            }
            byte[] byArray3 = builder.publicSeed;
            if (byArray3 != null) {
                if (byArray3.length != n) {
                    throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
                }
                this.publicSeed = byArray3;
            } else {
                this.publicSeed = new byte[n];
            }
        }
    }

    public byte[] toByteArray() {
        int n;
        int n2 = n = this.params.getDigestSize();
        int n3 = n;
        int n4 = n2 + n3;
        byte[] byArray = new byte[n4];
        int n5 = 0;
        XMSSUtil.copyBytesAtOffset(byArray, this.root, n5);
        XMSSUtil.copyBytesAtOffset(byArray, this.publicSeed, n5 += n2);
        return byArray;
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.root);
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }

    public XMSSParameters getParameters() {
        return this.params;
    }

    public static class Builder {
        private final XMSSParameters params;
        private byte[] root = null;
        private byte[] publicSeed = null;
        private byte[] publicKey = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public Builder withRoot(byte[] byArray) {
            this.root = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public Builder withPublicSeed(byte[] byArray) {
            this.publicSeed = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public Builder withPublicKey(byte[] byArray) {
            this.publicKey = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public XMSSPublicKeyParameters build() {
            return new XMSSPublicKeyParameters(this);
        }
    }
}

