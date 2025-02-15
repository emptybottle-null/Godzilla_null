/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.e4x;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public abstract class E4XNamespace
implements JSObjectFunctions {
    public E4XNamespace protype;
    protected JSFunction constructor;
    protected JSString prefix;
    protected JSString uri;

    public E4XNamespace() {
    }

    public E4XNamespace(JSString uriValue) {
    }

    public E4XNamespace(JSString prefixValue, JSString uriValue) {
    }
}

