/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class XMSSReducedSignature
implements XMSSStoreableObjectInterface {
    private final XMSSParameters params;
    private final WOTSPlusSignature wotsPlusSignature;
    private final List<XMSSNode> authPath;

    protected XMSSReducedSignature(Builder builder) {
        this.params = builder.params;
        if (this.params == null) {
            throw new NullPointerException("params == null");
        }
        int n = this.params.getDigestSize();
        int n2 = this.params.getWOTSPlus().getParams().getLen();
        int n3 = this.params.getHeight();
        byte[] byArray = builder.reducedSignature;
        if (byArray != null) {
            int n4 = n2 * n;
            int n5 = n3 * n;
            int n6 = n4 + n5;
            if (byArray.length != n6) {
                throw new IllegalArgumentException("signature has wrong size");
            }
            int n7 = 0;
            byte[][] byArrayArray = new byte[n2][];
            for (int i = 0; i < byArrayArray.length; ++i) {
                byArrayArray[i] = XMSSUtil.extractBytesAtOffset(byArray, n7, n);
                n7 += n;
            }
            this.wotsPlusSignature = new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), byArrayArray);
            ArrayList<XMSSNode> arrayList = new ArrayList<XMSSNode>();
            for (int i = 0; i < n3; ++i) {
                arrayList.add(new XMSSNode(i, XMSSUtil.extractBytesAtOffset(byArray, n7, n)));
                n7 += n;
            }
            this.authPath = arrayList;
        } else {
            WOTSPlusSignature wOTSPlusSignature = builder.wotsPlusSignature;
            this.wotsPlusSignature = wOTSPlusSignature != null ? wOTSPlusSignature : new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), new byte[n2][n]);
            List list = builder.authPath;
            if (list != null) {
                if (list.size() != n3) {
                    throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
                }
                this.authPath = list;
            } else {
                this.authPath = new ArrayList<XMSSNode>();
            }
        }
    }

    @Override
    public byte[] toByteArray() {
        int n;
        int n2 = this.params.getDigestSize();
        int n3 = this.params.getWOTSPlus().getParams().getLen() * n2;
        int n4 = this.params.getHeight() * n2;
        int n5 = n3 + n4;
        byte[] byArray = new byte[n5];
        int n6 = 0;
        byte[][] byArray2 = this.wotsPlusSignature.toByteArray();
        for (n = 0; n < byArray2.length; ++n) {
            XMSSUtil.copyBytesAtOffset(byArray, byArray2[n], n6);
            n6 += n2;
        }
        for (n = 0; n < this.authPath.size(); ++n) {
            byte[] byArray3 = this.authPath.get(n).getValue();
            XMSSUtil.copyBytesAtOffset(byArray, byArray3, n6);
            n6 += n2;
        }
        return byArray;
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public WOTSPlusSignature getWOTSPlusSignature() {
        return this.wotsPlusSignature;
    }

    public List<XMSSNode> getAuthPath() {
        return this.authPath;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Builder {
        private final XMSSParameters params;
        private WOTSPlusSignature wotsPlusSignature = null;
        private List<XMSSNode> authPath = null;
        private byte[] reducedSignature = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public Builder withWOTSPlusSignature(WOTSPlusSignature wOTSPlusSignature) {
            this.wotsPlusSignature = wOTSPlusSignature;
            return this;
        }

        public Builder withAuthPath(List<XMSSNode> list) {
            this.authPath = list;
            return this;
        }

        public Builder withReducedSignature(byte[] byArray) {
            this.reducedSignature = XMSSUtil.cloneArray(byArray);
            return this;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }
    }
}

