/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import java.util.Collections;
import java.util.List;
import javax.swing.KeyStroke;
import org.jetbrains.annotations.NotNull;

public class TerminalActionPresentation {
    private final String myName;
    private final List<KeyStroke> myKeyStrokes;

    public TerminalActionPresentation(@NotNull String name, @NotNull KeyStroke keyStroke) {
        if (name == null) {
            TerminalActionPresentation.$$$reportNull$$$0(0);
        }
        if (keyStroke == null) {
            TerminalActionPresentation.$$$reportNull$$$0(1);
        }
        this(name, Collections.singletonList(keyStroke));
    }

    public TerminalActionPresentation(@NotNull String name, @NotNull List<KeyStroke> keyStrokes) {
        if (name == null) {
            TerminalActionPresentation.$$$reportNull$$$0(2);
        }
        if (keyStrokes == null) {
            TerminalActionPresentation.$$$reportNull$$$0(3);
        }
        this.myName = name;
        this.myKeyStrokes = keyStrokes;
    }

    @NotNull
    public String getName() {
        String string = this.myName;
        if (string == null) {
            TerminalActionPresentation.$$$reportNull$$$0(4);
        }
        return string;
    }

    @NotNull
    public List<KeyStroke> getKeyStrokes() {
        List<KeyStroke> list = this.myKeyStrokes;
        if (list == null) {
            TerminalActionPresentation.$$$reportNull$$$0(5);
        }
        return list;
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
            case 4: 
            case 5: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 4: 
            case 5: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "name";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "keyStroke";
                break;
            }
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "keyStrokes";
                break;
            }
            case 4: 
            case 5: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/ui/TerminalActionPresentation";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/ui/TerminalActionPresentation";
                break;
            }
            case 4: {
                objectArray = objectArray2;
                objectArray2[1] = "getName";
                break;
            }
            case 5: {
                objectArray = objectArray2;
                objectArray2[1] = "getKeyStrokes";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 4: 
            case 5: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 4: 
            case 5: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

