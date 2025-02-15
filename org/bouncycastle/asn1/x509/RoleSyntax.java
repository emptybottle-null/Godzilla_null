/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

public class RoleSyntax
extends ASN1Object {
    private GeneralNames roleAuthority;
    private GeneralName roleName;

    public static RoleSyntax getInstance(Object object) {
        if (object instanceof RoleSyntax) {
            return (RoleSyntax)object;
        }
        if (object != null) {
            return new RoleSyntax(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public RoleSyntax(GeneralNames generalNames, GeneralName generalName) {
        if (generalName == null || generalName.getTagNo() != 6 || ((ASN1String)((Object)generalName.getName())).getString().equals("")) {
            throw new IllegalArgumentException("the role name MUST be non empty and MUST use the URI option of GeneralName");
        }
        this.roleAuthority = generalNames;
        this.roleName = generalName;
    }

    public RoleSyntax(GeneralName generalName) {
        this(null, generalName);
    }

    public RoleSyntax(String string) {
        this(new GeneralName(6, string == null ? "" : string));
    }

    private RoleSyntax(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        block4: for (int i = 0; i != aSN1Sequence.size(); ++i) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.roleAuthority = GeneralNames.getInstance(aSN1TaggedObject, false);
                    continue block4;
                }
                case 1: {
                    this.roleName = GeneralName.getInstance(aSN1TaggedObject, true);
                    continue block4;
                }
                default: {
                    throw new IllegalArgumentException("Unknown tag in RoleSyntax");
                }
            }
        }
    }

    public GeneralNames getRoleAuthority() {
        return this.roleAuthority;
    }

    public GeneralName getRoleName() {
        return this.roleName;
    }

    public String getRoleNameAsString() {
        ASN1String aSN1String = (ASN1String)((Object)this.roleName.getName());
        return aSN1String.getString();
    }

    public String[] getRoleAuthorityAsString() {
        if (this.roleAuthority == null) {
            return new String[0];
        }
        GeneralName[] generalNameArray = this.roleAuthority.getNames();
        String[] stringArray = new String[generalNameArray.length];
        for (int i = 0; i < generalNameArray.length; ++i) {
            ASN1Encodable aSN1Encodable = generalNameArray[i].getName();
            stringArray[i] = aSN1Encodable instanceof ASN1String ? ((ASN1String)((Object)aSN1Encodable)).getString() : aSN1Encodable.toString();
        }
        return stringArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.roleAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.roleAuthority));
        }
        aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.roleName));
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Name: " + this.getRoleNameAsString() + " - Auth: ");
        if (this.roleAuthority == null || this.roleAuthority.getNames().length == 0) {
            stringBuffer.append("N/A");
        } else {
            String[] stringArray = this.getRoleAuthorityAsString();
            stringBuffer.append('[').append(stringArray[0]);
            for (int i = 1; i < stringArray.length; ++i) {
                stringBuffer.append(", ").append(stringArray[i]);
            }
            stringBuffer.append(']');
        }
        return stringBuffer.toString();
    }
}

