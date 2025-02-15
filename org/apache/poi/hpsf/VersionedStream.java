/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.GUID;
import org.apache.poi.hpsf.IndirectPropertyName;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class VersionedStream {
    private final GUID _versionGuid = new GUID();
    private final IndirectPropertyName _streamName = new IndirectPropertyName();

    public void read(LittleEndianByteArrayInputStream lei) {
        this._versionGuid.read(lei);
        this._streamName.read(lei);
    }
}

