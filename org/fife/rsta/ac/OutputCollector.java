/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OutputCollector
implements Runnable {
    private InputStream in;
    private StringBuilder sb;

    public OutputCollector(InputStream in) {
        this(in, true);
    }

    public OutputCollector(InputStream in, StringBuilder sb) {
        this.in = in;
        this.sb = sb;
    }

    public OutputCollector(InputStream in, boolean collect) {
        this.in = in;
        if (collect) {
            this.sb = new StringBuilder();
        }
    }

    public StringBuilder getOutput() {
        return this.sb;
    }

    protected void handleLineRead(String line) {
        if (this.sb != null) {
            this.sb.append(line).append('\n');
        }
    }

    @Override
    public void run() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(this.in));){
            String line;
            while ((line = r.readLine()) != null) {
                this.handleLineRead(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

