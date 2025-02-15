/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;

public class XWPFStyle {
    protected XWPFStyles styles;
    private CTStyle ctStyle;

    public XWPFStyle(CTStyle style) {
        this(style, null);
    }

    public XWPFStyle(CTStyle style, XWPFStyles styles) {
        this.ctStyle = style;
        this.styles = styles;
    }

    public String getStyleId() {
        return this.ctStyle.getStyleId();
    }

    public void setStyleId(String styleId) {
        this.ctStyle.setStyleId(styleId);
    }

    public STStyleType.Enum getType() {
        return this.ctStyle.getType();
    }

    public void setType(STStyleType.Enum type) {
        this.ctStyle.setType(type);
    }

    public void setStyle(CTStyle style) {
        this.ctStyle = style;
    }

    public CTStyle getCTStyle() {
        return this.ctStyle;
    }

    public XWPFStyles getStyles() {
        return this.styles;
    }

    public String getBasisStyleID() {
        if (this.ctStyle.getBasedOn() != null) {
            return this.ctStyle.getBasedOn().getVal();
        }
        return null;
    }

    public String getLinkStyleID() {
        if (this.ctStyle.getLink() != null) {
            return this.ctStyle.getLink().getVal();
        }
        return null;
    }

    public String getNextStyleID() {
        if (this.ctStyle.getNext() != null) {
            return this.ctStyle.getNext().getVal();
        }
        return null;
    }

    public String getName() {
        if (this.ctStyle.isSetName()) {
            return this.ctStyle.getName().getVal();
        }
        return null;
    }

    public boolean hasSameName(XWPFStyle compStyle) {
        CTStyle ctCompStyle = compStyle.getCTStyle();
        String name = ctCompStyle.getName().getVal();
        return name.equals(this.ctStyle.getName().getVal());
    }
}

