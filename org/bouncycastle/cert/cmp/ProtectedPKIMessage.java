/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.CMPObjectIdentifiers;
import org.bouncycastle.asn1.cmp.PBMParameter;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.cmp.PKIHeader;
import org.bouncycastle.asn1.cmp.PKIMessage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.cmp.CMPException;
import org.bouncycastle.cert.cmp.GeneralPKIMessage;
import org.bouncycastle.cert.crmf.PKMACBuilder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.Arrays;

public class ProtectedPKIMessage {
    private PKIMessage pkiMessage;

    public ProtectedPKIMessage(GeneralPKIMessage generalPKIMessage) {
        if (!generalPKIMessage.hasProtection()) {
            throw new IllegalArgumentException("PKIMessage not protected");
        }
        this.pkiMessage = generalPKIMessage.toASN1Structure();
    }

    ProtectedPKIMessage(PKIMessage pKIMessage) {
        if (pKIMessage.getHeader().getProtectionAlg() == null) {
            throw new IllegalArgumentException("PKIMessage not protected");
        }
        this.pkiMessage = pKIMessage;
    }

    public PKIHeader getHeader() {
        return this.pkiMessage.getHeader();
    }

    public PKIBody getBody() {
        return this.pkiMessage.getBody();
    }

    public PKIMessage toASN1Structure() {
        return this.pkiMessage;
    }

    public boolean hasPasswordBasedMacProtection() {
        return this.pkiMessage.getHeader().getProtectionAlg().getAlgorithm().equals(CMPObjectIdentifiers.passwordBasedMac);
    }

    public X509CertificateHolder[] getCertificates() {
        CMPCertificate[] cMPCertificateArray = this.pkiMessage.getExtraCerts();
        if (cMPCertificateArray == null) {
            return new X509CertificateHolder[0];
        }
        X509CertificateHolder[] x509CertificateHolderArray = new X509CertificateHolder[cMPCertificateArray.length];
        for (int i = 0; i != cMPCertificateArray.length; ++i) {
            x509CertificateHolderArray[i] = new X509CertificateHolder(cMPCertificateArray[i].getX509v3PKCert());
        }
        return x509CertificateHolderArray;
    }

    public boolean verify(ContentVerifierProvider contentVerifierProvider) throws CMPException {
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.pkiMessage.getHeader().getProtectionAlg());
            return this.verifySignature(this.pkiMessage.getProtection().getBytes(), contentVerifier);
        } catch (Exception exception) {
            throw new CMPException("unable to verify signature: " + exception.getMessage(), exception);
        }
    }

    public boolean verify(PKMACBuilder pKMACBuilder, char[] cArray) throws CMPException {
        if (!CMPObjectIdentifiers.passwordBasedMac.equals(this.pkiMessage.getHeader().getProtectionAlg().getAlgorithm())) {
            throw new CMPException("protection algorithm not mac based");
        }
        try {
            pKMACBuilder.setParameters(PBMParameter.getInstance(this.pkiMessage.getHeader().getProtectionAlg().getParameters()));
            MacCalculator macCalculator = pKMACBuilder.build(cArray);
            OutputStream outputStream = macCalculator.getOutputStream();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(this.pkiMessage.getHeader());
            aSN1EncodableVector.add(this.pkiMessage.getBody());
            outputStream.write(new DERSequence(aSN1EncodableVector).getEncoded("DER"));
            outputStream.close();
            return Arrays.areEqual(macCalculator.getMac(), this.pkiMessage.getProtection().getBytes());
        } catch (Exception exception) {
            throw new CMPException("unable to verify MAC: " + exception.getMessage(), exception);
        }
    }

    private boolean verifySignature(byte[] byArray, ContentVerifier contentVerifier) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiMessage.getHeader());
        aSN1EncodableVector.add(this.pkiMessage.getBody());
        OutputStream outputStream = contentVerifier.getOutputStream();
        outputStream.write(new DERSequence(aSN1EncodableVector).getEncoded("DER"));
        outputStream.close();
        return contentVerifier.verify(byArray);
    }
}

