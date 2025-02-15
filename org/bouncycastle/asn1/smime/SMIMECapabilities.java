/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.smime;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.smime.SMIMECapability;

public class SMIMECapabilities
extends ASN1Object {
    public static final ASN1ObjectIdentifier preferSignedData = PKCSObjectIdentifiers.preferSignedData;
    public static final ASN1ObjectIdentifier canNotDecryptAny = PKCSObjectIdentifiers.canNotDecryptAny;
    public static final ASN1ObjectIdentifier sMIMECapabilitesVersions = PKCSObjectIdentifiers.sMIMECapabilitiesVersions;
    public static final ASN1ObjectIdentifier aes256_CBC = NISTObjectIdentifiers.id_aes256_CBC;
    public static final ASN1ObjectIdentifier aes192_CBC = NISTObjectIdentifiers.id_aes192_CBC;
    public static final ASN1ObjectIdentifier aes128_CBC = NISTObjectIdentifiers.id_aes128_CBC;
    public static final ASN1ObjectIdentifier idea_CBC = new ASN1ObjectIdentifier("1.3.6.1.4.1.188.7.1.1.2");
    public static final ASN1ObjectIdentifier cast5_CBC = new ASN1ObjectIdentifier("1.2.840.113533.7.66.10");
    public static final ASN1ObjectIdentifier dES_CBC = new ASN1ObjectIdentifier("1.3.14.3.2.7");
    public static final ASN1ObjectIdentifier dES_EDE3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC;
    public static final ASN1ObjectIdentifier rC2_CBC = PKCSObjectIdentifiers.RC2_CBC;
    private ASN1Sequence capabilities;

    public static SMIMECapabilities getInstance(Object object) {
        if (object == null || object instanceof SMIMECapabilities) {
            return (SMIMECapabilities)object;
        }
        if (object instanceof ASN1Sequence) {
            return new SMIMECapabilities((ASN1Sequence)object);
        }
        if (object instanceof Attribute) {
            return new SMIMECapabilities((ASN1Sequence)((Attribute)object).getAttrValues().getObjectAt(0));
        }
        throw new IllegalArgumentException("unknown object in factory: " + object.getClass().getName());
    }

    public SMIMECapabilities(ASN1Sequence aSN1Sequence) {
        this.capabilities = aSN1Sequence;
    }

    public Vector getCapabilities(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Enumeration enumeration = this.capabilities.getObjects();
        Vector<SMIMECapability> vector = new Vector<SMIMECapability>();
        if (aSN1ObjectIdentifier == null) {
            while (enumeration.hasMoreElements()) {
                SMIMECapability sMIMECapability = SMIMECapability.getInstance(enumeration.nextElement());
                vector.addElement(sMIMECapability);
            }
        } else {
            while (enumeration.hasMoreElements()) {
                SMIMECapability sMIMECapability = SMIMECapability.getInstance(enumeration.nextElement());
                if (!aSN1ObjectIdentifier.equals(sMIMECapability.getCapabilityID())) continue;
                vector.addElement(sMIMECapability);
            }
        }
        return vector;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.capabilities;
    }
}

