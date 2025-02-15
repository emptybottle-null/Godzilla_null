/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module;

import java.io.Serializable;
import java.net.URI;
import org.mozilla.javascript.Script;

public class ModuleScript
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Script script;
    private final URI uri;
    private final URI base;

    public ModuleScript(Script script, URI uri, URI base) {
        this.script = script;
        this.uri = uri;
        this.base = base;
    }

    public Script getScript() {
        return this.script;
    }

    public URI getUri() {
        return this.uri;
    }

    public URI getBase() {
        return this.base;
    }

    public boolean isSandboxed() {
        return this.base != null && this.uri != null && !this.base.relativize(this.uri).isAbsolute();
    }
}

