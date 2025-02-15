/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.usermodel.XDGFDocument;
import org.apache.poi.xdgf.usermodel.XDGFFactory;
import org.apache.poi.xdgf.usermodel.XDGFMasters;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XDGFPages;
import org.apache.poi.xdgf.usermodel.XDGFStyleSheet;
import org.apache.xmlbeans.XmlException;

public class XmlVisioDocument
extends POIXMLDocument {
    protected XDGFPages _pages;
    protected XDGFMasters _masters;
    protected XDGFDocument _document;

    public XmlVisioDocument(OPCPackage pkg) throws IOException {
        super(pkg, "http://schemas.microsoft.com/visio/2010/relationships/document");
        VisioDocumentType document;
        try {
            document = VisioDocumentDocument1.Factory.parse(this.getPackagePart().getInputStream()).getVisioDocument();
        } catch (IOException | XmlException e) {
            throw new POIXMLException(e);
        }
        this._document = new XDGFDocument(document);
        this.load(new XDGFFactory(this._document));
    }

    public XmlVisioDocument(InputStream is) throws IOException {
        this(PackageHelper.open(is));
    }

    @Override
    protected void onDocumentRead() {
        for (POIXMLDocumentPart part : this.getRelations()) {
            if (part instanceof XDGFPages) {
                this._pages = (XDGFPages)part;
                continue;
            }
            if (!(part instanceof XDGFMasters)) continue;
            this._masters = (XDGFMasters)part;
        }
        if (this._masters != null) {
            this._masters.onDocumentRead();
        }
        this._pages.onDocumentRead();
    }

    @Override
    public List<PackagePart> getAllEmbeddedParts() {
        return new ArrayList<PackagePart>();
    }

    public Collection<XDGFPage> getPages() {
        return this._pages.getPageList();
    }

    public XDGFStyleSheet getStyleById(long id) {
        return this._document.getStyleById(id);
    }
}

