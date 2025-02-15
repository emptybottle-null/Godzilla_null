/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

class Runner
implements Runnable,
ContextAction {
    ContextFactory factory;
    private Scriptable scope;
    private Function f;
    private Script s;
    private Object[] args;

    Runner(Scriptable scope, Function func, Object[] args) {
        this.scope = scope;
        this.f = func;
        this.args = args;
    }

    Runner(Scriptable scope, Script script) {
        this.scope = scope;
        this.s = script;
    }

    @Override
    public void run() {
        this.factory.call(this);
    }

    @Override
    public Object run(Context cx) {
        if (this.f != null) {
            return this.f.call(cx, this.scope, this.scope, this.args);
        }
        return this.s.exec(cx, this.scope);
    }
}

