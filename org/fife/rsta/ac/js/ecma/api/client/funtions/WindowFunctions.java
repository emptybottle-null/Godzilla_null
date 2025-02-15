/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.client.funtions;

import org.fife.rsta.ac.js.ecma.api.client.Window;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSBoolean;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma5.JS5Object;
import org.fife.rsta.ac.js.ecma.api.ecma5.functions.JS5ObjectFunctions;
import org.w3c.dom.Element;

public interface WindowFunctions
extends JS5ObjectFunctions {
    public void alert(JSString var1);

    public void blur();

    public void clearInterval(JS5Object var1);

    public void clearTimeout(JS5Object var1);

    public void close();

    public JSBoolean confirm(JSString var1);

    public void focus();

    public JS5Object getComputedStyle(Element var1, JSString var2);

    public void moveTo(JSNumber var1, JSNumber var2);

    public void moveBy(JSNumber var1, JSNumber var2);

    public Window open(JSString var1, JSString var2, JSString var3, JSBoolean var4);

    public void print();

    public JSString prompt();

    public void resizeTo(JSNumber var1, JSNumber var2);

    public void resizeBy(JSNumber var1, JSNumber var2);

    public void scrollTo(JSNumber var1, JSNumber var2);

    public void scrollBy(JSNumber var1, JSNumber var2);

    public JSNumber setInterval(JSObject var1, JSNumber var2);

    public JSNumber setTimeout(JSObject var1, JSNumber var2);

    public JSString atob(JSString var1);

    public JSString btoa(JSString var1);

    public void setResizable(JSBoolean var1);

    public void captureEvents(JSObject var1);

    public void releaseEvents(JSObject var1);

    public void routeEvent(JSObject var1);

    public void enableExternalCapture();

    public void disableExternalCapture();

    public void find();

    public void back();

    public void forward();

    public void home();

    public void stop();

    public void scroll(JSNumber var1, JSNumber var2);
}

