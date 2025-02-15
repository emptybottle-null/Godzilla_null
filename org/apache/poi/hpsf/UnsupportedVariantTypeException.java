/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.Variant;
import org.apache.poi.hpsf.VariantTypeException;
import org.apache.poi.util.HexDump;

public abstract class UnsupportedVariantTypeException
extends VariantTypeException {
    public UnsupportedVariantTypeException(long variantType, Object value) {
        super(variantType, value, "HPSF does not yet support the variant type " + variantType + " (" + Variant.getVariantName(variantType) + ", " + HexDump.toHex(variantType) + "). If you want support for this variant type in one of the next POI releases please submit a request for enhancement (RFE) to <http://issues.apache.org/bugzilla/>! Thank you!");
    }
}

