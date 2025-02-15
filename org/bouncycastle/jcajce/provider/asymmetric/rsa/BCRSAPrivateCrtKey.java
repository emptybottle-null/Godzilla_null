/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.BCRSAPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.util.Strings;

public class BCRSAPrivateCrtKey
extends BCRSAPrivateKey
implements RSAPrivateCrtKey {
    static final long serialVersionUID = 7834723820638524718L;
    private BigInteger publicExponent;
    private BigInteger primeP;
    private BigInteger primeQ;
    private BigInteger primeExponentP;
    private BigInteger primeExponentQ;
    private BigInteger crtCoefficient;

    BCRSAPrivateCrtKey(RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters) {
        super(rSAPrivateCrtKeyParameters);
        this.publicExponent = rSAPrivateCrtKeyParameters.getPublicExponent();
        this.primeP = rSAPrivateCrtKeyParameters.getP();
        this.primeQ = rSAPrivateCrtKeyParameters.getQ();
        this.primeExponentP = rSAPrivateCrtKeyParameters.getDP();
        this.primeExponentQ = rSAPrivateCrtKeyParameters.getDQ();
        this.crtCoefficient = rSAPrivateCrtKeyParameters.getQInv();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKeySpec rSAPrivateCrtKeySpec) {
        this.modulus = rSAPrivateCrtKeySpec.getModulus();
        this.publicExponent = rSAPrivateCrtKeySpec.getPublicExponent();
        this.privateExponent = rSAPrivateCrtKeySpec.getPrivateExponent();
        this.primeP = rSAPrivateCrtKeySpec.getPrimeP();
        this.primeQ = rSAPrivateCrtKeySpec.getPrimeQ();
        this.primeExponentP = rSAPrivateCrtKeySpec.getPrimeExponentP();
        this.primeExponentQ = rSAPrivateCrtKeySpec.getPrimeExponentQ();
        this.crtCoefficient = rSAPrivateCrtKeySpec.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKey rSAPrivateCrtKey) {
        this.modulus = rSAPrivateCrtKey.getModulus();
        this.publicExponent = rSAPrivateCrtKey.getPublicExponent();
        this.privateExponent = rSAPrivateCrtKey.getPrivateExponent();
        this.primeP = rSAPrivateCrtKey.getPrimeP();
        this.primeQ = rSAPrivateCrtKey.getPrimeQ();
        this.primeExponentP = rSAPrivateCrtKey.getPrimeExponentP();
        this.primeExponentQ = rSAPrivateCrtKey.getPrimeExponentQ();
        this.crtCoefficient = rSAPrivateCrtKey.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this(RSAPrivateKey.getInstance(privateKeyInfo.parsePrivateKey()));
    }

    BCRSAPrivateCrtKey(RSAPrivateKey rSAPrivateKey) {
        this.modulus = rSAPrivateKey.getModulus();
        this.publicExponent = rSAPrivateKey.getPublicExponent();
        this.privateExponent = rSAPrivateKey.getPrivateExponent();
        this.primeP = rSAPrivateKey.getPrime1();
        this.primeQ = rSAPrivateKey.getPrime2();
        this.primeExponentP = rSAPrivateKey.getExponent1();
        this.primeExponentQ = rSAPrivateKey.getExponent2();
        this.crtCoefficient = rSAPrivateKey.getCoefficient();
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        return KeyUtil.getEncodedPrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPrivateKey(this.getModulus(), this.getPublicExponent(), this.getPrivateExponent(), this.getPrimeP(), this.getPrimeQ(), this.getPrimeExponentP(), this.getPrimeExponentQ(), this.getCrtCoefficient()));
    }

    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }

    public BigInteger getPrimeP() {
        return this.primeP;
    }

    public BigInteger getPrimeQ() {
        return this.primeQ;
    }

    public BigInteger getPrimeExponentP() {
        return this.primeExponentP;
    }

    public BigInteger getPrimeExponentQ() {
        return this.primeExponentQ;
    }

    public BigInteger getCrtCoefficient() {
        return this.crtCoefficient;
    }

    public int hashCode() {
        return this.getModulus().hashCode() ^ this.getPublicExponent().hashCode() ^ this.getPrivateExponent().hashCode();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RSAPrivateCrtKey)) {
            return false;
        }
        RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey)object;
        return this.getModulus().equals(rSAPrivateCrtKey.getModulus()) && this.getPublicExponent().equals(rSAPrivateCrtKey.getPublicExponent()) && this.getPrivateExponent().equals(rSAPrivateCrtKey.getPrivateExponent()) && this.getPrimeP().equals(rSAPrivateCrtKey.getPrimeP()) && this.getPrimeQ().equals(rSAPrivateCrtKey.getPrimeQ()) && this.getPrimeExponentP().equals(rSAPrivateCrtKey.getPrimeExponentP()) && this.getPrimeExponentQ().equals(rSAPrivateCrtKey.getPrimeExponentQ()) && this.getCrtCoefficient().equals(rSAPrivateCrtKey.getCrtCoefficient());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = Strings.lineSeparator();
        stringBuffer.append("RSA Private CRT Key [").append(RSAUtil.generateKeyFingerprint(this.getModulus(), this.getPublicExponent())).append("]").append(string);
        stringBuffer.append("            modulus: ").append(this.getModulus().toString(16)).append(string);
        stringBuffer.append("    public exponent: ").append(this.getPublicExponent().toString(16)).append(string);
        return stringBuffer.toString();
    }
}

