/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class GenericKey {
    private AlgorithmIdentifier algorithmIdentifier;
    private Object representation;

    public GenericKey(Object object) {
        this.algorithmIdentifier = null;
        this.representation = object;
    }

    public GenericKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = byArray;
    }

    protected GenericKey(AlgorithmIdentifier algorithmIdentifier, Object object) {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = object;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public Object getRepresentation() {
        return this.representation;
    }
}

