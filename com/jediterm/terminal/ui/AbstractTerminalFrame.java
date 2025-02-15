/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.TabbedTerminalWidget;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.debug.BufferPanel;
import com.jediterm.terminal.model.SelectionUtil;
import com.jediterm.terminal.ui.AbstractTabbedTerminalWidget;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalPanelListener;
import com.jediterm.terminal.ui.TerminalSession;
import com.jediterm.terminal.ui.TerminalWidget;
import com.jediterm.terminal.ui.settings.DefaultTabbedSettingsProvider;
import com.jediterm.terminal.ui.settings.TabbedSettingsProvider;
import com.jediterm.terminal.util.Pair;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTerminalFrame {
    public static final Logger LOG = Logger.getLogger(AbstractTerminalFrame.class);
    private JFrame myBufferFrame;
    private TerminalWidget myTerminal;
    private AbstractAction myOpenAction = new AbstractAction("New Session"){

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractTerminalFrame.this.openSession(AbstractTerminalFrame.this.myTerminal);
        }
    };
    private AbstractAction myShowBuffersAction = new AbstractAction("Show buffers"){

        @Override
        public void actionPerformed(ActionEvent e) {
            if (AbstractTerminalFrame.this.myBufferFrame == null) {
                AbstractTerminalFrame.this.showBuffers();
            }
        }
    };
    private AbstractAction myDumpDimension = new AbstractAction("Dump terminal dimension"){

        @Override
        public void actionPerformed(ActionEvent e) {
            LOG.info(AbstractTerminalFrame.this.myTerminal.getTerminalDisplay().getColumnCount() + "x" + AbstractTerminalFrame.this.myTerminal.getTerminalDisplay().getRowCount());
        }
    };
    private AbstractAction myDumpSelection = new AbstractAction("Dump selection"){

        @Override
        public void actionPerformed(ActionEvent e) {
            Pair<Point, Point> points = AbstractTerminalFrame.this.myTerminal.getTerminalDisplay().getSelection().pointsForRun(AbstractTerminalFrame.this.myTerminal.getTerminalDisplay().getColumnCount());
            LOG.info(AbstractTerminalFrame.this.myTerminal.getTerminalDisplay().getSelection() + " : '" + SelectionUtil.getSelectionText((Point)points.first, (Point)points.second, AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminalTextBuffer()) + "'");
        }
    };
    private AbstractAction myDumpCursorPosition = new AbstractAction("Dump cursor position"){

        @Override
        public void actionPerformed(ActionEvent e) {
            LOG.info(AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminal().getCursorX() + "x" + AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminal().getCursorY());
        }
    };
    private AbstractAction myCursor0x0 = new AbstractAction("1x1"){

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminal().cursorPosition(1, 1);
        }
    };
    private AbstractAction myCursor10x10 = new AbstractAction("10x10"){

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminal().cursorPosition(10, 10);
        }
    };
    private AbstractAction myCursor80x24 = new AbstractAction("80x24"){

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractTerminalFrame.this.myTerminal.getCurrentSession().getTerminal().cursorPosition(80, 24);
        }
    };

    private JMenuBar getJMenuBar() {
        Level[] levels;
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("File");
        m.add(this.myOpenAction);
        mb.add(m);
        JMenu dm = new JMenu("Debug");
        JMenu logLevel = new JMenu("Set log level ...");
        for (final Level l : levels = new Level[]{Level.ALL, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.FATAL, Level.OFF}) {
            logLevel.add(new AbstractAction(l.toString()){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Logger.getRootLogger().setLevel(l);
                }
            });
        }
        dm.add(logLevel);
        dm.addSeparator();
        dm.add(this.myShowBuffersAction);
        dm.addSeparator();
        dm.add(this.myDumpDimension);
        dm.add(this.myDumpSelection);
        dm.add(this.myDumpCursorPosition);
        JMenu cursorPosition = new JMenu("Set cursor position ...");
        cursorPosition.add(this.myCursor0x0);
        cursorPosition.add(this.myCursor10x10);
        cursorPosition.add(this.myCursor80x24);
        dm.add(cursorPosition);
        mb.add(dm);
        return mb;
    }

    @Nullable
    protected JediTermWidget openSession(TerminalWidget terminal) {
        if (terminal.canOpenSession()) {
            return this.openSession(terminal, this.createTtyConnector());
        }
        return null;
    }

    public JediTermWidget openSession(TerminalWidget terminal, TtyConnector ttyConnector) {
        JediTermWidget session = terminal.createTerminalSession(ttyConnector);
        session.start();
        return session;
    }

    public abstract TtyConnector createTtyConnector();

    protected AbstractTerminalFrame() {
        this.myTerminal = this.createTabbedTerminalWidget();
        final JFrame frame = new JFrame("JediTerm");
        frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JMenuBar mb = this.getJMenuBar();
        frame.setJMenuBar(mb);
        this.sizeFrameForTerm(frame);
        frame.getContentPane().add("Center", this.myTerminal.getComponent());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(true);
        this.myTerminal.setTerminalPanelListener(new TerminalPanelListener(){

            @Override
            public void onPanelResize(@NotNull RequestOrigin origin) {
                if (origin == null) {
                    11.$$$reportNull$$$0(0);
                }
                if (origin == RequestOrigin.Remote) {
                    AbstractTerminalFrame.this.sizeFrameForTerm(frame);
                }
                frame.pack();
            }

            @Override
            public void onSessionChanged(TerminalSession currentSession) {
                frame.setTitle(currentSession.getSessionName());
            }

            @Override
            public void onTitleChanged(String title) {
                frame.setTitle(AbstractTerminalFrame.this.myTerminal.getCurrentSession().getSessionName());
            }

            private static /* synthetic */ void $$$reportNull$$$0(int n) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jediterm/terminal/ui/AbstractTerminalFrame$11", "onPanelResize"));
            }
        });
        this.openSession(this.myTerminal);
    }

    @NotNull
    protected AbstractTabbedTerminalWidget createTabbedTerminalWidget() {
        return new TabbedTerminalWidget(new DefaultTabbedSettingsProvider(), this::openSession){

            @Override
            public JediTermWidget createInnerTerminalWidget() {
                return AbstractTerminalFrame.this.createTerminalWidget(this.getSettingsProvider());
            }
        };
    }

    protected JediTermWidget createTerminalWidget(@NotNull TabbedSettingsProvider settingsProvider) {
        if (settingsProvider == null) {
            AbstractTerminalFrame.$$$reportNull$$$0(0);
        }
        return new JediTermWidget(settingsProvider);
    }

    private void sizeFrameForTerm(final JFrame frame) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                Dimension d = AbstractTerminalFrame.this.myTerminal.getPreferredSize();
                d.width += frame.getWidth() - frame.getContentPane().getWidth();
                d.height += frame.getHeight() - frame.getContentPane().getHeight();
                frame.setSize(d);
            }
        });
    }

    private void showBuffers() {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                AbstractTerminalFrame.this.myBufferFrame = new JFrame("buffers");
                BufferPanel panel = new BufferPanel(AbstractTerminalFrame.this.myTerminal.getCurrentSession());
                AbstractTerminalFrame.this.myBufferFrame.getContentPane().add(panel);
                AbstractTerminalFrame.this.myBufferFrame.pack();
                AbstractTerminalFrame.this.myBufferFrame.setLocationByPlatform(true);
                AbstractTerminalFrame.this.myBufferFrame.setVisible(true);
                AbstractTerminalFrame.this.myBufferFrame.setSize(800, 600);
                AbstractTerminalFrame.this.myBufferFrame.addWindowListener(new WindowAdapter(){

                    @Override
                    public void windowClosing(WindowEvent e) {
                        AbstractTerminalFrame.this.myBufferFrame = null;
                    }
                });
            }
        });
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settingsProvider", "com/jediterm/terminal/ui/AbstractTerminalFrame", "createTerminalWidget"));
    }
}

