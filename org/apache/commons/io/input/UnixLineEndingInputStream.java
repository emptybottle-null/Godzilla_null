/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.input.UnsupportedOperationExceptions;

public class UnixLineEndingInputStream
extends InputStream {
    private boolean slashNSeen;
    private boolean slashRSeen;
    private boolean eofSeen;
    private final InputStream target;
    private final boolean ensureLineFeedAtEndOfFile;

    public UnixLineEndingInputStream(InputStream in, boolean ensureLineFeedAtEndOfFile) {
        this.target = in;
        this.ensureLineFeedAtEndOfFile = ensureLineFeedAtEndOfFile;
    }

    private int readWithUpdate() throws IOException {
        int target = this.target.read();
        boolean bl = this.eofSeen = target == -1;
        if (this.eofSeen) {
            return target;
        }
        this.slashNSeen = target == 10;
        this.slashRSeen = target == 13;
        return target;
    }

    @Override
    public int read() throws IOException {
        boolean previousWasSlashR = this.slashRSeen;
        if (this.eofSeen) {
            return this.eofGame(previousWasSlashR);
        }
        int target = this.readWithUpdate();
        if (this.eofSeen) {
            return this.eofGame(previousWasSlashR);
        }
        if (this.slashRSeen) {
            return 10;
        }
        if (previousWasSlashR && this.slashNSeen) {
            return this.read();
        }
        return target;
    }

    private int eofGame(boolean previousWasSlashR) {
        if (previousWasSlashR || !this.ensureLineFeedAtEndOfFile) {
            return -1;
        }
        if (!this.slashNSeen) {
            this.slashNSeen = true;
            return 10;
        }
        return -1;
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.target.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        throw UnsupportedOperationExceptions.mark();
    }
}

