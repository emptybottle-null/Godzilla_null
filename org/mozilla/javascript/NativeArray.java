/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.NativeString;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

public class NativeArray
extends IdScriptableObject
implements List {
    static final long serialVersionUID = 7331366857676127338L;
    private static final Object ARRAY_TAG = "Array";
    private static final Integer NEGATIVE_ONE = -1;
    private static final int Id_length = 1;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 4;
    private static final int Id_join = 5;
    private static final int Id_reverse = 6;
    private static final int Id_sort = 7;
    private static final int Id_push = 8;
    private static final int Id_pop = 9;
    private static final int Id_shift = 10;
    private static final int Id_unshift = 11;
    private static final int Id_splice = 12;
    private static final int Id_concat = 13;
    private static final int Id_slice = 14;
    private static final int Id_indexOf = 15;
    private static final int Id_lastIndexOf = 16;
    private static final int Id_every = 17;
    private static final int Id_filter = 18;
    private static final int Id_forEach = 19;
    private static final int Id_map = 20;
    private static final int Id_some = 21;
    private static final int Id_find = 22;
    private static final int Id_findIndex = 23;
    private static final int Id_reduce = 24;
    private static final int Id_reduceRight = 25;
    private static final int MAX_PROTOTYPE_ID = 25;
    private static final int ConstructorId_join = -5;
    private static final int ConstructorId_reverse = -6;
    private static final int ConstructorId_sort = -7;
    private static final int ConstructorId_push = -8;
    private static final int ConstructorId_pop = -9;
    private static final int ConstructorId_shift = -10;
    private static final int ConstructorId_unshift = -11;
    private static final int ConstructorId_splice = -12;
    private static final int ConstructorId_concat = -13;
    private static final int ConstructorId_slice = -14;
    private static final int ConstructorId_indexOf = -15;
    private static final int ConstructorId_lastIndexOf = -16;
    private static final int ConstructorId_every = -17;
    private static final int ConstructorId_filter = -18;
    private static final int ConstructorId_forEach = -19;
    private static final int ConstructorId_map = -20;
    private static final int ConstructorId_some = -21;
    private static final int ConstructorId_find = -22;
    private static final int ConstructorId_findIndex = -23;
    private static final int ConstructorId_reduce = -24;
    private static final int ConstructorId_reduceRight = -25;
    private static final int ConstructorId_isArray = -26;
    private long length;
    private int lengthAttr = 6;
    private Object[] dense;
    private boolean denseOnly;
    private static int maximumInitialCapacity = 10000;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private static final int MAX_PRE_GROW_SIZE = 0x55555554;

    static void init(Scriptable scope, boolean sealed) {
        NativeArray obj = new NativeArray(0L);
        obj.exportAsJSClass(25, scope, sealed);
    }

    static int getMaximumInitialCapacity() {
        return maximumInitialCapacity;
    }

    static void setMaximumInitialCapacity(int maximumInitialCapacity) {
        NativeArray.maximumInitialCapacity = maximumInitialCapacity;
    }

    public NativeArray(long lengthArg) {
        boolean bl = this.denseOnly = lengthArg <= (long)maximumInitialCapacity;
        if (this.denseOnly) {
            int intLength = (int)lengthArg;
            if (intLength < 10) {
                intLength = 10;
            }
            this.dense = new Object[intLength];
            Arrays.fill(this.dense, Scriptable.NOT_FOUND);
        }
        this.length = lengthArg;
    }

    public NativeArray(Object[] array) {
        this.denseOnly = true;
        this.dense = array;
        this.length = array.length;
    }

    @Override
    public String getClassName() {
        return "Array";
    }

    @Override
    protected int getMaxInstanceId() {
        return 1;
    }

    @Override
    protected void setInstanceIdAttributes(int id, int attr) {
        if (id == 1) {
            this.lengthAttr = attr;
        }
    }

    @Override
    protected int findInstanceIdInfo(String s) {
        if (s.equals("length")) {
            return NativeArray.instanceIdInfo(this.lengthAttr, 1);
        }
        return super.findInstanceIdInfo(s);
    }

    @Override
    protected String getInstanceIdName(int id) {
        if (id == 1) {
            return "length";
        }
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id) {
        if (id == 1) {
            return ScriptRuntime.wrapNumber(this.length);
        }
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value) {
        if (id == 1) {
            this.setLength(value);
            return;
        }
        super.setInstanceIdValue(id, value);
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -5, "join", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -6, "reverse", 0);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -7, "sort", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -8, "push", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -9, "pop", 0);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -10, "shift", 0);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -11, "unshift", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -12, "splice", 2);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -13, "concat", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -14, "slice", 2);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -15, "indexOf", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -16, "lastIndexOf", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -17, "every", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -18, "filter", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -19, "forEach", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -20, "map", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -21, "some", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -22, "find", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -23, "findIndex", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -24, "reduce", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -25, "reduceRight", 1);
        this.addIdFunctionProperty(ctor, ARRAY_TAG, -26, "isArray", 1);
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
                s = "toSource";
                break;
            }
            case 5: {
                arity = 1;
                s = "join";
                break;
            }
            case 6: {
                arity = 0;
                s = "reverse";
                break;
            }
            case 7: {
                arity = 1;
                s = "sort";
                break;
            }
            case 8: {
                arity = 1;
                s = "push";
                break;
            }
            case 9: {
                arity = 0;
                s = "pop";
                break;
            }
            case 10: {
                arity = 0;
                s = "shift";
                break;
            }
            case 11: {
                arity = 1;
                s = "unshift";
                break;
            }
            case 12: {
                arity = 2;
                s = "splice";
                break;
            }
            case 13: {
                arity = 1;
                s = "concat";
                break;
            }
            case 14: {
                arity = 2;
                s = "slice";
                break;
            }
            case 15: {
                arity = 1;
                s = "indexOf";
                break;
            }
            case 16: {
                arity = 1;
                s = "lastIndexOf";
                break;
            }
            case 17: {
                arity = 1;
                s = "every";
                break;
            }
            case 18: {
                arity = 1;
                s = "filter";
                break;
            }
            case 19: {
                arity = 1;
                s = "forEach";
                break;
            }
            case 20: {
                arity = 1;
                s = "map";
                break;
            }
            case 21: {
                arity = 1;
                s = "some";
                break;
            }
            case 22: {
                arity = 1;
                s = "find";
                break;
            }
            case 23: {
                arity = 1;
                s = "findIndex";
                break;
            }
            case 24: {
                arity = 1;
                s = "reduce";
                break;
            }
            case 25: {
                arity = 1;
                s = "reduceRight";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(ARRAY_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(ARRAY_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        block22: while (true) {
            switch (id) {
                case -25: 
                case -24: 
                case -23: 
                case -22: 
                case -21: 
                case -20: 
                case -19: 
                case -18: 
                case -17: 
                case -16: 
                case -15: 
                case -14: 
                case -13: 
                case -12: 
                case -11: 
                case -10: 
                case -9: 
                case -8: 
                case -7: 
                case -6: 
                case -5: {
                    if (args.length > 0) {
                        thisObj = ScriptRuntime.toObject(cx, scope, args[0]);
                        Object[] newArgs = new Object[args.length - 1];
                        for (int i = 0; i < newArgs.length; ++i) {
                            newArgs[i] = args[i + 1];
                        }
                        args = newArgs;
                    }
                    id = -id;
                    continue block22;
                }
                case -26: {
                    return args.length > 0 && NativeArray.js_isArray(args[0]);
                }
                case 1: {
                    boolean inNewExpr;
                    boolean bl = inNewExpr = thisObj == null;
                    if (!inNewExpr) {
                        return f.construct(cx, scope, args);
                    }
                    return NativeArray.jsConstructor(cx, scope, args);
                }
                case 2: {
                    return NativeArray.toStringHelper(cx, scope, thisObj, cx.hasFeature(4), false);
                }
                case 3: {
                    return NativeArray.toStringHelper(cx, scope, thisObj, false, true);
                }
                case 4: {
                    return NativeArray.toStringHelper(cx, scope, thisObj, true, false);
                }
                case 5: {
                    return NativeArray.js_join(cx, thisObj, args);
                }
                case 6: {
                    return NativeArray.js_reverse(cx, thisObj, args);
                }
                case 7: {
                    return NativeArray.js_sort(cx, scope, thisObj, args);
                }
                case 8: {
                    return NativeArray.js_push(cx, thisObj, args);
                }
                case 9: {
                    return NativeArray.js_pop(cx, thisObj, args);
                }
                case 10: {
                    return NativeArray.js_shift(cx, thisObj, args);
                }
                case 11: {
                    return NativeArray.js_unshift(cx, thisObj, args);
                }
                case 12: {
                    return NativeArray.js_splice(cx, scope, thisObj, args);
                }
                case 13: {
                    return NativeArray.js_concat(cx, scope, thisObj, args);
                }
                case 14: {
                    return this.js_slice(cx, thisObj, args);
                }
                case 15: {
                    return NativeArray.js_indexOf(cx, thisObj, args);
                }
                case 16: {
                    return NativeArray.js_lastIndexOf(cx, thisObj, args);
                }
                case 17: 
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: {
                    return NativeArray.iterativeMethod(cx, id, scope, thisObj, args);
                }
                case 24: 
                case 25: {
                    return NativeArray.reduceMethod(cx, id, scope, thisObj, args);
                }
            }
            break;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    @Override
    public Object get(int index, Scriptable start) {
        if (!this.denseOnly && this.isGetterOrSetter(null, index, false)) {
            return super.get(index, start);
        }
        if (this.dense != null && 0 <= index && index < this.dense.length) {
            return this.dense[index];
        }
        return super.get(index, start);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        if (!this.denseOnly && this.isGetterOrSetter(null, index, false)) {
            return super.has(index, start);
        }
        if (this.dense != null && 0 <= index && index < this.dense.length) {
            return this.dense[index] != NOT_FOUND;
        }
        return super.has(index, start);
    }

    private static long toArrayIndex(Object id) {
        if (id instanceof String) {
            return NativeArray.toArrayIndex((String)id);
        }
        if (id instanceof Number) {
            return NativeArray.toArrayIndex(((Number)id).doubleValue());
        }
        return -1L;
    }

    private static long toArrayIndex(String id) {
        long index = NativeArray.toArrayIndex(ScriptRuntime.toNumber(id));
        if (Long.toString(index).equals(id)) {
            return index;
        }
        return -1L;
    }

    private static long toArrayIndex(double d) {
        long index;
        if (d == d && (double)(index = ScriptRuntime.toUint32(d)) == d && index != 0xFFFFFFFFL) {
            return index;
        }
        return -1L;
    }

    private static int toDenseIndex(Object id) {
        long index = NativeArray.toArrayIndex(id);
        return 0L <= index && index < Integer.MAX_VALUE ? (int)index : -1;
    }

    @Override
    public void put(String id, Scriptable start, Object value) {
        long index;
        super.put(id, start, value);
        if (start == this && (index = NativeArray.toArrayIndex(id)) >= this.length) {
            this.length = index + 1L;
            this.denseOnly = false;
        }
    }

    private boolean ensureCapacity(int capacity) {
        if (capacity > this.dense.length) {
            if (capacity > 0x55555554) {
                this.denseOnly = false;
                return false;
            }
            capacity = Math.max(capacity, (int)((double)this.dense.length * 1.5));
            Object[] newDense = new Object[capacity];
            System.arraycopy(this.dense, 0, newDense, 0, this.dense.length);
            Arrays.fill(newDense, this.dense.length, newDense.length, Scriptable.NOT_FOUND);
            this.dense = newDense;
        }
        return true;
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
        if (!(start != this || this.isSealed() || this.dense == null || 0 > index || !this.denseOnly && this.isGetterOrSetter(null, index, true))) {
            if (!this.isExtensible() && this.length <= (long)index) {
                return;
            }
            if (index < this.dense.length) {
                this.dense[index] = value;
                if (this.length <= (long)index) {
                    this.length = (long)index + 1L;
                }
                return;
            }
            if (this.denseOnly && (double)index < (double)this.dense.length * 1.5 && this.ensureCapacity(index + 1)) {
                this.dense[index] = value;
                this.length = (long)index + 1L;
                return;
            }
            this.denseOnly = false;
        }
        super.put(index, start, value);
        if (start == this && (this.lengthAttr & 1) == 0 && this.length <= (long)index) {
            this.length = (long)index + 1L;
        }
    }

    @Override
    public void delete(int index) {
        if (!(this.dense == null || 0 > index || index >= this.dense.length || this.isSealed() || !this.denseOnly && this.isGetterOrSetter(null, index, true))) {
            this.dense[index] = NOT_FOUND;
        } else {
            super.delete(index);
        }
    }

    @Override
    public Object[] getIds() {
        Object[] superIds = super.getIds();
        if (this.dense == null) {
            return superIds;
        }
        int N = this.dense.length;
        long currentLength = this.length;
        if ((long)N > currentLength) {
            N = (int)currentLength;
        }
        if (N == 0) {
            return superIds;
        }
        int superLength = superIds.length;
        Object[] ids = new Object[N + superLength];
        int presentCount = 0;
        for (int i = 0; i != N; ++i) {
            if (this.dense[i] == NOT_FOUND) continue;
            ids[presentCount] = i;
            ++presentCount;
        }
        if (presentCount != N) {
            Object[] tmp = new Object[presentCount + superLength];
            System.arraycopy(ids, 0, tmp, 0, presentCount);
            ids = tmp;
        }
        System.arraycopy(superIds, 0, ids, presentCount, superLength);
        return ids;
    }

    @Override
    public Object[] getAllIds() {
        LinkedHashSet<Object> allIds = new LinkedHashSet<Object>(Arrays.asList(this.getIds()));
        allIds.addAll(Arrays.asList(super.getAllIds()));
        return allIds.toArray();
    }

    public Integer[] getIndexIds() {
        Object[] ids = this.getIds();
        ArrayList<Integer> indices = new ArrayList<Integer>(ids.length);
        for (Object id : ids) {
            int int32Id = ScriptRuntime.toInt32(id);
            if (int32Id < 0 || !ScriptRuntime.toString(int32Id).equals(ScriptRuntime.toString(id))) continue;
            indices.add(int32Id);
        }
        return indices.toArray(new Integer[indices.size()]);
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
        Context cx;
        if (hint == ScriptRuntime.NumberClass && (cx = Context.getContext()).getLanguageVersion() == 120) {
            return this.length;
        }
        return super.getDefaultValue(hint);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object value) {
        Scriptable scope = this.getParentScope();
        if (scope == null) {
            scope = this;
        }
        NativeObject desc = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
        desc.defineProperty("value", value, 0);
        desc.defineProperty("writable", true, 0);
        desc.defineProperty("enumerable", true, 0);
        desc.defineProperty("configurable", true, 0);
        return desc;
    }

    @Override
    public int getAttributes(int index) {
        if (this.dense != null && index >= 0 && index < this.dense.length && this.dense[index] != NOT_FOUND) {
            return 0;
        }
        return super.getAttributes(index);
    }

    @Override
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
        int index;
        if (this.dense != null && 0 <= (index = NativeArray.toDenseIndex(id)) && index < this.dense.length && this.dense[index] != NOT_FOUND) {
            Object value = this.dense[index];
            return this.defaultIndexPropertyDescriptor(value);
        }
        return super.getOwnPropertyDescriptor(cx, id);
    }

    @Override
    protected void defineOwnProperty(Context cx, Object id, ScriptableObject desc, boolean checkValid) {
        long index;
        if (this.dense != null) {
            Object[] values = this.dense;
            this.dense = null;
            this.denseOnly = false;
            for (int i = 0; i < values.length; ++i) {
                if (values[i] == NOT_FOUND) continue;
                this.put(i, (Scriptable)this, values[i]);
            }
        }
        if ((index = NativeArray.toArrayIndex(id)) >= this.length) {
            this.length = index + 1L;
        }
        super.defineOwnProperty(cx, id, desc, checkValid);
    }

    private static Object jsConstructor(Context cx, Scriptable scope, Object[] args) {
        if (args.length == 0) {
            return new NativeArray(0L);
        }
        if (cx.getLanguageVersion() == 120) {
            return new NativeArray(args);
        }
        Object arg0 = args[0];
        if (args.length > 1 || !(arg0 instanceof Number)) {
            return new NativeArray(args);
        }
        long len = ScriptRuntime.toUint32(arg0);
        if ((double)len != ((Number)arg0).doubleValue()) {
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.constructError("RangeError", msg);
        }
        return new NativeArray(len);
    }

    public long getLength() {
        return this.length;
    }

    @Deprecated
    public long jsGet_length() {
        return this.getLength();
    }

    void setDenseOnly(boolean denseOnly) {
        if (denseOnly && !this.denseOnly) {
            throw new IllegalArgumentException();
        }
        this.denseOnly = denseOnly;
    }

    private void setLength(Object val) {
        if ((this.lengthAttr & 1) != 0) {
            return;
        }
        double d = ScriptRuntime.toNumber(val);
        long longVal = ScriptRuntime.toUint32(d);
        if ((double)longVal != d) {
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.constructError("RangeError", msg);
        }
        if (this.denseOnly) {
            if (longVal < this.length) {
                Arrays.fill(this.dense, (int)longVal, this.dense.length, NOT_FOUND);
                this.length = longVal;
                return;
            }
            if (longVal < 0x55555554L && (double)longVal < (double)this.length * 1.5 && this.ensureCapacity((int)longVal)) {
                this.length = longVal;
                return;
            }
            this.denseOnly = false;
        }
        if (longVal < this.length) {
            if (this.length - longVal > 4096L) {
                Object[] e = this.getIds();
                for (int i = 0; i < e.length; ++i) {
                    Object id = e[i];
                    if (id instanceof String) {
                        String strId = (String)id;
                        long index = NativeArray.toArrayIndex(strId);
                        if (index < longVal) continue;
                        this.delete(strId);
                        continue;
                    }
                    int index = (Integer)id;
                    if ((long)index < longVal) continue;
                    this.delete(index);
                }
            } else {
                for (long i = longVal; i < this.length; ++i) {
                    NativeArray.deleteElem(this, i);
                }
            }
        }
        this.length = longVal;
    }

    static long getLengthProperty(Context cx, Scriptable obj) {
        if (obj instanceof NativeString) {
            return ((NativeString)obj).getLength();
        }
        if (obj instanceof NativeArray) {
            return ((NativeArray)obj).getLength();
        }
        Object len = ScriptableObject.getProperty(obj, "length");
        if (len == Scriptable.NOT_FOUND) {
            return 0L;
        }
        return ScriptRuntime.toUint32(len);
    }

    private static Object setLengthProperty(Context cx, Scriptable target, long length) {
        Number len = ScriptRuntime.wrapNumber(length);
        ScriptableObject.putProperty(target, "length", (Object)len);
        return len;
    }

    private static void deleteElem(Scriptable target, long index) {
        int i = (int)index;
        if ((long)i == index) {
            target.delete(i);
        } else {
            target.delete(Long.toString(index));
        }
    }

    private static Object getElem(Context cx, Scriptable target, long index) {
        Object elem = NativeArray.getRawElem(target, index);
        return elem != Scriptable.NOT_FOUND ? elem : Undefined.instance;
    }

    private static Object getRawElem(Scriptable target, long index) {
        if (index > Integer.MAX_VALUE) {
            return ScriptableObject.getProperty(target, Long.toString(index));
        }
        return ScriptableObject.getProperty(target, (int)index);
    }

    private static void defineElem(Context cx, Scriptable target, long index, Object value) {
        if (index > Integer.MAX_VALUE) {
            String id = Long.toString(index);
            target.put(id, target, value);
        } else {
            target.put((int)index, target, value);
        }
    }

    private static void setElem(Context cx, Scriptable target, long index, Object value) {
        if (index > Integer.MAX_VALUE) {
            String id = Long.toString(index);
            ScriptableObject.putProperty(target, id, value);
        } else {
            ScriptableObject.putProperty(target, (int)index, value);
        }
    }

    private static void setRawElem(Context cx, Scriptable target, long index, Object value) {
        if (value == NOT_FOUND) {
            NativeArray.deleteElem(target, index);
        } else {
            NativeArray.setElem(cx, target, index, value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String toStringHelper(Context cx, Scriptable scope, Scriptable thisObj, boolean toSource, boolean toLocale) {
        boolean iterating;
        boolean toplevel;
        String separator;
        long length = NativeArray.getLengthProperty(cx, thisObj);
        StringBuilder result = new StringBuilder(256);
        if (toSource) {
            result.append('[');
            separator = ", ";
        } else {
            separator = ",";
        }
        boolean haslast = false;
        long i = 0L;
        if (cx.iterating == null) {
            toplevel = true;
            iterating = false;
            cx.iterating = new ObjToIntMap(31);
        } else {
            toplevel = false;
            iterating = cx.iterating.has(thisObj);
        }
        try {
            if (!iterating) {
                cx.iterating.put(thisObj, 0);
                boolean skipUndefinedAndNull = !toSource || cx.getLanguageVersion() < 150;
                for (i = 0L; i < length; ++i) {
                    Object elem;
                    if (i > 0L) {
                        result.append(separator);
                    }
                    if ((elem = NativeArray.getRawElem(thisObj, i)) == NOT_FOUND || skipUndefinedAndNull && (elem == null || elem == Undefined.instance)) {
                        haslast = false;
                        continue;
                    }
                    haslast = true;
                    if (toSource) {
                        result.append(ScriptRuntime.uneval(cx, scope, elem));
                        continue;
                    }
                    if (elem instanceof String) {
                        String s = (String)elem;
                        if (toSource) {
                            result.append('\"');
                            result.append(ScriptRuntime.escapeString(s));
                            result.append('\"');
                            continue;
                        }
                        result.append(s);
                        continue;
                    }
                    if (toLocale) {
                        Callable fun = ScriptRuntime.getPropFunctionAndThis(elem, "toLocaleString", cx, scope);
                        Scriptable funThis = ScriptRuntime.lastStoredScriptable(cx);
                        elem = fun.call(cx, scope, funThis, ScriptRuntime.emptyArgs);
                    }
                    result.append(ScriptRuntime.toString(elem));
                }
            }
        } finally {
            if (toplevel) {
                cx.iterating = null;
            }
        }
        if (toSource) {
            if (!haslast && i > 0L) {
                result.append(", ]");
            } else {
                result.append(']');
            }
        }
        return result.toString();
    }

    private static String js_join(Context cx, Scriptable thisObj, Object[] args) {
        String str;
        String separator;
        int length;
        long llength = NativeArray.getLengthProperty(cx, thisObj);
        if (llength != (long)(length = (int)llength)) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(llength));
        }
        String string = separator = args.length < 1 || args[0] == Undefined.instance ? "," : ScriptRuntime.toString(args[0]);
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; ++i) {
                    Object temp;
                    if (i != 0) {
                        sb.append(separator);
                    }
                    if (i >= na.dense.length || (temp = na.dense[i]) == null || temp == Undefined.instance || temp == Scriptable.NOT_FOUND) continue;
                    sb.append(ScriptRuntime.toString(temp));
                }
                return sb.toString();
            }
        }
        if (length == 0) {
            return "";
        }
        String[] buf = new String[length];
        int total_size = 0;
        for (int i = 0; i != length; ++i) {
            Object temp = NativeArray.getElem(cx, thisObj, i);
            if (temp == null || temp == Undefined.instance) continue;
            str = ScriptRuntime.toString(temp);
            total_size += str.length();
            buf[i] = str;
        }
        StringBuilder sb = new StringBuilder(total_size += (length - 1) * separator.length());
        for (int i = 0; i != length; ++i) {
            if (i != 0) {
                sb.append(separator);
            }
            if ((str = buf[i]) == null) continue;
            sb.append(str);
        }
        return sb.toString();
    }

    private static Scriptable js_reverse(Context cx, Scriptable thisObj, Object[] args) {
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly) {
                int i = 0;
                for (int j = (int)na.length - 1; i < j; ++i, --j) {
                    Object temp = na.dense[i];
                    na.dense[i] = na.dense[j];
                    na.dense[j] = temp;
                }
                return thisObj;
            }
        }
        long len = NativeArray.getLengthProperty(cx, thisObj);
        long half = len / 2L;
        for (long i = 0L; i < half; ++i) {
            long j = len - i - 1L;
            Object temp1 = NativeArray.getRawElem(thisObj, i);
            Object temp2 = NativeArray.getRawElem(thisObj, j);
            NativeArray.setRawElem(cx, thisObj, i, temp2);
            NativeArray.setRawElem(cx, thisObj, j, temp1);
        }
        return thisObj;
    }

    private static Scriptable js_sort(final Context cx, final Scriptable scope, Scriptable thisObj, Object[] args) {
        int i;
        Comparator<Object> comparator;
        if (args.length > 0 && Undefined.instance != args[0]) {
            final Callable jsCompareFunction = ScriptRuntime.getValueFunctionAndThis(args[0], cx);
            final Scriptable funThis = ScriptRuntime.lastStoredScriptable(cx);
            final Object[] cmpBuf = new Object[2];
            comparator = new Comparator<Object>(){

                @Override
                public int compare(Object x, Object y) {
                    if (x == Scriptable.NOT_FOUND) {
                        return y == Scriptable.NOT_FOUND ? 0 : 1;
                    }
                    if (y == Scriptable.NOT_FOUND) {
                        return -1;
                    }
                    if (x == Undefined.instance) {
                        return y == Undefined.instance ? 0 : 1;
                    }
                    if (y == Undefined.instance) {
                        return -1;
                    }
                    cmpBuf[0] = x;
                    cmpBuf[1] = y;
                    Object ret = jsCompareFunction.call(cx, scope, funThis, cmpBuf);
                    double d = ScriptRuntime.toNumber(ret);
                    if (d < 0.0) {
                        return -1;
                    }
                    if (d > 0.0) {
                        return 1;
                    }
                    return 0;
                }
            };
        } else {
            comparator = new Comparator<Object>(){

                @Override
                public int compare(Object x, Object y) {
                    if (x == Scriptable.NOT_FOUND) {
                        return y == Scriptable.NOT_FOUND ? 0 : 1;
                    }
                    if (y == Scriptable.NOT_FOUND) {
                        return -1;
                    }
                    if (x == Undefined.instance) {
                        return y == Undefined.instance ? 0 : 1;
                    }
                    if (y == Undefined.instance) {
                        return -1;
                    }
                    String a = ScriptRuntime.toString(x);
                    String b = ScriptRuntime.toString(y);
                    return a.compareTo(b);
                }
            };
        }
        long llength = NativeArray.getLengthProperty(cx, thisObj);
        int length = (int)llength;
        if (llength != (long)length) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(llength));
        }
        Object[] working = new Object[length];
        for (i = 0; i != length; ++i) {
            working[i] = NativeArray.getRawElem(thisObj, i);
        }
        Arrays.sort(working, comparator);
        for (i = 0; i < length; ++i) {
            NativeArray.setRawElem(cx, thisObj, i, working[i]);
        }
        return thisObj;
    }

    private static Object js_push(Context cx, Scriptable thisObj, Object[] args) {
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly && na.ensureCapacity((int)na.length + args.length)) {
                for (int i = 0; i < args.length; ++i) {
                    na.dense[(int)na.length++] = args[i];
                }
                return ScriptRuntime.wrapNumber(na.length);
            }
        }
        long length = NativeArray.getLengthProperty(cx, thisObj);
        for (int i = 0; i < args.length; ++i) {
            NativeArray.setElem(cx, thisObj, length + (long)i, args[i]);
        }
        Object lengthObj = NativeArray.setLengthProperty(cx, thisObj, length += (long)args.length);
        if (cx.getLanguageVersion() == 120) {
            return args.length == 0 ? Undefined.instance : args[args.length - 1];
        }
        return lengthObj;
    }

    private static Object js_pop(Context cx, Scriptable thisObj, Object[] args) {
        Object result;
        long length;
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly && na.length > 0L) {
                --na.length;
                Object result2 = na.dense[(int)na.length];
                na.dense[(int)na.length] = NOT_FOUND;
                return result2;
            }
        }
        if ((length = NativeArray.getLengthProperty(cx, thisObj)) > 0L) {
            result = NativeArray.getElem(cx, thisObj, --length);
            NativeArray.deleteElem(thisObj, length);
        } else {
            result = Undefined.instance;
        }
        NativeArray.setLengthProperty(cx, thisObj, length);
        return result;
    }

    private static Object js_shift(Context cx, Scriptable thisObj, Object[] args) {
        Object result;
        long length;
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly && na.length > 0L) {
                --na.length;
                Object result2 = na.dense[0];
                System.arraycopy(na.dense, 1, na.dense, 0, (int)na.length);
                na.dense[(int)na.length] = NOT_FOUND;
                return result2 == NOT_FOUND ? Undefined.instance : result2;
            }
        }
        if ((length = NativeArray.getLengthProperty(cx, thisObj)) > 0L) {
            long i = 0L;
            result = NativeArray.getElem(cx, thisObj, i);
            if (--length > 0L) {
                for (i = 1L; i <= length; ++i) {
                    Object temp = NativeArray.getRawElem(thisObj, i);
                    NativeArray.setRawElem(cx, thisObj, i - 1L, temp);
                }
            }
            NativeArray.deleteElem(thisObj, length);
        } else {
            result = Undefined.instance;
        }
        NativeArray.setLengthProperty(cx, thisObj, length);
        return result;
    }

    private static Object js_unshift(Context cx, Scriptable thisObj, Object[] args) {
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly && na.ensureCapacity((int)na.length + args.length)) {
                System.arraycopy(na.dense, 0, na.dense, args.length, (int)na.length);
                for (int i = 0; i < args.length; ++i) {
                    na.dense[i] = args[i];
                }
                na.length += (long)args.length;
                return ScriptRuntime.wrapNumber(na.length);
            }
        }
        long length = NativeArray.getLengthProperty(cx, thisObj);
        int argc = args.length;
        if (args.length > 0) {
            if (length > 0L) {
                for (long last = length - 1L; last >= 0L; --last) {
                    Object temp = NativeArray.getRawElem(thisObj, last);
                    NativeArray.setRawElem(cx, thisObj, last + (long)argc, temp);
                }
            }
            for (int i = 0; i < args.length; ++i) {
                NativeArray.setElem(cx, thisObj, i, args[i]);
            }
        }
        return NativeArray.setLengthProperty(cx, thisObj, length += (long)args.length);
    }

    private static Object js_splice(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object temp;
        long last;
        Object result;
        long count;
        NativeArray na = null;
        boolean denseMode = false;
        if (thisObj instanceof NativeArray) {
            na = (NativeArray)thisObj;
            denseMode = na.denseOnly;
        }
        scope = NativeArray.getTopLevelScope(scope);
        int argc = args.length;
        if (argc == 0) {
            return cx.newArray(scope, 0);
        }
        long length = NativeArray.getLengthProperty(cx, thisObj);
        long begin = NativeArray.toSliceIndex(ScriptRuntime.toInteger(args[0]), length);
        --argc;
        if (args.length == 1) {
            count = length - begin;
        } else {
            double dcount = ScriptRuntime.toInteger(args[1]);
            count = dcount < 0.0 ? 0L : (dcount > (double)(length - begin) ? length - begin : (long)dcount);
            --argc;
        }
        long end = begin + count;
        if (count != 0L) {
            if (count == 1L && cx.getLanguageVersion() == 120) {
                result = NativeArray.getElem(cx, thisObj, begin);
            } else if (denseMode) {
                int intLen = (int)(end - begin);
                Object[] copy = new Object[intLen];
                System.arraycopy(na.dense, (int)begin, copy, 0, intLen);
                result = cx.newArray(scope, copy);
            } else {
                Scriptable resultArray = cx.newArray(scope, 0);
                for (long last2 = begin; last2 != end; ++last2) {
                    Object temp2 = NativeArray.getRawElem(thisObj, last2);
                    if (temp2 == NOT_FOUND) continue;
                    NativeArray.setElem(cx, resultArray, last2 - begin, temp2);
                }
                NativeArray.setLengthProperty(cx, resultArray, end - begin);
                result = resultArray;
            }
        } else {
            result = cx.getLanguageVersion() == 120 ? Undefined.instance : cx.newArray(scope, 0);
        }
        long delta = (long)argc - count;
        if (denseMode && length + delta < Integer.MAX_VALUE && na.ensureCapacity((int)(length + delta))) {
            System.arraycopy(na.dense, (int)end, na.dense, (int)(begin + (long)argc), (int)(length - end));
            if (argc > 0) {
                System.arraycopy(args, 2, na.dense, (int)begin, argc);
            }
            if (delta < 0L) {
                Arrays.fill(na.dense, (int)(length + delta), (int)length, NOT_FOUND);
            }
            na.length = length + delta;
            return result;
        }
        if (delta > 0L) {
            for (last = length - 1L; last >= end; --last) {
                temp = NativeArray.getRawElem(thisObj, last);
                NativeArray.setRawElem(cx, thisObj, last + delta, temp);
            }
        } else if (delta < 0L) {
            for (last = end; last < length; ++last) {
                temp = NativeArray.getRawElem(thisObj, last);
                NativeArray.setRawElem(cx, thisObj, last + delta, temp);
            }
            for (long k = length + delta; k < length; ++k) {
                NativeArray.deleteElem(thisObj, k);
            }
        }
        int argoffset = args.length - argc;
        for (int i = 0; i < argc; ++i) {
            NativeArray.setElem(cx, thisObj, begin + (long)i, args[i + argoffset]);
        }
        NativeArray.setLengthProperty(cx, thisObj, length + delta);
        return result;
    }

    private static Scriptable js_concat(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable arg;
        int i;
        scope = NativeArray.getTopLevelScope(scope);
        Scriptable result = cx.newArray(scope, 0);
        if (thisObj instanceof NativeArray && result instanceof NativeArray) {
            NativeArray denseThis = (NativeArray)thisObj;
            NativeArray denseResult = (NativeArray)result;
            if (denseThis.denseOnly && denseResult.denseOnly) {
                boolean canUseDense = true;
                int length = (int)denseThis.length;
                for (i = 0; i < args.length && canUseDense; ++i) {
                    if (args[i] instanceof NativeArray) {
                        arg = (NativeArray)args[i];
                        canUseDense = ((NativeArray)arg).denseOnly;
                        length = (int)((long)length + ((NativeArray)arg).length);
                        continue;
                    }
                    ++length;
                }
                if (canUseDense && denseResult.ensureCapacity(length)) {
                    System.arraycopy(denseThis.dense, 0, denseResult.dense, 0, (int)denseThis.length);
                    int cursor = (int)denseThis.length;
                    for (int i2 = 0; i2 < args.length && canUseDense; ++i2) {
                        if (args[i2] instanceof NativeArray) {
                            NativeArray arg2 = (NativeArray)args[i2];
                            System.arraycopy(arg2.dense, 0, denseResult.dense, cursor, (int)arg2.length);
                            cursor += (int)arg2.length;
                            continue;
                        }
                        denseResult.dense[cursor++] = args[i2];
                    }
                    denseResult.length = length;
                    return result;
                }
            }
        }
        long slot = 0L;
        if (NativeArray.js_isArray(thisObj)) {
            long length = NativeArray.getLengthProperty(cx, thisObj);
            for (slot = 0L; slot < length; ++slot) {
                Object temp = NativeArray.getRawElem(thisObj, slot);
                if (temp == NOT_FOUND) continue;
                NativeArray.defineElem(cx, result, slot, temp);
            }
        } else {
            NativeArray.defineElem(cx, result, slot++, thisObj);
        }
        for (i = 0; i < args.length; ++i) {
            if (NativeArray.js_isArray(args[i])) {
                arg = (Scriptable)args[i];
                long length = NativeArray.getLengthProperty(cx, arg);
                long j = 0L;
                while (j < length) {
                    Object temp = NativeArray.getRawElem(arg, j);
                    if (temp != NOT_FOUND) {
                        NativeArray.defineElem(cx, result, slot, temp);
                    }
                    ++j;
                    ++slot;
                }
                continue;
            }
            NativeArray.defineElem(cx, result, slot++, args[i]);
        }
        NativeArray.setLengthProperty(cx, result, slot);
        return result;
    }

    private Scriptable js_slice(Context cx, Scriptable thisObj, Object[] args) {
        long end;
        long begin;
        Scriptable scope = NativeArray.getTopLevelScope(this);
        Scriptable result = cx.newArray(scope, 0);
        long length = NativeArray.getLengthProperty(cx, thisObj);
        if (args.length == 0) {
            begin = 0L;
            end = length;
        } else {
            begin = NativeArray.toSliceIndex(ScriptRuntime.toInteger(args[0]), length);
            end = args.length == 1 || args[1] == Undefined.instance ? length : NativeArray.toSliceIndex(ScriptRuntime.toInteger(args[1]), length);
        }
        for (long slot = begin; slot < end; ++slot) {
            Object temp = NativeArray.getRawElem(thisObj, slot);
            if (temp == NOT_FOUND) continue;
            NativeArray.defineElem(cx, result, slot - begin, temp);
        }
        NativeArray.setLengthProperty(cx, result, Math.max(0L, end - begin));
        return result;
    }

    private static long toSliceIndex(double value, long length) {
        long result = value < 0.0 ? (value + (double)length < 0.0 ? 0L : (long)(value + (double)length)) : (value > (double)length ? length : (long)value);
        return result;
    }

    private static Object js_indexOf(Context cx, Scriptable thisObj, Object[] args) {
        long start;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
        long length = NativeArray.getLengthProperty(cx, thisObj);
        if (args.length < 2) {
            start = 0L;
        } else {
            start = (long)ScriptRuntime.toInteger(args[1]);
            if (start < 0L && (start += length) < 0L) {
                start = 0L;
            }
            if (start > length - 1L) {
                return NEGATIVE_ONE;
            }
        }
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly) {
                Scriptable proto = na.getPrototype();
                int i = (int)start;
                while ((long)i < length) {
                    Object val = na.dense[i];
                    if (val == NOT_FOUND && proto != null) {
                        val = ScriptableObject.getProperty(proto, i);
                    }
                    if (val != NOT_FOUND && ScriptRuntime.shallowEq(val, compareTo)) {
                        return (long)i;
                    }
                    ++i;
                }
                return NEGATIVE_ONE;
            }
        }
        for (long i = start; i < length; ++i) {
            Object val = NativeArray.getRawElem(thisObj, i);
            if (val == NOT_FOUND || !ScriptRuntime.shallowEq(val, compareTo)) continue;
            return i;
        }
        return NEGATIVE_ONE;
    }

    private static Object js_lastIndexOf(Context cx, Scriptable thisObj, Object[] args) {
        long start;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
        long length = NativeArray.getLengthProperty(cx, thisObj);
        if (args.length < 2) {
            start = length - 1L;
        } else {
            start = (long)ScriptRuntime.toInteger(args[1]);
            if (start >= length) {
                start = length - 1L;
            } else if (start < 0L) {
                start += length;
            }
            if (start < 0L) {
                return NEGATIVE_ONE;
            }
        }
        if (thisObj instanceof NativeArray) {
            NativeArray na = (NativeArray)thisObj;
            if (na.denseOnly) {
                Scriptable proto = na.getPrototype();
                for (int i = (int)start; i >= 0; --i) {
                    Object val = na.dense[i];
                    if (val == NOT_FOUND && proto != null) {
                        val = ScriptableObject.getProperty(proto, i);
                    }
                    if (val == NOT_FOUND || !ScriptRuntime.shallowEq(val, compareTo)) continue;
                    return (long)i;
                }
                return NEGATIVE_ONE;
            }
        }
        for (long i = start; i >= 0L; --i) {
            Object val = NativeArray.getRawElem(thisObj, i);
            if (val == NOT_FOUND || !ScriptRuntime.shallowEq(val, compareTo)) continue;
            return i;
        }
        return NEGATIVE_ONE;
    }

    private static Object iterativeMethod(Context cx, int id, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object callbackArg;
        long length = NativeArray.getLengthProperty(cx, thisObj);
        Object object = callbackArg = args.length > 0 ? args[0] : Undefined.instance;
        if (callbackArg == null || !(callbackArg instanceof Function)) {
            throw ScriptRuntime.notFunctionError(callbackArg);
        }
        if (!(id != 22 && id != 23 || callbackArg instanceof NativeFunction)) {
            throw ScriptRuntime.notFunctionError(callbackArg);
        }
        Function f = (Function)callbackArg;
        Scriptable parent = ScriptableObject.getTopLevelScope(f);
        Scriptable thisArg = args.length < 2 || args[1] == null || args[1] == Undefined.instance ? parent : ScriptRuntime.toObject(cx, scope, args[1]);
        if ((22 == id || 23 == id) && thisArg == thisObj) {
            throw ScriptRuntime.typeError("Array.prototype method called on null or undefined");
        }
        Scriptable array = null;
        if (id == 18 || id == 20) {
            int resultLength = id == 20 ? (int)length : 0;
            array = cx.newArray(scope, resultLength);
        }
        long j = 0L;
        block15: for (long i = 0L; i < length; ++i) {
            Object[] innerArgs = new Object[3];
            Object elem = NativeArray.getRawElem(thisObj, i);
            if (elem == Scriptable.NOT_FOUND) continue;
            innerArgs[0] = elem;
            innerArgs[1] = i;
            innerArgs[2] = thisObj;
            Object result = f.call(cx, parent, thisArg, innerArgs);
            switch (id) {
                case 17: {
                    if (ScriptRuntime.toBoolean(result)) continue block15;
                    return Boolean.FALSE;
                }
                case 18: {
                    if (!ScriptRuntime.toBoolean(result)) continue block15;
                    NativeArray.defineElem(cx, array, j++, innerArgs[0]);
                    continue block15;
                }
                case 19: {
                    continue block15;
                }
                case 20: {
                    NativeArray.defineElem(cx, array, i, result);
                    continue block15;
                }
                case 21: {
                    if (!ScriptRuntime.toBoolean(result)) continue block15;
                    return Boolean.TRUE;
                }
                case 22: {
                    if (!ScriptRuntime.toBoolean(result)) continue block15;
                    return elem;
                }
                case 23: {
                    if (!ScriptRuntime.toBoolean(result)) continue block15;
                    return ScriptRuntime.wrapNumber(i);
                }
            }
        }
        switch (id) {
            case 17: {
                return Boolean.TRUE;
            }
            case 18: 
            case 20: {
                return array;
            }
            case 21: {
                return Boolean.FALSE;
            }
            case 23: {
                return ScriptRuntime.wrapNumber(-1.0);
            }
        }
        return Undefined.instance;
    }

    private static Object reduceMethod(Context cx, int id, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object callbackArg;
        long length = NativeArray.getLengthProperty(cx, thisObj);
        Object object = callbackArg = args.length > 0 ? args[0] : Undefined.instance;
        if (callbackArg == null || !(callbackArg instanceof Function)) {
            throw ScriptRuntime.notFunctionError(callbackArg);
        }
        Function f = (Function)callbackArg;
        Scriptable parent = ScriptableObject.getTopLevelScope(f);
        boolean movingLeft = id == 24;
        Object value = args.length > 1 ? args[1] : Scriptable.NOT_FOUND;
        for (long i = 0L; i < length; ++i) {
            long index = movingLeft ? i : length - 1L - i;
            Object elem = NativeArray.getRawElem(thisObj, index);
            if (elem == Scriptable.NOT_FOUND) continue;
            if (value == Scriptable.NOT_FOUND) {
                value = elem;
                continue;
            }
            Object[] innerArgs = new Object[]{value, elem, index, thisObj};
            value = f.call(cx, parent, parent, innerArgs);
        }
        if (value == Scriptable.NOT_FOUND) {
            throw ScriptRuntime.typeError0("msg.empty.array.reduce");
        }
        return value;
    }

    private static boolean js_isArray(Object o) {
        if (!(o instanceof Scriptable)) {
            return false;
        }
        return "Array".equals(((Scriptable)o).getClassName());
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) > -1;
    }

    @Override
    public Object[] toArray() {
        return this.toArray(ScriptRuntime.emptyArgs);
    }

    @Override
    public Object[] toArray(Object[] a) {
        long longLen = this.length;
        if (longLen > Integer.MAX_VALUE) {
            throw new IllegalStateException();
        }
        int len = (int)longLen;
        Object[] array = a.length >= len ? a : (Object[])Array.newInstance(a.getClass().getComponentType(), len);
        for (int i = 0; i < len; ++i) {
            array[i] = this.get(i);
        }
        return array;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object aC : c) {
            if (this.contains(aC)) continue;
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        long longLen = this.length;
        if (longLen > Integer.MAX_VALUE) {
            throw new IllegalStateException();
        }
        return (int)longLen;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0L;
    }

    public Object get(long index) {
        if (index < 0L || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object value = NativeArray.getRawElem(this, index);
        if (value == Scriptable.NOT_FOUND || value == Undefined.instance) {
            return null;
        }
        if (value instanceof Wrapper) {
            return ((Wrapper)value).unwrap();
        }
        return value;
    }

    public Object get(int index) {
        return this.get((long)index);
    }

    @Override
    public int indexOf(Object o) {
        long longLen = this.length;
        if (longLen > Integer.MAX_VALUE) {
            throw new IllegalStateException();
        }
        int len = (int)longLen;
        if (o == null) {
            for (int i = 0; i < len; ++i) {
                if (this.get(i) != null) continue;
                return i;
            }
        } else {
            for (int i = 0; i < len; ++i) {
                if (!o.equals(this.get(i))) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        long longLen = this.length;
        if (longLen > Integer.MAX_VALUE) {
            throw new IllegalStateException();
        }
        int len = (int)longLen;
        if (o == null) {
            for (int i = len - 1; i >= 0; --i) {
                if (this.get(i) != null) continue;
                return i;
            }
        } else {
            for (int i = len - 1; i >= 0; --i) {
                if (!o.equals(this.get(i))) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator iterator() {
        return this.listIterator(0);
    }

    public ListIterator listIterator() {
        return this.listIterator(0);
    }

    public ListIterator listIterator(final int start) {
        long longLen = this.length;
        if (longLen > Integer.MAX_VALUE) {
            throw new IllegalStateException();
        }
        final int len = (int)longLen;
        if (start < 0 || start > len) {
            throw new IndexOutOfBoundsException("Index: " + start);
        }
        return new ListIterator(){
            int cursor;
            {
                this.cursor = start;
            }

            @Override
            public boolean hasNext() {
                return this.cursor < len;
            }

            @Override
            public Object next() {
                if (this.cursor == len) {
                    throw new NoSuchElementException();
                }
                return NativeArray.this.get(this.cursor++);
            }

            @Override
            public boolean hasPrevious() {
                return this.cursor > 0;
            }

            public Object previous() {
                if (this.cursor == 0) {
                    throw new NoSuchElementException();
                }
                return NativeArray.this.get(--this.cursor);
            }

            @Override
            public int nextIndex() {
                return this.cursor;
            }

            @Override
            public int previousIndex() {
                return this.cursor - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void add(Object o) {
                throw new UnsupportedOperationException();
            }

            public void set(Object o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected int findPrototypeId(String s) {
        int id = 0;
        String X = null;
        block0 : switch (s.length()) {
            case 3: {
                char c = s.charAt(0);
                if (c == 'm') {
                    if (s.charAt(2) != 'p' || s.charAt(1) != 'a') break;
                    return 20;
                }
                if (c != 'p' || s.charAt(2) != 'p' || s.charAt(1) != 'o') break;
                return 9;
            }
            case 4: {
                switch (s.charAt(2)) {
                    case 'i': {
                        X = "join";
                        id = 5;
                        break block0;
                    }
                    case 'm': {
                        X = "some";
                        id = 21;
                        break block0;
                    }
                    case 'n': {
                        X = "find";
                        id = 22;
                        break block0;
                    }
                    case 'r': {
                        X = "sort";
                        id = 7;
                        break block0;
                    }
                    case 's': {
                        X = "push";
                        id = 8;
                        break block0;
                    }
                }
                break;
            }
            case 5: {
                char c = s.charAt(1);
                if (c == 'h') {
                    X = "shift";
                    id = 10;
                    break;
                }
                if (c == 'l') {
                    X = "slice";
                    id = 14;
                    break;
                }
                if (c != 'v') break;
                X = "every";
                id = 17;
                break;
            }
            case 6: {
                switch (s.charAt(0)) {
                    case 'c': {
                        X = "concat";
                        id = 13;
                        break block0;
                    }
                    case 'f': {
                        X = "filter";
                        id = 18;
                        break block0;
                    }
                    case 'r': {
                        X = "reduce";
                        id = 24;
                        break block0;
                    }
                    case 's': {
                        X = "splice";
                        id = 12;
                        break block0;
                    }
                }
                break;
            }
            case 7: {
                switch (s.charAt(0)) {
                    case 'f': {
                        X = "forEach";
                        id = 19;
                        break block0;
                    }
                    case 'i': {
                        X = "indexOf";
                        id = 15;
                        break block0;
                    }
                    case 'r': {
                        X = "reverse";
                        id = 6;
                        break block0;
                    }
                    case 'u': {
                        X = "unshift";
                        id = 11;
                        break block0;
                    }
                }
                break;
            }
            case 8: {
                char c = s.charAt(3);
                if (c == 'o') {
                    X = "toSource";
                    id = 4;
                    break;
                }
                if (c != 't') break;
                X = "toString";
                id = 2;
                break;
            }
            case 9: {
                X = "findIndex";
                id = 23;
                break;
            }
            case 11: {
                char c = s.charAt(0);
                if (c == 'c') {
                    X = "constructor";
                    id = 1;
                    break;
                }
                if (c == 'l') {
                    X = "lastIndexOf";
                    id = 16;
                    break;
                }
                if (c != 'r') break;
                X = "reduceRight";
                id = 25;
                break;
            }
            case 14: {
                X = "toLocaleString";
                id = 3;
            }
        }
        if (X == null) return id;
        if (X == s) return id;
        if (X.equals(s)) return id;
        return 0;
    }
}

