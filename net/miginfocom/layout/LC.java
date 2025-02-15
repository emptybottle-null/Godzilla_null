/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.UnitValue;

public final class LC
implements Externalizable {
    private int wrapAfter = 2097051;
    private Boolean leftToRight = null;
    private UnitValue[] insets = null;
    private UnitValue alignX = null;
    private UnitValue alignY = null;
    private BoundSize gridGapX = null;
    private BoundSize gridGapY = null;
    private BoundSize width = BoundSize.NULL_SIZE;
    private BoundSize height = BoundSize.NULL_SIZE;
    private BoundSize packW = BoundSize.NULL_SIZE;
    private BoundSize packH = BoundSize.NULL_SIZE;
    private float pwAlign = 0.5f;
    private float phAlign = 1.0f;
    private int debugMillis = 0;
    private int hideMode = 0;
    private boolean noCache = false;
    private boolean flowX = true;
    private boolean fillX = false;
    private boolean fillY = false;
    private boolean topToBottom = true;
    private boolean noGrid = false;
    private boolean visualPadding = true;

    public boolean isNoCache() {
        return this.noCache;
    }

    public void setNoCache(boolean b) {
        this.noCache = b;
    }

    public final UnitValue getAlignX() {
        return this.alignX;
    }

    public final void setAlignX(UnitValue uv) {
        this.alignX = uv;
    }

    public final UnitValue getAlignY() {
        return this.alignY;
    }

    public final void setAlignY(UnitValue uv) {
        this.alignY = uv;
    }

    public final int getDebugMillis() {
        return this.debugMillis;
    }

    public final void setDebugMillis(int millis) {
        this.debugMillis = millis;
    }

    public final boolean isFillX() {
        return this.fillX;
    }

    public final void setFillX(boolean b) {
        this.fillX = b;
    }

    public final boolean isFillY() {
        return this.fillY;
    }

    public final void setFillY(boolean b) {
        this.fillY = b;
    }

    public final boolean isFlowX() {
        return this.flowX;
    }

    public final void setFlowX(boolean b) {
        this.flowX = b;
    }

    public final BoundSize getGridGapX() {
        return this.gridGapX;
    }

    public final void setGridGapX(BoundSize x) {
        this.gridGapX = x;
    }

    public final BoundSize getGridGapY() {
        return this.gridGapY;
    }

    public final void setGridGapY(BoundSize y) {
        this.gridGapY = y;
    }

    public final int getHideMode() {
        return this.hideMode;
    }

    public final void setHideMode(int mode) {
        if (mode < 0 || mode > 3) {
            throw new IllegalArgumentException("Wrong hideMode: " + mode);
        }
        this.hideMode = mode;
    }

    public final UnitValue[] getInsets() {
        UnitValue[] unitValueArray;
        if (this.insets != null) {
            UnitValue[] unitValueArray2 = new UnitValue[4];
            unitValueArray2[0] = this.insets[0];
            unitValueArray2[1] = this.insets[1];
            unitValueArray2[2] = this.insets[2];
            unitValueArray = unitValueArray2;
            unitValueArray2[3] = this.insets[3];
        } else {
            unitValueArray = null;
        }
        return unitValueArray;
    }

    public final void setInsets(UnitValue[] ins) {
        UnitValue[] unitValueArray;
        if (ins != null) {
            UnitValue[] unitValueArray2 = new UnitValue[4];
            unitValueArray2[0] = ins[0];
            unitValueArray2[1] = ins[1];
            unitValueArray2[2] = ins[2];
            unitValueArray = unitValueArray2;
            unitValueArray2[3] = ins[3];
        } else {
            unitValueArray = null;
        }
        this.insets = unitValueArray;
    }

    public final Boolean getLeftToRight() {
        return this.leftToRight;
    }

    public final void setLeftToRight(Boolean b) {
        this.leftToRight = b;
    }

    public final boolean isNoGrid() {
        return this.noGrid;
    }

    public final void setNoGrid(boolean b) {
        this.noGrid = b;
    }

    public final boolean isTopToBottom() {
        return this.topToBottom;
    }

    public final void setTopToBottom(boolean b) {
        this.topToBottom = b;
    }

    public final boolean isVisualPadding() {
        return this.visualPadding;
    }

    public final void setVisualPadding(boolean b) {
        this.visualPadding = b;
    }

    public final int getWrapAfter() {
        return this.wrapAfter;
    }

    public final void setWrapAfter(int count) {
        this.wrapAfter = count;
    }

    public final BoundSize getPackWidth() {
        return this.packW;
    }

    public final void setPackWidth(BoundSize size) {
        this.packW = size != null ? size : BoundSize.NULL_SIZE;
    }

    public final BoundSize getPackHeight() {
        return this.packH;
    }

    public final void setPackHeight(BoundSize size) {
        this.packH = size != null ? size : BoundSize.NULL_SIZE;
    }

    public final float getPackHeightAlign() {
        return this.phAlign;
    }

    public final void setPackHeightAlign(float align) {
        this.phAlign = Math.max(0.0f, Math.min(1.0f, align));
    }

    public final float getPackWidthAlign() {
        return this.pwAlign;
    }

    public final void setPackWidthAlign(float align) {
        this.pwAlign = Math.max(0.0f, Math.min(1.0f, align));
    }

    public final BoundSize getWidth() {
        return this.width;
    }

    public final void setWidth(BoundSize size) {
        this.width = size != null ? size : BoundSize.NULL_SIZE;
    }

    public final BoundSize getHeight() {
        return this.height;
    }

    public final void setHeight(BoundSize size) {
        this.height = size != null ? size : BoundSize.NULL_SIZE;
    }

    public final LC pack() {
        return this.pack("pref", "pref");
    }

    public final LC pack(String width, String height) {
        this.setPackWidth(width != null ? ConstraintParser.parseBoundSize(width, false, true) : BoundSize.NULL_SIZE);
        this.setPackHeight(height != null ? ConstraintParser.parseBoundSize(height, false, false) : BoundSize.NULL_SIZE);
        return this;
    }

    public final LC packAlign(float alignX, float alignY) {
        this.setPackWidthAlign(alignX);
        this.setPackHeightAlign(alignY);
        return this;
    }

    public final LC wrap() {
        this.setWrapAfter(0);
        return this;
    }

    public final LC wrapAfter(int count) {
        this.setWrapAfter(count);
        return this;
    }

    public final LC noCache() {
        this.setNoCache(true);
        return this;
    }

    public final LC flowY() {
        this.setFlowX(false);
        return this;
    }

    public final LC flowX() {
        this.setFlowX(true);
        return this;
    }

    public final LC fill() {
        this.setFillX(true);
        this.setFillY(true);
        return this;
    }

    public final LC fillX() {
        this.setFillX(true);
        return this;
    }

    public final LC fillY() {
        this.setFillY(true);
        return this;
    }

    public final LC leftToRight(boolean b) {
        this.setLeftToRight(b ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public final LC rightToLeft() {
        this.setLeftToRight(Boolean.FALSE);
        return this;
    }

    public final LC bottomToTop() {
        this.setTopToBottom(false);
        return this;
    }

    public final LC topToBottom() {
        this.setTopToBottom(true);
        return this;
    }

    public final LC noGrid() {
        this.setNoGrid(true);
        return this;
    }

    public final LC noVisualPadding() {
        this.setVisualPadding(false);
        return this;
    }

    public final LC insetsAll(String allSides) {
        UnitValue insH = ConstraintParser.parseUnitValue(allSides, true);
        UnitValue insV = ConstraintParser.parseUnitValue(allSides, false);
        this.insets = new UnitValue[]{insV, insH, insV, insH};
        return this;
    }

    public final LC insets(String s) {
        this.insets = ConstraintParser.parseInsets(s, true);
        return this;
    }

    public final LC insets(String top, String left, String bottom, String right) {
        this.insets = new UnitValue[]{ConstraintParser.parseUnitValue(top, false), ConstraintParser.parseUnitValue(left, true), ConstraintParser.parseUnitValue(bottom, false), ConstraintParser.parseUnitValue(right, true)};
        return this;
    }

    public final LC alignX(String align) {
        this.setAlignX(ConstraintParser.parseUnitValueOrAlign(align, true, null));
        return this;
    }

    public final LC alignY(String align) {
        this.setAlignY(ConstraintParser.parseUnitValueOrAlign(align, false, null));
        return this;
    }

    public final LC align(String ax, String ay) {
        if (ax != null) {
            this.alignX(ax);
        }
        if (ay != null) {
            this.alignY(ay);
        }
        return this;
    }

    public final LC gridGapX(String boundsSize) {
        this.setGridGapX(ConstraintParser.parseBoundSize(boundsSize, true, true));
        return this;
    }

    public final LC gridGapY(String boundsSize) {
        this.setGridGapY(ConstraintParser.parseBoundSize(boundsSize, true, false));
        return this;
    }

    public final LC gridGap(String gapx, String gapy) {
        if (gapx != null) {
            this.gridGapX(gapx);
        }
        if (gapy != null) {
            this.gridGapY(gapy);
        }
        return this;
    }

    public final LC debug() {
        this.setDebugMillis(300);
        return this;
    }

    public final LC debug(int repaintMillis) {
        this.setDebugMillis(repaintMillis);
        return this;
    }

    public final LC hideMode(int mode) {
        this.setHideMode(mode);
        return this;
    }

    public final LC minWidth(String width) {
        this.setWidth(LayoutUtil.derive(this.getWidth(), ConstraintParser.parseUnitValue(width, true), null, null));
        return this;
    }

    public final LC width(String width) {
        this.setWidth(ConstraintParser.parseBoundSize(width, false, true));
        return this;
    }

    public final LC maxWidth(String width) {
        this.setWidth(LayoutUtil.derive(this.getWidth(), null, null, ConstraintParser.parseUnitValue(width, true)));
        return this;
    }

    public final LC minHeight(String height) {
        this.setHeight(LayoutUtil.derive(this.getHeight(), ConstraintParser.parseUnitValue(height, false), null, null));
        return this;
    }

    public final LC height(String height) {
        this.setHeight(ConstraintParser.parseBoundSize(height, false, false));
        return this;
    }

    public final LC maxHeight(String height) {
        this.setHeight(LayoutUtil.derive(this.getHeight(), null, null, ConstraintParser.parseUnitValue(height, false)));
        return this;
    }

    private Object readResolve() throws ObjectStreamException {
        return LayoutUtil.getSerializedObject(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        LayoutUtil.setSerializedObject(this, LayoutUtil.readAsXML(in));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        if (this.getClass() == LC.class) {
            LayoutUtil.writeAsXML(out, this);
        }
    }
}

