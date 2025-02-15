/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.io.IOUtils;

public class LineIterator
implements Iterator<String>,
Closeable {
    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished;

    public LineIterator(Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        this.bufferedReader = reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    @Override
    public boolean hasNext() {
        if (this.cachedLine != null) {
            return true;
        }
        if (this.finished) {
            return false;
        }
        try {
            String line;
            do {
                if ((line = this.bufferedReader.readLine()) != null) continue;
                this.finished = true;
                return false;
            } while (!this.isValidLine(line));
            this.cachedLine = line;
            return true;
        } catch (IOException ioe) {
            IOUtils.closeQuietly((Closeable)this, ioe::addSuppressed);
            throw new IllegalStateException(ioe);
        }
    }

    protected boolean isValidLine(String line) {
        return true;
    }

    @Override
    public String next() {
        return this.nextLine();
    }

    public String nextLine() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String currentLine = this.cachedLine;
        this.cachedLine = null;
        return currentLine;
    }

    @Override
    public void close() throws IOException {
        this.finished = true;
        this.cachedLine = null;
        IOUtils.close((Closeable)this.bufferedReader);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }

    @Deprecated
    public static void closeQuietly(LineIterator iterator) {
        IOUtils.closeQuietly((Closeable)iterator);
    }
}

