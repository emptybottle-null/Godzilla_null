/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class VariantBool {
    private static final POILogger LOG = POILogFactory.getLogger(VariantBool.class);
    static final int SIZE = 2;
    private boolean _value;

    public void read(LittleEndianByteArrayInputStream lei) {
        short value = lei.readShort();
        switch (value) {
            case 0: {
                this._value = false;
                break;
            }
            case -1: {
                this._value = true;
                break;
            }
            default: {
                LOG.log(5, "VARIANT_BOOL value '" + value + "' is incorrect");
                this._value = true;
            }
        }
    }

    public boolean getValue() {
        return this._value;
    }

    public void setValue(boolean value) {
        this._value = value;
    }
}

