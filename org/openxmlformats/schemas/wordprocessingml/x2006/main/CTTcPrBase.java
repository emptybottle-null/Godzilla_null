/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.w3c.dom.Node;

public interface CTTcPrBase
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTcPrBase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttcprbase93e6type");

    public CTCnf getCnfStyle();

    public boolean isSetCnfStyle();

    public void setCnfStyle(CTCnf var1);

    public CTCnf addNewCnfStyle();

    public void unsetCnfStyle();

    public CTTblWidth getTcW();

    public boolean isSetTcW();

    public void setTcW(CTTblWidth var1);

    public CTTblWidth addNewTcW();

    public void unsetTcW();

    public CTDecimalNumber getGridSpan();

    public boolean isSetGridSpan();

    public void setGridSpan(CTDecimalNumber var1);

    public CTDecimalNumber addNewGridSpan();

    public void unsetGridSpan();

    public CTHMerge getHMerge();

    public boolean isSetHMerge();

    public void setHMerge(CTHMerge var1);

    public CTHMerge addNewHMerge();

    public void unsetHMerge();

    public CTVMerge getVMerge();

    public boolean isSetVMerge();

    public void setVMerge(CTVMerge var1);

    public CTVMerge addNewVMerge();

    public void unsetVMerge();

    public CTTcBorders getTcBorders();

    public boolean isSetTcBorders();

    public void setTcBorders(CTTcBorders var1);

    public CTTcBorders addNewTcBorders();

    public void unsetTcBorders();

    public CTShd getShd();

    public boolean isSetShd();

    public void setShd(CTShd var1);

    public CTShd addNewShd();

    public void unsetShd();

    public CTOnOff getNoWrap();

    public boolean isSetNoWrap();

    public void setNoWrap(CTOnOff var1);

    public CTOnOff addNewNoWrap();

    public void unsetNoWrap();

    public CTTcMar getTcMar();

    public boolean isSetTcMar();

    public void setTcMar(CTTcMar var1);

    public CTTcMar addNewTcMar();

    public void unsetTcMar();

    public CTTextDirection getTextDirection();

    public boolean isSetTextDirection();

    public void setTextDirection(CTTextDirection var1);

    public CTTextDirection addNewTextDirection();

    public void unsetTextDirection();

    public CTOnOff getTcFitText();

    public boolean isSetTcFitText();

    public void setTcFitText(CTOnOff var1);

    public CTOnOff addNewTcFitText();

    public void unsetTcFitText();

    public CTVerticalJc getVAlign();

    public boolean isSetVAlign();

    public void setVAlign(CTVerticalJc var1);

    public CTVerticalJc addNewVAlign();

    public void unsetVAlign();

    public CTOnOff getHideMark();

    public boolean isSetHideMark();

    public void setHideMark(CTOnOff var1);

    public CTOnOff addNewHideMark();

    public void unsetHideMark();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTcPrBase.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTcPrBase newInstance() {
            return (CTTcPrBase)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTcPrBase newInstance(XmlOptions xmlOptions) {
            return (CTTcPrBase)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTcPrBase parse(String string) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTcPrBase parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTcPrBase parse(File file) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTcPrBase parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTcPrBase parse(URL uRL) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTcPrBase parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTcPrBase parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTcPrBase parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTcPrBase parse(Reader reader) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTcPrBase parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTcPrBase parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTcPrBase parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTcPrBase parse(Node node) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTcPrBase parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTcPrBase parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTcPrBase parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTcPrBase)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

