/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator.charset;

import com.jediterm.terminal.emulator.charset.CharacterSet;
import com.jediterm.terminal.emulator.charset.CharacterSets;
import com.jediterm.terminal.emulator.charset.GraphicSet;
import org.jetbrains.annotations.NotNull;

public class GraphicSetState {
    private final GraphicSet[] myGraphicSets = new GraphicSet[4];
    private GraphicSet myGL;
    private GraphicSet myGR;
    private GraphicSet myGlOverride;

    public GraphicSetState() {
        for (int i = 0; i < this.myGraphicSets.length; ++i) {
            this.myGraphicSets[i] = new GraphicSet(i);
        }
        this.resetState();
    }

    public void designateGraphicSet(@NotNull GraphicSet graphicSet, char designator) {
        if (graphicSet == null) {
            GraphicSetState.$$$reportNull$$$0(0);
        }
        graphicSet.setDesignation(CharacterSet.valueOf(designator));
    }

    public void designateGraphicSet(int num, CharacterSet characterSet) {
        this.getGraphicSet(num).setDesignation(characterSet);
    }

    @NotNull
    public GraphicSet getGL() {
        GraphicSet result = this.myGL;
        if (this.myGlOverride != null) {
            result = this.myGlOverride;
            this.myGlOverride = null;
        }
        GraphicSet graphicSet = result;
        if (graphicSet == null) {
            GraphicSetState.$$$reportNull$$$0(1);
        }
        return graphicSet;
    }

    @NotNull
    public GraphicSet getGR() {
        GraphicSet graphicSet = this.myGR;
        if (graphicSet == null) {
            GraphicSetState.$$$reportNull$$$0(2);
        }
        return graphicSet;
    }

    @NotNull
    public GraphicSet getGraphicSet(int index) {
        GraphicSet graphicSet = this.myGraphicSets[index % 4];
        if (graphicSet == null) {
            GraphicSetState.$$$reportNull$$$0(3);
        }
        return graphicSet;
    }

    public char map(char ch) {
        return CharacterSets.getChar(ch, this.getGL(), this.getGR());
    }

    public void overrideGL(int index) {
        this.myGlOverride = this.getGraphicSet(index);
    }

    public void resetState() {
        for (int i = 0; i < this.myGraphicSets.length; ++i) {
            this.myGraphicSets[i].setDesignation(CharacterSet.valueOf(i == 1 ? (char)'0' : 'B'));
        }
        this.myGL = this.myGraphicSets[0];
        this.myGR = this.myGraphicSets[1];
        this.myGlOverride = null;
    }

    public void setGL(int index) {
        this.myGL = this.getGraphicSet(index);
    }

    public void setGR(int index) {
        this.myGR = this.getGraphicSet(index);
    }

    public int getGLOverrideIndex() {
        return this.myGlOverride != null ? this.myGlOverride.getIndex() : -1;
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
            case 1: 
            case 2: 
            case 3: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "graphicSet";
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/emulator/charset/GraphicSetState";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/emulator/charset/GraphicSetState";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[1] = "getGL";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "getGR";
                break;
            }
            case 3: {
                objectArray = objectArray2;
                objectArray2[1] = "getGraphicSet";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "designateGraphicSet";
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

