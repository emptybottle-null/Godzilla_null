/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;

public class Title
extends SVGElement {
    public static final String TAG_NAME = "title";
    StringBuffer text = new StringBuffer();

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddText(SVGLoaderHelper helper, String text) {
        this.text.append(text);
    }

    public String getText() {
        return this.text.toString();
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }
}

