/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSArray;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Object;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ArrayFunctions;

public abstract class JS5Array
extends JSArray
implements JS5ArrayFunctions {
    public JS5Array() {
    }

    public JS5Array(JSNumber size) {
    }

    public JS5Array(JSObject element0, JSObject elementn) {
    }

    public static JSBoolean isArray(JS5Object o) {
        return null;
    }
}

