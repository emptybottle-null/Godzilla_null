/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

class ByteVector
implements Cloneable {
    private byte[] buffer = new byte[64];
    private int size = 0;

    public Object clone() throws CloneNotSupportedException {
        ByteVector bv = (ByteVector)super.clone();
        bv.buffer = (byte[])this.buffer.clone();
        return bv;
    }

    public final int getSize() {
        return this.size;
    }

    public final byte[] copy() {
        byte[] b = new byte[this.size];
        System.arraycopy(this.buffer, 0, b, 0, this.size);
        return b;
    }

    public int read(int offset) {
        if (offset < 0 || this.size <= offset) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        return this.buffer[offset];
    }

    public void write(int offset, int value) {
        if (offset < 0 || this.size <= offset) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        this.buffer[offset] = (byte)value;
    }

    public void add(int code) {
        this.addGap(1);
        this.buffer[this.size - 1] = (byte)code;
    }

    public void add(int b1, int b2) {
        this.addGap(2);
        this.buffer[this.size - 2] = (byte)b1;
        this.buffer[this.size - 1] = (byte)b2;
    }

    public void add(int b1, int b2, int b3, int b4) {
        this.addGap(4);
        this.buffer[this.size - 4] = (byte)b1;
        this.buffer[this.size - 3] = (byte)b2;
        this.buffer[this.size - 2] = (byte)b3;
        this.buffer[this.size - 1] = (byte)b4;
    }

    public void addGap(int length) {
        if (this.size + length > this.buffer.length) {
            int newSize = this.size << 1;
            if (newSize < this.size + length) {
                newSize = this.size + length;
            }
            byte[] newBuf = new byte[newSize];
            System.arraycopy(this.buffer, 0, newBuf, 0, this.size);
            this.buffer = newBuf;
        }
        this.size += length;
    }
}

