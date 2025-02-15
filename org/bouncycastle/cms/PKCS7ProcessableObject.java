/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedData;

public class PKCS7ProcessableObject
implements CMSTypedData {
    private final ASN1ObjectIdentifier type;
    private final ASN1Encodable structure;

    public PKCS7ProcessableObject(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.type = aSN1ObjectIdentifier;
        this.structure = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.type;
    }

    public void write(OutputStream outputStream) throws IOException, CMSException {
        if (this.structure instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(this.structure);
            for (ASN1Encodable aSN1Encodable : aSN1Sequence) {
                outputStream.write(aSN1Encodable.toASN1Primitive().getEncoded("DER"));
            }
        } else {
            byte[] byArray = this.structure.toASN1Primitive().getEncoded("DER");
            int n = 1;
            while ((byArray[n] & 0xFF) > 127) {
                ++n;
            }
            outputStream.write(byArray, ++n, byArray.length - n);
        }
    }

    public Object getContent() {
        return this.structure;
    }
}

