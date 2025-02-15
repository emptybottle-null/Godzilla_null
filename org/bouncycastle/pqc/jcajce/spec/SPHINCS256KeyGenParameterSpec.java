/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class SPHINCS256KeyGenParameterSpec
implements AlgorithmParameterSpec {
    public static final String SHA512_256 = "SHA512-256";
    public static final String SHA3_256 = "SHA3-256";
    private final String treeHash;

    public SPHINCS256KeyGenParameterSpec() {
        this(SHA512_256);
    }

    public SPHINCS256KeyGenParameterSpec(String string) {
        this.treeHash = string;
    }

    public String getTreeDigest() {
        return this.treeHash;
    }
}

