/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.animation.AnimateBase;
import com.kitfox.svg.animation.AnimateColorIface;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.AnimationTimeEval;
import com.kitfox.svg.animation.TrackBase;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;

public class TrackColor
extends TrackBase {
    public TrackColor(AnimationElement ele) throws SVGElementException {
        super(ele.getParent(), ele);
    }

    @Override
    public boolean getValue(StyleAttribute attrib, double curTime) {
        Color col = this.getValue(curTime);
        if (col == null) {
            return false;
        }
        attrib.setStringValue("#" + Integer.toHexString(col.getRGB()));
        return true;
    }

    public Color getValue(double curTime) {
        Color retVal = null;
        AnimationTimeEval state = new AnimationTimeEval();
        for (AnimationElement animationElement : this.animEvents) {
            AnimateBase ele = (AnimateBase)animationElement;
            AnimateColorIface eleColor = (AnimateColorIface)((Object)ele);
            ele.evalParametric(state, curTime);
            if (Double.isNaN(state.interp)) continue;
            if (retVal == null) {
                retVal = eleColor.evalColor(state.interp);
                continue;
            }
            Color curCol = eleColor.evalColor(state.interp);
            switch (ele.getAdditiveType()) {
                case 0: {
                    retVal = curCol;
                    break;
                }
                case 1: {
                    retVal = new Color(curCol.getRed() + retVal.getRed(), curCol.getGreen() + retVal.getGreen(), curCol.getBlue() + retVal.getBlue());
                }
            }
        }
        return retVal;
    }
}

