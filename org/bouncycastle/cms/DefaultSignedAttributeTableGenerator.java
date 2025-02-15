/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSAlgorithmProtection;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.cms.Time;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

public class DefaultSignedAttributeTableGenerator
implements CMSAttributeTableGenerator {
    private final Hashtable table;

    public DefaultSignedAttributeTableGenerator() {
        this.table = new Hashtable();
    }

    public DefaultSignedAttributeTableGenerator(AttributeTable attributeTable) {
        this.table = attributeTable != null ? attributeTable.toHashtable() : new Hashtable();
    }

    protected Hashtable createStandardAttributeTable(Map map) {
        Attribute attribute;
        Object object;
        Hashtable hashtable = DefaultSignedAttributeTableGenerator.copyHashTable(this.table);
        if (!hashtable.containsKey(CMSAttributes.contentType) && (object = ASN1ObjectIdentifier.getInstance(map.get("contentType"))) != null) {
            attribute = new Attribute(CMSAttributes.contentType, new DERSet((ASN1Encodable)object));
            hashtable.put(attribute.getAttrType(), attribute);
        }
        if (!hashtable.containsKey(CMSAttributes.signingTime)) {
            object = new Date();
            attribute = new Attribute(CMSAttributes.signingTime, new DERSet(new Time((Date)object)));
            hashtable.put(attribute.getAttrType(), attribute);
        }
        if (!hashtable.containsKey(CMSAttributes.messageDigest)) {
            object = (byte[])map.get("digest");
            attribute = new Attribute(CMSAttributes.messageDigest, new DERSet(new DEROctetString((byte[])object)));
            hashtable.put(attribute.getAttrType(), attribute);
        }
        if (!hashtable.contains(CMSAttributes.cmsAlgorithmProtect)) {
            object = new Attribute(CMSAttributes.cmsAlgorithmProtect, new DERSet(new CMSAlgorithmProtection((AlgorithmIdentifier)map.get("digestAlgID"), 1, (AlgorithmIdentifier)map.get("signatureAlgID"))));
            hashtable.put(((Attribute)object).getAttrType(), object);
        }
        return hashtable;
    }

    public AttributeTable getAttributes(Map map) {
        return new AttributeTable(this.createStandardAttributeTable(map));
    }

    private static Hashtable copyHashTable(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()) {
            Object k = enumeration.nextElement();
            hashtable2.put(k, hashtable.get(k));
        }
        return hashtable2;
    }
}

