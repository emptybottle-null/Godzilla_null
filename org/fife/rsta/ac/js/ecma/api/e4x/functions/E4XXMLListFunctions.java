/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.e4x.functions;

import org.fife.rsta.ac.js.ecma.api.e4x.E4XXML;
import org.fife.rsta.ac.js.ecma.api.e4x.E4XXMLList;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface E4XXMLListFunctions
extends JSObjectFunctions {
    public E4XXMLList attribute(JSString var1);

    public E4XXMLList attributes();

    public E4XXMLList child(JSString var1);

    public E4XXMLList children();

    public E4XXMLList comments();

    public JSBoolean contains(E4XXML var1);

    public JSBoolean copy();

    public E4XXMLList descendants(JSString var1);

    public E4XXMLList elements(JSString var1);

    public JSBoolean hasComplexContent();

    public JSBoolean hasSimpleContent();

    public JSNumber length();

    public E4XXMLList normalize();

    public E4XXML parent();

    public E4XXMLList processingInstructions(JSString var1);

    public E4XXMLList text();

    public JSString toXMLString();
}

