/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public abstract class JSObject
implements JSObjectFunctions {
    public JSObject prototype;
    protected JSFunction constructor;

    public JSObject() {
    }

    public JSObject(JSObject value) {
    }
}

