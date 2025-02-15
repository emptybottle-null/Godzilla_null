/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5FunctionFunctions;

public abstract class JS5Function
extends JSFunction
implements JS5FunctionFunctions {
    public JS5Function(JSString argument_names, JSString body) {
        super(argument_names, body);
    }
}

