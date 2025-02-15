/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;

public class CMSEnvelopedDataStreamGenerator
extends CMSEnvelopedGenerator {
    private ASN1Set _unprotectedAttributes = null;
    private int _bufferSize;
    private boolean _berEncodeRecipientSet;

    public void setBufferSize(int n) {
        this._bufferSize = n;
    }

    public void setBEREncodeRecipients(boolean bl) {
        this._berEncodeRecipientSet = bl;
    }

    private ASN1Integer getVersion() {
        if (this.originatorInfo != null || this._unprotectedAttributes != null) {
            return new ASN1Integer(2L);
        }
        return new ASN1Integer(0L);
    }

    private OutputStream doOpen(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GenericKey genericKey = outputEncryptor.getKey();
        for (RecipientInfoGenerator recipientInfoGenerator : this.recipientInfoGenerators) {
            aSN1EncodableVector.add(recipientInfoGenerator.generate(genericKey));
        }
        return this.open(aSN1ObjectIdentifier, outputStream, aSN1EncodableVector, outputEncryptor);
    }

    protected OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws IOException {
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.envelopedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(this.getVersion());
        if (this.originatorInfo != null) {
            bERSequenceGenerator2.addObject(new DERTaggedObject(false, 0, this.originatorInfo));
        }
        if (this._berEncodeRecipientSet) {
            bERSequenceGenerator2.getRawOutputStream().write(new BERSet(aSN1EncodableVector).getEncoded());
        } else {
            bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        }
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(aSN1ObjectIdentifier);
        AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
        bERSequenceGenerator3.getRawOutputStream().write(algorithmIdentifier.getEncoded());
        OutputStream outputStream2 = CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize);
        OutputStream outputStream3 = outputEncryptor.getOutputStream(outputStream2);
        return new CmsEnvelopedDataOutputStream(outputStream3, bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    protected OutputStream open(OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws CMSException {
        try {
            BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
            bERSequenceGenerator.addObject(CMSObjectIdentifiers.envelopedData);
            BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
            ASN1Set aSN1Set = this._berEncodeRecipientSet ? new BERSet(aSN1EncodableVector) : new DERSet(aSN1EncodableVector);
            bERSequenceGenerator2.addObject(new ASN1Integer(EnvelopedData.calculateVersion(this.originatorInfo, aSN1Set, this._unprotectedAttributes)));
            if (this.originatorInfo != null) {
                bERSequenceGenerator2.addObject(new DERTaggedObject(false, 0, this.originatorInfo));
            }
            bERSequenceGenerator2.getRawOutputStream().write(aSN1Set.getEncoded());
            BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
            bERSequenceGenerator3.addObject(CMSObjectIdentifiers.data);
            AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
            bERSequenceGenerator3.getRawOutputStream().write(algorithmIdentifier.getEncoded());
            OutputStream outputStream2 = CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize);
            return new CmsEnvelopedDataOutputStream(outputEncryptor.getOutputStream(outputStream2), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
        } catch (IOException iOException) {
            throw new CMSException("exception decoding algorithm parameters.", iOException);
        }
    }

    public OutputStream open(OutputStream outputStream, OutputEncryptor outputEncryptor) throws CMSException, IOException {
        return this.doOpen(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), outputStream, outputEncryptor);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws CMSException, IOException {
        return this.doOpen(aSN1ObjectIdentifier, outputStream, outputEncryptor);
    }

    private class CmsEnvelopedDataOutputStream
    extends OutputStream {
        private OutputStream _out;
        private BERSequenceGenerator _cGen;
        private BERSequenceGenerator _envGen;
        private BERSequenceGenerator _eiGen;

        public CmsEnvelopedDataOutputStream(OutputStream outputStream, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this._out = outputStream;
            this._cGen = bERSequenceGenerator;
            this._envGen = bERSequenceGenerator2;
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
            this._out.close();
            this._eiGen.close();
            if (CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator != null) {
                AttributeTable attributeTable = CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator.getAttributes(new HashMap());
                BERSet bERSet = new BERSet(attributeTable.toASN1EncodableVector());
                this._envGen.addObject(new DERTaggedObject(false, 1, bERSet));
            }
            this._envGen.close();
            this._cGen.close();
        }
    }
}

