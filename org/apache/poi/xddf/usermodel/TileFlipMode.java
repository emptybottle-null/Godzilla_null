/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

public enum TileFlipMode {
    NONE(STTileFlipMode.NONE),
    X(STTileFlipMode.X),
    XY(STTileFlipMode.XY),
    Y(STTileFlipMode.Y);

    final STTileFlipMode.Enum underlying;
    private static final HashMap<STTileFlipMode.Enum, TileFlipMode> reverse;

    private TileFlipMode(STTileFlipMode.Enum mode) {
        this.underlying = mode;
    }

    static TileFlipMode valueOf(STTileFlipMode.Enum mode) {
        return reverse.get(mode);
    }

    static {
        reverse = new HashMap();
        for (TileFlipMode value : TileFlipMode.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

