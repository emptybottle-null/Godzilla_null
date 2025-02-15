/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.IOException;
import org.apache.commons.compress.compressors.pack200.InMemoryCachingStreamBridge;
import org.apache.commons.compress.compressors.pack200.StreamBridge;
import org.apache.commons.compress.compressors.pack200.TempFileCachingStreamBridge;

public enum Pack200Strategy {
    IN_MEMORY{

        @Override
        StreamBridge newStreamBridge() {
            return new InMemoryCachingStreamBridge();
        }
    }
    ,
    TEMP_FILE{

        @Override
        StreamBridge newStreamBridge() throws IOException {
            return new TempFileCachingStreamBridge();
        }
    };


    abstract StreamBridge newStreamBridge() throws IOException;
}

