/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ecma.api.ecma3.functions;

import org.fife.rsta.ac.js.ecma.api.ecma3.JSNumber;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSObject;
import org.fife.rsta.ac.js.ecma.api.ecma3.JSString;
import org.fife.rsta.ac.js.ecma.api.ecma3.functions.JSObjectFunctions;

public interface JSDateFunctions
extends JSObjectFunctions {
    public JSString toDateString();

    public JSString toTimeString();

    @Override
    public JSString toLocaleString();

    public JSString toLocaleDateString();

    public JSString toLocaleTimeString();

    @Override
    public JSObject valueOf();

    public JSNumber getFullYear();

    public JSNumber getTime();

    public JSNumber getUTCFullYear();

    public JSNumber getMonth();

    public JSNumber getUTCMonth();

    public JSNumber getDate();

    public JSNumber getUTCDate();

    public JSNumber getDay();

    public JSNumber getUTCDay();

    public JSNumber getHours();

    public JSNumber getUTCHours();

    public JSNumber getMinutes();

    public JSNumber getUTCMinutes();

    public JSNumber getSeconds();

    public JSNumber getUTCSeconds();

    public JSNumber getMilliseconds();

    public JSNumber getUTCMilliseconds();

    public JSNumber getTimezoneOffset();

    public JSNumber setTime(JSNumber var1);

    public JSNumber setMilliseconds(JSNumber var1);

    public JSNumber setUTCMilliseconds(JSNumber var1);

    public JSNumber setSeconds(JSNumber var1, JSNumber var2);

    public JSNumber setUTCSeconds(JSNumber var1, JSNumber var2);

    public JSNumber setMinutes(JSNumber var1, JSNumber var2, JSNumber var3);

    public JSNumber setUTCMinute(JSNumber var1, JSNumber var2, JSNumber var3);

    public JSNumber setHours(JSNumber var1, JSNumber var2, JSNumber var3, JSNumber var4);

    public JSNumber setUTCHours(JSNumber var1, JSNumber var2, JSNumber var3, JSNumber var4);

    public JSNumber setDate(JSNumber var1);

    public JSNumber setUTCDate(JSNumber var1);

    public JSNumber setMonth(JSNumber var1, JSNumber var2);

    public JSNumber setUTCMonth(JSNumber var1, JSNumber var2);

    public JSNumber setFullYear(JSNumber var1, JSNumber var2, JSNumber var3);

    public JSNumber setUTCFullYear(JSNumber var1, JSNumber var2, JSNumber var3);

    public JSString toUTCString();
}

