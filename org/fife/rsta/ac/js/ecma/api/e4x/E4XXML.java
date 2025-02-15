/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.e4x;

import org.fife.rsta.ac.js.ecma.api.e4x.functions.E4XXMLFunctions;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;

public abstract class E4XXML
implements E4XXMLFunctions {
    public E4XXML protype;
    protected JSFunction constructor;
    public static JSBoolean ignoringComments;
    public static JSBoolean ignoreProcessingInstructions;
    public static JSBoolean ignoreWhitespace;
    public static JSBoolean prettyPrinting;
    public static JSNumber prettyIndent;

    public E4XXML(JSObject xml) {
    }

    public static JSObject settings() {
        return null;
    }

    public static void setSettings(JSObject settings) {
    }

    public static JSObject defaultSettings() {
        return null;
    }
}

