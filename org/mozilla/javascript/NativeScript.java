/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.DefaultErrorReporter;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

class NativeScript
extends BaseFunction {
    static final long serialVersionUID = -6795101161980121700L;
    private static final Object SCRIPT_TAG = "Script";
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_compile = 3;
    private static final int Id_exec = 4;
    private static final int MAX_PROTOTYPE_ID = 4;
    private Script script;

    static void init(Scriptable scope, boolean sealed) {
        NativeScript obj = new NativeScript(null);
        obj.exportAsJSClass(4, scope, sealed);
    }

    private NativeScript(Script script) {
        this.script = script;
    }

    @Override
    public String getClassName() {
        return "Script";
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (this.script != null) {
            return this.script.exec(cx, scope);
        }
        return Undefined.instance;
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        throw Context.reportRuntimeError0("msg.script.is.not.constructor");
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int getArity() {
        return 0;
    }

    @Override
    String decompile(int indent, int flags) {
        if (this.script instanceof NativeFunction) {
            return ((NativeFunction)((Object)this.script)).decompile(indent, flags);
        }
        return super.decompile(indent, flags);
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
            case 4: {
                arity = 0;
                s = "exec";
                break;
            }
            case 3: {
                arity = 1;
                s = "compile";
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(id));
            }
        }
        this.initPrototypeMethod(SCRIPT_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(SCRIPT_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1: {
                String source = args.length == 0 ? "" : ScriptRuntime.toString(args[0]);
                Script script = NativeScript.compile(cx, source);
                NativeScript nscript = new NativeScript(script);
                ScriptRuntime.setObjectProtoAndParent(nscript, scope);
                return nscript;
            }
            case 2: {
                NativeScript real = NativeScript.realThis(thisObj, f);
                Script realScript = real.script;
                if (realScript == null) {
                    return "";
                }
                return cx.decompileScript(realScript, 0);
            }
            case 4: {
                throw Context.reportRuntimeError1("msg.cant.call.indirect", "exec");
            }
            case 3: {
                NativeScript real = NativeScript.realThis(thisObj, f);
                String source = ScriptRuntime.toString(args, 0);
                real.script = NativeScript.compile(cx, source);
                return real;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static NativeScript realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeScript)) {
            throw NativeScript.incompatibleCallError(f);
        }
        return (NativeScript)thisObj;
    }

    private static Script compile(Context cx, String source) {
        int[] linep = new int[]{0};
        String filename = Context.getSourcePositionFromStack(linep);
        if (filename == null) {
            filename = "<Script object>";
            linep[0] = 1;
        }
        ErrorReporter reporter = DefaultErrorReporter.forEval(cx.getErrorReporter());
        return cx.compileString(source, null, reporter, filename, linep[0], null);
    }

    @Override
    protected int findPrototypeId(String s) {
        int id;
        block6: {
            id = 0;
            String X = null;
            switch (s.length()) {
                case 4: {
                    X = "exec";
                    id = 4;
                    break;
                }
                case 7: {
                    X = "compile";
                    id = 3;
                    break;
                }
                case 8: {
                    X = "toString";
                    id = 2;
                    break;
                }
                case 11: {
                    X = "constructor";
                    id = 1;
                    break;
                }
            }
            if (X == null || X == s || X.equals(s)) break block6;
            id = 0;
        }
        return id;
    }
}

