/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnotesEndnotes;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public abstract class XWPFAbstractFootnoteEndnote
implements Iterable<XWPFParagraph>,
IBody {
    private List<XWPFParagraph> paragraphs = new ArrayList<XWPFParagraph>();
    private List<XWPFTable> tables = new ArrayList<XWPFTable>();
    private List<XWPFPictureData> pictures = new ArrayList<XWPFPictureData>();
    private List<IBodyElement> bodyElements = new ArrayList<IBodyElement>();
    protected CTFtnEdn ctFtnEdn;
    protected XWPFAbstractFootnotesEndnotes footnotes;
    protected XWPFDocument document;

    public XWPFAbstractFootnoteEndnote() {
    }

    @Internal
    protected XWPFAbstractFootnoteEndnote(XWPFDocument document, CTFtnEdn body) {
        this.ctFtnEdn = body;
        this.document = document;
        this.init();
    }

    @Internal
    protected XWPFAbstractFootnoteEndnote(CTFtnEdn note, XWPFAbstractFootnotesEndnotes footnotes) {
        this.footnotes = footnotes;
        this.ctFtnEdn = note;
        this.document = footnotes.getXWPFDocument();
        this.init();
    }

    protected void init() {
        XmlCursor cursor = this.ctFtnEdn.newCursor();
        cursor.selectPath("./*");
        while (cursor.toNextSelection()) {
            XmlObject o = cursor.getObject();
            if (o instanceof CTP) {
                XWPFParagraph p = new XWPFParagraph((CTP)o, this);
                this.bodyElements.add(p);
                this.paragraphs.add(p);
                continue;
            }
            if (o instanceof CTTbl) {
                XWPFTable t = new XWPFTable((CTTbl)o, this);
                this.bodyElements.add(t);
                this.tables.add(t);
                continue;
            }
            if (!(o instanceof CTSdtBlock)) continue;
            XWPFSDT c = new XWPFSDT((CTSdtBlock)o, (IBody)this);
            this.bodyElements.add(c);
        }
        cursor.dispose();
    }

    @Override
    public List<XWPFParagraph> getParagraphs() {
        return this.paragraphs;
    }

    @Override
    public Iterator<XWPFParagraph> iterator() {
        return this.paragraphs.iterator();
    }

    @Override
    public List<XWPFTable> getTables() {
        return this.tables;
    }

    public List<XWPFPictureData> getPictures() {
        return this.pictures;
    }

    @Override
    public List<IBodyElement> getBodyElements() {
        return this.bodyElements;
    }

    public CTFtnEdn getCTFtnEdn() {
        return this.ctFtnEdn;
    }

    public void setCTFtnEdn(CTFtnEdn footnote) {
        this.ctFtnEdn = footnote;
    }

    @Override
    public XWPFTable getTableArray(int pos) {
        if (pos >= 0 && pos < this.tables.size()) {
            return this.tables.get(pos);
        }
        return null;
    }

    @Override
    public void insertTable(int pos, XWPFTable table) {
        this.bodyElements.add(pos, table);
        int i = 0;
        for (CTTbl tbl : this.ctFtnEdn.getTblList()) {
            if (tbl == table.getCTTbl()) break;
            ++i;
        }
        this.tables.add(i, table);
    }

    @Override
    public XWPFTable getTable(CTTbl ctTable) {
        for (XWPFTable table : this.tables) {
            if (table == null) {
                return null;
            }
            if (!table.getCTTbl().equals(ctTable)) continue;
            return table;
        }
        return null;
    }

    @Override
    public XWPFParagraph getParagraph(CTP p) {
        for (XWPFParagraph paragraph : this.paragraphs) {
            if (!paragraph.getCTP().equals(p)) continue;
            return paragraph;
        }
        return null;
    }

    @Override
    public XWPFParagraph getParagraphArray(int pos) {
        if (pos >= 0 && pos < this.paragraphs.size()) {
            return this.paragraphs.get(pos);
        }
        return null;
    }

    @Override
    public XWPFTableCell getTableCell(CTTc cell) {
        XmlCursor cursor = cell.newCursor();
        cursor.toParent();
        XmlObject o = cursor.getObject();
        if (!(o instanceof CTRow)) {
            return null;
        }
        CTRow row = (CTRow)o;
        cursor.toParent();
        o = cursor.getObject();
        cursor.dispose();
        if (!(o instanceof CTTbl)) {
            return null;
        }
        CTTbl tbl = (CTTbl)o;
        XWPFTable table = this.getTable(tbl);
        if (table == null) {
            return null;
        }
        XWPFTableRow tableRow = table.getRow(row);
        if (tableRow == null) {
            return null;
        }
        return tableRow.getTableCell(cell);
    }

    private boolean isCursorInFtn(XmlCursor cursor) {
        XmlCursor verify = cursor.newCursor();
        verify.toParent();
        return verify.getObject() == this.ctFtnEdn;
    }

    public POIXMLDocumentPart getOwner() {
        return this.footnotes;
    }

    @Override
    public XWPFTable insertNewTbl(XmlCursor cursor) {
        if (this.isCursorInFtn(cursor)) {
            String uri = CTTbl.type.getName().getNamespaceURI();
            String localPart = "tbl";
            cursor.beginElement(localPart, uri);
            cursor.toParent();
            CTTbl t = (CTTbl)cursor.getObject();
            XWPFTable newT = new XWPFTable(t, this);
            cursor.removeXmlContents();
            XmlObject o = null;
            while (!(o instanceof CTTbl) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTTbl)) {
                this.tables.add(0, newT);
            } else {
                int pos = this.tables.indexOf(this.getTable((CTTbl)o)) + 1;
                this.tables.add(pos, newT);
            }
            int i = 0;
            cursor = t.newCursor();
            while (cursor.toPrevSibling()) {
                o = cursor.getObject();
                if (!(o instanceof CTP) && !(o instanceof CTTbl)) continue;
                ++i;
            }
            this.bodyElements.add(i, newT);
            XmlCursor c2 = t.newCursor();
            cursor.toCursor(c2);
            cursor.toEndToken();
            c2.dispose();
            return newT;
        }
        return null;
    }

    @Override
    public XWPFParagraph insertNewParagraph(XmlCursor cursor) {
        if (this.isCursorInFtn(cursor)) {
            String uri = CTP.type.getName().getNamespaceURI();
            String localPart = "p";
            cursor.beginElement(localPart, uri);
            cursor.toParent();
            CTP p = (CTP)cursor.getObject();
            XWPFParagraph newP = new XWPFParagraph(p, this);
            XmlObject o = null;
            while (!(o instanceof CTP) && cursor.toPrevSibling()) {
                o = cursor.getObject();
            }
            if (!(o instanceof CTP) || o == p) {
                this.paragraphs.add(0, newP);
            } else {
                int pos = this.paragraphs.indexOf(this.getParagraph((CTP)o)) + 1;
                this.paragraphs.add(pos, newP);
            }
            int i = 0;
            XmlCursor p2 = p.newCursor();
            cursor.toCursor(p2);
            p2.dispose();
            while (cursor.toPrevSibling()) {
                o = cursor.getObject();
                if (!(o instanceof CTP) && !(o instanceof CTTbl)) continue;
                ++i;
            }
            this.bodyElements.add(i, newP);
            p2 = p.newCursor();
            cursor.toCursor(p2);
            cursor.toEndToken();
            p2.dispose();
            return newP;
        }
        return null;
    }

    public XWPFTable addNewTbl(CTTbl table) {
        CTTbl newTable = this.ctFtnEdn.addNewTbl();
        newTable.set(table);
        XWPFTable xTable = new XWPFTable(newTable, this);
        this.tables.add(xTable);
        return xTable;
    }

    public XWPFParagraph addNewParagraph(CTP paragraph) {
        CTP newPara = this.ctFtnEdn.addNewP();
        newPara.set(paragraph);
        XWPFParagraph xPara = new XWPFParagraph(newPara, this);
        this.paragraphs.add(xPara);
        return xPara;
    }

    @Override
    public XWPFDocument getXWPFDocument() {
        return this.document;
    }

    @Override
    public POIXMLDocumentPart getPart() {
        return this.footnotes;
    }

    @Override
    public BodyType getPartType() {
        return BodyType.FOOTNOTE;
    }

    public BigInteger getId() {
        return this.ctFtnEdn.getId();
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph p = new XWPFParagraph(this.ctFtnEdn.addNewP(), this);
        this.paragraphs.add(p);
        this.bodyElements.add(p);
        if (p.equals(this.getParagraphs().get(0))) {
            this.ensureFootnoteRef(p);
        }
        return p;
    }

    public abstract void ensureFootnoteRef(XWPFParagraph var1);

    public XWPFTable createTable() {
        XWPFTable table = new XWPFTable(this.ctFtnEdn.addNewTbl(), this);
        if (this.bodyElements.size() == 0) {
            XWPFParagraph p = this.createParagraph();
            this.ensureFootnoteRef(p);
        }
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }

    public XWPFTable createTable(int rows, int cols) {
        XWPFTable table = new XWPFTable(this.ctFtnEdn.addNewTbl(), this, rows, cols);
        this.bodyElements.add(table);
        this.tables.add(table);
        return table;
    }
}

