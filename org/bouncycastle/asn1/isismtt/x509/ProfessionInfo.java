/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.isismtt.x509.NamingAuthority;
import org.bouncycastle.asn1.x500.DirectoryString;

public class ProfessionInfo
extends ASN1Object {
    public static final ASN1ObjectIdentifier Rechtsanwltin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".1");
    public static final ASN1ObjectIdentifier Rechtsanwalt = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".2");
    public static final ASN1ObjectIdentifier Rechtsbeistand = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".3");
    public static final ASN1ObjectIdentifier Steuerberaterin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".4");
    public static final ASN1ObjectIdentifier Steuerberater = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".5");
    public static final ASN1ObjectIdentifier Steuerbevollmchtigte = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".6");
    public static final ASN1ObjectIdentifier Steuerbevollmchtigter = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".7");
    public static final ASN1ObjectIdentifier Notarin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".8");
    public static final ASN1ObjectIdentifier Notar = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".9");
    public static final ASN1ObjectIdentifier Notarvertreterin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".10");
    public static final ASN1ObjectIdentifier Notarvertreter = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".11");
    public static final ASN1ObjectIdentifier Notariatsverwalterin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".12");
    public static final ASN1ObjectIdentifier Notariatsverwalter = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".13");
    public static final ASN1ObjectIdentifier Wirtschaftsprferin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".14");
    public static final ASN1ObjectIdentifier Wirtschaftsprfer = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".15");
    public static final ASN1ObjectIdentifier VereidigteBuchprferin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".16");
    public static final ASN1ObjectIdentifier VereidigterBuchprfer = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".17");
    public static final ASN1ObjectIdentifier Patentanwltin = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".18");
    public static final ASN1ObjectIdentifier Patentanwalt = new ASN1ObjectIdentifier(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern + ".19");
    private NamingAuthority namingAuthority;
    private ASN1Sequence professionItems;
    private ASN1Sequence professionOIDs;
    private String registrationNumber;
    private ASN1OctetString addProfessionInfo;

    public static ProfessionInfo getInstance(Object object) {
        if (object == null || object instanceof ProfessionInfo) {
            return (ProfessionInfo)object;
        }
        if (object instanceof ASN1Sequence) {
            return new ProfessionInfo((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    private ProfessionInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            if (((ASN1TaggedObject)aSN1Encodable).getTagNo() != 0) {
                throw new IllegalArgumentException("Bad tag number: " + ((ASN1TaggedObject)aSN1Encodable).getTagNo());
            }
            this.namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)aSN1Encodable, true);
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
        }
        this.professionItems = ASN1Sequence.getInstance(aSN1Encodable);
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof ASN1Sequence) {
                this.professionOIDs = ASN1Sequence.getInstance(aSN1Encodable);
            } else if (aSN1Encodable instanceof DERPrintableString) {
                this.registrationNumber = DERPrintableString.getInstance(aSN1Encodable).getString();
            } else if (aSN1Encodable instanceof ASN1OctetString) {
                this.addProfessionInfo = ASN1OctetString.getInstance(aSN1Encodable);
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof DERPrintableString) {
                this.registrationNumber = DERPrintableString.getInstance(aSN1Encodable).getString();
            } else if (aSN1Encodable instanceof DEROctetString) {
                this.addProfessionInfo = (DEROctetString)aSN1Encodable;
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
        if (enumeration.hasMoreElements()) {
            aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof DEROctetString) {
                this.addProfessionInfo = (DEROctetString)aSN1Encodable;
            } else {
                throw new IllegalArgumentException("Bad object encountered: " + aSN1Encodable.getClass());
            }
        }
    }

    public ProfessionInfo(NamingAuthority namingAuthority, DirectoryString[] directoryStringArray, ASN1ObjectIdentifier[] aSN1ObjectIdentifierArray, String string, ASN1OctetString aSN1OctetString) {
        int n;
        this.namingAuthority = namingAuthority;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (n = 0; n != directoryStringArray.length; ++n) {
            aSN1EncodableVector.add(directoryStringArray[n]);
        }
        this.professionItems = new DERSequence(aSN1EncodableVector);
        if (aSN1ObjectIdentifierArray != null) {
            aSN1EncodableVector = new ASN1EncodableVector();
            for (n = 0; n != aSN1ObjectIdentifierArray.length; ++n) {
                aSN1EncodableVector.add(aSN1ObjectIdentifierArray[n]);
            }
            this.professionOIDs = new DERSequence(aSN1EncodableVector);
        }
        this.registrationNumber = string;
        this.addProfessionInfo = aSN1OctetString;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.namingAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.namingAuthority));
        }
        aSN1EncodableVector.add(this.professionItems);
        if (this.professionOIDs != null) {
            aSN1EncodableVector.add(this.professionOIDs);
        }
        if (this.registrationNumber != null) {
            aSN1EncodableVector.add(new DERPrintableString(this.registrationNumber, true));
        }
        if (this.addProfessionInfo != null) {
            aSN1EncodableVector.add(this.addProfessionInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public ASN1OctetString getAddProfessionInfo() {
        return this.addProfessionInfo;
    }

    public NamingAuthority getNamingAuthority() {
        return this.namingAuthority;
    }

    public DirectoryString[] getProfessionItems() {
        DirectoryString[] directoryStringArray = new DirectoryString[this.professionItems.size()];
        int n = 0;
        Enumeration enumeration = this.professionItems.getObjects();
        while (enumeration.hasMoreElements()) {
            directoryStringArray[n++] = DirectoryString.getInstance(enumeration.nextElement());
        }
        return directoryStringArray;
    }

    public ASN1ObjectIdentifier[] getProfessionOIDs() {
        if (this.professionOIDs == null) {
            return new ASN1ObjectIdentifier[0];
        }
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArray = new ASN1ObjectIdentifier[this.professionOIDs.size()];
        int n = 0;
        Enumeration enumeration = this.professionOIDs.getObjects();
        while (enumeration.hasMoreElements()) {
            aSN1ObjectIdentifierArray[n++] = ASN1ObjectIdentifier.getInstance(enumeration.nextElement());
        }
        return aSN1ObjectIdentifierArray;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }
}

