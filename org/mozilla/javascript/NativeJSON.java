/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeBoolean;
import org.mozilla.javascript.NativeNumber;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.NativeString;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.json.JsonParser;

public final class NativeJSON
extends IdScriptableObject {
    static final long serialVersionUID = -4567599697595654984L;
    private static final Object JSON_TAG = "JSON";
    private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
    private static final int Id_toSource = 1;
    private static final int Id_parse = 2;
    private static final int Id_stringify = 3;
    private static final int LAST_METHOD_ID = 3;
    private static final int MAX_ID = 3;

    static void init(Scriptable scope, boolean sealed) {
        NativeJSON obj = new NativeJSON();
        obj.activatePrototypeMap(3);
        obj.setPrototype(NativeJSON.getObjectPrototype(scope));
        obj.setParentScope(scope);
        if (sealed) {
            obj.sealObject();
        }
        ScriptableObject.defineProperty(scope, "JSON", obj, 2);
    }

    private NativeJSON() {
    }

    @Override
    public String getClassName() {
        return "JSON";
    }

    @Override
    protected void initPrototypeId(int id) {
        String name;
        int arity;
        if (id <= 3) {
            switch (id) {
                case 1: {
                    arity = 0;
                    name = "toSource";
                    break;
                }
                case 2: {
                    arity = 2;
                    name = "parse";
                    break;
                }
                case 3: {
                    arity = 3;
                    name = "stringify";
                    break;
                }
                default: {
                    throw new IllegalStateException(String.valueOf(id));
                }
            }
        } else {
            throw new IllegalStateException(String.valueOf(id));
        }
        this.initPrototypeMethod(JSON_TAG, id, name, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(JSON_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int methodId = f.methodId();
        switch (methodId) {
            case 1: {
                return "JSON";
            }
            case 2: {
                String jtext = ScriptRuntime.toString(args, 0);
                Object reviver = null;
                if (args.length > 1) {
                    reviver = args[1];
                }
                if (reviver instanceof Callable) {
                    return NativeJSON.parse(cx, scope, jtext, (Callable)reviver);
                }
                return NativeJSON.parse(cx, scope, jtext);
            }
            case 3: {
                Object value = null;
                Object replacer = null;
                Object space = null;
                switch (args.length) {
                    default: {
                        space = args[2];
                    }
                    case 2: {
                        replacer = args[1];
                    }
                    case 1: {
                        value = args[0];
                    }
                    case 0: 
                }
                return NativeJSON.stringify(cx, scope, value, replacer, space);
            }
        }
        throw new IllegalStateException(String.valueOf(methodId));
    }

    private static Object parse(Context cx, Scriptable scope, String jtext) {
        try {
            return new JsonParser(cx, scope).parseValue(jtext);
        } catch (JsonParser.ParseException ex) {
            throw ScriptRuntime.constructError("SyntaxError", ex.getMessage());
        }
    }

    public static Object parse(Context cx, Scriptable scope, String jtext, Callable reviver) {
        Object unfiltered = NativeJSON.parse(cx, scope, jtext);
        Scriptable root = cx.newObject(scope);
        root.put("", root, unfiltered);
        return NativeJSON.walk(cx, scope, reviver, root, "");
    }

    private static Object walk(Context cx, Scriptable scope, Callable reviver, Scriptable holder, Object name) {
        Object property;
        block10: {
            property = name instanceof Number ? holder.get(((Number)name).intValue(), holder) : holder.get((String)name, holder);
            if (!(property instanceof Scriptable)) break block10;
            Scriptable val = (Scriptable)property;
            if (val instanceof NativeArray) {
                long len = ((NativeArray)val).getLength();
                for (long i = 0L; i < len; ++i) {
                    Object newElement;
                    if (i > Integer.MAX_VALUE) {
                        String id = Long.toString(i);
                        newElement = NativeJSON.walk(cx, scope, reviver, val, id);
                        if (newElement == Undefined.instance) {
                            val.delete(id);
                            continue;
                        }
                        val.put(id, val, newElement);
                        continue;
                    }
                    int idx = (int)i;
                    newElement = NativeJSON.walk(cx, scope, reviver, val, idx);
                    if (newElement == Undefined.instance) {
                        val.delete(idx);
                        continue;
                    }
                    val.put(idx, val, newElement);
                }
            } else {
                Object[] keys;
                for (Object p : keys = val.getIds()) {
                    Object newElement = NativeJSON.walk(cx, scope, reviver, val, p);
                    if (newElement == Undefined.instance) {
                        if (p instanceof Number) {
                            val.delete(((Number)p).intValue());
                            continue;
                        }
                        val.delete((String)p);
                        continue;
                    }
                    if (p instanceof Number) {
                        val.put(((Number)p).intValue(), val, newElement);
                        continue;
                    }
                    val.put((String)p, val, newElement);
                }
            }
        }
        return reviver.call(cx, scope, holder, new Object[]{name, property});
    }

    private static String repeat(char c, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    public static Object stringify(Context cx, Scriptable scope, Object value, Object replacer, Object space) {
        String indent = "";
        String gap = "";
        LinkedList<Object> propertyList = null;
        Callable replacerFunction = null;
        if (replacer instanceof Callable) {
            replacerFunction = (Callable)replacer;
        } else if (replacer instanceof NativeArray) {
            propertyList = new LinkedList<Object>();
            NativeArray replacerArray = (NativeArray)replacer;
            Integer[] arr$ = replacerArray.getIndexIds();
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; ++i$) {
                int i = arr$[i$];
                Object v = replacerArray.get(i, (Scriptable)replacerArray);
                if (v instanceof String || v instanceof Number) {
                    propertyList.add(v);
                    continue;
                }
                if (!(v instanceof NativeString) && !(v instanceof NativeNumber)) continue;
                propertyList.add(ScriptRuntime.toString(v));
            }
        }
        if (space instanceof NativeNumber) {
            space = ScriptRuntime.toNumber(space);
        } else if (space instanceof NativeString) {
            space = ScriptRuntime.toString(space);
        }
        if (space instanceof Number) {
            int gapLength = (int)ScriptRuntime.toInteger(space);
            gap = (gapLength = Math.min(10, gapLength)) > 0 ? NativeJSON.repeat(' ', gapLength) : "";
            space = gapLength;
        } else if (space instanceof String && (gap = (String)space).length() > 10) {
            gap = gap.substring(0, 10);
        }
        StringifyState state = new StringifyState(cx, scope, indent, gap, replacerFunction, propertyList, space);
        NativeObject wrapper = new NativeObject();
        wrapper.setParentScope(scope);
        wrapper.setPrototype(ScriptableObject.getObjectPrototype(scope));
        wrapper.defineProperty("", value, 0);
        return NativeJSON.str("", wrapper, state);
    }

    private static Object str(Object key, Scriptable holder, StringifyState state) {
        Object toJSON;
        Object value = null;
        value = key instanceof String ? NativeJSON.getProperty(holder, (String)key) : NativeJSON.getProperty(holder, ((Number)key).intValue());
        if (value instanceof Scriptable && (toJSON = NativeJSON.getProperty((Scriptable)value, "toJSON")) instanceof Callable) {
            value = NativeJSON.callMethod(state.cx, (Scriptable)value, "toJSON", new Object[]{key});
        }
        if (state.replacer != null) {
            value = state.replacer.call(state.cx, state.scope, holder, new Object[]{key, value});
        }
        if (value instanceof NativeNumber) {
            value = ScriptRuntime.toNumber(value);
        } else if (value instanceof NativeString) {
            value = ScriptRuntime.toString(value);
        } else if (value instanceof NativeBoolean) {
            value = ((NativeBoolean)value).getDefaultValue(ScriptRuntime.BooleanClass);
        }
        if (value == null) {
            return "null";
        }
        if (value.equals(Boolean.TRUE)) {
            return "true";
        }
        if (value.equals(Boolean.FALSE)) {
            return "false";
        }
        if (value instanceof CharSequence) {
            return NativeJSON.quote(value.toString());
        }
        if (value instanceof Number) {
            double d = ((Number)value).doubleValue();
            if (d == d && d != Double.POSITIVE_INFINITY && d != Double.NEGATIVE_INFINITY) {
                return ScriptRuntime.toString(value);
            }
            return "null";
        }
        if (value instanceof Scriptable && !(value instanceof Callable)) {
            if (value instanceof NativeArray) {
                return NativeJSON.ja((NativeArray)value, state);
            }
            return NativeJSON.jo((Scriptable)value, state);
        }
        return Undefined.instance;
    }

    private static String join(Collection<Object> objs, String delimiter) {
        if (objs == null || objs.isEmpty()) {
            return "";
        }
        Iterator<Object> iter = objs.iterator();
        if (!iter.hasNext()) {
            return "";
        }
        StringBuilder builder = new StringBuilder(iter.next().toString());
        while (iter.hasNext()) {
            builder.append(delimiter).append(iter.next().toString());
        }
        return builder.toString();
    }

    private static String jo(Scriptable value, StringifyState state) {
        String finalValue;
        if (state.stack.search(value) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        state.stack.push(value);
        String stepback = state.indent;
        state.indent = state.indent + state.gap;
        Object[] k = null;
        k = state.propertyList != null ? state.propertyList.toArray() : value.getIds();
        LinkedList<Object> partial = new LinkedList<Object>();
        for (Object p : k) {
            Object strP = NativeJSON.str(p, value, state);
            if (strP == Undefined.instance) continue;
            String member = NativeJSON.quote(p.toString()) + ":";
            if (state.gap.length() > 0) {
                member = member + " ";
            }
            member = member + strP;
            partial.add(member);
        }
        if (partial.isEmpty()) {
            finalValue = "{}";
        } else if (state.gap.length() == 0) {
            finalValue = '{' + NativeJSON.join(partial, ",") + '}';
        } else {
            String separator = ",\n" + state.indent;
            String properties = NativeJSON.join(partial, separator);
            finalValue = "{\n" + state.indent + properties + '\n' + stepback + '}';
        }
        state.stack.pop();
        state.indent = stepback;
        return finalValue;
    }

    private static String ja(NativeArray value, StringifyState state) {
        String finalValue;
        if (state.stack.search(value) != -1) {
            throw ScriptRuntime.typeError0("msg.cyclic.value");
        }
        state.stack.push(value);
        String stepback = state.indent;
        state.indent = state.indent + state.gap;
        LinkedList<Object> partial = new LinkedList<Object>();
        long len = value.getLength();
        for (long index = 0L; index < len; ++index) {
            Object strP = index > Integer.MAX_VALUE ? NativeJSON.str(Long.toString(index), value, state) : NativeJSON.str((int)index, value, state);
            if (strP == Undefined.instance) {
                partial.add("null");
                continue;
            }
            partial.add(strP);
        }
        if (partial.isEmpty()) {
            finalValue = "[]";
        } else if (state.gap.length() == 0) {
            finalValue = '[' + NativeJSON.join(partial, ",") + ']';
        } else {
            String separator = ",\n" + state.indent;
            String properties = NativeJSON.join(partial, separator);
            finalValue = "[\n" + state.indent + properties + '\n' + stepback + ']';
        }
        state.stack.pop();
        state.indent = stepback;
        return finalValue;
    }

    private static String quote(String string) {
        StringBuilder product = new StringBuilder(string.length() + 2);
        product.append('\"');
        int length = string.length();
        block9: for (int i = 0; i < length; ++i) {
            char c = string.charAt(i);
            switch (c) {
                case '\"': {
                    product.append("\\\"");
                    continue block9;
                }
                case '\\': {
                    product.append("\\\\");
                    continue block9;
                }
                case '\b': {
                    product.append("\\b");
                    continue block9;
                }
                case '\f': {
                    product.append("\\f");
                    continue block9;
                }
                case '\n': {
                    product.append("\\n");
                    continue block9;
                }
                case '\r': {
                    product.append("\\r");
                    continue block9;
                }
                case '\t': {
                    product.append("\\t");
                    continue block9;
                }
                default: {
                    if (c < ' ') {
                        product.append("\\u");
                        String hex = String.format("%04x", c);
                        product.append(hex);
                        continue block9;
                    }
                    product.append(c);
                }
            }
        }
        product.append('\"');
        return product.toString();
    }

    @Override
    protected int findPrototypeId(String s) {
        int id = 0;
        String X = null;
        switch (s.length()) {
            case 5: {
                X = "parse";
                id = 2;
                break;
            }
            case 8: {
                X = "toSource";
                id = 1;
                break;
            }
            case 9: {
                X = "stringify";
                id = 3;
                break;
            }
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        return id;
    }

    private static class StringifyState {
        Stack<Scriptable> stack = new Stack();
        String indent;
        String gap;
        Callable replacer;
        List<Object> propertyList;
        Object space;
        Context cx;
        Scriptable scope;

        StringifyState(Context cx, Scriptable scope, String indent, String gap, Callable replacer, List<Object> propertyList, Object space) {
            this.cx = cx;
            this.scope = scope;
            this.indent = indent;
            this.gap = gap;
            this.replacer = replacer;
            this.propertyList = propertyList;
            this.space = space;
        }
    }
}

