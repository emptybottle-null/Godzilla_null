/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.asm.commons.LocalVariablesSorter;
import org.springframework.asm.commons.Method;
import org.springframework.asm.commons.TableSwitchGenerator;

public class GeneratorAdapter
extends LocalVariablesSorter {
    private static final Type BYTE_TYPE = Type.getType("Ljava/lang/Byte;");
    private static final Type BOOLEAN_TYPE = Type.getType("Ljava/lang/Boolean;");
    private static final Type SHORT_TYPE = Type.getType("Ljava/lang/Short;");
    private static final Type CHARACTER_TYPE = Type.getType("Ljava/lang/Character;");
    private static final Type INTEGER_TYPE = Type.getType("Ljava/lang/Integer;");
    private static final Type FLOAT_TYPE = Type.getType("Ljava/lang/Float;");
    private static final Type LONG_TYPE = Type.getType("Ljava/lang/Long;");
    private static final Type DOUBLE_TYPE = Type.getType("Ljava/lang/Double;");
    private static final Type NUMBER_TYPE = Type.getType("Ljava/lang/Number;");
    private static final Type OBJECT_TYPE = Type.getType("Ljava/lang/Object;");
    private static final Method BOOLEAN_VALUE = Method.getMethod("boolean booleanValue()");
    private static final Method CHAR_VALUE = Method.getMethod("char charValue()");
    private static final Method INT_VALUE = Method.getMethod("int intValue()");
    private static final Method FLOAT_VALUE = Method.getMethod("float floatValue()");
    private static final Method LONG_VALUE = Method.getMethod("long longValue()");
    private static final Method DOUBLE_VALUE = Method.getMethod("double doubleValue()");
    public static final int ADD = 96;
    public static final int SUB = 100;
    public static final int MUL = 104;
    public static final int DIV = 108;
    public static final int REM = 112;
    public static final int NEG = 116;
    public static final int SHL = 120;
    public static final int SHR = 122;
    public static final int USHR = 124;
    public static final int AND = 126;
    public static final int OR = 128;
    public static final int XOR = 130;
    public static final int EQ = 153;
    public static final int NE = 154;
    public static final int LT = 155;
    public static final int GE = 156;
    public static final int GT = 157;
    public static final int LE = 158;
    private final int access;
    private final Type returnType;
    private final Type[] argumentTypes;
    private final List localTypes;

    public GeneratorAdapter(MethodVisitor methodVisitor, int n, String string, String string2) {
        super(n, string2, methodVisitor);
        this.access = n;
        this.returnType = Type.getReturnType(string2);
        this.argumentTypes = Type.getArgumentTypes(string2);
        this.localTypes = new ArrayList();
    }

    public GeneratorAdapter(int n, Method method, MethodVisitor methodVisitor) {
        super(n, method.getDescriptor(), methodVisitor);
        this.access = n;
        this.returnType = method.getReturnType();
        this.argumentTypes = method.getArgumentTypes();
        this.localTypes = new ArrayList();
    }

    public GeneratorAdapter(int n, Method method, String string, Type[] typeArray, ClassVisitor classVisitor) {
        this(n, method, classVisitor.visitMethod(n, method.getName(), method.getDescriptor(), string, GeneratorAdapter.getInternalNames(typeArray)));
    }

    private static String[] getInternalNames(Type[] typeArray) {
        if (typeArray == null) {
            return null;
        }
        String[] stringArray = new String[typeArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            stringArray[i] = typeArray[i].getInternalName();
        }
        return stringArray;
    }

    public void push(boolean bl) {
        this.push(bl ? 1 : 0);
    }

    public void push(int n) {
        if (n >= -1 && n <= 5) {
            this.mv.visitInsn(3 + n);
        } else if (n >= -128 && n <= 127) {
            this.mv.visitIntInsn(16, n);
        } else if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE) {
            this.mv.visitIntInsn(17, n);
        } else {
            this.mv.visitLdcInsn(new Integer(n));
        }
    }

    public void push(long l) {
        if (l == 0L || l == 1L) {
            this.mv.visitInsn(9 + (int)l);
        } else {
            this.mv.visitLdcInsn(new Long(l));
        }
    }

    public void push(float f) {
        int n = Float.floatToIntBits(f);
        if ((long)n == 0L || n == 1065353216 || n == 0x40000000) {
            this.mv.visitInsn(11 + (int)f);
        } else {
            this.mv.visitLdcInsn(new Float(f));
        }
    }

    public void push(double d) {
        long l = Double.doubleToLongBits(d);
        if (l == 0L || l == 0x3FF0000000000000L) {
            this.mv.visitInsn(14 + (int)d);
        } else {
            this.mv.visitLdcInsn(new Double(d));
        }
    }

    public void push(String string) {
        if (string == null) {
            this.mv.visitInsn(1);
        } else {
            this.mv.visitLdcInsn(string);
        }
    }

    public void push(Type type) {
        if (type == null) {
            this.mv.visitInsn(1);
        } else {
            this.mv.visitLdcInsn(type);
        }
    }

    private int getArgIndex(int n) {
        int n2 = (this.access & 8) == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            n2 += this.argumentTypes[i].getSize();
        }
        return n2;
    }

    private void loadInsn(Type type, int n) {
        this.mv.visitVarInsn(type.getOpcode(21), n);
    }

    private void storeInsn(Type type, int n) {
        this.mv.visitVarInsn(type.getOpcode(54), n);
    }

    public void loadThis() {
        if ((this.access & 8) != 0) {
            throw new IllegalStateException("no 'this' pointer within static method");
        }
        this.mv.visitVarInsn(25, 0);
    }

    public void loadArg(int n) {
        this.loadInsn(this.argumentTypes[n], this.getArgIndex(n));
    }

    public void loadArgs(int n, int n2) {
        int n3 = this.getArgIndex(n);
        for (int i = 0; i < n2; ++i) {
            Type type = this.argumentTypes[n + i];
            this.loadInsn(type, n3);
            n3 += type.getSize();
        }
    }

    public void loadArgs() {
        this.loadArgs(0, this.argumentTypes.length);
    }

    public void loadArgArray() {
        this.push(this.argumentTypes.length);
        this.newArray(OBJECT_TYPE);
        for (int i = 0; i < this.argumentTypes.length; ++i) {
            this.dup();
            this.push(i);
            this.loadArg(i);
            this.box(this.argumentTypes[i]);
            this.arrayStore(OBJECT_TYPE);
        }
    }

    public void storeArg(int n) {
        this.storeInsn(this.argumentTypes[n], this.getArgIndex(n));
    }

    public int newLocal(Type type) {
        int n = super.newLocal(type.getSize());
        this.setLocalType(n, type);
        return n;
    }

    public Type getLocalType(int n) {
        return (Type)this.localTypes.get(n - this.firstLocal);
    }

    private void setLocalType(int n, Type type) {
        int n2 = n - this.firstLocal;
        while (this.localTypes.size() < n2 + 1) {
            this.localTypes.add(null);
        }
        this.localTypes.set(n2, type);
    }

    public void loadLocal(int n) {
        this.loadInsn(this.getLocalType(n), n);
    }

    public void loadLocal(int n, Type type) {
        this.setLocalType(n, type);
        this.loadInsn(type, n);
    }

    public void storeLocal(int n) {
        this.storeInsn(this.getLocalType(n), n);
    }

    public void storeLocal(int n, Type type) {
        this.setLocalType(n, type);
        this.storeInsn(type, n);
    }

    public void arrayLoad(Type type) {
        this.mv.visitInsn(type.getOpcode(46));
    }

    public void arrayStore(Type type) {
        this.mv.visitInsn(type.getOpcode(79));
    }

    public void pop() {
        this.mv.visitInsn(87);
    }

    public void pop2() {
        this.mv.visitInsn(88);
    }

    public void dup() {
        this.mv.visitInsn(89);
    }

    public void dup2() {
        this.mv.visitInsn(92);
    }

    public void dupX1() {
        this.mv.visitInsn(90);
    }

    public void dupX2() {
        this.mv.visitInsn(91);
    }

    public void dup2X1() {
        this.mv.visitInsn(93);
    }

    public void dup2X2() {
        this.mv.visitInsn(94);
    }

    public void swap() {
        this.mv.visitInsn(95);
    }

    public void swap(Type type, Type type2) {
        if (type2.getSize() == 1) {
            if (type.getSize() == 1) {
                this.swap();
            } else {
                this.dupX2();
                this.pop();
            }
        } else if (type.getSize() == 1) {
            this.dup2X1();
            this.pop2();
        } else {
            this.dup2X2();
            this.pop2();
        }
    }

    public void math(int n, Type type) {
        this.mv.visitInsn(type.getOpcode(n));
    }

    public void not() {
        this.mv.visitInsn(4);
        this.mv.visitInsn(130);
    }

    public void iinc(int n, int n2) {
        this.mv.visitIincInsn(n, n2);
    }

    public void cast(Type type, Type type2) {
        if (type != type2) {
            if (type == Type.DOUBLE_TYPE) {
                if (type2 == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(144);
                } else if (type2 == Type.LONG_TYPE) {
                    this.mv.visitInsn(143);
                } else {
                    this.mv.visitInsn(142);
                    this.cast(Type.INT_TYPE, type2);
                }
            } else if (type == Type.FLOAT_TYPE) {
                if (type2 == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(141);
                } else if (type2 == Type.LONG_TYPE) {
                    this.mv.visitInsn(140);
                } else {
                    this.mv.visitInsn(139);
                    this.cast(Type.INT_TYPE, type2);
                }
            } else if (type == Type.LONG_TYPE) {
                if (type2 == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(138);
                } else if (type2 == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(137);
                } else {
                    this.mv.visitInsn(136);
                    this.cast(Type.INT_TYPE, type2);
                }
            } else if (type2 == Type.BYTE_TYPE) {
                this.mv.visitInsn(145);
            } else if (type2 == Type.CHAR_TYPE) {
                this.mv.visitInsn(146);
            } else if (type2 == Type.DOUBLE_TYPE) {
                this.mv.visitInsn(135);
            } else if (type2 == Type.FLOAT_TYPE) {
                this.mv.visitInsn(134);
            } else if (type2 == Type.LONG_TYPE) {
                this.mv.visitInsn(133);
            } else if (type2 == Type.SHORT_TYPE) {
                this.mv.visitInsn(147);
            }
        }
    }

    public void box(Type type) {
        if (type.getSort() == 10 || type.getSort() == 9) {
            return;
        }
        if (type == Type.VOID_TYPE) {
            this.push((String)null);
        } else {
            Type type2 = type;
            switch (type.getSort()) {
                case 3: {
                    type2 = BYTE_TYPE;
                    break;
                }
                case 1: {
                    type2 = BOOLEAN_TYPE;
                    break;
                }
                case 4: {
                    type2 = SHORT_TYPE;
                    break;
                }
                case 2: {
                    type2 = CHARACTER_TYPE;
                    break;
                }
                case 5: {
                    type2 = INTEGER_TYPE;
                    break;
                }
                case 6: {
                    type2 = FLOAT_TYPE;
                    break;
                }
                case 7: {
                    type2 = LONG_TYPE;
                    break;
                }
                case 8: {
                    type2 = DOUBLE_TYPE;
                }
            }
            this.newInstance(type2);
            if (type.getSize() == 2) {
                this.dupX2();
                this.dupX2();
                this.pop();
            } else {
                this.dupX1();
                this.swap();
            }
            this.invokeConstructor(type2, new Method("<init>", Type.VOID_TYPE, new Type[]{type}));
        }
    }

    public void unbox(Type type) {
        Type type2 = NUMBER_TYPE;
        Method method = null;
        switch (type.getSort()) {
            case 0: {
                return;
            }
            case 2: {
                type2 = CHARACTER_TYPE;
                method = CHAR_VALUE;
                break;
            }
            case 1: {
                type2 = BOOLEAN_TYPE;
                method = BOOLEAN_VALUE;
                break;
            }
            case 8: {
                method = DOUBLE_VALUE;
                break;
            }
            case 6: {
                method = FLOAT_VALUE;
                break;
            }
            case 7: {
                method = LONG_VALUE;
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                method = INT_VALUE;
            }
        }
        if (method == null) {
            this.checkCast(type);
        } else {
            this.checkCast(type2);
            this.invokeVirtual(type2, method);
        }
    }

    public Label newLabel() {
        return new Label();
    }

    public void mark(Label label) {
        this.mv.visitLabel(label);
    }

    public Label mark() {
        Label label = new Label();
        this.mv.visitLabel(label);
        return label;
    }

    public void ifCmp(Type type, int n, Label label) {
        int n2 = -1;
        int n3 = n;
        switch (n) {
            case 156: {
                n3 = 155;
                break;
            }
            case 158: {
                n3 = 157;
            }
        }
        switch (type.getSort()) {
            case 7: {
                this.mv.visitInsn(148);
                break;
            }
            case 8: {
                this.mv.visitInsn(152);
                break;
            }
            case 6: {
                this.mv.visitInsn(150);
                break;
            }
            case 9: 
            case 10: {
                switch (n) {
                    case 153: {
                        this.mv.visitJumpInsn(165, label);
                        return;
                    }
                    case 154: {
                        this.mv.visitJumpInsn(166, label);
                        return;
                    }
                }
                throw new IllegalArgumentException("Bad comparison for type " + type);
            }
            default: {
                switch (n) {
                    case 153: {
                        n2 = 159;
                        break;
                    }
                    case 154: {
                        n2 = 160;
                        break;
                    }
                    case 156: {
                        n2 = 162;
                        break;
                    }
                    case 155: {
                        n2 = 161;
                        break;
                    }
                    case 158: {
                        n2 = 164;
                        break;
                    }
                    case 157: {
                        n2 = 163;
                    }
                }
                this.mv.visitJumpInsn(n2, label);
                return;
            }
        }
        this.mv.visitJumpInsn(n3, label);
    }

    public void ifICmp(int n, Label label) {
        this.ifCmp(Type.INT_TYPE, n, label);
    }

    public void ifZCmp(int n, Label label) {
        this.mv.visitJumpInsn(n, label);
    }

    public void ifNull(Label label) {
        this.mv.visitJumpInsn(198, label);
    }

    public void ifNonNull(Label label) {
        this.mv.visitJumpInsn(199, label);
    }

    public void goTo(Label label) {
        this.mv.visitJumpInsn(167, label);
    }

    public void ret(int n) {
        this.mv.visitVarInsn(169, n);
    }

    public void tableSwitch(int[] nArray, TableSwitchGenerator tableSwitchGenerator) {
        float f = nArray.length == 0 ? 0.0f : (float)nArray.length / (float)(nArray[nArray.length - 1] - nArray[0] + 1);
        this.tableSwitch(nArray, tableSwitchGenerator, f >= 0.5f);
    }

    public void tableSwitch(int[] nArray, TableSwitchGenerator tableSwitchGenerator, boolean bl) {
        for (int i = 1; i < nArray.length; ++i) {
            if (nArray[i] >= nArray[i - 1]) continue;
            throw new IllegalArgumentException("keys must be sorted ascending");
        }
        Label label = this.newLabel();
        Label label2 = this.newLabel();
        if (nArray.length > 0) {
            int n = nArray.length;
            int n2 = nArray[0];
            int n3 = nArray[n - 1];
            int n4 = n3 - n2 + 1;
            if (bl) {
                int n5;
                Object[] objectArray = new Label[n4];
                Arrays.fill(objectArray, label);
                for (n5 = 0; n5 < n; ++n5) {
                    objectArray[nArray[n5] - n2] = this.newLabel();
                }
                this.mv.visitTableSwitchInsn(n2, n3, label, (Label[])objectArray);
                for (n5 = 0; n5 < n4; ++n5) {
                    Object object = objectArray[n5];
                    if (object == label) continue;
                    this.mark((Label)object);
                    tableSwitchGenerator.generateCase(n5 + n2, label2);
                }
            } else {
                int n6;
                Label[] labelArray = new Label[n];
                for (n6 = 0; n6 < n; ++n6) {
                    labelArray[n6] = this.newLabel();
                }
                this.mv.visitLookupSwitchInsn(label, nArray, labelArray);
                for (n6 = 0; n6 < n; ++n6) {
                    this.mark(labelArray[n6]);
                    tableSwitchGenerator.generateCase(nArray[n6], label2);
                }
            }
        }
        this.mark(label);
        tableSwitchGenerator.generateDefault();
        this.mark(label2);
    }

    public void returnValue() {
        this.mv.visitInsn(this.returnType.getOpcode(172));
    }

    private void fieldInsn(int n, Type type, String string, Type type2) {
        this.mv.visitFieldInsn(n, type.getInternalName(), string, type2.getDescriptor());
    }

    public void getStatic(Type type, String string, Type type2) {
        this.fieldInsn(178, type, string, type2);
    }

    public void putStatic(Type type, String string, Type type2) {
        this.fieldInsn(179, type, string, type2);
    }

    public void getField(Type type, String string, Type type2) {
        this.fieldInsn(180, type, string, type2);
    }

    public void putField(Type type, String string, Type type2) {
        this.fieldInsn(181, type, string, type2);
    }

    private void invokeInsn(int n, Type type, Method method) {
        String string = type.getSort() == 9 ? type.getDescriptor() : type.getInternalName();
        this.mv.visitMethodInsn(n, string, method.getName(), method.getDescriptor());
    }

    public void invokeVirtual(Type type, Method method) {
        this.invokeInsn(182, type, method);
    }

    public void invokeConstructor(Type type, Method method) {
        this.invokeInsn(183, type, method);
    }

    public void invokeStatic(Type type, Method method) {
        this.invokeInsn(184, type, method);
    }

    public void invokeInterface(Type type, Method method) {
        this.invokeInsn(185, type, method);
    }

    private void typeInsn(int n, Type type) {
        String string = type.getSort() == 9 ? type.getDescriptor() : type.getInternalName();
        this.mv.visitTypeInsn(n, string);
    }

    public void newInstance(Type type) {
        this.typeInsn(187, type);
    }

    public void newArray(Type type) {
        int n;
        switch (type.getSort()) {
            case 1: {
                n = 4;
                break;
            }
            case 2: {
                n = 5;
                break;
            }
            case 3: {
                n = 8;
                break;
            }
            case 4: {
                n = 9;
                break;
            }
            case 5: {
                n = 10;
                break;
            }
            case 6: {
                n = 6;
                break;
            }
            case 7: {
                n = 11;
                break;
            }
            case 8: {
                n = 7;
                break;
            }
            default: {
                this.typeInsn(189, type);
                return;
            }
        }
        this.mv.visitIntInsn(188, n);
    }

    public void arrayLength() {
        this.mv.visitInsn(190);
    }

    public void throwException() {
        this.mv.visitInsn(191);
    }

    public void throwException(Type type, String string) {
        this.newInstance(type);
        this.dup();
        this.push(string);
        this.invokeConstructor(type, Method.getMethod("void <init> (String)"));
        this.throwException();
    }

    public void checkCast(Type type) {
        if (!type.equals(OBJECT_TYPE)) {
            this.typeInsn(192, type);
        }
    }

    public void instanceOf(Type type) {
        this.typeInsn(193, type);
    }

    public void monitorEnter() {
        this.mv.visitInsn(194);
    }

    public void monitorExit() {
        this.mv.visitInsn(195);
    }

    public void endMethod() {
        if ((this.access & 0x400) == 0) {
            this.mv.visitMaxs(0, 0);
        }
    }

    public void catchException(Label label, Label label2, Type type) {
        this.mv.visitTryCatchBlock(label, label2, this.mark(), type.getInternalName());
    }
}

