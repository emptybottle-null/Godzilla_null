/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPublicKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSAUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.util.Strings;

public class BCDSAPublicKey
implements DSAPublicKey {
    private static final long serialVersionUID = 1752452449903495175L;
    private static BigInteger ZERO = BigInteger.valueOf(0L);
    private BigInteger y;
    private transient DSAPublicKeyParameters lwKeyParams;
    private transient DSAParams dsaSpec;

    BCDSAPublicKey(DSAPublicKeySpec dSAPublicKeySpec) {
        this.y = dSAPublicKeySpec.getY();
        this.dsaSpec = new DSAParameterSpec(dSAPublicKeySpec.getP(), dSAPublicKeySpec.getQ(), dSAPublicKeySpec.getG());
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    BCDSAPublicKey(DSAPublicKey dSAPublicKey) {
        this.y = dSAPublicKey.getY();
        this.dsaSpec = dSAPublicKey.getParams();
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    BCDSAPublicKey(DSAPublicKeyParameters dSAPublicKeyParameters) {
        this.y = dSAPublicKeyParameters.getY();
        this.dsaSpec = dSAPublicKeyParameters != null ? new DSAParameterSpec(dSAPublicKeyParameters.getParameters().getP(), dSAPublicKeyParameters.getParameters().getQ(), dSAPublicKeyParameters.getParameters().getG()) : null;
        this.lwKeyParams = dSAPublicKeyParameters;
    }

    public BCDSAPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ASN1Integer aSN1Integer;
        try {
            aSN1Integer = (ASN1Integer)subjectPublicKeyInfo.parsePublicKey();
        } catch (IOException iOException) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
        this.y = aSN1Integer.getValue();
        if (this.isNotNull(subjectPublicKeyInfo.getAlgorithm().getParameters())) {
            DSAParameter dSAParameter = DSAParameter.getInstance(subjectPublicKeyInfo.getAlgorithm().getParameters());
            this.dsaSpec = new DSAParameterSpec(dSAParameter.getP(), dSAParameter.getQ(), dSAParameter.getG());
        } else {
            this.dsaSpec = null;
        }
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    private boolean isNotNull(ASN1Encodable aSN1Encodable) {
        return aSN1Encodable != null && !DERNull.INSTANCE.equals(aSN1Encodable.toASN1Primitive());
    }

    public String getAlgorithm() {
        return "DSA";
    }

    public String getFormat() {
        return "X.509";
    }

    DSAPublicKeyParameters engineGetKeyParameters() {
        return this.lwKeyParams;
    }

    public byte[] getEncoded() {
        if (this.dsaSpec == null) {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa), new ASN1Integer(this.y));
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(this.dsaSpec.getP(), this.dsaSpec.getQ(), this.dsaSpec.getG()).toASN1Primitive()), new ASN1Integer(this.y));
    }

    public DSAParams getParams() {
        return this.dsaSpec;
    }

    public BigInteger getY() {
        return this.y;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("DSA Public Key [").append(DSAUtil.generateKeyFingerprint(this.y, this.getParams())).append("]").append(string);
        stringBuffer.append("            y: ").append(this.getY().toString(16)).append(string);
        return stringBuffer.toString();
    }

    public int hashCode() {
        if (this.dsaSpec != null) {
            return this.getY().hashCode() ^ this.getParams().getG().hashCode() ^ this.getParams().getP().hashCode() ^ this.getParams().getQ().hashCode();
        }
        return this.getY().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof DSAPublicKey)) {
            return false;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey)object;
        if (this.dsaSpec != null) {
            return this.getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() != null && this.getParams().getG().equals(dSAPublicKey.getParams().getG()) && this.getParams().getP().equals(dSAPublicKey.getParams().getP()) && this.getParams().getQ().equals(dSAPublicKey.getParams().getQ());
        }
        return this.getY().equals(dSAPublicKey.getY()) && dSAPublicKey.getParams() == null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        BigInteger bigInteger = (BigInteger)objectInputStream.readObject();
        this.dsaSpec = bigInteger.equals(ZERO) ? null : new DSAParameterSpec(bigInteger, (BigInteger)objectInputStream.readObject(), (BigInteger)objectInputStream.readObject());
        this.lwKeyParams = new DSAPublicKeyParameters(this.y, DSAUtil.toDSAParameters(this.dsaSpec));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.dsaSpec == null) {
            objectOutputStream.writeObject(ZERO);
        } else {
            objectOutputStream.writeObject(this.dsaSpec.getP());
            objectOutputStream.writeObject(this.dsaSpec.getQ());
            objectOutputStream.writeObject(this.dsaSpec.getG());
        }
    }
}

