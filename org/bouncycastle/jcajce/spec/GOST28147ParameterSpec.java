/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.util.Arrays;

public class GOST28147ParameterSpec
implements AlgorithmParameterSpec {
    private byte[] iv = null;
    private byte[] sBox = null;
    private static Map oidMappings = new HashMap();

    public GOST28147ParameterSpec(byte[] byArray) {
        this.sBox = new byte[byArray.length];
        System.arraycopy(byArray, 0, this.sBox, 0, byArray.length);
    }

    public GOST28147ParameterSpec(byte[] byArray, byte[] byArray2) {
        this(byArray);
        this.iv = new byte[byArray2.length];
        System.arraycopy(byArray2, 0, this.iv, 0, byArray2.length);
    }

    public GOST28147ParameterSpec(String string) {
        this.sBox = GOST28147Engine.getSBox(string);
    }

    public GOST28147ParameterSpec(String string, byte[] byArray) {
        this(string);
        this.iv = new byte[byArray.length];
        System.arraycopy(byArray, 0, this.iv, 0, byArray.length);
    }

    public GOST28147ParameterSpec(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) {
        this(GOST28147ParameterSpec.getName(aSN1ObjectIdentifier));
        this.iv = Arrays.clone(byArray);
    }

    public byte[] getSbox() {
        return Arrays.clone(this.sBox);
    }

    public byte[] getSBox() {
        return Arrays.clone(this.sBox);
    }

    public byte[] getIV() {
        return Arrays.clone(this.iv);
    }

    private static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = (String)oidMappings.get(aSN1ObjectIdentifier);
        if (string == null) {
            throw new IllegalArgumentException("unknown OID: " + aSN1ObjectIdentifier);
        }
        return string;
    }

    static {
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
    }
}

