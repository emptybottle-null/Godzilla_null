/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import org.springframework.asm.Label;
import org.springframework.asm.MethodAdapter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;

public class LocalVariablesSorter
extends MethodAdapter {
    private int[] mapping = new int[40];
    protected final int firstLocal;
    private int nextLocal;

    public LocalVariablesSorter(int n, String string, MethodVisitor methodVisitor) {
        super(methodVisitor);
        Type[] typeArray = Type.getArgumentTypes(string);
        this.nextLocal = (8 & n) != 0 ? 0 : 1;
        for (int i = 0; i < typeArray.length; ++i) {
            this.nextLocal += typeArray[i].getSize();
        }
        this.firstLocal = this.nextLocal;
    }

    public void visitVarInsn(int n, int n2) {
        int n3;
        switch (n) {
            case 22: 
            case 24: 
            case 55: 
            case 57: {
                n3 = 2;
                break;
            }
            default: {
                n3 = 1;
            }
        }
        this.mv.visitVarInsn(n, this.remap(n2, n3));
    }

    public void visitIincInsn(int n, int n2) {
        this.mv.visitIincInsn(this.remap(n, 1), n2);
    }

    public void visitMaxs(int n, int n2) {
        this.mv.visitMaxs(n, this.nextLocal);
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n) {
        int n2 = "J".equals(string2) || "D".equals(string2) ? 2 : 1;
        this.mv.visitLocalVariable(string, string2, string3, label, label2, this.remap(n, n2));
    }

    protected int newLocal(int n) {
        int n2 = this.nextLocal;
        this.nextLocal += n;
        return n2;
    }

    private int remap(int n, int n2) {
        int n3;
        if (n < this.firstLocal) {
            return n;
        }
        int n4 = 2 * n + n2 - 1;
        int n5 = this.mapping.length;
        if (n4 >= n5) {
            int[] nArray = new int[Math.max(2 * n5, n4 + 1)];
            System.arraycopy(this.mapping, 0, nArray, 0, n5);
            this.mapping = nArray;
        }
        if ((n3 = this.mapping[n4]) == 0) {
            this.mapping[n4] = n3 = this.nextLocal + 1;
            this.nextLocal += n2;
        }
        return n3 - 1;
    }
}

