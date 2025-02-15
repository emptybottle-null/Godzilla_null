/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.ContentInfo;

public abstract class DVCSMessage {
    private final ContentInfo contentInfo;

    protected DVCSMessage(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentInfo.getContentType();
    }

    public abstract ASN1Encodable getContent();
}

