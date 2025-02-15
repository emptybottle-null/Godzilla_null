/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.oiw.ElGamalParameter;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.DHParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class PrivateKeyFactory {
    public static AsymmetricKeyParameter createKey(byte[] byArray) throws IOException {
        return PrivateKeyFactory.createKey(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
    }

    public static AsymmetricKeyParameter createKey(InputStream inputStream) throws IOException {
        return PrivateKeyFactory.createKey(PrivateKeyInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
    }

    public static AsymmetricKeyParameter createKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        AlgorithmIdentifier algorithmIdentifier = privateKeyInfo.getPrivateKeyAlgorithm();
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption)) {
            RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
            return new RSAPrivateCrtKeyParameters(rSAPrivateKey.getModulus(), rSAPrivateKey.getPublicExponent(), rSAPrivateKey.getPrivateExponent(), rSAPrivateKey.getPrime1(), rSAPrivateKey.getPrime2(), rSAPrivateKey.getExponent1(), rSAPrivateKey.getExponent2(), rSAPrivateKey.getCoefficient());
        }
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
            DHParameter dHParameter = DHParameter.getInstance(algorithmIdentifier.getParameters());
            ASN1Integer aSN1Integer = (ASN1Integer)privateKeyInfo.parsePrivateKey();
            BigInteger bigInteger = dHParameter.getL();
            int n = bigInteger == null ? 0 : bigInteger.intValue();
            DHParameters dHParameters = new DHParameters(dHParameter.getP(), dHParameter.getG(), null, n);
            return new DHPrivateKeyParameters(aSN1Integer.getValue(), dHParameters);
        }
        if (algorithmIdentifier.getAlgorithm().equals(OIWObjectIdentifiers.elGamalAlgorithm)) {
            ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(algorithmIdentifier.getParameters());
            ASN1Integer aSN1Integer = (ASN1Integer)privateKeyInfo.parsePrivateKey();
            return new ElGamalPrivateKeyParameters(aSN1Integer.getValue(), new ElGamalParameters(elGamalParameter.getP(), elGamalParameter.getG()));
        }
        if (algorithmIdentifier.getAlgorithm().equals(X9ObjectIdentifiers.id_dsa)) {
            ASN1Integer aSN1Integer = (ASN1Integer)privateKeyInfo.parsePrivateKey();
            ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
            DSAParameters dSAParameters = null;
            if (aSN1Encodable != null) {
                DSAParameter dSAParameter = DSAParameter.getInstance(aSN1Encodable.toASN1Primitive());
                dSAParameters = new DSAParameters(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
            }
            return new DSAPrivateKeyParameters(aSN1Integer.getValue(), dSAParameters);
        }
        if (algorithmIdentifier.getAlgorithm().equals(X9ObjectIdentifiers.id_ecPublicKey)) {
            ECDomainParameters eCDomainParameters;
            ASN1Object aSN1Object;
            X962Parameters x962Parameters = new X962Parameters((ASN1Primitive)algorithmIdentifier.getParameters());
            if (x962Parameters.isNamedCurve()) {
                aSN1Object = (ASN1ObjectIdentifier)x962Parameters.getParameters();
                X9ECParameters x9ECParameters = CustomNamedCurves.getByOID((ASN1ObjectIdentifier)aSN1Object);
                if (x9ECParameters == null) {
                    x9ECParameters = ECNamedCurveTable.getByOID((ASN1ObjectIdentifier)aSN1Object);
                }
                eCDomainParameters = new ECNamedDomainParameters((ASN1ObjectIdentifier)aSN1Object, x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
            } else {
                X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
                eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
            }
            aSN1Object = ECPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
            BigInteger bigInteger = ((ECPrivateKey)aSN1Object).getKey();
            return new ECPrivateKeyParameters(bigInteger, eCDomainParameters);
        }
        throw new RuntimeException("algorithm identifier in key not recognised");
    }
}

