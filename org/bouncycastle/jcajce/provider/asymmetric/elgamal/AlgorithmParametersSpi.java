/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.oiw.ElGamalParameter;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

public class AlgorithmParametersSpi
extends BaseAlgorithmParameters {
    ElGamalParameterSpec currentSpec;

    protected byte[] engineGetEncoded() {
        ElGamalParameter elGamalParameter = new ElGamalParameter(this.currentSpec.getP(), this.currentSpec.getG());
        try {
            return elGamalParameter.getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException("Error encoding ElGamalParameters");
        }
    }

    protected byte[] engineGetEncoded(String string) {
        if (this.isASN1FormatString(string) || string.equalsIgnoreCase("X.509")) {
            return this.engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == ElGamalParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
            return this.currentSpec;
        }
        if (clazz == DHParameterSpec.class) {
            return new DHParameterSpec(this.currentSpec.getP(), this.currentSpec.getG());
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof ElGamalParameterSpec) && !(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidParameterSpecException("DHParameterSpec required to initialise a ElGamal algorithm parameters object");
        }
        if (algorithmParameterSpec instanceof ElGamalParameterSpec) {
            this.currentSpec = (ElGamalParameterSpec)algorithmParameterSpec;
        } else {
            DHParameterSpec dHParameterSpec = (DHParameterSpec)algorithmParameterSpec;
            this.currentSpec = new ElGamalParameterSpec(dHParameterSpec.getP(), dHParameterSpec.getG());
        }
    }

    protected void engineInit(byte[] byArray) throws IOException {
        try {
            ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(ASN1Primitive.fromByteArray(byArray));
            this.currentSpec = new ElGamalParameterSpec(elGamalParameter.getP(), elGamalParameter.getG());
        } catch (ClassCastException classCastException) {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
        }
    }

    protected void engineInit(byte[] byArray, String string) throws IOException {
        if (!this.isASN1FormatString(string) && !string.equalsIgnoreCase("X.509")) {
            throw new IOException("Unknown parameter format " + string);
        }
        this.engineInit(byArray);
    }

    protected String engineToString() {
        return "ElGamal Parameters";
    }
}

