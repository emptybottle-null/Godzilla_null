/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.TtyConnector;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ProcessTtyConnector
implements TtyConnector {
    protected final InputStream myInputStream;
    protected final OutputStream myOutputStream;
    protected final InputStreamReader myReader;
    protected final Charset myCharset;
    private Dimension myPendingTermSize;
    private final Process myProcess;

    public ProcessTtyConnector(@NotNull Process process, @NotNull Charset charset) {
        if (process == null) {
            ProcessTtyConnector.$$$reportNull$$$0(0);
        }
        if (charset == null) {
            ProcessTtyConnector.$$$reportNull$$$0(1);
        }
        this.myOutputStream = process.getOutputStream();
        this.myCharset = charset;
        this.myInputStream = process.getInputStream();
        this.myReader = new InputStreamReader(this.myInputStream, charset);
        this.myProcess = process;
    }

    @NotNull
    public Process getProcess() {
        Process process = this.myProcess;
        if (process == null) {
            ProcessTtyConnector.$$$reportNull$$$0(2);
        }
        return process;
    }

    @Override
    public void resize(@NotNull Dimension termWinSize) {
        if (termWinSize == null) {
            ProcessTtyConnector.$$$reportNull$$$0(3);
        }
        this.setPendingTermSize(termWinSize);
        if (this.isConnected()) {
            this.resizeImmediately();
            this.setPendingTermSize(null);
        }
    }

    @Deprecated
    protected void resizeImmediately() {
    }

    @Override
    public abstract String getName();

    @Override
    public int read(char[] buf, int offset, int length) throws IOException {
        return this.myReader.read(buf, offset, length);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        this.myOutputStream.write(bytes);
        this.myOutputStream.flush();
    }

    @Override
    public abstract boolean isConnected();

    @Override
    public void write(String string) throws IOException {
        this.write(string.getBytes(this.myCharset));
    }

    @Deprecated
    protected void setPendingTermSize(@Nullable Dimension pendingTermSize) {
        this.myPendingTermSize = pendingTermSize;
    }

    @Deprecated
    @Nullable
    protected Dimension getPendingTermSize() {
        return this.myPendingTermSize;
    }

    @Deprecated
    protected Dimension getPendingPixelSize() {
        return new Dimension(0, 0);
    }

    @Override
    public boolean init(Questioner q) {
        return this.isConnected();
    }

    @Override
    public void close() {
        this.myProcess.destroy();
        try {
            this.myOutputStream.close();
        } catch (IOException iOException) {
            // empty catch block
        }
        try {
            this.myInputStream.close();
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public int waitFor() throws InterruptedException {
        return this.myProcess.waitFor();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        RuntimeException runtimeException;
        Object[] objectArray;
        Object[] objectArray2;
        int n2;
        String string;
        switch (n) {
            default: {
                string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 2: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 2: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "process";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "charset";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/ProcessTtyConnector";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "termWinSize";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/ProcessTtyConnector";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "getProcess";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "resize";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 2: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

