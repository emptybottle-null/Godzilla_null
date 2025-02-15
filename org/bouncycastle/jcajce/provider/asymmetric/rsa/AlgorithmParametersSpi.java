/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAESOAEPparams;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.jcajce.util.MessageDigestUtils;

public abstract class AlgorithmParametersSpi
extends java.security.AlgorithmParametersSpi {
    protected boolean isASN1FormatString(String string) {
        return string == null || string.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
        if (clazz == null) {
            throw new NullPointerException("argument to getParameterSpec must not be null");
        }
        return this.localEngineGetParameterSpec(clazz);
    }

    protected abstract AlgorithmParameterSpec localEngineGetParameterSpec(Class var1) throws InvalidParameterSpecException;

    public static class OAEP
    extends AlgorithmParametersSpi {
        OAEPParameterSpec currentSpec;

        protected byte[] engineGetEncoded() {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(DigestFactory.getOID(this.currentSpec.getDigestAlgorithm()), DERNull.INSTANCE);
            MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)this.currentSpec.getMGFParameters();
            AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, new AlgorithmIdentifier(DigestFactory.getOID(mGF1ParameterSpec.getDigestAlgorithm()), DERNull.INSTANCE));
            PSource.PSpecified pSpecified = (PSource.PSpecified)this.currentSpec.getPSource();
            AlgorithmIdentifier algorithmIdentifier3 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(pSpecified.getValue()));
            RSAESOAEPparams rSAESOAEPparams = new RSAESOAEPparams(algorithmIdentifier, algorithmIdentifier2, algorithmIdentifier3);
            try {
                return rSAESOAEPparams.getEncoded("DER");
            } catch (IOException iOException) {
                throw new RuntimeException("Error encoding OAEPParameters");
            }
        }

        protected byte[] engineGetEncoded(String string) {
            if (this.isASN1FormatString(string) || string.equalsIgnoreCase("X.509")) {
                return this.engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == OAEPParameterSpec.class || clazz == AlgorithmParameterSpec.class) {
                return this.currentSpec;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to OAEP parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof OAEPParameterSpec)) {
                throw new InvalidParameterSpecException("OAEPParameterSpec required to initialise an OAEP algorithm parameters object");
            }
            this.currentSpec = (OAEPParameterSpec)algorithmParameterSpec;
        }

        protected void engineInit(byte[] byArray) throws IOException {
            try {
                RSAESOAEPparams rSAESOAEPparams = RSAESOAEPparams.getInstance(byArray);
                if (!rSAESOAEPparams.getMaskGenAlgorithm().getAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1)) {
                    throw new IOException("unknown mask generation function: " + rSAESOAEPparams.getMaskGenAlgorithm().getAlgorithm());
                }
                this.currentSpec = new OAEPParameterSpec(MessageDigestUtils.getDigestName(rSAESOAEPparams.getHashAlgorithm().getAlgorithm()), OAEPParameterSpec.DEFAULT.getMGFAlgorithm(), new MGF1ParameterSpec(MessageDigestUtils.getDigestName(AlgorithmIdentifier.getInstance(rSAESOAEPparams.getMaskGenAlgorithm().getParameters()).getAlgorithm())), new PSource.PSpecified(ASN1OctetString.getInstance(rSAESOAEPparams.getPSourceAlgorithm().getParameters()).getOctets()));
            } catch (ClassCastException classCastException) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            }
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (!string.equalsIgnoreCase("X.509") && !string.equalsIgnoreCase("ASN.1")) {
                throw new IOException("Unknown parameter format " + string);
            }
            this.engineInit(byArray);
        }

        protected String engineToString() {
            return "OAEP Parameters";
        }
    }

    public static class PSS
    extends AlgorithmParametersSpi {
        PSSParameterSpec currentSpec;

        protected byte[] engineGetEncoded() throws IOException {
            PSSParameterSpec pSSParameterSpec = this.currentSpec;
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(DigestFactory.getOID(pSSParameterSpec.getDigestAlgorithm()), DERNull.INSTANCE);
            MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec)pSSParameterSpec.getMGFParameters();
            AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, new AlgorithmIdentifier(DigestFactory.getOID(mGF1ParameterSpec.getDigestAlgorithm()), DERNull.INSTANCE));
            RSASSAPSSparams rSASSAPSSparams = new RSASSAPSSparams(algorithmIdentifier, algorithmIdentifier2, new ASN1Integer(pSSParameterSpec.getSaltLength()), new ASN1Integer(pSSParameterSpec.getTrailerField()));
            return rSASSAPSSparams.getEncoded("DER");
        }

        protected byte[] engineGetEncoded(String string) throws IOException {
            if (string.equalsIgnoreCase("X.509") || string.equalsIgnoreCase("ASN.1")) {
                return this.engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class clazz) throws InvalidParameterSpecException {
            if (clazz == PSSParameterSpec.class && this.currentSpec != null) {
                return this.currentSpec;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PSS parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof PSSParameterSpec)) {
                throw new InvalidParameterSpecException("PSSParameterSpec required to initialise an PSS algorithm parameters object");
            }
            this.currentSpec = (PSSParameterSpec)algorithmParameterSpec;
        }

        protected void engineInit(byte[] byArray) throws IOException {
            try {
                RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(byArray);
                if (!rSASSAPSSparams.getMaskGenAlgorithm().getAlgorithm().equals(PKCSObjectIdentifiers.id_mgf1)) {
                    throw new IOException("unknown mask generation function: " + rSASSAPSSparams.getMaskGenAlgorithm().getAlgorithm());
                }
                this.currentSpec = new PSSParameterSpec(MessageDigestUtils.getDigestName(rSASSAPSSparams.getHashAlgorithm().getAlgorithm()), PSSParameterSpec.DEFAULT.getMGFAlgorithm(), new MGF1ParameterSpec(MessageDigestUtils.getDigestName(AlgorithmIdentifier.getInstance(rSASSAPSSparams.getMaskGenAlgorithm().getParameters()).getAlgorithm())), rSASSAPSSparams.getSaltLength().intValue(), rSASSAPSSparams.getTrailerField().intValue());
            } catch (ClassCastException classCastException) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            }
        }

        protected void engineInit(byte[] byArray, String string) throws IOException {
            if (!this.isASN1FormatString(string) && !string.equalsIgnoreCase("X.509")) {
                throw new IOException("Unknown parameter format " + string);
            }
            this.engineInit(byArray);
        }

        protected String engineToString() {
            return "PSS Parameters";
        }
    }
}

