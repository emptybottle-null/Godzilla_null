/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FillElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.Stop;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Gradient
extends FillElement {
    public static final String TAG_NAME = "gradient";
    public static final int SM_PAD = 0;
    public static final int SM_REPEAT = 1;
    public static final int SM_REFLECT = 2;
    int spreadMethod = 0;
    public static final int GU_OBJECT_BOUNDING_BOX = 0;
    public static final int GU_USER_SPACE_ON_USE = 1;
    protected int gradientUnits = 0;
    ArrayList<Stop> stops = new ArrayList();
    URI stopRef = null;
    protected AffineTransform gradientTransform = null;
    float[] stopFractions;
    Color[] stopColors;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
        if (!(child instanceof Stop)) {
            return;
        }
        this.appendStop((Stop)child);
    }

    @Override
    protected void build() throws SVGException {
        String strn;
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("spreadMethod"))) {
            strn = sty.getStringValue().toLowerCase();
            this.spreadMethod = strn.equals("repeat") ? 1 : (strn.equals("reflect") ? 2 : 0);
        }
        if (this.getPres(sty.setName("gradientUnits"))) {
            strn = sty.getStringValue().toLowerCase();
            this.gradientUnits = strn.equals("userspaceonuse") ? 1 : 0;
        }
        if (this.getPres(sty.setName("gradientTransform"))) {
            this.gradientTransform = Gradient.parseTransform(sty.getStringValue());
        }
        if (this.gradientTransform == null) {
            this.gradientTransform = new AffineTransform();
        }
        if (this.getPres(sty.setName("xlink:href"))) {
            try {
                this.stopRef = sty.getURIValue(this.getXMLBase());
            } catch (Exception e) {
                throw new SVGException("Could not resolve relative URL in Gradient: " + sty.getStringValue() + ", " + this.getXMLBase(), e);
            }
        }
    }

    private void buildStops() {
        ArrayList<Stop> stopList = new ArrayList<Stop>(this.stops);
        stopList.sort(new Comparator<Stop>(){

            @Override
            public int compare(Stop o1, Stop o2) {
                return Float.compare(o1.offset, o2.offset);
            }
        });
        for (int i = stopList.size() - 2; i > 0; --i) {
            if (stopList.get((int)(i + 1)).offset != stopList.get((int)i).offset) continue;
            stopList.remove(i + 1);
        }
        this.stopFractions = new float[stopList.size()];
        this.stopColors = new Color[stopList.size()];
        int idx = 0;
        for (Stop stop : stopList) {
            Color stopColor;
            int stopColorVal = stop.color.getRGB();
            this.stopColors[idx] = stopColor = new Color(stopColorVal >> 16 & 0xFF, stopColorVal >> 8 & 0xFF, stopColorVal & 0xFF, this.clamp((int)(stop.opacity * 255.0f), 0, 255));
            this.stopFractions[idx] = stop.offset;
            ++idx;
        }
    }

    public float[] getStopFractions() {
        if (this.stopRef != null) {
            Gradient grad = (Gradient)this.diagram.getUniverse().getElement(this.stopRef);
            return grad.getStopFractions();
        }
        if (this.stopFractions != null) {
            return this.stopFractions;
        }
        this.buildStops();
        return this.stopFractions;
    }

    public Color[] getStopColors() {
        if (this.stopRef != null) {
            Gradient grad = (Gradient)this.diagram.getUniverse().getElement(this.stopRef);
            return grad.getStopColors();
        }
        if (this.stopColors != null) {
            return this.stopColors;
        }
        this.buildStops();
        return this.stopColors;
    }

    private int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public void setStopRef(URI grad) {
        this.stopRef = grad;
    }

    public void appendStop(Stop stop) {
        this.stops.add(stop);
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        String strn;
        int newVal;
        boolean stateChange = false;
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("spreadMethod")) && this.spreadMethod != (newVal = (strn = sty.getStringValue().toLowerCase()).equals("repeat") ? 1 : (strn.equals("reflect") ? 2 : 0))) {
            this.spreadMethod = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("gradientUnits")) && (newVal = (strn = sty.getStringValue().toLowerCase()).equals("userspaceonuse") ? 1 : 0) != this.gradientUnits) {
            this.gradientUnits = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("gradientTransform")) && (newVal = Gradient.parseTransform(sty.getStringValue())) != null && newVal.equals(this.gradientTransform)) {
            this.gradientTransform = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("xlink:href"))) {
            try {
                URI newVal2 = sty.getURIValue(this.getXMLBase());
                if (newVal2 == null && this.stopRef != null || !newVal2.equals(this.stopRef)) {
                    this.stopRef = newVal2;
                    stateChange = true;
                }
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse xlink:href", e);
            }
        }
        for (Stop stop : this.stops) {
            if (!stop.updateTime(curTime)) continue;
            stateChange = true;
            this.stopFractions = null;
            this.stopColors = null;
        }
        return stateChange;
    }
}

