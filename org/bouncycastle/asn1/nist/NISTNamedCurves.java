/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.nist;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.util.Strings;

public class NISTNamedCurves {
    static final Hashtable objIds = new Hashtable();
    static final Hashtable names = new Hashtable();

    static void defineCurve(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        objIds.put(string, aSN1ObjectIdentifier);
        names.put(aSN1ObjectIdentifier, string);
    }

    public static X9ECParameters getByName(String string) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier)objIds.get(Strings.toUpperCase(string));
        if (aSN1ObjectIdentifier != null) {
            return NISTNamedCurves.getByOID(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return SECNamedCurves.getByOID(aSN1ObjectIdentifier);
    }

    public static ASN1ObjectIdentifier getOID(String string) {
        return (ASN1ObjectIdentifier)objIds.get(Strings.toUpperCase(string));
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String)names.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return objIds.keys();
    }

    static {
        NISTNamedCurves.defineCurve("B-571", SECObjectIdentifiers.sect571r1);
        NISTNamedCurves.defineCurve("B-409", SECObjectIdentifiers.sect409r1);
        NISTNamedCurves.defineCurve("B-283", SECObjectIdentifiers.sect283r1);
        NISTNamedCurves.defineCurve("B-233", SECObjectIdentifiers.sect233r1);
        NISTNamedCurves.defineCurve("B-163", SECObjectIdentifiers.sect163r2);
        NISTNamedCurves.defineCurve("K-571", SECObjectIdentifiers.sect571k1);
        NISTNamedCurves.defineCurve("K-409", SECObjectIdentifiers.sect409k1);
        NISTNamedCurves.defineCurve("K-283", SECObjectIdentifiers.sect283k1);
        NISTNamedCurves.defineCurve("K-233", SECObjectIdentifiers.sect233k1);
        NISTNamedCurves.defineCurve("K-163", SECObjectIdentifiers.sect163k1);
        NISTNamedCurves.defineCurve("P-521", SECObjectIdentifiers.secp521r1);
        NISTNamedCurves.defineCurve("P-384", SECObjectIdentifiers.secp384r1);
        NISTNamedCurves.defineCurve("P-256", SECObjectIdentifiers.secp256r1);
        NISTNamedCurves.defineCurve("P-224", SECObjectIdentifiers.secp224r1);
        NISTNamedCurves.defineCurve("P-192", SECObjectIdentifiers.secp192r1);
    }
}

