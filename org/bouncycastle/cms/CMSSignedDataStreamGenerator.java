/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedGenerator;
import org.bouncycastle.cms.CMSSignedHelper;
import org.bouncycastle.cms.CMSStreamException;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInformation;

public class CMSSignedDataStreamGenerator
extends CMSSignedGenerator {
    private int _bufferSize;

    public void setBufferSize(int n) {
        this._bufferSize = n;
    }

    public OutputStream open(OutputStream outputStream) throws IOException {
        return this.open(outputStream, false);
    }

    public OutputStream open(OutputStream outputStream, boolean bl) throws IOException {
        return this.open(CMSObjectIdentifiers.data, outputStream, bl);
    }

    public OutputStream open(OutputStream outputStream, boolean bl, OutputStream outputStream2) throws IOException {
        return this.open(CMSObjectIdentifiers.data, outputStream, bl, outputStream2);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, boolean bl) throws IOException {
        return this.open(aSN1ObjectIdentifier, outputStream, bl, null);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, boolean bl, OutputStream outputStream2) throws IOException {
        Object object2;
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(this.calculateVersion(aSN1ObjectIdentifier));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (Object object2 : this._signers) {
            aSN1EncodableVector.add(CMSSignedHelper.INSTANCE.fixAlgID(((SignerInformation)object2).getDigestAlgorithmID()));
        }
        for (Object object2 : this.signerGens) {
            aSN1EncodableVector.add(((SignerInfoGenerator)object2).getDigestAlgorithm());
        }
        bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(aSN1ObjectIdentifier);
        object2 = bl ? CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, true, this._bufferSize) : null;
        OutputStream outputStream3 = CMSUtils.getSafeTeeOutputStream(outputStream2, (OutputStream)object2);
        OutputStream outputStream4 = CMSUtils.attachSignersToOutputStream(this.signerGens, outputStream3);
        return new CmsSignedDataOutputStream(outputStream4, aSN1ObjectIdentifier, bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    private ASN1Integer calculateVersion(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        if (this.certs != null) {
            for (Object e : this.certs) {
                if (!(e instanceof ASN1TaggedObject)) continue;
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)e;
                if (aSN1TaggedObject.getTagNo() == 1) {
                    bl3 = true;
                    continue;
                }
                if (aSN1TaggedObject.getTagNo() == 2) {
                    bl4 = true;
                    continue;
                }
                if (aSN1TaggedObject.getTagNo() != 3) continue;
                bl = true;
            }
        }
        if (bl) {
            return new ASN1Integer(5L);
        }
        if (this.crls != null) {
            for (Object e : this.crls) {
                if (!(e instanceof ASN1TaggedObject)) continue;
                bl2 = true;
            }
        }
        if (bl2) {
            return new ASN1Integer(5L);
        }
        if (bl4) {
            return new ASN1Integer(4L);
        }
        if (bl3) {
            return new ASN1Integer(3L);
        }
        if (this.checkForVersion3(this._signers, this.signerGens)) {
            return new ASN1Integer(3L);
        }
        if (!CMSObjectIdentifiers.data.equals(aSN1ObjectIdentifier)) {
            return new ASN1Integer(3L);
        }
        return new ASN1Integer(1L);
    }

    private boolean checkForVersion3(List list, List list2) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object object = SignerInfo.getInstance(((SignerInformation)iterator.next()).toASN1Structure());
            if (((SignerInfo)object).getVersion().getValue().intValue() != 3) continue;
            return true;
        }
        for (Object object : list2) {
            if (((SignerInfoGenerator)object).getGeneratedVersion() != 3) continue;
            return true;
        }
        return false;
    }

    private class CmsSignedDataOutputStream
    extends OutputStream {
        private OutputStream _out;
        private ASN1ObjectIdentifier _contentOID;
        private BERSequenceGenerator _sGen;
        private BERSequenceGenerator _sigGen;
        private BERSequenceGenerator _eiGen;

        public CmsSignedDataOutputStream(OutputStream outputStream, ASN1ObjectIdentifier aSN1ObjectIdentifier, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this._out = outputStream;
            this._contentOID = aSN1ObjectIdentifier;
            this._sGen = bERSequenceGenerator;
            this._sigGen = bERSequenceGenerator2;
            this._eiGen = bERSequenceGenerator3;
        }

        public void write(int n) throws IOException {
            this._out.write(n);
        }

        public void write(byte[] byArray, int n, int n2) throws IOException {
            this._out.write(byArray, n, n2);
        }

        public void write(byte[] byArray) throws IOException {
            this._out.write(byArray);
        }

        public void close() throws IOException {
            Object object;
            this._out.close();
            this._eiGen.close();
            CMSSignedDataStreamGenerator.this.digests.clear();
            if (CMSSignedDataStreamGenerator.this.certs.size() != 0) {
                object = CMSUtils.createBerSetFromList(CMSSignedDataStreamGenerator.this.certs);
                this._sigGen.getRawOutputStream().write(new BERTaggedObject(false, 0, (ASN1Encodable)object).getEncoded());
            }
            if (CMSSignedDataStreamGenerator.this.crls.size() != 0) {
                object = CMSUtils.createBerSetFromList(CMSSignedDataStreamGenerator.this.crls);
                this._sigGen.getRawOutputStream().write(new BERTaggedObject(false, 1, (ASN1Encodable)object).getEncoded());
            }
            object = new ASN1EncodableVector();
            for (Object object2 : CMSSignedDataStreamGenerator.this.signerGens) {
                try {
                    ((ASN1EncodableVector)object).add(((SignerInfoGenerator)object2).generate(this._contentOID));
                    byte[] byArray = ((SignerInfoGenerator)object2).getCalculatedDigest();
                    CMSSignedDataStreamGenerator.this.digests.put(((SignerInfoGenerator)object2).getDigestAlgorithm().getAlgorithm().getId(), byArray);
                } catch (CMSException cMSException) {
                    throw new CMSStreamException("exception generating signers: " + cMSException.getMessage(), cMSException);
                }
            }
            for (Object object2 : CMSSignedDataStreamGenerator.this._signers) {
                ((ASN1EncodableVector)object).add(((SignerInformation)object2).toASN1Structure());
            }
            this._sigGen.getRawOutputStream().write(new DERSet((ASN1EncodableVector)object).getEncoded());
            this._sigGen.close();
            this._sGen.close();
        }
    }
}

