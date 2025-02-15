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
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.PlatformDefaults;
import net.miginfocom.layout.ResizeConstraint;
import net.miginfocom.layout.UnitValue;

public final class DimConstraint
implements Externalizable {
    final ResizeConstraint resize = new ResizeConstraint();
    private String sizeGroup = null;
    private BoundSize size = BoundSize.NULL_SIZE;
    private BoundSize gapBefore = null;
    private BoundSize gapAfter = null;
    private UnitValue align = null;
    private String endGroup = null;
    private boolean fill = false;
    private boolean noGrid = false;

    public int getGrowPriority() {
        return this.resize.growPrio;
    }

    public void setGrowPriority(int p) {
        this.resize.growPrio = p;
    }

    public Float getGrow() {
        return this.resize.grow;
    }

    public void setGrow(Float weight) {
        this.resize.grow = weight;
    }

    public int getShrinkPriority() {
        return this.resize.shrinkPrio;
    }

    public void setShrinkPriority(int p) {
        this.resize.shrinkPrio = p;
    }

    public Float getShrink() {
        return this.resize.shrink;
    }

    public void setShrink(Float weight) {
        this.resize.shrink = weight;
    }

    public UnitValue getAlignOrDefault(boolean isCols) {
        if (this.align != null) {
            return this.align;
        }
        if (isCols) {
            return UnitValue.LEADING;
        }
        return this.fill || !PlatformDefaults.getDefaultRowAlignmentBaseline() ? UnitValue.CENTER : UnitValue.BASELINE_IDENTITY;
    }

    public UnitValue getAlign() {
        return this.align;
    }

    public void setAlign(UnitValue uv) {
        this.align = uv;
    }

    public BoundSize getGapAfter() {
        return this.gapAfter;
    }

    public void setGapAfter(BoundSize size) {
        this.gapAfter = size;
    }

    boolean hasGapAfter() {
        return this.gapAfter != null && !this.gapAfter.isUnset();
    }

    boolean isGapAfterPush() {
        return this.gapAfter != null && this.gapAfter.getGapPush();
    }

    public BoundSize getGapBefore() {
        return this.gapBefore;
    }

    public void setGapBefore(BoundSize size) {
        this.gapBefore = size;
    }

    boolean hasGapBefore() {
        return this.gapBefore != null && !this.gapBefore.isUnset();
    }

    boolean isGapBeforePush() {
        return this.gapBefore != null && this.gapBefore.getGapPush();
    }

    public BoundSize getSize() {
        return this.size;
    }

    public void setSize(BoundSize size) {
        if (size != null) {
            size.checkNotLinked();
        }
        this.size = size;
    }

    public String getSizeGroup() {
        return this.sizeGroup;
    }

    public void setSizeGroup(String s) {
        this.sizeGroup = s;
    }

    public String getEndGroup() {
        return this.endGroup;
    }

    public void setEndGroup(String s) {
        this.endGroup = s;
    }

    public boolean isFill() {
        return this.fill;
    }

    public void setFill(boolean b) {
        this.fill = b;
    }

    public boolean isNoGrid() {
        return this.noGrid;
    }

    public void setNoGrid(boolean b) {
        this.noGrid = b;
    }

    int[] getRowGaps(ContainerWrapper parent, BoundSize defGap, int refSize, boolean before) {
        BoundSize gap;
        BoundSize boundSize = gap = before ? this.gapBefore : this.gapAfter;
        if (gap == null || gap.isUnset()) {
            gap = defGap;
        }
        if (gap == null || gap.isUnset()) {
            return null;
        }
        int[] ret = new int[3];
        for (int i = 0; i <= 2; ++i) {
            UnitValue uv = gap.getSize(i);
            ret[i] = uv != null ? uv.getPixels(refSize, parent, null) : -2147471302;
        }
        return ret;
    }

    int[] getComponentGaps(ContainerWrapper parent, ComponentWrapper comp, BoundSize adjGap, ComponentWrapper adjacentComp, String tag, int refSize, int adjacentSide, boolean isLTR) {
        boolean hasGap;
        BoundSize gap = adjacentSide < 2 ? this.gapBefore : this.gapAfter;
        boolean bl = hasGap = gap != null && gap.getGapPush();
        if ((gap == null || gap.isUnset()) && (adjGap == null || adjGap.isUnset()) && comp != null) {
            gap = PlatformDefaults.getDefaultComponentGap(comp, adjacentComp, adjacentSide + 1, tag, isLTR);
        }
        if (gap == null) {
            int[] nArray;
            if (hasGap) {
                int[] nArray2 = new int[3];
                nArray2[0] = 0;
                nArray2[1] = 0;
                nArray = nArray2;
                nArray2[2] = -2147471302;
            } else {
                nArray = null;
            }
            return nArray;
        }
        int[] ret = new int[3];
        for (int i = 0; i <= 2; ++i) {
            UnitValue uv = gap.getSize(i);
            ret[i] = uv != null ? uv.getPixels(refSize, parent, null) : -2147471302;
        }
        return ret;
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
        if (this.getClass() == DimConstraint.class) {
            LayoutUtil.writeAsXML(out, this);
        }
    }
}

