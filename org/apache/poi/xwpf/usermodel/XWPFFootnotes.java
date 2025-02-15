/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnoteEndnote;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnotesEndnotes;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnEdn;

public class XWPFFootnotes
extends XWPFAbstractFootnotesEndnotes {
    protected CTFootnotes ctFootnotes;

    public XWPFFootnotes(PackagePart part) {
        super(part);
    }

    public XWPFFootnotes() {
    }

    @Internal
    public void setFootnotes(CTFootnotes footnotes) {
        this.ctFootnotes = footnotes;
    }

    public XWPFFootnote createFootnote() {
        CTFtnEdn newNote = CTFtnEdn.Factory.newInstance();
        newNote.setType(STFtnEdn.NORMAL);
        XWPFFootnote footnote = this.addFootnote(newNote);
        footnote.getCTFtnEdn().setId(this.getIdManager().nextId());
        return footnote;
    }

    public boolean removeFootnote(int pos) {
        if (this.ctFootnotes.sizeOfFootnoteArray() >= pos - 1) {
            this.ctFootnotes.removeFootnote(pos);
            this.listFootnote.remove(pos);
            return true;
        }
        return false;
    }

    @Override
    protected void onDocumentRead() throws IOException {
        try (InputStream is = this.getPackagePart().getInputStream();){
            FootnotesDocument notesDoc = FootnotesDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.ctFootnotes = notesDoc.getFootnotes();
        } catch (XmlException e) {
            throw new POIXMLException();
        }
        for (CTFtnEdn note : this.ctFootnotes.getFootnoteList()) {
            this.listFootnote.add(new XWPFFootnote(note, this));
        }
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTFootnotes.type.getName().getNamespaceURI(), "footnotes"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.ctFootnotes.save(out, xmlOptions);
        out.close();
    }

    public void addFootnote(XWPFFootnote footnote) {
        this.listFootnote.add(footnote);
        this.ctFootnotes.addNewFootnote().set(footnote.getCTFtnEdn());
    }

    @Internal
    public XWPFFootnote addFootnote(CTFtnEdn note) {
        CTFtnEdn newNote = this.ctFootnotes.addNewFootnote();
        newNote.set(note);
        XWPFFootnote xNote = new XWPFFootnote(newNote, this);
        this.listFootnote.add(xNote);
        return xNote;
    }

    public List<XWPFFootnote> getFootnotesList() {
        ArrayList<XWPFFootnote> resultList = new ArrayList<XWPFFootnote>();
        for (XWPFAbstractFootnoteEndnote note : this.listFootnote) {
            resultList.add((XWPFFootnote)note);
        }
        return resultList;
    }
}

