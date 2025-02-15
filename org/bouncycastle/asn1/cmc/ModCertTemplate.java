/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmc.BodyPartList;
import org.bouncycastle.asn1.cmc.BodyPartPath;
import org.bouncycastle.asn1.crmf.CertTemplate;

public class ModCertTemplate
extends ASN1Object {
    private final BodyPartPath pkiDataReference;
    private final BodyPartList certReferences;
    private final boolean replace;
    private final CertTemplate certTemplate;

    public ModCertTemplate(BodyPartPath bodyPartPath, BodyPartList bodyPartList, boolean bl, CertTemplate certTemplate) {
        this.pkiDataReference = bodyPartPath;
        this.certReferences = bodyPartList;
        this.replace = bl;
        this.certTemplate = certTemplate;
    }

    private ModCertTemplate(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.pkiDataReference = BodyPartPath.getInstance(aSN1Sequence.getObjectAt(0));
        this.certReferences = BodyPartList.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 4) {
            this.replace = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(2)).isTrue();
            this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(3));
        } else {
            this.replace = true;
            this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static ModCertTemplate getInstance(Object object) {
        if (object instanceof ModCertTemplate) {
            return (ModCertTemplate)object;
        }
        if (object != null) {
            return new ModCertTemplate(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public BodyPartPath getPkiDataReference() {
        return this.pkiDataReference;
    }

    public BodyPartList getCertReferences() {
        return this.certReferences;
    }

    public boolean isReplacingFields() {
        return this.replace;
    }

    public CertTemplate getCertTemplate() {
        return this.certTemplate;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiDataReference);
        aSN1EncodableVector.add(this.certReferences);
        if (!this.replace) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(this.replace));
        }
        aSN1EncodableVector.add(this.certTemplate);
        return new DERSequence(aSN1EncodableVector);
    }
}

