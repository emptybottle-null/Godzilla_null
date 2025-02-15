/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSArray;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSRegExp;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface JSStringFunctions
extends JSObjectFunctions {
    public JSString charAt(JSNumber var1);

    public JSNumber charCodeAt(JSNumber var1);

    public JSString concat(JSString var1);

    public JSNumber indexOf(JSString var1, JSNumber var2);

    public JSNumber lastIndexOf(JSString var1, JSNumber var2);

    public JSNumber localeCompare(JSString var1);

    public JSString match(JSRegExp var1);

    public JSString replace(JSRegExp var1, JSString var2);

    public JSNumber search(JSRegExp var1);

    public JSString slice(JSNumber var1, JSNumber var2);

    public JSArray split(JSString var1, JSNumber var2);

    public JSString substring(JSNumber var1, JSNumber var2);

    public JSString toLowerCase();

    public JSString toLocaleLowerCase();

    public JSString toUpperCase();

    public JSString toLocaleUpperCase();
}

