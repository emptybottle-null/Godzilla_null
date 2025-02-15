/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.analysis;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import javassist.bytecode.analysis.Executor;
import javassist.bytecode.analysis.Frame;
import javassist.bytecode.analysis.IntQueue;
import javassist.bytecode.analysis.Subroutine;
import javassist.bytecode.analysis.SubroutineScanner;
import javassist.bytecode.analysis.Type;
import javassist.bytecode.analysis.Util;

public class Analyzer
implements Opcode {
    private final SubroutineScanner scanner = new SubroutineScanner();
    private CtClass clazz;
    private ExceptionInfo[] exceptions;
    private Frame[] frames;
    private Subroutine[] subroutines;

    public Frame[] analyze(CtClass clazz, MethodInfo method) throws BadBytecode {
        this.clazz = clazz;
        CodeAttribute codeAttribute = method.getCodeAttribute();
        if (codeAttribute == null) {
            return null;
        }
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        int codeLength = codeAttribute.getCodeLength();
        CodeIterator iter = codeAttribute.iterator();
        IntQueue queue = new IntQueue();
        this.exceptions = this.buildExceptionInfo(method);
        this.subroutines = this.scanner.scan(method);
        Executor executor = new Executor(clazz.getClassPool(), method.getConstPool());
        this.frames = new Frame[codeLength];
        this.frames[iter.lookAhead()] = this.firstFrame(method, maxLocals, maxStack);
        queue.add(iter.next());
        while (!queue.isEmpty()) {
            this.analyzeNextEntry(method, iter, queue, executor);
        }
        return this.frames;
    }

    public Frame[] analyze(CtMethod method) throws BadBytecode {
        return this.analyze(method.getDeclaringClass(), method.getMethodInfo2());
    }

    private void analyzeNextEntry(MethodInfo method, CodeIterator iter, IntQueue queue, Executor executor) throws BadBytecode {
        int pos = queue.take();
        iter.move(pos);
        iter.next();
        Frame frame = this.frames[pos].copy();
        Subroutine subroutine = this.subroutines[pos];
        try {
            executor.execute(method, pos, iter, frame, subroutine);
        } catch (RuntimeException e) {
            throw new BadBytecode(e.getMessage() + "[pos = " + pos + "]", (Throwable)e);
        }
        int opcode = iter.byteAt(pos);
        if (opcode == 170) {
            this.mergeTableSwitch(queue, pos, iter, frame);
        } else if (opcode == 171) {
            this.mergeLookupSwitch(queue, pos, iter, frame);
        } else if (opcode == 169) {
            this.mergeRet(queue, iter, pos, frame, subroutine);
        } else if (Util.isJumpInstruction(opcode)) {
            int target = Util.getJumpTarget(pos, iter);
            if (Util.isJsr(opcode)) {
                this.mergeJsr(queue, this.frames[pos], this.subroutines[target], pos, this.lookAhead(iter, pos));
            } else if (!Util.isGoto(opcode)) {
                this.merge(queue, frame, this.lookAhead(iter, pos));
            }
            this.merge(queue, frame, target);
        } else if (opcode != 191 && !Util.isReturn(opcode)) {
            this.merge(queue, frame, this.lookAhead(iter, pos));
        }
        this.mergeExceptionHandlers(queue, method, pos, frame);
    }

    private ExceptionInfo[] buildExceptionInfo(MethodInfo method) {
        ConstPool constPool = method.getConstPool();
        ClassPool classes = this.clazz.getClassPool();
        ExceptionTable table = method.getCodeAttribute().getExceptionTable();
        ExceptionInfo[] exceptions = new ExceptionInfo[table.size()];
        for (int i = 0; i < table.size(); ++i) {
            Type type;
            int index = table.catchType(i);
            try {
                type = index == 0 ? Type.THROWABLE : Type.get(classes.get(constPool.getClassInfo(index)));
            } catch (NotFoundException e) {
                throw new IllegalStateException(e.getMessage());
            }
            exceptions[i] = new ExceptionInfo(table.startPc(i), table.endPc(i), table.handlerPc(i), type);
        }
        return exceptions;
    }

    private Frame firstFrame(MethodInfo method, int maxLocals, int maxStack) {
        CtClass[] parameters;
        int pos = 0;
        Frame first = new Frame(maxLocals, maxStack);
        if ((method.getAccessFlags() & 8) == 0) {
            first.setLocal(pos++, Type.get(this.clazz));
        }
        try {
            parameters = Descriptor.getParameterTypes(method.getDescriptor(), this.clazz.getClassPool());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < parameters.length; ++i) {
            Type type = this.zeroExtend(Type.get(parameters[i]));
            first.setLocal(pos++, type);
            if (type.getSize() != 2) continue;
            first.setLocal(pos++, Type.TOP);
        }
        return first;
    }

    private int getNext(CodeIterator iter, int of, int restore) throws BadBytecode {
        iter.move(of);
        iter.next();
        int next = iter.lookAhead();
        iter.move(restore);
        iter.next();
        return next;
    }

    private int lookAhead(CodeIterator iter, int pos) throws BadBytecode {
        if (!iter.hasNext()) {
            throw new BadBytecode("Execution falls off end! [pos = " + pos + "]");
        }
        return iter.lookAhead();
    }

    private void merge(IntQueue queue, Frame frame, int target) {
        boolean changed;
        Frame old = this.frames[target];
        if (old == null) {
            this.frames[target] = frame.copy();
            changed = true;
        } else {
            changed = old.merge(frame);
        }
        if (changed) {
            queue.add(target);
        }
    }

    private void mergeExceptionHandlers(IntQueue queue, MethodInfo method, int pos, Frame frame) {
        for (int i = 0; i < this.exceptions.length; ++i) {
            ExceptionInfo exception = this.exceptions[i];
            if (pos < exception.start || pos >= exception.end) continue;
            Frame newFrame = frame.copy();
            newFrame.clearStack();
            newFrame.push(exception.type);
            this.merge(queue, newFrame, exception.handler);
        }
    }

    private void mergeJsr(IntQueue queue, Frame frame, Subroutine sub, int pos, int next) throws BadBytecode {
        if (sub == null) {
            throw new BadBytecode("No subroutine at jsr target! [pos = " + pos + "]");
        }
        Frame old = this.frames[next];
        boolean changed = false;
        if (old == null) {
            old = this.frames[next] = frame.copy();
            changed = true;
        } else {
            for (int i = 0; i < frame.localsLength(); ++i) {
                if (sub.isAccessed(i)) continue;
                Type oldType = old.getLocal(i);
                Type newType = frame.getLocal(i);
                if (oldType == null) {
                    old.setLocal(i, newType);
                    changed = true;
                    continue;
                }
                newType = oldType.merge(newType);
                old.setLocal(i, newType);
                if (newType.equals(oldType) && !newType.popChanged()) continue;
                changed = true;
            }
        }
        if (!old.isJsrMerged()) {
            old.setJsrMerged(true);
            changed = true;
        }
        if (changed && old.isRetMerged()) {
            queue.add(next);
        }
    }

    private void mergeLookupSwitch(IntQueue queue, int pos, CodeIterator iter, Frame frame) throws BadBytecode {
        int index = (pos & 0xFFFFFFFC) + 4;
        this.merge(queue, frame, pos + iter.s32bitAt(index));
        int npairs = iter.s32bitAt(index += 4);
        int end = npairs * 8 + (index += 4);
        index += 4;
        while (index < end) {
            int target = iter.s32bitAt(index) + pos;
            this.merge(queue, frame, target);
            index += 8;
        }
    }

    private void mergeRet(IntQueue queue, CodeIterator iter, int pos, Frame frame, Subroutine subroutine) throws BadBytecode {
        if (subroutine == null) {
            throw new BadBytecode("Ret on no subroutine! [pos = " + pos + "]");
        }
        for (int caller : subroutine.callers()) {
            int returnLoc = this.getNext(iter, caller, pos);
            boolean changed = false;
            Frame old = this.frames[returnLoc];
            if (old == null) {
                old = this.frames[returnLoc] = frame.copyStack();
                changed = true;
            } else {
                changed = old.mergeStack(frame);
            }
            for (int index : subroutine.accessed()) {
                Type newType;
                Type oldType = old.getLocal(index);
                if (oldType == (newType = frame.getLocal(index))) continue;
                old.setLocal(index, newType);
                changed = true;
            }
            if (!old.isRetMerged()) {
                old.setRetMerged(true);
                changed = true;
            }
            if (!changed || !old.isJsrMerged()) continue;
            queue.add(returnLoc);
        }
    }

    private void mergeTableSwitch(IntQueue queue, int pos, CodeIterator iter, Frame frame) throws BadBytecode {
        int index = (pos & 0xFFFFFFFC) + 4;
        this.merge(queue, frame, pos + iter.s32bitAt(index));
        int low = iter.s32bitAt(index += 4);
        int high = iter.s32bitAt(index += 4);
        int end = (high - low + 1) * 4 + (index += 4);
        while (index < end) {
            int target = iter.s32bitAt(index) + pos;
            this.merge(queue, frame, target);
            index += 4;
        }
    }

    private Type zeroExtend(Type type) {
        if (type == Type.SHORT || type == Type.BYTE || type == Type.CHAR || type == Type.BOOLEAN) {
            return Type.INTEGER;
        }
        return type;
    }

    private static class ExceptionInfo {
        private int end;
        private int handler;
        private int start;
        private Type type;

        private ExceptionInfo(int start, int end, int handler, Type type) {
            this.start = start;
            this.end = end;
            this.handler = handler;
            this.type = type;
        }
    }
}

