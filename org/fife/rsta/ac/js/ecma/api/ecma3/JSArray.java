/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSArrayFunctions;

public abstract class JSArray
implements JSArrayFunctions {
    public JSNumber length;
    public JSArray prototype;
    protected JSFunction constructor;

    public JSArray() {
    }

    public JSArray(JSNumber size) {
    }

    public JSArray(JSObject element0, JSObject elementn) {
    }
}

