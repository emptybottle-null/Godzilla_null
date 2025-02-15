/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.util;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;

public class DERDump
extends ASN1Dump {
    public static String dumpAsString(ASN1Primitive aSN1Primitive) {
        StringBuffer stringBuffer = new StringBuffer();
        DERDump._dumpAsString("", false, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }

    public static String dumpAsString(ASN1Encodable aSN1Encodable) {
        StringBuffer stringBuffer = new StringBuffer();
        DERDump._dumpAsString("", false, aSN1Encodable.toASN1Primitive(), stringBuffer);
        return stringBuffer.toString();
    }
}

