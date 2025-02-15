/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSDateFunctions;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5String;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;

public interface JS5DateFunctions
extends JS5ObjectFunctions,
JSDateFunctions {
    public JS5String toISOString();

    public JS5String toJSON(JS5String var1);
}

