/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.expr;

import java.util.LinkedList;
import java.util.List;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtPrimitiveType;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.ExceptionsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import javassist.expr.ExprEditor;

public abstract class Expr
implements Opcode {
    int currentPos;
    CodeIterator iterator;
    CtClass thisClass;
    MethodInfo thisMethod;
    boolean edited;
    int maxLocals;
    int maxStack;
    static final String javaLangObject = "java.lang.Object";

    protected Expr(int pos, CodeIterator i, CtClass declaring, MethodInfo m) {
        this.currentPos = pos;
        this.iterator = i;
        this.thisClass = declaring;
        this.thisMethod = m;
    }

    public CtClass getEnclosingClass() {
        return this.thisClass;
    }

    protected final ConstPool getConstPool() {
        return this.thisMethod.getConstPool();
    }

    protected final boolean edited() {
        return this.edited;
    }

    protected final int locals() {
        return this.maxLocals;
    }

    protected final int stack() {
        return this.maxStack;
    }

    protected final boolean withinStatic() {
        return (this.thisMethod.getAccessFlags() & 8) != 0;
    }

    public CtBehavior where() {
        MethodInfo mi = this.thisMethod;
        CtBehavior[] cb = this.thisClass.getDeclaredBehaviors();
        for (int i = cb.length - 1; i >= 0; --i) {
            if (cb[i].getMethodInfo2() != mi) continue;
            return cb[i];
        }
        CtConstructor init = this.thisClass.getClassInitializer();
        if (init != null && init.getMethodInfo2() == mi) {
            return init;
        }
        for (int i = cb.length - 1; i >= 0; --i) {
            if (!this.thisMethod.getName().equals(cb[i].getMethodInfo2().getName()) || !this.thisMethod.getDescriptor().equals(cb[i].getMethodInfo2().getDescriptor())) continue;
            return cb[i];
        }
        throw new RuntimeException("fatal: not found");
    }

    public CtClass[] mayThrow() {
        String[] exceptions;
        ExceptionsAttribute ea;
        ClassPool pool = this.thisClass.getClassPool();
        ConstPool cp = this.thisMethod.getConstPool();
        LinkedList<CtClass> list = new LinkedList<CtClass>();
        try {
            CodeAttribute ca = this.thisMethod.getCodeAttribute();
            ExceptionTable et = ca.getExceptionTable();
            int pos = this.currentPos;
            int n = et.size();
            for (int i = 0; i < n; ++i) {
                int t;
                if (et.startPc(i) > pos || pos >= et.endPc(i) || (t = et.catchType(i)) <= 0) continue;
                try {
                    Expr.addClass(list, pool.get(cp.getClassInfo(t)));
                    continue;
                } catch (NotFoundException notFoundException) {
                    // empty catch block
                }
            }
        } catch (NullPointerException ca) {
            // empty catch block
        }
        if ((ea = this.thisMethod.getExceptionsAttribute()) != null && (exceptions = ea.getExceptions()) != null) {
            int n = exceptions.length;
            for (int i = 0; i < n; ++i) {
                try {
                    Expr.addClass(list, pool.get(exceptions[i]));
                    continue;
                } catch (NotFoundException notFoundException) {
                    // empty catch block
                }
            }
        }
        return list.toArray(new CtClass[list.size()]);
    }

    private static void addClass(List<CtClass> list, CtClass c) {
        if (list.contains(c)) {
            return;
        }
        list.add(c);
    }

    public int indexOfBytecode() {
        return this.currentPos;
    }

    public int getLineNumber() {
        return this.thisMethod.getLineNumber(this.currentPos);
    }

    public String getFileName() {
        ClassFile cf = this.thisClass.getClassFile2();
        if (cf == null) {
            return null;
        }
        return cf.getSourceFile();
    }

    static final boolean checkResultValue(CtClass retType, String prog) throws CannotCompileException {
        boolean hasIt;
        boolean bl = hasIt = prog.indexOf("$_") >= 0;
        if (!hasIt && retType != CtClass.voidType) {
            throw new CannotCompileException("the resulting value is not stored in $_");
        }
        return hasIt;
    }

    static final void storeStack(CtClass[] params, boolean isStaticCall, int regno, Bytecode bytecode) {
        Expr.storeStack0(0, params.length, params, regno + 1, bytecode);
        if (isStaticCall) {
            bytecode.addOpcode(1);
        }
        bytecode.addAstore(regno);
    }

    private static void storeStack0(int i, int n, CtClass[] params, int regno, Bytecode bytecode) {
        if (i >= n) {
            return;
        }
        CtClass c = params[i];
        int size = c instanceof CtPrimitiveType ? ((CtPrimitiveType)c).getDataSize() : 1;
        Expr.storeStack0(i + 1, n, params, regno + size, bytecode);
        bytecode.addStore(regno, c);
    }

    public abstract void replace(String var1) throws CannotCompileException;

    public void replace(String statement, ExprEditor recursive) throws CannotCompileException {
        this.replace(statement);
        if (recursive != null) {
            this.runEditor(recursive, this.iterator);
        }
    }

    protected void replace0(int pos, Bytecode bytecode, int size) throws BadBytecode {
        byte[] code = bytecode.get();
        this.edited = true;
        int gap = code.length - size;
        for (int i = 0; i < size; ++i) {
            this.iterator.writeByte(0, pos + i);
        }
        if (gap > 0) {
            pos = this.iterator.insertGapAt((int)pos, (int)gap, (boolean)false).position;
        }
        this.iterator.write(code, pos);
        this.iterator.insert(bytecode.getExceptionTable(), pos);
        this.maxLocals = bytecode.getMaxLocals();
        this.maxStack = bytecode.getMaxStack();
    }

    protected void runEditor(ExprEditor ed, CodeIterator oldIterator) throws CannotCompileException {
        CodeAttribute codeAttr = oldIterator.get();
        int orgLocals = codeAttr.getMaxLocals();
        int orgStack = codeAttr.getMaxStack();
        int newLocals = this.locals();
        codeAttr.setMaxStack(this.stack());
        codeAttr.setMaxLocals(newLocals);
        ExprEditor.LoopContext context = new ExprEditor.LoopContext(newLocals);
        int size = oldIterator.getCodeLength();
        int endPos = oldIterator.lookAhead();
        oldIterator.move(this.currentPos);
        if (ed.doit(this.thisClass, this.thisMethod, context, oldIterator, endPos)) {
            this.edited = true;
        }
        oldIterator.move(endPos + oldIterator.getCodeLength() - size);
        codeAttr.setMaxLocals(orgLocals);
        codeAttr.setMaxStack(orgStack);
        this.maxLocals = context.maxLocals;
        this.maxStack += context.maxStack;
    }
}

