/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

final class NativeDate
extends IdScriptableObject {
    static final long serialVersionUID = -8307438915861678966L;
    private static final Object DATE_TAG = "Date";
    private static final String js_NaN_date_str = "Invalid Date";
    private static final double HalfTimeDomain = 8.64E15;
    private static final double HoursPerDay = 24.0;
    private static final double MinutesPerHour = 60.0;
    private static final double SecondsPerMinute = 60.0;
    private static final double msPerSecond = 1000.0;
    private static final double MinutesPerDay = 1440.0;
    private static final double SecondsPerDay = 86400.0;
    private static final double SecondsPerHour = 3600.0;
    private static final double msPerDay = 8.64E7;
    private static final double msPerHour = 3600000.0;
    private static final double msPerMinute = 60000.0;
    private static final int MAXARGS = 7;
    private static final int ConstructorId_now = -3;
    private static final int ConstructorId_parse = -2;
    private static final int ConstructorId_UTC = -1;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toTimeString = 3;
    private static final int Id_toDateString = 4;
    private static final int Id_toLocaleString = 5;
    private static final int Id_toLocaleTimeString = 6;
    private static final int Id_toLocaleDateString = 7;
    private static final int Id_toUTCString = 8;
    private static final int Id_toSource = 9;
    private static final int Id_valueOf = 10;
    private static final int Id_getTime = 11;
    private static final int Id_getYear = 12;
    private static final int Id_getFullYear = 13;
    private static final int Id_getUTCFullYear = 14;
    private static final int Id_getMonth = 15;
    private static final int Id_getUTCMonth = 16;
    private static final int Id_getDate = 17;
    private static final int Id_getUTCDate = 18;
    private static final int Id_getDay = 19;
    private static final int Id_getUTCDay = 20;
    private static final int Id_getHours = 21;
    private static final int Id_getUTCHours = 22;
    private static final int Id_getMinutes = 23;
    private static final int Id_getUTCMinutes = 24;
    private static final int Id_getSeconds = 25;
    private static final int Id_getUTCSeconds = 26;
    private static final int Id_getMilliseconds = 27;
    private static final int Id_getUTCMilliseconds = 28;
    private static final int Id_getTimezoneOffset = 29;
    private static final int Id_setTime = 30;
    private static final int Id_setMilliseconds = 31;
    private static final int Id_setUTCMilliseconds = 32;
    private static final int Id_setSeconds = 33;
    private static final int Id_setUTCSeconds = 34;
    private static final int Id_setMinutes = 35;
    private static final int Id_setUTCMinutes = 36;
    private static final int Id_setHours = 37;
    private static final int Id_setUTCHours = 38;
    private static final int Id_setDate = 39;
    private static final int Id_setUTCDate = 40;
    private static final int Id_setMonth = 41;
    private static final int Id_setUTCMonth = 42;
    private static final int Id_setFullYear = 43;
    private static final int Id_setUTCFullYear = 44;
    private static final int Id_setYear = 45;
    private static final int Id_toISOString = 46;
    private static final int Id_toJSON = 47;
    private static final int MAX_PROTOTYPE_ID = 47;
    private static final int Id_toGMTString = 8;
    private static TimeZone thisTimeZone;
    private static double LocalTZA;
    private static DateFormat timeZoneFormatter;
    private static DateFormat localeDateTimeFormatter;
    private static DateFormat localeDateFormatter;
    private static DateFormat localeTimeFormatter;
    private double date;

    static void init(Scriptable scope, boolean sealed) {
        NativeDate obj = new NativeDate();
        obj.date = ScriptRuntime.NaN;
        obj.exportAsJSClass(47, scope, sealed);
    }

    private NativeDate() {
        if (thisTimeZone == null) {
            thisTimeZone = TimeZone.getDefault();
            LocalTZA = thisTimeZone.getRawOffset();
        }
    }

    @Override
    public String getClassName() {
        return "Date";
    }

    @Override
    public Object getDefaultValue(Class<?> typeHint) {
        if (typeHint == null) {
            typeHint = ScriptRuntime.StringClass;
        }
        return super.getDefaultValue(typeHint);
    }

    double getJSTimeValue() {
        return this.date;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        this.addIdFunctionProperty(ctor, DATE_TAG, -3, "now", 0);
        this.addIdFunctionProperty(ctor, DATE_TAG, -2, "parse", 1);
        this.addIdFunctionProperty(ctor, DATE_TAG, -1, "UTC", 7);
        super.fillConstructorProperties(ctor);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 7;
                s = "constructor";
                break;
            }
            case 2: {
                arity = 0;
                s = "toString";
                break;
            }
            case 3: {
                arity = 0;
                s = "toTimeString";
                break;
            }
            case 4: {
                arity = 0;
                s = "toDateString";
                break;
            }
            case 5: {
                arity = 0;
                s = "toLocaleString";
                break;
            }
            case 6: {
                arity = 0;
                s = "toLocaleTimeString";
                break;
            }
            case 7: {
                arity = 0;
                s = "toLocaleDateString";
                break;
            }
            case 8: {
                arity = 0;
                s = "toUTCString";
                break;
            }
            case 9: {
                arity = 0;
                s = "toSource";
                break;
            }
            case 10: {
                arity = 0;
                s = "valueOf";
                break;
            }
            case 11: {
                arity = 0;
                s = "getTime";
                break;
            }
            case 12: {
                arity = 0;
                s = "getYear";
                break;
            }
            case 13: {
                arity = 0;
                s = "getFullYear";
                break;
            }
            case 14: {
                arity = 0;
                s = "getUTCFullYear";
                break;
            }
            case 15: {
                arity = 0;
                s = "getMonth";
                break;
            }
            case 16: {
                arity = 0;
                s = "getUTCMonth";
                break;
            }
            case 17: {
                arity = 0;
                s = "getDate";
                break;
            }
            case 18: {
                arity = 0;
                s = "getUTCDate";
                break;
            }
            case 19: {
                arity = 0;
                s = "getDay";
                break;
            }
            case 20: {
                arity = 0;
                s = "getUTCDay";
                break;
            }
            case 21: {
                arity = 0;
                s = "getHours";
                break;
            }
            case 22: {
                arity = 0;
                s = "getUTCHours";
                break;
            }
            case 23: {
                arity = 0;
                s = "getMinutes";
                break;
            }
            case 24: {
                arity = 0;
                s = "getUTCMinutes";
                break;
            }
            case 25: {
                arity = 0;
                s = "getSeconds";
                break;
            }
            case 26: {
                arity = 0;
                s = "getUTCSeconds";
                break;
            }
            case 27: {
                arity = 0;
                s = "getMilliseconds";
                break;
            }
            case 28: {
                arity = 0;
                s = "getUTCMilliseconds";
                break;
            }
            case 29: {
                arity = 0;
                s = "getTimezoneOffset";
                break;
            }
            case 30: {
                arity = 1;
                s = "setTime";
                break;
            }
            case 31: {
                arity = 1;
                s = "setMilliseconds";
                break;
            }
            case 32: {
                arity = 1;
                s = "setUTCMilliseconds";
                break;
            }
            case 33: {
                arity = 2;
                s = "setSeconds";
                break;
            }
            case 34: {
                arity = 2;
                s = "setUTCSeconds";
                break;
            }
            case 35: {
                arity = 3;
                s = "setMinutes";
                break;
            }
            case 36: {
                arity = 3;
                s = "setUTCMinutes";
                break;
            }
            case 37: {
                arity = 4;
                s = "setHours";
                break;
            }
            case 38: {
                arity = 4;
                s = "setUTCHours";
                break;
            }
            case 39: {
                arity = 1;
                s = "setDate";
                break;
            }
            case 40: {
                arity = 1;
                s = "setUTCDate";
                break;
            }
            case 41: {
                arity = 2;
                s = "setMonth";
                break;
            }
            case 42: {
                arity = 2;
                s = "setUTCMonth";
                break;
            }
            case 43: {
                arity = 3;
                s = "setFullYear";
                break;
            }
            case 44: {
                arity = 3;
                s = "setUTCFullYear";
                break;
            }
            case 45: {
                arity = 1;
                s = "setYear";
                break;
            }
            case 46: {
                arity = 0;
                s = "toISOString";
                break;
            }
            case 47: {
                arity = 1;
                s = "toJSON";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(DATE_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(DATE_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case -3: {
                return ScriptRuntime.wrapNumber(NativeDate.now());
            }
            case -2: {
                String dataStr = ScriptRuntime.toString(args, 0);
                return ScriptRuntime.wrapNumber(NativeDate.date_parseString(dataStr));
            }
            case -1: {
                return ScriptRuntime.wrapNumber(NativeDate.jsStaticFunction_UTC(args));
            }
            case 1: {
                if (thisObj != null) {
                    return NativeDate.date_format(NativeDate.now(), 2);
                }
                return NativeDate.jsConstructor(args);
            }
            case 47: {
                double d;
                String toISOString = "toISOString";
                Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
                Object tv = ScriptRuntime.toPrimitive(o, ScriptRuntime.NumberClass);
                if (tv instanceof Number && ((d = ((Number)tv).doubleValue()) != d || Double.isInfinite(d))) {
                    return null;
                }
                Object toISO = ScriptableObject.getProperty(o, "toISOString");
                if (toISO == NOT_FOUND) {
                    throw ScriptRuntime.typeError2("msg.function.not.found.in", "toISOString", ScriptRuntime.toString(o));
                }
                if (!(toISO instanceof Callable)) {
                    throw ScriptRuntime.typeError3("msg.isnt.function.in", "toISOString", ScriptRuntime.toString(o), ScriptRuntime.toString(toISO));
                }
                Object result = ((Callable)toISO).call(cx, scope, o, ScriptRuntime.emptyArgs);
                if (!ScriptRuntime.isPrimitive(result)) {
                    throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive", ScriptRuntime.toString(result));
                }
                return result;
            }
        }
        if (!(thisObj instanceof NativeDate)) {
            throw NativeDate.incompatibleCallError(f);
        }
        NativeDate realThis = (NativeDate)thisObj;
        double t = realThis.date;
        switch (id) {
            case 2: 
            case 3: 
            case 4: {
                if (t == t) {
                    return NativeDate.date_format(t, id);
                }
                return js_NaN_date_str;
            }
            case 5: 
            case 6: 
            case 7: {
                if (t == t) {
                    return NativeDate.toLocale_helper(t, id);
                }
                return js_NaN_date_str;
            }
            case 8: {
                if (t == t) {
                    return NativeDate.js_toUTCString(t);
                }
                return js_NaN_date_str;
            }
            case 9: {
                return "(new Date(" + ScriptRuntime.toString(t) + "))";
            }
            case 10: 
            case 11: {
                return ScriptRuntime.wrapNumber(t);
            }
            case 12: 
            case 13: 
            case 14: {
                if (t == t) {
                    if (id != 14) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.YearFromTime(t);
                    if (id == 12) {
                        if (cx.hasFeature(1)) {
                            if (1900.0 <= t && t < 2000.0) {
                                t -= 1900.0;
                            }
                        } else {
                            t -= 1900.0;
                        }
                    }
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 15: 
            case 16: {
                if (t == t) {
                    if (id == 15) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.MonthFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 17: 
            case 18: {
                if (t == t) {
                    if (id == 17) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.DateFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 19: 
            case 20: {
                if (t == t) {
                    if (id == 19) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.WeekDay(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 21: 
            case 22: {
                if (t == t) {
                    if (id == 21) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.HourFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 23: 
            case 24: {
                if (t == t) {
                    if (id == 23) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.MinFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 25: 
            case 26: {
                if (t == t) {
                    if (id == 25) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.SecFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 27: 
            case 28: {
                if (t == t) {
                    if (id == 27) {
                        t = NativeDate.LocalTime(t);
                    }
                    t = NativeDate.msFromTime(t);
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 29: {
                if (t == t) {
                    t = (t - NativeDate.LocalTime(t)) / 60000.0;
                }
                return ScriptRuntime.wrapNumber(t);
            }
            case 30: {
                realThis.date = t = NativeDate.TimeClip(ScriptRuntime.toNumber(args, 0));
                return ScriptRuntime.wrapNumber(t);
            }
            case 31: 
            case 32: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: {
                realThis.date = t = NativeDate.makeTime(t, args, id);
                return ScriptRuntime.wrapNumber(t);
            }
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 43: 
            case 44: {
                realThis.date = t = NativeDate.makeDate(t, args, id);
                return ScriptRuntime.wrapNumber(t);
            }
            case 45: {
                double year = ScriptRuntime.toNumber(args, 0);
                if (year != year || Double.isInfinite(year)) {
                    t = ScriptRuntime.NaN;
                } else {
                    t = t != t ? 0.0 : NativeDate.LocalTime(t);
                    if (year >= 0.0 && year <= 99.0) {
                        year += 1900.0;
                    }
                    double day = NativeDate.MakeDay(year, NativeDate.MonthFromTime(t), NativeDate.DateFromTime(t));
                    t = NativeDate.MakeDate(day, NativeDate.TimeWithinDay(t));
                    t = NativeDate.internalUTC(t);
                    t = NativeDate.TimeClip(t);
                }
                realThis.date = t;
                return ScriptRuntime.wrapNumber(t);
            }
            case 46: {
                if (t == t) {
                    return NativeDate.js_toISOString(t);
                }
                String msg = ScriptRuntime.getMessage0("msg.invalid.date");
                throw ScriptRuntime.constructError("RangeError", msg);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static double Day(double t) {
        return Math.floor(t / 8.64E7);
    }

    private static double TimeWithinDay(double t) {
        double result = t % 8.64E7;
        if (result < 0.0) {
            result += 8.64E7;
        }
        return result;
    }

    private static boolean IsLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private static double DayFromYear(double y) {
        return 365.0 * (y - 1970.0) + Math.floor((y - 1969.0) / 4.0) - Math.floor((y - 1901.0) / 100.0) + Math.floor((y - 1601.0) / 400.0);
    }

    private static double TimeFromYear(double y) {
        return NativeDate.DayFromYear(y) * 8.64E7;
    }

    private static int YearFromTime(double t) {
        int lo = (int)Math.floor(t / 8.64E7 / 366.0) + 1970;
        int hi = (int)Math.floor(t / 8.64E7 / 365.0) + 1970;
        if (hi < lo) {
            int temp = lo;
            lo = hi;
            hi = temp;
        }
        while (hi > lo) {
            int mid = (hi + lo) / 2;
            if (NativeDate.TimeFromYear(mid) > t) {
                hi = mid - 1;
                continue;
            }
            lo = mid + 1;
            if (!(NativeDate.TimeFromYear(lo) > t)) continue;
            return mid;
        }
        return lo;
    }

    private static double DayFromMonth(int m, int year) {
        int day = m * 30;
        day = m >= 7 ? (day += m / 2 - 1) : (m >= 2 ? (day += (m - 1) / 2 - 1) : (day += m));
        if (m >= 2 && NativeDate.IsLeapYear(year)) {
            ++day;
        }
        return day;
    }

    private static int DaysInMonth(int year, int month) {
        if (month == 2) {
            return NativeDate.IsLeapYear(year) ? 29 : 28;
        }
        return month >= 8 ? 31 - (month & 1) : 30 + (month & 1);
    }

    private static int MonthFromTime(double t) {
        int mstart;
        int year = NativeDate.YearFromTime(t);
        int d = (int)(NativeDate.Day(t) - NativeDate.DayFromYear(year));
        if ((d -= 59) < 0) {
            return d < -28 ? 0 : 1;
        }
        if (NativeDate.IsLeapYear(year)) {
            if (d == 0) {
                return 1;
            }
            --d;
        }
        int estimate = d / 30;
        switch (estimate) {
            case 0: {
                return 2;
            }
            case 1: {
                mstart = 31;
                break;
            }
            case 2: {
                mstart = 61;
                break;
            }
            case 3: {
                mstart = 92;
                break;
            }
            case 4: {
                mstart = 122;
                break;
            }
            case 5: {
                mstart = 153;
                break;
            }
            case 6: {
                mstart = 184;
                break;
            }
            case 7: {
                mstart = 214;
                break;
            }
            case 8: {
                mstart = 245;
                break;
            }
            case 9: {
                mstart = 275;
                break;
            }
            case 10: {
                return 11;
            }
            default: {
                throw Kit.codeBug();
            }
        }
        return d >= mstart ? estimate + 2 : estimate + 1;
    }

    private static int DateFromTime(double t) {
        int mstart;
        int mdays;
        int year = NativeDate.YearFromTime(t);
        int d = (int)(NativeDate.Day(t) - NativeDate.DayFromYear(year));
        if ((d -= 59) < 0) {
            return d < -28 ? d + 31 + 28 + 1 : d + 28 + 1;
        }
        if (NativeDate.IsLeapYear(year)) {
            if (d == 0) {
                return 29;
            }
            --d;
        }
        switch (d / 30) {
            case 0: {
                return d + 1;
            }
            case 1: {
                mdays = 31;
                mstart = 31;
                break;
            }
            case 2: {
                mdays = 30;
                mstart = 61;
                break;
            }
            case 3: {
                mdays = 31;
                mstart = 92;
                break;
            }
            case 4: {
                mdays = 30;
                mstart = 122;
                break;
            }
            case 5: {
                mdays = 31;
                mstart = 153;
                break;
            }
            case 6: {
                mdays = 31;
                mstart = 184;
                break;
            }
            case 7: {
                mdays = 30;
                mstart = 214;
                break;
            }
            case 8: {
                mdays = 31;
                mstart = 245;
                break;
            }
            case 9: {
                mdays = 30;
                mstart = 275;
                break;
            }
            case 10: {
                return d - 275 + 1;
            }
            default: {
                throw Kit.codeBug();
            }
        }
        if ((d -= mstart) < 0) {
            d += mdays;
        }
        return d + 1;
    }

    private static int WeekDay(double t) {
        double result = NativeDate.Day(t) + 4.0;
        if ((result %= 7.0) < 0.0) {
            result += 7.0;
        }
        return (int)result;
    }

    private static double now() {
        return System.currentTimeMillis();
    }

    private static double DaylightSavingTA(double t) {
        Date date;
        if (t < 0.0) {
            int year = NativeDate.EquivalentYear(NativeDate.YearFromTime(t));
            double day = NativeDate.MakeDay(year, NativeDate.MonthFromTime(t), NativeDate.DateFromTime(t));
            t = NativeDate.MakeDate(day, NativeDate.TimeWithinDay(t));
        }
        if (thisTimeZone.inDaylightTime(date = new Date((long)t))) {
            return 3600000.0;
        }
        return 0.0;
    }

    private static int EquivalentYear(int year) {
        int day = (int)NativeDate.DayFromYear(year) + 4;
        if ((day %= 7) < 0) {
            day += 7;
        }
        if (NativeDate.IsLeapYear(year)) {
            switch (day) {
                case 0: {
                    return 1984;
                }
                case 1: {
                    return 1996;
                }
                case 2: {
                    return 1980;
                }
                case 3: {
                    return 1992;
                }
                case 4: {
                    return 1976;
                }
                case 5: {
                    return 1988;
                }
                case 6: {
                    return 1972;
                }
            }
        } else {
            switch (day) {
                case 0: {
                    return 1978;
                }
                case 1: {
                    return 1973;
                }
                case 2: {
                    return 1985;
                }
                case 3: {
                    return 1986;
                }
                case 4: {
                    return 1981;
                }
                case 5: {
                    return 1971;
                }
                case 6: {
                    return 1977;
                }
            }
        }
        throw Kit.codeBug();
    }

    private static double LocalTime(double t) {
        return t + LocalTZA + NativeDate.DaylightSavingTA(t);
    }

    private static double internalUTC(double t) {
        return t - LocalTZA - NativeDate.DaylightSavingTA(t - LocalTZA);
    }

    private static int HourFromTime(double t) {
        double result = Math.floor(t / 3600000.0) % 24.0;
        if (result < 0.0) {
            result += 24.0;
        }
        return (int)result;
    }

    private static int MinFromTime(double t) {
        double result = Math.floor(t / 60000.0) % 60.0;
        if (result < 0.0) {
            result += 60.0;
        }
        return (int)result;
    }

    private static int SecFromTime(double t) {
        double result = Math.floor(t / 1000.0) % 60.0;
        if (result < 0.0) {
            result += 60.0;
        }
        return (int)result;
    }

    private static int msFromTime(double t) {
        double result = t % 1000.0;
        if (result < 0.0) {
            result += 1000.0;
        }
        return (int)result;
    }

    private static double MakeTime(double hour, double min, double sec, double ms) {
        return ((hour * 60.0 + min) * 60.0 + sec) * 1000.0 + ms;
    }

    private static double MakeDay(double year, double month, double date) {
        year += Math.floor(month / 12.0);
        if ((month %= 12.0) < 0.0) {
            month += 12.0;
        }
        double yearday = Math.floor(NativeDate.TimeFromYear(year) / 8.64E7);
        double monthday = NativeDate.DayFromMonth((int)month, (int)year);
        return yearday + monthday + date - 1.0;
    }

    private static double MakeDate(double day, double time) {
        return day * 8.64E7 + time;
    }

    private static double TimeClip(double d) {
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || Math.abs(d) > 8.64E15) {
            return ScriptRuntime.NaN;
        }
        if (d > 0.0) {
            return Math.floor(d + 0.0);
        }
        return Math.ceil(d + 0.0);
    }

    private static double date_msecFromDate(double year, double mon, double mday, double hour, double min, double sec, double msec) {
        double day = NativeDate.MakeDay(year, mon, mday);
        double time = NativeDate.MakeTime(hour, min, sec, msec);
        double result = NativeDate.MakeDate(day, time);
        return result;
    }

    private static double date_msecFromArgs(Object[] args) {
        double[] array = new double[7];
        for (int loop = 0; loop < 7; ++loop) {
            if (loop < args.length) {
                double d = ScriptRuntime.toNumber(args[loop]);
                if (d != d || Double.isInfinite(d)) {
                    return ScriptRuntime.NaN;
                }
                array[loop] = ScriptRuntime.toInteger(args[loop]);
                continue;
            }
            array[loop] = loop == 2 ? 1.0 : 0.0;
        }
        if (array[0] >= 0.0 && array[0] <= 99.0) {
            array[0] = array[0] + 1900.0;
        }
        return NativeDate.date_msecFromDate(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
    }

    private static double jsStaticFunction_UTC(Object[] args) {
        return NativeDate.TimeClip(NativeDate.date_msecFromArgs(args));
    }

    private static double parseISOString(String s) {
        int ERROR = -1;
        boolean YEAR = false;
        boolean MONTH = true;
        int DAY = 2;
        int HOUR = 3;
        int MIN = 4;
        int SEC = 5;
        int MSEC = 6;
        int TZHOUR = 7;
        int TZMIN = 8;
        int state = 0;
        int[] values = new int[]{1970, 1, 1, 0, 0, 0, 0, -1, -1};
        int yearlen = 4;
        int yearmod = 1;
        int tzmod = 1;
        int i = 0;
        int len = s.length();
        if (len != 0) {
            char c = s.charAt(0);
            if (c == '+' || c == '-') {
                ++i;
                yearlen = 6;
                yearmod = c == '-' ? -1 : 1;
            } else if (c == 'T') {
                ++i;
                state = 3;
            }
        }
        block16: while (state != -1) {
            char c;
            int m = i + (state == 0 ? yearlen : (state == 6 ? 3 : 2));
            if (m > len) {
                state = -1;
                break;
            }
            int value = 0;
            while (i < m) {
                c = s.charAt(i);
                if (c < '0' || c > '9') {
                    state = -1;
                    break block16;
                }
                value = 10 * value + (c - 48);
                ++i;
            }
            values[state] = value;
            if (i == len) {
                switch (state) {
                    case 3: 
                    case 7: {
                        state = -1;
                    }
                }
                break;
            }
            if ((c = s.charAt(i++)) == 'Z') {
                values[7] = 0;
                values[8] = 0;
                switch (state) {
                    case 4: 
                    case 5: 
                    case 6: {
                        break;
                    }
                    default: {
                        state = -1;
                        break;
                    }
                }
                break;
            }
            switch (state) {
                case 0: 
                case 1: {
                    state = c == '-' ? state + 1 : (c == 'T' ? 3 : -1);
                    break;
                }
                case 2: {
                    state = c == 'T' ? 3 : -1;
                    break;
                }
                case 3: {
                    state = c == ':' ? 4 : -1;
                    break;
                }
                case 7: {
                    if (c != ':') {
                        --i;
                    }
                    state = 8;
                    break;
                }
                case 4: {
                    state = c == ':' ? 5 : (c == '+' || c == '-' ? 7 : -1);
                    break;
                }
                case 5: {
                    state = c == '.' ? 6 : (c == '+' || c == '-' ? 7 : -1);
                    break;
                }
                case 6: {
                    state = c == '+' || c == '-' ? 7 : -1;
                    break;
                }
                case 8: {
                    state = -1;
                }
            }
            if (state != 7) continue;
            tzmod = c == '-' ? -1 : 1;
        }
        if (state != -1 && i == len) {
            int year = values[0];
            int month = values[1];
            int day = values[2];
            int hour = values[3];
            int min = values[4];
            int sec = values[5];
            int msec = values[6];
            int tzhour = values[7];
            int tzmin = values[8];
            if (year <= 275943 && month >= 1 && month <= 12 && day >= 1 && day <= NativeDate.DaysInMonth(year, month) && hour <= 24 && (hour != 24 || min <= 0 && sec <= 0 && msec <= 0) && min <= 59 && sec <= 59 && tzhour <= 23 && tzmin <= 59) {
                double date = NativeDate.date_msecFromDate(year * yearmod, month - 1, day, hour, min, sec, msec);
                if (tzhour != -1) {
                    date -= (double)(tzhour * 60 + tzmin) * 60000.0 * (double)tzmod;
                }
                if (!(date < -8.64E15) && !(date > 8.64E15)) {
                    return date;
                }
            }
        }
        return ScriptRuntime.NaN;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static double date_parseString(String s) {
        double d = NativeDate.parseISOString(s);
        if (d == d) {
            return d;
        }
        int year = -1;
        int mon = -1;
        int mday = -1;
        int hour = -1;
        int min = -1;
        int sec = -1;
        char c = '\u0000';
        char si = '\u0000';
        int i = 0;
        int n = -1;
        double tzoffset = -1.0;
        char prevc = '\u0000';
        int limit = 0;
        boolean seenplusminus = false;
        limit = s.length();
        block13: while (i < limit) {
            c = s.charAt(i);
            ++i;
            if (c <= ' ' || c == ',' || c == '-') {
                if (i >= limit) continue;
                si = s.charAt(i);
                if (c != '-' || '0' > si || si > '9') continue;
                prevc = c;
                continue;
            }
            if (c == '(') {
                int depth = 1;
                while (i < limit) {
                    c = s.charAt(i);
                    ++i;
                    if (c == '(') {
                        ++depth;
                        continue;
                    }
                    if (c != ')' || --depth > 0) continue;
                    continue block13;
                }
                continue;
            }
            if ('0' <= c && c <= '9') {
                n = c - 48;
                while (i < limit && '0' <= (c = s.charAt(i)) && c <= '9') {
                    n = n * 10 + c - 48;
                    ++i;
                }
                if (prevc == '+' || prevc == '-') {
                    seenplusminus = true;
                    n = n < 24 ? (n *= 60) : n % 100 + n / 100 * 60;
                    if (prevc == '+') {
                        n = -n;
                    }
                    if (tzoffset != 0.0 && tzoffset != -1.0) {
                        return ScriptRuntime.NaN;
                    }
                    tzoffset = n;
                } else if (n >= 70 || prevc == '/' && mon >= 0 && mday >= 0 && year < 0) {
                    if (year >= 0) {
                        return ScriptRuntime.NaN;
                    }
                    if (c > ' ' && c != ',' && c != '/' && i < limit) return ScriptRuntime.NaN;
                    year = n < 100 ? n + 1900 : n;
                } else if (c == ':') {
                    if (hour < 0) {
                        hour = n;
                    } else {
                        if (min >= 0) return ScriptRuntime.NaN;
                        min = n;
                    }
                } else if (c == '/') {
                    if (mon < 0) {
                        mon = n - 1;
                    } else {
                        if (mday >= 0) return ScriptRuntime.NaN;
                        mday = n;
                    }
                } else {
                    if (i < limit && c != ',' && c > ' ' && c != '-') {
                        return ScriptRuntime.NaN;
                    }
                    if (seenplusminus && n < 60) {
                        tzoffset = tzoffset < 0.0 ? (tzoffset -= (double)n) : (tzoffset += (double)n);
                    } else if (hour >= 0 && min < 0) {
                        min = n;
                    } else if (min >= 0 && sec < 0) {
                        sec = n;
                    } else {
                        if (mday >= 0) return ScriptRuntime.NaN;
                        mday = n;
                    }
                }
                prevc = '\u0000';
                continue;
            }
            if (c == '/' || c == ':' || c == '+' || c == '-') {
                prevc = c;
                continue;
            }
            int st = i - 1;
            while (i < limit && ('A' <= (c = s.charAt(i)) && c <= 'Z' || 'a' <= c && c <= 'z')) {
                ++i;
            }
            int letterCount = i - st;
            if (letterCount < 2) {
                return ScriptRuntime.NaN;
            }
            String wtb = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;";
            int index = 0;
            int wtbOffset = 0;
            while (true) {
                int wtbNext;
                if ((wtbNext = wtb.indexOf(59, wtbOffset)) < 0) {
                    return ScriptRuntime.NaN;
                }
                if (wtb.regionMatches(true, wtbOffset, s, st, letterCount)) break;
                wtbOffset = wtbNext + 1;
                ++index;
            }
            if (index < 2) {
                if (hour > 12 || hour < 0) {
                    return ScriptRuntime.NaN;
                }
                if (index == 0) {
                    if (hour != 12) continue;
                    hour = 0;
                    continue;
                }
                if (hour == 12) continue;
                hour += 12;
                continue;
            }
            if ((index -= 2) < 7) continue;
            if ((index -= 7) < 12) {
                if (mon >= 0) return ScriptRuntime.NaN;
                mon = index;
                continue;
            }
            switch (index -= 12) {
                case 0: {
                    tzoffset = 0.0;
                    continue block13;
                }
                case 1: {
                    tzoffset = 0.0;
                    continue block13;
                }
                case 2: {
                    tzoffset = 0.0;
                    continue block13;
                }
                case 3: {
                    tzoffset = 300.0;
                    continue block13;
                }
                case 4: {
                    tzoffset = 240.0;
                    continue block13;
                }
                case 5: {
                    tzoffset = 360.0;
                    continue block13;
                }
                case 6: {
                    tzoffset = 300.0;
                    continue block13;
                }
                case 7: {
                    tzoffset = 420.0;
                    continue block13;
                }
                case 8: {
                    tzoffset = 360.0;
                    continue block13;
                }
                case 9: {
                    tzoffset = 480.0;
                    continue block13;
                }
                case 10: {
                    tzoffset = 420.0;
                    continue block13;
                }
            }
            Kit.codeBug();
        }
        if (year < 0 || mon < 0 || mday < 0) {
            return ScriptRuntime.NaN;
        }
        if (sec < 0) {
            sec = 0;
        }
        if (min < 0) {
            min = 0;
        }
        if (hour < 0) {
            hour = 0;
        }
        double msec = NativeDate.date_msecFromDate(year, mon, mday, hour, min, sec, 0.0);
        if (tzoffset != -1.0) return msec + tzoffset * 60000.0;
        return NativeDate.internalUTC(msec);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String date_format(double t, int methodId) {
        StringBuilder result = new StringBuilder(60);
        double local = NativeDate.LocalTime(t);
        if (methodId != 3) {
            NativeDate.appendWeekDayName(result, NativeDate.WeekDay(local));
            result.append(' ');
            NativeDate.appendMonthName(result, NativeDate.MonthFromTime(local));
            result.append(' ');
            NativeDate.append0PaddedUint(result, NativeDate.DateFromTime(local), 2);
            result.append(' ');
            int year = NativeDate.YearFromTime(local);
            if (year < 0) {
                result.append('-');
                year = -year;
            }
            NativeDate.append0PaddedUint(result, year, 4);
            if (methodId != 4) {
                result.append(' ');
            }
        }
        if (methodId != 4) {
            NativeDate.append0PaddedUint(result, NativeDate.HourFromTime(local), 2);
            result.append(':');
            NativeDate.append0PaddedUint(result, NativeDate.MinFromTime(local), 2);
            result.append(':');
            NativeDate.append0PaddedUint(result, NativeDate.SecFromTime(local), 2);
            int minutes = (int)Math.floor((LocalTZA + NativeDate.DaylightSavingTA(t)) / 60000.0);
            int offset = minutes / 60 * 100 + minutes % 60;
            if (offset > 0) {
                result.append(" GMT+");
            } else {
                result.append(" GMT-");
                offset = -offset;
            }
            NativeDate.append0PaddedUint(result, offset, 4);
            if (timeZoneFormatter == null) {
                timeZoneFormatter = new SimpleDateFormat("zzz");
            }
            if (t < 0.0) {
                int equiv = NativeDate.EquivalentYear(NativeDate.YearFromTime(local));
                double day = NativeDate.MakeDay(equiv, NativeDate.MonthFromTime(t), NativeDate.DateFromTime(t));
                t = NativeDate.MakeDate(day, NativeDate.TimeWithinDay(t));
            }
            result.append(" (");
            Date date = new Date((long)t);
            DateFormat dateFormat = timeZoneFormatter;
            synchronized (dateFormat) {
                result.append(timeZoneFormatter.format(date));
            }
            result.append(')');
        }
        return result.toString();
    }

    private static Object jsConstructor(Object[] args) {
        NativeDate obj = new NativeDate();
        if (args.length == 0) {
            obj.date = NativeDate.now();
            return obj;
        }
        if (args.length == 1) {
            Object arg0 = args[0];
            if (arg0 instanceof Scriptable) {
                arg0 = ((Scriptable)arg0).getDefaultValue(null);
            }
            double date = arg0 instanceof CharSequence ? NativeDate.date_parseString(arg0.toString()) : ScriptRuntime.toNumber(arg0);
            obj.date = NativeDate.TimeClip(date);
            return obj;
        }
        double time = NativeDate.date_msecFromArgs(args);
        if (!Double.isNaN(time) && !Double.isInfinite(time)) {
            time = NativeDate.TimeClip(NativeDate.internalUTC(time));
        }
        obj.date = time;
        return obj;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String toLocale_helper(double t, int methodId) {
        DateFormat formatter;
        switch (methodId) {
            case 5: {
                if (localeDateTimeFormatter == null) {
                    localeDateTimeFormatter = DateFormat.getDateTimeInstance(1, 1);
                }
                formatter = localeDateTimeFormatter;
                break;
            }
            case 6: {
                if (localeTimeFormatter == null) {
                    localeTimeFormatter = DateFormat.getTimeInstance(1);
                }
                formatter = localeTimeFormatter;
                break;
            }
            case 7: {
                if (localeDateFormatter == null) {
                    localeDateFormatter = DateFormat.getDateInstance(1);
                }
                formatter = localeDateFormatter;
                break;
            }
            default: {
                throw new AssertionError();
            }
        }
        DateFormat dateFormat = formatter;
        synchronized (dateFormat) {
            return formatter.format(new Date((long)t));
        }
    }

    private static String js_toUTCString(double date) {
        StringBuilder result = new StringBuilder(60);
        NativeDate.appendWeekDayName(result, NativeDate.WeekDay(date));
        result.append(", ");
        NativeDate.append0PaddedUint(result, NativeDate.DateFromTime(date), 2);
        result.append(' ');
        NativeDate.appendMonthName(result, NativeDate.MonthFromTime(date));
        result.append(' ');
        int year = NativeDate.YearFromTime(date);
        if (year < 0) {
            result.append('-');
            year = -year;
        }
        NativeDate.append0PaddedUint(result, year, 4);
        result.append(' ');
        NativeDate.append0PaddedUint(result, NativeDate.HourFromTime(date), 2);
        result.append(':');
        NativeDate.append0PaddedUint(result, NativeDate.MinFromTime(date), 2);
        result.append(':');
        NativeDate.append0PaddedUint(result, NativeDate.SecFromTime(date), 2);
        result.append(" GMT");
        return result.toString();
    }

    private static String js_toISOString(double t) {
        StringBuilder result = new StringBuilder(27);
        int year = NativeDate.YearFromTime(t);
        if (year < 0) {
            result.append('-');
            NativeDate.append0PaddedUint(result, -year, 6);
        } else if (year > 9999) {
            NativeDate.append0PaddedUint(result, year, 6);
        } else {
            NativeDate.append0PaddedUint(result, year, 4);
        }
        result.append('-');
        NativeDate.append0PaddedUint(result, NativeDate.MonthFromTime(t) + 1, 2);
        result.append('-');
        NativeDate.append0PaddedUint(result, NativeDate.DateFromTime(t), 2);
        result.append('T');
        NativeDate.append0PaddedUint(result, NativeDate.HourFromTime(t), 2);
        result.append(':');
        NativeDate.append0PaddedUint(result, NativeDate.MinFromTime(t), 2);
        result.append(':');
        NativeDate.append0PaddedUint(result, NativeDate.SecFromTime(t), 2);
        result.append('.');
        NativeDate.append0PaddedUint(result, NativeDate.msFromTime(t), 3);
        result.append('Z');
        return result.toString();
    }

    private static void append0PaddedUint(StringBuilder sb, int i, int minWidth) {
        if (i < 0) {
            Kit.codeBug();
        }
        int scale = 1;
        --minWidth;
        if (i >= 10) {
            if (i < 1000000000) {
                int newScale;
                while (i >= (newScale = scale * 10)) {
                    --minWidth;
                    scale = newScale;
                }
            } else {
                minWidth -= 9;
                scale = 1000000000;
            }
        }
        while (minWidth > 0) {
            sb.append('0');
            --minWidth;
        }
        while (scale != 1) {
            sb.append((char)(48 + i / scale));
            i %= scale;
            scale /= 10;
        }
        sb.append((char)(48 + i));
    }

    private static void appendMonthName(StringBuilder sb, int index) {
        String months = "JanFebMarAprMayJunJulAugSepOctNovDec";
        index *= 3;
        for (int i = 0; i != 3; ++i) {
            sb.append(months.charAt(index + i));
        }
    }

    private static void appendWeekDayName(StringBuilder sb, int index) {
        String days = "SunMonTueWedThuFriSat";
        index *= 3;
        for (int i = 0; i != 3; ++i) {
            sb.append(days.charAt(index + i));
        }
    }

    private static double makeTime(double date, Object[] args, int methodId) {
        int i;
        int numNums;
        int maxargs;
        if (args.length == 0) {
            return ScriptRuntime.NaN;
        }
        boolean local = true;
        switch (methodId) {
            case 32: {
                local = false;
            }
            case 31: {
                maxargs = 1;
                break;
            }
            case 34: {
                local = false;
            }
            case 33: {
                maxargs = 2;
                break;
            }
            case 36: {
                local = false;
            }
            case 35: {
                maxargs = 3;
                break;
            }
            case 38: {
                local = false;
            }
            case 37: {
                maxargs = 4;
                break;
            }
            default: {
                throw Kit.codeBug();
            }
        }
        boolean hasNaN = false;
        int n = numNums = args.length < maxargs ? args.length : maxargs;
        assert (numNums <= 4);
        double[] nums = new double[4];
        for (i = 0; i < numNums; ++i) {
            double d = ScriptRuntime.toNumber(args[i]);
            if (d != d || Double.isInfinite(d)) {
                hasNaN = true;
                continue;
            }
            nums[i] = ScriptRuntime.toInteger(d);
        }
        if (hasNaN || date != date) {
            return ScriptRuntime.NaN;
        }
        i = 0;
        int stop = numNums;
        double lorutime = local ? NativeDate.LocalTime(date) : date;
        double hour = maxargs >= 4 && i < stop ? nums[i++] : (double)NativeDate.HourFromTime(lorutime);
        double min = maxargs >= 3 && i < stop ? nums[i++] : (double)NativeDate.MinFromTime(lorutime);
        double sec = maxargs >= 2 && i < stop ? nums[i++] : (double)NativeDate.SecFromTime(lorutime);
        double msec = maxargs >= 1 && i < stop ? nums[i++] : (double)NativeDate.msFromTime(lorutime);
        double time = NativeDate.MakeTime(hour, min, sec, msec);
        double result = NativeDate.MakeDate(NativeDate.Day(lorutime), time);
        if (local) {
            result = NativeDate.internalUTC(result);
        }
        return NativeDate.TimeClip(result);
    }

    private static double makeDate(double date, Object[] args, int methodId) {
        double lorutime;
        int i;
        int numNums;
        int maxargs;
        if (args.length == 0) {
            return ScriptRuntime.NaN;
        }
        boolean local = true;
        switch (methodId) {
            case 40: {
                local = false;
            }
            case 39: {
                maxargs = 1;
                break;
            }
            case 42: {
                local = false;
            }
            case 41: {
                maxargs = 2;
                break;
            }
            case 44: {
                local = false;
            }
            case 43: {
                maxargs = 3;
                break;
            }
            default: {
                throw Kit.codeBug();
            }
        }
        boolean hasNaN = false;
        int n = numNums = args.length < maxargs ? args.length : maxargs;
        assert (1 <= numNums && numNums <= 3);
        double[] nums = new double[3];
        for (i = 0; i < numNums; ++i) {
            double d = ScriptRuntime.toNumber(args[i]);
            if (d != d || Double.isInfinite(d)) {
                hasNaN = true;
                continue;
            }
            nums[i] = ScriptRuntime.toInteger(d);
        }
        if (hasNaN) {
            return ScriptRuntime.NaN;
        }
        i = 0;
        int stop = numNums;
        if (date != date) {
            if (maxargs < 3) {
                return ScriptRuntime.NaN;
            }
            lorutime = 0.0;
        } else {
            lorutime = local ? NativeDate.LocalTime(date) : date;
        }
        double year = maxargs >= 3 && i < stop ? nums[i++] : (double)NativeDate.YearFromTime(lorutime);
        double month = maxargs >= 2 && i < stop ? nums[i++] : (double)NativeDate.MonthFromTime(lorutime);
        double day = maxargs >= 1 && i < stop ? nums[i++] : (double)NativeDate.DateFromTime(lorutime);
        day = NativeDate.MakeDay(year, month, day);
        double result = NativeDate.MakeDate(day, NativeDate.TimeWithinDay(lorutime));
        if (local) {
            result = NativeDate.internalUTC(result);
        }
        return NativeDate.TimeClip(result);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block59: {
            id = 0;
            String X = null;
            block0 : switch (s.length()) {
                case 6: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        X = "getDay";
                        id = 19;
                        break;
                    }
                    if (c != 't') break;
                    X = "toJSON";
                    id = 47;
                    break;
                }
                case 7: {
                    switch (s.charAt(3)) {
                        case 'D': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getDate";
                                id = 17;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setDate";
                            id = 39;
                            break block0;
                        }
                        case 'T': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getTime";
                                id = 11;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setTime";
                            id = 30;
                            break block0;
                        }
                        case 'Y': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getYear";
                                id = 12;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setYear";
                            id = 45;
                            break block0;
                        }
                        case 'u': {
                            X = "valueOf";
                            id = 10;
                            break block0;
                        }
                    }
                    break;
                }
                case 8: {
                    switch (s.charAt(3)) {
                        case 'H': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getHours";
                                id = 21;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setHours";
                            id = 37;
                            break block0;
                        }
                        case 'M': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getMonth";
                                id = 15;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setMonth";
                            id = 41;
                            break block0;
                        }
                        case 'o': {
                            X = "toSource";
                            id = 9;
                            break block0;
                        }
                        case 't': {
                            X = "toString";
                            id = 2;
                            break block0;
                        }
                    }
                    break;
                }
                case 9: {
                    X = "getUTCDay";
                    id = 20;
                    break;
                }
                case 10: {
                    char c = s.charAt(3);
                    if (c == 'M') {
                        c = s.charAt(0);
                        if (c == 'g') {
                            X = "getMinutes";
                            id = 23;
                            break;
                        }
                        if (c != 's') break;
                        X = "setMinutes";
                        id = 35;
                        break;
                    }
                    if (c == 'S') {
                        c = s.charAt(0);
                        if (c == 'g') {
                            X = "getSeconds";
                            id = 25;
                            break;
                        }
                        if (c != 's') break;
                        X = "setSeconds";
                        id = 33;
                        break;
                    }
                    if (c != 'U') break;
                    c = s.charAt(0);
                    if (c == 'g') {
                        X = "getUTCDate";
                        id = 18;
                        break;
                    }
                    if (c != 's') break;
                    X = "setUTCDate";
                    id = 40;
                    break;
                }
                case 11: {
                    switch (s.charAt(3)) {
                        case 'F': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                X = "getFullYear";
                                id = 13;
                                break block0;
                            }
                            if (c != 's') break block0;
                            X = "setFullYear";
                            id = 43;
                            break block0;
                        }
                        case 'M': {
                            X = "toGMTString";
                            id = 8;
                            break block0;
                        }
                        case 'S': {
                            X = "toISOString";
                            id = 46;
                            break block0;
                        }
                        case 'T': {
                            X = "toUTCString";
                            id = 8;
                            break block0;
                        }
                        case 'U': {
                            char c = s.charAt(0);
                            if (c == 'g') {
                                c = s.charAt(9);
                                if (c == 'r') {
                                    X = "getUTCHours";
                                    id = 22;
                                    break block0;
                                }
                                if (c != 't') break block0;
                                X = "getUTCMonth";
                                id = 16;
                                break block0;
                            }
                            if (c != 's') break block0;
                            c = s.charAt(9);
                            if (c == 'r') {
                                X = "setUTCHours";
                                id = 38;
                                break block0;
                            }
                            if (c != 't') break block0;
                            X = "setUTCMonth";
                            id = 42;
                            break block0;
                        }
                        case 's': {
                            X = "constructor";
                            id = 1;
                            break block0;
                        }
                    }
                    break;
                }
                case 12: {
                    char c = s.charAt(2);
                    if (c == 'D') {
                        X = "toDateString";
                        id = 4;
                        break;
                    }
                    if (c != 'T') break;
                    X = "toTimeString";
                    id = 3;
                    break;
                }
                case 13: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        c = s.charAt(6);
                        if (c == 'M') {
                            X = "getUTCMinutes";
                            id = 24;
                            break;
                        }
                        if (c != 'S') break;
                        X = "getUTCSeconds";
                        id = 26;
                        break;
                    }
                    if (c != 's') break;
                    c = s.charAt(6);
                    if (c == 'M') {
                        X = "setUTCMinutes";
                        id = 36;
                        break;
                    }
                    if (c != 'S') break;
                    X = "setUTCSeconds";
                    id = 34;
                    break;
                }
                case 14: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        X = "getUTCFullYear";
                        id = 14;
                        break;
                    }
                    if (c == 's') {
                        X = "setUTCFullYear";
                        id = 44;
                        break;
                    }
                    if (c != 't') break;
                    X = "toLocaleString";
                    id = 5;
                    break;
                }
                case 15: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        X = "getMilliseconds";
                        id = 27;
                        break;
                    }
                    if (c != 's') break;
                    X = "setMilliseconds";
                    id = 31;
                    break;
                }
                case 17: {
                    X = "getTimezoneOffset";
                    id = 29;
                    break;
                }
                case 18: {
                    char c = s.charAt(0);
                    if (c == 'g') {
                        X = "getUTCMilliseconds";
                        id = 28;
                        break;
                    }
                    if (c == 's') {
                        X = "setUTCMilliseconds";
                        id = 32;
                        break;
                    }
                    if (c != 't') break;
                    c = s.charAt(8);
                    if (c == 'D') {
                        X = "toLocaleDateString";
                        id = 7;
                        break;
                    }
                    if (c != 'T') break;
                    X = "toLocaleTimeString";
                    id = 6;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block59;
            id = 0;
        }
        return id;
    }
}

