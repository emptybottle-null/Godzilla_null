/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.CertPathValidatorUtilities;
import org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathBuilderSpi
extends CertPathBuilderSpi {
    private Exception certPathException;

    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters;
        Object object;
        Object object2;
        Cloneable cloneable;
        Object object3;
        if (certPathParameters instanceof PKIXBuilderParameters) {
            object3 = new PKIXExtendedParameters.Builder((PKIXBuilderParameters)certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                cloneable = (ExtendedPKIXBuilderParameters)certPathParameters;
                object2 = ((ExtendedPKIXParameters)cloneable).getAdditionalStores().iterator();
                while (object2.hasNext()) {
                    ((PKIXExtendedParameters.Builder)object3).addCertificateStore((PKIXCertStore)object2.next());
                }
                object = new PKIXExtendedBuilderParameters.Builder(((PKIXExtendedParameters.Builder)object3).build());
                ((PKIXExtendedBuilderParameters.Builder)object).addExcludedCerts(((ExtendedPKIXBuilderParameters)cloneable).getExcludedCerts());
                ((PKIXExtendedBuilderParameters.Builder)object).setMaxPathLength(((ExtendedPKIXBuilderParameters)cloneable).getMaxPathLength());
            } else {
                object = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters)certPathParameters);
            }
            pKIXExtendedBuilderParameters = ((PKIXExtendedBuilderParameters.Builder)object).build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedBuilderParameters = (PKIXExtendedBuilderParameters)certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        }
        cloneable = new ArrayList();
        PKIXCertStoreSelector pKIXCertStoreSelector = pKIXExtendedBuilderParameters.getBaseParameters().getTargetConstraints();
        try {
            object3 = CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector, pKIXExtendedBuilderParameters.getBaseParameters().getCertificateStores());
            object3.addAll(CertPathValidatorUtilities.findCertificates(pKIXCertStoreSelector, pKIXExtendedBuilderParameters.getBaseParameters().getCertStores()));
        } catch (AnnotatedException annotatedException) {
            throw new ExtCertPathBuilderException("Error finding target certificate.", annotatedException);
        }
        if (object3.isEmpty()) {
            throw new CertPathBuilderException("No certificate found matching targetContraints.");
        }
        CertPathBuilderResult certPathBuilderResult = null;
        object = object3.iterator();
        while (object.hasNext() && certPathBuilderResult == null) {
            object2 = (X509Certificate)object.next();
            certPathBuilderResult = this.build((X509Certificate)object2, pKIXExtendedBuilderParameters, (List)((Object)cloneable));
        }
        if (certPathBuilderResult == null && this.certPathException != null) {
            if (this.certPathException instanceof AnnotatedException) {
                throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
            }
            throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
        }
        if (certPathBuilderResult == null && this.certPathException == null) {
            throw new CertPathBuilderException("Unable to find certificate chain.");
        }
        return certPathBuilderResult;
    }

    protected CertPathBuilderResult build(X509Certificate x509Certificate, PKIXExtendedBuilderParameters pKIXExtendedBuilderParameters, List list) {
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi;
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
            certificateFactory = new CertificateFactory();
            pKIXCertPathValidatorSpi = new PKIXCertPathValidatorSpi();
        } catch (Exception exception) {
            throw new RuntimeException("Exception creating support classes.");
        }
        try {
            if (CertPathValidatorUtilities.isIssuerTrustAnchor(x509Certificate, pKIXExtendedBuilderParameters.getBaseParameters().getTrustAnchors(), pKIXExtendedBuilderParameters.getBaseParameters().getSigProvider())) {
                CertPath certPath = null;
                PKIXCertPathValidatorResult pKIXCertPathValidatorResult = null;
                try {
                    certPath = certificateFactory.engineGenerateCertPath(list);
                } catch (Exception exception) {
                    throw new AnnotatedException("Certification path could not be constructed from certificate list.", exception);
                }
                try {
                    pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult)pKIXCertPathValidatorSpi.engineValidate(certPath, pKIXExtendedBuilderParameters);
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
                certPathBuilderResult = this.build(x509Certificate2, pKIXExtendedBuilderParameters, list);
            }
        } catch (AnnotatedException annotatedException) {
            this.certPathException = annotatedException;
        }
        if (certPathBuilderResult == null) {
            list.remove(x509Certificate);
        }
        return certPathBuilderResult;
    }
}

