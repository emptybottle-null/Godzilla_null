/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import java.util.HashMap;
import org.fife.rsta.ac.js.ast.JavaScriptFunctionDeclaration;
import org.fife.rsta.ac.js.ast.JavaScriptVariableDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;

public class VariableResolver {
    private HashMap<String, JavaScriptVariableDeclaration> localVariables = new HashMap();
    private HashMap<String, JavaScriptVariableDeclaration> preProcessedVariables = new HashMap();
    private HashMap<String, JavaScriptVariableDeclaration> systemVariables = new HashMap();
    private HashMap<String, JavaScriptFunctionDeclaration> localFunctions = new HashMap();
    private HashMap<String, JavaScriptFunctionDeclaration> preProcessedFunctions = new HashMap();

    public void addLocalVariable(JavaScriptVariableDeclaration declaration) {
        this.localVariables.put(declaration.getName(), declaration);
    }

    public void addPreProcessingVariable(JavaScriptVariableDeclaration declaration) {
        this.preProcessedVariables.put(declaration.getName(), declaration);
    }

    public void addSystemVariable(JavaScriptVariableDeclaration declaration) {
        this.systemVariables.put(declaration.getName(), declaration);
    }

    public void removePreProcessingVariable(String name) {
        this.preProcessedVariables.remove(name);
    }

    public void removeSystemVariable(String name) {
        this.systemVariables.remove(name);
    }

    public JavaScriptVariableDeclaration findDeclaration(String name, int dot) {
        JavaScriptVariableDeclaration findDeclaration = this.findDeclaration(this.localVariables, name, dot);
        findDeclaration = findDeclaration == null ? this.findDeclaration(this.preProcessedVariables, name, dot) : findDeclaration;
        return findDeclaration == null ? this.findDeclaration(this.systemVariables, name, dot) : findDeclaration;
    }

    public JavaScriptVariableDeclaration findDeclaration(String name, int dot, boolean local, boolean preProcessed, boolean system) {
        JavaScriptVariableDeclaration findDeclaration;
        JavaScriptVariableDeclaration javaScriptVariableDeclaration = findDeclaration = local ? this.findDeclaration(this.localVariables, name, dot) : null;
        JavaScriptVariableDeclaration javaScriptVariableDeclaration2 = findDeclaration == null ? (preProcessed ? this.findDeclaration(this.preProcessedVariables, name, dot) : null) : (findDeclaration = findDeclaration);
        return findDeclaration == null ? (system ? this.findDeclaration(this.systemVariables, name, dot) : null) : findDeclaration;
    }

    public JavaScriptVariableDeclaration findNonLocalDeclaration(String name, int dot) {
        JavaScriptVariableDeclaration findDeclaration = this.findDeclaration(this.preProcessedVariables, name, dot);
        return findDeclaration == null ? this.findDeclaration(this.systemVariables, name, dot) : findDeclaration;
    }

    private JavaScriptVariableDeclaration findDeclaration(HashMap<String, JavaScriptVariableDeclaration> variables, String name, int dot) {
        int decOffs;
        JavaScriptVariableDeclaration dec = variables.get(name);
        if (dec != null && (dec.getCodeBlock() == null || dec.getCodeBlock().contains(dot)) && dot <= (decOffs = dec.getOffset())) {
            return dec;
        }
        return null;
    }

    public TypeDeclaration getTypeDeclarationForVariable(String name, int dot) {
        JavaScriptVariableDeclaration dec = this.findDeclaration(name, dot);
        return dec != null ? dec.getTypeDeclaration() : null;
    }

    public void resetLocalVariables() {
        this.localVariables.clear();
        this.localFunctions.clear();
    }

    public void resetPreProcessingVariables(boolean clear) {
        if (clear) {
            this.preProcessedVariables.clear();
            this.preProcessedFunctions.clear();
        } else {
            for (JavaScriptVariableDeclaration dec : this.preProcessedVariables.values()) {
                dec.resetVariableToOriginalType();
            }
        }
    }

    public void resetSystemVariables() {
        this.systemVariables.clear();
    }

    public TypeDeclaration resolveType(String varName, int dot) {
        return this.getTypeDeclarationForVariable(varName, dot);
    }

    public void addLocalFunction(JavaScriptFunctionDeclaration func) {
        this.localFunctions.put(func.getName(), func);
    }

    public JavaScriptFunctionDeclaration findFunctionDeclaration(String name) {
        JavaScriptFunctionDeclaration dec = this.localFunctions.get(name);
        if (dec == null) {
            dec = this.preProcessedFunctions.get(name);
        }
        return dec;
    }

    public JavaScriptFunctionDeclaration findFunctionDeclaration(String name, boolean local, boolean preProcessed) {
        JavaScriptFunctionDeclaration dec;
        JavaScriptFunctionDeclaration javaScriptFunctionDeclaration = dec = local ? this.localFunctions.get(name) : null;
        if (dec == null) {
            dec = preProcessed ? this.preProcessedFunctions.get(name) : null;
        }
        return dec;
    }

    public JavaScriptFunctionDeclaration findFunctionDeclarationByFunctionName(String name, boolean local, boolean preprocessed) {
        JavaScriptFunctionDeclaration func;
        JavaScriptFunctionDeclaration javaScriptFunctionDeclaration = func = local ? this.findFirstFunction(name, this.localFunctions) : null;
        if (func == null) {
            func = preprocessed ? this.findFirstFunction(name, this.preProcessedFunctions) : null;
        }
        return func;
    }

    private JavaScriptFunctionDeclaration findFirstFunction(String name, HashMap<String, JavaScriptFunctionDeclaration> functions2) {
        for (JavaScriptFunctionDeclaration func : functions2.values()) {
            if (!name.equals(func.getFunctionName())) continue;
            return func;
        }
        return null;
    }

    public void addPreProcessingFunction(JavaScriptFunctionDeclaration func) {
        this.preProcessedFunctions.put(func.getName(), func);
    }
}

