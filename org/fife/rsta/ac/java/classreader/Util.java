/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader;

import java.io.DataInputStream;
import java.io.IOException;
import org.fife.rsta.ac.java.classreader.AccessFlags;

public class Util
implements AccessFlags {
    private Util() {
    }

    public static boolean isDefault(int accessFlags) {
        int access = 7;
        return (accessFlags & access) == 0;
    }

    public static boolean isPrivate(int accessFlags) {
        return (accessFlags & 2) > 0;
    }

    public static boolean isProtected(int accessFlags) {
        return (accessFlags & 4) > 0;
    }

    public static boolean isPublic(int accessFlags) {
        return (accessFlags & 1) > 0;
    }

    public static void skipBytes(DataInputStream in, int count) throws IOException {
        for (int skipped = 0; skipped < count; skipped += in.skipBytes(count - skipped)) {
        }
    }
}

