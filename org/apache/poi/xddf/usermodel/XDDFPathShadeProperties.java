/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.PathShadeType;
import org.apache.poi.xddf.usermodel.XDDFRelativeRectangle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;

public class XDDFPathShadeProperties {
    private CTPathShadeProperties props;

    public XDDFPathShadeProperties() {
        this(CTPathShadeProperties.Factory.newInstance());
    }

    protected XDDFPathShadeProperties(CTPathShadeProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTPathShadeProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillToRectangle() {
        if (this.props.isSetFillToRect()) {
            return new XDDFRelativeRectangle(this.props.getFillToRect());
        }
        return null;
    }

    public void setFillToRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetFillToRect()) {
                this.props.unsetFillToRect();
            }
        } else {
            this.props.setFillToRect(rectangle.getXmlObject());
        }
    }

    public PathShadeType getPathShadeType() {
        if (this.props.isSetPath()) {
            return PathShadeType.valueOf(this.props.getPath());
        }
        return null;
    }

    public void setPathShadeType(PathShadeType path) {
        if (path == null) {
            if (this.props.isSetPath()) {
                this.props.unsetPath();
            }
        } else {
            this.props.setPath(path.underlying);
        }
    }
}

