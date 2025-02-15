/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

public class NativeObject
extends IdScriptableObject
implements Map {
    static final long serialVersionUID = -6345305608474346996L;
    private static final Object OBJECT_TAG = "Object";
    private static final int ConstructorId_getPrototypeOf = -1;
    private static final int ConstructorId_keys = -2;
    private static final int ConstructorId_getOwnPropertyNames = -3;
    private static final int ConstructorId_getOwnPropertyDescriptor = -4;
    private static final int ConstructorId_defineProperty = -5;
    private static final int ConstructorId_isExtensible = -6;
    private static final int ConstructorId_preventExtensions = -7;
    private static final int ConstructorId_defineProperties = -8;
    private static final int ConstructorId_create = -9;
    private static final int ConstructorId_isSealed = -10;
    private static final int ConstructorId_isFrozen = -11;
    private static final int ConstructorId_seal = -12;
    private static final int ConstructorId_freeze = -13;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toLocaleString = 3;
    private static final int Id_valueOf = 4;
    private static final int Id_hasOwnProperty = 5;
    private static final int Id_propertyIsEnumerable = 6;
    private static final int Id_isPrototypeOf = 7;
    private static final int Id_toSource = 8;
    private static final int Id___defineGetter__ = 9;
    private static final int Id___defineSetter__ = 10;
    private static final int Id___lookupGetter__ = 11;
    private static final int Id___lookupSetter__ = 12;
    private static final int MAX_PROTOTYPE_ID = 12;

    static void init(Scriptable scope, boolean sealed) {
        NativeObject obj = new NativeObject();
        obj.exportAsJSClass(12, scope, sealed);
    }

    @Override
    public String getClassName() {
        return "Object";
    }

    public String toString() {
        return ScriptRuntime.defaultObjectToString(this);
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -1, "getPrototypeOf", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -2, "keys", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -4, "getOwnPropertyDescriptor", 2);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -5, "defineProperty", 3);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -6, "isExtensible", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -7, "preventExtensions", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -8, "defineProperties", 2);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -9, "create", 2);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -10, "isSealed", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -11, "isFrozen", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -12, "seal", 1);
        this.addIdFunctionProperty(ctor, OBJECT_TAG, -13, "freeze", 1);
        super.fillConstructorProperties(ctor);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 1;
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
                s = "toLocaleString";
                break;
            }
            case 4: {
                arity = 0;
                s = "valueOf";
                break;
            }
            case 5: {
                arity = 1;
                s = "hasOwnProperty";
                break;
            }
            case 6: {
                arity = 1;
                s = "propertyIsEnumerable";
                break;
            }
            case 7: {
                arity = 1;
                s = "isPrototypeOf";
                break;
            }
            case 8: {
                arity = 0;
                s = "toSource";
                break;
            }
            case 9: {
                arity = 2;
                s = "__defineGetter__";
                break;
            }
            case 10: {
                arity = 2;
                s = "__defineSetter__";
                break;
            }
            case 11: {
                arity = 1;
                s = "__lookupGetter__";
                break;
            }
            case 12: {
                arity = 1;
                s = "__lookupSetter__";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(OBJECT_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(OBJECT_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                if (thisObj != null) {
                    return f.construct(cx, scope, args);
                }
                if (args.length == 0 || args[0] == null || args[0] == Undefined.instance) {
                    return new NativeObject();
                }
                return ScriptRuntime.toObject(cx, scope, args[0]);
            }
            case 3: {
                Object toString = ScriptableObject.getProperty(thisObj, "toString");
                if (!(toString instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(toString);
                }
                Callable fun = (Callable)toString;
                return fun.call(cx, scope, thisObj, ScriptRuntime.emptyArgs);
            }
            case 2: {
                if (cx.hasFeature(4)) {
                    String s = ScriptRuntime.defaultObjectToSource(cx, scope, thisObj, args);
                    int L = s.length();
                    if (L != 0 && s.charAt(0) == '(' && s.charAt(L - 1) == ')') {
                        s = s.substring(1, L - 1);
                    }
                    return s;
                }
                return ScriptRuntime.defaultObjectToString(thisObj);
            }
            case 4: {
                return thisObj;
            }
            case 5: {
                boolean result;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                String s = ScriptRuntime.toStringIdOrIndex(cx, arg);
                if (s == null) {
                    int index = ScriptRuntime.lastIndexResult(cx);
                    result = thisObj.has(index, thisObj);
                } else {
                    result = thisObj.has(s, thisObj);
                }
                return ScriptRuntime.wrapBoolean(result);
            }
            case 6: {
                boolean result;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                String s = ScriptRuntime.toStringIdOrIndex(cx, arg);
                if (s == null) {
                    int index = ScriptRuntime.lastIndexResult(cx);
                    result = thisObj.has(index, thisObj);
                    if (result && thisObj instanceof ScriptableObject) {
                        ScriptableObject so = (ScriptableObject)thisObj;
                        int attrs = so.getAttributes(index);
                        result = (attrs & 2) == 0;
                    }
                } else {
                    result = thisObj.has(s, thisObj);
                    if (result && thisObj instanceof ScriptableObject) {
                        ScriptableObject so = (ScriptableObject)thisObj;
                        int attrs = so.getAttributes(s);
                        result = (attrs & 2) == 0;
                    }
                }
                return ScriptRuntime.wrapBoolean(result);
            }
            case 7: {
                boolean result = false;
                if (args.length != 0 && args[0] instanceof Scriptable) {
                    Scriptable v = (Scriptable)args[0];
                    do {
                        if ((v = v.getPrototype()) != thisObj) continue;
                        result = true;
                        break;
                    } while (v != null);
                }
                return ScriptRuntime.wrapBoolean(result);
            }
            case 8: {
                return ScriptRuntime.defaultObjectToSource(cx, scope, thisObj, args);
            }
            case 9: 
            case 10: {
                if (args.length < 2 || !(args[1] instanceof Callable)) {
                    Object badArg = args.length >= 2 ? args[1] : Undefined.instance;
                    throw ScriptRuntime.notFunctionError(badArg);
                }
                if (!(thisObj instanceof ScriptableObject)) {
                    throw Context.reportRuntimeError2("msg.extend.scriptable", thisObj.getClass().getName(), String.valueOf(args[0]));
                }
                ScriptableObject so = (ScriptableObject)thisObj;
                String name = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
                int index = name != null ? 0 : ScriptRuntime.lastIndexResult(cx);
                Callable getterOrSetter = (Callable)args[1];
                boolean isSetter = id == 10;
                so.setGetterOrSetter(name, index, getterOrSetter, isSetter);
                if (so instanceof NativeArray) {
                    ((NativeArray)so).setDenseOnly(false);
                }
                return Undefined.instance;
            }
            case 11: 
            case 12: {
                Scriptable v;
                Object gs;
                boolean isSetter;
                if (args.length < 1 || !(thisObj instanceof ScriptableObject)) {
                    return Undefined.instance;
                }
                ScriptableObject so = (ScriptableObject)thisObj;
                String name = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
                int index = name != null ? 0 : ScriptRuntime.lastIndexResult(cx);
                boolean bl = isSetter = id == 12;
                while ((gs = so.getGetterOrSetter(name, index, isSetter)) == null && (v = so.getPrototype()) != null && v instanceof ScriptableObject) {
                    so = (ScriptableObject)v;
                }
                if (gs != null) {
                    return gs;
                }
                return Undefined.instance;
            }
            case -1: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                Scriptable obj = NativeObject.ensureScriptable(arg);
                return obj.getPrototype();
            }
            case -2: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                Scriptable obj = NativeObject.ensureScriptable(arg);
                Object[] ids = obj.getIds();
                for (int i = 0; i < ids.length; ++i) {
                    ids[i] = ScriptRuntime.toString(ids[i]);
                }
                return cx.newArray(scope, ids);
            }
            case -3: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                Object[] ids = obj.getAllIds();
                for (int i = 0; i < ids.length; ++i) {
                    ids[i] = ScriptRuntime.toString(ids[i]);
                }
                return cx.newArray(scope, ids);
            }
            case -4: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                Object nameArg = args.length < 2 ? Undefined.instance : args[1];
                String name = ScriptRuntime.toString(nameArg);
                ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
                return desc == null ? Undefined.instance : desc;
            }
            case -5: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                Object name = args.length < 2 ? Undefined.instance : args[1];
                Object descArg = args.length < 3 ? Undefined.instance : args[2];
                ScriptableObject desc = NativeObject.ensureScriptableObject(descArg);
                obj.defineOwnProperty(cx, name, desc);
                return obj;
            }
            case -6: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                return obj.isExtensible();
            }
            case -7: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                obj.preventExtensions();
                return obj;
            }
            case -8: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                Object propsObj = args.length < 2 ? Undefined.instance : args[1];
                Scriptable props = Context.toObject(propsObj, this.getParentScope());
                obj.defineOwnProperties(cx, NativeObject.ensureScriptableObject(props));
                return obj;
            }
            case -9: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                Scriptable obj = arg == null ? null : NativeObject.ensureScriptable(arg);
                NativeObject newObject = new NativeObject();
                newObject.setParentScope(this.getParentScope());
                newObject.setPrototype(obj);
                if (args.length > 1 && args[1] != Undefined.instance) {
                    Scriptable props = Context.toObject(args[1], this.getParentScope());
                    newObject.defineOwnProperties(cx, NativeObject.ensureScriptableObject(props));
                }
                return newObject;
            }
            case -10: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                if (obj.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object name : obj.getAllIds()) {
                    Object configurable = obj.getOwnPropertyDescriptor(cx, name).get("configurable");
                    if (!Boolean.TRUE.equals(configurable)) continue;
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
            case -11: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                if (obj.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object name : obj.getAllIds()) {
                    ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
                    if (Boolean.TRUE.equals(desc.get("configurable"))) {
                        return Boolean.FALSE;
                    }
                    if (!this.isDataDescriptor(desc) || !Boolean.TRUE.equals(desc.get("writable"))) continue;
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            }
            case -12: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                for (Object name : obj.getAllIds()) {
                    ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
                    if (!Boolean.TRUE.equals(desc.get("configurable"))) continue;
                    desc.put("configurable", (Scriptable)desc, (Object)Boolean.FALSE);
                    obj.defineOwnProperty(cx, name, desc, false);
                }
                obj.preventExtensions();
                return obj;
            }
            case -13: {
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                ScriptableObject obj = NativeObject.ensureScriptableObject(arg);
                for (Object name : obj.getAllIds()) {
                    ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
                    if (this.isDataDescriptor(desc) && Boolean.TRUE.equals(desc.get("writable"))) {
                        desc.put("writable", (Scriptable)desc, (Object)Boolean.FALSE);
                    }
                    if (Boolean.TRUE.equals(desc.get("configurable"))) {
                        desc.put("configurable", (Scriptable)desc, (Object)Boolean.FALSE);
                    }
                    obj.defineOwnProperty(cx, name, desc, false);
                }
                obj.preventExtensions();
                return obj;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            return this.has((String)key, (Scriptable)this);
        }
        if (key instanceof Number) {
            return this.has(((Number)key).intValue(), (Scriptable)this);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Object obj : this.values()) {
            if (value != obj && (value == null || !value.equals(obj))) continue;
            return true;
        }
        return false;
    }

    public Object remove(Object key) {
        Object value = this.get(key);
        if (key instanceof String) {
            this.delete((String)key);
        } else if (key instanceof Number) {
            this.delete(((Number)key).intValue());
        }
        return value;
    }

    public Set<Object> keySet() {
        return new KeySet();
    }

    public Collection<Object> values() {
        return new ValueCollection();
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block14: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 7: {
                    X = "valueOf";
                    id = 4;
                    break;
                }
                case 8: {
                    char c = s.charAt(3);
                    if (c == 'o') {
                        X = "toSource";
                        id = 8;
                        break;
                    }
                    if (c != 't') break;
                    X = "toString";
                    id = 2;
                    break;
                }
                case 11: {
                    X = "constructor";
                    id = 1;
                    break;
                }
                case 13: {
                    X = "isPrototypeOf";
                    id = 7;
                    break;
                }
                case 14: {
                    char c = s.charAt(0);
                    if (c == 'h') {
                        X = "hasOwnProperty";
                        id = 5;
                        break;
                    }
                    if (c != 't') break;
                    X = "toLocaleString";
                    id = 3;
                    break;
                }
                case 16: {
                    char c = s.charAt(2);
                    if (c == 'd') {
                        c = s.charAt(8);
                        if (c == 'G') {
                            X = "__defineGetter__";
                            id = 9;
                            break;
                        }
                        if (c != 'S') break;
                        X = "__defineSetter__";
                        id = 10;
                        break;
                    }
                    if (c != 'l') break;
                    c = s.charAt(8);
                    if (c == 'G') {
                        X = "__lookupGetter__";
                        id = 11;
                        break;
                    }
                    if (c != 'S') break;
                    X = "__lookupSetter__";
                    id = 12;
                    break;
                }
                case 20: {
                    X = "propertyIsEnumerable";
                    id = 6;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block14;
            id = 0;
        }
        return id;
    }

    class ValueCollection
    extends AbstractCollection<Object> {
        ValueCollection() {
        }

        @Override
        public Iterator<Object> iterator() {
            return new Iterator<Object>(){
                Object[] ids;
                Object key;
                int index;
                {
                    this.ids = NativeObject.this.getIds();
                    this.index = 0;
                }

                @Override
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override
                public Object next() {
                    this.key = this.ids[this.index++];
                    return NativeObject.this.get(this.key);
                }

                @Override
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }

    class KeySet
    extends AbstractSet<Object> {
        KeySet() {
        }

        @Override
        public boolean contains(Object key) {
            return NativeObject.this.containsKey(key);
        }

        @Override
        public Iterator<Object> iterator() {
            return new Iterator<Object>(){
                Object[] ids;
                Object key;
                int index;
                {
                    this.ids = NativeObject.this.getIds();
                    this.index = 0;
                }

                @Override
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override
                public Object next() {
                    try {
                        this.key = this.ids[this.index++];
                        return this.key;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.key = null;
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }

    class EntrySet
    extends AbstractSet<Map.Entry<Object, Object>> {
        EntrySet() {
        }

        @Override
        public Iterator<Map.Entry<Object, Object>> iterator() {
            return new Iterator<Map.Entry<Object, Object>>(){
                Object[] ids;
                Object key;
                int index;
                {
                    this.ids = NativeObject.this.getIds();
                    this.key = null;
                    this.index = 0;
                }

                @Override
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override
                public Map.Entry<Object, Object> next() {
                    final Object ekey = this.key = this.ids[this.index++];
                    final Object value = NativeObject.this.get(this.key);
                    return new Map.Entry<Object, Object>(){

                        @Override
                        public Object getKey() {
                            return ekey;
                        }

                        @Override
                        public Object getValue() {
                            return value;
                        }

                        @Override
                        public Object setValue(Object value2) {
                            throw new UnsupportedOperationException();
                        }

                        @Override
                        public boolean equals(Object other) {
                            if (!(other instanceof Map.Entry)) {
                                return false;
                            }
                            Map.Entry e = (Map.Entry)other;
                            return (ekey == null ? e.getKey() == null : ekey.equals(e.getKey())) && (value == null ? e.getValue() == null : value.equals(e.getValue()));
                        }

                        @Override
                        public int hashCode() {
                            return (ekey == null ? 0 : ekey.hashCode()) ^ (value == null ? 0 : value.hashCode());
                        }

                        public String toString() {
                            return ekey + "=" + value;
                        }
                    };
                }

                @Override
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }
}

