/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.jediterm.terminal.ui.AbstractTabbedTerminalWidget$TabComponent.MyLabelHolder
 */
package com.jediterm.terminal.ui;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.TerminalDisplay;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.TtyConnectorWaitFor;
import com.jediterm.terminal.ui.AbstractTabbedTerminalWidget;
import com.jediterm.terminal.ui.AbstractTabs;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalAction;
import com.jediterm.terminal.ui.TerminalActionProvider;
import com.jediterm.terminal.ui.TerminalPanelListener;
import com.jediterm.terminal.ui.TerminalSession;
import com.jediterm.terminal.ui.TerminalWidget;
import com.jediterm.terminal.ui.TerminalWidgetListener;
import com.jediterm.terminal.ui.settings.TabbedSettingsProvider;
import com.jediterm.terminal.util.JTextFieldLimit;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractTabbedTerminalWidget<T extends JediTermWidget>
extends JPanel
implements TerminalWidget,
TerminalActionProvider {
    private final Object myLock;
    private TerminalPanelListener myTerminalPanelListener;
    private T myTermWidget;
    private AbstractTabs<T> myTabs;
    private TabbedSettingsProvider mySettingsProvider;
    private List<TabListener> myTabListeners;
    private List<TerminalWidgetListener> myWidgetListeners;
    private TerminalActionProvider myNextActionProvider;
    private final Function<AbstractTabbedTerminalWidget<T>, T> myCreateNewSessionAction;
    private JPanel myPanel;

    public AbstractTabbedTerminalWidget(@NotNull TabbedSettingsProvider settingsProvider, @NotNull Function<AbstractTabbedTerminalWidget<T>, T> createNewSessionAction) {
        if (settingsProvider == null) {
            AbstractTabbedTerminalWidget.$$$reportNull$$$0(0);
        }
        if (createNewSessionAction == null) {
            AbstractTabbedTerminalWidget.$$$reportNull$$$0(1);
        }
        super(new BorderLayout());
        this.myLock = new Object();
        this.myTerminalPanelListener = null;
        this.myTermWidget = null;
        this.myTabListeners = Lists.newArrayList();
        this.myWidgetListeners = new CopyOnWriteArrayList<TerminalWidgetListener>();
        this.mySettingsProvider = settingsProvider;
        this.myCreateNewSessionAction = createNewSessionAction;
        this.setFocusTraversalPolicy(new DefaultFocusTraversalPolicy());
        this.myPanel = new JPanel(new BorderLayout());
        this.myPanel.add((Component)this, "Center");
    }

    public T createTerminalSession(TtyConnector ttyConnector) {
        T terminal = this.createNewTabWidget();
        this.initSession(ttyConnector, terminal);
        return terminal;
    }

    public void initSession(TtyConnector ttyConnector, T terminal) {
        int index;
        ((JediTermWidget)terminal).createTerminalSession(ttyConnector);
        if (this.myTabs != null && (index = this.myTabs.indexOfComponent((Component)terminal)) != -1) {
            this.myTabs.setTitleAt(index, this.generateUniqueName(terminal, this.myTabs));
        }
        this.setupTtyConnectorWaitFor(ttyConnector, terminal);
    }

    public T createNewTabWidget() {
        T terminal = this.createInnerTerminalWidget();
        ((JediTermWidget)terminal).setNextProvider(this);
        if (this.myTerminalPanelListener != null) {
            ((JediTermWidget)terminal).setTerminalPanelListener(this.myTerminalPanelListener);
        }
        if (this.myTermWidget == null && this.myTabs == null) {
            this.myTermWidget = terminal;
            Dimension size = ((JediTermWidget)terminal).getComponent().getSize();
            this.add((Component)((JediTermWidget)this.myTermWidget).getComponent(), "Center");
            this.setSize(size);
            if (this.myTerminalPanelListener != null) {
                this.myTerminalPanelListener.onPanelResize(RequestOrigin.User);
            }
            this.onSessionChanged();
        } else {
            if (this.myTabs == null) {
                this.myTabs = this.setupTabs();
            }
            this.addTab(terminal, this.myTabs);
        }
        return terminal;
    }

    public abstract T createInnerTerminalWidget();

    protected void setupTtyConnectorWaitFor(TtyConnector ttyConnector, T widget) {
        new TtyConnectorWaitFor(ttyConnector, Executors.newSingleThreadExecutor()).setTerminationCallback(integer -> {
            if (this.mySettingsProvider.shouldCloseTabOnLogout(ttyConnector)) {
                this.closeTab(widget);
                if (this.myTabs.getTabCount() == 0) {
                    for (TerminalWidgetListener widgetListener : this.myWidgetListeners) {
                        widgetListener.allSessionsClosed((TerminalWidget)widget);
                    }
                }
            }
            return true;
        });
    }

    private void addTab(T terminal, AbstractTabs<T> tabs) {
        String name = this.generateUniqueName(terminal, tabs);
        this.addTab(terminal, tabs, name);
    }

    private String generateUniqueName(T terminal, AbstractTabs<T> tabs) {
        return this.generateUniqueName(this.mySettingsProvider.tabName(((JediTermWidget)terminal).getTtyConnector(), ((JediTermWidget)terminal).getSessionName()), tabs);
    }

    private void addTab(T terminal, AbstractTabs<T> tabs, String name) {
        tabs.addTab(name, terminal);
        tabs.setTabComponentAt(tabs.getTabCount() - 1, this.createTabComponent(tabs, terminal));
        tabs.setSelectedComponent(terminal);
    }

    public void addTab(String name, T terminal) {
        if (this.myTabs == null) {
            this.myTabs = this.setupTabs();
        }
        this.addTab(terminal, this.myTabs, name);
    }

    private String generateUniqueName(String suggestedName, AbstractTabs<T> tabs) {
        HashSet<String> names = Sets.newHashSet();
        for (int i = 0; i < tabs.getTabCount(); ++i) {
            names.add(tabs.getTitleAt(i));
        }
        String newSdkName = suggestedName;
        int i = 0;
        while (names.contains(newSdkName)) {
            newSdkName = suggestedName + " (" + ++i + ")";
        }
        return newSdkName;
    }

    private AbstractTabs<T> setupTabs() {
        AbstractTabs<T> tabs = this.createTabbedPane();
        tabs.addChangeListener(new AbstractTabs.TabChangeListener(){

            @Override
            public void tabRemoved() {
                if (AbstractTabbedTerminalWidget.this.myTabs.getTabCount() == 1) {
                    AbstractTabbedTerminalWidget.this.removeTabbedPane();
                }
            }

            @Override
            public void selectionChanged() {
                AbstractTabbedTerminalWidget.this.onSessionChanged();
            }
        });
        this.remove((Component)this.myTermWidget);
        this.addTab(this.myTermWidget, tabs);
        this.myTermWidget = null;
        this.add((Component)tabs.getComponent(), "Center");
        return tabs;
    }

    public boolean isNoActiveSessions() {
        return this.myTabs == null && this.myTermWidget == null;
    }

    private void onSessionChanged() {
        TerminalSession session = this.getCurrentSession();
        if (session != null) {
            if (this.myTerminalPanelListener != null) {
                this.myTerminalPanelListener.onSessionChanged(session);
            }
            ((JediTermWidget)session).getTerminalPanel().requestFocusInWindow();
        }
    }

    protected abstract AbstractTabs<T> createTabbedPane();

    protected Component createTabComponent(AbstractTabs<T> tabs, T terminal) {
        return new TabComponent(this, tabs, (JediTermWidget)terminal);
    }

    public void closeTab(T terminal) {
        if (terminal != null) {
            if (this.myTabs != null && this.myTabs.indexOfComponent((Component)terminal) != -1) {
                SwingUtilities.invokeLater(new Runnable((JediTermWidget)terminal){
                    final /* synthetic */ JediTermWidget val$terminal;
                    {
                        this.val$terminal = jediTermWidget;
                    }

                    @Override
                    public void run() {
                        AbstractTabbedTerminalWidget.this.removeTab(this.val$terminal);
                    }
                });
                this.fireTabClosed(terminal);
            } else if (this.myTermWidget == terminal) {
                this.myTermWidget = null;
                this.fireTabClosed(terminal);
            }
        }
    }

    public void closeCurrentSession() {
        TerminalSession session = this.getCurrentSession();
        if (session != null) {
            ((JediTermWidget)session).close();
            this.closeTab(session);
        }
    }

    public void dispose() {
        for (TerminalSession s : this.getAllTerminalSessions()) {
            if (s == null) continue;
            s.close();
        }
    }

    private List<T> getAllTerminalSessions() {
        ArrayList<T> session = Lists.newArrayList();
        if (this.myTabs != null) {
            for (int i = 0; i < this.myTabs.getTabCount(); ++i) {
                session.add(this.getTerminalPanel(i));
            }
        } else if (this.myTermWidget != null) {
            session.add(this.myTermWidget);
        }
        return session;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeTab(T terminal) {
        Object object = this.myLock;
        synchronized (object) {
            if (this.myTabs != null) {
                this.myTabs.remove(terminal);
            }
            this.onSessionChanged();
        }
    }

    private void removeTabbedPane() {
        this.myTermWidget = this.getTerminalPanel(0);
        this.myTabs.removeAll();
        this.remove(this.myTabs.getComponent());
        this.myTabs = null;
        this.add((Component)((JediTermWidget)this.myTermWidget).getComponent(), "Center");
    }

    @Override
    public List<TerminalAction> getActions() {
        return Lists.newArrayList(new TerminalAction(this.mySettingsProvider.getNewSessionActionPresentation(), (Predicate<KeyEvent>)new com.google.common.base.Predicate<KeyEvent>(){

            @Override
            public boolean apply(KeyEvent input) {
                AbstractTabbedTerminalWidget.this.handleNewSession();
                return true;
            }
        }).withMnemonicKey(78), new TerminalAction(this.mySettingsProvider.getCloseSessionActionPresentation(), (Predicate<KeyEvent>)new com.google.common.base.Predicate<KeyEvent>(){

            @Override
            public boolean apply(KeyEvent input) {
                AbstractTabbedTerminalWidget.this.closeCurrentSession();
                return true;
            }
        }).withMnemonicKey(83), new TerminalAction(this.mySettingsProvider.getNextTabActionPresentation(), (Predicate<KeyEvent>)new com.google.common.base.Predicate<KeyEvent>(){

            @Override
            public boolean apply(KeyEvent input) {
                AbstractTabbedTerminalWidget.this.selectNextTab();
                return true;
            }
        }).withEnabledSupplier((Supplier<Boolean>)new com.google.common.base.Supplier<Boolean>(){

            @Override
            public Boolean get() {
                return AbstractTabbedTerminalWidget.this.myTabs != null && AbstractTabbedTerminalWidget.this.myTabs.getSelectedIndex() < AbstractTabbedTerminalWidget.this.myTabs.getTabCount() - 1;
            }
        }), new TerminalAction(this.mySettingsProvider.getPreviousTabActionPresentation(), (Predicate<KeyEvent>)new com.google.common.base.Predicate<KeyEvent>(){

            @Override
            public boolean apply(KeyEvent input) {
                AbstractTabbedTerminalWidget.this.selectPreviousTab();
                return true;
            }
        }).withEnabledSupplier((Supplier<Boolean>)new com.google.common.base.Supplier<Boolean>(){

            @Override
            public Boolean get() {
                return AbstractTabbedTerminalWidget.this.myTabs != null && AbstractTabbedTerminalWidget.this.myTabs.getSelectedIndex() > 0;
            }
        }));
    }

    private void selectPreviousTab() {
        this.myTabs.setSelectedIndex(this.myTabs.getSelectedIndex() - 1);
    }

    private void selectNextTab() {
        this.myTabs.setSelectedIndex(this.myTabs.getSelectedIndex() + 1);
    }

    @Override
    public TerminalActionProvider getNextProvider() {
        return this.myNextActionProvider;
    }

    @Override
    public void setNextProvider(TerminalActionProvider provider) {
        this.myNextActionProvider = provider;
    }

    private void handleNewSession() {
        this.myCreateNewSessionAction.apply(this);
    }

    public AbstractTabs<T> getTerminalTabs() {
        return this.myTabs;
    }

    @Override
    public JComponent getComponent() {
        return this.myPanel;
    }

    public JComponent getFocusableComponent() {
        return this.myTabs != null ? this.myTabs.getComponent() : (this.myTermWidget != null ? this.myTermWidget : this);
    }

    @Override
    public JComponent getPreferredFocusableComponent() {
        return this.getFocusableComponent();
    }

    @Override
    public boolean canOpenSession() {
        return true;
    }

    @Override
    public void setTerminalPanelListener(TerminalPanelListener terminalPanelListener) {
        if (this.myTabs != null) {
            for (int i = 0; i < this.myTabs.getTabCount(); ++i) {
                ((JediTermWidget)this.getTerminalPanel(i)).setTerminalPanelListener(terminalPanelListener);
            }
        } else if (this.myTermWidget != null) {
            ((JediTermWidget)this.myTermWidget).setTerminalPanelListener(terminalPanelListener);
        }
        this.myTerminalPanelListener = terminalPanelListener;
    }

    @Nullable
    public T getCurrentSession() {
        if (this.myTabs != null) {
            return this.getTerminalPanel(this.myTabs.getSelectedIndex());
        }
        return this.myTermWidget;
    }

    @Override
    public TerminalDisplay getTerminalDisplay() {
        return ((JediTermWidget)this.getCurrentSession()).getTerminalDisplay();
    }

    @Nullable
    private T getTerminalPanel(int index) {
        if (index < this.myTabs.getTabCount() && index >= 0) {
            return (T)((JediTermWidget)this.myTabs.getComponentAt(index));
        }
        return null;
    }

    public void addTabListener(TabListener listener) {
        this.myTabListeners.add(listener);
    }

    public void removeTabListener(TabListener listener) {
        this.myTabListeners.remove(listener);
    }

    private void fireTabClosed(T terminal) {
        for (TabListener l : this.myTabListeners) {
            l.tabClosed(terminal);
        }
    }

    @Override
    public void addListener(TerminalWidgetListener listener) {
        this.myWidgetListeners.add(listener);
    }

    @Override
    public void removeListener(TerminalWidgetListener listener) {
        this.myWidgetListeners.remove(listener);
    }

    public TabbedSettingsProvider getSettingsProvider() {
        return this.mySettingsProvider;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2 = new Object[3];
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[0] = "settingsProvider";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[0] = "createNewSessionAction";
                break;
            }
        }
        objectArray[1] = "com/jediterm/terminal/ui/AbstractTabbedTerminalWidget";
        objectArray[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }

    public static interface TabListener<T extends JediTermWidget> {
        public void tabClosed(T var1);
    }

    private static class TabComponent
    extends JPanel
    implements FocusListener {
        private T myTerminal;
        private com.jediterm.terminal.ui.AbstractTabbedTerminalWidget$TabComponent.MyLabelHolder myLabelHolder;
        final /* synthetic */ AbstractTabbedTerminalWidget this$0;

        private TabComponent(final AbstractTabs<T> tabs, T terminal) {
            if (tabs == null) {
                TabComponent.$$$reportNull$$$0(0);
            }
            this.this$0 = var1_1;
            super(new FlowLayout(0, 0, 0));
            this.myLabelHolder = new MyLabelHolder();
            this.myTerminal = terminal;
            this.setOpaque(false);
            this.setFocusable(false);
            this.addFocusListener(this);
            TabComponentLabel label = new TabComponentLabel();
            label.addFocusListener(this);
            label.addMouseListener(new MouseAdapter((JediTermWidget)terminal){
                final /* synthetic */ JediTermWidget val$terminal;
                {
                    this.val$terminal = jediTermWidget;
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    TabComponent.this.handleMouse(event);
                }

                @Override
                public void mousePressed(MouseEvent event) {
                    tabs.setSelectedComponent(this.val$terminal);
                    TabComponent.this.handleMouse(event);
                }
            });
            this.myLabelHolder.set(label);
            this.add((Component)this.myLabelHolder);
        }

        protected void handleMouse(MouseEvent event) {
            if (event.isPopupTrigger()) {
                JPopupMenu menu = this.createPopup();
                menu.show(event.getComponent(), event.getX(), event.getY());
            } else if (event.getClickCount() == 2 && !event.isConsumed()) {
                event.consume();
                this.renameTab();
            }
        }

        protected JPopupMenu createPopup() {
            JPopupMenu popupMenu = new JPopupMenu();
            TerminalAction.addToMenu(popupMenu, this.this$0);
            JMenuItem rename = new JMenuItem("Rename Tab");
            rename.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    TabComponent.this.renameTab();
                }
            });
            popupMenu.add(rename);
            return popupMenu;
        }

        private void renameTab() {
            int selectedIndex = this.this$0.myTabs.getSelectedIndex();
            JLabel label = (JLabel)this.myLabelHolder.getComponent(0);
            new TabRenamer().install(selectedIndex, label.getText(), label, new TabRenamer.RenameCallBack(){

                @Override
                public void setComponent(Component c) {
                    TabComponent.this.myLabelHolder.set(c);
                }

                @Override
                public void setNewName(int index, String name) {
                    if (TabComponent.this.this$0.myTabs != null) {
                        TabComponent.this.this$0.myTabs.setTitleAt(index, name);
                    }
                }
            });
        }

        @Override
        public void focusGained(FocusEvent e) {
            ((JediTermWidget)this.myTerminal).getComponent().requestFocusInWindow();
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tabs", "com/jediterm/terminal/ui/AbstractTabbedTerminalWidget$TabComponent", "<init>"));
        }

        class TabComponentLabel
        extends JLabel {
            TabComponentLabel() {
            }

            TabComponent getTabComponent() {
                return TabComponent.this;
            }

            @Override
            public String getText() {
                int i;
                if (TabComponent.this.this$0.myTabs != null && (i = TabComponent.this.this$0.myTabs.indexOfTabComponent(TabComponent.this)) != -1) {
                    return TabComponent.this.this$0.myTabs.getTitleAt(i);
                }
                return null;
            }
        }

        private class MyLabelHolder
        extends JPanel {
            private MyLabelHolder() {
            }

            public void set(Component c) {
                TabComponent.this.myLabelHolder.removeAll();
                TabComponent.this.myLabelHolder.add(c);
                TabComponent.this.myLabelHolder.validate();
                TabComponent.this.myLabelHolder.repaint();
            }
        }
    }

    public static class TabRenamer {
        public void install(final int selectedIndex, String text, final Component label, final RenameCallBack callBack) {
            final JTextField textField = this.createTextField();
            textField.setOpaque(false);
            textField.setDocument(new JTextFieldLimit(50));
            textField.setText(text);
            final FocusAdapter focusAdapter = new FocusAdapter(){

                @Override
                public void focusLost(FocusEvent focusEvent) {
                    TabRenamer.finishRename(selectedIndex, label, textField.getText(), callBack);
                }
            };
            textField.addFocusListener(focusAdapter);
            textField.addKeyListener(new KeyAdapter(){

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    if (keyEvent.getKeyCode() == 27) {
                        textField.removeFocusListener(focusAdapter);
                        TabRenamer.finishRename(selectedIndex, label, null, callBack);
                    } else if (keyEvent.getKeyCode() == 10) {
                        textField.removeFocusListener(focusAdapter);
                        TabRenamer.finishRename(selectedIndex, label, textField.getText(), callBack);
                    } else {
                        super.keyPressed(keyEvent);
                    }
                }
            });
            callBack.setComponent(textField);
            textField.requestFocus();
            textField.selectAll();
        }

        protected JTextField createTextField() {
            return new JTextField();
        }

        private static void finishRename(int index, Component label, String newName, RenameCallBack callBack) {
            if (newName != null) {
                callBack.setNewName(index, newName);
            }
            callBack.setComponent(label);
        }

        public static interface RenameCallBack {
            public void setComponent(Component var1);

            public void setNewName(int var1, String var2);
        }
    }
}

