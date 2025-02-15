/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.keystore.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.io.DigestInputStream;
import org.bouncycastle.crypto.io.DigestOutputStream;
import org.bouncycastle.crypto.io.MacInputStream;
import org.bouncycastle.crypto.io.MacOutputStream;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.util.io.TeeOutputStream;

public class BcKeyStoreSpi
extends KeyStoreSpi
implements BCKeyStore {
    private static final int STORE_VERSION = 2;
    private static final int STORE_SALT_SIZE = 20;
    private static final String STORE_CIPHER = "PBEWithSHAAndTwofish-CBC";
    private static final int KEY_SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 1024;
    private static final String KEY_CIPHER = "PBEWithSHAAnd3-KeyTripleDES-CBC";
    static final int NULL = 0;
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int SECRET = 3;
    static final int SEALED = 4;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    protected Hashtable table = new Hashtable();
    protected SecureRandom random = new SecureRandom();
    protected int version;
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public BcKeyStoreSpi(int n) {
        this.version = n;
    }

    private void encodeCertificate(Certificate certificate, DataOutputStream dataOutputStream) throws IOException {
        try {
            byte[] byArray = certificate.getEncoded();
            dataOutputStream.writeUTF(certificate.getType());
            dataOutputStream.writeInt(byArray.length);
            dataOutputStream.write(byArray);
        } catch (CertificateEncodingException certificateEncodingException) {
            throw new IOException(certificateEncodingException.toString());
        }
    }

    private Certificate decodeCertificate(DataInputStream dataInputStream) throws IOException {
        String string = dataInputStream.readUTF();
        byte[] byArray = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(byArray);
        try {
            CertificateFactory certificateFactory = this.helper.createCertificateFactory(string);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
            return certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new IOException(noSuchProviderException.toString());
        } catch (CertificateException certificateException) {
            throw new IOException(certificateException.toString());
        }
    }

    private void encodeKey(Key key, DataOutputStream dataOutputStream) throws IOException {
        byte[] byArray = key.getEncoded();
        if (key instanceof PrivateKey) {
            dataOutputStream.write(0);
        } else if (key instanceof PublicKey) {
            dataOutputStream.write(1);
        } else {
            dataOutputStream.write(2);
        }
        dataOutputStream.writeUTF(key.getFormat());
        dataOutputStream.writeUTF(key.getAlgorithm());
        dataOutputStream.writeInt(byArray.length);
        dataOutputStream.write(byArray);
    }

    private Key decodeKey(DataInputStream dataInputStream) throws IOException {
        EncodedKeySpec encodedKeySpec;
        int n = dataInputStream.read();
        String string = dataInputStream.readUTF();
        String string2 = dataInputStream.readUTF();
        byte[] byArray = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(byArray);
        if (string.equals("PKCS#8") || string.equals("PKCS8")) {
            encodedKeySpec = new PKCS8EncodedKeySpec(byArray);
        } else if (string.equals("X.509") || string.equals("X509")) {
            encodedKeySpec = new X509EncodedKeySpec(byArray);
        } else {
            if (string.equals("RAW")) {
                return new SecretKeySpec(byArray, string2);
            }
            throw new IOException("Key format " + string + " not recognised!");
        }
        try {
            switch (n) {
                case 0: {
                    return this.helper.createKeyFactory(string2).generatePrivate(encodedKeySpec);
                }
                case 1: {
                    return this.helper.createKeyFactory(string2).generatePublic(encodedKeySpec);
                }
                case 2: {
                    return this.helper.createSecretKeyFactory(string2).generateSecret(encodedKeySpec);
                }
            }
            throw new IOException("Key type " + n + " not recognised!");
        } catch (Exception exception) {
            throw new IOException("Exception creating key: " + exception.toString());
        }
    }

    protected Cipher makePBECipher(String string, int n, char[] cArray, byte[] byArray, int n2) throws IOException {
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArray);
            SecretKeyFactory secretKeyFactory = this.helper.createSecretKeyFactory(string);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(byArray, n2);
            Cipher cipher = this.helper.createCipher(string);
            cipher.init(n, (Key)secretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return cipher;
        } catch (Exception exception) {
            throw new IOException("Error initialising store of key store: " + exception);
        }
    }

    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public Enumeration engineAliases() {
        return this.table.keys();
    }

    public boolean engineContainsAlias(String string) {
        return this.table.get(string) != null;
    }

    public void engineDeleteEntry(String string) throws KeyStoreException {
        Object v = this.table.get(string);
        if (v == null) {
            return;
        }
        this.table.remove(string);
    }

    public Certificate engineGetCertificate(String string) {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        if (storeEntry != null) {
            if (storeEntry.getType() == 1) {
                return (Certificate)storeEntry.getObject();
            }
            Certificate[] certificateArray = storeEntry.getCertificateChain();
            if (certificateArray != null) {
                return certificateArray[0];
            }
        }
        return null;
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration enumeration = this.table.elements();
        while (enumeration.hasMoreElements()) {
            Object object;
            StoreEntry storeEntry = (StoreEntry)enumeration.nextElement();
            if (!(storeEntry.getObject() instanceof Certificate ? ((Certificate)(object = (Certificate)storeEntry.getObject())).equals(certificate) : (object = storeEntry.getCertificateChain()) != null && ((Certificate)object[0]).equals(certificate))) continue;
            return storeEntry.getAlias();
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String string) {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        if (storeEntry != null) {
            return storeEntry.getCertificateChain();
        }
        return null;
    }

    public Date engineGetCreationDate(String string) {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        if (storeEntry != null) {
            return storeEntry.getDate();
        }
        return null;
    }

    public Key engineGetKey(String string, char[] cArray) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        if (storeEntry == null || storeEntry.getType() == 1) {
            return null;
        }
        return (Key)storeEntry.getObject(cArray);
    }

    public boolean engineIsCertificateEntry(String string) {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        return storeEntry != null && storeEntry.getType() == 1;
    }

    public boolean engineIsKeyEntry(String string) {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        return storeEntry != null && storeEntry.getType() != 1;
    }

    public void engineSetCertificateEntry(String string, Certificate certificate) throws KeyStoreException {
        StoreEntry storeEntry = (StoreEntry)this.table.get(string);
        if (storeEntry != null && storeEntry.getType() != 1) {
            throw new KeyStoreException("key store already has a key entry with alias " + string);
        }
        this.table.put(string, new StoreEntry(string, certificate));
    }

    public void engineSetKeyEntry(String string, byte[] byArray, Certificate[] certificateArray) throws KeyStoreException {
        this.table.put(string, new StoreEntry(string, byArray, certificateArray));
    }

    public void engineSetKeyEntry(String string, Key key, char[] cArray, Certificate[] certificateArray) throws KeyStoreException {
        if (key instanceof PrivateKey && certificateArray == null) {
            throw new KeyStoreException("no certificate chain for private key");
        }
        try {
            this.table.put(string, new StoreEntry(string, key, cArray, certificateArray));
        } catch (Exception exception) {
            throw new KeyStoreException(exception.toString());
        }
    }

    public int engineSize() {
        return this.table.size();
    }

    protected void loadStore(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int n = dataInputStream.read();
        while (n > 0) {
            String string = dataInputStream.readUTF();
            Date date = new Date(dataInputStream.readLong());
            int n2 = dataInputStream.readInt();
            Certificate[] certificateArray = null;
            if (n2 != 0) {
                certificateArray = new Certificate[n2];
                for (int i = 0; i != n2; ++i) {
                    certificateArray[i] = this.decodeCertificate(dataInputStream);
                }
            }
            switch (n) {
                case 1: {
                    Certificate certificate = this.decodeCertificate(dataInputStream);
                    this.table.put(string, new StoreEntry(string, date, 1, certificate));
                    break;
                }
                case 2: {
                    Key key = this.decodeKey(dataInputStream);
                    this.table.put(string, new StoreEntry(string, date, 2, key, certificateArray));
                    break;
                }
                case 3: 
                case 4: {
                    byte[] byArray = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(byArray);
                    this.table.put(string, new StoreEntry(string, date, n, byArray, certificateArray));
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown object type in store.");
                }
            }
            n = dataInputStream.read();
        }
    }

    protected void saveStore(OutputStream outputStream) throws IOException {
        Enumeration enumeration = this.table.elements();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        block5: while (enumeration.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry)enumeration.nextElement();
            dataOutputStream.write(storeEntry.getType());
            dataOutputStream.writeUTF(storeEntry.getAlias());
            dataOutputStream.writeLong(storeEntry.getDate().getTime());
            Certificate[] certificateArray = storeEntry.getCertificateChain();
            if (certificateArray == null) {
                dataOutputStream.writeInt(0);
            } else {
                dataOutputStream.writeInt(certificateArray.length);
                for (int i = 0; i != certificateArray.length; ++i) {
                    this.encodeCertificate(certificateArray[i], dataOutputStream);
                }
            }
            switch (storeEntry.getType()) {
                case 1: {
                    this.encodeCertificate((Certificate)storeEntry.getObject(), dataOutputStream);
                    continue block5;
                }
                case 2: {
                    this.encodeKey((Key)storeEntry.getObject(), dataOutputStream);
                    continue block5;
                }
                case 3: 
                case 4: {
                    byte[] byArray = (byte[])storeEntry.getObject();
                    dataOutputStream.writeInt(byArray.length);
                    dataOutputStream.write(byArray);
                    continue block5;
                }
            }
            throw new RuntimeException("Unknown object type in store.");
        }
        dataOutputStream.write(0);
    }

    public void engineLoad(InputStream inputStream, char[] cArray) throws IOException {
        this.table.clear();
        if (inputStream == null) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int n = dataInputStream.readInt();
        if (n != 2 && n != 0 && n != 1) {
            throw new IOException("Wrong version of key store.");
        }
        int n2 = dataInputStream.readInt();
        if (n2 <= 0) {
            throw new IOException("Invalid salt detected");
        }
        byte[] byArray = new byte[n2];
        dataInputStream.readFully(byArray);
        int n3 = dataInputStream.readInt();
        HMac hMac = new HMac(new SHA1Digest());
        if (cArray != null && cArray.length != 0) {
            byte[] byArray2 = PBEParametersGenerator.PKCS12PasswordToBytes(cArray);
            PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
            pKCS12ParametersGenerator.init(byArray2, byArray, n3);
            CipherParameters cipherParameters = n != 2 ? ((PBEParametersGenerator)pKCS12ParametersGenerator).generateDerivedMacParameters(hMac.getMacSize()) : ((PBEParametersGenerator)pKCS12ParametersGenerator).generateDerivedMacParameters(hMac.getMacSize() * 8);
            Arrays.fill(byArray2, (byte)0);
            hMac.init(cipherParameters);
            MacInputStream macInputStream = new MacInputStream(dataInputStream, hMac);
            this.loadStore(macInputStream);
            byte[] byArray3 = new byte[hMac.getMacSize()];
            hMac.doFinal(byArray3, 0);
            byte[] byArray4 = new byte[hMac.getMacSize()];
            dataInputStream.readFully(byArray4);
            if (!Arrays.constantTimeAreEqual(byArray3, byArray4)) {
                this.table.clear();
                throw new IOException("KeyStore integrity check failed.");
            }
        } else {
            this.loadStore(dataInputStream);
            byte[] byArray5 = new byte[hMac.getMacSize()];
            dataInputStream.readFully(byArray5);
        }
    }

    public void engineStore(OutputStream outputStream, char[] cArray) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] byArray = new byte[20];
        int n = 1024 + (this.random.nextInt() & 0x3FF);
        this.random.nextBytes(byArray);
        dataOutputStream.writeInt(this.version);
        dataOutputStream.writeInt(byArray.length);
        dataOutputStream.write(byArray);
        dataOutputStream.writeInt(n);
        HMac hMac = new HMac(new SHA1Digest());
        MacOutputStream macOutputStream = new MacOutputStream(hMac);
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(new SHA1Digest());
        byte[] byArray2 = PBEParametersGenerator.PKCS12PasswordToBytes(cArray);
        pKCS12ParametersGenerator.init(byArray2, byArray, n);
        if (this.version < 2) {
            hMac.init(((PBEParametersGenerator)pKCS12ParametersGenerator).generateDerivedMacParameters(hMac.getMacSize()));
        } else {
            hMac.init(((PBEParametersGenerator)pKCS12ParametersGenerator).generateDerivedMacParameters(hMac.getMacSize() * 8));
        }
        for (int i = 0; i != byArray2.length; ++i) {
            byArray2[i] = 0;
        }
        this.saveStore(new TeeOutputStream(dataOutputStream, macOutputStream));
        byte[] byArray3 = new byte[hMac.getMacSize()];
        hMac.doFinal(byArray3, 0);
        dataOutputStream.write(byArray3);
        dataOutputStream.close();
    }

    static Provider getBouncyCastleProvider() {
        if (Security.getProvider("BC") != null) {
            return Security.getProvider("BC");
        }
        return new BouncyCastleProvider();
    }

    public static class BouncyCastleStore
    extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        public void engineLoad(InputStream inputStream, char[] cArray) throws IOException {
            this.table.clear();
            if (inputStream == null) {
                return;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n = dataInputStream.readInt();
            if (n != 2 && n != 0 && n != 1) {
                throw new IOException("Wrong version of key store.");
            }
            byte[] byArray = new byte[dataInputStream.readInt()];
            if (byArray.length != 20) {
                throw new IOException("Key store corrupted.");
            }
            dataInputStream.readFully(byArray);
            int n2 = dataInputStream.readInt();
            if (n2 < 0 || n2 > 65536) {
                throw new IOException("Key store corrupted.");
            }
            String string = n == 0 ? "OldPBEWithSHAAndTwofish-CBC" : BcKeyStoreSpi.STORE_CIPHER;
            Cipher cipher = this.makePBECipher(string, 2, cArray, byArray, n2);
            CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, cipher);
            SHA1Digest sHA1Digest = new SHA1Digest();
            DigestInputStream digestInputStream = new DigestInputStream(cipherInputStream, sHA1Digest);
            this.loadStore(digestInputStream);
            byte[] byArray2 = new byte[sHA1Digest.getDigestSize()];
            sHA1Digest.doFinal(byArray2, 0);
            byte[] byArray3 = new byte[sHA1Digest.getDigestSize()];
            Streams.readFully(cipherInputStream, byArray3);
            if (!Arrays.constantTimeAreEqual(byArray2, byArray3)) {
                this.table.clear();
                throw new IOException("KeyStore integrity check failed.");
            }
        }

        public void engineStore(OutputStream outputStream, char[] cArray) throws IOException {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] byArray = new byte[20];
            int n = 1024 + (this.random.nextInt() & 0x3FF);
            this.random.nextBytes(byArray);
            dataOutputStream.writeInt(this.version);
            dataOutputStream.writeInt(byArray.length);
            dataOutputStream.write(byArray);
            dataOutputStream.writeInt(n);
            Cipher cipher = this.makePBECipher(BcKeyStoreSpi.STORE_CIPHER, 1, cArray, byArray, n);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, cipher);
            DigestOutputStream digestOutputStream = new DigestOutputStream(new SHA1Digest());
            this.saveStore(new TeeOutputStream(cipherOutputStream, digestOutputStream));
            byte[] byArray2 = digestOutputStream.getDigest();
            cipherOutputStream.write(byArray2);
            cipherOutputStream.close();
        }
    }

    public static class Std
    extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    private class StoreEntry {
        int type;
        String alias;
        Object obj;
        Certificate[] certChain;
        Date date = new Date();

        StoreEntry(String string, Certificate certificate) {
            this.type = 1;
            this.alias = string;
            this.obj = certificate;
            this.certChain = null;
        }

        StoreEntry(String string, byte[] byArray, Certificate[] certificateArray) {
            this.type = 3;
            this.alias = string;
            this.obj = byArray;
            this.certChain = certificateArray;
        }

        StoreEntry(String string, Key key, char[] cArray, Certificate[] certificateArray) throws Exception {
            this.type = 4;
            this.alias = string;
            this.certChain = certificateArray;
            byte[] byArray = new byte[20];
            BcKeyStoreSpi.this.random.setSeed(System.currentTimeMillis());
            BcKeyStoreSpi.this.random.nextBytes(byArray);
            int n = 1024 + (BcKeyStoreSpi.this.random.nextInt() & 0x3FF);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(byArray.length);
            dataOutputStream.write(byArray);
            dataOutputStream.writeInt(n);
            Cipher cipher = BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, cArray, byArray, n);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, cipher);
            dataOutputStream = new DataOutputStream(cipherOutputStream);
            BcKeyStoreSpi.this.encodeKey(key, dataOutputStream);
            dataOutputStream.close();
            this.obj = byteArrayOutputStream.toByteArray();
        }

        StoreEntry(String string, Date date, int n, Object object) {
            this.alias = string;
            this.date = date;
            this.type = n;
            this.obj = object;
        }

        StoreEntry(String string, Date date, int n, Object object, Certificate[] certificateArray) {
            this.alias = string;
            this.date = date;
            this.type = n;
            this.obj = object;
            this.certChain = certificateArray;
        }

        int getType() {
            return this.type;
        }

        String getAlias() {
            return this.alias;
        }

        Object getObject() {
            return this.obj;
        }

        Object getObject(char[] cArray) throws NoSuchAlgorithmException, UnrecoverableKeyException {
            if ((cArray == null || cArray.length == 0) && this.obj instanceof Key) {
                return this.obj;
            }
            if (this.type == 4) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])this.obj);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                try {
                    byte[] byArray = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(byArray);
                    int n = dataInputStream.readInt();
                    Cipher cipher = BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 2, cArray, byArray, n);
                    CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, cipher);
                    try {
                        return BcKeyStoreSpi.this.decodeKey(new DataInputStream(cipherInputStream));
                    } catch (Exception exception) {
                        byteArrayInputStream = new ByteArrayInputStream((byte[])this.obj);
                        dataInputStream = new DataInputStream(byteArrayInputStream);
                        byArray = new byte[dataInputStream.readInt()];
                        dataInputStream.readFully(byArray);
                        n = dataInputStream.readInt();
                        cipher = BcKeyStoreSpi.this.makePBECipher("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArray, byArray, n);
                        cipherInputStream = new CipherInputStream(dataInputStream, cipher);
                        Key key = null;
                        try {
                            key = BcKeyStoreSpi.this.decodeKey(new DataInputStream(cipherInputStream));
                        } catch (Exception exception2) {
                            byteArrayInputStream = new ByteArrayInputStream((byte[])this.obj);
                            dataInputStream = new DataInputStream(byteArrayInputStream);
                            byArray = new byte[dataInputStream.readInt()];
                            dataInputStream.readFully(byArray);
                            n = dataInputStream.readInt();
                            cipher = BcKeyStoreSpi.this.makePBECipher("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArray, byArray, n);
                            cipherInputStream = new CipherInputStream(dataInputStream, cipher);
                            key = BcKeyStoreSpi.this.decodeKey(new DataInputStream(cipherInputStream));
                        }
                        if (key != null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                            dataOutputStream.writeInt(byArray.length);
                            dataOutputStream.write(byArray);
                            dataOutputStream.writeInt(n);
                            Cipher cipher2 = BcKeyStoreSpi.this.makePBECipher(BcKeyStoreSpi.KEY_CIPHER, 1, cArray, byArray, n);
                            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, cipher2);
                            dataOutputStream = new DataOutputStream(cipherOutputStream);
                            BcKeyStoreSpi.this.encodeKey(key, dataOutputStream);
                            dataOutputStream.close();
                            this.obj = byteArrayOutputStream.toByteArray();
                            return key;
                        }
                        throw new UnrecoverableKeyException("no match");
                    }
                } catch (Exception exception) {
                    throw new UnrecoverableKeyException("no match");
                }
            }
            throw new RuntimeException("forget something!");
        }

        Certificate[] getCertificateChain() {
            return this.certChain;
        }

        Date getDate() {
            return this.date;
        }
    }

    public static class Version1
    extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }
}

