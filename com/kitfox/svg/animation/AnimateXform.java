/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimateBase;
import java.awt.geom.AffineTransform;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class AnimateXform
extends AnimateBase {
    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        super.loaderStartElement(helper, attrs, parent);
    }

    public abstract AffineTransform eval(AffineTransform var1, double var2);
}

