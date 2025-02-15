/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFColorHsl;
import org.apache.poi.xddf.usermodel.XDDFColorPreset;
import org.apache.poi.xddf.usermodel.XDDFColorRgbBinary;
import org.apache.poi.xddf.usermodel.XDDFColorRgbPercent;
import org.apache.poi.xddf.usermodel.XDDFColorSchemeBased;
import org.apache.poi.xddf.usermodel.XDDFColorSystemDefined;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class XDDFSolidFillProperties
implements XDDFFillProperties {
    private CTSolidColorFillProperties props;

    public XDDFSolidFillProperties() {
        this(CTSolidColorFillProperties.Factory.newInstance());
    }

    public XDDFSolidFillProperties(XDDFColor color) {
        this(CTSolidColorFillProperties.Factory.newInstance());
        this.setColor(color);
    }

    @Internal
    public XDDFSolidFillProperties(CTSolidColorFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTSolidColorFillProperties getXmlObject() {
        return this.props;
    }

    public XDDFColor getColor() {
        if (this.props.isSetHslClr()) {
            return new XDDFColorHsl(this.props.getHslClr());
        }
        if (this.props.isSetPrstClr()) {
            return new XDDFColorPreset(this.props.getPrstClr());
        }
        if (this.props.isSetSchemeClr()) {
            return new XDDFColorSchemeBased(this.props.getSchemeClr());
        }
        if (this.props.isSetScrgbClr()) {
            return new XDDFColorRgbPercent(this.props.getScrgbClr());
        }
        if (this.props.isSetSrgbClr()) {
            return new XDDFColorRgbBinary(this.props.getSrgbClr());
        }
        if (this.props.isSetSysClr()) {
            return new XDDFColorSystemDefined(this.props.getSysClr());
        }
        return null;
    }

    public void setColor(XDDFColor color) {
        if (this.props.isSetHslClr()) {
            this.props.unsetHslClr();
        }
        if (this.props.isSetPrstClr()) {
            this.props.unsetPrstClr();
        }
        if (this.props.isSetSchemeClr()) {
            this.props.unsetSchemeClr();
        }
        if (this.props.isSetScrgbClr()) {
            this.props.unsetScrgbClr();
        }
        if (this.props.isSetSrgbClr()) {
            this.props.unsetSrgbClr();
        }
        if (this.props.isSetSysClr()) {
            this.props.unsetSysClr();
        }
        if (color == null) {
            return;
        }
        if (color instanceof XDDFColorHsl) {
            this.props.setHslClr((CTHslColor)color.getXmlObject());
        } else if (color instanceof XDDFColorPreset) {
            this.props.setPrstClr((CTPresetColor)color.getXmlObject());
        } else if (color instanceof XDDFColorSchemeBased) {
            this.props.setSchemeClr((CTSchemeColor)color.getXmlObject());
        } else if (color instanceof XDDFColorRgbPercent) {
            this.props.setScrgbClr((CTScRgbColor)color.getXmlObject());
        } else if (color instanceof XDDFColorRgbBinary) {
            this.props.setSrgbClr((CTSRgbColor)color.getXmlObject());
        } else if (color instanceof XDDFColorSystemDefined) {
            this.props.setSysClr((CTSystemColor)color.getXmlObject());
        }
    }
}

