/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.extensions;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.ReadingOrder;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;

public class XSSFCellAlignment {
    private CTCellAlignment cellAlignement;

    public XSSFCellAlignment(CTCellAlignment cellAlignment) {
        this.cellAlignement = cellAlignment;
    }

    public VerticalAlignment getVertical() {
        STVerticalAlignment.Enum align = this.cellAlignement.getVertical();
        if (align == null) {
            align = STVerticalAlignment.BOTTOM;
        }
        return VerticalAlignment.values()[align.intValue() - 1];
    }

    public void setVertical(VerticalAlignment align) {
        this.cellAlignement.setVertical(STVerticalAlignment.Enum.forInt(align.ordinal() + 1));
    }

    public HorizontalAlignment getHorizontal() {
        STHorizontalAlignment.Enum align = this.cellAlignement.getHorizontal();
        if (align == null) {
            align = STHorizontalAlignment.GENERAL;
        }
        return HorizontalAlignment.values()[align.intValue() - 1];
    }

    public void setHorizontal(HorizontalAlignment align) {
        this.cellAlignement.setHorizontal(STHorizontalAlignment.Enum.forInt(align.ordinal() + 1));
    }

    public void setReadingOrder(ReadingOrder order) {
        this.cellAlignement.setReadingOrder(order.getCode());
    }

    public ReadingOrder getReadingOrder() {
        if (this.cellAlignement != null && this.cellAlignement.isSetReadingOrder()) {
            return ReadingOrder.forLong(this.cellAlignement.getReadingOrder());
        }
        return ReadingOrder.CONTEXT;
    }

    public long getIndent() {
        return this.cellAlignement.getIndent();
    }

    public void setIndent(long indent) {
        this.cellAlignement.setIndent(indent);
    }

    public long getTextRotation() {
        return this.cellAlignement.getTextRotation();
    }

    public void setTextRotation(long rotation) {
        if (rotation < 0L && rotation >= -90L) {
            rotation = 90L + -1L * rotation;
        }
        this.cellAlignement.setTextRotation(rotation);
    }

    public boolean getWrapText() {
        return this.cellAlignement.getWrapText();
    }

    public void setWrapText(boolean wrapped) {
        this.cellAlignement.setWrapText(wrapped);
    }

    public boolean getShrinkToFit() {
        return this.cellAlignement.getShrinkToFit();
    }

    public void setShrinkToFit(boolean shrink) {
        this.cellAlignement.setShrinkToFit(shrink);
    }

    @Internal
    public CTCellAlignment getCTCellAlignment() {
        return this.cellAlignement;
    }
}

