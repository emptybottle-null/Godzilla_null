/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSchema;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument;

public class MapInfo
extends POIXMLDocumentPart {
    private CTMapInfo mapInfo;
    private Map<Integer, XSSFMap> maps;

    public MapInfo() {
        this.mapInfo = CTMapInfo.Factory.newInstance();
    }

    public MapInfo(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            MapInfoDocument doc = MapInfoDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.mapInfo = doc.getMapInfo();
            this.maps = new HashMap<Integer, XSSFMap>();
            for (CTMap map : this.mapInfo.getMapArray()) {
                this.maps.put((int)map.getID(), new XSSFMap(map, this));
            }
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFWorkbook getWorkbook() {
        return (XSSFWorkbook)this.getParent();
    }

    public CTMapInfo getCTMapInfo() {
        return this.mapInfo;
    }

    public CTSchema getCTSchemaById(String schemaId) {
        CTSchema xmlSchema = null;
        for (CTSchema schema : this.mapInfo.getSchemaArray()) {
            if (!schema.getID().equals(schemaId)) continue;
            xmlSchema = schema;
            break;
        }
        return xmlSchema;
    }

    public XSSFMap getXSSFMapById(int id) {
        return this.maps.get(id);
    }

    public XSSFMap getXSSFMapByName(String name) {
        XSSFMap matchedMap = null;
        for (XSSFMap map : this.maps.values()) {
            if (map.getCtMap().getName() == null || !map.getCtMap().getName().equals(name)) continue;
            matchedMap = map;
        }
        return matchedMap;
    }

    public Collection<XSSFMap> getAllXSSFMaps() {
        return this.maps.values();
    }

    protected void writeTo(OutputStream out) throws IOException {
        MapInfoDocument doc = MapInfoDocument.Factory.newInstance();
        doc.setMapInfo(this.mapInfo);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }
}

