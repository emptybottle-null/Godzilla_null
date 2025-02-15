/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Scriptable;

public interface ConstProperties {
    public void putConst(String var1, Scriptable var2, Object var3);

    public void defineConst(String var1, Scriptable var2);

    public boolean isConst(String var1);
}

