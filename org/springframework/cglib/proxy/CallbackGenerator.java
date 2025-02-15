/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.proxy;

import java.util.List;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.cglib.core.Signature;

interface CallbackGenerator {
    public void generate(ClassEmitter var1, Context var2, List var3) throws Exception;

    public void generateStatic(CodeEmitter var1, Context var2, List var3) throws Exception;

    public static interface Context {
        public ClassLoader getClassLoader();

        public CodeEmitter beginMethod(ClassEmitter var1, MethodInfo var2);

        public int getOriginalModifiers(MethodInfo var1);

        public int getIndex(MethodInfo var1);

        public void emitCallback(CodeEmitter var1, int var2);

        public Signature getImplSignature(MethodInfo var1);

        public void emitLoadArgsAndInvoke(CodeEmitter var1, MethodInfo var2);
    }
}

