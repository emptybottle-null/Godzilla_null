/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.google.common.collect.Lists;
import com.jediterm.terminal.SubstringFinder;
import com.jediterm.terminal.Terminal;
import com.jediterm.terminal.TerminalDisplay;
import com.jediterm.terminal.TerminalMode;
import com.jediterm.terminal.TerminalStarter;
import com.jediterm.terminal.TtyBasedArrayDataStream;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.debug.DebugBufferType;
import com.jediterm.terminal.model.JediTerminal;
import com.jediterm.terminal.model.StyleState;
import com.jediterm.terminal.model.TerminalTextBuffer;
import com.jediterm.terminal.model.hyperlinks.HyperlinkFilter;
import com.jediterm.terminal.model.hyperlinks.TextProcessing;
import com.jediterm.terminal.ui.PreConnectHandler;
import com.jediterm.terminal.ui.TerminalAction;
import com.jediterm.terminal.ui.TerminalActionProvider;
import com.jediterm.terminal.ui.TerminalPanel;
import com.jediterm.terminal.ui.TerminalPanelListener;
import com.jediterm.terminal.ui.TerminalSession;
import com.jediterm.terminal.ui.TerminalWidget;
import com.jediterm.terminal.ui.TerminalWidgetListener;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class JediTermWidget
extends JPanel
implements TerminalSession,
TerminalWidget,
TerminalActionProvider {
    private static final Logger LOG = Logger.getLogger(JediTermWidget.class);
    protected final TerminalPanel myTerminalPanel;
    protected final JScrollBar myScrollBar;
    protected final JediTerminal myTerminal;
    protected final AtomicBoolean mySessionRunning;
    private SearchComponent myFindComponent;
    private final PreConnectHandler myPreConnectHandler;
    private TtyConnector myTtyConnector;
    private TerminalStarter myTerminalStarter;
    private Thread myEmuThread;
    protected final SettingsProvider mySettingsProvider;
    private TerminalActionProvider myNextActionProvider;
    private JLayeredPane myInnerPanel;
    private final TextProcessing myTextProcessing;
    private final List<TerminalWidgetListener> myListeners;

    public JediTermWidget(@NotNull SettingsProvider settingsProvider) {
        if (settingsProvider == null) {
            JediTermWidget.$$$reportNull$$$0(0);
        }
        this(80, 24, settingsProvider);
    }

    public JediTermWidget(Dimension dimension, SettingsProvider settingsProvider) {
        this(dimension.width, dimension.height, settingsProvider);
    }

    public JediTermWidget(int columns, int lines, SettingsProvider settingsProvider) {
        super(new BorderLayout());
        this.mySessionRunning = new AtomicBoolean();
        this.myListeners = new CopyOnWriteArrayList<TerminalWidgetListener>();
        this.mySettingsProvider = settingsProvider;
        StyleState styleState = this.createDefaultStyle();
        this.myTextProcessing = new TextProcessing(settingsProvider.getHyperlinkColor(), settingsProvider.getHyperlinkHighlightingMode());
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(columns, lines, styleState, settingsProvider.getBufferMaxLinesCount(), this.myTextProcessing);
        this.myTextProcessing.setTerminalTextBuffer(terminalTextBuffer);
        this.myTerminalPanel = this.createTerminalPanel(this.mySettingsProvider, styleState, terminalTextBuffer);
        this.myTerminal = new JediTerminal(this.myTerminalPanel, terminalTextBuffer, styleState);
        this.myTerminal.setModeEnabled(TerminalMode.AltSendsEscape, this.mySettingsProvider.altSendsEscape());
        this.myTerminalPanel.addTerminalMouseListener(this.myTerminal);
        this.myTerminalPanel.setNextProvider(this);
        this.myTerminalPanel.setCoordAccessor(this.myTerminal);
        this.myPreConnectHandler = this.createPreConnectHandler(this.myTerminal);
        this.myTerminalPanel.addCustomKeyListener(this.myPreConnectHandler);
        this.myScrollBar = this.createScrollBar();
        this.myInnerPanel = new JLayeredPane();
        this.myInnerPanel.setFocusable(false);
        this.setFocusable(false);
        this.myInnerPanel.setLayout(new TerminalLayout());
        this.myInnerPanel.add((Component)this.myTerminalPanel, "TERMINAL");
        this.myInnerPanel.add((Component)this.myScrollBar, "SCROLL");
        this.add((Component)this.myInnerPanel, "Center");
        this.myScrollBar.setModel(this.myTerminalPanel.getBoundedRangeModel());
        this.mySessionRunning.set(false);
        this.myTerminalPanel.init(this.myScrollBar);
        this.myTerminalPanel.setVisible(true);
    }

    protected JScrollBar createScrollBar() {
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setUI(new FindResultScrollBarUI());
        return scrollBar;
    }

    protected StyleState createDefaultStyle() {
        StyleState styleState = new StyleState();
        styleState.setDefaultStyle(this.mySettingsProvider.getDefaultStyle());
        return styleState;
    }

    protected TerminalPanel createTerminalPanel(@NotNull SettingsProvider settingsProvider, @NotNull StyleState styleState, @NotNull TerminalTextBuffer terminalTextBuffer) {
        if (settingsProvider == null) {
            JediTermWidget.$$$reportNull$$$0(1);
        }
        if (styleState == null) {
            JediTermWidget.$$$reportNull$$$0(2);
        }
        if (terminalTextBuffer == null) {
            JediTermWidget.$$$reportNull$$$0(3);
        }
        return new TerminalPanel(settingsProvider, terminalTextBuffer, styleState);
    }

    protected PreConnectHandler createPreConnectHandler(JediTerminal terminal) {
        return new PreConnectHandler(terminal);
    }

    @Override
    public TerminalDisplay getTerminalDisplay() {
        return this.getTerminalPanel();
    }

    public TerminalPanel getTerminalPanel() {
        return this.myTerminalPanel;
    }

    public void setTtyConnector(@NotNull TtyConnector ttyConnector) {
        if (ttyConnector == null) {
            JediTermWidget.$$$reportNull$$$0(4);
        }
        this.myTtyConnector = ttyConnector;
        this.myTerminalStarter = this.createTerminalStarter(this.myTerminal, this.myTtyConnector);
        this.myTerminalPanel.setTerminalStarter(this.myTerminalStarter);
    }

    protected TerminalStarter createTerminalStarter(JediTerminal terminal, TtyConnector connector) {
        return new TerminalStarter(terminal, connector, new TtyBasedArrayDataStream(connector));
    }

    @Override
    public TtyConnector getTtyConnector() {
        return this.myTtyConnector;
    }

    @Override
    public Terminal getTerminal() {
        return this.myTerminal;
    }

    @Override
    public String getSessionName() {
        if (this.myTtyConnector != null) {
            return this.myTtyConnector.getName();
        }
        return "Session";
    }

    @Override
    public void start() {
        if (!this.mySessionRunning.get()) {
            this.myEmuThread = new Thread(new EmulatorTask());
            this.myEmuThread.start();
        } else {
            LOG.error("Should not try to start session again at this point... ");
        }
    }

    public void stop() {
        if (this.mySessionRunning.get() && this.myEmuThread != null) {
            this.myEmuThread.interrupt();
        }
    }

    public boolean isSessionRunning() {
        return this.mySessionRunning.get();
    }

    @Override
    public String getBufferText(DebugBufferType type) {
        return type.getValue(this);
    }

    @Override
    public TerminalTextBuffer getTerminalTextBuffer() {
        return this.myTerminalPanel.getTerminalTextBuffer();
    }

    @Override
    public boolean requestFocusInWindow() {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                JediTermWidget.this.myTerminalPanel.requestFocusInWindow();
            }
        });
        return super.requestFocusInWindow();
    }

    @Override
    public void requestFocus() {
        this.myTerminalPanel.requestFocus();
    }

    @Override
    public boolean canOpenSession() {
        return !this.isSessionRunning();
    }

    @Override
    public void setTerminalPanelListener(TerminalPanelListener terminalPanelListener) {
        this.myTerminalPanel.setTerminalPanelListener(terminalPanelListener);
    }

    @Override
    public TerminalSession getCurrentSession() {
        return this;
    }

    @Override
    public JediTermWidget createTerminalSession(TtyConnector ttyConnector) {
        this.setTtyConnector(ttyConnector);
        return this;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void close() {
        this.stop();
        if (this.myTerminalStarter != null) {
            this.myTerminalStarter.close();
        }
        this.myTerminalPanel.dispose();
    }

    @Override
    public List<TerminalAction> getActions() {
        return Lists.newArrayList(new TerminalAction(this.mySettingsProvider.getFindActionPresentation(), (Predicate<KeyEvent>)new com.google.common.base.Predicate<KeyEvent>(){

            @Override
            public boolean apply(KeyEvent input) {
                JediTermWidget.this.showFindText();
                return true;
            }
        }).withMnemonicKey(70));
    }

    private void showFindText() {
        if (this.myFindComponent == null) {
            this.myFindComponent = this.createSearchComponent();
            final JComponent component = this.myFindComponent.getComponent();
            this.myInnerPanel.add((Component)component, "FIND");
            this.myInnerPanel.moveToFront(component);
            this.myInnerPanel.revalidate();
            this.myInnerPanel.repaint();
            component.requestFocus();
            this.myFindComponent.addDocumentChangeListener(new DocumentListener(){

                @Override
                public void insertUpdate(DocumentEvent e) {
                    this.textUpdated();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    this.textUpdated();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    this.textUpdated();
                }

                private void textUpdated() {
                    JediTermWidget.this.findText(JediTermWidget.this.myFindComponent.getText(), JediTermWidget.this.myFindComponent.ignoreCase());
                }
            });
            this.myFindComponent.addIgnoreCaseListener(new ItemListener(){

                @Override
                public void itemStateChanged(ItemEvent e) {
                    JediTermWidget.this.findText(JediTermWidget.this.myFindComponent.getText(), JediTermWidget.this.myFindComponent.ignoreCase());
                }
            });
            this.myFindComponent.addKeyListener(new KeyAdapter(){

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == 27) {
                        JediTermWidget.this.myInnerPanel.remove(component);
                        JediTermWidget.this.myInnerPanel.revalidate();
                        JediTermWidget.this.myInnerPanel.repaint();
                        JediTermWidget.this.myFindComponent = null;
                        JediTermWidget.this.myTerminalPanel.setFindResult(null);
                        JediTermWidget.this.myTerminalPanel.requestFocusInWindow();
                    } else if (keyEvent.getKeyCode() == 10 || keyEvent.getKeyCode() == 38) {
                        JediTermWidget.this.myFindComponent.nextFindResultItem(JediTermWidget.this.myTerminalPanel.selectNextFindResultItem());
                    } else if (keyEvent.getKeyCode() == 40) {
                        JediTermWidget.this.myFindComponent.prevFindResultItem(JediTermWidget.this.myTerminalPanel.selectPrevFindResultItem());
                    } else {
                        super.keyPressed(keyEvent);
                    }
                }
            });
        } else {
            this.myFindComponent.getComponent().requestFocusInWindow();
        }
    }

    protected SearchComponent createSearchComponent() {
        return new SearchPanel();
    }

    private void findText(String text, boolean ignoreCase) {
        SubstringFinder.FindResult results = this.myTerminal.searchInTerminalTextBuffer(text, ignoreCase);
        this.myTerminalPanel.setFindResult(results);
        this.myFindComponent.onResultUpdated(results);
        this.myScrollBar.repaint();
    }

    @Override
    public TerminalActionProvider getNextProvider() {
        return this.myNextActionProvider;
    }

    @Override
    public void setNextProvider(TerminalActionProvider actionProvider) {
        this.myNextActionProvider = actionProvider;
    }

    public TerminalStarter getTerminalStarter() {
        return this.myTerminalStarter;
    }

    public void addHyperlinkFilter(HyperlinkFilter filter) {
        this.myTextProcessing.addHyperlinkFilter(filter);
    }

    @Override
    public void addListener(TerminalWidgetListener listener) {
        this.myListeners.add(listener);
    }

    @Override
    public void removeListener(TerminalWidgetListener listener) {
        this.myListeners.remove(listener);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "settingsProvider";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "styleState";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "terminalTextBuffer";
                break;
            }
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "ttyConnector";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/ui/JediTermWidget";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "<init>";
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                objectArray = objectArray2;
                objectArray2[2] = "createTerminalPanel";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[2] = "setTtyConnector";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }

    private static class TerminalLayout
    implements LayoutManager {
        public static final String TERMINAL = "TERMINAL";
        public static final String SCROLL = "SCROLL";
        public static final String FIND = "FIND";
        private Component terminal;
        private Component scroll;
        private Component find;

        private TerminalLayout() {
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
            if (TERMINAL.equals(name)) {
                this.terminal = comp;
            } else if (FIND.equals(name)) {
                this.find = comp;
            } else if (SCROLL.equals(name)) {
                this.scroll = comp;
            } else {
                throw new IllegalArgumentException("unknown component name " + name);
            }
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            if (comp == this.terminal) {
                this.terminal = null;
            }
            if (comp == this.scroll) {
                this.scroll = null;
            }
            if (comp == this.find) {
                this.find = comp;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Dimension preferredLayoutSize(Container target) {
            Object object = target.getTreeLock();
            synchronized (object) {
                Dimension d;
                Dimension dim = new Dimension(0, 0);
                if (this.terminal != null) {
                    d = this.terminal.getPreferredSize();
                    dim.width = Math.max(d.width, dim.width);
                    dim.height = Math.max(d.height, dim.height);
                }
                if (this.scroll != null) {
                    d = this.scroll.getPreferredSize();
                    dim.width += d.width;
                    dim.height = Math.max(d.height, dim.height);
                }
                if (this.find != null) {
                    d = this.find.getPreferredSize();
                    dim.width = Math.max(d.width, dim.width);
                    dim.height = Math.max(d.height, dim.height);
                }
                Insets insets = target.getInsets();
                dim.width += insets.left + insets.right;
                dim.height += insets.top + insets.bottom;
                return dim;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Dimension minimumLayoutSize(Container target) {
            Object object = target.getTreeLock();
            synchronized (object) {
                Dimension d;
                Dimension dim = new Dimension(0, 0);
                if (this.terminal != null) {
                    d = this.terminal.getMinimumSize();
                    dim.width = Math.max(d.width, dim.width);
                    dim.height = Math.max(d.height, dim.height);
                }
                if (this.scroll != null) {
                    d = this.scroll.getPreferredSize();
                    dim.width += d.width;
                    dim.height = Math.max(d.height, dim.height);
                }
                if (this.find != null) {
                    d = this.find.getMinimumSize();
                    dim.width = Math.max(d.width, dim.width);
                    dim.height = Math.max(d.height, dim.height);
                }
                Insets insets = target.getInsets();
                dim.width += insets.left + insets.right;
                dim.height += insets.top + insets.bottom;
                return dim;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void layoutContainer(Container target) {
            Object object = target.getTreeLock();
            synchronized (object) {
                Insets insets = target.getInsets();
                int top = insets.top;
                int bottom = target.getHeight() - insets.bottom;
                int left = insets.left;
                int right = target.getWidth() - insets.right;
                Dimension scrollDim = new Dimension(0, 0);
                if (this.scroll != null) {
                    scrollDim = this.scroll.getPreferredSize();
                    this.scroll.setBounds(right - scrollDim.width, top, scrollDim.width, bottom - top);
                }
                if (this.terminal != null) {
                    this.terminal.setBounds(left, top, right - left - scrollDim.width, bottom - top);
                }
                if (this.find != null) {
                    Dimension d = this.find.getPreferredSize();
                    this.find.setBounds(right - d.width - scrollDim.width, top, d.width, d.height);
                }
            }
        }
    }

    private class FindResultScrollBarUI
    extends BasicScrollBarUI {
        private FindResultScrollBarUI() {
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            super.paintTrack(g, c, trackBounds);
            SubstringFinder.FindResult result = JediTermWidget.this.myTerminalPanel.getFindResult();
            if (result != null) {
                int modelHeight = this.scrollbar.getModel().getMaximum() - this.scrollbar.getModel().getMinimum();
                int anchorHeight = Math.max(2, trackBounds.height / modelHeight);
                Color color = JediTermWidget.this.mySettingsProvider.getTerminalColorPalette().getBackground(Objects.requireNonNull(JediTermWidget.this.mySettingsProvider.getFoundPatternColor().getBackground()));
                g.setColor(color);
                for (SubstringFinder.FindResult.FindItem r : result.getItems()) {
                    int where = trackBounds.height * r.getStart().y / modelHeight;
                    g.fillRect(trackBounds.x, trackBounds.y + where, trackBounds.width, anchorHeight);
                }
            }
        }
    }

    public class SearchPanel
    extends JPanel
    implements SearchComponent {
        private final JTextField myTextField = new JTextField();
        private final JLabel label = new JLabel();
        private final JButton prev;
        private final JButton next;
        private final JCheckBox ignoreCaseCheckBox = new JCheckBox("Ignore Case", true);

        public SearchPanel() {
            this.next = this.createNextButton();
            this.next.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    SearchPanel.this.nextFindResultItem(JediTermWidget.this.myTerminalPanel.selectNextFindResultItem());
                }
            });
            this.prev = this.createPrevButton();
            this.prev.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    SearchPanel.this.prevFindResultItem(JediTermWidget.this.myTerminalPanel.selectPrevFindResultItem());
                }
            });
            this.myTextField.setPreferredSize(new Dimension(JediTermWidget.this.myTerminalPanel.myCharSize.width * 30, JediTermWidget.this.myTerminalPanel.myCharSize.height + 3));
            this.myTextField.setEditable(true);
            this.updateLabel(null);
            this.add(this.myTextField);
            this.add(this.ignoreCaseCheckBox);
            this.add(this.label);
            this.add(this.next);
            this.add(this.prev);
            this.setOpaque(true);
        }

        protected JButton createNextButton() {
            return new BasicArrowButton(1);
        }

        protected JButton createPrevButton() {
            return new BasicArrowButton(5);
        }

        @Override
        public void nextFindResultItem(SubstringFinder.FindResult.FindItem selectedItem) {
            this.updateLabel(selectedItem);
        }

        @Override
        public void prevFindResultItem(SubstringFinder.FindResult.FindItem selectedItem) {
            this.updateLabel(selectedItem);
        }

        private void updateLabel(SubstringFinder.FindResult.FindItem selectedItem) {
            SubstringFinder.FindResult result = JediTermWidget.this.myTerminalPanel.getFindResult();
            this.label.setText((selectedItem != null ? selectedItem.getIndex() : 0) + " of " + (result != null ? result.getItems().size() : 0));
        }

        @Override
        public void onResultUpdated(SubstringFinder.FindResult results) {
            this.updateLabel(null);
        }

        @Override
        public String getText() {
            return this.myTextField.getText();
        }

        @Override
        public boolean ignoreCase() {
            return this.ignoreCaseCheckBox.isSelected();
        }

        @Override
        public JComponent getComponent() {
            return this;
        }

        @Override
        public void requestFocus() {
            this.myTextField.requestFocus();
        }

        @Override
        public void addDocumentChangeListener(DocumentListener listener) {
            this.myTextField.getDocument().addDocumentListener(listener);
        }

        @Override
        public void addKeyListener(KeyListener listener) {
            this.myTextField.addKeyListener(listener);
        }

        @Override
        public void addIgnoreCaseListener(ItemListener listener) {
            this.ignoreCaseCheckBox.addItemListener(listener);
        }
    }

    class EmulatorTask
    implements Runnable {
        EmulatorTask() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            try {
                JediTermWidget.this.mySessionRunning.set(true);
                Thread.currentThread().setName("Connector-" + JediTermWidget.this.myTtyConnector.getName());
                if (JediTermWidget.this.myTtyConnector.init(JediTermWidget.this.myPreConnectHandler)) {
                    JediTermWidget.this.myTerminalPanel.addCustomKeyListener(JediTermWidget.this.myTerminalPanel.getTerminalKeyListener());
                    JediTermWidget.this.myTerminalPanel.removeCustomKeyListener(JediTermWidget.this.myPreConnectHandler);
                    JediTermWidget.this.myTerminalStarter.start();
                }
            } catch (Exception e) {
                LOG.error("Exception running terminal", e);
            } finally {
                try {
                    JediTermWidget.this.myTtyConnector.close();
                } catch (Exception e) {}
                JediTermWidget.this.mySessionRunning.set(false);
                TerminalPanelListener terminalPanelListener = JediTermWidget.this.myTerminalPanel.getTerminalPanelListener();
                if (terminalPanelListener != null) {
                    terminalPanelListener.onSessionChanged(JediTermWidget.this.getCurrentSession());
                }
                for (TerminalWidgetListener listener : JediTermWidget.this.myListeners) {
                    listener.allSessionsClosed(JediTermWidget.this);
                }
                JediTermWidget.this.myTerminalPanel.addCustomKeyListener(JediTermWidget.this.myPreConnectHandler);
                JediTermWidget.this.myTerminalPanel.removeCustomKeyListener(JediTermWidget.this.myTerminalPanel.getTerminalKeyListener());
            }
        }
    }

    protected static interface SearchComponent {
        public String getText();

        public boolean ignoreCase();

        public JComponent getComponent();

        public void addDocumentChangeListener(DocumentListener var1);

        public void addKeyListener(KeyListener var1);

        public void addIgnoreCaseListener(ItemListener var1);

        public void onResultUpdated(SubstringFinder.FindResult var1);

        public void nextFindResultItem(SubstringFinder.FindResult.FindItem var1);

        public void prevFindResultItem(SubstringFinder.FindResult.FindItem var1);
    }
}

