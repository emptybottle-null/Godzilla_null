/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.JavaAdapter;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.optimizer.Codegen;

public class ClassCompiler {
    private String mainMethodClassName;
    private CompilerEnvirons compilerEnv;
    private Class<?> targetExtends;
    private Class<?>[] targetImplements;

    public ClassCompiler(CompilerEnvirons compilerEnv) {
        if (compilerEnv == null) {
            throw new IllegalArgumentException();
        }
        this.compilerEnv = compilerEnv;
        this.mainMethodClassName = "org.mozilla.javascript.optimizer.OptRuntime";
    }

    public void setMainMethodClass(String className) {
        this.mainMethodClassName = className;
    }

    public String getMainMethodClass() {
        return this.mainMethodClassName;
    }

    public CompilerEnvirons getCompilerEnv() {
        return this.compilerEnv;
    }

    public Class<?> getTargetExtends() {
        return this.targetExtends;
    }

    public void setTargetExtends(Class<?> extendsClass) {
        this.targetExtends = extendsClass;
    }

    public Class<?>[] getTargetImplements() {
        return this.targetImplements == null ? null : (Class[])this.targetImplements.clone();
    }

    public void setTargetImplements(Class<?>[] implementsClasses) {
        this.targetImplements = implementsClasses == null ? null : (Class[])implementsClasses.clone();
    }

    protected String makeAuxiliaryClassName(String mainClassName, String auxMarker) {
        return mainClassName + auxMarker;
    }

    public Object[] compileToClassFiles(String source, String sourceLocation, int lineno, String mainClassName) {
        Parser p = new Parser(this.compilerEnv);
        AstRoot ast = p.parse(source, sourceLocation, lineno);
        IRFactory irf = new IRFactory(this.compilerEnv);
        ScriptNode tree = irf.transformTree(ast);
        irf = null;
        ast = null;
        p = null;
        Class<?> superClass = this.getTargetExtends();
        Class<?>[] interfaces = this.getTargetImplements();
        boolean isPrimary = interfaces == null && superClass == null;
        String scriptClassName = isPrimary ? mainClassName : this.makeAuxiliaryClassName(mainClassName, "1");
        Codegen codegen = new Codegen();
        codegen.setMainMethodClass(this.mainMethodClassName);
        byte[] scriptClassBytes = codegen.compileToClassFile(this.compilerEnv, scriptClassName, tree, tree.getEncodedSource(), false);
        if (isPrimary) {
            return new Object[]{scriptClassName, scriptClassBytes};
        }
        int functionCount = tree.getFunctionCount();
        ObjToIntMap functionNames = new ObjToIntMap(functionCount);
        for (int i = 0; i != functionCount; ++i) {
            FunctionNode ofn = tree.getFunctionNode(i);
            String name = ofn.getName();
            if (name == null || name.length() == 0) continue;
            functionNames.put(name, ofn.getParamCount());
        }
        if (superClass == null) {
            superClass = ScriptRuntime.ObjectClass;
        }
        byte[] mainClassBytes = JavaAdapter.createAdapterCode(functionNames, mainClassName, superClass, interfaces, scriptClassName);
        return new Object[]{mainClassName, mainClassBytes, scriptClassName, scriptClassBytes};
    }
}

