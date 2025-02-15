/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.commonjs.module.ModuleScope;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.QuitAction;
import org.mozilla.javascript.tools.shell.SecurityProxy;
import org.mozilla.javascript.tools.shell.ShellConsole;
import org.mozilla.javascript.tools.shell.ShellContextFactory;

public class Main {
    public static ShellContextFactory shellContextFactory = new ShellContextFactory();
    public static Global global = new Global();
    protected static ToolErrorReporter errorReporter;
    protected static int exitCode;
    private static final int EXITCODE_RUNTIME_ERROR = 3;
    private static final int EXITCODE_FILE_NOT_FOUND = 4;
    static boolean processStdin;
    static List<String> fileList;
    static List<String> modulePath;
    static String mainModule;
    static boolean sandboxed;
    static boolean useRequire;
    static Require require;
    private static SecurityProxy securityImpl;
    private static final ScriptCache scriptCache;

    public static void main(String[] args) {
        try {
            if (Boolean.getBoolean("rhino.use_java_policy_security")) {
                Main.initJavaPolicySecuritySupport();
            }
        } catch (SecurityException ex) {
            ex.printStackTrace(System.err);
        }
        int result = Main.exec(args);
        if (result != 0) {
            System.exit(result);
        }
    }

    public static int exec(String[] origArgs) {
        errorReporter = new ToolErrorReporter(false, global.getErr());
        shellContextFactory.setErrorReporter(errorReporter);
        String[] args = Main.processOptions(origArgs);
        if (exitCode > 0) {
            return exitCode;
        }
        if (processStdin) {
            fileList.add(null);
        }
        if (!Main.global.initialized) {
            global.init(shellContextFactory);
        }
        IProxy iproxy = new IProxy(1);
        iproxy.args = args;
        shellContextFactory.call(iproxy);
        return exitCode;
    }

    static void processFiles(Context cx, String[] args) {
        Object[] array = new Object[args.length];
        System.arraycopy(args, 0, array, 0, args.length);
        Scriptable argsObj = cx.newArray((Scriptable)global, array);
        global.defineProperty("arguments", argsObj, 2);
        for (String file : fileList) {
            try {
                Main.processSource(cx, file);
            } catch (IOException ioex) {
                Context.reportError(ToolErrorReporter.getMessage("msg.couldnt.read.source", file, ioex.getMessage()));
                exitCode = 4;
            } catch (RhinoException rex) {
                ToolErrorReporter.reportException(cx.getErrorReporter(), rex);
                exitCode = 3;
            } catch (VirtualMachineError ex) {
                ex.printStackTrace();
                String msg = ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.toString());
                Context.reportError(msg);
                exitCode = 3;
            }
        }
    }

    static void evalInlineScript(Context cx, String scriptText) {
        try {
            Script script = cx.compileString(scriptText, "<command>", 1, null);
            if (script != null) {
                script.exec(cx, Main.getShellScope());
            }
        } catch (RhinoException rex) {
            ToolErrorReporter.reportException(cx.getErrorReporter(), rex);
            exitCode = 3;
        } catch (VirtualMachineError ex) {
            ex.printStackTrace();
            String msg = ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.toString());
            Context.reportError(msg);
            exitCode = 3;
        }
    }

    public static Global getGlobal() {
        return global;
    }

    static Scriptable getShellScope() {
        return Main.getScope(null);
    }

    static Scriptable getScope(String path) {
        if (useRequire) {
            URI uri;
            if (path == null) {
                uri = new File(System.getProperty("user.dir")).toURI();
            } else if (SourceReader.toUrl(path) != null) {
                try {
                    uri = new URI(path);
                } catch (URISyntaxException x) {
                    uri = new File(path).toURI();
                }
            } else {
                uri = new File(path).toURI();
            }
            return new ModuleScope(global, uri, null);
        }
        return global;
    }

    public static String[] processOptions(String[] args) {
        String usageError;
        int i = 0;
        while (true) {
            if (i == args.length) {
                return new String[0];
            }
            String arg = args[i];
            if (!arg.startsWith("-")) {
                processStdin = false;
                fileList.add(arg);
                mainModule = arg;
                String[] result = new String[args.length - i - 1];
                System.arraycopy(args, i + 1, result, 0, args.length - i - 1);
                return result;
            }
            if (arg.equals("-version")) {
                int version;
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                try {
                    version = Integer.parseInt(args[i]);
                } catch (NumberFormatException ex) {
                    usageError = args[i];
                    break;
                }
                if (!Context.isValidLanguageVersion(version)) {
                    usageError = args[i];
                    break;
                }
                shellContextFactory.setLanguageVersion(version);
            } else if (arg.equals("-opt") || arg.equals("-O")) {
                int opt;
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                try {
                    opt = Integer.parseInt(args[i]);
                } catch (NumberFormatException ex) {
                    usageError = args[i];
                    break;
                }
                if (opt == -2) {
                    opt = -1;
                } else if (!Context.isValidOptimizationLevel(opt)) {
                    usageError = args[i];
                    break;
                }
                shellContextFactory.setOptimizationLevel(opt);
            } else if (arg.equals("-encoding")) {
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                String enc = args[i];
                shellContextFactory.setCharacterEncoding(enc);
            } else if (arg.equals("-strict")) {
                shellContextFactory.setStrictMode(true);
                shellContextFactory.setAllowReservedKeywords(false);
                errorReporter.setIsReportingWarnings(true);
            } else if (arg.equals("-fatal-warnings")) {
                shellContextFactory.setWarningAsError(true);
            } else if (arg.equals("-e")) {
                processStdin = false;
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                if (!Main.global.initialized) {
                    global.init(shellContextFactory);
                }
                IProxy iproxy = new IProxy(2);
                iproxy.scriptText = args[i];
                shellContextFactory.call(iproxy);
            } else if (arg.equals("-require")) {
                useRequire = true;
            } else if (arg.equals("-sandbox")) {
                sandboxed = true;
                useRequire = true;
            } else if (arg.equals("-modules")) {
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                if (modulePath == null) {
                    modulePath = new ArrayList<String>();
                }
                modulePath.add(args[i]);
                useRequire = true;
            } else if (arg.equals("-w")) {
                errorReporter.setIsReportingWarnings(true);
            } else if (arg.equals("-f")) {
                processStdin = false;
                if (++i == args.length) {
                    usageError = arg;
                    break;
                }
                if (args[i].equals("-")) {
                    fileList.add(null);
                } else {
                    fileList.add(args[i]);
                    mainModule = args[i];
                }
            } else if (arg.equals("-sealedlib")) {
                global.setSealedStdLib(true);
            } else if (arg.equals("-debug")) {
                shellContextFactory.setGeneratingDebug(true);
            } else {
                if (arg.equals("-?") || arg.equals("-help")) {
                    global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", Main.class.getName()));
                    exitCode = 1;
                    return null;
                }
                usageError = arg;
                break;
            }
            ++i;
        }
        global.getOut().println(ToolErrorReporter.getMessage("msg.shell.invalid", usageError));
        global.getOut().println(ToolErrorReporter.getMessage("msg.shell.usage", Main.class.getName()));
        exitCode = 1;
        return null;
    }

    private static void initJavaPolicySecuritySupport() {
        Throwable exObj;
        try {
            Class<?> cl = Class.forName("org.mozilla.javascript.tools.shell.JavaPolicySecurity");
            securityImpl = (SecurityProxy)cl.newInstance();
            SecurityController.initGlobal(securityImpl);
            return;
        } catch (ClassNotFoundException ex) {
            exObj = ex;
        } catch (IllegalAccessException ex) {
            exObj = ex;
        } catch (InstantiationException ex) {
            exObj = ex;
        } catch (LinkageError ex) {
            exObj = ex;
        }
        throw Kit.initCause(new IllegalStateException("Can not load security support: " + exObj), exObj);
    }

    public static void processSource(Context cx, String filename) throws IOException {
        if (filename == null || filename.equals("-")) {
            Scriptable scope = Main.getShellScope();
            String charEnc = shellContextFactory.getCharacterEncoding();
            Charset cs = charEnc != null ? Charset.forName(charEnc) : Charset.defaultCharset();
            ShellConsole console = global.getConsole(cs);
            if (filename == null) {
                console.println(cx.getImplementationVersion());
            }
            int lineno = 1;
            boolean hitEOF = false;
            while (!hitEOF) {
                String[] prompts = global.getPrompts(cx);
                String prompt = null;
                if (filename == null) {
                    prompt = prompts[0];
                }
                console.flush();
                String source = "";
                while (true) {
                    String newline;
                    try {
                        newline = console.readLine(prompt);
                    } catch (IOException ioe) {
                        console.println(ioe.toString());
                        break;
                    }
                    if (newline == null) {
                        hitEOF = true;
                        break;
                    }
                    source = source + newline + "\n";
                    ++lineno;
                    if (cx.stringIsCompilableUnit(source)) break;
                    prompt = prompts[1];
                }
                try {
                    Script script = cx.compileString(source, "<stdin>", lineno, null);
                    if (script == null) continue;
                    Object result = script.exec(cx, scope);
                    if (!(result == Context.getUndefinedValue() || result instanceof Function && source.trim().startsWith("function"))) {
                        try {
                            console.println(Context.toString(result));
                        } catch (RhinoException rex) {
                            ToolErrorReporter.reportException(cx.getErrorReporter(), rex);
                        }
                    }
                    NativeArray h = Main.global.history;
                    h.put((int)h.getLength(), (Scriptable)h, (Object)source);
                } catch (RhinoException rex) {
                    ToolErrorReporter.reportException(cx.getErrorReporter(), rex);
                    exitCode = 3;
                } catch (VirtualMachineError ex) {
                    ex.printStackTrace();
                    String msg = ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.toString());
                    Context.reportError(msg);
                    exitCode = 3;
                }
            }
            console.println();
            console.flush();
        } else if (useRequire && filename.equals(mainModule)) {
            require.requireMain(cx, filename);
        } else {
            Main.processFile(cx, Main.getScope(filename), filename);
        }
    }

    public static void processFileNoThrow(Context cx, Scriptable scope, String filename) {
        try {
            Main.processFile(cx, scope, filename);
        } catch (IOException ioex) {
            Context.reportError(ToolErrorReporter.getMessage("msg.couldnt.read.source", filename, ioex.getMessage()));
            exitCode = 4;
        } catch (RhinoException rex) {
            ToolErrorReporter.reportException(cx.getErrorReporter(), rex);
            exitCode = 3;
        } catch (VirtualMachineError ex) {
            ex.printStackTrace();
            String msg = ToolErrorReporter.getMessage("msg.uncaughtJSException", ex.toString());
            Context.reportError(msg);
            exitCode = 3;
        }
    }

    public static void processFile(Context cx, Scriptable scope, String filename) throws IOException {
        if (securityImpl == null) {
            Main.processFileSecure(cx, scope, filename, null);
        } else {
            securityImpl.callProcessFileSecure(cx, scope, filename);
        }
    }

    static void processFileSecure(Context cx, Scriptable scope, String path, Object securityDomain) throws IOException {
        Script script;
        boolean isClass = path.endsWith(".class");
        Object source = Main.readFileOrUrl(path, !isClass);
        byte[] digest = Main.getDigest(source);
        String key = path + "_" + cx.getOptimizationLevel();
        ScriptReference ref = scriptCache.get(key, digest);
        Script script2 = script = ref != null ? (Script)ref.get() : null;
        if (script == null) {
            if (isClass) {
                script = Main.loadCompiledScript(cx, path, (byte[])source, securityDomain);
            } else {
                String strSrc = (String)source;
                if (strSrc.length() > 0 && strSrc.charAt(0) == '#') {
                    for (int i = 1; i != strSrc.length(); ++i) {
                        char c = strSrc.charAt(i);
                        if (c != '\n' && c != '\r') continue;
                        strSrc = strSrc.substring(i);
                        break;
                    }
                }
                script = cx.compileString(strSrc, path, 1, securityDomain);
            }
            scriptCache.put(key, digest, script);
        }
        if (script != null) {
            script.exec(cx, scope);
        }
    }

    private static byte[] getDigest(Object source) {
        byte[] digest = null;
        if (source != null) {
            byte[] bytes;
            if (source instanceof String) {
                try {
                    bytes = ((String)source).getBytes("UTF-8");
                } catch (UnsupportedEncodingException ue) {
                    bytes = ((String)source).getBytes();
                }
            } else {
                bytes = (byte[])source;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                digest = md.digest(bytes);
            } catch (NoSuchAlgorithmException nsa) {
                throw new RuntimeException(nsa);
            }
        }
        return digest;
    }

    private static Script loadCompiledScript(Context cx, String path, byte[] data, Object securityDomain) throws FileNotFoundException {
        if (data == null) {
            throw new FileNotFoundException(path);
        }
        int nameStart = path.lastIndexOf(47);
        nameStart = nameStart < 0 ? 0 : ++nameStart;
        int nameEnd = path.lastIndexOf(46);
        if (nameEnd < nameStart) {
            nameEnd = path.length();
        }
        String name = path.substring(nameStart, nameEnd);
        try {
            GeneratedClassLoader loader = SecurityController.createLoader(cx.getApplicationClassLoader(), securityDomain);
            Class<?> clazz = loader.defineClass(name, data);
            loader.linkClass(clazz);
            if (!Script.class.isAssignableFrom(clazz)) {
                throw Context.reportRuntimeError("msg.must.implement.Script");
            }
            return (Script)clazz.newInstance();
        } catch (IllegalAccessException iaex) {
            Context.reportError(iaex.toString());
            throw new RuntimeException(iaex);
        } catch (InstantiationException inex) {
            Context.reportError(inex.toString());
            throw new RuntimeException(inex);
        }
    }

    public static InputStream getIn() {
        return Main.getGlobal().getIn();
    }

    public static void setIn(InputStream in) {
        Main.getGlobal().setIn(in);
    }

    public static PrintStream getOut() {
        return Main.getGlobal().getOut();
    }

    public static void setOut(PrintStream out) {
        Main.getGlobal().setOut(out);
    }

    public static PrintStream getErr() {
        return Main.getGlobal().getErr();
    }

    public static void setErr(PrintStream err) {
        Main.getGlobal().setErr(err);
    }

    private static Object readFileOrUrl(String path, boolean convertToString) throws IOException {
        return SourceReader.readFileOrUrl(path, convertToString, shellContextFactory.getCharacterEncoding());
    }

    static {
        exitCode = 0;
        processStdin = true;
        fileList = new ArrayList<String>();
        sandboxed = false;
        useRequire = false;
        scriptCache = new ScriptCache(32);
        global.initQuitAction(new IProxy(3));
    }

    static class ScriptCache
    extends LinkedHashMap<String, ScriptReference> {
        ReferenceQueue<Script> queue;
        int capacity;

        ScriptCache(int capacity) {
            super(capacity + 1, 2.0f, true);
            this.capacity = capacity;
            this.queue = new ReferenceQueue();
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, ScriptReference> eldest) {
            return this.size() > this.capacity;
        }

        ScriptReference get(String path, byte[] digest) {
            ScriptReference ref;
            while ((ref = (ScriptReference)this.queue.poll()) != null) {
                this.remove(ref.path);
            }
            ref = (ScriptReference)this.get(path);
            if (ref != null && !Arrays.equals(digest, ref.digest)) {
                this.remove(ref.path);
                ref = null;
            }
            return ref;
        }

        void put(String path, byte[] digest, Script script) {
            this.put(path, new ScriptReference(path, digest, script, this.queue));
        }
    }

    static class ScriptReference
    extends SoftReference<Script> {
        String path;
        byte[] digest;

        ScriptReference(String path, byte[] digest, Script script, ReferenceQueue<Script> queue) {
            super(script, queue);
            this.path = path;
            this.digest = digest;
        }
    }

    private static class IProxy
    implements ContextAction,
    QuitAction {
        private static final int PROCESS_FILES = 1;
        private static final int EVAL_INLINE_SCRIPT = 2;
        private static final int SYSTEM_EXIT = 3;
        private int type;
        String[] args;
        String scriptText;

        IProxy(int type) {
            this.type = type;
        }

        @Override
        public Object run(Context cx) {
            if (useRequire) {
                require = global.installRequire(cx, modulePath, sandboxed);
            }
            if (this.type == 1) {
                Main.processFiles(cx, this.args);
            } else if (this.type == 2) {
                Main.evalInlineScript(cx, this.scriptText);
            } else {
                throw Kit.codeBug();
            }
            return null;
        }

        @Override
        public void quit(Context cx, int exitCode) {
            if (this.type == 3) {
                System.exit(exitCode);
                return;
            }
            throw Kit.codeBug();
        }
    }
}

