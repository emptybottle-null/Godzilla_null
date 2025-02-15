/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.GOST3410Params;
import org.bouncycastle.jce.interfaces.GOST3410PrivateKey;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.spec.GOST3410ParameterSpec;
import org.bouncycastle.jce.spec.GOST3410PrivateKeySpec;
import org.bouncycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class BCGOST3410PrivateKey
implements GOST3410PrivateKey,
PKCS12BagAttributeCarrier {
    static final long serialVersionUID = 8581661527592305464L;
    private BigInteger x;
    private transient GOST3410Params gost3410Spec;
    private transient PKCS12BagAttributeCarrier attrCarrier = new PKCS12BagAttributeCarrierImpl();

    protected BCGOST3410PrivateKey() {
    }

    BCGOST3410PrivateKey(GOST3410PrivateKey gOST3410PrivateKey) {
        this.x = gOST3410PrivateKey.getX();
        this.gost3410Spec = gOST3410PrivateKey.getParameters();
    }

    BCGOST3410PrivateKey(GOST3410PrivateKeySpec gOST3410PrivateKeySpec) {
        this.x = gOST3410PrivateKeySpec.getX();
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(gOST3410PrivateKeySpec.getP(), gOST3410PrivateKeySpec.getQ(), gOST3410PrivateKeySpec.getA()));
    }

    BCGOST3410PrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = new GOST3410PublicKeyAlgParameters((ASN1Sequence)privateKeyInfo.getAlgorithmId().getParameters());
        ASN1OctetString aSN1OctetString = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey());
        byte[] byArray = aSN1OctetString.getOctets();
        byte[] byArray2 = new byte[byArray.length];
        for (int i = 0; i != byArray.length; ++i) {
            byArray2[i] = byArray[byArray.length - 1 - i];
        }
        this.x = new BigInteger(1, byArray2);
        this.gost3410Spec = GOST3410ParameterSpec.fromPublicKeyAlg(gOST3410PublicKeyAlgParameters);
    }

    BCGOST3410PrivateKey(GOST3410PrivateKeyParameters gOST3410PrivateKeyParameters, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.x = gOST3410PrivateKeyParameters.getX();
        this.gost3410Spec = gOST3410ParameterSpec;
        if (gOST3410ParameterSpec == null) {
            throw new IllegalArgumentException("spec is null");
        }
    }

    public String getAlgorithm() {
        return "GOST3410";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        byte[] byArray = this.getX().toByteArray();
        byte[] byArray2 = byArray[0] == 0 ? new byte[byArray.length - 1] : new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray.length - 1 - i];
        }
        try {
            PrivateKeyInfo privateKeyInfo = this.gost3410Spec instanceof GOST3410ParameterSpec ? new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()))), new DEROctetString(byArray2)) : new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94), new DEROctetString(byArray2));
            return privateKeyInfo.getEncoded("DER");
        } catch (IOException iOException) {
            return null;
        }
    }

    public GOST3410Params getParameters() {
        return this.gost3410Spec;
    }

    public BigInteger getX() {
        return this.x;
    }

    public boolean equals(Object object) {
        if (!(object instanceof GOST3410PrivateKey)) {
            return false;
        }
        GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey)object;
        return this.getX().equals(gOST3410PrivateKey.getX()) && this.getParameters().getPublicKeyParameters().equals(gOST3410PrivateKey.getParameters().getPublicKeyParameters()) && this.getParameters().getDigestParamSetOID().equals(gOST3410PrivateKey.getParameters().getDigestParamSetOID()) && this.compareObj(this.getParameters().getEncryptionParamSetOID(), gOST3410PrivateKey.getParameters().getEncryptionParamSetOID());
    }

    private boolean compareObj(Object object, Object object2) {
        if (object == object2) {
            return true;
        }
        if (object == null) {
            return false;
        }
        return object.equals(object2);
    }

    public int hashCode() {
        return this.getX().hashCode() ^ this.gost3410Spec.hashCode();
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

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        String string = (String)objectInputStream.readObject();
        if (string != null) {
            this.gost3410Spec = new GOST3410ParameterSpec(string, (String)objectInputStream.readObject(), (String)objectInputStream.readObject());
        } else {
            this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec((BigInteger)objectInputStream.readObject(), (BigInteger)objectInputStream.readObject(), (BigInteger)objectInputStream.readObject()));
            objectInputStream.readObject();
            objectInputStream.readObject();
        }
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.gost3410Spec.getPublicKeyParamSetOID() != null) {
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getDigestParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
        } else {
            objectOutputStream.writeObject(null);
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getP());
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getQ());
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getA());
            objectOutputStream.writeObject(this.gost3410Spec.getDigestParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
        }
    }
}

