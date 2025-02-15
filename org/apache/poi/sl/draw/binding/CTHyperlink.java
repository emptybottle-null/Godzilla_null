/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTEmbeddedWAVAudioFile;
import org.apache.poi.sl.draw.binding.CTOfficeArtExtensionList;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Hyperlink", propOrder={"snd", "extLst"})
public class CTHyperlink {
    protected CTEmbeddedWAVAudioFile snd;
    protected CTOfficeArtExtensionList extLst;
    @XmlAttribute(name="id", namespace="http://schemas.openxmlformats.org/officeDocument/2006/relationships")
    protected String id;
    @XmlAttribute(name="invalidUrl")
    protected String invalidUrl;
    @XmlAttribute(name="action")
    protected String action;
    @XmlAttribute(name="tgtFrame")
    protected String tgtFrame;
    @XmlAttribute(name="tooltip")
    protected String tooltip;
    @XmlAttribute(name="history")
    protected Boolean history;
    @XmlAttribute(name="highlightClick")
    protected Boolean highlightClick;
    @XmlAttribute(name="endSnd")
    protected Boolean endSnd;

    public CTEmbeddedWAVAudioFile getSnd() {
        return this.snd;
    }

    public void setSnd(CTEmbeddedWAVAudioFile value) {
        this.snd = value;
    }

    public boolean isSetSnd() {
        return this.snd != null;
    }

    public CTOfficeArtExtensionList getExtLst() {
        return this.extLst;
    }

    public void setExtLst(CTOfficeArtExtensionList value) {
        this.extLst = value;
    }

    public boolean isSetExtLst() {
        return this.extLst != null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public String getInvalidUrl() {
        if (this.invalidUrl == null) {
            return "";
        }
        return this.invalidUrl;
    }

    public void setInvalidUrl(String value) {
        this.invalidUrl = value;
    }

    public boolean isSetInvalidUrl() {
        return this.invalidUrl != null;
    }

    public String getAction() {
        if (this.action == null) {
            return "";
        }
        return this.action;
    }

    public void setAction(String value) {
        this.action = value;
    }

    public boolean isSetAction() {
        return this.action != null;
    }

    public String getTgtFrame() {
        if (this.tgtFrame == null) {
            return "";
        }
        return this.tgtFrame;
    }

    public void setTgtFrame(String value) {
        this.tgtFrame = value;
    }

    public boolean isSetTgtFrame() {
        return this.tgtFrame != null;
    }

    public String getTooltip() {
        if (this.tooltip == null) {
            return "";
        }
        return this.tooltip;
    }

    public void setTooltip(String value) {
        this.tooltip = value;
    }

    public boolean isSetTooltip() {
        return this.tooltip != null;
    }

    public boolean isHistory() {
        if (this.history == null) {
            return true;
        }
        return this.history;
    }

    public void setHistory(boolean value) {
        this.history = value;
    }

    public boolean isSetHistory() {
        return this.history != null;
    }

    public void unsetHistory() {
        this.history = null;
    }

    public boolean isHighlightClick() {
        if (this.highlightClick == null) {
            return false;
        }
        return this.highlightClick;
    }

    public void setHighlightClick(boolean value) {
        this.highlightClick = value;
    }

    public boolean isSetHighlightClick() {
        return this.highlightClick != null;
    }

    public void unsetHighlightClick() {
        this.highlightClick = null;
    }

    public boolean isEndSnd() {
        if (this.endSnd == null) {
            return false;
        }
        return this.endSnd;
    }

    public void setEndSnd(boolean value) {
        this.endSnd = value;
    }

    public boolean isSetEndSnd() {
        return this.endSnd != null;
    }

    public void unsetEndSnd() {
        this.endSnd = null;
    }
}

