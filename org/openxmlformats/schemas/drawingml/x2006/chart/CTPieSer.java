/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTPieSer
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPieSer.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpieser5248type");

    public CTUnsignedInt getIdx();

    public void setIdx(CTUnsignedInt var1);

    public CTUnsignedInt addNewIdx();

    public CTUnsignedInt getOrder();

    public void setOrder(CTUnsignedInt var1);

    public CTUnsignedInt addNewOrder();

    public CTSerTx getTx();

    public boolean isSetTx();

    public void setTx(CTSerTx var1);

    public CTSerTx addNewTx();

    public void unsetTx();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTUnsignedInt getExplosion();

    public boolean isSetExplosion();

    public void setExplosion(CTUnsignedInt var1);

    public CTUnsignedInt addNewExplosion();

    public void unsetExplosion();

    public List<CTDPt> getDPtList();

    @Deprecated
    public CTDPt[] getDPtArray();

    public CTDPt getDPtArray(int var1);

    public int sizeOfDPtArray();

    public void setDPtArray(CTDPt[] var1);

    public void setDPtArray(int var1, CTDPt var2);

    public CTDPt insertNewDPt(int var1);

    public CTDPt addNewDPt();

    public void removeDPt(int var1);

    public CTDLbls getDLbls();

    public boolean isSetDLbls();

    public void setDLbls(CTDLbls var1);

    public CTDLbls addNewDLbls();

    public void unsetDLbls();

    public CTAxDataSource getCat();

    public boolean isSetCat();

    public void setCat(CTAxDataSource var1);

    public CTAxDataSource addNewCat();

    public void unsetCat();

    public CTNumDataSource getVal();

    public boolean isSetVal();

    public void setVal(CTNumDataSource var1);

    public CTNumDataSource addNewVal();

    public void unsetVal();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPieSer.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPieSer newInstance() {
            return (CTPieSer)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPieSer newInstance(XmlOptions xmlOptions) {
            return (CTPieSer)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPieSer parse(String string) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPieSer parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPieSer parse(File file) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPieSer parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPieSer parse(URL uRL) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPieSer parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPieSer parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPieSer parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPieSer parse(Reader reader) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPieSer parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPieSer)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPieSer parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPieSer parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPieSer parse(Node node) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPieSer parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPieSer)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPieSer parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPieSer)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPieSer parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPieSer)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

