/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Properties;

public class EC {
    private static final String PREFIX = "org.bouncycastle.jcajce.provider.asymmetric.ec.";
    private static final Map<String, String> generalEcAttributes = new HashMap<String, String>();

    static {
        generalEcAttributes.put("SupportedKeyClasses", "java.security.interfaces.ECPublicKey|java.security.interfaces.ECPrivateKey");
        generalEcAttributes.put("SupportedKeyFormats", "PKCS#8|X.509");
    }

    public static class Mappings
    extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("AlgorithmParameters.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.AlgorithmParametersSpi");
            configurableProvider.addAttributes("KeyAgreement.ECDH", generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DH");
            configurableProvider.addAttributes("KeyAgreement.ECDHC", generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC");
            configurableProvider.addAttributes("KeyAgreement.ECCDH", generalEcAttributes);
            configurableProvider.addAlgorithm("KeyAgreement.ECCDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC");
            configurableProvider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA1KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA224KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA224KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA256KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA256KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA384KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA384KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA512KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$CDHwithSHA512KDFAndSharedInfo");
            configurableProvider.addAlgorithm("KeyAgreement.ECDHWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDF");
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA1CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1CKDF");
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA256CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA256CKDF");
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA384CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA384CKDF");
            configurableProvider.addAlgorithm("KeyAgreement.ECCDHWITHSHA512CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA512CKDF");
            this.registerOid(configurableProvider, X9ObjectIdentifiers.id_ecPublicKey, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOid(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "EC", new KeyFactorySpi.EC());
            this.registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.id_ecPublicKey, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme, "EC");
            this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme, "EC");
            if (!Properties.isOverrideSet("org.bouncycastle.ec.disable_mqv")) {
                configurableProvider.addAlgorithm("KeyAgreement.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQV");
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA1CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1CKDF");
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA224CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA224CKDF");
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA256CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA256CKDF");
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA384CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA384CKDF");
                configurableProvider.addAlgorithm("KeyAgreement.ECMQVWITHSHA512CKDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA512CKDF");
                configurableProvider.addAlgorithm("KeyAgreement." + X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1KDFAndSharedInfo");
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA224KDFAndSharedInfo");
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA256KDFAndSharedInfo");
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA384KDFAndSharedInfo");
                configurableProvider.addAlgorithm("KeyAgreement." + SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA512KDFAndSharedInfo");
                this.registerOid(configurableProvider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC", new KeyFactorySpi.EC());
                this.registerOidAlgorithmParameters(configurableProvider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "EC");
                this.registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "EC");
                this.registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme, "EC");
                this.registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme, "EC");
                this.registerOid(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "ECMQV", new KeyFactorySpi.ECMQV());
                this.registerOidAlgorithmParameters(configurableProvider, SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme, "EC");
                configurableProvider.addAlgorithm("KeyFactory.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECMQV");
                configurableProvider.addAlgorithm("KeyPairGenerator.ECMQV", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECMQV");
            }
            configurableProvider.addAlgorithm("KeyFactory.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$EC");
            configurableProvider.addAlgorithm("KeyFactory.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDSA");
            configurableProvider.addAlgorithm("KeyFactory.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDH");
            configurableProvider.addAlgorithm("KeyFactory.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDHC");
            configurableProvider.addAlgorithm("KeyPairGenerator.EC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$EC");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDSA");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDH", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDHWITHSHA1KDF", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECDHC", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDHC");
            configurableProvider.addAlgorithm("KeyPairGenerator.ECIES", "org.bouncycastle.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            configurableProvider.addAlgorithm("Cipher.ECIES", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIES");
            configurableProvider.addAlgorithm("Cipher.ECIESwithAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.ECIESWITHAES-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAESCBC");
            configurableProvider.addAlgorithm("Cipher.ECIESwithDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC");
            configurableProvider.addAlgorithm("Cipher.ECIESWITHDESEDE-CBC", "org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESedeCBC");
            configurableProvider.addAlgorithm("Signature.ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA");
            configurableProvider.addAlgorithm("Signature.NONEwithECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAnone");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1withECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAwithSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WITHECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAWITHSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WithECDSA", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.ECDSAWithSHA1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.1.2.840.10045.4.1", "ECDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature." + TeleTrusTObjectIdentifiers.ecSignWithSha1, "ECDSA");
            configurableProvider.addAlgorithm("Signature.ECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA");
            configurableProvider.addAlgorithm("Signature.SHA1WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA");
            configurableProvider.addAlgorithm("Signature.SHA224WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA224");
            configurableProvider.addAlgorithm("Signature.SHA256WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA256");
            configurableProvider.addAlgorithm("Signature.SHA384WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA384");
            configurableProvider.addAlgorithm("Signature.SHA512WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA512");
            configurableProvider.addAlgorithm("Signature.SHA3-224WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_224");
            configurableProvider.addAlgorithm("Signature.SHA3-256WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_256");
            configurableProvider.addAlgorithm("Signature.SHA3-384WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_384");
            configurableProvider.addAlgorithm("Signature.SHA3-512WITHECDDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSASha3_512");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.DETECDSA", "ECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA1WITHDETECDSA", "SHA1WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA224WITHDETECDSA", "SHA224WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA256WITHDETECDSA", "SHA256WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA384WITHDETECDSA", "SHA384WITHECDDSA");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SHA512WITHDETECDSA", "SHA512WITHECDDSA");
            this.addSignatureAlgorithm(configurableProvider, "SHA224", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA224", X9ObjectIdentifiers.ecdsa_with_SHA224);
            this.addSignatureAlgorithm(configurableProvider, "SHA256", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA256", X9ObjectIdentifiers.ecdsa_with_SHA256);
            this.addSignatureAlgorithm(configurableProvider, "SHA384", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA384", X9ObjectIdentifiers.ecdsa_with_SHA384);
            this.addSignatureAlgorithm(configurableProvider, "SHA512", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA512", X9ObjectIdentifiers.ecdsa_with_SHA512);
            this.addSignatureAlgorithm(configurableProvider, "SHA3-224", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_224", NISTObjectIdentifiers.id_ecdsa_with_sha3_224);
            this.addSignatureAlgorithm(configurableProvider, "SHA3-256", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_256", NISTObjectIdentifiers.id_ecdsa_with_sha3_256);
            this.addSignatureAlgorithm(configurableProvider, "SHA3-384", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_384", NISTObjectIdentifiers.id_ecdsa_with_sha3_384);
            this.addSignatureAlgorithm(configurableProvider, "SHA3-512", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSASha3_512", NISTObjectIdentifiers.id_ecdsa_with_sha3_512);
            this.addSignatureAlgorithm(configurableProvider, "RIPEMD160", "ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSARipeMD160", TeleTrusTObjectIdentifiers.ecSignWithRipemd160);
            configurableProvider.addAlgorithm("Signature.SHA1WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR");
            configurableProvider.addAlgorithm("Signature.SHA224WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR224");
            configurableProvider.addAlgorithm("Signature.SHA256WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR256");
            configurableProvider.addAlgorithm("Signature.SHA384WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR384");
            configurableProvider.addAlgorithm("Signature.SHA512WITHECNR", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR512");
            this.addSignatureAlgorithm(configurableProvider, "SHA1", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
            this.addSignatureAlgorithm(configurableProvider, "SHA224", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
            this.addSignatureAlgorithm(configurableProvider, "SHA256", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
            this.addSignatureAlgorithm(configurableProvider, "SHA384", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
            this.addSignatureAlgorithm(configurableProvider, "SHA512", "CVC-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
            this.addSignatureAlgorithm(configurableProvider, "SHA1", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", BSIObjectIdentifiers.ecdsa_plain_SHA1);
            this.addSignatureAlgorithm(configurableProvider, "SHA224", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", BSIObjectIdentifiers.ecdsa_plain_SHA224);
            this.addSignatureAlgorithm(configurableProvider, "SHA256", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", BSIObjectIdentifiers.ecdsa_plain_SHA256);
            this.addSignatureAlgorithm(configurableProvider, "SHA384", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", BSIObjectIdentifiers.ecdsa_plain_SHA384);
            this.addSignatureAlgorithm(configurableProvider, "SHA512", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", BSIObjectIdentifiers.ecdsa_plain_SHA512);
            this.addSignatureAlgorithm(configurableProvider, "RIPEMD160", "PLAIN-ECDSA", "org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecPlainDSARP160", BSIObjectIdentifiers.ecdsa_plain_RIPEMD160);
        }
    }
}

