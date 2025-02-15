/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;

public class FileAppender
extends WriterAppender {
    protected boolean fileAppend = true;
    protected String fileName = null;
    protected boolean bufferedIO = false;
    protected int bufferSize = 8192;

    public FileAppender() {
    }

    public FileAppender(Layout layout, String filename, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        this.layout = layout;
        this.setFile(filename, append, bufferedIO, bufferSize);
    }

    public FileAppender(Layout layout, String filename, boolean append) throws IOException {
        this.layout = layout;
        this.setFile(filename, append, false, this.bufferSize);
    }

    public FileAppender(Layout layout, String filename) throws IOException {
        this(layout, filename, true);
    }

    public void setFile(String file) {
        String val;
        this.fileName = val = file.trim();
    }

    public boolean getAppend() {
        return this.fileAppend;
    }

    public String getFile() {
        return this.fileName;
    }

    public void activateOptions() {
        if (this.fileName != null) {
            try {
                this.setFile(this.fileName, this.fileAppend, this.bufferedIO, this.bufferSize);
            } catch (IOException e) {
                this.errorHandler.error("setFile(" + this.fileName + "," + this.fileAppend + ") call failed.", e, 4);
            }
        } else {
            LogLog.warn("File option not set for appender [" + this.name + "].");
            LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
        }
    }

    protected void closeFile() {
        if (this.qw != null) {
            try {
                this.qw.close();
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("Could not close " + this.qw, e);
            }
        }
    }

    public boolean getBufferedIO() {
        return this.bufferedIO;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setAppend(boolean flag) {
        this.fileAppend = flag;
    }

    public void setBufferedIO(boolean bufferedIO) {
        this.bufferedIO = bufferedIO;
        if (bufferedIO) {
            this.immediateFlush = false;
        }
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        LogLog.debug("setFile called: " + fileName + ", " + append);
        if (bufferedIO) {
            this.setImmediateFlush(false);
        }
        this.reset();
        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(fileName, append);
        } catch (FileNotFoundException ex) {
            String parentName = new File(fileName).getParent();
            if (parentName != null) {
                File parentDir = new File(parentName);
                if (!parentDir.exists() && parentDir.mkdirs()) {
                    ostream = new FileOutputStream(fileName, append);
                }
                throw ex;
            }
            throw ex;
        }
        Writer fw = this.createWriter(ostream);
        if (bufferedIO) {
            fw = new BufferedWriter(fw, bufferSize);
        }
        this.setQWForFiles(fw);
        this.fileName = fileName;
        this.fileAppend = append;
        this.bufferedIO = bufferedIO;
        this.bufferSize = bufferSize;
        this.writeHeader();
        LogLog.debug("setFile ended");
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new QuietWriter(writer, this.errorHandler);
    }

    protected void reset() {
        this.closeFile();
        this.fileName = null;
        super.reset();
    }
}

