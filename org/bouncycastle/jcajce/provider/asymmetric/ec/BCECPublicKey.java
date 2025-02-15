/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;

public class BCECPublicKey
implements ECPublicKey,
org.bouncycastle.jce.interfaces.ECPublicKey,
ECPointEncoder {
    static final long serialVersionUID = 2422789860422731812L;
    private String algorithm = "EC";
    private boolean withCompression;
    private transient ECPublicKeyParameters ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private transient ProviderConfiguration configuration;

    public BCECPublicKey(String string, BCECPublicKey bCECPublicKey) {
        this.algorithm = string;
        this.ecPublicKey = bCECPublicKey.ecPublicKey;
        this.ecSpec = bCECPublicKey.ecSpec;
        this.withCompression = bCECPublicKey.withCompression;
        this.configuration = bCECPublicKey.configuration;
    }

    public BCECPublicKey(String string, java.security.spec.ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = string;
        this.ecSpec = eCPublicKeySpec.getParams();
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(this.ecSpec, eCPublicKeySpec.getW(), false), EC5Util.getDomainParameters(providerConfiguration, eCPublicKeySpec.getParams()));
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String string, ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = string;
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
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        ECDomainParameters eCDomainParameters = eCPublicKeyParameters.getParameters();
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        if (eCParameterSpec == null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(eCDomainParameters.getCurve(), eCDomainParameters.getSeed());
            this.ecSpec = this.createSpec(ellipticCurve, eCDomainParameters);
        } else {
            this.ecSpec = eCParameterSpec;
        }
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
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
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(String string, ECPublicKeyParameters eCPublicKeyParameters, ProviderConfiguration providerConfiguration) {
        this.algorithm = string;
        this.ecPublicKey = eCPublicKeyParameters;
        this.ecSpec = null;
        this.configuration = providerConfiguration;
    }

    public BCECPublicKey(ECPublicKey eCPublicKey, ProviderConfiguration providerConfiguration) {
        this.algorithm = eCPublicKey.getAlgorithm();
        this.ecSpec = eCPublicKey.getParams();
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(this.ecSpec, eCPublicKey.getW(), false), EC5Util.getDomainParameters(providerConfiguration, eCPublicKey.getParams()));
    }

    BCECPublicKey(String string, SubjectPublicKeyInfo subjectPublicKeyInfo, ProviderConfiguration providerConfiguration) {
        this.algorithm = string;
        this.configuration = providerConfiguration;
        this.populateFromPubKeyInfo(subjectPublicKeyInfo);
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new ECPoint(eCDomainParameters.getG().getAffineXCoord().toBigInteger(), eCDomainParameters.getG().getAffineYCoord().toBigInteger()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        int n;
        X962Parameters x962Parameters = X962Parameters.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
        ECCurve eCCurve = EC5Util.getCurve(this.configuration, x962Parameters);
        this.ecSpec = EC5Util.convertToSpec(x962Parameters, eCCurve);
        DERBitString dERBitString = subjectPublicKeyInfo.getPublicKeyData();
        byte[] byArray = dERBitString.getBytes();
        ASN1OctetString aSN1OctetString = new DEROctetString(byArray);
        if (byArray[0] == 4 && byArray[1] == byArray.length - 2 && (byArray[2] == 2 || byArray[2] == 3) && (n = new X9IntegerConverter().getByteLength(eCCurve)) >= byArray.length - 3) {
            try {
                aSN1OctetString = (ASN1OctetString)ASN1Primitive.fromByteArray(byArray);
            } catch (IOException iOException) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
        X9ECPoint x9ECPoint = new X9ECPoint(eCCurve, aSN1OctetString);
        this.ecPublicKey = new ECPublicKeyParameters(x9ECPoint.getPoint(), ECUtil.getDomainParameters(this.configuration, x962Parameters));
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        X962Parameters x962Parameters = ECUtils.getDomainParametersFromName(this.ecSpec, this.withCompression);
        ASN1OctetString aSN1OctetString = ASN1OctetString.getInstance(new X9ECPoint(this.ecPublicKey.getQ(), this.withCompression).toASN1Primitive());
        SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, x962Parameters), aSN1OctetString.getOctets());
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
        return this.configuration.getEcImplicitlyCa();
    }

    public String toString() {
        return ECUtil.publicKeyToString("EC", this.ecPublicKey.getQ(), this.engineGetSpec());
    }

    public void setPointFormat(String string) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(string);
    }

    public boolean equals(Object object) {
        if (!(object instanceof BCECPublicKey)) {
            return false;
        }
        BCECPublicKey bCECPublicKey = (BCECPublicKey)object;
        return this.ecPublicKey.getQ().equals(bCECPublicKey.ecPublicKey.getQ()) && this.engineGetSpec().equals(bCECPublicKey.engineGetSpec());
    }

    public int hashCode() {
        return this.ecPublicKey.getQ().hashCode() ^ this.engineGetSpec().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        byte[] byArray = (byte[])objectInputStream.readObject();
        this.configuration = BouncyCastleProvider.CONFIGURATION;
        this.populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray)));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.getEncoded());
    }
}

