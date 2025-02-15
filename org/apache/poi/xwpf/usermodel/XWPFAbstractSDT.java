/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.ISDTContent;
import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

public abstract class XWPFAbstractSDT
implements ISDTContents {
    private final String title;
    private final String tag;
    private final IBody part;

    public XWPFAbstractSDT(CTSdtPr pr, IBody part) {
        if (pr == null) {
            this.title = "";
            this.tag = "";
        } else {
            CTString[] aliases = pr.getAliasArray();
            this.title = aliases != null && aliases.length > 0 ? aliases[0].getVal() : "";
            CTString[] tags = pr.getTagArray();
            this.tag = tags != null && tags.length > 0 ? tags[0].getVal() : "";
        }
        this.part = part;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTag() {
        return this.tag;
    }

    public abstract ISDTContent getContent();

    public IBody getBody() {
        return null;
    }

    public POIXMLDocumentPart getPart() {
        return this.part.getPart();
    }

    public BodyType getPartType() {
        return BodyType.CONTENTCONTROL;
    }

    public BodyElementType getElementType() {
        return BodyElementType.CONTENTCONTROL;
    }

    public XWPFDocument getDocument() {
        return this.part.getXWPFDocument();
    }
}

