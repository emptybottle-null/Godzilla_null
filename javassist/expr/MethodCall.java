/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.expr;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.expr.Expr;

public class MethodCall
extends Expr {
    protected MethodCall(int pos, CodeIterator i, CtClass declaring, MethodInfo m) {
        super(pos, i, declaring, m);
    }

    private int getNameAndType(ConstPool cp) {
        int pos = this.currentPos;
        int c = this.iterator.byteAt(pos);
        int index = this.iterator.u16bitAt(pos + 1);
        if (c == 185) {
            return cp.getInterfaceMethodrefNameAndType(index);
        }
        return cp.getMethodrefNameAndType(index);
    }

    @Override
    public CtBehavior where() {
        return super.where();
    }

    @Override
    public int getLineNumber() {
        return super.getLineNumber();
    }

    @Override
    public String getFileName() {
        return super.getFileName();
    }

    protected CtClass getCtClass() throws NotFoundException {
        return this.thisClass.getClassPool().get(this.getClassName());
    }

    public String getClassName() {
        ConstPool cp = this.getConstPool();
        int pos = this.currentPos;
        int c = this.iterator.byteAt(pos);
        int index = this.iterator.u16bitAt(pos + 1);
        String cname = c == 185 ? cp.getInterfaceMethodrefClassName(index) : cp.getMethodrefClassName(index);
        if (cname.charAt(0) == '[') {
            cname = Descriptor.toClassName(cname);
        }
        return cname;
    }

    public String getMethodName() {
        ConstPool cp = this.getConstPool();
        int nt = this.getNameAndType(cp);
        return cp.getUtf8Info(cp.getNameAndTypeName(nt));
    }

    public CtMethod getMethod() throws NotFoundException {
        return this.getCtClass().getMethod(this.getMethodName(), this.getSignature());
    }

    public String getSignature() {
        ConstPool cp = this.getConstPool();
        int nt = this.getNameAndType(cp);
        return cp.getUtf8Info(cp.getNameAndTypeDescriptor(nt));
    }

    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }

    public boolean isSuper() {
        return this.iterator.byteAt(this.currentPos) == 183 && !this.where().getDeclaringClass().getName().equals(this.getClassName());
    }

    @Override
    public void replace(String statement) throws CannotCompileException {
        String signature;
        String methodname;
        String classname;
        int opcodeSize;
        this.thisClass.getClassFile();
        ConstPool constPool = this.getConstPool();
        int pos = this.currentPos;
        int index = this.iterator.u16bitAt(pos + 1);
        int c = this.iterator.byteAt(pos);
        if (c == 185) {
            opcodeSize = 5;
            classname = constPool.getInterfaceMethodrefClassName(index);
            methodname = constPool.getInterfaceMethodrefName(index);
            signature = constPool.getInterfaceMethodrefType(index);
        } else if (c == 184 || c == 183 || c == 182) {
            opcodeSize = 3;
            classname = constPool.getMethodrefClassName(index);
            methodname = constPool.getMethodrefName(index);
            signature = constPool.getMethodrefType(index);
        } else {
            throw new CannotCompileException("not method invocation");
        }
        Javac jc = new Javac(this.thisClass);
        ClassPool cp = this.thisClass.getClassPool();
        CodeAttribute ca = this.iterator.get();
        try {
            CtClass[] params = Descriptor.getParameterTypes(signature, cp);
            CtClass retType = Descriptor.getReturnType(signature, cp);
            int paramVar = ca.getMaxLocals();
            jc.recordParams(classname, params, true, paramVar, this.withinStatic());
            int retVar = jc.recordReturnType(retType, true);
            if (c == 184) {
                jc.recordStaticProceed(classname, methodname);
            } else if (c == 183) {
                jc.recordSpecialProceed("$0", classname, methodname, signature, index);
            } else {
                jc.recordProceed("$0", methodname);
            }
            MethodCall.checkResultValue(retType, statement);
            Bytecode bytecode = jc.getBytecode();
            MethodCall.storeStack(params, c == 184, paramVar, bytecode);
            jc.recordLocalVariables(ca, pos);
            if (retType != CtClass.voidType) {
                bytecode.addConstZero(retType);
                bytecode.addStore(retVar, retType);
            }
            jc.compileStmnt(statement);
            if (retType != CtClass.voidType) {
                bytecode.addLoad(retVar, retType);
            }
            this.replace0(pos, bytecode, opcodeSize);
        } catch (CompileError e) {
            throw new CannotCompileException(e);
        } catch (NotFoundException e) {
            throw new CannotCompileException(e);
        } catch (BadBytecode e) {
            throw new CannotCompileException("broken method");
        }
    }
}

