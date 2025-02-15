/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;

public abstract class AbstractX500NameStyle
implements X500NameStyle {
    public static Hashtable copyHashTable(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()) {
            Object k = enumeration.nextElement();
            hashtable2.put(k, hashtable.get(k));
        }
        return hashtable2;
    }

    private int calcHashCode(ASN1Encodable aSN1Encodable) {
        String string = IETFUtils.valueToString(aSN1Encodable);
        string = IETFUtils.canonicalize(string);
        return string.hashCode();
    }

    public int calculateHashCode(X500Name x500Name) {
        int n = 0;
        RDN[] rDNArray = x500Name.getRDNs();
        for (int i = 0; i != rDNArray.length; ++i) {
            if (rDNArray[i].isMultiValued()) {
                AttributeTypeAndValue[] attributeTypeAndValueArray = rDNArray[i].getTypesAndValues();
                for (int j = 0; j != attributeTypeAndValueArray.length; ++j) {
                    n ^= attributeTypeAndValueArray[j].getType().hashCode();
                    n ^= this.calcHashCode(attributeTypeAndValueArray[j].getValue());
                }
                continue;
            }
            n ^= rDNArray[i].getFirst().getType().hashCode();
            n ^= this.calcHashCode(rDNArray[i].getFirst().getValue());
        }
        return n;
    }

    public ASN1Encodable stringToValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        if (string.length() != 0 && string.charAt(0) == '#') {
            try {
                return IETFUtils.valueFromHexString(string, 1);
            } catch (IOException iOException) {
                throw new ASN1ParsingException("can't recode value for oid " + aSN1ObjectIdentifier.getId());
            }
        }
        if (string.length() != 0 && string.charAt(0) == '\\') {
            string = string.substring(1);
        }
        return this.encodeStringValue(aSN1ObjectIdentifier, string);
    }

    protected ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        return new DERUTF8String(string);
    }

    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        RDN[] rDNArray;
        RDN[] rDNArray2 = x500Name.getRDNs();
        if (rDNArray2.length != (rDNArray = x500Name2.getRDNs()).length) {
            return false;
        }
        boolean bl = false;
        if (rDNArray2[0].getFirst() != null && rDNArray[0].getFirst() != null) {
            bl = !rDNArray2[0].getFirst().getType().equals(rDNArray[0].getFirst().getType());
        }
        for (int i = 0; i != rDNArray2.length; ++i) {
            if (this.foundMatch(bl, rDNArray2[i], rDNArray)) continue;
            return false;
        }
        return true;
    }

    private boolean foundMatch(boolean bl, RDN rDN, RDN[] rDNArray) {
        if (bl) {
            for (int i = rDNArray.length - 1; i >= 0; --i) {
                if (rDNArray[i] == null || !this.rdnAreEqual(rDN, rDNArray[i])) continue;
                rDNArray[i] = null;
                return true;
            }
        } else {
            for (int i = 0; i != rDNArray.length; ++i) {
                if (rDNArray[i] == null || !this.rdnAreEqual(rDN, rDNArray[i])) continue;
                rDNArray[i] = null;
                return true;
            }
        }
        return false;
    }

    protected boolean rdnAreEqual(RDN rDN, RDN rDN2) {
        return IETFUtils.rDNAreEqual(rDN, rDN2);
    }
}

