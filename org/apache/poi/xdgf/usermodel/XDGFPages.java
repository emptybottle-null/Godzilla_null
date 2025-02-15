/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.microsoft.schemas.office.visio.x2012.main.PagesDocument;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XDGFPageContents;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;
import org.apache.xmlbeans.XmlException;

public class XDGFPages
extends XDGFXMLDocumentPart {
    PagesType _pagesObject;
    List<XDGFPage> _pages = new ArrayList<XDGFPage>();

    public XDGFPages(PackagePart part) {
        super(part);
    }

    @Internal
    PagesType getXmlObject() {
        return this._pagesObject;
    }

    @Override
    protected void onDocumentRead() {
        try {
            try {
                this._pagesObject = PagesDocument.Factory.parse(this.getPackagePart().getInputStream()).getPages();
            } catch (IOException | XmlException e) {
                throw new POIXMLException(e);
            }
            for (PageType pageSettings : this._pagesObject.getPageArray()) {
                String relId = pageSettings.getRel().getId();
                POIXMLDocumentPart pageContentsPart = this.getRelationById(relId);
                if (pageContentsPart == null) {
                    throw new POIXMLException("PageSettings relationship for " + relId + " not found");
                }
                if (!(pageContentsPart instanceof XDGFPageContents)) {
                    throw new POIXMLException("Unexpected pages relationship for " + relId + ": " + pageContentsPart);
                }
                XDGFPageContents contents = (XDGFPageContents)pageContentsPart;
                XDGFPage page = new XDGFPage(pageSettings, contents, this._document, this);
                contents.onDocumentRead();
                this._pages.add(page);
            }
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this, e);
        }
    }

    public List<XDGFPage> getPageList() {
        return Collections.unmodifiableList(this._pages);
    }
}

