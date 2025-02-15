/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.ConstantDynamic;
import org.springframework.asm.Handle;
import org.springframework.asm.Label;
import org.springframework.asm.Type;
import org.springframework.asm.TypePath;

public abstract class MethodVisitor {
    private static final String REQUIRES_ASM5 = "This feature requires ASM5";
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int api) {
        this(api, null);
    }

    public MethodVisitor(int api, MethodVisitor methodVisitor) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 0x10A0000) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        this.api = api;
        this.mv = methodVisitor;
    }

    public void visitParameter(String name, int access) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            this.mv.visitParameter(name, access);
        }
    }

    public AnnotationVisitor visitAnnotationDefault() {
        if (this.mv != null) {
            return this.mv.visitAnnotationDefault();
        }
        return null;
    }

    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (this.mv != null) {
            return this.mv.visitAnnotation(descriptor, visible);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            return this.mv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
        }
        return null;
    }

    public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
        if (this.mv != null) {
            this.mv.visitAnnotableParameterCount(parameterCount, visible);
        }
    }

    public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
        if (this.mv != null) {
            return this.mv.visitParameterAnnotation(parameter, descriptor, visible);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.mv != null) {
            this.mv.visitAttribute(attribute);
        }
    }

    public void visitCode() {
        if (this.mv != null) {
            this.mv.visitCode();
        }
    }

    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        if (this.mv != null) {
            this.mv.visitFrame(type, numLocal, local, numStack, stack);
        }
    }

    public void visitInsn(int opcode) {
        if (this.mv != null) {
            this.mv.visitInsn(opcode);
        }
    }

    public void visitIntInsn(int opcode, int operand) {
        if (this.mv != null) {
            this.mv.visitIntInsn(opcode, operand);
        }
    }

    public void visitVarInsn(int opcode, int var) {
        if (this.mv != null) {
            this.mv.visitVarInsn(opcode, var);
        }
    }

    public void visitTypeInsn(int opcode, String type) {
        if (this.mv != null) {
            this.mv.visitTypeInsn(opcode, type);
        }
    }

    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(opcode, owner, name, descriptor);
        }
    }

    @Deprecated
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor) {
        int opcodeAndSource = opcode | (this.api < 327680 ? 256 : 0);
        this.visitMethodInsn(opcodeAndSource, owner, name, descriptor, opcode == 185);
    }

    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (this.api < 327680 && (opcode & 0x100) == 0) {
            if (isInterface != (opcode == 185)) {
                throw new UnsupportedOperationException("INVOKESPECIAL/STATIC on interfaces requires ASM5");
            }
            this.visitMethodInsn(opcode, owner, name, descriptor);
            return;
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(opcode & 0xFFFFFEFF, owner, name, descriptor, isInterface);
        }
    }

    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
        }
    }

    public void visitJumpInsn(int opcode, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(opcode, label);
        }
    }

    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
    }

    public void visitLdcInsn(Object value) {
        if (this.api < 327680 && (value instanceof Handle || value instanceof Type && ((Type)value).getSort() == 11)) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.api < 458752 && value instanceof ConstantDynamic) {
            throw new UnsupportedOperationException("This feature requires ASM7");
        }
        if (this.mv != null) {
            this.mv.visitLdcInsn(value);
        }
    }

    public void visitIincInsn(int var, int increment) {
        if (this.mv != null) {
            this.mv.visitIincInsn(var, increment);
        }
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt, Label ... labels) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(min, max, dflt, labels);
        }
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(dflt, keys, labels);
        }
    }

    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(descriptor, numDimensions);
        }
    }

    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            return this.mv.visitInsnAnnotation(typeRef, typePath, descriptor, visible);
        }
        return null;
    }

    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        if (this.mv != null) {
            this.mv.visitTryCatchBlock(start, end, handler, type);
        }
    }

    public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            return this.mv.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible);
        }
        return null;
    }

    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        if (this.mv != null) {
            this.mv.visitLocalVariable(name, descriptor, signature, start, end, index);
        }
    }

    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException(REQUIRES_ASM5);
        }
        if (this.mv != null) {
            return this.mv.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
        }
        return null;
    }

    public void visitLineNumber(int line, Label start) {
        if (this.mv != null) {
            this.mv.visitLineNumber(line, start);
        }
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        if (this.mv != null) {
            this.mv.visitMaxs(maxStack, maxLocals);
        }
    }

    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
}

