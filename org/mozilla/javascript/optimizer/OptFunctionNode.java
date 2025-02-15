/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;

public final class OptFunctionNode {
    public final FunctionNode fnode;
    private boolean[] numberVarFlags;
    private int directTargetIndex = -1;
    private boolean itsParameterNumberContext;
    boolean itsContainsCalls0;
    boolean itsContainsCalls1;

    OptFunctionNode(FunctionNode fnode) {
        this.fnode = fnode;
        fnode.setCompilerData(this);
    }

    public static OptFunctionNode get(ScriptNode scriptOrFn, int i) {
        FunctionNode fnode = scriptOrFn.getFunctionNode(i);
        return (OptFunctionNode)fnode.getCompilerData();
    }

    public static OptFunctionNode get(ScriptNode scriptOrFn) {
        return (OptFunctionNode)scriptOrFn.getCompilerData();
    }

    public boolean isTargetOfDirectCall() {
        return this.directTargetIndex >= 0;
    }

    public int getDirectTargetIndex() {
        return this.directTargetIndex;
    }

    void setDirectTargetIndex(int directTargetIndex) {
        if (directTargetIndex < 0 || this.directTargetIndex >= 0) {
            Kit.codeBug();
        }
        this.directTargetIndex = directTargetIndex;
    }

    void setParameterNumberContext(boolean b) {
        this.itsParameterNumberContext = b;
    }

    public boolean getParameterNumberContext() {
        return this.itsParameterNumberContext;
    }

    public int getVarCount() {
        return this.fnode.getParamAndVarCount();
    }

    public boolean isParameter(int varIndex) {
        return varIndex < this.fnode.getParamCount();
    }

    public boolean isNumberVar(int varIndex) {
        if ((varIndex -= this.fnode.getParamCount()) >= 0 && this.numberVarFlags != null) {
            return this.numberVarFlags[varIndex];
        }
        return false;
    }

    void setIsNumberVar(int varIndex) {
        if ((varIndex -= this.fnode.getParamCount()) < 0) {
            Kit.codeBug();
        }
        if (this.numberVarFlags == null) {
            int size = this.fnode.getParamAndVarCount() - this.fnode.getParamCount();
            this.numberVarFlags = new boolean[size];
        }
        this.numberVarFlags[varIndex] = true;
    }

    public int getVarIndex(Node n) {
        int index = n.getIntProp(7, -1);
        if (index == -1) {
            Node node;
            int type = n.getType();
            if (type == 55) {
                node = n;
            } else if (type == 56 || type == 156) {
                node = n.getFirstChild();
            } else {
                throw Kit.codeBug();
            }
            index = this.fnode.getIndexForNameNode(node);
            if (index < 0) {
                throw Kit.codeBug();
            }
            n.putIntProp(7, index);
        }
        return index;
    }
}

