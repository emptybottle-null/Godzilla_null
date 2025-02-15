/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.filesystem.POIFSWriterEvent;

public interface POIFSWriterListener {
    public void processPOIFSWriterEvent(POIFSWriterEvent var1);
}

