/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

public class SubjectPublicKeyInfoFactory {
    private SubjectPublicKeyInfoFactory() {
    }

    public static SubjectPublicKeyInfo createSubjectPublicKeyInfo(AsymmetricKeyParameter asymmetricKeyParameter) throws IOException {
        if (asymmetricKeyParameter instanceof RSAKeyParameters) {
            RSAKeyParameters rSAKeyParameters = (RSAKeyParameters)asymmetricKeyParameter;
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPublicKey(rSAKeyParameters.getModulus(), rSAKeyParameters.getExponent()));
        }
        if (asymmetricKeyParameter instanceof DSAPublicKeyParameters) {
            DSAPublicKeyParameters dSAPublicKeyParameters = (DSAPublicKeyParameters)asymmetricKeyParameter;
            DSAParameter dSAParameter = null;
            DSAParameters dSAParameters = dSAPublicKeyParameters.getParameters();
            if (dSAParameters != null) {
                dSAParameter = new DSAParameter(dSAParameters.getP(), dSAParameters.getQ(), dSAParameters.getG());
            }
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, dSAParameter), new ASN1Integer(dSAPublicKeyParameters.getY()));
        }
        if (asymmetricKeyParameter instanceof ECPublicKeyParameters) {
            ASN1Object aSN1Object;
            X962Parameters x962Parameters;
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters)asymmetricKeyParameter;
            ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
            if (eCDomainParameters == null) {
                x962Parameters = new X962Parameters(DERNull.INSTANCE);
            } else if (eCDomainParameters instanceof ECNamedDomainParameters) {
                x962Parameters = new X962Parameters(((ECNamedDomainParameters)eCDomainParameters).getName());
            } else {
                aSN1Object = new X9ECParameters(eCDomainParameters.getCurve(), eCDomainParameters.getG(), eCDomainParameters.getN(), eCDomainParameters.getH(), eCDomainParameters.getSeed());
                x962Parameters = new X962Parameters((X9ECParameters)aSN1Object);
            }
            aSN1Object = (ASN1OctetString)new X9ECPoint(eCPublicKeyParameters.getQ()).toASN1Primitive();
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, x962Parameters), ((ASN1OctetString)aSN1Object).getOctets());
        }
        throw new IOException("key parameters not recognised.");
    }
}

