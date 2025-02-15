/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.ConstructedOctetStream;
import org.bouncycastle.util.io.Streams;

public class BEROctetStringParser
implements ASN1OctetStringParser {
    private ASN1StreamParser _parser;

    BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return new BEROctetString(Streams.readAll(this.getOctetStream()));
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return this.getLoadedObject();
        } catch (IOException iOException) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + iOException.getMessage(), iOException);
        }
    }
}

