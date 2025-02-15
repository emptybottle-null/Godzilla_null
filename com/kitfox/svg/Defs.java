/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.TransformableElement;

public class Defs
extends TransformableElement {
    public static final String TAG_NAME = "defs";

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        boolean stateChange = false;
        for (SVGElement ele : this.children) {
            stateChange = stateChange || ele.updateTime(curTime);
        }
        return super.updateTime(curTime) || stateChange;
    }
}

