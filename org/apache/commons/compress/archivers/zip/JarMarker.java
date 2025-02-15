/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public final class JarMarker
implements ZipExtraField {
    private static final ZipShort ID = new ZipShort(51966);
    private static final ZipShort NULL = new ZipShort(0);
    private static final byte[] NO_BYTES = new byte[0];
    private static final JarMarker DEFAULT = new JarMarker();

    public static JarMarker getInstance() {
        return DEFAULT;
    }

    @Override
    public ZipShort getHeaderId() {
        return ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return NULL;
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return NULL;
    }

    @Override
    public byte[] getLocalFileDataData() {
        return NO_BYTES;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return NO_BYTES;
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        if (length != 0) {
            throw new ZipException("JarMarker doesn't expect any data");
        }
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.parseFromLocalFileData(buffer, offset, length);
    }
}

