/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassReader;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.transform.ClassFilter;
import org.springframework.cglib.transform.ClassReaderGenerator;

public abstract class AbstractClassLoader
extends ClassLoader {
    private ClassFilter filter;
    private ClassLoader classPath;
    private static ProtectionDomain DOMAIN = (ProtectionDomain)AccessController.doPrivileged(new PrivilegedAction(){

        public Object run() {
            return AbstractClassLoader.class.getProtectionDomain();
        }
    });

    protected AbstractClassLoader(ClassLoader parent, ClassLoader classPath, ClassFilter filter) {
        super(parent);
        this.filter = filter;
        this.classPath = classPath;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Class loadClass(String name) throws ClassNotFoundException {
        ClassReader r;
        Class<?> loaded = this.findLoadedClass(name);
        if (loaded != null && loaded.getClassLoader() == this) {
            return loaded;
        }
        if (!this.filter.accept(name)) {
            return super.loadClass(name);
        }
        try {
            InputStream is = this.classPath.getResourceAsStream(name.replace('.', '/') + ".class");
            if (is == null) {
                throw new ClassNotFoundException(name);
            }
            try {
                r = new ClassReader(is);
            } finally {
                is.close();
            }
        } catch (IOException e) {
            throw new ClassNotFoundException(name + ":" + e.getMessage());
        }
        try {
            DebuggingClassWriter w = new DebuggingClassWriter(2);
            this.getGenerator(r).generateClass(w);
            byte[] b = w.toByteArray();
            Class<?> c = super.defineClass(name, b, 0, b.length, DOMAIN);
            this.postProcess(c);
            return c;
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e) {
            throw e;
        } catch (Exception e) {
            throw new CodeGenerationException(e);
        }
    }

    protected ClassGenerator getGenerator(ClassReader r) {
        return new ClassReaderGenerator(r, this.attributes(), this.getFlags());
    }

    protected int getFlags() {
        return 0;
    }

    protected Attribute[] attributes() {
        return null;
    }

    protected void postProcess(Class c) {
    }
}

