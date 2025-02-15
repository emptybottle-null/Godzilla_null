/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

public final class XMSSNode
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int height;
    private final byte[] value;

    protected XMSSNode(int n, byte[] byArray) {
        this.height = n;
        this.value = byArray;
    }

    public int getHeight() {
        return this.height;
    }

    public byte[] getValue() {
        return XMSSUtil.cloneArray(this.value);
    }

    protected XMSSNode clone() {
        return new XMSSNode(this.getHeight(), this.getValue());
    }
}

