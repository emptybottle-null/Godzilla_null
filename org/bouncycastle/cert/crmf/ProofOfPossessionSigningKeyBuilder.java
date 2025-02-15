/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.crmf.POPOSigningKey;
import org.bouncycastle.asn1.crmf.POPOSigningKeyInput;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.CRMFUtil;
import org.bouncycastle.cert.crmf.PKMACValueGenerator;
import org.bouncycastle.operator.ContentSigner;

public class ProofOfPossessionSigningKeyBuilder {
    private CertRequest certRequest;
    private SubjectPublicKeyInfo pubKeyInfo;
    private GeneralName name;
    private PKMACValue publicKeyMAC;

    public ProofOfPossessionSigningKeyBuilder(CertRequest certRequest) {
        this.certRequest = certRequest;
    }

    public ProofOfPossessionSigningKeyBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.pubKeyInfo = subjectPublicKeyInfo;
    }

    public ProofOfPossessionSigningKeyBuilder setSender(GeneralName generalName) {
        this.name = generalName;
        return this;
    }

    public ProofOfPossessionSigningKeyBuilder setPublicKeyMac(PKMACValueGenerator pKMACValueGenerator, char[] cArray) throws CRMFException {
        this.publicKeyMAC = pKMACValueGenerator.generate(cArray, this.pubKeyInfo);
        return this;
    }

    public POPOSigningKey build(ContentSigner contentSigner) {
        POPOSigningKeyInput pOPOSigningKeyInput;
        if (this.name != null && this.publicKeyMAC != null) {
            throw new IllegalStateException("name and publicKeyMAC cannot both be set.");
        }
        if (this.certRequest != null) {
            pOPOSigningKeyInput = null;
            CRMFUtil.derEncodeToStream(this.certRequest, contentSigner.getOutputStream());
        } else if (this.name != null) {
            pOPOSigningKeyInput = new POPOSigningKeyInput(this.name, this.pubKeyInfo);
            CRMFUtil.derEncodeToStream(pOPOSigningKeyInput, contentSigner.getOutputStream());
        } else {
            pOPOSigningKeyInput = new POPOSigningKeyInput(this.publicKeyMAC, this.pubKeyInfo);
            CRMFUtil.derEncodeToStream(pOPOSigningKeyInput, contentSigner.getOutputStream());
        }
        return new POPOSigningKey(pOPOSigningKeyInput, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature()));
    }
}

