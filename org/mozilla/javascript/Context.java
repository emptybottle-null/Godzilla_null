/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.ClassShutter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ContextListener;
import org.mozilla.javascript.ContinuationPending;
import org.mozilla.javascript.DefaultErrorReporter;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.InterpretedFunction;
import org.mozilla.javascript.Interpreter;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeCall;
import org.mozilla.javascript.NativeContinuation;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.RegExpProxy;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.VMBridge;
import org.mozilla.javascript.WrapFactory;
import org.mozilla.javascript.WrappedException;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;
import org.mozilla.javascript.xml.XMLLib;

public class Context {
    public static final int VERSION_UNKNOWN = -1;
    public static final int VERSION_DEFAULT = 0;
    public static final int VERSION_1_0 = 100;
    public static final int VERSION_1_1 = 110;
    public static final int VERSION_1_2 = 120;
    public static final int VERSION_1_3 = 130;
    public static final int VERSION_1_4 = 140;
    public static final int VERSION_1_5 = 150;
    public static final int VERSION_1_6 = 160;
    public static final int VERSION_1_7 = 170;
    public static final int VERSION_1_8 = 180;
    public static final int FEATURE_NON_ECMA_GET_YEAR = 1;
    public static final int FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME = 2;
    public static final int FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER = 3;
    public static final int FEATURE_TO_STRING_AS_SOURCE = 4;
    public static final int FEATURE_PARENT_PROTO_PROPERTIES = 5;
    @Deprecated
    public static final int FEATURE_PARENT_PROTO_PROPRTIES = 5;
    public static final int FEATURE_E4X = 6;
    public static final int FEATURE_DYNAMIC_SCOPE = 7;
    public static final int FEATURE_STRICT_VARS = 8;
    public static final int FEATURE_STRICT_EVAL = 9;
    public static final int FEATURE_LOCATION_INFORMATION_IN_ERROR = 10;
    public static final int FEATURE_STRICT_MODE = 11;
    public static final int FEATURE_WARNING_AS_ERROR = 12;
    public static final int FEATURE_ENHANCED_JAVA_ACCESS = 13;
    public static final int FEATURE_V8_EXTENSIONS = 14;
    public static final String languageVersionProperty = "language version";
    public static final String errorReporterProperty = "error reporter";
    public static final Object[] emptyArgs = ScriptRuntime.emptyArgs;
    private static Class<?> codegenClass = Kit.classOrNull("org.mozilla.javascript.optimizer.Codegen");
    private static Class<?> interpreterClass = Kit.classOrNull("org.mozilla.javascript.Interpreter");
    private static String implementationVersion;
    private final ContextFactory factory;
    private boolean sealed;
    private Object sealKey;
    Scriptable topCallScope;
    boolean isContinuationsTopCall;
    NativeCall currentActivationCall;
    XMLLib cachedXMLLib;
    BaseFunction typeErrorThrower;
    ObjToIntMap iterating;
    Object interpreterSecurityDomain;
    int version;
    private SecurityController securityController;
    private boolean hasClassShutter;
    private ClassShutter classShutter;
    private ErrorReporter errorReporter;
    RegExpProxy regExpProxy;
    private Locale locale;
    private boolean generatingDebug;
    private boolean generatingDebugChanged;
    private boolean generatingSource = true;
    boolean useDynamicScope;
    private int optimizationLevel;
    private int maximumInterpreterStackDepth;
    private WrapFactory wrapFactory;
    Debugger debugger;
    private Object debuggerData;
    private int enterCount;
    private Object propertyListeners;
    private Map<Object, Object> threadLocalMap;
    private ClassLoader applicationClassLoader;
    Set<String> activationNames;
    Object lastInterpreterFrame;
    ObjArray previousInterpreterInvocations;
    int instructionCount;
    int instructionThreshold;
    int scratchIndex;
    long scratchUint32;
    Scriptable scratchScriptable;
    public boolean generateObserverCount = false;

    @Deprecated
    public Context() {
        this(ContextFactory.getGlobal());
    }

    protected Context(ContextFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("factory == null");
        }
        this.factory = factory;
        this.version = 0;
        this.optimizationLevel = codegenClass != null ? 0 : -1;
        this.maximumInterpreterStackDepth = Integer.MAX_VALUE;
    }

    public static Context getCurrentContext() {
        Object helper = VMBridge.instance.getThreadContextHelper();
        return VMBridge.instance.getContext(helper);
    }

    public static Context enter() {
        return Context.enter(null);
    }

    @Deprecated
    public static Context enter(Context cx) {
        return Context.enter(cx, ContextFactory.getGlobal());
    }

    static final Context enter(Context cx, ContextFactory factory) {
        Object helper = VMBridge.instance.getThreadContextHelper();
        Context old = VMBridge.instance.getContext(helper);
        if (old != null) {
            cx = old;
        } else {
            if (cx == null) {
                cx = factory.makeContext();
                if (cx.enterCount != 0) {
                    throw new IllegalStateException("factory.makeContext() returned Context instance already associated with some thread");
                }
                factory.onContextCreated(cx);
                if (factory.isSealed() && !cx.isSealed()) {
                    cx.seal(null);
                }
            } else if (cx.enterCount != 0) {
                throw new IllegalStateException("can not use Context instance already associated with some thread");
            }
            VMBridge.instance.setContext(helper, cx);
        }
        ++cx.enterCount;
        return cx;
    }

    public static void exit() {
        Object helper = VMBridge.instance.getThreadContextHelper();
        Context cx = VMBridge.instance.getContext(helper);
        if (cx == null) {
            throw new IllegalStateException("Calling Context.exit without previous Context.enter");
        }
        if (cx.enterCount < 1) {
            Kit.codeBug();
        }
        if (--cx.enterCount == 0) {
            VMBridge.instance.setContext(helper, null);
            cx.factory.onContextReleased(cx);
        }
    }

    @Deprecated
    public static Object call(ContextAction action) {
        return Context.call(ContextFactory.getGlobal(), action);
    }

    public static Object call(ContextFactory factory, final Callable callable, final Scriptable scope, final Scriptable thisObj, final Object[] args) {
        if (factory == null) {
            factory = ContextFactory.getGlobal();
        }
        return Context.call(factory, new ContextAction(){

            @Override
            public Object run(Context cx) {
                return callable.call(cx, scope, thisObj, args);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Object call(ContextFactory factory, ContextAction action) {
        Context cx = Context.enter(null, factory);
        try {
            Object object = action.run(cx);
            return object;
        } finally {
            Context.exit();
        }
    }

    @Deprecated
    public static void addContextListener(ContextListener listener) {
        String DBG = "org.mozilla.javascript.tools.debugger.Main";
        if (DBG.equals(listener.getClass().getName())) {
            Class<?> cl = listener.getClass();
            Class<?> factoryClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
            Class[] sig = new Class[]{factoryClass};
            Object[] args = new Object[]{ContextFactory.getGlobal()};
            try {
                Method m = cl.getMethod("attachTo", sig);
                m.invoke(listener, args);
            } catch (Exception ex) {
                RuntimeException rex = new RuntimeException();
                Kit.initCause(rex, ex);
                throw rex;
            }
            return;
        }
        ContextFactory.getGlobal().addListener(listener);
    }

    @Deprecated
    public static void removeContextListener(ContextListener listener) {
        ContextFactory.getGlobal().addListener(listener);
    }

    public final ContextFactory getFactory() {
        return this.factory;
    }

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal(Object sealKey) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.sealed = true;
        this.sealKey = sealKey;
    }

    public final void unseal(Object sealKey) {
        if (sealKey == null) {
            throw new IllegalArgumentException();
        }
        if (this.sealKey != sealKey) {
            throw new IllegalArgumentException();
        }
        if (!this.sealed) {
            throw new IllegalStateException();
        }
        this.sealed = false;
        this.sealKey = null;
    }

    static void onSealedMutation() {
        throw new IllegalStateException();
    }

    public final int getLanguageVersion() {
        return this.version;
    }

    public void setLanguageVersion(int version) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        Context.checkLanguageVersion(version);
        Object listeners = this.propertyListeners;
        if (listeners != null && version != this.version) {
            this.firePropertyChangeImpl(listeners, languageVersionProperty, this.version, version);
        }
        this.version = version;
    }

    public static boolean isValidLanguageVersion(int version) {
        switch (version) {
            case 0: 
            case 100: 
            case 110: 
            case 120: 
            case 130: 
            case 140: 
            case 150: 
            case 160: 
            case 170: 
            case 180: {
                return true;
            }
        }
        return false;
    }

    public static void checkLanguageVersion(int version) {
        if (Context.isValidLanguageVersion(version)) {
            return;
        }
        throw new IllegalArgumentException("Bad language version: " + version);
    }

    public final String getImplementationVersion() {
        if (implementationVersion == null) {
            implementationVersion = ScriptRuntime.getMessage0("implementation.version");
        }
        return implementationVersion;
    }

    public final ErrorReporter getErrorReporter() {
        if (this.errorReporter == null) {
            return DefaultErrorReporter.instance;
        }
        return this.errorReporter;
    }

    public final ErrorReporter setErrorReporter(ErrorReporter reporter) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (reporter == null) {
            throw new IllegalArgumentException();
        }
        ErrorReporter old = this.getErrorReporter();
        if (reporter == old) {
            return old;
        }
        Object listeners = this.propertyListeners;
        if (listeners != null) {
            this.firePropertyChangeImpl(listeners, errorReporterProperty, old, reporter);
        }
        this.errorReporter = reporter;
        return old;
    }

    public final Locale getLocale() {
        if (this.locale == null) {
            this.locale = Locale.getDefault();
        }
        return this.locale;
    }

    public final Locale setLocale(Locale loc) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        Locale result = this.locale;
        this.locale = loc;
        return result;
    }

    public final void addPropertyChangeListener(PropertyChangeListener l) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.propertyListeners = Kit.addListener(this.propertyListeners, l);
    }

    public final void removePropertyChangeListener(PropertyChangeListener l) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.propertyListeners = Kit.removeListener(this.propertyListeners, l);
    }

    final void firePropertyChange(String property, Object oldValue, Object newValue) {
        Object listeners = this.propertyListeners;
        if (listeners != null) {
            this.firePropertyChangeImpl(listeners, property, oldValue, newValue);
        }
    }

    private void firePropertyChangeImpl(Object listeners, String property, Object oldValue, Object newValue) {
        Object l;
        int i = 0;
        while ((l = Kit.getListener(listeners, i)) != null) {
            if (l instanceof PropertyChangeListener) {
                PropertyChangeListener pcl = (PropertyChangeListener)l;
                pcl.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
            }
            ++i;
        }
    }

    public static void reportWarning(String message, String sourceName, int lineno, String lineSource, int lineOffset) {
        Context cx = Context.getContext();
        if (cx.hasFeature(12)) {
            Context.reportError(message, sourceName, lineno, lineSource, lineOffset);
        } else {
            cx.getErrorReporter().warning(message, sourceName, lineno, lineSource, lineOffset);
        }
    }

    public static void reportWarning(String message) {
        int[] linep = new int[]{0};
        String filename = Context.getSourcePositionFromStack(linep);
        Context.reportWarning(message, filename, linep[0], null, 0);
    }

    public static void reportWarning(String message, Throwable t) {
        int[] linep = new int[]{0};
        String filename = Context.getSourcePositionFromStack(linep);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println(message);
        t.printStackTrace(pw);
        pw.flush();
        Context.reportWarning(((Object)sw).toString(), filename, linep[0], null, 0);
    }

    public static void reportError(String message, String sourceName, int lineno, String lineSource, int lineOffset) {
        Context cx = Context.getCurrentContext();
        if (cx == null) {
            throw new EvaluatorException(message, sourceName, lineno, lineSource, lineOffset);
        }
        cx.getErrorReporter().error(message, sourceName, lineno, lineSource, lineOffset);
    }

    public static void reportError(String message) {
        int[] linep = new int[]{0};
        String filename = Context.getSourcePositionFromStack(linep);
        Context.reportError(message, filename, linep[0], null, 0);
    }

    public static EvaluatorException reportRuntimeError(String message, String sourceName, int lineno, String lineSource, int lineOffset) {
        Context cx = Context.getCurrentContext();
        if (cx != null) {
            return cx.getErrorReporter().runtimeError(message, sourceName, lineno, lineSource, lineOffset);
        }
        throw new EvaluatorException(message, sourceName, lineno, lineSource, lineOffset);
    }

    static EvaluatorException reportRuntimeError0(String messageId) {
        String msg = ScriptRuntime.getMessage0(messageId);
        return Context.reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError1(String messageId, Object arg1) {
        String msg = ScriptRuntime.getMessage1(messageId, arg1);
        return Context.reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError2(String messageId, Object arg1, Object arg2) {
        String msg = ScriptRuntime.getMessage2(messageId, arg1, arg2);
        return Context.reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError3(String messageId, Object arg1, Object arg2, Object arg3) {
        String msg = ScriptRuntime.getMessage3(messageId, arg1, arg2, arg3);
        return Context.reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError4(String messageId, Object arg1, Object arg2, Object arg3, Object arg4) {
        String msg = ScriptRuntime.getMessage4(messageId, arg1, arg2, arg3, arg4);
        return Context.reportRuntimeError(msg);
    }

    public static EvaluatorException reportRuntimeError(String message) {
        int[] linep = new int[]{0};
        String filename = Context.getSourcePositionFromStack(linep);
        return Context.reportRuntimeError(message, filename, linep[0], null, 0);
    }

    public final ScriptableObject initStandardObjects() {
        return this.initStandardObjects(null, false);
    }

    public final ScriptableObject initSafeStandardObjects() {
        return this.initSafeStandardObjects(null, false);
    }

    public final Scriptable initStandardObjects(ScriptableObject scope) {
        return this.initStandardObjects(scope, false);
    }

    public final Scriptable initSafeStandardObjects(ScriptableObject scope) {
        return this.initSafeStandardObjects(scope, false);
    }

    public ScriptableObject initStandardObjects(ScriptableObject scope, boolean sealed) {
        return ScriptRuntime.initStandardObjects(this, scope, sealed);
    }

    public ScriptableObject initSafeStandardObjects(ScriptableObject scope, boolean sealed) {
        return ScriptRuntime.initSafeStandardObjects(this, scope, sealed);
    }

    public static Object getUndefinedValue() {
        return Undefined.instance;
    }

    public final Object evaluateString(Scriptable scope, String source, String sourceName, int lineno, Object securityDomain) {
        Script script = this.compileString(source, sourceName, lineno, securityDomain);
        if (script != null) {
            return script.exec(this, scope);
        }
        return null;
    }

    public final Object evaluateReader(Scriptable scope, Reader in, String sourceName, int lineno, Object securityDomain) throws IOException {
        Script script = this.compileReader(scope, in, sourceName, lineno, securityDomain);
        if (script != null) {
            return script.exec(this, scope);
        }
        return null;
    }

    public Object executeScriptWithContinuations(Script script, Scriptable scope) throws ContinuationPending {
        if (!(script instanceof InterpretedFunction) || !((InterpretedFunction)script).isScript()) {
            throw new IllegalArgumentException("Script argument was not a script or was not created by interpreted mode ");
        }
        return this.callFunctionWithContinuations((InterpretedFunction)script, scope, ScriptRuntime.emptyArgs);
    }

    public Object callFunctionWithContinuations(Callable function, Scriptable scope, Object[] args) throws ContinuationPending {
        if (!(function instanceof InterpretedFunction)) {
            throw new IllegalArgumentException("Function argument was not created by interpreted mode ");
        }
        if (ScriptRuntime.hasTopCall(this)) {
            throw new IllegalStateException("Cannot have any pending top calls when executing a script with continuations");
        }
        this.isContinuationsTopCall = true;
        return ScriptRuntime.doTopCall(function, this, scope, scope, args);
    }

    public ContinuationPending captureContinuation() {
        return new ContinuationPending(Interpreter.captureContinuation(this));
    }

    public Object resumeContinuation(Object continuation, Scriptable scope, Object functionResult) throws ContinuationPending {
        Object[] args = new Object[]{functionResult};
        return Interpreter.restartContinuation((NativeContinuation)continuation, this, scope, args);
    }

    public final boolean stringIsCompilableUnit(String source) {
        boolean errorseen = false;
        CompilerEnvirons compilerEnv = new CompilerEnvirons();
        compilerEnv.initFromContext(this);
        compilerEnv.setGeneratingSource(false);
        Parser p = new Parser(compilerEnv, DefaultErrorReporter.instance);
        try {
            p.parse(source, null, 1);
        } catch (EvaluatorException ee) {
            errorseen = true;
        }
        return !errorseen || !p.eof();
    }

    @Deprecated
    public final Script compileReader(Scriptable scope, Reader in, String sourceName, int lineno, Object securityDomain) throws IOException {
        return this.compileReader(in, sourceName, lineno, securityDomain);
    }

    public final Script compileReader(Reader in, String sourceName, int lineno, Object securityDomain) throws IOException {
        if (lineno < 0) {
            lineno = 0;
        }
        return (Script)this.compileImpl(null, in, null, sourceName, lineno, securityDomain, false, null, null);
    }

    public final Script compileString(String source, String sourceName, int lineno, Object securityDomain) {
        if (lineno < 0) {
            lineno = 0;
        }
        return this.compileString(source, null, null, sourceName, lineno, securityDomain);
    }

    final Script compileString(String source, Evaluator compiler, ErrorReporter compilationErrorReporter, String sourceName, int lineno, Object securityDomain) {
        try {
            return (Script)this.compileImpl(null, null, source, sourceName, lineno, securityDomain, false, compiler, compilationErrorReporter);
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public final Function compileFunction(Scriptable scope, String source, String sourceName, int lineno, Object securityDomain) {
        return this.compileFunction(scope, source, null, null, sourceName, lineno, securityDomain);
    }

    final Function compileFunction(Scriptable scope, String source, Evaluator compiler, ErrorReporter compilationErrorReporter, String sourceName, int lineno, Object securityDomain) {
        try {
            return (Function)this.compileImpl(scope, null, source, sourceName, lineno, securityDomain, true, compiler, compilationErrorReporter);
        } catch (IOException ioe) {
            throw new RuntimeException();
        }
    }

    public final String decompileScript(Script script, int indent) {
        NativeFunction scriptImpl = (NativeFunction)((Object)script);
        return scriptImpl.decompile(indent, 0);
    }

    public final String decompileFunction(Function fun, int indent) {
        if (fun instanceof BaseFunction) {
            return ((BaseFunction)fun).decompile(indent, 0);
        }
        return "function " + fun.getClassName() + "() {\n\t[native code]\n}\n";
    }

    public final String decompileFunctionBody(Function fun, int indent) {
        if (fun instanceof BaseFunction) {
            BaseFunction bf = (BaseFunction)fun;
            return bf.decompile(indent, 1);
        }
        return "[native code]\n";
    }

    public Scriptable newObject(Scriptable scope) {
        NativeObject result = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.Object);
        return result;
    }

    public Scriptable newObject(Scriptable scope, String constructorName) {
        return this.newObject(scope, constructorName, ScriptRuntime.emptyArgs);
    }

    public Scriptable newObject(Scriptable scope, String constructorName, Object[] args) {
        return ScriptRuntime.newObject(this, scope, constructorName, args);
    }

    public Scriptable newArray(Scriptable scope, int length) {
        NativeArray result = new NativeArray(length);
        ScriptRuntime.setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.Array);
        return result;
    }

    public Scriptable newArray(Scriptable scope, Object[] elements) {
        if (elements.getClass().getComponentType() != ScriptRuntime.ObjectClass) {
            throw new IllegalArgumentException();
        }
        NativeArray result = new NativeArray(elements);
        ScriptRuntime.setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.Array);
        return result;
    }

    public final Object[] getElements(Scriptable object) {
        return ScriptRuntime.getArrayElements(object);
    }

    public static boolean toBoolean(Object value) {
        return ScriptRuntime.toBoolean(value);
    }

    public static double toNumber(Object value) {
        return ScriptRuntime.toNumber(value);
    }

    public static String toString(Object value) {
        return ScriptRuntime.toString(value);
    }

    public static Scriptable toObject(Object value, Scriptable scope) {
        return ScriptRuntime.toObject(scope, value);
    }

    @Deprecated
    public static Scriptable toObject(Object value, Scriptable scope, Class<?> staticType) {
        return ScriptRuntime.toObject(scope, value);
    }

    public static Object javaToJS(Object value, Scriptable scope) {
        if (value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Scriptable) {
            return value;
        }
        if (value instanceof Character) {
            return String.valueOf(((Character)value).charValue());
        }
        Context cx = Context.getContext();
        return cx.getWrapFactory().wrap(cx, scope, value, null);
    }

    public static Object jsToJava(Object value, Class<?> desiredType) throws EvaluatorException {
        return NativeJavaObject.coerceTypeImpl(desiredType, value);
    }

    @Deprecated
    public static Object toType(Object value, Class<?> desiredType) throws IllegalArgumentException {
        try {
            return Context.jsToJava(value, desiredType);
        } catch (EvaluatorException ex) {
            IllegalArgumentException ex2 = new IllegalArgumentException(ex.getMessage());
            Kit.initCause(ex2, ex);
            throw ex2;
        }
    }

    public static RuntimeException throwAsScriptRuntimeEx(Throwable e) {
        Context cx;
        while (e instanceof InvocationTargetException) {
            e = ((InvocationTargetException)e).getTargetException();
        }
        if (e instanceof Error && ((cx = Context.getContext()) == null || !cx.hasFeature(13))) {
            throw (Error)e;
        }
        if (e instanceof RhinoException) {
            throw (RhinoException)e;
        }
        throw new WrappedException(e);
    }

    public final boolean isGeneratingDebug() {
        return this.generatingDebug;
    }

    public final void setGeneratingDebug(boolean generatingDebug) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.generatingDebugChanged = true;
        if (generatingDebug && this.getOptimizationLevel() > 0) {
            this.setOptimizationLevel(0);
        }
        this.generatingDebug = generatingDebug;
    }

    public final boolean isGeneratingSource() {
        return this.generatingSource;
    }

    public final void setGeneratingSource(boolean generatingSource) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.generatingSource = generatingSource;
    }

    public final int getOptimizationLevel() {
        return this.optimizationLevel;
    }

    public final void setOptimizationLevel(int optimizationLevel) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (optimizationLevel == -2) {
            optimizationLevel = -1;
        }
        Context.checkOptimizationLevel(optimizationLevel);
        if (codegenClass == null) {
            optimizationLevel = -1;
        }
        this.optimizationLevel = optimizationLevel;
    }

    public static boolean isValidOptimizationLevel(int optimizationLevel) {
        return -1 <= optimizationLevel && optimizationLevel <= 9;
    }

    public static void checkOptimizationLevel(int optimizationLevel) {
        if (Context.isValidOptimizationLevel(optimizationLevel)) {
            return;
        }
        throw new IllegalArgumentException("Optimization level outside [-1..9]: " + optimizationLevel);
    }

    public final int getMaximumInterpreterStackDepth() {
        return this.maximumInterpreterStackDepth;
    }

    public final void setMaximumInterpreterStackDepth(int max) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (this.optimizationLevel != -1) {
            throw new IllegalStateException("Cannot set maximumInterpreterStackDepth when optimizationLevel != -1");
        }
        if (max < 1) {
            throw new IllegalArgumentException("Cannot set maximumInterpreterStackDepth to less than 1");
        }
        this.maximumInterpreterStackDepth = max;
    }

    public final void setSecurityController(SecurityController controller) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (controller == null) {
            throw new IllegalArgumentException();
        }
        if (this.securityController != null) {
            throw new SecurityException("Can not overwrite existing SecurityController object");
        }
        if (SecurityController.hasGlobal()) {
            throw new SecurityException("Can not overwrite existing global SecurityController object");
        }
        this.securityController = controller;
    }

    public final synchronized void setClassShutter(ClassShutter shutter) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (shutter == null) {
            throw new IllegalArgumentException();
        }
        if (this.hasClassShutter) {
            throw new SecurityException("Cannot overwrite existing ClassShutter object");
        }
        this.classShutter = shutter;
        this.hasClassShutter = true;
    }

    final synchronized ClassShutter getClassShutter() {
        return this.classShutter;
    }

    public final synchronized ClassShutterSetter getClassShutterSetter() {
        if (this.hasClassShutter) {
            return null;
        }
        this.hasClassShutter = true;
        return new ClassShutterSetter(){

            @Override
            public void setClassShutter(ClassShutter shutter) {
                Context.this.classShutter = shutter;
            }

            @Override
            public ClassShutter getClassShutter() {
                return Context.this.classShutter;
            }
        };
    }

    public final Object getThreadLocal(Object key) {
        if (this.threadLocalMap == null) {
            return null;
        }
        return this.threadLocalMap.get(key);
    }

    public final synchronized void putThreadLocal(Object key, Object value) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (this.threadLocalMap == null) {
            this.threadLocalMap = new HashMap<Object, Object>();
        }
        this.threadLocalMap.put(key, value);
    }

    public final void removeThreadLocal(Object key) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (this.threadLocalMap == null) {
            return;
        }
        this.threadLocalMap.remove(key);
    }

    @Deprecated
    public static void setCachingEnabled(boolean cachingEnabled) {
    }

    public final void setWrapFactory(WrapFactory wrapFactory) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (wrapFactory == null) {
            throw new IllegalArgumentException();
        }
        this.wrapFactory = wrapFactory;
    }

    public final WrapFactory getWrapFactory() {
        if (this.wrapFactory == null) {
            this.wrapFactory = new WrapFactory();
        }
        return this.wrapFactory;
    }

    public final Debugger getDebugger() {
        return this.debugger;
    }

    public final Object getDebuggerContextData() {
        return this.debuggerData;
    }

    public final void setDebugger(Debugger debugger, Object contextData) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        this.debugger = debugger;
        this.debuggerData = contextData;
    }

    public static DebuggableScript getDebuggableView(Script script) {
        if (script instanceof NativeFunction) {
            return ((NativeFunction)((Object)script)).getDebuggableView();
        }
        return null;
    }

    public boolean hasFeature(int featureIndex) {
        ContextFactory f = this.getFactory();
        return f.hasFeature(this, featureIndex);
    }

    public XMLLib.Factory getE4xImplementationFactory() {
        return this.getFactory().getE4xImplementationFactory();
    }

    public final int getInstructionObserverThreshold() {
        return this.instructionThreshold;
    }

    public final void setInstructionObserverThreshold(int threshold) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (threshold < 0) {
            throw new IllegalArgumentException();
        }
        this.instructionThreshold = threshold;
        this.setGenerateObserverCount(threshold > 0);
    }

    public void setGenerateObserverCount(boolean generateObserverCount) {
        this.generateObserverCount = generateObserverCount;
    }

    protected void observeInstructionCount(int instructionCount) {
        ContextFactory f = this.getFactory();
        f.observeInstructionCount(this, instructionCount);
    }

    public GeneratedClassLoader createClassLoader(ClassLoader parent) {
        ContextFactory f = this.getFactory();
        return f.createClassLoader(parent);
    }

    public final ClassLoader getApplicationClassLoader() {
        if (this.applicationClassLoader == null) {
            ContextFactory f = this.getFactory();
            ClassLoader loader = f.getApplicationClassLoader();
            if (loader == null) {
                ClassLoader threadLoader = VMBridge.instance.getCurrentThreadClassLoader();
                if (threadLoader != null && Kit.testIfCanLoadRhinoClasses(threadLoader)) {
                    return threadLoader;
                }
                Class<?> fClass = f.getClass();
                loader = fClass != ScriptRuntime.ContextFactoryClass ? fClass.getClassLoader() : this.getClass().getClassLoader();
            }
            this.applicationClassLoader = loader;
        }
        return this.applicationClassLoader;
    }

    public final void setApplicationClassLoader(ClassLoader loader) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (loader == null) {
            this.applicationClassLoader = null;
            return;
        }
        if (!Kit.testIfCanLoadRhinoClasses(loader)) {
            throw new IllegalArgumentException("Loader can not resolve Rhino classes");
        }
        this.applicationClassLoader = loader;
    }

    static Context getContext() {
        Context cx = Context.getCurrentContext();
        if (cx == null) {
            throw new RuntimeException("No Context associated with current Thread");
        }
        return cx;
    }

    private Object compileImpl(Scriptable scope, Reader sourceReader, String sourceString, String sourceName, int lineno, Object securityDomain, boolean returnFunction, Evaluator compiler, ErrorReporter compilationErrorReporter) throws IOException {
        if (sourceName == null) {
            sourceName = "unnamed script";
        }
        if (securityDomain != null && this.getSecurityController() == null) {
            throw new IllegalArgumentException("securityDomain should be null if setSecurityController() was never called");
        }
        if (!(sourceReader == null ^ sourceString == null)) {
            Kit.codeBug();
        }
        if (!(scope == null ^ returnFunction)) {
            Kit.codeBug();
        }
        CompilerEnvirons compilerEnv = new CompilerEnvirons();
        compilerEnv.initFromContext(this);
        if (compilationErrorReporter == null) {
            compilationErrorReporter = compilerEnv.getErrorReporter();
        }
        if (this.debugger != null && sourceReader != null) {
            sourceString = Kit.readReader(sourceReader);
            sourceReader = null;
        }
        Parser p = new Parser(compilerEnv, compilationErrorReporter);
        if (returnFunction) {
            p.calledByCompileFunction = true;
        }
        AstRoot ast = sourceString != null ? p.parse(sourceString, sourceName, lineno) : p.parse(sourceReader, sourceName, lineno);
        if (returnFunction && (ast.getFirstChild() == null || ast.getFirstChild().getType() != 109)) {
            throw new IllegalArgumentException("compileFunction only accepts source with single JS function: " + sourceString);
        }
        IRFactory irf = new IRFactory(compilerEnv, compilationErrorReporter);
        ScriptNode tree = irf.transformTree(ast);
        p = null;
        ast = null;
        irf = null;
        if (compiler == null) {
            compiler = this.createCompiler();
        }
        Object bytecode = compiler.compile(compilerEnv, tree, tree.getEncodedSource(), returnFunction);
        if (this.debugger != null) {
            if (sourceString == null) {
                Kit.codeBug();
            }
            if (bytecode instanceof DebuggableScript) {
                DebuggableScript dscript = (DebuggableScript)bytecode;
                Context.notifyDebugger_r(this, dscript, sourceString);
            } else {
                throw new RuntimeException("NOT SUPPORTED");
            }
        }
        Object result = returnFunction ? compiler.createFunctionObject(this, scope, bytecode, securityDomain) : compiler.createScriptObject(bytecode, securityDomain);
        return result;
    }

    private static void notifyDebugger_r(Context cx, DebuggableScript dscript, String debugSource) {
        cx.debugger.handleCompilationDone(cx, dscript, debugSource);
        for (int i = 0; i != dscript.getFunctionCount(); ++i) {
            Context.notifyDebugger_r(cx, dscript.getFunction(i), debugSource);
        }
    }

    private Evaluator createCompiler() {
        Evaluator result = null;
        if (this.optimizationLevel >= 0 && codegenClass != null) {
            result = (Evaluator)Kit.newInstanceOrNull(codegenClass);
        }
        if (result == null) {
            result = Context.createInterpreter();
        }
        return result;
    }

    static Evaluator createInterpreter() {
        return (Evaluator)Kit.newInstanceOrNull(interpreterClass);
    }

    static String getSourcePositionFromStack(int[] linep) {
        StackTraceElement[] stackTrace;
        Evaluator evaluator;
        Context cx = Context.getCurrentContext();
        if (cx == null) {
            return null;
        }
        if (cx.lastInterpreterFrame != null && (evaluator = Context.createInterpreter()) != null) {
            return evaluator.getSourcePositionFromStack(cx, linep);
        }
        for (StackTraceElement st : stackTrace = new Throwable().getStackTrace()) {
            int line;
            String file = st.getFileName();
            if (file == null || file.endsWith(".java") || (line = st.getLineNumber()) < 0) continue;
            linep[0] = line;
            return file;
        }
        return null;
    }

    RegExpProxy getRegExpProxy() {
        Class<?> cl;
        if (this.regExpProxy == null && (cl = Kit.classOrNull("org.mozilla.javascript.regexp.RegExpImpl")) != null) {
            this.regExpProxy = (RegExpProxy)Kit.newInstanceOrNull(cl);
        }
        return this.regExpProxy;
    }

    final boolean isVersionECMA1() {
        return this.version == 0 || this.version >= 130;
    }

    SecurityController getSecurityController() {
        SecurityController global = SecurityController.global();
        if (global != null) {
            return global;
        }
        return this.securityController;
    }

    public final boolean isGeneratingDebugChanged() {
        return this.generatingDebugChanged;
    }

    public void addActivationName(String name) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (this.activationNames == null) {
            this.activationNames = new HashSet<String>();
        }
        this.activationNames.add(name);
    }

    public final boolean isActivationNeeded(String name) {
        return this.activationNames != null && this.activationNames.contains(name);
    }

    public void removeActivationName(String name) {
        if (this.sealed) {
            Context.onSealedMutation();
        }
        if (this.activationNames != null) {
            this.activationNames.remove(name);
        }
    }

    public static interface ClassShutterSetter {
        public void setClassShutter(ClassShutter var1);

        public ClassShutter getClassShutter();
    }
}

