/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.asn1.pkcs.DHParameter;

public class AlgorithmParametersSpi
extends java.security.AlgorithmParametersSpi {
    DHParameterSpec currentSpec;

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
        DHParameter dHParameter = new DHParameter(this.currentSpec.getP(), this.currentSpec.getG(), this.currentSpec.getL());
        try {
            return dHParameter.getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException("Error encoding DHParameters");
        }
    }

    protected byte[] engineGetEncoded(String string) {
        if (this.isASN1FormatString(string)) {
            return this.engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == DHParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
            return this.currentSpec;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to DH parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidParameterSpecException("DHParameterSpec required to initialise a Diffie-Hellman algorithm parameters object");
        }
        this.currentSpec = (DHParameterSpec)algorithmParameterSpec;
    }

    protected void engineInit(byte[] byArray) throws IOException {
        try {
            DHParameter dHParameter = DHParameter.getInstance(byArray);
            this.currentSpec = dHParameter.getL() != null ? new DHParameterSpec(dHParameter.getP(), dHParameter.getG(), dHParameter.getL().intValue()) : new DHParameterSpec(dHParameter.getP(), dHParameter.getG());
        } catch (ClassCastException classCastException) {
            throw new IOException("Not a valid DH Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new IOException("Not a valid DH Parameter encoding.");
        }
    }

    protected void engineInit(byte[] byArray, String string) throws IOException {
        if (!this.isASN1FormatString(string)) {
            throw new IOException("Unknown parameter format " + string);
        }
        this.engineInit(byArray);
    }

    protected String engineToString() {
        return "Diffie-Hellman Parameters";
    }
}

