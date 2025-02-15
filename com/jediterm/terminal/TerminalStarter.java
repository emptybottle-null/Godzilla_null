/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.Terminal;
import com.jediterm.terminal.TerminalDataStream;
import com.jediterm.terminal.TerminalOutputStream;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.emulator.Emulator;
import com.jediterm.terminal.emulator.JediEmulator;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class TerminalStarter
implements TerminalOutputStream {
    private static final Logger LOG = Logger.getLogger(TerminalStarter.class);
    private final Emulator myEmulator;
    private final Terminal myTerminal;
    private final TtyConnector myTtyConnector;
    private final ScheduledExecutorService myEmulatorExecutor = Executors.newSingleThreadScheduledExecutor();

    public TerminalStarter(Terminal terminal, TtyConnector ttyConnector, TerminalDataStream dataStream) {
        this.myTtyConnector = ttyConnector;
        this.myTerminal = terminal;
        this.myTerminal.setTerminalOutput(this);
        this.myEmulator = this.createEmulator(dataStream, terminal);
    }

    protected JediEmulator createEmulator(TerminalDataStream dataStream, Terminal terminal) {
        return new JediEmulator(dataStream, terminal);
    }

    private void execute(Runnable runnable) {
        if (!this.myEmulatorExecutor.isShutdown()) {
            this.myEmulatorExecutor.execute(runnable);
        }
    }

    public void start() {
        try {
            while (!Thread.currentThread().isInterrupted() && this.myEmulator.hasNext()) {
                this.myEmulator.next();
            }
        } catch (InterruptedIOException e) {
            LOG.info("Terminal exiting");
        } catch (Exception e) {
            if (!this.myTtyConnector.isConnected()) {
                this.myTerminal.disconnected();
                return;
            }
            LOG.error("Caught exception in terminal thread", e);
        }
    }

    public byte[] getCode(int key, int modifiers) {
        return this.myTerminal.getCodeForKey(key, modifiers);
    }

    public void postResize(@NotNull Dimension dimension, @NotNull RequestOrigin origin) {
        if (dimension == null) {
            TerminalStarter.$$$reportNull$$$0(0);
        }
        if (origin == null) {
            TerminalStarter.$$$reportNull$$$0(1);
        }
        this.execute(() -> TerminalStarter.resize(this.myEmulator, this.myTerminal, this.myTtyConnector, dimension, origin, (millisDelay, runnable) -> this.myEmulatorExecutor.schedule((Runnable)runnable, (long)millisDelay, TimeUnit.MILLISECONDS)));
    }

    public static void resize(@NotNull Emulator emulator, @NotNull Terminal terminal, @NotNull TtyConnector ttyConnector, @NotNull Dimension newTermSize, @NotNull RequestOrigin origin, @NotNull BiConsumer<Long, Runnable> taskScheduler) {
        if (emulator == null) {
            TerminalStarter.$$$reportNull$$$0(2);
        }
        if (terminal == null) {
            TerminalStarter.$$$reportNull$$$0(3);
        }
        if (ttyConnector == null) {
            TerminalStarter.$$$reportNull$$$0(4);
        }
        if (newTermSize == null) {
            TerminalStarter.$$$reportNull$$$0(5);
        }
        if (origin == null) {
            TerminalStarter.$$$reportNull$$$0(6);
        }
        if (taskScheduler == null) {
            TerminalStarter.$$$reportNull$$$0(7);
        }
        CompletableFuture<?> promptUpdated = ((JediEmulator)emulator).getPromptUpdatedAfterResizeFuture(taskScheduler);
        terminal.resize(newTermSize, origin, promptUpdated);
        ttyConnector.resize(newTermSize);
    }

    @Override
    public void sendBytes(byte[] bytes) {
        this.execute(() -> {
            try {
                this.myTtyConnector.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void sendString(String string) {
        this.execute(() -> {
            try {
                this.myTtyConnector.write(string);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void close() {
        this.execute(() -> {
            try {
                this.myTtyConnector.close();
            } catch (Exception e) {
                LOG.error("Error closing terminal", e);
            } finally {
                this.myEmulatorExecutor.shutdown();
            }
        });
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "dimension";
                break;
            }
            case 1: 
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "origin";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "emulator";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "terminal";
                break;
            }
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "ttyConnector";
                break;
            }
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "newTermSize";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "taskScheduler";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/TerminalStarter";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "postResize";
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                objectArray = objectArray2;
                objectArray2[2] = "resize";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

