/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.keystore.pkcs12;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BEROutputStream;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.AuthenticatedSafe;
import org.bouncycastle.asn1.pkcs.CertBag;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.EncryptedData;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.Pfx;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12StoreParameter;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JDKPKCS12StoreParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class PKCS12KeyStoreSpi
extends KeyStoreSpi
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers,
BCKeyStore {
    static final String PKCS12_MAX_IT_COUNT_PROPERTY = "org.bouncycastle.pkcs12.max_it_count";
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private static final int SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 1024;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private Hashtable localIds = new Hashtable();
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private Hashtable keyCerts = new Hashtable();
    static final int NULL = 0;
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int SECRET = 3;
    static final int SEALED = 4;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    protected SecureRandom random = new SecureRandom();
    private CertificateFactory certFact;
    private ASN1ObjectIdentifier keyAlgorithm;
    private ASN1ObjectIdentifier certAlgorithm;
    private AlgorithmIdentifier macAlgorithm = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    private int itCount = 1024;
    private int saltLength = 20;

    public PKCS12KeyStoreSpi(Provider provider, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.certAlgorithm = aSN1ObjectIdentifier2;
        try {
            this.certFact = provider != null ? CertificateFactory.getInstance("X.509", provider) : CertificateFactory.getInstance("X.509");
        } catch (Exception exception) {
            throw new IllegalArgumentException("can't create cert factory - " + exception.toString());
        }
    }

    private SubjectKeyIdentifier createSubjectKeyId(PublicKey publicKey) {
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
            return new SubjectKeyIdentifier(PKCS12KeyStoreSpi.getDigest(subjectPublicKeyInfo));
        } catch (Exception exception) {
            throw new RuntimeException("error creating key");
        }
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Digest digest = DigestFactory.createSHA1();
        byte[] byArray = new byte[digest.getDigestSize()];
        byte[] byArray2 = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        digest.update(byArray2, 0, byArray2.length);
        digest.doFinal(byArray, 0);
        return byArray;
    }

    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public Enumeration engineAliases() {
        Hashtable<Object, String> hashtable = new Hashtable<Object, String>();
        Enumeration enumeration = this.certs.keys();
        while (enumeration.hasMoreElements()) {
            hashtable.put(enumeration.nextElement(), "cert");
        }
        enumeration = this.keys.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            if (hashtable.get(string) != null) continue;
            hashtable.put(string, "key");
        }
        return hashtable.keys();
    }

    public boolean engineContainsAlias(String string) {
        return this.certs.get(string) != null || this.keys.get(string) != null;
    }

    public void engineDeleteEntry(String string) throws KeyStoreException {
        Key key = (Key)this.keys.remove(string);
        Certificate certificate = (Certificate)this.certs.remove(string);
        if (certificate != null) {
            this.chainCerts.remove(new CertId(certificate.getPublicKey()));
        }
        if (key != null) {
            String string2 = (String)this.localIds.remove(string);
            if (string2 != null) {
                certificate = (Certificate)this.keyCerts.remove(string2);
            }
            if (certificate != null) {
                this.chainCerts.remove(new CertId(certificate.getPublicKey()));
            }
        }
    }

    public Certificate engineGetCertificate(String string) {
        if (string == null) {
            throw new IllegalArgumentException("null alias passed to getCertificate.");
        }
        Certificate certificate = (Certificate)this.certs.get(string);
        if (certificate == null) {
            String string2 = (String)this.localIds.get(string);
            certificate = string2 != null ? (Certificate)this.keyCerts.get(string2) : (Certificate)this.keyCerts.get(string);
        }
        return certificate;
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        String string;
        Certificate certificate2;
        Enumeration enumeration = this.certs.elements();
        Enumeration enumeration2 = this.certs.keys();
        while (enumeration.hasMoreElements()) {
            certificate2 = (Certificate)enumeration.nextElement();
            string = (String)enumeration2.nextElement();
            if (!certificate2.equals(certificate)) continue;
            return string;
        }
        enumeration = this.keyCerts.elements();
        enumeration2 = this.keyCerts.keys();
        while (enumeration.hasMoreElements()) {
            certificate2 = (Certificate)enumeration.nextElement();
            string = (String)enumeration2.nextElement();
            if (!certificate2.equals(certificate)) continue;
            return string;
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String string) {
        if (string == null) {
            throw new IllegalArgumentException("null alias passed to getCertificateChain.");
        }
        if (!this.engineIsKeyEntry(string)) {
            return null;
        }
        Certificate certificate = this.engineGetCertificate(string);
        if (certificate != null) {
            Certificate[] certificateArray;
            Vector<Certificate> vector = new Vector<Certificate>();
            while (certificate != null) {
                Object object;
                Object object2;
                Object object3;
                certificateArray = (Certificate[])certificate;
                Certificate certificate2 = null;
                byte[] byArray = certificateArray.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (byArray != null) {
                    try {
                        object3 = new ASN1InputStream(byArray);
                        object2 = ((ASN1OctetString)((ASN1InputStream)object3).readObject()).getOctets();
                        object3 = new ASN1InputStream((byte[])object2);
                        object = AuthorityKeyIdentifier.getInstance(((ASN1InputStream)object3).readObject());
                        if (((AuthorityKeyIdentifier)object).getKeyIdentifier() != null) {
                            certificate2 = (Certificate)this.chainCerts.get(new CertId(((AuthorityKeyIdentifier)object).getKeyIdentifier()));
                        }
                    } catch (IOException iOException) {
                        throw new RuntimeException(iOException.toString());
                    }
                }
                if (certificate2 == null && !(object3 = certificateArray.getIssuerDN()).equals(object2 = certificateArray.getSubjectDN())) {
                    object = this.chainCerts.keys();
                    while (object.hasMoreElements()) {
                        X509Certificate x509Certificate = (X509Certificate)this.chainCerts.get(object.nextElement());
                        Principal principal = x509Certificate.getSubjectDN();
                        if (!principal.equals(object3)) continue;
                        try {
                            certificateArray.verify(x509Certificate.getPublicKey());
                            certificate2 = x509Certificate;
                            break;
                        } catch (Exception exception) {
                        }
                    }
                }
                if (vector.contains(certificate)) {
                    certificate = null;
                    continue;
                }
                vector.addElement(certificate);
                if (certificate2 != certificate) {
                    certificate = certificate2;
                    continue;
                }
                certificate = null;
            }
            certificateArray = new Certificate[vector.size()];
            for (int i = 0; i != certificateArray.length; ++i) {
                certificateArray[i] = (Certificate)vector.elementAt(i);
            }
            return certificateArray;
        }
        return null;
    }

    public Date engineGetCreationDate(String string) {
        if (string == null) {
            throw new NullPointerException("alias == null");
        }
        if (this.keys.get(string) == null && this.certs.get(string) == null) {
            return null;
        }
        return new Date();
    }

    public Key engineGetKey(String string, char[] cArray) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (string == null) {
            throw new IllegalArgumentException("null alias passed to getKey.");
        }
        return (Key)this.keys.get(string);
    }

    public boolean engineIsCertificateEntry(String string) {
        return this.certs.get(string) != null && this.keys.get(string) == null;
    }

    public boolean engineIsKeyEntry(String string) {
        return this.keys.get(string) != null;
    }

    public void engineSetCertificateEntry(String string, Certificate certificate) throws KeyStoreException {
        if (this.keys.get(string) != null) {
            throw new KeyStoreException("There is a key entry with the name " + string + ".");
        }
        this.certs.put(string, certificate);
        this.chainCerts.put(new CertId(certificate.getPublicKey()), certificate);
    }

    public void engineSetKeyEntry(String string, byte[] byArray, Certificate[] certificateArray) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    public void engineSetKeyEntry(String string, Key key, char[] cArray, Certificate[] certificateArray) throws KeyStoreException {
        if (!(key instanceof PrivateKey)) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        }
        if (key instanceof PrivateKey && certificateArray == null) {
            throw new KeyStoreException("no certificate chain for private key");
        }
        if (this.keys.get(string) != null) {
            this.engineDeleteEntry(string);
        }
        this.keys.put(string, key);
        if (certificateArray != null) {
            this.certs.put(string, certificateArray[0]);
            for (int i = 0; i != certificateArray.length; ++i) {
                this.chainCerts.put(new CertId(certificateArray[i].getPublicKey()), certificateArray[i]);
            }
        }
    }

    public int engineSize() {
        Hashtable<Object, String> hashtable = new Hashtable<Object, String>();
        Enumeration enumeration = this.certs.keys();
        while (enumeration.hasMoreElements()) {
            hashtable.put(enumeration.nextElement(), "cert");
        }
        enumeration = this.keys.keys();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            if (hashtable.get(string) != null) continue;
            hashtable.put(string, "key");
        }
        return hashtable.size();
    }

    protected PrivateKey unwrapKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray, char[] cArray, boolean bl) throws IOException {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
        try {
            if (aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), this.validateIterationCount(pKCS12PBEParams.getIterations()));
                Cipher cipher = this.helper.createCipher(aSN1ObjectIdentifier.getId());
                PKCS12Key pKCS12Key = new PKCS12Key(cArray, bl);
                cipher.init(4, (Key)pKCS12Key, pBEParameterSpec);
                return (PrivateKey)cipher.unwrap(byArray, "", 2);
            }
            if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.id_PBES2)) {
                Cipher cipher = this.createCipher(4, cArray, algorithmIdentifier);
                return (PrivateKey)cipher.unwrap(byArray, "", 2);
            }
        } catch (Exception exception) {
            throw new IOException("exception unwrapping private key - " + exception.toString());
        }
        throw new IOException("exception unwrapping private key - cannot recognise: " + aSN1ObjectIdentifier);
    }

    protected byte[] wrapKey(String string, Key key, PKCS12PBEParams pKCS12PBEParams, char[] cArray) throws IOException {
        byte[] byArray;
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArray);
        try {
            SecretKeyFactory secretKeyFactory = this.helper.createSecretKeyFactory(string);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
            Cipher cipher = this.helper.createCipher(string);
            cipher.init(3, (Key)secretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            byArray = cipher.wrap(key);
        } catch (Exception exception) {
            throw new IOException("exception encrypting data - " + exception.toString());
        }
        return byArray;
    }

    protected byte[] cryptData(boolean bl, AlgorithmIdentifier algorithmIdentifier, char[] cArray, boolean bl2, byte[] byArray) throws IOException {
        int n;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
        int n2 = n = bl ? 1 : 2;
        if (aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            try {
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArray, bl2);
                Cipher cipher = this.helper.createCipher(aSN1ObjectIdentifier.getId());
                cipher.init(n, (Key)pKCS12Key, pBEParameterSpec);
                return cipher.doFinal(byArray);
            } catch (Exception exception) {
                throw new IOException("exception decrypting data - " + exception.toString());
            }
        }
        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.id_PBES2)) {
            try {
                Cipher cipher = this.createCipher(n, cArray, algorithmIdentifier);
                return cipher.doFinal(byArray);
            } catch (Exception exception) {
                throw new IOException("exception decrypting data - " + exception.toString());
            }
        }
        throw new IOException("unknown PBE algorithm: " + aSN1ObjectIdentifier);
    }

    private Cipher createCipher(int n, char[] cArray, AlgorithmIdentifier algorithmIdentifier) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException {
        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
        SecretKeyFactory secretKeyFactory = this.helper.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
        SecretKey secretKey = pBKDF2Params.isDefaultPrf() ? secretKeyFactory.generateSecret(new PBEKeySpec(cArray, pBKDF2Params.getSalt(), this.validateIterationCount(pBKDF2Params.getIterationCount()), keySizeProvider.getKeySize(algorithmIdentifier2))) : secretKeyFactory.generateSecret(new PBKDF2KeySpec(cArray, pBKDF2Params.getSalt(), this.validateIterationCount(pBKDF2Params.getIterationCount()), keySizeProvider.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
        Cipher cipher = Cipher.getInstance(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
        ASN1Encodable aSN1Encodable = pBES2Parameters.getEncryptionScheme().getParameters();
        if (aSN1Encodable instanceof ASN1OctetString) {
            cipher.init(n, (Key)secretKey, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Encodable).getOctets()));
        } else {
            GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(aSN1Encodable);
            cipher.init(n, (Key)secretKey, new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV()));
        }
        return cipher;
    }

    public void engineLoad(InputStream inputStream, char[] cArray) throws IOException {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        ASN1Object aSN1Object;
        Object object6;
        Object object7;
        Object object8;
        Object object9;
        ASN1Object aSN1Object2;
        if (inputStream == null) {
            return;
        }
        if (cArray == null) {
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(10);
        int n = bufferedInputStream.read();
        if (n != 48) {
            throw new IOException("stream does not represent a PKCS12 key store");
        }
        bufferedInputStream.reset();
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bufferedInputStream);
        ASN1Sequence aSN1Sequence = (ASN1Sequence)aSN1InputStream.readObject();
        Pfx pfx = Pfx.getInstance(aSN1Sequence);
        ContentInfo contentInfo = pfx.getAuthSafe();
        Vector<ASN1Object> vector = new Vector<ASN1Object>();
        boolean bl = false;
        boolean bl2 = false;
        if (pfx.getMacData() != null) {
            aSN1Object2 = pfx.getMacData();
            object9 = ((MacData)aSN1Object2).getMac();
            this.macAlgorithm = ((DigestInfo)object9).getAlgorithmId();
            byte[] byArray = ((MacData)aSN1Object2).getSalt();
            this.itCount = this.validateIterationCount(((MacData)aSN1Object2).getIterationCount());
            this.saltLength = byArray.length;
            object8 = ((ASN1OctetString)contentInfo.getContent()).getOctets();
            try {
                object7 = this.calculatePbeMac(this.macAlgorithm.getAlgorithm(), byArray, this.itCount, cArray, false, (byte[])object8);
                object6 = ((DigestInfo)object9).getDigest();
                if (!Arrays.constantTimeAreEqual(object7, (byte[])object6)) {
                    if (cArray.length > 0) {
                        throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                    }
                    object7 = this.calculatePbeMac(this.macAlgorithm.getAlgorithm(), byArray, this.itCount, cArray, true, (byte[])object8);
                    if (!Arrays.constantTimeAreEqual(object7, (byte[])object6)) {
                        throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                    }
                    bl2 = true;
                }
            } catch (IOException iOException) {
                throw iOException;
            } catch (Exception exception) {
                throw new IOException("error constructing MAC: " + exception.toString());
            }
        }
        this.keys = new IgnoresCaseHashtable();
        this.localIds = new Hashtable();
        if (contentInfo.getContentType().equals(data)) {
            aSN1InputStream = new ASN1InputStream(((ASN1OctetString)contentInfo.getContent()).getOctets());
            aSN1Object2 = AuthenticatedSafe.getInstance(aSN1InputStream.readObject());
            object9 = ((AuthenticatedSafe)aSN1Object2).getContentInfo();
            for (int i = 0; i != ((ContentInfo[])object9).length; ++i) {
                ASN1Encodable aSN1Encodable;
                ASN1Primitive aSN1Primitive;
                ASN1Primitive aSN1Primitive2;
                Object object10;
                Object object11;
                if (object9[i].getContentType().equals(data)) {
                    object8 = new ASN1InputStream(((ASN1OctetString)((ContentInfo)object9[i]).getContent()).getOctets());
                    object7 = (ASN1Sequence)((ASN1InputStream)object8).readObject();
                    for (int j = 0; j != object7.size(); ++j) {
                        SafeBag safeBag = SafeBag.getInstance(object7.getObjectAt(j));
                        if (safeBag.getBagId().equals(pkcs8ShroudedKeyBag)) {
                            aSN1Object = EncryptedPrivateKeyInfo.getInstance(safeBag.getBagValue());
                            object5 = this.unwrapKey(((EncryptedPrivateKeyInfo)aSN1Object).getEncryptionAlgorithm(), ((EncryptedPrivateKeyInfo)aSN1Object).getEncryptedData(), cArray, bl2);
                            object4 = (PKCS12BagAttributeCarrier)object5;
                            object3 = null;
                            object2 = null;
                            if (safeBag.getBagAttributes() != null) {
                                object = safeBag.getBagAttributes().getObjects();
                                while (object.hasMoreElements()) {
                                    object11 = (ASN1Sequence)object.nextElement();
                                    object10 = (ASN1ObjectIdentifier)((ASN1Sequence)object11).getObjectAt(0);
                                    aSN1Primitive2 = (ASN1Set)((ASN1Sequence)object11).getObjectAt(1);
                                    aSN1Primitive = null;
                                    if (((ASN1Set)aSN1Primitive2).size() > 0) {
                                        aSN1Primitive = (ASN1Primitive)((ASN1Set)aSN1Primitive2).getObjectAt(0);
                                        aSN1Encodable = object4.getBagAttribute((ASN1ObjectIdentifier)object10);
                                        if (aSN1Encodable != null) {
                                            if (!aSN1Encodable.toASN1Primitive().equals(aSN1Primitive)) {
                                                throw new IOException("attempt to add existing attribute with different value");
                                            }
                                        } else {
                                            object4.setBagAttribute((ASN1ObjectIdentifier)object10, aSN1Primitive);
                                        }
                                    }
                                    if (((ASN1Primitive)object10).equals(pkcs_9_at_friendlyName)) {
                                        object3 = ((DERBMPString)aSN1Primitive).getString();
                                        this.keys.put((String)object3, object5);
                                        continue;
                                    }
                                    if (!((ASN1Primitive)object10).equals(pkcs_9_at_localKeyId)) continue;
                                    object2 = (ASN1OctetString)aSN1Primitive;
                                }
                            }
                            if (object2 != null) {
                                object = new String(Hex.encode(((ASN1OctetString)object2).getOctets()));
                                if (object3 == null) {
                                    this.keys.put((String)object, object5);
                                    continue;
                                }
                                this.localIds.put(object3, object);
                                continue;
                            }
                            bl = true;
                            this.keys.put("unmarked", object5);
                            continue;
                        }
                        if (safeBag.getBagId().equals(certBag)) {
                            vector.addElement(safeBag);
                            continue;
                        }
                        System.out.println("extra in data " + safeBag.getBagId());
                        System.out.println(ASN1Dump.dumpAsString(safeBag));
                    }
                    continue;
                }
                if (((ContentInfo)object9[i]).getContentType().equals(encryptedData)) {
                    object8 = EncryptedData.getInstance(((ContentInfo)object9[i]).getContent());
                    object7 = this.cryptData(false, ((EncryptedData)object8).getEncryptionAlgorithm(), cArray, bl2, ((EncryptedData)object8).getContent().getOctets());
                    object6 = (ASN1Sequence)ASN1Primitive.fromByteArray(object7);
                    for (int j = 0; j != ((ASN1Sequence)object6).size(); ++j) {
                        ASN1Encodable aSN1Encodable2;
                        aSN1Object = SafeBag.getInstance(((ASN1Sequence)object6).getObjectAt(j));
                        if (((SafeBag)aSN1Object).getBagId().equals(certBag)) {
                            vector.addElement(aSN1Object);
                            continue;
                        }
                        if (((SafeBag)aSN1Object).getBagId().equals(pkcs8ShroudedKeyBag)) {
                            object5 = EncryptedPrivateKeyInfo.getInstance(((SafeBag)aSN1Object).getBagValue());
                            object4 = this.unwrapKey(((EncryptedPrivateKeyInfo)object5).getEncryptionAlgorithm(), ((EncryptedPrivateKeyInfo)object5).getEncryptedData(), cArray, bl2);
                            object3 = (PKCS12BagAttributeCarrier)object4;
                            object2 = null;
                            object = null;
                            object11 = ((SafeBag)aSN1Object).getBagAttributes().getObjects();
                            while (object11.hasMoreElements()) {
                                object10 = (ASN1Sequence)object11.nextElement();
                                aSN1Primitive2 = (ASN1ObjectIdentifier)((ASN1Sequence)object10).getObjectAt(0);
                                aSN1Primitive = (ASN1Set)((ASN1Sequence)object10).getObjectAt(1);
                                aSN1Encodable = null;
                                if (((ASN1Set)aSN1Primitive).size() > 0) {
                                    aSN1Encodable = (ASN1Primitive)((ASN1Set)aSN1Primitive).getObjectAt(0);
                                    aSN1Encodable2 = object3.getBagAttribute((ASN1ObjectIdentifier)aSN1Primitive2);
                                    if (aSN1Encodable2 != null) {
                                        if (!aSN1Encodable2.toASN1Primitive().equals(aSN1Encodable)) {
                                            throw new IOException("attempt to add existing attribute with different value");
                                        }
                                    } else {
                                        object3.setBagAttribute((ASN1ObjectIdentifier)aSN1Primitive2, aSN1Encodable);
                                    }
                                }
                                if (aSN1Primitive2.equals(pkcs_9_at_friendlyName)) {
                                    object2 = ((DERBMPString)aSN1Encodable).getString();
                                    this.keys.put((String)object2, object4);
                                    continue;
                                }
                                if (!aSN1Primitive2.equals(pkcs_9_at_localKeyId)) continue;
                                object = (ASN1OctetString)aSN1Encodable;
                            }
                            object10 = new String(Hex.encode(((ASN1OctetString)object).getOctets()));
                            if (object2 == null) {
                                this.keys.put((String)object10, object4);
                                continue;
                            }
                            this.localIds.put(object2, object10);
                            continue;
                        }
                        if (((SafeBag)aSN1Object).getBagId().equals(keyBag)) {
                            object5 = PrivateKeyInfo.getInstance(((SafeBag)aSN1Object).getBagValue());
                            object4 = BouncyCastleProvider.getPrivateKey((PrivateKeyInfo)object5);
                            object3 = (PKCS12BagAttributeCarrier)object4;
                            object2 = null;
                            object = null;
                            object11 = ((SafeBag)aSN1Object).getBagAttributes().getObjects();
                            while (object11.hasMoreElements()) {
                                object10 = ASN1Sequence.getInstance(object11.nextElement());
                                aSN1Primitive2 = ASN1ObjectIdentifier.getInstance(((ASN1Sequence)object10).getObjectAt(0));
                                aSN1Primitive = ASN1Set.getInstance(((ASN1Sequence)object10).getObjectAt(1));
                                aSN1Encodable = null;
                                if (((ASN1Set)aSN1Primitive).size() <= 0) continue;
                                aSN1Encodable = (ASN1Primitive)((ASN1Set)aSN1Primitive).getObjectAt(0);
                                aSN1Encodable2 = object3.getBagAttribute((ASN1ObjectIdentifier)aSN1Primitive2);
                                if (aSN1Encodable2 != null) {
                                    if (!aSN1Encodable2.toASN1Primitive().equals(aSN1Encodable)) {
                                        throw new IOException("attempt to add existing attribute with different value");
                                    }
                                } else {
                                    object3.setBagAttribute((ASN1ObjectIdentifier)aSN1Primitive2, aSN1Encodable);
                                }
                                if (aSN1Primitive2.equals(pkcs_9_at_friendlyName)) {
                                    object2 = ((DERBMPString)aSN1Encodable).getString();
                                    this.keys.put((String)object2, object4);
                                    continue;
                                }
                                if (!aSN1Primitive2.equals(pkcs_9_at_localKeyId)) continue;
                                object = (ASN1OctetString)aSN1Encodable;
                            }
                            object10 = new String(Hex.encode(((ASN1OctetString)object).getOctets()));
                            if (object2 == null) {
                                this.keys.put((String)object10, object4);
                                continue;
                            }
                            this.localIds.put(object2, object10);
                            continue;
                        }
                        System.out.println("extra in encryptedData " + ((SafeBag)aSN1Object).getBagId());
                        System.out.println(ASN1Dump.dumpAsString(aSN1Object));
                    }
                    continue;
                }
                System.out.println("extra " + ((ContentInfo)object9[i]).getContentType().getId());
                System.out.println("extra " + ASN1Dump.dumpAsString(((ContentInfo)object9[i]).getContent()));
            }
        }
        this.certs = new IgnoresCaseHashtable();
        this.chainCerts = new Hashtable();
        this.keyCerts = new Hashtable();
        for (int i = 0; i != vector.size(); ++i) {
            Object object12;
            object9 = (SafeBag)vector.elementAt(i);
            CertBag certBag = CertBag.getInstance(((SafeBag)object9).getBagValue());
            if (!certBag.getCertId().equals(x509Certificate)) {
                throw new RuntimeException("Unsupported certificate type: " + certBag.getCertId());
            }
            try {
                object7 = new ByteArrayInputStream(((ASN1OctetString)certBag.getCertValue()).getOctets());
                object8 = this.certFact.generateCertificate((InputStream)object7);
            } catch (Exception exception) {
                throw new RuntimeException(exception.toString());
            }
            object7 = null;
            object6 = null;
            if (((SafeBag)object9).getBagAttributes() != null) {
                object12 = ((SafeBag)object9).getBagAttributes().getObjects();
                while (object12.hasMoreElements()) {
                    aSN1Object = ASN1Sequence.getInstance(object12.nextElement());
                    object5 = ASN1ObjectIdentifier.getInstance(((ASN1Sequence)aSN1Object).getObjectAt(0));
                    object4 = ASN1Set.getInstance(((ASN1Sequence)aSN1Object).getObjectAt(1));
                    if (((ASN1Set)object4).size() <= 0) continue;
                    object3 = (ASN1Primitive)((ASN1Set)object4).getObjectAt(0);
                    object2 = null;
                    if (object8 instanceof PKCS12BagAttributeCarrier) {
                        object2 = (PKCS12BagAttributeCarrier)object8;
                        object = object2.getBagAttribute((ASN1ObjectIdentifier)object5);
                        if (object != null) {
                            if (!object.toASN1Primitive().equals(object3)) {
                                throw new IOException("attempt to add existing attribute with different value");
                            }
                        } else {
                            object2.setBagAttribute((ASN1ObjectIdentifier)object5, (ASN1Encodable)object3);
                        }
                    }
                    if (((ASN1Primitive)object5).equals(pkcs_9_at_friendlyName)) {
                        object6 = ((DERBMPString)object3).getString();
                        continue;
                    }
                    if (!((ASN1Primitive)object5).equals(pkcs_9_at_localKeyId)) continue;
                    object7 = (ASN1OctetString)object3;
                }
            }
            this.chainCerts.put(new CertId(((Certificate)object8).getPublicKey()), object8);
            if (bl) {
                if (!this.keyCerts.isEmpty()) continue;
                object12 = new String(Hex.encode(this.createSubjectKeyId(((Certificate)object8).getPublicKey()).getKeyIdentifier()));
                this.keyCerts.put(object12, object8);
                this.keys.put((String)object12, this.keys.remove("unmarked"));
                continue;
            }
            if (object7 != null) {
                object12 = new String(Hex.encode(object7.getOctets()));
                this.keyCerts.put(object12, object8);
            }
            if (object6 == null) continue;
            this.certs.put((String)object6, object8);
        }
    }

    private int validateIterationCount(BigInteger bigInteger) {
        int n = bigInteger.intValue();
        if (n < 0) {
            throw new IllegalStateException("negative iteration count found");
        }
        BigInteger bigInteger2 = Properties.asBigInteger(PKCS12_MAX_IT_COUNT_PROPERTY);
        if (bigInteger2 != null && bigInteger2.intValue() < n) {
            throw new IllegalStateException("iteration count " + n + " greater than " + bigInteger2.intValue());
        }
        return n;
    }

    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws IOException, NoSuchAlgorithmException, CertificateException {
        char[] cArray;
        if (loadStoreParameter == null) {
            throw new IllegalArgumentException("'param' arg cannot be null");
        }
        if (!(loadStoreParameter instanceof PKCS12StoreParameter) && !(loadStoreParameter instanceof JDKPKCS12StoreParameter)) {
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
        PKCS12StoreParameter pKCS12StoreParameter = loadStoreParameter instanceof PKCS12StoreParameter ? (PKCS12StoreParameter)loadStoreParameter : new PKCS12StoreParameter(((JDKPKCS12StoreParameter)loadStoreParameter).getOutputStream(), loadStoreParameter.getProtectionParameter(), ((JDKPKCS12StoreParameter)loadStoreParameter).isUseDEREncoding());
        KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
        if (protectionParameter == null) {
            cArray = null;
        } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
            cArray = ((KeyStore.PasswordProtection)protectionParameter).getPassword();
        } else {
            throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
        }
        this.doStore(pKCS12StoreParameter.getOutputStream(), cArray, pKCS12StoreParameter.isForDEREncoding());
    }

    public void engineStore(OutputStream outputStream, char[] cArray) throws IOException {
        this.doStore(outputStream, cArray, false);
    }

    private void doStore(OutputStream outputStream, char[] cArray, boolean bl) throws IOException {
        MacData macData;
        Object object;
        Object object2;
        Object object3;
        Object object4;
        Object object5;
        Object object6;
        ContentInfo[] contentInfoArray;
        Object object7;
        Object object8;
        Object object9;
        Object object10;
        AlgorithmIdentifier algorithmIdentifier;
        Object object11;
        Object object12;
        Object object13;
        Object object14;
        byte[] byArray;
        if (cArray == null) {
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumeration = this.keys.keys();
        while (enumeration.hasMoreElements()) {
            Object object15;
            byArray = new byte[20];
            this.random.nextBytes(byArray);
            object14 = (String)enumeration.nextElement();
            object13 = (PrivateKey)this.keys.get((String)object14);
            object12 = new PKCS12PBEParams(byArray, 1024);
            object11 = this.wrapKey(this.keyAlgorithm.getId(), (Key)object13, (PKCS12PBEParams)object12, cArray);
            algorithmIdentifier = new AlgorithmIdentifier(this.keyAlgorithm, ((PKCS12PBEParams)object12).toASN1Primitive());
            object10 = new EncryptedPrivateKeyInfo(algorithmIdentifier, (byte[])object11);
            boolean bl2 = false;
            object9 = new ASN1EncodableVector();
            if (object13 instanceof PKCS12BagAttributeCarrier) {
                object8 = (PKCS12BagAttributeCarrier)object13;
                object15 = (DERBMPString)object8.getBagAttribute(pkcs_9_at_friendlyName);
                if (object15 == null || !((DERBMPString)object15).getString().equals(object14)) {
                    object8.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString((String)object14));
                }
                if (object8.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                    object7 = this.engineGetCertificate((String)object14);
                    object8.setBagAttribute(pkcs_9_at_localKeyId, this.createSubjectKeyId(((Certificate)object7).getPublicKey()));
                }
                object7 = object8.getBagAttributeKeys();
                while (object7.hasMoreElements()) {
                    contentInfoArray = (ASN1ObjectIdentifier)object7.nextElement();
                    object6 = new ASN1EncodableVector();
                    ((ASN1EncodableVector)object6).add((ASN1Encodable)contentInfoArray);
                    ((ASN1EncodableVector)object6).add(new DERSet(object8.getBagAttribute((ASN1ObjectIdentifier)contentInfoArray)));
                    bl2 = true;
                    ((ASN1EncodableVector)object9).add(new DERSequence((ASN1EncodableVector)object6));
                }
            }
            if (!bl2) {
                object8 = new ASN1EncodableVector();
                object15 = this.engineGetCertificate((String)object14);
                ((ASN1EncodableVector)object8).add(pkcs_9_at_localKeyId);
                ((ASN1EncodableVector)object8).add(new DERSet(this.createSubjectKeyId(((Certificate)object15).getPublicKey())));
                ((ASN1EncodableVector)object9).add(new DERSequence((ASN1EncodableVector)object8));
                object8 = new ASN1EncodableVector();
                ((ASN1EncodableVector)object8).add(pkcs_9_at_friendlyName);
                ((ASN1EncodableVector)object8).add(new DERSet(new DERBMPString((String)object14)));
                ((ASN1EncodableVector)object9).add(new DERSequence((ASN1EncodableVector)object8));
            }
            object8 = new SafeBag(pkcs8ShroudedKeyBag, ((EncryptedPrivateKeyInfo)object10).toASN1Primitive(), new DERSet((ASN1EncodableVector)object9));
            aSN1EncodableVector.add((ASN1Encodable)object8);
        }
        byArray = new DERSequence(aSN1EncodableVector).getEncoded("DER");
        object14 = new BEROctetString(byArray);
        object13 = new byte[20];
        this.random.nextBytes((byte[])object13);
        object12 = new ASN1EncodableVector();
        object11 = new PKCS12PBEParams((byte[])object13, 1024);
        algorithmIdentifier = new AlgorithmIdentifier(this.certAlgorithm, ((PKCS12PBEParams)object11).toASN1Primitive());
        object10 = new Hashtable();
        Enumeration enumeration2 = this.keys.keys();
        while (enumeration2.hasMoreElements()) {
            try {
                object9 = (String)enumeration2.nextElement();
                object8 = this.engineGetCertificate((String)object9);
                boolean bl3 = false;
                object7 = new CertBag(x509Certificate, new DEROctetString(((Certificate)object8).getEncoded()));
                contentInfoArray = new ASN1EncodableVector();
                if (object8 instanceof PKCS12BagAttributeCarrier) {
                    object6 = (PKCS12BagAttributeCarrier)object8;
                    object5 = (DERBMPString)object6.getBagAttribute(pkcs_9_at_friendlyName);
                    if (object5 == null || !((DERBMPString)object5).getString().equals(object9)) {
                        object6.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString((String)object9));
                    }
                    if (object6.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                        object6.setBagAttribute(pkcs_9_at_localKeyId, this.createSubjectKeyId(((Certificate)object8).getPublicKey()));
                    }
                    object4 = object6.getBagAttributeKeys();
                    while (object4.hasMoreElements()) {
                        object3 = (ASN1ObjectIdentifier)object4.nextElement();
                        object2 = new ASN1EncodableVector();
                        ((ASN1EncodableVector)object2).add((ASN1Encodable)object3);
                        ((ASN1EncodableVector)object2).add(new DERSet(object6.getBagAttribute((ASN1ObjectIdentifier)object3)));
                        contentInfoArray.add(new DERSequence((ASN1EncodableVector)object2));
                        bl3 = true;
                    }
                }
                if (!bl3) {
                    object6 = new ASN1EncodableVector();
                    ((ASN1EncodableVector)object6).add(pkcs_9_at_localKeyId);
                    ((ASN1EncodableVector)object6).add(new DERSet(this.createSubjectKeyId(((Certificate)object8).getPublicKey())));
                    contentInfoArray.add(new DERSequence((ASN1EncodableVector)object6));
                    object6 = new ASN1EncodableVector();
                    ((ASN1EncodableVector)object6).add(pkcs_9_at_friendlyName);
                    ((ASN1EncodableVector)object6).add(new DERSet(new DERBMPString((String)object9)));
                    contentInfoArray.add(new DERSequence((ASN1EncodableVector)object6));
                }
                object6 = new SafeBag(certBag, ((CertBag)object7).toASN1Primitive(), new DERSet((ASN1EncodableVector)contentInfoArray));
                ((ASN1EncodableVector)object12).add((ASN1Encodable)object6);
                ((Hashtable)object10).put(object8, object8);
            } catch (CertificateEncodingException certificateEncodingException) {
                throw new IOException("Error encoding certificate: " + certificateEncodingException.toString());
            }
        }
        enumeration2 = this.certs.keys();
        while (enumeration2.hasMoreElements()) {
            try {
                object9 = (String)enumeration2.nextElement();
                object8 = (Certificate)this.certs.get((String)object9);
                boolean bl4 = false;
                if (this.keys.get((String)object9) != null) continue;
                object7 = new CertBag(x509Certificate, new DEROctetString(((Certificate)object8).getEncoded()));
                contentInfoArray = new ASN1EncodableVector();
                if (object8 instanceof PKCS12BagAttributeCarrier) {
                    object6 = (PKCS12BagAttributeCarrier)object8;
                    object5 = (DERBMPString)object6.getBagAttribute(pkcs_9_at_friendlyName);
                    if (object5 == null || !((DERBMPString)object5).getString().equals(object9)) {
                        object6.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString((String)object9));
                    }
                    object4 = object6.getBagAttributeKeys();
                    while (object4.hasMoreElements()) {
                        object3 = (ASN1ObjectIdentifier)object4.nextElement();
                        if (((ASN1Primitive)object3).equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId)) continue;
                        object2 = new ASN1EncodableVector();
                        ((ASN1EncodableVector)object2).add((ASN1Encodable)object3);
                        ((ASN1EncodableVector)object2).add(new DERSet(object6.getBagAttribute((ASN1ObjectIdentifier)object3)));
                        contentInfoArray.add(new DERSequence((ASN1EncodableVector)object2));
                        bl4 = true;
                    }
                }
                if (!bl4) {
                    object6 = new ASN1EncodableVector();
                    ((ASN1EncodableVector)object6).add(pkcs_9_at_friendlyName);
                    ((ASN1EncodableVector)object6).add(new DERSet(new DERBMPString((String)object9)));
                    contentInfoArray.add(new DERSequence((ASN1EncodableVector)object6));
                }
                object6 = new SafeBag(certBag, ((CertBag)object7).toASN1Primitive(), new DERSet((ASN1EncodableVector)contentInfoArray));
                ((ASN1EncodableVector)object12).add((ASN1Encodable)object6);
                ((Hashtable)object10).put(object8, object8);
            } catch (CertificateEncodingException certificateEncodingException) {
                throw new IOException("Error encoding certificate: " + certificateEncodingException.toString());
            }
        }
        object9 = this.getUsedCertificateSet();
        enumeration2 = this.chainCerts.keys();
        while (enumeration2.hasMoreElements()) {
            try {
                object8 = (CertId)enumeration2.nextElement();
                Certificate certificate = (Certificate)this.chainCerts.get(object8);
                if (!object9.contains(certificate) || ((Hashtable)object10).get(certificate) != null) continue;
                object7 = new CertBag(x509Certificate, new DEROctetString(certificate.getEncoded()));
                contentInfoArray = new ASN1EncodableVector();
                if (certificate instanceof PKCS12BagAttributeCarrier) {
                    object6 = (PKCS12BagAttributeCarrier)((Object)certificate);
                    object5 = object6.getBagAttributeKeys();
                    while (object5.hasMoreElements()) {
                        object4 = (ASN1ObjectIdentifier)object5.nextElement();
                        if (((ASN1Primitive)object4).equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId)) continue;
                        object3 = new ASN1EncodableVector();
                        ((ASN1EncodableVector)object3).add((ASN1Encodable)object4);
                        ((ASN1EncodableVector)object3).add(new DERSet(object6.getBagAttribute((ASN1ObjectIdentifier)object4)));
                        contentInfoArray.add(new DERSequence((ASN1EncodableVector)object3));
                    }
                }
                object6 = new SafeBag(certBag, ((CertBag)object7).toASN1Primitive(), new DERSet((ASN1EncodableVector)contentInfoArray));
                ((ASN1EncodableVector)object12).add((ASN1Encodable)object6);
            } catch (CertificateEncodingException certificateEncodingException) {
                throw new IOException("Error encoding certificate: " + certificateEncodingException.toString());
            }
        }
        object8 = new DERSequence((ASN1EncodableVector)object12).getEncoded("DER");
        byte[] byArray2 = this.cryptData(true, algorithmIdentifier, cArray, false, (byte[])object8);
        object7 = new EncryptedData(data, algorithmIdentifier, new BEROctetString(byArray2));
        contentInfoArray = new ContentInfo[]{new ContentInfo(data, (ASN1Encodable)object14), new ContentInfo(encryptedData, ((EncryptedData)object7).toASN1Primitive())};
        object6 = new AuthenticatedSafe(contentInfoArray);
        object5 = new ByteArrayOutputStream();
        object4 = bl ? new DEROutputStream((OutputStream)object5) : new BEROutputStream((OutputStream)object5);
        ((DEROutputStream)object4).writeObject((ASN1Encodable)object6);
        object3 = ((ByteArrayOutputStream)object5).toByteArray();
        object2 = new ContentInfo(data, new BEROctetString((byte[])object3));
        byte[] byArray3 = new byte[this.saltLength];
        this.random.nextBytes(byArray3);
        byte[] byArray4 = ((ASN1OctetString)((ContentInfo)object2).getContent()).getOctets();
        try {
            object = this.calculatePbeMac(this.macAlgorithm.getAlgorithm(), byArray3, this.itCount, cArray, false, byArray4);
            DigestInfo digestInfo = new DigestInfo(this.macAlgorithm, (byte[])object);
            macData = new MacData(digestInfo, byArray3, this.itCount);
        } catch (Exception exception) {
            throw new IOException("error constructing MAC: " + exception.toString());
        }
        object = new Pfx((ContentInfo)object2, macData);
        object4 = bl ? new DEROutputStream(outputStream) : new BEROutputStream(outputStream);
        ((DEROutputStream)object4).writeObject((ASN1Encodable)object);
    }

    private Set getUsedCertificateSet() {
        Object object;
        String string;
        HashSet<Object> hashSet = new HashSet<Object>();
        Enumeration enumeration = this.keys.keys();
        while (enumeration.hasMoreElements()) {
            string = (String)enumeration.nextElement();
            object = this.engineGetCertificateChain(string);
            for (int i = 0; i != ((Certificate[])object).length; ++i) {
                hashSet.add(object[i]);
            }
        }
        enumeration = this.certs.keys();
        while (enumeration.hasMoreElements()) {
            string = (String)enumeration.nextElement();
            object = this.engineGetCertificate(string);
            hashSet.add(object);
        }
        return hashSet;
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray, int n, char[] cArray, boolean bl, byte[] byArray2) throws Exception {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(byArray, n);
        Mac mac = this.helper.createMac(aSN1ObjectIdentifier.getId());
        mac.init(new PKCS12Key(cArray, bl), pBEParameterSpec);
        mac.update(byArray2);
        return mac.doFinal();
    }

    public static class BCPKCS12KeyStore
    extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class BCPKCS12KeyStore3DES
    extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private class CertId {
        byte[] id;

        CertId(PublicKey publicKey) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(publicKey).getKeyIdentifier();
        }

        CertId(byte[] byArray) {
            this.id = byArray;
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof CertId)) {
                return false;
            }
            CertId certId = (CertId)object;
            return Arrays.areEqual(this.id, certId.id);
        }
    }

    public static class DefPKCS12KeyStore
    extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class DefPKCS12KeyStore3DES
    extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            HashMap<ASN1ObjectIdentifier, Integer> hashMap = new HashMap<ASN1ObjectIdentifier, Integer>();
            hashMap.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            hashMap.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            hashMap.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            hashMap.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            hashMap.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(hashMap);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer n = (Integer)this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (n != null) {
                return n;
            }
            return -1;
        }
    }

    private static class IgnoresCaseHashtable {
        private Hashtable orig = new Hashtable();
        private Hashtable keys = new Hashtable();

        private IgnoresCaseHashtable() {
        }

        public void put(String string, Object object) {
            String string2 = string == null ? null : Strings.toLowerCase(string);
            String string3 = (String)this.keys.get(string2);
            if (string3 != null) {
                this.orig.remove(string3);
            }
            this.keys.put(string2, string);
            this.orig.put(string, object);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public Object remove(String string) {
            String string2 = (String)this.keys.remove(string == null ? null : Strings.toLowerCase(string));
            if (string2 == null) {
                return null;
            }
            return this.orig.remove(string2);
        }

        public Object get(String string) {
            String string2 = (String)this.keys.get(string == null ? null : Strings.toLowerCase(string));
            if (string2 == null) {
                return null;
            }
            return this.orig.get(string2);
        }

        public Enumeration elements() {
            return this.orig.elements();
        }
    }
}

