/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.EncryptedContentInfoParser;
import org.bouncycastle.asn1.cms.OriginatorInfo;

public class AuthEnvelopedDataParser {
    private ASN1SequenceParser seq;
    private ASN1Integer version;
    private ASN1Encodable nextObject;
    private boolean originatorInfoCalled;
    private EncryptedContentInfoParser authEncryptedContentInfoParser;

    public AuthEnvelopedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.seq = aSN1SequenceParser;
        this.version = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
        if (this.version.getValue().intValue() != 0) {
            throw new ASN1ParsingException("AuthEnvelopedData version number must be 0");
        }
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public OriginatorInfo getOriginatorInfo() throws IOException {
        this.originatorInfoCalled = true;
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        if (this.nextObject instanceof ASN1TaggedObjectParser && ((ASN1TaggedObjectParser)this.nextObject).getTagNo() == 0) {
            ASN1SequenceParser aSN1SequenceParser = (ASN1SequenceParser)((ASN1TaggedObjectParser)this.nextObject).getObjectParser(16, false);
            this.nextObject = null;
            return OriginatorInfo.getInstance(aSN1SequenceParser.toASN1Primitive());
        }
        return null;
    }

    public ASN1SetParser getRecipientInfos() throws IOException {
        if (!this.originatorInfoCalled) {
            this.getOriginatorInfo();
        }
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        ASN1SetParser aSN1SetParser = (ASN1SetParser)this.nextObject;
        this.nextObject = null;
        return aSN1SetParser;
    }

    public EncryptedContentInfoParser getAuthEncryptedContentInfo() throws IOException {
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        if (this.nextObject != null) {
            ASN1SequenceParser aSN1SequenceParser = (ASN1SequenceParser)this.nextObject;
            this.nextObject = null;
            this.authEncryptedContentInfoParser = new EncryptedContentInfoParser(aSN1SequenceParser);
            return this.authEncryptedContentInfoParser;
        }
        return null;
    }

    public ASN1SetParser getAuthAttrs() throws IOException {
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        if (this.nextObject instanceof ASN1TaggedObjectParser) {
            ASN1Encodable aSN1Encodable = this.nextObject;
            this.nextObject = null;
            return (ASN1SetParser)((ASN1TaggedObjectParser)aSN1Encodable).getObjectParser(17, false);
        }
        if (!this.authEncryptedContentInfoParser.getContentType().equals(CMSObjectIdentifiers.data)) {
            throw new ASN1ParsingException("authAttrs must be present with non-data content");
        }
        return null;
    }

    public ASN1OctetString getMac() throws IOException {
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        ASN1Encodable aSN1Encodable = this.nextObject;
        this.nextObject = null;
        return ASN1OctetString.getInstance(aSN1Encodable.toASN1Primitive());
    }

    public ASN1SetParser getUnauthAttrs() throws IOException {
        if (this.nextObject == null) {
            this.nextObject = this.seq.readObject();
        }
        if (this.nextObject != null) {
            ASN1Encodable aSN1Encodable = this.nextObject;
            this.nextObject = null;
            return (ASN1SetParser)((ASN1TaggedObjectParser)aSN1Encodable).getObjectParser(17, false);
        }
        return null;
    }
}

