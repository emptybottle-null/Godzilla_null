/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.Iterator;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.NativeGenerator;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.VMBridge;

public final class NativeIterator
extends IdScriptableObject {
    private static final long serialVersionUID = -4136968203581667681L;
    private static final Object ITERATOR_TAG = "Iterator";
    private static final String STOP_ITERATION = "StopIteration";
    public static final String ITERATOR_PROPERTY_NAME = "__iterator__";
    private static final int Id_constructor = 1;
    private static final int Id_next = 2;
    private static final int Id___iterator__ = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    private Object objectIterator;

    static void init(ScriptableObject scope, boolean sealed) {
        NativeIterator iterator = new NativeIterator();
        iterator.exportAsJSClass(3, scope, sealed);
        NativeGenerator.init(scope, sealed);
        StopIteration obj = new StopIteration();
        obj.setPrototype(NativeIterator.getObjectPrototype(scope));
        obj.setParentScope(scope);
        if (sealed) {
            obj.sealObject();
        }
        ScriptableObject.defineProperty(scope, STOP_ITERATION, obj, 2);
        scope.associateValue(ITERATOR_TAG, obj);
    }

    private NativeIterator() {
    }

    private NativeIterator(Object objectIterator) {
        this.objectIterator = objectIterator;
    }

    public static Object getStopIterationObject(Scriptable scope) {
        Scriptable top = ScriptableObject.getTopLevelScope(scope);
        return ScriptableObject.getTopScopeValue(top, ITERATOR_TAG);
    }

    @Override
    public String getClassName() {
        return "Iterator";
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 2;
                s = "constructor";
                break;
            }
            case 2: {
                arity = 0;
                s = "next";
                break;
            }
            case 3: {
                arity = 1;
                s = ITERATOR_PROPERTY_NAME;
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(ITERATOR_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(ITERATOR_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        if (id == 1) {
            return NativeIterator.jsConstructor(cx, scope, thisObj, args);
        }
        if (!(thisObj instanceof NativeIterator)) {
            throw NativeIterator.incompatibleCallError(f);
        }
        NativeIterator iterator = (NativeIterator)thisObj;
        switch (id) {
            case 2: {
                return iterator.next(cx, scope);
            }
            case 3: {
                return thisObj;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static Object jsConstructor(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        boolean keyOnly;
        if (args.length == 0 || args[0] == null || args[0] == Undefined.instance) {
            Object argument = args.length == 0 ? Undefined.instance : args[0];
            throw ScriptRuntime.typeError1("msg.no.properties", ScriptRuntime.toString(argument));
        }
        Scriptable obj = ScriptRuntime.toObject(cx, scope, args[0]);
        boolean bl = keyOnly = args.length > 1 && ScriptRuntime.toBoolean(args[1]);
        if (thisObj != null) {
            Iterator<?> iterator = VMBridge.instance.getJavaIterator(cx, scope, obj);
            if (iterator != null) {
                scope = ScriptableObject.getTopLevelScope(scope);
                return cx.getWrapFactory().wrap(cx, scope, new WrappedJavaIterator(iterator, scope), WrappedJavaIterator.class);
            }
            Scriptable jsIterator = ScriptRuntime.toIterator(cx, scope, obj, keyOnly);
            if (jsIterator != null) {
                return jsIterator;
            }
        }
        Object objectIterator = ScriptRuntime.enumInit(obj, cx, scope, keyOnly ? 3 : 5);
        ScriptRuntime.setEnumNumbers(objectIterator, true);
        NativeIterator result = new NativeIterator(objectIterator);
        result.setPrototype(ScriptableObject.getClassPrototype(scope, result.getClassName()));
        result.setParentScope(scope);
        return result;
    }

    private Object next(Context cx, Scriptable scope) {
        Boolean b = ScriptRuntime.enumNext(this.objectIterator);
        if (!b.booleanValue()) {
            throw new JavaScriptException(NativeIterator.getStopIterationObject(scope), null, 0);
        }
        return ScriptRuntime.enumId(this.objectIterator, cx);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block5: {
            id = 0;
            String X = null;
            int s_length = s.length();
            if (s_length == 4) {
                X = "next";
                id = 2;
            } else if (s_length == 11) {
                X = "constructor";
                id = 1;
            } else if (s_length == 12) {
                X = ITERATOR_PROPERTY_NAME;
                id = 3;
            }
            if (X == null || X == s || X.equals(s)) break block5;
            id = 0;
        }
        return id;
    }

    public static class WrappedJavaIterator {
        private Iterator<?> iterator;
        private Scriptable scope;

        WrappedJavaIterator(Iterator<?> iterator, Scriptable scope) {
            this.iterator = iterator;
            this.scope = scope;
        }

        public Object next() {
            if (!this.iterator.hasNext()) {
                throw new JavaScriptException(NativeIterator.getStopIterationObject(this.scope), null, 0);
            }
            return this.iterator.next();
        }

        public Object __iterator__(boolean b) {
            return this;
        }
    }

    static class StopIteration
    extends NativeObject {
        private static final long serialVersionUID = 2485151085722377663L;

        StopIteration() {
        }

        @Override
        public String getClassName() {
            return NativeIterator.STOP_ITERATION;
        }

        @Override
        public boolean hasInstance(Scriptable instance) {
            return instance instanceof StopIteration;
        }
    }
}

