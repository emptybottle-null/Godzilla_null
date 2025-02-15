/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class Type {
    public static final int VOID = 0;
    public static final int BOOLEAN = 1;
    public static final int CHAR = 2;
    public static final int BYTE = 3;
    public static final int SHORT = 4;
    public static final int INT = 5;
    public static final int FLOAT = 6;
    public static final int LONG = 7;
    public static final int DOUBLE = 8;
    public static final int ARRAY = 9;
    public static final int OBJECT = 10;
    public static final int METHOD = 11;
    private static final int INTERNAL = 12;
    private static final String PRIMITIVE_DESCRIPTORS = "VZCBSIFJD";
    public static final Type VOID_TYPE = new Type(0, "VZCBSIFJD", 0, 1);
    public static final Type BOOLEAN_TYPE = new Type(1, "VZCBSIFJD", 1, 2);
    public static final Type CHAR_TYPE = new Type(2, "VZCBSIFJD", 2, 3);
    public static final Type BYTE_TYPE = new Type(3, "VZCBSIFJD", 3, 4);
    public static final Type SHORT_TYPE = new Type(4, "VZCBSIFJD", 4, 5);
    public static final Type INT_TYPE = new Type(5, "VZCBSIFJD", 5, 6);
    public static final Type FLOAT_TYPE = new Type(6, "VZCBSIFJD", 6, 7);
    public static final Type LONG_TYPE = new Type(7, "VZCBSIFJD", 7, 8);
    public static final Type DOUBLE_TYPE = new Type(8, "VZCBSIFJD", 8, 9);
    private final int sort;
    private final String valueBuffer;
    private final int valueBegin;
    private final int valueEnd;

    private Type(int sort, String valueBuffer, int valueBegin, int valueEnd) {
        this.sort = sort;
        this.valueBuffer = valueBuffer;
        this.valueBegin = valueBegin;
        this.valueEnd = valueEnd;
    }

    public static Type getType(String typeDescriptor) {
        return Type.getTypeInternal(typeDescriptor, 0, typeDescriptor.length());
    }

    public static Type getType(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == Integer.TYPE) {
                return INT_TYPE;
            }
            if (clazz == Void.TYPE) {
                return VOID_TYPE;
            }
            if (clazz == Boolean.TYPE) {
                return BOOLEAN_TYPE;
            }
            if (clazz == Byte.TYPE) {
                return BYTE_TYPE;
            }
            if (clazz == Character.TYPE) {
                return CHAR_TYPE;
            }
            if (clazz == Short.TYPE) {
                return SHORT_TYPE;
            }
            if (clazz == Double.TYPE) {
                return DOUBLE_TYPE;
            }
            if (clazz == Float.TYPE) {
                return FLOAT_TYPE;
            }
            if (clazz == Long.TYPE) {
                return LONG_TYPE;
            }
            throw new AssertionError();
        }
        return Type.getType(Type.getDescriptor(clazz));
    }

    public static Type getType(Constructor<?> constructor) {
        return Type.getType(Type.getConstructorDescriptor(constructor));
    }

    public static Type getType(Method method) {
        return Type.getType(Type.getMethodDescriptor(method));
    }

    public Type getElementType() {
        int numDimensions = this.getDimensions();
        return Type.getTypeInternal(this.valueBuffer, this.valueBegin + numDimensions, this.valueEnd);
    }

    public static Type getObjectType(String internalName) {
        return new Type(internalName.charAt(0) == '[' ? 9 : 12, internalName, 0, internalName.length());
    }

    public static Type getMethodType(String methodDescriptor) {
        return new Type(11, methodDescriptor, 0, methodDescriptor.length());
    }

    public static Type getMethodType(Type returnType, Type ... argumentTypes) {
        return Type.getType(Type.getMethodDescriptor(returnType, argumentTypes));
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.getDescriptor());
    }

    public static Type[] getArgumentTypes(String methodDescriptor) {
        int numArgumentTypes = 0;
        int currentOffset = 1;
        while (methodDescriptor.charAt(currentOffset) != ')') {
            while (methodDescriptor.charAt(currentOffset) == '[') {
                ++currentOffset;
            }
            if (methodDescriptor.charAt(currentOffset++) == 'L') {
                int semiColumnOffset = methodDescriptor.indexOf(59, currentOffset);
                currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
            }
            ++numArgumentTypes;
        }
        Type[] argumentTypes = new Type[numArgumentTypes];
        currentOffset = 1;
        int currentArgumentTypeIndex = 0;
        while (methodDescriptor.charAt(currentOffset) != ')') {
            int currentArgumentTypeOffset = currentOffset;
            while (methodDescriptor.charAt(currentOffset) == '[') {
                ++currentOffset;
            }
            if (methodDescriptor.charAt(currentOffset++) == 'L') {
                int semiColumnOffset = methodDescriptor.indexOf(59, currentOffset);
                currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
            }
            argumentTypes[currentArgumentTypeIndex++] = Type.getTypeInternal(methodDescriptor, currentArgumentTypeOffset, currentOffset);
        }
        return argumentTypes;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] classes = method.getParameterTypes();
        Type[] types = new Type[classes.length];
        for (int i = classes.length - 1; i >= 0; --i) {
            types[i] = Type.getType(classes[i]);
        }
        return types;
    }

    public Type getReturnType() {
        return Type.getReturnType(this.getDescriptor());
    }

    public static Type getReturnType(String methodDescriptor) {
        return Type.getTypeInternal(methodDescriptor, Type.getReturnTypeOffset(methodDescriptor), methodDescriptor.length());
    }

    public static Type getReturnType(Method method) {
        return Type.getType(method.getReturnType());
    }

    static int getReturnTypeOffset(String methodDescriptor) {
        int currentOffset = 1;
        while (methodDescriptor.charAt(currentOffset) != ')') {
            while (methodDescriptor.charAt(currentOffset) == '[') {
                ++currentOffset;
            }
            if (methodDescriptor.charAt(currentOffset++) != 'L') continue;
            int semiColumnOffset = methodDescriptor.indexOf(59, currentOffset);
            currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
        }
        return currentOffset + 1;
    }

    private static Type getTypeInternal(String descriptorBuffer, int descriptorBegin, int descriptorEnd) {
        switch (descriptorBuffer.charAt(descriptorBegin)) {
            case 'V': {
                return VOID_TYPE;
            }
            case 'Z': {
                return BOOLEAN_TYPE;
            }
            case 'C': {
                return CHAR_TYPE;
            }
            case 'B': {
                return BYTE_TYPE;
            }
            case 'S': {
                return SHORT_TYPE;
            }
            case 'I': {
                return INT_TYPE;
            }
            case 'F': {
                return FLOAT_TYPE;
            }
            case 'J': {
                return LONG_TYPE;
            }
            case 'D': {
                return DOUBLE_TYPE;
            }
            case '[': {
                return new Type(9, descriptorBuffer, descriptorBegin, descriptorEnd);
            }
            case 'L': {
                return new Type(10, descriptorBuffer, descriptorBegin + 1, descriptorEnd - 1);
            }
            case '(': {
                return new Type(11, descriptorBuffer, descriptorBegin, descriptorEnd);
            }
        }
        throw new IllegalArgumentException();
    }

    public String getClassName() {
        switch (this.sort) {
            case 0: {
                return "void";
            }
            case 1: {
                return "boolean";
            }
            case 2: {
                return "char";
            }
            case 3: {
                return "byte";
            }
            case 4: {
                return "short";
            }
            case 5: {
                return "int";
            }
            case 6: {
                return "float";
            }
            case 7: {
                return "long";
            }
            case 8: {
                return "double";
            }
            case 9: {
                StringBuilder stringBuilder = new StringBuilder(this.getElementType().getClassName());
                for (int i = this.getDimensions(); i > 0; --i) {
                    stringBuilder.append("[]");
                }
                return stringBuilder.toString();
            }
            case 10: 
            case 12: {
                return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
            }
        }
        throw new AssertionError();
    }

    public String getInternalName() {
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public static String getInternalName(Class<?> clazz) {
        return clazz.getName().replace('.', '/');
    }

    public String getDescriptor() {
        if (this.sort == 10) {
            return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
        }
        if (this.sort == 12) {
            return 'L' + this.valueBuffer.substring(this.valueBegin, this.valueEnd) + ';';
        }
        return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
    }

    public static String getDescriptor(Class<?> clazz) {
        StringBuilder stringBuilder = new StringBuilder();
        Type.appendDescriptor(clazz, stringBuilder);
        return stringBuilder.toString();
    }

    public static String getConstructorDescriptor(Constructor<?> constructor) {
        Class<?>[] parameters;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (Class<?> parameter : parameters = constructor.getParameterTypes()) {
            Type.appendDescriptor(parameter, stringBuilder);
        }
        return stringBuilder.append(")V").toString();
    }

    public static String getMethodDescriptor(Type returnType, Type ... argumentTypes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (Type argumentType : argumentTypes) {
            argumentType.appendDescriptor(stringBuilder);
        }
        stringBuilder.append(')');
        returnType.appendDescriptor(stringBuilder);
        return stringBuilder.toString();
    }

    public static String getMethodDescriptor(Method method) {
        Class<?>[] parameters;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        for (Class<?> parameter : parameters = method.getParameterTypes()) {
            Type.appendDescriptor(parameter, stringBuilder);
        }
        stringBuilder.append(')');
        Type.appendDescriptor(method.getReturnType(), stringBuilder);
        return stringBuilder.toString();
    }

    private void appendDescriptor(StringBuilder stringBuilder) {
        if (this.sort == 10) {
            stringBuilder.append(this.valueBuffer, this.valueBegin - 1, this.valueEnd + 1);
        } else if (this.sort == 12) {
            stringBuilder.append('L').append(this.valueBuffer, this.valueBegin, this.valueEnd).append(';');
        } else {
            stringBuilder.append(this.valueBuffer, this.valueBegin, this.valueEnd);
        }
    }

    private static void appendDescriptor(Class<?> clazz, StringBuilder stringBuilder) {
        Class<?> currentClass = clazz;
        while (currentClass.isArray()) {
            stringBuilder.append('[');
            currentClass = currentClass.getComponentType();
        }
        if (currentClass.isPrimitive()) {
            int descriptor;
            if (currentClass == Integer.TYPE) {
                descriptor = 73;
            } else if (currentClass == Void.TYPE) {
                descriptor = 86;
            } else if (currentClass == Boolean.TYPE) {
                descriptor = 90;
            } else if (currentClass == Byte.TYPE) {
                descriptor = 66;
            } else if (currentClass == Character.TYPE) {
                descriptor = 67;
            } else if (currentClass == Short.TYPE) {
                descriptor = 83;
            } else if (currentClass == Double.TYPE) {
                descriptor = 68;
            } else if (currentClass == Float.TYPE) {
                descriptor = 70;
            } else if (currentClass == Long.TYPE) {
                descriptor = 74;
            } else {
                throw new AssertionError();
            }
            stringBuilder.append((char)descriptor);
        } else {
            stringBuilder.append('L').append(Type.getInternalName(currentClass)).append(';');
        }
    }

    public int getSort() {
        return this.sort == 12 ? 10 : this.sort;
    }

    public int getDimensions() {
        int numDimensions = 1;
        while (this.valueBuffer.charAt(this.valueBegin + numDimensions) == '[') {
            ++numDimensions;
        }
        return numDimensions;
    }

    public int getSize() {
        switch (this.sort) {
            case 0: {
                return 0;
            }
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 10: 
            case 12: {
                return 1;
            }
            case 7: 
            case 8: {
                return 2;
            }
        }
        throw new AssertionError();
    }

    public int getArgumentsAndReturnSizes() {
        return Type.getArgumentsAndReturnSizes(this.getDescriptor());
    }

    public static int getArgumentsAndReturnSizes(String methodDescriptor) {
        int argumentsSize = 1;
        int currentOffset = 1;
        char currentChar = methodDescriptor.charAt(currentOffset);
        while (currentChar != ')') {
            if (currentChar == 'J' || currentChar == 'D') {
                ++currentOffset;
                argumentsSize += 2;
            } else {
                while (methodDescriptor.charAt(currentOffset) == '[') {
                    ++currentOffset;
                }
                if (methodDescriptor.charAt(currentOffset++) == 'L') {
                    int semiColumnOffset = methodDescriptor.indexOf(59, currentOffset);
                    currentOffset = Math.max(currentOffset, semiColumnOffset + 1);
                }
                ++argumentsSize;
            }
            currentChar = methodDescriptor.charAt(currentOffset);
        }
        currentChar = methodDescriptor.charAt(currentOffset + 1);
        if (currentChar == 'V') {
            return argumentsSize << 2;
        }
        int returnSize = currentChar == 'J' || currentChar == 'D' ? 2 : 1;
        return argumentsSize << 2 | returnSize;
    }

    public int getOpcode(int opcode) {
        if (opcode == 46 || opcode == 79) {
            switch (this.sort) {
                case 1: 
                case 3: {
                    return opcode + 5;
                }
                case 2: {
                    return opcode + 6;
                }
                case 4: {
                    return opcode + 7;
                }
                case 5: {
                    return opcode;
                }
                case 6: {
                    return opcode + 2;
                }
                case 7: {
                    return opcode + 1;
                }
                case 8: {
                    return opcode + 3;
                }
                case 9: 
                case 10: 
                case 12: {
                    return opcode + 4;
                }
                case 0: 
                case 11: {
                    throw new UnsupportedOperationException();
                }
            }
            throw new AssertionError();
        }
        switch (this.sort) {
            case 0: {
                if (opcode != 172) {
                    throw new UnsupportedOperationException();
                }
                return 177;
            }
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return opcode;
            }
            case 6: {
                return opcode + 2;
            }
            case 7: {
                return opcode + 1;
            }
            case 8: {
                return opcode + 3;
            }
            case 9: 
            case 10: 
            case 12: {
                if (opcode != 21 && opcode != 54 && opcode != 172) {
                    throw new UnsupportedOperationException();
                }
                return opcode + 4;
            }
            case 11: {
                throw new UnsupportedOperationException();
            }
        }
        throw new AssertionError();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type)object;
        if ((this.sort == 12 ? 10 : this.sort) != (other.sort == 12 ? 10 : other.sort)) {
            return false;
        }
        int end = this.valueEnd;
        int begin = this.valueBegin;
        int otherEnd = other.valueEnd;
        int otherBegin = other.valueBegin;
        if (end - begin != otherEnd - otherBegin) {
            return false;
        }
        int i = begin;
        int j = otherBegin;
        while (i < end) {
            if (this.valueBuffer.charAt(i) != other.valueBuffer.charAt(j)) {
                return false;
            }
            ++i;
            ++j;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = 13 * (this.sort == 12 ? 10 : this.sort);
        if (this.sort >= 9) {
            int end = this.valueEnd;
            for (int i = this.valueBegin; i < end; ++i) {
                hashCode = 17 * (hashCode + this.valueBuffer.charAt(i));
            }
        }
        return hashCode;
    }

    public String toString() {
        return this.getDescriptor();
    }
}

