/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.util.Objects;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
public class XSSFHyperlinkRecord {
    private final CellRangeAddress cellRangeAddress;
    private final String relId;
    private String location;
    private String toolTip;
    private String display;

    XSSFHyperlinkRecord(CellRangeAddress cellRangeAddress, String relId, String location, String toolTip, String display) {
        this.cellRangeAddress = cellRangeAddress;
        this.relId = relId;
        this.location = location;
        this.toolTip = toolTip;
        this.display = display;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    void setDisplay(String display) {
        this.display = display;
    }

    CellRangeAddress getCellRangeAddress() {
        return this.cellRangeAddress;
    }

    public String getRelId() {
        return this.relId;
    }

    public String getLocation() {
        return this.location;
    }

    public String getToolTip() {
        return this.toolTip;
    }

    public String getDisplay() {
        return this.display;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        XSSFHyperlinkRecord that = (XSSFHyperlinkRecord)o;
        if (this.cellRangeAddress != null ? !this.cellRangeAddress.equals(that.cellRangeAddress) : that.cellRangeAddress != null) {
            return false;
        }
        if (this.relId != null ? !this.relId.equals(that.relId) : that.relId != null) {
            return false;
        }
        if (this.location != null ? !this.location.equals(that.location) : that.location != null) {
            return false;
        }
        if (this.toolTip != null ? !this.toolTip.equals(that.toolTip) : that.toolTip != null) {
            return false;
        }
        return this.display != null ? this.display.equals(that.display) : that.display == null;
    }

    public int hashCode() {
        return Objects.hash(this.cellRangeAddress, this.relId, this.location, this.toolTip, this.display);
    }

    public String toString() {
        return "XSSFHyperlinkRecord{cellRangeAddress=" + this.cellRangeAddress + ", relId='" + this.relId + '\'' + ", location='" + this.location + '\'' + ", toolTip='" + this.toolTip + '\'' + ", display='" + this.display + '\'' + '}';
    }
}

