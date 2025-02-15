/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.tools.reflect.Reflection;

public class Loader
extends javassist.Loader {
    protected Reflection reflection;

    public static void main(String[] args) throws Throwable {
        Loader cl = new Loader();
        cl.run(args);
    }

    public Loader() throws CannotCompileException, NotFoundException {
        this.delegateLoadingOf("javassist.tools.reflect.Loader");
        this.reflection = new Reflection();
        ClassPool pool = ClassPool.getDefault();
        this.addTranslator(pool, this.reflection);
    }

    public boolean makeReflective(String clazz, String metaobject, String metaclass) throws CannotCompileException, NotFoundException {
        return this.reflection.makeReflective(clazz, metaobject, metaclass);
    }
}

