/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSFunctionFunctions;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Array;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Function;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Object;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;

public interface JS5FunctionFunctions
extends JS5ObjectFunctions,
JSFunctionFunctions {
    public JS5Function bind(JS5Object var1, JS5Array var2);
}

