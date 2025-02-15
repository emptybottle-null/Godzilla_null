/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class ResourceAlignmentExtraField
implements ZipExtraField {
    public static final ZipShort ID = new ZipShort(41246);
    public static final int BASE_SIZE = 2;
    private static final int ALLOW_METHOD_MESSAGE_CHANGE_FLAG = 32768;
    private short alignment;
    private boolean allowMethodChange;
    private int padding = 0;

    public ResourceAlignmentExtraField() {
    }

    public ResourceAlignmentExtraField(int alignment) {
        this(alignment, false);
    }

    public ResourceAlignmentExtraField(int alignment, boolean allowMethodChange) {
        this(alignment, allowMethodChange, 0);
    }

    public ResourceAlignmentExtraField(int alignment, boolean allowMethodChange, int padding) {
        if (alignment < 0 || alignment > Short.MAX_VALUE) {
            throw new IllegalArgumentException("Alignment must be between 0 and 0x7fff, was: " + alignment);
        }
        if (padding < 0) {
            throw new IllegalArgumentException("Padding must not be negative, was: " + padding);
        }
        this.alignment = (short)alignment;
        this.allowMethodChange = allowMethodChange;
        this.padding = padding;
    }

    public short getAlignment() {
        return this.alignment;
    }

    public boolean allowMethodChange() {
        return this.allowMethodChange;
    }

    @Override
    public ZipShort getHeaderId() {
        return ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(2 + this.padding);
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort(2);
    }

    @Override
    public byte[] getLocalFileDataData() {
        byte[] content = new byte[2 + this.padding];
        ZipShort.putShort(this.alignment | (this.allowMethodChange ? 32768 : 0), content, 0);
        return content;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return ZipShort.getBytes(this.alignment | (this.allowMethodChange ? 32768 : 0));
    }

    @Override
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {
        this.parseFromCentralDirectoryData(buffer, offset, length);
        this.padding = length - 2;
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        if (length < 2) {
            throw new ZipException("Too short content for ResourceAlignmentExtraField (0xa11e): " + length);
        }
        int alignmentValue = ZipShort.getValue(buffer, offset);
        this.alignment = (short)(alignmentValue & Short.MAX_VALUE);
        this.allowMethodChange = (alignmentValue & 0x8000) != 0;
    }
}

