/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.runtime;

public class DotClass {
    public static NoClassDefFoundError fail(ClassNotFoundException e) {
        return new NoClassDefFoundError(e.getMessage());
    }
}

