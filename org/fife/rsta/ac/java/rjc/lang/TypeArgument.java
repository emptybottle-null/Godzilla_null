/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lang;

import org.fife.rsta.ac.java.rjc.lang.Type;

public class TypeArgument {
    public static final int NOTHING = 0;
    public static final int EXTENDS = 1;
    public static final int SUPER = 2;
    private Type type;
    private int doesExtend;
    private Type otherType;

    public TypeArgument(Type type) {
        this.type = type;
    }

    public TypeArgument(Type type, int doesExtend, Type otherType) {
        if (doesExtend < 0 || doesExtend > 2) {
            throw new IllegalArgumentException("Illegal doesExtend: " + doesExtend);
        }
        this.type = type;
        this.doesExtend = doesExtend;
        this.otherType = otherType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type == null) {
            sb.append('?');
        } else {
            sb.append(this.type.toString());
        }
        if (this.doesExtend == 1) {
            sb.append(" extends ");
            sb.append(this.otherType.toString());
        } else if (this.doesExtend == 2) {
            sb.append(" super ");
            sb.append(this.otherType.toString());
        }
        return sb.toString();
    }
}

