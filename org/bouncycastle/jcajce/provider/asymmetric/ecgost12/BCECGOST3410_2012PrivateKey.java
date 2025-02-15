/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.ECGOST3410NamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;

public class BCECGOST3410_2012PrivateKey
implements java.security.interfaces.ECPrivateKey,
ECPrivateKey,
PKCS12BagAttributeCarrier,
ECPointEncoder {
    static final long serialVersionUID = 7245981689601667138L;
    private String algorithm = "ECGOST3410-2012";
    private boolean withCompression;
    private transient GOST3410PublicKeyAlgParameters gostParams;
    private transient BigInteger d;
    private transient ECParameterSpec ecSpec;
    private transient DERBitString publicKey;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();

    protected BCECGOST3410_2012PrivateKey() {
    }

    public BCECGOST3410_2012PrivateKey(java.security.interfaces.ECPrivateKey eCPrivateKey) {
        this.d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
    }

    public BCECGOST3410_2012PrivateKey(org.bouncycastle.jce.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.d = eCPrivateKeySpec.getD();
        if (eCPrivateKeySpec.getParams() != null) {
            ECCurve eCCurve = eCPrivateKeySpec.getParams().getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, eCPrivateKeySpec.getParams().getSeed());
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, eCPrivateKeySpec.getParams());
        } else {
            this.ecSpec = null;
        }
    }

    public BCECGOST3410_2012PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410_2012PrivateKey(BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey) {
        this.d = bCECGOST3410_2012PrivateKey.d;
        this.ecSpec = bCECGOST3410_2012PrivateKey.ecSpec;
        this.withCompression = bCECGOST3410_2012PrivateKey.withCompression;
        this.attrCarrier = bCECGOST3410_2012PrivateKey.attrCarrier;
        this.publicKey = bCECGOST3410_2012PrivateKey.publicKey;
        this.gostParams = bCECGOST3410_2012PrivateKey.gostParams;
    }

    public BCECGOST3410_2012PrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = string;
        this.d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.gostParams = bCECGOST3410_2012PublicKey.getGostParams();
        this.publicKey = this.getPublicKeyDetails(bCECGOST3410_2012PublicKey);
    }

    public BCECGOST3410_2012PrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = string;
        this.d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
        } else {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed());
            this.ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(eCParameterSpec.getG().getAffineXCoord().toBigInteger(), eCParameterSpec.getG().getAffineYCoord().toBigInteger()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
        }
        this.gostParams = bCECGOST3410_2012PublicKey.getGostParams();
        this.publicKey = this.getPublicKeyDetails(bCECGOST3410_2012PublicKey);
    }

    public BCECGOST3410_2012PrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.algorithm = string;
        this.d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
    }

    BCECGOST3410_2012PrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.populateFromPrivKeyInfo(privateKeyInfo);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1Primitive aSN1Primitive = privateKeyInfo.getPrivateKeyAlgorithm().getParameters().toASN1Primitive();
        if (aSN1Primitive instanceof ASN1Sequence && (ASN1Sequence.getInstance(aSN1Primitive).size() == 2 || ASN1Sequence.getInstance(aSN1Primitive).size() == 3)) {
            this.gostParams = GOST3410PublicKeyAlgParameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
            ECNamedCurveParameterSpec eCNamedCurveParameterSpec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()));
            ECCurve eCCurve = eCNamedCurveParameterSpec.getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, eCNamedCurveParameterSpec.getSeed());
            this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()), ellipticCurve, new ECPoint(eCNamedCurveParameterSpec.getG().getAffineXCoord().toBigInteger(), eCNamedCurveParameterSpec.getG().getAffineYCoord().toBigInteger()), eCNamedCurveParameterSpec.getN(), eCNamedCurveParameterSpec.getH());
            ASN1Encodable aSN1Encodable = privateKeyInfo.parsePrivateKey();
            if (aSN1Encodable instanceof ASN1Integer) {
                this.d = ASN1Integer.getInstance(aSN1Encodable).getPositiveValue();
            } else {
                byte[] byArray = ASN1OctetString.getInstance(aSN1Encodable).getOctets();
                byte[] byArray2 = new byte[byArray.length];
                for (int i = 0; i != byArray.length; ++i) {
                    byArray2[i] = byArray[byArray.length - 1 - i];
                }
                this.d = new BigInteger(1, byArray2);
            }
        } else {
            Object object;
            ASN1Encodable aSN1Encodable;
            X962Parameters x962Parameters = X962Parameters.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
            if (x962Parameters.isNamedCurve()) {
                aSN1Encodable = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
                object = ECUtil.getNamedCurveByOid((ASN1ObjectIdentifier)aSN1Encodable);
                if (object == null) {
                    ECDomainParameters eCDomainParameters = ECGOST3410NamedCurves.getByOID((ASN1ObjectIdentifier)aSN1Encodable);
                    EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
                    this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName((ASN1ObjectIdentifier)aSN1Encodable), ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH());
                } else {
                    EllipticCurve ellipticCurve = EC5Util.convertCurve(((X9ECParameters)object).getCurve(), ((X9ECParameters)object).getSeed());
                    this.ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName((ASN1ObjectIdentifier)aSN1Encodable), ellipticCurve, new ECPoint(((X9ECParameters)object).getG().getAffineXCoord().toBigInteger(), ((X9ECParameters)object).getG().getAffineYCoord().toBigInteger()), ((X9ECParameters)object).getN(), ((X9ECParameters)object).getH());
                }
            } else if (x962Parameters.isImplicitlyCA()) {
                this.ecSpec = null;
            } else {
                aSN1Encodable = X9ECParameters.getInstance(x962Parameters.getParameters());
                object = EC5Util.convertCurve(((X9ECParameters)aSN1Encodable).getCurve(), ((X9ECParameters)aSN1Encodable).getSeed());
                this.ecSpec = new ECParameterSpec((EllipticCurve)object, new ECPoint(((X9ECParameters)aSN1Encodable).getG().getAffineXCoord().toBigInteger(), ((X9ECParameters)aSN1Encodable).getG().getAffineYCoord().toBigInteger()), ((X9ECParameters)aSN1Encodable).getN(), ((X9ECParameters)aSN1Encodable).getH().intValue());
            }
            aSN1Encodable = privateKeyInfo.parsePrivateKey();
            if (aSN1Encodable instanceof ASN1Integer) {
                object = ASN1Integer.getInstance(aSN1Encodable);
                this.d = ((ASN1Integer)object).getValue();
            } else {
                object = org.bouncycastle.asn1.sec.ECPrivateKey.getInstance(aSN1Encodable);
                this.d = ((org.bouncycastle.asn1.sec.ECPrivateKey)object).getKey();
                this.publicKey = ((org.bouncycastle.asn1.sec.ECPrivateKey)object).getPublicKey();
            }
        }
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        ASN1Object aSN1Object;
        int n;
        X962Parameters x962Parameters;
        Object object;
        int n2;
        boolean bl = this.d.bitLength() > 256;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = bl ? RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512 : RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256;
        int n3 = n2 = bl ? 64 : 32;
        if (this.gostParams != null) {
            byte[] byArray = new byte[n2];
            this.extractBytes(byArray, n2, 0, this.getS());
            try {
                PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, this.gostParams), new DEROctetString(byArray));
                return privateKeyInfo.getEncoded("DER");
            } catch (IOException iOException) {
                return null;
            }
        }
        if (this.ecSpec instanceof ECNamedCurveSpec) {
            object = ECUtil.getNamedCurveOid(((ECNamedCurveSpec)this.ecSpec).getName());
            if (object == null) {
                object = new ASN1ObjectIdentifier(((ECNamedCurveSpec)this.ecSpec).getName());
            }
            x962Parameters = new X962Parameters((ASN1ObjectIdentifier)object);
            n = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), this.getS());
        } else if (this.ecSpec == null) {
            x962Parameters = new X962Parameters(DERNull.INSTANCE);
            n = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, null, this.getS());
        } else {
            object = EC5Util.convertCurve(this.ecSpec.getCurve());
            aSN1Object = new X9ECParameters((ECCurve)object, EC5Util.convertPoint((ECCurve)object, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed());
            x962Parameters = new X962Parameters((X9ECParameters)aSN1Object);
            n = ECUtil.getOrderBitLength(BouncyCastleProvider.CONFIGURATION, this.ecSpec.getOrder(), this.getS());
        }
        aSN1Object = this.publicKey != null ? new org.bouncycastle.asn1.sec.ECPrivateKey(n, this.getS(), this.publicKey, x962Parameters) : new org.bouncycastle.asn1.sec.ECPrivateKey(n, this.getS(), (ASN1Encodable)x962Parameters);
        try {
            object = new PrivateKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, x962Parameters.toASN1Primitive()), ((org.bouncycastle.asn1.sec.ECPrivateKey)aSN1Object).toASN1Primitive());
            return ((ASN1Object)object).getEncoded("DER");
        } catch (IOException iOException) {
            return null;
        }
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

    org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        if (this.ecSpec != null) {
            return EC5Util.convertSpec(this.ecSpec, this.withCompression);
        }
        return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public BigInteger getS() {
        return this.d;
    }

    public BigInteger getD() {
        return this.d;
    }

    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    public void setPointFormat(String string) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(string);
    }

    public boolean equals(Object object) {
        if (!(object instanceof BCECGOST3410_2012PrivateKey)) {
            return false;
        }
        BCECGOST3410_2012PrivateKey bCECGOST3410_2012PrivateKey = (BCECGOST3410_2012PrivateKey)object;
        return this.getD().equals(bCECGOST3410_2012PrivateKey.getD()) && this.engineGetSpec().equals(bCECGOST3410_2012PrivateKey.engineGetSpec());
    }

    public int hashCode() {
        return this.getD().hashCode() ^ this.engineGetSpec().hashCode();
    }

    public String toString() {
        return ECUtil.privateKeyToString(this.algorithm, this.d, this.engineGetSpec());
    }

    private DERBitString getPublicKeyDetails(BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey) {
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bCECGOST3410_2012PublicKey.getEncoded()));
            return subjectPublicKeyInfo.getPublicKeyData();
        } catch (IOException iOException) {
            return null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        byte[] byArray = (byte[])objectInputStream.readObject();
        this.populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }
}

