/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.CtField;

class FieldInitLink {
    FieldInitLink next = null;
    CtField field;
    CtField.Initializer init;

    FieldInitLink(CtField f, CtField.Initializer i) {
        this.field = f;
        this.init = i;
    }
}

