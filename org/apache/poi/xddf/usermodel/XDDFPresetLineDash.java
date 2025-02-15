/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.PresetLineDash;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;

public class XDDFPresetLineDash {
    private CTPresetLineDashProperties props;

    public XDDFPresetLineDash(PresetLineDash dash) {
        this(CTPresetLineDashProperties.Factory.newInstance());
        this.setValue(dash);
    }

    protected XDDFPresetLineDash(CTPresetLineDashProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTPresetLineDashProperties getXmlObject() {
        return this.props;
    }

    public PresetLineDash getValue() {
        if (this.props.isSetVal()) {
            return PresetLineDash.valueOf(this.props.getVal());
        }
        return null;
    }

    public void setValue(PresetLineDash dash) {
        if (dash == null) {
            if (this.props.isSetVal()) {
                this.props.unsetVal();
            }
        } else {
            this.props.setVal(dash.underlying);
        }
    }
}

