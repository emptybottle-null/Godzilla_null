/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MasterContentsDocument;
import java.io.IOException;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.XDGFBaseContents;
import org.apache.poi.xdgf.usermodel.XDGFMaster;
import org.apache.xmlbeans.XmlException;

public class XDGFMasterContents
extends XDGFBaseContents {
    protected XDGFMaster _master;

    public XDGFMasterContents(PackagePart part) {
        super(part);
    }

    @Override
    protected void onDocumentRead() {
        try {
            try {
                this._pageContents = MasterContentsDocument.Factory.parse(this.getPackagePart().getInputStream()).getMasterContents();
            } catch (IOException | XmlException e) {
                throw new POIXMLException(e);
            }
            super.onDocumentRead();
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this, e);
        }
    }

    public XDGFMaster getMaster() {
        return this._master;
    }

    protected void setMaster(XDGFMaster master) {
        this._master = master;
    }
}

