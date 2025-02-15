/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;

public class CMSEnvelopedDataGenerator
extends CMSEnvelopedGenerator {
    private CMSEnvelopedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        Object object;
        Object object22;
        Object object3;
        if (!this.oldRecipientInfoGenerators.isEmpty()) {
            throw new IllegalStateException("can only use addRecipientGenerator() with this method");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            object3 = outputEncryptor.getOutputStream(byteArrayOutputStream);
            cMSTypedData.write((OutputStream)object3);
            ((OutputStream)object3).close();
        } catch (IOException iOException) {
            throw new CMSException("");
        }
        object3 = byteArrayOutputStream.toByteArray();
        AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
        BEROctetString bEROctetString = new BEROctetString((byte[])object3);
        GenericKey genericKey = outputEncryptor.getKey();
        for (Object object22 : this.recipientInfoGenerators) {
            aSN1EncodableVector.add(object22.generate(genericKey));
        }
        EncryptedContentInfo encryptedContentInfo = new EncryptedContentInfo(cMSTypedData.getContentType(), algorithmIdentifier, bEROctetString);
        object22 = null;
        if (this.unprotectedAttributeGenerator != null) {
            object = this.unprotectedAttributeGenerator.getAttributes(new HashMap());
            object22 = new BERSet(((AttributeTable)object).toASN1EncodableVector());
        }
        object = new ContentInfo(CMSObjectIdentifiers.envelopedData, new EnvelopedData(this.originatorInfo, (ASN1Set)new DERSet(aSN1EncodableVector), encryptedContentInfo, (ASN1Set)object22));
        return new CMSEnvelopedData((ContentInfo)object);
    }

    public CMSEnvelopedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return this.doGenerate(cMSTypedData, outputEncryptor);
    }
}

