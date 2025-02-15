/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.netscape;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class NetscapeCertRequest
extends ASN1Object {
    AlgorithmIdentifier sigAlg;
    AlgorithmIdentifier keyAlg;
    byte[] sigBits;
    String challenge;
    DERBitString content;
    PublicKey pubkey;

    private static ASN1Sequence getReq(byte[] byArray) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(new ByteArrayInputStream(byArray));
        return ASN1Sequence.getInstance(aSN1InputStream.readObject());
    }

    public NetscapeCertRequest(byte[] byArray) throws IOException {
        this(NetscapeCertRequest.getReq(byArray));
    }

    public NetscapeCertRequest(ASN1Sequence aSN1Sequence) {
        try {
            if (aSN1Sequence.size() != 3) {
                throw new IllegalArgumentException("invalid SPKAC (size):" + aSN1Sequence.size());
            }
            this.sigAlg = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.sigBits = ((DERBitString)aSN1Sequence.getObjectAt(2)).getOctets();
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(0);
            if (aSN1Sequence2.size() != 2) {
                throw new IllegalArgumentException("invalid PKAC (len): " + aSN1Sequence2.size());
            }
            this.challenge = ((DERIA5String)aSN1Sequence2.getObjectAt(1)).getString();
            this.content = new DERBitString(aSN1Sequence2);
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence2.getObjectAt(0));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getBytes());
            this.keyAlg = subjectPublicKeyInfo.getAlgorithm();
            this.pubkey = KeyFactory.getInstance(this.keyAlg.getAlgorithm().getId(), "BC").generatePublic(x509EncodedKeySpec);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception.toString());
        }
    }

    public NetscapeCertRequest(String string, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        this.challenge = string;
        this.sigAlg = algorithmIdentifier;
        this.pubkey = publicKey;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(string));
        try {
            this.content = new DERBitString(new DERSequence(aSN1EncodableVector));
        } catch (IOException iOException) {
            throw new InvalidKeySpecException("exception encoding key: " + iOException.toString());
        }
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String string) {
        this.challenge = string;
    }

    public AlgorithmIdentifier getSigningAlgorithm() {
        return this.sigAlg;
    }

    public void setSigningAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.sigAlg = algorithmIdentifier;
    }

    public AlgorithmIdentifier getKeyAlgorithm() {
        return this.keyAlg;
    }

    public void setKeyAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.keyAlg = algorithmIdentifier;
    }

    public PublicKey getPublicKey() {
        return this.pubkey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.pubkey = publicKey;
    }

    public boolean verify(String string) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
        if (!string.equals(this.challenge)) {
            return false;
        }
        Signature signature = Signature.getInstance(this.sigAlg.getAlgorithm().getId(), "BC");
        signature.initVerify(this.pubkey);
        signature.update(this.content.getBytes());
        return signature.verify(this.sigBits);
    }

    public void sign(PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, InvalidKeySpecException {
        this.sign(privateKey, null);
    }

    public void sign(PrivateKey privateKey, SecureRandom secureRandom) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, InvalidKeySpecException {
        Signature signature = Signature.getInstance(this.sigAlg.getAlgorithm().getId(), "BC");
        if (secureRandom != null) {
            signature.initSign(privateKey, secureRandom);
        } else {
            signature.initSign(privateKey);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(this.challenge));
        try {
            signature.update(new DERSequence(aSN1EncodableVector).getEncoded("DER"));
        } catch (IOException iOException) {
            throw new SignatureException(iOException.getMessage());
        }
        this.sigBits = signature.sign();
    }

    private ASN1Primitive getKeySpec() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1Primitive aSN1Primitive = null;
        try {
            byteArrayOutputStream.write(this.pubkey.getEncoded());
            byteArrayOutputStream.close();
            ASN1InputStream aSN1InputStream = new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            aSN1Primitive = aSN1InputStream.readObject();
        } catch (IOException iOException) {
            throw new InvalidKeySpecException(iOException.getMessage());
        }
        return aSN1Primitive;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        try {
            aSN1EncodableVector2.add(this.getKeySpec());
        } catch (Exception exception) {
            // empty catch block
        }
        aSN1EncodableVector2.add(new DERIA5String(this.challenge));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(this.sigAlg);
        aSN1EncodableVector.add(new DERBitString(this.sigBits));
        return new DERSequence(aSN1EncodableVector);
    }
}

