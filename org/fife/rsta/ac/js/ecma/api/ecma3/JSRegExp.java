/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSRegExpFunctions;

public abstract class JSRegExp
implements JSRegExpFunctions {
    public JSRegExp prototype;
    protected JSFunction constructor;
    protected JSString source;
    protected JSBoolean global;
    protected JSBoolean ignoreCase;
    protected JSBoolean multiline;
    protected JSNumber lastIndex;

    public JSRegExp(JSString pattern, JSString attributes) {
    }
}

