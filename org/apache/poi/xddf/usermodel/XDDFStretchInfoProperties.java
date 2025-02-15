/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFRelativeRectangle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;

public class XDDFStretchInfoProperties {
    private CTStretchInfoProperties props;

    protected XDDFStretchInfoProperties(CTStretchInfoProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTStretchInfoProperties getXmlObject() {
        return this.props;
    }

    public XDDFRelativeRectangle getFillRectangle() {
        if (this.props.isSetFillRect()) {
            return new XDDFRelativeRectangle(this.props.getFillRect());
        }
        return null;
    }

    public void setFillRectangle(XDDFRelativeRectangle rectangle) {
        if (rectangle == null) {
            if (this.props.isSetFillRect()) {
                this.props.unsetFillRect();
            }
        } else {
            this.props.setFillRect(rectangle.getXmlObject());
        }
    }
}

