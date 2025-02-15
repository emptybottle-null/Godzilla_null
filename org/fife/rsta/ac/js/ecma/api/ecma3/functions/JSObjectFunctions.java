/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;

public interface JSObjectFunctions {
    public String toString();

    public JSString toLocaleString();

    public JSObject valueOf();

    public JSBoolean hasOwnProperty(String var1);

    public JSBoolean isPrototypeOf(JSObject var1);

    public JSBoolean propertyIsEnumerable(JSObject var1);
}

