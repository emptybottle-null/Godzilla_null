/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.util.ArrayList;

public abstract class TrackBase {
    protected final String attribName;
    protected final int attribType;
    protected final SVGElement parent;
    final ArrayList<AnimationElement> animEvents = new ArrayList();

    public TrackBase(SVGElement parent, AnimationElement ele) throws SVGElementException {
        this(parent, ele.getAttribName(), ele.getAttribType());
    }

    public TrackBase(SVGElement parent, String attribName, int attribType) throws SVGElementException {
        this.parent = parent;
        this.attribName = attribName;
        this.attribType = attribType;
        if (attribType == 2 && !parent.hasAttribute(attribName, 0) && !parent.hasAttribute(attribName, 1)) {
            parent.addAttribute(attribName, 0, "");
        } else if (!parent.hasAttribute(attribName, attribType)) {
            parent.addAttribute(attribName, attribType, "");
        }
    }

    public String getAttribName() {
        return this.attribName;
    }

    public int getAttribType() {
        return this.attribType;
    }

    public void addElement(AnimationElement ele) {
        this.animEvents.add(ele);
    }

    public abstract boolean getValue(StyleAttribute var1, double var2) throws SVGException;
}

