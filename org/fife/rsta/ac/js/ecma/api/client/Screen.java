/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.client;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;

public abstract class Screen
implements JS5ObjectFunctions {
    protected JSFunction constructor;
    public Screen prototype;
    public JSNumber availHeight;
    public JSNumber availWidth;
    public JSNumber colorDepth;
    public JSNumber height;
    public JSNumber width;
}

