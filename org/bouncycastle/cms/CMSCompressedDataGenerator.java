/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.CompressedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSCompressedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.operator.OutputCompressor;

public class CMSCompressedDataGenerator {
    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";

    public CMSCompressedData generate(CMSTypedData cMSTypedData, OutputCompressor outputCompressor) throws CMSException {
        BEROctetString bEROctetString;
        AlgorithmIdentifier algorithmIdentifier;
        Object object;
        Object object2;
        try {
            object2 = new ByteArrayOutputStream();
            object = outputCompressor.getOutputStream((OutputStream)object2);
            cMSTypedData.write((OutputStream)object);
            ((OutputStream)object).close();
            algorithmIdentifier = outputCompressor.getAlgorithmIdentifier();
            bEROctetString = new BEROctetString(((ByteArrayOutputStream)object2).toByteArray());
        } catch (IOException iOException) {
            throw new CMSException("exception encoding data.", iOException);
        }
        object2 = new ContentInfo(cMSTypedData.getContentType(), bEROctetString);
        object = new ContentInfo(CMSObjectIdentifiers.compressedData, new CompressedData(algorithmIdentifier, (ContentInfo)object2));
        return new CMSCompressedData((ContentInfo)object);
    }
}

