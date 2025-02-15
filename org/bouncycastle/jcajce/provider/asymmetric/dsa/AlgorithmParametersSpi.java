/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.DSAParameter;

public class AlgorithmParametersSpi
extends java.security.AlgorithmParametersSpi {
    DSAParameterSpec currentSpec;

    protected boolean isASN1FormatString(String string) {
        return string == null || string.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == null) {
            throw new NullPointerException("argument to getParameterSpec must not be null");
        }
        return this.localEngineGetParameterSpec(clazz);
    }

    protected byte[] engineGetEncoded() {
        DSAParameter dSAParameter = new DSAParameter(this.currentSpec.getP(), this.currentSpec.getQ(), this.currentSpec.getG());
        try {
            return dSAParameter.getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException("Error encoding DSAParameters");
        }
    }

    protected byte[] engineGetEncoded(String string) {
        if (this.isASN1FormatString(string)) {
            return this.engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == DSAParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
            return this.currentSpec;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to DSA parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof DSAParameterSpec)) {
            throw new InvalidParameterSpecException("DSAParameterSpec required to initialise a DSA algorithm parameters object");
        }
        this.currentSpec = (DSAParameterSpec)algorithmParameterSpec;
    }

    protected void engineInit(byte[] byArray) throws IOException {
        try {
            DSAParameter dSAParameter = DSAParameter.getInstance(ASN1Primitive.fromByteArray(byArray));
            this.currentSpec = new DSAParameterSpec(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
        } catch (ClassCastException classCastException) {
            throw new IOException("Not a valid DSA Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new IOException("Not a valid DSA Parameter encoding.");
        }
    }

    protected void engineInit(byte[] byArray, String string) throws IOException {
        if (!this.isASN1FormatString(string) && !string.equalsIgnoreCase("X.509")) {
            throw new IOException("Unknown parameter format " + string);
        }
        this.engineInit(byArray);
    }

    protected String engineToString() {
        return "DSA Parameters";
    }
}

