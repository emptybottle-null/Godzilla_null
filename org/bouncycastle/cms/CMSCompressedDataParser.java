/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.cms.CompressedDataParser;
import org.bouncycastle.asn1.cms.ContentInfoParser;
import org.bouncycastle.cms.CMSContentInfoParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.operator.InputExpander;
import org.bouncycastle.operator.InputExpanderProvider;

public class CMSCompressedDataParser
extends CMSContentInfoParser {
    public CMSCompressedDataParser(byte[] byArray) throws CMSException {
        this(new ByteArrayInputStream(byArray));
    }

    public CMSCompressedDataParser(InputStream inputStream) throws CMSException {
        super(inputStream);
    }

    public CMSTypedStream getContent(InputExpanderProvider inputExpanderProvider) throws CMSException {
        try {
            CompressedDataParser compressedDataParser = new CompressedDataParser((ASN1SequenceParser)this._contentInfo.getContent(16));
            ContentInfoParser contentInfoParser = compressedDataParser.getEncapContentInfo();
            InputExpander inputExpander = inputExpanderProvider.get(compressedDataParser.getCompressionAlgorithmIdentifier());
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser)contentInfoParser.getContent(4);
            return new CMSTypedStream(contentInfoParser.getContentType().getId(), inputExpander.getInputStream(aSN1OctetStringParser.getOctetStream()));
        } catch (IOException iOException) {
            throw new CMSException("IOException reading compressed content.", iOException);
        }
    }
}

