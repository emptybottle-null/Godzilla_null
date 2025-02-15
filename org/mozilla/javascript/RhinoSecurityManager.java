/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.InterpretedFunction;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.PolicySecurityController;

public class RhinoSecurityManager
extends SecurityManager {
    protected Class<?> getCurrentScriptClass() {
        Class[] context;
        for (Class c : context = this.getClassContext()) {
            if ((c == InterpretedFunction.class || !NativeFunction.class.isAssignableFrom(c)) && !PolicySecurityController.SecureCaller.class.isAssignableFrom(c)) continue;
            return c;
        }
        return null;
    }
}

