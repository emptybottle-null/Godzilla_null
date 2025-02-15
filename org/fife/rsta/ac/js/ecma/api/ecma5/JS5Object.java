/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma5;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Array;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5String;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;

public abstract class JS5Object
extends JSObject
implements JS5ObjectFunctions {
    public JS5Object() {
    }

    public JS5Object(JSObject value) {
    }

    public static JS5Object create(JS5Object proto, JS5Object descriptors) {
        return null;
    }

    public static JS5Object defineProperties(JS5Object o, JS5Object descriptors) {
        return null;
    }

    public static JS5Object defineProperty(JS5Object o, JS5String name, JS5Object desc) {
        return null;
    }

    public static JS5Object freeze(JS5Object o) {
        return null;
    }

    public static JS5Object getOwnPropertyDescriptor(JS5Object o, JS5String name) {
        return null;
    }

    public static JS5Array getOwnPropertyNames(JS5Object o) {
        return null;
    }

    public static JS5Object getPrototypeOf(JS5Object o) {
        return null;
    }

    public static JSBoolean isExtensible(JS5Object o) {
        return null;
    }

    public static JSBoolean isFrozen(JS5Object o) {
        return null;
    }

    public static JSBoolean isSealed(JS5Object o) {
        return null;
    }

    public static JS5Array keys(JS5Object o) {
        return null;
    }

    public static JS5Object preventExtensions(JS5Object o) {
        return null;
    }

    public static JS5Object seal(JS5Object o) {
        return null;
    }
}

