/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.debug;

import com.jediterm.terminal.LoggingTtyConnector;
import com.jediterm.terminal.debug.ControlSequenceVisualizer;
import com.jediterm.terminal.ui.TerminalSession;

public enum DebugBufferType {
    Back{

        @Override
        public String getValue(TerminalSession session) {
            return session.getTerminalTextBuffer().getScreenLines();
        }
    }
    ,
    BackStyle{

        @Override
        public String getValue(TerminalSession session) {
            return session.getTerminalTextBuffer().getStyleLines();
        }
    }
    ,
    Scroll{

        @Override
        public String getValue(TerminalSession session) {
            return session.getTerminalTextBuffer().getHistoryBuffer().getLines();
        }
    }
    ,
    ControlSequences{
        private ControlSequenceVisualizer myVisualizer = new ControlSequenceVisualizer();

        @Override
        public String getValue(TerminalSession session) {
            if (session.getTtyConnector() instanceof LoggingTtyConnector) {
                return this.myVisualizer.getVisualizedString(((LoggingTtyConnector)((Object)session.getTtyConnector())).getChunks());
            }
            return "Control sequences aren't logged";
        }
    };


    public abstract String getValue(TerminalSession var1);
}

