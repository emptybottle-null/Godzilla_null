/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.util.proxy;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import javassist.CannotCompileException;
import javassist.bytecode.ClassFile;
import javassist.util.proxy.SecurityActions;

public class DefinePackageHelper {
    private static final Helper privileged = ClassFile.MAJOR_VERSION >= 53 ? new Java9() : (ClassFile.MAJOR_VERSION >= 51 ? new Java7() : new JavaOther());

    public static void definePackage(String className, ClassLoader loader) throws CannotCompileException {
        try {
            privileged.definePackage(loader, className, null, null, null, null, null, null, null);
        } catch (IllegalArgumentException e) {
            return;
        } catch (Exception e) {
            throw new CannotCompileException(e);
        }
    }

    private DefinePackageHelper() {
    }

    private static class JavaOther
    extends Helper {
        private final SecurityActions stack = SecurityActions.stack;
        private final Method definePackage = this.getDefinePackageMethod();

        private JavaOther() {
        }

        private Method getDefinePackageMethod() {
            if (this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getDeclaredMethod(ClassLoader.class, "definePackage", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class});
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("cannot initialize", e);
            }
        }

        @Override
        Package definePackage(ClassLoader loader, String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion, String implVendor, URL sealBase) throws IllegalArgumentException {
            if (this.stack.getCallerClass() != DefinePackageHelper.class) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                this.definePackage.setAccessible(true);
                return (Package)this.definePackage.invoke(loader, name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
            } catch (Throwable e) {
                Throwable t;
                if (e instanceof InvocationTargetException && (t = ((InvocationTargetException)e).getTargetException()) instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException)t;
                }
                if (e instanceof RuntimeException) {
                    throw (RuntimeException)e;
                }
                return null;
            }
        }
    }

    private static class Java7
    extends Helper {
        private final SecurityActions stack = SecurityActions.stack;
        private final MethodHandle definePackage = this.getDefinePackageMethodHandle();

        private Java7() {
        }

        private MethodHandle getDefinePackageMethodHandle() {
            if (this.stack.getCallerClass() != this.getClass()) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return SecurityActions.getMethodHandle(ClassLoader.class, "definePackage", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, URL.class});
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("cannot initialize", e);
            }
        }

        @Override
        Package definePackage(ClassLoader loader, String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion, String implVendor, URL sealBase) throws IllegalArgumentException {
            if (this.stack.getCallerClass() != DefinePackageHelper.class) {
                throw new IllegalAccessError("Access denied for caller.");
            }
            try {
                return (Package)this.definePackage.invokeWithArguments(loader, name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
            } catch (Throwable e) {
                if (e instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException)e;
                }
                if (e instanceof RuntimeException) {
                    throw (RuntimeException)e;
                }
                return null;
            }
        }
    }

    private static class Java9
    extends Helper {
        private Java9() {
        }

        @Override
        Package definePackage(ClassLoader loader, String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion, String implVendor, URL sealBase) throws IllegalArgumentException {
            throw new RuntimeException("define package has been disabled for jigsaw");
        }
    }

    private static abstract class Helper {
        private Helper() {
        }

        abstract Package definePackage(ClassLoader var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, URL var9) throws IllegalArgumentException;
    }
}

