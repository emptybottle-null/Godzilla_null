/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.BERSequenceGenerator;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.operator.OutputCompressor;

public class CMSCompressedDataStreamGenerator {
    public static final String ZLIB = "1.2.840.113549.1.9.16.3.8";
    private int _bufferSize;

    public void setBufferSize(int n) {
        this._bufferSize = n;
    }

    public OutputStream open(OutputStream outputStream, OutputCompressor outputCompressor) throws IOException {
        return this.open(CMSObjectIdentifiers.data, outputStream, outputCompressor);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputCompressor outputCompressor) throws IOException {
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.compressedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(new ASN1Integer(0L));
        bERSequenceGenerator2.addObject(outputCompressor.getAlgorithmIdentifier());
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(aSN1ObjectIdentifier);
        OutputStream outputStream2 = CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, true, this._bufferSize);
        return new CmsCompressedOutputStream(outputCompressor.getOutputStream(outputStream2), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    private class CmsCompressedOutputStream
    extends OutputStream {
        private OutputStream _out;
        private BERSequenceGenerator _sGen;
        private BERSequenceGenerator _cGen;
        private BERSequenceGenerator _eiGen;

        CmsCompressedOutputStream(OutputStream outputStream, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this._out = outputStream;
            this._sGen = bERSequenceGenerator;
            this._cGen = bERSequenceGenerator2;
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
            this._cGen.close();
            this._sGen.close();
        }
    }
}

