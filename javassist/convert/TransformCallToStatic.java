/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.convert;

import javassist.CtMethod;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.convert.TransformCall;
import javassist.convert.Transformer;

public class TransformCallToStatic
extends TransformCall {
    public TransformCallToStatic(Transformer next, CtMethod origMethod, CtMethod substMethod) {
        super(next, origMethod, substMethod);
        this.methodDescriptor = origMethod.getMethodInfo2().getDescriptor();
    }

    @Override
    protected int match(int c, int pos, CodeIterator iterator, int typedesc, ConstPool cp) {
        if (this.newIndex == 0) {
            String desc = Descriptor.insertParameter(this.classname, this.methodDescriptor);
            int nt = cp.addNameAndTypeInfo(this.newMethodname, desc);
            int ci = cp.addClassInfo(this.newClassname);
            this.newIndex = cp.addMethodrefInfo(ci, nt);
            this.constPool = cp;
        }
        iterator.writeByte(184, pos);
        iterator.write16bit(this.newIndex, pos + 1);
        return pos;
    }
}

