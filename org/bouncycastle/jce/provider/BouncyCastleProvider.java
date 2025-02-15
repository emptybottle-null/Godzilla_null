/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.jce.provider.BouncyCastleProviderConfiguration;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.provider.mceliece.McElieceCCA2KeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.mceliece.McElieceKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.newhope.NHKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.rainbow.RainbowKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.xmss.XMSSKeyFactorySpi;
import org.bouncycastle.pqc.jcajce.provider.xmss.XMSSMTKeyFactorySpi;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class BouncyCastleProvider
extends Provider
implements ConfigurableProvider {
    private static String info = "BouncyCastle Security Provider v1.58";
    public static final String PROVIDER_NAME = "BC";
    public static final ProviderConfiguration CONFIGURATION = new BouncyCastleProviderConfiguration();
    private static final Map keyInfoConverters = new HashMap();
    private static final String SYMMETRIC_PACKAGE = "org.bouncycastle.jcajce.provider.symmetric.";
    private static final String[] SYMMETRIC_GENERIC = new String[]{"PBEPBKDF1", "PBEPBKDF2", "PBEPKCS12", "TLSKDF"};
    private static final String[] SYMMETRIC_MACS = new String[]{"SipHash", "Poly1305"};
    private static final String[] SYMMETRIC_CIPHERS = new String[]{"AES", "ARC4", "ARIA", "Blowfish", "Camellia", "CAST5", "CAST6", "ChaCha", "DES", "DESede", "GOST28147", "Grainv1", "Grain128", "HC128", "HC256", "IDEA", "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Shacal2", "Skipjack", "SM4", "TEA", "Twofish", "Threefish", "VMPC", "VMPCKSA3", "XTEA", "XSalsa20", "OpenSSLPBKDF", "DSTU7624"};
    private static final String ASYMMETRIC_PACKAGE = "org.bouncycastle.jcajce.provider.asymmetric.";
    private static final String[] ASYMMETRIC_GENERIC = new String[]{"X509", "IES"};
    private static final String[] ASYMMETRIC_CIPHERS = new String[]{"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145", "GM"};
    private static final String DIGEST_PACKAGE = "org.bouncycastle.jcajce.provider.digest.";
    private static final String[] DIGESTS = new String[]{"GOST3411", "Keccak", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool", "Blake2b", "DSTU7564"};
    private static final String KEYSTORE_PACKAGE = "org.bouncycastle.jcajce.provider.keystore.";
    private static final String[] KEYSTORES = new String[]{"BC", "BCFKS", "PKCS12"};
    private static final String SECURE_RANDOM_PACKAGE = "org.bouncycastle.jcajce.provider.drbg.";
    private static final String[] SECURE_RANDOMS = new String[]{"DRBG"};

    public BouncyCastleProvider() {
        super(PROVIDER_NAME, 1.58, info);
        AccessController.doPrivileged(new PrivilegedAction(){

            public Object run() {
                BouncyCastleProvider.this.setup();
                return null;
            }
        });
    }

    private void setup() {
        this.loadAlgorithms(DIGEST_PACKAGE, DIGESTS);
        this.loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_GENERIC);
        this.loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_MACS);
        this.loadAlgorithms(SYMMETRIC_PACKAGE, SYMMETRIC_CIPHERS);
        this.loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_GENERIC);
        this.loadAlgorithms(ASYMMETRIC_PACKAGE, ASYMMETRIC_CIPHERS);
        this.loadAlgorithms(KEYSTORE_PACKAGE, KEYSTORES);
        this.loadAlgorithms(SECURE_RANDOM_PACKAGE, SECURE_RANDOMS);
        this.loadPQCKeys();
        this.put("X509Store.CERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertCollection");
        this.put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreAttrCertCollection");
        this.put("X509Store.CRL/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCRLCollection");
        this.put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertPairCollection");
        this.put("X509Store.CERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCerts");
        this.put("X509Store.CRL/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCRLs");
        this.put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPAttrCerts");
        this.put("X509Store.CERTIFICATEPAIR/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCertPairs");
        this.put("X509StreamParser.CERTIFICATE", "org.bouncycastle.jce.provider.X509CertParser");
        this.put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.bouncycastle.jce.provider.X509AttrCertParser");
        this.put("X509StreamParser.CRL", "org.bouncycastle.jce.provider.X509CRLParser");
        this.put("X509StreamParser.CERTIFICATEPAIR", "org.bouncycastle.jce.provider.X509CertPairParser");
        this.put("Cipher.BROKENPBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        this.put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        this.put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        this.put("CertPathValidator.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        this.put("CertPathBuilder.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        this.put("CertPathValidator.RFC3280", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        this.put("CertPathBuilder.RFC3280", "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        this.put("CertPathValidator.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        this.put("CertPathBuilder.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        this.put("CertStore.Collection", "org.bouncycastle.jce.provider.CertStoreCollectionSpi");
        this.put("CertStore.LDAP", "org.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
        this.put("CertStore.Multi", "org.bouncycastle.jce.provider.MultiCertStoreSpi");
        this.put("Alg.Alias.CertStore.X509LDAP", "LDAP");
    }

    private void loadAlgorithms(String string, String[] stringArray) {
        for (int i = 0; i != stringArray.length; ++i) {
            Class clazz = ClassUtil.loadClass(BouncyCastleProvider.class, string + stringArray[i] + "$Mappings");
            if (clazz == null) continue;
            try {
                ((AlgorithmProvider)clazz.newInstance()).configure(this);
                continue;
            } catch (Exception exception) {
                throw new InternalError("cannot create instance of " + string + stringArray[i] + "$Mappings : " + exception);
            }
        }
    }

    private void loadPQCKeys() {
        this.addKeyInfoConverter(PQCObjectIdentifiers.sphincs256, new Sphincs256KeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.newHope, new NHKeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.xmss, new XMSSKeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.mcEliece, new McElieceKeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.mcElieceCca2, new McElieceCCA2KeyFactorySpi());
        this.addKeyInfoConverter(PQCObjectIdentifiers.rainbow, new RainbowKeyFactorySpi());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setParameter(String string, Object object) {
        ProviderConfiguration providerConfiguration = CONFIGURATION;
        synchronized (providerConfiguration) {
            ((BouncyCastleProviderConfiguration)CONFIGURATION).setParameter(string, object);
        }
    }

    @Override
    public boolean hasAlgorithm(String string, String string2) {
        return this.containsKey(string + "." + string2) || this.containsKey("Alg.Alias." + string + "." + string2);
    }

    @Override
    public void addAlgorithm(String string, String string2) {
        if (this.containsKey(string)) {
            throw new IllegalStateException("duplicate provider key (" + string + ") found");
        }
        this.put(string, string2);
    }

    @Override
    public void addAlgorithm(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier, String string2) {
        this.addAlgorithm(string + "." + aSN1ObjectIdentifier, string2);
        this.addAlgorithm(string + ".OID." + aSN1ObjectIdentifier, string2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        Map map = keyInfoConverters;
        synchronized (map) {
            keyInfoConverters.put(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
        }
    }

    @Override
    public void addAttributes(String string, Map<String, String> map) {
        for (String string2 : map.keySet()) {
            String string3 = string + " " + string2;
            if (this.containsKey(string3)) {
                throw new IllegalStateException("duplicate provider attribute key (" + string3 + ") found");
            }
            this.put(string3, map.get(string2));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static AsymmetricKeyInfoConverter getAsymmetricKeyInfoConverter(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Map map = keyInfoConverters;
        synchronized (map) {
            return (AsymmetricKeyInfoConverter)keyInfoConverters.get(aSN1ObjectIdentifier);
        }
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = BouncyCastleProvider.getAsymmetricKeyInfoConverter(subjectPublicKeyInfo.getAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePublic(subjectPublicKeyInfo);
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = BouncyCastleProvider.getAsymmetricKeyInfoConverter(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.generatePrivate(privateKeyInfo);
    }
}

