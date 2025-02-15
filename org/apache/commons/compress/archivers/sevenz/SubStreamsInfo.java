/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.util.BitSet;

class SubStreamsInfo {
    long[] unpackSizes;
    BitSet hasCrc;
    long[] crcs;

    SubStreamsInfo() {
    }
}

