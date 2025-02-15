/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200Strategy;
import org.apache.commons.compress.compressors.pack200.StreamBridge;
import org.apache.commons.compress.utils.IOUtils;

public class Pack200CompressorInputStream
extends CompressorInputStream {
    private final InputStream originalInput;
    private final StreamBridge streamBridge;
    private static final byte[] CAFE_DOOD = new byte[]{-54, -2, -48, 13};
    private static final int SIG_LENGTH = CAFE_DOOD.length;

    public Pack200CompressorInputStream(InputStream in) throws IOException {
        this(in, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode) throws IOException {
        this(in, null, mode, null);
    }

    public Pack200CompressorInputStream(InputStream in, Map<String, String> props) throws IOException {
        this(in, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this(in, null, mode, props);
    }

    public Pack200CompressorInputStream(File f) throws IOException {
        this(f, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File f, Pack200Strategy mode) throws IOException {
        this(null, f, mode, null);
    }

    public Pack200CompressorInputStream(File f, Map<String, String> props) throws IOException {
        this(f, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorInputStream(File f, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this(null, f, mode, props);
    }

    private Pack200CompressorInputStream(InputStream in, File f, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this.originalInput = in;
        this.streamBridge = mode.newStreamBridge();
        try (JarOutputStream jarOut = new JarOutputStream(this.streamBridge);){
            Pack200.Unpacker u = Pack200.newUnpacker();
            if (props != null) {
                u.properties().putAll(props);
            }
            if (f == null) {
                u.unpack(new FilterInputStream(in){

                    @Override
                    public void close() {
                    }
                }, jarOut);
            } else {
                u.unpack(f, jarOut);
            }
        }
    }

    @Override
    public int read() throws IOException {
        return this.streamBridge.getInput().read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.streamBridge.getInput().read(b);
    }

    @Override
    public int read(byte[] b, int off, int count) throws IOException {
        return this.streamBridge.getInput().read(b, off, count);
    }

    @Override
    public int available() throws IOException {
        return this.streamBridge.getInput().available();
    }

    @Override
    public boolean markSupported() {
        try {
            return this.streamBridge.getInput().markSupported();
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void mark(int limit) {
        try {
            this.streamBridge.getInput().mark(limit);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void reset() throws IOException {
        this.streamBridge.getInput().reset();
    }

    @Override
    public long skip(long count) throws IOException {
        return IOUtils.skip(this.streamBridge.getInput(), count);
    }

    @Override
    public void close() throws IOException {
        try {
            this.streamBridge.stop();
        } finally {
            if (this.originalInput != null) {
                this.originalInput.close();
            }
        }
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < SIG_LENGTH) {
            return false;
        }
        for (int i = 0; i < SIG_LENGTH; ++i) {
            if (signature[i] == CAFE_DOOD[i]) continue;
            return false;
        }
        return true;
    }
}

