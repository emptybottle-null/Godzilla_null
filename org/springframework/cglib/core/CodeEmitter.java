/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.util.Arrays;
import org.springframework.asm.Attribute;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.Block;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.LocalVariablesSorter;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.ProcessSwitchCallback;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.core.TypeUtils;

public class CodeEmitter
extends LocalVariablesSorter {
    private static final Signature BOOLEAN_VALUE = TypeUtils.parseSignature("boolean booleanValue()");
    private static final Signature CHAR_VALUE = TypeUtils.parseSignature("char charValue()");
    private static final Signature LONG_VALUE = TypeUtils.parseSignature("long longValue()");
    private static final Signature DOUBLE_VALUE = TypeUtils.parseSignature("double doubleValue()");
    private static final Signature FLOAT_VALUE = TypeUtils.parseSignature("float floatValue()");
    private static final Signature INT_VALUE = TypeUtils.parseSignature("int intValue()");
    private static final Signature CSTRUCT_NULL = TypeUtils.parseConstructor("");
    private static final Signature CSTRUCT_STRING = TypeUtils.parseConstructor("String");
    public static final int ADD = 96;
    public static final int MUL = 104;
    public static final int XOR = 130;
    public static final int USHR = 124;
    public static final int SUB = 100;
    public static final int DIV = 108;
    public static final int NEG = 116;
    public static final int REM = 112;
    public static final int AND = 126;
    public static final int OR = 128;
    public static final int GT = 157;
    public static final int LT = 155;
    public static final int GE = 156;
    public static final int LE = 158;
    public static final int NE = 154;
    public static final int EQ = 153;
    private ClassEmitter ce;
    private State state;

    CodeEmitter(ClassEmitter ce, MethodVisitor mv, int access, Signature sig, Type[] exceptionTypes) {
        super(access, sig.getDescriptor(), mv);
        this.ce = ce;
        this.state = new State(ce.getClassInfo(), access, sig, exceptionTypes);
    }

    public CodeEmitter(CodeEmitter wrap) {
        super(wrap);
        this.ce = wrap.ce;
        this.state = wrap.state;
    }

    public boolean isStaticHook() {
        return false;
    }

    public Signature getSignature() {
        return this.state.sig;
    }

    public Type getReturnType() {
        return this.state.sig.getReturnType();
    }

    public MethodInfo getMethodInfo() {
        return this.state;
    }

    public ClassEmitter getClassEmitter() {
        return this.ce;
    }

    public void end_method() {
        this.visitMaxs(0, 0);
    }

    public Block begin_block() {
        return new Block(this);
    }

    public void catch_exception(Block block, Type exception) {
        if (block.getEnd() == null) {
            throw new IllegalStateException("end of block is unset");
        }
        this.mv.visitTryCatchBlock(block.getStart(), block.getEnd(), this.mark(), exception.getInternalName());
    }

    public void goTo(Label label) {
        this.mv.visitJumpInsn(167, label);
    }

    public void ifnull(Label label) {
        this.mv.visitJumpInsn(198, label);
    }

    public void ifnonnull(Label label) {
        this.mv.visitJumpInsn(199, label);
    }

    public void if_jump(int mode, Label label) {
        this.mv.visitJumpInsn(mode, label);
    }

    public void if_icmp(int mode, Label label) {
        this.if_cmp(Type.INT_TYPE, mode, label);
    }

    public void if_cmp(Type type, int mode, Label label) {
        int intOp = -1;
        int jumpmode = mode;
        switch (mode) {
            case 156: {
                jumpmode = 155;
                break;
            }
            case 158: {
                jumpmode = 157;
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
                switch (mode) {
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
                switch (mode) {
                    case 153: {
                        intOp = 159;
                        break;
                    }
                    case 154: {
                        intOp = 160;
                        break;
                    }
                    case 156: {
                        this.swap();
                    }
                    case 155: {
                        intOp = 161;
                        break;
                    }
                    case 158: {
                        this.swap();
                    }
                    case 157: {
                        intOp = 163;
                    }
                }
                this.mv.visitJumpInsn(intOp, label);
                return;
            }
        }
        this.if_jump(jumpmode, label);
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

    public void dup_x1() {
        this.mv.visitInsn(90);
    }

    public void dup_x2() {
        this.mv.visitInsn(91);
    }

    public void dup2_x1() {
        this.mv.visitInsn(93);
    }

    public void dup2_x2() {
        this.mv.visitInsn(94);
    }

    public void swap() {
        this.mv.visitInsn(95);
    }

    public void aconst_null() {
        this.mv.visitInsn(1);
    }

    public void swap(Type prev, Type type) {
        if (type.getSize() == 1) {
            if (prev.getSize() == 1) {
                this.swap();
            } else {
                this.dup_x2();
                this.pop();
            }
        } else if (prev.getSize() == 1) {
            this.dup2_x1();
            this.pop2();
        } else {
            this.dup2_x2();
            this.pop2();
        }
    }

    public void monitorenter() {
        this.mv.visitInsn(194);
    }

    public void monitorexit() {
        this.mv.visitInsn(195);
    }

    public void math(int op, Type type) {
        this.mv.visitInsn(type.getOpcode(op));
    }

    public void array_load(Type type) {
        this.mv.visitInsn(type.getOpcode(46));
    }

    public void array_store(Type type) {
        this.mv.visitInsn(type.getOpcode(79));
    }

    public void cast_numeric(Type from, Type to) {
        if (from != to) {
            if (from == Type.DOUBLE_TYPE) {
                if (to == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(144);
                } else if (to == Type.LONG_TYPE) {
                    this.mv.visitInsn(143);
                } else {
                    this.mv.visitInsn(142);
                    this.cast_numeric(Type.INT_TYPE, to);
                }
            } else if (from == Type.FLOAT_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(141);
                } else if (to == Type.LONG_TYPE) {
                    this.mv.visitInsn(140);
                } else {
                    this.mv.visitInsn(139);
                    this.cast_numeric(Type.INT_TYPE, to);
                }
            } else if (from == Type.LONG_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    this.mv.visitInsn(138);
                } else if (to == Type.FLOAT_TYPE) {
                    this.mv.visitInsn(137);
                } else {
                    this.mv.visitInsn(136);
                    this.cast_numeric(Type.INT_TYPE, to);
                }
            } else if (to == Type.BYTE_TYPE) {
                this.mv.visitInsn(145);
            } else if (to == Type.CHAR_TYPE) {
                this.mv.visitInsn(146);
            } else if (to == Type.DOUBLE_TYPE) {
                this.mv.visitInsn(135);
            } else if (to == Type.FLOAT_TYPE) {
                this.mv.visitInsn(134);
            } else if (to == Type.LONG_TYPE) {
                this.mv.visitInsn(133);
            } else if (to == Type.SHORT_TYPE) {
                this.mv.visitInsn(147);
            }
        }
    }

    public void push(int i) {
        if (i < -1) {
            this.mv.visitLdcInsn(new Integer(i));
        } else if (i <= 5) {
            this.mv.visitInsn(TypeUtils.ICONST(i));
        } else if (i <= 127) {
            this.mv.visitIntInsn(16, i);
        } else if (i <= Short.MAX_VALUE) {
            this.mv.visitIntInsn(17, i);
        } else {
            this.mv.visitLdcInsn(new Integer(i));
        }
    }

    public void push(long value) {
        if (value == 0L || value == 1L) {
            this.mv.visitInsn(TypeUtils.LCONST(value));
        } else {
            this.mv.visitLdcInsn(new Long(value));
        }
    }

    public void push(float value) {
        if (value == 0.0f || value == 1.0f || value == 2.0f) {
            this.mv.visitInsn(TypeUtils.FCONST(value));
        } else {
            this.mv.visitLdcInsn(new Float(value));
        }
    }

    public void push(double value) {
        if (value == 0.0 || value == 1.0) {
            this.mv.visitInsn(TypeUtils.DCONST(value));
        } else {
            this.mv.visitLdcInsn(new Double(value));
        }
    }

    public void push(String value) {
        this.mv.visitLdcInsn(value);
    }

    public void newarray() {
        this.newarray(Constants.TYPE_OBJECT);
    }

    public void newarray(Type type) {
        if (TypeUtils.isPrimitive(type)) {
            this.mv.visitIntInsn(188, TypeUtils.NEWARRAY(type));
        } else {
            this.emit_type(189, type);
        }
    }

    public void arraylength() {
        this.mv.visitInsn(190);
    }

    public void load_this() {
        if (TypeUtils.isStatic(this.state.access)) {
            throw new IllegalStateException("no 'this' pointer within static method");
        }
        this.mv.visitVarInsn(25, 0);
    }

    public void load_args() {
        this.load_args(0, this.state.argumentTypes.length);
    }

    public void load_arg(int index) {
        this.load_local(this.state.argumentTypes[index], this.state.localOffset + this.skipArgs(index));
    }

    public void load_args(int fromArg, int count) {
        int pos = this.state.localOffset + this.skipArgs(fromArg);
        for (int i = 0; i < count; ++i) {
            Type t = this.state.argumentTypes[fromArg + i];
            this.load_local(t, pos);
            pos += t.getSize();
        }
    }

    private int skipArgs(int numArgs) {
        int amount = 0;
        for (int i = 0; i < numArgs; ++i) {
            amount += this.state.argumentTypes[i].getSize();
        }
        return amount;
    }

    private void load_local(Type t, int pos) {
        this.mv.visitVarInsn(t.getOpcode(21), pos);
    }

    private void store_local(Type t, int pos) {
        this.mv.visitVarInsn(t.getOpcode(54), pos);
    }

    public void iinc(Local local, int amount) {
        this.mv.visitIincInsn(local.getIndex(), amount);
    }

    public void store_local(Local local) {
        this.store_local(local.getType(), local.getIndex());
    }

    public void load_local(Local local) {
        this.load_local(local.getType(), local.getIndex());
    }

    public void return_value() {
        this.mv.visitInsn(this.state.sig.getReturnType().getOpcode(172));
    }

    public void getfield(String name) {
        ClassEmitter.FieldInfo info = this.ce.getFieldInfo(name);
        int opcode = TypeUtils.isStatic(info.access) ? 178 : 180;
        this.emit_field(opcode, this.ce.getClassType(), name, info.type);
    }

    public void putfield(String name) {
        ClassEmitter.FieldInfo info = this.ce.getFieldInfo(name);
        int opcode = TypeUtils.isStatic(info.access) ? 179 : 181;
        this.emit_field(opcode, this.ce.getClassType(), name, info.type);
    }

    public void super_getfield(String name, Type type) {
        this.emit_field(180, this.ce.getSuperType(), name, type);
    }

    public void super_putfield(String name, Type type) {
        this.emit_field(181, this.ce.getSuperType(), name, type);
    }

    public void super_getstatic(String name, Type type) {
        this.emit_field(178, this.ce.getSuperType(), name, type);
    }

    public void super_putstatic(String name, Type type) {
        this.emit_field(179, this.ce.getSuperType(), name, type);
    }

    public void getfield(Type owner, String name, Type type) {
        this.emit_field(180, owner, name, type);
    }

    public void putfield(Type owner, String name, Type type) {
        this.emit_field(181, owner, name, type);
    }

    public void getstatic(Type owner, String name, Type type) {
        this.emit_field(178, owner, name, type);
    }

    public void putstatic(Type owner, String name, Type type) {
        this.emit_field(179, owner, name, type);
    }

    void emit_field(int opcode, Type ctype, String name, Type ftype) {
        this.mv.visitFieldInsn(opcode, ctype.getInternalName(), name, ftype.getDescriptor());
    }

    public void super_invoke() {
        this.super_invoke(this.state.sig);
    }

    public void super_invoke(Signature sig) {
        this.emit_invoke(183, this.ce.getSuperType(), sig, false);
    }

    public void invoke_constructor(Type type) {
        this.invoke_constructor(type, CSTRUCT_NULL);
    }

    public void super_invoke_constructor() {
        this.invoke_constructor(this.ce.getSuperType());
    }

    public void invoke_constructor_this() {
        this.invoke_constructor(this.ce.getClassType());
    }

    private void emit_invoke(int opcode, Type type, Signature sig, boolean isInterface) {
        if (!sig.getName().equals("<init>") || opcode == 182 || opcode == 184) {
            // empty if block
        }
        this.mv.visitMethodInsn(opcode, type.getInternalName(), sig.getName(), sig.getDescriptor(), isInterface);
    }

    public void invoke_interface(Type owner, Signature sig) {
        this.emit_invoke(185, owner, sig, true);
    }

    public void invoke_virtual(Type owner, Signature sig) {
        this.emit_invoke(182, owner, sig, false);
    }

    @Deprecated
    public void invoke_static(Type owner, Signature sig) {
        this.invoke_static(owner, sig, false);
    }

    public void invoke_static(Type owner, Signature sig, boolean isInterface) {
        this.emit_invoke(184, owner, sig, isInterface);
    }

    public void invoke_virtual_this(Signature sig) {
        this.invoke_virtual(this.ce.getClassType(), sig);
    }

    public void invoke_static_this(Signature sig) {
        this.invoke_static(this.ce.getClassType(), sig);
    }

    public void invoke_constructor(Type type, Signature sig) {
        this.emit_invoke(183, type, sig, false);
    }

    public void invoke_constructor_this(Signature sig) {
        this.invoke_constructor(this.ce.getClassType(), sig);
    }

    public void super_invoke_constructor(Signature sig) {
        this.invoke_constructor(this.ce.getSuperType(), sig);
    }

    public void new_instance_this() {
        this.new_instance(this.ce.getClassType());
    }

    public void new_instance(Type type) {
        this.emit_type(187, type);
    }

    private void emit_type(int opcode, Type type) {
        String desc = TypeUtils.isArray(type) ? type.getDescriptor() : type.getInternalName();
        this.mv.visitTypeInsn(opcode, desc);
    }

    public void aaload(int index) {
        this.push(index);
        this.aaload();
    }

    public void aaload() {
        this.mv.visitInsn(50);
    }

    public void aastore() {
        this.mv.visitInsn(83);
    }

    public void athrow() {
        this.mv.visitInsn(191);
    }

    public Label make_label() {
        return new Label();
    }

    public Local make_local() {
        return this.make_local(Constants.TYPE_OBJECT);
    }

    public Local make_local(Type type) {
        return new Local(this.newLocal(type.getSize()), type);
    }

    public void checkcast_this() {
        this.checkcast(this.ce.getClassType());
    }

    public void checkcast(Type type) {
        if (!type.equals(Constants.TYPE_OBJECT)) {
            this.emit_type(192, type);
        }
    }

    public void instance_of(Type type) {
        this.emit_type(193, type);
    }

    public void instance_of_this() {
        this.instance_of(this.ce.getClassType());
    }

    public void process_switch(int[] keys, ProcessSwitchCallback callback) {
        float density = keys.length == 0 ? 0.0f : (float)keys.length / (float)(keys[keys.length - 1] - keys[0] + 1);
        this.process_switch(keys, callback, density >= 0.5f);
    }

    public void process_switch(int[] keys, ProcessSwitchCallback callback, boolean useTable) {
        if (!CodeEmitter.isSorted(keys)) {
            throw new IllegalArgumentException("keys to switch must be sorted ascending");
        }
        Label def = this.make_label();
        Label end = this.make_label();
        try {
            if (keys.length > 0) {
                int len = keys.length;
                int min = keys[0];
                int max = keys[len - 1];
                int range = max - min + 1;
                if (useTable) {
                    int i;
                    Object[] labels = new Label[range];
                    Arrays.fill(labels, def);
                    for (i = 0; i < len; ++i) {
                        labels[keys[i] - min] = this.make_label();
                    }
                    this.mv.visitTableSwitchInsn(min, max, def, (Label[])labels);
                    for (i = 0; i < range; ++i) {
                        Object label = labels[i];
                        if (label == def) continue;
                        this.mark((Label)label);
                        callback.processCase(i + min, end);
                    }
                } else {
                    int i;
                    Label[] labels = new Label[len];
                    for (i = 0; i < len; ++i) {
                        labels[i] = this.make_label();
                    }
                    this.mv.visitLookupSwitchInsn(def, keys, labels);
                    for (i = 0; i < len; ++i) {
                        this.mark(labels[i]);
                        callback.processCase(keys[i], end);
                    }
                }
            }
            this.mark(def);
            callback.processDefault();
            this.mark(end);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw new CodeGenerationException(e);
        }
    }

    private static boolean isSorted(int[] keys) {
        for (int i = 1; i < keys.length; ++i) {
            if (keys[i] >= keys[i - 1]) continue;
            return false;
        }
        return true;
    }

    public void mark(Label label) {
        this.mv.visitLabel(label);
    }

    Label mark() {
        Label label = this.make_label();
        this.mv.visitLabel(label);
        return label;
    }

    public void push(boolean value) {
        this.push(value ? 1 : 0);
    }

    public void not() {
        this.push(1);
        this.math(130, Type.INT_TYPE);
    }

    public void throw_exception(Type type, String msg) {
        this.new_instance(type);
        this.dup();
        this.push(msg);
        this.invoke_constructor(type, CSTRUCT_STRING);
        this.athrow();
    }

    public void box(Type type) {
        if (TypeUtils.isPrimitive(type)) {
            if (type == Type.VOID_TYPE) {
                this.aconst_null();
            } else {
                Type boxed = TypeUtils.getBoxedType(type);
                this.new_instance(boxed);
                if (type.getSize() == 2) {
                    this.dup_x2();
                    this.dup_x2();
                    this.pop();
                } else {
                    this.dup_x1();
                    this.swap();
                }
                this.invoke_constructor(boxed, new Signature("<init>", Type.VOID_TYPE, new Type[]{type}));
            }
        }
    }

    public void unbox(Type type) {
        Type t = Constants.TYPE_NUMBER;
        Signature sig = null;
        switch (type.getSort()) {
            case 0: {
                return;
            }
            case 2: {
                t = Constants.TYPE_CHARACTER;
                sig = CHAR_VALUE;
                break;
            }
            case 1: {
                t = Constants.TYPE_BOOLEAN;
                sig = BOOLEAN_VALUE;
                break;
            }
            case 8: {
                sig = DOUBLE_VALUE;
                break;
            }
            case 6: {
                sig = FLOAT_VALUE;
                break;
            }
            case 7: {
                sig = LONG_VALUE;
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                sig = INT_VALUE;
            }
        }
        if (sig == null) {
            this.checkcast(type);
        } else {
            this.checkcast(t);
            this.invoke_virtual(t, sig);
        }
    }

    public void create_arg_array() {
        this.push(this.state.argumentTypes.length);
        this.newarray();
        for (int i = 0; i < this.state.argumentTypes.length; ++i) {
            this.dup();
            this.push(i);
            this.load_arg(i);
            this.box(this.state.argumentTypes[i]);
            this.aastore();
        }
    }

    public void zero_or_null(Type type) {
        if (TypeUtils.isPrimitive(type)) {
            switch (type.getSort()) {
                case 8: {
                    this.push(0.0);
                    break;
                }
                case 7: {
                    this.push(0L);
                    break;
                }
                case 6: {
                    this.push(0.0f);
                    break;
                }
                case 0: {
                    this.aconst_null();
                }
                default: {
                    this.push(0);
                    break;
                }
            }
        } else {
            this.aconst_null();
        }
    }

    public void unbox_or_zero(Type type) {
        if (TypeUtils.isPrimitive(type)) {
            if (type != Type.VOID_TYPE) {
                Label nonNull = this.make_label();
                Label end = this.make_label();
                this.dup();
                this.ifnonnull(nonNull);
                this.pop();
                this.zero_or_null(type);
                this.goTo(end);
                this.mark(nonNull);
                this.unbox(type);
                this.mark(end);
            }
        } else {
            this.checkcast(type);
        }
    }

    public void visitMaxs(int maxStack, int maxLocals) {
        if (!TypeUtils.isAbstract(this.state.access)) {
            this.mv.visitMaxs(0, 0);
        }
    }

    public void invoke(MethodInfo method, Type virtualType) {
        ClassInfo classInfo = method.getClassInfo();
        Type type = classInfo.getType();
        Signature sig = method.getSignature();
        if (sig.getName().equals("<init>")) {
            this.invoke_constructor(type, sig);
        } else if (TypeUtils.isStatic(method.getModifiers())) {
            this.invoke_static(type, sig, TypeUtils.isInterface(classInfo.getModifiers()));
        } else if (TypeUtils.isInterface(classInfo.getModifiers())) {
            this.invoke_interface(type, sig);
        } else {
            this.invoke_virtual(virtualType, sig);
        }
    }

    public void invoke(MethodInfo method) {
        this.invoke(method, method.getClassInfo().getType());
    }

    private static class State
    extends MethodInfo {
        ClassInfo classInfo;
        int access;
        Signature sig;
        Type[] argumentTypes;
        int localOffset;
        Type[] exceptionTypes;

        State(ClassInfo classInfo, int access, Signature sig, Type[] exceptionTypes) {
            this.classInfo = classInfo;
            this.access = access;
            this.sig = sig;
            this.exceptionTypes = exceptionTypes;
            this.localOffset = TypeUtils.isStatic(access) ? 0 : 1;
            this.argumentTypes = sig.getArgumentTypes();
        }

        public ClassInfo getClassInfo() {
            return this.classInfo;
        }

        public int getModifiers() {
            return this.access;
        }

        public Signature getSignature() {
            return this.sig;
        }

        public Type[] getExceptionTypes() {
            return this.exceptionTypes;
        }

        public Attribute getAttribute() {
            return null;
        }
    }
}

