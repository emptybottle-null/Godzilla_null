/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjArray
implements Serializable {
    static final long serialVersionUID = 4174889037736658296L;
    private int size;
    private boolean sealed;
    private static final int FIELDS_STORE_SIZE = 5;
    private transient Object f0;
    private transient Object f1;
    private transient Object f2;
    private transient Object f3;
    private transient Object f4;
    private transient Object[] data;

    public final boolean isSealed() {
        return this.sealed;
    }

    public final void seal() {
        this.sealed = true;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final int size() {
        return this.size;
    }

    public final void setSize(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException();
        }
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        int N = this.size;
        if (newSize < N) {
            for (int i = newSize; i != N; ++i) {
                this.setImpl(i, null);
            }
        } else if (newSize > N && newSize > 5) {
            this.ensureCapacity(newSize);
        }
        this.size = newSize;
    }

    public final Object get(int index) {
        if (0 > index || index >= this.size) {
            throw ObjArray.onInvalidIndex(index, this.size);
        }
        return this.getImpl(index);
    }

    public final void set(int index, Object value) {
        if (0 > index || index >= this.size) {
            throw ObjArray.onInvalidIndex(index, this.size);
        }
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        this.setImpl(index, value);
    }

    private Object getImpl(int index) {
        switch (index) {
            case 0: {
                return this.f0;
            }
            case 1: {
                return this.f1;
            }
            case 2: {
                return this.f2;
            }
            case 3: {
                return this.f3;
            }
            case 4: {
                return this.f4;
            }
        }
        return this.data[index - 5];
    }

    private void setImpl(int index, Object value) {
        switch (index) {
            case 0: {
                this.f0 = value;
                break;
            }
            case 1: {
                this.f1 = value;
                break;
            }
            case 2: {
                this.f2 = value;
                break;
            }
            case 3: {
                this.f3 = value;
                break;
            }
            case 4: {
                this.f4 = value;
                break;
            }
            default: {
                this.data[index - 5] = value;
            }
        }
    }

    public int indexOf(Object obj) {
        int N = this.size;
        for (int i = 0; i != N; ++i) {
            Object current = this.getImpl(i);
            if (current != obj && (current == null || !current.equals(obj))) continue;
            return i;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int i = this.size;
        while (i != 0) {
            Object current;
            if ((current = this.getImpl(--i)) != obj && (current == null || !current.equals(obj))) continue;
            return i;
        }
        return -1;
    }

    public final Object peek() {
        int N = this.size;
        if (N == 0) {
            throw ObjArray.onEmptyStackTopRead();
        }
        return this.getImpl(N - 1);
    }

    public final Object pop() {
        Object top;
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        int N = this.size;
        switch (--N) {
            case -1: {
                throw ObjArray.onEmptyStackTopRead();
            }
            case 0: {
                top = this.f0;
                this.f0 = null;
                break;
            }
            case 1: {
                top = this.f1;
                this.f1 = null;
                break;
            }
            case 2: {
                top = this.f2;
                this.f2 = null;
                break;
            }
            case 3: {
                top = this.f3;
                this.f3 = null;
                break;
            }
            case 4: {
                top = this.f4;
                this.f4 = null;
                break;
            }
            default: {
                top = this.data[N - 5];
                this.data[N - 5] = null;
            }
        }
        this.size = N;
        return top;
    }

    public final void push(Object value) {
        this.add(value);
    }

    public final void add(Object value) {
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        int N = this.size;
        if (N >= 5) {
            this.ensureCapacity(N + 1);
        }
        this.size = N + 1;
        this.setImpl(N, value);
    }

    public final void add(int index, Object value) {
        int N = this.size;
        if (0 > index || index > N) {
            throw ObjArray.onInvalidIndex(index, N + 1);
        }
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        switch (index) {
            case 0: {
                if (N == 0) {
                    this.f0 = value;
                    break;
                }
                Object tmp = this.f0;
                this.f0 = value;
                value = tmp;
            }
            case 1: {
                if (N == 1) {
                    this.f1 = value;
                    break;
                }
                Object tmp = this.f1;
                this.f1 = value;
                value = tmp;
            }
            case 2: {
                if (N == 2) {
                    this.f2 = value;
                    break;
                }
                Object tmp = this.f2;
                this.f2 = value;
                value = tmp;
            }
            case 3: {
                if (N == 3) {
                    this.f3 = value;
                    break;
                }
                Object tmp = this.f3;
                this.f3 = value;
                value = tmp;
            }
            case 4: {
                if (N == 4) {
                    this.f4 = value;
                    break;
                }
                Object tmp = this.f4;
                this.f4 = value;
                value = tmp;
                index = 5;
            }
            default: {
                this.ensureCapacity(N + 1);
                if (index != N) {
                    System.arraycopy(this.data, index - 5, this.data, index - 5 + 1, N - index);
                }
                this.data[index - 5] = value;
            }
        }
        this.size = N + 1;
    }

    public final void remove(int index) {
        int N = this.size;
        if (0 > index || index >= N) {
            throw ObjArray.onInvalidIndex(index, N);
        }
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        --N;
        switch (index) {
            case 0: {
                if (N == 0) {
                    this.f0 = null;
                    break;
                }
                this.f0 = this.f1;
            }
            case 1: {
                if (N == 1) {
                    this.f1 = null;
                    break;
                }
                this.f1 = this.f2;
            }
            case 2: {
                if (N == 2) {
                    this.f2 = null;
                    break;
                }
                this.f2 = this.f3;
            }
            case 3: {
                if (N == 3) {
                    this.f3 = null;
                    break;
                }
                this.f3 = this.f4;
            }
            case 4: {
                if (N == 4) {
                    this.f4 = null;
                    break;
                }
                this.f4 = this.data[0];
                index = 5;
            }
            default: {
                if (index != N) {
                    System.arraycopy(this.data, index - 5 + 1, this.data, index - 5, N - index);
                }
                this.data[N - 5] = null;
            }
        }
        this.size = N;
    }

    public final void clear() {
        if (this.sealed) {
            throw ObjArray.onSeledMutation();
        }
        int N = this.size;
        for (int i = 0; i != N; ++i) {
            this.setImpl(i, null);
        }
        this.size = 0;
    }

    public final Object[] toArray() {
        Object[] array = new Object[this.size];
        this.toArray(array, 0);
        return array;
    }

    public final void toArray(Object[] array) {
        this.toArray(array, 0);
    }

    public final void toArray(Object[] array, int offset) {
        int N = this.size;
        switch (N) {
            default: {
                System.arraycopy(this.data, 0, array, offset + 5, N - 5);
            }
            case 5: {
                array[offset + 4] = this.f4;
            }
            case 4: {
                array[offset + 3] = this.f3;
            }
            case 3: {
                array[offset + 2] = this.f2;
            }
            case 2: {
                array[offset + 1] = this.f1;
            }
            case 1: {
                array[offset + 0] = this.f0;
            }
            case 0: 
        }
    }

    private void ensureCapacity(int minimalCapacity) {
        int required = minimalCapacity - 5;
        if (required <= 0) {
            throw new IllegalArgumentException();
        }
        if (this.data == null) {
            int alloc = 10;
            if (alloc < required) {
                alloc = required;
            }
            this.data = new Object[alloc];
        } else {
            int alloc = this.data.length;
            if (alloc < required) {
                alloc = alloc <= 5 ? 10 : (alloc *= 2);
                if (alloc < required) {
                    alloc = required;
                }
                Object[] tmp = new Object[alloc];
                if (this.size > 5) {
                    System.arraycopy(this.data, 0, tmp, 0, this.size - 5);
                }
                this.data = tmp;
            }
        }
    }

    private static RuntimeException onInvalidIndex(int index, int upperBound) {
        String msg = index + " \u2209 [0, " + upperBound + ')';
        throw new IndexOutOfBoundsException(msg);
    }

    private static RuntimeException onEmptyStackTopRead() {
        throw new RuntimeException("Empty stack");
    }

    private static RuntimeException onSeledMutation() {
        throw new IllegalStateException("Attempt to modify sealed array");
    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();
        int N = this.size;
        for (int i = 0; i != N; ++i) {
            Object obj = this.getImpl(i);
            os.writeObject(obj);
        }
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        int N = this.size;
        if (N > 5) {
            this.data = new Object[N - 5];
        }
        for (int i = 0; i != N; ++i) {
            Object obj = is.readObject();
            this.setImpl(i, obj);
        }
    }
}

