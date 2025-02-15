/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientId;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.util.io.Streams;

public abstract class RecipientInformation {
    protected RecipientId rid;
    protected AlgorithmIdentifier keyEncAlg;
    protected AlgorithmIdentifier messageAlgorithm;
    protected CMSSecureReadable secureReadable;
    private AuthAttributesProvider additionalData;
    private byte[] resultMac;
    private RecipientOperator operator;

    RecipientInformation(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        this.keyEncAlg = algorithmIdentifier;
        this.messageAlgorithm = algorithmIdentifier2;
        this.secureReadable = cMSSecureReadable;
        this.additionalData = authAttributesProvider;
    }

    public RecipientId getRID() {
        return this.rid;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncAlg;
    }

    public String getKeyEncryptionAlgOID() {
        return this.keyEncAlg.getAlgorithm().getId();
    }

    public byte[] getKeyEncryptionAlgParams() {
        try {
            return this.encodeObj(this.keyEncAlg.getParameters());
        } catch (Exception exception) {
            throw new RuntimeException("exception getting encryption parameters " + exception);
        }
    }

    public byte[] getContentDigest() {
        if (this.secureReadable instanceof CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable) {
            return ((CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable)this.secureReadable).getDigest();
        }
        return null;
    }

    public byte[] getMac() {
        if (this.resultMac == null && this.operator.isMacBased()) {
            if (this.additionalData != null) {
                try {
                    Streams.drain(this.operator.getInputStream(new ByteArrayInputStream(this.additionalData.getAuthAttributes().getEncoded("DER"))));
                } catch (IOException iOException) {
                    throw new IllegalStateException("unable to drain input: " + iOException.getMessage());
                }
            }
            this.resultMac = this.operator.getMac();
        }
        return this.resultMac;
    }

    public byte[] getContent(Recipient recipient) throws CMSException {
        try {
            return CMSUtils.streamToByteArray(this.getContentStream(recipient).getContentStream());
        } catch (IOException iOException) {
            throw new CMSException("unable to parse internal stream: " + iOException.getMessage(), iOException);
        }
    }

    public CMSTypedStream getContentStream(Recipient recipient) throws CMSException, IOException {
        this.operator = this.getRecipientOperator(recipient);
        if (this.additionalData != null) {
            return new CMSTypedStream(this.secureReadable.getInputStream());
        }
        return new CMSTypedStream(this.operator.getInputStream(this.secureReadable.getInputStream()));
    }

    protected abstract RecipientOperator getRecipientOperator(Recipient var1) throws CMSException, IOException;
}

