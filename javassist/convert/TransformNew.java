/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.convert;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;
import javassist.convert.Transformer;

public final class TransformNew
extends Transformer {
    private int nested;
    private String classname;
    private String trapClass;
    private String trapMethod;

    public TransformNew(Transformer next, String classname, String trapClass, String trapMethod) {
        super(next);
        this.classname = classname;
        this.trapClass = trapClass;
        this.trapMethod = trapMethod;
    }

    @Override
    public void initialize(ConstPool cp, CodeAttribute attr) {
        this.nested = 0;
    }

    @Override
    public int transform(CtClass clazz, int pos, CodeIterator iterator, ConstPool cp) throws CannotCompileException {
        int index;
        int typedesc;
        int c = iterator.byteAt(pos);
        if (c == 187) {
            int index2 = iterator.u16bitAt(pos + 1);
            if (cp.getClassInfo(index2).equals(this.classname)) {
                StackMap sm;
                if (iterator.byteAt(pos + 3) != 89) {
                    throw new CannotCompileException("NEW followed by no DUP was found");
                }
                iterator.writeByte(0, pos);
                iterator.writeByte(0, pos + 1);
                iterator.writeByte(0, pos + 2);
                iterator.writeByte(0, pos + 3);
                ++this.nested;
                StackMapTable smt = (StackMapTable)iterator.get().getAttribute("StackMapTable");
                if (smt != null) {
                    smt.removeNew(pos);
                }
                if ((sm = (StackMap)iterator.get().getAttribute("StackMap")) != null) {
                    sm.removeNew(pos);
                }
            }
        } else if (c == 183 && (typedesc = cp.isConstructor(this.classname, index = iterator.u16bitAt(pos + 1))) != 0 && this.nested > 0) {
            int methodref = this.computeMethodref(typedesc, cp);
            iterator.writeByte(184, pos);
            iterator.write16bit(methodref, pos + 1);
            --this.nested;
        }
        return pos;
    }

    private int computeMethodref(int typedesc, ConstPool cp) {
        int classIndex = cp.addClassInfo(this.trapClass);
        int mnameIndex = cp.addUtf8Info(this.trapMethod);
        typedesc = cp.addUtf8Info(Descriptor.changeReturnType(this.classname, cp.getUtf8Info(typedesc)));
        return cp.addMethodrefInfo(classIndex, cp.addNameAndTypeInfo(mnameIndex, typedesc));
    }
}

