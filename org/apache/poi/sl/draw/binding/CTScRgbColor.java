/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_ScRgbColor", propOrder={"egColorTransform"})
public class CTScRgbColor {
    @XmlElementRefs(value={@XmlElementRef(name="gamma", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="greenOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hueMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alphaOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="redOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="green", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="satMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="tint", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lumOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="greenMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alpha", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alphaMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lum", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hueOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="shade", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="invGamma", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="inv", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="comp", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="sat", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="blue", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="redMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hue", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="blueOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="satOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="gray", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lumMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="red", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="blueMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false)})
    protected List<JAXBElement<?>> egColorTransform;
    @XmlAttribute(name="r", required=true)
    protected int r;
    @XmlAttribute(name="g", required=true)
    protected int g;
    @XmlAttribute(name="b", required=true)
    protected int b;

    public List<JAXBElement<?>> getEGColorTransform() {
        if (this.egColorTransform == null) {
            this.egColorTransform = new ArrayList();
        }
        return this.egColorTransform;
    }

    public boolean isSetEGColorTransform() {
        return this.egColorTransform != null && !this.egColorTransform.isEmpty();
    }

    public void unsetEGColorTransform() {
        this.egColorTransform = null;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int value) {
        this.r = value;
    }

    public boolean isSetR() {
        return true;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int value) {
        this.g = value;
    }

    public boolean isSetG() {
        return true;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int value) {
        this.b = value;
    }

    public boolean isSetB() {
        return true;
    }
}

