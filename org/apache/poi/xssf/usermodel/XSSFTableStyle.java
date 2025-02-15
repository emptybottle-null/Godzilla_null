/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.ss.usermodel.TableStyleType;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFDxfStyleProvider;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleElement;

public class XSSFTableStyle
implements TableStyle {
    private static final POILogger logger = POILogFactory.getLogger(XSSFTableStyle.class);
    private final String name;
    private final int index;
    private final Map<TableStyleType, DifferentialStyleProvider> elementMap = new EnumMap<TableStyleType, DifferentialStyleProvider>(TableStyleType.class);

    public XSSFTableStyle(int index, CTDxfs dxfs, CTTableStyle tableStyle, IndexedColorMap colorMap) {
        this.name = tableStyle.getName();
        this.index = index;
        ArrayList<CTDxf> dxfList = new ArrayList<CTDxf>();
        XmlCursor cur = dxfs.newCursor();
        String xquery = "declare namespace x='http://schemas.openxmlformats.org/spreadsheetml/2006/main' .//x:dxf | .//dxf";
        cur.selectPath(xquery);
        while (cur.toNextSelection()) {
            XmlObject obj = cur.getObject();
            String parentName = obj.getDomNode().getParentNode().getNodeName();
            if (!parentName.equals("mc:Fallback") && !parentName.equals("x:dxfs") && !parentName.contentEquals("dxfs")) continue;
            try {
                CTDxf dxf = obj instanceof CTDxf ? (CTDxf)obj : CTDxf.Factory.parse(obj.newXMLStreamReader(), new XmlOptions().setDocumentType(CTDxf.type));
                if (dxf == null) continue;
                dxfList.add(dxf);
            } catch (XmlException e) {
                logger.log(5, "Error parsing XSSFTableStyle", e);
            }
        }
        for (CTTableStyleElement element : tableStyle.getTableStyleElementList()) {
            TableStyleType type = TableStyleType.valueOf(element.getType().toString());
            XSSFDxfStyleProvider dstyle = null;
            if (element.isSetDxfId()) {
                int idx = (int)element.getDxfId();
                CTDxf dxf = (CTDxf)dxfList.get(idx);
                int stripeSize = 0;
                if (element.isSetSize()) {
                    stripeSize = (int)element.getSize();
                }
                if (dxf != null) {
                    dstyle = new XSSFDxfStyleProvider(dxf, stripeSize, colorMap);
                }
            }
            this.elementMap.put(type, dstyle);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public DifferentialStyleProvider getStyle(TableStyleType type) {
        return this.elementMap.get((Object)type);
    }
}

