/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.keystore.bcfks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.EncryptedObjectStoreData;
import org.bouncycastle.asn1.bc.EncryptedPrivateKeyData;
import org.bouncycastle.asn1.bc.EncryptedSecretKeyData;
import org.bouncycastle.asn1.bc.ObjectData;
import org.bouncycastle.asn1.bc.ObjectDataSequence;
import org.bouncycastle.asn1.bc.ObjectStore;
import org.bouncycastle.asn1.bc.ObjectStoreData;
import org.bouncycastle.asn1.bc.ObjectStoreIntegrityCheck;
import org.bouncycastle.asn1.bc.PbkdMacIntegrityCheck;
import org.bouncycastle.asn1.bc.SecretKeyData;
import org.bouncycastle.asn1.cms.CCMParameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class BcFKSKeyStoreSpi
extends KeyStoreSpi {
    private static final Map<String, ASN1ObjectIdentifier> oidMap = new HashMap<String, ASN1ObjectIdentifier>();
    private static final Map<ASN1ObjectIdentifier, String> publicAlgMap = new HashMap<ASN1ObjectIdentifier, String>();
    private static final BigInteger CERTIFICATE;
    private static final BigInteger PRIVATE_KEY;
    private static final BigInteger SECRET_KEY;
    private static final BigInteger PROTECTED_PRIVATE_KEY;
    private static final BigInteger PROTECTED_SECRET_KEY;
    private final BouncyCastleProvider provider;
    private final Map<String, ObjectData> entries = new HashMap<String, ObjectData>();
    private final Map<String, PrivateKey> privateKeyCache = new HashMap<String, PrivateKey>();
    private AlgorithmIdentifier hmacAlgorithm;
    private KeyDerivationFunc hmacPkbdAlgorithm;
    private Date creationDate;
    private Date lastModifiedDate;

    private static String getPublicKeyAlg(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String string = publicAlgMap.get(aSN1ObjectIdentifier);
        if (string != null) {
            return string;
        }
        return aSN1ObjectIdentifier.getId();
    }

    BcFKSKeyStoreSpi(BouncyCastleProvider bouncyCastleProvider) {
        this.provider = bouncyCastleProvider;
    }

    @Override
    public Key engineGetKey(String string, char[] cArray) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            if (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY)) {
                PrivateKey privateKey = this.privateKeyCache.get(string);
                if (privateKey != null) {
                    return privateKey;
                }
                EncryptedPrivateKeyData encryptedPrivateKeyData = EncryptedPrivateKeyData.getInstance(objectData.getData());
                EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(encryptedPrivateKeyData.getEncryptedPrivateKeyInfo());
                try {
                    PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(this.decryptData("PRIVATE_KEY_ENCRYPTION", encryptedPrivateKeyInfo.getEncryptionAlgorithm(), cArray, encryptedPrivateKeyInfo.getEncryptedData()));
                    KeyFactory keyFactory = this.provider != null ? KeyFactory.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), this.provider) : KeyFactory.getInstance(BcFKSKeyStoreSpi.getPublicKeyAlg(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm()));
                    PrivateKey privateKey2 = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded()));
                    this.privateKeyCache.put(string, privateKey2);
                    return privateKey2;
                } catch (Exception exception) {
                    throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover private key (" + string + "): " + exception.getMessage());
                }
            }
            if (objectData.getType().equals(SECRET_KEY) || objectData.getType().equals(PROTECTED_SECRET_KEY)) {
                EncryptedSecretKeyData encryptedSecretKeyData = EncryptedSecretKeyData.getInstance(objectData.getData());
                try {
                    SecretKeyData secretKeyData = SecretKeyData.getInstance(this.decryptData("SECRET_KEY_ENCRYPTION", encryptedSecretKeyData.getKeyEncryptionAlgorithm(), cArray, encryptedSecretKeyData.getEncryptedKeyData()));
                    SecretKeyFactory secretKeyFactory = this.provider != null ? SecretKeyFactory.getInstance(secretKeyData.getKeyAlgorithm().getId(), this.provider) : SecretKeyFactory.getInstance(secretKeyData.getKeyAlgorithm().getId());
                    return secretKeyFactory.generateSecret(new SecretKeySpec(secretKeyData.getKeyBytes(), secretKeyData.getKeyAlgorithm().getId()));
                } catch (Exception exception) {
                    throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + string + "): " + exception.getMessage());
                }
            }
            throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + string + "): type not recognized");
        }
        return null;
    }

    @Override
    public java.security.cert.Certificate[] engineGetCertificateChain(String string) {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null && (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY))) {
            EncryptedPrivateKeyData encryptedPrivateKeyData = EncryptedPrivateKeyData.getInstance(objectData.getData());
            Certificate[] certificateArray = encryptedPrivateKeyData.getCertificateChain();
            java.security.cert.Certificate[] certificateArray2 = new X509Certificate[certificateArray.length];
            for (int i = 0; i != certificateArray2.length; ++i) {
                certificateArray2[i] = this.decodeCertificate(certificateArray[i]);
            }
            return certificateArray2;
        }
        return null;
    }

    @Override
    public java.security.cert.Certificate engineGetCertificate(String string) {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            if (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY)) {
                EncryptedPrivateKeyData encryptedPrivateKeyData = EncryptedPrivateKeyData.getInstance(objectData.getData());
                Certificate[] certificateArray = encryptedPrivateKeyData.getCertificateChain();
                return this.decodeCertificate(certificateArray[0]);
            }
            if (objectData.getType().equals(CERTIFICATE)) {
                return this.decodeCertificate(objectData.getData());
            }
        }
        return null;
    }

    private java.security.cert.Certificate decodeCertificate(Object object) {
        if (this.provider != null) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", this.provider);
                return certificateFactory.generateCertificate(new ByteArrayInputStream(Certificate.getInstance(object).getEncoded()));
            } catch (Exception exception) {
                return null;
            }
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            return certificateFactory.generateCertificate(new ByteArrayInputStream(Certificate.getInstance(object).getEncoded()));
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public Date engineGetCreationDate(String string) {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            try {
                return objectData.getLastModifiedDate().getDate();
            } catch (ParseException parseException) {
                return new Date();
            }
        }
        return null;
    }

    @Override
    public void engineSetKeyEntry(String string, Key key, char[] cArray, java.security.cert.Certificate[] certificateArray) throws KeyStoreException {
        Date date;
        Date date2 = date = new Date();
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            date = this.extractCreationDate(objectData, date);
        }
        this.privateKeyCache.remove(string);
        if (key instanceof PrivateKey) {
            if (certificateArray == null) {
                throw new KeyStoreException("BCFKS KeyStore requires a certificate chain for private key storage.");
            }
            try {
                byte[] byArray = key.getEncoded();
                KeyDerivationFunc keyDerivationFunc = this.generatePkbdAlgorithmIdentifier(32);
                byte[] byArray2 = this.generateKey(keyDerivationFunc, "PRIVATE_KEY_ENCRYPTION", cArray != null ? cArray : new char[]{});
                Cipher cipher = this.provider == null ? Cipher.getInstance("AES/CCM/NoPadding") : Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                cipher.init(1, new SecretKeySpec(byArray2, "AES"));
                byte[] byArray3 = cipher.doFinal(byArray);
                AlgorithmParameters algorithmParameters = cipher.getParameters();
                PBES2Parameters pBES2Parameters = new PBES2Parameters(keyDerivationFunc, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(algorithmParameters.getEncoded())));
                EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, pBES2Parameters), byArray3);
                EncryptedPrivateKeyData encryptedPrivateKeyData = this.createPrivateKeySequence(encryptedPrivateKeyInfo, certificateArray);
                this.entries.put(string, new ObjectData(PRIVATE_KEY, string, date, date2, encryptedPrivateKeyData.getEncoded(), null));
            } catch (Exception exception) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + exception.toString(), exception);
            }
        } else if (key instanceof SecretKey) {
            if (certificateArray != null) {
                throw new KeyStoreException("BCFKS KeyStore cannot store certificate chain with secret key.");
            }
            try {
                Object object;
                byte[] byArray;
                byte[] byArray4 = key.getEncoded();
                KeyDerivationFunc keyDerivationFunc = this.generatePkbdAlgorithmIdentifier(32);
                byte[] byArray5 = this.generateKey(keyDerivationFunc, "SECRET_KEY_ENCRYPTION", cArray != null ? cArray : new char[]{});
                Cipher cipher = this.provider == null ? Cipher.getInstance("AES/CCM/NoPadding") : Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                cipher.init(1, new SecretKeySpec(byArray5, "AES"));
                String string2 = Strings.toUpperCase(key.getAlgorithm());
                if (string2.indexOf("AES") > -1) {
                    byArray = cipher.doFinal(new SecretKeyData(NISTObjectIdentifiers.aes, byArray4).getEncoded());
                } else {
                    object = oidMap.get(string2);
                    if (object != null) {
                        byArray = cipher.doFinal(new SecretKeyData((ASN1ObjectIdentifier)object, byArray4).getEncoded());
                    } else {
                        throw new KeyStoreException("BCFKS KeyStore cannot recognize secret key (" + string2 + ") for storage.");
                    }
                }
                object = cipher.getParameters();
                PBES2Parameters pBES2Parameters = new PBES2Parameters(keyDerivationFunc, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(((AlgorithmParameters)object).getEncoded())));
                EncryptedSecretKeyData encryptedSecretKeyData = new EncryptedSecretKeyData(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, pBES2Parameters), byArray);
                this.entries.put(string, new ObjectData(SECRET_KEY, string, date, date2, encryptedSecretKeyData.getEncoded(), null));
            } catch (Exception exception) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + exception.toString(), exception);
            }
        } else {
            throw new KeyStoreException("BCFKS KeyStore unable to recognize key.");
        }
        this.lastModifiedDate = date2;
    }

    private SecureRandom getDefaultSecureRandom() {
        return new SecureRandom();
    }

    private EncryptedPrivateKeyData createPrivateKeySequence(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo, java.security.cert.Certificate[] certificateArray) throws CertificateEncodingException {
        Certificate[] certificateArray2 = new Certificate[certificateArray.length];
        for (int i = 0; i != certificateArray.length; ++i) {
            certificateArray2[i] = Certificate.getInstance(certificateArray[i].getEncoded());
        }
        return new EncryptedPrivateKeyData(encryptedPrivateKeyInfo, certificateArray2);
    }

    @Override
    public void engineSetKeyEntry(String string, byte[] byArray, java.security.cert.Certificate[] certificateArray) throws KeyStoreException {
        Date date;
        Date date2 = date = new Date();
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            date = this.extractCreationDate(objectData, date);
        }
        if (certificateArray != null) {
            EncryptedPrivateKeyInfo encryptedPrivateKeyInfo;
            try {
                encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(byArray);
            } catch (Exception exception) {
                throw new ExtKeyStoreException("BCFKS KeyStore private key encoding must be an EncryptedPrivateKeyInfo.", exception);
            }
            try {
                this.privateKeyCache.remove(string);
                this.entries.put(string, new ObjectData(PROTECTED_PRIVATE_KEY, string, date, date2, this.createPrivateKeySequence(encryptedPrivateKeyInfo, certificateArray).getEncoded(), null));
            } catch (Exception exception) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + exception.toString(), exception);
            }
        }
        try {
            this.entries.put(string, new ObjectData(PROTECTED_SECRET_KEY, string, date, date2, byArray, null));
        } catch (Exception exception) {
            throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + exception.toString(), exception);
        }
        this.lastModifiedDate = date2;
    }

    @Override
    public void engineSetCertificateEntry(String string, java.security.cert.Certificate certificate) throws KeyStoreException {
        Date date;
        ObjectData objectData = this.entries.get(string);
        Date date2 = date = new Date();
        if (objectData != null) {
            if (!objectData.getType().equals(CERTIFICATE)) {
                throw new KeyStoreException("BCFKS KeyStore already has a key entry with alias " + string);
            }
            date = this.extractCreationDate(objectData, date);
        }
        try {
            this.entries.put(string, new ObjectData(CERTIFICATE, string, date, date2, certificate.getEncoded(), null));
        } catch (CertificateEncodingException certificateEncodingException) {
            throw new ExtKeyStoreException("BCFKS KeyStore unable to handle certificate: " + certificateEncodingException.getMessage(), certificateEncodingException);
        }
        this.lastModifiedDate = date2;
    }

    private Date extractCreationDate(ObjectData objectData, Date date) {
        try {
            date = objectData.getCreationDate().getDate();
        } catch (ParseException parseException) {
            // empty catch block
        }
        return date;
    }

    @Override
    public void engineDeleteEntry(String string) throws KeyStoreException {
        ObjectData objectData = this.entries.get(string);
        if (objectData == null) {
            return;
        }
        this.privateKeyCache.remove(string);
        this.entries.remove(string);
        this.lastModifiedDate = new Date();
    }

    @Override
    public Enumeration<String> engineAliases() {
        final Iterator<String> iterator = new HashSet<String>(this.entries.keySet()).iterator();
        return new Enumeration(){

            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            public Object nextElement() {
                return iterator.next();
            }
        };
    }

    @Override
    public boolean engineContainsAlias(String string) {
        if (string == null) {
            throw new NullPointerException("alias value is null");
        }
        return this.entries.containsKey(string);
    }

    @Override
    public int engineSize() {
        return this.entries.size();
    }

    @Override
    public boolean engineIsKeyEntry(String string) {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            BigInteger bigInteger = objectData.getType();
            return bigInteger.equals(PRIVATE_KEY) || bigInteger.equals(SECRET_KEY) || bigInteger.equals(PROTECTED_PRIVATE_KEY) || bigInteger.equals(PROTECTED_SECRET_KEY);
        }
        return false;
    }

    @Override
    public boolean engineIsCertificateEntry(String string) {
        ObjectData objectData = this.entries.get(string);
        if (objectData != null) {
            return objectData.getType().equals(CERTIFICATE);
        }
        return false;
    }

    @Override
    public String engineGetCertificateAlias(java.security.cert.Certificate certificate) {
        byte[] byArray;
        if (certificate == null) {
            return null;
        }
        try {
            byArray = certificate.getEncoded();
        } catch (CertificateEncodingException certificateEncodingException) {
            return null;
        }
        for (String string : this.entries.keySet()) {
            ObjectData objectData = this.entries.get(string);
            if (objectData.getType().equals(CERTIFICATE)) {
                if (!Arrays.areEqual(objectData.getData(), byArray)) continue;
                return string;
            }
            if (!objectData.getType().equals(PRIVATE_KEY) && !objectData.getType().equals(PROTECTED_PRIVATE_KEY)) continue;
            try {
                EncryptedPrivateKeyData encryptedPrivateKeyData = EncryptedPrivateKeyData.getInstance(objectData.getData());
                if (!Arrays.areEqual(encryptedPrivateKeyData.getCertificateChain()[0].toASN1Primitive().getEncoded(), byArray)) continue;
                return string;
            } catch (IOException iOException) {
            }
        }
        return null;
    }

    private byte[] generateKey(KeyDerivationFunc keyDerivationFunc, String string, char[] cArray) throws IOException {
        PBKDF2Params pBKDF2Params;
        byte[] byArray = PBEParametersGenerator.PKCS12PasswordToBytes(cArray);
        byte[] byArray2 = PBEParametersGenerator.PKCS12PasswordToBytes(string.toCharArray());
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA512Digest());
        if (keyDerivationFunc.getAlgorithm().equals(PKCSObjectIdentifiers.id_PBKDF2)) {
            pBKDF2Params = PBKDF2Params.getInstance(keyDerivationFunc.getParameters());
            if (!pBKDF2Params.getPrf().getAlgorithm().equals(PKCSObjectIdentifiers.id_hmacWithSHA512)) {
                throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD PRF.");
            }
        } else {
            throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD.");
        }
        pKCS5S2ParametersGenerator.init(Arrays.concatenate(byArray, byArray2), pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
        int n = pBKDF2Params.getKeyLength().intValue();
        return ((KeyParameter)pKCS5S2ParametersGenerator.generateDerivedParameters(n * 8)).getKey();
    }

    private void verifyMac(byte[] byArray, PbkdMacIntegrityCheck pbkdMacIntegrityCheck, char[] cArray) throws NoSuchAlgorithmException, IOException {
        byte[] byArray2 = this.calculateMac(byArray, pbkdMacIntegrityCheck.getMacAlgorithm(), pbkdMacIntegrityCheck.getPbkdAlgorithm(), cArray);
        if (!Arrays.constantTimeAreEqual(byArray2, pbkdMacIntegrityCheck.getMac())) {
            throw new IOException("BCFKS KeyStore corrupted: MAC calculation failed.");
        }
    }

    private byte[] calculateMac(byte[] byArray, AlgorithmIdentifier algorithmIdentifier, KeyDerivationFunc keyDerivationFunc, char[] cArray) throws NoSuchAlgorithmException, IOException {
        String string = algorithmIdentifier.getAlgorithm().getId();
        Mac mac = this.provider != null ? Mac.getInstance(string, this.provider) : Mac.getInstance(string);
        try {
            mac.init(new SecretKeySpec(this.generateKey(keyDerivationFunc, "INTEGRITY_CHECK", cArray != null ? cArray : new char[]{}), string));
        } catch (InvalidKeyException invalidKeyException) {
            throw new IOException("Cannot set up MAC calculation: " + invalidKeyException.getMessage());
        }
        return mac.doFinal(byArray);
    }

    @Override
    public void engineStore(OutputStream outputStream, char[] cArray) throws IOException, NoSuchAlgorithmException, CertificateException {
        EncryptedObjectStoreData encryptedObjectStoreData;
        ASN1Object aSN1Object;
        Object object;
        byte[] byArray;
        Object object2;
        ObjectData[] objectDataArray = this.entries.values().toArray(new ObjectData[this.entries.size()]);
        KeyDerivationFunc keyDerivationFunc = this.generatePkbdAlgorithmIdentifier(32);
        byte[] byArray2 = this.generateKey(keyDerivationFunc, "STORE_ENCRYPTION", cArray != null ? cArray : new char[]{});
        ObjectStoreData objectStoreData = new ObjectStoreData(this.hmacAlgorithm, this.creationDate, this.lastModifiedDate, new ObjectDataSequence(objectDataArray), null);
        try {
            object2 = this.provider == null ? Cipher.getInstance("AES/CCM/NoPadding") : Cipher.getInstance("AES/CCM/NoPadding", this.provider);
            ((Cipher)object2).init(1, new SecretKeySpec(byArray2, "AES"));
            byArray = ((Cipher)object2).doFinal(objectStoreData.getEncoded());
            object = ((Cipher)object2).getParameters();
            aSN1Object = new PBES2Parameters(keyDerivationFunc, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(((AlgorithmParameters)object).getEncoded())));
            encryptedObjectStoreData = new EncryptedObjectStoreData(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, aSN1Object), byArray);
        } catch (NoSuchPaddingException noSuchPaddingException) {
            throw new NoSuchAlgorithmException(noSuchPaddingException.toString());
        } catch (BadPaddingException badPaddingException) {
            throw new IOException(badPaddingException.toString());
        } catch (IllegalBlockSizeException illegalBlockSizeException) {
            throw new IOException(illegalBlockSizeException.toString());
        } catch (InvalidKeyException invalidKeyException) {
            throw new IOException(invalidKeyException.toString());
        }
        object2 = PBKDF2Params.getInstance(this.hmacPkbdAlgorithm.getParameters());
        byArray = new byte[((PBKDF2Params)object2).getSalt().length];
        this.getDefaultSecureRandom().nextBytes(byArray);
        this.hmacPkbdAlgorithm = new KeyDerivationFunc(this.hmacPkbdAlgorithm.getAlgorithm(), new PBKDF2Params(byArray, ((PBKDF2Params)object2).getIterationCount().intValue(), ((PBKDF2Params)object2).getKeyLength().intValue(), ((PBKDF2Params)object2).getPrf()));
        object = this.calculateMac(encryptedObjectStoreData.getEncoded(), this.hmacAlgorithm, this.hmacPkbdAlgorithm, cArray);
        aSN1Object = new ObjectStore(encryptedObjectStoreData, new ObjectStoreIntegrityCheck(new PbkdMacIntegrityCheck(this.hmacAlgorithm, this.hmacPkbdAlgorithm, (byte[])object)));
        outputStream.write(aSN1Object.getEncoded());
        outputStream.flush();
    }

    @Override
    public void engineLoad(InputStream inputStream, char[] cArray) throws IOException, NoSuchAlgorithmException, CertificateException {
        ObjectStoreData objectStoreData;
        ASN1Object aSN1Object;
        Object object;
        this.entries.clear();
        this.privateKeyCache.clear();
        this.creationDate = null;
        this.lastModifiedDate = null;
        this.hmacAlgorithm = null;
        if (inputStream == null) {
            this.lastModifiedDate = this.creationDate = new Date();
            this.hmacAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, DERNull.INSTANCE);
            this.hmacPkbdAlgorithm = this.generatePkbdAlgorithmIdentifier(64);
            return;
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(inputStream);
        ObjectStore objectStore = ObjectStore.getInstance(aSN1InputStream.readObject());
        ObjectStoreIntegrityCheck objectStoreIntegrityCheck = objectStore.getIntegrityCheck();
        if (objectStoreIntegrityCheck.getType() != 0) {
            throw new IOException("BCFKS KeyStore unable to recognize integrity check.");
        }
        ASN1Encodable aSN1Encodable = PbkdMacIntegrityCheck.getInstance(objectStoreIntegrityCheck.getIntegrityCheck());
        this.hmacAlgorithm = aSN1Encodable.getMacAlgorithm();
        this.hmacPkbdAlgorithm = aSN1Encodable.getPbkdAlgorithm();
        this.verifyMac(objectStore.getStoreData().toASN1Primitive().getEncoded(), (PbkdMacIntegrityCheck)aSN1Encodable, cArray);
        aSN1Encodable = objectStore.getStoreData();
        if (aSN1Encodable instanceof EncryptedObjectStoreData) {
            object = (EncryptedObjectStoreData)aSN1Encodable;
            aSN1Object = ((EncryptedObjectStoreData)object).getEncryptionAlgorithm();
            objectStoreData = ObjectStoreData.getInstance(this.decryptData("STORE_ENCRYPTION", (AlgorithmIdentifier)aSN1Object, cArray, ((EncryptedObjectStoreData)object).getEncryptedContent().getOctets()));
        } else {
            objectStoreData = ObjectStoreData.getInstance(aSN1Encodable);
        }
        try {
            this.creationDate = objectStoreData.getCreationDate().getDate();
            this.lastModifiedDate = objectStoreData.getLastModifiedDate().getDate();
        } catch (ParseException parseException) {
            throw new IOException("BCFKS KeyStore unable to parse store data information.");
        }
        if (!objectStoreData.getIntegrityAlgorithm().equals(this.hmacAlgorithm)) {
            throw new IOException("BCFKS KeyStore storeData integrity algorithm does not match store integrity algorithm.");
        }
        object = objectStoreData.getObjectDataSequence().iterator();
        while (object.hasNext()) {
            aSN1Object = ObjectData.getInstance(object.next());
            this.entries.put(((ObjectData)aSN1Object).getIdentifier(), (ObjectData)aSN1Object);
        }
    }

    private byte[] decryptData(String string, AlgorithmIdentifier algorithmIdentifier, char[] cArray, byte[] byArray) throws IOException {
        if (!algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_PBES2)) {
            throw new IOException("BCFKS KeyStore cannot recognize protection algorithm.");
        }
        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        EncryptionScheme encryptionScheme = pBES2Parameters.getEncryptionScheme();
        if (!encryptionScheme.getAlgorithm().equals(NISTObjectIdentifiers.id_aes256_CCM)) {
            throw new IOException("BCFKS KeyStore cannot recognize protection encryption algorithm.");
        }
        try {
            AlgorithmParameters algorithmParameters;
            Cipher cipher;
            CCMParameters cCMParameters = CCMParameters.getInstance(encryptionScheme.getParameters());
            if (this.provider == null) {
                cipher = Cipher.getInstance("AES/CCM/NoPadding");
                algorithmParameters = AlgorithmParameters.getInstance("CCM");
            } else {
                cipher = Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                algorithmParameters = AlgorithmParameters.getInstance("CCM", this.provider);
            }
            algorithmParameters.init(cCMParameters.getEncoded());
            byte[] byArray2 = this.generateKey(pBES2Parameters.getKeyDerivationFunc(), string, cArray != null ? cArray : new char[]{});
            cipher.init(2, (Key)new SecretKeySpec(byArray2, "AES"), algorithmParameters);
            byte[] byArray3 = cipher.doFinal(byArray);
            return byArray3;
        } catch (Exception exception) {
            throw new IOException(exception.toString());
        }
    }

    private KeyDerivationFunc generatePkbdAlgorithmIdentifier(int n) {
        byte[] byArray = new byte[64];
        this.getDefaultSecureRandom().nextBytes(byArray);
        return new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(byArray, 1024, n, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, DERNull.INSTANCE)));
    }

    static {
        oidMap.put("DESEDE", OIWObjectIdentifiers.desEDE);
        oidMap.put("TRIPLEDES", OIWObjectIdentifiers.desEDE);
        oidMap.put("TDEA", OIWObjectIdentifiers.desEDE);
        oidMap.put("HMACSHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
        oidMap.put("HMACSHA224", PKCSObjectIdentifiers.id_hmacWithSHA224);
        oidMap.put("HMACSHA256", PKCSObjectIdentifiers.id_hmacWithSHA256);
        oidMap.put("HMACSHA384", PKCSObjectIdentifiers.id_hmacWithSHA384);
        oidMap.put("HMACSHA512", PKCSObjectIdentifiers.id_hmacWithSHA512);
        publicAlgMap.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        publicAlgMap.put(X9ObjectIdentifiers.id_ecPublicKey, "EC");
        publicAlgMap.put(OIWObjectIdentifiers.elGamalAlgorithm, "DH");
        publicAlgMap.put(PKCSObjectIdentifiers.dhKeyAgreement, "DH");
        publicAlgMap.put(X9ObjectIdentifiers.id_dsa, "DSA");
        CERTIFICATE = BigInteger.valueOf(0L);
        PRIVATE_KEY = BigInteger.valueOf(1L);
        SECRET_KEY = BigInteger.valueOf(2L);
        PROTECTED_PRIVATE_KEY = BigInteger.valueOf(3L);
        PROTECTED_SECRET_KEY = BigInteger.valueOf(4L);
    }

    public static class Def
    extends BcFKSKeyStoreSpi {
        public Def() {
            super(null);
        }
    }

    private static class ExtKeyStoreException
    extends KeyStoreException {
        private final Throwable cause;

        ExtKeyStoreException(String string, Throwable throwable) {
            super(string);
            this.cause = throwable;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }

    public static class Std
    extends BcFKSKeyStoreSpi {
        public Std() {
            super(new BouncyCastleProvider());
        }
    }
}

