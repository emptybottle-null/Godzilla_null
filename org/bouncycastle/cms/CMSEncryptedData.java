/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EncryptedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;

public class CMSEncryptedData {
    private ContentInfo contentInfo;
    private EncryptedData encryptedData;

    public CMSEncryptedData(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
        this.encryptedData = EncryptedData.getInstance(contentInfo.getContent());
    }

    public byte[] getContent(InputDecryptorProvider inputDecryptorProvider) throws CMSException {
        try {
            return CMSUtils.streamToByteArray(this.getContentStream(inputDecryptorProvider).getContentStream());
        } catch (IOException iOException) {
            throw new CMSException("unable to parse internal stream: " + iOException.getMessage(), iOException);
        }
    }

    public CMSTypedStream getContentStream(InputDecryptorProvider inputDecryptorProvider) throws CMSException {
        try {
            EncryptedContentInfo encryptedContentInfo = this.encryptedData.getEncryptedContentInfo();
            InputDecryptor inputDecryptor = inputDecryptorProvider.get(encryptedContentInfo.getContentEncryptionAlgorithm());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encryptedContentInfo.getEncryptedContent().getOctets());
            return new CMSTypedStream(encryptedContentInfo.getContentType(), inputDecryptor.getInputStream(byteArrayInputStream));
        } catch (Exception exception) {
            throw new CMSException("unable to create stream: " + exception.getMessage(), exception);
        }
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }
}

