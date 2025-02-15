/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSDate;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5DateFunctions;

public abstract class JS5Date
extends JSDate
implements JS5DateFunctions {
    public JS5Date() {
    }

    public JS5Date(JSNumber milliseconds) {
    }

    public JS5Date(JSString datestring) {
    }

    public JS5Date(JSNumber year, JSNumber month, JSNumber day, JSNumber hours, JSNumber minutes, JSNumber seconds, JSNumber ms) {
    }

    public static JSNumber now() {
        return null;
    }
}

