/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.ClassPoolTail;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.bytecode.ClassFile;

public class Loader
extends ClassLoader {
    private HashMap<String, ClassLoader> notDefinedHere;
    private Vector<String> notDefinedPackages;
    private ClassPool source;
    private Translator translator;
    private ProtectionDomain domain;
    public boolean doDelegation = true;

    public Loader() {
        this((ClassPool)null);
    }

    public Loader(ClassPool cp) {
        this.init(cp);
    }

    public Loader(ClassLoader parent, ClassPool cp) {
        super(parent);
        this.init(cp);
    }

    private void init(ClassPool cp) {
        this.notDefinedHere = new HashMap();
        this.notDefinedPackages = new Vector();
        this.source = cp;
        this.translator = null;
        this.domain = null;
        this.delegateLoadingOf("javassist.Loader");
    }

    public void delegateLoadingOf(String classname) {
        if (classname.endsWith(".")) {
            this.notDefinedPackages.addElement(classname);
        } else {
            this.notDefinedHere.put(classname, this);
        }
    }

    public void setDomain(ProtectionDomain d) {
        this.domain = d;
    }

    public void setClassPool(ClassPool cp) {
        this.source = cp;
    }

    public void addTranslator(ClassPool cp, Translator t) throws NotFoundException, CannotCompileException {
        this.source = cp;
        this.translator = t;
        t.start(cp);
    }

    public static void main(String[] args) throws Throwable {
        Loader cl = new Loader();
        cl.run(args);
    }

    public void run(String[] args) throws Throwable {
        if (args.length >= 1) {
            this.run(args[0], Arrays.copyOfRange(args, 1, args.length));
        }
    }

    public void run(String classname, String[] args) throws Throwable {
        Class<?> c = this.loadClass(classname);
        try {
            c.getDeclaredMethod("main", String[].class).invoke(null, new Object[]{args});
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassFormatError, ClassNotFoundException {
        String string = name = name.intern();
        synchronized (string) {
            Class<?> c = this.findLoadedClass(name);
            if (c == null) {
                c = this.loadClassByDelegation(name);
            }
            if (c == null) {
                c = this.findClass(name);
            }
            if (c == null) {
                c = this.delegateToParent(name);
            }
            if (resolve) {
                this.resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String pname;
        byte[] classfile;
        block11: {
            try {
                if (this.source != null) {
                    if (this.translator != null) {
                        this.translator.onLoad(this.source, name);
                    }
                    try {
                        classfile = this.source.get(name).toBytecode();
                        break block11;
                    } catch (NotFoundException e) {
                        return null;
                    }
                }
                String jarname = "/" + name.replace('.', '/') + ".class";
                InputStream in = this.getClass().getResourceAsStream(jarname);
                if (in == null) {
                    return null;
                }
                classfile = ClassPoolTail.readStream(in);
            } catch (Exception e) {
                throw new ClassNotFoundException("caught an exception while obtaining a class file for " + name, e);
            }
        }
        int i = name.lastIndexOf(46);
        if (i != -1 && this.isDefinedPackage(pname = name.substring(0, i))) {
            try {
                this.definePackage(pname, null, null, null, null, null, null, null);
            } catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        if (this.domain == null) {
            return this.defineClass(name, classfile, 0, classfile.length);
        }
        return this.defineClass(name, classfile, 0, classfile.length, this.domain);
    }

    private boolean isDefinedPackage(String name) {
        if (ClassFile.MAJOR_VERSION >= 53) {
            return this.getDefinedPackage(name) == null;
        }
        return this.getPackage(name) == null;
    }

    protected Class<?> loadClassByDelegation(String name) throws ClassNotFoundException {
        Class<?> c = null;
        if (this.doDelegation && (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("sun.") || name.startsWith("com.sun.") || name.startsWith("org.w3c.") || name.startsWith("org.xml.") || this.notDelegated(name))) {
            c = this.delegateToParent(name);
        }
        return c;
    }

    private boolean notDelegated(String name) {
        if (this.notDefinedHere.containsKey(name)) {
            return true;
        }
        for (String pack : this.notDefinedPackages) {
            if (!name.startsWith(pack)) continue;
            return true;
        }
        return false;
    }

    protected Class<?> delegateToParent(String classname) throws ClassNotFoundException {
        ClassLoader cl = this.getParent();
        if (cl != null) {
            return cl.loadClass(classname);
        }
        return this.findSystemClass(classname);
    }

    public static class Simple
    extends ClassLoader {
        public Simple() {
        }

        public Simple(ClassLoader parent) {
            super(parent);
        }

        public Class<?> invokeDefineClass(CtClass cc) throws IOException, CannotCompileException {
            byte[] code = cc.toBytecode();
            return this.defineClass(cc.getName(), code, 0, code.length);
        }
    }
}

