/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamProvider;
import org.apache.commons.compress.compressors.brotli.BrotliCompressorInputStream;
import org.apache.commons.compress.compressors.brotli.BrotliUtils;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMAUtils;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZUtils;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.ServiceLoaderIterator;
import org.apache.commons.compress.utils.Sets;

public class CompressorStreamFactory
implements CompressorStreamProvider {
    private static final CompressorStreamFactory SINGLETON = new CompressorStreamFactory();
    public static final String BROTLI = "br";
    public static final String BZIP2 = "bzip2";
    public static final String GZIP = "gz";
    public static final String PACK200 = "pack200";
    public static final String XZ = "xz";
    public static final String LZMA = "lzma";
    public static final String SNAPPY_FRAMED = "snappy-framed";
    public static final String SNAPPY_RAW = "snappy-raw";
    public static final String Z = "z";
    public static final String DEFLATE = "deflate";
    public static final String DEFLATE64 = "deflate64";
    public static final String LZ4_BLOCK = "lz4-block";
    public static final String LZ4_FRAMED = "lz4-framed";
    public static final String ZSTANDARD = "zstd";
    private static final String YOU_NEED_BROTLI_DEC = CompressorStreamFactory.youNeed("Google Brotli Dec", "https://github.com/google/brotli/");
    private static final String YOU_NEED_XZ_JAVA = CompressorStreamFactory.youNeed("XZ for Java", "https://tukaani.org/xz/java.html");
    private static final String YOU_NEED_ZSTD_JNI = CompressorStreamFactory.youNeed("Zstd JNI", "https://github.com/luben/zstd-jni");
    private final Boolean decompressUntilEOF;
    private SortedMap<String, CompressorStreamProvider> compressorInputStreamProviders;
    private SortedMap<String, CompressorStreamProvider> compressorOutputStreamProviders;
    private volatile boolean decompressConcatenated = false;
    private final int memoryLimitInKb;

    private static String youNeed(String name, String url) {
        return " In addition to Apache Commons Compress you need the " + name + " library - see " + url;
    }

    public static SortedMap<String, CompressorStreamProvider> findAvailableCompressorInputStreamProviders() {
        return AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, CompressorStreamProvider>>(){

            @Override
            public SortedMap<String, CompressorStreamProvider> run() {
                TreeMap<String, CompressorStreamProvider> map = new TreeMap<String, CompressorStreamProvider>();
                CompressorStreamFactory.putAll(SINGLETON.getInputStreamCompressorNames(), SINGLETON, map);
                for (CompressorStreamProvider provider : CompressorStreamFactory.findCompressorStreamProviders()) {
                    CompressorStreamFactory.putAll(provider.getInputStreamCompressorNames(), provider, map);
                }
                return map;
            }
        });
    }

    public static SortedMap<String, CompressorStreamProvider> findAvailableCompressorOutputStreamProviders() {
        return AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, CompressorStreamProvider>>(){

            @Override
            public SortedMap<String, CompressorStreamProvider> run() {
                TreeMap<String, CompressorStreamProvider> map = new TreeMap<String, CompressorStreamProvider>();
                CompressorStreamFactory.putAll(SINGLETON.getOutputStreamCompressorNames(), SINGLETON, map);
                for (CompressorStreamProvider provider : CompressorStreamFactory.findCompressorStreamProviders()) {
                    CompressorStreamFactory.putAll(provider.getOutputStreamCompressorNames(), provider, map);
                }
                return map;
            }
        });
    }

    private static ArrayList<CompressorStreamProvider> findCompressorStreamProviders() {
        return Lists.newArrayList(CompressorStreamFactory.serviceLoaderIterator());
    }

    public static String getBrotli() {
        return BROTLI;
    }

    public static String getBzip2() {
        return BZIP2;
    }

    public static String getDeflate() {
        return DEFLATE;
    }

    public static String getDeflate64() {
        return DEFLATE64;
    }

    public static String getGzip() {
        return GZIP;
    }

    public static String getLzma() {
        return LZMA;
    }

    public static String getPack200() {
        return PACK200;
    }

    public static CompressorStreamFactory getSingleton() {
        return SINGLETON;
    }

    public static String getSnappyFramed() {
        return SNAPPY_FRAMED;
    }

    public static String getSnappyRaw() {
        return SNAPPY_RAW;
    }

    public static String getXz() {
        return XZ;
    }

    public static String getZ() {
        return Z;
    }

    public static String getLZ4Framed() {
        return LZ4_FRAMED;
    }

    public static String getLZ4Block() {
        return LZ4_BLOCK;
    }

    public static String getZstandard() {
        return ZSTANDARD;
    }

    static void putAll(Set<String> names, CompressorStreamProvider provider, TreeMap<String, CompressorStreamProvider> map) {
        for (String name : names) {
            map.put(CompressorStreamFactory.toKey(name), provider);
        }
    }

    private static Iterator<CompressorStreamProvider> serviceLoaderIterator() {
        return new ServiceLoaderIterator<CompressorStreamProvider>(CompressorStreamProvider.class);
    }

    private static String toKey(String name) {
        return name.toUpperCase(Locale.ROOT);
    }

    public CompressorStreamFactory() {
        this.decompressUntilEOF = null;
        this.memoryLimitInKb = -1;
    }

    public CompressorStreamFactory(boolean decompressUntilEOF, int memoryLimitInKb) {
        this.decompressUntilEOF = decompressUntilEOF;
        this.decompressConcatenated = decompressUntilEOF;
        this.memoryLimitInKb = memoryLimitInKb;
    }

    public CompressorStreamFactory(boolean decompressUntilEOF) {
        this(decompressUntilEOF, -1);
    }

    public static String detect(InputStream in) throws CompressorException {
        if (in == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        }
        if (!in.markSupported()) {
            throw new IllegalArgumentException("Mark is not supported.");
        }
        byte[] signature = new byte[12];
        in.mark(signature.length);
        int signatureLength = -1;
        try {
            signatureLength = IOUtils.readFully(in, signature);
            in.reset();
        } catch (IOException e) {
            throw new CompressorException("IOException while reading signature.", e);
        }
        if (BZip2CompressorInputStream.matches(signature, signatureLength)) {
            return BZIP2;
        }
        if (GzipCompressorInputStream.matches(signature, signatureLength)) {
            return GZIP;
        }
        if (Pack200CompressorInputStream.matches(signature, signatureLength)) {
            return PACK200;
        }
        if (FramedSnappyCompressorInputStream.matches(signature, signatureLength)) {
            return SNAPPY_FRAMED;
        }
        if (ZCompressorInputStream.matches(signature, signatureLength)) {
            return Z;
        }
        if (DeflateCompressorInputStream.matches(signature, signatureLength)) {
            return DEFLATE;
        }
        if (XZUtils.matches(signature, signatureLength)) {
            return XZ;
        }
        if (LZMAUtils.matches(signature, signatureLength)) {
            return LZMA;
        }
        if (FramedLZ4CompressorInputStream.matches(signature, signatureLength)) {
            return LZ4_FRAMED;
        }
        if (ZstdUtils.matches(signature, signatureLength)) {
            return ZSTANDARD;
        }
        throw new CompressorException("No Compressor found for the stream signature.");
    }

    public CompressorInputStream createCompressorInputStream(InputStream in) throws CompressorException {
        return this.createCompressorInputStream(CompressorStreamFactory.detect(in), in);
    }

    public CompressorInputStream createCompressorInputStream(String name, InputStream in) throws CompressorException {
        return this.createCompressorInputStream(name, in, this.decompressConcatenated);
    }

    @Override
    public CompressorInputStream createCompressorInputStream(String name, InputStream in, boolean actualDecompressConcatenated) throws CompressorException {
        if (name == null || in == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (GZIP.equalsIgnoreCase(name)) {
                return new GzipCompressorInputStream(in, actualDecompressConcatenated);
            }
            if (BZIP2.equalsIgnoreCase(name)) {
                return new BZip2CompressorInputStream(in, actualDecompressConcatenated);
            }
            if (BROTLI.equalsIgnoreCase(name)) {
                if (!BrotliUtils.isBrotliCompressionAvailable()) {
                    throw new CompressorException("Brotli compression is not available." + YOU_NEED_BROTLI_DEC);
                }
                return new BrotliCompressorInputStream(in);
            }
            if (XZ.equalsIgnoreCase(name)) {
                if (!XZUtils.isXZCompressionAvailable()) {
                    throw new CompressorException("XZ compression is not available." + YOU_NEED_XZ_JAVA);
                }
                return new XZCompressorInputStream(in, actualDecompressConcatenated, this.memoryLimitInKb);
            }
            if (ZSTANDARD.equalsIgnoreCase(name)) {
                if (!ZstdUtils.isZstdCompressionAvailable()) {
                    throw new CompressorException("Zstandard compression is not available." + YOU_NEED_ZSTD_JNI);
                }
                return new ZstdCompressorInputStream(in);
            }
            if (LZMA.equalsIgnoreCase(name)) {
                if (!LZMAUtils.isLZMACompressionAvailable()) {
                    throw new CompressorException("LZMA compression is not available" + YOU_NEED_XZ_JAVA);
                }
                return new LZMACompressorInputStream(in, this.memoryLimitInKb);
            }
            if (PACK200.equalsIgnoreCase(name)) {
                return new Pack200CompressorInputStream(in);
            }
            if (SNAPPY_RAW.equalsIgnoreCase(name)) {
                return new SnappyCompressorInputStream(in);
            }
            if (SNAPPY_FRAMED.equalsIgnoreCase(name)) {
                return new FramedSnappyCompressorInputStream(in);
            }
            if (Z.equalsIgnoreCase(name)) {
                return new ZCompressorInputStream(in, this.memoryLimitInKb);
            }
            if (DEFLATE.equalsIgnoreCase(name)) {
                return new DeflateCompressorInputStream(in);
            }
            if (DEFLATE64.equalsIgnoreCase(name)) {
                return new Deflate64CompressorInputStream(in);
            }
            if (LZ4_BLOCK.equalsIgnoreCase(name)) {
                return new BlockLZ4CompressorInputStream(in);
            }
            if (LZ4_FRAMED.equalsIgnoreCase(name)) {
                return new FramedLZ4CompressorInputStream(in, actualDecompressConcatenated);
            }
        } catch (IOException e) {
            throw new CompressorException("Could not create CompressorInputStream.", e);
        }
        CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider)this.getCompressorInputStreamProviders().get(CompressorStreamFactory.toKey(name));
        if (compressorStreamProvider != null) {
            return compressorStreamProvider.createCompressorInputStream(name, in, actualDecompressConcatenated);
        }
        throw new CompressorException("Compressor: " + name + " not found.");
    }

    @Override
    public CompressorOutputStream createCompressorOutputStream(String name, OutputStream out) throws CompressorException {
        if (name == null || out == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (GZIP.equalsIgnoreCase(name)) {
                return new GzipCompressorOutputStream(out);
            }
            if (BZIP2.equalsIgnoreCase(name)) {
                return new BZip2CompressorOutputStream(out);
            }
            if (XZ.equalsIgnoreCase(name)) {
                return new XZCompressorOutputStream(out);
            }
            if (PACK200.equalsIgnoreCase(name)) {
                return new Pack200CompressorOutputStream(out);
            }
            if (LZMA.equalsIgnoreCase(name)) {
                return new LZMACompressorOutputStream(out);
            }
            if (DEFLATE.equalsIgnoreCase(name)) {
                return new DeflateCompressorOutputStream(out);
            }
            if (SNAPPY_FRAMED.equalsIgnoreCase(name)) {
                return new FramedSnappyCompressorOutputStream(out);
            }
            if (LZ4_BLOCK.equalsIgnoreCase(name)) {
                return new BlockLZ4CompressorOutputStream(out);
            }
            if (LZ4_FRAMED.equalsIgnoreCase(name)) {
                return new FramedLZ4CompressorOutputStream(out);
            }
            if (ZSTANDARD.equalsIgnoreCase(name)) {
                return new ZstdCompressorOutputStream(out);
            }
        } catch (IOException e) {
            throw new CompressorException("Could not create CompressorOutputStream", e);
        }
        CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider)this.getCompressorOutputStreamProviders().get(CompressorStreamFactory.toKey(name));
        if (compressorStreamProvider != null) {
            return compressorStreamProvider.createCompressorOutputStream(name, out);
        }
        throw new CompressorException("Compressor: " + name + " not found.");
    }

    public SortedMap<String, CompressorStreamProvider> getCompressorInputStreamProviders() {
        if (this.compressorInputStreamProviders == null) {
            this.compressorInputStreamProviders = Collections.unmodifiableSortedMap(CompressorStreamFactory.findAvailableCompressorInputStreamProviders());
        }
        return this.compressorInputStreamProviders;
    }

    public SortedMap<String, CompressorStreamProvider> getCompressorOutputStreamProviders() {
        if (this.compressorOutputStreamProviders == null) {
            this.compressorOutputStreamProviders = Collections.unmodifiableSortedMap(CompressorStreamFactory.findAvailableCompressorOutputStreamProviders());
        }
        return this.compressorOutputStreamProviders;
    }

    boolean getDecompressConcatenated() {
        return this.decompressConcatenated;
    }

    public Boolean getDecompressUntilEOF() {
        return this.decompressUntilEOF;
    }

    @Override
    public Set<String> getInputStreamCompressorNames() {
        return Sets.newHashSet(GZIP, BROTLI, BZIP2, XZ, LZMA, PACK200, DEFLATE, SNAPPY_RAW, SNAPPY_FRAMED, Z, LZ4_BLOCK, LZ4_FRAMED, ZSTANDARD, DEFLATE64);
    }

    @Override
    public Set<String> getOutputStreamCompressorNames() {
        return Sets.newHashSet(GZIP, BZIP2, XZ, LZMA, PACK200, DEFLATE, SNAPPY_FRAMED, LZ4_BLOCK, LZ4_FRAMED, ZSTANDARD);
    }

    @Deprecated
    public void setDecompressConcatenated(boolean decompressConcatenated) {
        if (this.decompressUntilEOF != null) {
            throw new IllegalStateException("Cannot override the setting defined by the constructor");
        }
        this.decompressConcatenated = decompressConcatenated;
    }
}

