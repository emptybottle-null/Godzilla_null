/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x500;

import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x500.style.BCStyle;

public class X500NameBuilder {
    private X500NameStyle template;
    private Vector rdns = new Vector();

    public X500NameBuilder() {
        this(BCStyle.INSTANCE);
    }

    public X500NameBuilder(X500NameStyle x500NameStyle) {
        this.template = x500NameStyle;
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        this.addRDN(aSN1ObjectIdentifier, this.template.stringToValue(aSN1ObjectIdentifier, string));
        return this;
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.rdns.addElement(new RDN(aSN1ObjectIdentifier, aSN1Encodable));
        return this;
    }

    public X500NameBuilder addRDN(AttributeTypeAndValue attributeTypeAndValue) {
        this.rdns.addElement(new RDN(attributeTypeAndValue));
        return this;
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArray, String[] stringArray) {
        ASN1Encodable[] aSN1EncodableArray = new ASN1Encodable[stringArray.length];
        for (int i = 0; i != aSN1EncodableArray.length; ++i) {
            aSN1EncodableArray[i] = this.template.stringToValue(aSN1ObjectIdentifierArray[i], stringArray[i]);
        }
        return this.addMultiValuedRDN(aSN1ObjectIdentifierArray, aSN1EncodableArray);
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArray, ASN1Encodable[] aSN1EncodableArray) {
        AttributeTypeAndValue[] attributeTypeAndValueArray = new AttributeTypeAndValue[aSN1ObjectIdentifierArray.length];
        for (int i = 0; i != aSN1ObjectIdentifierArray.length; ++i) {
            attributeTypeAndValueArray[i] = new AttributeTypeAndValue(aSN1ObjectIdentifierArray[i], aSN1EncodableArray[i]);
        }
        return this.addMultiValuedRDN(attributeTypeAndValueArray);
    }

    public X500NameBuilder addMultiValuedRDN(AttributeTypeAndValue[] attributeTypeAndValueArray) {
        this.rdns.addElement(new RDN(attributeTypeAndValueArray));
        return this;
    }

    public X500Name build() {
        RDN[] rDNArray = new RDN[this.rdns.size()];
        for (int i = 0; i != rDNArray.length; ++i) {
            rDNArray[i] = (RDN)this.rdns.elementAt(i);
        }
        return new X500Name(this.template, rDNArray);
    }
}

