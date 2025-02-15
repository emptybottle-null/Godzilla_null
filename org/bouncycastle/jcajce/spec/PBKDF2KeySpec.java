/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2KeySpec
extends PBEKeySpec {
    private static final AlgorithmIdentifier defaultPRF = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    private AlgorithmIdentifier prf;

    public PBKDF2KeySpec(char[] cArray, byte[] byArray, int n, int n2, AlgorithmIdentifier algorithmIdentifier) {
        super(cArray, byArray, n, n2);
        this.prf = algorithmIdentifier;
    }

    public boolean isDefaultPrf() {
        return defaultPRF.equals(this.prf);
    }

    public AlgorithmIdentifier getPrf() {
        return this.prf;
    }
}

