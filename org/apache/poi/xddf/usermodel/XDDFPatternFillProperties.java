/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.PresetPattern;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;

public class XDDFPatternFillProperties
implements XDDFFillProperties {
    private CTPatternFillProperties props;

    public XDDFPatternFillProperties() {
        this(CTPatternFillProperties.Factory.newInstance());
    }

    protected XDDFPatternFillProperties(CTPatternFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTPatternFillProperties getXmlObject() {
        return this.props;
    }

    public PresetPattern getPresetPattern() {
        if (this.props.isSetPrst()) {
            return PresetPattern.valueOf(this.props.getPrst());
        }
        return null;
    }

    public void setPresetPattern(PresetPattern pattern) {
        if (pattern == null) {
            if (this.props.isSetPrst()) {
                this.props.unsetPrst();
            }
        } else {
            this.props.setPrst(pattern.underlying);
        }
    }

    public XDDFColor getBackgroundColor() {
        if (this.props.isSetBgClr()) {
            return XDDFColor.forColorContainer(this.props.getBgClr());
        }
        return null;
    }

    public void setBackgroundColor(XDDFColor color) {
        if (color == null) {
            if (this.props.isSetBgClr()) {
                this.props.unsetBgClr();
            }
        } else {
            this.props.setBgClr(color.getColorContainer());
        }
    }

    public XDDFColor getForegroundColor() {
        if (this.props.isSetFgClr()) {
            return XDDFColor.forColorContainer(this.props.getFgClr());
        }
        return null;
    }

    public void setForegroundColor(XDDFColor color) {
        if (color == null) {
            if (this.props.isSetFgClr()) {
                this.props.unsetFgClr();
            }
        } else {
            this.props.setFgClr(color.getColorContainer());
        }
    }
}

