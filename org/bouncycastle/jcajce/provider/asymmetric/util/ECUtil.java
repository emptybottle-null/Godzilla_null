/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.Enumeration;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.anssi.ANSSINamedCurves;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962NamedCurves;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECNamedDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Fingerprint;
import org.bouncycastle.util.Strings;

public class ECUtil {
    static int[] convertMidTerms(int[] nArray) {
        int[] nArray2 = new int[3];
        if (nArray.length == 1) {
            nArray2[0] = nArray[0];
        } else {
            if (nArray.length != 3) {
                throw new IllegalArgumentException("Only Trinomials and pentanomials supported");
            }
            if (nArray[0] < nArray[1] && nArray[0] < nArray[2]) {
                nArray2[0] = nArray[0];
                if (nArray[1] < nArray[2]) {
                    nArray2[1] = nArray[1];
                    nArray2[2] = nArray[2];
                } else {
                    nArray2[1] = nArray[2];
                    nArray2[2] = nArray[1];
                }
            } else if (nArray[1] < nArray[2]) {
                nArray2[0] = nArray[1];
                if (nArray[0] < nArray[2]) {
                    nArray2[1] = nArray[0];
                    nArray2[2] = nArray[2];
                } else {
                    nArray2[1] = nArray[2];
                    nArray2[2] = nArray[0];
                }
            } else {
                nArray2[0] = nArray[2];
                if (nArray[0] < nArray[1]) {
                    nArray2[1] = nArray[0];
                    nArray2[2] = nArray[1];
                } else {
                    nArray2[1] = nArray[1];
                    nArray2[2] = nArray[0];
                }
            }
        }
        return nArray2;
    }

    public static ECDomainParameters getDomainParameters(ProviderConfiguration providerConfiguration, ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters;
        if (eCParameterSpec instanceof ECNamedCurveParameterSpec) {
            ECNamedCurveParameterSpec eCNamedCurveParameterSpec = (ECNamedCurveParameterSpec)eCParameterSpec;
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ECUtil.getNamedCurveOid(eCNamedCurveParameterSpec.getName());
            eCDomainParameters = new ECNamedDomainParameters(aSN1ObjectIdentifier, eCNamedCurveParameterSpec.getCurve(), eCNamedCurveParameterSpec.getG(), eCNamedCurveParameterSpec.getN(), eCNamedCurveParameterSpec.getH(), eCNamedCurveParameterSpec.getSeed());
        } else if (eCParameterSpec == null) {
            ECParameterSpec eCParameterSpec2 = providerConfiguration.getEcImplicitlyCa();
            eCDomainParameters = new ECDomainParameters(eCParameterSpec2.getCurve(), eCParameterSpec2.getG(), eCParameterSpec2.getN(), eCParameterSpec2.getH(), eCParameterSpec2.getSeed());
        } else {
            eCDomainParameters = new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed());
        }
        return eCDomainParameters;
    }

    public static ECDomainParameters getDomainParameters(ProviderConfiguration providerConfiguration, X962Parameters x962Parameters) {
        ECDomainParameters eCDomainParameters;
        if (x962Parameters.isNamedCurve()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
            X9ECParameters x9ECParameters = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
            if (x9ECParameters == null) {
                Map map = providerConfiguration.getAdditionalECParameters();
                x9ECParameters = (X9ECParameters)map.get(aSN1ObjectIdentifier);
            }
            eCDomainParameters = new ECNamedDomainParameters(aSN1ObjectIdentifier, x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
        } else if (x962Parameters.isImplicitlyCA()) {
            ECParameterSpec eCParameterSpec = providerConfiguration.getEcImplicitlyCa();
            eCDomainParameters = new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed());
        } else {
            X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
            eCDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH(), x9ECParameters.getSeed());
        }
        return eCDomainParameters;
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof org.bouncycastle.jce.interfaces.ECPublicKey) {
            org.bouncycastle.jce.interfaces.ECPublicKey eCPublicKey = (org.bouncycastle.jce.interfaces.ECPublicKey)publicKey;
            ECParameterSpec eCParameterSpec = eCPublicKey.getParameters();
            return new ECPublicKeyParameters(eCPublicKey.getQ(), new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed()));
        }
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey)publicKey;
            ECParameterSpec eCParameterSpec = EC5Util.convertSpec(eCPublicKey.getParams(), false);
            return new ECPublicKeyParameters(EC5Util.convertPoint(eCPublicKey.getParams(), eCPublicKey.getW(), false), new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed()));
        }
        try {
            byte[] byArray = publicKey.getEncoded();
            if (byArray == null) {
                throw new InvalidKeyException("no encoding for EC public key");
            }
            PublicKey publicKey2 = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(byArray));
            if (publicKey2 instanceof ECPublicKey) {
                return ECUtil.generatePublicKeyParameter(publicKey2);
            }
        } catch (Exception exception) {
            throw new InvalidKeyException("cannot identify EC public key: " + exception.toString());
        }
        throw new InvalidKeyException("cannot identify EC public key.");
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey)privateKey;
            ECParameterSpec eCParameterSpec = eCPrivateKey.getParameters();
            if (eCParameterSpec == null) {
                eCParameterSpec = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            }
            return new ECPrivateKeyParameters(eCPrivateKey.getD(), new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed()));
        }
        if (privateKey instanceof java.security.interfaces.ECPrivateKey) {
            java.security.interfaces.ECPrivateKey eCPrivateKey = (java.security.interfaces.ECPrivateKey)privateKey;
            ECParameterSpec eCParameterSpec = EC5Util.convertSpec(eCPrivateKey.getParams(), false);
            return new ECPrivateKeyParameters(eCPrivateKey.getS(), new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH(), eCParameterSpec.getSeed()));
        }
        try {
            byte[] byArray = privateKey.getEncoded();
            if (byArray == null) {
                throw new InvalidKeyException("no encoding for EC private key");
            }
            PrivateKey privateKey2 = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(byArray));
            if (privateKey2 instanceof java.security.interfaces.ECPrivateKey) {
                return ECUtil.generatePrivateKeyParameter(privateKey2);
            }
        } catch (Exception exception) {
            throw new InvalidKeyException("cannot identify EC private key: " + exception.toString());
        }
        throw new InvalidKeyException("can't identify EC private key.");
    }

    public static int getOrderBitLength(ProviderConfiguration providerConfiguration, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger == null) {
            ECParameterSpec eCParameterSpec = providerConfiguration.getEcImplicitlyCa();
            if (eCParameterSpec == null) {
                return bigInteger2.bitLength();
            }
            return eCParameterSpec.getN().bitLength();
        }
        return bigInteger.bitLength();
    }

    public static ASN1ObjectIdentifier getNamedCurveOid(String string) {
        String string2 = string.indexOf(32) > 0 ? string.substring(string.indexOf(32) + 1) : string;
        try {
            if (string2.charAt(0) >= '0' && string2.charAt(0) <= '2') {
                return new ASN1ObjectIdentifier(string2);
            }
            return ECUtil.lookupOidByName(string2);
        } catch (IllegalArgumentException illegalArgumentException) {
            return ECUtil.lookupOidByName(string2);
        }
    }

    private static ASN1ObjectIdentifier lookupOidByName(String string) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = X962NamedCurves.getOID(string);
        if (aSN1ObjectIdentifier == null) {
            aSN1ObjectIdentifier = SECNamedCurves.getOID(string);
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = NISTNamedCurves.getOID(string);
            }
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = TeleTrusTNamedCurves.getOID(string);
            }
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = ECGOST3410NamedCurves.getOID(string);
            }
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = ANSSINamedCurves.getOID(string);
            }
            if (aSN1ObjectIdentifier == null) {
                aSN1ObjectIdentifier = GMNamedCurves.getOID(string);
            }
        }
        return aSN1ObjectIdentifier;
    }

    public static ASN1ObjectIdentifier getNamedCurveOid(ECParameterSpec eCParameterSpec) {
        Enumeration enumeration = ECNamedCurveTable.getNames();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            X9ECParameters x9ECParameters = ECNamedCurveTable.getByName(string);
            if (!x9ECParameters.getN().equals(eCParameterSpec.getN()) || !x9ECParameters.getH().equals(eCParameterSpec.getH()) || !x9ECParameters.getCurve().equals(eCParameterSpec.getCurve()) || !x9ECParameters.getG().equals(eCParameterSpec.getG())) continue;
            return ECNamedCurveTable.getOID(string);
        }
        return null;
    }

    public static X9ECParameters getNamedCurveByOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParameters x9ECParameters = CustomNamedCurves.getByOID(aSN1ObjectIdentifier);
        if (x9ECParameters == null) {
            x9ECParameters = X962NamedCurves.getByOID(aSN1ObjectIdentifier);
            if (x9ECParameters == null) {
                x9ECParameters = SECNamedCurves.getByOID(aSN1ObjectIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = NISTNamedCurves.getByOID(aSN1ObjectIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = TeleTrusTNamedCurves.getByOID(aSN1ObjectIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = ANSSINamedCurves.getByOID(aSN1ObjectIdentifier);
            }
            if (x9ECParameters == null) {
                x9ECParameters = GMNamedCurves.getByOID(aSN1ObjectIdentifier);
            }
        }
        return x9ECParameters;
    }

    public static X9ECParameters getNamedCurveByName(String string) {
        X9ECParameters x9ECParameters = CustomNamedCurves.getByName(string);
        if (x9ECParameters == null) {
            x9ECParameters = X962NamedCurves.getByName(string);
            if (x9ECParameters == null) {
                x9ECParameters = SECNamedCurves.getByName(string);
            }
            if (x9ECParameters == null) {
                x9ECParameters = NISTNamedCurves.getByName(string);
            }
            if (x9ECParameters == null) {
                x9ECParameters = TeleTrusTNamedCurves.getByName(string);
            }
            if (x9ECParameters == null) {
                x9ECParameters = ANSSINamedCurves.getByName(string);
            }
            if (x9ECParameters == null) {
                x9ECParameters = GMNamedCurves.getByName(string);
            }
        }
        return x9ECParameters;
    }

    public static String getCurveName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = X962NamedCurves.getName(aSN1ObjectIdentifier);
        if (string == null) {
            string = SECNamedCurves.getName(aSN1ObjectIdentifier);
            if (string == null) {
                string = NISTNamedCurves.getName(aSN1ObjectIdentifier);
            }
            if (string == null) {
                string = TeleTrusTNamedCurves.getName(aSN1ObjectIdentifier);
            }
            if (string == null) {
                string = ECGOST3410NamedCurves.getName(aSN1ObjectIdentifier);
            }
            if (string == null) {
                string = ANSSINamedCurves.getName(aSN1ObjectIdentifier);
            }
            if (string == null) {
                string = GMNamedCurves.getName(aSN1ObjectIdentifier);
            }
        }
        return string;
    }

    public static String privateKeyToString(String string, BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        StringBuffer stringBuffer = new StringBuffer();
        String string2 = Strings.lineSeparator();
        ECPoint eCPoint = ECUtil.calculateQ(bigInteger, eCParameterSpec);
        stringBuffer.append(string);
        stringBuffer.append(" Private Key [").append(ECUtil.generateKeyFingerprint(eCPoint, eCParameterSpec)).append("]").append(string2);
        stringBuffer.append("            X: ").append(eCPoint.getAffineXCoord().toBigInteger().toString(16)).append(string2);
        stringBuffer.append("            Y: ").append(eCPoint.getAffineYCoord().toBigInteger().toString(16)).append(string2);
        return stringBuffer.toString();
    }

    private static ECPoint calculateQ(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        return eCParameterSpec.getG().multiply(bigInteger).normalize();
    }

    public static String publicKeyToString(String string, ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        StringBuffer stringBuffer = new StringBuffer();
        String string2 = Strings.lineSeparator();
        stringBuffer.append(string);
        stringBuffer.append(" Public Key [").append(ECUtil.generateKeyFingerprint(eCPoint, eCParameterSpec)).append("]").append(string2);
        stringBuffer.append("            X: ").append(eCPoint.getAffineXCoord().toBigInteger().toString(16)).append(string2);
        stringBuffer.append("            Y: ").append(eCPoint.getAffineYCoord().toBigInteger().toString(16)).append(string2);
        return stringBuffer.toString();
    }

    public static String generateKeyFingerprint(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        ECCurve eCCurve = eCParameterSpec.getCurve();
        ECPoint eCPoint2 = eCParameterSpec.getG();
        if (eCCurve != null) {
            return new Fingerprint(Arrays.concatenate(eCPoint.getEncoded(false), eCCurve.getA().getEncoded(), eCCurve.getB().getEncoded(), eCPoint2.getEncoded(false))).toString();
        }
        return new Fingerprint(eCPoint.getEncoded(false)).toString();
    }
}

