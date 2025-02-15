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
import org.apache.poi.xwpf.usermodel.XWPFEndnote;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnEdn;

public class XWPFEndnotes
extends XWPFAbstractFootnotesEndnotes {
    protected CTEndnotes ctEndnotes;

    public XWPFEndnotes() {
    }

    public XWPFEndnotes(PackagePart part) {
        super(part);
    }

    @Internal
    public void setEndnotes(CTEndnotes endnotes) {
        this.ctEndnotes = endnotes;
    }

    public XWPFEndnote createEndnote() {
        CTFtnEdn newNote = CTFtnEdn.Factory.newInstance();
        newNote.setType(STFtnEdn.NORMAL);
        XWPFEndnote footnote = this.addEndnote(newNote);
        footnote.getCTFtnEdn().setId(this.getIdManager().nextId());
        return footnote;
    }

    public boolean removeFootnote(int pos) {
        if (this.ctEndnotes.sizeOfEndnoteArray() >= pos - 1) {
            this.ctEndnotes.removeEndnote(pos);
            this.listFootnote.remove(pos);
            return true;
        }
        return false;
    }

    @Override
    protected void onDocumentRead() throws IOException {
        try (InputStream is = this.getPackagePart().getInputStream();){
            EndnotesDocument notesDoc = EndnotesDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.ctEndnotes = notesDoc.getEndnotes();
        } catch (XmlException e) {
            throw new POIXMLException();
        }
        for (CTFtnEdn note : this.ctEndnotes.getEndnoteList()) {
            this.listFootnote.add(new XWPFEndnote(note, this));
        }
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTEndnotes.type.getName().getNamespaceURI(), "endnotes"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.ctEndnotes.save(out, xmlOptions);
        out.close();
    }

    public void addEndnote(XWPFEndnote endnote) {
        this.listFootnote.add(endnote);
        this.ctEndnotes.addNewEndnote().set(endnote.getCTFtnEdn());
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn note) {
        CTFtnEdn newNote = this.ctEndnotes.addNewEndnote();
        newNote.set(note);
        XWPFEndnote xNote = new XWPFEndnote(newNote, this);
        this.listFootnote.add(xNote);
        return xNote;
    }

    @Override
    public XWPFEndnote getFootnoteById(int id) {
        return (XWPFEndnote)super.getFootnoteById(id);
    }

    public List<XWPFEndnote> getEndnotesList() {
        ArrayList<XWPFEndnote> resultList = new ArrayList<XWPFEndnote>();
        for (XWPFAbstractFootnoteEndnote note : this.listFootnote) {
            resultList.add((XWPFEndnote)note);
        }
        return resultList;
    }

    public boolean removeEndnote(int pos) {
        if (this.ctEndnotes.sizeOfEndnoteArray() >= pos - 1) {
            this.ctEndnotes.removeEndnote(pos);
            this.listFootnote.remove(pos);
            return true;
        }
        return false;
    }
}

