/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.animation.AnimateXform;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.AnimationTimeEval;
import com.kitfox.svg.animation.TrackBase;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.geom.AffineTransform;

public class TrackTransform
extends TrackBase {
    public TrackTransform(AnimationElement ele) throws SVGElementException {
        super(ele.getParent(), ele);
    }

    @Override
    public boolean getValue(StyleAttribute attrib, double curTime) throws SVGException {
        AffineTransform retVal = new AffineTransform();
        retVal = this.getValue(retVal, curTime);
        double[] mat = new double[6];
        retVal.getMatrix(mat);
        attrib.setStringValue("matrix(" + mat[0] + " " + mat[1] + " " + mat[2] + " " + mat[3] + " " + mat[4] + " " + mat[5] + ")");
        return true;
    }

    public AffineTransform getValue(AffineTransform retVal, double curTime) throws SVGException {
        StyleAttribute attr = null;
        switch (this.attribType) {
            case 0: {
                attr = this.parent.getStyleAbsolute(this.attribName);
                retVal.setTransform(SVGElement.parseSingleTransform(attr.getStringValue()));
                break;
            }
            case 1: {
                attr = this.parent.getPresAbsolute(this.attribName);
                retVal.setTransform(SVGElement.parseSingleTransform(attr.getStringValue()));
                break;
            }
            case 2: {
                attr = this.parent.getStyleAbsolute(this.attribName);
                if (attr == null) {
                    attr = this.parent.getPresAbsolute(this.attribName);
                }
                retVal.setTransform(SVGElement.parseSingleTransform(attr.getStringValue()));
            }
        }
        AnimationTimeEval state = new AnimationTimeEval();
        AffineTransform xform = new AffineTransform();
        for (AnimationElement animationElement : this.animEvents) {
            AnimateXform ele = (AnimateXform)animationElement;
            ele.evalParametric(state, curTime);
            if (Double.isNaN(state.interp)) continue;
            switch (ele.getAdditiveType()) {
                case 1: {
                    retVal.concatenate(ele.eval(xform, state.interp));
                    break;
                }
                case 0: {
                    retVal.setTransform(ele.eval(xform, state.interp));
                }
            }
        }
        return retVal;
    }
}

