/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSStringFunctions;

public abstract class JSString
implements JSStringFunctions {
    protected JSNumber length;
    public JSString prototype;
    protected JSFunction constructor;

    public JSString(JSString s) {
    }

    public static JSString fromCharCode(JSNumber charCode) {
        return null;
    }
}

