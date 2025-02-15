/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.net.URI;
import java.util.LinkedList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;

public class MultiModuleScriptProvider
implements ModuleScriptProvider {
    private final ModuleScriptProvider[] providers;

    public MultiModuleScriptProvider(Iterable<? extends ModuleScriptProvider> providers) {
        LinkedList<ModuleScriptProvider> l = new LinkedList<ModuleScriptProvider>();
        for (ModuleScriptProvider moduleScriptProvider : providers) {
            l.add(moduleScriptProvider);
        }
        this.providers = l.toArray(new ModuleScriptProvider[l.size()]);
    }

    @Override
    public ModuleScript getModuleScript(Context cx, String moduleId, URI uri, URI base, Scriptable paths) throws Exception {
        for (ModuleScriptProvider provider : this.providers) {
            ModuleScript script = provider.getModuleScript(cx, moduleId, uri, base, paths);
            if (script == null) continue;
            return script;
        }
        return null;
    }
}

