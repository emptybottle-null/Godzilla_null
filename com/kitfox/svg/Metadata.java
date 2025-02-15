/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;

public class Metadata
extends SVGElement {
    public static final String TAG_NAME = "metadata";

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public boolean updateTime(double curTime) {
        return false;
    }
}

