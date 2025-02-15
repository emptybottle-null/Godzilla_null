/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableObject;

public class ClassUtils {
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
    private static final Map<String, Class<?>> namePrimitiveMap = new HashMap();
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    private static final Map<String, String> abbreviationMap;
    private static final Map<String, String> reverseAbbreviationMap;

    public static String getShortClassName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return ClassUtils.getShortClassName(object.getClass());
    }

    public static String getShortClassName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return ClassUtils.getShortClassName(cls.getName());
    }

    public static String getShortClassName(String className) {
        int lastDotIdx;
        if (StringUtils.isEmpty(className)) {
            return "";
        }
        StringBuilder arrayPrefix = new StringBuilder();
        if (className.startsWith("[")) {
            while (className.charAt(0) == '[') {
                className = className.substring(1);
                arrayPrefix.append("[]");
            }
            if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
                className = className.substring(1, className.length() - 1);
            }
            if (reverseAbbreviationMap.containsKey(className)) {
                className = reverseAbbreviationMap.get(className);
            }
        }
        int innerIdx = className.indexOf(36, (lastDotIdx = className.lastIndexOf(46)) == -1 ? 0 : lastDotIdx + 1);
        String out = className.substring(lastDotIdx + 1);
        if (innerIdx != -1) {
            out = out.replace('$', '.');
        }
        return out + arrayPrefix;
    }

    public static String getSimpleName(Class<?> cls) {
        return ClassUtils.getSimpleName(cls, "");
    }

    public static String getSimpleName(Class<?> cls, String valueIfNull) {
        return cls == null ? valueIfNull : cls.getSimpleName();
    }

    public static String getSimpleName(Object object) {
        return ClassUtils.getSimpleName(object, "");
    }

    public static String getSimpleName(Object object, String valueIfNull) {
        return object == null ? valueIfNull : object.getClass().getSimpleName();
    }

    public static String getName(Class<?> cls) {
        return ClassUtils.getName(cls, "");
    }

    public static String getName(Class<?> cls, String valueIfNull) {
        return cls == null ? valueIfNull : cls.getName();
    }

    public static String getName(Object object) {
        return ClassUtils.getName(object, "");
    }

    public static String getName(Object object, String valueIfNull) {
        return object == null ? valueIfNull : object.getClass().getName();
    }

    public static String getPackageName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return ClassUtils.getPackageName(object.getClass());
    }

    public static String getPackageName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return ClassUtils.getPackageName(cls.getName());
    }

    public static String getPackageName(String className) {
        int i;
        if (StringUtils.isEmpty(className)) {
            return "";
        }
        while (className.charAt(0) == '[') {
            className = className.substring(1);
        }
        if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
            className = className.substring(1);
        }
        if ((i = className.lastIndexOf(46)) == -1) {
            return "";
        }
        return className.substring(0, i);
    }

    public static String getAbbreviatedName(Class<?> cls, int lengthHint) {
        if (cls == null) {
            return "";
        }
        return ClassUtils.getAbbreviatedName(cls.getName(), lengthHint);
    }

    public static String getAbbreviatedName(String className, int lengthHint) {
        if (lengthHint <= 0) {
            throw new IllegalArgumentException("len must be > 0");
        }
        if (className == null) {
            return "";
        }
        if (className.length() <= lengthHint) {
            return className;
        }
        char[] abbreviated = className.toCharArray();
        int target = 0;
        int source = 0;
        while (source < abbreviated.length) {
            int runAheadTarget = target;
            while (source < abbreviated.length && abbreviated[source] != '.') {
                abbreviated[runAheadTarget++] = abbreviated[source++];
            }
            if (ClassUtils.useFull(runAheadTarget, source, abbreviated.length, lengthHint) || ++target > runAheadTarget) {
                target = runAheadTarget;
            }
            if (source >= abbreviated.length) continue;
            abbreviated[target++] = abbreviated[source++];
        }
        return new String(abbreviated, 0, target);
    }

    private static boolean useFull(int runAheadTarget, int source, int originalLength, int desiredLength) {
        return source >= originalLength || runAheadTarget + originalLength - source <= desiredLength;
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList classes = new ArrayList();
        for (Class<?> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            classes.add(superclass);
        }
        return classes;
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet interfacesFound = new LinkedHashSet();
        ClassUtils.getAllInterfaces(cls, interfacesFound);
        return new ArrayList(interfacesFound);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            Class<?>[] interfaces;
            for (Class<?> i : interfaces = cls.getInterfaces()) {
                if (!interfacesFound.add(i)) continue;
                ClassUtils.getAllInterfaces(i, interfacesFound);
            }
            cls = cls.getSuperclass();
        }
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> classNames) {
        if (classNames == null) {
            return null;
        }
        ArrayList classes = new ArrayList(classNames.size());
        for (String className : classNames) {
            try {
                classes.add(Class.forName(className));
            } catch (Exception ex) {
                classes.add(null);
            }
        }
        return classes;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> classes) {
        if (classes == null) {
            return null;
        }
        ArrayList<String> classNames = new ArrayList<String>(classes.size());
        for (Class<?> cls : classes) {
            if (cls == null) {
                classNames.add(null);
                continue;
            }
            classNames.add(cls.getName());
        }
        return classNames;
    }

    public static boolean isAssignable(Class<?>[] classArray, Class<?> ... toClassArray) {
        return ClassUtils.isAssignable(classArray, toClassArray, true);
    }

    public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing) {
        if (!ArrayUtils.isSameLength(classArray, toClassArray)) {
            return false;
        }
        if (classArray == null) {
            classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (toClassArray == null) {
            toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i = 0; i < classArray.length; ++i) {
            if (ClassUtils.isAssignable(classArray[i], toClassArray[i], autoboxing)) continue;
            return false;
        }
        return true;
    }

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        if (type == null) {
            return false;
        }
        return type.isPrimitive() || ClassUtils.isPrimitiveWrapper(type);
    }

    public static boolean isPrimitiveWrapper(Class<?> type) {
        return wrapperPrimitiveMap.containsKey(type);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> toClass) {
        return ClassUtils.isAssignable(cls, toClass, true);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing) {
        if (toClass == null) {
            return false;
        }
        if (cls == null) {
            return !toClass.isPrimitive();
        }
        if (autoboxing) {
            if (cls.isPrimitive() && !toClass.isPrimitive() && (cls = ClassUtils.primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (toClass.isPrimitive() && !cls.isPrimitive() && (cls = ClassUtils.wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(toClass)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!toClass.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Boolean.TYPE.equals(cls)) {
                return false;
            }
            if (Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(toClass);
            }
            if (Character.TYPE.equals(cls)) {
                return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Short.TYPE.equals(cls)) {
                return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(toClass) || Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            return false;
        }
        return toClass.isAssignableFrom(cls);
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        Class<?> convertedClass = cls;
        if (cls != null && cls.isPrimitive()) {
            convertedClass = primitiveWrapperMap.get(cls);
        }
        return convertedClass;
    }

    public static Class<?>[] primitivesToWrappers(Class<?> ... classes) {
        if (classes == null) {
            return null;
        }
        if (classes.length == 0) {
            return classes;
        }
        Class[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; ++i) {
            convertedClasses[i] = ClassUtils.primitiveToWrapper(classes[i]);
        }
        return convertedClasses;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }

    public static Class<?>[] wrappersToPrimitives(Class<?> ... classes) {
        if (classes == null) {
            return null;
        }
        if (classes.length == 0) {
            return classes;
        }
        Class[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; ++i) {
            convertedClasses[i] = ClassUtils.wrapperToPrimitive(classes[i]);
        }
        return convertedClasses;
    }

    public static boolean isInnerClass(Class<?> cls) {
        return cls != null && cls.getEnclosingClass() != null;
    }

    public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException {
        try {
            Class<?> clazz = namePrimitiveMap.containsKey(className) ? namePrimitiveMap.get(className) : Class.forName(ClassUtils.toCanonicalName(className), initialize, classLoader);
            return clazz;
        } catch (ClassNotFoundException ex) {
            int lastDotIndex = className.lastIndexOf(46);
            if (lastDotIndex != -1) {
                try {
                    return ClassUtils.getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1), initialize);
                } catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            throw ex;
        }
    }

    public static Class<?> getClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
        return ClassUtils.getClass(classLoader, className, true);
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        return ClassUtils.getClass(className, true);
    }

    public static Class<?> getClass(String className, boolean initialize) throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
        return ClassUtils.getClass(loader, className, initialize);
    }

    public static Method getPublicMethod(Class<?> cls, String methodName, Class<?> ... parameterTypes) throws NoSuchMethodException {
        Method declaredMethod = cls.getMethod(methodName, parameterTypes);
        if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers())) {
            return declaredMethod;
        }
        ArrayList candidateClasses = new ArrayList(ClassUtils.getAllInterfaces(cls));
        candidateClasses.addAll(ClassUtils.getAllSuperclasses(cls));
        for (Class clazz : candidateClasses) {
            Method candidateMethod;
            if (!Modifier.isPublic(clazz.getModifiers())) continue;
            try {
                candidateMethod = clazz.getMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException ex) {
                continue;
            }
            if (!Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) continue;
            return candidateMethod;
        }
        throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
    }

    private static String toCanonicalName(String className) {
        className = StringUtils.deleteWhitespace(className);
        Validate.notNull(className, "className", new Object[0]);
        if (className.endsWith("[]")) {
            StringBuilder classNameBuffer = new StringBuilder();
            while (className.endsWith("[]")) {
                className = className.substring(0, className.length() - 2);
                classNameBuffer.append("[");
            }
            String abbreviation = abbreviationMap.get(className);
            if (abbreviation != null) {
                classNameBuffer.append(abbreviation);
            } else {
                classNameBuffer.append("L").append(className).append(";");
            }
            className = classNameBuffer.toString();
        }
        return className;
    }

    public static Class<?>[] toClass(Object ... array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class[] classes = new Class[array.length];
        for (int i = 0; i < array.length; ++i) {
            classes[i] = array[i] == null ? null : array[i].getClass();
        }
        return classes;
    }

    public static String getShortCanonicalName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return ClassUtils.getShortCanonicalName(object.getClass().getName());
    }

    public static String getCanonicalName(Class<?> cls) {
        return ClassUtils.getCanonicalName(cls, "");
    }

    public static String getCanonicalName(Class<?> cls, String valueIfNull) {
        if (cls == null) {
            return valueIfNull;
        }
        String canonicalName = cls.getCanonicalName();
        return canonicalName == null ? valueIfNull : canonicalName;
    }

    public static String getCanonicalName(Object object) {
        return ClassUtils.getCanonicalName(object, "");
    }

    public static String getCanonicalName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        String canonicalName = object.getClass().getCanonicalName();
        return canonicalName == null ? valueIfNull : canonicalName;
    }

    public static String getShortCanonicalName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return ClassUtils.getShortCanonicalName(cls.getName());
    }

    public static String getShortCanonicalName(String canonicalName) {
        return ClassUtils.getShortClassName(ClassUtils.getCanonicalName(canonicalName));
    }

    public static String getPackageCanonicalName(Object object, String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return ClassUtils.getPackageCanonicalName(object.getClass().getName());
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return ClassUtils.getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(String name) {
        return ClassUtils.getPackageName(ClassUtils.getCanonicalName(name));
    }

    private static String getCanonicalName(String className) {
        if ((className = StringUtils.deleteWhitespace(className)) == null) {
            return null;
        }
        int dim = 0;
        while (className.startsWith("[")) {
            ++dim;
            className = className.substring(1);
        }
        if (dim < 1) {
            return className;
        }
        if (className.startsWith("L")) {
            className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
        } else if (!className.isEmpty()) {
            className = reverseAbbreviationMap.get(className.substring(0, 1));
        }
        StringBuilder canonicalClassNameBuffer = new StringBuilder(className);
        for (int i = 0; i < dim; ++i) {
            canonicalClassNameBuffer.append("[]");
        }
        return canonicalClassNameBuffer.toString();
    }

    public static Iterable<Class<?>> hierarchy(Class<?> type) {
        return ClassUtils.hierarchy(type, Interfaces.EXCLUDE);
    }

    public static Iterable<Class<?>> hierarchy(Class<?> type, Interfaces interfacesBehavior) {
        Iterable<Class<?>> classes = () -> {
            final MutableObject<Class> next = new MutableObject<Class>(type);
            return new Iterator<Class<?>>(){

                @Override
                public boolean hasNext() {
                    return next.getValue() != null;
                }

                @Override
                public Class<?> next() {
                    Class result = (Class)next.getValue();
                    next.setValue(result.getSuperclass());
                    return result;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        };
        if (interfacesBehavior != Interfaces.INCLUDE) {
            return classes;
        }
        return () -> {
            final HashSet seenInterfaces = new HashSet();
            final Iterator wrapped = classes.iterator();
            return new Iterator<Class<?>>(){
                Iterator interfaces = Collections.emptySet().iterator();

                @Override
                public boolean hasNext() {
                    return this.interfaces.hasNext() || wrapped.hasNext();
                }

                @Override
                public Class<?> next() {
                    if (this.interfaces.hasNext()) {
                        Class nextInterface = (Class)this.interfaces.next();
                        seenInterfaces.add(nextInterface);
                        return nextInterface;
                    }
                    Class nextSuperclass = (Class)wrapped.next();
                    LinkedHashSet currentInterfaces = new LinkedHashSet();
                    this.walkInterfaces(currentInterfaces, nextSuperclass);
                    this.interfaces = currentInterfaces.iterator();
                    return nextSuperclass;
                }

                private void walkInterfaces(Set<Class<?>> addTo, Class<?> c) {
                    for (Class<?> iface : c.getInterfaces()) {
                        if (!seenInterfaces.contains(iface)) {
                            addTo.add(iface);
                        }
                        this.walkInterfaces(addTo, iface);
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        };
    }

    static {
        namePrimitiveMap.put("boolean", Boolean.TYPE);
        namePrimitiveMap.put("byte", Byte.TYPE);
        namePrimitiveMap.put("char", Character.TYPE);
        namePrimitiveMap.put("short", Short.TYPE);
        namePrimitiveMap.put("int", Integer.TYPE);
        namePrimitiveMap.put("long", Long.TYPE);
        namePrimitiveMap.put("double", Double.TYPE);
        namePrimitiveMap.put("float", Float.TYPE);
        namePrimitiveMap.put("void", Void.TYPE);
        primitiveWrapperMap = new HashMap();
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperMap.entrySet()) {
            Class<?> wrapperClass;
            Class<?> primitiveClass = entry.getKey();
            if (primitiveClass.equals(wrapperClass = entry.getValue())) continue;
            wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
        }
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("int", "I");
        m.put("boolean", "Z");
        m.put("float", "F");
        m.put("long", "J");
        m.put("short", "S");
        m.put("byte", "B");
        m.put("double", "D");
        m.put("char", "C");
        HashMap r = new HashMap();
        for (Map.Entry e : m.entrySet()) {
            r.put(e.getValue(), e.getKey());
        }
        abbreviationMap = Collections.unmodifiableMap(m);
        reverseAbbreviationMap = Collections.unmodifiableMap(r);
    }

    public static enum Interfaces {
        INCLUDE,
        EXCLUDE;

    }
}

