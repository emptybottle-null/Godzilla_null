/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.apache.poi.xddf.usermodel.text.FontAlignment;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
import org.apache.poi.xddf.usermodel.text.XDDFParagraphBulletProperties;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.apache.poi.xddf.usermodel.text.XDDFTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;

public class XDDFParagraphProperties {
    private CTTextParagraphProperties props;
    private XDDFParagraphBulletProperties bullet;

    @Internal
    protected XDDFParagraphProperties(CTTextParagraphProperties properties) {
        this.props = properties;
        this.bullet = new XDDFParagraphBulletProperties(properties);
    }

    @Internal
    protected CTTextParagraphProperties getXmlObject() {
        return this.props;
    }

    public XDDFParagraphBulletProperties getBulletProperties() {
        return this.bullet;
    }

    public int getLevel() {
        if (this.props.isSetLvl()) {
            return 1 + this.props.getLvl();
        }
        return 0;
    }

    public void setLevel(Integer level) {
        if (level == null) {
            if (this.props.isSetLvl()) {
                this.props.unsetLvl();
            }
        } else {
            if (level < 1 || 9 < level) {
                throw new IllegalArgumentException("Minimum inclusive: 1. Maximum inclusive: 9.");
            }
            this.props.setLvl(level - 1);
        }
    }

    public XDDFRunProperties addDefaultRunProperties() {
        if (!this.props.isSetDefRPr()) {
            this.props.addNewDefRPr();
        }
        return this.getDefaultRunProperties();
    }

    public XDDFRunProperties getDefaultRunProperties() {
        if (this.props.isSetDefRPr()) {
            return new XDDFRunProperties(this.props.getDefRPr());
        }
        return null;
    }

    public void setDefaultRunProperties(XDDFRunProperties properties) {
        if (properties == null) {
            if (this.props.isSetDefRPr()) {
                this.props.unsetDefRPr();
            }
        } else {
            this.props.setDefRPr(properties.getXmlObject());
        }
    }

    public void setEastAsianLineBreak(Boolean value) {
        if (value == null) {
            if (this.props.isSetEaLnBrk()) {
                this.props.unsetEaLnBrk();
            }
        } else {
            this.props.setEaLnBrk(value);
        }
    }

    public void setLatinLineBreak(Boolean value) {
        if (value == null) {
            if (this.props.isSetLatinLnBrk()) {
                this.props.unsetLatinLnBrk();
            }
        } else {
            this.props.setLatinLnBrk(value);
        }
    }

    public void setHangingPunctuation(Boolean value) {
        if (value == null) {
            if (this.props.isSetHangingPunct()) {
                this.props.unsetHangingPunct();
            }
        } else {
            this.props.setHangingPunct(value);
        }
    }

    public void setRightToLeft(Boolean value) {
        if (value == null) {
            if (this.props.isSetRtl()) {
                this.props.unsetRtl();
            }
        } else {
            this.props.setRtl(value);
        }
    }

    public void setFontAlignment(FontAlignment align) {
        if (align == null) {
            if (this.props.isSetFontAlgn()) {
                this.props.unsetFontAlgn();
            }
        } else {
            this.props.setFontAlgn(align.underlying);
        }
    }

    public void setTextAlignment(TextAlignment align) {
        if (align == null) {
            if (this.props.isSetAlgn()) {
                this.props.unsetAlgn();
            }
        } else {
            this.props.setAlgn(align.underlying);
        }
    }

    public void setDefaultTabSize(Double points) {
        if (points == null) {
            if (this.props.isSetDefTabSz()) {
                this.props.unsetDefTabSz();
            }
        } else {
            this.props.setDefTabSz(Units.toEMU(points));
        }
    }

    public void setIndentation(Double points) {
        if (points == null) {
            if (this.props.isSetIndent()) {
                this.props.unsetIndent();
            }
        } else {
            if (points < -4032.0 || 4032.0 < points) {
                throw new IllegalArgumentException("Minimum inclusive = -4032. Maximum inclusive = 4032.");
            }
            this.props.setIndent(Units.toEMU(points));
        }
    }

    public void setMarginLeft(Double points) {
        if (points == null) {
            if (this.props.isSetMarL()) {
                this.props.unsetMarL();
            }
        } else {
            if (points < 0.0 || 4032.0 < points) {
                throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
            }
            this.props.setMarL(Units.toEMU(points));
        }
    }

    public void setMarginRight(Double points) {
        if (points == null) {
            if (this.props.isSetMarR()) {
                this.props.unsetMarR();
            }
        } else {
            if (points < 0.0 || 4032.0 < points) {
                throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4032.");
            }
            this.props.setMarR(Units.toEMU(points));
        }
    }

    public void setLineSpacing(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetLnSpc()) {
                this.props.unsetLnSpc();
            }
        } else {
            this.props.setLnSpc(spacing.getXmlObject());
        }
    }

    public void setSpaceAfter(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetSpcAft()) {
                this.props.unsetSpcAft();
            }
        } else {
            this.props.setSpcAft(spacing.getXmlObject());
        }
    }

    public void setSpaceBefore(XDDFSpacing spacing) {
        if (spacing == null) {
            if (this.props.isSetSpcBef()) {
                this.props.unsetSpcBef();
            }
        } else {
            this.props.setSpcBef(spacing.getXmlObject());
        }
    }

    public XDDFTabStop addTabStop() {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().addNewTab());
    }

    public XDDFTabStop insertTabStop(int index) {
        if (!this.props.isSetTabLst()) {
            this.props.addNewTabLst();
        }
        return new XDDFTabStop(this.props.getTabLst().insertNewTab(index));
    }

    public void removeTabStop(int index) {
        if (this.props.isSetTabLst()) {
            this.props.getTabLst().removeTab(index);
        }
    }

    public XDDFTabStop getTabStop(int index) {
        if (this.props.isSetTabLst()) {
            return new XDDFTabStop(this.props.getTabLst().getTabArray(index));
        }
        return null;
    }

    public List<XDDFTabStop> getTabStops() {
        if (this.props.isSetTabLst()) {
            return Collections.unmodifiableList(this.props.getTabLst().getTabList().stream().map(gs -> new XDDFTabStop((CTTextTabStop)gs)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public int countTabStops() {
        if (this.props.isSetTabLst()) {
            return this.props.getTabLst().sizeOfTabArray();
        }
        return 0;
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList list) {
        if (list == null) {
            if (this.props.isSetExtLst()) {
                this.props.unsetExtLst();
            }
        } else {
            this.props.setExtLst(list.getXmlObject());
        }
    }
}

