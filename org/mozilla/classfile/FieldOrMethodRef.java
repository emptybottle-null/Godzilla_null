/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.classfile;

final class FieldOrMethodRef {
    private String className;
    private String name;
    private String type;
    private int hashCode = -1;

    FieldOrMethodRef(String className, String name, String type) {
        this.className = className;
        this.name = name;
        this.type = type;
    }

    public String getClassName() {
        return this.className;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FieldOrMethodRef)) {
            return false;
        }
        FieldOrMethodRef x = (FieldOrMethodRef)obj;
        return this.className.equals(x.className) && this.name.equals(x.name) && this.type.equals(x.type);
    }

    public int hashCode() {
        if (this.hashCode == -1) {
            int h1 = this.className.hashCode();
            int h2 = this.name.hashCode();
            int h3 = this.type.hashCode();
            this.hashCode = h1 ^ h2 ^ h3;
        }
        return this.hashCode;
    }
}

