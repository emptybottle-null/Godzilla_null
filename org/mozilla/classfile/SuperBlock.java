/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.classfile;

import org.mozilla.classfile.ConstantPool;
import org.mozilla.classfile.TypeInfo;

final class SuperBlock {
    private int index;
    private int start;
    private int end;
    private int[] locals;
    private int[] stack;
    private boolean isInitialized;
    private boolean isInQueue;

    SuperBlock(int index, int start, int end, int[] initialLocals) {
        this.index = index;
        this.start = start;
        this.end = end;
        this.locals = new int[initialLocals.length];
        System.arraycopy(initialLocals, 0, this.locals, 0, initialLocals.length);
        this.stack = new int[0];
        this.isInitialized = false;
        this.isInQueue = false;
    }

    int getIndex() {
        return this.index;
    }

    int[] getLocals() {
        int[] copy = new int[this.locals.length];
        System.arraycopy(this.locals, 0, copy, 0, this.locals.length);
        return copy;
    }

    int[] getTrimmedLocals() {
        int last;
        for (last = this.locals.length - 1; last >= 0 && this.locals[last] == 0 && !TypeInfo.isTwoWords(this.locals[last - 1]); --last) {
        }
        int size = ++last;
        for (int i = 0; i < last; ++i) {
            if (!TypeInfo.isTwoWords(this.locals[i])) continue;
            --size;
        }
        int[] copy = new int[size];
        int i = 0;
        int j = 0;
        while (i < size) {
            copy[i] = this.locals[j];
            if (TypeInfo.isTwoWords(this.locals[j])) {
                ++j;
            }
            ++i;
            ++j;
        }
        return copy;
    }

    int[] getStack() {
        int[] copy = new int[this.stack.length];
        System.arraycopy(this.stack, 0, copy, 0, this.stack.length);
        return copy;
    }

    boolean merge(int[] locals, int localsTop, int[] stack, int stackTop, ConstantPool pool) {
        if (!this.isInitialized) {
            System.arraycopy(locals, 0, this.locals, 0, localsTop);
            this.stack = new int[stackTop];
            System.arraycopy(stack, 0, this.stack, 0, stackTop);
            this.isInitialized = true;
            return true;
        }
        if (this.locals.length == localsTop && this.stack.length == stackTop) {
            boolean localsChanged = this.mergeState(this.locals, locals, localsTop, pool);
            boolean stackChanged = this.mergeState(this.stack, stack, stackTop, pool);
            return localsChanged || stackChanged;
        }
        throw new IllegalArgumentException("bad merge attempt");
    }

    private boolean mergeState(int[] current, int[] incoming, int size, ConstantPool pool) {
        boolean changed = false;
        for (int i = 0; i < size; ++i) {
            int currentType = current[i];
            current[i] = TypeInfo.merge(current[i], incoming[i], pool);
            if (currentType == current[i]) continue;
            changed = true;
        }
        return changed;
    }

    int getStart() {
        return this.start;
    }

    int getEnd() {
        return this.end;
    }

    public String toString() {
        return "sb " + this.index;
    }

    boolean isInitialized() {
        return this.isInitialized;
    }

    void setInitialized(boolean b) {
        this.isInitialized = b;
    }

    boolean isInQueue() {
        return this.isInQueue;
    }

    void setInQueue(boolean b) {
        this.isInQueue = b;
    }
}

