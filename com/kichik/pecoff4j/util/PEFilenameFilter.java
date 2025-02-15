/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import java.io.File;
import java.io.FilenameFilter;

public class PEFilenameFilter
implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".exe") || name.toLowerCase().endsWith(".dll");
    }
}

