/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.arj;

import java.util.Arrays;
import java.util.Objects;

class LocalFileHeader {
    int archiverVersionNumber;
    int minVersionToExtract;
    int hostOS;
    int arjFlags;
    int method;
    int fileType;
    int reserved;
    int dateTimeModified;
    long compressedSize;
    long originalSize;
    long originalCrc32;
    int fileSpecPosition;
    int fileAccessMode;
    int firstChapter;
    int lastChapter;
    int extendedFilePosition;
    int dateTimeAccessed;
    int dateTimeCreated;
    int originalSizeEvenForVolumes;
    String name;
    String comment;
    byte[][] extendedHeaders = null;

    LocalFileHeader() {
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LocalFileHeader [archiverVersionNumber=");
        builder.append(this.archiverVersionNumber);
        builder.append(", minVersionToExtract=");
        builder.append(this.minVersionToExtract);
        builder.append(", hostOS=");
        builder.append(this.hostOS);
        builder.append(", arjFlags=");
        builder.append(this.arjFlags);
        builder.append(", method=");
        builder.append(this.method);
        builder.append(", fileType=");
        builder.append(this.fileType);
        builder.append(", reserved=");
        builder.append(this.reserved);
        builder.append(", dateTimeModified=");
        builder.append(this.dateTimeModified);
        builder.append(", compressedSize=");
        builder.append(this.compressedSize);
        builder.append(", originalSize=");
        builder.append(this.originalSize);
        builder.append(", originalCrc32=");
        builder.append(this.originalCrc32);
        builder.append(", fileSpecPosition=");
        builder.append(this.fileSpecPosition);
        builder.append(", fileAccessMode=");
        builder.append(this.fileAccessMode);
        builder.append(", firstChapter=");
        builder.append(this.firstChapter);
        builder.append(", lastChapter=");
        builder.append(this.lastChapter);
        builder.append(", extendedFilePosition=");
        builder.append(this.extendedFilePosition);
        builder.append(", dateTimeAccessed=");
        builder.append(this.dateTimeAccessed);
        builder.append(", dateTimeCreated=");
        builder.append(this.dateTimeCreated);
        builder.append(", originalSizeEvenForVolumes=");
        builder.append(this.originalSizeEvenForVolumes);
        builder.append(", name=");
        builder.append(this.name);
        builder.append(", comment=");
        builder.append(this.comment);
        builder.append(", extendedHeaders=");
        builder.append(Arrays.toString((Object[])this.extendedHeaders));
        builder.append("]");
        return builder.toString();
    }

    public int hashCode() {
        return this.name == null ? 0 : this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        LocalFileHeader other = (LocalFileHeader)obj;
        return this.archiverVersionNumber == other.archiverVersionNumber && this.minVersionToExtract == other.minVersionToExtract && this.hostOS == other.hostOS && this.arjFlags == other.arjFlags && this.method == other.method && this.fileType == other.fileType && this.reserved == other.reserved && this.dateTimeModified == other.dateTimeModified && this.compressedSize == other.compressedSize && this.originalSize == other.originalSize && this.originalCrc32 == other.originalCrc32 && this.fileSpecPosition == other.fileSpecPosition && this.fileAccessMode == other.fileAccessMode && this.firstChapter == other.firstChapter && this.lastChapter == other.lastChapter && this.extendedFilePosition == other.extendedFilePosition && this.dateTimeAccessed == other.dateTimeAccessed && this.dateTimeCreated == other.dateTimeCreated && this.originalSizeEvenForVolumes == other.originalSizeEvenForVolumes && Objects.equals(this.name, other.name) && Objects.equals(this.comment, other.comment) && Arrays.deepEquals((Object[])this.extendedHeaders, (Object[])other.extendedHeaders);
    }

    static class Methods {
        static final int STORED = 0;
        static final int COMPRESSED_MOST = 1;
        static final int COMPRESSED = 2;
        static final int COMPRESSED_FASTER = 3;
        static final int COMPRESSED_FASTEST = 4;
        static final int NO_DATA_NO_CRC = 8;
        static final int NO_DATA = 9;

        Methods() {
        }
    }

    static class FileTypes {
        static final int BINARY = 0;
        static final int SEVEN_BIT_TEXT = 1;
        static final int COMMENT_HEADER = 2;
        static final int DIRECTORY = 3;
        static final int VOLUME_LABEL = 4;
        static final int CHAPTER_LABEL = 5;

        FileTypes() {
        }
    }

    static class Flags {
        static final int GARBLED = 1;
        static final int VOLUME = 4;
        static final int EXTFILE = 8;
        static final int PATHSYM = 16;
        static final int BACKUP = 32;

        Flags() {
        }
    }
}

