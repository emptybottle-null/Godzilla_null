/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class CyclicBuffer {
    LoggingEvent[] ea;
    int first;
    int last;
    int numElems;
    int maxSize;

    public CyclicBuffer(int maxSize) throws IllegalArgumentException {
        if (maxSize < 1) {
            throw new IllegalArgumentException("The maxSize argument (" + maxSize + ") is not a positive integer.");
        }
        this.maxSize = maxSize;
        this.ea = new LoggingEvent[maxSize];
        this.first = 0;
        this.last = 0;
        this.numElems = 0;
    }

    public void add(LoggingEvent event) {
        this.ea[this.last] = event;
        if (++this.last == this.maxSize) {
            this.last = 0;
        }
        if (this.numElems < this.maxSize) {
            ++this.numElems;
        } else if (++this.first == this.maxSize) {
            this.first = 0;
        }
    }

    public LoggingEvent get(int i) {
        if (i < 0 || i >= this.numElems) {
            return null;
        }
        return this.ea[(this.first + i) % this.maxSize];
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public LoggingEvent get() {
        LoggingEvent r = null;
        if (this.numElems > 0) {
            --this.numElems;
            r = this.ea[this.first];
            this.ea[this.first] = null;
            if (++this.first == this.maxSize) {
                this.first = 0;
            }
        }
        return r;
    }

    public int length() {
        return this.numElems;
    }

    public void resize(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException("Negative array size [" + newSize + "] not allowed.");
        }
        if (newSize == this.numElems) {
            return;
        }
        LoggingEvent[] temp = new LoggingEvent[newSize];
        int loopLen = newSize < this.numElems ? newSize : this.numElems;
        for (int i = 0; i < loopLen; ++i) {
            temp[i] = this.ea[this.first];
            this.ea[this.first] = null;
            if (++this.first != this.numElems) continue;
            this.first = 0;
        }
        this.ea = temp;
        this.first = 0;
        this.numElems = loopLen;
        this.maxSize = newSize;
        this.last = loopLen == newSize ? 0 : loopLen;
    }
}

