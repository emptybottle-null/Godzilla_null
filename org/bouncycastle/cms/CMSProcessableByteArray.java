/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSReadable;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.util.Arrays;

public class CMSProcessableByteArray
implements CMSTypedData,
CMSReadable {
    private final ASN1ObjectIdentifier type;
    private final byte[] bytes;

    public CMSProcessableByteArray(byte[] byArray) {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), byArray);
    }

    public CMSProcessableByteArray(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) {
        this.type = aSN1ObjectIdentifier;
        this.bytes = byArray;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.bytes);
    }

    public void write(OutputStream outputStream) throws IOException, CMSException {
        outputStream.write(this.bytes);
    }

    public Object getContent() {
        return Arrays.clone(this.bytes);
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.type;
    }
}

