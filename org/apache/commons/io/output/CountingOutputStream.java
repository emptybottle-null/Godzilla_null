/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.OutputStream;
import org.apache.commons.io.output.ProxyOutputStream;

public class CountingOutputStream
extends ProxyOutputStream {
    private long count;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    protected synchronized void beforeWrite(int n) {
        this.count += (long)n;
    }

    public int getCount() {
        long result = this.getByteCount();
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int)result;
    }

    public int resetCount() {
        long result = this.resetByteCount();
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
        }
        return (int)result;
    }

    public synchronized long getByteCount() {
        return this.count;
    }

    public synchronized long resetByteCount() {
        long tmp = this.count;
        this.count = 0L;
        return tmp;
    }
}

