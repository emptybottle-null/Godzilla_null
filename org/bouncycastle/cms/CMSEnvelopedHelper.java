/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSReadable;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.KEKRecipientInformation;
import org.bouncycastle.cms.KeyAgreeRecipientInformation;
import org.bouncycastle.cms.KeyTransRecipientInformation;
import org.bouncycastle.cms.PasswordRecipientInformation;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientInformationStore;
import org.bouncycastle.operator.DigestCalculator;

class CMSEnvelopedHelper {
    CMSEnvelopedHelper() {
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable) {
        return CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, algorithmIdentifier, cMSSecureReadable, null);
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        ArrayList<RecipientInformation> arrayList = new ArrayList<RecipientInformation>();
        for (int i = 0; i != aSN1Set.size(); ++i) {
            RecipientInfo recipientInfo = RecipientInfo.getInstance(aSN1Set.getObjectAt(i));
            CMSEnvelopedHelper.readRecipientInfo(arrayList, recipientInfo, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        }
        return new RecipientInformationStore(arrayList);
    }

    private static void readRecipientInfo(List list, RecipientInfo recipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        ASN1Encodable aSN1Encodable = recipientInfo.getInfo();
        if (aSN1Encodable instanceof KeyTransRecipientInfo) {
            list.add(new KeyTransRecipientInformation((KeyTransRecipientInfo)aSN1Encodable, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        } else if (aSN1Encodable instanceof KEKRecipientInfo) {
            list.add(new KEKRecipientInformation((KEKRecipientInfo)aSN1Encodable, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        } else if (aSN1Encodable instanceof KeyAgreeRecipientInfo) {
            KeyAgreeRecipientInformation.readRecipientInfo(list, (KeyAgreeRecipientInfo)aSN1Encodable, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        } else if (aSN1Encodable instanceof PasswordRecipientInfo) {
            list.add(new PasswordRecipientInformation((PasswordRecipientInfo)aSN1Encodable, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        }
    }

    static class CMSAuthenticatedSecureReadable
    implements CMSSecureReadable {
        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSAuthenticatedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        public InputStream getInputStream() throws IOException, CMSException {
            return this.readable.getInputStream();
        }
    }

    static class CMSDigestAuthenticatedSecureReadable
    implements CMSSecureReadable {
        private DigestCalculator digestCalculator;
        private CMSReadable readable;

        public CMSDigestAuthenticatedSecureReadable(DigestCalculator digestCalculator, CMSReadable cMSReadable) {
            this.digestCalculator = digestCalculator;
            this.readable = cMSReadable;
        }

        public InputStream getInputStream() throws IOException, CMSException {
            return new FilterInputStream(this.readable.getInputStream()){

                public int read() throws IOException {
                    int n = this.in.read();
                    if (n >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(n);
                    }
                    return n;
                }

                public int read(byte[] byArray, int n, int n2) throws IOException {
                    int n3 = this.in.read(byArray, n, n2);
                    if (n3 >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(byArray, n, n3);
                    }
                    return n3;
                }
            };
        }

        public byte[] getDigest() {
            return this.digestCalculator.getDigest();
        }
    }

    static class CMSEnvelopedSecureReadable
    implements CMSSecureReadable {
        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSEnvelopedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        public InputStream getInputStream() throws IOException, CMSException {
            return this.readable.getInputStream();
        }
    }
}

