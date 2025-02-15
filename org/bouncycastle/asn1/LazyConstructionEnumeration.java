/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

class LazyConstructionEnumeration
implements Enumeration {
    private ASN1InputStream aIn;
    private Object nextObj;

    public LazyConstructionEnumeration(byte[] byArray) {
        this.aIn = new ASN1InputStream(byArray, true);
        this.nextObj = this.readObject();
    }

    public boolean hasMoreElements() {
        return this.nextObj != null;
    }

    public Object nextElement() {
        Object object = this.nextObj;
        this.nextObj = this.readObject();
        return object;
    }

    private Object readObject() {
        try {
            return this.aIn.readObject();
        } catch (IOException iOException) {
            throw new ASN1ParsingException("malformed DER construction: " + iOException, iOException);
        }
    }
}

