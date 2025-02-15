/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.pack200.StreamBridge;

class InMemoryCachingStreamBridge
extends StreamBridge {
    InMemoryCachingStreamBridge() {
        super(new ByteArrayOutputStream());
    }

    @Override
    InputStream getInputView() throws IOException {
        return new ByteArrayInputStream(((ByteArrayOutputStream)this.out).toByteArray());
    }
}

