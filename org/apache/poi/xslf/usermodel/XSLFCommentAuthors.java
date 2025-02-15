/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

public class XSLFCommentAuthors
extends POIXMLDocumentPart {
    private final CTCommentAuthorList _authors;

    XSLFCommentAuthors() {
        CmAuthorLstDocument doc = CmAuthorLstDocument.Factory.newInstance();
        this._authors = doc.addNewCmAuthorLst();
    }

    XSLFCommentAuthors(PackagePart part) throws IOException, XmlException {
        super(part);
        CmAuthorLstDocument doc = CmAuthorLstDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._authors = doc.getCmAuthorLst();
    }

    public CTCommentAuthorList getCTCommentAuthorsList() {
        return this._authors;
    }

    public CTCommentAuthor getAuthorById(long id) {
        for (CTCommentAuthor author : this._authors.getCmAuthorArray()) {
            if (author.getId() != id) continue;
            return author;
        }
        return null;
    }
}

