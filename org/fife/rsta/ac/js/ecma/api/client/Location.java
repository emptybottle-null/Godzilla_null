/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.client;

import org.fife.rsta.ac.js.ecma.api.client.funtions.LocationFunctions;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSFunction;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;

public abstract class Location
implements LocationFunctions {
    protected JSFunction constructor;
    public Location prototype;
    public Location location;
    public JSString hash;
    public JSString host;
    public JSString hostname;
    public JSString href;
    public JSString pathname;
    public JSString port;
    public JSString protocol;
    public JSString search;
}

