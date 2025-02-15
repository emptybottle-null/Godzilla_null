/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

public final class CtConstructor
extends CtBehavior {
    protected CtConstructor(MethodInfo minfo, CtClass declaring) {
        super(declaring, minfo);
    }

    public CtConstructor(CtClass[] parameters, CtClass declaring) {
        this((MethodInfo)null, declaring);
        ConstPool cp = declaring.getClassFile2().getConstPool();
        String desc = Descriptor.ofConstructor(parameters);
        this.methodInfo = new MethodInfo(cp, "<init>", desc);
        this.setModifiers(1);
    }

    public CtConstructor(CtConstructor src, CtClass declaring, ClassMap map) throws CannotCompileException {
        this((MethodInfo)null, declaring);
        this.copy(src, true, map);
    }

    public boolean isConstructor() {
        return this.methodInfo.isConstructor();
    }

    public boolean isClassInitializer() {
        return this.methodInfo.isStaticInitializer();
    }

    @Override
    public String getLongName() {
        return this.getDeclaringClass().getName() + (this.isConstructor() ? Descriptor.toString(this.getSignature()) : ".<clinit>()");
    }

    @Override
    public String getName() {
        if (this.methodInfo.isStaticInitializer()) {
            return "<clinit>";
        }
        return this.declaringClass.getSimpleName();
    }

    @Override
    public boolean isEmpty() {
        CodeAttribute ca = this.getMethodInfo2().getCodeAttribute();
        if (ca == null) {
            return false;
        }
        ConstPool cp = ca.getConstPool();
        CodeIterator it = ca.iterator();
        try {
            int desc;
            int pos;
            int op0 = it.byteAt(it.next());
            return op0 == 177 || op0 == 42 && it.byteAt(pos = it.next()) == 183 && (desc = cp.isConstructor(this.getSuperclassName(), it.u16bitAt(pos + 1))) != 0 && "()V".equals(cp.getUtf8Info(desc)) && it.byteAt(it.next()) == 177 && !it.hasNext();
        } catch (BadBytecode badBytecode) {
            return false;
        }
    }

    private String getSuperclassName() {
        ClassFile cf = this.declaringClass.getClassFile2();
        return cf.getSuperclass();
    }

    public boolean callsSuper() throws CannotCompileException {
        CodeAttribute codeAttr = this.methodInfo.getCodeAttribute();
        if (codeAttr != null) {
            CodeIterator it = codeAttr.iterator();
            try {
                int index = it.skipSuperConstructor();
                return index >= 0;
            } catch (BadBytecode e) {
                throw new CannotCompileException(e);
            }
        }
        return false;
    }

    @Override
    public void setBody(String src) throws CannotCompileException {
        if (src == null) {
            src = this.isClassInitializer() ? ";" : "super();";
        }
        super.setBody(src);
    }

    public void setBody(CtConstructor src, ClassMap map) throws CannotCompileException {
        CtConstructor.setBody0(src.declaringClass, src.methodInfo, this.declaringClass, this.methodInfo, map);
    }

    public void insertBeforeBody(String src) throws CannotCompileException {
        CtClass cc = this.declaringClass;
        cc.checkModify();
        if (this.isClassInitializer()) {
            throw new CannotCompileException("class initializer");
        }
        CodeAttribute ca = this.methodInfo.getCodeAttribute();
        CodeIterator iterator = ca.iterator();
        Bytecode b = new Bytecode(this.methodInfo.getConstPool(), ca.getMaxStack(), ca.getMaxLocals());
        b.setStackDepth(ca.getMaxStack());
        Javac jv = new Javac(b, cc);
        try {
            jv.recordParams(this.getParameterTypes(), false);
            jv.compileStmnt(src);
            ca.setMaxStack(b.getMaxStack());
            ca.setMaxLocals(b.getMaxLocals());
            iterator.skipConstructor();
            int pos = iterator.insertEx(b.get());
            iterator.insert(b.getExceptionTable(), pos);
            this.methodInfo.rebuildStackMapIf6(cc.getClassPool(), cc.getClassFile2());
        } catch (NotFoundException e) {
            throw new CannotCompileException(e);
        } catch (CompileError e) {
            throw new CannotCompileException(e);
        } catch (BadBytecode e) {
            throw new CannotCompileException(e);
        }
    }

    @Override
    int getStartPosOfBody(CodeAttribute ca) throws CannotCompileException {
        CodeIterator ci = ca.iterator();
        try {
            ci.skipConstructor();
            return ci.next();
        } catch (BadBytecode e) {
            throw new CannotCompileException(e);
        }
    }

    public CtMethod toMethod(String name, CtClass declaring) throws CannotCompileException {
        return this.toMethod(name, declaring, null);
    }

    public CtMethod toMethod(String name, CtClass declaring, ClassMap map) throws CannotCompileException {
        MethodInfo minfo;
        CodeAttribute ca;
        CtMethod method = new CtMethod(null, declaring);
        method.copy(this, false, map);
        if (this.isConstructor() && (ca = (minfo = method.getMethodInfo2()).getCodeAttribute()) != null) {
            CtConstructor.removeConsCall(ca);
            try {
                this.methodInfo.rebuildStackMapIf6(declaring.getClassPool(), declaring.getClassFile2());
            } catch (BadBytecode e) {
                throw new CannotCompileException(e);
            }
        }
        method.setName(name);
        return method;
    }

    private static void removeConsCall(CodeAttribute ca) throws CannotCompileException {
        CodeIterator iterator = ca.iterator();
        try {
            int pos = iterator.skipConstructor();
            if (pos >= 0) {
                int mref = iterator.u16bitAt(pos + 1);
                String desc = ca.getConstPool().getMethodrefType(mref);
                int num = Descriptor.numOfParameters(desc) + 1;
                if (num > 3) {
                    pos = iterator.insertGapAt((int)pos, (int)(num - 3), (boolean)false).position;
                }
                iterator.writeByte(87, pos++);
                iterator.writeByte(0, pos);
                iterator.writeByte(0, pos + 1);
                Descriptor.Iterator it = new Descriptor.Iterator(desc);
                while (true) {
                    it.next();
                    if (it.isParameter()) {
                        iterator.writeByte(it.is2byte() ? 88 : 87, pos++);
                        continue;
                    }
                    break;
                }
            }
        } catch (BadBytecode e) {
            throw new CannotCompileException(e);
        }
    }
}

