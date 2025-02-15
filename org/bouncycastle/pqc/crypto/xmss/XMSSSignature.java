/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.util.Pack;

public final class XMSSSignature
extends XMSSReducedSignature
implements XMSSStoreableObjectInterface {
    private final int index;
    private final byte[] random;

    private XMSSSignature(Builder builder) {
        super(builder);
        this.index = builder.index;
        int n = this.getParams().getDigestSize();
        byte[] byArray = builder.random;
        if (byArray != null) {
            if (byArray.length != n) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            }
            this.random = byArray;
        } else {
            this.random = new byte[n];
        }
    }

    public byte[] toByteArray() {
        int n;
        int n2 = this.getParams().getDigestSize();
        int n3 = 4;
        int n4 = n2;
        int n5 = this.getParams().getWOTSPlus().getParams().getLen() * n2;
        int n6 = this.getParams().getHeight() * n2;
        int n7 = n3 + n4 + n5 + n6;
        byte[] byArray = new byte[n7];
        int n8 = 0;
        Pack.intToBigEndian(this.index, byArray, n8);
        XMSSUtil.copyBytesAtOffset(byArray, this.random, n8 += n3);
        n8 += n4;
        byte[][] byArray2 = this.getWOTSPlusSignature().toByteArray();
        for (n = 0; n < byArray2.length; ++n) {
            XMSSUtil.copyBytesAtOffset(byArray, byArray2[n], n8);
            n8 += n2;
        }
        for (n = 0; n < this.getAuthPath().size(); ++n) {
            byte[] byArray3 = this.getAuthPath().get(n).getValue();
            XMSSUtil.copyBytesAtOffset(byArray, byArray3, n8);
            n8 += n2;
        }
        return byArray;
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    public static class Builder
    extends XMSSReducedSignature.Builder {
        private final XMSSParameters params;
        private int index = 0;
        private byte[] random = null;

        public Builder(XMSSParameters xMSSParameters) {
            super(xMSSParameters);
            this.params = xMSSParameters;
        }

        public Builder withIndex(int n) {
            this.index = n;
            return this;
        }

        public Builder withRandom(byte[] byArray) {
            this.random = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public Builder withSignature(byte[] byArray) {
            if (byArray == null) {
                throw new NullPointerException("signature == null");
            }
            int n = this.params.getDigestSize();
            int n2 = this.params.getWOTSPlus().getParams().getLen();
            int n3 = this.params.getHeight();
            int n4 = 4;
            int n5 = n;
            int n6 = n2 * n;
            int n7 = n3 * n;
            int n8 = 0;
            this.index = Pack.bigEndianToInt(byArray, n8);
            this.random = XMSSUtil.extractBytesAtOffset(byArray, n8 += n4, n5);
            this.withReducedSignature(XMSSUtil.extractBytesAtOffset(byArray, n8 += n5, n6 + n7));
            return this;
        }

        public XMSSSignature build() {
            return new XMSSSignature(this);
        }
    }
}

