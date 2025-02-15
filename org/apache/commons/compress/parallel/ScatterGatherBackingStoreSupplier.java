/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.parallel;

import java.io.IOException;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public interface ScatterGatherBackingStoreSupplier {
    public ScatterGatherBackingStore get() throws IOException;
}

