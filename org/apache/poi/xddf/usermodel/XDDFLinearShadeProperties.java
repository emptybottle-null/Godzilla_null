/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.Angles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;

public class XDDFLinearShadeProperties {
    private CTLinearShadeProperties props;

    protected XDDFLinearShadeProperties(CTLinearShadeProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTLinearShadeProperties getXmlObject() {
        return this.props;
    }

    public Double getAngle() {
        if (this.props.isSetAng()) {
            return Angles.attributeToDegrees(this.props.getAng());
        }
        return null;
    }

    public void setAngle(Double angle) {
        if (angle == null) {
            if (this.props.isSetAng()) {
                this.props.unsetAng();
            }
        } else {
            if (angle < 0.0 || 360.0 <= angle) {
                throw new IllegalArgumentException("angle must be in the range [0, 360).");
            }
            this.props.setAng(Angles.degreesToAttribute(angle));
        }
    }

    public Boolean isScaled() {
        if (this.props.isSetScaled()) {
            return this.props.getScaled();
        }
        return null;
    }

    public void setScaled(Boolean scaled) {
        if (scaled == null) {
            if (this.props.isSetScaled()) {
                this.props.unsetScaled();
            }
        } else {
            this.props.setScaled(scaled);
        }
    }
}

