/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import javassist.tools.reflect.CannotCreateException;
import javassist.tools.reflect.CannotInvokeException;

public class ClassMetaobject
implements Serializable {
    private static final long serialVersionUID = 1L;
    static final String methodPrefix = "_m_";
    static final int methodPrefixLen = 3;
    private Class<?> javaClass;
    private Constructor<?>[] constructors;
    private Method[] methods;
    public static boolean useContextClassLoader = false;

    public ClassMetaobject(String[] params) {
        try {
            this.javaClass = this.getClassObject(params[0]);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("not found: " + params[0] + ", useContextClassLoader: " + Boolean.toString(useContextClassLoader), e);
        }
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(this.javaClass.getName());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.javaClass = this.getClassObject(in.readUTF());
        this.constructors = this.javaClass.getConstructors();
        this.methods = null;
    }

    private Class<?> getClassObject(String name) throws ClassNotFoundException {
        if (useContextClassLoader) {
            return Thread.currentThread().getContextClassLoader().loadClass(name);
        }
        return Class.forName(name);
    }

    public final Class<?> getJavaClass() {
        return this.javaClass;
    }

    public final String getName() {
        return this.javaClass.getName();
    }

    public final boolean isInstance(Object obj) {
        return this.javaClass.isInstance(obj);
    }

    public final Object newInstance(Object[] args) throws CannotCreateException {
        int n = this.constructors.length;
        for (int i = 0; i < n; ++i) {
            try {
                return this.constructors[i].newInstance(args);
            } catch (IllegalArgumentException illegalArgumentException) {
                continue;
            } catch (InstantiationException e) {
                throw new CannotCreateException(e);
            } catch (IllegalAccessException e) {
                throw new CannotCreateException(e);
            } catch (InvocationTargetException e) {
                throw new CannotCreateException(e);
            }
        }
        throw new CannotCreateException("no constructor matches");
    }

    public Object trapFieldRead(String name) {
        Class<?> jc = this.getJavaClass();
        try {
            return jc.getField(name).get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public void trapFieldWrite(String name, Object value) {
        Class<?> jc = this.getJavaClass();
        try {
            jc.getField(name).set(null, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static Object invoke(Object target, int identifier, Object[] args) throws Throwable {
        Method[] allmethods = target.getClass().getMethods();
        int n = allmethods.length;
        String head = methodPrefix + identifier;
        for (int i = 0; i < n; ++i) {
            if (!allmethods[i].getName().startsWith(head)) continue;
            try {
                return allmethods[i].invoke(target, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (IllegalAccessException e) {
                throw new CannotInvokeException(e);
            }
        }
        throw new CannotInvokeException("cannot find a method");
    }

    public Object trapMethodcall(int identifier, Object[] args) throws Throwable {
        try {
            Method[] m = this.getReflectiveMethods();
            return m[identifier].invoke(null, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (IllegalAccessException e) {
            throw new CannotInvokeException(e);
        }
    }

    public final Method[] getReflectiveMethods() {
        int i;
        if (this.methods != null) {
            return this.methods;
        }
        Class<?> baseclass = this.getJavaClass();
        Method[] allmethods = baseclass.getDeclaredMethods();
        int n = allmethods.length;
        int[] index = new int[n];
        int max = 0;
        for (i = 0; i < n; ++i) {
            char c;
            Method m = allmethods[i];
            String mname = m.getName();
            if (!mname.startsWith(methodPrefix)) continue;
            int k = 0;
            int j = 3;
            while ('0' <= (c = mname.charAt(j)) && c <= '9') {
                k = k * 10 + c - 48;
                ++j;
            }
            index[i] = ++k;
            if (k <= max) continue;
            max = k;
        }
        this.methods = new Method[max];
        for (i = 0; i < n; ++i) {
            if (index[i] <= 0) continue;
            this.methods[index[i] - 1] = allmethods[i];
        }
        return this.methods;
    }

    public final Method getMethod(int identifier) {
        return this.getReflectiveMethods()[identifier];
    }

    public final String getMethodName(int identifier) {
        char c;
        String mname = this.getReflectiveMethods()[identifier].getName();
        int j = 3;
        while ((c = mname.charAt(j++)) >= '0' && '9' >= c) {
        }
        return mname.substring(j);
    }

    public final Class<?>[] getParameterTypes(int identifier) {
        return this.getReflectiveMethods()[identifier].getParameterTypes();
    }

    public final Class<?> getReturnType(int identifier) {
        return this.getReflectiveMethods()[identifier].getReturnType();
    }

    public final int getMethodIndex(String originalName, Class<?>[] argTypes) throws NoSuchMethodException {
        Method[] mthds = this.getReflectiveMethods();
        for (int i = 0; i < mthds.length; ++i) {
            if (mthds[i] == null || !this.getMethodName(i).equals(originalName) || !Arrays.equals(argTypes, mthds[i].getParameterTypes())) continue;
            return i;
        }
        throw new NoSuchMethodException("Method " + originalName + " not found");
    }
}

