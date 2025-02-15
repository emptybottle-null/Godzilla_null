/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSFunctionFunctions;

public abstract class JSFunction
implements JSFunctionFunctions {
    protected JSNumber length;
    public JSFunction prototype;
    protected JSFunction constructor;

    public JSFunction(JSString argument_names, JSString body) {
    }
}

