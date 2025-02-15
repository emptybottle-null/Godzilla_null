/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument;

public class MapInfoDocumentImpl
extends XmlComplexContentImpl
implements MapInfoDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MAPINFO$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "MapInfo");

    public MapInfoDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMapInfo getMapInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMapInfo cTMapInfo = null;
            cTMapInfo = (CTMapInfo)((Object)this.get_store().find_element_user(MAPINFO$0, 0));
            if (cTMapInfo == null) {
                return null;
            }
            return cTMapInfo;
        }
    }

    @Override
    public void setMapInfo(CTMapInfo cTMapInfo) {
        this.generatedSetterHelperImpl(cTMapInfo, MAPINFO$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMapInfo addNewMapInfo() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMapInfo cTMapInfo = null;
            cTMapInfo = (CTMapInfo)((Object)this.get_store().add_element_user(MAPINFO$0));
            return cTMapInfo;
        }
    }
}

