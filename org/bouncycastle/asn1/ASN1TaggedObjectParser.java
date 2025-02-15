/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.InMemoryRepresentable;

public interface ASN1TaggedObjectParser
extends ASN1Encodable,
InMemoryRepresentable {
    public int getTagNo();

    public ASN1Encodable getObjectParser(int var1, boolean var2) throws IOException;
}

