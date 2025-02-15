/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTControlList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTControlList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.w3c.dom.Node;

public interface CTCommonSlideData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCommonSlideData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcommonslidedata8c7ftype");

    public CTBackground getBg();

    public boolean isSetBg();

    public void setBg(CTBackground var1);

    public CTBackground addNewBg();

    public void unsetBg();

    public CTGroupShape getSpTree();

    public void setSpTree(CTGroupShape var1);

    public CTGroupShape addNewSpTree();

    public CTCustomerDataList getCustDataLst();

    public boolean isSetCustDataLst();

    public void setCustDataLst(CTCustomerDataList var1);

    public CTCustomerDataList addNewCustDataLst();

    public void unsetCustDataLst();

    public CTControlList getControls();

    public boolean isSetControls();

    public void setControls(CTControlList var1);

    public CTControlList addNewControls();

    public void unsetControls();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCommonSlideData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCommonSlideData newInstance() {
            return (CTCommonSlideData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCommonSlideData newInstance(XmlOptions xmlOptions) {
            return (CTCommonSlideData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCommonSlideData parse(String string) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCommonSlideData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCommonSlideData parse(File file) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCommonSlideData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCommonSlideData parse(URL uRL) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCommonSlideData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCommonSlideData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCommonSlideData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCommonSlideData parse(Reader reader) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCommonSlideData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCommonSlideData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCommonSlideData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCommonSlideData parse(Node node) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCommonSlideData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCommonSlideData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCommonSlideData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCommonSlideData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

