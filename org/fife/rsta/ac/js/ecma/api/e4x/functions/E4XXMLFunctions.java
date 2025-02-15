/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.e4x.functions;

import org.fife.rsta.ac.js.ecma.api.e4x.E4XNamespace;
import org.fife.rsta.ac.js.ecma.api.e4x.E4XQName;
import org.fife.rsta.ac.js.ecma.api.e4x.E4XXML;
import org.fife.rsta.ac.js.ecma.api.e4x.E4XXMLList;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSArray;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface E4XXMLFunctions
extends JSObjectFunctions {
    public void addNamespace(E4XNamespace var1);

    public E4XXML appendChild(E4XXML var1);

    public E4XXMLList attribute(JSString var1);

    public E4XXMLList attributes();

    public E4XXMLList child(JSString var1);

    public E4XXMLList child(JSNumber var1);

    public JSNumber childIndex();

    public E4XXMLList children();

    public E4XXMLList comments();

    public JSBoolean contains(E4XXML var1);

    public JSBoolean contains(E4XXMLList var1);

    public JSBoolean copy();

    public E4XXMLList descendants(JSString var1);

    public E4XXMLList elements(JSString var1);

    public JSBoolean hasComplexContent();

    public JSBoolean hasSimpleContent();

    public JSArray inScopeNamespaces();

    public E4XXML insertChildAfter(E4XXML var1, E4XXML var2);

    public E4XXML insertChildBefore(E4XXML var1, E4XXML var2);

    public JSNumber length();

    public JSNumber localName();

    public E4XQName name();

    public E4XNamespace namespace(JSString var1);

    public JSArray namespaceDeclarations();

    public JSString nodeKind();

    public E4XXML normalize();

    public E4XXML parent();

    public E4XXMLList processingInstructions(JSString var1);

    public E4XXML prependChild(E4XXML var1);

    public E4XXML removeNamespace(E4XNamespace var1);

    public E4XXML replace(JSString var1, JSObject var2);

    public E4XXML replace(JSNumber var1, JSObject var2);

    public E4XXML setChildren(E4XXML var1);

    public E4XXML setChildren(E4XXMLList var1);

    public void setLocalName(JSString var1);

    public void setName(E4XQName var1);

    public void setNamespace(E4XNamespace var1);

    public E4XXMLList text();

    public JSString toXMLString();
}

