/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.jar.Pack200;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.pack200.Pack200Strategy;
import org.apache.commons.compress.compressors.pack200.StreamBridge;

public class Pack200CompressorOutputStream
extends CompressorOutputStream {
    private boolean finished = false;
    private final OutputStream originalOutput;
    private final StreamBridge streamBridge;
    private final Map<String, String> properties;

    public Pack200CompressorOutputStream(OutputStream out) throws IOException {
        this(out, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode) throws IOException {
        this(out, mode, null);
    }

    public Pack200CompressorOutputStream(OutputStream out, Map<String, String> props) throws IOException {
        this(out, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this.originalOutput = out;
        this.streamBridge = mode.newStreamBridge();
        this.properties = props;
    }

    @Override
    public void write(int b) throws IOException {
        this.streamBridge.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.streamBridge.write(b);
    }

    @Override
    public void write(byte[] b, int from, int length) throws IOException {
        this.streamBridge.write(b, from, length);
    }

    @Override
    public void close() throws IOException {
        try {
            this.finish();
        } finally {
            try {
                this.streamBridge.stop();
            } finally {
                this.originalOutput.close();
            }
        }
    }

    public void finish() throws IOException {
        if (!this.finished) {
            this.finished = true;
            Pack200.Packer p = Pack200.newPacker();
            if (this.properties != null) {
                p.properties().putAll(this.properties);
            }
            try (JarInputStream ji = new JarInputStream(this.streamBridge.getInput());){
                p.pack(ji, this.originalOutput);
            }
        }
    }
}

