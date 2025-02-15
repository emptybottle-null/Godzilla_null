/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public interface CompressorStreamProvider {
    public CompressorInputStream createCompressorInputStream(String var1, InputStream var2, boolean var3) throws CompressorException;

    public CompressorOutputStream createCompressorOutputStream(String var1, OutputStream var2) throws CompressorException;

    public Set<String> getInputStreamCompressorNames();

    public Set<String> getOutputStreamCompressorNames();
}

