/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.TerminalCopyPasteHandler;
import com.jediterm.terminal.ui.UIUtil;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultTerminalCopyPasteHandler
implements TerminalCopyPasteHandler,
ClipboardOwner {
    private static final Logger LOG = Logger.getLogger(DefaultTerminalCopyPasteHandler.class);

    @Override
    public void setContents(@NotNull String text, boolean useSystemSelectionClipboardIfAvailable) {
        Clipboard systemSelectionClipboard;
        if (text == null) {
            DefaultTerminalCopyPasteHandler.$$$reportNull$$$0(0);
        }
        if (useSystemSelectionClipboardIfAvailable && (systemSelectionClipboard = DefaultTerminalCopyPasteHandler.getSystemSelectionClipboard()) != null) {
            this.setClipboardContents(new StringSelection(text), systemSelectionClipboard);
            return;
        }
        this.setSystemClipboardContents(text);
    }

    @Override
    @Nullable
    public String getContents(boolean useSystemSelectionClipboardIfAvailable) {
        Clipboard systemSelectionClipboard;
        if (useSystemSelectionClipboardIfAvailable && (systemSelectionClipboard = DefaultTerminalCopyPasteHandler.getSystemSelectionClipboard()) != null) {
            return this.getClipboardContents(systemSelectionClipboard);
        }
        return this.getSystemClipboardContents();
    }

    protected void setSystemClipboardContents(@NotNull String text) {
        if (text == null) {
            DefaultTerminalCopyPasteHandler.$$$reportNull$$$0(1);
        }
        this.setClipboardContents(new StringSelection(text), DefaultTerminalCopyPasteHandler.getSystemClipboard());
    }

    @Nullable
    private String getSystemClipboardContents() {
        return this.getClipboardContents(DefaultTerminalCopyPasteHandler.getSystemClipboard());
    }

    private void setClipboardContents(@NotNull Transferable contents, @Nullable Clipboard clipboard) {
        if (contents == null) {
            DefaultTerminalCopyPasteHandler.$$$reportNull$$$0(2);
        }
        if (clipboard != null) {
            try {
                clipboard.setContents(contents, this);
            } catch (IllegalStateException e) {
                DefaultTerminalCopyPasteHandler.logException("Cannot set contents", e);
            }
        }
    }

    @Nullable
    private String getClipboardContents(@Nullable Clipboard clipboard) {
        if (clipboard != null) {
            try {
                if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    return (String)clipboard.getData(DataFlavor.stringFlavor);
                }
            } catch (Exception e) {
                DefaultTerminalCopyPasteHandler.logException("Cannot get clipboard contents", e);
            }
        }
        return null;
    }

    @Nullable
    private static Clipboard getSystemClipboard() {
        try {
            return Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (IllegalStateException e) {
            DefaultTerminalCopyPasteHandler.logException("Cannot get system clipboard", e);
            return null;
        }
    }

    @Nullable
    private static Clipboard getSystemSelectionClipboard() {
        try {
            return Toolkit.getDefaultToolkit().getSystemSelection();
        } catch (IllegalStateException e) {
            DefaultTerminalCopyPasteHandler.logException("Cannot get system selection clipboard", e);
            return null;
        }
    }

    private static void logException(@NotNull String message, @NotNull Exception e) {
        if (message == null) {
            DefaultTerminalCopyPasteHandler.$$$reportNull$$$0(3);
        }
        if (e == null) {
            DefaultTerminalCopyPasteHandler.$$$reportNull$$$0(4);
        }
        if (UIUtil.isWindows && e instanceof IllegalStateException) {
            LOG.debug(message, e);
        } else {
            LOG.warn(message, e);
        }
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "text";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "contents";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "message";
                break;
            }
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "e";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/DefaultTerminalCopyPasteHandler";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "setContents";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[2] = "setSystemClipboardContents";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[2] = "setClipboardContents";
                break;
            }
            case 3: 
            case 4: {
                objectArray = objectArray2;
                objectArray2[2] = "logException";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

