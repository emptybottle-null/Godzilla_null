/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.OtherRevocationInfoFormat;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AttributeCertificate;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;

class CMSSignedHelper {
    static final CMSSignedHelper INSTANCE = new CMSSignedHelper();
    private static final Map encryptionAlgs = new HashMap();
    private static final Map digestAlgs = new HashMap();
    private static final Map digestAliases = new HashMap();

    CMSSignedHelper() {
    }

    private static void addEntries(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string, String string2) {
        digestAlgs.put(aSN1ObjectIdentifier.getId(), string);
        encryptionAlgs.put(aSN1ObjectIdentifier.getId(), string2);
    }

    String getEncryptionAlgName(String string) {
        String string2 = (String)encryptionAlgs.get(string);
        if (string2 != null) {
            return string2;
        }
        return string;
    }

    AlgorithmIdentifier fixAlgID(AlgorithmIdentifier algorithmIdentifier) {
        if (algorithmIdentifier.getParameters() == null) {
            return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
        }
        return algorithmIdentifier;
    }

    void setSigningEncryptionAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        encryptionAlgs.put(aSN1ObjectIdentifier.getId(), string);
    }

    void setSigningDigestAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        digestAlgs.put(aSN1ObjectIdentifier.getId(), string);
    }

    Store getCertificates(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList<X509CertificateHolder> arrayList = new ArrayList<X509CertificateHolder>(aSN1Set.size());
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)enumeration.nextElement()).toASN1Primitive();
                if (!(aSN1Primitive instanceof ASN1Sequence)) continue;
                arrayList.add(new X509CertificateHolder(Certificate.getInstance(aSN1Primitive)));
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    Store getAttributeCertificates(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList<X509AttributeCertificateHolder> arrayList = new ArrayList<X509AttributeCertificateHolder>(aSN1Set.size());
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)enumeration.nextElement()).toASN1Primitive();
                if (!(aSN1Primitive instanceof ASN1TaggedObject)) continue;
                arrayList.add(new X509AttributeCertificateHolder(AttributeCertificate.getInstance(((ASN1TaggedObject)aSN1Primitive).getObject())));
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    Store getCRLs(ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList<X509CRLHolder> arrayList = new ArrayList<X509CRLHolder>(aSN1Set.size());
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)enumeration.nextElement()).toASN1Primitive();
                if (!(aSN1Primitive instanceof ASN1Sequence)) continue;
                arrayList.add(new X509CRLHolder(CertificateList.getInstance(aSN1Primitive)));
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set) {
        if (aSN1Set != null) {
            ArrayList<ASN1Encodable> arrayList = new ArrayList<ASN1Encodable>(aSN1Set.size());
            Enumeration enumeration = aSN1Set.getObjects();
            while (enumeration.hasMoreElements()) {
                OtherRevocationInfoFormat otherRevocationInfoFormat;
                ASN1TaggedObject aSN1TaggedObject;
                ASN1Primitive aSN1Primitive = ((ASN1Encodable)enumeration.nextElement()).toASN1Primitive();
                if (!(aSN1Primitive instanceof ASN1TaggedObject) || (aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Primitive)).getTagNo() != 1 || !aSN1ObjectIdentifier.equals((otherRevocationInfoFormat = OtherRevocationInfoFormat.getInstance(aSN1TaggedObject, false)).getInfoFormat())) continue;
                arrayList.add(otherRevocationInfoFormat.getInfo());
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    static {
        CMSSignedHelper.addEntries(NISTObjectIdentifiers.dsa_with_sha224, "SHA224", "DSA");
        CMSSignedHelper.addEntries(NISTObjectIdentifiers.dsa_with_sha256, "SHA256", "DSA");
        CMSSignedHelper.addEntries(NISTObjectIdentifiers.dsa_with_sha384, "SHA384", "DSA");
        CMSSignedHelper.addEntries(NISTObjectIdentifiers.dsa_with_sha512, "SHA512", "DSA");
        CMSSignedHelper.addEntries(OIWObjectIdentifiers.dsaWithSHA1, "SHA1", "DSA");
        CMSSignedHelper.addEntries(OIWObjectIdentifiers.md4WithRSA, "MD4", "RSA");
        CMSSignedHelper.addEntries(OIWObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        CMSSignedHelper.addEntries(OIWObjectIdentifiers.md5WithRSA, "MD5", "RSA");
        CMSSignedHelper.addEntries(OIWObjectIdentifiers.sha1WithRSA, "SHA1", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.md2WithRSAEncryption, "MD2", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.md5WithRSAEncryption, "MD5", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.sha1WithRSAEncryption, "SHA1", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384", "RSA");
        CMSSignedHelper.addEntries(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512", "RSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1", "ECDSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224", "ECDSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256", "ECDSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384", "ECDSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512", "ECDSA");
        CMSSignedHelper.addEntries(X9ObjectIdentifiers.id_dsa_with_sha1, "SHA1", "DSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1", "ECDSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224", "ECDSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256", "ECDSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384", "ECDSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512", "ECDSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1", "RSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256", "RSA");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1", "RSAandMGF1");
        CMSSignedHelper.addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256", "RSAandMGF1");
        encryptionAlgs.put(X9ObjectIdentifiers.id_dsa.getId(), "DSA");
        encryptionAlgs.put(PKCSObjectIdentifiers.rsaEncryption.getId(), "RSA");
        encryptionAlgs.put(TeleTrusTObjectIdentifiers.teleTrusTRSAsignatureAlgorithm, "RSA");
        encryptionAlgs.put(X509ObjectIdentifiers.id_ea_rsa.getId(), "RSA");
        encryptionAlgs.put(CMSSignedDataGenerator.ENCRYPTION_RSA_PSS, "RSAandMGF1");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3410_94.getId(), "GOST3410");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3410_2001.getId(), "ECGOST3410");
        encryptionAlgs.put("1.3.6.1.4.1.5849.1.6.2", "ECGOST3410");
        encryptionAlgs.put("1.3.6.1.4.1.5849.1.1.5", "GOST3410");
        encryptionAlgs.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256, "ECGOST3410-2012-256");
        encryptionAlgs.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512, "ECGOST3410-2012-512");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001.getId(), "ECGOST3410");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94.getId(), "GOST3410");
        encryptionAlgs.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, "ECGOST3410-2012-256");
        encryptionAlgs.put(RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, "ECGOST3410-2012-512");
        digestAlgs.put(PKCSObjectIdentifiers.md2.getId(), "MD2");
        digestAlgs.put(PKCSObjectIdentifiers.md4.getId(), "MD4");
        digestAlgs.put(PKCSObjectIdentifiers.md5.getId(), "MD5");
        digestAlgs.put(OIWObjectIdentifiers.idSHA1.getId(), "SHA1");
        digestAlgs.put(NISTObjectIdentifiers.id_sha224.getId(), "SHA224");
        digestAlgs.put(NISTObjectIdentifiers.id_sha256.getId(), "SHA256");
        digestAlgs.put(NISTObjectIdentifiers.id_sha384.getId(), "SHA384");
        digestAlgs.put(NISTObjectIdentifiers.id_sha512.getId(), "SHA512");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd128.getId(), "RIPEMD128");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd160.getId(), "RIPEMD160");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd256.getId(), "RIPEMD256");
        digestAlgs.put(CryptoProObjectIdentifiers.gostR3411.getId(), "GOST3411");
        digestAlgs.put("1.3.6.1.4.1.5849.1.2.1", "GOST3411");
        digestAlgs.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, "GOST3411-2012-256");
        digestAlgs.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, "GOST3411-2012-512");
        digestAliases.put("SHA1", new String[]{"SHA-1"});
        digestAliases.put("SHA224", new String[]{"SHA-224"});
        digestAliases.put("SHA256", new String[]{"SHA-256"});
        digestAliases.put("SHA384", new String[]{"SHA-384"});
        digestAliases.put("SHA512", new String[]{"SHA-512"});
    }
}

