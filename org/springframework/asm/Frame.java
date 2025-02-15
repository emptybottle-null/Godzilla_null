/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.ByteVector;
import org.springframework.asm.Label;
import org.springframework.asm.MethodWriter;
import org.springframework.asm.Opcodes;
import org.springframework.asm.Symbol;
import org.springframework.asm.SymbolTable;
import org.springframework.asm.Type;

class Frame {
    static final int SAME_FRAME = 0;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int RESERVED = 128;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    static final int CHOP_FRAME = 248;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int APPEND_FRAME = 252;
    static final int FULL_FRAME = 255;
    static final int ITEM_TOP = 0;
    static final int ITEM_INTEGER = 1;
    static final int ITEM_FLOAT = 2;
    static final int ITEM_DOUBLE = 3;
    static final int ITEM_LONG = 4;
    static final int ITEM_NULL = 5;
    static final int ITEM_UNINITIALIZED_THIS = 6;
    static final int ITEM_OBJECT = 7;
    static final int ITEM_UNINITIALIZED = 8;
    private static final int ITEM_ASM_BOOLEAN = 9;
    private static final int ITEM_ASM_BYTE = 10;
    private static final int ITEM_ASM_CHAR = 11;
    private static final int ITEM_ASM_SHORT = 12;
    private static final int DIM_SIZE = 6;
    private static final int KIND_SIZE = 4;
    private static final int FLAGS_SIZE = 2;
    private static final int VALUE_SIZE = 20;
    private static final int DIM_SHIFT = 26;
    private static final int KIND_SHIFT = 22;
    private static final int FLAGS_SHIFT = 20;
    private static final int DIM_MASK = -67108864;
    private static final int KIND_MASK = 0x3C00000;
    private static final int VALUE_MASK = 1048575;
    private static final int ARRAY_OF = 0x4000000;
    private static final int ELEMENT_OF = -67108864;
    private static final int CONSTANT_KIND = 0x400000;
    private static final int REFERENCE_KIND = 0x800000;
    private static final int UNINITIALIZED_KIND = 0xC00000;
    private static final int LOCAL_KIND = 0x1000000;
    private static final int STACK_KIND = 0x1400000;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 0x100000;
    private static final int TOP = 0x400000;
    private static final int BOOLEAN = 0x400009;
    private static final int BYTE = 0x40000A;
    private static final int CHAR = 0x40000B;
    private static final int SHORT = 0x40000C;
    private static final int INTEGER = 0x400001;
    private static final int FLOAT = 0x400002;
    private static final int LONG = 0x400004;
    private static final int DOUBLE = 0x400003;
    private static final int NULL = 0x400005;
    private static final int UNINITIALIZED_THIS = 0x400006;
    Label owner;
    private int[] inputLocals;
    private int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    private int initializationCount;
    private int[] initializations;

    Frame(Label owner) {
        this.owner = owner;
    }

    final void copyFrom(Frame frame) {
        this.inputLocals = frame.inputLocals;
        this.inputStack = frame.inputStack;
        this.outputStackStart = 0;
        this.outputLocals = frame.outputLocals;
        this.outputStack = frame.outputStack;
        this.outputStackTop = frame.outputStackTop;
        this.initializationCount = frame.initializationCount;
        this.initializations = frame.initializations;
    }

    static int getAbstractTypeFromApiFormat(SymbolTable symbolTable, Object type) {
        if (type instanceof Integer) {
            return 0x400000 | (Integer)type;
        }
        if (type instanceof String) {
            String descriptor = Type.getObjectType((String)type).getDescriptor();
            return Frame.getAbstractTypeFromDescriptor(symbolTable, descriptor, 0);
        }
        return 0xC00000 | symbolTable.addUninitializedType("", ((Label)type).bytecodeOffset);
    }

    static int getAbstractTypeFromInternalName(SymbolTable symbolTable, String internalName) {
        return 0x800000 | symbolTable.addType(internalName);
    }

    private static int getAbstractTypeFromDescriptor(SymbolTable symbolTable, String buffer, int offset) {
        switch (buffer.charAt(offset)) {
            case 'V': {
                return 0;
            }
            case 'B': 
            case 'C': 
            case 'I': 
            case 'S': 
            case 'Z': {
                return 0x400001;
            }
            case 'F': {
                return 0x400002;
            }
            case 'J': {
                return 0x400004;
            }
            case 'D': {
                return 0x400003;
            }
            case 'L': {
                String internalName = buffer.substring(offset + 1, buffer.length() - 1);
                return 0x800000 | symbolTable.addType(internalName);
            }
            case '[': {
                int typeValue;
                int elementDescriptorOffset = offset + 1;
                while (buffer.charAt(elementDescriptorOffset) == '[') {
                    ++elementDescriptorOffset;
                }
                switch (buffer.charAt(elementDescriptorOffset)) {
                    case 'Z': {
                        typeValue = 0x400009;
                        break;
                    }
                    case 'C': {
                        typeValue = 0x40000B;
                        break;
                    }
                    case 'B': {
                        typeValue = 0x40000A;
                        break;
                    }
                    case 'S': {
                        typeValue = 0x40000C;
                        break;
                    }
                    case 'I': {
                        typeValue = 0x400001;
                        break;
                    }
                    case 'F': {
                        typeValue = 0x400002;
                        break;
                    }
                    case 'J': {
                        typeValue = 0x400004;
                        break;
                    }
                    case 'D': {
                        typeValue = 0x400003;
                        break;
                    }
                    case 'L': {
                        String internalName = buffer.substring(elementDescriptorOffset + 1, buffer.length() - 1);
                        typeValue = 0x800000 | symbolTable.addType(internalName);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException();
                    }
                }
                return elementDescriptorOffset - offset << 26 | typeValue;
            }
        }
        throw new IllegalArgumentException();
    }

    final void setInputFrameFromDescriptor(SymbolTable symbolTable, int access, String descriptor, int maxLocals) {
        this.inputLocals = new int[maxLocals];
        this.inputStack = new int[0];
        int inputLocalIndex = 0;
        if ((access & 8) == 0) {
            this.inputLocals[inputLocalIndex++] = (access & 0x40000) == 0 ? 0x800000 | symbolTable.addType(symbolTable.getClassName()) : 0x400006;
        }
        for (Type argumentType : Type.getArgumentTypes(descriptor)) {
            int abstractType = Frame.getAbstractTypeFromDescriptor(symbolTable, argumentType.getDescriptor(), 0);
            this.inputLocals[inputLocalIndex++] = abstractType;
            if (abstractType != 0x400004 && abstractType != 0x400003) continue;
            this.inputLocals[inputLocalIndex++] = 0x400000;
        }
        while (inputLocalIndex < maxLocals) {
            this.inputLocals[inputLocalIndex++] = 0x400000;
        }
    }

    final void setInputFrameFromApiFormat(SymbolTable symbolTable, int numLocal, Object[] local, int numStack, Object[] stack) {
        int inputLocalIndex = 0;
        for (int i = 0; i < numLocal; ++i) {
            this.inputLocals[inputLocalIndex++] = Frame.getAbstractTypeFromApiFormat(symbolTable, local[i]);
            if (local[i] != Opcodes.LONG && local[i] != Opcodes.DOUBLE) continue;
            this.inputLocals[inputLocalIndex++] = 0x400000;
        }
        while (inputLocalIndex < this.inputLocals.length) {
            this.inputLocals[inputLocalIndex++] = 0x400000;
        }
        int numStackTop = 0;
        for (int i = 0; i < numStack; ++i) {
            if (stack[i] != Opcodes.LONG && stack[i] != Opcodes.DOUBLE) continue;
            ++numStackTop;
        }
        this.inputStack = new int[numStack + numStackTop];
        int inputStackIndex = 0;
        for (int i = 0; i < numStack; ++i) {
            this.inputStack[inputStackIndex++] = Frame.getAbstractTypeFromApiFormat(symbolTable, stack[i]);
            if (stack[i] != Opcodes.LONG && stack[i] != Opcodes.DOUBLE) continue;
            this.inputStack[inputStackIndex++] = 0x400000;
        }
        this.outputStackTop = 0;
        this.initializationCount = 0;
    }

    final int getInputStackSize() {
        return this.inputStack.length;
    }

    private int getLocal(int localIndex) {
        if (this.outputLocals == null || localIndex >= this.outputLocals.length) {
            return 0x1000000 | localIndex;
        }
        int abstractType = this.outputLocals[localIndex];
        if (abstractType == 0) {
            abstractType = this.outputLocals[localIndex] = 0x1000000 | localIndex;
        }
        return abstractType;
    }

    private void setLocal(int localIndex, int abstractType) {
        int outputLocalsLength;
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        if (localIndex >= (outputLocalsLength = this.outputLocals.length)) {
            int[] newOutputLocals = new int[Math.max(localIndex + 1, 2 * outputLocalsLength)];
            System.arraycopy(this.outputLocals, 0, newOutputLocals, 0, outputLocalsLength);
            this.outputLocals = newOutputLocals;
        }
        this.outputLocals[localIndex] = abstractType;
    }

    private void push(int abstractType) {
        int outputStackLength;
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        if (this.outputStackTop >= (outputStackLength = this.outputStack.length)) {
            int[] newOutputStack = new int[Math.max(this.outputStackTop + 1, 2 * outputStackLength)];
            System.arraycopy(this.outputStack, 0, newOutputStack, 0, outputStackLength);
            this.outputStack = newOutputStack;
        }
        short s = this.outputStackTop;
        this.outputStackTop = (short)(s + 1);
        this.outputStack[s] = abstractType;
        short outputStackSize = (short)(this.outputStackStart + this.outputStackTop);
        if (outputStackSize > this.owner.outputStackMax) {
            this.owner.outputStackMax = outputStackSize;
        }
    }

    private void push(SymbolTable symbolTable, String descriptor) {
        int typeDescriptorOffset = descriptor.charAt(0) == '(' ? Type.getReturnTypeOffset(descriptor) : 0;
        int abstractType = Frame.getAbstractTypeFromDescriptor(symbolTable, descriptor, typeDescriptorOffset);
        if (abstractType != 0) {
            this.push(abstractType);
            if (abstractType == 0x400004 || abstractType == 0x400003) {
                this.push(0x400000);
            }
        }
    }

    private int pop() {
        if (this.outputStackTop > 0) {
            this.outputStackTop = (short)(this.outputStackTop - 1);
            return this.outputStack[this.outputStackTop];
        }
        this.outputStackStart = (short)(this.outputStackStart - 1);
        return 0x1400000 | -this.outputStackStart;
    }

    private void pop(int elements) {
        if (this.outputStackTop >= elements) {
            this.outputStackTop = (short)(this.outputStackTop - elements);
        } else {
            this.outputStackStart = (short)(this.outputStackStart - (elements - this.outputStackTop));
            this.outputStackTop = 0;
        }
    }

    private void pop(String descriptor) {
        char firstDescriptorChar = descriptor.charAt(0);
        if (firstDescriptorChar == '(') {
            this.pop((Type.getArgumentsAndReturnSizes(descriptor) >> 2) - 1);
        } else if (firstDescriptorChar == 'J' || firstDescriptorChar == 'D') {
            this.pop(2);
        } else {
            this.pop(1);
        }
    }

    private void addInitializedType(int abstractType) {
        int initializationsLength;
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        if (this.initializationCount >= (initializationsLength = this.initializations.length)) {
            int[] newInitializations = new int[Math.max(this.initializationCount + 1, 2 * initializationsLength)];
            System.arraycopy(this.initializations, 0, newInitializations, 0, initializationsLength);
            this.initializations = newInitializations;
        }
        this.initializations[this.initializationCount++] = abstractType;
    }

    private int getInitializedType(SymbolTable symbolTable, int abstractType) {
        if (abstractType == 0x400006 || (abstractType & 0xFFC00000) == 0xC00000) {
            for (int i = 0; i < this.initializationCount; ++i) {
                int initializedType = this.initializations[i];
                int dim = initializedType & 0xFC000000;
                int kind = initializedType & 0x3C00000;
                int value = initializedType & 0xFFFFF;
                if (kind == 0x1000000) {
                    initializedType = dim + this.inputLocals[value];
                } else if (kind == 0x1400000) {
                    initializedType = dim + this.inputStack[this.inputStack.length - value];
                }
                if (abstractType != initializedType) continue;
                if (abstractType == 0x400006) {
                    return 0x800000 | symbolTable.addType(symbolTable.getClassName());
                }
                return 0x800000 | symbolTable.addType(symbolTable.getType((int)(abstractType & 0xFFFFF)).value);
            }
        }
        return abstractType;
    }

    void execute(int opcode, int arg, Symbol argSymbol, SymbolTable symbolTable) {
        block0 : switch (opcode) {
            case 0: 
            case 116: 
            case 117: 
            case 118: 
            case 119: 
            case 145: 
            case 146: 
            case 147: 
            case 167: 
            case 177: {
                break;
            }
            case 1: {
                this.push(0x400005);
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 16: 
            case 17: 
            case 21: {
                this.push(0x400001);
                break;
            }
            case 9: 
            case 10: 
            case 22: {
                this.push(0x400004);
                this.push(0x400000);
                break;
            }
            case 11: 
            case 12: 
            case 13: 
            case 23: {
                this.push(0x400002);
                break;
            }
            case 14: 
            case 15: 
            case 24: {
                this.push(0x400003);
                this.push(0x400000);
                break;
            }
            case 18: {
                switch (argSymbol.tag) {
                    case 3: {
                        this.push(0x400001);
                        break block0;
                    }
                    case 5: {
                        this.push(0x400004);
                        this.push(0x400000);
                        break block0;
                    }
                    case 4: {
                        this.push(0x400002);
                        break block0;
                    }
                    case 6: {
                        this.push(0x400003);
                        this.push(0x400000);
                        break block0;
                    }
                    case 7: {
                        this.push(0x800000 | symbolTable.addType("java/lang/Class"));
                        break block0;
                    }
                    case 8: {
                        this.push(0x800000 | symbolTable.addType("java/lang/String"));
                        break block0;
                    }
                    case 16: {
                        this.push(0x800000 | symbolTable.addType("java/lang/invoke/MethodType"));
                        break block0;
                    }
                    case 15: {
                        this.push(0x800000 | symbolTable.addType("java/lang/invoke/MethodHandle"));
                        break block0;
                    }
                    case 17: {
                        this.push(symbolTable, argSymbol.value);
                        break block0;
                    }
                }
                throw new AssertionError();
            }
            case 25: {
                this.push(this.getLocal(arg));
                break;
            }
            case 47: 
            case 143: {
                this.pop(2);
                this.push(0x400004);
                this.push(0x400000);
                break;
            }
            case 49: 
            case 138: {
                this.pop(2);
                this.push(0x400003);
                this.push(0x400000);
                break;
            }
            case 50: {
                this.pop(1);
                int abstractType1 = this.pop();
                this.push(abstractType1 == 0x400005 ? abstractType1 : -67108864 + abstractType1);
                break;
            }
            case 54: 
            case 56: 
            case 58: {
                int abstractType1 = this.pop();
                this.setLocal(arg, abstractType1);
                if (arg <= 0) break;
                int previousLocalType = this.getLocal(arg - 1);
                if (previousLocalType == 0x400004 || previousLocalType == 0x400003) {
                    this.setLocal(arg - 1, 0x400000);
                    break;
                }
                if ((previousLocalType & 0x3C00000) != 0x1000000 && (previousLocalType & 0x3C00000) != 0x1400000) break;
                this.setLocal(arg - 1, previousLocalType | 0x100000);
                break;
            }
            case 55: 
            case 57: {
                this.pop(1);
                int abstractType1 = this.pop();
                this.setLocal(arg, abstractType1);
                this.setLocal(arg + 1, 0x400000);
                if (arg <= 0) break;
                int previousLocalType = this.getLocal(arg - 1);
                if (previousLocalType == 0x400004 || previousLocalType == 0x400003) {
                    this.setLocal(arg - 1, 0x400000);
                    break;
                }
                if ((previousLocalType & 0x3C00000) != 0x1000000 && (previousLocalType & 0x3C00000) != 0x1400000) break;
                this.setLocal(arg - 1, previousLocalType | 0x100000);
                break;
            }
            case 79: 
            case 81: 
            case 83: 
            case 84: 
            case 85: 
            case 86: {
                this.pop(3);
                break;
            }
            case 80: 
            case 82: {
                this.pop(4);
                break;
            }
            case 87: 
            case 153: 
            case 154: 
            case 155: 
            case 156: 
            case 157: 
            case 158: 
            case 170: 
            case 171: 
            case 172: 
            case 174: 
            case 176: 
            case 191: 
            case 194: 
            case 195: 
            case 198: 
            case 199: {
                this.pop(1);
                break;
            }
            case 88: 
            case 159: 
            case 160: 
            case 161: 
            case 162: 
            case 163: 
            case 164: 
            case 165: 
            case 166: 
            case 173: 
            case 175: {
                this.pop(2);
                break;
            }
            case 89: {
                int abstractType1 = this.pop();
                this.push(abstractType1);
                this.push(abstractType1);
                break;
            }
            case 90: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                this.push(abstractType1);
                this.push(abstractType2);
                this.push(abstractType1);
                break;
            }
            case 91: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                int abstractType3 = this.pop();
                this.push(abstractType1);
                this.push(abstractType3);
                this.push(abstractType2);
                this.push(abstractType1);
                break;
            }
            case 92: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                this.push(abstractType2);
                this.push(abstractType1);
                this.push(abstractType2);
                this.push(abstractType1);
                break;
            }
            case 93: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                int abstractType3 = this.pop();
                this.push(abstractType2);
                this.push(abstractType1);
                this.push(abstractType3);
                this.push(abstractType2);
                this.push(abstractType1);
                break;
            }
            case 94: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                int abstractType3 = this.pop();
                int abstractType4 = this.pop();
                this.push(abstractType2);
                this.push(abstractType1);
                this.push(abstractType4);
                this.push(abstractType3);
                this.push(abstractType2);
                this.push(abstractType1);
                break;
            }
            case 95: {
                int abstractType1 = this.pop();
                int abstractType2 = this.pop();
                this.push(abstractType1);
                this.push(abstractType2);
                break;
            }
            case 46: 
            case 51: 
            case 52: 
            case 53: 
            case 96: 
            case 100: 
            case 104: 
            case 108: 
            case 112: 
            case 120: 
            case 122: 
            case 124: 
            case 126: 
            case 128: 
            case 130: 
            case 136: 
            case 142: 
            case 149: 
            case 150: {
                this.pop(2);
                this.push(0x400001);
                break;
            }
            case 97: 
            case 101: 
            case 105: 
            case 109: 
            case 113: 
            case 127: 
            case 129: 
            case 131: {
                this.pop(4);
                this.push(0x400004);
                this.push(0x400000);
                break;
            }
            case 48: 
            case 98: 
            case 102: 
            case 106: 
            case 110: 
            case 114: 
            case 137: 
            case 144: {
                this.pop(2);
                this.push(0x400002);
                break;
            }
            case 99: 
            case 103: 
            case 107: 
            case 111: 
            case 115: {
                this.pop(4);
                this.push(0x400003);
                this.push(0x400000);
                break;
            }
            case 121: 
            case 123: 
            case 125: {
                this.pop(3);
                this.push(0x400004);
                this.push(0x400000);
                break;
            }
            case 132: {
                this.setLocal(arg, 0x400001);
                break;
            }
            case 133: 
            case 140: {
                this.pop(1);
                this.push(0x400004);
                this.push(0x400000);
                break;
            }
            case 134: {
                this.pop(1);
                this.push(0x400002);
                break;
            }
            case 135: 
            case 141: {
                this.pop(1);
                this.push(0x400003);
                this.push(0x400000);
                break;
            }
            case 139: 
            case 190: 
            case 193: {
                this.pop(1);
                this.push(0x400001);
                break;
            }
            case 148: 
            case 151: 
            case 152: {
                this.pop(4);
                this.push(0x400001);
                break;
            }
            case 168: 
            case 169: {
                throw new IllegalArgumentException("JSR/RET are not supported with computeFrames option");
            }
            case 178: {
                this.push(symbolTable, argSymbol.value);
                break;
            }
            case 179: {
                this.pop(argSymbol.value);
                break;
            }
            case 180: {
                this.pop(1);
                this.push(symbolTable, argSymbol.value);
                break;
            }
            case 181: {
                this.pop(argSymbol.value);
                this.pop();
                break;
            }
            case 182: 
            case 183: 
            case 184: 
            case 185: {
                this.pop(argSymbol.value);
                if (opcode != 184) {
                    int abstractType1 = this.pop();
                    if (opcode == 183 && argSymbol.name.charAt(0) == '<') {
                        this.addInitializedType(abstractType1);
                    }
                }
                this.push(symbolTable, argSymbol.value);
                break;
            }
            case 186: {
                this.pop(argSymbol.value);
                this.push(symbolTable, argSymbol.value);
                break;
            }
            case 187: {
                this.push(0xC00000 | symbolTable.addUninitializedType(argSymbol.value, arg));
                break;
            }
            case 188: {
                this.pop();
                switch (arg) {
                    case 4: {
                        this.push(0x4400009);
                        break block0;
                    }
                    case 5: {
                        this.push(0x440000B);
                        break block0;
                    }
                    case 8: {
                        this.push(0x440000A);
                        break block0;
                    }
                    case 9: {
                        this.push(0x440000C);
                        break block0;
                    }
                    case 10: {
                        this.push(0x4400001);
                        break block0;
                    }
                    case 6: {
                        this.push(0x4400002);
                        break block0;
                    }
                    case 7: {
                        this.push(0x4400003);
                        break block0;
                    }
                    case 11: {
                        this.push(0x4400004);
                        break block0;
                    }
                }
                throw new IllegalArgumentException();
            }
            case 189: {
                String arrayElementType = argSymbol.value;
                this.pop();
                if (arrayElementType.charAt(0) == '[') {
                    this.push(symbolTable, '[' + arrayElementType);
                    break;
                }
                this.push(0x4800000 | symbolTable.addType(arrayElementType));
                break;
            }
            case 192: {
                String castType = argSymbol.value;
                this.pop();
                if (castType.charAt(0) == '[') {
                    this.push(symbolTable, castType);
                    break;
                }
                this.push(0x800000 | symbolTable.addType(castType));
                break;
            }
            case 197: {
                this.pop(arg);
                this.push(symbolTable, argSymbol.value);
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    private int getConcreteOutputType(int abstractOutputType, int numStack) {
        int dim = abstractOutputType & 0xFC000000;
        int kind = abstractOutputType & 0x3C00000;
        if (kind == 0x1000000) {
            int concreteOutputType = dim + this.inputLocals[abstractOutputType & 0xFFFFF];
            if ((abstractOutputType & 0x100000) != 0 && (concreteOutputType == 0x400004 || concreteOutputType == 0x400003)) {
                concreteOutputType = 0x400000;
            }
            return concreteOutputType;
        }
        if (kind == 0x1400000) {
            int concreteOutputType = dim + this.inputStack[numStack - (abstractOutputType & 0xFFFFF)];
            if ((abstractOutputType & 0x100000) != 0 && (concreteOutputType == 0x400004 || concreteOutputType == 0x400003)) {
                concreteOutputType = 0x400000;
            }
            return concreteOutputType;
        }
        return abstractOutputType;
    }

    final boolean merge(SymbolTable symbolTable, Frame dstFrame, int catchTypeIndex) {
        int i;
        int abstractOutputType;
        int i2;
        boolean frameChanged = false;
        int numLocal = this.inputLocals.length;
        int numStack = this.inputStack.length;
        if (dstFrame.inputLocals == null) {
            dstFrame.inputLocals = new int[numLocal];
            frameChanged = true;
        }
        for (i2 = 0; i2 < numLocal; ++i2) {
            int concreteOutputType = this.outputLocals != null && i2 < this.outputLocals.length ? ((abstractOutputType = this.outputLocals[i2]) == 0 ? this.inputLocals[i2] : this.getConcreteOutputType(abstractOutputType, numStack)) : this.inputLocals[i2];
            if (this.initializations != null) {
                concreteOutputType = this.getInitializedType(symbolTable, concreteOutputType);
            }
            frameChanged |= Frame.merge(symbolTable, concreteOutputType, dstFrame.inputLocals, i2);
        }
        if (catchTypeIndex > 0) {
            for (i2 = 0; i2 < numLocal; ++i2) {
                frameChanged |= Frame.merge(symbolTable, this.inputLocals[i2], dstFrame.inputLocals, i2);
            }
            if (dstFrame.inputStack == null) {
                dstFrame.inputStack = new int[1];
                frameChanged = true;
            }
            return frameChanged |= Frame.merge(symbolTable, catchTypeIndex, dstFrame.inputStack, 0);
        }
        int numInputStack = this.inputStack.length + this.outputStackStart;
        if (dstFrame.inputStack == null) {
            dstFrame.inputStack = new int[numInputStack + this.outputStackTop];
            frameChanged = true;
        }
        for (i = 0; i < numInputStack; ++i) {
            int concreteOutputType = this.inputStack[i];
            if (this.initializations != null) {
                concreteOutputType = this.getInitializedType(symbolTable, concreteOutputType);
            }
            frameChanged |= Frame.merge(symbolTable, concreteOutputType, dstFrame.inputStack, i);
        }
        for (i = 0; i < this.outputStackTop; ++i) {
            abstractOutputType = this.outputStack[i];
            int concreteOutputType = this.getConcreteOutputType(abstractOutputType, numStack);
            if (this.initializations != null) {
                concreteOutputType = this.getInitializedType(symbolTable, concreteOutputType);
            }
            frameChanged |= Frame.merge(symbolTable, concreteOutputType, dstFrame.inputStack, numInputStack + i);
        }
        return frameChanged;
    }

    private static boolean merge(SymbolTable symbolTable, int sourceType, int[] dstTypes, int dstIndex) {
        int mergedType;
        int dstType = dstTypes[dstIndex];
        if (dstType == sourceType) {
            return false;
        }
        int srcType = sourceType;
        if ((sourceType & 0x3FFFFFF) == 0x400005) {
            if (dstType == 0x400005) {
                return false;
            }
            srcType = 0x400005;
        }
        if (dstType == 0) {
            dstTypes[dstIndex] = srcType;
            return true;
        }
        if ((dstType & 0xFC000000) != 0 || (dstType & 0x3C00000) == 0x800000) {
            if (srcType == 0x400005) {
                return false;
            }
            if ((srcType & 0xFFC00000) == (dstType & 0xFFC00000)) {
                if ((dstType & 0x3C00000) == 0x800000) {
                    mergedType = srcType & 0xFC000000 | 0x800000 | symbolTable.addMergedType(srcType & 0xFFFFF, dstType & 0xFFFFF);
                } else {
                    int mergedDim = -67108864 + (srcType & 0xFC000000);
                    mergedType = mergedDim | 0x800000 | symbolTable.addType("java/lang/Object");
                }
            } else if ((srcType & 0xFC000000) != 0 || (srcType & 0x3C00000) == 0x800000) {
                int dstDim;
                int srcDim = srcType & 0xFC000000;
                if (srcDim != 0 && (srcType & 0x3C00000) != 0x800000) {
                    srcDim = -67108864 + srcDim;
                }
                if ((dstDim = dstType & 0xFC000000) != 0 && (dstType & 0x3C00000) != 0x800000) {
                    dstDim = -67108864 + dstDim;
                }
                mergedType = Math.min(srcDim, dstDim) | 0x800000 | symbolTable.addType("java/lang/Object");
            } else {
                mergedType = 0x400000;
            }
        } else {
            mergedType = dstType == 0x400005 ? ((srcType & 0xFC000000) != 0 || (srcType & 0x3C00000) == 0x800000 ? srcType : 0x400000) : 0x400000;
        }
        if (mergedType != dstType) {
            dstTypes[dstIndex] = mergedType;
            return true;
        }
        return false;
    }

    final void accept(MethodWriter methodWriter) {
        int[] localTypes = this.inputLocals;
        int numLocal = 0;
        int numTrailingTop = 0;
        int i = 0;
        while (i < localTypes.length) {
            int localType;
            i += (localType = localTypes[i]) == 0x400004 || localType == 0x400003 ? 2 : 1;
            if (localType == 0x400000) {
                ++numTrailingTop;
                continue;
            }
            numLocal += numTrailingTop + 1;
            numTrailingTop = 0;
        }
        int[] stackTypes = this.inputStack;
        int numStack = 0;
        i = 0;
        while (i < stackTypes.length) {
            int stackType;
            i += (stackType = stackTypes[i]) == 0x400004 || stackType == 0x400003 ? 2 : 1;
            ++numStack;
        }
        int frameIndex = methodWriter.visitFrameStart(this.owner.bytecodeOffset, numLocal, numStack);
        i = 0;
        while (numLocal-- > 0) {
            int localType;
            i += (localType = localTypes[i]) == 0x400004 || localType == 0x400003 ? 2 : 1;
            methodWriter.visitAbstractType(frameIndex++, localType);
        }
        i = 0;
        while (numStack-- > 0) {
            int stackType;
            i += (stackType = stackTypes[i]) == 0x400004 || stackType == 0x400003 ? 2 : 1;
            methodWriter.visitAbstractType(frameIndex++, stackType);
        }
        methodWriter.visitFrameEnd();
    }

    static void putAbstractType(SymbolTable symbolTable, int abstractType, ByteVector output) {
        int arrayDimensions = (abstractType & 0xFC000000) >> 26;
        if (arrayDimensions == 0) {
            int typeValue = abstractType & 0xFFFFF;
            switch (abstractType & 0x3C00000) {
                case 0x400000: {
                    output.putByte(typeValue);
                    break;
                }
                case 0x800000: {
                    output.putByte(7).putShort(symbolTable.addConstantClass((String)symbolTable.getType((int)typeValue).value).index);
                    break;
                }
                case 0xC00000: {
                    output.putByte(8).putShort((int)symbolTable.getType((int)typeValue).data);
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
        } else {
            StringBuilder typeDescriptor = new StringBuilder(32);
            while (arrayDimensions-- > 0) {
                typeDescriptor.append('[');
            }
            if ((abstractType & 0x3C00000) == 0x800000) {
                typeDescriptor.append('L').append(symbolTable.getType((int)(abstractType & 0xFFFFF)).value).append(';');
            } else {
                switch (abstractType & 0xFFFFF) {
                    case 9: {
                        typeDescriptor.append('Z');
                        break;
                    }
                    case 10: {
                        typeDescriptor.append('B');
                        break;
                    }
                    case 11: {
                        typeDescriptor.append('C');
                        break;
                    }
                    case 12: {
                        typeDescriptor.append('S');
                        break;
                    }
                    case 1: {
                        typeDescriptor.append('I');
                        break;
                    }
                    case 2: {
                        typeDescriptor.append('F');
                        break;
                    }
                    case 4: {
                        typeDescriptor.append('J');
                        break;
                    }
                    case 3: {
                        typeDescriptor.append('D');
                        break;
                    }
                    default: {
                        throw new AssertionError();
                    }
                }
            }
            output.putByte(7).putShort(symbolTable.addConstantClass((String)typeDescriptor.toString()).index);
        }
    }
}

