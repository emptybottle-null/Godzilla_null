/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.util.Hashtable;

public class BidirectionalMap
extends Hashtable {
    private static final long serialVersionUID = -7457289971962812909L;
    Hashtable reverseMap = new Hashtable();

    public Object getReverse(Object object) {
        return this.reverseMap.get(object);
    }

    public Object put(Object object, Object object2) {
        this.reverseMap.put(object2, object);
        return super.put(object, object2);
    }
}

