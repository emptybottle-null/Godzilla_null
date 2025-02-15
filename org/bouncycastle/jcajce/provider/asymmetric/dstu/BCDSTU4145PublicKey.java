/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dstu;

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
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ua.DSTU4145BinaryField;
import org.bouncycastle.asn1.ua.DSTU4145ECBinary;
import org.bouncycastle.asn1.ua.DSTU4145NamedCurves;
import org.bouncycastle.asn1.ua.DSTU4145Params;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
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
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;

public class BCDSTU4145PublicKey
implements ECPublicKey,
org.bouncycastle.jce.interfaces.ECPublicKey,
ECPointEncoder {
    static final long serialVersionUID = 7026240464295649314L;
    private String algorithm = "DSTU4145";
    private boolean withCompression;
    private transient ECPublicKeyParameters ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private transient DSTU4145Params dstuParams;

    public BCDSTU4145PublicKey(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        this.ecPublicKey = bCDSTU4145PublicKey.ecPublicKey;
        this.ecSpec = bCDSTU4145PublicKey.ecSpec;
        this.withCompression = bCDSTU4145PublicKey.withCompression;
        this.dstuParams = bCDSTU4145PublicKey.dstuParams;
    }

    public BCDSTU4145PublicKey(java.security.spec.ECPublicKeySpec eCPublicKeySpec) {
        this.ecSpec = eCPublicKeySpec.getParams();
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(this.ecSpec, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(null, this.ecSpec));
    }

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
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

    public BCDSTU4145PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
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

    public BCDSTU4145PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        this.algorithm = string;
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = this.createSpec(ellipticCurve, eCDomainParameters);
        } else {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed());
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, eCParameterSpec);
        }
        this.ecPublicKey = eCPublicKeyParameters;
    }

    public BCDSTU4145PublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters) {
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        this.ecSpec = null;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    BCDSTU4145PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.populateFromPubKeyInfo(subjectPublicKeyInfo);
    }

    private void reverseBytes(byte[] byArray) {
        for (int i = 0; i < byArray.length / 2; ++i) {
            byte by = byArray[i];
            byArray[i] = byArray[byArray.length - 1 - i];
            byArray[byArray.length - 1 - i] = by;
        }
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Object object;
        Object object2;
        ASN1OctetString aSN1OctetString;
        DERBitString dERBitString = subjectPublicKeyInfo.getPublicKeyData();
        this.algorithm = "DSTU4145";
        try {
            aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(dERBitString.getBytes());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("error recovering public key");
        }
        byte[] byArray = aSN1OctetString.getOctets();
        if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().equals(UAObjectIdentifiers.dstu4145le)) {
            this.reverseBytes(byArray);
        }
        this.dstuParams = DSTU4145Params.getInstance((ASN1Sequence)subjectPublicKeyInfo.getAlgorithm().getParameters());
        org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec = null;
        if (this.dstuParams.isNamedCurve()) {
            object2 = this.dstuParams.getNamedCurve();
            object = DSTU4145NamedCurves.getByOID((ASN1ObjectIdentifier)object2);
            eCParameterSpec = new ECNamedCurveParameterSpec(((ASN1ObjectIdentifier)object2).getId(), ((ECDomainParameters)object).getCurve(), ((ECDomainParameters)object).getG(), ((ECDomainParameters)object).getN(), ((ECDomainParameters)object).getH(), ((ECDomainParameters)object).getSeed());
        } else {
            object2 = this.dstuParams.getECBinary();
            object = ((DSTU4145ECBinary)object2).getB();
            if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().equals(UAObjectIdentifiers.dstu4145le)) {
                this.reverseBytes((byte[])object);
            }
            DSTU4145BinaryField dSTU4145BinaryField = ((DSTU4145ECBinary)object2).getField();
            ECCurve.F2m f2m = new ECCurve.F2m(dSTU4145BinaryField.getM(), dSTU4145BinaryField.getK1(), dSTU4145BinaryField.getK2(), dSTU4145BinaryField.getK3(), ((DSTU4145ECBinary)object2).getA(), new BigInteger(1, (byte[])object));
            byte[] byArray2 = ((DSTU4145ECBinary)object2).getG();
            if (subjectPublicKeyInfo.getAlgorithm().getAlgorithm().equals(UAObjectIdentifiers.dstu4145le)) {
                this.reverseBytes(byArray2);
            }
            eCParameterSpec = new org.bouncycastle.jce.spec.ECParameterSpec(f2m, DSTU4145PointEncoder.decodePoint(f2m, byArray2), ((DSTU4145ECBinary)object2).getN());
        }
        object2 = eCParameterSpec.getCurve();
        object = EC5Util.convertCurve((ECCurve)object2, eCParameterSpec.getSeed());
        this.ecSpec = this.dstuParams.isNamedCurve() ? new ECNamedCurveSpec(this.dstuParams.getNamedCurve().getId(), (EllipticCurve)object, new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH()) : new ECParameterSpec((EllipticCurve)object, new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
        this.ecPublicKey = new ECPublicKeyParameters(DSTU4145PointEncoder.decodePoint((ECCurve)object2, byArray), EC5Util.getDomainParameters(null, this.ecSpec));
    }

    public byte[] getSbox() {
        if (null != this.dstuParams) {
            return this.dstuParams.getDKE();
        }
        return DSTU4145Params.getDefaultDKE();
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        SubjectPublicKeyInfo subjectPublicKeyInfo;
        Object object;
        ASN1Object aSN1Object;
        if (this.dstuParams != null) {
            aSN1Object = this.dstuParams;
        } else if (this.ecSpec instanceof ECNamedCurveSpec) {
            aSN1Object = new DSTU4145Params(new ASN1ObjectIdentifier(((ECNamedCurveSpec)this.ecSpec).getName()));
        } else {
            object = EC5Util.convertCurve(this.ecSpec.getCurve());
            X9ECParameters x9ECParameters = new X9ECParameters((ECCurve)object, EC5Util.convertPoint((ECCurve)object, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed());
            aSN1Object = new X962Parameters(x9ECParameters);
        }
        object = DSTU4145PointEncoder.encodePoint(this.ecPublicKey.getQ());
        try {
            subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(UAObjectIdentifiers.dstu4145be, aSN1Object), new DEROctetString((byte[])object));
        } catch (IOException iOException) {
            return null;
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(subjectPublicKeyInfo);
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
        org.bouncycastle.math.ec.ECPoint eCPoint = this.ecPublicKey.getQ();
        return new ECPoint(eCPoint.getAffineXCoord().toBigInteger(), eCPoint.getAffineYCoord().toBigInteger());
    }

    public org.bouncycastle.math.ec.ECPoint getQ() {
        org.bouncycastle.math.ec.ECPoint eCPoint = this.ecPublicKey.getQ();
        if (this.ecSpec == null) {
            return eCPoint.getDetachedPoint();
        }
        return eCPoint;
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
        if (!(object instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey)object;
        return this.ecPublicKey.getQ().equals(bCDSTU4145PublicKey.ecPublicKey.getQ()) && this.engineGetSpec().equals(bCDSTU4145PublicKey.engineGetSpec());
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
}

