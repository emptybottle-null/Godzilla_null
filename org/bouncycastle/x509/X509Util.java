/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Strings;

class X509Util {
    private static Hashtable algorithms = new Hashtable();
    private static Hashtable params = new Hashtable();
    private static Set noParams = new HashSet();

    X509Util() {
    }

    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier algorithmIdentifier, int n) {
        return new RSASSAPSSparams(algorithmIdentifier, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, algorithmIdentifier), new ASN1Integer(n), new ASN1Integer(1L));
    }

    static ASN1ObjectIdentifier getAlgorithmOID(String string) {
        if (algorithms.containsKey(string = Strings.toUpperCase(string))) {
            return (ASN1ObjectIdentifier)algorithms.get(string);
        }
        return new ASN1ObjectIdentifier(string);
    }

    static AlgorithmIdentifier getSigAlgID(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        if (noParams.contains(aSN1ObjectIdentifier)) {
            return new AlgorithmIdentifier(aSN1ObjectIdentifier);
        }
        if (params.containsKey(string = Strings.toUpperCase(string))) {
            return new AlgorithmIdentifier(aSN1ObjectIdentifier, (ASN1Encodable)params.get(string));
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    static Iterator getAlgNames() {
        Enumeration enumeration = algorithms.keys();
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList.iterator();
    }

    static Signature getSignatureInstance(String string) throws NoSuchAlgorithmException {
        return Signature.getInstance(string);
    }

    static Signature getSignatureInstance(String string, String string2) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (string2 != null) {
            return Signature.getInstance(string, string2);
        }
        return Signature.getInstance(string);
    }

    static byte[] calculateSignature(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string, PrivateKey privateKey, SecureRandom secureRandom, ASN1Encodable aSN1Encodable) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (aSN1ObjectIdentifier == null) {
            throw new IllegalStateException("no signature algorithm specified");
        }
        Signature signature = X509Util.getSignatureInstance(string);
        if (secureRandom != null) {
            signature.initSign(privateKey, secureRandom);
        } else {
            signature.initSign(privateKey);
        }
        signature.update(aSN1Encodable.toASN1Primitive().getEncoded("DER"));
        return signature.sign();
    }

    static byte[] calculateSignature(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string, String string2, PrivateKey privateKey, SecureRandom secureRandom, ASN1Encodable aSN1Encodable) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (aSN1ObjectIdentifier == null) {
            throw new IllegalStateException("no signature algorithm specified");
        }
        Signature signature = X509Util.getSignatureInstance(string, string2);
        if (secureRandom != null) {
            signature.initSign(privateKey, secureRandom);
        } else {
            signature.initSign(privateKey);
        }
        signature.update(aSN1Encodable.toASN1Primitive().getEncoded("DER"));
        return signature.sign();
    }

    static X509Principal convertPrincipal(X500Principal x500Principal) {
        try {
            return new X509Principal(x500Principal.getEncoded());
        } catch (IOException iOException) {
            throw new IllegalArgumentException("cannot convert principal");
        }
    }

    static Implementation getImplementation(String string, String string2, Provider provider) throws NoSuchAlgorithmException {
        String string3;
        string2 = Strings.toUpperCase(string2);
        while ((string3 = provider.getProperty("Alg.Alias." + string + "." + string2)) != null) {
            string2 = string3;
        }
        String string4 = provider.getProperty(string + "." + string2);
        if (string4 != null) {
            try {
                ClassLoader classLoader = provider.getClass().getClassLoader();
                Class<?> clazz = classLoader != null ? classLoader.loadClass(string4) : Class.forName(string4);
                return new Implementation(clazz.newInstance(), provider);
            } catch (ClassNotFoundException classNotFoundException) {
                throw new IllegalStateException("algorithm " + string2 + " in provider " + provider.getName() + " but no class \"" + string4 + "\" found!");
            } catch (Exception exception) {
                throw new IllegalStateException("algorithm " + string2 + " in provider " + provider.getName() + " but class \"" + string4 + "\" inaccessible!");
            }
        }
        throw new NoSuchAlgorithmException("cannot find implementation " + string2 + " for provider " + provider.getName());
    }

    static Implementation getImplementation(String string, String string2) throws NoSuchAlgorithmException {
        Provider[] providerArray = Security.getProviders();
        for (int i = 0; i != providerArray.length; ++i) {
            Implementation implementation = X509Util.getImplementation(string, Strings.toUpperCase(string2), providerArray[i]);
            if (implementation != null) {
                return implementation;
            }
            try {
                implementation = X509Util.getImplementation(string, string2, providerArray[i]);
                continue;
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                // empty catch block
            }
        }
        throw new NoSuchAlgorithmException("cannot find implementation " + string2);
    }

    static Provider getProvider(String string) throws NoSuchProviderException {
        Provider provider = Security.getProvider(string);
        if (provider == null) {
            throw new NoSuchProviderException("Provider " + string + " not found");
        }
        return provider;
    }

    static {
        algorithms.put("MD2WITHRSAENCRYPTION", PKCSObjectIdentifiers.md2WithRSAEncryption);
        algorithms.put("MD2WITHRSA", PKCSObjectIdentifiers.md2WithRSAEncryption);
        algorithms.put("MD5WITHRSAENCRYPTION", PKCSObjectIdentifiers.md5WithRSAEncryption);
        algorithms.put("MD5WITHRSA", PKCSObjectIdentifiers.md5WithRSAEncryption);
        algorithms.put("SHA1WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha1WithRSAEncryption);
        algorithms.put("SHA1WITHRSA", PKCSObjectIdentifiers.sha1WithRSAEncryption);
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
        algorithms.put("RIPEMD160WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD160WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD128WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD128WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD256WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("RIPEMD256WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("SHA1WITHDSA", X9ObjectIdentifiers.id_dsa_with_sha1);
        algorithms.put("DSAWITHSHA1", X9ObjectIdentifiers.id_dsa_with_sha1);
        algorithms.put("SHA224WITHDSA", NISTObjectIdentifiers.dsa_with_sha224);
        algorithms.put("SHA256WITHDSA", NISTObjectIdentifiers.dsa_with_sha256);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        algorithms.put("SHA1WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("ECDSAWITHSHA1", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("SHA224WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA224);
        algorithms.put("SHA256WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA256);
        algorithms.put("SHA384WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA384);
        algorithms.put("SHA512WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA512);
        algorithms.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHGOST3410-94", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHECGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHECGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA1);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA224);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA256);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA384);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA512);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha224);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha256);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha384);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha512);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        params.put("SHA1WITHRSAANDMGF1", X509Util.creatPSSParams(algorithmIdentifier, 20));
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE);
        params.put("SHA224WITHRSAANDMGF1", X509Util.creatPSSParams(algorithmIdentifier2, 28));
        AlgorithmIdentifier algorithmIdentifier3 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        params.put("SHA256WITHRSAANDMGF1", X509Util.creatPSSParams(algorithmIdentifier3, 32));
        AlgorithmIdentifier algorithmIdentifier4 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        params.put("SHA384WITHRSAANDMGF1", X509Util.creatPSSParams(algorithmIdentifier4, 48));
        AlgorithmIdentifier algorithmIdentifier5 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        params.put("SHA512WITHRSAANDMGF1", X509Util.creatPSSParams(algorithmIdentifier5, 64));
    }

    static class Implementation {
        Object engine;
        Provider provider;

        Implementation(Object object, Provider provider) {
            this.engine = object;
            this.provider = provider;
        }

        Object getEngine() {
            return this.engine;
        }

        Provider getProvider() {
            return this.provider;
        }
    }
}

