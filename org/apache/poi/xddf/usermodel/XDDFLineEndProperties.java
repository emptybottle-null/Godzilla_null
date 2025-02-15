/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.LineEndLength;
import org.apache.poi.xddf.usermodel.LineEndType;
import org.apache.poi.xddf.usermodel.LineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;

public class XDDFLineEndProperties {
    private CTLineEndProperties props;

    protected XDDFLineEndProperties(CTLineEndProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTLineEndProperties getXmlObject() {
        return this.props;
    }

    public LineEndLength getLength() {
        return LineEndLength.valueOf(this.props.getLen());
    }

    public void setLength(LineEndLength length) {
        this.props.setLen(length.underlying);
    }

    public LineEndType getType() {
        return LineEndType.valueOf(this.props.getType());
    }

    public void setType(LineEndType type) {
        this.props.setType(type.underlying);
    }

    public LineEndWidth getWidth() {
        return LineEndWidth.valueOf(this.props.getW());
    }

    public void setWidth(LineEndWidth width) {
        this.props.setW(width.underlying);
    }
}

