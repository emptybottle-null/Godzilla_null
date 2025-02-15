/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.x509.X509StoreParameters;

public class X509CollectionStoreParameters
implements X509StoreParameters {
    private Collection collection;

    public X509CollectionStoreParameters(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("collection cannot be null");
        }
        this.collection = collection;
    }

    public Object clone() {
        return new X509CollectionStoreParameters(this.collection);
    }

    public Collection getCollection() {
        return new ArrayList(this.collection);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("X509CollectionStoreParameters: [\n");
        stringBuffer.append("  collection: " + this.collection + "\n");
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}

