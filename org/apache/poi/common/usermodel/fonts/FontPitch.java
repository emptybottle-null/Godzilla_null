/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel.fonts;

import org.apache.poi.common.usermodel.fonts.FontFamily;

public enum FontPitch {
    DEFAULT(0),
    FIXED(1),
    VARIABLE(2);

    private int nativeId;

    private FontPitch(int nativeId) {
        this.nativeId = nativeId;
    }

    public int getNativeId() {
        return this.nativeId;
    }

    public static FontPitch valueOf(int flag) {
        for (FontPitch fp : FontPitch.values()) {
            if (fp.nativeId != flag) continue;
            return fp;
        }
        return null;
    }

    public static byte getNativeId(FontPitch pitch, FontFamily family) {
        return (byte)(pitch.getNativeId() | family.getFlag() << 4);
    }

    public static FontPitch valueOfPitchFamily(byte pitchAndFamily) {
        return FontPitch.valueOf(pitchAndFamily & 3);
    }
}

