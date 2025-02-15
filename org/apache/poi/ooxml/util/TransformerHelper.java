/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.util;

import javax.xml.transform.TransformerFactory;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;

@Removal(version="5.0.0")
@Deprecated
public final class TransformerHelper {
    private TransformerHelper() {
    }

    public static TransformerFactory getFactory() {
        return XMLHelper.getTransformerFactory();
    }
}

