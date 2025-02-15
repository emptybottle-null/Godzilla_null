/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import java.io.IOException;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.bc.BcKeyTransRecipientInfoGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.bc.BcAsymmetricKeyWrapper;
import org.bouncycastle.operator.bc.BcRSAAsymmetricKeyWrapper;

public class BcRSAKeyTransRecipientInfoGenerator
extends BcKeyTransRecipientInfoGenerator {
    public BcRSAKeyTransRecipientInfoGenerator(byte[] byArray, AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(byArray, (BcAsymmetricKeyWrapper)new BcRSAAsymmetricKeyWrapper(algorithmIdentifier, asymmetricKeyParameter));
    }

    public BcRSAKeyTransRecipientInfoGenerator(X509CertificateHolder x509CertificateHolder) throws IOException {
        super(x509CertificateHolder, (BcAsymmetricKeyWrapper)new BcRSAAsymmetricKeyWrapper(x509CertificateHolder.getSubjectPublicKeyInfo().getAlgorithmId(), x509CertificateHolder.getSubjectPublicKeyInfo()));
    }
}

