/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.MethodWrapper;
import org.springframework.cglib.core.Predicate;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;

public class DuplicatesPredicate
implements Predicate {
    private final Set unique;
    private final Set rejected;

    public DuplicatesPredicate() {
        this.unique = new HashSet();
        this.rejected = Collections.emptySet();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public DuplicatesPredicate(List allMethods) {
        this.rejected = new HashSet();
        this.unique = new HashSet();
        HashMap<Object, Method> scanned = new HashMap<Object, Method>();
        HashMap<Object, Method> suspects = new HashMap<Object, Method>();
        for (Object o : allMethods) {
            Method method = (Method)o;
            Object object = MethodWrapper.create(method);
            Method existing = (Method)scanned.get(object);
            if (existing == null) {
                scanned.put(object, method);
                continue;
            }
            if (suspects.containsKey(object) || !existing.isBridge() || method.isBridge()) continue;
            suspects.put(object, existing);
        }
        if (!suspects.isEmpty()) {
            HashSet classes = new HashSet();
            UnnecessaryBridgeFinder finder = new UnnecessaryBridgeFinder(this.rejected);
            for (Object v : suspects.values()) {
                Method m = (Method)v;
                classes.add(m.getDeclaringClass());
                finder.addSuspectMethod(m);
            }
            for (Object object : classes) {
                Class c = (Class)object;
                try {
                    InputStream is;
                    ClassLoader cl = DuplicatesPredicate.getClassLoader(c);
                    if (cl == null || (is = cl.getResourceAsStream(c.getName().replace('.', '/') + ".class")) == null) continue;
                    try {
                        new ClassReader(is).accept(finder, 6);
                    } finally {
                        is.close();
                    }
                } catch (IOException iOException) {}
            }
        }
    }

    public boolean evaluate(Object arg) {
        return !this.rejected.contains(arg) && this.unique.add(MethodWrapper.create((Method)arg));
    }

    private static ClassLoader getClassLoader(Class c) {
        ClassLoader cl = c.getClassLoader();
        if (cl == null) {
            cl = DuplicatesPredicate.class.getClassLoader();
        }
        if (cl == null) {
            cl = Thread.currentThread().getContextClassLoader();
        }
        return cl;
    }

    private static class UnnecessaryBridgeFinder
    extends ClassVisitor {
        private final Set rejected;
        private Signature currentMethodSig = null;
        private Map methods = new HashMap();

        UnnecessaryBridgeFinder(Set rejected) {
            super(Constants.ASM_API);
            this.rejected = rejected;
        }

        void addSuspectMethod(Method m) {
            this.methods.put(ReflectUtils.getSignature(m), m);
        }

        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            Signature sig = new Signature(name, desc);
            final Method currentMethod = (Method)this.methods.remove(sig);
            if (currentMethod != null) {
                this.currentMethodSig = sig;
                return new MethodVisitor(Constants.ASM_API){

                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        if (opcode == 183 && UnnecessaryBridgeFinder.this.currentMethodSig != null) {
                            Signature target = new Signature(name, desc);
                            if (target.equals(UnnecessaryBridgeFinder.this.currentMethodSig)) {
                                UnnecessaryBridgeFinder.this.rejected.add(currentMethod);
                            }
                            UnnecessaryBridgeFinder.this.currentMethodSig = null;
                        }
                    }
                };
            }
            return null;
        }
    }
}

