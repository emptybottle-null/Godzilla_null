/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.beans.Introspector;
import java.io.Closeable;
import java.io.Externalizable;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

public abstract class ClassUtils {
    public static final String ARRAY_SUFFIX = "[]";
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char PATH_SEPARATOR = '/';
    private static final char NESTED_CLASS_SEPARATOR = '$';
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    public static final String CLASS_FILE_SUFFIX = ".class";
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap(9);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap(9);
    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap(32);
    private static final Map<String, Class<?>> commonClassCache = new HashMap(64);
    private static final Set<Class<?>> javaLanguageInterfaces;
    private static final Map<Method, Method> interfaceMethodCache;

    private static void registerCommonClasses(Class<?> ... commonClasses) {
        for (Class<?> clazz : commonClasses) {
            commonClassCache.put(clazz.getName(), clazz);
        }
    }

    @Nullable
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable throwable) {
            // empty catch block
        }
        if (cl == null && (cl = ClassUtils.class.getClassLoader()) == null) {
            try {
                cl = ClassLoader.getSystemClassLoader();
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return cl;
    }

    @Nullable
    public static ClassLoader overrideThreadContextClassLoader(@Nullable ClassLoader classLoaderToUse) {
        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
        if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
            currentThread.setContextClassLoader(classLoaderToUse);
            return threadContextClassLoader;
        }
        return null;
    }

    public static Class<?> forName(String name, @Nullable ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        Assert.notNull((Object)name, "Name must not be null");
        Class<?> clazz = ClassUtils.resolvePrimitiveClassName(name);
        if (clazz == null) {
            clazz = commonClassCache.get(name);
        }
        if (clazz != null) {
            return clazz;
        }
        if (name.endsWith(ARRAY_SUFFIX)) {
            String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
            Class<?> elementClass = ClassUtils.forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
            String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
            Class<?> elementClass = ClassUtils.forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
            String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
            Class<?> elementClass = ClassUtils.forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        ClassLoader clToUse = classLoader;
        if (clToUse == null) {
            clToUse = ClassUtils.getDefaultClassLoader();
        }
        try {
            return Class.forName(name, false, clToUse);
        } catch (ClassNotFoundException ex) {
            int lastDotIndex = name.lastIndexOf(46);
            if (lastDotIndex != -1) {
                String nestedClassName = name.substring(0, lastDotIndex) + '$' + name.substring(lastDotIndex + 1);
                try {
                    return Class.forName(nestedClassName, false, clToUse);
                } catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            throw ex;
        }
    }

    public static Class<?> resolveClassName(String className, @Nullable ClassLoader classLoader) throws IllegalArgumentException {
        try {
            return ClassUtils.forName(className, classLoader);
        } catch (IllegalAccessError err) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" + className + "]: " + err.getMessage(), err);
        } catch (LinkageError err) {
            throw new IllegalArgumentException("Unresolvable class definition for class [" + className + "]", err);
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("Could not find class [" + className + "]", ex);
        }
    }

    public static boolean isPresent(String className, @Nullable ClassLoader classLoader) {
        try {
            ClassUtils.forName(className, classLoader);
            return true;
        } catch (IllegalAccessError err) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" + className + "]: " + err.getMessage(), err);
        } catch (Throwable ex) {
            return false;
        }
    }

    public static boolean isVisible(Class<?> clazz, @Nullable ClassLoader classLoader) {
        if (classLoader == null) {
            return true;
        }
        try {
            if (clazz.getClassLoader() == classLoader) {
                return true;
            }
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return ClassUtils.isLoadable(clazz, classLoader);
    }

    public static boolean isCacheSafe(Class<?> clazz, @Nullable ClassLoader classLoader) {
        Assert.notNull(clazz, "Class must not be null");
        try {
            ClassLoader target = clazz.getClassLoader();
            if (target == classLoader || target == null) {
                return true;
            }
            if (classLoader == null) {
                return false;
            }
            ClassLoader current = classLoader;
            while (current != null) {
                if ((current = current.getParent()) != target) continue;
                return true;
            }
            while (target != null) {
                if ((target = target.getParent()) != classLoader) continue;
                return false;
            }
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return classLoader != null && ClassUtils.isLoadable(clazz, classLoader);
    }

    private static boolean isLoadable(Class<?> clazz, ClassLoader classLoader) {
        try {
            return clazz == classLoader.loadClass(clazz.getName());
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    @Nullable
    public static Class<?> resolvePrimitiveClassName(@Nullable String name) {
        Class<?> result = null;
        if (name != null && name.length() <= 7) {
            result = primitiveTypeNameMap.get(name);
        }
        return result;
    }

    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return primitiveWrapperTypeMap.containsKey(clazz);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.isPrimitive() || ClassUtils.isPrimitiveWrapper(clazz);
    }

    public static boolean isPrimitiveArray(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.isArray() && clazz.getComponentType().isPrimitive();
    }

    public static boolean isPrimitiveWrapperArray(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.isArray() && ClassUtils.isPrimitiveWrapper(clazz.getComponentType());
    }

    public static Class<?> resolvePrimitiveIfNecessary(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.isPrimitive() && clazz != Void.TYPE ? primitiveTypeToWrapperMap.get(clazz) : clazz;
    }

    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
            return lhsType == resolvedPrimitive;
        }
        Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
        return resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper);
    }

    public static boolean isAssignableValue(Class<?> type, @Nullable Object value) {
        Assert.notNull(type, "Type must not be null");
        return value != null ? ClassUtils.isAssignable(type, value.getClass()) : !type.isPrimitive();
    }

    public static String convertResourcePathToClassName(String resourcePath) {
        Assert.notNull((Object)resourcePath, "Resource path must not be null");
        return resourcePath.replace('/', '.');
    }

    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull((Object)className, "Class name must not be null");
        return className.replace('.', '/');
    }

    public static String addResourcePathToPackagePath(Class<?> clazz, String resourceName) {
        Assert.notNull((Object)resourceName, "Resource name must not be null");
        if (!resourceName.startsWith("/")) {
            return ClassUtils.classPackageAsResourcePath(clazz) + '/' + resourceName;
        }
        return ClassUtils.classPackageAsResourcePath(clazz) + resourceName;
    }

    public static String classPackageAsResourcePath(@Nullable Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        String className = clazz.getName();
        int packageEndIndex = className.lastIndexOf(46);
        if (packageEndIndex == -1) {
            return "";
        }
        String packageName = className.substring(0, packageEndIndex);
        return packageName.replace('.', '/');
    }

    public static String classNamesToString(Class<?> ... classes) {
        return ClassUtils.classNamesToString(Arrays.asList(classes));
    }

    public static String classNamesToString(@Nullable Collection<Class<?>> classes) {
        if (CollectionUtils.isEmpty(classes)) {
            return ARRAY_SUFFIX;
        }
        StringJoiner stringJoiner = new StringJoiner(", ", INTERNAL_ARRAY_PREFIX, "]");
        for (Class<?> clazz : classes) {
            stringJoiner.add(clazz.getName());
        }
        return stringJoiner.toString();
    }

    public static Class<?>[] toClassArray(@Nullable Collection<Class<?>> collection) {
        return !CollectionUtils.isEmpty(collection) ? collection.toArray(EMPTY_CLASS_ARRAY) : EMPTY_CLASS_ARRAY;
    }

    public static Class<?>[] getAllInterfaces(Object instance) {
        Assert.notNull(instance, "Instance must not be null");
        return ClassUtils.getAllInterfacesForClass(instance.getClass());
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz) {
        return ClassUtils.getAllInterfacesForClass(clazz, null);
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz, @Nullable ClassLoader classLoader) {
        return ClassUtils.toClassArray(ClassUtils.getAllInterfacesForClassAsSet(clazz, classLoader));
    }

    public static Set<Class<?>> getAllInterfacesAsSet(Object instance) {
        Assert.notNull(instance, "Instance must not be null");
        return ClassUtils.getAllInterfacesForClassAsSet(instance.getClass());
    }

    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz) {
        return ClassUtils.getAllInterfacesForClassAsSet(clazz, null);
    }

    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz, @Nullable ClassLoader classLoader) {
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface() && ClassUtils.isVisible(clazz, classLoader)) {
            return Collections.singleton(clazz);
        }
        LinkedHashSet interfaces = new LinkedHashSet();
        for (Class<?> current = clazz; current != null; current = current.getSuperclass()) {
            Class<?>[] ifcs;
            for (Class<?> ifc : ifcs = current.getInterfaces()) {
                if (!ClassUtils.isVisible(ifc, classLoader)) continue;
                interfaces.add(ifc);
            }
        }
        return interfaces;
    }

    public static Class<?> createCompositeInterface(Class<?>[] interfaces, @Nullable ClassLoader classLoader) {
        Assert.notEmpty((Object[])interfaces, "Interface array must not be empty");
        return Proxy.getProxyClass(classLoader, interfaces);
    }

    @Nullable
    public static Class<?> determineCommonAncestor(@Nullable Class<?> clazz1, @Nullable Class<?> clazz2) {
        if (clazz1 == null) {
            return clazz2;
        }
        if (clazz2 == null) {
            return clazz1;
        }
        if (clazz1.isAssignableFrom(clazz2)) {
            return clazz1;
        }
        if (clazz2.isAssignableFrom(clazz1)) {
            return clazz2;
        }
        Class<?> ancestor = clazz1;
        do {
            if ((ancestor = ancestor.getSuperclass()) != null && Object.class != ancestor) continue;
            return null;
        } while (!ancestor.isAssignableFrom(clazz2));
        return ancestor;
    }

    public static boolean isJavaLanguageInterface(Class<?> ifc) {
        return javaLanguageInterfaces.contains(ifc);
    }

    public static boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers());
    }

    @Deprecated
    public static boolean isCglibProxy(Object object) {
        return ClassUtils.isCglibProxyClass(object.getClass());
    }

    @Deprecated
    public static boolean isCglibProxyClass(@Nullable Class<?> clazz) {
        return clazz != null && ClassUtils.isCglibProxyClassName(clazz.getName());
    }

    @Deprecated
    public static boolean isCglibProxyClassName(@Nullable String className) {
        return className != null && className.contains(CGLIB_CLASS_SEPARATOR);
    }

    public static Class<?> getUserClass(Object instance) {
        Assert.notNull(instance, "Instance must not be null");
        return ClassUtils.getUserClass(instance.getClass());
    }

    public static Class<?> getUserClass(Class<?> clazz) {
        Class<?> superclass;
        if (clazz.getName().contains(CGLIB_CLASS_SEPARATOR) && (superclass = clazz.getSuperclass()) != null && superclass != Object.class) {
            return superclass;
        }
        return clazz;
    }

    @Nullable
    public static String getDescriptiveType(@Nullable Object value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (Proxy.isProxyClass(clazz)) {
            String prefix = clazz.getName() + " implementing ";
            StringJoiner result = new StringJoiner(",", prefix, "");
            for (Class<?> ifc : clazz.getInterfaces()) {
                result.add(ifc.getName());
            }
            return result.toString();
        }
        return clazz.getTypeName();
    }

    public static boolean matchesTypeName(Class<?> clazz, @Nullable String typeName) {
        return typeName != null && (typeName.equals(clazz.getTypeName()) || typeName.equals(clazz.getSimpleName()));
    }

    public static String getShortName(String className) {
        Assert.hasLength(className, "Class name must not be empty");
        int lastDotIndex = className.lastIndexOf(46);
        int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
        if (nameEndIndex == -1) {
            nameEndIndex = className.length();
        }
        String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
        shortName = shortName.replace('$', '.');
        return shortName;
    }

    public static String getShortName(Class<?> clazz) {
        return ClassUtils.getShortName(ClassUtils.getQualifiedName(clazz));
    }

    public static String getShortNameAsProperty(Class<?> clazz) {
        String shortName = ClassUtils.getShortName(clazz);
        int dotIndex = shortName.lastIndexOf(46);
        shortName = dotIndex != -1 ? shortName.substring(dotIndex + 1) : shortName;
        return Introspector.decapitalize(shortName);
    }

    public static String getClassFileName(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        String className = clazz.getName();
        int lastDotIndex = className.lastIndexOf(46);
        return className.substring(lastDotIndex + 1) + CLASS_FILE_SUFFIX;
    }

    public static String getPackageName(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return ClassUtils.getPackageName(clazz.getName());
    }

    public static String getPackageName(String fqClassName) {
        Assert.notNull((Object)fqClassName, "Class name must not be null");
        int lastDotIndex = fqClassName.lastIndexOf(46);
        return lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "";
    }

    public static String getQualifiedName(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return clazz.getTypeName();
    }

    public static String getQualifiedMethodName(Method method) {
        return ClassUtils.getQualifiedMethodName(method, null);
    }

    public static String getQualifiedMethodName(Method method, @Nullable Class<?> clazz) {
        Assert.notNull((Object)method, "Method must not be null");
        return (clazz != null ? clazz : method.getDeclaringClass()).getName() + '.' + method.getName();
    }

    public static boolean hasConstructor(Class<?> clazz, Class<?> ... paramTypes) {
        return ClassUtils.getConstructorIfAvailable(clazz, paramTypes) != null;
    }

    @Nullable
    public static <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?> ... paramTypes) {
        Assert.notNull(clazz, "Class must not be null");
        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    public static boolean hasMethod(Class<?> clazz, Method method) {
        Class<?>[] paramTypes;
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)method, "Method must not be null");
        if (clazz == method.getDeclaringClass()) {
            return true;
        }
        String methodName = method.getName();
        return ClassUtils.getMethodOrNull(clazz, methodName, paramTypes = method.getParameterTypes()) != null;
    }

    public static boolean hasMethod(Class<?> clazz, String methodName, Class<?> ... paramTypes) {
        return ClassUtils.getMethodIfAvailable(clazz, methodName, paramTypes) != null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, @Nullable Class<?> ... paramTypes) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)methodName, "Method name must not be null");
        if (paramTypes != null) {
            try {
                return clazz.getMethod(methodName, paramTypes);
            } catch (NoSuchMethodException ex) {
                throw new IllegalStateException("Expected method not found: " + ex);
            }
        }
        Set<Method> candidates = ClassUtils.findMethodCandidatesByName(clazz, methodName);
        if (candidates.size() == 1) {
            return candidates.iterator().next();
        }
        if (candidates.isEmpty()) {
            throw new IllegalStateException("Expected method not found: " + clazz.getName() + '.' + methodName);
        }
        throw new IllegalStateException("No unique method found: " + clazz.getName() + '.' + methodName);
    }

    @Nullable
    public static Method getMethodIfAvailable(Class<?> clazz, String methodName, @Nullable Class<?> ... paramTypes) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)methodName, "Method name must not be null");
        if (paramTypes != null) {
            return ClassUtils.getMethodOrNull(clazz, methodName, paramTypes);
        }
        Set<Method> candidates = ClassUtils.findMethodCandidatesByName(clazz, methodName);
        if (candidates.size() == 1) {
            return candidates.iterator().next();
        }
        return null;
    }

    public static int getMethodCountForName(Class<?> clazz, String methodName) {
        Class<?>[] ifcs;
        Method[] declaredMethods;
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)methodName, "Method name must not be null");
        int count = 0;
        for (Method method : declaredMethods = clazz.getDeclaredMethods()) {
            if (!methodName.equals(method.getName())) continue;
            ++count;
        }
        for (Class<?> ifc : ifcs = clazz.getInterfaces()) {
            count += ClassUtils.getMethodCountForName(ifc, methodName);
        }
        if (clazz.getSuperclass() != null) {
            count += ClassUtils.getMethodCountForName(clazz.getSuperclass(), methodName);
        }
        return count;
    }

    public static boolean hasAtLeastOneMethodWithName(Class<?> clazz, String methodName) {
        Class<?>[] ifcs;
        Method[] declaredMethods;
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)methodName, "Method name must not be null");
        for (Method method : declaredMethods = clazz.getDeclaredMethods()) {
            if (!method.getName().equals(methodName)) continue;
            return true;
        }
        for (Class<?> ifc : ifcs = clazz.getInterfaces()) {
            if (!ClassUtils.hasAtLeastOneMethodWithName(ifc, methodName)) continue;
            return true;
        }
        return clazz.getSuperclass() != null && ClassUtils.hasAtLeastOneMethodWithName(clazz.getSuperclass(), methodName);
    }

    public static Method getMostSpecificMethod(Method method, @Nullable Class<?> targetClass) {
        if (targetClass != null && targetClass != method.getDeclaringClass() && ClassUtils.isOverridable(method, targetClass)) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    try {
                        return targetClass.getMethod(method.getName(), method.getParameterTypes());
                    } catch (NoSuchMethodException ex) {
                        return method;
                    }
                }
                Method specificMethod = ReflectionUtils.findMethod(targetClass, method.getName(), method.getParameterTypes());
                return specificMethod != null ? specificMethod : method;
            } catch (SecurityException securityException) {
                // empty catch block
            }
        }
        return method;
    }

    public static Method getInterfaceMethodIfPossible(Method method) {
        if (!Modifier.isPublic(method.getModifiers()) || method.getDeclaringClass().isInterface()) {
            return method;
        }
        return interfaceMethodCache.computeIfAbsent(method, key -> {
            for (Class<?> current = key.getDeclaringClass(); current != null && current != Object.class; current = current.getSuperclass()) {
                Class<?>[] ifcs;
                for (Class<?> ifc : ifcs = current.getInterfaces()) {
                    try {
                        return ifc.getMethod(key.getName(), key.getParameterTypes());
                    } catch (NoSuchMethodException noSuchMethodException) {
                    }
                }
            }
            return key;
        });
    }

    public static boolean isUserLevelMethod(Method method) {
        Assert.notNull((Object)method, "Method must not be null");
        return method.isBridge() || !method.isSynthetic() && !ClassUtils.isGroovyObjectMethod(method);
    }

    private static boolean isGroovyObjectMethod(Method method) {
        return method.getDeclaringClass().getName().equals("groovy.lang.GroovyObject");
    }

    private static boolean isOverridable(Method method, @Nullable Class<?> targetClass) {
        if (Modifier.isPrivate(method.getModifiers())) {
            return false;
        }
        if (Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
            return true;
        }
        return targetClass == null || ClassUtils.getPackageName(method.getDeclaringClass()).equals(ClassUtils.getPackageName(targetClass));
    }

    @Nullable
    public static Method getStaticMethod(Class<?> clazz, String methodName, Class<?> ... args) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull((Object)methodName, "Method name must not be null");
        try {
            Method method = clazz.getMethod(methodName, args);
            return Modifier.isStatic(method.getModifiers()) ? method : null;
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    @Nullable
    private static Method getMethodOrNull(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private static Set<Method> findMethodCandidatesByName(Class<?> clazz, String methodName) {
        Method[] methods;
        HashSet<Method> candidates = new HashSet<Method>(1);
        for (Method method : methods = clazz.getMethods()) {
            if (!methodName.equals(method.getName())) continue;
            candidates.add(method);
        }
        return candidates;
    }

    static {
        interfaceMethodCache = new ConcurrentReferenceHashMap<Method, Method>(256);
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
        primitiveWrapperTypeMap.put(Void.class, Void.TYPE);
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            ClassUtils.registerCommonClasses(entry.getKey());
        }
        HashSet primitiveTypes = new HashSet(32);
        primitiveTypes.addAll(primitiveWrapperTypeMap.values());
        Collections.addAll(primitiveTypes, boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class);
        for (Class clazz : primitiveTypes) {
            primitiveTypeNameMap.put(clazz.getName(), clazz);
        }
        ClassUtils.registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class);
        ClassUtils.registerCommonClasses(Number.class, Number[].class, String.class, String[].class, Class.class, Class[].class, Object.class, Object[].class);
        ClassUtils.registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class);
        ClassUtils.registerCommonClasses(Enum.class, Iterable.class, Iterator.class, Enumeration.class, Collection.class, List.class, Set.class, Map.class, Map.Entry.class, Optional.class);
        Class[] classArray = new Class[]{Serializable.class, Externalizable.class, Closeable.class, AutoCloseable.class, Cloneable.class, Comparable.class};
        ClassUtils.registerCommonClasses(classArray);
        javaLanguageInterfaces = new HashSet<Class>(Arrays.asList(classArray));
    }
}

