/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EncryptedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEncryptedData;
import org.bouncycastle.cms.CMSEncryptedGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.operator.OutputEncryptor;

public class CMSEncryptedDataGenerator
extends CMSEncryptedGenerator {
    private CMSEncryptedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        Object object;
        Object object2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            object2 = outputEncryptor.getOutputStream(byteArrayOutputStream);
            cMSTypedData.write((OutputStream)object2);
            ((OutputStream)object2).close();
        } catch (IOException iOException) {
            throw new CMSException("");
        }
        object2 = byteArrayOutputStream.toByteArray();
        AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
        BEROctetString bEROctetString = new BEROctetString((byte[])object2);
        EncryptedContentInfo encryptedContentInfo = new EncryptedContentInfo(cMSTypedData.getContentType(), algorithmIdentifier, bEROctetString);
        BERSet bERSet = null;
        if (this.unprotectedAttributeGenerator != null) {
            object = this.unprotectedAttributeGenerator.getAttributes(new HashMap());
            bERSet = new BERSet(((AttributeTable)object).toASN1EncodableVector());
        }
        object = new ContentInfo(CMSObjectIdentifiers.encryptedData, new EncryptedData(encryptedContentInfo, bERSet));
        return new CMSEncryptedData((ContentInfo)object);
    }

    public CMSEncryptedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return this.doGenerate(cMSTypedData, outputEncryptor);
    }
}

