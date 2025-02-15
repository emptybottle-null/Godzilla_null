/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.arj;

import java.util.Arrays;

class MainHeader {
    int archiverVersionNumber;
    int minVersionToExtract;
    int hostOS;
    int arjFlags;
    int securityVersion;
    int fileType;
    int reserved;
    int dateTimeCreated;
    int dateTimeModified;
    long archiveSize;
    int securityEnvelopeFilePosition;
    int fileSpecPosition;
    int securityEnvelopeLength;
    int encryptionVersion;
    int lastChapter;
    int arjProtectionFactor;
    int arjFlags2;
    String name;
    String comment;
    byte[] extendedHeaderBytes = null;

    MainHeader() {
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MainHeader [archiverVersionNumber=");
        builder.append(this.archiverVersionNumber);
        builder.append(", minVersionToExtract=");
        builder.append(this.minVersionToExtract);
        builder.append(", hostOS=");
        builder.append(this.hostOS);
        builder.append(", arjFlags=");
        builder.append(this.arjFlags);
        builder.append(", securityVersion=");
        builder.append(this.securityVersion);
        builder.append(", fileType=");
        builder.append(this.fileType);
        builder.append(", reserved=");
        builder.append(this.reserved);
        builder.append(", dateTimeCreated=");
        builder.append(this.dateTimeCreated);
        builder.append(", dateTimeModified=");
        builder.append(this.dateTimeModified);
        builder.append(", archiveSize=");
        builder.append(this.archiveSize);
        builder.append(", securityEnvelopeFilePosition=");
        builder.append(this.securityEnvelopeFilePosition);
        builder.append(", fileSpecPosition=");
        builder.append(this.fileSpecPosition);
        builder.append(", securityEnvelopeLength=");
        builder.append(this.securityEnvelopeLength);
        builder.append(", encryptionVersion=");
        builder.append(this.encryptionVersion);
        builder.append(", lastChapter=");
        builder.append(this.lastChapter);
        builder.append(", arjProtectionFactor=");
        builder.append(this.arjProtectionFactor);
        builder.append(", arjFlags2=");
        builder.append(this.arjFlags2);
        builder.append(", name=");
        builder.append(this.name);
        builder.append(", comment=");
        builder.append(this.comment);
        builder.append(", extendedHeaderBytes=");
        builder.append(Arrays.toString(this.extendedHeaderBytes));
        builder.append("]");
        return builder.toString();
    }

    static class HostOS {
        static final int MS_DOS = 0;
        static final int PRIMOS = 1;
        static final int UNIX = 2;
        static final int AMIGA = 3;
        static final int MAC_OS = 4;
        static final int OS2 = 5;
        static final int APPLE_GS = 6;
        static final int ATARI_ST = 7;
        static final int NeXT = 8;
        static final int VAX_VMS = 9;
        static final int WIN95 = 10;
        static final int WIN32 = 11;

        HostOS() {
        }
    }

    static class Flags {
        static final int GARBLED = 1;
        static final int OLD_SECURED_NEW_ANSI_PAGE = 2;
        static final int VOLUME = 4;
        static final int ARJPROT = 8;
        static final int PATHSYM = 16;
        static final int BACKUP = 32;
        static final int SECURED = 64;
        static final int ALTNAME = 128;

        Flags() {
        }
    }
}

