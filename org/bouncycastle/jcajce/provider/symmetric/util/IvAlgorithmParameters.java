/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.util.Arrays;

public class IvAlgorithmParameters
extends BaseAlgorithmParameters {
    private byte[] iv;

    protected byte[] engineGetEncoded() throws IOException {
        return this.engineGetEncoded("ASN.1");
    }

    protected byte[] engineGetEncoded(String string) throws IOException {
        if (this.isASN1FormatString(string)) {
            return new DEROctetString(this.engineGetEncoded("RAW")).getEncoded();
        }
        if (string.equals("RAW")) {
            return Arrays.clone(this.iv);
        }
        return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == IvParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
            return new IvParameterSpec(this.iv);
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
            throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
        }
        this.iv = ((IvParameterSpec)algorithmParameterSpec).getIV();
    }

    protected void engineInit(byte[] byArray) throws IOException {
        if (byArray.length % 8 != 0 && byArray[0] == 4 && byArray[1] == byArray.length - 2) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(byArray);
            byArray = aSN1OctetString.getOctets();
        }
        this.iv = Arrays.clone(byArray);
    }

    protected void engineInit(byte[] byArray, String string) throws IOException {
        if (this.isASN1FormatString(string)) {
            try {
                ASN1OctetString aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(byArray);
                this.engineInit(aSN1OctetString.getOctets());
            } catch (Exception exception) {
                throw new IOException("Exception decoding: " + exception);
            }
            return;
        }
        if (string.equals("RAW")) {
            this.engineInit(byArray);
            return;
        }
        throw new IOException("Unknown parameters format in IV parameters object");
    }

    protected String engineToString() {
        return "IV Parameters";
    }
}

