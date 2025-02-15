/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ClassUtils;

abstract class MemberUtils {
    private static final int ACCESS_TEST = 7;
    private static final Class<?>[] ORDERED_PRIMITIVE_TYPES = new Class[]{Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    MemberUtils() {
    }

    static boolean setAccessibleWorkaround(AccessibleObject o) {
        if (o == null || o.isAccessible()) {
            return false;
        }
        Member m = (Member)((Object)o);
        if (!o.isAccessible() && Modifier.isPublic(m.getModifiers()) && MemberUtils.isPackageAccess(m.getDeclaringClass().getModifiers())) {
            try {
                o.setAccessible(true);
                return true;
            } catch (SecurityException securityException) {
                // empty catch block
            }
        }
        return false;
    }

    static boolean isPackageAccess(int modifiers) {
        return (modifiers & 7) == 0;
    }

    static boolean isAccessible(Member m) {
        return m != null && Modifier.isPublic(m.getModifiers()) && !m.isSynthetic();
    }

    static int compareConstructorFit(Constructor<?> left, Constructor<?> right, Class<?>[] actual) {
        return MemberUtils.compareParameterTypes(Executable.of(left), Executable.of(right), actual);
    }

    static int compareMethodFit(Method left, Method right, Class<?>[] actual) {
        return MemberUtils.compareParameterTypes(Executable.of(left), Executable.of(right), actual);
    }

    private static int compareParameterTypes(Executable left, Executable right, Class<?>[] actual) {
        float leftCost = MemberUtils.getTotalTransformationCost(actual, left);
        float rightCost = MemberUtils.getTotalTransformationCost(actual, right);
        return Float.compare(leftCost, rightCost);
    }

    private static float getTotalTransformationCost(Class<?>[] srcArgs, Executable executable) {
        long normalArgsLen;
        Class<?>[] destArgs = executable.getParameterTypes();
        boolean isVarArgs = executable.isVarArgs();
        float totalCost = 0.0f;
        long l = normalArgsLen = isVarArgs ? (long)(destArgs.length - 1) : (long)destArgs.length;
        if ((long)srcArgs.length < normalArgsLen) {
            return Float.MAX_VALUE;
        }
        int i = 0;
        while ((long)i < normalArgsLen) {
            totalCost += MemberUtils.getObjectTransformationCost(srcArgs[i], destArgs[i]);
            ++i;
        }
        if (isVarArgs) {
            boolean noVarArgsPassed = srcArgs.length < destArgs.length;
            boolean explicitArrayForVarargs = srcArgs.length == destArgs.length && srcArgs[srcArgs.length - 1] != null && srcArgs[srcArgs.length - 1].isArray();
            float varArgsCost = 0.001f;
            Class<?> destClass = destArgs[destArgs.length - 1].getComponentType();
            if (noVarArgsPassed) {
                totalCost += MemberUtils.getObjectTransformationCost(destClass, Object.class) + 0.001f;
            } else if (explicitArrayForVarargs) {
                Class<?> sourceClass = srcArgs[srcArgs.length - 1].getComponentType();
                totalCost += MemberUtils.getObjectTransformationCost(sourceClass, destClass) + 0.001f;
            } else {
                for (int i2 = destArgs.length - 1; i2 < srcArgs.length; ++i2) {
                    Class<?> srcClass = srcArgs[i2];
                    totalCost += MemberUtils.getObjectTransformationCost(srcClass, destClass) + 0.001f;
                }
            }
        }
        return totalCost;
    }

    private static float getObjectTransformationCost(Class<?> srcClass, Class<?> destClass) {
        if (destClass.isPrimitive()) {
            return MemberUtils.getPrimitivePromotionCost(srcClass, destClass);
        }
        float cost = 0.0f;
        while (srcClass != null && !destClass.equals(srcClass)) {
            if (destClass.isInterface() && ClassUtils.isAssignable(srcClass, destClass)) {
                cost += 0.25f;
                break;
            }
            cost += 1.0f;
            srcClass = srcClass.getSuperclass();
        }
        if (srcClass == null) {
            cost += 1.5f;
        }
        return cost;
    }

    private static float getPrimitivePromotionCost(Class<?> srcClass, Class<?> destClass) {
        if (srcClass == null) {
            return 1.5f;
        }
        float cost = 0.0f;
        Class<?> cls = srcClass;
        if (!cls.isPrimitive()) {
            cost += 0.1f;
            cls = ClassUtils.wrapperToPrimitive(cls);
        }
        for (int i = 0; cls != destClass && i < ORDERED_PRIMITIVE_TYPES.length; ++i) {
            if (cls != ORDERED_PRIMITIVE_TYPES[i]) continue;
            cost += 0.1f;
            if (i >= ORDERED_PRIMITIVE_TYPES.length - 1) continue;
            cls = ORDERED_PRIMITIVE_TYPES[i + 1];
        }
        return cost;
    }

    static boolean isMatchingMethod(Method method, Class<?>[] parameterTypes) {
        return MemberUtils.isMatchingExecutable(Executable.of(method), parameterTypes);
    }

    static boolean isMatchingConstructor(Constructor<?> method, Class<?>[] parameterTypes) {
        return MemberUtils.isMatchingExecutable(Executable.of(method), parameterTypes);
    }

    private static boolean isMatchingExecutable(Executable method, Class<?>[] parameterTypes) {
        Class<?>[] methodParameterTypes = method.getParameterTypes();
        if (ClassUtils.isAssignable(parameterTypes, methodParameterTypes, true)) {
            return true;
        }
        if (method.isVarArgs()) {
            int i;
            for (i = 0; i < methodParameterTypes.length - 1 && i < parameterTypes.length; ++i) {
                if (ClassUtils.isAssignable(parameterTypes[i], methodParameterTypes[i], true)) continue;
                return false;
            }
            Class<?> varArgParameterType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
            while (i < parameterTypes.length) {
                if (!ClassUtils.isAssignable(parameterTypes[i], varArgParameterType, true)) {
                    return false;
                }
                ++i;
            }
            return true;
        }
        return false;
    }

    private static final class Executable {
        private final Class<?>[] parameterTypes;
        private final boolean isVarArgs;

        private static Executable of(Method method) {
            return new Executable(method);
        }

        private static Executable of(Constructor<?> constructor) {
            return new Executable(constructor);
        }

        private Executable(Method method) {
            this.parameterTypes = method.getParameterTypes();
            this.isVarArgs = method.isVarArgs();
        }

        private Executable(Constructor<?> constructor) {
            this.parameterTypes = constructor.getParameterTypes();
            this.isVarArgs = constructor.isVarArgs();
        }

        public Class<?>[] getParameterTypes() {
            return this.parameterTypes;
        }

        public boolean isVarArgs() {
            return this.isVarArgs;
        }
    }
}

