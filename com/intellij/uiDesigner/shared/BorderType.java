/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.shared;

import com.intellij.uiDesigner.compiler.UnexpectedFormElementException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class BorderType {
    public static final BorderType NONE = new BorderType("none", "None", null, null);
    public static final BorderType BEVEL_LOWERED = new BorderType("bevel-lowered", "Bevel Lowered", BorderFactory.createLoweredBevelBorder(), "createLoweredBevelBorder");
    public static final BorderType BEVEL_RAISED = new BorderType("bevel-raised", "Bevel Raised", BorderFactory.createRaisedBevelBorder(), "createRaisedBevelBorder");
    public static final BorderType ETCHED = new BorderType("etched", "Etched", BorderFactory.createEtchedBorder(), "createEtchedBorder");
    public static final BorderType LINE = new BorderType("line", "Line", BorderFactory.createLineBorder(Color.BLACK), "createLineBorder");
    public static final BorderType EMPTY = new BorderType("empty", "Empty", BorderFactory.createEmptyBorder(0, 0, 0, 0), "createEmptyBorder");
    private final String myId;
    private final String myName;
    private final Border myBorder;
    private final String myBorderFactoryMethodName;

    private BorderType(String id, String name, Border border, String borderFactoryMethodName) {
        this.myId = id;
        this.myName = name;
        this.myBorder = border;
        this.myBorderFactoryMethodName = borderFactoryMethodName;
    }

    public String getId() {
        return this.myId;
    }

    public String getName() {
        return this.myName;
    }

    public Border createBorder(String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor, Insets borderSize, Color borderColor) {
        Border baseBorder = this.myBorder;
        if (this.equals(EMPTY) && borderSize != null) {
            baseBorder = BorderFactory.createEmptyBorder(borderSize.top, borderSize.left, borderSize.bottom, borderSize.right);
        } else if (this.equals(LINE) && borderColor != null) {
            baseBorder = BorderFactory.createLineBorder(borderColor);
        }
        if (title != null) {
            return BorderFactory.createTitledBorder(baseBorder, title, titleJustification, titlePosition, titleFont, titleColor);
        }
        return baseBorder;
    }

    public String getBorderFactoryMethodName() {
        return this.myBorderFactoryMethodName;
    }

    public boolean equals(Object o) {
        if (o instanceof BorderType) {
            return this.myId.equals(((BorderType)o).myId);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public static BorderType valueOf(String name) {
        BorderType[] allTypes = BorderType.getAllTypes();
        for (int i = 0; i < allTypes.length; ++i) {
            if (!allTypes[i].getId().equals(name)) continue;
            return allTypes[i];
        }
        throw new UnexpectedFormElementException("unknown type: " + name);
    }

    public static BorderType[] getAllTypes() {
        return new BorderType[]{NONE, EMPTY, BEVEL_LOWERED, BEVEL_RAISED, ETCHED, LINE};
    }
}

