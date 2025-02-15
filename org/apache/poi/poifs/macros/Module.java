/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.macros;

public interface Module {
    public String getContent();

    public ModuleType geModuleType();

    public static enum ModuleType {
        Document,
        Module,
        Class;

    }
}

