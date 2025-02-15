/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module;

import java.net.URI;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;

public interface ModuleScriptProvider {
    public ModuleScript getModuleScript(Context var1, String var2, URI var3, URI var4, Scriptable var5) throws Exception;
}

