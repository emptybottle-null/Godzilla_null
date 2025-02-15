/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Principal;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidator;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CertStoreSelector;

public class PKIXAttrCertPathBuilderSpi
extends CertPathBuilderSpi {
    private Exception certPathException;

    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters;
        Object object;
        Object object2;
        if (!(certPathParameters instanceof PKIXBuilderParameters || certPathParameters instanceof ExtendedPKIXBuilderParameters || certPathParameters instanceof PKIXExtendedBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        }
        List list = new ArrayList();
        if (certPathParameters instanceof PKIXBuilderParameters) {
            object2 = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters)certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                object = (ExtendedPKIXBuilderParameters)certPathParameters;
                ((PKIXExtendedBuilderParameters.Builder)object2).addExcludedCerts(((ExtendedPKIXBuilderParameters)object).getExcludedCerts());
                ((PKIXExtendedBuilderParameters.Builder)object2).setMaxPathLength(((ExtendedPKIXBuilderParameters)object).getMaxPathLength());
                list = ((ExtendedPKIXParameters)object).getStores();
            }
            pKIXExtendedBuilderParameters = ((PKIXExtendedBuilderParameters.Builder)object2).build();
        } else {
            pKIXExtendedBuilderParameters = (PKIXExtendedBuilderParameters)certPathParameters;
        }
        ArrayList arrayList = new ArrayList();
        PKIXCertStoreSelector pKIXCertStoreSelector = pKIXExtendedBuilderParameters.getBaseParameters().getTargetConstraints();
        if (!(pKIXCertStoreSelector instanceof X509AttributeCertStoreSelector)) {
            throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + this.getClass().getName() + " class.");
        }
        try {
            object2 = PKIXAttrCertPathBuilderSpi.findCertificates((X509AttributeCertStoreSelector)((Object)pKIXCertStoreSelector), list);
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathBuilderException("Error finding target attribute certificate.", annotatedException);
        }
        if (object2.isEmpty()) {
            throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
        }
        CertPathBuilderResult certPathBuilderResult = null;
        object = object2.iterator();
        while (object.hasNext() && certPathBuilderResult == null) {
            X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate)object.next();
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            Principal[] principalArray = x509AttributeCertificate.getIssuer().getPrincipals();
            HashSet hashSet = new HashSet();
            for (int i = 0; i < principalArray.length; ++i) {
                try {
                    if (principalArray[i] instanceof X500Principal) {
                        x509CertStoreSelector.setSubject(((X500Principal)principalArray[i]).getEncoded());
                    }
                    PKIXCertStoreSelector<? extends Certificate> pKIXCertStoreSelector2 = new PKIXCertStoreSelector.Builder(x509CertStoreSelector).build();
                    hashSet.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector2, pKIXExtendedBuilderParameters.getBaseParameters().getCertStores()));
                    hashSet.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector2, pKIXExtendedBuilderParameters.getBaseParameters().getCertificateStores()));
                    continue;
                } catch (AnnotatedException annotatedException) {
                    throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", annotatedException);
                } catch (IOException iOException) {
                    throw new ExtCertPathBuilderException("cannot encode X500Principal.", iOException);
                }
            }
            if (hashSet.isEmpty()) {
                throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
            }
            Iterator iterator = hashSet.iterator();
            while (iterator.hasNext() && certPathBuilderResult == null) {
                certPathBuilderResult = this.build(x509AttributeCertificate, (X509Certificate)iterator.next(), pKIXExtendedBuilderParameters, arrayList);
            }
        }
        if (certPathBuilderResult == null && this.certPathException != null) {
            throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
        }
        if (certPathBuilderResult == null && this.certPathException == null) {
            throw new CertPathBuilderException("Unable to find certificate chain.");
        }
        return certPathBuilderResult;
    }

    private CertPathBuilderResult build(X509AttributeCertificate x509AttributeCertificate, X509Certificate x509Certificate, PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters, List list) {
        CertPathValidator certPathValidator;
        CertificateFactory certificateFactory;
        if (list.contains(x509Certificate)) {
            return null;
        }
        if (pKIXExtendedBuilderParameters.getExcludedCerts().contains(x509Certificate)) {
            return null;
        }
        if (pKIXExtendedBuilderParameters.getMaxPathLength() != -1 && list.size() - 1 > pKIXExtendedBuilderParameters.getMaxPathLength()) {
            return null;
        }
        list.add(x509Certificate);
        CertPathBuilderResult certPathBuilderResult = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509", "BC");
            certPathValidator = CertPathValidator.getInstance("RFC3281", "BC");
        } catch (Exception exception) {
            throw new RuntimeException("Exception creating support classes.");
        }
        try {
            if (CertPathValidatorUtilities.isIssuerTrustAnchor(x509Certificate, pKIXExtendedBuilderParameters.getBaseParameters().getTrustAnchors(), pKIXExtendedBuilderParameters.getBaseParameters().getSigProvider())) {
                PKIXCertPathValidatorResult pKIXCertPathValidatorResult;
                CertPath certPath;
                try {
                    certPath = certificateFactory.generateCertPath(list);
                } catch (Exception exception) {
                    throw new AnnotatedException("Certification path could not be constructed from certificate list.", exception);
                }
                try {
                    pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult)certPathValidator.validate(certPath, pKIXExtendedBuilderParameters);
                } catch (Exception exception) {
                    throw new AnnotatedException("Certification path could not be validated.", exception);
                }
                return new PKIXCertPathBuilderResult(certPath, pKIXCertPathValidatorResult.getTrustAnchor(), pKIXCertPathValidatorResult.getPolicyTree(), pKIXCertPathValidatorResult.getPublicKey());
            }
            ArrayList<PKIXCertStore> arrayList = new ArrayList<PKIXCertStore>();
            arrayList.addAll(pKIXExtendedBuilderParameters.getBaseParameters().getCertificateStores());
            try {
                arrayList.addAll(CertPathValidatorUtilities.getAdditionalStoresFromAltNames(x509Certificate.getExtensionValue(Extension.issuerAlternativeName.getId()), pKIXExtendedBuilderParameters.getBaseParameters().getNamedCertificateStoreMap()));
            } catch (CertificateParsingException certificateParsingException) {
                throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", certificateParsingException);
            }
            HashSet hashSet = new HashSet();
            try {
                hashSet.addAll(CertPathValidatorUtilities.findIssuerCerts(x509Certificate, pKIXExtendedBuilderParameters.getBaseParameters().getCertStores(), arrayList));
            } catch (AnnotatedException annotatedException) {
                throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", annotatedException);
            }
            if (hashSet.isEmpty()) {
                throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
            }
            Iterator iterator = hashSet.iterator();
            while (iterator.hasNext() && certPathBuilderResult == null) {
                X509Certificate x509Certificate2 = (X509Certificate)iterator.next();
                if (x509Certificate2.getIssuerX500Principal().equals(x509Certificate2.getSubjectX500Principal())) continue;
                certPathBuilderResult = this.build(x509AttributeCertificate, x509Certificate2, pKIXExtendedBuilderParameters, list);
            }
        } catch (AnnotatedException annotatedException) {
            this.certPathException = new AnnotatedException("No valid certification path could be build.", annotatedException);
        }
        if (certPathBuilderResult == null) {
            list.remove(x509Certificate);
        }
        return certPathBuilderResult;
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, List list) throws AnnotatedException {
        HashSet hashSet = new HashSet();
        for (Object e : list) {
            if (!(e instanceof Store)) continue;
            Store store = (Store)e;
            try {
                hashSet.addAll(store.getMatches(x509AttributeCertStoreSelector));
            } catch (StoreException storeException) {
                throw new AnnotatedException("Problem while picking certificates from X.509 store.", storeException);
            }
        }
        return hashSet;
    }
}

