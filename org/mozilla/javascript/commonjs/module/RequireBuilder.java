/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module;

import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.commonjs.module.ModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.Require;

public class RequireBuilder
implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean sandboxed = true;
    private ModuleScriptProvider moduleScriptProvider;
    private Script preExec;
    private Script postExec;

    public RequireBuilder setModuleScriptProvider(ModuleScriptProvider moduleScriptProvider) {
        this.moduleScriptProvider = moduleScriptProvider;
        return this;
    }

    public RequireBuilder setPostExec(Script postExec) {
        this.postExec = postExec;
        return this;
    }

    public RequireBuilder setPreExec(Script preExec) {
        this.preExec = preExec;
        return this;
    }

    public RequireBuilder setSandboxed(boolean sandboxed) {
        this.sandboxed = sandboxed;
        return this;
    }

    public Require createRequire(Context cx, Scriptable globalScope) {
        return new Require(cx, globalScope, this.moduleScriptProvider, this.preExec, this.postExec, this.sandboxed);
    }
}

