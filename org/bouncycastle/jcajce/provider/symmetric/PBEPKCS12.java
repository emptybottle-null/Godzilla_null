/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

public class PBEPKCS12 {
    private PBEPKCS12() {
    }

    public static class AlgParams
    extends BaseAlgorithmParameters {
        PKCS12PBEParams params;

        protected byte[] engineGetEncoded() {
            try {
                return this.params.getEncoded("DER");
            } catch (IOException iOException) {
                throw new RuntimeException("Oooops! " + iOException.toString());
            }
        }

        protected byte[] engineGetEncoded(String string) {
            if (this.isASN1FormatString(string)) {
                return this.engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == PBEParameterSpec.class) {
                return new PBEParameterSpec(this.params.getIV(), this.params.getIterations().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PKCS12 PBE parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PKCS12 PBE parameters algorithm parameters object");
            }
            PBEParameterSpec pBEParameterSpec = (PBEParameterSpec)algorithmParameterSpec;
            this.params = new PKCS12PBEParams(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
        }

        protected void engineInit(byte[] byArray) throws IOException {
            this.params = PKCS12PBEParams.getInstance(ASN1Primitive.fromByteArray(byArray));
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (this.isASN1FormatString(string)) {
                this.engineInit(byArray);
                return;
            }
            throw new IOException("Unknown parameters format in PKCS12 PBE parameters object");
        }

        protected String engineToString() {
            return "PKCS12 PBE Parameters";
        }
    }

    public static class Mappings
    extends AlgorithmProvider {
        private static final String PREFIX = PBEPKCS12.class.getName();

        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.PKCS12PBE", PREFIX + "$AlgParams");
        }
    }
}

