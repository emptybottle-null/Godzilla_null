/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.util.HashMap;
import javassist.CtClass;
import javassist.bytecode.Descriptor;

public class ClassMap
extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;
    private ClassMap parent;

    public ClassMap() {
        this.parent = null;
    }

    ClassMap(ClassMap map) {
        this.parent = map;
    }

    @Override
    public void put(CtClass oldname, CtClass newname) {
        this.put(oldname.getName(), newname.getName());
    }

    @Override
    public String put(String oldname, String newname) {
        if (oldname == newname) {
            return oldname;
        }
        String oldname2 = ClassMap.toJvmName(oldname);
        String s = this.get(oldname2);
        if (s == null || !s.equals(oldname2)) {
            return super.put(oldname2, ClassMap.toJvmName(newname));
        }
        return s;
    }

    public void putIfNone(String oldname, String newname) {
        if (oldname == newname) {
            return;
        }
        String oldname2 = ClassMap.toJvmName(oldname);
        String s = this.get(oldname2);
        if (s == null) {
            super.put(oldname2, ClassMap.toJvmName(newname));
        }
    }

    protected final String put0(String oldname, String newname) {
        return super.put(oldname, newname);
    }

    @Override
    public String get(Object jvmClassName) {
        String found = (String)super.get(jvmClassName);
        if (found == null && this.parent != null) {
            return this.parent.get(jvmClassName);
        }
        return found;
    }

    public void fix(CtClass clazz) {
        this.fix(clazz.getName());
    }

    public void fix(String name) {
        String name2 = ClassMap.toJvmName(name);
        super.put(name2, name2);
    }

    public static String toJvmName(String classname) {
        return Descriptor.toJvmName(classname);
    }

    public static String toJavaName(String classname) {
        return Descriptor.toJavaName(classname);
    }
}

