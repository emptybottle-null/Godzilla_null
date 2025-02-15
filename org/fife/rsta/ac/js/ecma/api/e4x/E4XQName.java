/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.e4x;

import org.fife.rsta.ac.js.ecma.api.e4x.E4XNamespace;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public abstract class E4XQName
implements JSObjectFunctions {
    public E4XQName protype;
    protected JSFunction constructor;
    protected JSString localName;
    protected JSString uri;

    public E4XQName() {
    }

    public E4XQName(JSString name) {
    }

    public E4XQName(E4XNamespace namespace, JSString name) {
    }
}

