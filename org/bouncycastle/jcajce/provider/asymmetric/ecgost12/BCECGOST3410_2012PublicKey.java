/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.ECGOST3410NamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;

public class BCECGOST3410_2012PublicKey
implements ECPublicKey,
org.bouncycastle.jce.interfaces.ECPublicKey,
ECPointEncoder {
    static final long serialVersionUID = 7026240464295649314L;
    private String algorithm = "ECGOST3410-2012";
    private boolean withCompression;
    private transient ECPublicKeyParameters ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private transient GOST3410PublicKeyAlgParameters gostParams;

    public BCECGOST3410_2012PublicKey(BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey) {
        this.ecPublicKey = bCECGOST3410_2012PublicKey.ecPublicKey;
        this.ecSpec = bCECGOST3410_2012PublicKey.ecSpec;
        this.withCompression = bCECGOST3410_2012PublicKey.withCompression;
        this.gostParams = bCECGOST3410_2012PublicKey.gostParams;
    }

    public BCECGOST3410_2012PublicKey(java.security.spec.ECPublicKeySpec eCPublicKeySpec) {
        this.ecSpec = eCPublicKeySpec.getParams();
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(this.ecSpec, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(null, eCPublicKeySpec.getParams()));
    }

    public BCECGOST3410_2012PublicKey(ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        if (eCPublicKeySpec.getParams() != null) {
            ECCurve eCCurve = eCPublicKeySpec.getParams().getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, eCPublicKeySpec.getParams().getSeed());
            this.ecPublicKey = new ECPublicKeyParameters(eCPublicKeySpec.getQ(), ECUtil.getDomainParameters(providerConfiguration, eCPublicKeySpec.getParams()));
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, eCPublicKeySpec.getParams());
        } else {
            org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = providerConfiguration.getEcImplicitlyCa();
            this.ecPublicKey = new ECPublicKeyParameters(eCParameterSpec.getCurve().createPoint(eCPublicKeySpec.getQ().getAffineXCoord().toBigInteger(), eCPublicKeySpec.getQ().getAffineYCoord().toBigInteger()), EC5Util.getDomainParameters(providerConfiguration, null));
            this.ecSpec = null;
        }
    }

    public BCECGOST3410_2012PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = this.createSpec(ellipticCurve, eCDomainParameters);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCECGOST3410_2012PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = this.createSpec(ellipticCurve, eCDomainParameters);
        } else {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed());
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, eCParameterSpec);
        }
    }

    public BCECGOST3410_2012PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters) {
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        this.ecSpec = null;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    public BCECGOST3410_2012PublicKey(ECPublicKey eCPublicKey) {
        this.algorithm = eCPublicKey.getAlgorithm();
        this.ecSpec = eCPublicKey.getParams();
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(this.ecSpec, eCPublicKey.getW(), false), EC5Util.getDomainParameters(null, eCPublicKey.getParams()));
    }

    BCECGOST3410_2012PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.populateFromPubKeyInfo(subjectPublicKeyInfo);
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        int n;
        ASN1OctetString aSN1OctetString;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
        DERBitString dERBitString = subjectPublicKeyInfo.getPublicKeyData();
        this.algorithm = "ECGOST3410-2012";
        try {
            aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(dERBitString.getBytes());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("error recovering public key");
        }
        byte[] byArray = aSN1OctetString.getOctets();
        int n2 = 64;
        if (aSN1ObjectIdentifier.equals(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512)) {
            n2 = 128;
        }
        int n3 = n2 / 2;
        byte[] byArray2 = new byte[n3];
        byte[] byArray3 = new byte[n3];
        for (n = 0; n != byArray2.length; ++n) {
            byArray2[n] = byArray[n3 - 1 - n];
        }
        for (n = 0; n != byArray3.length; ++n) {
            byArray3[n] = byArray[n2 - 1 - n];
        }
        this.gostParams = GOST3410PublicKeyAlgParameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
        ECNamedCurveParameterSpec eCNamedCurveParameterSpec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()));
        ECCurve eCCurve = eCNamedCurveParameterSpec.getCurve();
        EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, eCNamedCurveParameterSpec.getSeed());
        this.ecPublicKey = new ECPublicKeyParameters(eCCurve.createPoint(new BigInteger(1, byArray2), new BigInteger(1, byArray3)), ECUtil.getDomainParameters(null, eCNamedCurveParameterSpec));
        this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()), ellipticCurve, new ECPoint(eCNamedCurveParameterSpec.getG().getAffineXCoord().toBigInteger(), eCNamedCurveParameterSpec.getG().getAffineYCoord().toBigInteger()), eCNamedCurveParameterSpec.getN(), eCNamedCurveParameterSpec.getH());
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        SubjectPublicKeyInfo subjectPublicKeyInfo;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        int n;
        int n2;
        ASN1Object aSN1Object;
        boolean bl;
        BigInteger bigInteger = this.ecPublicKey.getQ().getAffineXCoord().toBigInteger();
        BigInteger bigInteger2 = this.ecPublicKey.getQ().getAffineYCoord().toBigInteger();
        boolean bl2 = bl = bigInteger.bitLength() > 256;
        if (this.gostParams != null) {
            aSN1Object = this.gostParams;
        } else if (this.ecSpec instanceof ECNamedCurveSpec) {
            aSN1Object = bl ? new GOST3410PublicKeyAlgParameters(ECGOST3410NamedCurves.getOID(((ECNamedCurveSpec)this.ecSpec).getName()), RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512) : new GOST3410PublicKeyAlgParameters(ECGOST3410NamedCurves.getOID(((ECNamedCurveSpec)this.ecSpec).getName()), RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256);
        } else {
            ECCurve eCCurve = EC5Util.convertCurve(this.ecSpec.getCurve());
            X9ECParameters x9ECParameters = new X9ECParameters(eCCurve, EC5Util.convertPoint(eCCurve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed());
            aSN1Object = new X962Parameters(x9ECParameters);
        }
        if (bl) {
            n2 = 128;
            n = 64;
            aSN1ObjectIdentifier = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512;
        } else {
            n2 = 64;
            n = 32;
            aSN1ObjectIdentifier = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256;
        }
        byte[] byArray = new byte[n2];
        this.extractBytes(byArray, n2 / 2, 0, bigInteger);
        this.extractBytes(byArray, n2 / 2, n, bigInteger2);
        try {
            subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, aSN1Object), new DEROctetString(byArray));
        } catch (IOException iOException) {
            return null;
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(subjectPublicKeyInfo);
    }

    private void extractBytes(byte[] byArray, int n, int n2, BigInteger bigInteger) {
        byte[] byArray2 = bigInteger.toByteArray();
        if (byArray2.length < n) {
            byte[] byArray3 = new byte[n];
            System.arraycopy(byArray2, 0, byArray3, byArray3.length - byArray2.length, byArray2.length);
            byArray2 = byArray3;
        }
        for (int i = 0; i != n; ++i) {
            byArray[n2 + i] = byArray2[byArray2.length - 1 - i];
        }
    }

    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    public org.bouncycastle.jce.spec.ECParameterSpec getParameters() {
        if (this.ecSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(this.ecSpec, this.withCompression);
    }

    public ECPoint getW() {
        return new ECPoint(this.ecPublicKey.getQ().getAffineXCoord().toBigInteger(), this.ecPublicKey.getQ().getAffineYCoord().toBigInteger());
    }

    public org.bouncycastle.math.ec.ECPoint getQ() {
        if (this.ecSpec == null) {
            return this.ecPublicKey.getQ().getDetachedPoint();
        }
        return this.ecPublicKey.getQ();
    }

    ECPublicKeyParameters engineGetKeyParameters() {
        return this.ecPublicKey;
    }

    org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        if (this.ecSpec != null) {
            return EC5Util.convertSpec(this.ecSpec, this.withCompression);
        }
        return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public String toString() {
        return ECUtil.publicKeyToString(this.algorithm, this.ecPublicKey.getQ(), this.engineGetSpec());
    }

    public void setPointFormat(String string) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(string);
    }

    public boolean equals(Object object) {
        if (!(object instanceof BCECGOST3410_2012PublicKey)) {
            return false;
        }
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = (BCECGOST3410_2012PublicKey)object;
        return this.ecPublicKey.getQ().equals(bCECGOST3410_2012PublicKey.ecPublicKey.getQ()) && this.engineGetSpec().equals(bCECGOST3410_2012PublicKey.engineGetSpec());
    }

    public int hashCode() {
        return this.ecPublicKey.getQ().hashCode() ^ this.engineGetSpec().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        byte[] byArray = (byte[])objectInputStream.readObject();
        this.populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }

    public GOST3410PublicKeyAlgParameters getGostParams() {
        return this.gostParams;
    }
}

