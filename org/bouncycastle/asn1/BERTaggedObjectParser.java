/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;

public class BERTaggedObjectParser
implements ASN1TaggedObjectParser {
    private boolean _constructed;
    private int _tagNumber;
    private ASN1StreamParser _parser;

    BERTaggedObjectParser(boolean bl, int n, ASN1StreamParser aSN1StreamParser) {
        this._constructed = bl;
        this._tagNumber = n;
        this._parser = aSN1StreamParser;
    }

    public boolean isConstructed() {
        return this._constructed;
    }

    public int getTagNo() {
        return this._tagNumber;
    }

    public ASN1Encodable getObjectParser(int n, boolean bl) throws IOException {
        if (bl) {
            if (!this._constructed) {
                throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
            }
            return this._parser.readObject();
        }
        return this._parser.readImplicit(this._constructed, n);
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.readTaggedObject(this._constructed, this._tagNumber);
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return this.getLoadedObject();
        } catch (IOException iOException) {
            throw new ASN1ParsingException(iOException.getMessage());
        }
    }
}

