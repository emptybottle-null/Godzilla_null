/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml;

import com.kitfox.svg.xml.XMLParseUtil;
import java.io.Serializable;

public class NumberWithUnits
implements Serializable {
    public static final long serialVersionUID = 0L;
    public static final int UT_UNITLESS = 0;
    public static final int UT_PX = 1;
    public static final int UT_CM = 2;
    public static final int UT_MM = 3;
    public static final int UT_IN = 4;
    public static final int UT_EM = 5;
    public static final int UT_EX = 6;
    public static final int UT_PT = 7;
    public static final int UT_PC = 8;
    public static final int UT_PERCENT = 9;
    float value = 0.0f;
    int unitType = 0;

    public NumberWithUnits() {
    }

    public NumberWithUnits(String value) {
        this.set(value);
    }

    public NumberWithUnits(float value, int unitType) {
        this.value = value;
        this.unitType = unitType;
    }

    public float getValue() {
        return this.value;
    }

    public int getUnits() {
        return this.unitType;
    }

    public void set(String value) {
        this.value = XMLParseUtil.findFloat(value);
        this.unitType = 0;
        if (value.indexOf("px") != -1) {
            this.unitType = 1;
            return;
        }
        if (value.indexOf("cm") != -1) {
            this.unitType = 2;
            return;
        }
        if (value.indexOf("mm") != -1) {
            this.unitType = 3;
            return;
        }
        if (value.indexOf("in") != -1) {
            this.unitType = 4;
            return;
        }
        if (value.indexOf("em") != -1) {
            this.unitType = 5;
            return;
        }
        if (value.indexOf("ex") != -1) {
            this.unitType = 6;
            return;
        }
        if (value.indexOf("pt") != -1) {
            this.unitType = 7;
            return;
        }
        if (value.indexOf("pc") != -1) {
            this.unitType = 8;
            return;
        }
        if (value.indexOf("%") != -1) {
            this.unitType = 9;
            return;
        }
    }

    public static String unitsAsString(int unitIdx) {
        switch (unitIdx) {
            default: {
                return "";
            }
            case 1: {
                return "px";
            }
            case 2: {
                return "cm";
            }
            case 3: {
                return "mm";
            }
            case 4: {
                return "in";
            }
            case 5: {
                return "em";
            }
            case 6: {
                return "ex";
            }
            case 7: {
                return "pt";
            }
            case 8: {
                return "pc";
            }
            case 9: 
        }
        return "%";
    }

    public String toString() {
        return "" + this.value + NumberWithUnits.unitsAsString(this.unitType);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        NumberWithUnits other = (NumberWithUnits)obj;
        if (Float.floatToIntBits(this.value) != Float.floatToIntBits(other.value)) {
            return false;
        }
        return this.unitType == other.unitType;
    }

    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Float.floatToIntBits(this.value);
        hash = 37 * hash + this.unitType;
        return hash;
    }
}

