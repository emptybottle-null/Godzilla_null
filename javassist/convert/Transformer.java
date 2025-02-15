/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

public abstract class Transformer
implements Opcode {
    private Transformer next;

    public Transformer(Transformer t) {
        this.next = t;
    }

    public Transformer getNext() {
        return this.next;
    }

    public void initialize(ConstPool cp, CodeAttribute attr) {
    }

    public void initialize(ConstPool cp, CtClass clazz, MethodInfo minfo) throws CannotCompileException {
        this.initialize(cp, minfo.getCodeAttribute());
    }

    public void clean() {
    }

    public abstract int transform(CtClass var1, int var2, CodeIterator var3, ConstPool var4) throws CannotCompileException, BadBytecode;

    public int extraLocals() {
        return 0;
    }

    public int extraStack() {
        return 0;
    }
}

