/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import java.nio.charset.StandardCharsets;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;

public class ConstantUtf8Info
extends ConstantPoolInfo {
    private String representedString;

    public ConstantUtf8Info(byte[] bytes) {
        super(1);
        this.representedString = this.createRepresentedString(bytes);
    }

    private String createRepresentedString(byte[] bytes) {
        this.representedString = new String(bytes, StandardCharsets.UTF_8);
        return this.representedString;
    }

    public String getRepresentedString(boolean quoted) {
        if (!quoted) {
            return this.representedString;
        }
        String temp = "\"" + this.representedString.replaceAll("\"", "\\\"") + "\"";
        return temp;
    }

    public String toString() {
        return "[ConstantUtf8Info: " + this.representedString + "]";
    }
}

