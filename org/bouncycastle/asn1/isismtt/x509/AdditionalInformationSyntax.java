/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.DirectoryString;

public class AdditionalInformationSyntax
extends ASN1Object {
    private DirectoryString information;

    public static AdditionalInformationSyntax getInstance(Object object) {
        if (object instanceof AdditionalInformationSyntax) {
            return (AdditionalInformationSyntax)object;
        }
        if (object != null) {
            return new AdditionalInformationSyntax(DirectoryString.getInstance(object));
        }
        return null;
    }

    private AdditionalInformationSyntax(DirectoryString directoryString) {
        this.information = directoryString;
    }

    public AdditionalInformationSyntax(String string) {
        this(new DirectoryString(string));
    }

    public DirectoryString getInformation() {
        return this.information;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.information.toASN1Primitive();
    }
}

