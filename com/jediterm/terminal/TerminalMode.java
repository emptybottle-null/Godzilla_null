/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.Terminal;
import org.apache.log4j.Logger;

public enum TerminalMode {
    Null,
    CursorKey{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setApplicationArrowKeys(enabled);
        }
    }
    ,
    ANSI,
    WideColumn{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.clearScreen();
            terminal.resetScrollRegions();
        }
    }
    ,
    CursorVisible{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setCursorVisible(enabled);
        }
    }
    ,
    AlternateBuffer{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.useAlternateBuffer(enabled);
        }
    }
    ,
    SmoothScroll,
    ReverseVideo,
    OriginMode{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
        }
    }
    ,
    AutoWrap{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
        }
    }
    ,
    AutoRepeatKeys,
    Interlace,
    Keypad{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setApplicationKeypad(enabled);
        }
    }
    ,
    StoreCursor{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            if (enabled) {
                terminal.saveCursor();
            } else {
                terminal.restoreCursor();
            }
        }
    }
    ,
    CursorBlinking{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setBlinkingCursor(enabled);
        }
    }
    ,
    AllowWideColumn,
    ReverseWrapAround,
    AutoNewLine{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setAutoNewLine(enabled);
        }
    }
    ,
    KeyboardAction,
    InsertMode,
    SendReceive,
    EightBitInput,
    AltSendsEscape{

        @Override
        public void setEnabled(Terminal terminal, boolean enabled) {
            terminal.setAltSendsEscape(enabled);
        }
    };

    private static final Logger LOG;

    public void setEnabled(Terminal terminal, boolean enabled) {
        LOG.error("Mode " + this.name() + " is not implemented, setting to " + enabled);
    }

    static {
        LOG = Logger.getLogger(TerminalMode.class);
    }
}

