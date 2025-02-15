/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.mozilla.javascript.commonjs.module.ModuleScript;
import org.mozilla.javascript.commonjs.module.provider.CachingModuleScriptProviderBase;
import org.mozilla.javascript.commonjs.module.provider.ModuleSourceProvider;

public class StrongCachingModuleScriptProvider
extends CachingModuleScriptProviderBase {
    private static final long serialVersionUID = 1L;
    private final Map<String, CachingModuleScriptProviderBase.CachedModuleScript> modules = new ConcurrentHashMap<String, CachingModuleScriptProviderBase.CachedModuleScript>(16, 0.75f, StrongCachingModuleScriptProvider.getConcurrencyLevel());

    public StrongCachingModuleScriptProvider(ModuleSourceProvider moduleSourceProvider) {
        super(moduleSourceProvider);
    }

    @Override
    protected CachingModuleScriptProviderBase.CachedModuleScript getLoadedModule(String moduleId) {
        return this.modules.get(moduleId);
    }

    @Override
    protected void putLoadedModule(String moduleId, ModuleScript moduleScript, Object validator) {
        this.modules.put(moduleId, new CachingModuleScriptProviderBase.CachedModuleScript(moduleScript, validator));
    }
}

