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
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_SRgbColor", propOrder={"egColorTransform"})
public class CTSRgbColor {
    @XmlElementRefs(value={@XmlElementRef(name="blue", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="gamma", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="blueOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="satOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="green", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="sat", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="greenOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="redOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hueMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alphaMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="comp", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="satMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="red", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hue", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alphaOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="gray", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="blueMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="alpha", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lumOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="redMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="hueOff", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="greenMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="tint", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="invGamma", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="shade", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lumMod", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="inv", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false), @XmlElementRef(name="lum", namespace="http://schemas.openxmlformats.org/drawingml/2006/main", type=JAXBElement.class, required=false)})
    protected List<JAXBElement<?>> egColorTransform;
    @XmlAttribute(name="val", required=true)
    @XmlJavaTypeAdapter(value=HexBinaryAdapter.class)
    protected byte[] val;

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

    public byte[] getVal() {
        return this.val;
    }

    public void setVal(byte[] value) {
        this.val = value;
    }

    public boolean isSetVal() {
        return this.val != null;
    }
}

