/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.CertConfirmContent;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.cmp.CMPException;
import org.bouncycastle.cert.cmp.CMPUtil;
import org.bouncycastle.cert.cmp.CertificateConfirmationContent;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;

public class CertificateConfirmationContentBuilder {
    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private List acceptedCerts = new ArrayList();
    private List acceptedReqIds = new ArrayList();

    public CertificateConfirmationContentBuilder() {
        this(new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContentBuilder(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
    }

    public CertificateConfirmationContentBuilder addAcceptedCertificate(X509CertificateHolder x509CertificateHolder, BigInteger bigInteger) {
        this.acceptedCerts.add(x509CertificateHolder);
        this.acceptedReqIds.add(bigInteger);
        return this;
    }

    public CertificateConfirmationContent build(DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.acceptedCerts.size(); ++i) {
            DigestCalculator digestCalculator;
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder)this.acceptedCerts.get(i);
            BigInteger bigInteger = (BigInteger)this.acceptedReqIds.get(i);
            AlgorithmIdentifier algorithmIdentifier = this.digestAlgFinder.find(x509CertificateHolder.toASN1Structure().getSignatureAlgorithm());
            if (algorithmIdentifier == null) {
                throw new CMPException("cannot find algorithm for digest from signature");
            }
            try {
                digestCalculator = digestCalculatorProvider.get(algorithmIdentifier);
            } catch (OperatorCreationException operatorCreationException) {
                throw new CMPException("unable to create digest: " + operatorCreationException.getMessage(), operatorCreationException);
            }
            CMPUtil.derEncodeToStream(x509CertificateHolder.toASN1Structure(), digestCalculator.getOutputStream());
            aSN1EncodableVector.add(new CertStatus(digestCalculator.getDigest(), bigInteger));
        }
        return new CertificateConfirmationContent(CertConfirmContent.getInstance(new DERSequence(aSN1EncodableVector)), this.digestAlgFinder);
    }
}

