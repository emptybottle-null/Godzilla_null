/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.provider.ModuleSource;

public interface ModuleSourceProvider {
    public static final ModuleSource NOT_MODIFIED = new ModuleSource(null, null, null, null, null);

    public ModuleSource loadSource(String var1, Scriptable var2, Object var3) throws IOException, URISyntaxException;

    public ModuleSource loadSource(URI var1, URI var2, Object var3) throws IOException, URISyntaxException;
}

