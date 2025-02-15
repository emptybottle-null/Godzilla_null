/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSArray;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface JSArrayFunctions
extends JSObjectFunctions {
    public JSArray concat(JSArray var1);

    public JSString join(String var1);

    public JSObject pop();

    public void push(JSArray var1);

    public JSArray reverse();

    public JSObject shift();

    public JSArray slice(Number var1, Number var2);

    public JSArray sort(JSFunction var1);

    public JSArray splice(JSNumber var1, JSNumber var2, JSArray var3);

    public JSNumber unshift(JSArray var1);
}

