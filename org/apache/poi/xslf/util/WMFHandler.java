/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.util;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.EMFHandler;

@Internal
class WMFHandler
extends EMFHandler {
    WMFHandler() {
    }

    @Override
    protected String getContentType() {
        return PictureData.PictureType.WMF.contentType;
    }
}

