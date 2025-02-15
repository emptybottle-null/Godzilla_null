/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.UniqueTag;

public interface Scriptable {
    public static final Object NOT_FOUND = UniqueTag.NOT_FOUND;

    public String getClassName();

    public Object get(String var1, Scriptable var2);

    public Object get(int var1, Scriptable var2);

    public boolean has(String var1, Scriptable var2);

    public boolean has(int var1, Scriptable var2);

    public void put(String var1, Scriptable var2, Object var3);

    public void put(int var1, Scriptable var2, Object var3);

    public void delete(String var1);

    public void delete(int var1);

    public Scriptable getPrototype();

    public void setPrototype(Scriptable var1);

    public Scriptable getParentScope();

    public void setParentScope(Scriptable var1);

    public Object[] getIds();

    public Object getDefaultValue(Class<?> var1);

    public boolean hasInstance(Scriptable var1);
}

