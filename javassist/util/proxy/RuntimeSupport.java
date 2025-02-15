/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.lang.reflect.Method;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import javassist.util.proxy.SecurityActions;
import javassist.util.proxy.SerializedProxy;

public class RuntimeSupport {
    public static MethodHandler default_interceptor = new DefaultMethodHandler();

    public static void find2Methods(Class<?> clazz, String superMethod, String thisMethod, int index, String desc, Method[] methods) {
        methods[index + 1] = thisMethod == null ? null : RuntimeSupport.findMethod(clazz, thisMethod, desc);
        methods[index] = RuntimeSupport.findSuperClassMethod(clazz, superMethod, desc);
    }

    @Deprecated
    public static void find2Methods(Object self, String superMethod, String thisMethod, int index, String desc, Method[] methods) {
        methods[index + 1] = thisMethod == null ? null : RuntimeSupport.findMethod(self, thisMethod, desc);
        methods[index] = RuntimeSupport.findSuperMethod(self, superMethod, desc);
    }

    @Deprecated
    public static Method findMethod(Object self, String name, String desc) {
        Method m = RuntimeSupport.findMethod2(self.getClass(), name, desc);
        if (m == null) {
            RuntimeSupport.error(self.getClass(), name, desc);
        }
        return m;
    }

    public static Method findMethod(Class<?> clazz, String name, String desc) {
        Method m = RuntimeSupport.findMethod2(clazz, name, desc);
        if (m == null) {
            RuntimeSupport.error(clazz, name, desc);
        }
        return m;
    }

    public static Method findSuperMethod(Object self, String name, String desc) {
        Class<?> clazz = self.getClass();
        return RuntimeSupport.findSuperClassMethod(clazz, name, desc);
    }

    public static Method findSuperClassMethod(Class<?> clazz, String name, String desc) {
        Method m = RuntimeSupport.findSuperMethod2(clazz.getSuperclass(), name, desc);
        if (m == null) {
            m = RuntimeSupport.searchInterfaces(clazz, name, desc);
        }
        if (m == null) {
            RuntimeSupport.error(clazz, name, desc);
        }
        return m;
    }

    private static void error(Class<?> clazz, String name, String desc) {
        throw new RuntimeException("not found " + name + ":" + desc + " in " + clazz.getName());
    }

    private static Method findSuperMethod2(Class<?> clazz, String name, String desc) {
        Method m = RuntimeSupport.findMethod2(clazz, name, desc);
        if (m != null) {
            return m;
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && (m = RuntimeSupport.findSuperMethod2(superClass, name, desc)) != null) {
            return m;
        }
        return RuntimeSupport.searchInterfaces(clazz, name, desc);
    }

    private static Method searchInterfaces(Class<?> clazz, String name, String desc) {
        Method m = null;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            m = RuntimeSupport.findSuperMethod2(interfaces[i], name, desc);
            if (m == null) continue;
            return m;
        }
        return m;
    }

    private static Method findMethod2(Class<?> clazz, String name, String desc) {
        Method[] methods = SecurityActions.getDeclaredMethods(clazz);
        int n = methods.length;
        for (int i = 0; i < n; ++i) {
            if (!methods[i].getName().equals(name) || !RuntimeSupport.makeDescriptor(methods[i]).equals(desc)) continue;
            return methods[i];
        }
        return null;
    }

    public static String makeDescriptor(Method m) {
        Class<?>[] params = m.getParameterTypes();
        return RuntimeSupport.makeDescriptor(params, m.getReturnType());
    }

    public static String makeDescriptor(Class<?>[] params, Class<?> retType) {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append('(');
        for (int i = 0; i < params.length; ++i) {
            RuntimeSupport.makeDesc(sbuf, params[i]);
        }
        sbuf.append(')');
        if (retType != null) {
            RuntimeSupport.makeDesc(sbuf, retType);
        }
        return sbuf.toString();
    }

    public static String makeDescriptor(String params, Class<?> retType) {
        StringBuffer sbuf = new StringBuffer(params);
        RuntimeSupport.makeDesc(sbuf, retType);
        return sbuf.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void makeDesc(StringBuffer sbuf, Class<?> type) {
        if (type.isArray()) {
            sbuf.append('[');
            RuntimeSupport.makeDesc(sbuf, type.getComponentType());
            return;
        } else if (type.isPrimitive()) {
            if (type == Void.TYPE) {
                sbuf.append('V');
                return;
            } else if (type == Integer.TYPE) {
                sbuf.append('I');
                return;
            } else if (type == Byte.TYPE) {
                sbuf.append('B');
                return;
            } else if (type == Long.TYPE) {
                sbuf.append('J');
                return;
            } else if (type == Double.TYPE) {
                sbuf.append('D');
                return;
            } else if (type == Float.TYPE) {
                sbuf.append('F');
                return;
            } else if (type == Character.TYPE) {
                sbuf.append('C');
                return;
            } else if (type == Short.TYPE) {
                sbuf.append('S');
                return;
            } else {
                if (type != Boolean.TYPE) throw new RuntimeException("bad type: " + type.getName());
                sbuf.append('Z');
            }
            return;
        } else {
            sbuf.append('L').append(type.getName().replace('.', '/')).append(';');
        }
    }

    public static SerializedProxy makeSerializedProxy(Object proxy) throws InvalidClassException {
        Class<?> clazz = proxy.getClass();
        MethodHandler methodHandler = null;
        if (proxy instanceof ProxyObject) {
            methodHandler = ((ProxyObject)proxy).getHandler();
        } else if (proxy instanceof Proxy) {
            methodHandler = ProxyFactory.getHandler((Proxy)proxy);
        }
        return new SerializedProxy(clazz, ProxyFactory.getFilterSignature(clazz), methodHandler);
    }

    static class DefaultMethodHandler
    implements MethodHandler,
    Serializable {
        private static final long serialVersionUID = 1L;

        DefaultMethodHandler() {
        }

        @Override
        public Object invoke(Object self, Method m, Method proceed, Object[] args) throws Exception {
            return proceed.invoke(self, args);
        }
    }
}

