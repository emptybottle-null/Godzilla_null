/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.cmp.CMPException;
import org.bouncycastle.cert.cmp.CMPUtil;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;

public class CertificateStatus {
    private DigestAlgorithmIdentifierFinder digestAlgFinder;
    private CertStatus certStatus;

    CertificateStatus(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder, CertStatus certStatus) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.certStatus = certStatus;
    }

    public PKIStatusInfo getStatusInfo() {
        return this.certStatus.getStatusInfo();
    }

    public BigInteger getCertRequestID() {
        return this.certStatus.getCertReqId().getValue();
    }

    public boolean isVerified(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        DigestCalculator digestCalculator;
        AlgorithmIdentifier algorithmIdentifier = this.digestAlgFinder.find(x509CertificateHolder.toASN1Structure().getSignatureAlgorithm());
        if (algorithmIdentifier == null) {
            throw new CMPException("cannot find algorithm for digest from signature");
        }
        try {
            digestCalculator = digestCalculatorProvider.get(algorithmIdentifier);
        } catch (OperatorCreationException operatorCreationException) {
            throw new CMPException("unable to create digester: " + operatorCreationException.getMessage(), operatorCreationException);
        }
        CMPUtil.derEncodeToStream(x509CertificateHolder.toASN1Structure(), digestCalculator.getOutputStream());
        return Arrays.areEqual(this.certStatus.getCertHash().getOctets(), digestCalculator.getDigest());
    }
}

