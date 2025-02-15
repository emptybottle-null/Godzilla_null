/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFTitle {
    private final CTTitle title;
    private final TextContainer parent;

    public XDDFTitle(TextContainer parent, CTTitle title) {
        this.parent = parent;
        this.title = title;
    }

    public XDDFTextBody getBody() {
        CTTx tx;
        if (!this.title.isSetTx()) {
            this.title.addNewTx();
        }
        if ((tx = this.title.getTx()).isSetStrRef()) {
            tx.unsetStrRef();
        }
        if (!tx.isSetRich()) {
            tx.addNewRich();
        }
        return new XDDFTextBody(this.parent, tx.getRich());
    }

    public void setText(String text) {
        if (!this.title.isSetLayout()) {
            this.title.addNewLayout();
        }
        this.getBody().setText(text);
    }

    public void setOverlay(Boolean overlay) {
        if (overlay == null) {
            if (this.title.isSetOverlay()) {
                this.title.unsetOverlay();
            }
        } else if (this.title.isSetOverlay()) {
            this.title.getOverlay().setVal(overlay);
        } else {
            this.title.addNewOverlay().setVal(overlay);
        }
    }

    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text = this.title.isSetTxPr() ? this.title.getTxPr() : this.title.addNewTxPr();
        return new XDDFRunProperties(this.getOrAddTextProperties(text));
    }

    private CTTextCharacterProperties getOrAddTextProperties(CTTextBody body) {
        if (body.getBodyPr() == null) {
            body.addNewBodyPr();
        }
        CTTextParagraph paragraph = body.sizeOfPArray() > 0 ? body.getPArray(0) : body.addNewP();
        CTTextParagraphProperties paraprops = paragraph.isSetPPr() ? paragraph.getPPr() : paragraph.addNewPPr();
        CTTextCharacterProperties properties = paraprops.isSetDefRPr() ? paraprops.getDefRPr() : paraprops.addNewDefRPr();
        return properties;
    }
}

