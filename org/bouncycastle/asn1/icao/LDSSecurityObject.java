/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.icao;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.icao.DataGroupHash;
import org.bouncycastle.asn1.icao.ICAOObjectIdentifiers;
import org.bouncycastle.asn1.icao.LDSVersionInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class LDSSecurityObject
extends ASN1Object
implements ICAOObjectIdentifiers {
    public static final int ub_DataGroups = 16;
    private ASN1Integer version = new ASN1Integer(0L);
    private AlgorithmIdentifier digestAlgorithmIdentifier;
    private DataGroupHash[] datagroupHash;
    private LDSVersionInfo versionInfo;

    public static LDSSecurityObject getInstance(Object object) {
        if (object instanceof LDSSecurityObject) {
            return (LDSSecurityObject)object;
        }
        if (object != null) {
            return new LDSSecurityObject(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private LDSSecurityObject(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            throw new IllegalArgumentException("null or empty sequence passed.");
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.version = ASN1Integer.getInstance(enumeration.nextElement());
        this.digestAlgorithmIdentifier = AlgorithmIdentifier.getInstance(enumeration.nextElement());
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(enumeration.nextElement());
        if (this.version.getValue().intValue() == 1) {
            this.versionInfo = LDSVersionInfo.getInstance(enumeration.nextElement());
        }
        this.checkDatagroupHashSeqSize(aSN1Sequence2.size());
        this.datagroupHash = new DataGroupHash[aSN1Sequence2.size()];
        for (int i = 0; i < aSN1Sequence2.size(); ++i) {
            this.datagroupHash[i] = DataGroupHash.getInstance(aSN1Sequence2.getObjectAt(i));
        }
    }

    public LDSSecurityObject(AlgorithmIdentifier algorithmIdentifier, DataGroupHash[] dataGroupHashArray) {
        this.version = new ASN1Integer(0L);
        this.digestAlgorithmIdentifier = algorithmIdentifier;
        this.datagroupHash = dataGroupHashArray;
        this.checkDatagroupHashSeqSize(dataGroupHashArray.length);
    }

    public LDSSecurityObject(AlgorithmIdentifier algorithmIdentifier, DataGroupHash[] dataGroupHashArray, LDSVersionInfo lDSVersionInfo) {
        this.version = new ASN1Integer(1L);
        this.digestAlgorithmIdentifier = algorithmIdentifier;
        this.datagroupHash = dataGroupHashArray;
        this.versionInfo = lDSVersionInfo;
        this.checkDatagroupHashSeqSize(dataGroupHashArray.length);
    }

    private void checkDatagroupHashSeqSize(int n) {
        if (n < 2 || n > 16) {
            throw new IllegalArgumentException("wrong size in DataGroupHashValues : not in (2..16)");
        }
    }

    public int getVersion() {
        return this.version.getValue().intValue();
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.digestAlgorithmIdentifier;
    }

    public DataGroupHash[] getDatagroupHash() {
        return this.datagroupHash;
    }

    public LDSVersionInfo getVersionInfo() {
        return this.versionInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.digestAlgorithmIdentifier);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i < this.datagroupHash.length; ++i) {
            aSN1EncodableVector2.add(this.datagroupHash[i]);
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        if (this.versionInfo != null) {
            aSN1EncodableVector.add(this.versionInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

