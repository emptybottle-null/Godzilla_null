/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSArrayFunctions;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Array;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Function;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Object;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;

public interface JS5ArrayFunctions
extends JS5ObjectFunctions,
JSArrayFunctions {
    public JSBoolean every(JS5Function var1, JS5Object var2);

    public JS5Array filter(JS5Function var1, JS5Object var2);

    public void forEach(JS5Function var1, JS5Object var2);

    public JSNumber indexOf(JS5Object var1, JSNumber var2);

    public JSNumber lastIndexOf(JS5Object var1, JSNumber var2);

    public JS5Array map(JS5Function var1, JS5Object var2);

    public JS5Object reduce(JS5Function var1, JS5Object var2);

    public JS5Object reduceRight(JS5Function var1, JS5Object var2);

    public JSBoolean some(JS5Function var1, JS5Object var2);
}

