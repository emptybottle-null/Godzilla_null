/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.mozilla;

import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Encodable;

public class SignedPublicKeyAndChallenge
implements Encodable {
    protected final org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge spkacSeq;

    public SignedPublicKeyAndChallenge(byte[] byArray) {
        this.spkacSeq = org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge.getInstance(byArray);
    }

    protected SignedPublicKeyAndChallenge(org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge signedPublicKeyAndChallenge) {
        this.spkacSeq = signedPublicKeyAndChallenge;
    }

    public org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge toASN1Structure() {
        return this.spkacSeq;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.spkacSeq.toASN1Primitive();
    }

    public PublicKeyAndChallenge getPublicKeyAndChallenge() {
        return this.spkacSeq.getPublicKeyAndChallenge();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OperatorCreationException, IOException {
        ContentVerifier contentVerifier = contentVerifierProvider.get(this.spkacSeq.getSignatureAlgorithm());
        OutputStream outputStream = contentVerifier.getOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
        dEROutputStream.writeObject(this.spkacSeq.getPublicKeyAndChallenge());
        outputStream.close();
        return contentVerifier.verify(this.spkacSeq.getSignature().getOctets());
    }

    public boolean verify() throws NoSuchAlgorithmException, SignatureException, NoSuchProviderException, InvalidKeyException {
        return this.verify(null);
    }

    public boolean verify(String string) throws NoSuchAlgorithmException, SignatureException, NoSuchProviderException, InvalidKeyException {
        Signature signature = null;
        signature = string == null ? Signature.getInstance(this.spkacSeq.getSignatureAlgorithm().getAlgorithm().getId()) : Signature.getInstance(this.spkacSeq.getSignatureAlgorithm().getAlgorithm().getId(), string);
        PublicKey publicKey = this.getPublicKey(string);
        signature.initVerify(publicKey);
        try {
            signature.update(this.spkacSeq.getPublicKeyAndChallenge().getEncoded());
            return signature.verify(this.spkacSeq.getSignature().getBytes());
        } catch (Exception exception) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.spkacSeq.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
    }

    public String getChallenge() {
        return this.spkacSeq.getPublicKeyAndChallenge().getChallenge().getString();
    }

    public PublicKey getPublicKey(String string) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.spkacSeq.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
        try {
            DERBitString dERBitString = new DERBitString(subjectPublicKeyInfo);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dERBitString.getOctets());
            AlgorithmIdentifier algorithmIdentifier = subjectPublicKeyInfo.getAlgorithm();
            KeyFactory keyFactory = KeyFactory.getInstance(algorithmIdentifier.getAlgorithm().getId(), string);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception exception) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.toASN1Structure().getEncoded();
    }
}

