/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.tools.shell.Global;

class PipeThread
extends Thread {
    private boolean fromProcess;
    private InputStream from;
    private OutputStream to;

    PipeThread(boolean fromProcess, InputStream from, OutputStream to) {
        this.setDaemon(true);
        this.fromProcess = fromProcess;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        try {
            Global.pipe(this.fromProcess, this.from, this.to);
        } catch (IOException ex) {
            throw Context.throwAsScriptRuntimeEx(ex);
        }
    }
}

