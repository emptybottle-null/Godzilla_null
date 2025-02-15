/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.attributes;

import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;

public class UnsupportedAttribute
extends AttributeInfo {
    private String name;

    public UnsupportedAttribute(ClassFile cf, String name) {
        super(cf);
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "[UnsupportedAttribute: name=" + this.getName() + "]";
    }
}

