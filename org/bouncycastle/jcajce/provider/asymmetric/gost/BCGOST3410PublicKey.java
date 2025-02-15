/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.GOST3410PublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jce.interfaces.GOST3410Params;
import org.bouncycastle.jce.interfaces.GOST3410PublicKey;
import org.bouncycastle.jce.spec.GOST3410ParameterSpec;
import org.bouncycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;
import org.bouncycastle.jce.spec.GOST3410PublicKeySpec;
import org.bouncycastle.util.Strings;

public class BCGOST3410PublicKey
implements GOST3410PublicKey {
    static final long serialVersionUID = -6251023343619275990L;
    private BigInteger y;
    private transient GOST3410Params gost3410Spec;

    BCGOST3410PublicKey(GOST3410PublicKeySpec gOST3410PublicKeySpec) {
        this.y = gOST3410PublicKeySpec.getY();
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(gOST3410PublicKeySpec.getP(), gOST3410PublicKeySpec.getQ(), gOST3410PublicKeySpec.getA()));
    }

    BCGOST3410PublicKey(GOST3410PublicKey gOST3410PublicKey) {
        this.y = gOST3410PublicKey.getY();
        this.gost3410Spec = gOST3410PublicKey.getParameters();
    }

    BCGOST3410PublicKey(GOST3410PublicKeyParameters gOST3410PublicKeyParameters, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.y = gOST3410PublicKeyParameters.getY();
        this.gost3410Spec = gOST3410ParameterSpec;
    }

    BCGOST3410PublicKey(BigInteger bigInteger, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.y = bigInteger;
        this.gost3410Spec = gOST3410ParameterSpec;
    }

    BCGOST3410PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = new GOST3410PublicKeyAlgParameters((ASN1Sequence)subjectPublicKeyInfo.getAlgorithmId().getParameters());
        try {
            DEROctetString dEROctetString = (DEROctetString)subjectPublicKeyInfo.parsePublicKey();
            byte[] byArray = dEROctetString.getOctets();
            byte[] byArray2 = new byte[byArray.length];
            for (int i = 0; i != byArray.length; ++i) {
                byArray2[i] = byArray[byArray.length - 1 - i];
            }
            this.y = new BigInteger(1, byArray2);
        } catch (IOException iOException) {
            throw new IllegalArgumentException("invalid info structure in GOST3410 public key");
        }
        this.gost3410Spec = GOST3410ParameterSpec.fromPublicKeyAlg(gOST3410PublicKeyAlgParameters);
    }

    public String getAlgorithm() {
        return "GOST3410";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        byte[] byArray = this.getY().toByteArray();
        byte[] byArray2 = byArray[0] == 0 ? new byte[byArray.length - 1] : new byte[byArray.length];
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = byArray[byArray.length - 1 - i];
        }
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = this.gost3410Spec instanceof GOST3410ParameterSpec ? (this.gost3410Spec.getEncryptionParamSetOID() != null ? new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getEncryptionParamSetOID()))), new DEROctetString(byArray2)) : new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()))), new DEROctetString(byArray2))) : new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94), new DEROctetString(byArray2));
            return KeyUtil.getEncodedSubjectPublicKeyInfo(subjectPublicKeyInfo);
        } catch (IOException iOException) {
            return null;
        }
    }

    public GOST3410Params getParameters() {
        return this.gost3410Spec;
    }

    public BigInteger getY() {
        return this.y;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("GOST3410 Public Key").append(string);
        stringBuffer.append("            y: ").append(this.getY().toString(16)).append(string);
        return stringBuffer.toString();
    }

    public boolean equals(Object object) {
        if (object instanceof BCGOST3410PublicKey) {
            BCGOST3410PublicKey bCGOST3410PublicKey = (BCGOST3410PublicKey)object;
            return this.y.equals(bCGOST3410PublicKey.y) && this.gost3410Spec.equals(bCGOST3410PublicKey.gost3410Spec);
        }
        return false;
    }

    public int hashCode() {
        return this.y.hashCode() ^ this.gost3410Spec.hashCode();
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

