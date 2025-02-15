/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.DERExternal;
import org.bouncycastle.asn1.InMemoryRepresentable;

public class DERExternalParser
implements ASN1Encodable,
InMemoryRepresentable {
    private ASN1StreamParser _parser;

    public DERExternalParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public ASN1Encodable readObject() throws IOException {
        return this._parser.readObject();
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        try {
            return new DERExternal(this._parser.readVector());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ASN1Exception(illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return this.getLoadedObject();
        } catch (IOException iOException) {
            throw new ASN1ParsingException("unable to get DER object", iOException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new ASN1ParsingException("unable to get DER object", illegalArgumentException);
        }
    }
}

