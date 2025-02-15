/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPicture;
import org.apache.poi.xddf.usermodel.XDDFRelativeRectangle;
import org.apache.poi.xddf.usermodel.XDDFStretchInfoProperties;
import org.apache.poi.xddf.usermodel.XDDFTileInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;

public class XDDFPictureFillProperties
implements XDDFFillProperties {
    private CTBlipFillProperties props;

    public XDDFPictureFillProperties() {
        this(CTBlipFillProperties.Factory.newInstance());
    }

    protected XDDFPictureFillProperties(CTBlipFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTBlipFillProperties getXmlObject() {
        return this.props;
    }

    public XDDFPicture getPicture() {
        if (this.props.isSetBlip()) {
            return new XDDFPicture(this.props.getBlip());
        }
        return null;
    }

    public void setPicture(XDDFPicture picture) {
        if (picture == null) {
            this.props.unsetBlip();
        } else {
            this.props.setBlip(picture.getXmlObject());
        }
    }

    public Boolean isRotatingWithShape() {
        if (this.props.isSetRotWithShape()) {
            return this.props.getRotWithShape();
        }
        return null;
    }

    public void setRotatingWithShape(Boolean rotating) {
        if (rotating == null) {
            if (this.props.isSetRotWithShape()) {
                this.props.unsetRotWithShape();
            }
        } else {
            this.props.setRotWithShape(rotating);
        }
    }

    public Long getDpi() {
        if (this.props.isSetDpi()) {
            return this.props.getDpi();
        }
        return null;
    }

    public void setDpi(Long dpi) {
        if (dpi == null) {
            if (this.props.isSetDpi()) {
                this.props.unsetDpi();
            }
        } else {
            this.props.setDpi(dpi);
        }
    }

    public XDDFRelativeRectangle getSourceRectangle() {
        if (this.props.isSetSrcRect()) {
            return new XDDFRelativeRectangle(this.props.getSrcRect());
        }
        return null;
    }

    public void setSourceRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetSrcRect()) {
                this.props.unsetSrcRect();
            }
        } else {
            this.props.setSrcRect(rectangle.getXmlObject());
        }
    }

    public XDDFStretchInfoProperties getStetchInfoProperties() {
        if (this.props.isSetStretch()) {
            return new XDDFStretchInfoProperties(this.props.getStretch());
        }
        return null;
    }

    public void setStretchInfoProperties(XDDFStretchInfoProperties properties) {
        if (properties == null) {
            if (this.props.isSetStretch()) {
                this.props.unsetStretch();
            }
        } else {
            this.props.setStretch(properties.getXmlObject());
        }
    }

    public XDDFTileInfoProperties getTileInfoProperties() {
        if (this.props.isSetTile()) {
            return new XDDFTileInfoProperties(this.props.getTile());
        }
        return null;
    }

    public void setTileInfoProperties(XDDFTileInfoProperties properties) {
        if (properties == null) {
            if (this.props.isSetTile()) {
                this.props.unsetTile();
            }
        } else {
            this.props.setTile(properties.getXmlObject());
        }
    }
}

