/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.analysis;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import javassist.bytecode.analysis.Frame;
import javassist.bytecode.analysis.Subroutine;
import javassist.bytecode.analysis.Type;

public class Executor
implements Opcode {
    private final ConstPool constPool;
    private final ClassPool classPool;
    private final Type STRING_TYPE;
    private final Type CLASS_TYPE;
    private final Type THROWABLE_TYPE;
    private int lastPos;

    public Executor(ClassPool classPool, ConstPool constPool) {
        this.constPool = constPool;
        this.classPool = classPool;
        try {
            this.STRING_TYPE = this.getType("java.lang.String");
            this.CLASS_TYPE = this.getType("java.lang.Class");
            this.THROWABLE_TYPE = this.getType("java.lang.Throwable");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(MethodInfo method, int pos, CodeIterator iter, Frame frame, Subroutine subroutine) throws BadBytecode {
        this.lastPos = pos;
        int opcode = iter.byteAt(pos);
        switch (opcode) {
            case 0: {
                break;
            }
            case 1: {
                frame.push(Type.UNINIT);
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                frame.push(Type.INTEGER);
                break;
            }
            case 9: 
            case 10: {
                frame.push(Type.LONG);
                frame.push(Type.TOP);
                break;
            }
            case 11: 
            case 12: 
            case 13: {
                frame.push(Type.FLOAT);
                break;
            }
            case 14: 
            case 15: {
                frame.push(Type.DOUBLE);
                frame.push(Type.TOP);
                break;
            }
            case 16: 
            case 17: {
                frame.push(Type.INTEGER);
                break;
            }
            case 18: {
                this.evalLDC(iter.byteAt(pos + 1), frame);
                break;
            }
            case 19: 
            case 20: {
                this.evalLDC(iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 21: {
                this.evalLoad(Type.INTEGER, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 22: {
                this.evalLoad(Type.LONG, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 23: {
                this.evalLoad(Type.FLOAT, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 24: {
                this.evalLoad(Type.DOUBLE, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 25: {
                this.evalLoad(Type.OBJECT, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 26: 
            case 27: 
            case 28: 
            case 29: {
                this.evalLoad(Type.INTEGER, opcode - 26, frame, subroutine);
                break;
            }
            case 30: 
            case 31: 
            case 32: 
            case 33: {
                this.evalLoad(Type.LONG, opcode - 30, frame, subroutine);
                break;
            }
            case 34: 
            case 35: 
            case 36: 
            case 37: {
                this.evalLoad(Type.FLOAT, opcode - 34, frame, subroutine);
                break;
            }
            case 38: 
            case 39: 
            case 40: 
            case 41: {
                this.evalLoad(Type.DOUBLE, opcode - 38, frame, subroutine);
                break;
            }
            case 42: 
            case 43: 
            case 44: 
            case 45: {
                this.evalLoad(Type.OBJECT, opcode - 42, frame, subroutine);
                break;
            }
            case 46: {
                this.evalArrayLoad(Type.INTEGER, frame);
                break;
            }
            case 47: {
                this.evalArrayLoad(Type.LONG, frame);
                break;
            }
            case 48: {
                this.evalArrayLoad(Type.FLOAT, frame);
                break;
            }
            case 49: {
                this.evalArrayLoad(Type.DOUBLE, frame);
                break;
            }
            case 50: {
                this.evalArrayLoad(Type.OBJECT, frame);
                break;
            }
            case 51: 
            case 52: 
            case 53: {
                this.evalArrayLoad(Type.INTEGER, frame);
                break;
            }
            case 54: {
                this.evalStore(Type.INTEGER, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 55: {
                this.evalStore(Type.LONG, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 56: {
                this.evalStore(Type.FLOAT, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 57: {
                this.evalStore(Type.DOUBLE, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 58: {
                this.evalStore(Type.OBJECT, iter.byteAt(pos + 1), frame, subroutine);
                break;
            }
            case 59: 
            case 60: 
            case 61: 
            case 62: {
                this.evalStore(Type.INTEGER, opcode - 59, frame, subroutine);
                break;
            }
            case 63: 
            case 64: 
            case 65: 
            case 66: {
                this.evalStore(Type.LONG, opcode - 63, frame, subroutine);
                break;
            }
            case 67: 
            case 68: 
            case 69: 
            case 70: {
                this.evalStore(Type.FLOAT, opcode - 67, frame, subroutine);
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: {
                this.evalStore(Type.DOUBLE, opcode - 71, frame, subroutine);
                break;
            }
            case 75: 
            case 76: 
            case 77: 
            case 78: {
                this.evalStore(Type.OBJECT, opcode - 75, frame, subroutine);
                break;
            }
            case 79: {
                this.evalArrayStore(Type.INTEGER, frame);
                break;
            }
            case 80: {
                this.evalArrayStore(Type.LONG, frame);
                break;
            }
            case 81: {
                this.evalArrayStore(Type.FLOAT, frame);
                break;
            }
            case 82: {
                this.evalArrayStore(Type.DOUBLE, frame);
                break;
            }
            case 83: {
                this.evalArrayStore(Type.OBJECT, frame);
                break;
            }
            case 84: 
            case 85: 
            case 86: {
                this.evalArrayStore(Type.INTEGER, frame);
                break;
            }
            case 87: {
                if (frame.pop() != Type.TOP) break;
                throw new BadBytecode("POP can not be used with a category 2 value, pos = " + pos);
            }
            case 88: {
                frame.pop();
                frame.pop();
                break;
            }
            case 89: {
                Type type = frame.peek();
                if (type == Type.TOP) {
                    throw new BadBytecode("DUP can not be used with a category 2 value, pos = " + pos);
                }
                frame.push(frame.peek());
                break;
            }
            case 90: 
            case 91: {
                int end;
                Type type = frame.peek();
                if (type == Type.TOP) {
                    throw new BadBytecode("DUP can not be used with a category 2 value, pos = " + pos);
                }
                int insert = end - (opcode - 90) - 1;
                frame.push(type);
                for (end = frame.getTopIndex(); end > insert; --end) {
                    frame.setStack(end, frame.getStack(end - 1));
                }
                frame.setStack(insert, type);
                break;
            }
            case 92: {
                frame.push(frame.getStack(frame.getTopIndex() - 1));
                frame.push(frame.getStack(frame.getTopIndex() - 1));
                break;
            }
            case 93: 
            case 94: {
                int end;
                int insert = end - (opcode - 93) - 1;
                Type type1 = frame.getStack(frame.getTopIndex() - 1);
                Type type2 = frame.peek();
                frame.push(type1);
                frame.push(type2);
                for (end = frame.getTopIndex(); end > insert; --end) {
                    frame.setStack(end, frame.getStack(end - 2));
                }
                frame.setStack(insert, type2);
                frame.setStack(insert - 1, type1);
                break;
            }
            case 95: {
                Type type1 = frame.pop();
                Type type2 = frame.pop();
                if (type1.getSize() == 2 || type2.getSize() == 2) {
                    throw new BadBytecode("Swap can not be used with category 2 values, pos = " + pos);
                }
                frame.push(type1);
                frame.push(type2);
                break;
            }
            case 96: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 97: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 98: {
                this.evalBinaryMath(Type.FLOAT, frame);
                break;
            }
            case 99: {
                this.evalBinaryMath(Type.DOUBLE, frame);
                break;
            }
            case 100: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 101: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 102: {
                this.evalBinaryMath(Type.FLOAT, frame);
                break;
            }
            case 103: {
                this.evalBinaryMath(Type.DOUBLE, frame);
                break;
            }
            case 104: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 105: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 106: {
                this.evalBinaryMath(Type.FLOAT, frame);
                break;
            }
            case 107: {
                this.evalBinaryMath(Type.DOUBLE, frame);
                break;
            }
            case 108: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 109: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 110: {
                this.evalBinaryMath(Type.FLOAT, frame);
                break;
            }
            case 111: {
                this.evalBinaryMath(Type.DOUBLE, frame);
                break;
            }
            case 112: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 113: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 114: {
                this.evalBinaryMath(Type.FLOAT, frame);
                break;
            }
            case 115: {
                this.evalBinaryMath(Type.DOUBLE, frame);
                break;
            }
            case 116: {
                this.verifyAssignable(Type.INTEGER, this.simplePeek(frame));
                break;
            }
            case 117: {
                this.verifyAssignable(Type.LONG, this.simplePeek(frame));
                break;
            }
            case 118: {
                this.verifyAssignable(Type.FLOAT, this.simplePeek(frame));
                break;
            }
            case 119: {
                this.verifyAssignable(Type.DOUBLE, this.simplePeek(frame));
                break;
            }
            case 120: {
                this.evalShift(Type.INTEGER, frame);
                break;
            }
            case 121: {
                this.evalShift(Type.LONG, frame);
                break;
            }
            case 122: {
                this.evalShift(Type.INTEGER, frame);
                break;
            }
            case 123: {
                this.evalShift(Type.LONG, frame);
                break;
            }
            case 124: {
                this.evalShift(Type.INTEGER, frame);
                break;
            }
            case 125: {
                this.evalShift(Type.LONG, frame);
                break;
            }
            case 126: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 127: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 128: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 129: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 130: {
                this.evalBinaryMath(Type.INTEGER, frame);
                break;
            }
            case 131: {
                this.evalBinaryMath(Type.LONG, frame);
                break;
            }
            case 132: {
                int index = iter.byteAt(pos + 1);
                this.verifyAssignable(Type.INTEGER, frame.getLocal(index));
                this.access(index, Type.INTEGER, subroutine);
                break;
            }
            case 133: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                this.simplePush(Type.LONG, frame);
                break;
            }
            case 134: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                this.simplePush(Type.FLOAT, frame);
                break;
            }
            case 135: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                this.simplePush(Type.DOUBLE, frame);
                break;
            }
            case 136: {
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                this.simplePush(Type.INTEGER, frame);
                break;
            }
            case 137: {
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                this.simplePush(Type.FLOAT, frame);
                break;
            }
            case 138: {
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                this.simplePush(Type.DOUBLE, frame);
                break;
            }
            case 139: {
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                this.simplePush(Type.INTEGER, frame);
                break;
            }
            case 140: {
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                this.simplePush(Type.LONG, frame);
                break;
            }
            case 141: {
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                this.simplePush(Type.DOUBLE, frame);
                break;
            }
            case 142: {
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                this.simplePush(Type.INTEGER, frame);
                break;
            }
            case 143: {
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                this.simplePush(Type.LONG, frame);
                break;
            }
            case 144: {
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                this.simplePush(Type.FLOAT, frame);
                break;
            }
            case 145: 
            case 146: 
            case 147: {
                this.verifyAssignable(Type.INTEGER, frame.peek());
                break;
            }
            case 148: {
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                frame.push(Type.INTEGER);
                break;
            }
            case 149: 
            case 150: {
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                frame.push(Type.INTEGER);
                break;
            }
            case 151: 
            case 152: {
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                frame.push(Type.INTEGER);
                break;
            }
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                break;
            }
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                break;
            }
            case 165: 
            case 166: {
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                break;
            }
            case 167: {
                break;
            }
            case 168: {
                frame.push(Type.RETURN_ADDRESS);
                break;
            }
            case 169: {
                this.verifyAssignable(Type.RETURN_ADDRESS, frame.getLocal(iter.byteAt(pos + 1)));
                break;
            }
            case 170: 
            case 171: 
            case 172: {
                this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
                break;
            }
            case 173: {
                this.verifyAssignable(Type.LONG, this.simplePop(frame));
                break;
            }
            case 174: {
                this.verifyAssignable(Type.FLOAT, this.simplePop(frame));
                break;
            }
            case 175: {
                this.verifyAssignable(Type.DOUBLE, this.simplePop(frame));
                break;
            }
            case 176: {
                try {
                    CtClass returnType = Descriptor.getReturnType(method.getDescriptor(), this.classPool);
                    this.verifyAssignable(Type.get(returnType), this.simplePop(frame));
                    break;
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            case 177: {
                break;
            }
            case 178: {
                this.evalGetField(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 179: {
                this.evalPutField(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 180: {
                this.evalGetField(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 181: {
                this.evalPutField(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 182: 
            case 183: 
            case 184: {
                this.evalInvokeMethod(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 185: {
                this.evalInvokeIntfMethod(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 186: {
                this.evalInvokeDynamic(opcode, iter.u16bitAt(pos + 1), frame);
                break;
            }
            case 187: {
                frame.push(this.resolveClassInfo(this.constPool.getClassInfo(iter.u16bitAt(pos + 1))));
                break;
            }
            case 188: {
                this.evalNewArray(pos, iter, frame);
                break;
            }
            case 189: {
                this.evalNewObjectArray(pos, iter, frame);
                break;
            }
            case 190: {
                Type array = this.simplePop(frame);
                if (!array.isArray() && array != Type.UNINIT) {
                    throw new BadBytecode("Array length passed a non-array [pos = " + pos + "]: " + array);
                }
                frame.push(Type.INTEGER);
                break;
            }
            case 191: {
                this.verifyAssignable(this.THROWABLE_TYPE, this.simplePop(frame));
                break;
            }
            case 192: {
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                frame.push(this.typeFromDesc(this.constPool.getClassInfoByDescriptor(iter.u16bitAt(pos + 1))));
                break;
            }
            case 193: {
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                frame.push(Type.INTEGER);
                break;
            }
            case 194: 
            case 195: {
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                break;
            }
            case 196: {
                this.evalWide(pos, iter, frame, subroutine);
                break;
            }
            case 197: {
                this.evalNewObjectArray(pos, iter, frame);
                break;
            }
            case 198: 
            case 199: {
                this.verifyAssignable(Type.OBJECT, this.simplePop(frame));
                break;
            }
            case 200: {
                break;
            }
            case 201: {
                frame.push(Type.RETURN_ADDRESS);
            }
        }
    }

    private Type zeroExtend(Type type) {
        if (type == Type.SHORT || type == Type.BYTE || type == Type.CHAR || type == Type.BOOLEAN) {
            return Type.INTEGER;
        }
        return type;
    }

    private void evalArrayLoad(Type expectedComponent, Frame frame) throws BadBytecode {
        Type index = frame.pop();
        Type array = frame.pop();
        if (array == Type.UNINIT) {
            this.verifyAssignable(Type.INTEGER, index);
            if (expectedComponent == Type.OBJECT) {
                this.simplePush(Type.UNINIT, frame);
            } else {
                this.simplePush(expectedComponent, frame);
            }
            return;
        }
        Type component = array.getComponent();
        if (component == null) {
            throw new BadBytecode("Not an array! [pos = " + this.lastPos + "]: " + component);
        }
        component = this.zeroExtend(component);
        this.verifyAssignable(expectedComponent, component);
        this.verifyAssignable(Type.INTEGER, index);
        this.simplePush(component, frame);
    }

    private void evalArrayStore(Type expectedComponent, Frame frame) throws BadBytecode {
        Type value = this.simplePop(frame);
        Type index = frame.pop();
        Type array = frame.pop();
        if (array == Type.UNINIT) {
            this.verifyAssignable(Type.INTEGER, index);
            return;
        }
        Type component = array.getComponent();
        if (component == null) {
            throw new BadBytecode("Not an array! [pos = " + this.lastPos + "]: " + component);
        }
        component = this.zeroExtend(component);
        this.verifyAssignable(expectedComponent, component);
        this.verifyAssignable(Type.INTEGER, index);
        if (expectedComponent == Type.OBJECT) {
            this.verifyAssignable(expectedComponent, value);
        } else {
            this.verifyAssignable(component, value);
        }
    }

    private void evalBinaryMath(Type expected, Frame frame) throws BadBytecode {
        Type value2 = this.simplePop(frame);
        Type value1 = this.simplePop(frame);
        this.verifyAssignable(expected, value2);
        this.verifyAssignable(expected, value1);
        this.simplePush(value1, frame);
    }

    private void evalGetField(int opcode, int index, Frame frame) throws BadBytecode {
        String desc = this.constPool.getFieldrefType(index);
        Type type = this.zeroExtend(this.typeFromDesc(desc));
        if (opcode == 180) {
            Type objectType = this.resolveClassInfo(this.constPool.getFieldrefClassName(index));
            this.verifyAssignable(objectType, this.simplePop(frame));
        }
        this.simplePush(type, frame);
    }

    private void evalInvokeIntfMethod(int opcode, int index, Frame frame) throws BadBytecode {
        String desc = this.constPool.getInterfaceMethodrefType(index);
        Type[] types = this.paramTypesFromDesc(desc);
        int i = types.length;
        while (i > 0) {
            this.verifyAssignable(this.zeroExtend(types[--i]), this.simplePop(frame));
        }
        String classInfo = this.constPool.getInterfaceMethodrefClassName(index);
        Type objectType = this.resolveClassInfo(classInfo);
        this.verifyAssignable(objectType, this.simplePop(frame));
        Type returnType = this.returnTypeFromDesc(desc);
        if (returnType != Type.VOID) {
            this.simplePush(this.zeroExtend(returnType), frame);
        }
    }

    private void evalInvokeMethod(int opcode, int index, Frame frame) throws BadBytecode {
        Type returnType;
        String desc = this.constPool.getMethodrefType(index);
        Type[] types = this.paramTypesFromDesc(desc);
        int i = types.length;
        while (i > 0) {
            this.verifyAssignable(this.zeroExtend(types[--i]), this.simplePop(frame));
        }
        if (opcode != 184) {
            Type objectType = this.resolveClassInfo(this.constPool.getMethodrefClassName(index));
            this.verifyAssignable(objectType, this.simplePop(frame));
        }
        if ((returnType = this.returnTypeFromDesc(desc)) != Type.VOID) {
            this.simplePush(this.zeroExtend(returnType), frame);
        }
    }

    private void evalInvokeDynamic(int opcode, int index, Frame frame) throws BadBytecode {
        String desc = this.constPool.getInvokeDynamicType(index);
        Type[] types = this.paramTypesFromDesc(desc);
        int i = types.length;
        while (i > 0) {
            this.verifyAssignable(this.zeroExtend(types[--i]), this.simplePop(frame));
        }
        Type returnType = this.returnTypeFromDesc(desc);
        if (returnType != Type.VOID) {
            this.simplePush(this.zeroExtend(returnType), frame);
        }
    }

    private void evalLDC(int index, Frame frame) throws BadBytecode {
        Type type;
        int tag = this.constPool.getTag(index);
        switch (tag) {
            case 8: {
                type = this.STRING_TYPE;
                break;
            }
            case 3: {
                type = Type.INTEGER;
                break;
            }
            case 4: {
                type = Type.FLOAT;
                break;
            }
            case 5: {
                type = Type.LONG;
                break;
            }
            case 6: {
                type = Type.DOUBLE;
                break;
            }
            case 7: {
                type = this.CLASS_TYPE;
                break;
            }
            default: {
                throw new BadBytecode("bad LDC [pos = " + this.lastPos + "]: " + tag);
            }
        }
        this.simplePush(type, frame);
    }

    private void evalLoad(Type expected, int index, Frame frame, Subroutine subroutine) throws BadBytecode {
        Type type = frame.getLocal(index);
        this.verifyAssignable(expected, type);
        this.simplePush(type, frame);
        this.access(index, type, subroutine);
    }

    private void evalNewArray(int pos, CodeIterator iter, Frame frame) throws BadBytecode {
        this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
        Type type = null;
        int typeInfo = iter.byteAt(pos + 1);
        switch (typeInfo) {
            case 4: {
                type = this.getType("boolean[]");
                break;
            }
            case 5: {
                type = this.getType("char[]");
                break;
            }
            case 8: {
                type = this.getType("byte[]");
                break;
            }
            case 9: {
                type = this.getType("short[]");
                break;
            }
            case 10: {
                type = this.getType("int[]");
                break;
            }
            case 11: {
                type = this.getType("long[]");
                break;
            }
            case 6: {
                type = this.getType("float[]");
                break;
            }
            case 7: {
                type = this.getType("double[]");
                break;
            }
            default: {
                throw new BadBytecode("Invalid array type [pos = " + pos + "]: " + typeInfo);
            }
        }
        frame.push(type);
    }

    private void evalNewObjectArray(int pos, CodeIterator iter, Frame frame) throws BadBytecode {
        int dimensions;
        Type type = this.resolveClassInfo(this.constPool.getClassInfo(iter.u16bitAt(pos + 1)));
        String name = type.getCtClass().getName();
        int opcode = iter.byteAt(pos);
        if (opcode == 197) {
            dimensions = iter.byteAt(pos + 3);
        } else {
            name = name + "[]";
            dimensions = 1;
        }
        while (dimensions-- > 0) {
            this.verifyAssignable(Type.INTEGER, this.simplePop(frame));
        }
        this.simplePush(this.getType(name), frame);
    }

    private void evalPutField(int opcode, int index, Frame frame) throws BadBytecode {
        String desc = this.constPool.getFieldrefType(index);
        Type type = this.zeroExtend(this.typeFromDesc(desc));
        this.verifyAssignable(type, this.simplePop(frame));
        if (opcode == 181) {
            Type objectType = this.resolveClassInfo(this.constPool.getFieldrefClassName(index));
            this.verifyAssignable(objectType, this.simplePop(frame));
        }
    }

    private void evalShift(Type expected, Frame frame) throws BadBytecode {
        Type value2 = this.simplePop(frame);
        Type value1 = this.simplePop(frame);
        this.verifyAssignable(Type.INTEGER, value2);
        this.verifyAssignable(expected, value1);
        this.simplePush(value1, frame);
    }

    private void evalStore(Type expected, int index, Frame frame, Subroutine subroutine) throws BadBytecode {
        Type type = this.simplePop(frame);
        if (expected != Type.OBJECT || type != Type.RETURN_ADDRESS) {
            this.verifyAssignable(expected, type);
        }
        this.simpleSetLocal(index, type, frame);
        this.access(index, type, subroutine);
    }

    private void evalWide(int pos, CodeIterator iter, Frame frame, Subroutine subroutine) throws BadBytecode {
        int opcode = iter.byteAt(pos + 1);
        int index = iter.u16bitAt(pos + 2);
        switch (opcode) {
            case 21: {
                this.evalLoad(Type.INTEGER, index, frame, subroutine);
                break;
            }
            case 22: {
                this.evalLoad(Type.LONG, index, frame, subroutine);
                break;
            }
            case 23: {
                this.evalLoad(Type.FLOAT, index, frame, subroutine);
                break;
            }
            case 24: {
                this.evalLoad(Type.DOUBLE, index, frame, subroutine);
                break;
            }
            case 25: {
                this.evalLoad(Type.OBJECT, index, frame, subroutine);
                break;
            }
            case 54: {
                this.evalStore(Type.INTEGER, index, frame, subroutine);
                break;
            }
            case 55: {
                this.evalStore(Type.LONG, index, frame, subroutine);
                break;
            }
            case 56: {
                this.evalStore(Type.FLOAT, index, frame, subroutine);
                break;
            }
            case 57: {
                this.evalStore(Type.DOUBLE, index, frame, subroutine);
                break;
            }
            case 58: {
                this.evalStore(Type.OBJECT, index, frame, subroutine);
                break;
            }
            case 132: {
                this.verifyAssignable(Type.INTEGER, frame.getLocal(index));
                break;
            }
            case 169: {
                this.verifyAssignable(Type.RETURN_ADDRESS, frame.getLocal(index));
                break;
            }
            default: {
                throw new BadBytecode("Invalid WIDE operand [pos = " + pos + "]: " + opcode);
            }
        }
    }

    private Type getType(String name) throws BadBytecode {
        try {
            return Type.get(this.classPool.get(name));
        } catch (NotFoundException e) {
            throw new BadBytecode("Could not find class [pos = " + this.lastPos + "]: " + name);
        }
    }

    private Type[] paramTypesFromDesc(String desc) throws BadBytecode {
        CtClass[] classes = null;
        try {
            classes = Descriptor.getParameterTypes(desc, this.classPool);
        } catch (NotFoundException e) {
            throw new BadBytecode("Could not find class in descriptor [pos = " + this.lastPos + "]: " + e.getMessage());
        }
        if (classes == null) {
            throw new BadBytecode("Could not obtain parameters for descriptor [pos = " + this.lastPos + "]: " + desc);
        }
        Type[] types = new Type[classes.length];
        for (int i = 0; i < types.length; ++i) {
            types[i] = Type.get(classes[i]);
        }
        return types;
    }

    private Type returnTypeFromDesc(String desc) throws BadBytecode {
        CtClass clazz = null;
        try {
            clazz = Descriptor.getReturnType(desc, this.classPool);
        } catch (NotFoundException e) {
            throw new BadBytecode("Could not find class in descriptor [pos = " + this.lastPos + "]: " + e.getMessage());
        }
        if (clazz == null) {
            throw new BadBytecode("Could not obtain return type for descriptor [pos = " + this.lastPos + "]: " + desc);
        }
        return Type.get(clazz);
    }

    private Type simplePeek(Frame frame) {
        Type type = frame.peek();
        return type == Type.TOP ? frame.getStack(frame.getTopIndex() - 1) : type;
    }

    private Type simplePop(Frame frame) {
        Type type = frame.pop();
        return type == Type.TOP ? frame.pop() : type;
    }

    private void simplePush(Type type, Frame frame) {
        frame.push(type);
        if (type.getSize() == 2) {
            frame.push(Type.TOP);
        }
    }

    private void access(int index, Type type, Subroutine subroutine) {
        if (subroutine == null) {
            return;
        }
        subroutine.access(index);
        if (type.getSize() == 2) {
            subroutine.access(index + 1);
        }
    }

    private void simpleSetLocal(int index, Type type, Frame frame) {
        frame.setLocal(index, type);
        if (type.getSize() == 2) {
            frame.setLocal(index + 1, Type.TOP);
        }
    }

    private Type resolveClassInfo(String info) throws BadBytecode {
        CtClass clazz = null;
        try {
            clazz = info.charAt(0) == '[' ? Descriptor.toCtClass(info, this.classPool) : this.classPool.get(info);
        } catch (NotFoundException e) {
            throw new BadBytecode("Could not find class in descriptor [pos = " + this.lastPos + "]: " + e.getMessage());
        }
        if (clazz == null) {
            throw new BadBytecode("Could not obtain type for descriptor [pos = " + this.lastPos + "]: " + info);
        }
        return Type.get(clazz);
    }

    private Type typeFromDesc(String desc) throws BadBytecode {
        CtClass clazz = null;
        try {
            clazz = Descriptor.toCtClass(desc, this.classPool);
        } catch (NotFoundException e) {
            throw new BadBytecode("Could not find class in descriptor [pos = " + this.lastPos + "]: " + e.getMessage());
        }
        if (clazz == null) {
            throw new BadBytecode("Could not obtain type for descriptor [pos = " + this.lastPos + "]: " + desc);
        }
        return Type.get(clazz);
    }

    private void verifyAssignable(Type expected, Type type) throws BadBytecode {
        if (!expected.isAssignableFrom(type)) {
            throw new BadBytecode("Expected type: " + expected + " Got: " + type + " [pos = " + this.lastPos + "]");
        }
    }
}

