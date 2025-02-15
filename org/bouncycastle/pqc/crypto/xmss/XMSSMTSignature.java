/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class XMSSMTSignature
implements XMSSStoreableObjectInterface {
    private final XMSSMTParameters params;
    private final long index;
    private final byte[] random;
    private final List<XMSSReducedSignature> reducedSignatures;

    private XMSSMTSignature(Builder builder) {
        this.params = builder.params;
        if (this.params == null) {
            throw new NullPointerException("params == null");
        }
        int n = this.params.getDigestSize();
        byte[] byArray = builder.signature;
        if (byArray != null) {
            int n2;
            int n3;
            int n4;
            int n5 = this.params.getWOTSPlus().getParams().getLen();
            int n6 = (int)Math.ceil((double)this.params.getHeight() / 8.0);
            int n7 = n6 + (n4 = n) + (n3 = (n2 = (this.params.getHeight() / this.params.getLayers() + n5) * n) * this.params.getLayers());
            if (byArray.length != n7) {
                throw new IllegalArgumentException("signature has wrong size");
            }
            int n8 = 0;
            this.index = XMSSUtil.bytesToXBigEndian(byArray, n8, n6);
            if (!XMSSUtil.isIndexValid(this.params.getHeight(), this.index)) {
                throw new IllegalArgumentException("index out of bounds");
            }
            this.random = XMSSUtil.extractBytesAtOffset(byArray, n8 += n6, n4);
            n8 += n4;
            this.reducedSignatures = new ArrayList<XMSSReducedSignature>();
            while (n8 < byArray.length) {
                XMSSReducedSignature xMSSReducedSignature = new XMSSReducedSignature.Builder(this.params.getXMSSParameters()).withReducedSignature(XMSSUtil.extractBytesAtOffset(byArray, n8, n2)).build();
                this.reducedSignatures.add(xMSSReducedSignature);
                n8 += n2;
            }
        } else {
            this.index = builder.index;
            byte[] byArray2 = builder.random;
            if (byArray2 != null) {
                if (byArray2.length != n) {
                    throw new IllegalArgumentException("size of random needs to be equal to size of digest");
                }
                this.random = byArray2;
            } else {
                this.random = new byte[n];
            }
            List<Object> list = builder.reducedSignatures;
            this.reducedSignatures = list != null ? list : new ArrayList<XMSSReducedSignature>();
        }
    }

    @Override
    public byte[] toByteArray() {
        int n = this.params.getDigestSize();
        int n2 = this.params.getWOTSPlus().getParams().getLen();
        int n3 = (int)Math.ceil((double)this.params.getHeight() / 8.0);
        int n4 = n;
        int n5 = (this.params.getHeight() / this.params.getLayers() + n2) * n;
        int n6 = n5 * this.params.getLayers();
        int n7 = n3 + n4 + n6;
        byte[] byArray = new byte[n7];
        int n8 = 0;
        byte[] byArray2 = XMSSUtil.toBytesBigEndian(this.index, n3);
        XMSSUtil.copyBytesAtOffset(byArray, byArray2, n8);
        XMSSUtil.copyBytesAtOffset(byArray, this.random, n8 += n3);
        n8 += n4;
        for (XMSSReducedSignature xMSSReducedSignature : this.reducedSignatures) {
            byte[] byArray3 = xMSSReducedSignature.toByteArray();
            XMSSUtil.copyBytesAtOffset(byArray, byArray3, n8);
            n8 += n5;
        }
        return byArray;
    }

    public long getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    public List<XMSSReducedSignature> getReducedSignatures() {
        return this.reducedSignatures;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final XMSSMTParameters params;
        private long index = 0L;
        private byte[] random = null;
        private List<XMSSReducedSignature> reducedSignatures = null;
        private byte[] signature = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.params = xMSSMTParameters;
        }

        public Builder withIndex(long l) {
            this.index = l;
            return this;
        }

        public Builder withRandom(byte[] byArray) {
            this.random = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public Builder withReducedSignatures(List<XMSSReducedSignature> list) {
            this.reducedSignatures = list;
            return this;
        }

        public Builder withSignature(byte[] byArray) {
            this.signature = byArray;
            return this;
        }

        public XMSSMTSignature build() {
            return new XMSSMTSignature(this);
        }
    }
}

