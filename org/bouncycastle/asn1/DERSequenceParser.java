/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERSequence;

public class DERSequenceParser
implements ASN1SequenceParser {
    private ASN1StreamParser _parser;

    DERSequenceParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public ASN1Encodable readObject() throws IOException {
        return this._parser.readObject();
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return new DERSequence(this._parser.readVector());
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return this.getLoadedObject();
        } catch (IOException iOException) {
            throw new IllegalStateException(iOException.getMessage());
        }
    }
}

