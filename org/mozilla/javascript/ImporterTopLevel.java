/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.NativeJavaPackage;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

public class ImporterTopLevel
extends TopLevel {
    static final long serialVersionUID = -9095380847465315412L;
    private static final Object IMPORTER_TAG = "Importer";
    private static final int Id_constructor = 1;
    private static final int Id_importClass = 2;
    private static final int Id_importPackage = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    private ObjArray importedPackages = new ObjArray();
    private boolean topScopeFlag;

    public ImporterTopLevel() {
    }

    public ImporterTopLevel(Context cx) {
        this(cx, false);
    }

    public ImporterTopLevel(Context cx, boolean sealed) {
        this.initStandardObjects(cx, sealed);
    }

    @Override
    public String getClassName() {
        return this.topScopeFlag ? "global" : "JavaImporter";
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        ImporterTopLevel obj = new ImporterTopLevel();
        obj.exportAsJSClass(3, scope, sealed);
    }

    public void initStandardObjects(Context cx, boolean sealed) {
        cx.initStandardObjects(this, sealed);
        this.topScopeFlag = true;
        IdFunctionObject ctor = this.exportAsJSClass(3, this, false);
        if (sealed) {
            ctor.sealObject();
        }
        this.delete("constructor");
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return super.has(name, start) || this.getPackageProperty(name, start) != NOT_FOUND;
    }

    @Override
    public Object get(String name, Scriptable start) {
        Object result = super.get(name, start);
        if (result != NOT_FOUND) {
            return result;
        }
        result = this.getPackageProperty(name, start);
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private Object getPackageProperty(String name, Scriptable start) {
        Object[] elements;
        Object result = NOT_FOUND;
        ObjArray objArray = this.importedPackages;
        synchronized (objArray) {
            elements = this.importedPackages.toArray();
        }
        for (int i = 0; i < elements.length; ++i) {
            NativeJavaPackage p = (NativeJavaPackage)elements[i];
            Object v = p.getPkgProperty(name, start, false);
            if (v == null || v instanceof NativeJavaPackage) continue;
            if (result == NOT_FOUND) {
                result = v;
                continue;
            }
            throw Context.reportRuntimeError2("msg.ambig.import", result.toString(), v.toString());
        }
        return result;
    }

    @Deprecated
    public void importPackage(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        this.js_importPackage(args);
    }

    private Object js_construct(Scriptable scope, Object[] args) {
        ImporterTopLevel result = new ImporterTopLevel();
        for (int i = 0; i != args.length; ++i) {
            Object arg = args[i];
            if (arg instanceof NativeJavaClass) {
                result.importClass((NativeJavaClass)arg);
                continue;
            }
            if (arg instanceof NativeJavaPackage) {
                result.importPackage((NativeJavaPackage)arg);
                continue;
            }
            throw Context.reportRuntimeError1("msg.not.class.not.pkg", Context.toString(arg));
        }
        result.setParentScope(scope);
        result.setPrototype(this);
        return result;
    }

    private Object js_importClass(Object[] args) {
        for (int i = 0; i != args.length; ++i) {
            Object arg = args[i];
            if (!(arg instanceof NativeJavaClass)) {
                throw Context.reportRuntimeError1("msg.not.class", Context.toString(arg));
            }
            this.importClass((NativeJavaClass)arg);
        }
        return Undefined.instance;
    }

    private Object js_importPackage(Object[] args) {
        for (int i = 0; i != args.length; ++i) {
            Object arg = args[i];
            if (!(arg instanceof NativeJavaPackage)) {
                throw Context.reportRuntimeError1("msg.not.pkg", Context.toString(arg));
            }
            this.importPackage((NativeJavaPackage)arg);
        }
        return Undefined.instance;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void importPackage(NativeJavaPackage pkg) {
        if (pkg == null) {
            return;
        }
        ObjArray objArray = this.importedPackages;
        synchronized (objArray) {
            for (int j = 0; j != this.importedPackages.size(); ++j) {
                if (!pkg.equals(this.importedPackages.get(j))) continue;
                return;
            }
            this.importedPackages.add(pkg);
        }
    }

    private void importClass(NativeJavaClass cl) {
        String s = cl.getClassObject().getName();
        String n = s.substring(s.lastIndexOf(46) + 1);
        Object val = this.get(n, (Scriptable)this);
        if (val != NOT_FOUND && val != cl) {
            throw Context.reportRuntimeError1("msg.prop.defined", n);
        }
        this.put(n, (Scriptable)this, (Object)cl);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case 1: {
                arity = 0;
                s = "constructor";
                break;
            }
            case 2: {
                arity = 1;
                s = "importClass";
                break;
            }
            case 3: {
                arity = 1;
                s = "importPackage";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(IMPORTER_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(IMPORTER_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                return this.js_construct(scope, args);
            }
            case 2: {
                return this.realThis(thisObj, f).js_importClass(args);
            }
            case 3: {
                return this.realThis(thisObj, f).js_importPackage(args);
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private ImporterTopLevel realThis(Scriptable thisObj, IdFunctionObject f) {
        if (this.topScopeFlag) {
            return this;
        }
        if (!(thisObj instanceof ImporterTopLevel)) {
            throw ImporterTopLevel.incompatibleCallError(f);
        }
        return (ImporterTopLevel)thisObj;
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block6: {
            id = 0;
            String X = null;
            int s_length = s.length();
            if (s_length == 11) {
                char c = s.charAt(0);
                if (c == 'c') {
                    X = "constructor";
                    id = 1;
                } else if (c == 'i') {
                    X = "importClass";
                    id = 2;
                }
            } else if (s_length == 13) {
                X = "importPackage";
                id = 3;
            }
            if (X == null || X == s || X.equals(s)) break block6;
            id = 0;
        }
        return id;
    }
}

