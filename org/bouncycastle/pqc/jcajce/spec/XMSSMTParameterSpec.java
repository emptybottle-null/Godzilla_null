/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class XMSSMTParameterSpec
implements AlgorithmParameterSpec {
    public static final String SHA256 = "SHA256";
    public static final String SHA512 = "SHA512";
    public static final String SHAKE128 = "SHAKE128";
    public static final String SHAKE256 = "SHAKE256";
    private final int height;
    private final int layers;
    private final String treeDigest;

    public XMSSMTParameterSpec(int n, int n2, String string) {
        this.height = n;
        this.layers = n2;
        this.treeDigest = string;
    }

    public String getTreeDigest() {
        return this.treeDigest;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLayers() {
        return this.layers;
    }
}

