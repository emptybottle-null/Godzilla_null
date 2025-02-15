/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.icao;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;

public class LDSVersionInfo
extends ASN1Object {
    private DERPrintableString ldsVersion;
    private DERPrintableString unicodeVersion;

    public LDSVersionInfo(String string, String string2) {
        this.ldsVersion = new DERPrintableString(string);
        this.unicodeVersion = new DERPrintableString(string2);
    }

    private LDSVersionInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("sequence wrong size for LDSVersionInfo");
        }
        this.ldsVersion = DERPrintableString.getInstance(aSN1Sequence.getObjectAt(0));
        this.unicodeVersion = DERPrintableString.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static LDSVersionInfo getInstance(Object object) {
        if (object instanceof LDSVersionInfo) {
            return (LDSVersionInfo)object;
        }
        if (object != null) {
            return new LDSVersionInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public String getLdsVersion() {
        return this.ldsVersion.getString();
    }

    public String getUnicodeVersion() {
        return this.unicodeVersion.getString();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.ldsVersion);
        aSN1EncodableVector.add(this.unicodeVersion);
        return new DERSequence(aSN1EncodableVector);
    }
}

