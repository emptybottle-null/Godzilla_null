/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Evaluator;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeFunction;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.optimizer.BodyCodegen;
import org.mozilla.javascript.optimizer.OptFunctionNode;
import org.mozilla.javascript.optimizer.OptTransformer;
import org.mozilla.javascript.optimizer.Optimizer;

public class Codegen
implements Evaluator {
    static final String DEFAULT_MAIN_METHOD_CLASS = "org.mozilla.javascript.optimizer.OptRuntime";
    private static final String SUPER_CLASS_NAME = "org.mozilla.javascript.NativeFunction";
    static final String ID_FIELD_NAME = "_id";
    static final String REGEXP_INIT_METHOD_NAME = "_reInit";
    static final String REGEXP_INIT_METHOD_SIGNATURE = "(Lorg/mozilla/javascript/Context;)V";
    static final String FUNCTION_INIT_SIGNATURE = "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)V";
    static final String FUNCTION_CONSTRUCTOR_SIGNATURE = "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Context;I)V";
    private static final Object globalLock = new Object();
    private static int globalSerialClassCounter;
    private CompilerEnvirons compilerEnv;
    private ObjArray directCallTargets;
    ScriptNode[] scriptOrFnNodes;
    private ObjToIntMap scriptOrFnIndexes;
    private String mainMethodClass = "org.mozilla.javascript.optimizer.OptRuntime";
    String mainClassName;
    String mainClassSignature;
    private double[] itsConstantList;
    private int itsConstantListSize;

    @Override
    public void captureStackInfo(RhinoException ex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSourcePositionFromStack(Context cx, int[] linep) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPatchedStack(RhinoException ex, String nativeStackTrace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getScriptStack(RhinoException ex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEvalScriptFlag(Script script) {
        throw new UnsupportedOperationException();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object compile(CompilerEnvirons compilerEnv, ScriptNode tree, String encodedSource, boolean returnFunction) {
        int serial;
        Object object = globalLock;
        synchronized (object) {
            serial = ++globalSerialClassCounter;
        }
        String baseName = "c";
        if (tree.getSourceName().length() > 0 && !Character.isJavaIdentifierStart((baseName = tree.getSourceName().replaceAll("\\W", "_")).charAt(0))) {
            baseName = "_" + baseName;
        }
        String mainClassName = "org.mozilla.javascript.gen." + baseName + "_" + serial;
        byte[] mainClassBytes = this.compileToClassFile(compilerEnv, mainClassName, tree, encodedSource, returnFunction);
        return new Object[]{mainClassName, mainClassBytes};
    }

    @Override
    public Script createScriptObject(Object bytecode, Object staticSecurityDomain) {
        Script script;
        Class<?> cl = this.defineClass(bytecode, staticSecurityDomain);
        try {
            script = (Script)cl.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to instantiate compiled class:" + ex.toString());
        }
        return script;
    }

    @Override
    public Function createFunctionObject(Context cx, Scriptable scope, Object bytecode, Object staticSecurityDomain) {
        NativeFunction f;
        Class<?> cl = this.defineClass(bytecode, staticSecurityDomain);
        try {
            Constructor<?> ctor = cl.getConstructors()[0];
            Object[] initArgs = new Object[]{scope, cx, 0};
            f = (NativeFunction)ctor.newInstance(initArgs);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to instantiate compiled class:" + ex.toString());
        }
        return f;
    }

    private Class<?> defineClass(Object bytecode, Object staticSecurityDomain) {
        RuntimeException e;
        Object[] nameBytesPair = (Object[])bytecode;
        String className = (String)nameBytesPair[0];
        byte[] classBytes = (byte[])nameBytesPair[1];
        ClassLoader rhinoLoader = this.getClass().getClassLoader();
        GeneratedClassLoader loader = SecurityController.createLoader(rhinoLoader, staticSecurityDomain);
        try {
            Class<?> cl = loader.defineClass(className, classBytes);
            loader.linkClass(cl);
            return cl;
        } catch (SecurityException x) {
            e = x;
        } catch (IllegalArgumentException x) {
            e = x;
        }
        throw new RuntimeException("Malformed optimizer package " + e);
    }

    public byte[] compileToClassFile(CompilerEnvirons compilerEnv, String mainClassName, ScriptNode scriptOrFn, String encodedSource, boolean returnFunction) {
        this.compilerEnv = compilerEnv;
        this.transform(scriptOrFn);
        if (returnFunction) {
            scriptOrFn = scriptOrFn.getFunctionNode(0);
        }
        this.initScriptNodesData(scriptOrFn);
        this.mainClassName = mainClassName;
        this.mainClassSignature = ClassFileWriter.classNameToSignature(mainClassName);
        try {
            return this.generateCode(encodedSource);
        } catch (ClassFileWriter.ClassFileFormatException e) {
            throw this.reportClassFileFormatException(scriptOrFn, e.getMessage());
        }
    }

    private RuntimeException reportClassFileFormatException(ScriptNode scriptOrFn, String message) {
        String msg = scriptOrFn instanceof FunctionNode ? ScriptRuntime.getMessage2("msg.while.compiling.fn", ((FunctionNode)scriptOrFn).getFunctionName(), message) : ScriptRuntime.getMessage1("msg.while.compiling.script", message);
        return Context.reportRuntimeError(msg, scriptOrFn.getSourceName(), scriptOrFn.getLineno(), null, 0);
    }

    private void transform(ScriptNode tree) {
        Codegen.initOptFunctions_r(tree);
        int optLevel = this.compilerEnv.getOptimizationLevel();
        HashMap<String, OptFunctionNode> possibleDirectCalls = null;
        if (optLevel > 0 && tree.getType() == 136) {
            int functionCount = tree.getFunctionCount();
            for (int i = 0; i != functionCount; ++i) {
                String name;
                OptFunctionNode ofn = OptFunctionNode.get(tree, i);
                if (ofn.fnode.getFunctionType() != 1 || (name = ofn.fnode.getName()).length() == 0) continue;
                if (possibleDirectCalls == null) {
                    possibleDirectCalls = new HashMap<String, OptFunctionNode>();
                }
                possibleDirectCalls.put(name, ofn);
            }
        }
        if (possibleDirectCalls != null) {
            this.directCallTargets = new ObjArray();
        }
        OptTransformer ot = new OptTransformer(possibleDirectCalls, this.directCallTargets);
        ot.transform(tree);
        if (optLevel > 0) {
            new Optimizer().optimize(tree);
        }
    }

    private static void initOptFunctions_r(ScriptNode scriptOrFn) {
        int N = scriptOrFn.getFunctionCount();
        for (int i = 0; i != N; ++i) {
            FunctionNode fn = scriptOrFn.getFunctionNode(i);
            new OptFunctionNode(fn);
            Codegen.initOptFunctions_r(fn);
        }
    }

    private void initScriptNodesData(ScriptNode scriptOrFn) {
        ObjArray x = new ObjArray();
        Codegen.collectScriptNodes_r(scriptOrFn, x);
        int count = x.size();
        this.scriptOrFnNodes = new ScriptNode[count];
        x.toArray(this.scriptOrFnNodes);
        this.scriptOrFnIndexes = new ObjToIntMap(count);
        for (int i = 0; i != count; ++i) {
            this.scriptOrFnIndexes.put(this.scriptOrFnNodes[i], i);
        }
    }

    private static void collectScriptNodes_r(ScriptNode n, ObjArray x) {
        x.add(n);
        int nestedCount = n.getFunctionCount();
        for (int i = 0; i != nestedCount; ++i) {
            Codegen.collectScriptNodes_r(n.getFunctionNode(i), x);
        }
    }

    private byte[] generateCode(String encodedSource) {
        boolean hasScript = this.scriptOrFnNodes[0].getType() == 136;
        boolean hasFunctions = this.scriptOrFnNodes.length > 1 || !hasScript;
        String sourceFile = null;
        if (this.compilerEnv.isGenerateDebugInfo()) {
            sourceFile = this.scriptOrFnNodes[0].getSourceName();
        }
        ClassFileWriter cfw = new ClassFileWriter(this.mainClassName, SUPER_CLASS_NAME, sourceFile);
        cfw.addField(ID_FIELD_NAME, "I", (short)2);
        if (hasFunctions) {
            this.generateFunctionConstructor(cfw);
        }
        if (hasScript) {
            cfw.addInterface("org/mozilla/javascript/Script");
            this.generateScriptCtor(cfw);
            this.generateMain(cfw);
            this.generateExecute(cfw);
        }
        this.generateCallMethod(cfw);
        this.generateResumeGenerator(cfw);
        this.generateNativeFunctionOverrides(cfw, encodedSource);
        int count = this.scriptOrFnNodes.length;
        for (int i = 0; i != count; ++i) {
            ScriptNode n = this.scriptOrFnNodes[i];
            BodyCodegen bodygen = new BodyCodegen();
            bodygen.cfw = cfw;
            bodygen.codegen = this;
            bodygen.compilerEnv = this.compilerEnv;
            bodygen.scriptOrFn = n;
            bodygen.scriptOrFnIndex = i;
            try {
                bodygen.generateBodyCode();
            } catch (ClassFileWriter.ClassFileFormatException e) {
                throw this.reportClassFileFormatException(n, e.getMessage());
            }
            if (n.getType() != 109) continue;
            OptFunctionNode ofn = OptFunctionNode.get(n);
            this.generateFunctionInit(cfw, ofn);
            if (!ofn.isTargetOfDirectCall()) continue;
            this.emitDirectConstructor(cfw, ofn);
        }
        this.emitRegExpInit(cfw);
        this.emitConstantDudeInitializers(cfw);
        return cfw.toByteArray();
    }

    private void emitDirectConstructor(ClassFileWriter cfw, OptFunctionNode ofn) {
        cfw.startMethod(this.getDirectCtorName(ofn.fnode), this.getBodyMethodSignature(ofn.fnode), (short)10);
        int argCount = ofn.fnode.getParamCount();
        int firstLocal = 4 + argCount * 3 + 1;
        cfw.addALoad(0);
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addInvoke(182, "org/mozilla/javascript/BaseFunction", "createObject", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;");
        cfw.addAStore(firstLocal);
        cfw.addALoad(0);
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addALoad(firstLocal);
        for (int i = 0; i < argCount; ++i) {
            cfw.addALoad(4 + i * 3);
            cfw.addDLoad(5 + i * 3);
        }
        cfw.addALoad(4 + argCount * 3);
        cfw.addInvoke(184, this.mainClassName, this.getBodyMethodName(ofn.fnode), this.getBodyMethodSignature(ofn.fnode));
        int exitLabel = cfw.acquireLabel();
        cfw.add(89);
        cfw.add(193, "org/mozilla/javascript/Scriptable");
        cfw.add(153, exitLabel);
        cfw.add(192, "org/mozilla/javascript/Scriptable");
        cfw.add(176);
        cfw.markLabel(exitLabel);
        cfw.addALoad(firstLocal);
        cfw.add(176);
        cfw.stopMethod((short)(firstLocal + 1));
    }

    static boolean isGenerator(ScriptNode node) {
        return node.getType() == 109 && ((FunctionNode)node).isGenerator();
    }

    private void generateResumeGenerator(ClassFileWriter cfw) {
        boolean hasGenerators = false;
        for (int i = 0; i < this.scriptOrFnNodes.length; ++i) {
            if (!Codegen.isGenerator(this.scriptOrFnNodes[i])) continue;
            hasGenerators = true;
        }
        if (!hasGenerators) {
            return;
        }
        cfw.startMethod("resumeGenerator", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", (short)17);
        cfw.addALoad(0);
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addALoad(4);
        cfw.addALoad(5);
        cfw.addILoad(3);
        cfw.addLoadThis();
        cfw.add(180, cfw.getClassName(), ID_FIELD_NAME, "I");
        int startSwitch = cfw.addTableSwitch(0, this.scriptOrFnNodes.length - 1);
        cfw.markTableSwitchDefault(startSwitch);
        int endlabel = cfw.acquireLabel();
        for (int i = 0; i < this.scriptOrFnNodes.length; ++i) {
            ScriptNode n = this.scriptOrFnNodes[i];
            cfw.markTableSwitchCase(startSwitch, i, 6);
            if (Codegen.isGenerator(n)) {
                String type = "(" + this.mainClassSignature + "Lorg/mozilla/javascript/Context;" + "Lorg/mozilla/javascript/Scriptable;" + "Ljava/lang/Object;" + "Ljava/lang/Object;I)Ljava/lang/Object;";
                cfw.addInvoke(184, this.mainClassName, this.getBodyMethodName(n) + "_gen", type);
                cfw.add(176);
                continue;
            }
            cfw.add(167, endlabel);
        }
        cfw.markLabel(endlabel);
        Codegen.pushUndefined(cfw);
        cfw.add(176);
        cfw.stopMethod((short)6);
    }

    private void generateCallMethod(ClassFileWriter cfw) {
        cfw.startMethod("call", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;", (short)17);
        int nonTopCallLabel = cfw.acquireLabel();
        cfw.addALoad(1);
        cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "hasTopCall", "(Lorg/mozilla/javascript/Context;)Z");
        cfw.add(154, nonTopCallLabel);
        cfw.addALoad(0);
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addALoad(3);
        cfw.addALoad(4);
        cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "doTopCall", "(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        cfw.add(176);
        cfw.markLabel(nonTopCallLabel);
        cfw.addALoad(0);
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addALoad(3);
        cfw.addALoad(4);
        int end = this.scriptOrFnNodes.length;
        boolean generateSwitch = 2 <= end;
        int switchStart = 0;
        short switchStackTop = 0;
        if (generateSwitch) {
            cfw.addLoadThis();
            cfw.add(180, cfw.getClassName(), ID_FIELD_NAME, "I");
            switchStart = cfw.addTableSwitch(1, end - 1);
        }
        for (int i = 0; i != end; ++i) {
            int pcount;
            OptFunctionNode ofn;
            ScriptNode n = this.scriptOrFnNodes[i];
            if (generateSwitch) {
                if (i == 0) {
                    cfw.markTableSwitchDefault(switchStart);
                    switchStackTop = cfw.getStackTop();
                } else {
                    cfw.markTableSwitchCase(switchStart, i - 1, switchStackTop);
                }
            }
            if (n.getType() == 109 && (ofn = OptFunctionNode.get(n)).isTargetOfDirectCall() && (pcount = ofn.fnode.getParamCount()) != 0) {
                for (int p = 0; p != pcount; ++p) {
                    cfw.add(190);
                    cfw.addPush(p);
                    int undefArg = cfw.acquireLabel();
                    int beyond = cfw.acquireLabel();
                    cfw.add(164, undefArg);
                    cfw.addALoad(4);
                    cfw.addPush(p);
                    cfw.add(50);
                    cfw.add(167, beyond);
                    cfw.markLabel(undefArg);
                    Codegen.pushUndefined(cfw);
                    cfw.markLabel(beyond);
                    cfw.adjustStackTop(-1);
                    cfw.addPush(0.0);
                    cfw.addALoad(4);
                }
            }
            cfw.addInvoke(184, this.mainClassName, this.getBodyMethodName(n), this.getBodyMethodSignature(n));
            cfw.add(176);
        }
        cfw.stopMethod((short)5);
    }

    private void generateMain(ClassFileWriter cfw) {
        cfw.startMethod("main", "([Ljava/lang/String;)V", (short)9);
        cfw.add(187, cfw.getClassName());
        cfw.add(89);
        cfw.addInvoke(183, cfw.getClassName(), "<init>", "()V");
        cfw.add(42);
        cfw.addInvoke(184, this.mainMethodClass, "main", "(Lorg/mozilla/javascript/Script;[Ljava/lang/String;)V");
        cfw.add(177);
        cfw.stopMethod((short)1);
    }

    private void generateExecute(ClassFileWriter cfw) {
        cfw.startMethod("exec", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;", (short)17);
        boolean CONTEXT_ARG = true;
        int SCOPE_ARG = 2;
        cfw.addLoadThis();
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.add(89);
        cfw.add(1);
        cfw.addInvoke(182, cfw.getClassName(), "call", "(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;)Ljava/lang/Object;");
        cfw.add(176);
        cfw.stopMethod((short)3);
    }

    private void generateScriptCtor(ClassFileWriter cfw) {
        cfw.startMethod("<init>", "()V", (short)1);
        cfw.addLoadThis();
        cfw.addInvoke(183, SUPER_CLASS_NAME, "<init>", "()V");
        cfw.addLoadThis();
        cfw.addPush(0);
        cfw.add(181, cfw.getClassName(), ID_FIELD_NAME, "I");
        cfw.add(177);
        cfw.stopMethod((short)1);
    }

    private void generateFunctionConstructor(ClassFileWriter cfw) {
        boolean SCOPE_ARG = true;
        int CONTEXT_ARG = 2;
        int ID_ARG = 3;
        cfw.startMethod("<init>", FUNCTION_CONSTRUCTOR_SIGNATURE, (short)1);
        cfw.addALoad(0);
        cfw.addInvoke(183, SUPER_CLASS_NAME, "<init>", "()V");
        cfw.addLoadThis();
        cfw.addILoad(3);
        cfw.add(181, cfw.getClassName(), ID_FIELD_NAME, "I");
        cfw.addLoadThis();
        cfw.addALoad(2);
        cfw.addALoad(1);
        int start = this.scriptOrFnNodes[0].getType() == 136 ? 1 : 0;
        int end = this.scriptOrFnNodes.length;
        if (start == end) {
            throw Codegen.badTree();
        }
        boolean generateSwitch = 2 <= end - start;
        int switchStart = 0;
        short switchStackTop = 0;
        if (generateSwitch) {
            cfw.addILoad(3);
            switchStart = cfw.addTableSwitch(start + 1, end - 1);
        }
        for (int i = start; i != end; ++i) {
            if (generateSwitch) {
                if (i == start) {
                    cfw.markTableSwitchDefault(switchStart);
                    switchStackTop = cfw.getStackTop();
                } else {
                    cfw.markTableSwitchCase(switchStart, i - 1 - start, switchStackTop);
                }
            }
            OptFunctionNode ofn = OptFunctionNode.get(this.scriptOrFnNodes[i]);
            cfw.addInvoke(183, this.mainClassName, this.getFunctionInitMethodName(ofn), FUNCTION_INIT_SIGNATURE);
            cfw.add(177);
        }
        cfw.stopMethod((short)4);
    }

    private void generateFunctionInit(ClassFileWriter cfw, OptFunctionNode ofn) {
        boolean CONTEXT_ARG = true;
        int SCOPE_ARG = 2;
        cfw.startMethod(this.getFunctionInitMethodName(ofn), FUNCTION_INIT_SIGNATURE, (short)18);
        cfw.addLoadThis();
        cfw.addALoad(1);
        cfw.addALoad(2);
        cfw.addInvoke(182, "org/mozilla/javascript/NativeFunction", "initScriptFunction", FUNCTION_INIT_SIGNATURE);
        if (ofn.fnode.getRegexpCount() != 0) {
            cfw.addALoad(1);
            cfw.addInvoke(184, this.mainClassName, REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE);
        }
        cfw.add(177);
        cfw.stopMethod((short)3);
    }

    private void generateNativeFunctionOverrides(ClassFileWriter cfw, String encodedSource) {
        cfw.startMethod("getLanguageVersion", "()I", (short)1);
        cfw.addPush(this.compilerEnv.getLanguageVersion());
        cfw.add(172);
        cfw.stopMethod((short)1);
        boolean Do_getFunctionName = false;
        boolean Do_getParamCount = true;
        int Do_getParamAndVarCount = 2;
        int Do_getParamOrVarName = 3;
        int Do_getEncodedSource = 4;
        int Do_getParamOrVarConst = 5;
        int SWITCH_COUNT = 6;
        for (int methodIndex = 0; methodIndex != 6; ++methodIndex) {
            short methodLocals;
            if (methodIndex == 4 && encodedSource == null) continue;
            switch (methodIndex) {
                case 0: {
                    methodLocals = 1;
                    cfw.startMethod("getFunctionName", "()Ljava/lang/String;", (short)1);
                    break;
                }
                case 1: {
                    methodLocals = 1;
                    cfw.startMethod("getParamCount", "()I", (short)1);
                    break;
                }
                case 2: {
                    methodLocals = 1;
                    cfw.startMethod("getParamAndVarCount", "()I", (short)1);
                    break;
                }
                case 3: {
                    methodLocals = 2;
                    cfw.startMethod("getParamOrVarName", "(I)Ljava/lang/String;", (short)1);
                    break;
                }
                case 5: {
                    methodLocals = 3;
                    cfw.startMethod("getParamOrVarConst", "(I)Z", (short)1);
                    break;
                }
                case 4: {
                    methodLocals = 1;
                    cfw.startMethod("getEncodedSource", "()Ljava/lang/String;", (short)1);
                    cfw.addPush(encodedSource);
                    break;
                }
                default: {
                    throw Kit.codeBug();
                }
            }
            int count = this.scriptOrFnNodes.length;
            int switchStart = 0;
            int switchStackTop = 0;
            if (count > 1) {
                cfw.addLoadThis();
                cfw.add(180, cfw.getClassName(), ID_FIELD_NAME, "I");
                switchStart = cfw.addTableSwitch(1, count - 1);
            }
            block17: for (int i = 0; i != count; ++i) {
                ScriptNode n = this.scriptOrFnNodes[i];
                if (i == 0) {
                    if (count > 1) {
                        cfw.markTableSwitchDefault(switchStart);
                        switchStackTop = cfw.getStackTop();
                    }
                } else {
                    cfw.markTableSwitchCase(switchStart, i - 1, switchStackTop);
                }
                switch (methodIndex) {
                    case 0: {
                        if (n.getType() == 136) {
                            cfw.addPush("");
                        } else {
                            String name = ((FunctionNode)n).getName();
                            cfw.addPush(name);
                        }
                        cfw.add(176);
                        continue block17;
                    }
                    case 1: {
                        cfw.addPush(n.getParamCount());
                        cfw.add(172);
                        continue block17;
                    }
                    case 2: {
                        cfw.addPush(n.getParamAndVarCount());
                        cfw.add(172);
                        continue block17;
                    }
                    case 3: {
                        int paramAndVarCount = n.getParamAndVarCount();
                        if (paramAndVarCount == 0) {
                            cfw.add(1);
                            cfw.add(176);
                            continue block17;
                        }
                        if (paramAndVarCount == 1) {
                            cfw.addPush(n.getParamOrVarName(0));
                            cfw.add(176);
                            continue block17;
                        }
                        cfw.addILoad(1);
                        int paramSwitchStart = cfw.addTableSwitch(1, paramAndVarCount - 1);
                        for (int j = 0; j != paramAndVarCount; ++j) {
                            if (cfw.getStackTop() != 0) {
                                Kit.codeBug();
                            }
                            String s = n.getParamOrVarName(j);
                            if (j == 0) {
                                cfw.markTableSwitchDefault(paramSwitchStart);
                            } else {
                                cfw.markTableSwitchCase(paramSwitchStart, j - 1, 0);
                            }
                            cfw.addPush(s);
                            cfw.add(176);
                        }
                        continue block17;
                    }
                    case 5: {
                        int paramAndVarCount = n.getParamAndVarCount();
                        boolean[] constness = n.getParamAndVarConst();
                        if (paramAndVarCount == 0) {
                            cfw.add(3);
                            cfw.add(172);
                            continue block17;
                        }
                        if (paramAndVarCount == 1) {
                            cfw.addPush(constness[0]);
                            cfw.add(172);
                            continue block17;
                        }
                        cfw.addILoad(1);
                        int paramSwitchStart = cfw.addTableSwitch(1, paramAndVarCount - 1);
                        for (int j = 0; j != paramAndVarCount; ++j) {
                            if (cfw.getStackTop() != 0) {
                                Kit.codeBug();
                            }
                            if (j == 0) {
                                cfw.markTableSwitchDefault(paramSwitchStart);
                            } else {
                                cfw.markTableSwitchCase(paramSwitchStart, j - 1, 0);
                            }
                            cfw.addPush(constness[j]);
                            cfw.add(172);
                        }
                        continue block17;
                    }
                    case 4: {
                        cfw.addPush(n.getEncodedSourceStart());
                        cfw.addPush(n.getEncodedSourceEnd());
                        cfw.addInvoke(182, "java/lang/String", "substring", "(II)Ljava/lang/String;");
                        cfw.add(176);
                        continue block17;
                    }
                    default: {
                        throw Kit.codeBug();
                    }
                }
            }
            cfw.stopMethod(methodLocals);
        }
    }

    private void emitRegExpInit(ClassFileWriter cfw) {
        int totalRegCount = 0;
        for (int i = 0; i != this.scriptOrFnNodes.length; ++i) {
            totalRegCount += this.scriptOrFnNodes[i].getRegexpCount();
        }
        if (totalRegCount == 0) {
            return;
        }
        cfw.startMethod(REGEXP_INIT_METHOD_NAME, REGEXP_INIT_METHOD_SIGNATURE, (short)10);
        cfw.addField("_reInitDone", "Z", (short)74);
        cfw.add(178, this.mainClassName, "_reInitDone", "Z");
        int doInit = cfw.acquireLabel();
        cfw.add(153, doInit);
        cfw.add(177);
        cfw.markLabel(doInit);
        cfw.addALoad(0);
        cfw.addInvoke(184, "org/mozilla/javascript/ScriptRuntime", "checkRegExpProxy", "(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/RegExpProxy;");
        cfw.addAStore(1);
        for (int i = 0; i != this.scriptOrFnNodes.length; ++i) {
            ScriptNode n = this.scriptOrFnNodes[i];
            int regCount = n.getRegexpCount();
            for (int j = 0; j != regCount; ++j) {
                String reFieldName = this.getCompiledRegexpName(n, j);
                String reFieldType = "Ljava/lang/Object;";
                String reString = n.getRegexpString(j);
                String reFlags = n.getRegexpFlags(j);
                cfw.addField(reFieldName, reFieldType, (short)10);
                cfw.addALoad(1);
                cfw.addALoad(0);
                cfw.addPush(reString);
                if (reFlags == null) {
                    cfw.add(1);
                } else {
                    cfw.addPush(reFlags);
                }
                cfw.addInvoke(185, "org/mozilla/javascript/RegExpProxy", "compileRegExp", "(Lorg/mozilla/javascript/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
                cfw.add(179, this.mainClassName, reFieldName, reFieldType);
            }
        }
        cfw.addPush(1);
        cfw.add(179, this.mainClassName, "_reInitDone", "Z");
        cfw.add(177);
        cfw.stopMethod((short)2);
    }

    private void emitConstantDudeInitializers(ClassFileWriter cfw) {
        int N = this.itsConstantListSize;
        if (N == 0) {
            return;
        }
        cfw.startMethod("<clinit>", "()V", (short)24);
        double[] array = this.itsConstantList;
        for (int i = 0; i != N; ++i) {
            double num = array[i];
            String constantName = "_k" + i;
            String constantType = Codegen.getStaticConstantWrapperType(num);
            cfw.addField(constantName, constantType, (short)10);
            int inum = (int)num;
            if ((double)inum == num) {
                cfw.addPush(inum);
                cfw.addInvoke(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            } else {
                cfw.addPush(num);
                Codegen.addDoubleWrap(cfw);
            }
            cfw.add(179, this.mainClassName, constantName, constantType);
        }
        cfw.add(177);
        cfw.stopMethod((short)0);
    }

    void pushNumberAsObject(ClassFileWriter cfw, double num) {
        if (num == 0.0) {
            if (1.0 / num > 0.0) {
                cfw.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "zeroObj", "Ljava/lang/Double;");
            } else {
                cfw.addPush(num);
                Codegen.addDoubleWrap(cfw);
            }
        } else {
            if (num == 1.0) {
                cfw.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "oneObj", "Ljava/lang/Double;");
                return;
            }
            if (num == -1.0) {
                cfw.add(178, "org/mozilla/javascript/optimizer/OptRuntime", "minusOneObj", "Ljava/lang/Double;");
            } else if (num != num) {
                cfw.add(178, "org/mozilla/javascript/ScriptRuntime", "NaNobj", "Ljava/lang/Double;");
            } else if (this.itsConstantListSize >= 2000) {
                cfw.addPush(num);
                Codegen.addDoubleWrap(cfw);
            } else {
                int index;
                int N = this.itsConstantListSize;
                if (N == 0) {
                    this.itsConstantList = new double[64];
                } else {
                    double[] array = this.itsConstantList;
                    for (index = 0; index != N && array[index] != num; ++index) {
                    }
                    if (N == array.length) {
                        array = new double[N * 2];
                        System.arraycopy(this.itsConstantList, 0, array, 0, N);
                        this.itsConstantList = array;
                    }
                }
                if (index == N) {
                    this.itsConstantList[N] = num;
                    this.itsConstantListSize = N + 1;
                }
                String constantName = "_k" + index;
                String constantType = Codegen.getStaticConstantWrapperType(num);
                cfw.add(178, this.mainClassName, constantName, constantType);
            }
        }
    }

    private static void addDoubleWrap(ClassFileWriter cfw) {
        cfw.addInvoke(184, "org/mozilla/javascript/optimizer/OptRuntime", "wrapDouble", "(D)Ljava/lang/Double;");
    }

    private static String getStaticConstantWrapperType(double num) {
        int inum = (int)num;
        if ((double)inum == num) {
            return "Ljava/lang/Integer;";
        }
        return "Ljava/lang/Double;";
    }

    static void pushUndefined(ClassFileWriter cfw) {
        cfw.add(178, "org/mozilla/javascript/Undefined", "instance", "Ljava/lang/Object;");
    }

    int getIndex(ScriptNode n) {
        return this.scriptOrFnIndexes.getExisting(n);
    }

    String getDirectCtorName(ScriptNode n) {
        return "_n" + this.getIndex(n);
    }

    String getBodyMethodName(ScriptNode n) {
        return "_c_" + this.cleanName(n) + "_" + this.getIndex(n);
    }

    String cleanName(ScriptNode n) {
        Name name;
        String result = "";
        result = n instanceof FunctionNode ? ((name = ((FunctionNode)n).getFunctionName()) == null ? "anonymous" : name.getIdentifier()) : "script";
        return result;
    }

    String getBodyMethodSignature(ScriptNode n) {
        OptFunctionNode ofn;
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(this.mainClassSignature);
        sb.append("Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;");
        if (n.getType() == 109 && (ofn = OptFunctionNode.get(n)).isTargetOfDirectCall()) {
            int pCount = ofn.fnode.getParamCount();
            for (int i = 0; i != pCount; ++i) {
                sb.append("Ljava/lang/Object;D");
            }
        }
        sb.append("[Ljava/lang/Object;)Ljava/lang/Object;");
        return sb.toString();
    }

    String getFunctionInitMethodName(OptFunctionNode ofn) {
        return "_i" + this.getIndex(ofn.fnode);
    }

    String getCompiledRegexpName(ScriptNode n, int regexpIndex) {
        return "_re" + this.getIndex(n) + "_" + regexpIndex;
    }

    static RuntimeException badTree() {
        throw new RuntimeException("Bad tree in codegen");
    }

    public void setMainMethodClass(String className) {
        this.mainMethodClass = className;
    }
}

