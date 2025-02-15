/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.io.PEParser;
import java.io.File;

public class ResourceStripper {
    public static void remove(File pecoff, File output) throws Exception {
        PE pe = PEParser.parse(pecoff);
    }
}

