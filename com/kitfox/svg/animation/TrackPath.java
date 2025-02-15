/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.animation.Animate;
import com.kitfox.svg.animation.AnimateBase;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.AnimationTimeEval;
import com.kitfox.svg.animation.TrackBase;
import com.kitfox.svg.pathcmd.PathUtil;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.geom.GeneralPath;

public class TrackPath
extends TrackBase {
    public TrackPath(AnimationElement ele) throws SVGElementException {
        super(ele.getParent(), ele);
    }

    @Override
    public boolean getValue(StyleAttribute attrib, double curTime) {
        GeneralPath path = this.getValue(curTime);
        if (path == null) {
            return false;
        }
        attrib.setStringValue(PathUtil.buildPathString(path));
        return true;
    }

    public GeneralPath getValue(double curTime) {
        GeneralPath retVal = null;
        AnimationTimeEval state = new AnimationTimeEval();
        block4: for (AnimationElement animationElement : this.animEvents) {
            AnimateBase ele = (AnimateBase)animationElement;
            Animate eleAnim = (Animate)ele;
            ele.evalParametric(state, curTime);
            if (Double.isNaN(state.interp)) continue;
            if (retVal == null) {
                retVal = eleAnim.evalPath(state.interp);
                continue;
            }
            GeneralPath curPath = eleAnim.evalPath(state.interp);
            switch (ele.getAdditiveType()) {
                case 0: {
                    retVal = curPath;
                    continue block4;
                }
                case 1: {
                    throw new RuntimeException("Not implemented");
                }
            }
            throw new RuntimeException();
        }
        return retVal;
    }
}

