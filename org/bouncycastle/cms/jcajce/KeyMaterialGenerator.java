/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

interface KeyMaterialGenerator {
    public byte[] generateKDFMaterial(AlgorithmIdentifier var1, int var2, byte[] var3);
}

