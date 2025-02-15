/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.demo;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class ExtensionFileFilter
extends FileFilter {
    private String desc;
    private String ext;

    public ExtensionFileFilter(String desc, String ext) {
        this.desc = desc;
        this.ext = ext;
    }

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(this.ext);
    }

    @Override
    public String getDescription() {
        return this.desc + " (*." + this.ext + ")";
    }
}

