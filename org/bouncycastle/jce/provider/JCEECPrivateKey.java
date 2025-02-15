/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

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
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.sec.ECPrivateKeyStructure;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JCEECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Strings;

public class JCEECPrivateKey
implements java.security.interfaces.ECPrivateKey,
ECPrivateKey,
PKCS12BagAttributeCarrier,
ECPointEncoder {
    private String algorithm = "EC";
    private BigInteger d;
    private ECParameterSpec ecSpec;
    private boolean withCompression;
    private DERBitString publicKey;
    private PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();

    protected JCEECPrivateKey() {
    }

    public JCEECPrivateKey(java.security.interfaces.ECPrivateKey eCPrivateKey) {
        this.d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
    }

    public JCEECPrivateKey(String string, org.bouncycastle.jce.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = string;
        this.d = eCPrivateKeySpec.getD();
        if (eCPrivateKeySpec.getParams() != null) {
            ECCurve eCCurve = eCPrivateKeySpec.getParams().getCurve();
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCCurve, eCPrivateKeySpec.getParams().getSeed());
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, eCPrivateKeySpec.getParams());
        } else {
            this.ecSpec = null;
        }
    }

    public JCEECPrivateKey(String string, ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = string;
        this.d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
    }

    public JCEECPrivateKey(String string, JCEECPrivateKey jCEECPrivateKey) {
        this.algorithm = string;
        this.d = jCEECPrivateKey.d;
        this.ecSpec = jCEECPrivateKey.ecSpec;
        this.withCompression = jCEECPrivateKey.withCompression;
        this.attrCarrier = jCEECPrivateKey.attrCarrier;
        this.publicKey = jCEECPrivateKey.publicKey;
    }

    public JCEECPrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters, JCEECPublicKey jCEECPublicKey, ECParameterSpec eCParameterSpec) {
        ECDomainParameters eCDomainParameters = eCPrivateKeyParameters.getParameters();
        this.algorithm = string;
        this.d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.publicKey = this.getPublicKeyDetails(jCEECPublicKey);
    }

    public JCEECPrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters, JCEECPublicKey jCEECPublicKey, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
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
        this.publicKey = this.getPublicKeyDetails(jCEECPublicKey);
    }

    public JCEECPrivateKey(String string, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.algorithm = string;
        this.d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
    }

    JCEECPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.populateFromPrivKeyInfo(privateKeyInfo);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo privateKeyInfo) throws IOException {
        Object object;
        ASN1Encodable aSN1Encodable;
        X962Parameters x962Parameters = new X962Parameters((ASN1Primitive)privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
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
            object = new ECPrivateKeyStructure((ASN1Sequence)aSN1Encodable);
            this.d = ((ECPrivateKeyStructure)object).getKey();
            this.publicKey = ((ECPrivateKeyStructure)object).getPublicKey();
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
        X962Parameters x962Parameters;
        Object object;
        if (this.ecSpec instanceof ECNamedCurveSpec) {
            object = ECUtil.getNamedCurveOid(((ECNamedCurveSpec)this.ecSpec).getName());
            if (object == null) {
                object = new ASN1ObjectIdentifier(((ECNamedCurveSpec)this.ecSpec).getName());
            }
            x962Parameters = new X962Parameters((ASN1ObjectIdentifier)object);
        } else if (this.ecSpec == null) {
            x962Parameters = new X962Parameters(DERNull.INSTANCE);
        } else {
            object = EC5Util.convertCurve(this.ecSpec.getCurve());
            aSN1Object = new X9ECParameters((ECCurve)object, EC5Util.convertPoint((ECCurve)object, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf(this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed());
            x962Parameters = new X962Parameters((X9ECParameters)aSN1Object);
        }
        aSN1Object = this.publicKey != null ? new ECPrivateKeyStructure(this.getS(), this.publicKey, x962Parameters) : new ECPrivateKeyStructure(this.getS(), x962Parameters);
        try {
            object = this.algorithm.equals("ECGOST3410") ? new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, x962Parameters.toASN1Primitive()), ((ECPrivateKeyStructure)aSN1Object).toASN1Primitive()) : new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, x962Parameters.toASN1Primitive()), ((ECPrivateKeyStructure)aSN1Object).toASN1Primitive());
            return ((ASN1Object)object).getEncoded("DER");
        } catch (IOException iOException) {
            return null;
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
        if (!(object instanceof JCEECPrivateKey)) {
            return false;
        }
        JCEECPrivateKey jCEECPrivateKey = (JCEECPrivateKey)object;
        return this.getD().equals(jCEECPrivateKey.getD()) && this.engineGetSpec().equals(jCEECPrivateKey.engineGetSpec());
    }

    public int hashCode() {
        return this.getD().hashCode() ^ this.engineGetSpec().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("EC Private Key").append(string);
        stringBuffer.append("             S: ").append(this.d.toString(16)).append(string);
        return stringBuffer.toString();
    }

    private DERBitString getPublicKeyDetails(JCEECPublicKey jCEECPublicKey) {
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(jCEECPublicKey.getEncoded()));
            return subjectPublicKeyInfo.getPublicKeyData();
        } catch (IOException iOException) {
            return null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        byte[] byArray = (byte[])objectInputStream.readObject();
        this.populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
        this.algorithm = (String)objectInputStream.readObject();
        this.withCompression = objectInputStream.readBoolean();
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.attrCarrier.readObject(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.getEncoded());
        objectOutputStream.writeObject(this.algorithm);
        objectOutputStream.writeBoolean(this.withCompression);
        this.attrCarrier.writeObject(objectOutputStream);
    }
}

