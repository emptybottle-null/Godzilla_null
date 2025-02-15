/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.ui.TerminalActionPresentation;
import com.jediterm.terminal.ui.TerminalActionProvider;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerminalAction {
    private final String myName;
    private final KeyStroke[] myKeyStrokes;
    private final Predicate<KeyEvent> myRunnable;
    private Character myMnemonic;
    private Supplier<Boolean> myEnabledSupplier;
    private Integer myMnemonicKey;
    private boolean mySeparatorBefore;
    private boolean myHidden;

    public TerminalAction(@NotNull TerminalActionPresentation presentation, @NotNull Predicate<KeyEvent> runnable) {
        if (presentation == null) {
            TerminalAction.$$$reportNull$$$0(0);
        }
        if (runnable == null) {
            TerminalAction.$$$reportNull$$$0(1);
        }
        this(presentation.getName(), presentation.getKeyStrokes().toArray(new KeyStroke[0]), runnable);
    }

    public TerminalAction(@NotNull TerminalActionPresentation presentation) {
        if (presentation == null) {
            TerminalAction.$$$reportNull$$$0(2);
        }
        this(presentation, keyEvent -> true);
    }

    public TerminalAction(@NotNull String name, @NotNull KeyStroke[] keyStrokes, @NotNull Predicate<KeyEvent> runnable) {
        if (name == null) {
            TerminalAction.$$$reportNull$$$0(3);
        }
        if (runnable == null) {
            TerminalAction.$$$reportNull$$$0(4);
        }
        if (keyStrokes == null) {
            TerminalAction.$$$reportNull$$$0(5);
        }
        this.myMnemonic = null;
        this.myEnabledSupplier = () -> true;
        this.myMnemonicKey = null;
        this.mySeparatorBefore = false;
        this.myHidden = false;
        this.myName = name;
        this.myKeyStrokes = keyStrokes;
        this.myRunnable = runnable;
    }

    public boolean matches(KeyEvent e) {
        for (KeyStroke ks : this.myKeyStrokes) {
            if (!ks.equals(KeyStroke.getKeyStrokeForEvent(e))) continue;
            return true;
        }
        return false;
    }

    public boolean isEnabled(@Nullable KeyEvent e) {
        return this.myEnabledSupplier.get();
    }

    public boolean actionPerformed(@Nullable KeyEvent e) {
        return this.myRunnable.test(e);
    }

    public static boolean processEvent(@NotNull TerminalActionProvider actionProvider, @NotNull KeyEvent e) {
        if (actionProvider == null) {
            TerminalAction.$$$reportNull$$$0(6);
        }
        if (e == null) {
            TerminalAction.$$$reportNull$$$0(7);
        }
        for (TerminalAction a : actionProvider.getActions()) {
            if (!a.matches(e)) continue;
            return a.isEnabled(e) && a.actionPerformed(e);
        }
        if (actionProvider.getNextProvider() != null) {
            return TerminalAction.processEvent(actionProvider.getNextProvider(), e);
        }
        return false;
    }

    public static boolean addToMenu(JPopupMenu menu, TerminalActionProvider actionProvider) {
        boolean added = false;
        if (actionProvider.getNextProvider() != null) {
            added = TerminalAction.addToMenu(menu, actionProvider.getNextProvider());
        }
        boolean addSeparator = added;
        for (TerminalAction a : actionProvider.getActions()) {
            if (a.isHidden()) continue;
            if (!addSeparator) {
                addSeparator = a.isSeparated();
            }
            if (addSeparator) {
                menu.addSeparator();
                addSeparator = false;
            }
            menu.add(a.toMenuItem());
            added = true;
        }
        return added;
    }

    public int getKeyCode() {
        int n = 0;
        KeyStroke[] keyStrokeArray = this.myKeyStrokes;
        int n2 = keyStrokeArray.length;
        if (n < n2) {
            KeyStroke ks = keyStrokeArray[n];
            return ks.getKeyCode();
        }
        return 0;
    }

    public int getModifiers() {
        int n = 0;
        KeyStroke[] keyStrokeArray = this.myKeyStrokes;
        int n2 = keyStrokeArray.length;
        if (n < n2) {
            KeyStroke ks = keyStrokeArray[n];
            return ks.getModifiers();
        }
        return 0;
    }

    public String getName() {
        return this.myName;
    }

    public TerminalAction withMnemonic(Character ch) {
        this.myMnemonic = ch;
        return this;
    }

    public TerminalAction withMnemonicKey(Integer key) {
        this.myMnemonicKey = key;
        return this;
    }

    public TerminalAction withEnabledSupplier(@NotNull Supplier<Boolean> enabledSupplier) {
        if (enabledSupplier == null) {
            TerminalAction.$$$reportNull$$$0(8);
        }
        this.myEnabledSupplier = enabledSupplier;
        return this;
    }

    public TerminalAction separatorBefore(boolean enabled) {
        this.mySeparatorBefore = enabled;
        return this;
    }

    public JMenuItem toMenuItem() {
        JMenuItem menuItem = new JMenuItem(this.myName);
        if (this.myMnemonic != null) {
            menuItem.setMnemonic(this.myMnemonic.charValue());
        }
        if (this.myMnemonicKey != null) {
            menuItem.setMnemonic(this.myMnemonicKey);
        }
        if (this.myKeyStrokes.length > 0) {
            menuItem.setAccelerator(this.myKeyStrokes[0]);
        }
        menuItem.addActionListener(actionEvent -> this.actionPerformed(null));
        menuItem.setEnabled(this.isEnabled(null));
        return menuItem;
    }

    public boolean isSeparated() {
        return this.mySeparatorBefore;
    }

    public boolean isHidden() {
        return this.myHidden;
    }

    public TerminalAction withHidden(boolean hidden) {
        this.myHidden = hidden;
        return this;
    }

    public String toString() {
        return "'" + this.myName + "'";
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "presentation";
                break;
            }
            case 1: 
            case 4: {
                objectArray2 = objectArray3;
                objectArray3[0] = "runnable";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "name";
                break;
            }
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "keyStrokes";
                break;
            }
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "actionProvider";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "e";
                break;
            }
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "enabledSupplier";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/ui/TerminalAction";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "<init>";
                break;
            }
            case 6: 
            case 7: {
                objectArray = objectArray2;
                objectArray2[2] = "processEvent";
                break;
            }
            case 8: {
                objectArray = objectArray2;
                objectArray2[2] = "withEnabledSupplier";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

