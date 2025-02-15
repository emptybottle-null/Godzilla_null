/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.MastersDocument;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.XDGFMaster;
import org.apache.poi.xdgf.usermodel.XDGFMasterContents;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;
import org.apache.xmlbeans.XmlException;

public class XDGFMasters
extends XDGFXMLDocumentPart {
    MastersType _mastersObject;
    protected Map<Long, XDGFMaster> _masters = new HashMap<Long, XDGFMaster>();

    public XDGFMasters(PackagePart part) {
        super(part);
    }

    @Internal
    protected MastersType getXmlObject() {
        return this._mastersObject;
    }

    @Override
    protected void onDocumentRead() {
        try {
            try {
                this._mastersObject = MastersDocument.Factory.parse(this.getPackagePart().getInputStream()).getMasters();
            } catch (IOException | XmlException e) {
                throw new POIXMLException(e);
            }
            HashMap<String, MasterType> masterSettings = new HashMap<String, MasterType>();
            for (MasterType master : this._mastersObject.getMasterArray()) {
                masterSettings.put(master.getRel().getId(), master);
            }
            for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
                Object part = rp.getDocumentPart();
                String relId = rp.getRelationship().getId();
                MasterType settings = (MasterType)masterSettings.get(relId);
                if (settings == null) {
                    throw new POIXMLException("Master relationship for " + relId + " not found");
                }
                if (!(part instanceof XDGFMasterContents)) {
                    throw new POIXMLException("Unexpected masters relationship for " + relId + ": " + part);
                }
                XDGFMasterContents contents = (XDGFMasterContents)part;
                contents.onDocumentRead();
                XDGFMaster master = new XDGFMaster(settings, contents, this._document);
                this._masters.put(master.getID(), master);
            }
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this, e);
        }
    }

    public Collection<XDGFMaster> getMastersList() {
        return Collections.unmodifiableCollection(this._masters.values());
    }

    public XDGFMaster getMasterById(long masterId) {
        return this._masters.get(masterId);
    }
}

