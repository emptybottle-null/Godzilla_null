/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.client;

import org.fife.rsta.ac.js.ecma.api.client.BarProp;
import org.fife.rsta.ac.js.ecma.api.client.History;
import org.fife.rsta.ac.js.ecma.api.client.Location;
import org.fife.rsta.ac.js.ecma.api.client.Navigator;
import org.fife.rsta.ac.js.ecma.api.client.Screen;
import org.fife.rsta.ac.js.ecma.api.client.funtions.WindowFunctions;
import org.fife.rsta.ac.js.ecma.api.dom.html.JSHTMLDocument;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSArray;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSGlobal;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;

public abstract class Window
extends JSGlobal
implements WindowFunctions {
    protected JSFunction constructor;
    public Window prototype;
    public JSBoolean closed;
    public Window window;
    public JSArray frames;
    public JSString defaultStatus;
    public JSHTMLDocument document;
    public History history;
    public Location location;
    public JSString name;
    public Navigator navigator;
    public Window opener;
    public JSNumber outerWidth;
    public JSNumber outerHeight;
    public JSNumber pageXOffset;
    public JSNumber pageYOffset;
    public Window parent;
    public Screen screen;
    public JSString status;
    public Window top;
    public JSNumber innerWidth;
    public JSNumber innerHeight;
    public JSNumber screenX;
    public JSNumber screenY;
    public JSNumber screenLeft;
    public JSNumber screenTop;
    public JSNumber length;
    public BarProp scrollbars;
    public JSNumber scrollX;
    public JSNumber scrollY;
    public Window content;
    public BarProp menubar;
    public BarProp toolbar;
    public BarProp locationbar;
    public BarProp personalbar;
    public BarProp statusbar;
    public BarProp directories;
    public JSNumber scrollMaxX;
    public JSNumber scrollMaxY;
    public JSString fullScreen;
    public JSString frameElement;
    public JSString sessionStorage;
}

