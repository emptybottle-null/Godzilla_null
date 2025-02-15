/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_SphereCoords")
public class CTSphereCoords {
    @XmlAttribute(name="lat", required=true)
    protected int lat;
    @XmlAttribute(name="lon", required=true)
    protected int lon;
    @XmlAttribute(name="rev", required=true)
    protected int rev;

    public int getLat() {
        return this.lat;
    }

    public void setLat(int value) {
        this.lat = value;
    }

    public boolean isSetLat() {
        return true;
    }

    public int getLon() {
        return this.lon;
    }

    public void setLon(int value) {
        this.lon = value;
    }

    public boolean isSetLon() {
        return true;
    }

    public int getRev() {
        return this.rev;
    }

    public void setRev(int value) {
        this.rev = value;
    }

    public boolean isSetRev() {
        return true;
    }
}

