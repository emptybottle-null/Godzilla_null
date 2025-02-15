/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface JSGlobalFunctions
extends JSObjectFunctions {
    public JSString decodeURI(JSString var1);

    public JSString decodeURIComponent(JSString var1);

    public JSString encodeURI(JSString var1);

    public JSString encodeURIComponent(JSString var1);

    public JSString escape(JSString var1);

    public JSObject eval(JSString var1);

    public JSBoolean isFinite(JSNumber var1);

    public JSBoolean isNaN(JSNumber var1);

    public JSString parseFloat(JSString var1);

    public JSString parseInt(JSString var1, JSNumber var2);

    public JSString unescape(JSString var1);
}

