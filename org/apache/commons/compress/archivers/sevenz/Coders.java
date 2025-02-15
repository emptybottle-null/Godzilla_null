/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.tukaani.xz.ARMOptions
 *  org.tukaani.xz.ARMThumbOptions
 *  org.tukaani.xz.FilterOptions
 *  org.tukaani.xz.FinishableOutputStream
 *  org.tukaani.xz.FinishableWrapperOutputStream
 *  org.tukaani.xz.IA64Options
 *  org.tukaani.xz.PowerPCOptions
 *  org.tukaani.xz.SPARCOptions
 *  org.tukaani.xz.X86Options
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder;
import org.apache.commons.compress.archivers.sevenz.Coder;
import org.apache.commons.compress.archivers.sevenz.CoderBase;
import org.apache.commons.compress.archivers.sevenz.DeltaDecoder;
import org.apache.commons.compress.archivers.sevenz.LZMA2Decoder;
import org.apache.commons.compress.archivers.sevenz.LZMADecoder;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.FlushShieldFilterOutputStream;
import org.tukaani.xz.ARMOptions;
import org.tukaani.xz.ARMThumbOptions;
import org.tukaani.xz.FilterOptions;
import org.tukaani.xz.FinishableOutputStream;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.IA64Options;
import org.tukaani.xz.PowerPCOptions;
import org.tukaani.xz.SPARCOptions;
import org.tukaani.xz.X86Options;

class Coders {
    private static final Map<SevenZMethod, CoderBase> CODER_MAP = new HashMap<SevenZMethod, CoderBase>(){
        private static final long serialVersionUID = 1664829131806520867L;
        {
            this.put(SevenZMethod.COPY, new CopyDecoder());
            this.put(SevenZMethod.LZMA, new LZMADecoder());
            this.put(SevenZMethod.LZMA2, new LZMA2Decoder());
            this.put(SevenZMethod.DEFLATE, new DeflateDecoder());
            this.put(SevenZMethod.DEFLATE64, new Deflate64Decoder());
            this.put(SevenZMethod.BZIP2, new BZIP2Decoder());
            this.put(SevenZMethod.AES256SHA256, new AES256SHA256Decoder());
            this.put(SevenZMethod.BCJ_X86_FILTER, new BCJDecoder((FilterOptions)new X86Options()));
            this.put(SevenZMethod.BCJ_PPC_FILTER, new BCJDecoder((FilterOptions)new PowerPCOptions()));
            this.put(SevenZMethod.BCJ_IA64_FILTER, new BCJDecoder((FilterOptions)new IA64Options()));
            this.put(SevenZMethod.BCJ_ARM_FILTER, new BCJDecoder((FilterOptions)new ARMOptions()));
            this.put(SevenZMethod.BCJ_ARM_THUMB_FILTER, new BCJDecoder((FilterOptions)new ARMThumbOptions()));
            this.put(SevenZMethod.BCJ_SPARC_FILTER, new BCJDecoder((FilterOptions)new SPARCOptions()));
            this.put(SevenZMethod.DELTA_FILTER, new DeltaDecoder());
        }
    };

    Coders() {
    }

    static CoderBase findByMethod(SevenZMethod method) {
        return CODER_MAP.get((Object)method);
    }

    static InputStream addDecoder(String archiveName, InputStream is, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
        CoderBase cb = Coders.findByMethod(SevenZMethod.byId(coder.decompressionMethodId));
        if (cb == null) {
            throw new IOException("Unsupported compression method " + Arrays.toString(coder.decompressionMethodId) + " used in " + archiveName);
        }
        return cb.decode(archiveName, is, uncompressedLength, coder, password, maxMemoryLimitInKb);
    }

    static OutputStream addEncoder(OutputStream out, SevenZMethod method, Object options) throws IOException {
        CoderBase cb = Coders.findByMethod(method);
        if (cb == null) {
            throw new IOException("Unsupported compression method " + (Object)((Object)method));
        }
        return cb.encode(out, options);
    }

    static class BZIP2Decoder
    extends CoderBase {
        BZIP2Decoder() {
            super(Number.class);
        }

        @Override
        InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
            return new BZip2CompressorInputStream(in);
        }

        @Override
        OutputStream encode(OutputStream out, Object options) throws IOException {
            int blockSize = BZIP2Decoder.numberOptionOrDefault(options, 9);
            return new BZip2CompressorOutputStream(out, blockSize);
        }
    }

    static class Deflate64Decoder
    extends CoderBase {
        Deflate64Decoder() {
            super(Number.class);
        }

        @Override
        InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
            return new Deflate64CompressorInputStream(in);
        }
    }

    static class DeflateDecoder
    extends CoderBase {
        private static final byte[] ONE_ZERO_BYTE = new byte[1];

        DeflateDecoder() {
            super(Number.class);
        }

        @Override
        InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
            Inflater inflater = new Inflater(true);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(new SequenceInputStream(in, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater);
            return new DeflateDecoderInputStream(inflaterInputStream, inflater);
        }

        @Override
        OutputStream encode(OutputStream out, Object options) {
            int level = DeflateDecoder.numberOptionOrDefault(options, 9);
            Deflater deflater = new Deflater(level, true);
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(out, deflater);
            return new DeflateDecoderOutputStream(deflaterOutputStream, deflater);
        }

        static class DeflateDecoderOutputStream
        extends OutputStream {
            DeflaterOutputStream deflaterOutputStream;
            Deflater deflater;

            public DeflateDecoderOutputStream(DeflaterOutputStream deflaterOutputStream, Deflater deflater) {
                this.deflaterOutputStream = deflaterOutputStream;
                this.deflater = deflater;
            }

            @Override
            public void write(int b) throws IOException {
                this.deflaterOutputStream.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                this.deflaterOutputStream.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                this.deflaterOutputStream.write(b, off, len);
            }

            @Override
            public void close() throws IOException {
                try {
                    this.deflaterOutputStream.close();
                } finally {
                    this.deflater.end();
                }
            }
        }

        static class DeflateDecoderInputStream
        extends InputStream {
            InflaterInputStream inflaterInputStream;
            Inflater inflater;

            public DeflateDecoderInputStream(InflaterInputStream inflaterInputStream, Inflater inflater) {
                this.inflaterInputStream = inflaterInputStream;
                this.inflater = inflater;
            }

            @Override
            public int read() throws IOException {
                return this.inflaterInputStream.read();
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return this.inflaterInputStream.read(b, off, len);
            }

            @Override
            public int read(byte[] b) throws IOException {
                return this.inflaterInputStream.read(b);
            }

            @Override
            public void close() throws IOException {
                try {
                    this.inflaterInputStream.close();
                } finally {
                    this.inflater.end();
                }
            }
        }
    }

    static class BCJDecoder
    extends CoderBase {
        private final FilterOptions opts;

        BCJDecoder(FilterOptions opts) {
            super(new Class[0]);
            this.opts = opts;
        }

        @Override
        InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
            try {
                return this.opts.getInputStream(in);
            } catch (AssertionError e) {
                throw new IOException("BCJ filter used in " + archiveName + " needs XZ for Java > 1.4 - see https://commons.apache.org/proper/commons-compress/limitations.html#7Z", (Throwable)((Object)e));
            }
        }

        @Override
        OutputStream encode(OutputStream out, Object options) {
            return new FlushShieldFilterOutputStream((OutputStream)this.opts.getOutputStream((FinishableOutputStream)new FinishableWrapperOutputStream(out)));
        }
    }

    static class CopyDecoder
    extends CoderBase {
        CopyDecoder() {
            super(new Class[0]);
        }

        @Override
        InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitInKb) throws IOException {
            return in;
        }

        @Override
        OutputStream encode(OutputStream out, Object options) {
            return out;
        }
    }
}

