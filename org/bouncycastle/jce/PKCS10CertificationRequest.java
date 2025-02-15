/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Strings;

public class PKCS10CertificationRequest
extends CertificationRequest {
    private static Hashtable algorithms = new Hashtable();
    private static Hashtable params = new Hashtable();
    private static Hashtable keyAlgorithms = new Hashtable();
    private static Hashtable oids = new Hashtable();
    private static Set noParams = new HashSet();

    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier algorithmIdentifier, int n) {
        return new RSASSAPSSparams(algorithmIdentifier, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, algorithmIdentifier), new ASN1Integer(n), new ASN1Integer(1L));
    }

    private static ASN1Sequence toDERSequence(byte[] byArray) {
        try {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
            return (ASN1Sequence)aSN1InputStream.readObject();
        } catch (Exception exception) {
            throw new IllegalArgumentException("badly encoded request");
        }
    }

    public PKCS10CertificationRequest(byte[] byArray) {
        super(PKCS10CertificationRequest.toDERSequence(byArray));
    }

    public PKCS10CertificationRequest(ASN1Sequence aSN1Sequence) {
        super(aSN1Sequence);
    }

    public PKCS10CertificationRequest(String string, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        this(string, x509Name, publicKey, aSN1Set, privateKey, "BC");
    }

    private static X509Name convertName(X500Principal x500Principal) {
        try {
            return new X509Principal(x500Principal.getEncoded());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("can't convert name");
        }
    }

    public PKCS10CertificationRequest(String string, X500Principal x500Principal, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        this(string, PKCS10CertificationRequest.convertName(x500Principal), publicKey, aSN1Set, privateKey, "BC");
    }

    public PKCS10CertificationRequest(String string, X500Principal x500Principal, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey, String string2) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        this(string, PKCS10CertificationRequest.convertName(x500Principal), publicKey, aSN1Set, privateKey, string2);
    }

    public PKCS10CertificationRequest(String string, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey, String string2) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Object object;
        String string3 = Strings.toUpperCase(string);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)algorithms.get(string3);
        if (aSN1ObjectIdentifier == null) {
            try {
                aSN1ObjectIdentifier = new ASN1ObjectIdentifier(string3);
            } catch (Exception exception) {
                throw new IllegalArgumentException("Unknown signature type requested");
            }
        }
        if (x509Name == null) {
            throw new IllegalArgumentException("subject must not be null");
        }
        if (publicKey == null) {
            throw new IllegalArgumentException("public key must not be null");
        }
        this.sigAlgId = noParams.contains(aSN1ObjectIdentifier) ? new AlgorithmIdentifier(aSN1ObjectIdentifier) : (params.containsKey(string3) ? new AlgorithmIdentifier(aSN1ObjectIdentifier, (ASN1Encodable)params.get(string3)) : new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE));
        try {
            object = (ASN1Sequence)ASN1Primitive.fromByteArray(publicKey.getEncoded());
            this.reqInfo = new CertificationRequestInfo(x509Name, SubjectPublicKeyInfo.getInstance(object), aSN1Set);
        } catch (IOException iOException) {
            throw new IllegalArgumentException("can't encode public key");
        }
        object = string2 == null ? Signature.getInstance(string) : Signature.getInstance(string, string2);
        ((Signature)object).initSign(privateKey);
        try {
            ((Signature)object).update(this.reqInfo.getEncoded("DER"));
        } catch (Exception exception) {
            throw new IllegalArgumentException("exception encoding TBS cert request - " + exception);
        }
        this.sigBits = new DERBitString(((Signature)object).sign());
    }

    public PublicKey getPublicKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        return this.getPublicKey("BC");
    }

    public PublicKey getPublicKey(String string) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.reqInfo.getSubjectPublicKeyInfo();
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getOctets());
            AlgorithmIdentifier algorithmIdentifier = subjectPublicKeyInfo.getAlgorithm();
            try {
                if (string == null) {
                    return KeyFactory.getInstance(algorithmIdentifier.getAlgorithm().getId()).generatePublic(x509EncodedKeySpec);
                }
                return KeyFactory.getInstance(algorithmIdentifier.getAlgorithm().getId(), string).generatePublic(x509EncodedKeySpec);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                if (keyAlgorithms.get(algorithmIdentifier.getAlgorithm()) != null) {
                    String string2 = (String)keyAlgorithms.get(algorithmIdentifier.getAlgorithm());
                    if (string == null) {
                        return KeyFactory.getInstance(string2).generatePublic(x509EncodedKeySpec);
                    }
                    return KeyFactory.getInstance(string2, string).generatePublic(x509EncodedKeySpec);
                }
                throw noSuchAlgorithmException;
            }
        } catch (InvalidKeySpecException invalidKeySpecException) {
            throw new InvalidKeyException("error decoding public key");
        } catch (IOException iOException) {
            throw new InvalidKeyException("error decoding public key");
        }
    }

    public boolean verify() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        return this.verify("BC");
    }

    public boolean verify(String string) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        return this.verify(this.getPublicKey(string), string);
    }

    public boolean verify(PublicKey publicKey, String string) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature signature;
        try {
            signature = string == null ? Signature.getInstance(PKCS10CertificationRequest.getSignatureName(this.sigAlgId)) : Signature.getInstance(PKCS10CertificationRequest.getSignatureName(this.sigAlgId), string);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            if (oids.get(this.sigAlgId.getAlgorithm()) != null) {
                String string2 = (String)oids.get(this.sigAlgId.getAlgorithm());
                signature = string == null ? Signature.getInstance(string2) : Signature.getInstance(string2, string);
            }
            throw noSuchAlgorithmException;
        }
        this.setSignatureParameters(signature, this.sigAlgId.getParameters());
        signature.initVerify(publicKey);
        try {
            signature.update(this.reqInfo.getEncoded("DER"));
        } catch (Exception exception) {
            throw new SignatureException("exception encoding TBS cert request - " + exception);
        }
        return signature.verify(this.sigBits.getOctets());
    }

    public byte[] getEncoded() {
        try {
            return this.getEncoded("DER");
        } catch (IOException iOException) {
            throw new RuntimeException(iOException.toString());
        }
    }

    private void setSignatureParameters(Signature signature, ASN1Encodable aSN1Encodable) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (aSN1Encodable != null && !DERNull.INSTANCE.equals(aSN1Encodable)) {
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try {
                algorithmParameters.init(aSN1Encodable.toASN1Primitive().getEncoded("DER"));
            } catch (IOException iOException) {
                throw new SignatureException("IOException decoding parameters: " + iOException.getMessage());
            }
            if (signature.getAlgorithm().endsWith("MGF1")) {
                try {
                    signature.setParameter(algorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new SignatureException("Exception extracting parameters: " + generalSecurityException.getMessage());
                }
            }
        }
    }

    static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
        if (aSN1Encodable != null && !DERNull.INSTANCE.equals(aSN1Encodable) && algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(aSN1Encodable);
            return PKCS10CertificationRequest.getDigestAlgName(rSASSAPSSparams.getHashAlgorithm().getAlgorithm()) + "withRSAandMGF1";
        }
        return algorithmIdentifier.getAlgorithm().getId();
    }

    private static String getDigestAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (PKCSObjectIdentifiers.md5.equals(aSN1ObjectIdentifier)) {
            return "MD5";
        }
        if (OIWObjectIdentifiers.idSHA1.equals(aSN1ObjectIdentifier)) {
            return "SHA1";
        }
        if (NISTObjectIdentifiers.id_sha224.equals(aSN1ObjectIdentifier)) {
            return "SHA224";
        }
        if (NISTObjectIdentifiers.id_sha256.equals(aSN1ObjectIdentifier)) {
            return "SHA256";
        }
        if (NISTObjectIdentifiers.id_sha384.equals(aSN1ObjectIdentifier)) {
            return "SHA384";
        }
        if (NISTObjectIdentifiers.id_sha512.equals(aSN1ObjectIdentifier)) {
            return "SHA512";
        }
        if (TeleTrusTObjectIdentifiers.ripemd128.equals(aSN1ObjectIdentifier)) {
            return "RIPEMD128";
        }
        if (TeleTrusTObjectIdentifiers.ripemd160.equals(aSN1ObjectIdentifier)) {
            return "RIPEMD160";
        }
        if (TeleTrusTObjectIdentifiers.ripemd256.equals(aSN1ObjectIdentifier)) {
            return "RIPEMD256";
        }
        if (CryptoProObjectIdentifiers.gostR3411.equals(aSN1ObjectIdentifier)) {
            return "GOST3411";
        }
        return aSN1ObjectIdentifier.getId();
    }

    static {
        algorithms.put("MD2WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD2WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD5WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("MD5WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("RSAWITHMD5", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("SHA1WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA224WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA224WITHRSA", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA256WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA256WITHRSA", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA384WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA384WITHRSA", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA512WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA512WITHRSA", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA1WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA224WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA256WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA384WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA512WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("RSAWITHSHA1", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("RIPEMD128WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD128WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD160WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD160WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD256WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("RIPEMD256WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("SHA1WITHDSA", new ASN1ObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("DSAWITHSHA1", new ASN1ObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("SHA224WITHDSA", NISTObjectIdentifiers.dsa_with_sha224);
        algorithms.put("SHA256WITHDSA", NISTObjectIdentifiers.dsa_with_sha256);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        algorithms.put("SHA1WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("SHA224WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA224);
        algorithms.put("SHA256WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA256);
        algorithms.put("SHA384WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA384);
        algorithms.put("SHA512WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA512);
        algorithms.put("ECDSAWITHSHA1", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3410WITHGOST3411", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHECGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHECGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        oids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        oids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        oids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        oids.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA1);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA224);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA256);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA384);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA512);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha224);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha256);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        params.put("SHA1WITHRSAANDMGF1", PKCS10CertificationRequest.creatPSSParams(algorithmIdentifier, 20));
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE);
        params.put("SHA224WITHRSAANDMGF1", PKCS10CertificationRequest.creatPSSParams(algorithmIdentifier2, 28));
        AlgorithmIdentifier algorithmIdentifier3 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        params.put("SHA256WITHRSAANDMGF1", PKCS10CertificationRequest.creatPSSParams(algorithmIdentifier3, 32));
        AlgorithmIdentifier algorithmIdentifier4 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        params.put("SHA384WITHRSAANDMGF1", PKCS10CertificationRequest.creatPSSParams(algorithmIdentifier4, 48));
        AlgorithmIdentifier algorithmIdentifier5 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        params.put("SHA512WITHRSAANDMGF1", PKCS10CertificationRequest.creatPSSParams(algorithmIdentifier5, 64));
    }
}

