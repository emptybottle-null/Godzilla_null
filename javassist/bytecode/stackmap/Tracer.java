/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.stackmap;

import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.Opcode;
import javassist.bytecode.stackmap.TypeData;
import javassist.bytecode.stackmap.TypeTag;

public abstract class Tracer
implements TypeTag {
    protected ClassPool classPool;
    protected ConstPool cpool;
    protected String returnType;
    protected int stackTop;
    protected TypeData[] stackTypes;
    protected TypeData[] localsTypes;

    public Tracer(ClassPool classes, ConstPool cp, int maxStack, int maxLocals, String retType) {
        this.classPool = classes;
        this.cpool = cp;
        this.returnType = retType;
        this.stackTop = 0;
        this.stackTypes = TypeData.make(maxStack);
        this.localsTypes = TypeData.make(maxLocals);
    }

    public Tracer(Tracer t) {
        this.classPool = t.classPool;
        this.cpool = t.cpool;
        this.returnType = t.returnType;
        this.stackTop = t.stackTop;
        this.stackTypes = TypeData.make(t.stackTypes.length);
        this.localsTypes = TypeData.make(t.localsTypes.length);
    }

    protected int doOpcode(int pos, byte[] code) throws BadBytecode {
        try {
            int op = code[pos] & 0xFF;
            if (op < 54) {
                return this.doOpcode0_53(pos, code, op);
            }
            if (op < 96) {
                return this.doOpcode54_95(pos, code, op);
            }
            if (op < 148) {
                return this.doOpcode96_147(pos, code, op);
            }
            return this.doOpcode148_201(pos, code, op);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BadBytecode("inconsistent stack height " + e.getMessage(), (Throwable)e);
        }
    }

    protected void visitBranch(int pos, byte[] code, int offset) throws BadBytecode {
    }

    protected void visitGoto(int pos, byte[] code, int offset) throws BadBytecode {
    }

    protected void visitReturn(int pos, byte[] code) throws BadBytecode {
    }

    protected void visitThrow(int pos, byte[] code) throws BadBytecode {
    }

    protected void visitTableSwitch(int pos, byte[] code, int n, int offsetPos, int defaultOffset) throws BadBytecode {
    }

    protected void visitLookupSwitch(int pos, byte[] code, int n, int pairsPos, int defaultOffset) throws BadBytecode {
    }

    protected void visitJSR(int pos, byte[] code) throws BadBytecode {
    }

    protected void visitRET(int pos, byte[] code) throws BadBytecode {
    }

    private int doOpcode0_53(int pos, byte[] code, int op) throws BadBytecode {
        TypeData[] stackTypes = this.stackTypes;
        switch (op) {
            case 0: {
                break;
            }
            case 1: {
                stackTypes[this.stackTop++] = new TypeData.NullType();
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                stackTypes[this.stackTop++] = INTEGER;
                break;
            }
            case 9: 
            case 10: {
                stackTypes[this.stackTop++] = LONG;
                stackTypes[this.stackTop++] = TOP;
                break;
            }
            case 11: 
            case 12: 
            case 13: {
                stackTypes[this.stackTop++] = FLOAT;
                break;
            }
            case 14: 
            case 15: {
                stackTypes[this.stackTop++] = DOUBLE;
                stackTypes[this.stackTop++] = TOP;
                break;
            }
            case 16: 
            case 17: {
                stackTypes[this.stackTop++] = INTEGER;
                return op == 17 ? 3 : 2;
            }
            case 18: {
                this.doLDC(code[pos + 1] & 0xFF);
                return 2;
            }
            case 19: 
            case 20: {
                this.doLDC(ByteArray.readU16bit(code, pos + 1));
                return 3;
            }
            case 21: {
                return this.doXLOAD(INTEGER, code, pos);
            }
            case 22: {
                return this.doXLOAD(LONG, code, pos);
            }
            case 23: {
                return this.doXLOAD(FLOAT, code, pos);
            }
            case 24: {
                return this.doXLOAD(DOUBLE, code, pos);
            }
            case 25: {
                return this.doALOAD(code[pos + 1] & 0xFF);
            }
            case 26: 
            case 27: 
            case 28: 
            case 29: {
                stackTypes[this.stackTop++] = INTEGER;
                break;
            }
            case 30: 
            case 31: 
            case 32: 
            case 33: {
                stackTypes[this.stackTop++] = LONG;
                stackTypes[this.stackTop++] = TOP;
                break;
            }
            case 34: 
            case 35: 
            case 36: 
            case 37: {
                stackTypes[this.stackTop++] = FLOAT;
                break;
            }
            case 38: 
            case 39: 
            case 40: 
            case 41: {
                stackTypes[this.stackTop++] = DOUBLE;
                stackTypes[this.stackTop++] = TOP;
                break;
            }
            case 42: 
            case 43: 
            case 44: 
            case 45: {
                int reg = op - 42;
                stackTypes[this.stackTop++] = this.localsTypes[reg];
                break;
            }
            case 46: {
                stackTypes[--this.stackTop - 1] = INTEGER;
                break;
            }
            case 47: {
                stackTypes[this.stackTop - 2] = LONG;
                stackTypes[this.stackTop - 1] = TOP;
                break;
            }
            case 48: {
                stackTypes[--this.stackTop - 1] = FLOAT;
                break;
            }
            case 49: {
                stackTypes[this.stackTop - 2] = DOUBLE;
                stackTypes[this.stackTop - 1] = TOP;
                break;
            }
            case 50: {
                int s = --this.stackTop - 1;
                TypeData data = stackTypes[s];
                stackTypes[s] = TypeData.ArrayElement.make(data);
                break;
            }
            case 51: 
            case 52: 
            case 53: {
                stackTypes[--this.stackTop - 1] = INTEGER;
                break;
            }
            default: {
                throw new RuntimeException("fatal");
            }
        }
        return 1;
    }

    private void doLDC(int index) {
        TypeData[] stackTypes = this.stackTypes;
        int tag = this.cpool.getTag(index);
        if (tag == 8) {
            stackTypes[this.stackTop++] = new TypeData.ClassName("java.lang.String");
        } else if (tag == 3) {
            stackTypes[this.stackTop++] = INTEGER;
        } else if (tag == 4) {
            stackTypes[this.stackTop++] = FLOAT;
        } else if (tag == 5) {
            stackTypes[this.stackTop++] = LONG;
            stackTypes[this.stackTop++] = TOP;
        } else if (tag == 6) {
            stackTypes[this.stackTop++] = DOUBLE;
            stackTypes[this.stackTop++] = TOP;
        } else if (tag == 7) {
            stackTypes[this.stackTop++] = new TypeData.ClassName("java.lang.Class");
        } else if (tag == 17) {
            String desc = this.cpool.getDynamicType(index);
            this.pushMemberType(desc);
        } else {
            throw new RuntimeException("bad LDC: " + tag);
        }
    }

    private int doXLOAD(TypeData type, byte[] code, int pos) {
        int localVar = code[pos + 1] & 0xFF;
        return this.doXLOAD(localVar, type);
    }

    private int doXLOAD(int localVar, TypeData type) {
        this.stackTypes[this.stackTop++] = type;
        if (type.is2WordType()) {
            this.stackTypes[this.stackTop++] = TOP;
        }
        return 2;
    }

    private int doALOAD(int localVar) {
        this.stackTypes[this.stackTop++] = this.localsTypes[localVar];
        return 2;
    }

    private int doOpcode54_95(int pos, byte[] code, int op) throws BadBytecode {
        switch (op) {
            case 54: {
                return this.doXSTORE(pos, code, INTEGER);
            }
            case 55: {
                return this.doXSTORE(pos, code, LONG);
            }
            case 56: {
                return this.doXSTORE(pos, code, FLOAT);
            }
            case 57: {
                return this.doXSTORE(pos, code, DOUBLE);
            }
            case 58: {
                return this.doASTORE(code[pos + 1] & 0xFF);
            }
            case 59: 
            case 60: 
            case 61: 
            case 62: {
                int var = op - 59;
                this.localsTypes[var] = INTEGER;
                --this.stackTop;
                break;
            }
            case 63: 
            case 64: 
            case 65: 
            case 66: {
                int var = op - 63;
                this.localsTypes[var] = LONG;
                this.localsTypes[var + 1] = TOP;
                this.stackTop -= 2;
                break;
            }
            case 67: 
            case 68: 
            case 69: 
            case 70: {
                int var = op - 67;
                this.localsTypes[var] = FLOAT;
                --this.stackTop;
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: {
                int var = op - 71;
                this.localsTypes[var] = DOUBLE;
                this.localsTypes[var + 1] = TOP;
                this.stackTop -= 2;
                break;
            }
            case 75: 
            case 76: 
            case 77: 
            case 78: {
                int var = op - 75;
                this.doASTORE(var);
                break;
            }
            case 79: 
            case 80: 
            case 81: 
            case 82: {
                this.stackTop -= op == 80 || op == 82 ? 4 : 3;
                break;
            }
            case 83: {
                TypeData.ArrayElement.aastore(this.stackTypes[this.stackTop - 3], this.stackTypes[this.stackTop - 1], this.classPool);
                this.stackTop -= 3;
                break;
            }
            case 84: 
            case 85: 
            case 86: {
                this.stackTop -= 3;
                break;
            }
            case 87: {
                --this.stackTop;
                break;
            }
            case 88: {
                this.stackTop -= 2;
                break;
            }
            case 89: {
                int sp = this.stackTop;
                this.stackTypes[sp] = this.stackTypes[sp - 1];
                this.stackTop = sp + 1;
                break;
            }
            case 90: 
            case 91: {
                int len = op - 90 + 2;
                this.doDUP_XX(1, len);
                int sp = this.stackTop;
                this.stackTypes[sp - len] = this.stackTypes[sp];
                this.stackTop = sp + 1;
                break;
            }
            case 92: {
                this.doDUP_XX(2, 2);
                this.stackTop += 2;
                break;
            }
            case 93: 
            case 94: {
                int len = op - 93 + 3;
                this.doDUP_XX(2, len);
                int sp = this.stackTop;
                this.stackTypes[sp - len] = this.stackTypes[sp];
                this.stackTypes[sp - len + 1] = this.stackTypes[sp + 1];
                this.stackTop = sp + 2;
                break;
            }
            case 95: {
                int sp = this.stackTop - 1;
                TypeData t = this.stackTypes[sp];
                this.stackTypes[sp] = this.stackTypes[sp - 1];
                this.stackTypes[sp - 1] = t;
                break;
            }
            default: {
                throw new RuntimeException("fatal");
            }
        }
        return 1;
    }

    private int doXSTORE(int pos, byte[] code, TypeData type) {
        int index = code[pos + 1] & 0xFF;
        return this.doXSTORE(index, type);
    }

    private int doXSTORE(int index, TypeData type) {
        --this.stackTop;
        this.localsTypes[index] = type;
        if (type.is2WordType()) {
            --this.stackTop;
            this.localsTypes[index + 1] = TOP;
        }
        return 2;
    }

    private int doASTORE(int index) {
        --this.stackTop;
        this.localsTypes[index] = this.stackTypes[this.stackTop];
        return 2;
    }

    private void doDUP_XX(int delta, int len) {
        int sp;
        TypeData[] types = this.stackTypes;
        int end = sp - len;
        for (sp = this.stackTop - 1; sp > end; --sp) {
            types[sp + delta] = types[sp];
        }
    }

    private int doOpcode96_147(int pos, byte[] code, int op) {
        if (op <= 131) {
            this.stackTop += Opcode.STACK_GROW[op];
            return 1;
        }
        switch (op) {
            case 132: {
                return 3;
            }
            case 133: {
                this.stackTypes[this.stackTop - 1] = LONG;
                this.stackTypes[this.stackTop] = TOP;
                ++this.stackTop;
                break;
            }
            case 134: {
                this.stackTypes[this.stackTop - 1] = FLOAT;
                break;
            }
            case 135: {
                this.stackTypes[this.stackTop - 1] = DOUBLE;
                this.stackTypes[this.stackTop] = TOP;
                ++this.stackTop;
                break;
            }
            case 136: {
                this.stackTypes[--this.stackTop - 1] = INTEGER;
                break;
            }
            case 137: {
                this.stackTypes[--this.stackTop - 1] = FLOAT;
                break;
            }
            case 138: {
                this.stackTypes[this.stackTop - 2] = DOUBLE;
                break;
            }
            case 139: {
                this.stackTypes[this.stackTop - 1] = INTEGER;
                break;
            }
            case 140: {
                this.stackTypes[this.stackTop - 1] = LONG;
                this.stackTypes[this.stackTop] = TOP;
                ++this.stackTop;
                break;
            }
            case 141: {
                this.stackTypes[this.stackTop - 1] = DOUBLE;
                this.stackTypes[this.stackTop] = TOP;
                ++this.stackTop;
                break;
            }
            case 142: {
                this.stackTypes[--this.stackTop - 1] = INTEGER;
                break;
            }
            case 143: {
                this.stackTypes[this.stackTop - 2] = LONG;
                break;
            }
            case 144: {
                this.stackTypes[--this.stackTop - 1] = FLOAT;
                break;
            }
            case 145: 
            case 146: 
            case 147: {
                break;
            }
            default: {
                throw new RuntimeException("fatal");
            }
        }
        return 1;
    }

    private int doOpcode148_201(int pos, byte[] code, int op) throws BadBytecode {
        switch (op) {
            case 148: {
                this.stackTypes[this.stackTop - 4] = INTEGER;
                this.stackTop -= 3;
                break;
            }
            case 149: 
            case 150: {
                this.stackTypes[--this.stackTop - 1] = INTEGER;
                break;
            }
            case 151: 
            case 152: {
                this.stackTypes[this.stackTop - 4] = INTEGER;
                this.stackTop -= 3;
                break;
            }
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: {
                --this.stackTop;
                this.visitBranch(pos, code, ByteArray.readS16bit(code, pos + 1));
                return 3;
            }
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: {
                this.stackTop -= 2;
                this.visitBranch(pos, code, ByteArray.readS16bit(code, pos + 1));
                return 3;
            }
            case 167: {
                this.visitGoto(pos, code, ByteArray.readS16bit(code, pos + 1));
                return 3;
            }
            case 168: {
                this.visitJSR(pos, code);
                return 3;
            }
            case 169: {
                this.visitRET(pos, code);
                return 2;
            }
            case 170: {
                --this.stackTop;
                int pos2 = (pos & 0xFFFFFFFC) + 8;
                int low = ByteArray.read32bit(code, pos2);
                int high = ByteArray.read32bit(code, pos2 + 4);
                int n = high - low + 1;
                this.visitTableSwitch(pos, code, n, pos2 + 8, ByteArray.read32bit(code, pos2 - 4));
                return n * 4 + 16 - (pos & 3);
            }
            case 171: {
                --this.stackTop;
                int pos2 = (pos & 0xFFFFFFFC) + 8;
                int n = ByteArray.read32bit(code, pos2);
                this.visitLookupSwitch(pos, code, n, pos2 + 4, ByteArray.read32bit(code, pos2 - 4));
                return n * 8 + 12 - (pos & 3);
            }
            case 172: {
                --this.stackTop;
                this.visitReturn(pos, code);
                break;
            }
            case 173: {
                this.stackTop -= 2;
                this.visitReturn(pos, code);
                break;
            }
            case 174: {
                --this.stackTop;
                this.visitReturn(pos, code);
                break;
            }
            case 175: {
                this.stackTop -= 2;
                this.visitReturn(pos, code);
                break;
            }
            case 176: {
                this.stackTypes[--this.stackTop].setType(this.returnType, this.classPool);
                this.visitReturn(pos, code);
                break;
            }
            case 177: {
                this.visitReturn(pos, code);
                break;
            }
            case 178: {
                return this.doGetField(pos, code, false);
            }
            case 179: {
                return this.doPutField(pos, code, false);
            }
            case 180: {
                return this.doGetField(pos, code, true);
            }
            case 181: {
                return this.doPutField(pos, code, true);
            }
            case 182: 
            case 183: {
                return this.doInvokeMethod(pos, code, true);
            }
            case 184: {
                return this.doInvokeMethod(pos, code, false);
            }
            case 185: {
                return this.doInvokeIntfMethod(pos, code);
            }
            case 186: {
                return this.doInvokeDynamic(pos, code);
            }
            case 187: {
                int i = ByteArray.readU16bit(code, pos + 1);
                this.stackTypes[this.stackTop++] = new TypeData.UninitData(pos, this.cpool.getClassInfo(i));
                return 3;
            }
            case 188: {
                return this.doNEWARRAY(pos, code);
            }
            case 189: {
                int i = ByteArray.readU16bit(code, pos + 1);
                String type = this.cpool.getClassInfo(i).replace('.', '/');
                type = type.charAt(0) == '[' ? "[" + type : "[L" + type + ";";
                this.stackTypes[this.stackTop - 1] = new TypeData.ClassName(type);
                return 3;
            }
            case 190: {
                this.stackTypes[this.stackTop - 1].setType("[Ljava.lang.Object;", this.classPool);
                this.stackTypes[this.stackTop - 1] = INTEGER;
                break;
            }
            case 191: {
                this.stackTypes[--this.stackTop].setType("java.lang.Throwable", this.classPool);
                this.visitThrow(pos, code);
                break;
            }
            case 192: {
                int i = ByteArray.readU16bit(code, pos + 1);
                String type = this.cpool.getClassInfo(i);
                if (type.charAt(0) == '[') {
                    type = type.replace('.', '/');
                }
                this.stackTypes[this.stackTop - 1] = new TypeData.ClassName(type);
                return 3;
            }
            case 193: {
                this.stackTypes[this.stackTop - 1] = INTEGER;
                return 3;
            }
            case 194: 
            case 195: {
                --this.stackTop;
                break;
            }
            case 196: {
                return this.doWIDE(pos, code);
            }
            case 197: {
                return this.doMultiANewArray(pos, code);
            }
            case 198: 
            case 199: {
                --this.stackTop;
                this.visitBranch(pos, code, ByteArray.readS16bit(code, pos + 1));
                return 3;
            }
            case 200: {
                this.visitGoto(pos, code, ByteArray.read32bit(code, pos + 1));
                return 5;
            }
            case 201: {
                this.visitJSR(pos, code);
                return 5;
            }
        }
        return 1;
    }

    private int doWIDE(int pos, byte[] code) throws BadBytecode {
        int op = code[pos + 1] & 0xFF;
        switch (op) {
            case 21: {
                this.doWIDE_XLOAD(pos, code, INTEGER);
                break;
            }
            case 22: {
                this.doWIDE_XLOAD(pos, code, LONG);
                break;
            }
            case 23: {
                this.doWIDE_XLOAD(pos, code, FLOAT);
                break;
            }
            case 24: {
                this.doWIDE_XLOAD(pos, code, DOUBLE);
                break;
            }
            case 25: {
                int index = ByteArray.readU16bit(code, pos + 2);
                this.doALOAD(index);
                break;
            }
            case 54: {
                this.doWIDE_STORE(pos, code, INTEGER);
                break;
            }
            case 55: {
                this.doWIDE_STORE(pos, code, LONG);
                break;
            }
            case 56: {
                this.doWIDE_STORE(pos, code, FLOAT);
                break;
            }
            case 57: {
                this.doWIDE_STORE(pos, code, DOUBLE);
                break;
            }
            case 58: {
                int index = ByteArray.readU16bit(code, pos + 2);
                this.doASTORE(index);
                break;
            }
            case 132: {
                return 6;
            }
            case 169: {
                this.visitRET(pos, code);
                break;
            }
            default: {
                throw new RuntimeException("bad WIDE instruction: " + op);
            }
        }
        return 4;
    }

    private void doWIDE_XLOAD(int pos, byte[] code, TypeData type) {
        int index = ByteArray.readU16bit(code, pos + 2);
        this.doXLOAD(index, type);
    }

    private void doWIDE_STORE(int pos, byte[] code, TypeData type) {
        int index = ByteArray.readU16bit(code, pos + 2);
        this.doXSTORE(index, type);
    }

    private int doPutField(int pos, byte[] code, boolean notStatic) throws BadBytecode {
        int index = ByteArray.readU16bit(code, pos + 1);
        String desc = this.cpool.getFieldrefType(index);
        this.stackTop -= Descriptor.dataSize(desc);
        char c = desc.charAt(0);
        if (c == 'L') {
            this.stackTypes[this.stackTop].setType(Tracer.getFieldClassName(desc, 0), this.classPool);
        } else if (c == '[') {
            this.stackTypes[this.stackTop].setType(desc, this.classPool);
        }
        this.setFieldTarget(notStatic, index);
        return 3;
    }

    private int doGetField(int pos, byte[] code, boolean notStatic) throws BadBytecode {
        int index = ByteArray.readU16bit(code, pos + 1);
        this.setFieldTarget(notStatic, index);
        String desc = this.cpool.getFieldrefType(index);
        this.pushMemberType(desc);
        return 3;
    }

    private void setFieldTarget(boolean notStatic, int index) throws BadBytecode {
        if (notStatic) {
            String className = this.cpool.getFieldrefClassName(index);
            this.stackTypes[--this.stackTop].setType(className, this.classPool);
        }
    }

    private int doNEWARRAY(int pos, byte[] code) {
        String type;
        int s = this.stackTop - 1;
        switch (code[pos + 1] & 0xFF) {
            case 4: {
                type = "[Z";
                break;
            }
            case 5: {
                type = "[C";
                break;
            }
            case 6: {
                type = "[F";
                break;
            }
            case 7: {
                type = "[D";
                break;
            }
            case 8: {
                type = "[B";
                break;
            }
            case 9: {
                type = "[S";
                break;
            }
            case 10: {
                type = "[I";
                break;
            }
            case 11: {
                type = "[J";
                break;
            }
            default: {
                throw new RuntimeException("bad newarray");
            }
        }
        this.stackTypes[s] = new TypeData.ClassName(type);
        return 2;
    }

    private int doMultiANewArray(int pos, byte[] code) {
        int i = ByteArray.readU16bit(code, pos + 1);
        int dim = code[pos + 3] & 0xFF;
        this.stackTop -= dim - 1;
        String type = this.cpool.getClassInfo(i).replace('.', '/');
        this.stackTypes[this.stackTop - 1] = new TypeData.ClassName(type);
        return 4;
    }

    private int doInvokeMethod(int pos, byte[] code, boolean notStatic) throws BadBytecode {
        int i = ByteArray.readU16bit(code, pos + 1);
        String desc = this.cpool.getMethodrefType(i);
        this.checkParamTypes(desc, 1);
        if (notStatic) {
            TypeData target;
            String className = this.cpool.getMethodrefClassName(i);
            if ((target = this.stackTypes[--this.stackTop]) instanceof TypeData.UninitTypeVar && target.isUninit()) {
                this.constructorCalled(target, ((TypeData.UninitTypeVar)target).offset());
            } else if (target instanceof TypeData.UninitData) {
                this.constructorCalled(target, ((TypeData.UninitData)target).offset());
            }
            target.setType(className, this.classPool);
        }
        this.pushMemberType(desc);
        return 3;
    }

    private void constructorCalled(TypeData target, int offset) {
        int i;
        target.constructorCalled(offset);
        for (i = 0; i < this.stackTop; ++i) {
            this.stackTypes[i].constructorCalled(offset);
        }
        for (i = 0; i < this.localsTypes.length; ++i) {
            this.localsTypes[i].constructorCalled(offset);
        }
    }

    private int doInvokeIntfMethod(int pos, byte[] code) throws BadBytecode {
        int i = ByteArray.readU16bit(code, pos + 1);
        String desc = this.cpool.getInterfaceMethodrefType(i);
        this.checkParamTypes(desc, 1);
        String className = this.cpool.getInterfaceMethodrefClassName(i);
        this.stackTypes[--this.stackTop].setType(className, this.classPool);
        this.pushMemberType(desc);
        return 5;
    }

    private int doInvokeDynamic(int pos, byte[] code) throws BadBytecode {
        int i = ByteArray.readU16bit(code, pos + 1);
        String desc = this.cpool.getInvokeDynamicType(i);
        this.checkParamTypes(desc, 1);
        this.pushMemberType(desc);
        return 5;
    }

    private void pushMemberType(String descriptor) {
        int top = 0;
        if (descriptor.charAt(0) == '(' && (top = descriptor.indexOf(41) + 1) < 1) {
            throw new IndexOutOfBoundsException("bad descriptor: " + descriptor);
        }
        TypeData[] types = this.stackTypes;
        int index = this.stackTop;
        switch (descriptor.charAt(top)) {
            case '[': {
                types[index] = new TypeData.ClassName(descriptor.substring(top));
                break;
            }
            case 'L': {
                types[index] = new TypeData.ClassName(Tracer.getFieldClassName(descriptor, top));
                break;
            }
            case 'J': {
                types[index] = LONG;
                types[index + 1] = TOP;
                this.stackTop += 2;
                return;
            }
            case 'F': {
                types[index] = FLOAT;
                break;
            }
            case 'D': {
                types[index] = DOUBLE;
                types[index + 1] = TOP;
                this.stackTop += 2;
                return;
            }
            case 'V': {
                return;
            }
            default: {
                types[index] = INTEGER;
            }
        }
        ++this.stackTop;
    }

    private static String getFieldClassName(String desc, int index) {
        return desc.substring(index + 1, desc.length() - 1).replace('/', '.');
    }

    private void checkParamTypes(String desc, int i) throws BadBytecode {
        char c = desc.charAt(i);
        if (c == ')') {
            return;
        }
        int k = i;
        boolean array = false;
        while (c == '[') {
            array = true;
            c = desc.charAt(++k);
        }
        if (c == 'L') {
            if ((k = desc.indexOf(59, k) + 1) <= 0) {
                throw new IndexOutOfBoundsException("bad descriptor");
            }
        } else {
            ++k;
        }
        this.checkParamTypes(desc, k);
        this.stackTop = !(array || c != 'J' && c != 'D') ? (this.stackTop -= 2) : --this.stackTop;
        if (array) {
            this.stackTypes[this.stackTop].setType(desc.substring(i, k), this.classPool);
        } else if (c == 'L') {
            this.stackTypes[this.stackTop].setType(desc.substring(i + 1, k - 1).replace('/', '.'), this.classPool);
        }
    }
}

