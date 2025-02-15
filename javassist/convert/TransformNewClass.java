/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.convert.Transformer;

public final class TransformNewClass
extends Transformer {
    private int nested;
    private String classname;
    private String newClassName;
    private int newClassIndex;
    private int newMethodNTIndex;
    private int newMethodIndex;

    public TransformNewClass(Transformer next, String classname, String newClassName) {
        super(next);
        this.classname = classname;
        this.newClassName = newClassName;
    }

    @Override
    public void initialize(ConstPool cp, CodeAttribute attr) {
        this.nested = 0;
        this.newMethodIndex = 0;
        this.newMethodNTIndex = 0;
        this.newClassIndex = 0;
    }

    @Override
    public int transform(CtClass clazz, int pos, CodeIterator iterator, ConstPool cp) throws CannotCompileException {
        int index;
        int typedesc;
        int c = iterator.byteAt(pos);
        if (c == 187) {
            int index2 = iterator.u16bitAt(pos + 1);
            if (cp.getClassInfo(index2).equals(this.classname)) {
                if (iterator.byteAt(pos + 3) != 89) {
                    throw new CannotCompileException("NEW followed by no DUP was found");
                }
                if (this.newClassIndex == 0) {
                    this.newClassIndex = cp.addClassInfo(this.newClassName);
                }
                iterator.write16bit(this.newClassIndex, pos + 1);
                ++this.nested;
            }
        } else if (c == 183 && (typedesc = cp.isConstructor(this.classname, index = iterator.u16bitAt(pos + 1))) != 0 && this.nested > 0) {
            int nt = cp.getMethodrefNameAndType(index);
            if (this.newMethodNTIndex != nt) {
                this.newMethodNTIndex = nt;
                this.newMethodIndex = cp.addMethodrefInfo(this.newClassIndex, nt);
            }
            iterator.write16bit(this.newMethodIndex, pos + 1);
            --this.nested;
        }
        return pos;
    }
}

