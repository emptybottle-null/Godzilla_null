/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Synchronizer;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider;
import org.mozilla.javascript.serialize.ScriptableInputStream;
import org.mozilla.javascript.serialize.ScriptableOutputStream;
import org.mozilla.javascript.tools.ToolErrorReporter;
import org.mozilla.javascript.tools.shell.Environment;
import org.mozilla.javascript.tools.shell.Main;
import org.mozilla.javascript.tools.shell.PipeThread;
import org.mozilla.javascript.tools.shell.QuitAction;
import org.mozilla.javascript.tools.shell.Runner;
import org.mozilla.javascript.tools.shell.ShellConsole;

public class Global
extends ImporterTopLevel {
    static final long serialVersionUID = 4029130780977538005L;
    NativeArray history;
    boolean attemptedJLineLoad;
    private ShellConsole console;
    private InputStream inStream;
    private PrintStream outStream;
    private PrintStream errStream;
    private boolean sealedStdLib = false;
    boolean initialized;
    private QuitAction quitAction;
    private String[] prompts = new String[]{"js> ", "  > "};
    private HashMap<String, String> doctestCanonicalizations;

    public Global() {
    }

    public Global(Context cx) {
        this.init(cx);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void initQuitAction(QuitAction quitAction) {
        if (quitAction == null) {
            throw new IllegalArgumentException("quitAction is null");
        }
        if (this.quitAction != null) {
            throw new IllegalArgumentException("The method is once-call.");
        }
        this.quitAction = quitAction;
    }

    public void init(ContextFactory factory) {
        factory.call(new ContextAction(){

            @Override
            public Object run(Context cx) {
                Global.this.init(cx);
                return null;
            }
        });
    }

    public void init(Context cx) {
        this.initStandardObjects(cx, this.sealedStdLib);
        String[] names = new String[]{"defineClass", "deserialize", "doctest", "gc", "help", "load", "loadClass", "print", "quit", "readFile", "readUrl", "runCommand", "seal", "serialize", "spawn", "sync", "toint32", "version"};
        this.defineFunctionProperties(names, Global.class, 2);
        Environment.defineClass(this);
        Environment environment = new Environment(this);
        this.defineProperty("environment", environment, 2);
        this.history = (NativeArray)cx.newArray((Scriptable)this, 0);
        this.defineProperty("history", this.history, 2);
        this.initialized = true;
    }

    public Require installRequire(Context cx, List<String> modulePath, boolean sandboxed) {
        RequireBuilder rb = new RequireBuilder();
        rb.setSandboxed(sandboxed);
        ArrayList<URI> uris = new ArrayList<URI>();
        if (modulePath != null) {
            for (String path : modulePath) {
                try {
                    URI uri = new URI(path);
                    if (!uri.isAbsolute()) {
                        uri = new File(path).toURI().resolve("");
                    }
                    if (!uri.toString().endsWith("/")) {
                        uri = new URI(uri + "/");
                    }
                    uris.add(uri);
                } catch (URISyntaxException usx) {
                    throw new RuntimeException(usx);
                }
            }
        }
        rb.setModuleScriptProvider(new SoftCachingModuleScriptProvider(new UrlModuleSourceProvider(uris, null)));
        Require require = rb.createRequire(cx, this);
        require.install(this);
        return require;
    }

    public static void help(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        PrintStream out = Global.getInstance(funObj).getOut();
        out.println(ToolErrorReporter.getMessage("msg.help"));
    }

    public static void gc(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        System.gc();
    }

    public static Object print(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        PrintStream out = Global.getInstance(funObj).getOut();
        for (int i = 0; i < args.length; ++i) {
            if (i > 0) {
                out.print(" ");
            }
            String s = Context.toString(args[i]);
            out.print(s);
        }
        out.println();
        return Context.getUndefinedValue();
    }

    public static void quit(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Global global = Global.getInstance(funObj);
        if (global.quitAction != null) {
            int exitCode = args.length == 0 ? 0 : ScriptRuntime.toInt32(args[0]);
            global.quitAction.quit(cx, exitCode);
        }
    }

    public static double version(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        double result = cx.getLanguageVersion();
        if (args.length > 0) {
            double d = Context.toNumber(args[0]);
            cx.setLanguageVersion((int)d);
        }
        return result;
    }

    public static void load(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        for (Object arg : args) {
            String file = Context.toString(arg);
            try {
                Main.processFile(cx, thisObj, file);
            } catch (IOException ioex) {
                String msg = ToolErrorReporter.getMessage("msg.couldnt.read.source", file, ioex.getMessage());
                throw Context.reportRuntimeError(msg);
            } catch (VirtualMachineError ex) {
                ex.printStackTrace();
                String msg = ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.toString());
                throw Context.reportRuntimeError(msg);
            }
        }
    }

    public static void defineClass(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = Global.getClass(args);
        if (!Scriptable.class.isAssignableFrom(clazz)) {
            throw Global.reportRuntimeError("msg.must.implement.Scriptable");
        }
        ScriptableObject.defineClass(thisObj, clazz);
    }

    public static void loadClass(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = Global.getClass(args);
        if (!Script.class.isAssignableFrom(clazz)) {
            throw Global.reportRuntimeError("msg.must.implement.Script");
        }
        Script script = (Script)clazz.newInstance();
        script.exec(cx, thisObj);
    }

    private static Class<?> getClass(Object[] args) {
        Object wrapped;
        if (args.length == 0) {
            throw Global.reportRuntimeError("msg.expected.string.arg");
        }
        Object arg0 = args[0];
        if (arg0 instanceof Wrapper && (wrapped = ((Wrapper)arg0).unwrap()) instanceof Class) {
            return (Class)wrapped;
        }
        String className = Context.toString(args[0]);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw Global.reportRuntimeError("msg.class.not.found", className);
        }
    }

    public static void serialize(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IOException {
        if (args.length < 2) {
            throw Context.reportRuntimeError("Expected an object to serialize and a filename to write the serialization to");
        }
        Object obj = args[0];
        String filename = Context.toString(args[1]);
        FileOutputStream fos = new FileOutputStream(filename);
        Scriptable scope = ScriptableObject.getTopLevelScope(thisObj);
        ScriptableOutputStream out = new ScriptableOutputStream(fos, scope);
        out.writeObject(obj);
        out.close();
    }

    public static Object deserialize(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IOException, ClassNotFoundException {
        if (args.length < 1) {
            throw Context.reportRuntimeError("Expected a filename to read the serialization from");
        }
        String filename = Context.toString(args[0]);
        FileInputStream fis = new FileInputStream(filename);
        Scriptable scope = ScriptableObject.getTopLevelScope(thisObj);
        ScriptableInputStream in = new ScriptableInputStream(fis, scope);
        Object deserialized = in.readObject();
        in.close();
        return Context.toObject(deserialized, scope);
    }

    public String[] getPrompts(Context cx) {
        Scriptable s;
        Object promptsJS;
        if (ScriptableObject.hasProperty((Scriptable)this, "prompts") && (promptsJS = ScriptableObject.getProperty((Scriptable)this, "prompts")) instanceof Scriptable && ScriptableObject.hasProperty(s = (Scriptable)promptsJS, 0) && ScriptableObject.hasProperty(s, 1)) {
            Object elem0 = ScriptableObject.getProperty(s, 0);
            if (elem0 instanceof Function) {
                elem0 = ((Function)elem0).call(cx, this, s, new Object[0]);
            }
            this.prompts[0] = Context.toString(elem0);
            Object elem1 = ScriptableObject.getProperty(s, 1);
            if (elem1 instanceof Function) {
                elem1 = ((Function)elem1).call(cx, this, s, new Object[0]);
            }
            this.prompts[1] = Context.toString(elem1);
        }
        return this.prompts;
    }

    public static Object doctest(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        if (args.length == 0) {
            return Boolean.FALSE;
        }
        String session = Context.toString(args[0]);
        Global global = Global.getInstance(funObj);
        return new Integer(global.runDoctest(cx, global, session, null, 0));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int runDoctest(Context cx, Scriptable scope, String session, String sourceName, int lineNumber) {
        int i;
        this.doctestCanonicalizations = new HashMap();
        String[] lines = session.split("\r\n?|\n");
        String prompt0 = this.prompts[0].trim();
        String prompt1 = this.prompts[1].trim();
        int testCount = 0;
        for (i = 0; i < lines.length && !lines[i].trim().startsWith(prompt0); ++i) {
        }
        while (i < lines.length) {
            String inputString = lines[i].trim().substring(prompt0.length());
            inputString = inputString + "\n";
            ++i;
            while (i < lines.length && lines[i].trim().startsWith(prompt1)) {
                inputString = inputString + lines[i].trim().substring(prompt1.length());
                inputString = inputString + "\n";
                ++i;
            }
            String expectedString = "";
            while (i < lines.length && !lines[i].trim().startsWith(prompt0)) {
                expectedString = expectedString + lines[i] + "\n";
                ++i;
            }
            PrintStream savedOut = this.getOut();
            PrintStream savedErr = this.getErr();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            this.setOut(new PrintStream(out));
            this.setErr(new PrintStream(err));
            String resultString = "";
            ErrorReporter savedErrorReporter = cx.getErrorReporter();
            cx.setErrorReporter(new ToolErrorReporter(false, this.getErr()));
            try {
                ++testCount;
                Object result = cx.evaluateString(scope, inputString, "doctest input", 1, null);
                if (!(result == Context.getUndefinedValue() || result instanceof Function && inputString.trim().startsWith("function"))) {
                    resultString = Context.toString(result);
                }
            } catch (RhinoException e) {
                ToolErrorReporter.reportException(cx.getErrorReporter(), e);
            } finally {
                this.setOut(savedOut);
                this.setErr(savedErr);
                cx.setErrorReporter(savedErrorReporter);
                resultString = resultString + err.toString() + out.toString();
            }
            if (this.doctestOutputMatches(expectedString, resultString)) continue;
            String message = "doctest failure running:\n" + inputString + "expected: " + expectedString + "actual: " + resultString + "\n";
            if (sourceName != null) {
                throw Context.reportRuntimeError(message, sourceName, lineNumber + i - 1, null, 0);
            }
            throw Context.reportRuntimeError(message);
        }
        return testCount;
    }

    private boolean doctestOutputMatches(String expected, String actual) {
        if ((expected = expected.trim()).equals(actual = actual.trim().replace("\r\n", "\n"))) {
            return true;
        }
        for (Map.Entry<String, String> entry : this.doctestCanonicalizations.entrySet()) {
            expected = expected.replace(entry.getKey(), entry.getValue());
        }
        if (expected.equals(actual)) {
            return true;
        }
        Pattern p = Pattern.compile("@[0-9a-fA-F]+");
        Matcher expectedMatcher = p.matcher(expected);
        Matcher actualMatcher = p.matcher(actual);
        do {
            if (!expectedMatcher.find()) {
                return false;
            }
            if (!actualMatcher.find()) {
                return false;
            }
            if (actualMatcher.start() != expectedMatcher.start()) {
                return false;
            }
            int start = expectedMatcher.start();
            if (!expected.substring(0, start).equals(actual.substring(0, start))) {
                return false;
            }
            String expectedGroup = expectedMatcher.group();
            String actualGroup = actualMatcher.group();
            String mapping = this.doctestCanonicalizations.get(expectedGroup);
            if (mapping == null) {
                this.doctestCanonicalizations.put(expectedGroup, actualGroup);
                expected = expected.replace(expectedGroup, actualGroup);
                continue;
            }
            if (actualGroup.equals(mapping)) continue;
            return false;
        } while (!expected.equals(actual));
        return true;
    }

    public static Object spawn(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Runner runner;
        Scriptable scope = funObj.getParentScope();
        if (args.length != 0 && args[0] instanceof Function) {
            Object[] newArgs = null;
            if (args.length > 1 && args[1] instanceof Scriptable) {
                newArgs = cx.getElements((Scriptable)args[1]);
            }
            if (newArgs == null) {
                newArgs = ScriptRuntime.emptyArgs;
            }
            runner = new Runner(scope, (Function)args[0], newArgs);
        } else if (args.length != 0 && args[0] instanceof Script) {
            runner = new Runner(scope, (Script)args[0]);
        } else {
            throw Global.reportRuntimeError("msg.spawn.args");
        }
        runner.factory = cx.getFactory();
        Thread thread = new Thread(runner);
        thread.start();
        return thread;
    }

    public static Object sync(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        if (args.length >= 1 && args.length <= 2 && args[0] instanceof Function) {
            Object syncObject = null;
            if (args.length == 2 && args[1] != Undefined.instance) {
                syncObject = args[1];
            }
            return new Synchronizer((Function)args[0], syncObject);
        }
        throw Global.reportRuntimeError("msg.sync.args");
    }

    public static Object runCommand(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IOException {
        String s;
        int i;
        int L = args.length;
        if (L == 0 || L == 1 && args[0] instanceof Scriptable) {
            throw Global.reportRuntimeError("msg.runCommand.bad.args");
        }
        File wd = null;
        InputStream in = null;
        OutputStream out = null;
        OutputStream err = null;
        ByteArrayOutputStream outBytes = null;
        ByteArrayOutputStream errBytes = null;
        Object outObj = null;
        Object errObj = null;
        String[] environment = null;
        Scriptable params = null;
        Object[] addArgs = null;
        if (args[L - 1] instanceof Scriptable) {
            Object addArgsObj;
            Object inObj;
            Object wdObj;
            params = (Scriptable)args[L - 1];
            --L;
            Object envObj = ScriptableObject.getProperty(params, "env");
            if (envObj != Scriptable.NOT_FOUND) {
                if (envObj == null) {
                    environment = new String[]{};
                } else {
                    if (!(envObj instanceof Scriptable)) {
                        throw Global.reportRuntimeError("msg.runCommand.bad.env");
                    }
                    Scriptable envHash = (Scriptable)envObj;
                    Object[] ids = ScriptableObject.getPropertyIds(envHash);
                    environment = new String[ids.length];
                    for (int i2 = 0; i2 != ids.length; ++i2) {
                        Object val;
                        String key;
                        Object keyObj = ids[i2];
                        if (keyObj instanceof String) {
                            key = (String)keyObj;
                            val = ScriptableObject.getProperty(envHash, key);
                        } else {
                            int ikey = ((Number)keyObj).intValue();
                            key = Integer.toString(ikey);
                            val = ScriptableObject.getProperty(envHash, ikey);
                        }
                        if (val == ScriptableObject.NOT_FOUND) {
                            val = Undefined.instance;
                        }
                        environment[i2] = key + '=' + ScriptRuntime.toString(val);
                    }
                }
            }
            if ((wdObj = ScriptableObject.getProperty(params, "dir")) != Scriptable.NOT_FOUND) {
                wd = new File(ScriptRuntime.toString(wdObj));
            }
            if ((inObj = ScriptableObject.getProperty(params, "input")) != Scriptable.NOT_FOUND) {
                in = Global.toInputStream(inObj);
            }
            if ((outObj = ScriptableObject.getProperty(params, "output")) != Scriptable.NOT_FOUND && (out = Global.toOutputStream(outObj)) == null) {
                outBytes = new ByteArrayOutputStream();
                out = outBytes;
            }
            if ((errObj = ScriptableObject.getProperty(params, "err")) != Scriptable.NOT_FOUND && (err = Global.toOutputStream(errObj)) == null) {
                errBytes = new ByteArrayOutputStream();
                err = errBytes;
            }
            if ((addArgsObj = ScriptableObject.getProperty(params, "args")) != Scriptable.NOT_FOUND) {
                Scriptable s2 = Context.toObject(addArgsObj, Global.getTopLevelScope(thisObj));
                addArgs = cx.getElements(s2);
            }
        }
        Global global = Global.getInstance(funObj);
        if (out == null) {
            PrintStream printStream = out = global != null ? global.getOut() : System.out;
        }
        if (err == null) {
            err = global != null ? global.getErr() : System.err;
        }
        String[] cmd = new String[addArgs == null ? L : L + addArgs.length];
        for (i = 0; i != L; ++i) {
            cmd[i] = ScriptRuntime.toString(args[i]);
        }
        if (addArgs != null) {
            for (i = 0; i != addArgs.length; ++i) {
                cmd[L + i] = ScriptRuntime.toString(addArgs[i]);
            }
        }
        int exitCode = Global.runProcess(cmd, environment, wd, in, out, err);
        if (outBytes != null) {
            s = ScriptRuntime.toString(outObj) + outBytes.toString();
            ScriptableObject.putProperty(params, "output", (Object)s);
        }
        if (errBytes != null) {
            s = ScriptRuntime.toString(errObj) + errBytes.toString();
            ScriptableObject.putProperty(params, "err", (Object)s);
        }
        return new Integer(exitCode);
    }

    public static void seal(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Object arg;
        int i;
        for (i = 0; i != args.length; ++i) {
            arg = args[i];
            if (arg instanceof ScriptableObject && arg != Undefined.instance) continue;
            if (!(arg instanceof Scriptable) || arg == Undefined.instance) {
                throw Global.reportRuntimeError("msg.shell.seal.not.object");
            }
            throw Global.reportRuntimeError("msg.shell.seal.not.scriptable");
        }
        for (i = 0; i != args.length; ++i) {
            arg = args[i];
            ((ScriptableObject)arg).sealObject();
        }
    }

    public static Object readFile(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IOException {
        if (args.length == 0) {
            throw Global.reportRuntimeError("msg.shell.readFile.bad.args");
        }
        String path = ScriptRuntime.toString(args[0]);
        String charCoding = null;
        if (args.length >= 2) {
            charCoding = ScriptRuntime.toString(args[1]);
        }
        return Global.readUrl(path, charCoding, true);
    }

    public static Object readUrl(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws IOException {
        if (args.length == 0) {
            throw Global.reportRuntimeError("msg.shell.readUrl.bad.args");
        }
        String url = ScriptRuntime.toString(args[0]);
        String charCoding = null;
        if (args.length >= 2) {
            charCoding = ScriptRuntime.toString(args[1]);
        }
        return Global.readUrl(url, charCoding, false);
    }

    public static Object toint32(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Object arg;
        Object object = arg = args.length != 0 ? args[0] : Undefined.instance;
        if (arg instanceof Integer) {
            return arg;
        }
        return ScriptRuntime.wrapInt(ScriptRuntime.toInt32(arg));
    }

    private boolean loadJLine(Charset cs) {
        if (!this.attemptedJLineLoad) {
            this.attemptedJLineLoad = true;
            this.console = ShellConsole.getConsole(this, cs);
        }
        return this.console != null;
    }

    public ShellConsole getConsole(Charset cs) {
        if (!this.loadJLine(cs)) {
            this.console = ShellConsole.getConsole(this.getIn(), this.getErr(), cs);
        }
        return this.console;
    }

    public InputStream getIn() {
        if (this.inStream == null && !this.attemptedJLineLoad && this.loadJLine(Charset.defaultCharset())) {
            this.inStream = this.console.getIn();
        }
        return this.inStream == null ? System.in : this.inStream;
    }

    public void setIn(InputStream in) {
        this.inStream = in;
    }

    public PrintStream getOut() {
        return this.outStream == null ? System.out : this.outStream;
    }

    public void setOut(PrintStream out) {
        this.outStream = out;
    }

    public PrintStream getErr() {
        return this.errStream == null ? System.err : this.errStream;
    }

    public void setErr(PrintStream err) {
        this.errStream = err;
    }

    public void setSealedStdLib(boolean value) {
        this.sealedStdLib = value;
    }

    private static Global getInstance(Function function) {
        Scriptable scope = function.getParentScope();
        if (!(scope instanceof Global)) {
            throw Global.reportRuntimeError("msg.bad.shell.function.scope", String.valueOf(scope));
        }
        return (Global)scope;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static int runProcess(String[] cmd, String[] environment, File wd, InputStream in, OutputStream out, OutputStream err) throws IOException {
        Process p = environment == null ? Runtime.getRuntime().exec(cmd, null, wd) : Runtime.getRuntime().exec(cmd, environment, wd);
        try {
            PipeThread inThread = null;
            if (in != null) {
                inThread = new PipeThread(false, in, p.getOutputStream());
                inThread.start();
            } else {
                p.getOutputStream().close();
            }
            PipeThread outThread = null;
            if (out != null) {
                outThread = new PipeThread(true, p.getInputStream(), out);
                outThread.start();
            } else {
                p.getInputStream().close();
            }
            PipeThread errThread = null;
            if (err != null) {
                errThread = new PipeThread(true, p.getErrorStream(), err);
                errThread.start();
            } else {
                p.getErrorStream().close();
            }
            while (true) {
                try {
                    p.waitFor();
                    if (outThread != null) {
                        outThread.join();
                    }
                    if (inThread != null) {
                        inThread.join();
                    }
                    if (errThread == null) break;
                    errThread.join();
                } catch (InterruptedException ignore) {
                    continue;
                }
                break;
            }
            int n = p.exitValue();
            return n;
        } finally {
            p.destroy();
        }
    }

    /*
     * Exception decompiling
     */
    static void pipe(boolean fromProcess, InputStream from, OutputStream to) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 11[UNCONDITIONALDOLOOP]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private static InputStream toInputStream(Object value) throws IOException {
        InputStream is = null;
        String s = null;
        if (value instanceof Wrapper) {
            Object unwrapped = ((Wrapper)value).unwrap();
            if (unwrapped instanceof InputStream) {
                is = (InputStream)unwrapped;
            } else if (unwrapped instanceof byte[]) {
                is = new ByteArrayInputStream((byte[])unwrapped);
            } else if (unwrapped instanceof Reader) {
                s = Global.readReader((Reader)unwrapped);
            } else if (unwrapped instanceof char[]) {
                s = new String((char[])unwrapped);
            }
        }
        if (is == null) {
            if (s == null) {
                s = ScriptRuntime.toString(value);
            }
            is = new ByteArrayInputStream(s.getBytes());
        }
        return is;
    }

    private static OutputStream toOutputStream(Object value) {
        Object unwrapped;
        OutputStream os = null;
        if (value instanceof Wrapper && (unwrapped = ((Wrapper)value).unwrap()) instanceof OutputStream) {
            os = (OutputStream)unwrapped;
        }
        return os;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String readUrl(String filePath, String charCoding, boolean urlIsFile) throws IOException {
        InputStream is = null;
        try {
            int chunkLength;
            if (!urlIsFile) {
                String type;
                URL urlObj = new URL(filePath);
                URLConnection uc = urlObj.openConnection();
                is = uc.getInputStream();
                chunkLength = uc.getContentLength();
                if (chunkLength <= 0) {
                    chunkLength = 1024;
                }
                if (charCoding == null && (type = uc.getContentType()) != null) {
                    charCoding = Global.getCharCodingFromType(type);
                }
            } else {
                File f = new File(filePath);
                if (!f.exists()) {
                    throw new FileNotFoundException("File not found: " + filePath);
                }
                if (!f.canRead()) {
                    throw new IOException("Cannot read file: " + filePath);
                }
                long length = f.length();
                chunkLength = (int)length;
                if ((long)chunkLength != length) {
                    throw new IOException("Too big file size: " + length);
                }
                if (chunkLength == 0) {
                    String string = "";
                    return string;
                }
                is = new FileInputStream(f);
            }
            InputStreamReader r = charCoding == null ? new InputStreamReader(is) : new InputStreamReader(is, charCoding);
            String string = Global.readReader(r, chunkLength);
            return string;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private static String getCharCodingFromType(String type) {
        int i = type.indexOf(59);
        if (i >= 0) {
            int end = type.length();
            ++i;
            while (i != end && type.charAt(i) <= ' ') {
                ++i;
            }
            String charset = "charset";
            if (charset.regionMatches(true, 0, type, i, charset.length())) {
                i += charset.length();
                while (i != end && type.charAt(i) <= ' ') {
                    ++i;
                }
                if (i != end && type.charAt(i) == '=') {
                    ++i;
                    while (i != end && type.charAt(i) <= ' ') {
                        ++i;
                    }
                    if (i != end) {
                        while (type.charAt(end - 1) <= ' ') {
                            --end;
                        }
                        return type.substring(i, end);
                    }
                }
            }
        }
        return null;
    }

    private static String readReader(Reader reader) throws IOException {
        return Global.readReader(reader, 4096);
    }

    private static String readReader(Reader reader, int initialBufferSize) throws IOException {
        int n;
        char[] buffer = new char[initialBufferSize];
        int offset = 0;
        while ((n = reader.read(buffer, offset, buffer.length - offset)) >= 0) {
            if ((offset += n) != buffer.length) continue;
            char[] tmp = new char[buffer.length * 2];
            System.arraycopy(buffer, 0, tmp, 0, offset);
            buffer = tmp;
        }
        return new String(buffer, 0, offset);
    }

    static RuntimeException reportRuntimeError(String msgId) {
        String message = ToolErrorReporter.getMessage(msgId);
        return Context.reportRuntimeError(message);
    }

    static RuntimeException reportRuntimeError(String msgId, String msgArg) {
        String message = ToolErrorReporter.getMessage(msgId, msgArg);
        return Context.reportRuntimeError(message);
    }
}

