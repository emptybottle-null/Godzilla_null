/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

public abstract class ReflectionHelper {
    @Nullable
    static ArgumentsMatchInfo compareArguments(List<TypeDescriptor> expectedArgTypes, List<TypeDescriptor> suppliedArgTypes, TypeConverter typeConverter) {
        Assert.isTrue(expectedArgTypes.size() == suppliedArgTypes.size(), "Expected argument types and supplied argument types should be arrays of same length");
        ArgumentsMatchKind match = ArgumentsMatchKind.EXACT;
        for (int i = 0; i < expectedArgTypes.size() && match != null; ++i) {
            TypeDescriptor suppliedArg = suppliedArgTypes.get(i);
            TypeDescriptor expectedArg = expectedArgTypes.get(i);
            if (suppliedArg == null) {
                if (!expectedArg.isPrimitive()) continue;
                match = null;
                continue;
            }
            if (expectedArg.equals(suppliedArg)) continue;
            if (suppliedArg.isAssignableTo(expectedArg)) {
                if (match == ArgumentsMatchKind.REQUIRES_CONVERSION) continue;
                match = ArgumentsMatchKind.CLOSE;
                continue;
            }
            match = typeConverter.canConvert(suppliedArg, expectedArg) ? ArgumentsMatchKind.REQUIRES_CONVERSION : null;
        }
        return match != null ? new ArgumentsMatchInfo(match) : null;
    }

    public static int getTypeDifferenceWeight(List<TypeDescriptor> paramTypes, List<TypeDescriptor> argTypes) {
        int result = 0;
        for (int i = 0; i < paramTypes.size(); ++i) {
            TypeDescriptor argType;
            TypeDescriptor paramType = paramTypes.get(i);
            TypeDescriptor typeDescriptor = argType = i < argTypes.size() ? argTypes.get(i) : null;
            if (argType == null) {
                if (!paramType.isPrimitive()) continue;
                return Integer.MAX_VALUE;
            }
            Class<Object> paramTypeClazz = paramType.getType();
            if (!ClassUtils.isAssignable(paramTypeClazz, argType.getType())) {
                return Integer.MAX_VALUE;
            }
            if (paramTypeClazz.isPrimitive()) {
                paramTypeClazz = Object.class;
            }
            Class<?> superClass = argType.getType().getSuperclass();
            while (superClass != null) {
                if (paramTypeClazz.equals(superClass)) {
                    result += 2;
                    superClass = null;
                    continue;
                }
                if (ClassUtils.isAssignable(paramTypeClazz, superClass)) {
                    result += 2;
                    superClass = superClass.getSuperclass();
                    continue;
                }
                superClass = null;
            }
            if (!paramTypeClazz.isInterface()) continue;
            ++result;
        }
        return result;
    }

    @Nullable
    static ArgumentsMatchInfo compareArgumentsVarargs(List<TypeDescriptor> expectedArgTypes, List<TypeDescriptor> suppliedArgTypes, TypeConverter typeConverter) {
        Assert.isTrue(!CollectionUtils.isEmpty(expectedArgTypes), "Expected arguments must at least include one array (the varargs parameter)");
        Assert.isTrue(expectedArgTypes.get(expectedArgTypes.size() - 1).isArray(), "Final expected argument should be array type (the varargs parameter)");
        ArgumentsMatchKind match = ArgumentsMatchKind.EXACT;
        int argCountUpToVarargs = expectedArgTypes.size() - 1;
        for (int i = 0; i < argCountUpToVarargs && match != null; ++i) {
            TypeDescriptor suppliedArg = suppliedArgTypes.get(i);
            TypeDescriptor expectedArg = expectedArgTypes.get(i);
            if (suppliedArg == null) {
                if (!expectedArg.isPrimitive()) continue;
                match = null;
                continue;
            }
            if (expectedArg.equals(suppliedArg)) continue;
            if (suppliedArg.isAssignableTo(expectedArg)) {
                if (match == ArgumentsMatchKind.REQUIRES_CONVERSION) continue;
                match = ArgumentsMatchKind.CLOSE;
                continue;
            }
            match = typeConverter.canConvert(suppliedArg, expectedArg) ? ArgumentsMatchKind.REQUIRES_CONVERSION : null;
        }
        if (match == null) {
            return null;
        }
        if (suppliedArgTypes.size() != expectedArgTypes.size() || !expectedArgTypes.get(expectedArgTypes.size() - 1).equals(suppliedArgTypes.get(suppliedArgTypes.size() - 1))) {
            TypeDescriptor varargsDesc = expectedArgTypes.get(expectedArgTypes.size() - 1);
            TypeDescriptor elementDesc = varargsDesc.getElementTypeDescriptor();
            Assert.state(elementDesc != null, "No element type");
            Class<?> varargsParamType = elementDesc.getType();
            for (int i = expectedArgTypes.size() - 1; i < suppliedArgTypes.size(); ++i) {
                TypeDescriptor suppliedArg = suppliedArgTypes.get(i);
                if (suppliedArg == null) {
                    if (!varargsParamType.isPrimitive()) continue;
                    match = null;
                    continue;
                }
                if (varargsParamType == suppliedArg.getType()) continue;
                if (ClassUtils.isAssignable(varargsParamType, suppliedArg.getType())) {
                    if (match == ArgumentsMatchKind.REQUIRES_CONVERSION) continue;
                    match = ArgumentsMatchKind.CLOSE;
                    continue;
                }
                match = typeConverter.canConvert(suppliedArg, TypeDescriptor.valueOf(varargsParamType)) ? ArgumentsMatchKind.REQUIRES_CONVERSION : null;
            }
        }
        return match != null ? new ArgumentsMatchInfo(match) : null;
    }

    public static boolean convertAllArguments(TypeConverter converter, Object[] arguments, Method method) throws SpelEvaluationException {
        Integer varargsPosition = method.isVarArgs() ? Integer.valueOf(method.getParameterCount() - 1) : null;
        return ReflectionHelper.convertArguments(converter, arguments, method, varargsPosition);
    }

    static boolean convertArguments(TypeConverter converter, Object[] arguments, Executable executable, @Nullable Integer varargsPosition) throws EvaluationException {
        boolean conversionOccurred;
        block4: {
            MethodParameter methodParam;
            TypeDescriptor targetType;
            block5: {
                Object argument;
                block3: {
                    conversionOccurred = false;
                    if (varargsPosition != null) break block3;
                    for (int i = 0; i < arguments.length; ++i) {
                        TypeDescriptor targetType2 = new TypeDescriptor(MethodParameter.forExecutable(executable, i));
                        Object argument2 = arguments[i];
                        arguments[i] = converter.convertValue(argument2, TypeDescriptor.forObject(argument2), targetType2);
                        conversionOccurred |= argument2 != arguments[i];
                    }
                    break block4;
                }
                for (int i = 0; i < varargsPosition; ++i) {
                    targetType = new TypeDescriptor(MethodParameter.forExecutable(executable, i));
                    argument = arguments[i];
                    arguments[i] = converter.convertValue(argument, TypeDescriptor.forObject(argument), targetType);
                    conversionOccurred |= argument != arguments[i];
                }
                methodParam = MethodParameter.forExecutable(executable, varargsPosition);
                if (varargsPosition != arguments.length - 1) break block5;
                targetType = new TypeDescriptor(methodParam);
                argument = arguments[varargsPosition];
                TypeDescriptor sourceType = TypeDescriptor.forObject(argument);
                arguments[varargsPosition.intValue()] = converter.convertValue(argument, sourceType, targetType);
                if (argument == arguments[varargsPosition] || ReflectionHelper.isFirstEntryInArray(argument, arguments[varargsPosition])) break block4;
                conversionOccurred = true;
                break block4;
            }
            targetType = new TypeDescriptor(methodParam).getElementTypeDescriptor();
            Assert.state(targetType != null, "No element type");
            for (int i = varargsPosition.intValue(); i < arguments.length; ++i) {
                Object argument = arguments[i];
                arguments[i] = converter.convertValue(argument, TypeDescriptor.forObject(argument), targetType);
                conversionOccurred |= argument != arguments[i];
            }
        }
        return conversionOccurred;
    }

    private static boolean isFirstEntryInArray(Object value, @Nullable Object possibleArray) {
        if (possibleArray == null) {
            return false;
        }
        Class<?> type = possibleArray.getClass();
        if (!type.isArray() || Array.getLength(possibleArray) == 0 || !ClassUtils.isAssignableValue(type.getComponentType(), value)) {
            return false;
        }
        Object arrayValue = Array.get(possibleArray, 0);
        return type.getComponentType().isPrimitive() ? arrayValue.equals(value) : arrayValue == value;
    }

    public static Object[] setupArgumentsForVarargsInvocation(Class<?>[] requiredParameterTypes, Object ... args) {
        int parameterCount = requiredParameterTypes.length;
        int argumentCount = args.length;
        if (parameterCount != args.length || requiredParameterTypes[parameterCount - 1] != (args[argumentCount - 1] != null ? args[argumentCount - 1].getClass() : null)) {
            int arraySize = 0;
            if (argumentCount >= parameterCount) {
                arraySize = argumentCount - (parameterCount - 1);
            }
            Object[] newArgs = new Object[parameterCount];
            System.arraycopy(args, 0, newArgs, 0, newArgs.length - 1);
            Class<?> componentType = requiredParameterTypes[parameterCount - 1].getComponentType();
            Object repackagedArgs = Array.newInstance(componentType, arraySize);
            for (int i = 0; i < arraySize; ++i) {
                Array.set(repackagedArgs, i, args[parameterCount - 1 + i]);
            }
            newArgs[newArgs.length - 1] = repackagedArgs;
            return newArgs;
        }
        return args;
    }

    static class ArgumentsMatchInfo {
        private final ArgumentsMatchKind kind;

        ArgumentsMatchInfo(ArgumentsMatchKind kind) {
            this.kind = kind;
        }

        public boolean isExactMatch() {
            return this.kind == ArgumentsMatchKind.EXACT;
        }

        public boolean isCloseMatch() {
            return this.kind == ArgumentsMatchKind.CLOSE;
        }

        public boolean isMatchRequiringConversion() {
            return this.kind == ArgumentsMatchKind.REQUIRES_CONVERSION;
        }

        public String toString() {
            return "ArgumentMatchInfo: " + (Object)((Object)this.kind);
        }
    }

    static enum ArgumentsMatchKind {
        EXACT,
        CLOSE,
        REQUIRES_CONVERSION;

    }
}

