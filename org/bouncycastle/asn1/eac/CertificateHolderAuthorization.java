/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.eac.BidirectionalMap;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.util.Integers;

public class CertificateHolderAuthorization
extends ASN1Object {
    ASN1ObjectIdentifier oid;
    DERApplicationSpecific accessRights;
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;
    public static final int IS = 0;
    public static final int RADG4 = 2;
    public static final int RADG3 = 1;
    static Hashtable RightsDecodeMap = new Hashtable();
    static BidirectionalMap AuthorizationRole = new BidirectionalMap();
    static Hashtable ReverseMap = new Hashtable();

    public static String getRoleDescription(int n) {
        return (String)AuthorizationRole.get(Integers.valueOf(n));
    }

    public static int getFlag(String string) {
        Integer n = (Integer)AuthorizationRole.getReverse(string);
        if (n == null) {
            throw new IllegalArgumentException("Unknown value " + string);
        }
        return n;
    }

    private void setPrivateData(ASN1InputStream aSN1InputStream) throws IOException {
        ASN1Primitive aSN1Primitive = aSN1InputStream.readObject();
        if (!(aSN1Primitive instanceof ASN1ObjectIdentifier)) {
            throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
        }
        this.oid = (ASN1ObjectIdentifier)aSN1Primitive;
        aSN1Primitive = aSN1InputStream.readObject();
        if (!(aSN1Primitive instanceof DERApplicationSpecific)) {
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        this.accessRights = (DERApplicationSpecific)aSN1Primitive;
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n) throws IOException {
        this.setOid(aSN1ObjectIdentifier);
        this.setAccessRights((byte)n);
    }

    public CertificateHolderAuthorization(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        if (dERApplicationSpecific.getApplicationTag() == 76) {
            this.setPrivateData(new ASN1InputStream(dERApplicationSpecific.getContents()));
        }
    }

    public int getAccessRights() {
        return this.accessRights.getContents()[0] & 0xFF;
    }

    private void setAccessRights(byte by) {
        byte[] byArray = new byte[]{by};
        this.accessRights = new DERApplicationSpecific(19, byArray);
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    private void setOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.oid = aSN1ObjectIdentifier;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oid);
        aSN1EncodableVector.add(this.accessRights);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }

    static {
        RightsDecodeMap.put(Integers.valueOf(2), "RADG4");
        RightsDecodeMap.put(Integers.valueOf(1), "RADG3");
        AuthorizationRole.put(Integers.valueOf(192), "CVCA");
        AuthorizationRole.put(Integers.valueOf(128), "DV_DOMESTIC");
        AuthorizationRole.put(Integers.valueOf(64), "DV_FOREIGN");
        AuthorizationRole.put(Integers.valueOf(0), "IS");
    }
}

