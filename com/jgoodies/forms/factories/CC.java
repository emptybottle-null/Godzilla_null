/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.factories;

import com.jgoodies.forms.layout.CellConstraints;
import java.io.Serializable;

public final class CC
implements Cloneable,
Serializable {
    public static final CellConstraints.Alignment DEFAULT = CellConstraints.DEFAULT;
    public static final CellConstraints.Alignment FILL = CellConstraints.FILL;
    public static final CellConstraints.Alignment LEFT = CellConstraints.LEFT;
    public static final CellConstraints.Alignment RIGHT = CellConstraints.RIGHT;
    public static final CellConstraints.Alignment CENTER = CellConstraints.CENTER;
    public static final CellConstraints.Alignment TOP = CellConstraints.TOP;
    public static final CellConstraints.Alignment BOTTOM = CellConstraints.BOTTOM;

    public static CellConstraints xy(int col, int row) {
        return CC.xywh(col, row, 1, 1);
    }

    public static CellConstraints xy(int col, int row, String encodedAlignments) {
        return CC.xywh(col, row, 1, 1, encodedAlignments);
    }

    public static CellConstraints xy(int col, int row, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
        return CC.xywh(col, row, 1, 1, colAlign, rowAlign);
    }

    public static CellConstraints xyw(int col, int row, int colSpan) {
        return CC.xywh(col, row, colSpan, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT);
    }

    public static CellConstraints xyw(int col, int row, int colSpan, String encodedAlignments) {
        return CC.xywh(col, row, colSpan, 1, encodedAlignments);
    }

    public static CellConstraints xyw(int col, int row, int colSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
        return CC.xywh(col, row, colSpan, 1, colAlign, rowAlign);
    }

    public static CellConstraints xywh(int col, int row, int colSpan, int rowSpan) {
        return CC.xywh(col, row, colSpan, rowSpan, CellConstraints.DEFAULT, CellConstraints.DEFAULT);
    }

    public static CellConstraints xywh(int col, int row, int colSpan, int rowSpan, String encodedAlignments) {
        return new CellConstraints().xywh(col, row, colSpan, rowSpan, encodedAlignments);
    }

    public static CellConstraints xywh(int col, int row, int colSpan, int rowSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
        return new CellConstraints(col, row, colSpan, rowSpan, colAlign, rowAlign);
    }

    public static CellConstraints rc(int row, int col) {
        return CC.rchw(row, col, 1, 1);
    }

    public static CellConstraints rc(int row, int col, String encodedAlignments) {
        return CC.rchw(row, col, 1, 1, encodedAlignments);
    }

    public static CellConstraints rc(int row, int col, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
        return CC.rchw(row, col, 1, 1, rowAlign, colAlign);
    }

    public static CellConstraints rcw(int row, int col, int colSpan) {
        return CC.rchw(row, col, 1, colSpan, CellConstraints.DEFAULT, CellConstraints.DEFAULT);
    }

    public static CellConstraints rcw(int row, int col, int colSpan, String encodedAlignments) {
        return CC.rchw(row, col, 1, colSpan, encodedAlignments);
    }

    public static CellConstraints rcw(int row, int col, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
        return CC.rchw(row, col, 1, colSpan, rowAlign, colAlign);
    }

    public static CellConstraints rchw(int row, int col, int rowSpan, int colSpan) {
        return CC.rchw(row, col, rowSpan, colSpan, CellConstraints.DEFAULT, CellConstraints.DEFAULT);
    }

    public static CellConstraints rchw(int row, int col, int rowSpan, int colSpan, String encodedAlignments) {
        return new CellConstraints().rchw(row, col, rowSpan, colSpan, encodedAlignments);
    }

    public static CellConstraints rchw(int row, int col, int rowSpan, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
        return CC.xywh(col, row, colSpan, rowSpan, colAlign, rowAlign);
    }
}

