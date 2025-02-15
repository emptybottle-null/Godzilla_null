/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CRL;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Set;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class JcaJceUtils {
    public static X509TrustManager getTrustAllTrustManager() {
        return new X509TrustManager(){

            public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public static X509TrustManager[] getCertPathTrustManager(final Set<TrustAnchor> set, final CRL[] cRLArray) {
        final X509Certificate[] x509CertificateArray = new X509Certificate[set.size()];
        int n = 0;
        for (TrustAnchor trustAnchor : set) {
            x509CertificateArray[n++] = trustAnchor.getTrustedCert();
        }
        return new X509TrustManager[]{new X509TrustManager(){

            public void checkClientTrusted(X509Certificate[] x509CertificateArray2, String string) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArray2, String string) throws CertificateException {
                try {
                    CertStore certStore = CertStore.getInstance("Collection", (CertStoreParameters)new CollectionCertStoreParameters(Arrays.asList(x509CertificateArray2)), "BC");
                    CertPathBuilder certPathBuilder = CertPathBuilder.getInstance("PKIX", "BC");
                    X509CertSelector x509CertSelector = new X509CertSelector();
                    x509CertSelector.setCertificate(x509CertificateArray2[0]);
                    PKIXBuilderParameters pKIXBuilderParameters = new PKIXBuilderParameters(set, (CertSelector)x509CertSelector);
                    pKIXBuilderParameters.addCertStore(certStore);
                    if (cRLArray != null) {
                        pKIXBuilderParameters.setRevocationEnabled(true);
                        pKIXBuilderParameters.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(Arrays.asList(cRLArray))));
                    } else {
                        pKIXBuilderParameters.setRevocationEnabled(false);
                    }
                    PKIXCertPathValidatorResult pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult)((Object)certPathBuilder.build(pKIXBuilderParameters));
                    JcaJceUtils.validateServerCertUsage(x509CertificateArray2[0]);
                } catch (CertificateException certificateException) {
                    throw certificateException;
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new CertificateException("unable to process certificates: " + generalSecurityException.getMessage(), generalSecurityException);
                }
            }

            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509CertificateArray2 = new X509Certificate[x509CertificateArray.length];
                System.arraycopy(x509CertificateArray, 0, x509CertificateArray2, 0, x509CertificateArray2.length);
                return x509CertificateArray2;
            }
        }};
    }

    public static void validateServerCertUsage(X509Certificate x509Certificate) throws CertificateException {
        try {
            ExtendedKeyUsage extendedKeyUsage;
            X509CertificateHolder x509CertificateHolder = new X509CertificateHolder(x509Certificate.getEncoded());
            KeyUsage keyUsage = KeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
            if (keyUsage != null) {
                if (keyUsage.hasUsages(4)) {
                    throw new CertificateException("Key usage must not contain keyCertSign");
                }
                if (!keyUsage.hasUsages(128) && !keyUsage.hasUsages(32)) {
                    throw new CertificateException("Key usage must be none, digitalSignature or keyEncipherment");
                }
            }
            if (!((extendedKeyUsage = ExtendedKeyUsage.fromExtensions(x509CertificateHolder.getExtensions())) == null || extendedKeyUsage.hasKeyPurposeId(KeyPurposeId.id_kp_serverAuth) || extendedKeyUsage.hasKeyPurposeId(KeyPurposeId.id_kp_msSGC) || extendedKeyUsage.hasKeyPurposeId(KeyPurposeId.id_kp_nsSGC))) {
                throw new CertificateException("Certificate extended key usage must include serverAuth, msSGC or nsSGC");
            }
        } catch (CertificateException certificateException) {
            throw certificateException;
        } catch (Exception exception) {
            throw new CertificateException(exception.getMessage(), exception);
        }
    }

    public static KeyManagerFactory createKeyManagerFactory(String string, String string2, KeyStore keyStore, char[] cArray) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException {
        KeyManagerFactory keyManagerFactory = null;
        keyManagerFactory = string == null && string2 == null ? KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()) : (string2 == null ? KeyManagerFactory.getInstance(string) : KeyManagerFactory.getInstance(string, string2));
        keyManagerFactory.init(keyStore, cArray);
        return keyManagerFactory;
    }
}

