/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public interface IBody {
    public POIXMLDocumentPart getPart();

    public BodyType getPartType();

    public List<IBodyElement> getBodyElements();

    public List<XWPFParagraph> getParagraphs();

    public List<XWPFTable> getTables();

    public XWPFParagraph getParagraph(CTP var1);

    public XWPFTable getTable(CTTbl var1);

    public XWPFParagraph getParagraphArray(int var1);

    public XWPFTable getTableArray(int var1);

    public XWPFParagraph insertNewParagraph(XmlCursor var1);

    public XWPFTable insertNewTbl(XmlCursor var1);

    public void insertTable(int var1, XWPFTable var2);

    public XWPFTableCell getTableCell(CTTc var1);

    public XWPFDocument getXWPFDocument();
}

