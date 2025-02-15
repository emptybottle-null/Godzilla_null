/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.oiw.ElGamalParameter;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;
import org.bouncycastle.jce.spec.ElGamalPrivateKeySpec;

public class BCElGamalPrivateKey
implements ElGamalPrivateKey,
DHPrivateKey,
PKCS12BagAttributeCarrier {
    static final long serialVersionUID = 4819350091141529678L;
    private BigInteger x;
    private transient ElGamalParameterSpec elSpec;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();

    protected BCElGamalPrivateKey() {
    }

    BCElGamalPrivateKey(ElGamalPrivateKey elGamalPrivateKey) {
        this.x = elGamalPrivateKey.getX();
        this.elSpec = elGamalPrivateKey.getParameters();
    }

    BCElGamalPrivateKey(DHPrivateKey dHPrivateKey) {
        this.x = dHPrivateKey.getX();
        this.elSpec = new ElGamalParameterSpec(dHPrivateKey.getParams().getP(), dHPrivateKey.getParams().getG());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeySpec elGamalPrivateKeySpec) {
        this.x = elGamalPrivateKeySpec.getX();
        this.elSpec = new ElGamalParameterSpec(elGamalPrivateKeySpec.getParams().getP(), elGamalPrivateKeySpec.getParams().getG());
    }

    BCElGamalPrivateKey(DHPrivateKeySpec dHPrivateKeySpec) {
        this.x = dHPrivateKeySpec.getX();
        this.elSpec = new ElGamalParameterSpec(dHPrivateKeySpec.getP(), dHPrivateKeySpec.getG());
    }

    BCElGamalPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        ElGamalParameter elGamalParameter = ElGamalParameter.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        ASN1Integer aSN1Integer = ASN1Integer.getInstance(privateKeyInfo.parsePrivateKey());
        this.x = aSN1Integer.getValue();
        this.elSpec = new ElGamalParameterSpec(elGamalParameter.getP(), elGamalParameter.getG());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeyParameters elGamalPrivateKeyParameters) {
        this.x = elGamalPrivateKeyParameters.getX();
        this.elSpec = new ElGamalParameterSpec(elGamalPrivateKeyParameters.getParameters().getP(), elGamalPrivateKeyParameters.getParameters().getG());
    }

    public String getAlgorithm() {
        return "ElGamal";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        try {
            PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.elGamalAlgorithm, new ElGamalParameter(this.elSpec.getP(), this.elSpec.getG())), new ASN1Integer(this.getX()));
            return privateKeyInfo.getEncoded("DER");
        } catch (IOException iOException) {
            return null;
        }
    }

    public ElGamalParameterSpec getParameters() {
        return this.elSpec;
    }

    public DHParameterSpec getParams() {
        return new DHParameterSpec(this.elSpec.getP(), this.elSpec.getG());
    }

    public BigInteger getX() {
        return this.x;
    }

    public boolean equals(Object object) {
        if (!(object instanceof DHPrivateKey)) {
            return false;
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey)object;
        return this.getX().equals(dHPrivateKey.getX()) && this.getParams().getG().equals(dHPrivateKey.getParams().getG()) && this.getParams().getP().equals(dHPrivateKey.getParams().getP()) && this.getParams().getL() == dHPrivateKey.getParams().getL();
    }

    public int hashCode() {
        return this.getX().hashCode() ^ this.getParams().getG().hashCode() ^ this.getParams().getP().hashCode() ^ this.getParams().getL();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.elSpec = new ElGamalParameterSpec((BigInteger)objectInputStream.readObject(), (BigInteger)objectInputStream.readObject());
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.elSpec.getP());
        objectOutputStream.writeObject(this.elSpec.getG());
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
}

